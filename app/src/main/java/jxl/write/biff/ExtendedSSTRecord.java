package jxl.write.biff;

import jxl.biff.IntegerHelper;
import jxl.biff.Type;
import jxl.biff.WritableRecordData;

class ExtendedSSTRecord extends WritableRecordData {
   private static final int infoRecordSize = 8;
   private int[] absoluteStreamPositions;
   private int currentStringIndex = 0;
   private int numberOfStrings;
   private int[] relativeStreamPositions;

   public ExtendedSSTRecord(int var1) {
      super(Type.EXTSST);
      this.numberOfStrings = var1;
      var1 = this.getNumberOfBuckets();
      this.absoluteStreamPositions = new int[var1];
      this.relativeStreamPositions = new int[var1];
      this.currentStringIndex = 0;
   }

   public void addString(int var1, int var2) {
      int[] var4 = this.absoluteStreamPositions;
      int var3 = this.currentStringIndex;
      var4[var3] = var1 + var2;
      this.relativeStreamPositions[var3] = var2;
      this.currentStringIndex = var3 + 1;
   }

   public byte[] getData() {
      int var2 = this.getNumberOfBuckets();
      byte[] var5 = new byte[var2 * 8 + 2];
      int var3 = this.getNumberOfStringsPerBucket();
      int var1 = 0;
      IntegerHelper.getTwoBytes(var3, var5, 0);

      while(var1 < var2) {
         int var4 = this.absoluteStreamPositions[var1];
         var3 = var1 * 8;
         IntegerHelper.getFourBytes(var4, var5, var3 + 2);
         IntegerHelper.getTwoBytes(this.relativeStreamPositions[var1], var5, var3 + 6);
         ++var1;
      }

      return var5;
   }

   public int getNumberOfBuckets() {
      int var1 = this.getNumberOfStringsPerBucket();
      if (var1 != 0) {
         var1 = (this.numberOfStrings + var1 - 1) / var1;
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int getNumberOfStringsPerBucket() {
      return (this.numberOfStrings + 128 - 1) / 128;
   }
}
