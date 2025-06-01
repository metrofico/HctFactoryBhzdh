package jxl.biff.formula;

class Equal extends BinaryOperator implements ParsedThing {
   public Equal() {
   }

   int getPrecedence() {
      return 5;
   }

   public String getSymbol() {
      return "=";
   }

   Token getToken() {
      return Token.EQUAL;
   }
}
