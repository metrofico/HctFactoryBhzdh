package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class DefaultColumnWidth extends WritableRecordData {
   private byte[] data;
   private int width;

   public DefaultColumnWidth(int var1) {
      super(Type.DEFCOLWIDTH);
      this.width = var1;
      byte[] var2 = new byte[2];
      this.data = var2;
      IntegerHelper.getTwoBytes(var1, var2, 0);
   }

   protected byte[] getData() {
      return this.data;
   }
}
