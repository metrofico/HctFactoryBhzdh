package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class SaveRecalcRecord extends RecordData {
   private static Logger logger = Logger.getLogger(SaveRecalcRecord.class);
   private boolean recalculateOnSave;

   public SaveRecalcRecord(Record var1) {
      super(var1);
      byte[] var3 = var1.getData();
      boolean var2 = false;
      if (IntegerHelper.getInt(var3[0], var3[1]) == 1) {
         var2 = true;
      }

      this.recalculateOnSave = var2;
   }

   public boolean getRecalculateOnSave() {
      return this.recalculateOnSave;
   }
}
