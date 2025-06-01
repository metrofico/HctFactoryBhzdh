package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class HideobjRecord extends WritableRecordData {
   private byte[] data;
   private int hidemode;

   public HideobjRecord(int var1) {
      super(Type.HIDEOBJ);
      this.hidemode = var1;
      byte[] var2 = new byte[2];
      this.data = var2;
      IntegerHelper.getTwoBytes(var1, var2, 0);
   }

   public byte[] getData() {
      return this.data;
   }
}
