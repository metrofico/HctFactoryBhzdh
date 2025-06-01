package jxl.biff;

class BuiltInStyle extends WritableRecordData {
   private int styleNumber;
   private int xfIndex;

   public BuiltInStyle(int var1, int var2) {
      super(Type.STYLE);
      this.xfIndex = var1;
      this.styleNumber = var2;
   }

   public byte[] getData() {
      byte[] var1 = new byte[4];
      IntegerHelper.getTwoBytes(this.xfIndex, var1, 0);
      var1[1] = (byte)(var1[1] | 128);
      var1[2] = (byte)this.styleNumber;
      var1[3] = -1;
      return var1;
   }
}
