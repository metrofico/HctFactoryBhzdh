package jxl.write.biff;

import java.text.NumberFormat;
import jxl.NumberFormulaCell;
import jxl.biff.DoubleHelper;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Logger;

class ReadNumberFormulaRecord extends ReadFormulaRecord implements NumberFormulaCell {
   private static Logger logger = Logger.getLogger(ReadNumberFormulaRecord.class);

   public ReadNumberFormulaRecord(FormulaData var1) {
      super(var1);
   }

   public NumberFormat getNumberFormat() {
      return ((NumberFormulaCell)this.getReadFormula()).getNumberFormat();
   }

   public double getValue() {
      return ((NumberFormulaCell)this.getReadFormula()).getValue();
   }

   protected byte[] handleFormulaException() {
      byte[] var2 = super.getCellData();
      WritableWorkbookImpl var3 = this.getSheet().getWorkbook();
      FormulaParser var6 = new FormulaParser(Double.toString(this.getValue()), var3, var3, var3.getSettings());

      try {
         var6.parse();
      } catch (FormulaException var5) {
         logger.warn(var5.getMessage());
      }

      byte[] var4 = var6.getBytes();
      int var1 = var4.length + 16;
      byte[] var7 = new byte[var1];
      IntegerHelper.getTwoBytes(var4.length, var7, 14);
      System.arraycopy(var4, 0, var7, 16, var4.length);
      var7[8] = (byte)(var7[8] | 2);
      var4 = new byte[var2.length + var1];
      System.arraycopy(var2, 0, var4, 0, var2.length);
      System.arraycopy(var7, 0, var4, var2.length, var1);
      DoubleHelper.getIEEEBytes(this.getValue(), var4, 6);
      return var4;
   }
}
