package jxl;

import jxl.biff.formula.ExternalSheet;
import jxl.write.WritableWorkbook;

public final class CellReferenceHelper {
   private CellReferenceHelper() {
   }

   public static String getCellReference(int var0, int var1) {
      return jxl.biff.CellReferenceHelper.getCellReference(var0, var1);
   }

   public static String getCellReference(int var0, int var1, int var2, Workbook var3) {
      return jxl.biff.CellReferenceHelper.getCellReference(var0, var1, var2, (ExternalSheet)var3);
   }

   public static String getCellReference(int var0, int var1, int var2, WritableWorkbook var3) {
      return jxl.biff.CellReferenceHelper.getCellReference(var0, var1, var2, (ExternalSheet)var3);
   }

   public static String getCellReference(Cell var0) {
      return getCellReference(var0.getColumn(), var0.getRow());
   }

   public static void getCellReference(int var0, int var1, int var2, Workbook var3, StringBuffer var4) {
      jxl.biff.CellReferenceHelper.getCellReference(var0, var1, var2, (ExternalSheet)var3, var4);
   }

   public static void getCellReference(int var0, int var1, int var2, WritableWorkbook var3, StringBuffer var4) {
      jxl.biff.CellReferenceHelper.getCellReference(var0, var1, var2, (ExternalSheet)var3, var4);
   }

   public static void getCellReference(int var0, int var1, StringBuffer var2) {
      jxl.biff.CellReferenceHelper.getCellReference(var0, var1, var2);
   }

   public static void getCellReference(int var0, int var1, boolean var2, int var3, boolean var4, Workbook var5, StringBuffer var6) {
      jxl.biff.CellReferenceHelper.getCellReference(var0, var1, var2, var3, var4, (ExternalSheet)var5, var6);
   }

   public static void getCellReference(int var0, boolean var1, int var2, boolean var3, StringBuffer var4) {
      jxl.biff.CellReferenceHelper.getCellReference(var0, var1, var2, var3, var4);
   }

   public static void getCellReference(Cell var0, StringBuffer var1) {
      getCellReference(var0.getColumn(), var0.getRow(), var1);
   }

   public static int getColumn(String var0) {
      return jxl.biff.CellReferenceHelper.getColumn(var0);
   }

   public static String getColumnReference(int var0) {
      return jxl.biff.CellReferenceHelper.getColumnReference(var0);
   }

   public static int getRow(String var0) {
      return jxl.biff.CellReferenceHelper.getRow(var0);
   }

   public static String getSheet(String var0) {
      return jxl.biff.CellReferenceHelper.getSheet(var0);
   }

   public static boolean isColumnRelative(String var0) {
      return jxl.biff.CellReferenceHelper.isColumnRelative(var0);
   }

   public static boolean isRowRelative(String var0) {
      return jxl.biff.CellReferenceHelper.isRowRelative(var0);
   }
}
