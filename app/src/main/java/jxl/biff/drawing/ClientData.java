package jxl.biff.drawing;

import jxl.common.Logger;

class ClientData extends EscherAtom {
   private static Logger logger = Logger.getLogger(ClientData.class);
   private byte[] data;

   public ClientData() {
      super(EscherRecordType.CLIENT_DATA);
   }

   public ClientData(EscherRecordData var1) {
      super(var1);
   }

   byte[] getData() {
      byte[] var1 = new byte[0];
      this.data = var1;
      return this.setHeaderData(var1);
   }
}
