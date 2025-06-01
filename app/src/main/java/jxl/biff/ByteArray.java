package jxl.biff;

public class ByteArray {
   private static final int defaultGrowSize = 1024;
   private byte[] bytes;
   private int growSize;
   private int pos;

   public ByteArray() {
      this(1024);
   }

   public ByteArray(int var1) {
      this.growSize = var1;
      this.bytes = new byte[1024];
      this.pos = 0;
   }

   private void checkSize(int var1) {
      while(true) {
         int var2 = this.pos;
         byte[] var3 = this.bytes;
         if (var2 + var1 < var3.length) {
            return;
         }

         byte[] var4 = new byte[var3.length + this.growSize];
         System.arraycopy(var3, 0, var4, 0, var2);
         this.bytes = var4;
      }
   }

   public void add(byte var1) {
      this.checkSize(1);
      byte[] var3 = this.bytes;
      int var2 = this.pos;
      var3[var2] = var1;
      this.pos = var2 + 1;
   }

   public void add(byte[] var1) {
      this.checkSize(var1.length);
      System.arraycopy(var1, 0, this.bytes, this.pos, var1.length);
      this.pos += var1.length;
   }

   public byte[] getBytes() {
      int var1 = this.pos;
      byte[] var2 = new byte[var1];
      System.arraycopy(this.bytes, 0, var2, 0, var1);
      return var2;
   }
}
