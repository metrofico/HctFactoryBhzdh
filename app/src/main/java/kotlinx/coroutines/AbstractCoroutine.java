package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\u0015\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\r\u0010\u001e\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u001fJ\r\u0010 \u001a\u00020\u0019H\u0010¢\u0006\u0002\b!J\u0018\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\tH\u0014J\u0015\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010'J\u0012\u0010(\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0004J\b\u0010)\u001a\u00020\u0015H\u0014J\r\u0010*\u001a\u00020\u0015H\u0000¢\u0006\u0002\b+J\u001c\u0010,\u001a\u00020\u00152\f\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000.ø\u0001\u0000¢\u0006\u0002\u0010'JM\u0010/\u001a\u00020\u0015\"\u0004\b\u0001\u001002\u0006\u0010/\u001a\u0002012\u0006\u00102\u001a\u0002H02'\u00103\u001a#\b\u0001\u0012\u0004\u0012\u0002H0\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001704¢\u0006\u0002\b5ø\u0001\u0000¢\u0006\u0002\u00106J4\u0010/\u001a\u00020\u00152\u0006\u0010/\u001a\u0002012\u001c\u00103\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u001707ø\u0001\u0000¢\u0006\u0002\u00108R\u0017\u0010\u000b\u001a\u00020\u0007¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0006\u001a\u00020\u00078\u0004X\u0085\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00069"},
   d2 = {"Lkotlinx/coroutines/AbstractCoroutine;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/CoroutineScope;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Z)V", "context", "getContext$annotations", "()V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "getCoroutineContext", "isActive", "()Z", "afterResume", "", "state", "", "cancellationExceptionMessage", "", "handleOnCompletionException", "exception", "", "handleOnCompletionException$kotlinx_coroutines_core", "initParentJob", "initParentJob$kotlinx_coroutines_core", "nameString", "nameString$kotlinx_coroutines_core", "onCancelled", "cause", "handled", "onCompleted", "value", "(Ljava/lang/Object;)V", "onCompletionInternal", "onStart", "onStartInternal", "onStartInternal$kotlinx_coroutines_core", "resumeWith", "result", "Lkotlin/Result;", "start", "R", "Lkotlinx/coroutines/CoroutineStart;", "receiver", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineStart;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/Function1;", "(Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;)V", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public abstract class AbstractCoroutine extends JobSupport implements Job, Continuation, CoroutineScope {
   private final CoroutineContext context;
   protected final CoroutineContext parentContext;

   public AbstractCoroutine(CoroutineContext var1, boolean var2) {
      super(var2);
      this.parentContext = var1;
      this.context = var1.plus((CoroutineContext)this);
   }

   // $FF: synthetic method
   public AbstractCoroutine(CoroutineContext var1, boolean var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = true;
      }

      this(var1, var2);
   }

   // $FF: synthetic method
   public static void getContext$annotations() {
   }

   protected void afterResume(Object var1) {
      this.afterCompletion(var1);
   }

   protected String cancellationExceptionMessage() {
      return DebugStringsKt.getClassSimpleName(this) + " was cancelled";
   }

   public final CoroutineContext getContext() {
      return this.context;
   }

   public CoroutineContext getCoroutineContext() {
      return this.context;
   }

   public final void handleOnCompletionException$kotlinx_coroutines_core(Throwable var1) {
      CoroutineExceptionHandlerKt.handleCoroutineException(this.context, var1);
   }

   public final void initParentJob$kotlinx_coroutines_core() {
      this.initParentJobInternal$kotlinx_coroutines_core((Job)this.parentContext.get((CoroutineContext.Key)Job.Key));
   }

   public boolean isActive() {
      return super.isActive();
   }

   public String nameString$kotlinx_coroutines_core() {
      String var1 = CoroutineContextKt.getCoroutineName(this.context);
      return var1 != null ? '"' + var1 + "\":" + super.nameString$kotlinx_coroutines_core() : super.nameString$kotlinx_coroutines_core();
   }

   protected void onCancelled(Throwable var1, boolean var2) {
   }

   protected void onCompleted(Object var1) {
   }

   protected final void onCompletionInternal(Object var1) {
      if (var1 instanceof CompletedExceptionally) {
         CompletedExceptionally var2 = (CompletedExceptionally)var1;
         this.onCancelled(var2.cause, var2.getHandled());
      } else {
         this.onCompleted(var1);
      }

   }

   protected void onStart() {
   }

   public final void onStartInternal$kotlinx_coroutines_core() {
      this.onStart();
   }

   public final void resumeWith(Object var1) {
      var1 = this.makeCompletingOnce$kotlinx_coroutines_core(CompletionStateKt.toState$default(var1, (Function1)null, 1, (Object)null));
      if (var1 != JobSupportKt.COMPLETING_WAITING_CHILDREN) {
         this.afterResume(var1);
      }
   }

   public final void start(CoroutineStart var1, Object var2, Function2 var3) {
      this.initParentJob$kotlinx_coroutines_core();
      var1.invoke(var3, var2, (Continuation)this);
   }

   public final void start(CoroutineStart var1, Function1 var2) {
      this.initParentJob$kotlinx_coroutines_core();
      var1.invoke(var2, (Continuation)this);
   }
}
