package jxl.biff.formula;

class Concatenate extends BinaryOperator implements ParsedThing {
   public Concatenate() {
   }

   int getPrecedence() {
      return 3;
   }

   public String getSymbol() {
      return "&";
   }

   Token getToken() {
      return Token.CONCAT;
   }
}
