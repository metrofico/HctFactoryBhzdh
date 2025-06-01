package jxl.biff.formula;

class UnaryPlus extends UnaryOperator implements ParsedThing {
   public UnaryPlus() {
   }

   int getPrecedence() {
      return 2;
   }

   public String getSymbol() {
      return "+";
   }

   Token getToken() {
      return Token.UNARY_PLUS;
   }

   void handleImportedCellReferences() {
   }
}
