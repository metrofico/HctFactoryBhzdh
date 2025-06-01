package jxl.write;

import jxl.format.CellFormat;
import jxl.write.biff.FormulaRecord;

public class Formula extends FormulaRecord implements WritableCell {
   public Formula(int var1, int var2, String var3) {
      super(var1, var2, var3);
   }

   public Formula(int var1, int var2, String var3, CellFormat var4) {
      super(var1, var2, var3, var4);
   }

   protected Formula(int var1, int var2, Formula var3) {
      super(var1, var2, (FormulaRecord)var3);
   }

   public WritableCell copyTo(int var1, int var2) {
      return new Formula(var1, var2, this);
   }
}
