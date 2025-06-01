package jxl.biff.formula;

class Multiply extends BinaryOperator implements ParsedThing {
   public Multiply() {
   }

   int getPrecedence() {
      return 3;
   }

   public String getSymbol() {
      return "*";
   }

   Token getToken() {
      return Token.MULTIPLY;
   }
}
