package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class SelectionRecord extends WritableRecordData {
   public static final PaneType lowerLeft = new PaneType(2);
   public static final PaneType lowerRight = new PaneType(0);
   public static final PaneType upperLeft = new PaneType(3);
   public static final PaneType upperRight = new PaneType(1);
   private int column;
   private PaneType pane;
   private int row;

   public SelectionRecord(PaneType var1, int var2, int var3) {
      super(Type.SELECTION);
      this.column = var2;
      this.row = var3;
      this.pane = var1;
   }

   public byte[] getData() {
      byte[] var2 = new byte[15];
      var2[0] = (byte)this.pane.val;
      IntegerHelper.getTwoBytes(this.row, var2, 1);
      IntegerHelper.getTwoBytes(this.column, var2, 3);
      var2[7] = 1;
      IntegerHelper.getTwoBytes(this.row, var2, 9);
      IntegerHelper.getTwoBytes(this.row, var2, 11);
      int var1 = this.column;
      var2[13] = (byte)var1;
      var2[14] = (byte)var1;
      return var2;
   }

   private static class PaneType {
      int val;

      PaneType(int var1) {
         this.val = var1;
      }
   }
}
