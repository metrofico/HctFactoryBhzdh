package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001aU\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u000e\u001a[\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0010\"\u0004\b\u0000\u0010\u0005*\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aF\u0010\u0014\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0005*\u00020\u00152)\b\b\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rH\u0086Jø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001aO\u0010\u0017\u001a\u00020\u0018*\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\rø\u0001\u0000¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"},
   d2 = {"RESUMED", "", "SUSPENDED", "UNDECIDED", "withContext", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "async", "Lkotlinx/coroutines/Deferred;", "start", "Lkotlinx/coroutines/CoroutineStart;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Deferred;", "invoke", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launch", "Lkotlinx/coroutines/Job;", "", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/BuildersKt"
)
final class BuildersKt__Builders_commonKt {
   private static final int RESUMED = 2;
   private static final int SUSPENDED = 1;
   private static final int UNDECIDED = 0;

   public static final Deferred async(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3) {
      CoroutineContext var4 = CoroutineContextKt.newCoroutineContext(var0, var1);
      DeferredCoroutine var5;
      if (var2.isLazy()) {
         var5 = (DeferredCoroutine)(new LazyDeferredCoroutine(var4, var3));
      } else {
         var5 = new DeferredCoroutine(var4, true);
      }

      var5.start(var2, var5, var3);
      return (Deferred)var5;
   }

   // $FF: synthetic method
   public static Deferred async$default(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var4 & 2) != 0) {
         var2 = CoroutineStart.DEFAULT;
      }

      return BuildersKt.async(var0, var1, var2, var3);
   }

   public static final Object invoke(CoroutineDispatcher var0, Function2 var1, Continuation var2) {
      return BuildersKt.withContext((CoroutineContext)var0, var1, var2);
   }

   private static final Object invoke$$forInline(CoroutineDispatcher var0, Function2 var1, Continuation var2) {
      CoroutineContext var3 = (CoroutineContext)var0;
      InlineMarker.mark(0);
      Object var4 = BuildersKt.withContext(var3, var1, var2);
      InlineMarker.mark(1);
      return var4;
   }

   public static final Job launch(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3) {
      CoroutineContext var4 = CoroutineContextKt.newCoroutineContext(var0, var1);
      StandaloneCoroutine var5;
      if (var2.isLazy()) {
         var5 = (StandaloneCoroutine)(new LazyStandaloneCoroutine(var4, var3));
      } else {
         var5 = new StandaloneCoroutine(var4, true);
      }

      var5.start(var2, var5, var3);
      return (Job)var5;
   }

   // $FF: synthetic method
   public static Job launch$default(CoroutineScope var0, CoroutineContext var1, CoroutineStart var2, Function2 var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      if ((var4 & 2) != 0) {
         var2 = CoroutineStart.DEFAULT;
      }

      return BuildersKt.launch(var0, var1, var2, var3);
   }

   public static final Object withContext(CoroutineContext var0, Function2 var1, Continuation var2) {
      CoroutineContext var4 = var2.getContext();
      CoroutineContext var3 = var4.plus(var0);
      YieldKt.checkCompletion(var3);
      Object var8;
      if (var3 == var4) {
         ScopeCoroutine var7 = new ScopeCoroutine(var3, var2);
         var8 = UndispatchedKt.startUndispatchedOrReturn(var7, var7, var1);
      } else if (Intrinsics.areEqual((Object)((ContinuationInterceptor)var3.get((CoroutineContext.Key)ContinuationInterceptor.Key)), (Object)((ContinuationInterceptor)var4.get((CoroutineContext.Key)ContinuationInterceptor.Key)))) {
         UndispatchedCoroutine var9 = new UndispatchedCoroutine(var3, var2);
         Object var11 = ThreadContextKt.updateThreadContext(var3, (Object)null);

         try {
            var8 = UndispatchedKt.startUndispatchedOrReturn((ScopeCoroutine)var9, var9, var1);
         } finally {
            ThreadContextKt.restoreThreadContext(var3, var11);
         }
      } else {
         DispatchedCoroutine var10 = new DispatchedCoroutine(var3, var2);
         var10.initParentJob$kotlinx_coroutines_core();
         CancellableKt.startCoroutineCancellable$default(var1, var10, (Continuation)var10, (Function1)null, 4, (Object)null);
         var8 = var10.getResult();
      }

      if (var8 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var8;
   }
}
