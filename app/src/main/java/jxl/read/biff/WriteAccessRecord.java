package jxl.read.biff;

import jxl.WorkbookSettings;
import jxl.biff.RecordData;
import jxl.biff.StringHelper;
import jxl.biff.Type;

class WriteAccessRecord extends RecordData {
   private String wauser;

   public WriteAccessRecord(Record var1, boolean var2, WorkbookSettings var3) {
      super(Type.WRITEACCESS);
      byte[] var4 = var1.getData();
      if (var2) {
         this.wauser = StringHelper.getUnicodeString(var4, 56, 0);
      } else {
         this.wauser = StringHelper.getString(var4, var4[1], 1, var3);
      }

   }

   public String getWriteAccess() {
      return this.wauser;
   }
}
