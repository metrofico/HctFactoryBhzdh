package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;

class DefaultColumnWidthRecord extends RecordData {
   private int width;

   public DefaultColumnWidthRecord(Record var1) {
      super(var1);
      byte[] var2 = var1.getData();
      this.width = IntegerHelper.getInt(var2[0], var2[1]);
   }

   public int getWidth() {
      return this.width;
   }
}
