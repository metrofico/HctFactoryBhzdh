package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010#\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0010\b\u0001\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u0002H\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a7\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a7\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"},
   d2 = {"toCollection", "C", "T", "", "Lkotlinx/coroutines/flow/Flow;", "destination", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toList", "", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSet", "", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__CollectionKt {
   public static final Object toCollection(Flow var0, Collection var1, Continuation var2) {
      Object var6;
      label23: {
         if (var2 instanceof <undefinedtype>) {
            <undefinedtype> var4 = (<undefinedtype>)var2;
            if ((var4.label & Integer.MIN_VALUE) != 0) {
               var4.label += Integer.MIN_VALUE;
               var6 = var4;
               break label23;
            }
         }

         var6 = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            Object L$2;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return FlowKt.toCollection((Flow)null, (Collection)null, this);
            }
         };
      }

      Object var7 = ((<undefinedtype>)var6).result;
      Object var5 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var3 = ((<undefinedtype>)var6).label;
      if (var3 != 0) {
         if (var3 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var0 = (Flow)((<undefinedtype>)var6).L$2;
         var1 = (Collection)((<undefinedtype>)var6).L$1;
         var0 = (Flow)((<undefinedtype>)var6).L$0;
         ResultKt.throwOnFailure(var7);
      } else {
         ResultKt.throwOnFailure(var7);
         FlowCollector var8 = (FlowCollector)(new FlowCollector(var1) {
            final Collection $destination$inlined;

            public {
               this.$destination$inlined = var1;
            }

            public Object emit(Object var1, Continuation var2) {
               this.$destination$inlined.add(var1);
               return Unit.INSTANCE;
            }
         });
         ((<undefinedtype>)var6).L$0 = var0;
         ((<undefinedtype>)var6).L$1 = var1;
         ((<undefinedtype>)var6).L$2 = var0;
         ((<undefinedtype>)var6).label = 1;
         if (var0.collect(var8, (Continuation)var6) == var5) {
            return var5;
         }
      }

      return var1;
   }

   public static final Object toList(Flow var0, List var1, Continuation var2) {
      return FlowKt.toCollection(var0, (Collection)var1, var2);
   }

   // $FF: synthetic method
   public static Object toList$default(Flow var0, List var1, Continuation var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (List)(new ArrayList());
      }

      return FlowKt.toList(var0, var1, var2);
   }

   public static final Object toSet(Flow var0, Set var1, Continuation var2) {
      return FlowKt.toCollection(var0, (Collection)var1, var2);
   }

   // $FF: synthetic method
   public static Object toSet$default(Flow var0, Set var1, Continuation var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (Set)(new LinkedHashSet());
      }

      return FlowKt.toSet(var0, var1, var2);
   }
}
