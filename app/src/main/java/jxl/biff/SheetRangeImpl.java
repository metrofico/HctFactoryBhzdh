package jxl.biff;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;

public class SheetRangeImpl implements Range {
   private int column1;
   private int column2;
   private int row1;
   private int row2;
   private Sheet sheet;

   public SheetRangeImpl(Sheet var1, int var2, int var3, int var4, int var5) {
      this.sheet = var1;
      this.row1 = var3;
      this.row2 = var5;
      this.column1 = var2;
      this.column2 = var4;
   }

   public SheetRangeImpl(SheetRangeImpl var1, Sheet var2) {
      this.sheet = var2;
      this.row1 = var1.row1;
      this.row2 = var1.row2;
      this.column1 = var1.column1;
      this.column2 = var1.column2;
   }

   public boolean equals(Object var1) {
      boolean var2 = true;
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof SheetRangeImpl)) {
         return false;
      } else {
         SheetRangeImpl var3 = (SheetRangeImpl)var1;
         if (this.column1 != var3.column1 || this.column2 != var3.column2 || this.row1 != var3.row1 || this.row2 != var3.row2) {
            var2 = false;
         }

         return var2;
      }
   }

   public Cell getBottomRight() {
      return (Cell)(this.column2 < this.sheet.getColumns() && this.row2 < this.sheet.getRows() ? this.sheet.getCell(this.column2, this.row2) : new EmptyCell(this.column2, this.row2));
   }

   public int getFirstSheetIndex() {
      return -1;
   }

   public int getLastSheetIndex() {
      return -1;
   }

   public Cell getTopLeft() {
      return (Cell)(this.column1 < this.sheet.getColumns() && this.row1 < this.sheet.getRows() ? this.sheet.getCell(this.column1, this.row1) : new EmptyCell(this.column1, this.row1));
   }

   public int hashCode() {
      return this.row1 ^ '\uffff' ^ this.row2 ^ this.column1 ^ this.column2;
   }

   public void insertColumn(int var1) {
      int var3 = this.column2;
      if (var1 <= var3) {
         int var2 = this.column1;
         if (var1 <= var2) {
            this.column1 = var2 + 1;
         }

         if (var1 <= var3) {
            this.column2 = var3 + 1;
         }

      }
   }

   public void insertRow(int var1) {
      int var2 = this.row2;
      if (var1 <= var2) {
         int var3 = this.row1;
         if (var1 <= var3) {
            this.row1 = var3 + 1;
         }

         if (var1 <= var2) {
            this.row2 = var2 + 1;
         }

      }
   }

   public boolean intersects(SheetRangeImpl var1) {
      if (var1 == this) {
         return true;
      } else {
         return this.row2 >= var1.row1 && this.row1 <= var1.row2 && this.column2 >= var1.column1 && this.column1 <= var1.column2;
      }
   }

   public void removeColumn(int var1) {
      int var3 = this.column2;
      if (var1 <= var3) {
         int var2 = this.column1;
         if (var1 < var2) {
            this.column1 = var2 - 1;
         }

         if (var1 < var3) {
            this.column2 = var3 - 1;
         }

      }
   }

   public void removeRow(int var1) {
      int var3 = this.row2;
      if (var1 <= var3) {
         int var2 = this.row1;
         if (var1 < var2) {
            this.row1 = var2 - 1;
         }

         if (var1 < var3) {
            this.row2 = var3 - 1;
         }

      }
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      CellReferenceHelper.getCellReference(this.column1, this.row1, var1);
      var1.append('-');
      CellReferenceHelper.getCellReference(this.column2, this.row2, var1);
      return var1.toString();
   }
}
