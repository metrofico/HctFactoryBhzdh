package jxl.biff;

import jxl.read.biff.Record;

public class XCTRecord extends WritableRecordData {
   public XCTRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.getRecord().getData();
   }
}
