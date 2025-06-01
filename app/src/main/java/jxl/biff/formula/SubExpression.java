package jxl.biff.formula;

import java.util.Stack;
import jxl.biff.IntegerHelper;

abstract class SubExpression extends Operand implements ParsedThing {
   private int length;
   private ParseItem[] subExpression;

   protected SubExpression() {
   }

   byte[] getBytes() {
      return null;
   }

   public int getLength() {
      return this.length;
   }

   public void getOperands(Stack var1) {
   }

   int getPrecedence() {
      return 5;
   }

   protected ParseItem[] getSubExpression() {
      return this.subExpression;
   }

   public int read(byte[] var1, int var2) {
      this.length = IntegerHelper.getInt(var1[var2], var1[var2 + 1]);
      return 2;
   }

   protected final void setLength(int var1) {
      this.length = var1;
   }

   public void setSubExpression(ParseItem[] var1) {
      this.subExpression = var1;
   }
}
