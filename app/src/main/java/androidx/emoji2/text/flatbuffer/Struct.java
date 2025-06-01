package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public class Struct {
   protected ByteBuffer bb;
   protected int bb_pos;

   public void __reset() {
      this.__reset(0, (ByteBuffer)null);
   }

   protected void __reset(int var1, ByteBuffer var2) {
      this.bb = var2;
      if (var2 != null) {
         this.bb_pos = var1;
      } else {
         this.bb_pos = 0;
      }

   }
}
