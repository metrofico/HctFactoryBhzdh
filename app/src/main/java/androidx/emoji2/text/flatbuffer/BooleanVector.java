package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class BooleanVector extends BaseVector {
   public BooleanVector __assign(int var1, ByteBuffer var2) {
      this.__reset(var1, 1, var2);
      return this;
   }

   public boolean get(int var1) {
      boolean var2;
      if (this.bb.get(this.__element(var1)) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }
}
