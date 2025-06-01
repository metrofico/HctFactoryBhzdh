package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class IndexRecord extends WritableRecordData {
   private int blocks;
   private int bofPosition;
   private byte[] data;
   private int dataPos;
   private int rows;

   public IndexRecord(int var1, int var2, int var3) {
      super(Type.INDEX);
      this.bofPosition = var1;
      this.rows = var2;
      this.blocks = var3;
      this.data = new byte[var3 * 4 + 16];
      this.dataPos = 16;
   }

   void addBlockPosition(int var1) {
      IntegerHelper.getFourBytes(var1 - this.bofPosition, this.data, this.dataPos);
      this.dataPos += 4;
   }

   protected byte[] getData() {
      IntegerHelper.getFourBytes(this.rows, this.data, 8);
      return this.data;
   }

   void setDataStartPosition(int var1) {
      IntegerHelper.getFourBytes(var1 - this.bofPosition, this.data, 12);
   }
}
