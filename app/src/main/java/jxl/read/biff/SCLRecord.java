package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.biff.Type;

class SCLRecord extends RecordData {
   private int denominator;
   private int numerator;

   protected SCLRecord(Record var1) {
      super(Type.SCL);
      byte[] var2 = var1.getData();
      this.numerator = IntegerHelper.getInt(var2[0], var2[1]);
      this.denominator = IntegerHelper.getInt(var2[2], var2[3]);
   }

   public int getZoomFactor() {
      return this.numerator * 100 / this.denominator;
   }
}
