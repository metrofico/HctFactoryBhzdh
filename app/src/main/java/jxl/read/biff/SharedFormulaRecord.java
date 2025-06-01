package jxl.read.biff;

import java.util.ArrayList;
import jxl.Cell;
import jxl.CellType;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.common.Logger;

class SharedFormulaRecord {
   private static Logger logger = Logger.getLogger(SharedFormulaRecord.class);
   private ExternalSheet externalSheet;
   private int firstCol;
   private int firstRow;
   private ArrayList formulas;
   private int lastCol;
   private int lastRow;
   private SheetImpl sheet;
   private BaseSharedFormulaRecord templateFormula;
   private byte[] tokens;

   public SharedFormulaRecord(Record var1, BaseSharedFormulaRecord var2, ExternalSheet var3, WorkbookMethods var4, SheetImpl var5) {
      this.sheet = var5;
      byte[] var6 = var1.getData();
      this.firstRow = IntegerHelper.getInt(var6[0], var6[1]);
      this.lastRow = IntegerHelper.getInt(var6[2], var6[3]);
      this.firstCol = var6[4] & 255;
      this.lastCol = var6[5] & 255;
      this.formulas = new ArrayList();
      this.templateFormula = var2;
      byte[] var7 = new byte[var6.length - 10];
      this.tokens = var7;
      System.arraycopy(var6, 10, var7, 0, var7.length);
   }

   public boolean add(BaseSharedFormulaRecord var1) {
      int var2 = var1.getRow();
      boolean var3;
      if (var2 >= this.firstRow && var2 <= this.lastRow) {
         var2 = var1.getColumn();
         if (var2 >= this.firstCol && var2 <= this.lastCol) {
            this.formulas.add(var1);
            var3 = true;
            return var3;
         }
      }

      var3 = false;
      return var3;
   }

   Cell[] getFormulas(FormattingRecords var1, boolean var2) {
      Cell[] var6 = new Cell[this.formulas.size() + 1];
      BaseSharedFormulaRecord var4 = this.templateFormula;
      int var3 = 0;
      if (var4 == null) {
         logger.warn("Shared formula template formula is null");
         return new Cell[0];
      } else {
         var4.setTokens(this.tokens);
         if (this.templateFormula.getType() == CellType.NUMBER_FORMULA) {
            SharedNumberFormulaRecord var5 = (SharedNumberFormulaRecord)this.templateFormula;
            var5.getNumberFormat();
            if (var1.isDate(this.templateFormula.getXFIndex())) {
               SharedDateFormulaRecord var8 = new SharedDateFormulaRecord(var5, var1, var2, this.sheet, var5.getFilePos());
               this.templateFormula = var8;
               var8.setTokens(var5.getTokens());
            }
         }

         Object var9;
         for(var6[0] = this.templateFormula; var3 < this.formulas.size(); var6[var3] = (Cell)var9) {
            BaseSharedFormulaRecord var10 = (BaseSharedFormulaRecord)this.formulas.get(var3);
            var9 = var10;
            if (var10.getType() == CellType.NUMBER_FORMULA) {
               SharedNumberFormulaRecord var7 = (SharedNumberFormulaRecord)var10;
               var9 = var10;
               if (var1.isDate(var10.getXFIndex())) {
                  var9 = new SharedDateFormulaRecord(var7, var1, var2, this.sheet, var7.getFilePos());
               }
            }

            ((BaseSharedFormulaRecord)var9).setTokens(this.tokens);
            ++var3;
         }

         return var6;
      }
   }

   BaseSharedFormulaRecord getTemplateFormula() {
      return this.templateFormula;
   }
}
