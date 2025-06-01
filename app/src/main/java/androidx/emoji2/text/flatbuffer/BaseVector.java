package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public class BaseVector {
   protected ByteBuffer bb;
   private int element_size;
   private int length;
   private int vector;

   protected int __element(int var1) {
      return this.vector + var1 * this.element_size;
   }

   protected void __reset(int var1, int var2, ByteBuffer var3) {
      this.bb = var3;
      if (var3 != null) {
         this.vector = var1;
         this.length = var3.getInt(var1 - 4);
         this.element_size = var2;
      } else {
         this.vector = 0;
         this.length = 0;
         this.element_size = 0;
      }

   }

   protected int __vector() {
      return this.vector;
   }

   public int length() {
      return this.length;
   }

   public void reset() {
      this.__reset(0, 0, (ByteBuffer)null);
   }
}
