package jxl.read.biff;

import jxl.CellType;
import jxl.biff.FormattingRecords;

public class BlankCell extends CellValue {
   BlankCell(Record var1, FormattingRecords var2, SheetImpl var3) {
      super(var1, var2, var3);
   }

   public String getContents() {
      return "";
   }

   public CellType getType() {
      return CellType.EMPTY;
   }
}
