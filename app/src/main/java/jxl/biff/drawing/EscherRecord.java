package jxl.biff.drawing;

import jxl.common.Logger;

abstract class EscherRecord {
   protected static final int HEADER_LENGTH = 8;
   private static Logger logger = Logger.getLogger(EscherRecord.class);
   private EscherRecordData data;

   protected EscherRecord(EscherRecordData var1) {
      this.data = var1;
   }

   protected EscherRecord(EscherRecordType var1) {
      this.data = new EscherRecordData(var1);
   }

   byte[] getBytes() {
      return this.data.getBytes();
   }

   abstract byte[] getData();

   protected EscherRecordData getEscherData() {
      return this.data;
   }

   protected final EscherStream getEscherStream() {
      return this.data.getEscherStream();
   }

   protected final int getInstance() {
      return this.data.getInstance();
   }

   public int getLength() {
      return this.data.getLength() + 8;
   }

   protected final int getPos() {
      return this.data.getPos();
   }

   protected int getStreamLength() {
      return this.data.getStreamLength();
   }

   public EscherRecordType getType() {
      return this.data.getType();
   }

   protected void setContainer(boolean var1) {
      this.data.setContainer(var1);
   }

   final byte[] setHeaderData(byte[] var1) {
      return this.data.setHeaderData(var1);
   }

   protected final void setInstance(int var1) {
      this.data.setInstance(var1);
   }

   protected final void setVersion(int var1) {
      this.data.setVersion(var1);
   }
}
