package jxl.read.biff;

import jxl.biff.RecordData;
import jxl.common.Logger;

class Excel9FileRecord extends RecordData {
   private static Logger logger = Logger.getLogger(Excel9FileRecord.class);
   private boolean excel9file = true;

   public Excel9FileRecord(Record var1) {
      super(var1);
   }

   public boolean getExcel9File() {
      return this.excel9file;
   }
}
