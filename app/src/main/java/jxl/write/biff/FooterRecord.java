package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class FooterRecord extends WritableRecordData {
   private byte[] data;
   private String footer;

   public FooterRecord(String var1) {
      super(Type.FOOTER);
      this.footer = var1;
   }

   public FooterRecord(FooterRecord var1) {
      super(Type.FOOTER);
      this.footer = var1.footer;
   }

   public byte[] getData() {
      String var1 = this.footer;
      byte[] var2;
      if (var1 != null && var1.length() != 0) {
         this.data = new byte[this.footer.length() * 2 + 3];
         IntegerHelper.getTwoBytes(this.footer.length(), this.data, 0);
         var2 = this.data;
         var2[2] = 1;
         StringHelper.getUnicodeBytes(this.footer, var2, 3);
         return this.data;
      } else {
         var2 = new byte[0];
         this.data = var2;
         return var2;
      }
   }
}
