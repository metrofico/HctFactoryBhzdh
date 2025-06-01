package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Comparator;

public class Table {
   protected ByteBuffer bb;
   protected int bb_pos;
   Utf8 utf8 = Utf8.getDefault();
   private int vtable_size;
   private int vtable_start;

   protected static boolean __has_identifier(ByteBuffer var0, String var1) {
      if (var1.length() == 4) {
         for(int var2 = 0; var2 < 4; ++var2) {
            if (var1.charAt(var2) != (char)var0.get(var0.position() + 4 + var2)) {
               return false;
            }
         }

         return true;
      } else {
         throw new AssertionError("FlatBuffers: file identifier must be length 4");
      }
   }

   protected static int __indirect(int var0, ByteBuffer var1) {
      return var0 + var1.getInt(var0);
   }

   protected static int __offset(int var0, int var1, ByteBuffer var2) {
      var1 = var2.capacity() - var1;
      return var2.getShort(var0 + var1 - var2.getInt(var1)) + var1;
   }

   protected static String __string(int var0, ByteBuffer var1, Utf8 var2) {
      var0 += var1.getInt(var0);
      return var2.decodeUtf8(var1, var0 + 4, var1.getInt(var0));
   }

   protected static Table __union(Table var0, int var1, ByteBuffer var2) {
      var0.__reset(__indirect(var1, var2), var2);
      return var0;
   }

   protected static int compareStrings(int var0, int var1, ByteBuffer var2) {
      int var3 = var0 + var2.getInt(var0);
      int var4 = var1 + var2.getInt(var1);
      int var6 = var2.getInt(var3);
      var1 = var2.getInt(var4);
      int var5 = Math.min(var6, var1);

      for(var0 = 0; var0 < var5; ++var0) {
         int var8 = var0 + var3 + 4;
         byte var9 = var2.get(var8);
         int var7 = var0 + var4 + 4;
         if (var9 != var2.get(var7)) {
            return var2.get(var8) - var2.get(var7);
         }
      }

      return var6 - var1;
   }

   protected static int compareStrings(int var0, byte[] var1, ByteBuffer var2) {
      int var4 = var0 + var2.getInt(var0);
      int var6 = var2.getInt(var4);
      int var3 = var1.length;
      int var5 = Math.min(var6, var3);

      for(var0 = 0; var0 < var5; ++var0) {
         int var7 = var0 + var4 + 4;
         if (var2.get(var7) != var1[var0]) {
            return var2.get(var7) - var1[var0];
         }
      }

      return var6 - var3;
   }

   protected int __indirect(int var1) {
      return var1 + this.bb.getInt(var1);
   }

   protected int __offset(int var1) {
      short var2;
      if (var1 < this.vtable_size) {
         var2 = this.bb.getShort(this.vtable_start + var1);
      } else {
         var2 = 0;
      }

      return var2;
   }

   public void __reset() {
      this.__reset(0, (ByteBuffer)null);
   }

   protected void __reset(int var1, ByteBuffer var2) {
      this.bb = var2;
      if (var2 != null) {
         this.bb_pos = var1;
         var1 -= var2.getInt(var1);
         this.vtable_start = var1;
         this.vtable_size = this.bb.getShort(var1);
      } else {
         this.bb_pos = 0;
         this.vtable_start = 0;
         this.vtable_size = 0;
      }

   }

   protected String __string(int var1) {
      return __string(var1, this.bb, this.utf8);
   }

   protected Table __union(Table var1, int var2) {
      return __union(var1, var2, this.bb);
   }

   protected int __vector(int var1) {
      var1 += this.bb_pos;
      return var1 + this.bb.getInt(var1) + 4;
   }

   protected ByteBuffer __vector_as_bytebuffer(int var1, int var2) {
      var1 = this.__offset(var1);
      if (var1 == 0) {
         return null;
      } else {
         ByteBuffer var4 = this.bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
         int var3 = this.__vector(var1);
         var4.position(var3);
         var4.limit(var3 + this.__vector_len(var1) * var2);
         return var4;
      }
   }

   protected ByteBuffer __vector_in_bytebuffer(ByteBuffer var1, int var2, int var3) {
      var2 = this.__offset(var2);
      if (var2 == 0) {
         return null;
      } else {
         int var4 = this.__vector(var2);
         var1.rewind();
         var1.limit(this.__vector_len(var2) * var3 + var4);
         var1.position(var4);
         return var1;
      }
   }

   protected int __vector_len(int var1) {
      int var2 = var1 + this.bb_pos;
      var1 = this.bb.getInt(var2);
      return this.bb.getInt(var2 + var1);
   }

   public ByteBuffer getByteBuffer() {
      return this.bb;
   }

   protected int keysCompare(Integer var1, Integer var2, ByteBuffer var3) {
      return 0;
   }

   protected void sortTables(int[] var1, ByteBuffer var2) {
      Integer[] var5 = new Integer[var1.length];
      byte var4 = 0;

      int var3;
      for(var3 = 0; var3 < var1.length; ++var3) {
         var5[var3] = var1[var3];
      }

      Arrays.sort(var5, new Comparator(this, var2) {
         final Table this$0;
         final ByteBuffer val$bb;

         {
            this.this$0 = var1;
            this.val$bb = var2;
         }

         public int compare(Integer var1, Integer var2) {
            return this.this$0.keysCompare(var1, var2, this.val$bb);
         }
      });

      for(var3 = var4; var3 < var1.length; ++var3) {
         var1[var3] = var5[var3];
      }

   }
}
