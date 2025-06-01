package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class RefModeRecord extends WritableRecordData {
   public RefModeRecord() {
      super(Type.REFMODE);
   }

   public byte[] getData() {
      byte[] var1 = new byte[]{1, 0};
      return var1;
   }
}
