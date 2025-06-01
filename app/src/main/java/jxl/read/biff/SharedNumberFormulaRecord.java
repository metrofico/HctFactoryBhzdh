package jxl.read.biff;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import jxl.CellType;
import jxl.NumberCell;
import jxl.NumberFormulaCell;
import jxl.biff.DoubleHelper;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Logger;

public class SharedNumberFormulaRecord extends BaseSharedFormulaRecord implements NumberCell, FormulaData, NumberFormulaCell {
   private static DecimalFormat defaultFormat = new DecimalFormat("#.###");
   private static Logger logger = Logger.getLogger(SharedNumberFormulaRecord.class);
   private NumberFormat format;
   private FormattingRecords formattingRecords;
   private double value;

   public SharedNumberFormulaRecord(Record var1, File var2, double var3, FormattingRecords var5, ExternalSheet var6, WorkbookMethods var7, SheetImpl var8) {
      super(var1, var5, var6, var7, var8, var2.getPos());
      this.value = var3;
      this.format = defaultFormat;
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

   public byte[] getFormulaData() throws FormulaException {
      if (this.getSheet().getWorkbookBof().isBiff8()) {
         FormulaParser var2 = new FormulaParser(this.getTokens(), this, this.getExternalSheet(), this.getNameTable(), this.getSheet().getWorkbook().getSettings());
         var2.parse();
         byte[] var3 = var2.getBytes();
         int var1 = var3.length + 22;
         byte[] var4 = new byte[var1];
         IntegerHelper.getTwoBytes(this.getRow(), var4, 0);
         IntegerHelper.getTwoBytes(this.getColumn(), var4, 2);
         IntegerHelper.getTwoBytes(this.getXFIndex(), var4, 4);
         DoubleHelper.getIEEEBytes(this.value, var4, 6);
         System.arraycopy(var3, 0, var4, 22, var3.length);
         IntegerHelper.getTwoBytes(var3.length, var4, 20);
         var1 -= 6;
         var3 = new byte[var1];
         System.arraycopy(var4, 6, var3, 0, var1);
         return var3;
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

   final void setNumberFormat(NumberFormat var1) {
      if (var1 != null) {
         this.format = var1;
      }

   }
}
