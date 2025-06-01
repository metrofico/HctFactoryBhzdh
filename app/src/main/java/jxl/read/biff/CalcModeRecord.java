package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class CalcModeRecord extends RecordData {
   private static Logger logger = Logger.getLogger(CalcModeRecord.class);
   private boolean automatic;

   public CalcModeRecord(Record var1) {
      super(var1);
      byte[] var3 = var1.getData();
      boolean var2 = false;
      if (IntegerHelper.getInt(var3[0], var3[1]) == 1) {
         var2 = true;
      }

      this.automatic = var2;
   }

   public boolean isAutomatic() {
      return this.automatic;
   }
}
