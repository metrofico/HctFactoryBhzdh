package jxl.biff.formula;

import jxl.common.Logger;

class CellReferenceError extends Operand implements ParsedThing {
   private static Logger logger = Logger.getLogger(CellReferenceError.class);

   public CellReferenceError() {
   }

   byte[] getBytes() {
      byte[] var1 = new byte[5];
      var1[0] = Token.REFERR.getCode();
      return var1;
   }

   public void getString(StringBuffer var1) {
      var1.append(FormulaErrorCode.REF.getDescription());
   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      return 4;
   }
}
