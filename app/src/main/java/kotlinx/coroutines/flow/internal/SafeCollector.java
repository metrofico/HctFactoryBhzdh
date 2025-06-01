package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.text.StringsKt;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u00032\u00020\u0004B\u001b\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ'\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ%\u0010\u001a\u001a\u0004\u0018\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0018\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001eJ\u001a\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!2\b\u0010\u0018\u001a\u0004\u0018\u00010\u001cH\u0002J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\"\u0010$\u001a\u0004\u0018\u00010\u001c2\u000e\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0&H\u0016ø\u0001\u0000¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020\u0010H\u0016R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\u0006\u001a\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"},
   d2 = {"Lkotlinx/coroutines/flow/internal/SafeCollector;", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "collector", "collectContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "collectContextSize", "", "completion", "Lkotlin/coroutines/Continuation;", "", "context", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "lastEmissionContext", "checkContext", "currentContext", "previousContext", "value", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "uCont", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)Ljava/lang/Object;", "exceptionTransparencyViolated", "exception", "Lkotlinx/coroutines/flow/internal/DownstreamExceptionElement;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "invokeSuspend", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class SafeCollector extends ContinuationImpl implements FlowCollector, CoroutineStackFrame {
   public final CoroutineContext collectContext;
   public final int collectContextSize;
   public final FlowCollector collector;
   private Continuation completion;
   private CoroutineContext lastEmissionContext;

   public SafeCollector(FlowCollector var1, CoroutineContext var2) {
      super((Continuation)NoOpContinuation.INSTANCE, (CoroutineContext)EmptyCoroutineContext.INSTANCE);
      this.collector = var1;
      this.collectContext = var2;
      this.collectContextSize = ((Number)var2.fold(0, (Function2)null.INSTANCE)).intValue();
   }

   private final void checkContext(CoroutineContext var1, CoroutineContext var2, Object var3) {
      if (var2 instanceof DownstreamExceptionElement) {
         this.exceptionTransparencyViolated((DownstreamExceptionElement)var2, var3);
      }

      SafeCollector_commonKt.checkContext(this, var1);
      this.lastEmissionContext = var1;
   }

   private final Object emit(Continuation var1, Object var2) {
      CoroutineContext var3 = var1.getContext();
      JobKt.ensureActive(var3);
      CoroutineContext var4 = this.lastEmissionContext;
      if (var4 != var3) {
         this.checkContext(var3, var4, var2);
      }

      this.completion = var1;
      Function3 var6 = SafeCollectorKt.access$getEmitFun$p();
      FlowCollector var5 = this.collector;
      if (var5 != null) {
         return var6.invoke(var5, var2, (Continuation)this);
      } else {
         throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
      }
   }

   private final void exceptionTransparencyViolated(DownstreamExceptionElement var1, Object var2) {
      throw (Throwable)(new IllegalStateException(StringsKt.trimIndent("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + var1.e + ", but then emission attempt of value '" + var2 + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString()));
   }

   public Object emit(Object var1, Continuation var2) {
      try {
         var1 = this.emit(var2, var1);
      } catch (Throwable var4) {
         this.lastEmissionContext = (CoroutineContext)(new DownstreamExceptionElement(var4));
         throw var4;
      }

      if (var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var2);
      }

      return var1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var1 : Unit.INSTANCE;
   }

   public CoroutineStackFrame getCallerFrame() {
      Continuation var2 = this.completion;
      Continuation var1 = var2;
      if (!(var2 instanceof CoroutineStackFrame)) {
         var1 = null;
      }

      return (CoroutineStackFrame)var1;
   }

   public CoroutineContext getContext() {
      Continuation var1 = this.completion;
      CoroutineContext var2;
      if (var1 != null) {
         var2 = var1.getContext();
         if (var2 != null) {
            return var2;
         }
      }

      var2 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      return var2;
   }

   public StackTraceElement getStackTraceElement() {
      return null;
   }

   public Object invokeSuspend(Object var1) {
      Throwable var2 = Result.exceptionOrNull_impl(var1);
      if (var2 != null) {
         this.lastEmissionContext = (CoroutineContext)(new DownstreamExceptionElement(var2));
      }

      Continuation var3 = this.completion;
      if (var3 != null) {
         var3.resumeWith(var1);
      }

      return IntrinsicsKt.getCOROUTINE_SUSPENDED();
   }

   public void releaseIntercepted() {
      super.releaseIntercepted();
   }
}
