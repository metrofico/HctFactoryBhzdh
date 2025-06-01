package jxl.read.biff;

import jxl.CellType;
import jxl.ErrorCell;
import jxl.biff.FormattingRecords;

class ErrorRecord extends CellValue implements ErrorCell {
   private int errorCode = this.getRecord().getData()[6];

   public ErrorRecord(Record var1, FormattingRecords var2, SheetImpl var3) {
      super(var1, var2, var3);
   }

   public String getContents() {
      return "ERROR " + this.errorCode;
   }

   public int getErrorCode() {
      return this.errorCode;
   }

   public CellType getType() {
      return CellType.ERROR;
   }
}
