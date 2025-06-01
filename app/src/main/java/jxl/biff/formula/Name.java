package jxl.biff.formula;

class Name extends Operand implements ParsedThing {
   public Name() {
   }

   byte[] getBytes() {
      return new byte[6];
   }

   public void getString(StringBuffer var1) {
      var1.append("[Name record not implemented]");
   }

   void handleImportedCellReferences() {
      this.setInvalid();
   }

   public int read(byte[] var1, int var2) {
      return 6;
   }
}
