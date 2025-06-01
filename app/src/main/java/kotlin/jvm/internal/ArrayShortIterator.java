package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.ShortIterator;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\n\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"},
   d2 = {"Lkotlin/jvm/internal/ArrayShortIterator;", "Lkotlin/collections/ShortIterator;", "array", "", "([S)V", "index", "", "hasNext", "", "nextShort", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class ArrayShortIterator extends ShortIterator {
   private final short[] array;
   private int index;

   public ArrayShortIterator(short[] var1) {
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

   public short nextShort() {
      int var2;
      short[] var3;
      try {
         var3 = this.array;
         var2 = this.index++;
      } catch (ArrayIndexOutOfBoundsException var4) {
         --this.index;
         throw new NoSuchElementException(var4.getMessage());
      }

      short var1 = var3[var2];
      return var1;
   }
}
