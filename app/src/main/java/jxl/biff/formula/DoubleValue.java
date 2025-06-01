package jxl.biff.formula;

import jxl.biff.DoubleHelper;
import jxl.common.Logger;

class DoubleValue extends NumberValue implements ParsedThing {
   private static Logger logger = Logger.getLogger(DoubleValue.class);
   private double value;

   public DoubleValue() {
   }

   DoubleValue(double var1) {
      this.value = var1;
   }

   public DoubleValue(String var1) {
      try {
         this.value = Double.parseDouble(var1);
      } catch (NumberFormatException var2) {
         logger.warn(var2, var2);
         this.value = 0.0;
      }

   }

   byte[] getBytes() {
      byte[] var1 = new byte[9];
      var1[0] = Token.DOUBLE.getCode();
      DoubleHelper.getIEEEBytes(this.value, var1, 1);
      return var1;
   }

   public double getValue() {
      return this.value;
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      this.value = DoubleHelper.getIEEEDouble(var1, var2);
      return 8;
   }
}
