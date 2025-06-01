package jxl.biff.formula;

class Subtract extends BinaryOperator implements ParsedThing {
   public Subtract() {
   }

   int getPrecedence() {
      return 4;
   }

   public String getSymbol() {
      return "-";
   }

   Token getToken() {
      return Token.SUBTRACT;
   }
}
