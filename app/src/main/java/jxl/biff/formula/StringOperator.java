package jxl.biff.formula;

import java.util.Stack;
import jxl.common.Assert;

abstract class StringOperator extends Operator {
   protected StringOperator() {
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      Assert.verify(false);
   }

   void columnInserted(int var1, int var2, boolean var3) {
      Assert.verify(false);
   }

   void columnRemoved(int var1, int var2, boolean var3) {
      Assert.verify(false);
   }

   abstract Operator getBinaryOperator();

   byte[] getBytes() {
      Assert.verify(false);
      return null;
   }

   public void getOperands(Stack var1) {
      Assert.verify(false);
   }

   int getPrecedence() {
      Assert.verify(false);
      return 0;
   }

   void getString(StringBuffer var1) {
      Assert.verify(false);
   }

   abstract Operator getUnaryOperator();

   void rowInserted(int var1, int var2, boolean var3) {
      Assert.verify(false);
   }

   void rowRemoved(int var1, int var2, boolean var3) {
      Assert.verify(false);
   }
}
