package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class ButtonPropertySetRecord extends WritableRecordData {
   private byte[] data;

   public ButtonPropertySetRecord(jxl.read.biff.ButtonPropertySetRecord var1) {
      super(Type.BUTTONPROPERTYSET);
      this.data = var1.getData();
   }

   public ButtonPropertySetRecord(ButtonPropertySetRecord var1) {
      super(Type.BUTTONPROPERTYSET);
      this.data = var1.getData();
   }

   public byte[] getData() {
      return this.data;
   }
}
