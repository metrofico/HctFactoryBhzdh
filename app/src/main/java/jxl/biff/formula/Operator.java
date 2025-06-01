package jxl.biff.formula;

import java.util.Stack;

abstract class Operator extends ParseItem {
   private ParseItem[] operands = new ParseItem[0];

   public Operator() {
   }

   protected void add(ParseItem var1) {
      var1.setParent(this);
      ParseItem[] var2 = this.operands;
      ParseItem[] var3 = new ParseItem[var2.length + 1];
      System.arraycopy(var2, 0, var3, 0, var2.length);
      var3[this.operands.length] = var1;
      this.operands = var3;
   }

   public abstract void getOperands(Stack var1);

   protected ParseItem[] getOperands() {
      return this.operands;
   }

   abstract int getPrecedence();

   protected void setOperandAlternateCode() {
      int var1 = 0;

      while(true) {
         ParseItem[] var2 = this.operands;
         if (var1 >= var2.length) {
            return;
         }

         var2[var1].setAlternateCode();
         ++var1;
      }
   }
}
