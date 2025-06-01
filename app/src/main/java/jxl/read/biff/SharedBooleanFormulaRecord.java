package jxl.read.biff;

import jxl.BooleanCell;
import jxl.BooleanFormulaCell;
import jxl.CellType;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.WorkbookMethods;
import jxl.biff.formula.ExternalSheet;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;
import jxl.common.Logger;

public class SharedBooleanFormulaRecord extends BaseSharedFormulaRecord implements BooleanCell, FormulaData, BooleanFormulaCell {
   private static Logger logger = Logger.getLogger(SharedBooleanFormulaRecord.class);
   private boolean value;

   public SharedBooleanFormulaRecord(Record var1, File var2, boolean var3, FormattingRecords var4, ExternalSheet var5, WorkbookMethods var6, SheetImpl var7) {
      super(var1, var4, var5, var6, var7, var2.getPos());
      this.value = var3;
   }

   public String getContents() {
      return (new Boolean(this.value)).toString();
   }

   public byte[] getFormulaData() throws FormulaException {
      if (this.getSheet().getWorkbookBof().isBiff8()) {
         FormulaParser var3 = new FormulaParser(this.getTokens(), this, this.getExternalSheet(), this.getNameTable(), this.getSheet().getWorkbook().getSettings());
         var3.parse();
         byte[] var4 = var3.getBytes();
         int var2 = var4.length + 22;
         byte[] var5 = new byte[var2];
         IntegerHelper.getTwoBytes(this.getRow(), var5, 0);
         IntegerHelper.getTwoBytes(this.getColumn(), var5, 2);
         IntegerHelper.getTwoBytes(this.getXFIndex(), var5, 4);
         int var1 = 1;
         var5[6] = 1;
         if (!this.value) {
            var1 = 0;
         }

         var5[8] = (byte)var1;
         var5[12] = -1;
         var5[13] = -1;
         System.arraycopy(var4, 0, var5, 22, var4.length);
         IntegerHelper.getTwoBytes(var4.length, var5, 20);
         var1 = var2 - 6;
         var4 = new byte[var1];
         System.arraycopy(var5, 6, var4, 0, var1);
         return var4;
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
