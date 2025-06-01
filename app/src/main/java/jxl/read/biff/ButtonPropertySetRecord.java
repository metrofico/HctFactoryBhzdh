package jxl.read.biff;

import jxl.biff.RecordData;
import jxl.common.Logger;

public class ButtonPropertySetRecord extends RecordData {
   private static Logger logger = Logger.getLogger(ButtonPropertySetRecord.class);

   ButtonPropertySetRecord(Record var1) {
      super(var1);
   }

   public byte[] getData() {
      return this.getRecord().getData();
   }
}
