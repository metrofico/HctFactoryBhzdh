package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.selects.SelectClause0;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\u0012\u0010\u0019\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0017J\u0018\u0010\u0019\u001a\u00020\u001c2\u000e\u0010\u001a\u001a\n\u0018\u00010\u001dj\u0004\u0018\u0001`\u001eH\u0017J\f\u0010\u001f\u001a\u00060\u001dj\u0002`\u001eH\u0017JA\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010#\u001a\u00020\n2'\u0010$\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001c0%j\u0002`(H\u0017J1\u0010 \u001a\u00020!2'\u0010$\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001c0%j\u0002`(H\u0017J\u0011\u0010)\u001a\u00020\u001cH\u0097@ø\u0001\u0000¢\u0006\u0002\u0010*J\b\u0010+\u001a\u00020\nH\u0017J\b\u0010,\u001a\u00020-H\u0016R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00058VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\n8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\t\u0010\fR\u001a\u0010\r\u001a\u00020\n8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\r\u0010\fR\u001a\u0010\u000f\u001a\u00020\n8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."},
   d2 = {"Lkotlinx/coroutines/NonCancellable;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/Job;", "()V", "children", "Lkotlin/sequences/Sequence;", "getChildren$annotations", "getChildren", "()Lkotlin/sequences/Sequence;", "isActive", "", "isActive$annotations", "()Z", "isCancelled", "isCancelled$annotations", "isCompleted", "isCompleted$annotations", "onJoin", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "attachChild", "Lkotlinx/coroutines/ChildHandle;", "child", "Lkotlinx/coroutines/ChildJob;", "cancel", "cause", "", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "getCancellationException", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "onCancelling", "invokeImmediately", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "join", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "start", "toString", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public final class NonCancellable extends AbstractCoroutineContextElement implements Job {
   public static final NonCancellable INSTANCE = new NonCancellable();

   private NonCancellable() {
      super((CoroutineContext.Key)Job.Key);
   }

   // $FF: synthetic method
   public static void getChildren$annotations() {
   }

   // $FF: synthetic method
   public static void isActive$annotations() {
   }

   // $FF: synthetic method
   public static void isCancelled$annotations() {
   }

   // $FF: synthetic method
   public static void isCompleted$annotations() {
   }

   public ChildHandle attachChild(ChildJob var1) {
      return (ChildHandle)NonDisposableHandle.INSTANCE;
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public void cancel() {
      Job.DefaultImpls.cancel(this);
   }

   public void cancel(CancellationException var1) {
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public boolean cancel(Throwable var1) {
      return false;
   }

   public CancellationException getCancellationException() {
      throw (Throwable)(new IllegalStateException("This job is always active"));
   }

   public Sequence getChildren() {
      return SequencesKt.emptySequence();
   }

   public SelectClause0 getOnJoin() {
      throw (Throwable)(new UnsupportedOperationException("This job is always active"));
   }

   public DisposableHandle invokeOnCompletion(Function1 var1) {
      return (DisposableHandle)NonDisposableHandle.INSTANCE;
   }

   public DisposableHandle invokeOnCompletion(boolean var1, boolean var2, Function1 var3) {
      return (DisposableHandle)NonDisposableHandle.INSTANCE;
   }

   public boolean isActive() {
      return true;
   }

   public boolean isCancelled() {
      return false;
   }

   public boolean isCompleted() {
      return false;
   }

   public Object join(Continuation var1) {
      throw (Throwable)(new UnsupportedOperationException("This job is always active"));
   }

   @Deprecated(
      level = DeprecationLevel.ERROR,
      message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`."
   )
   public Job plus(Job var1) {
      return Job.DefaultImpls.plus(this, (Job)var1);
   }

   public boolean start() {
      return false;
   }

   public String toString() {
      return "NonCancellable";
   }
}
