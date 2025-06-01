package jxl.write.biff;

import jxl.biff.BuiltInName;
import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Logger;

class NameRecord extends WritableRecordData {
   private static final NameRange EMPTY_RANGE = new NameRange(0, 0, 0, 0, 0);
   private static final int areaReference = 59;
   private static final int cellReference = 58;
   private static Logger logger = Logger.getLogger(NameRecord.class);
   private static final int subExpression = 41;
   private static final int union = 16;
   private BuiltInName builtInName;
   private byte[] data;
   private int index;
   private boolean modified;
   private String name;
   private NameRange[] ranges;
   private int sheetRef;

   NameRecord(String var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      super(Type.NAME);
      this.sheetRef = 0;
      this.name = var1;
      this.index = var2;
      if (var8) {
         var2 = 0;
      } else {
         ++var2;
      }

      this.sheetRef = var2;
      NameRange[] var9 = new NameRange[1];
      this.ranges = var9;
      var9[0] = new NameRange(var3, var4, var5, var6, var7);
      this.modified = true;
   }

   NameRecord(BuiltInName var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, boolean var12) {
      super(Type.NAME);
      this.sheetRef = 0;
      this.builtInName = var1;
      this.index = var2;
      if (var12) {
         var2 = 0;
      } else {
         ++var2;
      }

      this.sheetRef = var2;
      NameRange[] var13 = new NameRange[2];
      this.ranges = var13;
      var13[0] = new NameRange(var3, var4, var5, var6, var7);
      this.ranges[1] = new NameRange(var3, var8, var9, var10, var11);
   }

   NameRecord(BuiltInName var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      super(Type.NAME);
      this.sheetRef = 0;
      this.builtInName = var1;
      this.index = var2;
      if (var8) {
         var2 = 0;
      } else {
         ++var2;
      }

      this.sheetRef = var2;
      NameRange[] var9 = new NameRange[1];
      this.ranges = var9;
      var9[0] = new NameRange(var3, var4, var5, var6, var7);
   }

   public NameRecord(jxl.read.biff.NameRecord var1, int var2) {
      super(Type.NAME);
      byte var3 = 0;
      this.sheetRef = 0;
      this.data = var1.getData();
      this.name = var1.getName();
      this.sheetRef = var1.getSheetRef();
      this.index = var2;
      this.modified = false;
      jxl.read.biff.NameRecord.NameRange[] var4 = var1.getRanges();
      this.ranges = new NameRange[var4.length];
      var2 = var3;

      while(true) {
         NameRange[] var5 = this.ranges;
         if (var2 >= var5.length) {
            return;
         }

         var5[var2] = new NameRange(var4[var2]);
         ++var2;
      }
   }

   void columnInserted(int var1, int var2) {
      int var3 = 0;

      while(true) {
         NameRange[] var4 = this.ranges;
         if (var3 >= var4.length) {
            return;
         }

         if (var1 == var4[var3].getExternalSheet()) {
            if (var2 <= this.ranges[var3].getFirstColumn()) {
               this.ranges[var3].incrementFirstColumn();
               this.modified = true;
            }

            if (var2 <= this.ranges[var3].getLastColumn()) {
               this.ranges[var3].incrementLastColumn();
               this.modified = true;
            }
         }

         ++var3;
      }
   }

   boolean columnRemoved(int var1, int var2) {
      int var3 = 0;

      while(true) {
         NameRange[] var4 = this.ranges;
         if (var3 >= var4.length) {
            var2 = 0;
            var3 = 0;

            while(true) {
               var4 = this.ranges;
               if (var2 >= var4.length) {
                  if (var3 == var4.length) {
                     return true;
                  }

                  var4 = new NameRange[var4.length - var3];
                  var1 = 0;

                  while(true) {
                     NameRange[] var5 = this.ranges;
                     if (var1 >= var5.length) {
                        this.ranges = var4;
                        return false;
                     }

                     NameRange var6 = var5[var1];
                     if (var6 != EMPTY_RANGE) {
                        var4[var1] = var6;
                     }

                     ++var1;
                  }
               }

               var1 = var3;
               if (var4[var2] == EMPTY_RANGE) {
                  var1 = var3 + 1;
               }

               ++var2;
               var3 = var1;
            }
         }

         if (var1 == var4[var3].getExternalSheet()) {
            if (var2 == this.ranges[var3].getFirstColumn() && var2 == this.ranges[var3].getLastColumn()) {
               this.ranges[var3] = EMPTY_RANGE;
            }

            if (var2 < this.ranges[var3].getFirstColumn() && var2 > 0) {
               this.ranges[var3].decrementFirstColumn();
               this.modified = true;
            }

            if (var2 <= this.ranges[var3].getLastColumn()) {
               this.ranges[var3].decrementLastColumn();
               this.modified = true;
            }
         }

         ++var3;
      }
   }

   public byte[] getData() {
      byte[] var4 = this.data;
      if (var4 != null && !this.modified) {
         return var4;
      } else {
         NameRange[] var7 = this.ranges;
         int var2 = var7.length;
         int var1 = 11;
         if (var2 > 1) {
            var1 = var7.length * 11 + 4;
         }

         if (this.builtInName != null) {
            var2 = 1;
         } else {
            var2 = this.name.length();
         }

         var4 = new byte[var1 + 15 + var2];
         this.data = var4;
         byte var6;
         if (this.builtInName != null) {
            var6 = 32;
         } else {
            var6 = 0;
         }

         IntegerHelper.getTwoBytes(var6, var4, 0);
         var4 = this.data;
         var4[2] = 0;
         if (this.builtInName != null) {
            var4[3] = 1;
         } else {
            var4[3] = (byte)this.name.length();
         }

         IntegerHelper.getTwoBytes(var1, this.data, 4);
         IntegerHelper.getTwoBytes(this.sheetRef, this.data, 6);
         IntegerHelper.getTwoBytes(this.sheetRef, this.data, 8);
         BuiltInName var8 = this.builtInName;
         if (var8 != null) {
            this.data[15] = (byte)var8.getValue();
         } else {
            StringHelper.getBytes(this.name, this.data, 15);
         }

         if (this.builtInName != null) {
            var2 = 16;
         } else {
            var2 = this.name.length() + 15;
         }

         var7 = this.ranges;
         if (var7.length > 1) {
            var4 = this.data;
            int var3 = var2 + 1;
            var4[var2] = 41;
            IntegerHelper.getTwoBytes(var1 - 3, var4, var3);
            var2 = var3 + 2;
            var1 = 0;

            while(true) {
               var7 = this.ranges;
               if (var1 >= var7.length) {
                  this.data[var2] = 16;
                  break;
               }

               byte[] var5 = this.data;
               var3 = var2 + 1;
               var5[var2] = 59;
               var4 = var7[var1].getData();
               System.arraycopy(var4, 0, this.data, var3, var4.length);
               var2 = var3 + var4.length;
               ++var1;
            }
         } else {
            this.data[var2] = 59;
            var4 = var7[0].getData();
            System.arraycopy(var4, 0, this.data, var2 + 1, var4.length);
         }

         return this.data;
      }
   }

   public int getIndex() {
      return this.index;
   }

   public String getName() {
      return this.name;
   }

   public NameRange[] getRanges() {
      return this.ranges;
   }

   public int getSheetRef() {
      return this.sheetRef;
   }

   void rowInserted(int var1, int var2) {
      int var3 = 0;

      while(true) {
         NameRange[] var4 = this.ranges;
         if (var3 >= var4.length) {
            return;
         }

         if (var1 == var4[var3].getExternalSheet()) {
            if (var2 <= this.ranges[var3].getFirstRow()) {
               this.ranges[var3].incrementFirstRow();
               this.modified = true;
            }

            if (var2 <= this.ranges[var3].getLastRow()) {
               this.ranges[var3].incrementLastRow();
               this.modified = true;
            }
         }

         ++var3;
      }
   }

   boolean rowRemoved(int var1, int var2) {
      int var3 = 0;

      while(true) {
         NameRange[] var4 = this.ranges;
         if (var3 >= var4.length) {
            var3 = 0;
            var2 = 0;

            while(true) {
               var4 = this.ranges;
               if (var3 >= var4.length) {
                  if (var2 == var4.length) {
                     return true;
                  }

                  var4 = new NameRange[var4.length - var2];
                  var1 = 0;

                  while(true) {
                     NameRange[] var5 = this.ranges;
                     if (var1 >= var5.length) {
                        this.ranges = var4;
                        return false;
                     }

                     NameRange var6 = var5[var1];
                     if (var6 != EMPTY_RANGE) {
                        var4[var1] = var6;
                     }

                     ++var1;
                  }
               }

               var1 = var2;
               if (var4[var3] == EMPTY_RANGE) {
                  var1 = var2 + 1;
               }

               ++var3;
               var2 = var1;
            }
         }

         if (var1 == var4[var3].getExternalSheet()) {
            if (var2 == this.ranges[var3].getFirstRow() && var2 == this.ranges[var3].getLastRow()) {
               this.ranges[var3] = EMPTY_RANGE;
            }

            if (var2 < this.ranges[var3].getFirstRow() && var2 > 0) {
               this.ranges[var3].decrementFirstRow();
               this.modified = true;
            }

            if (var2 <= this.ranges[var3].getLastRow()) {
               this.ranges[var3].decrementLastRow();
               this.modified = true;
            }
         }

         ++var3;
      }
   }

   public void setSheetRef(int var1) {
      this.sheetRef = var1;
      IntegerHelper.getTwoBytes(var1, this.data, 8);
   }

   static class NameRange {
      private int columnFirst;
      private int columnLast;
      private int externalSheet;
      private int rowFirst;
      private int rowLast;

      NameRange(int var1, int var2, int var3, int var4, int var5) {
         this.columnFirst = var4;
         this.rowFirst = var2;
         this.columnLast = var5;
         this.rowLast = var3;
         this.externalSheet = var1;
      }

      NameRange(jxl.read.biff.NameRecord.NameRange var1) {
         this.columnFirst = var1.getFirstColumn();
         this.rowFirst = var1.getFirstRow();
         this.columnLast = var1.getLastColumn();
         this.rowLast = var1.getLastRow();
         this.externalSheet = var1.getExternalSheet();
      }

      void decrementFirstColumn() {
         --this.columnFirst;
      }

      void decrementFirstRow() {
         --this.rowFirst;
      }

      void decrementLastColumn() {
         --this.columnLast;
      }

      void decrementLastRow() {
         --this.rowLast;
      }

      byte[] getData() {
         byte[] var1 = new byte[10];
         IntegerHelper.getTwoBytes(this.externalSheet, var1, 0);
         IntegerHelper.getTwoBytes(this.rowFirst, var1, 2);
         IntegerHelper.getTwoBytes(this.rowLast, var1, 4);
         IntegerHelper.getTwoBytes(this.columnFirst & 255, var1, 6);
         IntegerHelper.getTwoBytes(this.columnLast & 255, var1, 8);
         return var1;
      }

      int getExternalSheet() {
         return this.externalSheet;
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

      void incrementFirstColumn() {
         ++this.columnFirst;
      }

      void incrementFirstRow() {
         ++this.rowFirst;
      }

      void incrementLastColumn() {
         ++this.columnLast;
      }

      void incrementLastRow() {
         ++this.rowLast;
      }
   }
}
