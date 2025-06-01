package jxl.write.biff;

import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Logger;

class ExternalNameRecord extends WritableRecordData {
   Logger logger = Logger.getLogger(ExternalNameRecord.class);
   private String name;

   public ExternalNameRecord(String var1) {
      super(Type.EXTERNNAME);
      this.name = var1;
   }

   public byte[] getData() {
      byte[] var2 = new byte[this.name.length() * 2 + 12];
      var2[6] = (byte)this.name.length();
      var2[7] = 1;
      StringHelper.getUnicodeBytes(this.name, var2, 8);
      int var1 = this.name.length() * 2 + 8;
      var2[var1] = 2;
      var2[var1 + 1] = 0;
      var2[var1 + 2] = 28;
      var2[var1 + 3] = 23;
      return var2;
   }
}
