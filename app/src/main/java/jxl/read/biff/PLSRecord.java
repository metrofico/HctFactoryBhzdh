package jxl.read.biff;

import jxl.biff.RecordData;

public class PLSRecord extends RecordData {
   public PLSRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.getRecord().getData();
   }
}
