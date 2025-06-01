package jxl.biff.formula;

class Power extends BinaryOperator implements ParsedThing {
   public Power() {
   }

   int getPrecedence() {
      return 1;
   }

   public String getSymbol() {
      return "^";
   }

   Token getToken() {
      return Token.POWER;
   }
}
