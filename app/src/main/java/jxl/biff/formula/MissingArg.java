package jxl.biff.formula;

class MissingArg extends Operand implements ParsedThing {
   public MissingArg() {
   }

   byte[] getBytes() {
      return new byte[]{Token.MISSING_ARG.getCode()};
   }

   public void getString(StringBuffer var1) {
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      return 0;
   }
}
