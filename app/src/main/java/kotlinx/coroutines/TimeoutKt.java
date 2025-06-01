package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a_\u0010\u0006\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\b\"\b\b\u0001\u0010\t*\u0002H\b2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\n2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\u000fH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001aU\u0010\u0011\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\u0012\u001a\u00020\u00032'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\u000fH\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0013\u001aZ\u0010\u0011\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\u0014\u001a\u00020\u00152'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\u000fH\u0087@ø\u0001\u0000ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001aJ\u0010\u0018\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\u0012\u001a\u00020\u00032'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\u000fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001aO\u0010\u0018\u001a\u0004\u0018\u0001H\t\"\u0004\b\u0000\u0010\t2\u0006\u0010\u0014\u001a\u00020\u00152'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00070\f¢\u0006\u0002\b\u000fH\u0087@ø\u0001\u0000ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"},
   d2 = {"TimeoutCancellationException", "Lkotlinx/coroutines/TimeoutCancellationException;", "time", "", "coroutine", "Lkotlinx/coroutines/Job;", "setupTimeout", "", "U", "T", "Lkotlinx/coroutines/TimeoutCoroutine;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/TimeoutCoroutine;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "withTimeout", "timeMillis", "(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "timeout", "Lkotlin/time/Duration;", "withTimeout-lwyi7ZQ", "(DLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTimeoutOrNull", "withTimeoutOrNull-lwyi7ZQ", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class TimeoutKt {
   public static final TimeoutCancellationException TimeoutCancellationException(long var0, Job var2) {
      return new TimeoutCancellationException("Timed out waiting for " + var0 + " ms", var2);
   }

   private static final Object setupTimeout(TimeoutCoroutine var0, Function2 var1) {
      CoroutineContext var2 = var0.uCont.getContext();
      JobKt.disposeOnCompletion((Job)var0, DelayKt.getDelay(var2).invokeOnTimeout(var0.time, (Runnable)var0, var0.getContext()));
      return UndispatchedKt.startUndispatchedOrReturnIgnoreTimeout((ScopeCoroutine)var0, var0, var1);
   }

   public static final Object withTimeout(long var0, Function2 var2, Continuation var3) {
      if (var0 > 0L) {
         Object var4 = setupTimeout(new TimeoutCoroutine(var0, var3), var2);
         if (var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var3);
         }

         return var4;
      } else {
         throw (Throwable)(new TimeoutCancellationException("Timed out immediately"));
      }
   }

   public static final Object withTimeout_lwyi7ZQ(double var0, Function2 var2, Continuation var3) {
      return withTimeout(DelayKt.toDelayMillis_LRDsOJo(var0), var2, var3);
   }

   public static final Object withTimeoutOrNull(long var0, Function2 var2, Continuation var3) {
      Object var13;
      label56: {
         if (var3 instanceof <undefinedtype>) {
            <undefinedtype> var5 = (<undefinedtype>)var3;
            if ((var5.label & Integer.MIN_VALUE) != 0) {
               var5.label += Integer.MIN_VALUE;
               var13 = var5;
               break label56;
            }
         }

         var13 = new ContinuationImpl(var3) {
            long J$0;
            Object L$0;
            Object L$1;
            int label;
            Object result;

            public final Object invokeSuspend(Object var1) {
               this.result = var1;
               this.label |= Integer.MIN_VALUE;
               return TimeoutKt.withTimeoutOrNull(0L, (Function2)null, this);
            }
         };
      }

      Object var15 = ((<undefinedtype>)var13).result;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      int var4 = ((<undefinedtype>)var13).label;
      TimeoutCancellationException var11;
      Object var12;
      Ref.ObjectRef var14;
      if (var4 != 0) {
         label68: {
            if (var4 != 1) {
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            Ref.ObjectRef var17 = (Ref.ObjectRef)((<undefinedtype>)var13).L$1;
            var2 = (Function2)((<undefinedtype>)var13).L$0;
            var0 = ((<undefinedtype>)var13).J$0;

            try {
               ResultKt.throwOnFailure(var15);
            } catch (TimeoutCancellationException var9) {
               var11 = var9;
               var14 = var17;
               break label68;
            }

            var12 = var15;
            return var12;
         }
      } else {
         label69: {
            ResultKt.throwOnFailure(var15);
            if (var0 <= 0L) {
               return null;
            }

            Ref.ObjectRef var16 = new Ref.ObjectRef();
            TimeoutCoroutine var7 = (TimeoutCoroutine)null;
            var16.element = null;

            try {
               ((<undefinedtype>)var13).J$0 = var0;
               ((<undefinedtype>)var13).L$0 = var2;
               ((<undefinedtype>)var13).L$1 = var16;
               ((<undefinedtype>)var13).label = 1;
               Continuation var8 = (Continuation)var13;
               var7 = new TimeoutCoroutine(var0, var8);
               var16.element = var7;
               var12 = setupTimeout(var7, var2);
               if (var12 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                  DebugProbesKt.probeCoroutineSuspended((Continuation)var13);
               }
            } catch (TimeoutCancellationException var10) {
               var11 = var10;
               var14 = var16;
               break label69;
            }

            if (var12 == var6) {
               return var6;
            }

            return var12;
         }
      }

      if (var11.coroutine == (TimeoutCoroutine)var14.element) {
         return null;
      } else {
         throw (Throwable)var11;
      }
   }

   public static final Object withTimeoutOrNull_lwyi7ZQ(double var0, Function2 var2, Continuation var3) {
      return withTimeoutOrNull(DelayKt.toDelayMillis_LRDsOJo(var0), var2, var3);
   }
}
