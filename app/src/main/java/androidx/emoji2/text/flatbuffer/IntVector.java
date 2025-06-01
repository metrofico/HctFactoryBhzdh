package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class IntVector extends BaseVector {
   public IntVector __assign(int var1, ByteBuffer var2) {
      this.__reset(var1, 4, var2);
      return this;
   }

   public int get(int var1) {
      return this.bb.getInt(this.__element(var1));
   }

   public long getAsUnsigned(int var1) {
      return (long)this.get(var1) & 4294967295L;
   }
}
