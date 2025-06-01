package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class VerticalPageBreaksRecord extends RecordData {
   public static Biff7 biff7 = new Biff7();
   private int[] columnBreaks;
   private final Logger logger = Logger.getLogger(VerticalPageBreaksRecord.class);

   public VerticalPageBreaksRecord(Record var1) {
      super(var1);
      byte[] var5 = var1.getData();
      int var3 = 0;
      int var4 = IntegerHelper.getInt(var5[0], var5[1]);
      this.columnBreaks = new int[var4];

      for(int var2 = 2; var3 < var4; ++var3) {
         this.columnBreaks[var3] = IntegerHelper.getInt(var5[var2], var5[var2 + 1]);
         var2 += 6;
      }

   }

   public VerticalPageBreaksRecord(Record var1, Biff7 var2) {
      super(var1);
      byte[] var6 = var1.getData();
      int var3 = 0;
      int var5 = IntegerHelper.getInt(var6[0], var6[1]);
      this.columnBreaks = new int[var5];

      for(int var4 = 2; var3 < var5; ++var3) {
         this.columnBreaks[var3] = IntegerHelper.getInt(var6[var4], var6[var4 + 1]);
         var4 += 2;
      }

   }

   public int[] getColumnBreaks() {
      return this.columnBreaks;
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
