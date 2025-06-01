package jxl.read.biff;

import jxl.Range;
import jxl.Sheet;
import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.SheetRangeImpl;

public class MergedCellsRecord extends RecordData {
   private Range[] ranges;

   MergedCellsRecord(Record var1, Sheet var2) {
      super(var1);
      byte[] var10 = this.getRecord().getData();
      int var4 = 0;
      int var5 = IntegerHelper.getInt(var10[0], var10[1]);
      this.ranges = new Range[var5];

      for(int var3 = 2; var4 < var5; ++var4) {
         int var6 = IntegerHelper.getInt(var10[var3], var10[var3 + 1]);
         int var7 = IntegerHelper.getInt(var10[var3 + 2], var10[var3 + 3]);
         int var9 = IntegerHelper.getInt(var10[var3 + 4], var10[var3 + 5]);
         int var8 = IntegerHelper.getInt(var10[var3 + 6], var10[var3 + 7]);
         this.ranges[var4] = new SheetRangeImpl(var2, var9, var6, var8, var7);
         var3 += 8;
      }

   }

   public Range[] getRanges() {
      return this.ranges;
   }
}
