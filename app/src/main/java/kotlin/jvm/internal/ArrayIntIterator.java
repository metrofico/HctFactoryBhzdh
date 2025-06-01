package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"},
   d2 = {"Lkotlin/jvm/internal/ArrayIntIterator;", "Lkotlin/collections/IntIterator;", "array", "", "([I)V", "index", "", "hasNext", "", "nextInt", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class ArrayIntIterator extends IntIterator {
   private final int[] array;
   private int index;

   public ArrayIntIterator(int[] var1) {
      Intrinsics.checkNotNullParameter(var1, "array");
      super();
      this.array = var1;
   }

   public boolean hasNext() {
      boolean var1;
      if (this.index < this.array.length) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public int nextInt() {
      int var1;
      int[] var2;
      try {
         var2 = this.array;
         var1 = this.index++;
      } catch (ArrayIndexOutOfBoundsException var3) {
         --this.index;
         throw new NoSuchElementException(var3.getMessage());
      }

      var1 = var2[var1];
      return var1;
   }
}
