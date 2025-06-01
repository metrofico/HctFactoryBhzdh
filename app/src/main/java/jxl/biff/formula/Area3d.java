package jxl.biff.formula;

import jxl.biff.CellReferenceHelper;
import jxl.biff.IntegerHelper;
import jxl.common.Assert;
import jxl.common.Logger;

class Area3d extends Operand implements ParsedThing {
   private static Logger logger = Logger.getLogger(Area3d.class);
   private int columnFirst;
   private boolean columnFirstRelative;
   private int columnLast;
   private boolean columnLastRelative;
   private int rowFirst;
   private boolean rowFirstRelative;
   private int rowLast;
   private boolean rowLastRelative;
   private int sheet;
   private ExternalSheet workbook;

   Area3d(String var1, ExternalSheet var2) throws FormulaException {
      this.workbook = var2;
      int var3 = var1.lastIndexOf(":");
      boolean var5;
      if (var3 != -1) {
         var5 = true;
      } else {
         var5 = false;
      }

      Assert.verify(var5);
      String var7 = var1.substring(var3 + 1);
      int var4 = var1.indexOf(33);
      String var6 = var1.substring(var4 + 1, var3);
      this.columnFirst = CellReferenceHelper.getColumn(var6);
      this.rowFirst = CellReferenceHelper.getRow(var6);
      var6 = var1.substring(0, var4);
      var1 = var6;
      if (var6.charAt(0) == '\'') {
         var1 = var6;
         if (var6.charAt(var6.length() - 1) == '\'') {
            var1 = var6.substring(1, var6.length() - 1);
         }
      }

      var3 = var2.getExternalSheetIndex(var1);
      this.sheet = var3;
      if (var3 >= 0) {
         this.columnLast = CellReferenceHelper.getColumn(var7);
         this.rowLast = CellReferenceHelper.getRow(var7);
         this.columnFirstRelative = true;
         this.rowFirstRelative = true;
         this.columnLastRelative = true;
         this.rowLastRelative = true;
      } else {
         throw new FormulaException(FormulaException.SHEET_REF_NOT_FOUND, var1);
      }
   }

   Area3d(ExternalSheet var1) {
      this.workbook = var1;
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      if (this.columnFirstRelative) {
         this.columnFirst += var1;
      }

      if (this.columnLastRelative) {
         this.columnLast += var1;
      }

      if (this.rowFirstRelative) {
         this.rowFirst += var2;
      }

      if (this.rowLastRelative) {
         this.rowLast += var2;
      }

   }

   public void columnInserted(int var1, int var2, boolean var3) {
      if (var1 == this.sheet) {
         var1 = this.columnFirst;
         if (var1 >= var2) {
            this.columnFirst = var1 + 1;
         }

         var1 = this.columnLast;
         if (var1 >= var2) {
            this.columnLast = var1 + 1;
         }

      }
   }

   void columnRemoved(int var1, int var2, boolean var3) {
      if (var1 == this.sheet) {
         var1 = this.columnFirst;
         if (var2 < var1) {
            this.columnFirst = var1 - 1;
         }

         var1 = this.columnLast;
         if (var2 <= var1) {
            this.columnLast = var1 - 1;
         }

      }
   }

   byte[] getBytes() {
      byte[] var3 = new byte[11];
      var3[0] = Token.AREA3D.getCode();
      IntegerHelper.getTwoBytes(this.sheet, var3, 1);
      IntegerHelper.getTwoBytes(this.rowFirst, var3, 3);
      IntegerHelper.getTwoBytes(this.rowLast, var3, 5);
      int var2 = this.columnFirst;
      int var1 = var2;
      if (this.rowFirstRelative) {
         var1 = var2 | '耀';
      }

      var2 = var1;
      if (this.columnFirstRelative) {
         var2 = var1 | 16384;
      }

      IntegerHelper.getTwoBytes(var2, var3, 7);
      var2 = this.columnLast;
      var1 = var2;
      if (this.rowLastRelative) {
         var1 = var2 | '耀';
      }

      var2 = var1;
      if (this.columnLastRelative) {
         var2 = var1 | 16384;
      }

      IntegerHelper.getTwoBytes(var2, var3, 9);
      return var3;
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
      CellReferenceHelper.getCellReference(this.sheet, this.columnFirst, this.rowFirst, this.workbook, var1);
      var1.append(':');
      CellReferenceHelper.getCellReference(this.columnLast, this.rowLast, var1);
   }

   void handleImportedCellReferences() {
      this.setInvalid();
   }

   public int read(byte[] var1, int var2) {
      this.sheet = IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
      this.rowFirst = IntegerHelper.getInt(var1[var2 + 2], var1[var2 + 3]);
      this.rowLast = IntegerHelper.getInt(var1[var2 + 4], var1[var2 + 5]);
      int var3 = IntegerHelper.getInt(var1[var2 + 6], var1[var2 + 7]);
      this.columnFirst = var3 & 255;
      boolean var5 = false;
      boolean var4;
      if ((var3 & 16384) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.columnFirstRelative = var4;
      if ((var3 & '耀') != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.rowFirstRelative = var4;
      var2 = IntegerHelper.getInt(var1[var2 + 8], var1[var2 + 9]);
      this.columnLast = var2 & 255;
      if ((var2 & 16384) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.columnLastRelative = var4;
      var4 = var5;
      if ((var2 & '耀') != 0) {
         var4 = true;
      }

      this.rowLastRelative = var4;
      return 10;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      if (var1 == this.sheet) {
         var1 = this.rowLast;
         if (var1 != 65535) {
            int var4 = this.rowFirst;
            if (var2 <= var4) {
               this.rowFirst = var4 + 1;
            }

            if (var2 <= var1) {
               this.rowLast = var1 + 1;
            }

         }
      }
   }

   void rowRemoved(int var1, int var2, boolean var3) {
      if (var1 == this.sheet) {
         int var4 = this.rowLast;
         if (var4 != 65535) {
            var1 = this.rowFirst;
            if (var2 < var1) {
               this.rowFirst = var1 - 1;
            }

            if (var2 <= var4) {
               this.rowLast = var4 - 1;
            }

         }
      }
   }

   protected void setRangeData(int var1, int var2, int var3, int var4, int var5, boolean var6, boolean var7, boolean var8, boolean var9) {
      this.sheet = var1;
      this.columnFirst = var2;
      this.columnLast = var3;
      this.rowFirst = var4;
      this.rowLast = var5;
      this.columnFirstRelative = var6;
      this.columnLastRelative = var7;
      this.rowFirstRelative = var8;
      this.rowLastRelative = var9;
   }
}
