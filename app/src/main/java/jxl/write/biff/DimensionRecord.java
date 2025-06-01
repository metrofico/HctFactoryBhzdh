package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class DimensionRecord extends WritableRecordData {
   private byte[] data;
   private int numCols;
   private int numRows;

   public DimensionRecord(int var1, int var2) {
      super(Type.DIMENSION);
      this.numRows = var1;
      this.numCols = var2;
      byte[] var3 = new byte[14];
      this.data = var3;
      IntegerHelper.getFourBytes(var1, var3, 4);
      IntegerHelper.getTwoBytes(this.numCols, this.data, 10);
   }

   protected byte[] getData() {
      return this.data;
   }
}
