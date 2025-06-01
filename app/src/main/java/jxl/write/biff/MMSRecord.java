package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class MMSRecord extends WritableRecordData {
   private byte[] data;
   private byte numMenuItemsAdded;
   private byte numMenuItemsDeleted;

   public MMSRecord(int var1, int var2) {
      super(Type.MMS);
      byte var3 = (byte)var1;
      this.numMenuItemsAdded = var3;
      byte var4 = (byte)var2;
      this.numMenuItemsDeleted = var4;
      byte[] var5 = new byte[2];
      this.data = var5;
      var5[0] = var3;
      var5[1] = var4;
   }

   public byte[] getData() {
      return this.data;
   }
}
