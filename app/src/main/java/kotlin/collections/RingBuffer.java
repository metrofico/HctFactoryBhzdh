package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001d\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010\fJ\u0013\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0018\u001a\u00020\u0006J\u0016\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0096\u0002J\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u0006J\u0015\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tH\u0014¢\u0006\u0002\u0010#J'\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0004\b\u0001\u0010\u00012\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH\u0014¢\u0006\u0002\u0010%J\u0015\u0010&\u001a\u00020\u0006*\u00020\u00062\u0006\u0010!\u001a\u00020\u0006H\u0082\bR\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"},
   d2 = {"Lkotlin/collections/RingBuffer;", "T", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "capacity", "", "(I)V", "buffer", "", "", "filledSize", "([Ljava/lang/Object;I)V", "[Ljava/lang/Object;", "<set-?>", "size", "getSize", "()I", "startIndex", "add", "", "element", "(Ljava/lang/Object;)V", "expanded", "maxCapacity", "get", "index", "(I)Ljava/lang/Object;", "isFull", "", "iterator", "", "removeFirst", "n", "toArray", "()[Ljava/lang/Object;", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "forward", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class RingBuffer extends AbstractList implements RandomAccess {
   private final Object[] buffer;
   private final int capacity;
   private int size;
   private int startIndex;

   public RingBuffer(int var1) {
      this(new Object[var1], 0);
   }

   public RingBuffer(Object[] var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "buffer");
      super();
      this.buffer = var1;
      boolean var4 = true;
      boolean var3;
      if (var2 >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (var3) {
         if (var2 <= var1.length) {
            var3 = var4;
         } else {
            var3 = false;
         }

         if (var3) {
            this.capacity = var1.length;
            this.size = var2;
         } else {
            throw new IllegalArgumentException(("ring buffer filled size: " + var2 + " cannot be larger than the buffer size: " + var1.length).toString());
         }
      } else {
         throw new IllegalArgumentException(("ring buffer filled size should not be negative but it is " + var2).toString());
      }
   }

   private final int forward(int var1, int var2) {
      return (var1 + var2) % access$getCapacity$p(this);
   }

   public final void add(Object var1) {
      if (!this.isFull()) {
         this.buffer[(this.startIndex + this.size()) % access$getCapacity$p(this)] = var1;
         this.size = this.size() + 1;
      } else {
         throw new IllegalStateException("ring buffer is full");
      }
   }

   public final RingBuffer expanded(int var1) {
      int var2 = this.capacity;
      var1 = RangesKt.coerceAtMost(var2 + (var2 >> 1) + 1, var1);
      Object[] var3;
      if (this.startIndex == 0) {
         var3 = Arrays.copyOf(this.buffer, var1);
         Intrinsics.checkNotNullExpressionValue(var3, "copyOf(this, newSize)");
      } else {
         var3 = this.toArray(new Object[var1]);
      }

      return new RingBuffer(var3, this.size());
   }

   public Object get(int var1) {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(var1, this.size());
      return this.buffer[(this.startIndex + var1) % access$getCapacity$p(this)];
   }

   public int getSize() {
      return this.size;
   }

   public final boolean isFull() {
      boolean var1;
      if (this.size() == this.capacity) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Iterator iterator() {
      return (Iterator)(new AbstractIterator(this) {
         private int count;
         private int index;
         final RingBuffer this$0;

         {
            this.this$0 = var1;
            this.count = var1.size();
            this.index = var1.startIndex;
         }

         protected void computeNext() {
            if (this.count == 0) {
               this.done();
            } else {
               this.setNext(this.this$0.buffer[this.index]);
               RingBuffer var1 = this.this$0;
               this.index = (this.index + 1) % var1.capacity;
               --this.count;
            }

         }
      });
   }

   public final void removeFirst(int var1) {
      boolean var3 = true;
      boolean var2;
      if (var1 >= 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         if (var1 <= this.size()) {
            var2 = var3;
         } else {
            var2 = false;
         }

         if (var2) {
            if (var1 > 0) {
               int var5 = this.startIndex;
               int var4 = (var5 + var1) % access$getCapacity$p(this);
               if (var5 > var4) {
                  ArraysKt.fill(this.buffer, (Object)null, var5, this.capacity);
                  ArraysKt.fill(this.buffer, (Object)null, 0, var4);
               } else {
                  ArraysKt.fill(this.buffer, (Object)null, var5, var4);
               }

               this.startIndex = var4;
               this.size = this.size() - var1;
            }

         } else {
            throw new IllegalArgumentException(("n shouldn't be greater than the buffer size: n = " + var1 + ", size = " + this.size()).toString());
         }
      } else {
         throw new IllegalArgumentException(("n shouldn't be negative but it is " + var1).toString());
      }
   }

   public Object[] toArray() {
      return this.toArray(new Object[this.size()]);
   }

   public Object[] toArray(Object[] var1) {
      Intrinsics.checkNotNullParameter(var1, "array");
      Object[] var8 = var1;
      if (var1.length < this.size()) {
         var8 = Arrays.copyOf(var1, this.size());
         Intrinsics.checkNotNullExpressionValue(var8, "copyOf(this, newSize)");
      }

      int var7 = this.size();
      int var3 = this.startIndex;
      byte var6 = 0;
      int var2 = 0;

      int var4;
      int var5;
      while(true) {
         var4 = var6;
         var5 = var2;
         if (var2 >= var7) {
            break;
         }

         var4 = var6;
         var5 = var2;
         if (var3 >= this.capacity) {
            break;
         }

         var8[var2] = this.buffer[var3];
         ++var2;
         ++var3;
      }

      while(var5 < var7) {
         var8[var5] = this.buffer[var4];
         ++var5;
         ++var4;
      }

      if (var8.length > this.size()) {
         var8[this.size()] = null;
      }

      Intrinsics.checkNotNull(var8, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.RingBuffer.toArray>");
      return var8;
   }
}
