package jxl.read.biff;

import java.text.DateFormat;
import java.util.Date;
import jxl.CellType;
import jxl.DateCell;
import jxl.DateFormulaCell;
import jxl.biff.DoubleHelper;
import jxl.biff.FormattingRecords;
import jxl.biff.FormulaData;
import jxl.biff.IntegerHelper;
import jxl.biff.formula.FormulaException;
import jxl.biff.formula.FormulaParser;

public class SharedDateFormulaRecord extends BaseSharedFormulaRecord implements DateCell, FormulaData, DateFormulaCell {
   private DateRecord dateRecord;
   private double value;

   public SharedDateFormulaRecord(SharedNumberFormulaRecord var1, FormattingRecords var2, boolean var3, SheetImpl var4, int var5) {
      super(var1.getRecord(), var2, var1.getExternalSheet(), var1.getNameTable(), var4, var5);
      this.dateRecord = new DateRecord(var1, var1.getXFIndex(), var2, var3, var4);
      this.value = var1.getValue();
   }

   public String getContents() {
      return this.dateRecord.getContents();
   }

   public Date getDate() {
      return this.dateRecord.getDate();
   }

   public DateFormat getDateFormat() {
      return this.dateRecord.getDateFormat();
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

   public CellType getType() {
      return CellType.DATE_FORMULA;
   }

   public double getValue() {
      return this.value;
   }

   public boolean isTime() {
      return this.dateRecord.isTime();
   }
}
