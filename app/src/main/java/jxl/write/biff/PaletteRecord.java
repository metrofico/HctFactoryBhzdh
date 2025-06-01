package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class PaletteRecord extends WritableRecordData {
   private byte[] data;

   public PaletteRecord(jxl.read.biff.PaletteRecord var1) {
      super(Type.PALETTE);
      this.data = var1.getData();
   }

   public byte[] getData() {
      return this.data;
   }
}
