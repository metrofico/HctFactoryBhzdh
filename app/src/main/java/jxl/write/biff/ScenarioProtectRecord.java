package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class ScenarioProtectRecord extends WritableRecordData {
   private byte[] data;
   private boolean protection;

   public ScenarioProtectRecord(boolean var1) {
      super(Type.SCENPROTECT);
      this.protection = var1;
      byte[] var2 = new byte[2];
      this.data = var2;
      if (var1) {
         IntegerHelper.getTwoBytes(1, var2, 0);
      }

   }

   public byte[] getData() {
      return this.data;
   }
}
