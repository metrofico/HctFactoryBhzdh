package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class Window1Record extends WritableRecordData {
   private byte[] data;
   private int selectedSheet;

   public Window1Record(int var1) {
      super(Type.WINDOW1);
      this.selectedSheet = var1;
      byte[] var2 = new byte[]{104, 1, 14, 1, 92, 58, -66, 35, 56, 0, 0, 0, 0, 0, 1, 0, 88, 2};
      this.data = var2;
      IntegerHelper.getTwoBytes(var1, var2, 10);
   }

   public byte[] getData() {
      return this.data;
   }
}
