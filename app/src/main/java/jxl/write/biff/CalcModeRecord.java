package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class CalcModeRecord extends WritableRecordData {
   static CalcMode automatic = new CalcMode(1);
   static CalcMode automaticNoTables = new CalcMode(-1);
   static CalcMode manual = new CalcMode(0);
   private CalcMode calculationMode;

   public CalcModeRecord(CalcMode var1) {
      super(Type.CALCMODE);
      this.calculationMode = var1;
   }

   public byte[] getData() {
      byte[] var1 = new byte[2];
      IntegerHelper.getTwoBytes(this.calculationMode.value, var1, 0);
      return var1;
   }

   private static class CalcMode {
      int value;

      public CalcMode(int var1) {
         this.value = var1;
      }
   }
}
