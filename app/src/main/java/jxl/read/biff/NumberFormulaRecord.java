package jxl.read.biff;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jxl.CellType;
import jxl.NumberCell;
import jxl.NumberFormulaCell;
import jxl.biff.DoubleHelper;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Logger;

class NumberFormulaRecord extends CellValue implements NumberCell, FormulaData, NumberFormulaCell {
   private static final DecimalFormat defaultFormat = new DecimalFormat("#.###");
   private static Logger logger = Logger.getLogger(NumberFormulaRecord.class);
   private byte[] data;
   private ExternalSheet externalSheet;
   private NumberFormat format;
   private String formulaString;
   private WorkbookMethods nameTable;
   private double value;

   public NumberFormulaRecord(Record var1, FormattingRecords var2, ExternalSheet var3, WorkbookMethods var4, SheetImpl var5) {
      super(var1, var2, var5);
      this.externalSheet = var3;
      this.nameTable = var4;
      this.data = this.getRecord().getData();
      NumberFormat var6 = var2.getNumberFormat(this.getXFIndex());
      this.format = var6;
      if (var6 == null) {
         this.format = defaultFormat;
      }

      this.value = DoubleHelper.getIEEEDouble(this.data, 6);
   }

   public String getContents() {
      String var1;
      if (!Double.isNaN(this.value)) {
         var1 = this.format.format(this.value);
      } else {
         var1 = "";
      }

      return var1;
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

   public NumberFormat getNumberFormat() {
      return this.format;
   }

   public CellType getType() {
      return CellType.NUMBER_FORMULA;
   }

   public double getValue() {
      return this.value;
   }
}
