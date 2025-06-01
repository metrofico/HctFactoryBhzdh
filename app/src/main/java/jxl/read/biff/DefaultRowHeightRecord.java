package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;

class DefaultRowHeightRecord extends RecordData {
   private int height;

   public DefaultRowHeightRecord(Record var1) {
      super(var1);
      byte[] var2 = var1.getData();
      if (var2.length > 2) {
         this.height = IntegerHelper.getInt(var2[2], var2[3]);
      }

   }

   public int getHeight() {
      return this.height;
   }
}
