package jxl.read.biff;

import java.net.URL;
import jxl.Hyperlink;
import jxl.Range;
import jxl.Sheet;
import jxl.WorkbookSettings;
import jxl.biff.RecordData;
import jxl.biff.SheetRangeImpl;
import jxl.common.Logger;

public class HyperlinkRecord extends RecordData implements Hyperlink {
   private static final LinkType fileLink = new LinkType();
   private static Logger logger = Logger.getLogger(HyperlinkRecord.class);
   private static final LinkType unknown = new LinkType();
   private static final LinkType urlLink = new LinkType();
   private static final LinkType workbookLink = new LinkType();
   private java.io.File file;
   private int firstColumn;
   private int firstRow;
   private int lastColumn;
   private int lastRow;
   private LinkType linkType;
   private String location;
   private SheetRangeImpl range;
   private URL url;

   HyperlinkRecord(Record param1, Sheet param2, WorkbookSettings param3) {
      // $FF: Couldn't be decompiled
   }

   public int getColumn() {
      return this.firstColumn;
   }

   public java.io.File getFile() {
      return this.file;
   }

   public int getLastColumn() {
      return this.lastColumn;
   }

   public int getLastRow() {
      return this.lastRow;
   }

   public String getLocation() {
      return this.location;
   }

   public Range getRange() {
      return this.range;
   }

   public Record getRecord() {
      return super.getRecord();
   }

   public int getRow() {
      return this.firstRow;
   }

   public URL getURL() {
      return this.url;
   }

   public boolean isFile() {
      boolean var1;
      if (this.linkType == fileLink) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isLocation() {
      boolean var1;
      if (this.linkType == workbookLink) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isURL() {
      boolean var1;
      if (this.linkType == urlLink) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private static class LinkType {
      private LinkType() {
      }

      // $FF: synthetic method
      LinkType(Object var1) {
         this();
      }
   }
}
