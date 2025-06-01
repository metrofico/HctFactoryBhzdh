package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class StringVector extends BaseVector {
   private Utf8 utf8 = Utf8.getDefault();

   public StringVector __assign(int var1, int var2, ByteBuffer var3) {
      this.__reset(var1, var2, var3);
      return this;
   }

   public String get(int var1) {
      return Table.__string(this.__element(var1), this.bb, this.utf8);
   }
}
