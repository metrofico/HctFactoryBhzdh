package jxl.biff.formula;

import jxl.Cell;
import jxl.biff.CellReferenceHelper;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class SharedFormulaCellReference extends Operand implements ParsedThing {
   private static Logger logger = Logger.getLogger(SharedFormulaCellReference.class);
   private int column;
   private boolean columnRelative;
   private Cell relativeTo;
   private int row;
   private boolean rowRelative;

   public SharedFormulaCellReference(Cell var1) {
      this.relativeTo = var1;
   }

   byte[] getBytes() {
      byte[] var3 = new byte[5];
      var3[0] = Token.REF.getCode();
      IntegerHelper.getTwoBytes(this.row, var3, 1);
      int var2 = this.column;
      int var1 = var2;
      if (this.columnRelative) {
         var1 = var2 | 16384;
      }

      var2 = var1;
      if (this.rowRelative) {
         var2 = var1 | '耀';
      }

      IntegerHelper.getTwoBytes(var2, var3, 3);
      return var3;
   }

   public int getColumn() {
      return this.column;
   }

   public int getRow() {
      return this.row;
   }

   public void getString(StringBuffer var1) {
      CellReferenceHelper.getCellReference(this.column, this.row, var1);
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      this.row = IntegerHelper.getShort(var1[var2], var1[var2 + 1]);
      var2 = IntegerHelper.getInt(var1[var2 + 2], var1[var2 + 3]);
      this.column = (byte)(var2 & 255);
      boolean var4 = false;
      boolean var3;
      if ((var2 & 16384) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.columnRelative = var3;
      if ((var2 & '耀') != 0) {
         var4 = true;
      }

      this.rowRelative = var4;
      Cell var5;
      if (var3) {
         var5 = this.relativeTo;
         if (var5 != null) {
            this.column += var5.getColumn();
         }
      }

      if (this.rowRelative) {
         var5 = this.relativeTo;
         if (var5 != null) {
            this.row += var5.getRow();
         }
      }

      return 4;
   }
}
