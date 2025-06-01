package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;

@Metadata(
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012:\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0096\u0002RB\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
   d2 = {"Lkotlin/text/DelimitedRangesSequence;", "Lkotlin/sequences/Sequence;", "Lkotlin/ranges/IntRange;", "input", "", "startIndex", "", "limit", "getNextMatch", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "currentIndex", "Lkotlin/Pair;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/CharSequence;IILkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class DelimitedRangesSequence implements Sequence {
   private final Function2 getNextMatch;
   private final CharSequence input;
   private final int limit;
   private final int startIndex;

   public DelimitedRangesSequence(CharSequence var1, int var2, int var3, Function2 var4) {
      Intrinsics.checkNotNullParameter(var1, "input");
      Intrinsics.checkNotNullParameter(var4, "getNextMatch");
      super();
      this.input = var1;
      this.startIndex = var2;
      this.limit = var3;
      this.getNextMatch = var4;
   }

   public Iterator iterator() {
      return (Iterator)(new Iterator(this) {
         private int counter;
         private int currentStartIndex;
         private IntRange nextItem;
         private int nextSearchIndex;
         private int nextState;
         final DelimitedRangesSequence this$0;

         {
            this.this$0 = var1;
            this.nextState = -1;
            int var2 = RangesKt.coerceIn(var1.startIndex, 0, var1.input.length());
            this.currentStartIndex = var2;
            this.nextSearchIndex = var2;
         }

         private final void calcNext() {
            int var2 = this.nextSearchIndex;
            byte var1 = 0;
            if (var2 < 0) {
               this.nextState = 0;
               this.nextItem = null;
            } else {
               label26: {
                  label25: {
                     if (this.this$0.limit > 0) {
                        var2 = this.counter + 1;
                        this.counter = var2;
                        if (var2 >= this.this$0.limit) {
                           break label25;
                        }
                     }

                     if (this.nextSearchIndex <= this.this$0.input.length()) {
                        Pair var4 = (Pair)this.this$0.getNextMatch.invoke(this.this$0.input, this.nextSearchIndex);
                        if (var4 == null) {
                           this.nextItem = new IntRange(this.currentStartIndex, StringsKt.getLastIndex(this.this$0.input));
                           this.nextSearchIndex = -1;
                        } else {
                           var2 = ((Number)var4.component1()).intValue();
                           int var3 = ((Number)var4.component2()).intValue();
                           this.nextItem = RangesKt.until(this.currentStartIndex, var2);
                           var2 += var3;
                           this.currentStartIndex = var2;
                           if (var3 == 0) {
                              var1 = 1;
                           }

                           this.nextSearchIndex = var2 + var1;
                        }
                        break label26;
                     }
                  }

                  this.nextItem = new IntRange(this.currentStartIndex, StringsKt.getLastIndex(this.this$0.input));
                  this.nextSearchIndex = -1;
               }

               this.nextState = 1;
            }

         }

         public final int getCounter() {
            return this.counter;
         }

         public final int getCurrentStartIndex() {
            return this.currentStartIndex;
         }

         public final IntRange getNextItem() {
            return this.nextItem;
         }

         public final int getNextSearchIndex() {
            return this.nextSearchIndex;
         }

         public final int getNextState() {
            return this.nextState;
         }

         public boolean hasNext() {
            if (this.nextState == -1) {
               this.calcNext();
            }

            int var1 = this.nextState;
            boolean var2 = true;
            if (var1 != 1) {
               var2 = false;
            }

            return var2;
         }

         public IntRange next() {
            if (this.nextState == -1) {
               this.calcNext();
            }

            if (this.nextState != 0) {
               IntRange var1 = this.nextItem;
               Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type kotlin.ranges.IntRange");
               this.nextItem = null;
               this.nextState = -1;
               return var1;
            } else {
               throw new NoSuchElementException();
            }
         }

         public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
         }

         public final void setCounter(int var1) {
            this.counter = var1;
         }

         public final void setCurrentStartIndex(int var1) {
            this.currentStartIndex = var1;
         }

         public final void setNextItem(IntRange var1) {
            this.nextItem = var1;
         }

         public final void setNextSearchIndex(int var1) {
            this.nextSearchIndex = var1;
         }

         public final void setNextState(int var1) {
            this.nextState = var1;
         }
      });
   }
}
