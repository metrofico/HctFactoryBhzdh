package jxl.read.biff;

import jxl.CellType;
import jxl.WorkbookSettings;
import jxl.biff.DoubleHelper;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.common.Assert;
import jxl.common.Logger;

class FormulaRecord extends CellValue {
   public static final IgnoreSharedFormula ignoreSharedFormula = new IgnoreSharedFormula();
   private static Logger logger = Logger.getLogger(FormulaRecord.class);
   private CellValue formula;
   private boolean shared;

   public FormulaRecord(Record var1, File var2, FormattingRecords var3, ExternalSheet var4, WorkbookMethods var5, IgnoreSharedFormula var6, SheetImpl var7, WorkbookSettings var8) {
      super(var1, var3, var7);
      byte[] var10 = this.getRecord().getData();
      this.shared = false;
      byte var9 = var10[6];
      if (var9 == 0 && var10[12] == -1 && var10[13] == -1) {
         this.formula = new StringFormulaRecord(var1, var2, var3, var4, var5, var7, var8);
      } else if (var9 == 1 && var10[12] == -1 && var10[13] == -1) {
         this.formula = new BooleanFormulaRecord(var1, var3, var4, var5, var7);
      } else if (var9 == 2 && var10[12] == -1 && var10[13] == -1) {
         this.formula = new ErrorFormulaRecord(var1, var3, var4, var5, var7);
      } else {
         this.formula = new NumberFormulaRecord(var1, var3, var4, var5, var7);
      }

   }

   public FormulaRecord(Record var1, File var2, FormattingRecords var3, ExternalSheet var4, WorkbookMethods var5, SheetImpl var6, WorkbookSettings var7) {
      super(var1, var3, var6);
      byte[] var10 = this.getRecord().getData();
      this.shared = false;
      byte var8;
      if ((IntegerHelper.getInt(var10[14], var10[15]) & 8) != 0) {
         this.shared = true;
         var8 = var10[6];
         if (var8 == 0 && var10[12] == -1 && var10[13] == -1) {
            this.formula = new SharedStringFormulaRecord(var1, var2, var3, var4, var5, var6, var7);
         } else if (var8 == 3 && var10[12] == -1 && var10[13] == -1) {
            this.formula = new SharedStringFormulaRecord(var1, var2, var3, var4, var5, var6, SharedStringFormulaRecord.EMPTY_STRING);
         } else if (var8 == 2 && var10[12] == -1 && var10[13] == -1) {
            this.formula = new SharedErrorFormulaRecord(var1, var2, var10[8], var3, var4, var5, var6);
         } else if (var8 == 1 && var10[12] == -1 && var10[13] == -1) {
            boolean var9;
            if (var10[8] == 1) {
               var9 = true;
            } else {
               var9 = false;
            }

            this.formula = new SharedBooleanFormulaRecord(var1, var2, var9, var3, var4, var5, var6);
         } else {
            SharedNumberFormulaRecord var11 = new SharedNumberFormulaRecord(var1, var2, DoubleHelper.getIEEEDouble(var10, 6), var3, var4, var5, var6);
            var11.setNumberFormat(var3.getNumberFormat(this.getXFIndex()));
            this.formula = var11;
         }

      } else {
         var8 = var10[6];
         if (var8 == 0 && var10[12] == -1 && var10[13] == -1) {
            this.formula = new StringFormulaRecord(var1, var2, var3, var4, var5, var6, var7);
         } else if (var8 == 1 && var10[12] == -1 && var10[13] == -1) {
            this.formula = new BooleanFormulaRecord(var1, var3, var4, var5, var6);
         } else if (var8 == 2 && var10[12] == -1 && var10[13] == -1) {
            this.formula = new ErrorFormulaRecord(var1, var3, var4, var5, var6);
         } else if (var8 == 3 && var10[12] == -1 && var10[13] == -1) {
            this.formula = new StringFormulaRecord(var1, var3, var4, var5, var6);
         } else {
            this.formula = new NumberFormulaRecord(var1, var3, var4, var5, var6);
         }

      }
   }

   public String getContents() {
      Assert.verify(false);
      return "";
   }

   final CellValue getFormula() {
      return this.formula;
   }

   public CellType getType() {
      Assert.verify(false);
      return CellType.EMPTY;
   }

   final boolean isShared() {
      return this.shared;
   }

   private static class IgnoreSharedFormula {
      private IgnoreSharedFormula() {
      }

      // $FF: synthetic method
      IgnoreSharedFormula(Object var1) {
         this();
      }
   }
}
