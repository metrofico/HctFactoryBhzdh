package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public class ConditionalFormatRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(ConditionalFormatRecord.class);
   private byte[] data = this.getRecord().getData();

   public ConditionalFormatRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.data;
   }
}
