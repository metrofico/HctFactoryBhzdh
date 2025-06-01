package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class StringRecord extends WritableRecordData {
   private String value;

   public StringRecord(String var1) {
      super(Type.STRING);
      this.value = var1;
   }

   public byte[] getData() {
      byte[] var1 = new byte[this.value.length() * 2 + 3];
      IntegerHelper.getTwoBytes(this.value.length(), var1, 0);
      var1[2] = 1;
      StringHelper.getUnicodeBytes(this.value, var1, 3);
      return var1;
   }
}
