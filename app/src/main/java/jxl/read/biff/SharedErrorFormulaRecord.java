package jxl.read.biff;

import jxl.CellType;
import jxl.ErrorCell;
import jxl.ErrorFormulaCell;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaErrorCode;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Logger;

public class SharedErrorFormulaRecord extends BaseSharedFormulaRecord implements ErrorCell, FormulaData, ErrorFormulaCell {
   private static Logger logger = Logger.getLogger(SharedErrorFormulaRecord.class);
   private byte[] data;
   private FormulaErrorCode error;
   private int errorCode;

   public SharedErrorFormulaRecord(Record var1, File var2, int var3, FormattingRecords var4, ExternalSheet var5, WorkbookMethods var6, SheetImpl var7) {
      super(var1, var4, var5, var6, var7, var2.getPos());
      this.errorCode = var3;
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
         var4[6] = 2;
         var4[8] = (byte)this.errorCode;
         var4[12] = -1;
         var4[13] = -1;
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

   public CellType getType() {
      return CellType.FORMULA_ERROR;
   }
}
