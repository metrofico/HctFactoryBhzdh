package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class VerticalPageBreaksRecord extends WritableRecordData {
   private int[] columnBreaks;

   public VerticalPageBreaksRecord(int[] var1) {
      super(Type.VERTICALPAGEBREAKS);
      this.columnBreaks = var1;
   }

   public byte[] getData() {
      int[] var5 = this.columnBreaks;
      int var1 = var5.length;
      int var2 = 2;
      byte[] var4 = new byte[var1 * 6 + 2];
      int var3 = var5.length;
      var1 = 0;
      IntegerHelper.getTwoBytes(var3, var4, 0);

      while(true) {
         var5 = this.columnBreaks;
         if (var1 >= var5.length) {
            return var4;
         }

         IntegerHelper.getTwoBytes(var5[var1], var4, var2);
         IntegerHelper.getTwoBytes(255, var4, var2 + 4);
         var2 += 6;
         ++var1;
      }
   }
}
