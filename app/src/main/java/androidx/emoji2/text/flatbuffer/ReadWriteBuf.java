package androidx.emoji2.text.flatbuffer;

interface ReadWriteBuf extends ReadBuf {
   int limit();

   void put(byte var1);

   void put(byte[] var1, int var2, int var3);

   void putBoolean(boolean var1);

   void putDouble(double var1);

   void putFloat(float var1);

   void putInt(int var1);

   void putLong(long var1);

   void putShort(short var1);

   boolean requestCapacity(int var1);

   void set(int var1, byte var2);

   void set(int var1, byte[] var2, int var3, int var4);

   void setBoolean(int var1, boolean var2);

   void setDouble(int var1, double var2);

   void setFloat(int var1, float var2);

   void setInt(int var1, int var2);

   void setLong(int var1, long var2);

   void setShort(int var1, short var2);

   int writePosition();
}
