package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u000e:\u0002\u000b\fB\u001d\u0012\u0014\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0086@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tR$\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"},
   d2 = {"Lkotlinx/coroutines/AwaitAll;", "T", "", "Lkotlinx/coroutines/Deferred;", "deferreds", "<init>", "([Lkotlinx/coroutines/Deferred;)V", "", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "[Lkotlinx/coroutines/Deferred;", "AwaitAllNode", "DisposeHandlersOnCancel", "kotlinx-coroutines-core", ""},
   k = 1,
   mv = {1, 4, 0}
)
final class AwaitAll {
   static final AtomicIntegerFieldUpdater notCompletedCount$FU = AtomicIntegerFieldUpdater.newUpdater(AwaitAll.class, "notCompletedCount");
   private final Deferred[] deferreds;
   volatile int notCompletedCount;

   public AwaitAll(Deferred[] var1) {
      this.deferreds = var1;
      this.notCompletedCount = var1.length;
   }

   public final Object await(Continuation var1) {
      CancellableContinuationImpl var7 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var1), 1);
      var7.initCancellability();
      CancellableContinuation var6 = (CancellableContinuation)var7;
      int var4 = access$getDeferreds$p(this).length;
      AwaitAllNode[] var8 = new AwaitAllNode[var4];
      byte var3 = 0;

      int var2;
      for(var2 = 0; var2 < var4; ++var2) {
         int var5 = ((Number)Boxing.boxInt(var2)).intValue();
         Deferred var10 = access$getDeferreds$p(this)[var5];
         var10.start();
         AwaitAllNode var9 = new AwaitAllNode(this, var6, (Job)var10);
         var9.setHandle(var10.invokeOnCompletion((Function1)((CompletionHandlerBase)var9)));
         var8[var2] = var9;
      }

      DisposeHandlersOnCancel var12 = new DisposeHandlersOnCancel(this, var8);

      for(var2 = var3; var2 < var4; ++var2) {
         var8[var2].setDisposer(var12);
      }

      if (var6.isCompleted()) {
         var12.disposeAll();
      } else {
         var6.invokeOnCancellation((Function1)((CancelHandlerBase)var12));
      }

      Object var11 = var7.getResult();
      if (var11 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(var1);
      }

      return var11;
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00040\u001eB#\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fR\"\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u00018\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR<\u0010\u0015\u001a\u000e\u0018\u00010\u000eR\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0012\u0010\u0010\u001a\u000e\u0018\u00010\u000eR\b\u0012\u0004\u0012\u00028\u00000\u000f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\"\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006\u001d"},
      d2 = {"Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/CancellableContinuation;", "", "continuation", "Lkotlinx/coroutines/Job;", "job", "<init>", "(Lkotlinx/coroutines/AwaitAll;Lkotlinx/coroutines/CancellableContinuation;Lkotlinx/coroutines/Job;)V", "", "cause", "", "invoke", "(Ljava/lang/Throwable;)V", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/AwaitAll;", "value", "getDisposer", "()Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "setDisposer", "(Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;)V", "disposer", "Lkotlinx/coroutines/DisposableHandle;", "handle", "Lkotlinx/coroutines/DisposableHandle;", "getHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "kotlinx-coroutines-core", "Lkotlinx/coroutines/JobNode;"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class AwaitAllNode extends JobNode {
      private volatile Object _disposer;
      private final CancellableContinuation continuation;
      public DisposableHandle handle;
      final AwaitAll this$0;

      public AwaitAllNode(AwaitAll var1, CancellableContinuation var2, Job var3) {
         super(var3);
         this.this$0 = var1;
         this.continuation = var2;
         this._disposer = null;
      }

      public final DisposeHandlersOnCancel getDisposer() {
         return (DisposeHandlersOnCancel)this._disposer;
      }

      public final DisposableHandle getHandle() {
         DisposableHandle var1 = this.handle;
         if (var1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("handle");
         }

         return var1;
      }

      public void invoke(Throwable var1) {
         if (var1 != null) {
            Object var6 = this.continuation.tryResumeWithException(var1);
            if (var6 != null) {
               this.continuation.completeResume(var6);
               DisposeHandlersOnCancel var7 = this.getDisposer();
               if (var7 != null) {
                  var7.disposeAll();
               }
            }
         } else {
            AwaitAll var8 = this.this$0;
            if (AwaitAll.notCompletedCount$FU.decrementAndGet(var8) == 0) {
               Continuation var9 = (Continuation)this.continuation;
               Deferred[] var4 = this.this$0.deferreds;
               Collection var5 = (Collection)(new ArrayList(var4.length));
               int var3 = var4.length;

               for(int var2 = 0; var2 < var3; ++var2) {
                  var5.add(var4[var2].getCompleted());
               }

               List var11 = (List)var5;
               Result.Companion var10 = Result.Companion;
               var9.resumeWith(Result.constructor_impl(var11));
            }
         }

      }

      public final void setDisposer(DisposeHandlersOnCancel var1) {
         this._disposer = var1;
      }

      public final void setHandle(DisposableHandle var1) {
         this.handle = var1;
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\b\u001a\u00020\tJ\u0013\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R \u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000f"},
      d2 = {"Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "nodes", "", "Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/AwaitAll;", "(Lkotlinx/coroutines/AwaitAll;[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;)V", "[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "disposeAll", "", "invoke", "cause", "", "toString", "", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   private final class DisposeHandlersOnCancel extends CancelHandler {
      private final AwaitAllNode[] nodes;
      final AwaitAll this$0;

      public DisposeHandlersOnCancel(AwaitAll var1, AwaitAllNode[] var2) {
         this.this$0 = var1;
         this.nodes = var2;
      }

      public final void disposeAll() {
         AwaitAllNode[] var3 = this.nodes;
         int var2 = var3.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            var3[var1].getHandle().dispose();
         }

      }

      public void invoke(Throwable var1) {
         this.disposeAll();
      }

      public String toString() {
         return "DisposeHandlersOnCancel[" + this.nodes + ']';
      }
   }
}
