package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class UnionVector extends BaseVector {
   public UnionVector __assign(int var1, int var2, ByteBuffer var3) {
      this.__reset(var1, var2, var3);
      return this;
   }

   public Table get(Table var1, int var2) {
      return Table.__union(var1, this.__element(var2), this.bb);
   }
}
