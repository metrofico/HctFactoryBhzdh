package jxl.biff.formula;

class Percent extends UnaryOperator implements ParsedThing {
   public Percent() {
   }

   int getPrecedence() {
      return 5;
   }

   public void getString(StringBuffer var1) {
      this.getOperands()[0].getString(var1);
      var1.append(this.getSymbol());
   }

   public String getSymbol() {
      return "%";
   }

   Token getToken() {
      return Token.PERCENT;
   }

   void handleImportedCellReferences() {
      this.getOperands()[0].handleImportedCellReferences();
   }
}
