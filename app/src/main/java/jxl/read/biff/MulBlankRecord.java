package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class MulBlankRecord extends RecordData {
   private static Logger logger = Logger.getLogger(MulBlankRecord.class);
   private int colFirst;
   private int colLast;
   private int numblanks;
   private int row;
   private int[] xfIndices;

   public MulBlankRecord(Record var1) {
      super(var1);
      byte[] var3 = this.getRecord().getData();
      int var2 = this.getRecord().getLength();
      this.row = IntegerHelper.getInt(var3[0], var3[1]);
      this.colFirst = IntegerHelper.getInt(var3[2], var3[3]);
      var2 = IntegerHelper.getInt(var3[var2 - 2], var3[var2 - 1]);
      this.colLast = var2;
      var2 = var2 - this.colFirst + 1;
      this.numblanks = var2;
      this.xfIndices = new int[var2];
      this.readBlanks(var3);
   }

   private void readBlanks(byte[] var1) {
      int var3 = 4;

      for(int var2 = 0; var2 < this.numblanks; ++var2) {
         this.xfIndices[var2] = IntegerHelper.getInt(var1[var3], var1[var3 + 1]);
         var3 += 2;
      }

   }

   public int getFirstColumn() {
      return this.colFirst;
   }

   public int getNumberOfColumns() {
      return this.numblanks;
   }

   public int getRow() {
      return this.row;
   }

   public int getXFIndex(int var1) {
      return this.xfIndices[var1];
   }
}
