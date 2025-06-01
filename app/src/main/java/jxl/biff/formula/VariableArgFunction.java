package jxl.biff.formula;

import java.util.Stack;
import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class VariableArgFunction extends Operator implements ParsedThing {
   private static Logger logger = Logger.getLogger(VariableArgFunction.class);
   private int arguments;
   private Function function;
   private boolean readFromSheet;
   private WorkbookSettings settings;

   public VariableArgFunction(WorkbookSettings var1) {
      this.readFromSheet = true;
      this.settings = var1;
   }

   public VariableArgFunction(Function var1, int var2, WorkbookSettings var3) {
      this.function = var1;
      this.arguments = var2;
      this.readFromSheet = false;
      this.settings = var3;
   }

   private void handleSpecialCases() {
      if (this.function == Function.SUMPRODUCT) {
         ParseItem[] var3 = this.getOperands();

         for(int var1 = var3.length - 1; var1 >= 0; --var1) {
            ParseItem var2 = var3[var1];
            if (var2 instanceof Area) {
               var2.setAlternateCode();
            }
         }
      }

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
      this.handleSpecialCases();
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

      var4 = new byte[var3.length + 4];
      System.arraycopy(var3, 0, var4, 0, var3.length);
      var2 = var3.length;
      byte var1;
      if (!this.useAlternateCode()) {
         var1 = Token.FUNCTIONVARARG.getCode();
      } else {
         var1 = Token.FUNCTIONVARARG.getCode2();
      }

      var4[var2] = var1;
      var4[var3.length + 1] = (byte)this.arguments;
      IntegerHelper.getTwoBytes(this.function.getCode(), var4, var3.length + 2);
      return var4;
   }

   Function getFunction() {
      return this.function;
   }

   public void getOperands(Stack var1) {
      int var2 = this.arguments;
      ParseItem[] var3 = new ParseItem[var2];
      --var2;

      while(var2 >= 0) {
         var3[var2] = (ParseItem)var1.pop();
         --var2;
      }

      for(var2 = 0; var2 < this.arguments; ++var2) {
         this.add(var3[var2]);
      }

   }

   int getPrecedence() {
      return 3;
   }

   public void getString(StringBuffer var1) {
      var1.append(this.function.getName(this.settings));
      var1.append('(');
      if (this.arguments > 0) {
         ParseItem[] var4 = this.getOperands();
         boolean var3 = this.readFromSheet;
         int var2 = 1;
         if (var3) {
            var4[0].getString(var1);

            while(var2 < this.arguments) {
               var1.append(',');
               var4[var2].getString(var1);
               ++var2;
            }
         } else {
            var4[this.arguments - 1].getString(var1);

            for(var2 = this.arguments - 2; var2 >= 0; --var2) {
               var1.append(',');
               var4[var2].getString(var1);
            }
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

   public int read(byte[] var1, int var2) throws FormulaException {
      this.arguments = var1[var2];
      var2 = IntegerHelper.getInt(var1[var2 + 1], var1[var2 + 2]);
      Function var3 = Function.getFunction(var2);
      this.function = var3;
      if (var3 != Function.UNKNOWN) {
         return 3;
      } else {
         throw new FormulaException(FormulaException.UNRECOGNIZED_FUNCTION, var2);
      }
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
