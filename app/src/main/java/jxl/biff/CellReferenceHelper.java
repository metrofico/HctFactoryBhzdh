package jxl.biff;

import jxl.biff.formula.ExternalSheet;
import jxl.common.Logger;

public final class CellReferenceHelper {
   private static final char fixedInd = '$';
   private static Logger logger = Logger.getLogger(CellReferenceHelper.class);
   private static final char sheetInd = '!';

   private CellReferenceHelper() {
   }

   public static String getCellReference(int var0, int var1) {
      StringBuffer var2 = new StringBuffer();
      getCellReference(var0, var1, var2);
      return var2.toString();
   }

   public static String getCellReference(int var0, int var1, int var2, ExternalSheet var3) {
      StringBuffer var4 = new StringBuffer();
      getCellReference(var0, var1, var2, var3, var4);
      return var4.toString();
   }

   public static void getCellReference(int var0, int var1, int var2, ExternalSheet var3, StringBuffer var4) {
      var4.append(StringHelper.replace(var3.getExternalSheetName(var0), "'", "''"));
      var4.append('!');
      getCellReference(var1, var2, var4);
   }

   public static void getCellReference(int var0, int var1, StringBuffer var2) {
      getColumnReference(var0, var2);
      var2.append(Integer.toString(var1 + 1));
   }

   public static void getCellReference(int var0, int var1, boolean var2, int var3, boolean var4, ExternalSheet var5, StringBuffer var6) {
      var6.append(var5.getExternalSheetName(var0));
      var6.append('!');
      getCellReference(var1, var2, var3, var4, var6);
   }

   public static void getCellReference(int var0, boolean var1, int var2, boolean var3, StringBuffer var4) {
      if (var1) {
         var4.append('$');
      }

      getColumnReference(var0, var4);
      if (var3) {
         var4.append('$');
      }

      var4.append(Integer.toString(var2 + 1));
   }

   public static int getColumn(String var0) {
      int var2 = getNumberIndex(var0);
      String var6 = var0.toUpperCase();
      int var3 = var0.lastIndexOf(33) + 1;
      int var1 = var3;
      if (var0.charAt(var3) == '$') {
         var1 = var3 + 1;
      }

      var3 = var2;
      if (var0.charAt(var2 - 1) == '$') {
         var3 = var2 - 1;
      }

      var2 = 0;

      for(int var4 = var1; var4 < var3; ++var4) {
         int var5 = var2;
         if (var4 != var1) {
            var5 = (var2 + 1) * 26;
         }

         var2 = var5 + (var6.charAt(var4) - 65);
      }

      return var2;
   }

   public static String getColumnReference(int var0) {
      StringBuffer var1 = new StringBuffer();
      getColumnReference(var0, var1);
      return var1.toString();
   }

   public static void getColumnReference(int var0, StringBuffer var1) {
      int var3 = var0 / 26;
      int var2 = var0 % 26;
      StringBuffer var4 = new StringBuffer();

      for(var0 = var3; var0 != 0; var0 /= 26) {
         var4.append((char)(var2 + 65));
         var2 = var0 % 26 - 1;
      }

      var4.append((char)(var2 + 65));

      for(var0 = var4.length() - 1; var0 >= 0; --var0) {
         var1.append(var4.charAt(var0));
      }

   }

   private static int getNumberIndex(String var0) {
      int var2 = var0.lastIndexOf(33) + 1;
      boolean var1 = false;

      while(!var1 && var2 < var0.length()) {
         char var3 = var0.charAt(var2);
         if (var3 >= '0' && var3 <= '9') {
            var1 = true;
         } else {
            ++var2;
         }
      }

      return var2;
   }

   public static int getRow(String var0) {
      int var1;
      try {
         var1 = Integer.parseInt(var0.substring(getNumberIndex(var0)));
      } catch (NumberFormatException var2) {
         logger.warn(var2, var2);
         return 65535;
      }

      return var1 - 1;
   }

   public static String getSheet(String var0) {
      int var1 = var0.lastIndexOf(33);
      return var1 == -1 ? "" : var0.substring(0, var1);
   }

   public static boolean isColumnRelative(String var0) {
      boolean var1 = false;
      if (var0.charAt(0) != '$') {
         var1 = true;
      }

      return var1;
   }

   public static boolean isRowRelative(String var0) {
      int var1 = getNumberIndex(var0);
      boolean var2 = true;
      if (var0.charAt(var1 - 1) == '$') {
         var2 = false;
      }

      return var2;
   }
}
