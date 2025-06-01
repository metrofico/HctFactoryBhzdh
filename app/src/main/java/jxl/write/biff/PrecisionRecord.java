package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class PrecisionRecord extends WritableRecordData {
   private boolean asDisplayed;
   private byte[] data;

   public PrecisionRecord(boolean var1) {
      super(Type.PRECISION);
      this.asDisplayed = var1;
      byte[] var2 = new byte[2];
      this.data = var2;
      if (!var1) {
         IntegerHelper.getTwoBytes(1, var2, 0);
      }

   }

   public byte[] getData() {
      return this.data;
   }
}
