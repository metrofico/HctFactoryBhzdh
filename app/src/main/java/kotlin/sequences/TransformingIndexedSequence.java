package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00020\u0003B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\nH\u0096\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
   d2 = {"Lkotlin/sequences/TransformingIndexedSequence;", "T", "R", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function2;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class TransformingIndexedSequence implements Sequence {
   private final Sequence sequence;
   private final Function2 transformer;

   public TransformingIndexedSequence(Sequence var1, Function2 var2) {
      Intrinsics.checkNotNullParameter(var1, "sequence");
      Intrinsics.checkNotNullParameter(var2, "transformer");
      super();
      this.sequence = var1;
      this.transformer = var2;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private int index;
         private final Iterator iterator;
         final TransformingIndexedSequence this$0;

         {
            this.this$0 = var1;
            this.iterator = var1.sequence.iterator();
         }

         public final int getIndex() {
            return this.index;
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public boolean hasNext() {
            return this.iterator.hasNext();
         }

         public Object next() {
            Function2 var2 = this.this$0.transformer;
            int var1 = this.index++;
            if (var1 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            return var2.invoke(var1, this.iterator.next());
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public final void setIndex(int var1) {
            this.index = var1;
         }
      });
   }
}
