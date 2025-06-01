package jxl.read.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.RecordData;
import jxl.common.Logger;

class DimensionRecord extends RecordData {
   public static Biff7 biff7 = new Biff7();
   private static Logger logger = Logger.getLogger(DimensionRecord.class);
   private int numCols;
   private int numRows;

   public DimensionRecord(Record var1) {
      super(var1);
      byte[] var2 = var1.getData();
      if (var2.length == 10) {
         this.read10ByteData(var2);
      } else {
         this.read14ByteData(var2);
      }

   }

   public DimensionRecord(Record var1, Biff7 var2) {
      super(var1);
      this.read10ByteData(var1.getData());
   }

   private void read10ByteData(byte[] var1) {
      this.numRows = IntegerHelper.getInt(var1[2], var1[3]);
      this.numCols = IntegerHelper.getInt(var1[6], var1[7]);
   }

   private void read14ByteData(byte[] var1) {
      this.numRows = IntegerHelper.getInt(var1[4], var1[5], var1[6], var1[7]);
      this.numCols = IntegerHelper.getInt(var1[10], var1[11]);
   }

   public int getNumberOfColumns() {
      return this.numCols;
   }

   public int getNumberOfRows() {
      return this.numRows;
   }

   private static class Biff7 {
      private Biff7() {
      }

      // $FF: synthetic method
      Biff7(Object var1) {
         this();
      }
   }
}
