package jxl.read.biff;

import java.text.NumberFormat;
import jxl.CellType;
import jxl.DateCell;
import jxl.DateFormulaCell;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;

class DateFormulaRecord extends DateRecord implements DateCell, FormulaData, DateFormulaCell {
   private byte[] data;
   private ExternalSheet externalSheet;
   private String formulaString;
   private WorkbookMethods nameTable;

   public DateFormulaRecord(NumberFormulaRecord var1, FormattingRecords var2, ExternalSheet var3, WorkbookMethods var4, boolean var5, SheetImpl var6) throws FormulaException {
      super(var1, var1.getXFIndex(), var2, var5, var6);
      this.externalSheet = var3;
      this.nameTable = var4;
      this.data = var1.getFormulaData();
   }

   public String getFormula() throws FormulaException {
      if (this.formulaString == null) {
         byte[] var2 = this.data;
         int var1 = var2.length - 16;
         byte[] var3 = new byte[var1];
         System.arraycopy(var2, 16, var3, 0, var1);
         FormulaParser var4 = new FormulaParser(var3, this, this.externalSheet, this.nameTable, this.getSheet().getWorkbook().getSettings());
         var4.parse();
         this.formulaString = var4.getFormula();
      }

      return this.formulaString;
   }

   public byte[] getFormulaData() throws FormulaException {
      if (this.getSheet().getWorkbookBof().isBiff8()) {
         return this.data;
      } else {
         throw new FormulaException(FormulaException.BIFF8_SUPPORTED);
      }
   }

   public NumberFormat getNumberFormat() {
      return null;
   }

   public CellType getType() {
      return CellType.DATE_FORMULA;
   }

   public double getValue() {
      return 0.0;
   }
}
