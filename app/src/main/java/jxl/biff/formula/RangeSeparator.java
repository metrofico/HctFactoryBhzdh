package jxl.biff.formula;

import jxl.biff.IntegerHelper;

class RangeSeparator extends BinaryOperator implements ParsedThing {
   public RangeSeparator() {
   }

   byte[] getBytes() {
      this.setVolatile();
      this.setOperandAlternateCode();
      byte[] var2 = super.getBytes();
      byte[] var1 = new byte[var2.length + 3];
      System.arraycopy(var2, 0, var1, 3, var2.length);
      var1[0] = Token.MEM_FUNC.getCode();
      IntegerHelper.getTwoBytes(var2.length, var1, 1);
      return var1;
   }

   int getPrecedence() {
      return 1;
   }

   public String getSymbol() {
      return ":";
   }

   Token getToken() {
      return Token.RANGE;
   }
}
