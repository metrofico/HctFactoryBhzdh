package jxl.read.biff;

import jxl.BooleanCell;
import jxl.CellType;
import jxl.biff.FormattingRecords;
import jxl.common.Assert;

class BooleanRecord extends CellValue implements BooleanCell {
   private boolean error;
   private boolean value;

   public BooleanRecord(Record var1, FormattingRecords var2, SheetImpl var3) {
      super(var1, var2, var3);
      boolean var5 = false;
      this.error = false;
      this.value = false;
      byte[] var6 = this.getRecord().getData();
      boolean var4;
      if (var6[7] == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.error = var4;
      if (!var4) {
         var4 = var5;
         if (var6[6] == 1) {
            var4 = true;
         }

         this.value = var4;
      }

   }

   public String getContents() {
      Assert.verify(this.isError() ^ true);
      return (new Boolean(this.value)).toString();
   }

   public Record getRecord() {
      return super.getRecord();
   }

   public CellType getType() {
      return CellType.BOOLEAN;
   }

   public boolean getValue() {
      return this.value;
   }

   public boolean isError() {
      return this.error;
   }
}
