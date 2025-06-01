package androidx.emoji2.text;

import android.content.res.AssetManager;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class MetadataListReader {
   private static final int EMJI_TAG = 1164798569;
   private static final int EMJI_TAG_DEPRECATED = 1701669481;
   private static final int META_TABLE_NAME = 1835365473;

   private MetadataListReader() {
   }

   private static OffsetInfo findOffsetInfo(OpenTypeReader var0) throws IOException {
      var0.skip(4);
      int var3 = var0.readUnsignedShort();
      if (var3 > 100) {
         throw new IOException("Cannot read metadata.");
      } else {
         var0.skip(6);
         int var2 = 0;
         int var1 = 0;

         long var5;
         while(true) {
            if (var1 >= var3) {
               var5 = -1L;
               break;
            }

            int var4 = var0.readTag();
            var0.skip(4);
            var5 = var0.readUnsignedInt();
            var0.skip(4);
            if (1835365473 == var4) {
               break;
            }

            ++var1;
         }

         if (var5 != -1L) {
            var0.skip((int)(var5 - var0.getPosition()));
            var0.skip(12);
            long var9 = var0.readUnsignedInt();

            for(var1 = var2; (long)var1 < var9; ++var1) {
               var2 = var0.readTag();
               long var11 = var0.readUnsignedInt();
               long var7 = var0.readUnsignedInt();
               if (1164798569 == var2 || 1701669481 == var2) {
                  return new OffsetInfo(var11 + var5, var7);
               }
            }
         }

         throw new IOException("Cannot read metadata.");
      }
   }

   static MetadataList read(AssetManager var0, String var1) throws IOException {
      InputStream var9 = var0.open(var1);

      MetadataList var8;
      try {
         var8 = read(var9);
      } catch (Throwable var7) {
         if (var9 != null) {
            try {
               var9.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
               throw var7;
            }
         }

         throw var7;
      }

      if (var9 != null) {
         var9.close();
      }

      return var8;
   }

   static MetadataList read(InputStream var0) throws IOException {
      InputStreamOpenTypeReader var3 = new InputStreamOpenTypeReader(var0);
      OffsetInfo var2 = findOffsetInfo(var3);
      var3.skip((int)(var2.getStartOffset() - var3.getPosition()));
      ByteBuffer var4 = ByteBuffer.allocate((int)var2.getLength());
      int var1 = var0.read(var4.array());
      if ((long)var1 == var2.getLength()) {
         return MetadataList.getRootAsMetadataList(var4);
      } else {
         throw new IOException("Needed " + var2.getLength() + " bytes, got " + var1);
      }
   }

   static MetadataList read(ByteBuffer var0) throws IOException {
      var0 = var0.duplicate();
      var0.position((int)findOffsetInfo(new ByteBufferReader(var0)).getStartOffset());
      return MetadataList.getRootAsMetadataList(var0);
   }

   static long toUnsignedInt(int var0) {
      return (long)var0 & 4294967295L;
   }

   static int toUnsignedShort(short var0) {
      return var0 & '\uffff';
   }

   private static class ByteBufferReader implements OpenTypeReader {
      private final ByteBuffer mByteBuffer;

      ByteBufferReader(ByteBuffer var1) {
         this.mByteBuffer = var1;
         var1.order(ByteOrder.BIG_ENDIAN);
      }

      public long getPosition() {
         return (long)this.mByteBuffer.position();
      }

      public int readTag() throws IOException {
         return this.mByteBuffer.getInt();
      }

      public long readUnsignedInt() throws IOException {
         return MetadataListReader.toUnsignedInt(this.mByteBuffer.getInt());
      }

      public int readUnsignedShort() throws IOException {
         return MetadataListReader.toUnsignedShort(this.mByteBuffer.getShort());
      }

      public void skip(int var1) throws IOException {
         ByteBuffer var2 = this.mByteBuffer;
         var2.position(var2.position() + var1);
      }
   }

   private static class InputStreamOpenTypeReader implements OpenTypeReader {
      private final byte[] mByteArray;
      private final ByteBuffer mByteBuffer;
      private final InputStream mInputStream;
      private long mPosition = 0L;

      InputStreamOpenTypeReader(InputStream var1) {
         this.mInputStream = var1;
         byte[] var2 = new byte[4];
         this.mByteArray = var2;
         ByteBuffer var3 = ByteBuffer.wrap(var2);
         this.mByteBuffer = var3;
         var3.order(ByteOrder.BIG_ENDIAN);
      }

      private void read(int var1) throws IOException {
         if (this.mInputStream.read(this.mByteArray, 0, var1) == var1) {
            this.mPosition += (long)var1;
         } else {
            throw new IOException("read failed");
         }
      }

      public long getPosition() {
         return this.mPosition;
      }

      public int readTag() throws IOException {
         this.mByteBuffer.position(0);
         this.read(4);
         return this.mByteBuffer.getInt();
      }

      public long readUnsignedInt() throws IOException {
         this.mByteBuffer.position(0);
         this.read(4);
         return MetadataListReader.toUnsignedInt(this.mByteBuffer.getInt());
      }

      public int readUnsignedShort() throws IOException {
         this.mByteBuffer.position(0);
         this.read(2);
         return MetadataListReader.toUnsignedShort(this.mByteBuffer.getShort());
      }

      public void skip(int var1) throws IOException {
         while(true) {
            if (var1 > 0) {
               int var2 = (int)this.mInputStream.skip((long)var1);
               if (var2 >= 1) {
                  var1 -= var2;
                  this.mPosition += (long)var2;
                  continue;
               }

               throw new IOException("Skip didn't move at least 1 byte forward");
            }

            return;
         }
      }
   }

   private static class OffsetInfo {
      private final long mLength;
      private final long mStartOffset;

      OffsetInfo(long var1, long var3) {
         this.mStartOffset = var1;
         this.mLength = var3;
      }

      long getLength() {
         return this.mLength;
      }

      long getStartOffset() {
         return this.mStartOffset;
      }
   }

   private interface OpenTypeReader {
      int UINT16_BYTE_COUNT = 2;
      int UINT32_BYTE_COUNT = 4;

      long getPosition();

      int readTag() throws IOException;

      long readUnsignedInt() throws IOException;

      int readUnsignedShort() throws IOException;

      void skip(int var1) throws IOException;
   }
}
