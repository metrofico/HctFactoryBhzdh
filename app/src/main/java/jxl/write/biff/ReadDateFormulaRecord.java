package jxl.write.biff;

import java.text.DateFormat;
import java.util.Date;
import jxl.DateFormulaCell;
import jxl.biff.FormulaData;

class ReadDateFormulaRecord extends ReadFormulaRecord implements DateFormulaCell {
   public ReadDateFormulaRecord(FormulaData var1) {
      super(var1);
   }

   public Date getDate() {
      return ((DateFormulaCell)this.getReadFormula()).getDate();
   }

   public DateFormat getDateFormat() {
      return ((DateFormulaCell)this.getReadFormula()).getDateFormat();
   }

   public boolean isTime() {
      return ((DateFormulaCell)this.getReadFormula()).isTime();
   }
}
