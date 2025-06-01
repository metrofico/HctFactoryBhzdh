package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class LongVector extends BaseVector {
   public LongVector __assign(int var1, ByteBuffer var2) {
      this.__reset(var1, 8, var2);
      return this;
   }

   public long get(int var1) {
      return this.bb.getLong(this.__element(var1));
   }
}
