package jxl.biff.formula;

import java.util.Stack;

class Parenthesis extends Operator implements ParsedThing {
   public Parenthesis() {
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

   int getPrecedence() {
      return 4;
   }

   public void getString(StringBuffer var1) {
      ParseItem[] var2 = this.getOperands();
      var1.append('(');
      var2[0].getString(var1);
      var1.append(')');
   }

   Token getToken() {
      return Token.PARENTHESIS;
   }

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
