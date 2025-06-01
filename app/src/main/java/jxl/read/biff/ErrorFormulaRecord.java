package jxl.read.biff;

import jxl.CellType;
import jxl.ErrorCell;
import jxl.ErrorFormulaCell;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaErrorCode;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Assert;

class ErrorFormulaRecord extends CellValue implements ErrorCell, FormulaData, ErrorFormulaCell {
   private byte[] data;
   private FormulaErrorCode error;
   private int errorCode;
   private ExternalSheet externalSheet;
   private String formulaString;
   private WorkbookMethods nameTable;

   public ErrorFormulaRecord(Record var1, FormattingRecords var2, ExternalSheet var3, WorkbookMethods var4, SheetImpl var5) {
      super(var1, var2, var5);
      this.externalSheet = var3;
      this.nameTable = var4;
      byte[] var7 = this.getRecord().getData();
      this.data = var7;
      boolean var6;
      if (var7[6] == 2) {
         var6 = true;
      } else {
         var6 = false;
      }

      Assert.verify(var6);
      this.errorCode = this.data[8];
   }

   public String getContents() {
      if (this.error == null) {
         this.error = FormulaErrorCode.getErrorCode(this.errorCode);
      }

      String var1;
      if (this.error != FormulaErrorCode.UNKNOWN) {
         var1 = this.error.getDescription();
      } else {
         var1 = "ERROR " + this.errorCode;
      }

      return var1;
   }

   public int getErrorCode() {
      return this.errorCode;
   }

   public String getFormula() throws FormulaException {
      if (this.formulaString == null) {
         byte[] var2 = this.data;
         int var1 = var2.length - 22;
         byte[] var3 = new byte[var1];
         System.arraycopy(var2, 22, var3, 0, var1);
         FormulaParser var4 = new FormulaParser(var3, this, this.externalSheet, this.nameTable, this.getSheet().getWorkbook().getSettings());
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
      return CellType.FORMULA_ERROR;
   }
}
