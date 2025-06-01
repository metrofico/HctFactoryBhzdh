package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class UsesElfsRecord extends WritableRecordData {
   private byte[] data;
   private boolean usesElfs = true;

   public UsesElfsRecord() {
      super(Type.USESELFS);
      byte[] var1 = new byte[2];
      this.data = var1;
      var1[0] = 1;
   }

   public byte[] getData() {
      return this.data;
   }
}
