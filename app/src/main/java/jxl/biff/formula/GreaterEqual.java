package jxl.biff.formula;

class GreaterEqual extends BinaryOperator implements ParsedThing {
   public GreaterEqual() {
   }

   int getPrecedence() {
      return 5;
   }

   public String getSymbol() {
      return ">=";
   }

   Token getToken() {
      return Token.GREATER_EQUAL;
   }
}
