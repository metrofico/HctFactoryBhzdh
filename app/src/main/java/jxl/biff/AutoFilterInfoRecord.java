package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public class AutoFilterInfoRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(AutoFilterInfoRecord.class);
   private byte[] data = this.getRecord().getData();

   public AutoFilterInfoRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.data;
   }
}
