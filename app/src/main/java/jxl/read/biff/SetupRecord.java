package jxl.read.biff;

import jxl.biff.DoubleHelper;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.Type;
import jxl.common.Logger;

public class SetupRecord extends RecordData {
   private static Logger logger = Logger.getLogger(SetupRecord.class);
   private int copies;
   private byte[] data;
   private int fitHeight;
   private int fitWidth;
   private double footerMargin;
   private double headerMargin;
   private int horizontalPrintResolution;
   private boolean initialized;
   private boolean pageOrder;
   private int pageStart;
   private int paperSize;
   private boolean portraitOrientation;
   private int scaleFactor;
   private int verticalPrintResolution;

   SetupRecord(Record var1) {
      super(Type.SETUP);
      byte[] var5 = var1.getData();
      this.data = var5;
      boolean var4 = false;
      this.paperSize = IntegerHelper.getInt(var5[0], var5[1]);
      var5 = this.data;
      this.scaleFactor = IntegerHelper.getInt(var5[2], var5[3]);
      var5 = this.data;
      this.pageStart = IntegerHelper.getInt(var5[4], var5[5]);
      var5 = this.data;
      this.fitWidth = IntegerHelper.getInt(var5[6], var5[7]);
      var5 = this.data;
      this.fitHeight = IntegerHelper.getInt(var5[8], var5[9]);
      var5 = this.data;
      this.horizontalPrintResolution = IntegerHelper.getInt(var5[12], var5[13]);
      var5 = this.data;
      this.verticalPrintResolution = IntegerHelper.getInt(var5[14], var5[15]);
      var5 = this.data;
      this.copies = IntegerHelper.getInt(var5[32], var5[33]);
      this.headerMargin = DoubleHelper.getIEEEDouble(this.data, 16);
      this.footerMargin = DoubleHelper.getIEEEDouble(this.data, 24);
      var5 = this.data;
      int var2 = IntegerHelper.getInt(var5[10], var5[11]);
      boolean var3;
      if ((var2 & 1) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.pageOrder = var3;
      if ((var2 & 2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.portraitOrientation = var3;
      var3 = var4;
      if ((var2 & 4) == 0) {
         var3 = true;
      }

      this.initialized = var3;
   }

   public int getCopies() {
      return this.copies;
   }

   public int getFitHeight() {
      return this.fitHeight;
   }

   public int getFitWidth() {
      return this.fitWidth;
   }

   public double getFooterMargin() {
      return this.footerMargin;
   }

   public double getHeaderMargin() {
      return this.headerMargin;
   }

   public int getHorizontalPrintResolution() {
      return this.horizontalPrintResolution;
   }

   public boolean getInitialized() {
      return this.initialized;
   }

   public int getPageStart() {
      return this.pageStart;
   }

   public int getPaperSize() {
      return this.paperSize;
   }

   public int getScaleFactor() {
      return this.scaleFactor;
   }

   public int getVerticalPrintResolution() {
      return this.verticalPrintResolution;
   }

   public boolean isPortrait() {
      return this.portraitOrientation;
   }

   public boolean isRightDown() {
      return this.pageOrder;
   }
}
