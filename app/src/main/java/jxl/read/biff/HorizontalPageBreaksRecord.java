package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class HorizontalPageBreaksRecord extends RecordData {
   public static Biff7 biff7 = new Biff7();
   private final Logger logger = Logger.getLogger(HorizontalPageBreaksRecord.class);
   private int[] rowBreaks;

   public HorizontalPageBreaksRecord(Record var1) {
      super(var1);
      byte[] var5 = var1.getData();
      int var2 = 0;
      int var4 = IntegerHelper.getInt(var5[0], var5[1]);
      this.rowBreaks = new int[var4];

      for(int var3 = 2; var2 < var4; ++var2) {
         this.rowBreaks[var2] = IntegerHelper.getInt(var5[var3], var5[var3 + 1]);
         var3 += 6;
      }

   }

   public HorizontalPageBreaksRecord(Record var1, Biff7 var2) {
      super(var1);
      byte[] var6 = var1.getData();
      int var3 = 0;
      int var5 = IntegerHelper.getInt(var6[0], var6[1]);
      this.rowBreaks = new int[var5];

      for(int var4 = 2; var3 < var5; ++var3) {
         this.rowBreaks[var3] = IntegerHelper.getInt(var6[var4], var6[var4 + 1]);
         var4 += 2;
      }

   }

   public int[] getRowBreaks() {
      return this.rowBreaks;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }
}
