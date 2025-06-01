package jxl.biff;

import jxl.common.Logger;

public class DValParser {
   private static int PROMPT_BOX_AT_CELL_MASK = 2;
   private static int PROMPT_BOX_VISIBLE_MASK = 1;
   private static int VALIDITY_DATA_CACHED_MASK = 4;
   private static Logger logger = Logger.getLogger(DValParser.class);
   private int numDVRecords;
   private int objectId;
   private boolean promptBoxAtCell;
   private boolean promptBoxVisible;
   private boolean validityDataCached;

   public DValParser(int var1, int var2) {
      this.objectId = var1;
      this.numDVRecords = var2;
      this.validityDataCached = true;
   }

   public DValParser(byte[] var1) {
      boolean var4 = false;
      int var2 = IntegerHelper.getInt(var1[0], var1[1]);
      boolean var3;
      if ((PROMPT_BOX_VISIBLE_MASK & var2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.promptBoxVisible = var3;
      if ((PROMPT_BOX_AT_CELL_MASK & var2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.promptBoxAtCell = var3;
      var3 = var4;
      if ((var2 & VALIDITY_DATA_CACHED_MASK) != 0) {
         var3 = true;
      }

      this.validityDataCached = var3;
      this.objectId = IntegerHelper.getInt(var1[10], var1[11], var1[12], var1[13]);
      this.numDVRecords = IntegerHelper.getInt(var1[14], var1[15], var1[16], var1[17]);
   }

   public void dvAdded() {
      ++this.numDVRecords;
   }

   public void dvRemoved() {
      --this.numDVRecords;
   }

   public byte[] getData() {
      byte[] var3 = new byte[18];
      int var2;
      if (this.promptBoxVisible) {
         var2 = PROMPT_BOX_VISIBLE_MASK | 0;
      } else {
         var2 = 0;
      }

      int var1 = var2;
      if (this.promptBoxAtCell) {
         var1 = var2 | PROMPT_BOX_AT_CELL_MASK;
      }

      var2 = var1;
      if (this.validityDataCached) {
         var2 = var1 | VALIDITY_DATA_CACHED_MASK;
      }

      IntegerHelper.getTwoBytes(var2, var3, 0);
      IntegerHelper.getFourBytes(this.objectId, var3, 10);
      IntegerHelper.getFourBytes(this.numDVRecords, var3, 14);
      return var3;
   }

   public int getNumberOfDVRecords() {
      return this.numDVRecords;
   }

   public int getObjectId() {
      return this.objectId;
   }
}
