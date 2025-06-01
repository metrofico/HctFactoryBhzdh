package kotlinx.coroutines;

import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000D\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u001a\u0019\u0010\b\u001a\u00020\t2\u000e\b\u0004\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0087\b\u001a\u0012\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u001a\u0019\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\b\r\u001a\f\u0010\u0011\u001a\u00020\f*\u00020\u0002H\u0007\u001a\u0018\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a\u001c\u0010\u0011\u001a\u00020\f*\u00020\u00022\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015\u001a\u001e\u0010\u0011\u001a\u00020\f*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u001a\u0015\u0010\u0018\u001a\u00020\f*\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\f\u0010\u001a\u001a\u00020\f*\u00020\u0002H\u0007\u001a\u0018\u0010\u001a\u001a\u00020\f*\u00020\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a\u001c\u0010\u001a\u001a\u00020\f*\u00020\u00022\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015\u001a\f\u0010\u001a\u001a\u00020\f*\u00020\u0005H\u0007\u001a\u0018\u0010\u001a\u001a\u00020\f*\u00020\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a\u001c\u0010\u001a\u001a\u00020\f*\u00020\u00052\u0010\b\u0002\u0010\u0012\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015\u001a\u0014\u0010\u001b\u001a\u00020\t*\u00020\u00052\u0006\u0010\u001c\u001a\u00020\tH\u0000\u001a\n\u0010\u001d\u001a\u00020\f*\u00020\u0002\u001a\n\u0010\u001d\u001a\u00020\f*\u00020\u0005\u001a\u001b\u0010\u001e\u001a\u00020\u0013*\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¢\u0006\u0002\b\u001f\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0005*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "},
   d2 = {"isActive", "", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)Z", "job", "Lkotlinx/coroutines/Job;", "getJob", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Job;", "DisposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "block", "Lkotlin/Function0;", "", "Job", "Lkotlinx/coroutines/CompletableJob;", "parent", "Job0", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "message", "", "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelChildren", "disposeOnCompletion", "handle", "ensureActive", "orCancellation", "orCancellation$JobKt__JobKt", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/JobKt"
)
final class JobKt__JobKt {
   public static final DisposableHandle DisposableHandle(Function0 var0) {
      return (DisposableHandle)(new DisposableHandle(var0) {
         final Function0 $block;

         public {
            this.$block = var1;
         }

         public void dispose() {
            this.$block.invoke();
         }
      });
   }

   public static final CompletableJob Job(Job var0) {
      return (CompletableJob)(new JobImpl(var0));
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final Job Job(Job var0) {
      return (Job)JobKt.Job(var0);
   }

   // $FF: synthetic method
   public static CompletableJob Job$default(Job var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = null;
         Job var3 = (Job)null;
      }

      return JobKt.Job(var0);
   }

   // $FF: synthetic method
   public static Job Job$default(Job var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = null;
         Job var3 = (Job)null;
      }

      return JobKt.Job(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancel(CoroutineContext var0) {
      JobKt.cancel(var0, (CancellationException)null);
   }

   public static final void cancel(CoroutineContext var0, CancellationException var1) {
      Job var2 = (Job)var0.get((CoroutineContext.Key)Job.Key);
      if (var2 != null) {
         var2.cancel(var1);
      }

   }

   public static final void cancel(Job var0, String var1, Throwable var2) {
      var0.cancel(ExceptionsKt.CancellationException(var1, var2));
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final boolean cancel(CoroutineContext var0, Throwable var1) {
      CoroutineContext.Element var2 = var0.get((CoroutineContext.Key)Job.Key);
      CoroutineContext.Element var3 = var2;
      if (!(var2 instanceof JobSupport)) {
         var3 = null;
      }

      JobSupport var4 = (JobSupport)var3;
      if (var4 != null) {
         var4.cancelInternal(orCancellation$JobKt__JobKt(var1, (Job)var4));
         return true;
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   public static void cancel$default(CoroutineContext var0, CancellationException var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         CancellationException var4 = (CancellationException)null;
      }

      JobKt.cancel(var0, var1);
   }

   // $FF: synthetic method
   public static void cancel$default(Job var0, String var1, Throwable var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
         Throwable var5 = (Throwable)null;
      }

      JobKt.cancel(var0, var1, var2);
   }

   // $FF: synthetic method
   public static boolean cancel$default(CoroutineContext var0, Throwable var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         Throwable var4 = (Throwable)null;
      }

      return JobKt.cancel(var0, var1);
   }

   public static final Object cancelAndJoin(Job var0, Continuation var1) {
      Job.DefaultImpls.cancel$default(var0, (CancellationException)null, 1, (Object)null);
      Object var2 = var0.join(var1);
      return var2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var2 : Unit.INSTANCE;
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(CoroutineContext var0) {
      JobKt.cancelChildren((CoroutineContext)var0, (CancellationException)null);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(CoroutineContext var0, Throwable var1) {
      Job var3 = (Job)var0.get((CoroutineContext.Key)Job.Key);
      if (var3 != null) {
         Iterator var4 = var3.getChildren().iterator();

         while(var4.hasNext()) {
            Job var2 = (Job)var4.next();
            Job var5 = var2;
            if (!(var2 instanceof JobSupport)) {
               var5 = null;
            }

            JobSupport var6 = (JobSupport)var5;
            if (var6 != null) {
               var6.cancelInternal(orCancellation$JobKt__JobKt(var1, var3));
            }
         }
      }

   }

   public static final void cancelChildren(CoroutineContext var0, CancellationException var1) {
      Job var2 = (Job)var0.get((CoroutineContext.Key)Job.Key);
      if (var2 != null) {
         Sequence var3 = var2.getChildren();
         if (var3 != null) {
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
               ((Job)var4.next()).cancel(var1);
            }
         }
      }

   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(Job var0) {
      JobKt.cancelChildren((Job)var0, (CancellationException)null);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(Job var0, Throwable var1) {
      Iterator var4 = var0.getChildren().iterator();

      while(var4.hasNext()) {
         Job var3 = (Job)var4.next();
         Job var2 = var3;
         if (!(var3 instanceof JobSupport)) {
            var2 = null;
         }

         JobSupport var5 = (JobSupport)var2;
         if (var5 != null) {
            var5.cancelInternal(orCancellation$JobKt__JobKt(var1, var0));
         }
      }

   }

   public static final void cancelChildren(Job var0, CancellationException var1) {
      Iterator var2 = var0.getChildren().iterator();

      while(var2.hasNext()) {
         ((Job)var2.next()).cancel(var1);
      }

   }

   // $FF: synthetic method
   public static void cancelChildren$default(CoroutineContext var0, Throwable var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         Throwable var4 = (Throwable)null;
      }

      JobKt.cancelChildren(var0, var1);
   }

   // $FF: synthetic method
   public static void cancelChildren$default(CoroutineContext var0, CancellationException var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         CancellationException var4 = (CancellationException)null;
      }

      JobKt.cancelChildren(var0, var1);
   }

   // $FF: synthetic method
   public static void cancelChildren$default(Job var0, Throwable var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         Throwable var4 = (Throwable)null;
      }

      JobKt.cancelChildren(var0, var1);
   }

   // $FF: synthetic method
   public static void cancelChildren$default(Job var0, CancellationException var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         CancellationException var4 = (CancellationException)null;
      }

      JobKt.cancelChildren(var0, var1);
   }

   public static final DisposableHandle disposeOnCompletion(Job var0, DisposableHandle var1) {
      return var0.invokeOnCompletion((Function1)((CompletionHandlerBase)(new DisposeOnCompletion(var0, var1))));
   }

   public static final void ensureActive(CoroutineContext var0) {
      Job var1 = (Job)var0.get((CoroutineContext.Key)Job.Key);
      if (var1 != null) {
         JobKt.ensureActive(var1);
      }

   }

   public static final void ensureActive(Job var0) {
      if (!var0.isActive()) {
         throw (Throwable)var0.getCancellationException();
      }
   }

   public static final Job getJob(CoroutineContext var0) {
      Job var1 = (Job)var0.get((CoroutineContext.Key)Job.Key);
      if (var1 != null) {
         return var1;
      } else {
         throw (Throwable)(new IllegalStateException(("Current context doesn't contain Job in it: " + var0).toString()));
      }
   }

   public static final boolean isActive(CoroutineContext var0) {
      Job var2 = (Job)var0.get((CoroutineContext.Key)Job.Key);
      boolean var1 = true;
      if (var2 == null || !var2.isActive()) {
         var1 = false;
      }

      return var1;
   }

   private static final Throwable orCancellation$JobKt__JobKt(Throwable var0, Job var1) {
      if (var0 == null) {
         var0 = (Throwable)(new JobCancellationException("Job was cancelled", (Throwable)null, var1));
      }

      return var0;
   }
}
