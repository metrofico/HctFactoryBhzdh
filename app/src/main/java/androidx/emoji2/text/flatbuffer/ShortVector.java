package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public final class ShortVector extends BaseVector {
   public ShortVector __assign(int var1, ByteBuffer var2) {
      this.__reset(var1, 2, var2);
      return this;
   }

   public short get(int var1) {
      return this.bb.getShort(this.__element(var1));
   }

   public int getAsUnsigned(int var1) {
      return this.get(var1) & '\uffff';
   }
}
