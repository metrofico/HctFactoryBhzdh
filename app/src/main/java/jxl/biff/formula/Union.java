package jxl.biff.formula;

class Union extends BinaryOperator implements ParsedThing {
   public Union() {
   }

   int getPrecedence() {
      return 4;
   }

   public String getSymbol() {
      return ",";
   }

   Token getToken() {
      return Token.UNION;
   }
}
