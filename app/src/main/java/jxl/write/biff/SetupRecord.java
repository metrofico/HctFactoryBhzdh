package jxl.write.biff;

import jxl.SheetSettings;
import jxl.biff.DoubleHelper;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Logger;
import jxl.format.PageOrder;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;

class SetupRecord extends WritableRecordData {
   private int copies;
   private byte[] data;
   private int fitHeight;
   private int fitWidth;
   private double footerMargin;
   private double headerMargin;
   private int horizontalPrintResolution;
   private boolean initialized;
   Logger logger = Logger.getLogger(SetupRecord.class);
   private PageOrder order;
   private PageOrientation orientation;
   private int pageStart;
   private int paperSize;
   private int scaleFactor;
   private int verticalPrintResolution;

   public SetupRecord(SheetSettings var1) {
      super(Type.SETUP);
      this.orientation = var1.getOrientation();
      this.order = var1.getPageOrder();
      this.headerMargin = var1.getHeaderMargin();
      this.footerMargin = var1.getFooterMargin();
      this.paperSize = var1.getPaperSize().getValue();
      this.horizontalPrintResolution = var1.getHorizontalPrintResolution();
      this.verticalPrintResolution = var1.getVerticalPrintResolution();
      this.fitWidth = var1.getFitWidth();
      this.fitHeight = var1.getFitHeight();
      this.pageStart = var1.getPageStart();
      this.scaleFactor = var1.getScaleFactor();
      this.copies = var1.getCopies();
      this.initialized = true;
   }

   public byte[] getData() {
      byte[] var3 = new byte[34];
      this.data = var3;
      int var1 = this.paperSize;
      int var2 = 0;
      IntegerHelper.getTwoBytes(var1, var3, 0);
      IntegerHelper.getTwoBytes(this.scaleFactor, this.data, 2);
      IntegerHelper.getTwoBytes(this.pageStart, this.data, 4);
      IntegerHelper.getTwoBytes(this.fitWidth, this.data, 6);
      IntegerHelper.getTwoBytes(this.fitHeight, this.data, 8);
      if (this.order == PageOrder.RIGHT_THEN_DOWN) {
         var2 = 1;
      }

      var1 = var2;
      if (this.orientation == PageOrientation.PORTRAIT) {
         var1 = var2 | 2;
      }

      var2 = var1;
      if (this.pageStart != 0) {
         var2 = var1 | 128;
      }

      var1 = var2;
      if (!this.initialized) {
         var1 = var2 | 4;
      }

      IntegerHelper.getTwoBytes(var1, this.data, 10);
      IntegerHelper.getTwoBytes(this.horizontalPrintResolution, this.data, 12);
      IntegerHelper.getTwoBytes(this.verticalPrintResolution, this.data, 14);
      DoubleHelper.getIEEEBytes(this.headerMargin, this.data, 16);
      DoubleHelper.getIEEEBytes(this.footerMargin, this.data, 24);
      IntegerHelper.getTwoBytes(this.copies, this.data, 32);
      return this.data;
   }

   public void setMargins(double var1, double var3) {
      this.headerMargin = var1;
      this.footerMargin = var3;
   }

   public void setOrder(PageOrder var1) {
      this.order = var1;
   }

   public void setOrientation(PageOrientation var1) {
      this.orientation = var1;
   }

   public void setPaperSize(PaperSize var1) {
      this.paperSize = var1.getValue();
   }
}
