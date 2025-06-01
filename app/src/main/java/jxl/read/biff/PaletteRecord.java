package jxl.read.biff;

import jxl.biff.RecordData;

public class PaletteRecord extends RecordData {
   PaletteRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.getRecord().getData();
   }
}
