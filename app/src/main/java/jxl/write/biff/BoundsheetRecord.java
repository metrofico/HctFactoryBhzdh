package jxl.write.biff;

import jxl.biff.StringHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class BoundsheetRecord extends WritableRecordData {
   private boolean chartOnly;
   private byte[] data;
   private boolean hidden;
   private String name;

   public BoundsheetRecord(String var1) {
      super(Type.BOUNDSHEET);
      this.name = var1;
      this.hidden = false;
      this.chartOnly = false;
   }

   public byte[] getData() {
      byte[] var1 = new byte[this.name.length() * 2 + 8];
      this.data = var1;
      if (this.chartOnly) {
         var1[5] = 2;
      } else {
         var1[5] = 0;
      }

      if (this.hidden) {
         var1[4] = 1;
         var1[5] = 0;
      }

      var1[6] = (byte)this.name.length();
      var1 = this.data;
      var1[7] = 1;
      StringHelper.getUnicodeBytes(this.name, var1, 8);
      return this.data;
   }

   void setChartOnly() {
      this.chartOnly = true;
   }

   void setHidden() {
      this.hidden = true;
   }
}
