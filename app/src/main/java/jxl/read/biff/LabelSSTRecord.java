package jxl.read.biff;

import jxl.CellType;
import jxl.LabelCell;
import jxl.biff.FormattingRecords;
import jxl.biff.IntegerHelper;

class LabelSSTRecord extends CellValue implements LabelCell {
   private int index;
   private String string;

   public LabelSSTRecord(Record var1, SSTRecord var2, FormattingRecords var3, SheetImpl var4) {
      super(var1, var3, var4);
      byte[] var6 = this.getRecord().getData();
      int var5 = IntegerHelper.getInt(var6[6], var6[7], var6[8], var6[9]);
      this.index = var5;
      this.string = var2.getString(var5);
   }

   public String getContents() {
      return this.string;
   }

   public String getString() {
      return this.string;
   }

   public CellType getType() {
      return CellType.LABEL;
   }
}
