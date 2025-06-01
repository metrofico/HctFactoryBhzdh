package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class HeaderRecord extends WritableRecordData {
   private byte[] data;
   private String header;

   public HeaderRecord(String var1) {
      super(Type.HEADER);
      this.header = var1;
   }

   public HeaderRecord(HeaderRecord var1) {
      super(Type.HEADER);
      this.header = var1.header;
   }

   public byte[] getData() {
      String var1 = this.header;
      byte[] var2;
      if (var1 != null && var1.length() != 0) {
         this.data = new byte[this.header.length() * 2 + 3];
         IntegerHelper.getTwoBytes(this.header.length(), this.data, 0);
         var2 = this.data;
         var2[2] = 1;
         StringHelper.getUnicodeBytes(this.header, var2, 3);
         return this.data;
      } else {
         var2 = new byte[0];
         this.data = var2;
         return var2;
      }
   }
}
