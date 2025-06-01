package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0096\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u0014\u0010\t\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"},
   d2 = {"Lkotlin/sequences/SubSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "startIndex", "", "endIndex", "(Lkotlin/sequences/Sequence;II)V", "count", "getCount", "()I", "drop", "n", "iterator", "", "take", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class SubSequence implements Sequence, DropTakeSequence {
   private final int endIndex;
   private final Sequence sequence;
   private final int startIndex;

   public SubSequence(Sequence var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var1, "sequence");
      super();
      this.sequence = var1;
      this.startIndex = var2;
      this.endIndex = var3;
      boolean var5 = true;
      boolean var4;
      if (var2 >= 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (var4) {
         if (var3 >= 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (var4) {
            if (var3 >= var2) {
               var4 = var5;
            } else {
               var4 = false;
            }

            if (!var4) {
               throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + var3 + " < " + var2).toString());
            }
         } else {
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + var3).toString());
         }
      } else {
         throw new IllegalArgumentException(("startIndex should be non-negative, but is " + var2).toString());
      }
   }

   private final int getCount() {
      return this.endIndex - this.startIndex;
   }

   public Sequence drop(int var1) {
      Sequence var2;
      if (var1 >= this.getCount()) {
         var2 = SequencesKt.emptySequence();
      } else {
         var2 = (Sequence)(new SubSequence(this.sequence, this.startIndex + var1, this.endIndex));
      }

      return var2;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private final Iterator iterator;
         private int position;
         final SubSequence this$0;

         {
            this.this$0 = var1;
            this.iterator = var1.sequence.iterator();
         }

         private final void drop() {
            while(this.position < this.this$0.startIndex && this.iterator.hasNext()) {
               this.iterator.next();
               ++this.position;
            }

         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public final int getPosition() {
            return this.position;
         }

         public boolean hasNext() {
            this.drop();
            boolean var1;
            if (this.position < this.this$0.endIndex && this.iterator.hasNext()) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public Object next() {
            this.drop();
            if (this.position < this.this$0.endIndex) {
               ++this.position;
               return this.iterator.next();
            } else {
               throw new NoSuchElementException();
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public final void setPosition(int var1) {
            this.position = var1;
         }
      });
   }

   public Sequence take(int var1) {
      Sequence var3;
      if (var1 >= this.getCount()) {
         var3 = (Sequence)this;
      } else {
         var3 = this.sequence;
         int var2 = this.startIndex;
         var3 = (Sequence)(new SubSequence(var3, var2, var1 + var2));
      }

      return var3;
   }
}
