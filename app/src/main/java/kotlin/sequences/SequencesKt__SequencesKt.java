package kotlin.sequences;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

@Metadata(
   d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\bø\u0001\u0000\u001a\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001ab\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f2\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00050\u000eH\u0000\u001a&\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0004\u001a<\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u00042\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000e\u001a=\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u0001H\u00022\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u000eH\u0007¢\u0006\u0002\u0010\u0014\u001a+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0017\"\u0002H\u0002¢\u0006\u0002\u0010\u0018\u001a\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001aC\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00050\u000eH\u0002¢\u0006\u0002\b\u001c\u001a)\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u001d0\u0001H\u0007¢\u0006\u0002\b\u001e\u001a\"\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a2\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0004H\u0007\u001a!\u0010!\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001H\u0087\b\u001a\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001a&\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010#\u001a\u00020$H\u0007\u001a@\u0010%\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020'\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0'0&\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\b*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0&0\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006("},
   d2 = {"Sequence", "Lkotlin/sequences/Sequence;", "T", "iterator", "Lkotlin/Function0;", "", "emptySequence", "flatMapIndexed", "R", "C", "source", "transform", "Lkotlin/Function2;", "", "Lkotlin/Function1;", "generateSequence", "", "nextFunction", "seedFunction", "seed", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Lkotlin/sequences/Sequence;", "sequenceOf", "elements", "", "([Ljava/lang/Object;)Lkotlin/sequences/Sequence;", "asSequence", "constrainOnce", "flatten", "flatten$SequencesKt__SequencesKt", "", "flattenSequenceOfIterable", "ifEmpty", "defaultValue", "orEmpty", "shuffled", "random", "Lkotlin/random/Random;", "unzip", "Lkotlin/Pair;", "", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/sequences/SequencesKt"
)
class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
   public SequencesKt__SequencesKt() {
   }

   private static final Sequence Sequence(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "iterator");
      return (Sequence)(new Sequence(var0) {
         final Function0 $iterator;

         public {
            this.$iterator = var1;
         }

         public Iterator iterator() {
            return (Iterator)this.$iterator.invoke();
         }
      });
   }

   public static final Sequence asSequence(Iterator var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.constrainOnce((Sequence)(new Sequence(var0) {
         final Iterator $this_asSequence$inlined;

         public {
            this.$this_asSequence$inlined = var1;
         }

         public Iterator iterator() {
            return this.$this_asSequence$inlined;
         }
      }));
   }

   public static final Sequence constrainOnce(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      if (!(var0 instanceof ConstrainedOnceSequence)) {
         var0 = (Sequence)(new ConstrainedOnceSequence(var0));
      }

      return var0;
   }

   public static final Sequence emptySequence() {
      return (Sequence)EmptySequence.INSTANCE;
   }

   public static final Sequence flatMapIndexed(Sequence var0, Function2 var1, Function1 var2) {
      Intrinsics.checkNotNullParameter(var0, "source");
      Intrinsics.checkNotNullParameter(var1, "transform");
      Intrinsics.checkNotNullParameter(var2, "iterator");
      return SequencesKt.sequence((Function2)(new Function2(var0, var1, var2, (Continuation)null) {
         final Function1 $iterator;
         final Sequence $source;
         final Function2 $transform;
         int I$0;
         private Object L$0;
         Object L$1;
         int label;

         {
            this.$source = var1;
            this.$transform = var2;
            this.$iterator = var3;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$source, this.$transform, this.$iterator, var2);
            var3.L$0 = var1;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope var1, Continuation var2) {
            return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            Iterator var4;
            SequenceScope var8;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               var2 = this.I$0;
               var4 = (Iterator)this.L$1;
               SequenceScope var5 = (SequenceScope)this.L$0;
               ResultKt.throwOnFailure(var1);
               var8 = var5;
            } else {
               ResultKt.throwOnFailure(var1);
               var8 = (SequenceScope)this.L$0;
               var2 = 0;
               var4 = this.$source.iterator();
            }

            while(var4.hasNext()) {
               Object var9 = var4.next();
               Function2 var7 = this.$transform;
               int var3 = var2 + 1;
               if (var2 < 0) {
                  CollectionsKt.throwIndexOverflow();
               }

               var9 = var7.invoke(Boxing.boxInt(var2), var9);
               Iterator var10 = (Iterator)this.$iterator.invoke(var9);
               Continuation var11 = (Continuation)this;
               this.L$0 = var8;
               this.L$1 = var4;
               this.I$0 = var3;
               this.label = 1;
               if (var8.yieldAll(var10, var11) == var6) {
                  return var6;
               }

               var2 = var3;
            }

            return Unit.INSTANCE;
         }
      }));
   }

   public static final Sequence flatten(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return flatten$SequencesKt__SequencesKt(var0, (Function1)null.INSTANCE);
   }

   private static final Sequence flatten$SequencesKt__SequencesKt(Sequence var0, Function1 var1) {
      return var0 instanceof TransformingSequence ? ((TransformingSequence)var0).flatten$kotlin_stdlib(var1) : (Sequence)(new FlatteningSequence(var0, (Function1)null.INSTANCE, var1));
   }

   public static final Sequence flattenSequenceOfIterable(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return flatten$SequencesKt__SequencesKt(var0, (Function1)null.INSTANCE);
   }

   public static final Sequence generateSequence(Object var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "nextFunction");
      Sequence var2;
      if (var0 == null) {
         var2 = (Sequence)EmptySequence.INSTANCE;
      } else {
         var2 = (Sequence)(new GeneratorSequence((Function0)(new Function0(var0) {
            final Object $seed;

            {
               this.$seed = var1;
            }

            public final Object invoke() {
               return this.$seed;
            }
         }), var1));
      }

      return var2;
   }

   public static final Sequence generateSequence(Function0 var0) {
      Intrinsics.checkNotNullParameter(var0, "nextFunction");
      return SequencesKt.constrainOnce((Sequence)(new GeneratorSequence(var0, (Function1)(new Function1(var0) {
         final Function0 $nextFunction;

         {
            this.$nextFunction = var1;
         }

         public final Object invoke(Object var1) {
            Intrinsics.checkNotNullParameter(var1, "it");
            return this.$nextFunction.invoke();
         }
      }))));
   }

   public static final Sequence generateSequence(Function0 var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "seedFunction");
      Intrinsics.checkNotNullParameter(var1, "nextFunction");
      return (Sequence)(new GeneratorSequence(var0, var1));
   }

   public static final Sequence ifEmpty(Sequence var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      return SequencesKt.sequence((Function2)(new Function2(var0, var1, (Continuation)null) {
         final Function0 $defaultValue;
         final Sequence $this_ifEmpty;
         private Object L$0;
         int label;

         {
            this.$this_ifEmpty = var1;
            this.$defaultValue = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$this_ifEmpty, this.$defaultValue, var2);
            var3.L$0 = var1;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope var1, Continuation var2) {
            return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            if (var2 != 0) {
               if (var2 != 1 && var2 != 2) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               ResultKt.throwOnFailure(var1);
            } else {
               ResultKt.throwOnFailure(var1);
               SequenceScope var6 = (SequenceScope)this.L$0;
               Iterator var4 = this.$this_ifEmpty.iterator();
               Continuation var5;
               if (var4.hasNext()) {
                  var5 = (Continuation)this;
                  this.label = 1;
                  if (var6.yieldAll(var4, var5) == var3) {
                     return var3;
                  }
               } else {
                  Sequence var7 = (Sequence)this.$defaultValue.invoke();
                  var5 = (Continuation)this;
                  this.label = 2;
                  if (var6.yieldAll(var7, var5) == var3) {
                     return var3;
                  }
               }
            }

            return Unit.INSTANCE;
         }
      }));
   }

   private static final Sequence orEmpty(Sequence var0) {
      Sequence var1 = var0;
      if (var0 == null) {
         var1 = SequencesKt.emptySequence();
      }

      return var1;
   }

   public static final Sequence sequenceOf(Object... var0) {
      Intrinsics.checkNotNullParameter(var0, "elements");
      boolean var1;
      if (var0.length == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      Sequence var2;
      if (var1) {
         var2 = SequencesKt.emptySequence();
      } else {
         var2 = ArraysKt.asSequence(var0);
      }

      return var2;
   }

   public static final Sequence shuffled(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return SequencesKt.shuffled(var0, (Random)Random.Default);
   }

   public static final Sequence shuffled(Sequence var0, Random var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "random");
      return SequencesKt.sequence((Function2)(new Function2(var0, var1, (Continuation)null) {
         final Random $random;
         final Sequence $this_shuffled;
         private Object L$0;
         Object L$1;
         int label;

         {
            this.$this_shuffled = var1;
            this.$random = var2;
         }

         public final Continuation create(Object var1, Continuation var2) {
            Function2 var3 = new <anonymous constructor>(this.$this_shuffled, this.$random, var2);
            var3.L$0 = var1;
            return (Continuation)var3;
         }

         public final Object invoke(SequenceScope var1, Continuation var2) {
            return ((<undefinedtype>)this.create(var1, var2)).invokeSuspend(Unit.INSTANCE);
         }

         public final Object invokeSuspend(Object var1) {
            Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int var2 = this.label;
            SequenceScope var3;
            List var7;
            if (var2 != 0) {
               if (var2 != 1) {
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               List var4 = (List)this.L$1;
               var3 = (SequenceScope)this.L$0;
               ResultKt.throwOnFailure(var1);
               var7 = var4;
            } else {
               ResultKt.throwOnFailure(var1);
               var3 = (SequenceScope)this.L$0;
               var7 = SequencesKt.toMutableList(this.$this_shuffled);
            }

            Object var8;
            Continuation var9;
            do {
               if (!(((Collection)var7).isEmpty() ^ true)) {
                  return Unit.INSTANCE;
               }

               var2 = this.$random.nextInt(var7.size());
               Object var5 = CollectionsKt.removeLast(var7);
               var8 = var5;
               if (var2 < var7.size()) {
                  var8 = var7.set(var2, var5);
               }

               var9 = (Continuation)this;
               this.L$0 = var3;
               this.L$1 = var7;
               this.label = 1;
            } while(var3.yield(var8, var9) != var6);

            return var6;
         }
      }));
   }

   public static final Pair unzip(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ArrayList var2 = new ArrayList();
      ArrayList var1 = new ArrayList();
      Iterator var4 = var0.iterator();

      while(var4.hasNext()) {
         Pair var3 = (Pair)var4.next();
         var2.add(var3.getFirst());
         var1.add(var3.getSecond());
      }

      return TuplesKt.to(var2, var1);
   }
}
