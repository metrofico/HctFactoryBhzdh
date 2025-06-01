package jxl.write.biff;

import jxl.biff.DisplayFormat;
import jxl.biff.FontRecord;
import jxl.biff.XFRecord;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.Orientation;
import jxl.format.Pattern;
import jxl.format.VerticalAlignment;
import jxl.write.WriteException;

public class CellXFRecord extends XFRecord {
   protected CellXFRecord(FontRecord var1, DisplayFormat var2) {
      super(var1, var2);
      this.setXFDetails(XFRecord.cell, 0);
   }

   CellXFRecord(XFRecord var1) {
      super(var1);
      this.setXFDetails(XFRecord.cell, 0);
   }

   protected CellXFRecord(CellFormat var1) {
      super(var1);
   }

   public void setAlignment(Alignment var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFAlignment(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setBackground(Colour var1, Pattern var2) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFBackground(var1, var2);
         super.setXFCellOptions(16384);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setBorder(Border var1, BorderLineStyle var2, Colour var3) throws WriteException {
      if (!this.isInitialized()) {
         if (var1 == Border.ALL) {
            super.setXFBorder(Border.LEFT, var2, var3);
            super.setXFBorder(Border.RIGHT, var2, var3);
            super.setXFBorder(Border.TOP, var2, var3);
            super.setXFBorder(Border.BOTTOM, var2, var3);
         } else if (var1 == Border.NONE) {
            super.setXFBorder(Border.LEFT, BorderLineStyle.NONE, Colour.BLACK);
            super.setXFBorder(Border.RIGHT, BorderLineStyle.NONE, Colour.BLACK);
            super.setXFBorder(Border.TOP, BorderLineStyle.NONE, Colour.BLACK);
            super.setXFBorder(Border.BOTTOM, BorderLineStyle.NONE, Colour.BLACK);
         } else {
            super.setXFBorder(var1, var2, var3);
         }
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setIndentation(int var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFIndentation(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setLocked(boolean var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFLocked(var1);
         super.setXFCellOptions(32768);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setOrientation(Orientation var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFOrientation(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setShrinkToFit(boolean var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFShrinkToFit(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setVerticalAlignment(VerticalAlignment var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFVerticalAlignment(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }

   public void setWrap(boolean var1) throws WriteException {
      if (!this.isInitialized()) {
         super.setXFWrap(var1);
      } else {
         throw new JxlWriteException(JxlWriteException.formatInitialized);
      }
   }
}
