package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class CalcCountRecord extends WritableRecordData {
   private int calcCount;
   private byte[] data;

   public CalcCountRecord(int var1) {
      super(Type.CALCCOUNT);
      this.calcCount = var1;
   }

   public byte[] getData() {
      byte[] var1 = new byte[2];
      IntegerHelper.getTwoBytes(this.calcCount, var1, 0);
      return var1;
   }
}
