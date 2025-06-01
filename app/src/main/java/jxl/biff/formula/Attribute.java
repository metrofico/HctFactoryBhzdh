package jxl.biff.formula;

import java.util.Stack;
import jxl.WorkbookSettings;
import jxl.biff.IntegerHelper;
import jxl.common.Logger;

class Attribute extends Operator implements ParsedThing {
   private static final int CHOOSE_MASK = 4;
   private static final int GOTO_MASK = 8;
   private static final int IF_MASK = 2;
   private static final int SUM_MASK = 16;
   private static Logger logger = Logger.getLogger(Attribute.class);
   private VariableArgFunction ifConditions;
   private int options;
   private WorkbookSettings settings;
   private int word;

   public Attribute(WorkbookSettings var1) {
      this.settings = var1;
   }

   public Attribute(StringFunction var1, WorkbookSettings var2) {
      this.settings = var2;
      if (var1.getFunction(var2) == Function.SUM) {
         this.options |= 16;
      } else if (var1.getFunction(this.settings) == Function.IF) {
         this.options |= 2;
      }

   }

   private byte[] getIf() {
      ParseItem[] var8 = this.ifConditions.getOperands();
      int var1 = var8.length;
      byte[] var7 = var8[0].getBytes();
      int var2 = var7.length;
      int var3 = var7.length + 4;
      byte[] var6 = new byte[var3];
      System.arraycopy(var7, 0, var6, 0, var7.length);
      var6[var2] = Token.ATTRIBUTE.getCode();
      var6[var2 + 1] = 2;
      var2 += 2;
      var7 = var8[1].getBytes();
      int var5 = var7.length + var3;
      byte[] var9 = new byte[var5];
      System.arraycopy(var6, 0, var9, 0, var3);
      System.arraycopy(var7, 0, var9, var3, var7.length);
      int var4 = var5 + 4;
      var7 = new byte[var4];
      System.arraycopy(var9, 0, var7, 0, var5);
      var7[var5] = Token.ATTRIBUTE.getCode();
      var7[var5 + 1] = 8;
      var3 = var5 + 2;
      var6 = var7;
      if (var1 > 2) {
         IntegerHelper.getTwoBytes(var4 - var2 - 2, var7, var2);
         var6 = var8[var1 - 1].getBytes();
         var5 = var6.length + var4;
         byte[] var10 = new byte[var5];
         System.arraycopy(var7, 0, var10, 0, var4);
         System.arraycopy(var6, 0, var10, var4, var6.length);
         var6 = new byte[var5 + 4];
         System.arraycopy(var10, 0, var6, 0, var5);
         var6[var5] = Token.ATTRIBUTE.getCode();
         var6[var5 + 1] = 8;
         var6[var5 + 2] = 3;
      }

      var5 = var6.length;
      var4 = var6.length + 4;
      var7 = new byte[var4];
      System.arraycopy(var6, 0, var7, 0, var6.length);
      var7[var5] = Token.FUNCTIONVARARG.getCode();
      var7[var5 + 1] = (byte)var1;
      var7[var5 + 2] = 1;
      var7[var5 + 3] = 0;
      --var4;
      if (var1 < 3) {
         IntegerHelper.getTwoBytes(var4 - var2 - 5, var7, var2);
      }

      IntegerHelper.getTwoBytes(var4 - var3 - 2, var7, var3);
      return var7;
   }

   public void adjustRelativeCellReferences(int var1, int var2) {
      ParseItem[] var4;
      if (this.isIf()) {
         var4 = this.ifConditions.getOperands();
      } else {
         var4 = this.getOperands();
      }

      for(int var3 = 0; var3 < var4.length; ++var3) {
         var4[var3].adjustRelativeCellReferences(var1, var2);
      }

   }

   void columnInserted(int var1, int var2, boolean var3) {
      ParseItem[] var5;
      if (this.isIf()) {
         var5 = this.ifConditions.getOperands();
      } else {
         var5 = this.getOperands();
      }

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].columnInserted(var1, var2, var3);
      }

   }

   void columnRemoved(int var1, int var2, boolean var3) {
      ParseItem[] var5;
      if (this.isIf()) {
         var5 = this.ifConditions.getOperands();
      } else {
         var5 = this.getOperands();
      }

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].columnRemoved(var1, var2, var3);
      }

   }

   byte[] getBytes() {
      byte[] var2 = new byte[0];
      if (this.isSum()) {
         ParseItem[] var4 = this.getOperands();

         byte[] var3;
         for(int var1 = var4.length - 1; var1 >= 0; var2 = var3) {
            byte[] var5 = var4[var1].getBytes();
            var3 = new byte[var2.length + var5.length];
            System.arraycopy(var2, 0, var3, 0, var2.length);
            System.arraycopy(var5, 0, var3, var2.length, var5.length);
            --var1;
         }

         var3 = new byte[var2.length + 4];
         System.arraycopy(var2, 0, var3, 0, var2.length);
         var3[var2.length] = Token.ATTRIBUTE.getCode();
         var3[var2.length + 1] = 16;
         var2 = var3;
      } else if (this.isIf()) {
         return this.getIf();
      }

      return var2;
   }

   public void getOperands(Stack var1) {
      int var2 = this.options;
      if ((var2 & 16) != 0) {
         this.add((ParseItem)var1.pop());
      } else if ((var2 & 2) != 0) {
         this.add((ParseItem)var1.pop());
      }

   }

   int getPrecedence() {
      return 3;
   }

   public void getString(StringBuffer var1) {
      int var3 = this.options;
      int var2 = 0;
      ParseItem[] var4;
      if ((var3 & 16) != 0) {
         var4 = this.getOperands();
         var1.append(Function.SUM.getName(this.settings));
         var1.append('(');
         var4[0].getString(var1);
         var1.append(')');
      } else if ((var3 & 2) != 0) {
         var1.append(Function.IF.getName(this.settings));
         var1.append('(');

         for(var4 = this.ifConditions.getOperands(); var2 < var4.length - 1; ++var2) {
            var4[var2].getString(var1);
            var1.append(',');
         }

         var4[var4.length - 1].getString(var1);
         var1.append(')');
      }

   }

   void handleImportedCellReferences() {
      ParseItem[] var2;
      if (this.isIf()) {
         var2 = this.ifConditions.getOperands();
      } else {
         var2 = this.getOperands();
      }

      for(int var1 = 0; var1 < var2.length; ++var1) {
         var2[var1].handleImportedCellReferences();
      }

   }

   public boolean isChoose() {
      boolean var1;
      if ((this.options & 4) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isFunction() {
      boolean var1;
      if ((this.options & 18) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isGoto() {
      boolean var1;
      if ((this.options & 8) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isIf() {
      boolean var1;
      if ((this.options & 2) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isSum() {
      boolean var1;
      if ((this.options & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public int read(byte[] var1, int var2) {
      this.options = var1[var2];
      this.word = IntegerHelper.getInt(var1[var2 + 1], var1[var2 + 2]);
      return !this.isChoose() ? 3 : (this.word + 1) * 2 + 3;
   }

   void rowInserted(int var1, int var2, boolean var3) {
      ParseItem[] var5;
      if (this.isIf()) {
         var5 = this.ifConditions.getOperands();
      } else {
         var5 = this.getOperands();
      }

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].rowInserted(var1, var2, var3);
      }

   }

   void rowRemoved(int var1, int var2, boolean var3) {
      ParseItem[] var5;
      if (this.isIf()) {
         var5 = this.ifConditions.getOperands();
      } else {
         var5 = this.getOperands();
      }

      for(int var4 = 0; var4 < var5.length; ++var4) {
         var5[var4].rowRemoved(var1, var2, var3);
      }

   }

   void setIfConditions(VariableArgFunction var1) {
      this.ifConditions = var1;
      this.options |= 2;
   }
}
