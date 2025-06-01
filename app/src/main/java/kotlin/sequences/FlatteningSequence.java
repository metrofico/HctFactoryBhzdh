package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\b\u0012\u0004\u0012\u0002H\u00030\u0004BA\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\tH\u0096\u0002R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
   d2 = {"Lkotlin/sequences/FlatteningSequence;", "T", "R", "E", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function1;", "iterator", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class FlatteningSequence implements Sequence {
   private final Function1 iterator;
   private final Sequence sequence;
   private final Function1 transformer;

   public FlatteningSequence(Sequence var1, Function1 var2, Function1 var3) {
      Intrinsics.checkNotNullParameter(var1, "sequence");
      Intrinsics.checkNotNullParameter(var2, "transformer");
      Intrinsics.checkNotNullParameter(var3, "iterator");
      super();
      this.sequence = var1;
      this.transformer = var2;
      this.iterator = var3;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private Iterator itemIterator;
         private final Iterator iterator;
         final FlatteningSequence this$0;

         {
            this.this$0 = var1;
            this.iterator = var1.sequence.iterator();
         }

         private final boolean ensureItemIterator() {
            Iterator var2 = this.itemIterator;
            boolean var1;
            if (var2 != null && !var2.hasNext()) {
               var1 = true;
            } else {
               var1 = false;
            }

            if (var1) {
               this.itemIterator = null;
            }

            while(this.itemIterator == null) {
               if (!this.iterator.hasNext()) {
                  return false;
               }

               Object var3 = this.iterator.next();
               var2 = (Iterator)this.this$0.iterator.invoke(this.this$0.transformer.invoke(var3));
               if (var2.hasNext()) {
                  this.itemIterator = var2;
                  break;
               }
            }

            return true;
         }

         public final Iterator getItemIterator() {
            return this.itemIterator;
         }

         public final Iterator getIterator() {
            return this.iterator;
         }

         public boolean hasNext() {
            return this.ensureItemIterator();
         }

         public Object next() {
            if (this.ensureItemIterator()) {
               Iterator var1 = this.itemIterator;
               Intrinsics.checkNotNull(var1);
               return var1.next();
            } else {
               throw new NoSuchElementException();
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public final void setItemIterator(Iterator var1) {
            this.itemIterator = var1;
         }
      });
   }
}
