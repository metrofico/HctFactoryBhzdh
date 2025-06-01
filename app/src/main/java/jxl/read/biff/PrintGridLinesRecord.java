package jxl.read.biff;

import jxl.biff.RecordData;

class PrintGridLinesRecord extends RecordData {
   private boolean printGridLines;

   public PrintGridLinesRecord(Record var1) {
      super(var1);
      byte[] var3 = var1.getData();
      boolean var2 = false;
      if (var3[0] == 1) {
         var2 = true;
      }

      this.printGridLines = var2;
   }

   public boolean getPrintGridLines() {
      return this.printGridLines;
   }
}
