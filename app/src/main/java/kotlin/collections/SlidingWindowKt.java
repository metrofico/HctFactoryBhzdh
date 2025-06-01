package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(
   d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000\u001aH\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u00062\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000\u001aD\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0000¨\u0006\u000f"},
   d2 = {"checkWindowSizeStep", "", "size", "", "step", "windowedIterator", "", "", "T", "iterator", "partialWindows", "", "reuseBuffer", "windowedSequence", "Lkotlin/sequences/Sequence;", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class SlidingWindowKt {
   public static final void checkWindowSizeStep(int var0, int var1) {
      boolean var2;
      if (var0 > 0 && var1 > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (!var2) {
         String var3;
         if (var0 != var1) {
            var3 = "Both size " + var0 + " and step " + var1 + " must be greater than zero.";
         } else {
            var3 = "size " + var0 + " must be greater than zero.";
         }

         throw new IllegalArgumentException(var3.toString());
      }
   }

   public static final Iterator windowedIterator(Iterator var0, int var1, int var2, boolean var3, boolean var4) {
      Intrinsics.checkNotNullParameter(var0, "iterator");
      return !var0.hasNext() ? (Iterator)EmptyIterator.INSTANCE : SequencesKt.iterator((Function2)(new Function2(var1, var2, var0, var4, var3, (Continuation)null) {
         final Iterator $iterator;
         final boolean $partialWindows;
         final boolean $reuseBuffer;
         final int $size;
         final int $step;
         int I$0;
         private Object L$0;
         Object L$1;
         Object L$2;
         int label;

         {
            this.$size = var1;
            this.$step = var2;
            this.$iterator = var3;
            this.$reuseBuffer = var4;
            this.$partialWindows = var5;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, var2);
            var3.L$0 = var1;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope var1, Continuation var2) {
            return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var9;
            SequenceScope var13;
            <undefinedtype> var14;
            RingBuffer var16;
            label128: {
               int var2;
               int var3;
               SequenceScope var5;
               Iterator var6;
               Continuation var10;
               RingBuffer var12;
               label151: {
                  var9 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  var2 = this.label;
                  <undefinedtype> var7;
                  ArrayList var11;
                  if (var2 != 0) {
                     if (var2 != 1) {
                        if (var2 == 2) {
                           ResultKt.throwOnFailure(var1);
                           return Unit.INSTANCE;
                        }

                        if (var2 != 3) {
                           if (var2 != 4) {
                              if (var2 != 5) {
                                 throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                              }

                              ResultKt.throwOnFailure(var1);
                              return Unit.INSTANCE;
                           }

                           var16 = (RingBuffer)this.L$1;
                           SequenceScope var18 = (SequenceScope)this.L$0;
                           ResultKt.throwOnFailure(var1);
                           var14 = this;
                           var16.removeFirst(this.$step);
                           var13 = var18;
                           break label128;
                        }

                        var6 = (Iterator)this.L$2;
                        var16 = (RingBuffer)this.L$1;
                        SequenceScope var19 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(var1);
                        var14 = this;
                        var12 = var16;
                        var16.removeFirst(this.$step);
                        var5 = var19;
                        break label151;
                     }

                     var3 = this.I$0;
                     var6 = (Iterator)this.L$2;
                     ArrayList var4 = (ArrayList)this.L$1;
                     SequenceScope var8 = (SequenceScope)this.L$0;
                     ResultKt.throwOnFailure(var1);
                     var7 = this;
                     if (this.$reuseBuffer) {
                        var4.clear();
                        var11 = var4;
                     } else {
                        var11 = new ArrayList(this.$size);
                     }

                     var2 = var3;
                     var5 = var8;
                  } else {
                     ResultKt.throwOnFailure(var1);
                     var5 = (SequenceScope)this.L$0;
                     var2 = RangesKt.coerceAtMost(this.$size, 1024);
                     var3 = this.$step - this.$size;
                     if (var3 < 0) {
                        var12 = new RingBuffer(var2);
                        var6 = this.$iterator;
                        var14 = this;
                        break label151;
                     }

                     var11 = new ArrayList(var2);
                     var2 = 0;
                     var6 = this.$iterator;
                     var7 = this;
                  }

                  while(var6.hasNext()) {
                     Object var15 = var6.next();
                     if (var2 > 0) {
                        --var2;
                     } else {
                        var11.add(var15);
                        if (var11.size() == var7.$size) {
                           var10 = (Continuation)var7;
                           var7.L$0 = var5;
                           var7.L$1 = var11;
                           var7.L$2 = var6;
                           var7.I$0 = var3;
                           var7.label = 1;
                           if (var5.yield(var11, var10) == var9) {
                              return var9;
                           }

                           if (var7.$reuseBuffer) {
                              var11.clear();
                              var11 = var11;
                           } else {
                              var11 = new ArrayList(var7.$size);
                           }

                           var2 = var3;
                           var5 = var5;
                        }
                     }
                  }

                  if (((Collection)var11).isEmpty() ^ true && (var7.$partialWindows || var11.size() == var7.$size)) {
                     Continuation var17 = (Continuation)var7;
                     var7.L$0 = null;
                     var7.L$1 = null;
                     var7.L$2 = null;
                     var7.label = 2;
                     if (var5.yield(var11, var17) == var9) {
                        return var9;
                     }
                  }

                  return Unit.INSTANCE;
               }

               while(true) {
                  if (!var6.hasNext()) {
                     if (!var14.$partialWindows) {
                        return Unit.INSTANCE;
                     }

                     var13 = var5;
                     var16 = var12;
                     break;
                  }

                  var12.add(var6.next());
                  if (var12.isFull()) {
                     var3 = var12.size();
                     var2 = var14.$size;
                     if (var3 < var2) {
                        var12 = var12.expanded(var2);
                     } else {
                        List var21;
                        if (var14.$reuseBuffer) {
                           var21 = (List)var12;
                        } else {
                           var21 = (List)(new ArrayList((Collection)var12));
                        }

                        var10 = (Continuation)var14;
                        var14.L$0 = var5;
                        var14.L$1 = var12;
                        var14.L$2 = var6;
                        var14.label = 3;
                        if (var5.yield(var21, var10) == var9) {
                           return var9;
                        }

                        var12.removeFirst(var14.$step);
                        var5 = var5;
                     }
                  }
               }
            }

            while(true) {
               if (var16.size() <= var14.$step) {
                  if (((Collection)var16).isEmpty() ^ true) {
                     Continuation var22 = (Continuation)var14;
                     var14.L$0 = null;
                     var14.L$1 = null;
                     var14.L$2 = null;
                     var14.label = 5;
                     if (var13.yield(var16, var22) == var9) {
                        return var9;
                     }
                  }
                  break;
               }

               List var23;
               if (var14.$reuseBuffer) {
                  var23 = (List)var16;
               } else {
                  var23 = (List)(new ArrayList((Collection)var16));
               }

               Continuation var24 = (Continuation)var14;
               var14.L$0 = var13;
               var14.L$1 = var16;
               var14.L$2 = null;
               var14.label = 4;
               if (var13.yield(var23, var24) == var9) {
                  return var9;
               }

               var16.removeFirst(var14.$step);
               var13 = var13;
            }

            return Unit.INSTANCE;
         }
      }));
   }

   public static final Sequence windowedSequence(Sequence var0, int var1, int var2, boolean var3, boolean var4) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      checkWindowSizeStep(var1, var2);
      return (Sequence)(new Sequence(var0, var1, var2, var3, var4) {
         final boolean $partialWindows$inlined;
         final boolean $reuseBuffer$inlined;
         final int $size$inlined;
         final int $step$inlined;
         final Sequence $this_windowedSequence$inlined;

         public {
            this.$this_windowedSequence$inlined = var1;
            this.$size$inlined = var2;
            this.$step$inlined = var3;
            this.$partialWindows$inlined = var4;
            this.$reuseBuffer$inlined = var5;
         }

         public Iterator iterator() {
            return SlidingWindowKt.windowedIterator(this.$this_windowedSequence$inlined.iterator(), this.$size$inlined, this.$step$inlined, this.$partialWindows$inlined, this.$reuseBuffer$inlined);
         }
      });
   }
}
