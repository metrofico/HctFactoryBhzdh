package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ByteBufferReadWriteBuf implements ReadWriteBuf {
   private final ByteBuffer buffer;

   public ByteBufferReadWriteBuf(ByteBuffer var1) {
      this.buffer = var1;
      var1.order(ByteOrder.LITTLE_ENDIAN);
   }

   public byte[] data() {
      return this.buffer.array();
   }

   public byte get(int var1) {
      return this.buffer.get(var1);
   }

   public boolean getBoolean(int var1) {
      boolean var2;
      if (this.get(var1) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public double getDouble(int var1) {
      return this.buffer.getDouble(var1);
   }

   public float getFloat(int var1) {
      return this.buffer.getFloat(var1);
   }

   public int getInt(int var1) {
      return this.buffer.getInt(var1);
   }

   public long getLong(int var1) {
      return this.buffer.getLong(var1);
   }

   public short getShort(int var1) {
      return this.buffer.getShort(var1);
   }

   public String getString(int var1, int var2) {
      return Utf8Safe.decodeUtf8Buffer(this.buffer, var1, var2);
   }

   public int limit() {
      return this.buffer.limit();
   }

   public void put(byte var1) {
      this.buffer.put(var1);
   }

   public void put(byte[] var1, int var2, int var3) {
      this.buffer.put(var1, var2, var3);
   }

   public void putBoolean(boolean var1) {
      this.buffer.put(var1);
   }

   public void putDouble(double var1) {
      this.buffer.putDouble(var1);
   }

   public void putFloat(float var1) {
      this.buffer.putFloat(var1);
   }

   public void putInt(int var1) {
      this.buffer.putInt(var1);
   }

   public void putLong(long var1) {
      this.buffer.putLong(var1);
   }

   public void putShort(short var1) {
      this.buffer.putShort(var1);
   }

   public boolean requestCapacity(int var1) {
      boolean var2;
      if (var1 <= this.buffer.limit()) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public void set(int var1, byte var2) {
      this.requestCapacity(var1 + 1);
      this.buffer.put(var1, var2);
   }

   public void set(int var1, byte[] var2, int var3, int var4) {
      this.requestCapacity(var4 - var3 + var1);
      int var5 = this.buffer.position();
      this.buffer.position(var1);
      this.buffer.put(var2, var3, var4);
      this.buffer.position(var5);
   }

   public void setBoolean(int var1, boolean var2) {
      this.set(var1, var2);
   }

   public void setDouble(int var1, double var2) {
      this.requestCapacity(var1 + 8);
      this.buffer.putDouble(var1, var2);
   }

   public void setFloat(int var1, float var2) {
      this.requestCapacity(var1 + 4);
      this.buffer.putFloat(var1, var2);
   }

   public void setInt(int var1, int var2) {
      this.requestCapacity(var1 + 4);
      this.buffer.putInt(var1, var2);
   }

   public void setLong(int var1, long var2) {
      this.requestCapacity(var1 + 8);
      this.buffer.putLong(var1, var2);
   }

   public void setShort(int var1, short var2) {
      this.requestCapacity(var1 + 2);
      this.buffer.putShort(var1, var2);
   }

   public int writePosition() {
      return this.buffer.position();
   }
}
