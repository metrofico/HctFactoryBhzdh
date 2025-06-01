package org.apache.log4j.chainsaw;

import java.util.StringTokenizer;
import org.apache.log4j.Level;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class XMLFileHandler extends DefaultHandler {
   private static final String TAG_EVENT = "log4j:event";
   private static final String TAG_LOCATION_INFO = "log4j:locationInfo";
   private static final String TAG_MESSAGE = "log4j:message";
   private static final String TAG_NDC = "log4j:NDC";
   private static final String TAG_THROWABLE = "log4j:throwable";
   private final StringBuffer mBuf = new StringBuffer();
   private String mCategoryName;
   private Level mLevel;
   private String mLocationDetails;
   private String mMessage;
   private final MyTableModel mModel;
   private String mNDC;
   private int mNumEvents;
   private String mThreadName;
   private String[] mThrowableStrRep;
   private long mTimeStamp;

   XMLFileHandler(MyTableModel var1) {
      this.mModel = var1;
   }

   private void addEvent() {
      this.mModel.addEvent(new EventDetails(this.mTimeStamp, this.mLevel, this.mCategoryName, this.mNDC, this.mThreadName, this.mMessage, this.mThrowableStrRep, this.mLocationDetails));
      ++this.mNumEvents;
   }

   private void resetData() {
      this.mTimeStamp = 0L;
      this.mLevel = null;
      this.mCategoryName = null;
      this.mNDC = null;
      this.mThreadName = null;
      this.mMessage = null;
      this.mThrowableStrRep = null;
      this.mLocationDetails = null;
   }

   public void characters(char[] var1, int var2, int var3) {
      this.mBuf.append(String.valueOf(var1, var2, var3));
   }

   public void endElement(String var1, String var2, String var3) {
      if ("log4j:event".equals(var3)) {
         this.addEvent();
         this.resetData();
      } else if ("log4j:NDC".equals(var3)) {
         this.mNDC = this.mBuf.toString();
      } else if ("log4j:message".equals(var3)) {
         this.mMessage = this.mBuf.toString();
      } else if ("log4j:throwable".equals(var3)) {
         StringTokenizer var5 = new StringTokenizer(this.mBuf.toString(), "\n\t");
         String[] var6 = new String[var5.countTokens()];
         this.mThrowableStrRep = var6;
         if (var6.length > 0) {
            var6[0] = var5.nextToken();
            int var4 = 1;

            while(true) {
               var6 = this.mThrowableStrRep;
               if (var4 >= var6.length) {
                  break;
               }

               var6[var4] = "\t" + var5.nextToken();
               ++var4;
            }
         }
      }

   }

   int getNumEvents() {
      return this.mNumEvents;
   }

   public void startDocument() throws SAXException {
      this.mNumEvents = 0;
   }

   public void startElement(String var1, String var2, String var3, Attributes var4) {
      this.mBuf.setLength(0);
      if ("log4j:event".equals(var3)) {
         this.mThreadName = var4.getValue("thread");
         this.mTimeStamp = Long.parseLong(var4.getValue("timestamp"));
         this.mCategoryName = var4.getValue("logger");
         this.mLevel = Level.toLevel(var4.getValue("level"));
      } else if ("log4j:locationInfo".equals(var3)) {
         this.mLocationDetails = var4.getValue("class") + "." + var4.getValue("method") + "(" + var4.getValue("file") + ":" + var4.getValue("line") + ")";
      }

   }
}
