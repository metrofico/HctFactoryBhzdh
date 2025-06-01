package jxl.read.biff;

import jxl.biff.RecordData;

class NineteenFourRecord extends RecordData {
   private boolean nineteenFour;

   public NineteenFourRecord(Record var1) {
      super(var1);
      byte[] var3 = this.getRecord().getData();
      boolean var2 = false;
      if (var3[0] == 1) {
         var2 = true;
      }

      this.nineteenFour = var2;
   }

   public boolean is1904() {
      return this.nineteenFour;
   }
}
