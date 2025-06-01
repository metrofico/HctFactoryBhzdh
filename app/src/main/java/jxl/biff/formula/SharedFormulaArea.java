package jxl.biff.formula;

import jxl.Cell;
import jxl.biff.CellReferenceHelper;
import jxl.biff.IntegerHelper;

class SharedFormulaArea extends Operand implements ParsedThing {
   private int columnFirst;
   private boolean columnFirstRelative;
   private int columnLast;
   private boolean columnLastRelative;
   private Cell relativeTo;
   private int rowFirst;
   private boolean rowFirstRelative;
   private int rowLast;
   private boolean rowLastRelative;

   public SharedFormulaArea(Cell var1) {
      this.relativeTo = var1;
   }

   byte[] getBytes() {
      byte[] var1 = new byte[9];
      var1[0] = Token.AREA.getCode();
      IntegerHelper.getTwoBytes(this.rowFirst, var1, 1);
      IntegerHelper.getTwoBytes(this.rowLast, var1, 3);
      IntegerHelper.getTwoBytes(this.columnFirst, var1, 5);
      IntegerHelper.getTwoBytes(this.columnLast, var1, 7);
      return var1;
   }

   int getFirstColumn() {
      return this.columnFirst;
   }

   int getFirstRow() {
      return this.rowFirst;
   }

   int getLastColumn() {
      return this.columnLast;
   }

   int getLastRow() {
      return this.rowLast;
   }

   public void getString(StringBuffer var1) {
      CellReferenceHelper.getCellReference(this.columnFirst, this.rowFirst, var1);
      var1.append(':');
      CellReferenceHelper.getCellReference(this.columnLast, this.rowLast, var1);
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      this.rowFirst = IntegerHelper.getShort(var1[var2], var1[var2 + 1]);
      this.rowLast = IntegerHelper.getShort(var1[var2 + 2], var1[var2 + 3]);
      int var3 = IntegerHelper.getInt(var1[var2 + 4], var1[var2 + 5]);
      this.columnFirst = var3 & 255;
      boolean var6 = false;
      boolean var4;
      if ((var3 & 16384) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.columnFirstRelative = var4;
      boolean var5;
      if ((var3 & '耀') != 0) {
         var5 = true;
      } else {
         var5 = false;
      }

      this.rowFirstRelative = var5;
      if (var4) {
         this.columnFirst += this.relativeTo.getColumn();
      }

      if (this.rowFirstRelative) {
         this.rowFirst += this.relativeTo.getRow();
      }

      var2 = IntegerHelper.getInt(var1[var2 + 6], var1[var2 + 7]);
      this.columnLast = var2 & 255;
      if ((var2 & 16384) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.columnLastRelative = var4;
      var5 = var6;
      if ((var2 & '耀') != 0) {
         var5 = true;
      }

      this.rowLastRelative = var5;
      if (var4) {
         this.columnLast += this.relativeTo.getColumn();
      }

      if (this.rowLastRelative) {
         this.rowLast += this.relativeTo.getRow();
      }

      return 8;
   }
}
