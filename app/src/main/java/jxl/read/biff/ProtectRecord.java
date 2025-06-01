package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;

class ProtectRecord extends RecordData {
   private boolean prot;

   ProtectRecord(Record var1) {
      super(var1);
      byte[] var3 = this.getRecord().getData();
      boolean var2 = false;
      if (IntegerHelper.getInt(var3[0], var3[1]) == 1) {
         var2 = true;
      }

      this.prot = var2;
   }

   boolean isProtected() {
      return this.prot;
   }
}
