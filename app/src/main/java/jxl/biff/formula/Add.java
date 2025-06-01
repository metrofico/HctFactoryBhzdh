package jxl.biff.formula;

class Add extends BinaryOperator implements ParsedThing {
   public Add() {
   }

   int getPrecedence() {
      return 4;
   }

   public String getSymbol() {
      return "+";
   }

   Token getToken() {
      return Token.ADD;
   }
}
