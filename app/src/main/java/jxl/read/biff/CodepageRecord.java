package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class CodepageRecord extends RecordData {
   private static Logger logger = Logger.getLogger(CodepageRecord.class);
   private int characterSet;

   public CodepageRecord(Record var1) {
      super(var1);
      byte[] var2 = var1.getData();
      this.characterSet = IntegerHelper.getInt(var2[0], var2[1]);
   }

   public int getCharacterSet() {
      return this.characterSet;
   }
}
