package jxl.biff.formula;

import jxl.Cell;
import jxl.biff.CellReferenceHelper;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class CellReference extends Operand implements ParsedThing {
   private static Logger logger = Logger.getLogger(CellReference.class);
   private int column;
   private boolean columnRelative;
   private Cell relativeTo;
   private int row;
   private boolean rowRelative;

   public CellReference() {
   }

   public CellReference(String var1) {
      this.column = CellReferenceHelper.getColumn(var1);
      this.row = CellReferenceHelper.getRow(var1);
      this.columnRelative = CellReferenceHelper.isColumnRelative(var1);
      this.rowRelative = CellReferenceHelper.isRowRelative(var1);
   }

   public CellReference(Cell var1) {
      this.relativeTo = var1;
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      if (this.columnRelative) {
         this.column += var1;
      }

      if (this.rowRelative) {
         this.row += var2;
      }

   }

   public void columnInserted(int var1, int var2, boolean var3) {
      if (var3) {
         var1 = this.column;
         if (var1 >= var2) {
            this.column = var1 + 1;
         }

      }
   }

   void columnRemoved(int var1, int var2, boolean var3) {
      if (var3) {
         var1 = this.column;
         if (var1 >= var2) {
            this.column = var1 - 1;
         }

      }
   }

   byte[] getBytes() {
      byte[] var4 = new byte[5];
      byte var1;
      if (!this.useAlternateCode()) {
         var1 = Token.REF.getCode();
      } else {
         var1 = Token.REF.getCode2();
      }

      var4[0] = var1;
      IntegerHelper.getTwoBytes(this.row, var4, 1);
      int var3 = this.column;
      int var2 = var3;
      if (this.rowRelative) {
         var2 = var3 | '耀';
      }

      var3 = var2;
      if (this.columnRelative) {
         var3 = var2 | 16384;
      }

      IntegerHelper.getTwoBytes(var3, var4, 3);
      return var4;
   }

   public int getColumn() {
      return this.column;
   }

   public int getRow() {
      return this.row;
   }

   public void getString(StringBuffer var1) {
      CellReferenceHelper.getCellReference(this.column, this.columnRelative ^ true, this.row, this.rowRelative ^ true, var1);
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      this.row = IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
      var2 = IntegerHelper.getInt(var1[var2 + 2], var1[var2 + 3]);
      this.column = var2 & 255;
      boolean var4 = false;
      boolean var3;
      if ((var2 & 16384) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.columnRelative = var3;
      var3 = var4;
      if ((var2 & '耀') != 0) {
         var3 = true;
      }

      this.rowRelative = var3;
      return 4;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      if (var3) {
         var1 = this.row;
         if (var1 >= var2) {
            this.row = var1 + 1;
         }

      }
   }

   void rowRemoved(int var1, int var2, boolean var3) {
      if (var3) {
         var1 = this.row;
         if (var1 >= var2) {
            this.row = var1 - 1;
         }

      }
   }
}
