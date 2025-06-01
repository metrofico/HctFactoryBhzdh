package jxl.write.biff;

import jxl.Cell;
import jxl.CellType;
import jxl.biff.Type;
import jxl.common.Logger;
import jxl.format.CellFormat;

public abstract class BlankRecord extends CellValue {
   private static Logger logger = Logger.getLogger(BlankRecord.class);

   protected BlankRecord(int var1, int var2) {
      super(Type.BLANK, var1, var2);
   }

   protected BlankRecord(int var1, int var2, CellFormat var3) {
      super(Type.BLANK, var1, var2, var3);
   }

   protected BlankRecord(int var1, int var2, BlankRecord var3) {
      super(Type.BLANK, var1, var2, (CellValue)var3);
   }

   protected BlankRecord(Cell var1) {
      super(Type.BLANK, var1);
   }

   public String getContents() {
      return "";
   }

   public CellType getType() {
      return CellType.EMPTY;
   }
}
