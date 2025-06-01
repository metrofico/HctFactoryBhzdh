package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;

public class GuttersRecord extends RecordData {
   private int columnOutlineLevel;
   private int height;
   private int rowOutlineLevel;
   private int width;

   public GuttersRecord(Record var1) {
      super(var1);
      byte[] var2 = this.getRecord().getData();
      this.width = IntegerHelper.getInt(var2[0], var2[1]);
      this.height = IntegerHelper.getInt(var2[2], var2[3]);
      this.rowOutlineLevel = IntegerHelper.getInt(var2[4], var2[5]);
      this.columnOutlineLevel = IntegerHelper.getInt(var2[6], var2[7]);
   }

   int getColumnOutlineLevel() {
      return this.columnOutlineLevel;
   }

   int getRowOutlineLevel() {
      return this.rowOutlineLevel;
   }
}
