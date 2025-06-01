package jxl.write.biff;

import jxl.StringFormulaCell;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Assert;
import jxl.common.Logger;

class ReadStringFormulaRecord extends ReadFormulaRecord implements StringFormulaCell {
   private static Logger logger = Logger.getLogger(ReadFormulaRecord.class);

   public ReadStringFormulaRecord(FormulaData var1) {
      super(var1);
   }

   public String getString() {
      return ((StringFormulaCell)this.getReadFormula()).getString();
   }

   protected byte[] handleFormulaException() {
      byte[] var3 = super.getCellData();
      WritableWorkbookImpl var4 = this.getSheet().getWorkbook();
      FormulaParser var2 = new FormulaParser("\"" + this.getContents() + "\"", var4, var4, var4.getSettings());

      try {
         var2.parse();
      } catch (FormulaException var6) {
         logger.warn(var6.getMessage());
         var2 = new FormulaParser("\"ERROR\"", var4, var4, var4.getSettings());

         try {
            var2.parse();
         } catch (FormulaException var5) {
            Assert.verify(false);
         }
      }

      byte[] var8 = var2.getBytes();
      int var1 = var8.length + 16;
      byte[] var7 = new byte[var1];
      IntegerHelper.getTwoBytes(var8.length, var7, 14);
      System.arraycopy(var8, 0, var7, 16, var8.length);
      var7[8] = (byte)(var7[8] | 2);
      var8 = new byte[var3.length + var1];
      System.arraycopy(var3, 0, var8, 0, var3.length);
      System.arraycopy(var7, 0, var8, var3.length, var1);
      var8[6] = 0;
      var8[12] = -1;
      var8[13] = -1;
      return var8;
   }
}
