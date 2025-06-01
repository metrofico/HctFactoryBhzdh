package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class PaneRecord extends WritableRecordData {
   private static final int bottomLeftPane = 2;
   private static final int bottomRightPane = 0;
   private static final int topLeftPane = 3;
   private static final int topRightPane = 1;
   private int columnsVisible;
   private int rowsVisible;

   public PaneRecord(int var1, int var2) {
      super(Type.PANE);
      this.rowsVisible = var2;
      this.columnsVisible = var1;
   }

   public byte[] getData() {
      byte[] var3 = new byte[10];
      int var2 = this.columnsVisible;
      byte var1 = 0;
      IntegerHelper.getTwoBytes(var2, var3, 0);
      IntegerHelper.getTwoBytes(this.rowsVisible, var3, 2);
      var2 = this.rowsVisible;
      if (var2 > 0) {
         IntegerHelper.getTwoBytes(var2, var3, 4);
      }

      var2 = this.columnsVisible;
      if (var2 > 0) {
         IntegerHelper.getTwoBytes(var2, var3, 6);
      }

      var2 = this.rowsVisible;
      if (var2 > 0 && this.columnsVisible == 0) {
         var1 = 2;
      } else if (var2 == 0 && this.columnsVisible > 0) {
         var1 = 1;
      } else if (var2 <= 0 || this.columnsVisible <= 0) {
         var1 = 3;
      }

      IntegerHelper.getTwoBytes(var1, var3, 8);
      return var3;
   }
}
