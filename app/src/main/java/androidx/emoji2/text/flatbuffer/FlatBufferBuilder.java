package androidx.emoji2.text.flatbuffer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class FlatBufferBuilder {
   static final boolean $assertionsDisabled = false;
   ByteBuffer bb;
   ByteBufferFactory bb_factory;
   boolean finished;
   boolean force_defaults;
   int minalign;
   boolean nested;
   int num_vtables;
   int object_start;
   int space;
   final Utf8 utf8;
   int vector_num_elems;
   int[] vtable;
   int vtable_in_use;
   int[] vtables;

   public FlatBufferBuilder() {
      this(1024);
   }

   public FlatBufferBuilder(int var1) {
      this(var1, FlatBufferBuilder.HeapByteBufferFactory.INSTANCE, (ByteBuffer)null, Utf8.getDefault());
   }

   public FlatBufferBuilder(int var1, ByteBufferFactory var2) {
      this(var1, var2, (ByteBuffer)null, Utf8.getDefault());
   }

   public FlatBufferBuilder(int var1, ByteBufferFactory var2, ByteBuffer var3, Utf8 var4) {
      this.minalign = 1;
      this.vtable = null;
      this.vtable_in_use = 0;
      this.nested = false;
      this.finished = false;
      this.vtables = new int[16];
      this.num_vtables = 0;
      this.vector_num_elems = 0;
      this.force_defaults = false;
      int var5 = var1;
      if (var1 <= 0) {
         var5 = 1;
      }

      this.bb_factory = var2;
      if (var3 != null) {
         this.bb = var3;
         var3.clear();
         this.bb.order(ByteOrder.LITTLE_ENDIAN);
      } else {
         this.bb = var2.newByteBuffer(var5);
      }

      this.utf8 = var4;
      this.space = this.bb.capacity();
   }

   public FlatBufferBuilder(ByteBuffer var1) {
      this(var1, new HeapByteBufferFactory());
   }

   public FlatBufferBuilder(ByteBuffer var1, ByteBufferFactory var2) {
      this(var1.capacity(), var2, var1, Utf8.getDefault());
   }

   @Deprecated
   private int dataStart() {
      this.finished();
      return this.space;
   }

   static ByteBuffer growByteBuffer(ByteBuffer var0, ByteBufferFactory var1) {
      int var3 = var0.capacity();
      if ((-1073741824 & var3) == 0) {
         int var2;
         if (var3 == 0) {
            var2 = 1;
         } else {
            var2 = var3 << 1;
         }

         var0.position(0);
         ByteBuffer var4 = var1.newByteBuffer(var2);
         var4.position(var4.clear().capacity() - var3);
         var4.put(var0);
         return var4;
      } else {
         throw new AssertionError("FlatBuffers: cannot grow buffer beyond 2 gigabytes.");
      }
   }

   public static boolean isFieldPresent(Table var0, int var1) {
      boolean var2;
      if (var0.__offset(var1) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public void Nested(int var1) {
      if (var1 != this.offset()) {
         throw new AssertionError("FlatBuffers: struct must be serialized inline.");
      }
   }

   public void addBoolean(int var1, boolean var2, boolean var3) {
      if (this.force_defaults || var2 != var3) {
         this.addBoolean(var2);
         this.slot(var1);
      }

   }

   public void addBoolean(boolean var1) {
      this.prep(1, 0);
      this.putBoolean(var1);
   }

   public void addByte(byte var1) {
      this.prep(1, 0);
      this.putByte(var1);
   }

   public void addByte(int var1, byte var2, int var3) {
      if (this.force_defaults || var2 != var3) {
         this.addByte(var2);
         this.slot(var1);
      }

   }

   public void addDouble(double var1) {
      this.prep(8, 0);
      this.putDouble(var1);
   }

   public void addDouble(int var1, double var2, double var4) {
      if (this.force_defaults || var2 != var4) {
         this.addDouble(var2);
         this.slot(var1);
      }

   }

   public void addFloat(float var1) {
      this.prep(4, 0);
      this.putFloat(var1);
   }

   public void addFloat(int var1, float var2, double var3) {
      if (this.force_defaults || (double)var2 != var3) {
         this.addFloat(var2);
         this.slot(var1);
      }

   }

   public void addInt(int var1) {
      this.prep(4, 0);
      this.putInt(var1);
   }

   public void addInt(int var1, int var2, int var3) {
      if (this.force_defaults || var2 != var3) {
         this.addInt(var2);
         this.slot(var1);
      }

   }

   public void addLong(int var1, long var2, long var4) {
      if (this.force_defaults || var2 != var4) {
         this.addLong(var2);
         this.slot(var1);
      }

   }

   public void addLong(long var1) {
      this.prep(8, 0);
      this.putLong(var1);
   }

   public void addOffset(int var1) {
      this.prep(4, 0);
      this.putInt(this.offset() - var1 + 4);
   }

   public void addOffset(int var1, int var2, int var3) {
      if (this.force_defaults || var2 != var3) {
         this.addOffset(var2);
         this.slot(var1);
      }

   }

   public void addShort(int var1, short var2, int var3) {
      if (this.force_defaults || var2 != var3) {
         this.addShort(var2);
         this.slot(var1);
      }

   }

   public void addShort(short var1) {
      this.prep(2, 0);
      this.putShort(var1);
   }

   public void addStruct(int var1, int var2, int var3) {
      if (var2 != var3) {
         this.Nested(var2);
         this.slot(var1);
      }

   }

   public void clear() {
      this.space = this.bb.capacity();
      this.bb.clear();
      this.minalign = 1;

      while(true) {
         int var1 = this.vtable_in_use;
         if (var1 <= 0) {
            this.vtable_in_use = 0;
            this.nested = false;
            this.finished = false;
            this.object_start = 0;
            this.num_vtables = 0;
            this.vector_num_elems = 0;
            return;
         }

         int[] var2 = this.vtable;
         --var1;
         this.vtable_in_use = var1;
         var2[var1] = 0;
      }
   }

   public int createByteVector(ByteBuffer var1) {
      int var2 = var1.remaining();
      this.startVector(1, var2, 1);
      ByteBuffer var3 = this.bb;
      var2 = this.space - var2;
      this.space = var2;
      var3.position(var2);
      this.bb.put(var1);
      return this.endVector();
   }

   public int createByteVector(byte[] var1) {
      int var2 = var1.length;
      this.startVector(1, var2, 1);
      ByteBuffer var3 = this.bb;
      var2 = this.space - var2;
      this.space = var2;
      var3.position(var2);
      this.bb.put(var1);
      return this.endVector();
   }

   public int createByteVector(byte[] var1, int var2, int var3) {
      this.startVector(1, var3, 1);
      ByteBuffer var5 = this.bb;
      int var4 = this.space - var3;
      this.space = var4;
      var5.position(var4);
      this.bb.put(var1, var2, var3);
      return this.endVector();
   }

   public int createSortedVectorOfTables(Table var1, int[] var2) {
      var1.sortTables(var2, this.bb);
      return this.createVectorOfTables(var2);
   }

   public int createString(CharSequence var1) {
      int var2 = this.utf8.encodedLength(var1);
      this.addByte((byte)0);
      this.startVector(1, var2, 1);
      ByteBuffer var3 = this.bb;
      var2 = this.space - var2;
      this.space = var2;
      var3.position(var2);
      this.utf8.encodeUtf8(var1, this.bb);
      return this.endVector();
   }

   public int createString(ByteBuffer var1) {
      int var2 = var1.remaining();
      this.addByte((byte)0);
      this.startVector(1, var2, 1);
      ByteBuffer var3 = this.bb;
      var2 = this.space - var2;
      this.space = var2;
      var3.position(var2);
      this.bb.put(var1);
      return this.endVector();
   }

   public ByteBuffer createUnintializedVector(int var1, int var2, int var3) {
      int var4 = var1 * var2;
      this.startVector(var1, var2, var3);
      ByteBuffer var5 = this.bb;
      var1 = this.space - var4;
      this.space = var1;
      var5.position(var1);
      var5 = this.bb.slice().order(ByteOrder.LITTLE_ENDIAN);
      var5.limit(var4);
      return var5;
   }

   public int createVectorOfTables(int[] var1) {
      this.notNested();
      this.startVector(4, var1.length, 4);

      for(int var2 = var1.length - 1; var2 >= 0; --var2) {
         this.addOffset(var1[var2]);
      }

      return this.endVector();
   }

   public ByteBuffer dataBuffer() {
      this.finished();
      return this.bb;
   }

   public int endTable() {
      if (this.vtable != null && this.nested) {
         this.addInt(0);
         int var4 = this.offset();

         int var1;
         for(var1 = this.vtable_in_use - 1; var1 >= 0 && this.vtable[var1] == 0; --var1) {
         }

         int var2 = var1;

         while(true) {
            int var3 = var2;
            if (var2 < 0) {
               this.addShort((short)(var4 - this.object_start));
               this.addShort((short)((var1 + 1 + 2) * 2));
               var1 = 0;

               label52:
               while(true) {
                  if (var1 >= this.num_vtables) {
                     var1 = 0;
                     break;
                  }

                  int var5 = this.bb.capacity() - this.vtables[var1];
                  int var6 = this.space;
                  short var8 = this.bb.getShort(var5);
                  if (var8 == this.bb.getShort(var6)) {
                     var2 = 2;

                     while(true) {
                        if (var2 >= var8) {
                           var1 = this.vtables[var1];
                           break label52;
                        }

                        if (this.bb.getShort(var5 + var2) != this.bb.getShort(var6 + var2)) {
                           break;
                        }

                        var2 += 2;
                     }
                  }

                  ++var1;
               }

               if (var1 != 0) {
                  var2 = this.bb.capacity() - var4;
                  this.space = var2;
                  this.bb.putInt(var2, var1 - var4);
               } else {
                  var1 = this.num_vtables;
                  int[] var7 = this.vtables;
                  if (var1 == var7.length) {
                     this.vtables = Arrays.copyOf(var7, var1 * 2);
                  }

                  var7 = this.vtables;
                  var1 = this.num_vtables++;
                  var7[var1] = this.offset();
                  ByteBuffer var9 = this.bb;
                  var9.putInt(var9.capacity() - var4, this.offset() - var4);
               }

               this.nested = false;
               return var4;
            }

            var2 = this.vtable[var2];
            if (var2 != 0) {
               var2 = var4 - var2;
            } else {
               var2 = 0;
            }

            this.addShort((short)var2);
            var2 = var3 - 1;
         }
      } else {
         throw new AssertionError("FlatBuffers: endTable called without startTable");
      }
   }

   public int endVector() {
      if (this.nested) {
         this.nested = false;
         this.putInt(this.vector_num_elems);
         return this.offset();
      } else {
         throw new AssertionError("FlatBuffers: endVector called without startVector");
      }
   }

   public void finish(int var1) {
      this.finish(var1, false);
   }

   public void finish(int var1, String var2) {
      this.finish(var1, var2, false);
   }

   protected void finish(int var1, String var2, boolean var3) {
      int var5 = this.minalign;
      int var4;
      if (var3) {
         var4 = 4;
      } else {
         var4 = 0;
      }

      this.prep(var5, var4 + 8);
      if (var2.length() != 4) {
         throw new AssertionError("FlatBuffers: file identifier must be length 4");
      } else {
         for(var4 = 3; var4 >= 0; --var4) {
            this.addByte((byte)var2.charAt(var4));
         }

         this.finish(var1, var3);
      }
   }

   protected void finish(int var1, boolean var2) {
      int var4 = this.minalign;
      byte var3;
      if (var2) {
         var3 = 4;
      } else {
         var3 = 0;
      }

      this.prep(var4, var3 + 4);
      this.addOffset(var1);
      if (var2) {
         this.addInt(this.bb.capacity() - this.space);
      }

      this.bb.position(this.space);
      this.finished = true;
   }

   public void finishSizePrefixed(int var1) {
      this.finish(var1, true);
   }

   public void finishSizePrefixed(int var1, String var2) {
      this.finish(var1, var2, true);
   }

   public void finished() {
      if (!this.finished) {
         throw new AssertionError("FlatBuffers: you can only access the serialized buffer after it has been finished by FlatBufferBuilder.finish().");
      }
   }

   public FlatBufferBuilder forceDefaults(boolean var1) {
      this.force_defaults = var1;
      return this;
   }

   public FlatBufferBuilder init(ByteBuffer var1, ByteBufferFactory var2) {
      this.bb_factory = var2;
      this.bb = var1;
      var1.clear();
      this.bb.order(ByteOrder.LITTLE_ENDIAN);
      this.minalign = 1;
      this.space = this.bb.capacity();
      this.vtable_in_use = 0;
      this.nested = false;
      this.finished = false;
      this.object_start = 0;
      this.num_vtables = 0;
      this.vector_num_elems = 0;
      return this;
   }

   public void notNested() {
      if (this.nested) {
         throw new AssertionError("FlatBuffers: object serialization must not be nested.");
      }
   }

   public int offset() {
      return this.bb.capacity() - this.space;
   }

   public void pad(int var1) {
      for(int var2 = 0; var2 < var1; ++var2) {
         ByteBuffer var4 = this.bb;
         int var3 = this.space - 1;
         this.space = var3;
         var4.put(var3, (byte)0);
      }

   }

   public void prep(int var1, int var2) {
      if (var1 > this.minalign) {
         this.minalign = var1;
      }

      int var3;
      int var4;
      for(var3 = ~(this.bb.capacity() - this.space + var2) + 1 & var1 - 1; this.space < var3 + var1 + var2; this.space += this.bb.capacity() - var4) {
         var4 = this.bb.capacity();
         ByteBuffer var6 = this.bb;
         ByteBuffer var5 = growByteBuffer(var6, this.bb_factory);
         this.bb = var5;
         if (var6 != var5) {
            this.bb_factory.releaseByteBuffer(var6);
         }
      }

      this.pad(var3);
   }

   public void putBoolean(boolean var1) {
      ByteBuffer var3 = this.bb;
      int var2 = this.space - 1;
      this.space = var2;
      var3.put(var2, (byte)var1);
   }

   public void putByte(byte var1) {
      ByteBuffer var3 = this.bb;
      int var2 = this.space - 1;
      this.space = var2;
      var3.put(var2, var1);
   }

   public void putDouble(double var1) {
      ByteBuffer var4 = this.bb;
      int var3 = this.space - 8;
      this.space = var3;
      var4.putDouble(var3, var1);
   }

   public void putFloat(float var1) {
      ByteBuffer var3 = this.bb;
      int var2 = this.space - 4;
      this.space = var2;
      var3.putFloat(var2, var1);
   }

   public void putInt(int var1) {
      ByteBuffer var3 = this.bb;
      int var2 = this.space - 4;
      this.space = var2;
      var3.putInt(var2, var1);
   }

   public void putLong(long var1) {
      ByteBuffer var4 = this.bb;
      int var3 = this.space - 8;
      this.space = var3;
      var4.putLong(var3, var1);
   }

   public void putShort(short var1) {
      ByteBuffer var3 = this.bb;
      int var2 = this.space - 2;
      this.space = var2;
      var3.putShort(var2, var1);
   }

   public void required(int var1, int var2) {
      var1 = this.bb.capacity() - var1;
      int var3 = this.bb.getInt(var1);
      boolean var4;
      if (this.bb.getShort(var1 - var3 + var2) != 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw new AssertionError("FlatBuffers: field " + var2 + " must be set");
      }
   }

   public byte[] sizedByteArray() {
      return this.sizedByteArray(this.space, this.bb.capacity() - this.space);
   }

   public byte[] sizedByteArray(int var1, int var2) {
      this.finished();
      byte[] var3 = new byte[var2];
      this.bb.position(var1);
      this.bb.get(var3);
      return var3;
   }

   public InputStream sizedInputStream() {
      this.finished();
      ByteBuffer var1 = this.bb.duplicate();
      var1.position(this.space);
      var1.limit(this.bb.capacity());
      return new ByteBufferBackedInputStream(var1);
   }

   public void slot(int var1) {
      this.vtable[var1] = this.offset();
   }

   public void startTable(int var1) {
      this.notNested();
      int[] var2 = this.vtable;
      if (var2 == null || var2.length < var1) {
         this.vtable = new int[var1];
      }

      this.vtable_in_use = var1;
      Arrays.fill(this.vtable, 0, var1, 0);
      this.nested = true;
      this.object_start = this.offset();
   }

   public void startVector(int var1, int var2, int var3) {
      this.notNested();
      this.vector_num_elems = var2;
      var1 *= var2;
      this.prep(4, var1);
      this.prep(var3, var1);
      this.nested = true;
   }

   static class ByteBufferBackedInputStream extends InputStream {
      ByteBuffer buf;

      public ByteBufferBackedInputStream(ByteBuffer var1) {
         this.buf = var1;
      }

      public int read() throws IOException {
         byte var1;
         try {
            var1 = this.buf.get();
         } catch (BufferUnderflowException var3) {
            return -1;
         }

         return var1 & 255;
      }
   }

   public abstract static class ByteBufferFactory {
      public abstract ByteBuffer newByteBuffer(int var1);

      public void releaseByteBuffer(ByteBuffer var1) {
      }
   }

   public static final class HeapByteBufferFactory extends ByteBufferFactory {
      public static final HeapByteBufferFactory INSTANCE = new HeapByteBufferFactory();

      public ByteBuffer newByteBuffer(int var1) {
         return ByteBuffer.allocate(var1).order(ByteOrder.LITTLE_ENDIAN);
      }
   }
}
