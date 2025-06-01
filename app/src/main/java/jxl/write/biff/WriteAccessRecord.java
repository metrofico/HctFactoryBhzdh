package jxl.write.biff;

import jxl.Workbook;
import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class WriteAccessRecord extends WritableRecordData {
   private static final String authorString = "Java Excel API";
   private byte[] data = new byte[112];
   private String userName;

   public WriteAccessRecord(String var1) {
      super(Type.WRITEACCESS);
      if (var1 == null) {
         var1 = "Java Excel API v" + Workbook.getVersion();
      }

      StringHelper.getBytes(var1, this.data, 0);
      int var2 = var1.length();

      while(true) {
         byte[] var3 = this.data;
         if (var2 >= var3.length) {
            return;
         }

         var3[var2] = 32;
         ++var2;
      }
   }

   public byte[] getData() {
      return this.data;
   }
}
