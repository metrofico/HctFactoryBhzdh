package jxl.biff.formula;

class LessThan extends BinaryOperator implements ParsedThing {
   public LessThan() {
   }

   int getPrecedence() {
      return 5;
   }

   public String getSymbol() {
      return "<";
   }

   Token getToken() {
      return Token.LESS_THAN;
   }
}
