package jxl.biff.formula;

import jxl.biff.IntegerHelper;

class MemArea extends SubExpression {
   public MemArea() {
   }

   public void getString(StringBuffer var1) {
      ParseItem[] var2 = this.getSubExpression();
      if (var2.length == 1) {
         var2[0].getString(var1);
      } else if (var2.length == 2) {
         var2[1].getString(var1);
         var1.append(':');
         var2[0].getString(var1);
      }

   }

   void handleImportedCellReferences() {
   }

   public int read(byte[] var1, int var2) {
      this.setLength(IntegerHelper.getInt(var1[var2 + 4], var1[var2 + 5]));
      return 6;
   }
}
