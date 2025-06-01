package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B+\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007¢\u0006\u0002\u0010\bJ\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0096\u0002R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
   d2 = {"Lkotlin/sequences/GeneratorSequence;", "T", "", "Lkotlin/sequences/Sequence;", "getInitialValue", "Lkotlin/Function0;", "getNextValue", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class GeneratorSequence implements Sequence {
   private final Function0 getInitialValue;
   private final Function1 getNextValue;

   public GeneratorSequence(Function0 var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var1, "getInitialValue");
      Intrinsics.checkNotNullParameter(var2, "getNextValue");
      super();
      this.getInitialValue = var1;
      this.getNextValue = var2;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private Object nextItem;
         private int nextState;
         final GeneratorSequence this$0;

         {
            this.this$0 = var1;
            this.nextState = -2;
         }

         private final void calcNext() {
            Object var2;
            if (this.nextState == -2) {
               var2 = this.this$0.getInitialValue.invoke();
            } else {
               Function1 var4 = this.this$0.getNextValue;
               Object var3 = this.nextItem;
               Intrinsics.checkNotNull(var3);
               var2 = var4.invoke(var3);
            }

            this.nextItem = var2;
            byte var1;
            if (var2 == null) {
               var1 = 0;
            } else {
               var1 = 1;
            }

            this.nextState = var1;
         }

         public final Object getNextItem() {
            return this.nextItem;
         }

         public final int getNextState() {
            return this.nextState;
         }

         public boolean hasNext() {
            if (this.nextState < 0) {
               this.calcNext();
            }

            int var1 = this.nextState;
            boolean var2 = true;
            if (var1 != 1) {
               var2 = false;
            }

            return var2;
         }

         public Object next() {
            if (this.nextState < 0) {
               this.calcNext();
            }

            if (this.nextState != 0) {
               Object var1 = this.nextItem;
               Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
               this.nextState = -1;
               return var1;
            } else {
               throw new NoSuchElementException();
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public final void setNextItem(Object var1) {
            this.nextItem = var1;
         }

         public final void setNextState(int var1) {
            this.nextState = var1;
         }
      });
   }
}
