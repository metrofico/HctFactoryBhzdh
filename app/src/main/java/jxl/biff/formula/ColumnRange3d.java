package jxl.biff.formula;

import jxl.biff.CellReferenceHelper;
import jxl.common.Assert;
import jxl.common.Logger;

class ColumnRange3d extends Area3d {
   private static Logger logger = Logger.getLogger(ColumnRange3d.class);
   private int sheet;
   private ExternalSheet workbook;

   ColumnRange3d(String var1, ExternalSheet var2) throws FormulaException {
      super(var2);
      this.workbook = var2;
      int var3 = var1.lastIndexOf(":");
      boolean var5;
      if (var3 != -1) {
         var5 = true;
      } else {
         var5 = false;
      }

      Assert.verify(var5);
      var1.substring(0, var3);
      String var7 = var1.substring(var3 + 1);
      int var4 = var1.indexOf(33);
      var3 = CellReferenceHelper.getColumn(var1.substring(var4 + 1, var3));
      String var6 = var1.substring(0, var4);
      var6.lastIndexOf(93);
      var1 = var6;
      if (var6.charAt(0) == '\'') {
         var1 = var6;
         if (var6.charAt(var6.length() - 1) == '\'') {
            var1 = var6.substring(1, var6.length() - 1);
         }
      }

      var4 = var2.getExternalSheetIndex(var1);
      this.sheet = var4;
      if (var4 >= 0) {
         var4 = CellReferenceHelper.getColumn(var7);
         this.setRangeData(this.sheet, var3, var4, 0, 65535, true, true, true, true);
      } else {
         throw new FormulaException(FormulaException.SHEET_REF_NOT_FOUND, var1);
      }
   }

   ColumnRange3d(ExternalSheet var1) {
      super(var1);
      this.workbook = var1;
   }

   public void getString(StringBuffer var1) {
      var1.append('\'');
      var1.append(this.workbook.getExternalSheetName(this.sheet));
      var1.append('\'');
      var1.append('!');
      CellReferenceHelper.getColumnReference(this.getFirstColumn(), var1);
      var1.append(':');
      CellReferenceHelper.getColumnReference(this.getLastColumn(), var1);
   }
}
