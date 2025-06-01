package jxl.biff.formula;

class ErrorConstant extends Operand implements ParsedThing {
   private FormulaErrorCode error;

   public ErrorConstant() {
   }

   public ErrorConstant(String var1) {
      this.error = FormulaErrorCode.getErrorCode(var1);
   }

   byte[] getBytes() {
      return new byte[]{Token.ERR.getCode(), (byte)this.error.getCode()};
   }

   public void getString(StringBuffer var1) {
      var1.append(this.error.getDescription());
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      this.error = FormulaErrorCode.getErrorCode(var1[var2]);
      return 1;
   }
}
