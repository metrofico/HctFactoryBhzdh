package jxl.biff.formula;

public class FormulaErrorCode {
   public static final FormulaErrorCode DIV0 = new FormulaErrorCode(7, "#DIV/0!");
   public static final FormulaErrorCode NA = new FormulaErrorCode(42, "#N/A!");
   public static final FormulaErrorCode NAME = new FormulaErrorCode(29, "#NAME?");
   public static final FormulaErrorCode NULL = new FormulaErrorCode(0, "#NULL!");
   public static final FormulaErrorCode NUM = new FormulaErrorCode(36, "#NUM!");
   public static final FormulaErrorCode REF = new FormulaErrorCode(23, "#REF!");
   public static final FormulaErrorCode UNKNOWN = new FormulaErrorCode(255, "?");
   public static final FormulaErrorCode VALUE = new FormulaErrorCode(15, "#VALUE!");
   private static FormulaErrorCode[] codes = new FormulaErrorCode[0];
   private String description;
   private int errorCode;

   FormulaErrorCode(int var1, String var2) {
      this.errorCode = var1;
      this.description = var2;
      FormulaErrorCode[] var4 = codes;
      FormulaErrorCode[] var3 = new FormulaErrorCode[var4.length + 1];
      System.arraycopy(var4, 0, var3, 0, var4.length);
      var3[codes.length] = this;
      codes = var3;
   }

   public static FormulaErrorCode getErrorCode(int var0) {
      FormulaErrorCode var3 = UNKNOWN;
      int var1 = 0;
      boolean var2 = false;

      while(true) {
         FormulaErrorCode[] var4 = codes;
         if (var1 >= var4.length || var2) {
            return var3;
         }

         FormulaErrorCode var5 = var4[var1];
         if (var5.errorCode == var0) {
            var2 = true;
            var3 = var5;
         }

         ++var1;
      }
   }

   public static FormulaErrorCode getErrorCode(String var0) {
      FormulaErrorCode var3 = UNKNOWN;
      FormulaErrorCode var4 = var3;
      if (var0 != null) {
         if (var0.length() == 0) {
            var4 = var3;
         } else {
            int var1 = 0;
            boolean var2 = false;

            while(true) {
               FormulaErrorCode[] var5 = codes;
               var4 = var3;
               if (var1 >= var5.length) {
                  break;
               }

               var4 = var3;
               if (var2) {
                  break;
               }

               if (var5[var1].description.equals(var0)) {
                  var3 = codes[var1];
                  var2 = true;
               }

               ++var1;
            }
         }
      }

      return var4;
   }

   public int getCode() {
      return this.errorCode;
   }

   public String getDescription() {
      return this.description;
   }
}
