package jxl.biff.formula;

class Plus extends StringOperator {
   public Plus() {
   }

   Operator getBinaryOperator() {
      return new Add();
   }

   Operator getUnaryOperator() {
      return new UnaryPlus();
   }

   void handleImportedCellReferences() {
   }
}
