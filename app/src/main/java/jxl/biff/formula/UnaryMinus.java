package jxl.biff.formula;

class UnaryMinus extends UnaryOperator implements ParsedThing {
   public UnaryMinus() {
   }

   int getPrecedence() {
      return 2;
   }

   public String getSymbol() {
      return "-";
   }

   Token getToken() {
      return Token.UNARY_MINUS;
   }

   void handleImportedCellReferences() {
   }
}
