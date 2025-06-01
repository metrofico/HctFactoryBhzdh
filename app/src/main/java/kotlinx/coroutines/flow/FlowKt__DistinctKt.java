package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e\u001aT\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e26\u0010\u0010\u001a2\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00030\u0001\u001a6\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0016*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00160\t\u001au\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u000e2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u0002H\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2:\u0010\u0010\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00030\u0001H\u0002¢\u0006\u0002\b\u0018\"2\u0010\u0000\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u00018\u0002X\u0083\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\",\u0010\b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0002X\u0083\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0005\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"},
   d2 = {"defaultAreEquivalent", "Lkotlin/Function2;", "", "", "getDefaultAreEquivalent$FlowKt__DistinctKt$annotations", "()V", "getDefaultAreEquivalent$FlowKt__DistinctKt", "()Lkotlin/jvm/functions/Function2;", "defaultKeySelector", "Lkotlin/Function1;", "getDefaultKeySelector$FlowKt__DistinctKt$annotations", "getDefaultKeySelector$FlowKt__DistinctKt", "()Lkotlin/jvm/functions/Function1;", "distinctUntilChanged", "Lkotlinx/coroutines/flow/Flow;", "T", "areEquivalent", "Lkotlin/ParameterName;", "name", "old", "new", "distinctUntilChangedBy", "K", "keySelector", "distinctUntilChangedBy$FlowKt__DistinctKt", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/flow/FlowKt"
)
final class FlowKt__DistinctKt {
   private static final Function2 defaultAreEquivalent;
   private static final Function1 defaultKeySelector;

   static {
      defaultKeySelector = (Function1)null.INSTANCE;
      defaultAreEquivalent = (Function2)null.INSTANCE;
   }

   public static final Flow distinctUntilChanged(Flow var0) {
      if (!(var0 instanceof StateFlow)) {
         var0 = distinctUntilChangedBy$FlowKt__DistinctKt(var0, defaultKeySelector, defaultAreEquivalent);
      }

      return var0;
   }

   public static final Flow distinctUntilChanged(Flow var0, Function2 var1) {
      Function1 var2 = defaultKeySelector;
      if (var1 != null) {
         return distinctUntilChangedBy$FlowKt__DistinctKt(var0, var2, (Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var1, 2));
      } else {
         throw new NullPointerException("null cannot be cast to non-null type (kotlin.Any?, kotlin.Any?) -> kotlin.Boolean");
      }
   }

   public static final Flow distinctUntilChangedBy(Flow var0, Function1 var1) {
      return distinctUntilChangedBy$FlowKt__DistinctKt(var0, var1, defaultAreEquivalent);
   }

   private static final Flow distinctUntilChangedBy$FlowKt__DistinctKt(Flow var0, Function1 var1, Function2 var2) {
      if (var0 instanceof DistinctFlowImpl) {
         DistinctFlowImpl var3 = (DistinctFlowImpl)var0;
         if (var3.keySelector == var1 && var3.areEquivalent == var2) {
            return var0;
         }
      }

      var0 = (Flow)(new DistinctFlowImpl(var0, var1, var2));
      return var0;
   }

   private static final Function2 getDefaultAreEquivalent$FlowKt__DistinctKt() {
      return defaultAreEquivalent;
   }

   // $FF: synthetic method
   private static void getDefaultAreEquivalent$FlowKt__DistinctKt$annotations() {
   }

   private static final Function1 getDefaultKeySelector$FlowKt__DistinctKt() {
      return defaultKeySelector;
   }

   // $FF: synthetic method
   private static void getDefaultKeySelector$FlowKt__DistinctKt$annotations() {
   }
}
