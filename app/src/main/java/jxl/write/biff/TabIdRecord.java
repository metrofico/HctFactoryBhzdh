package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class TabIdRecord extends WritableRecordData {
   private byte[] data;

   public TabIdRecord(int var1) {
      super(Type.TABID);
      this.data = new byte[var1 * 2];

      int var3;
      for(int var2 = 0; var2 < var1; var2 = var3) {
         var3 = var2 + 1;
         IntegerHelper.getTwoBytes(var3, this.data, var2 * 2);
      }

   }

   public byte[] getData() {
      return this.data;
   }
}
