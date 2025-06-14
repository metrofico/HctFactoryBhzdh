package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0013\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\u000eJ\b\u0010\u0012\u001a\u00020\u000eH\u0002J\r\u0010\u0013\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0014R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"},
   d2 = {"Lkotlinx/coroutines/internal/ArrayQueue;", "T", "", "()V", "elements", "", "[Ljava/lang/Object;", "head", "", "isEmpty", "", "()Z", "tail", "addLast", "", "element", "(Ljava/lang/Object;)V", "clear", "ensureCapacity", "removeFirstOrNull", "()Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public class ArrayQueue {
   private Object[] elements = new Object[16];
   private int head;
   private int tail;

   private final void ensureCapacity() {
      Object[] var5 = this.elements;
      int var3 = var5.length;
      Object[] var4 = new Object[var3 << 1];
      ArraysKt.copyInto$default(var5, var4, 0, this.head, 0, 10, (Object)null);
      var5 = this.elements;
      int var2 = var5.length;
      int var1 = this.head;
      ArraysKt.copyInto$default(var5, var4, var2 - var1, 0, var1, 4, (Object)null);
      this.elements = var4;
      this.head = 0;
      this.tail = var3;
   }

   public final void addLast(Object var1) {
      Object[] var3 = this.elements;
      int var2 = this.tail;
      var3[var2] = var1;
      var2 = var3.length - 1 & var2 + 1;
      this.tail = var2;
      if (var2 == this.head) {
         this.ensureCapacity();
      }

   }

   public final void clear() {
      this.head = 0;
      this.tail = 0;
      this.elements = new Object[this.elements.length];
   }

   public final boolean isEmpty() {
      boolean var1;
      if (this.head == this.tail) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final Object removeFirstOrNull() {
      int var1 = this.head;
      if (var1 == this.tail) {
         return null;
      } else {
         Object[] var3 = this.elements;
         Object var2 = var3[var1];
         var3[var1] = null;
         this.head = var1 + 1 & var3.length - 1;
         if (var2 != null) {
            return var2;
         } else {
            throw new NullPointerException("null cannot be cast to non-null type T");
         }
      }
   }
}
