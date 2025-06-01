package org.apache.log4j.chainsaw;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

class EventDetails {
   private final String mCategoryName;
   private final String mLocationDetails;
   private final String mMessage;
   private final String mNDC;
   private final Priority mPriority;
   private final String mThreadName;
   private final String[] mThrowableStrRep;
   private final long mTimeStamp;

   EventDetails(long var1, Priority var3, String var4, String var5, String var6, String var7, String[] var8, String var9) {
      this.mTimeStamp = var1;
      this.mPriority = var3;
      this.mCategoryName = var4;
      this.mNDC = var5;
      this.mThreadName = var6;
      this.mMessage = var7;
      this.mThrowableStrRep = var8;
      this.mLocationDetails = var9;
   }

   EventDetails(LoggingEvent var1) {
      long var2 = var1.timeStamp;
      Level var7 = var1.getLevel();
      String var4 = var1.getLoggerName();
      String var5 = var1.getNDC();
      String var6 = var1.getThreadName();
      String var9 = var1.getRenderedMessage();
      String[] var8 = var1.getThrowableStrRep();
      String var10;
      if (var1.getLocationInformation() == null) {
         var10 = null;
      } else {
         var10 = var1.getLocationInformation().fullInfo;
      }

      this(var2, var7, var4, var5, var6, var9, var8, var10);
   }

   String getCategoryName() {
      return this.mCategoryName;
   }

   String getLocationDetails() {
      return this.mLocationDetails;
   }

   String getMessage() {
      return this.mMessage;
   }

   String getNDC() {
      return this.mNDC;
   }

   Priority getPriority() {
      return this.mPriority;
   }

   String getThreadName() {
      return this.mThreadName;
   }

   String[] getThrowableStrRep() {
      return this.mThrowableStrRep;
   }

   long getTimeStamp() {
      return this.mTimeStamp;
   }
}
