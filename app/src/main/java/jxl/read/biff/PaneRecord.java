package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class PaneRecord extends RecordData {
   private static Logger logger = Logger.getLogger(PaneRecord.class);
   private int columnsVisible;
   private int rowsVisible;

   public PaneRecord(Record var1) {
      super(var1);
      byte[] var2 = var1.getData();
      this.columnsVisible = IntegerHelper.getInt(var2[0], var2[1]);
      this.rowsVisible = IntegerHelper.getInt(var2[2], var2[3]);
   }

   public final int getColumnsVisible() {
      return this.columnsVisible;
   }

   public final int getRowsVisible() {
      return this.rowsVisible;
   }
}
