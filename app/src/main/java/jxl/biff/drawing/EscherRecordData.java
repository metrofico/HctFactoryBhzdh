package jxl.biff.drawing;

import jxl.biff.IntegerHelper;
import jxl.common.Logger;

final class EscherRecordData {
   private static Logger logger = Logger.getLogger(EscherRecordData.class);
   private boolean container;
   private EscherStream escherStream;
   private int instance;
   private int length;
   private int pos;
   private int recordId;
   private int streamLength;
   private EscherRecordType type;
   private int version;

   public EscherRecordData(EscherRecordType var1) {
      this.type = var1;
      this.recordId = var1.getValue();
   }

   public EscherRecordData(EscherStream var1, int var2) {
      this.escherStream = var1;
      this.pos = var2;
      byte[] var3 = var1.getData();
      this.streamLength = var3.length;
      var2 = this.pos;
      var2 = IntegerHelper.getInt(var3[var2], var3[var2 + 1]);
      this.instance = ('\ufff0' & var2) >> 4;
      this.version = var2 & 15;
      var2 = this.pos;
      this.recordId = IntegerHelper.getInt(var3[var2 + 2], var3[var2 + 3]);
      var2 = this.pos;
      this.length = IntegerHelper.getInt(var3[var2 + 4], var3[var2 + 5], var3[var2 + 6], var3[var2 + 7]);
      if (this.version == 15) {
         this.container = true;
      } else {
         this.container = false;
      }

   }

   byte[] getBytes() {
      byte[] var1 = new byte[this.length];
      System.arraycopy(this.escherStream.getData(), this.pos + 8, var1, 0, this.length);
      return var1;
   }

   EscherStream getDrawingGroup() {
      return this.escherStream;
   }

   EscherStream getEscherStream() {
      return this.escherStream;
   }

   int getInstance() {
      return this.instance;
   }

   public int getLength() {
      return this.length;
   }

   int getPos() {
      return this.pos;
   }

   public int getRecordId() {
      return this.recordId;
   }

   int getStreamLength() {
      return this.streamLength;
   }

   EscherRecordType getType() {
      if (this.type == null) {
         this.type = EscherRecordType.getType(this.recordId);
      }

      return this.type;
   }

   public boolean isContainer() {
      return this.container;
   }

   void setContainer(boolean var1) {
      this.container = var1;
   }

   byte[] setHeaderData(byte[] var1) {
      byte[] var2 = new byte[var1.length + 8];
      System.arraycopy(var1, 0, var2, 8, var1.length);
      if (this.container) {
         this.version = 15;
      }

      IntegerHelper.getTwoBytes(this.instance << 4 | this.version, var2, 0);
      IntegerHelper.getTwoBytes(this.recordId, var2, 2);
      IntegerHelper.getFourBytes(var1.length, var2, 4);
      return var2;
   }

   void setInstance(int var1) {
      this.instance = var1;
   }

   void setLength(int var1) {
      this.length = var1;
   }

   void setVersion(int var1) {
      this.version = var1;
   }
}
