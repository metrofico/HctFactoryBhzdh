package jxl.biff.formula;

import jxl.common.Logger;

class Minus extends StringOperator {
   private static Logger logger = Logger.getLogger(StringOperator.class);

   public Minus() {
   }

   Operator getBinaryOperator() {
      return new Subtract();
   }

   Operator getUnaryOperator() {
      return new UnaryMinus();
   }

   void handleImportedCellReferences() {
   }
}
