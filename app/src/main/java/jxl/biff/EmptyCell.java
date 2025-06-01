package jxl.biff;

import jxl.CellFeatures;
import jxl.CellType;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCell;
import jxl.write.WritableCellFeatures;

public class EmptyCell implements WritableCell {
   private int col;
   private int row;

   public EmptyCell(int var1, int var2) {
      this.row = var2;
      this.col = var1;
   }

   public WritableCell copyTo(int var1, int var2) {
      return new EmptyCell(var1, var2);
   }

   public CellFeatures getCellFeatures() {
      return null;
   }

   public CellFormat getCellFormat() {
      return null;
   }

   public int getColumn() {
      return this.col;
   }

   public String getContents() {
      return "";
   }

   public int getRow() {
      return this.row;
   }

   public CellType getType() {
      return CellType.EMPTY;
   }

   public WritableCellFeatures getWritableCellFeatures() {
      return null;
   }

   public boolean isHidden() {
      return false;
   }

   public void setAlignment(Alignment var1) {
   }

   public void setBorder(Border var1, BorderLineStyle var2) {
   }

   public void setCellFeatures(WritableCellFeatures var1) {
   }

   public void setCellFormat(jxl.CellFormat var1) {
   }

   public void setCellFormat(CellFormat var1) {
   }

   public void setHidden(boolean var1) {
   }

   public void setLocked(boolean var1) {
   }

   public void setVerticalAlignment(VerticalAlignment var1) {
   }
}
