package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class FunctionGroupCountRecord extends WritableRecordData {
   private byte[] data;
   private int numFunctionGroups = 14;

   public FunctionGroupCountRecord() {
      super(Type.FNGROUPCOUNT);
      byte[] var1 = new byte[2];
      this.data = var1;
      IntegerHelper.getTwoBytes(14, var1, 0);
   }

   public byte[] getData() {
      return this.data;
   }
}
