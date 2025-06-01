package jxl.read.biff;

import jxl.biff.RecordData;

class PrintHeadersRecord extends RecordData {
   private boolean printHeaders;

   public PrintHeadersRecord(Record var1) {
      super(var1);
      byte[] var3 = var1.getData();
      boolean var2 = false;
      if (var3[0] == 1) {
         var2 = true;
      }

      this.printHeaders = var2;
   }

   public boolean getPrintHeaders() {
      return this.printHeaders;
   }
}
