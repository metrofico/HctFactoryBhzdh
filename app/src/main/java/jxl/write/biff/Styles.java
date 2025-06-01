package jxl.write.biff;

import jxl.biff.XFRecord;
import jxl.common.Logger;
import jxl.write.DateFormat;
import jxl.write.DateFormats;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableWorkbook;

class Styles {
   private static Logger logger = Logger.getLogger(Styles.class);
   private WritableFont arial10pt = null;
   private WritableCellFormat defaultDateFormat;
   private WritableCellFormat hiddenStyle = null;
   private WritableFont hyperlinkFont = null;
   private WritableCellFormat hyperlinkStyle = null;
   private WritableCellFormat normalStyle = null;

   public Styles() {
   }

   private void initArial10Pt() {
      synchronized(this){}

      try {
         WritableFont var1 = new WritableFont(WritableWorkbook.ARIAL_10_PT);
         this.arial10pt = var1;
      } finally {
         ;
      }

   }

   private void initDefaultDateFormat() {
      synchronized(this){}

      try {
         WritableCellFormat var1 = new WritableCellFormat(DateFormats.DEFAULT);
         this.defaultDateFormat = var1;
      } finally {
         ;
      }

   }

   private void initHiddenStyle() {
      synchronized(this){}

      try {
         WritableFont var2 = this.getArial10Pt();
         DateFormat var3 = new DateFormat(";;;");
         WritableCellFormat var1 = new WritableCellFormat(var2, var3);
         this.hiddenStyle = var1;
      } finally {
         ;
      }

   }

   private void initHyperlinkFont() {
      synchronized(this){}

      try {
         WritableFont var1 = new WritableFont(WritableWorkbook.HYPERLINK_FONT);
         this.hyperlinkFont = var1;
      } finally {
         ;
      }

   }

   private void initHyperlinkStyle() {
      synchronized(this){}

      try {
         WritableCellFormat var1 = new WritableCellFormat(this.getHyperlinkFont(), NumberFormats.DEFAULT);
         this.hyperlinkStyle = var1;
      } finally {
         ;
      }

   }

   private void initNormalStyle() {
      synchronized(this){}

      try {
         WritableCellFormat var1 = new WritableCellFormat(this.getArial10Pt(), NumberFormats.DEFAULT);
         this.normalStyle = var1;
         var1.setFont(this.getArial10Pt());
      } finally {
         ;
      }

   }

   public WritableFont getArial10Pt() {
      if (this.arial10pt == null) {
         this.initArial10Pt();
      }

      return this.arial10pt;
   }

   public WritableCellFormat getDefaultDateFormat() {
      if (this.defaultDateFormat == null) {
         this.initDefaultDateFormat();
      }

      return this.defaultDateFormat;
   }

   public XFRecord getFormat(XFRecord var1) {
      Object var2;
      if (var1 == WritableWorkbook.NORMAL_STYLE) {
         var2 = this.getNormalStyle();
      } else if (var1 == WritableWorkbook.HYPERLINK_STYLE) {
         var2 = this.getHyperlinkStyle();
      } else if (var1 == WritableWorkbook.HIDDEN_STYLE) {
         var2 = this.getHiddenStyle();
      } else {
         var2 = var1;
         if (var1 == DateRecord.defaultDateFormat) {
            var2 = this.getDefaultDateFormat();
         }
      }

      if (((XFRecord)var2).getFont() == WritableWorkbook.ARIAL_10_PT) {
         ((XFRecord)var2).setFont(this.getArial10Pt());
      } else if (((XFRecord)var2).getFont() == WritableWorkbook.HYPERLINK_FONT) {
         ((XFRecord)var2).setFont(this.getHyperlinkFont());
      }

      return (XFRecord)var2;
   }

   public WritableCellFormat getHiddenStyle() {
      if (this.hiddenStyle == null) {
         this.initHiddenStyle();
      }

      return this.hiddenStyle;
   }

   public WritableFont getHyperlinkFont() {
      if (this.hyperlinkFont == null) {
         this.initHyperlinkFont();
      }

      return this.hyperlinkFont;
   }

   public WritableCellFormat getHyperlinkStyle() {
      if (this.hyperlinkStyle == null) {
         this.initHyperlinkStyle();
      }

      return this.hyperlinkStyle;
   }

   public WritableCellFormat getNormalStyle() {
      if (this.normalStyle == null) {
         this.initNormalStyle();
      }

      return this.normalStyle;
   }
}
