package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class MetadataList extends Table {
   public static void ValidateVersion() {
      Constants.FLATBUFFERS_1_12_0();
   }

   public static void addList(FlatBufferBuilder var0, int var1) {
      var0.addOffset(1, var1, 0);
   }

   public static void addSourceSha(FlatBufferBuilder var0, int var1) {
      var0.addOffset(2, var1, 0);
   }

   public static void addVersion(FlatBufferBuilder var0, int var1) {
      var0.addInt(0, var1, 0);
   }

   public static int createListVector(FlatBufferBuilder var0, int[] var1) {
      var0.startVector(4, var1.length, 4);

      for(int var2 = var1.length - 1; var2 >= 0; --var2) {
         var0.addOffset(var1[var2]);
      }

      return var0.endVector();
   }

   public static int createMetadataList(FlatBufferBuilder var0, int var1, int var2, int var3) {
      var0.startTable(3);
      addSourceSha(var0, var3);
      addList(var0, var2);
      addVersion(var0, var1);
      return endMetadataList(var0);
   }

   public static int endMetadataList(FlatBufferBuilder var0) {
      return var0.endTable();
   }

   public static void finishMetadataListBuffer(FlatBufferBuilder var0, int var1) {
      var0.finish(var1);
   }

   public static void finishSizePrefixedMetadataListBuffer(FlatBufferBuilder var0, int var1) {
      var0.finishSizePrefixed(var1);
   }

   public static MetadataList getRootAsMetadataList(ByteBuffer var0) {
      return getRootAsMetadataList(var0, new MetadataList());
   }

   public static MetadataList getRootAsMetadataList(ByteBuffer var0, MetadataList var1) {
      var0.order(ByteOrder.LITTLE_ENDIAN);
      return var1.__assign(var0.getInt(var0.position()) + var0.position(), var0);
   }

   public static void startListVector(FlatBufferBuilder var0, int var1) {
      var0.startVector(4, var1, 4);
   }

   public static void startMetadataList(FlatBufferBuilder var0) {
      var0.startTable(3);
   }

   public MetadataList __assign(int var1, ByteBuffer var2) {
      this.__init(var1, var2);
      return this;
   }

   public void __init(int var1, ByteBuffer var2) {
      this.__reset(var1, var2);
   }

   public MetadataItem list(int var1) {
      return this.list(new MetadataItem(), var1);
   }

   public MetadataItem list(MetadataItem var1, int var2) {
      int var3 = this.__offset(6);
      if (var3 != 0) {
         var1 = var1.__assign(this.__indirect(this.__vector(var3) + var2 * 4), this.bb);
      } else {
         var1 = null;
      }

      return var1;
   }

   public int listLength() {
      int var1 = this.__offset(6);
      if (var1 != 0) {
         var1 = this.__vector_len(var1);
      } else {
         var1 = 0;
      }

      return var1;
   }

   public MetadataItem.Vector listVector() {
      return this.listVector(new MetadataItem.Vector());
   }

   public MetadataItem.Vector listVector(MetadataItem.Vector var1) {
      int var2 = this.__offset(6);
      if (var2 != 0) {
         var1 = var1.__assign(this.__vector(var2), 4, this.bb);
      } else {
         var1 = null;
      }

      return var1;
   }

   public String sourceSha() {
      int var1 = this.__offset(8);
      String var2;
      if (var1 != 0) {
         var2 = this.__string(var1 + this.bb_pos);
      } else {
         var2 = null;
      }

      return var2;
   }

   public ByteBuffer sourceShaAsByteBuffer() {
      return this.__vector_as_bytebuffer(8, 1);
   }

   public ByteBuffer sourceShaInByteBuffer(ByteBuffer var1) {
      return this.__vector_in_bytebuffer(var1, 8, 1);
   }

   public int version() {
      int var1 = this.__offset(4);
      if (var1 != 0) {
         var1 = this.bb.getInt(var1 + this.bb_pos);
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

      public MetadataList get(int var1) {
         return this.get(new MetadataList(), var1);
      }

      public MetadataList get(MetadataList var1, int var2) {
         return var1.__assign(MetadataList.__indirect(this.__element(var2), this.bb), this.bb);
      }
   }
}
