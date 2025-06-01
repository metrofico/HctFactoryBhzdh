package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"kotlinx/coroutines/JobKt__FutureKt", "kotlinx/coroutines/JobKt__JobKt"},
   k = 4,
   mv = {1, 4, 0}
)
public final class JobKt {
   public static final DisposableHandle DisposableHandle(Function0 var0) {
      return JobKt__JobKt.DisposableHandle(var0);
   }

   public static final CompletableJob Job(Job var0) {
      return JobKt__JobKt.Job(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final Job Job(Job var0) {
      return JobKt__JobKt.Job(var0);
   }

   // $FF: synthetic method
   public static CompletableJob Job$default(Job var0, int var1, Object var2) {
      return JobKt__JobKt.Job$default(var0, var1, var2);
   }

   // $FF: synthetic method
   public static Job Job$default(Job var0, int var1, Object var2) {
      return JobKt__JobKt.Job$default(var0, var1, var2);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancel(CoroutineContext var0) {
      JobKt__JobKt.cancel(var0);
   }

   public static final void cancel(CoroutineContext var0, CancellationException var1) {
      JobKt__JobKt.cancel(var0, var1);
   }

   public static final void cancel(Job var0, String var1, Throwable var2) {
      JobKt__JobKt.cancel(var0, var1, var2);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final boolean cancel(CoroutineContext var0, Throwable var1) {
      return JobKt__JobKt.cancel(var0, var1);
   }

   // $FF: synthetic method
   public static void cancel$default(CoroutineContext var0, CancellationException var1, int var2, Object var3) {
      JobKt__JobKt.cancel$default(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void cancel$default(Job var0, String var1, Throwable var2, int var3, Object var4) {
      JobKt__JobKt.cancel$default(var0, var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public static boolean cancel$default(CoroutineContext var0, Throwable var1, int var2, Object var3) {
      return JobKt__JobKt.cancel$default(var0, var1, var2, var3);
   }

   public static final Object cancelAndJoin(Job var0, Continuation var1) {
      return JobKt__JobKt.cancelAndJoin(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(CoroutineContext var0) {
      JobKt__JobKt.cancelChildren(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(CoroutineContext var0, Throwable var1) {
      JobKt__JobKt.cancelChildren(var0, var1);
   }

   public static final void cancelChildren(CoroutineContext var0, CancellationException var1) {
      JobKt__JobKt.cancelChildren(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(Job var0) {
      JobKt__JobKt.cancelChildren(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
   )
   public static final void cancelChildren(Job var0, Throwable var1) {
      JobKt__JobKt.cancelChildren(var0, var1);
   }

   public static final void cancelChildren(Job var0, CancellationException var1) {
      JobKt__JobKt.cancelChildren(var0, var1);
   }

   // $FF: synthetic method
   public static void cancelChildren$default(CoroutineContext var0, Throwable var1, int var2, Object var3) {
      JobKt__JobKt.cancelChildren$default(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void cancelChildren$default(CoroutineContext var0, CancellationException var1, int var2, Object var3) {
      JobKt__JobKt.cancelChildren$default(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void cancelChildren$default(Job var0, Throwable var1, int var2, Object var3) {
      JobKt__JobKt.cancelChildren$default(var0, var1, var2, var3);
   }

   // $FF: synthetic method
   public static void cancelChildren$default(Job var0, CancellationException var1, int var2, Object var3) {
      JobKt__JobKt.cancelChildren$default(var0, var1, var2, var3);
   }

   public static final void cancelFutureOnCancellation(CancellableContinuation var0, Future var1) {
      JobKt__FutureKt.cancelFutureOnCancellation(var0, var1);
   }

   public static final DisposableHandle cancelFutureOnCompletion(Job var0, Future var1) {
      return JobKt__FutureKt.cancelFutureOnCompletion(var0, var1);
   }

   public static final DisposableHandle disposeOnCompletion(Job var0, DisposableHandle var1) {
      return JobKt__JobKt.disposeOnCompletion(var0, var1);
   }

   public static final void ensureActive(CoroutineContext var0) {
      JobKt__JobKt.ensureActive(var0);
   }

   public static final void ensureActive(Job var0) {
      JobKt__JobKt.ensureActive(var0);
   }

   public static final Job getJob(CoroutineContext var0) {
      return JobKt__JobKt.getJob(var0);
   }

   public static final boolean isActive(CoroutineContext var0) {
      return JobKt__JobKt.isActive(var0);
   }
}
