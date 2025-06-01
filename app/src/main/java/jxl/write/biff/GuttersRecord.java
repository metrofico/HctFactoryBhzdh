package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class GuttersRecord extends WritableRecordData {
   private int colGutter;
   private byte[] data;
   private int maxColumnOutline;
   private int maxRowOutline;
   private int rowGutter;

   public GuttersRecord() {
      super(Type.GUTS);
   }

   public byte[] getData() {
      byte[] var1 = new byte[8];
      this.data = var1;
      IntegerHelper.getTwoBytes(this.rowGutter, var1, 0);
      IntegerHelper.getTwoBytes(this.colGutter, this.data, 2);
      IntegerHelper.getTwoBytes(this.maxRowOutline, this.data, 4);
      IntegerHelper.getTwoBytes(this.maxColumnOutline, this.data, 6);
      return this.data;
   }

   public int getMaxColumnOutline() {
      return this.maxColumnOutline;
   }

   public int getMaxRowOutline() {
      return this.maxRowOutline;
   }

   public void setMaxColumnOutline(int var1) {
      this.maxColumnOutline = var1;
      this.colGutter = var1 * 14 + 1;
   }

   public void setMaxRowOutline(int var1) {
      this.maxRowOutline = var1;
      this.rowGutter = var1 * 14 + 1;
   }
}
