package jxl.write.biff;

import java.util.ArrayList;
import jxl.Cell;
import jxl.Range;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

public class MergedCellsRecord extends WritableRecordData {
   private ArrayList ranges;

   protected MergedCellsRecord(ArrayList var1) {
      super(Type.MERGEDCELLS);
      this.ranges = var1;
   }

   public byte[] getData() {
      int var1 = this.ranges.size();
      int var2 = 2;
      byte[] var5 = new byte[var1 * 8 + 2];
      int var3 = this.ranges.size();
      var1 = 0;
      IntegerHelper.getTwoBytes(var3, var5, 0);

      while(var1 < this.ranges.size()) {
         Range var6 = (Range)this.ranges.get(var1);
         Cell var4 = var6.getTopLeft();
         Cell var7 = var6.getBottomRight();
         IntegerHelper.getTwoBytes(var4.getRow(), var5, var2);
         IntegerHelper.getTwoBytes(var7.getRow(), var5, var2 + 2);
         IntegerHelper.getTwoBytes(var4.getColumn(), var5, var2 + 4);
         IntegerHelper.getTwoBytes(var7.getColumn(), var5, var2 + 6);
         var2 += 8;
         ++var1;
      }

      return var5;
   }
}
