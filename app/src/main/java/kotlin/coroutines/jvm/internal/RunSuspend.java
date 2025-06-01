package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u0002J\u001e\u0010\u000f\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R(\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\tX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0011"},
   d2 = {"Lkotlin/coroutines/jvm/internal/RunSuspend;", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "result", "Lkotlin/Result;", "getResult-xLWZpok", "()Lkotlin/Result;", "setResult", "(Lkotlin/Result;)V", "await", "resumeWith", "(Ljava/lang/Object;)V", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
final class RunSuspend implements Continuation {
   private Result result;

   public RunSuspend() {
   }

   public final void await() {
      synchronized(this){}

      Throwable var10000;
      while(true) {
         Result var1;
         boolean var10001;
         try {
            var1 = this.result;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break;
         }

         if (var1 == null) {
            try {
               Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
               ((Object)this).wait();
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               break;
            }
         } else {
            try {
               ResultKt.throwOnFailure(var1.unbox_impl());
               return;
            } catch (Throwable var12) {
               var10000 = var12;
               var10001 = false;
               break;
            }
         }
      }

      Throwable var14 = var10000;
      throw var14;
   }

   public CoroutineContext getContext() {
      return (CoroutineContext)EmptyCoroutineContext.INSTANCE;
   }

   public final Result getResult_xLWZpok() {
      return this.result;
   }

   public void resumeWith(Object var1) {
      synchronized(this){}

      try {
         this.result = Result.box_impl(var1);
         Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
         ((Object)this).notifyAll();
         Unit var4 = Unit.INSTANCE;
      } finally {
         ;
      }

   }

   public final void setResult(Result var1) {
      this.result = var1;
   }
}
