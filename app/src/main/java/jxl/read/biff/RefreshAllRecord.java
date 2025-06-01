package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class RefreshAllRecord extends RecordData {
   private static Logger logger = Logger.getLogger(RefreshAllRecord.class);
   private boolean refreshAll;

   public RefreshAllRecord(Record var1) {
      super(var1);
      byte[] var3 = var1.getData();
      boolean var2 = false;
      if (IntegerHelper.getInt(var3[0], var3[1]) == 1) {
         var2 = true;
      }

      this.refreshAll = var2;
   }

   public boolean getRefreshAll() {
      return this.refreshAll;
   }
}
