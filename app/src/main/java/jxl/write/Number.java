package jxl.write;

import jxl.NumberCell;
import jxl.format.CellFormat;
import jxl.write.biff.NumberRecord;

public class Number extends NumberRecord implements WritableCell, NumberCell {
   public Number(int var1, int var2, double var3) {
      super(var1, var2, var3);
   }

   public Number(int var1, int var2, double var3, CellFormat var5) {
      super(var1, var2, var3, var5);
   }

   protected Number(int var1, int var2, Number var3) {
      super(var1, var2, var3);
   }

   public Number(NumberCell var1) {
      super(var1);
   }

   public WritableCell copyTo(int var1, int var2) {
      return new Number(var1, var2, this);
   }

   public void setValue(double var1) {
      super.setValue(var1);
   }
}
