package jxl.biff.formula;

import jxl.Cell;
import jxl.biff.CellReferenceHelper;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class CellReference3d extends Operand implements ParsedThing {
   private static Logger logger = Logger.getLogger(CellReference3d.class);
   private int column;
   private boolean columnRelative;
   private Cell relativeTo;
   private int row;
   private boolean rowRelative;
   private int sheet;
   private ExternalSheet workbook;

   public CellReference3d(String var1, ExternalSheet var2) throws FormulaException {
      this.workbook = var2;
      this.columnRelative = true;
      this.rowRelative = true;
      int var3 = var1.indexOf(33);
      String var4 = var1.substring(var3 + 1);
      this.column = CellReferenceHelper.getColumn(var4);
      this.row = CellReferenceHelper.getRow(var4);
      var4 = var1.substring(0, var3);
      var1 = var4;
      if (var4.charAt(0) == '\'') {
         var1 = var4;
         if (var4.charAt(var4.length() - 1) == '\'') {
            var1 = var4.substring(1, var4.length() - 1);
         }
      }

      var3 = var2.getExternalSheetIndex(var1);
      this.sheet = var3;
      if (var3 < 0) {
         throw new FormulaException(FormulaException.SHEET_REF_NOT_FOUND, var1);
      }
   }

   public CellReference3d(Cell var1, ExternalSheet var2) {
      this.relativeTo = var1;
      this.workbook = var2;
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
      if (var1 == this.sheet) {
         var1 = this.column;
         if (var1 >= var2) {
            this.column = var1 + 1;
         }

      }
   }

   void columnRemoved(int var1, int var2, boolean var3) {
      if (var1 == this.sheet) {
         var1 = this.column;
         if (var1 >= var2) {
            this.column = var1 - 1;
         }

      }
   }

   byte[] getBytes() {
      byte[] var3 = new byte[7];
      var3[0] = Token.REF3D.getCode();
      IntegerHelper.getTwoBytes(this.sheet, var3, 1);
      IntegerHelper.getTwoBytes(this.row, var3, 3);
      int var2 = this.column;
      int var1 = var2;
      if (this.rowRelative) {
         var1 = var2 | '耀';
      }

      var2 = var1;
      if (this.columnRelative) {
         var2 = var1 | 16384;
      }

      IntegerHelper.getTwoBytes(var2, var3, 5);
      return var3;
   }

   public int getColumn() {
      return this.column;
   }

   public int getRow() {
      return this.row;
   }

   public void getString(StringBuffer var1) {
      CellReferenceHelper.getCellReference(this.sheet, this.column, this.columnRelative ^ true, this.row, this.rowRelative ^ true, this.workbook, var1);
   }

   void handleImportedCellReferences() {
      this.setInvalid();
   }

   public int read(byte[] var1, int var2) {
      this.sheet = IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
      this.row = IntegerHelper.getInt(var1[var2 + 2], var1[var2 + 3]);
      var2 = IntegerHelper.getInt(var1[var2 + 4], var1[var2 + 5]);
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
      return 6;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      if (var1 == this.sheet) {
         var1 = this.row;
         if (var1 >= var2) {
            this.row = var1 + 1;
         }

      }
   }

   void rowRemoved(int var1, int var2, boolean var3) {
      if (var1 == this.sheet) {
         var1 = this.row;
         if (var1 >= var2) {
            this.row = var1 - 1;
         }

      }
   }
}
