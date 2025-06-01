package androidx.emoji2.text.flatbuffer;

interface ReadBuf {
   byte[] data();

   byte get(int var1);

   boolean getBoolean(int var1);

   double getDouble(int var1);

   float getFloat(int var1);

   int getInt(int var1);

   long getLong(int var1);

   short getShort(int var1);

   String getString(int var1, int var2);

   int limit();
}
