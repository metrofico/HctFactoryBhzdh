package androidx.emoji2.text.flatbuffer;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class FlexBuffersBuilder {
   static final boolean $assertionsDisabled = false;
   public static final int BUILDER_FLAG_NONE = 0;
   public static final int BUILDER_FLAG_SHARE_ALL = 7;
   public static final int BUILDER_FLAG_SHARE_KEYS = 1;
   public static final int BUILDER_FLAG_SHARE_KEYS_AND_STRINGS = 3;
   public static final int BUILDER_FLAG_SHARE_KEY_VECTORS = 4;
   public static final int BUILDER_FLAG_SHARE_STRINGS = 2;
   private static final int WIDTH_16 = 1;
   private static final int WIDTH_32 = 2;
   private static final int WIDTH_64 = 3;
   private static final int WIDTH_8 = 0;
   private final ReadWriteBuf bb;
   private boolean finished;
   private final int flags;
   private Comparator keyComparator;
   private final HashMap keyPool;
   private final ArrayList stack;
   private final HashMap stringPool;

   public FlexBuffersBuilder() {
      this(256);
   }

   public FlexBuffersBuilder(int var1) {
      this((ReadWriteBuf)(new ArrayReadWriteBuf(var1)), 1);
   }

   public FlexBuffersBuilder(ReadWriteBuf var1, int var2) {
      this.stack = new ArrayList();
      this.keyPool = new HashMap();
      this.stringPool = new HashMap();
      this.finished = false;
      this.keyComparator = new Comparator(this) {
         final FlexBuffersBuilder this$0;

         {
            this.this$0 = var1;
         }

         public int compare(Value var1, Value var2) {
            int var4 = var1.key;
            int var3 = var2.key;

            byte var5;
            byte var6;
            do {
               var5 = this.this$0.bb.get(var4);
               var6 = this.this$0.bb.get(var3);
               if (var5 == 0) {
                  return var5 - var6;
               }

               ++var4;
               ++var3;
            } while(var5 == var6);

            return var5 - var6;
         }
      };
      this.bb = var1;
      this.flags = var2;
   }

   public FlexBuffersBuilder(ByteBuffer var1) {
      this((ByteBuffer)var1, 1);
   }

   @Deprecated
   public FlexBuffersBuilder(ByteBuffer var1, int var2) {
      this((ReadWriteBuf)(new ArrayReadWriteBuf(var1.array())), var2);
   }

   private int align(int var1) {
      int var2 = 1 << var1;

      for(var1 = FlexBuffersBuilder.Value.paddingBytes(this.bb.writePosition(), var2); var1 != 0; --var1) {
         this.bb.put((byte)0);
      }

      return var2;
   }

   private Value createKeyVector(int var1, int var2) {
      long var6 = (long)var2;
      var2 = Math.max(0, widthUInBits(var6));

      int var3;
      int var4;
      long var8;
      for(var3 = var1; var3 < this.stack.size(); var2 = Math.max(var2, FlexBuffersBuilder.Value.elemWidth(4, 0, var8, var4, var3))) {
         var8 = (long)((Value)this.stack.get(var3)).key;
         var4 = this.bb.writePosition();
         ++var3;
      }

      var3 = this.align(var2);
      this.writeInt(var6, var3);

      for(var4 = this.bb.writePosition(); var1 < this.stack.size(); ++var1) {
         int var5 = ((Value)this.stack.get(var1)).key;
         this.writeOffset((long)((Value)this.stack.get(var1)).key, var3);
      }

      return new Value(-1, FlexBuffers.toTypedVector(4, 0), var2, (long)var4);
   }

   private Value createVector(int var1, int var2, int var3, boolean var4, boolean var5, Value var6) {
      int var7 = var3;
      long var12 = (long)var3;
      var3 = Math.max(0, widthUInBits(var12));
      int var9;
      if (var6 != null) {
         var3 = Math.max(var3, var6.elemWidth(this.bb.writePosition(), 0));
         var9 = 3;
      } else {
         var9 = 1;
      }

      int var8 = 4;

      int var10;
      int var11;
      for(var10 = var2; var10 < this.stack.size(); var3 = var11) {
         var11 = Math.max(var3, ((Value)this.stack.get(var10)).elemWidth(this.bb.writePosition(), var10 + var9));
         var3 = var8;
         if (var4) {
            var3 = var8;
            if (var10 == var2) {
               var3 = ((Value)this.stack.get(var10)).type;
               if (!FlexBuffers.isTypedVectorElementType(var3)) {
                  throw new FlexBuffers.FlexBufferException("TypedVector does not support this element type");
               }
            }
         }

         ++var10;
         var8 = var3;
      }

      var11 = this.align(var3);
      if (var6 != null) {
         this.writeOffset(var6.iValue, var11);
         this.writeInt(1L << var6.minBitWidth, var11);
      }

      if (!var5) {
         this.writeInt(var12, var11);
      }

      var10 = this.bb.writePosition();

      for(var9 = var2; var9 < this.stack.size(); ++var9) {
         this.writeAny((Value)this.stack.get(var9), var11);
      }

      if (!var4) {
         while(var2 < this.stack.size()) {
            this.bb.put(((Value)this.stack.get(var2)).storedPackedType(var3));
            ++var2;
         }
      }

      if (var6 != null) {
         var2 = 9;
      } else if (var4) {
         if (!var5) {
            var7 = 0;
         }

         var2 = FlexBuffers.toTypedVector(var8, var7);
      } else {
         var2 = 10;
      }

      return new Value(var1, var2, var3, (long)var10);
   }

   private int putKey(String var1) {
      if (var1 == null) {
         return -1;
      } else {
         int var2 = this.bb.writePosition();
         byte[] var4;
         if ((this.flags & 1) != 0) {
            Integer var3 = (Integer)this.keyPool.get(var1);
            if (var3 == null) {
               var4 = var1.getBytes(StandardCharsets.UTF_8);
               this.bb.put(var4, 0, var4.length);
               this.bb.put((byte)0);
               this.keyPool.put(var1, var2);
            } else {
               var2 = var3;
            }
         } else {
            var4 = var1.getBytes(StandardCharsets.UTF_8);
            this.bb.put(var4, 0, var4.length);
            this.bb.put((byte)0);
            this.keyPool.put(var1, var2);
         }

         return var2;
      }
   }

   private void putUInt(String var1, long var2) {
      int var4 = this.putKey(var1);
      int var5 = widthUInBits(var2);
      Value var6;
      if (var5 == 0) {
         var6 = FlexBuffersBuilder.Value.uInt8(var4, (int)var2);
      } else if (var5 == 1) {
         var6 = FlexBuffersBuilder.Value.uInt16(var4, (int)var2);
      } else if (var5 == 2) {
         var6 = FlexBuffersBuilder.Value.uInt32(var4, (int)var2);
      } else {
         var6 = FlexBuffersBuilder.Value.uInt64(var4, var2);
      }

      this.stack.add(var6);
   }

   private void putUInt64(String var1, long var2) {
      this.stack.add(FlexBuffersBuilder.Value.uInt64(this.putKey(var1), var2));
   }

   static int widthUInBits(long var0) {
      if (var0 <= (long)FlexBuffers.Unsigned.byteToUnsignedInt((byte)-1)) {
         return 0;
      } else if (var0 <= (long)FlexBuffers.Unsigned.shortToUnsignedInt((short)-1)) {
         return 1;
      } else {
         return var0 <= FlexBuffers.Unsigned.intToUnsignedLong(-1) ? 2 : 3;
      }
   }

   private void writeAny(Value var1, int var2) {
      int var3 = var1.type;
      if (var3 != 0 && var3 != 1 && var3 != 2) {
         if (var3 == 3) {
            this.writeDouble(var1.dValue, var2);
            return;
         }

         if (var3 != 26) {
            this.writeOffset(var1.iValue, var2);
            return;
         }
      }

      this.writeInt(var1.iValue, var2);
   }

   private Value writeBlob(int var1, byte[] var2, int var3, boolean var4) {
      int var5 = widthUInBits((long)var2.length);
      int var6 = this.align(var5);
      this.writeInt((long)var2.length, var6);
      var6 = this.bb.writePosition();
      this.bb.put(var2, 0, var2.length);
      if (var4) {
         this.bb.put((byte)0);
      }

      return FlexBuffersBuilder.Value.blob(var1, var6, var3, var5);
   }

   private void writeDouble(double var1, int var3) {
      if (var3 == 4) {
         this.bb.putFloat((float)var1);
      } else if (var3 == 8) {
         this.bb.putDouble(var1);
      }

   }

   private void writeInt(long var1, int var3) {
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 != 4) {
               if (var3 == 8) {
                  this.bb.putLong(var1);
               }
            } else {
               this.bb.putInt((int)var1);
            }
         } else {
            this.bb.putShort((short)((int)var1));
         }
      } else {
         this.bb.put((byte)((int)var1));
      }

   }

   private void writeOffset(long var1, int var3) {
      this.writeInt((long)((int)((long)this.bb.writePosition() - var1)), var3);
   }

   private Value writeString(int var1, String var2) {
      return this.writeBlob(var1, var2.getBytes(StandardCharsets.UTF_8), 5, true);
   }

   public int endMap(String var1, int var2) {
      int var3 = this.putKey(var1);
      ArrayList var5 = this.stack;
      Collections.sort(var5.subList(var2, var5.size()), this.keyComparator);
      Value var6 = this.createKeyVector(var2, this.stack.size() - var2);
      Value var4 = this.createVector(var3, var2, this.stack.size() - var2, false, false, var6);

      while(this.stack.size() > var2) {
         var5 = this.stack;
         var5.remove(var5.size() - 1);
      }

      this.stack.add(var4);
      return (int)var4.iValue;
   }

   public int endVector(String var1, int var2, boolean var3, boolean var4) {
      Value var6 = this.createVector(this.putKey(var1), var2, this.stack.size() - var2, var3, var4, (Value)null);

      while(this.stack.size() > var2) {
         ArrayList var5 = this.stack;
         var5.remove(var5.size() - 1);
      }

      this.stack.add(var6);
      return (int)var6.iValue;
   }

   public ByteBuffer finish() {
      int var1 = this.align(((Value)this.stack.get(0)).elemWidth(this.bb.writePosition(), 0));
      this.writeAny((Value)this.stack.get(0), var1);
      this.bb.put(((Value)this.stack.get(0)).storedPackedType());
      this.bb.put((byte)var1);
      this.finished = true;
      return ByteBuffer.wrap(this.bb.data(), 0, this.bb.writePosition());
   }

   public ReadWriteBuf getBuffer() {
      return this.bb;
   }

   public int putBlob(String var1, byte[] var2) {
      Value var3 = this.writeBlob(this.putKey(var1), var2, 25, false);
      this.stack.add(var3);
      return (int)var3.iValue;
   }

   public int putBlob(byte[] var1) {
      return this.putBlob((String)null, var1);
   }

   public void putBoolean(String var1, boolean var2) {
      this.stack.add(FlexBuffersBuilder.Value.bool(this.putKey(var1), var2));
   }

   public void putBoolean(boolean var1) {
      this.putBoolean((String)null, var1);
   }

   public void putFloat(double var1) {
      this.putFloat((String)null, var1);
   }

   public void putFloat(float var1) {
      this.putFloat((String)null, var1);
   }

   public void putFloat(String var1, double var2) {
      this.stack.add(FlexBuffersBuilder.Value.float64(this.putKey(var1), var2));
   }

   public void putFloat(String var1, float var2) {
      this.stack.add(FlexBuffersBuilder.Value.float32(this.putKey(var1), var2));
   }

   public void putInt(int var1) {
      this.putInt((String)null, var1);
   }

   public void putInt(long var1) {
      this.putInt((String)null, var1);
   }

   public void putInt(String var1, int var2) {
      this.putInt(var1, (long)var2);
   }

   public void putInt(String var1, long var2) {
      int var4 = this.putKey(var1);
      if (-128L <= var2 && var2 <= 127L) {
         this.stack.add(FlexBuffersBuilder.Value.int8(var4, (int)var2));
      } else if (-32768L <= var2 && var2 <= 32767L) {
         this.stack.add(FlexBuffersBuilder.Value.int16(var4, (int)var2));
      } else if (-2147483648L <= var2 && var2 <= 2147483647L) {
         this.stack.add(FlexBuffersBuilder.Value.int32(var4, (int)var2));
      } else {
         this.stack.add(FlexBuffersBuilder.Value.int64(var4, var2));
      }

   }

   public int putString(String var1) {
      return this.putString((String)null, var1);
   }

   public int putString(String var1, String var2) {
      int var4 = this.putKey(var1);
      Value var5;
      if ((this.flags & 2) != 0) {
         Integer var6 = (Integer)this.stringPool.get(var2);
         if (var6 == null) {
            var5 = this.writeString(var4, var2);
            this.stringPool.put(var2, (int)var5.iValue);
            this.stack.add(var5);
            return (int)var5.iValue;
         } else {
            int var3 = widthUInBits((long)var2.length());
            this.stack.add(FlexBuffersBuilder.Value.blob(var4, var6, 5, var3));
            return var6;
         }
      } else {
         var5 = this.writeString(var4, var2);
         this.stack.add(var5);
         return (int)var5.iValue;
      }
   }

   public void putUInt(int var1) {
      this.putUInt((String)null, (long)var1);
   }

   public void putUInt(long var1) {
      this.putUInt((String)null, var1);
   }

   public void putUInt64(BigInteger var1) {
      this.putUInt64((String)null, var1.longValue());
   }

   public int startMap() {
      return this.stack.size();
   }

   public int startVector() {
      return this.stack.size();
   }

   private static class Value {
      static final boolean $assertionsDisabled = false;
      final double dValue;
      long iValue;
      int key;
      final int minBitWidth;
      final int type;

      Value(int var1, int var2, int var3, double var4) {
         this.key = var1;
         this.type = var2;
         this.minBitWidth = var3;
         this.dValue = var4;
         this.iValue = Long.MIN_VALUE;
      }

      Value(int var1, int var2, int var3, long var4) {
         this.key = var1;
         this.type = var2;
         this.minBitWidth = var3;
         this.iValue = var4;
         this.dValue = Double.MIN_VALUE;
      }

      static Value blob(int var0, int var1, int var2, int var3) {
         return new Value(var0, var2, var3, (long)var1);
      }

      static Value bool(int var0, boolean var1) {
         long var2;
         if (var1) {
            var2 = 1L;
         } else {
            var2 = 0L;
         }

         return new Value(var0, 26, 0, var2);
      }

      private int elemWidth(int var1, int var2) {
         return elemWidth(this.type, this.minBitWidth, this.iValue, var1, var2);
      }

      private static int elemWidth(int var0, int var1, long var2, int var4, int var5) {
         if (FlexBuffers.isTypeInline(var0)) {
            return var1;
         } else {
            for(var0 = 1; var0 <= 32; var0 *= 2) {
               var1 = FlexBuffersBuilder.widthUInBits((long)((int)((long)(paddingBytes(var4, var0) + var4 + var5 * var0) - var2)));
               if (1L << var1 == (long)var0) {
                  return var1;
               }
            }

            return 3;
         }
      }

      static Value float32(int var0, float var1) {
         return new Value(var0, 3, 2, (double)var1);
      }

      static Value float64(int var0, double var1) {
         return new Value(var0, 3, 3, var1);
      }

      static Value int16(int var0, int var1) {
         return new Value(var0, 1, 1, (long)var1);
      }

      static Value int32(int var0, int var1) {
         return new Value(var0, 1, 2, (long)var1);
      }

      static Value int64(int var0, long var1) {
         return new Value(var0, 1, 3, var1);
      }

      static Value int8(int var0, int var1) {
         return new Value(var0, 1, 0, (long)var1);
      }

      private static byte packedType(int var0, int var1) {
         return (byte)(var0 | var1 << 2);
      }

      private static int paddingBytes(int var0, int var1) {
         return ~var0 + 1 & var1 - 1;
      }

      private byte storedPackedType() {
         return this.storedPackedType(0);
      }

      private byte storedPackedType(int var1) {
         return packedType(this.storedWidth(var1), this.type);
      }

      private int storedWidth(int var1) {
         return FlexBuffers.isTypeInline(this.type) ? Math.max(this.minBitWidth, var1) : this.minBitWidth;
      }

      static Value uInt16(int var0, int var1) {
         return new Value(var0, 2, 1, (long)var1);
      }

      static Value uInt32(int var0, int var1) {
         return new Value(var0, 2, 2, (long)var1);
      }

      static Value uInt64(int var0, long var1) {
         return new Value(var0, 2, 3, var1);
      }

      static Value uInt8(int var0, int var1) {
         return new Value(var0, 2, 0, (long)var1);
      }
   }
}
