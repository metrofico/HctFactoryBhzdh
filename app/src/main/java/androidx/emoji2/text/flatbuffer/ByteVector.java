package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class ByteVector extends BaseVector {
   public ByteVector __assign(int var1, ByteBuffer var2) {
      this.__reset(var1, 1, var2);
      return this;
   }

   public byte get(int var1) {
      return this.bb.get(this.__element(var1));
   }

   public int getAsUnsigned(int var1) {
      return this.get(var1) & 255;
   }
}
