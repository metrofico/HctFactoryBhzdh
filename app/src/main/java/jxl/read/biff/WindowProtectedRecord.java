package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class WindowProtectedRecord extends RecordData {
   private static Logger logger = Logger.getLogger(WindowProtectedRecord.class);
   private boolean windowProtected;

   public WindowProtectedRecord(Record var1) {
      super(var1);
      byte[] var3 = var1.getData();
      boolean var2 = false;
      if (IntegerHelper.getInt(var3[0], var3[1]) == 1) {
         var2 = true;
      }

      this.windowProtected = var2;
   }

   public boolean getWindowProtected() {
      return this.windowProtected;
   }
}
