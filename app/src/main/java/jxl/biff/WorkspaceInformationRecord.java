package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public class WorkspaceInformationRecord extends WritableRecordData {
   private static final int DEFAULT_OPTIONS = 1217;
   private static final int FIT_TO_PAGES = 256;
   private static final int SHOW_COLUMN_OUTLINE_SYMBOLS = 2048;
   private static final int SHOW_ROW_OUTLINE_SYMBOLS = 1024;
   private static Logger logger = Logger.getLogger(WorkspaceInformationRecord.class);
   private boolean columnOutlines;
   private boolean fitToPages;
   private boolean rowOutlines;
   private int wsoptions;

   public WorkspaceInformationRecord() {
      super(Type.WSBOOL);
      this.wsoptions = 1217;
   }

   public WorkspaceInformationRecord(Record var1) {
      super(var1);
      byte[] var5 = this.getRecord().getData();
      boolean var4 = false;
      int var2 = IntegerHelper.getInt(var5[0], var5[1]);
      this.wsoptions = var2;
      boolean var3;
      if ((var2 | 256) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.fitToPages = var3;
      if ((var2 | 1024) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.rowOutlines = var3;
      var3 = var4;
      if ((var2 | 2048) != 0) {
         var3 = true;
      }

      this.columnOutlines = var3;
   }

   public byte[] getData() {
      byte[] var1 = new byte[2];
      if (this.fitToPages) {
         this.wsoptions |= 256;
      }

      if (this.rowOutlines) {
         this.wsoptions |= 1024;
      }

      if (this.columnOutlines) {
         this.wsoptions |= 2048;
      }

      IntegerHelper.getTwoBytes(this.wsoptions, var1, 0);
      return var1;
   }

   public boolean getFitToPages() {
      return this.fitToPages;
   }

   public void setColumnOutlines(boolean var1) {
      this.rowOutlines = true;
   }

   public void setFitToPages(boolean var1) {
      this.fitToPages = var1;
   }

   public void setRowOutlines(boolean var1) {
      this.rowOutlines = true;
   }
}
