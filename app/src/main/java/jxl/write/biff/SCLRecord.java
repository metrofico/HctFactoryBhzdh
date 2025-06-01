package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class SCLRecord extends WritableRecordData {
   private int zoomFactor;

   public SCLRecord(int var1) {
      super(Type.SCL);
      this.zoomFactor = var1;
   }

   public byte[] getData() {
      byte[] var1 = new byte[4];
      IntegerHelper.getTwoBytes(this.zoomFactor, var1, 0);
      IntegerHelper.getTwoBytes(100, var1, 2);
      return var1;
   }
}
