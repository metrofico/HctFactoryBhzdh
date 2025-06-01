package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004B;\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b¢\u0006\u0002\u0010\tJ\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00020\u000bH\u0096\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"},
   d2 = {"Lkotlin/sequences/MergingSequence;", "T1", "T2", "V", "Lkotlin/sequences/Sequence;", "sequence1", "sequence2", "transform", "Lkotlin/Function2;", "(Lkotlin/sequences/Sequence;Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class MergingSequence implements Sequence {
   private final Sequence sequence1;
   private final Sequence sequence2;
   private final Function2 transform;

   public MergingSequence(Sequence var1, Sequence var2, Function2 var3) {
      Intrinsics.checkNotNullParameter(var1, "sequence1");
      Intrinsics.checkNotNullParameter(var2, "sequence2");
      Intrinsics.checkNotNullParameter(var3, "transform");
      super();
      this.sequence1 = var1;
      this.sequence2 = var2;
      this.transform = var3;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private final Iterator iterator1;
         private final Iterator iterator2;
         final MergingSequence this$0;

         {
            this.this$0 = var1;
            this.iterator1 = var1.sequence1.iterator();
            this.iterator2 = var1.sequence2.iterator();
         }

         public final Iterator getIterator1() {
            return this.iterator1;
         }

         public final Iterator getIterator2() {
            return this.iterator2;
         }

         public boolean hasNext() {
            boolean var1;
            if (this.iterator1.hasNext() && this.iterator2.hasNext()) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public Object next() {
            return this.this$0.transform.invoke(this.iterator1.next(), this.iterator2.next());
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }
      });
   }
}
