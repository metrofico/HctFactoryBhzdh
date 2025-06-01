package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public class FilterModeRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(FilterModeRecord.class);
   private byte[] data = this.getRecord().getData();

   public FilterModeRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.data;
   }
}
