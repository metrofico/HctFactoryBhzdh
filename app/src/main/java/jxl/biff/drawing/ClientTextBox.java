package jxl.biff.drawing;

import jxl.common.Logger;

class ClientTextBox extends EscherAtom {
   private static Logger logger = Logger.getLogger(ClientTextBox.class);
   private byte[] data;

   public ClientTextBox() {
      super(EscherRecordType.CLIENT_TEXT_BOX);
   }

   public ClientTextBox(EscherRecordData var1) {
      super(var1);
   }

   byte[] getData() {
      byte[] var1 = new byte[0];
      this.data = var1;
      return this.setHeaderData(var1);
   }
}
