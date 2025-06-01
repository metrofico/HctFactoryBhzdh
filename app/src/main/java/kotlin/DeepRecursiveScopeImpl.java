package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004BK\u00129\u0010\u0005\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0002\b\b\u0012\u0006\u0010\t\u001a\u00028\u0000ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\u0015\u001a\u00028\u00012\u0006\u0010\t\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0016Jc\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000429\u0010\u0018\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0002\b\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\u000b\u0010\u001d\u001a\u00028\u0001¢\u0006\u0002\u0010\u001eJ5\u0010\u0015\u001a\u0002H\u001f\"\u0004\b\u0002\u0010 \"\u0004\b\u0003\u0010\u001f*\u000e\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\u001f0!2\u0006\u0010\t\u001a\u0002H H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\"R\u0018\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fRF\u0010\u0010\u001a5\b\u0001\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\u0002\b\bX\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0011R\u001e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0013X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006#"},
   d2 = {"Lkotlin/DeepRecursiveScopeImpl;", "T", "R", "Lkotlin/DeepRecursiveScope;", "Lkotlin/coroutines/Continuation;", "block", "Lkotlin/Function3;", "", "Lkotlin/ExtensionFunctionType;", "value", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)V", "cont", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "function", "Lkotlin/jvm/functions/Function3;", "result", "Lkotlin/Result;", "Ljava/lang/Object;", "callRecursive", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "crossFunctionCompletion", "currentFunction", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resumeWith", "", "(Ljava/lang/Object;)V", "runCallLoop", "()Ljava/lang/Object;", "S", "U", "Lkotlin/DeepRecursiveFunction;", "(Lkotlin/DeepRecursiveFunction;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class DeepRecursiveScopeImpl extends DeepRecursiveScope implements Continuation {
   private Continuation cont;
   private Function3 function;
   private Object result;
   private Object value;

   public DeepRecursiveScopeImpl(Function3 var1, Object var2) {
      Intrinsics.checkNotNullParameter(var1, "block");
      super((DefaultConstructorMarker)null);
      this.function = var1;
      this.value = var2;
      Intrinsics.checkNotNull(this, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
      this.cont = (Continuation)this;
      this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
   }

   private final Continuation crossFunctionCompletion(Function3 var1, Continuation var2) {
      return (Continuation)(new Continuation((CoroutineContext)EmptyCoroutineContext.INSTANCE, this, var1, var2) {
         final Continuation $cont$inlined;
         final CoroutineContext $context;
         final Function3 $currentFunction$inlined;
         final DeepRecursiveScopeImpl this$0;

         public {
            this.$context = var1;
            this.this$0 = var2;
            this.$currentFunction$inlined = var3;
            this.$cont$inlined = var4;
         }

         public CoroutineContext getContext() {
            return this.$context;
         }

         public void resumeWith(Object var1) {
            this.this$0.function = this.$currentFunction$inlined;
            this.this$0.cont = this.$cont$inlined;
            this.this$0.result = var1;
         }
      });
   }

   public Object callRecursive(Object var1, Continuation var2) {
      Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
      this.cont = var2;
      this.value = var1;
      var1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var1;
   }

   public Object callRecursive(DeepRecursiveFunction var1, Object var2, Continuation var3) {
      Function3 var5 = var1.getBlock$kotlin_stdlib();
      Intrinsics.checkNotNull(var5, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.coroutines.SuspendFunction2<kotlin.DeepRecursiveScope<*, *>, kotlin.Any?, kotlin.Any?>{ kotlin.DeepRecursiveKt.DeepRecursiveFunctionBlock }");
      DeepRecursiveScopeImpl var4 = (DeepRecursiveScopeImpl)this;
      Function3 var7 = this.function;
      if (var5 != var7) {
         this.function = var5;
         Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         this.cont = this.crossFunctionCompletion(var7, var3);
      } else {
         Intrinsics.checkNotNull(var3, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
         this.cont = var3;
      }

      this.value = var2;
      Object var6 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      if (var6 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var3);
      }

      return var6;
   }

   public CoroutineContext getContext() {
      return (CoroutineContext)EmptyCoroutineContext.INSTANCE;
   }

   public void resumeWith(Object var1) {
      this.cont = null;
      this.result = var1;
   }

   public final Object runCallLoop() {
      while(true) {
         Object var2 = this.result;
         Continuation var1 = this.cont;
         if (var1 == null) {
            ResultKt.throwOnFailure(var2);
            return var2;
         }

         if (Result.equals_impl0(DeepRecursiveKt.access$getUNDEFINED_RESULT$p(), var2)) {
            Result.Companion var3;
            try {
               Function3 var6 = this.function;
               var2 = this.value;
               Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type kotlin.Function3<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.startCoroutineUninterceptedOrReturn, P of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.startCoroutineUninterceptedOrReturn, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.startCoroutineUninterceptedOrReturn>, kotlin.Any?>");
               var2 = ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(var6, 3)).invoke(this, var2, var1);
            } catch (Throwable var5) {
               var3 = Result.Companion;
               var1.resumeWith(Result.constructor_impl(ResultKt.createFailure(var5)));
               continue;
            }

            if (var2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               var3 = Result.Companion;
               var1.resumeWith(Result.constructor_impl(var2));
            }
         } else {
            this.result = DeepRecursiveKt.access$getUNDEFINED_RESULT$p();
            var1.resumeWith(var2);
         }
      }
   }
}
