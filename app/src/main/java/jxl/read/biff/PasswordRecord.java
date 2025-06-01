package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.Type;

class PasswordRecord extends RecordData {
   private String password;
   private int passwordHash;

   public PasswordRecord(Record var1) {
      super(Type.PASSWORD);
      byte[] var2 = var1.getData();
      this.passwordHash = IntegerHelper.getInt(var2[0], var2[1]);
   }

   public int getPasswordHash() {
      return this.passwordHash;
   }
}
