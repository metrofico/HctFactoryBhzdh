package jxl.write;

import jxl.biff.DisplayFormat;
import jxl.format.CellFormat;
import jxl.format.Orientation;
import jxl.write.biff.CellXFRecord;

public class WritableCellFormat extends CellXFRecord {
   public WritableCellFormat() {
      this(WritableWorkbook.ARIAL_10_PT, NumberFormats.DEFAULT);
   }

   public WritableCellFormat(DisplayFormat var1) {
      this(WritableWorkbook.ARIAL_10_PT, var1);
   }

   public WritableCellFormat(CellFormat var1) {
      super(var1);
   }

   public WritableCellFormat(WritableFont var1) {
      this(var1, NumberFormats.DEFAULT);
   }

   public WritableCellFormat(WritableFont var1, DisplayFormat var2) {
      super(var1, var2);
   }

   public void setAlignment(jxl.format.Alignment var1) throws WriteException {
      super.setAlignment(var1);
   }

   public void setBackground(jxl.format.Colour var1) throws WriteException {
      this.setBackground(var1, jxl.format.Pattern.SOLID);
   }

   public void setBackground(jxl.format.Colour var1, jxl.format.Pattern var2) throws WriteException {
      super.setBackground(var1, var2);
   }

   public void setBorder(jxl.format.Border var1, jxl.format.BorderLineStyle var2) throws WriteException {
      super.setBorder(var1, var2, jxl.format.Colour.BLACK);
   }

   public void setBorder(jxl.format.Border var1, jxl.format.BorderLineStyle var2, jxl.format.Colour var3) throws WriteException {
      super.setBorder(var1, var2, var3);
   }

   public void setIndentation(int var1) throws WriteException {
      super.setIndentation(var1);
   }

   public void setLocked(boolean var1) throws WriteException {
      super.setLocked(var1);
   }

   public void setOrientation(Orientation var1) throws WriteException {
      super.setOrientation(var1);
   }

   public void setShrinkToFit(boolean var1) throws WriteException {
      super.setShrinkToFit(var1);
   }

   public void setVerticalAlignment(jxl.format.VerticalAlignment var1) throws WriteException {
      super.setVerticalAlignment(var1);
   }

   public void setWrap(boolean var1) throws WriteException {
      super.setWrap(var1);
   }
}
