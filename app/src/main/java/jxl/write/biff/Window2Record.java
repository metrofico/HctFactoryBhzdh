package jxl.write.biff;

import jxl.SheetSettings;
import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class Window2Record extends WritableRecordData {
   private byte[] data;

   public Window2Record(SheetSettings var1) {
      super(Type.WINDOW2);
      int var2;
      if (var1.getShowGridLines()) {
         var2 = 2;
      } else {
         var2 = 0;
      }

      int var3 = var2 | 4 | 0;
      var2 = var3;
      if (var1.getDisplayZeroValues()) {
         var2 = var3 | 16;
      }

      label27: {
         var2 = var2 | 32 | 128;
         if (var1.getHorizontalFreeze() == 0) {
            var3 = var2;
            if (var1.getVerticalFreeze() == 0) {
               break label27;
            }
         }

         var3 = var2 | 8 | 256;
      }

      var2 = var3;
      if (var1.isSelected()) {
         var2 = var3 | 1536;
      }

      var3 = var2;
      if (var1.getPageBreakPreviewMode()) {
         var3 = var2 | 2048;
      }

      byte[] var4 = new byte[18];
      this.data = var4;
      IntegerHelper.getTwoBytes(var3, var4, 0);
      IntegerHelper.getTwoBytes(64, this.data, 6);
      IntegerHelper.getTwoBytes(var1.getPageBreakPreviewMagnification(), this.data, 10);
      IntegerHelper.getTwoBytes(var1.getNormalMagnification(), this.data, 12);
   }

   public byte[] getData() {
      return this.data;
   }
}
