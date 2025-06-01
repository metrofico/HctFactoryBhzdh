package jxl.biff.formula;

class GreaterThan extends BinaryOperator implements ParsedThing {
   public GreaterThan() {
   }

   int getPrecedence() {
      return 5;
   }

   public String getSymbol() {
      return ">";
   }

   Token getToken() {
      return Token.GREATER_THAN;
   }
}
