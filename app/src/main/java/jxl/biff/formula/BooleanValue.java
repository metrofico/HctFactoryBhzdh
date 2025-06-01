package jxl.biff.formula;

class BooleanValue extends Operand implements ParsedThing {
   private boolean value;

   public BooleanValue() {
   }

   public BooleanValue(String var1) {
      this.value = Boolean.valueOf(var1);
   }

   byte[] getBytes() {
      byte var1 = Token.BOOL.getCode();
      byte var2 = 0;
      if (this.value) {
         var2 = 1;
      }

      return new byte[]{var1, (byte)var2};
   }

   public void getString(StringBuffer var1) {
      var1.append((new Boolean(this.value)).toString());
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      boolean var3;
      if (var1[var2] == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.value = var3;
      return 1;
   }
}
