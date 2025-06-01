package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH\u0096\u0002J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"},
   d2 = {"Lkotlin/sequences/DropSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "count", "", "(Lkotlin/sequences/Sequence;I)V", "drop", "n", "iterator", "", "take", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class DropSequence implements Sequence, DropTakeSequence {
   private final int count;
   private final Sequence sequence;

   public DropSequence(Sequence var1, int var2) {
      Intrinsics.checkNotNullParameter(var1, "sequence");
      super();
      this.sequence = var1;
      this.count = var2;
      boolean var3;
      if (var2 >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (!var3) {
         throw new IllegalArgumentException(("count must be non-negative, but was " + var2 + '.').toString());
      }
   }

   public Sequence drop(int var1) {
      int var2 = this.count + var1;
      DropSequence var3;
      if (var2 < 0) {
         var3 = new DropSequence((Sequence)this, var1);
      } else {
         var3 = new DropSequence(this.sequence, var2);
      }

      return (Sequence)var3;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private final Iterator iterator;
         private int left;

         {
            this.iterator = var1.sequence.iterator();
            this.left = var1.count;
         }

         private final void drop() {
            while(this.left > 0 && this.iterator.hasNext()) {
               this.iterator.next();
               --this.left;
            }

         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public final int getLeft() {
            return this.left;
         }

         public boolean hasNext() {
            this.drop();
            return this.iterator.hasNext();
         }

         public Object next() {
            this.drop();
            return this.iterator.next();
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public final void setLeft(int var1) {
            this.left = var1;
         }
      });
   }

   public Sequence take(int var1) {
      int var2 = this.count + var1;
      DropTakeSequence var3;
      if (var2 < 0) {
         var3 = (DropTakeSequence)(new TakeSequence((Sequence)this, var1));
      } else {
         var3 = (DropTakeSequence)(new SubSequence(this.sequence, this.count, var2));
      }

      return (Sequence)var3;
   }
}
