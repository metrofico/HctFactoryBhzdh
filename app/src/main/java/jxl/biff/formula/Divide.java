package jxl.biff.formula;

class Divide extends BinaryOperator implements ParsedThing {
   public Divide() {
   }

   int getPrecedence() {
      return 3;
   }

   public String getSymbol() {
      return "/";
   }

   Token getToken() {
      return Token.DIVIDE;
   }
}
