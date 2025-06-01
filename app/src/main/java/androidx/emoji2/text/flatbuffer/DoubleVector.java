package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class DoubleVector extends BaseVector {
   public DoubleVector __assign(int var1, ByteBuffer var2) {
      this.__reset(var1, 8, var2);
      return this;
   }

   public double get(int var1) {
      return this.bb.getDouble(this.__element(var1));
   }
}
