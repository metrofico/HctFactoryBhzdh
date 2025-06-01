package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class PrintGridLinesRecord extends WritableRecordData {
   private byte[] data;
   private boolean printGridLines;

   public PrintGridLinesRecord(boolean var1) {
      super(Type.PRINTGRIDLINES);
      this.printGridLines = var1;
      byte[] var2 = new byte[2];
      this.data = var2;
      if (var1) {
         var2[0] = 1;
      }

   }

   public byte[] getData() {
      return this.data;
   }
}
