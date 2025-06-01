package jxl.read.biff;

import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;

public abstract class BaseSharedFormulaRecord extends CellValue implements FormulaData {
   private ExternalSheet externalSheet;
   private int filePos;
   private String formulaString;
   private WorkbookMethods nameTable;
   private byte[] tokens;

   public BaseSharedFormulaRecord(Record var1, FormattingRecords var2, ExternalSheet var3, WorkbookMethods var4, SheetImpl var5, int var6) {
      super(var1, var2, var5);
      this.externalSheet = var3;
      this.nameTable = var4;
      this.filePos = var6;
   }

   protected final ExternalSheet getExternalSheet() {
      return this.externalSheet;
   }

   final int getFilePos() {
      return this.filePos;
   }

   public String getFormula() throws FormulaException {
      if (this.formulaString == null) {
         FormulaParser var1 = new FormulaParser(this.tokens, this, this.externalSheet, this.nameTable, this.getSheet().getWorkbook().getSettings());
         var1.parse();
         this.formulaString = var1.getFormula();
      }

      return this.formulaString;
   }

   protected final WorkbookMethods getNameTable() {
      return this.nameTable;
   }

   public Record getRecord() {
      return super.getRecord();
   }

   protected final byte[] getTokens() {
      return this.tokens;
   }

   void setTokens(byte[] var1) {
      this.tokens = var1;
   }
}
