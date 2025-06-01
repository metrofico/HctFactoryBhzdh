package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class MulRKRecord extends RecordData {
   private static Logger logger = Logger.getLogger(MulRKRecord.class);
   private int colFirst;
   private int colLast;
   private int numrks;
   private int[] rknumbers;
   private int row;
   private int[] xfIndices;

   public MulRKRecord(Record var1) {
      super(var1);
      byte[] var3 = this.getRecord().getData();
      int var2 = this.getRecord().getLength();
      this.row = IntegerHelper.getInt(var3[0], var3[1]);
      this.colFirst = IntegerHelper.getInt(var3[2], var3[3]);
      var2 = IntegerHelper.getInt(var3[var2 - 2], var3[var2 - 1]);
      this.colLast = var2;
      var2 = var2 - this.colFirst + 1;
      this.numrks = var2;
      this.rknumbers = new int[var2];
      this.xfIndices = new int[var2];
      this.readRks(var3);
   }

   private void readRks(byte[] var1) {
      int var3 = 4;

      for(int var2 = 0; var2 < this.numrks; ++var2) {
         this.xfIndices[var2] = IntegerHelper.getInt(var1[var3], var1[var3 + 1]);
         int var4 = IntegerHelper.getInt(var1[var3 + 2], var1[var3 + 3], var1[var3 + 4], var1[var3 + 5]);
         this.rknumbers[var2] = var4;
         var3 += 6;
      }

   }

   public int getFirstColumn() {
      return this.colFirst;
   }

   public int getNumberOfColumns() {
      return this.numrks;
   }

   public int getRKNumber(int var1) {
      return this.rknumbers[var1];
   }

   public int getRow() {
      return this.row;
   }

   public int getXFIndex(int var1) {
      return this.xfIndices[var1];
   }
}
