package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public class ConditionalFormatRangeRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(ConditionalFormatRangeRecord.class);
   private byte[] data = this.getRecord().getData();
   private Range enclosingRange;
   private boolean initialized = false;
   private boolean modified = false;
   private int numRanges;
   private Range[] ranges;

   public ConditionalFormatRangeRecord(Record var1) {
      super(var1);
   }

   private void initialize() {
      Range var3 = new Range();
      this.enclosingRange = var3;
      byte[] var4 = this.data;
      var3.firstRow = IntegerHelper.getInt(var4[4], var4[5]);
      var3 = this.enclosingRange;
      var4 = this.data;
      var3.lastRow = IntegerHelper.getInt(var4[6], var4[7]);
      Range var6 = this.enclosingRange;
      byte[] var5 = this.data;
      var6.firstColumn = IntegerHelper.getInt(var5[8], var5[9]);
      var6 = this.enclosingRange;
      var5 = this.data;
      var6.lastColumn = IntegerHelper.getInt(var5[10], var5[11]);
      var5 = this.data;
      int var1 = IntegerHelper.getInt(var5[12], var5[13]);
      this.numRanges = var1;
      this.ranges = new Range[var1];
      int var2 = 14;

      for(var1 = 0; var1 < this.numRanges; ++var1) {
         this.ranges[var1] = new Range();
         var3 = this.ranges[var1];
         var4 = this.data;
         var3.firstRow = IntegerHelper.getInt(var4[var2], var4[var2 + 1]);
         var3 = this.ranges[var1];
         var4 = this.data;
         var3.lastRow = IntegerHelper.getInt(var4[var2 + 2], var4[var2 + 3]);
         var3 = this.ranges[var1];
         var4 = this.data;
         var3.firstColumn = IntegerHelper.getInt(var4[var2 + 4], var4[var2 + 5]);
         var3 = this.ranges[var1];
         var4 = this.data;
         var3.lastColumn = IntegerHelper.getInt(var4[var2 + 6], var4[var2 + 7]);
         var2 += 8;
      }

      this.initialized = true;
   }

   public byte[] getData() {
      if (!this.modified) {
         return this.data;
      } else {
         int var1 = this.ranges.length;
         int var2 = 14;
         byte[] var3 = new byte[var1 * 8 + 14];
         byte[] var4 = this.data;
         var1 = 0;
         System.arraycopy(var4, 0, var3, 0, 4);
         IntegerHelper.getTwoBytes(this.enclosingRange.firstRow, var3, 4);
         IntegerHelper.getTwoBytes(this.enclosingRange.lastRow, var3, 6);
         IntegerHelper.getTwoBytes(this.enclosingRange.firstColumn, var3, 8);
         IntegerHelper.getTwoBytes(this.enclosingRange.lastColumn, var3, 10);
         IntegerHelper.getTwoBytes(this.numRanges, var3, 12);

         while(true) {
            Range[] var5 = this.ranges;
            if (var1 >= var5.length) {
               return var3;
            }

            IntegerHelper.getTwoBytes(var5[var1].firstRow, var3, var2);
            IntegerHelper.getTwoBytes(this.ranges[var1].lastRow, var3, var2 + 2);
            IntegerHelper.getTwoBytes(this.ranges[var1].firstColumn, var3, var2 + 4);
            IntegerHelper.getTwoBytes(this.ranges[var1].lastColumn, var3, var2 + 6);
            var2 += 8;
            ++var1;
         }
      }
   }

   public void insertColumn(int var1) {
      if (!this.initialized) {
         this.initialize();
      }

      this.enclosingRange.insertColumn(var1);
      if (this.enclosingRange.modified) {
         this.modified = true;
      }

      int var2 = 0;

      while(true) {
         Range[] var3 = this.ranges;
         if (var2 >= var3.length) {
            return;
         }

         var3[var2].insertColumn(var1);
         if (this.ranges[var2].modified) {
            this.modified = true;
         }

         ++var2;
      }
   }

   public void insertRow(int var1) {
      if (!this.initialized) {
         this.initialize();
      }

      this.enclosingRange.insertRow(var1);
      if (this.enclosingRange.modified) {
         this.modified = true;
      }

      int var2 = 0;

      while(true) {
         Range[] var3 = this.ranges;
         if (var2 >= var3.length) {
            return;
         }

         var3[var2].insertRow(var1);
         if (this.ranges[var2].modified) {
            this.modified = true;
         }

         ++var2;
      }
   }

   public void removeColumn(int var1) {
      if (!this.initialized) {
         this.initialize();
      }

      this.enclosingRange.removeColumn(var1);
      if (this.enclosingRange.modified) {
         this.modified = true;
      }

      int var2 = 0;

      while(true) {
         Range[] var3 = this.ranges;
         if (var2 >= var3.length) {
            return;
         }

         var3[var2].removeColumn(var1);
         if (this.ranges[var2].modified) {
            this.modified = true;
         }

         ++var2;
      }
   }

   public void removeRow(int var1) {
      if (!this.initialized) {
         this.initialize();
      }

      this.enclosingRange.removeRow(var1);
      if (this.enclosingRange.modified) {
         this.modified = true;
      }

      int var2 = 0;

      while(true) {
         Range[] var3 = this.ranges;
         if (var2 >= var3.length) {
            return;
         }

         var3[var2].removeRow(var1);
         if (this.ranges[var2].modified) {
            this.modified = true;
         }

         ++var2;
      }
   }

   private static class Range {
      public int firstColumn;
      public int firstRow;
      public int lastColumn;
      public int lastRow;
      public boolean modified = false;

      public Range() {
      }

      public void insertColumn(int var1) {
         int var3 = this.lastColumn;
         if (var1 <= var3) {
            int var2 = this.firstColumn;
            if (var1 <= var2) {
               this.firstColumn = var2 + 1;
               this.modified = true;
            }

            if (var1 <= var3) {
               this.lastColumn = var3 + 1;
               this.modified = true;
            }

         }
      }

      public void insertRow(int var1) {
         int var2 = this.lastRow;
         if (var1 <= var2) {
            int var3 = this.firstRow;
            if (var1 <= var3) {
               this.firstRow = var3 + 1;
               this.modified = true;
            }

            if (var1 <= var2) {
               this.lastRow = var2 + 1;
               this.modified = true;
            }

         }
      }

      public void removeColumn(int var1) {
         int var3 = this.lastColumn;
         if (var1 <= var3) {
            int var2 = this.firstColumn;
            if (var1 < var2) {
               this.firstColumn = var2 - 1;
               this.modified = true;
            }

            if (var1 <= var3) {
               this.lastColumn = var3 - 1;
               this.modified = true;
            }

         }
      }

      public void removeRow(int var1) {
         int var3 = this.lastRow;
         if (var1 <= var3) {
            int var2 = this.firstRow;
            if (var1 < var2) {
               this.firstRow = var2 - 1;
               this.modified = true;
            }

            if (var1 <= var3) {
               this.lastRow = var3 - 1;
               this.modified = true;
            }

         }
      }
   }
}
