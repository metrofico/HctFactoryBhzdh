package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class VerticalCentreRecord extends WritableRecordData {
   private boolean centre;
   private byte[] data;

   public VerticalCentreRecord(boolean var1) {
      super(Type.VCENTER);
      this.centre = var1;
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
