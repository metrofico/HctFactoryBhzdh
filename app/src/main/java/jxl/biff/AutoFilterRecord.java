package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public class AutoFilterRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(AutoFilterRecord.class);
   private byte[] data = this.getRecord().getData();

   public AutoFilterRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.data;
   }
}
