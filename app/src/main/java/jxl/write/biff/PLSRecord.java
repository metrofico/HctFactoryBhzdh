package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class PLSRecord extends WritableRecordData {
   private byte[] data;

   public PLSRecord(jxl.read.biff.PLSRecord var1) {
      super(Type.PLS);
      this.data = var1.getData();
   }

   public PLSRecord(PLSRecord var1) {
      super(Type.PLS);
      byte[] var2 = new byte[var1.data.length];
      this.data = var2;
      System.arraycopy(var1.data, 0, var2, 0, var2.length);
   }

   public byte[] getData() {
      return this.data;
   }
}
