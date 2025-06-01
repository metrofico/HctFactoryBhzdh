package jxl.biff.formula;

import java.util.Stack;
import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.common.Assert;
import jxl.common.Logger;

class BuiltInFunction extends Operator implements ParsedThing {
   private static Logger logger = Logger.getLogger(BuiltInFunction.class);
   private Function function;
   private WorkbookSettings settings;

   public BuiltInFunction(WorkbookSettings var1) {
      this.settings = var1;
   }

   public BuiltInFunction(Function var1, WorkbookSettings var2) {
      this.function = var1;
      this.settings = var2;
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      ParseItem[] var4 = this.getOperands();

      for(int var3 = 0; var3 < var4.length; ++var3) {
         var4[var3].adjustRelativeCellReferences(var1, var2);
      }

   }

   void columnInserted(int var1, int var2, boolean var3) {
      ParseItem[] var5 = this.getOperands();

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].columnInserted(var1, var2, var3);
      }

   }

   void columnRemoved(int var1, int var2, boolean var3) {
      ParseItem[] var5 = this.getOperands();

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].columnRemoved(var1, var2, var3);
      }

   }

   byte[] getBytes() {
      ParseItem[] var5 = this.getOperands();
      byte[] var3 = new byte[0];

      int var2;
      byte[] var4;
      for(var2 = 0; var2 < var5.length; var3 = var4) {
         byte[] var6 = var5[var2].getBytes();
         var4 = new byte[var3.length + var6.length];
         System.arraycopy(var3, 0, var4, 0, var3.length);
         System.arraycopy(var6, 0, var4, var3.length, var6.length);
         ++var2;
      }

      var4 = new byte[var3.length + 3];
      System.arraycopy(var3, 0, var4, 0, var3.length);
      var2 = var3.length;
      byte var1;
      if (!this.useAlternateCode()) {
         var1 = Token.FUNCTION.getCode();
      } else {
         var1 = Token.FUNCTION.getCode2();
      }

      var4[var2] = var1;
      IntegerHelper.getTwoBytes(this.function.getCode(), var4, var3.length + 1);
      return var4;
   }

   public void getOperands(Stack var1) {
      ParseItem[] var3 = new ParseItem[this.function.getNumArgs()];

      int var2;
      for(var2 = this.function.getNumArgs() - 1; var2 >= 0; --var2) {
         var3[var2] = (ParseItem)var1.pop();
      }

      for(var2 = 0; var2 < this.function.getNumArgs(); ++var2) {
         this.add(var3[var2]);
      }

   }

   int getPrecedence() {
      return 3;
   }

   public void getString(StringBuffer var1) {
      var1.append(this.function.getName(this.settings));
      var1.append('(');
      int var3 = this.function.getNumArgs();
      if (var3 > 0) {
         ParseItem[] var4 = this.getOperands();
         var4[0].getString(var1);

         for(int var2 = 1; var2 < var3; ++var2) {
            var1.append(',');
            var4[var2].getString(var1);
         }
      }

      var1.append(')');
   }

   void handleImportedCellReferences() {
      ParseItem[] var2 = this.getOperands();

      for(int var1 = 0; var1 < var2.length; ++var1) {
         var2[var1].handleImportedCellReferences();
      }

   }

   public int read(byte[] var1, int var2) {
      byte var3 = var1[var2];
      boolean var4 = true;
      var2 = IntegerHelper.getInt(var3, var1[var2 + 1]);
      Function var5 = Function.getFunction(var2);
      this.function = var5;
      if (var5 == Function.UNKNOWN) {
         var4 = false;
      }

      Assert.verify(var4, "function code " + var2);
      return 2;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      ParseItem[] var5 = this.getOperands();

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].rowInserted(var1, var2, var3);
      }

   }

   void rowRemoved(int var1, int var2, boolean var3) {
      ParseItem[] var5 = this.getOperands();

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].rowRemoved(var1, var2, var3);
      }

   }
}
