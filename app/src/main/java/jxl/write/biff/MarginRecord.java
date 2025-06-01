package jxl.write.biff;

import jxl.biff.DoubleHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

abstract class MarginRecord extends WritableRecordData {
   private double margin;

   public MarginRecord(Type var1, double var2) {
      super(var1);
      this.margin = var2;
   }

   public byte[] getData() {
      byte[] var1 = new byte[8];
      DoubleHelper.getIEEEBytes(this.margin, var1, 0);
      return var1;
   }
}
