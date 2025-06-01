package jxl.biff.formula;

import jxl.biff.CellReferenceHelper;
import jxl.biff.IntegerHelper;
import jxl.common.Assert;
import jxl.common.Logger;

class Area extends Operand implements ParsedThing {
   private static Logger logger = Logger.getLogger(Area.class);
   private int columnFirst;
   private boolean columnFirstRelative;
   private int columnLast;
   private boolean columnLastRelative;
   private int rowFirst;
   private boolean rowFirstRelative;
   private int rowLast;
   private boolean rowLastRelative;

   Area() {
   }

   Area(String var1) {
      int var2 = var1.indexOf(":");
      boolean var3;
      if (var2 != -1) {
         var3 = true;
      } else {
         var3 = false;
      }

      Assert.verify(var3);
      String var4 = var1.substring(0, var2);
      var1 = var1.substring(var2 + 1);
      this.columnFirst = CellReferenceHelper.getColumn(var4);
      this.rowFirst = CellReferenceHelper.getRow(var4);
      this.columnLast = CellReferenceHelper.getColumn(var1);
      this.rowLast = CellReferenceHelper.getRow(var1);
      this.columnFirstRelative = CellReferenceHelper.isColumnRelative(var4);
      this.rowFirstRelative = CellReferenceHelper.isRowRelative(var4);
      this.columnLastRelative = CellReferenceHelper.isColumnRelative(var1);
      this.rowLastRelative = CellReferenceHelper.isRowRelative(var1);
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

   void columnInserted(int var1, int var2, boolean var3) {
      if (var3) {
         var1 = this.columnFirst;
         if (var2 <= var1) {
            this.columnFirst = var1 + 1;
         }

         var1 = this.columnLast;
         if (var2 <= var1) {
            this.columnLast = var1 + 1;
         }

      }
   }

   void columnRemoved(int var1, int var2, boolean var3) {
      if (var3) {
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
      byte[] var4 = new byte[9];
      byte var1;
      if (!this.useAlternateCode()) {
         var1 = Token.AREA.getCode();
      } else {
         var1 = Token.AREA.getCode2();
      }

      var4[0] = var1;
      IntegerHelper.getTwoBytes(this.rowFirst, var4, 1);
      IntegerHelper.getTwoBytes(this.rowLast, var4, 3);
      int var3 = this.columnFirst;
      int var2 = var3;
      if (this.rowFirstRelative) {
         var2 = var3 | '耀';
      }

      var3 = var2;
      if (this.columnFirstRelative) {
         var3 = var2 | 16384;
      }

      IntegerHelper.getTwoBytes(var3, var4, 5);
      var3 = this.columnLast;
      var2 = var3;
      if (this.rowLastRelative) {
         var2 = var3 | '耀';
      }

      var3 = var2;
      if (this.columnLastRelative) {
         var3 = var2 | 16384;
      }

      IntegerHelper.getTwoBytes(var3, var4, 7);
      return var4;
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
      this.rowFirst = IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
      this.rowLast = IntegerHelper.getInt(var1[var2 + 2], var1[var2 + 3]);
      int var3 = IntegerHelper.getInt(var1[var2 + 4], var1[var2 + 5]);
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
      var2 = IntegerHelper.getInt(var1[var2 + 6], var1[var2 + 7]);
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
      return 8;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      if (var3) {
         int var4 = this.rowLast;
         if (var4 != 65535) {
            var1 = this.rowFirst;
            if (var2 <= var1) {
               this.rowFirst = var1 + 1;
            }

            if (var2 <= var4) {
               this.rowLast = var4 + 1;
            }

         }
      }
   }

   void rowRemoved(int var1, int var2, boolean var3) {
      if (var3) {
         var1 = this.rowLast;
         if (var1 != 65535) {
            int var4 = this.rowFirst;
            if (var2 < var4) {
               this.rowFirst = var4 - 1;
            }

            if (var2 <= var1) {
               this.rowLast = var1 - 1;
            }

         }
      }
   }

   protected void setRangeData(int var1, int var2, int var3, int var4, boolean var5, boolean var6, boolean var7, boolean var8) {
      this.columnFirst = var1;
      this.columnLast = var2;
      this.rowFirst = var3;
      this.rowLast = var4;
      this.columnFirstRelative = var5;
      this.columnLastRelative = var6;
      this.rowFirstRelative = var7;
      this.rowLastRelative = var8;
   }
}
