package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class Weird1Record extends WritableRecordData {
   public Weird1Record() {
      super(Type.WEIRD1);
   }

   public byte[] getData() {
      byte[] var1 = new byte[6];
      var1[2] = 55;
      return var1;
   }
}
