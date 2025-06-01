package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class HideobjRecord extends RecordData {
   private static Logger logger = Logger.getLogger(HideobjRecord.class);
   private int hidemode;

   public HideobjRecord(Record var1) {
      super(var1);
      byte[] var2 = var1.getData();
      this.hidemode = IntegerHelper.getInt(var2[0], var2[1]);
   }

   public int getHideMode() {
      return this.hidemode;
   }
}
