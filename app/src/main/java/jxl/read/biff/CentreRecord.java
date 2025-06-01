package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;

class CentreRecord extends RecordData {
   private boolean centre;

   public CentreRecord(Record var1) {
      super(var1);
      byte[] var3 = this.getRecord().getData();
      boolean var2 = false;
      if (IntegerHelper.getInt(var3[0], var3[1]) != 0) {
         var2 = true;
      }

      this.centre = var2;
   }

   public boolean isCentre() {
      return this.centre;
   }
}
