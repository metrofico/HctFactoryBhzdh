package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class MetadataItem extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_1_12_0();
   }

   public static void addCodepoints(FlatBufferBuilder var0, int var1) {
      var0.addOffset(6, var1, 0);
   }

   public static void addCompatAdded(FlatBufferBuilder var0, short var1) {
      var0.addShort(3, var1, 0);
   }

   public static void addEmojiStyle(FlatBufferBuilder var0, boolean var1) {
      var0.addBoolean(1, var1, false);
   }

   public static void addHeight(FlatBufferBuilder var0, short var1) {
      var0.addShort(5, var1, 0);
   }

   public static void addId(FlatBufferBuilder var0, int var1) {
      var0.addInt(0, var1, 0);
   }

   public static void addSdkAdded(FlatBufferBuilder var0, short var1) {
      var0.addShort(2, var1, 0);
   }

   public static void addWidth(FlatBufferBuilder var0, short var1) {
      var0.addShort(4, var1, 0);
   }

   public static int createCodepointsVector(FlatBufferBuilder var0, int[] var1) {
      var0.startVector(4, var1.length, 4);

      for(int var2 = var1.length - 1; var2 >= 0; --var2) {
         var0.addInt(var1[var2]);
      }

      return var0.endVector();
   }

   public static int createMetadataItem(FlatBufferBuilder var0, int var1, boolean var2, short var3, short var4, short var5, short var6, int var7) {
      var0.startTable(7);
      addCodepoints(var0, var7);
      addId(var0, var1);
      addHeight(var0, var6);
      addWidth(var0, var5);
      addCompatAdded(var0, var4);
      addSdkAdded(var0, var3);
      addEmojiStyle(var0, var2);
      return endMetadataItem(var0);
   }

   public static int endMetadataItem(FlatBufferBuilder var0) {
      return var0.endTable();
   }

   public static MetadataItem getRootAsMetadataItem(ByteBuffer var0) {
      return getRootAsMetadataItem(var0, new MetadataItem());
   }

   public static MetadataItem getRootAsMetadataItem(ByteBuffer var0, MetadataItem var1) {
      var0.order(ByteOrder.LITTLE_ENDIAN);
      return var1.__assign(var0.getInt(var0.position()) + var0.position(), var0);
   }

   public static void startCodepointsVector(FlatBufferBuilder var0, int var1) {
      var0.startVector(4, var1, 4);
   }

   public static void startMetadataItem(FlatBufferBuilder var0) {
      var0.startTable(7);
   }

   public MetadataItem __assign(int var1, ByteBuffer var2) {
      this.__init(var1, var2);
      return this;
   }

   public void __init(int var1, ByteBuffer var2) {
      this.__reset(var1, var2);
   }

   public int codepoints(int var1) {
      int var2 = this.__offset(16);
      if (var2 != 0) {
         var1 = this.bb.getInt(this.__vector(var2) + var1 * 4);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public ByteBuffer codepointsAsByteBuffer() {
      return this.__vector_as_bytebuffer(16, 4);
   }

   public ByteBuffer codepointsInByteBuffer(ByteBuffer var1) {
      return this.__vector_in_bytebuffer(var1, 16, 4);
   }

   public int codepointsLength() {
      int var1 = this.__offset(16);
      if (var1 != 0) {
         var1 = this.__vector_len(var1);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public IntVector codepointsVector() {
      return this.codepointsVector(new IntVector());
   }

   public IntVector codepointsVector(IntVector var1) {
      int var2 = this.__offset(16);
      if (var2 != 0) {
         var1 = var1.__assign(this.__vector(var2), this.bb);
      } else {
         var1 = null;
      }

      return var1;
   }

   public short compatAdded() {
      int var2 = this.__offset(10);
      short var1;
      if (var2 != 0) {
         var1 = this.bb.getShort(var2 + this.bb_pos);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public boolean emojiStyle() {
      int var1 = this.__offset(6);
      boolean var3 = false;
      boolean var2 = var3;
      if (var1 != 0) {
         var2 = var3;
         if (this.bb.get(var1 + this.bb_pos) != 0) {
            var2 = true;
         }
      }

      return var2;
   }

   public short height() {
      int var2 = this.__offset(14);
      short var1;
      if (var2 != 0) {
         var1 = this.bb.getShort(var2 + this.bb_pos);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int id() {
      int var1 = this.__offset(4);
      if (var1 != 0) {
         var1 = this.bb.getInt(var1 + this.bb_pos);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public short sdkAdded() {
      int var2 = this.__offset(8);
      short var1;
      if (var2 != 0) {
         var1 = this.bb.getShort(var2 + this.bb_pos);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public short width() {
      int var2 = this.__offset(12);
      short var1;
      if (var2 != 0) {
         var1 = this.bb.getShort(var2 + this.bb_pos);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static final class Vector extends BaseVector {
      public Vector __assign(int var1, int var2, ByteBuffer var3) {
         this.__reset(var1, var2, var3);
         return this;
      }

      public MetadataItem get(int var1) {
         return this.get(new MetadataItem(), var1);
      }

      public MetadataItem get(MetadataItem var1, int var2) {
         return var1.__assign(MetadataItem.__indirect(this.__element(var2), this.bb), this.bb);
      }
   }
}
