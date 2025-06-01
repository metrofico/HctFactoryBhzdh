package jxl.biff.formula;

import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class IntegerValue extends NumberValue implements ParsedThing {
   private static Logger logger = Logger.getLogger(IntegerValue.class);
   private boolean outOfRange;
   private double value;

   public IntegerValue() {
      this.outOfRange = false;
   }

   public IntegerValue(String var1) {
      try {
         this.value = (double)Integer.parseInt(var1);
      } catch (NumberFormatException var5) {
         logger.warn(var5, var5);
         this.value = 0.0;
      }

      double var2 = this.value;
      boolean var4;
      if (var2 != (double)((short)((int)var2))) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.outOfRange = var4;
   }

   byte[] getBytes() {
      byte[] var1 = new byte[]{Token.INTEGER.getCode(), 0, 0};
      IntegerHelper.getTwoBytes((int)this.value, var1, 1);
      return var1;
   }

   public double getValue() {
      return this.value;
   }

   void handleImportedCellReferences() {
   }

   boolean isOutOfRange() {
      return this.outOfRange;
   }

   public int read(byte[] var1, int var2) {
      this.value = (double)IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
      return 2;
   }
}
