package jxl.biff;

import jxl.common.Logger;
import jxl.read.biff.Record;

public abstract class WritableRecordData extends RecordData implements ByteData {
   private static Logger logger = Logger.getLogger(WritableRecordData.class);
   protected static final int maxRecordLength = 8228;

   protected WritableRecordData(Type var1) {
      super(var1);
   }

   protected WritableRecordData(Record var1) {
      super(var1);
   }

   private byte[] handleContinueRecords(byte[] var1) {
      int var5 = (var1.length - 8224) / 8224 + 1;
      byte[] var7 = new byte[var1.length + var5 * 4];
      int var3 = 0;
      System.arraycopy(var1, 0, var7, 0, 8224);
      int var4 = 8224;

      for(int var2 = 8224; var3 < var5; ++var3) {
         int var6 = Math.min(var1.length - var4, 8224);
         IntegerHelper.getTwoBytes(Type.CONTINUE.value, var7, var2);
         IntegerHelper.getTwoBytes(var6, var7, var2 + 2);
         System.arraycopy(var1, var4, var7, var2 + 4, var6);
         var4 += var6;
         var2 += var6 + 4;
      }

      return var7;
   }

   public final byte[] getBytes() {
      byte[] var3 = this.getData();
      int var1 = var3.length;
      byte[] var2 = var3;
      if (var3.length > 8224) {
         var2 = this.handleContinueRecords(var3);
         var1 = 8224;
      }

      var3 = new byte[var2.length + 4];
      System.arraycopy(var2, 0, var3, 4, var2.length);
      IntegerHelper.getTwoBytes(this.getCode(), var3, 0);
      IntegerHelper.getTwoBytes(var1, var3, 2);
      return var3;
   }

   protected abstract byte[] getData();
}
