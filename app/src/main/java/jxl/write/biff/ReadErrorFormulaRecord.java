package jxl.write.biff;

import jxl.ErrorFormulaCell;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.formula.FormulaErrorCode;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Logger;

class ReadErrorFormulaRecord extends ReadFormulaRecord implements ErrorFormulaCell {
   private static Logger logger = Logger.getLogger(ReadErrorFormulaRecord.class);

   public ReadErrorFormulaRecord(FormulaData var1) {
      super(var1);
   }

   public int getErrorCode() {
      return ((ErrorFormulaCell)this.getReadFormula()).getErrorCode();
   }

   protected byte[] handleFormulaException() {
      byte[] var4 = super.getCellData();
      int var1 = this.getErrorCode();
      String var3;
      if (var1 == FormulaErrorCode.DIV0.getCode()) {
         var3 = "1/0";
      } else if (var1 == FormulaErrorCode.VALUE.getCode()) {
         var3 = "\"\"/0";
      } else if (var1 == FormulaErrorCode.REF.getCode()) {
         var3 = "\"#REF!\"";
      } else {
         var3 = "\"ERROR\"";
      }

      WritableWorkbookImpl var5 = this.getSheet().getWorkbook();
      FormulaParser var7 = new FormulaParser(var3, var5, var5, var5.getSettings());

      try {
         var7.parse();
      } catch (FormulaException var6) {
         logger.warn(var6.getMessage());
      }

      byte[] var9 = var7.getBytes();
      int var2 = var9.length + 16;
      byte[] var8 = new byte[var2];
      IntegerHelper.getTwoBytes(var9.length, var8, 14);
      System.arraycopy(var9, 0, var8, 16, var9.length);
      var8[8] = (byte)(var8[8] | 2);
      var9 = new byte[var4.length + var2];
      System.arraycopy(var4, 0, var9, 0, var4.length);
      System.arraycopy(var8, 0, var9, var4.length, var2);
      var9[6] = 2;
      var9[12] = -1;
      var9[13] = -1;
      var9[8] = (byte)var1;
      return var9;
   }
}
