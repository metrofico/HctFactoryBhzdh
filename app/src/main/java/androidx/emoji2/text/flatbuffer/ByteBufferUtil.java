package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public class ByteBufferUtil {
   public static int getSizePrefix(ByteBuffer var0) {
      return var0.getInt(var0.position());
   }

   public static ByteBuffer removeSizePrefix(ByteBuffer var0) {
      var0 = var0.duplicate();
      var0.position(var0.position() + 4);
      return var0;
   }
}
