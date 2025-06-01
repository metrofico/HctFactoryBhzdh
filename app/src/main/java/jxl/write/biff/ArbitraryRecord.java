package jxl.write.biff;

import jxl.biff.Type;
import jxl.biff.WritableRecordData;
import jxl.common.Logger;

class ArbitraryRecord extends WritableRecordData {
   private static Logger logger = Logger.getLogger(ArbitraryRecord.class);
   private byte[] data;

   public ArbitraryRecord(int var1, byte[] var2) {
      super(Type.createType(var1));
      this.data = var2;
      logger.warn("ArbitraryRecord of type " + var1 + " created");
   }

   public byte[] getData() {
      return this.data;
   }
}
