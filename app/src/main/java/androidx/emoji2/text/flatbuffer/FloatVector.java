package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class FloatVector extends BaseVector {
   public FloatVector __assign(int var1, ByteBuffer var2) {
      this.__reset(var1, 4, var2);
      return this;
   }

   public float get(int var1) {
      return this.bb.getFloat(this.__element(var1));
   }
}
