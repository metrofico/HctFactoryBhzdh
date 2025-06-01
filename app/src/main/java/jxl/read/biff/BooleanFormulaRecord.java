package jxl.read.biff;

import jxl.BooleanCell;
import jxl.BooleanFormulaCell;
import jxl.CellType;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Assert;

class BooleanFormulaRecord extends CellValue implements BooleanCell, FormulaData, BooleanFormulaCell {
   private byte[] data;
   private ExternalSheet externalSheet;
   private String formulaString;
   private WorkbookMethods nameTable;
   private boolean value;

   public BooleanFormulaRecord(Record var1, FormattingRecords var2, ExternalSheet var3, WorkbookMethods var4, SheetImpl var5) {
      super(var1, var2, var5);
      this.externalSheet = var3;
      this.nameTable = var4;
      boolean var7 = false;
      this.value = false;
      byte[] var8 = this.getRecord().getData();
      this.data = var8;
      boolean var6;
      if (var8[6] != 2) {
         var6 = true;
      } else {
         var6 = false;
      }

      Assert.verify(var6);
      var6 = var7;
      if (this.data[8] == 1) {
         var6 = true;
      }

      this.value = var6;
   }

   public String getContents() {
      return (new Boolean(this.value)).toString();
   }

   public String getFormula() throws FormulaException {
      if (this.formulaString == null) {
         byte[] var3 = this.data;
         int var1 = var3.length - 22;
         byte[] var2 = new byte[var1];
         System.arraycopy(var3, 22, var2, 0, var1);
         FormulaParser var4 = new FormulaParser(var2, this, this.externalSheet, this.nameTable, this.getSheet().getWorkbook().getSettings());
         var4.parse();
         this.formulaString = var4.getFormula();
      }

      return this.formulaString;
   }

   public byte[] getFormulaData() throws FormulaException {
      if (this.getSheet().getWorkbookBof().isBiff8()) {
         byte[] var1 = this.data;
         byte[] var2 = new byte[var1.length - 6];
         System.arraycopy(var1, 6, var2, 0, var1.length - 6);
         return var2;
      } else {
         throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
      }
   }

   public CellType getType() {
      return CellType.BOOLEAN_FORMULA;
   }

   public boolean getValue() {
      return this.value;
   }
}
