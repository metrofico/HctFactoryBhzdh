package jxl.biff.formula;

class NotEqual extends BinaryOperator implements ParsedThing {
   public NotEqual() {
   }

   int getPrecedence() {
      return 5;
   }

   public String getSymbol() {
      return "<>";
   }

   Token getToken() {
      return Token.NOT_EQUAL;
   }
}
