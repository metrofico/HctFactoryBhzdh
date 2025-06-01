package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class DefaultRowHeightRecord extends WritableRecordData {
   private boolean changed;
   private byte[] data = new byte[4];
   private int rowHeight;

   public DefaultRowHeightRecord(int var1, boolean var2) {
      super(Type.DEFAULTROWHEIGHT);
      this.rowHeight = var1;
      this.changed = var2;
   }

   public byte[] getData() {
      if (this.changed) {
         byte[] var1 = this.data;
         var1[0] = (byte)(var1[0] | 1);
      }

      IntegerHelper.getTwoBytes(this.rowHeight, this.data, 2);
      return this.data;
   }
}
