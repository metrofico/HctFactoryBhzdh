package jxl.biff;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.common.Logger;

public class RangeImpl implements Range {
   private static Logger logger = Logger.getLogger(RangeImpl.class);
   private int column1;
   private int column2;
   private int row1;
   private int row2;
   private int sheet1;
   private int sheet2;
   private WorkbookMethods workbook;

   public RangeImpl(WorkbookMethods var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.workbook = var1;
      this.sheet1 = var2;
      this.sheet2 = var5;
      this.row1 = var4;
      this.row2 = var7;
      this.column1 = var3;
      this.column2 = var6;
   }

   public Cell getBottomRight() {
      Sheet var1 = this.workbook.getReadSheet(this.sheet2);
      return (Cell)(this.column2 < var1.getColumns() && this.row2 < var1.getRows() ? var1.getCell(this.column2, this.row2) : new EmptyCell(this.column2, this.row2));
   }

   public int getFirstSheetIndex() {
      return this.sheet1;
   }

   public int getLastSheetIndex() {
      return this.sheet2;
   }

   public Cell getTopLeft() {
      Sheet var1 = this.workbook.getReadSheet(this.sheet1);
      return (Cell)(this.column1 < var1.getColumns() && this.row1 < var1.getRows() ? var1.getCell(this.column1, this.row1) : new EmptyCell(this.column1, this.row1));
   }
}
