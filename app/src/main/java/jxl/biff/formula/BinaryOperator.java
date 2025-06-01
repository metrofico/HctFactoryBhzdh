package jxl.biff.formula;

import java.util.Stack;
import jxl.common.Logger;

abstract class BinaryOperator extends Operator implements ParsedThing {
   private static final Logger logger = Logger.getLogger(BinaryOperator.class);

   public BinaryOperator() {
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      ParseItem[] var3 = this.getOperands();
      var3[1].adjustRelativeCellReferences(var1, var2);
      var3[0].adjustRelativeCellReferences(var1, var2);
   }

   void columnInserted(int var1, int var2, boolean var3) {
      ParseItem[] var4 = this.getOperands();
      var4[1].columnInserted(var1, var2, var3);
      var4[0].columnInserted(var1, var2, var3);
   }

   void columnRemoved(int var1, int var2, boolean var3) {
      ParseItem[] var4 = this.getOperands();
      var4[1].columnRemoved(var1, var2, var3);
      var4[0].columnRemoved(var1, var2, var3);
   }

   byte[] getBytes() {
      ParseItem[] var4 = this.getOperands();
      byte[] var2 = new byte[0];

      byte[] var3;
      for(int var1 = var4.length - 1; var1 >= 0; var2 = var3) {
         byte[] var5 = var4[var1].getBytes();
         var3 = new byte[var2.length + var5.length];
         System.arraycopy(var2, 0, var3, 0, var2.length);
         System.arraycopy(var5, 0, var3, var2.length, var5.length);
         --var1;
      }

      var3 = new byte[var2.length + 1];
      System.arraycopy(var2, 0, var3, 0, var2.length);
      var3[var2.length] = this.getToken().getCode();
      return var3;
   }

   public void getOperands(Stack var1) {
      ParseItem var2 = (ParseItem)var1.pop();
      ParseItem var3 = (ParseItem)var1.pop();
      this.add(var2);
      this.add(var3);
   }

   public void getString(StringBuffer var1) {
      ParseItem[] var2 = this.getOperands();
      var2[1].getString(var1);
      var1.append(this.getSymbol());
      var2[0].getString(var1);
   }

   abstract String getSymbol();

   abstract Token getToken();

   void handleImportedCellReferences() {
      ParseItem[] var1 = this.getOperands();
      var1[0].handleImportedCellReferences();
      var1[1].handleImportedCellReferences();
   }

   public int read(byte[] var1, int var2) {
      return 0;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      ParseItem[] var4 = this.getOperands();
      var4[1].rowInserted(var1, var2, var3);
      var4[0].rowInserted(var1, var2, var3);
   }

   void rowRemoved(int var1, int var2, boolean var3) {
      ParseItem[] var4 = this.getOperands();
      var4[1].rowRemoved(var1, var2, var3);
      var4[0].rowRemoved(var1, var2, var3);
   }
}
