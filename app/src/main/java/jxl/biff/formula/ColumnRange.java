package jxl.biff.formula;

import jxl.biff.CellReferenceHelper;
import jxl.common.Assert;
import jxl.common.Logger;

class ColumnRange extends Area {
   private static Logger logger = Logger.getLogger(ColumnRange.class);

   ColumnRange() {
   }

   ColumnRange(String var1) {
      int var2 = var1.indexOf(":");
      boolean var3;
      if (var2 != -1) {
         var3 = true;
      } else {
         var3 = false;
      }

      Assert.verify(var3);
      String var4 = var1.substring(0, var2);
      var1 = var1.substring(var2 + 1);
      this.setRangeData(CellReferenceHelper.getColumn(var4), CellReferenceHelper.getColumn(var1), 0, 65535, CellReferenceHelper.isColumnRelative(var4), CellReferenceHelper.isColumnRelative(var1), false, false);
   }

   public void getString(StringBuffer var1) {
      CellReferenceHelper.getColumnReference(this.getFirstColumn(), var1);
      var1.append(':');
      CellReferenceHelper.getColumnReference(this.getLastColumn(), var1);
   }
}
