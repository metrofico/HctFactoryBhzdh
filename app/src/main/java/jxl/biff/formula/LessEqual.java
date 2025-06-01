package jxl.biff.formula;

class LessEqual extends BinaryOperator implements ParsedThing {
   public LessEqual() {
   }

   int getPrecedence() {
      return 5;
   }

   public String getSymbol() {
      return "<=";
   }

   Token getToken() {
      return Token.LESS_EQUAL;
   }
}
