package jxl.write.biff;

import jxl.BooleanFormulaCell;
import jxl.biff.FormulaData;

class ReadBooleanFormulaRecord extends ReadFormulaRecord implements BooleanFormulaCell {
   public ReadBooleanFormulaRecord(FormulaData var1) {
      super(var1);
   }

   public boolean getValue() {
      return ((BooleanFormulaCell)this.getReadFormula()).getValue();
   }
}
