package androidx.emoji2.text.flatbuffer;

import java.util.Arrays;

public class ArrayReadWriteBuf implements ReadWriteBuf {
   private byte[] buffer;
   private int writePos;

   public ArrayReadWriteBuf() {
      this(10);
   }

   public ArrayReadWriteBuf(int var1) {
      this(new byte[var1]);
   }

   public ArrayReadWriteBuf(byte[] var1) {
      this.buffer = var1;
      this.writePos = 0;
   }

   public ArrayReadWriteBuf(byte[] var1, int var2) {
      this.buffer = var1;
      this.writePos = var2;
   }

   public byte[] data() {
      return this.buffer;
   }

   public byte get(int var1) {
      return this.buffer[var1];
   }

   public boolean getBoolean(int var1) {
      boolean var2;
      if (this.buffer[var1] != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public double getDouble(int var1) {
      return Double.longBitsToDouble(this.getLong(var1));
   }

   public float getFloat(int var1) {
      return Float.intBitsToFloat(this.getInt(var1));
   }

   public int getInt(int var1) {
      byte[] var5 = this.buffer;
      byte var3 = var5[var1 + 3];
      byte var2 = var5[var1 + 2];
      byte var4 = var5[var1 + 1];
      return var5[var1] & 255 | var3 << 24 | (var2 & 255) << 16 | (var4 & 255) << 8;
   }

   public long getLong(int var1) {
      byte[] var14 = this.buffer;
      int var2 = var1 + 1;
      long var12 = (long)var14[var1];
      var1 = var2 + 1;
      long var6 = (long)var14[var2];
      var2 = var1 + 1;
      long var8 = (long)var14[var1];
      int var3 = var2 + 1;
      long var4 = (long)var14[var2];
      var1 = var3 + 1;
      long var10 = (long)var14[var3];
      var2 = var1 + 1;
      return var12 & 255L | (var6 & 255L) << 8 | (var8 & 255L) << 16 | (var4 & 255L) << 24 | (var10 & 255L) << 32 | ((long)var14[var1] & 255L) << 40 | (255L & (long)var14[var2]) << 48 | (long)var14[var2 + 1] << 56;
   }

   public short getShort(int var1) {
      byte[] var3 = this.buffer;
      byte var2 = var3[var1 + 1];
      return (short)(var3[var1] & 255 | var2 << 8);
   }

   public String getString(int var1, int var2) {
      return Utf8Safe.decodeUtf8Array(this.buffer, var1, var2);
   }

   public int limit() {
      return this.writePos;
   }

   public void put(byte var1) {
      this.set(this.writePos, var1);
      ++this.writePos;
   }

   public void put(byte[] var1, int var2, int var3) {
      this.set(this.writePos, var1, var2, var3);
      this.writePos += var3;
   }

   public void putBoolean(boolean var1) {
      this.setBoolean(this.writePos, var1);
      ++this.writePos;
   }

   public void putDouble(double var1) {
      this.setDouble(this.writePos, var1);
      this.writePos += 8;
   }

   public void putFloat(float var1) {
      this.setFloat(this.writePos, var1);
      this.writePos += 4;
   }

   public void putInt(int var1) {
      this.setInt(this.writePos, var1);
      this.writePos += 4;
   }

   public void putLong(long var1) {
      this.setLong(this.writePos, var1);
      this.writePos += 8;
   }

   public void putShort(short var1) {
      this.setShort(this.writePos, var1);
      this.writePos += 2;
   }

   public boolean requestCapacity(int var1) {
      byte[] var2 = this.buffer;
      if (var2.length > var1) {
         return true;
      } else {
         var1 = var2.length;
         this.buffer = Arrays.copyOf(var2, var1 + (var1 >> 1));
         return true;
      }
   }

   public void set(int var1, byte var2) {
      this.requestCapacity(var1 + 1);
      this.buffer[var1] = var2;
   }

   public void set(int var1, byte[] var2, int var3, int var4) {
      this.requestCapacity(var4 - var3 + var1);
      System.arraycopy(var2, var3, this.buffer, var1, var4);
   }

   public void setBoolean(int var1, boolean var2) {
      this.set(var1, var2);
   }

   public void setDouble(int var1, double var2) {
      this.requestCapacity(var1 + 8);
      long var6 = Double.doubleToRawLongBits(var2);
      int var4 = (int)var6;
      byte[] var8 = this.buffer;
      int var5 = var1 + 1;
      var8[var1] = (byte)(var4 & 255);
      var1 = var5 + 1;
      var8[var5] = (byte)(var4 >> 8 & 255);
      var5 = var1 + 1;
      var8[var1] = (byte)(var4 >> 16 & 255);
      var1 = var5 + 1;
      var8[var5] = (byte)(var4 >> 24 & 255);
      var5 = (int)(var6 >> 32);
      var4 = var1 + 1;
      var8[var1] = (byte)(var5 & 255);
      var1 = var4 + 1;
      var8[var4] = (byte)(var5 >> 8 & 255);
      var8[var1] = (byte)(var5 >> 16 & 255);
      var8[var1 + 1] = (byte)(var5 >> 24 & 255);
   }

   public void setFloat(int var1, float var2) {
      this.requestCapacity(var1 + 4);
      int var4 = Float.floatToRawIntBits(var2);
      byte[] var5 = this.buffer;
      int var3 = var1 + 1;
      var5[var1] = (byte)(var4 & 255);
      var1 = var3 + 1;
      var5[var3] = (byte)(var4 >> 8 & 255);
      var5[var1] = (byte)(var4 >> 16 & 255);
      var5[var1 + 1] = (byte)(var4 >> 24 & 255);
   }

   public void setInt(int var1, int var2) {
      this.requestCapacity(var1 + 4);
      byte[] var4 = this.buffer;
      int var3 = var1 + 1;
      var4[var1] = (byte)(var2 & 255);
      var1 = var3 + 1;
      var4[var3] = (byte)(var2 >> 8 & 255);
      var4[var1] = (byte)(var2 >> 16 & 255);
      var4[var1 + 1] = (byte)(var2 >> 24 & 255);
   }

   public void setLong(int var1, long var2) {
      this.requestCapacity(var1 + 8);
      int var4 = (int)var2;
      byte[] var6 = this.buffer;
      int var5 = var1 + 1;
      var6[var1] = (byte)(var4 & 255);
      var1 = var5 + 1;
      var6[var5] = (byte)(var4 >> 8 & 255);
      var5 = var1 + 1;
      var6[var1] = (byte)(var4 >> 16 & 255);
      var1 = var5 + 1;
      var6[var5] = (byte)(var4 >> 24 & 255);
      var4 = (int)(var2 >> 32);
      var5 = var1 + 1;
      var6[var1] = (byte)(var4 & 255);
      var1 = var5 + 1;
      var6[var5] = (byte)(var4 >> 8 & 255);
      var6[var1] = (byte)(var4 >> 16 & 255);
      var6[var1 + 1] = (byte)(var4 >> 24 & 255);
   }

   public void setShort(int var1, short var2) {
      this.requestCapacity(var1 + 2);
      byte[] var3 = this.buffer;
      var3[var1] = (byte)(var2 & 255);
      var3[var1 + 1] = (byte)(var2 >> 8 & 255);
   }

   public int writePosition() {
      return this.writePos;
   }
}
