package jxl.write;

import jxl.Cell;
import jxl.format.CellFormat;
import jxl.write.biff.BlankRecord;

public class Blank extends BlankRecord implements WritableCell {
   public Blank(int var1, int var2) {
      super(var1, var2);
   }

   public Blank(int var1, int var2, CellFormat var3) {
      super(var1, var2, var3);
   }

   protected Blank(int var1, int var2, Blank var3) {
      super(var1, var2, (BlankRecord)var3);
   }

   public Blank(Cell var1) {
      super(var1);
   }

   public WritableCell copyTo(int var1, int var2) {
      return new Blank(var1, var2, this);
   }
}
