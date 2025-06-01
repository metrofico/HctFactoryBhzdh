package jxl.write;

import jxl.BooleanCell;
import jxl.format.CellFormat;
import jxl.write.biff.BooleanRecord;

public class Boolean extends BooleanRecord implements WritableCell, BooleanCell {
   protected Boolean(int var1, int var2, Boolean var3) {
      super(var1, var2, var3);
   }

   public Boolean(int var1, int var2, boolean var3) {
      super(var1, var2, var3);
   }

   public Boolean(int var1, int var2, boolean var3, CellFormat var4) {
      super(var1, var2, var3, var4);
   }

   public Boolean(BooleanCell var1) {
      super(var1);
   }

   public WritableCell copyTo(int var1, int var2) {
      return new Boolean(var1, var2, this);
   }

   public void setValue(boolean var1) {
      super.setValue(var1);
   }
}
