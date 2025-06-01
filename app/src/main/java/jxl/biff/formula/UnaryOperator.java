package jxl.biff.formula;

import java.util.Stack;

abstract class UnaryOperator extends Operator implements ParsedThing {
   public UnaryOperator() {
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      this.getOperands()[0].adjustRelativeCellReferences(var1, var2);
   }

   void columnInserted(int var1, int var2, boolean var3) {
      this.getOperands()[0].columnInserted(var1, var2, var3);
   }

   void columnRemoved(int var1, int var2, boolean var3) {
      this.getOperands()[0].columnRemoved(var1, var2, var3);
   }

   byte[] getBytes() {
      byte[] var1 = this.getOperands()[0].getBytes();
      byte[] var2 = new byte[var1.length + 1];
      System.arraycopy(var1, 0, var2, 0, var1.length);
      var2[var1.length] = this.getToken().getCode();
      return var2;
   }

   public void getOperands(Stack var1) {
      this.add((ParseItem)var1.pop());
   }

   public void getString(StringBuffer var1) {
      ParseItem[] var2 = this.getOperands();
      var1.append(this.getSymbol());
      var2[0].getString(var1);
   }

   abstract String getSymbol();

   abstract Token getToken();

   void handleImportedCellReferences() {
      this.getOperands()[0].handleImportedCellReferences();
   }

   public int read(byte[] var1, int var2) {
      return 0;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      this.getOperands()[0].rowInserted(var1, var2, var3);
   }

   void rowRemoved(int var1, int var2, boolean var3) {
      this.getOperands()[0].rowRemoved(var1, var2, var3);
   }
}
