package kotlinx.coroutines.android;

import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import android.view.Choreographer;
import java.lang.reflect.Constructor;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0011\u0010\b\u001a\u00020\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0002\u001a\u0016\u0010\u000e\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\rH\u0002\u001a\u001d\u0010\u000f\u001a\u00020\u0003*\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0007¢\u0006\u0002\b\u0013\u001a\u0014\u0010\u0014\u001a\u00020\u0010*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0004\u0010\u0005\"\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"},
   d2 = {"MAX_DELAY", "", "Main", "Lkotlinx/coroutines/android/HandlerDispatcher;", "getMain$annotations", "()V", "choreographer", "Landroid/view/Choreographer;", "awaitFrame", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postFrameCallback", "", "cont", "Lkotlinx/coroutines/CancellableContinuation;", "updateChoreographerAndPostFrameCallback", "asCoroutineDispatcher", "Landroid/os/Handler;", "name", "", "from", "asHandler", "Landroid/os/Looper;", "async", "", "kotlinx-coroutines-android"},
   k = 2,
   mv = {1, 4, 0}
)
public final class HandlerDispatcherKt {
   private static final long MAX_DELAY = 4611686018427387903L;
   public static final HandlerDispatcher Main;
   private static volatile Choreographer choreographer;

   static {
      Object var1 = null;

      Result.Companion var0;
      Object var5;
      label32:
      try {
         var0 = Result.Companion;
         HandlerContext var6 = new HandlerContext(asHandler(Looper.getMainLooper(), true), (String)null, 2, (DefaultConstructorMarker)null);
         var5 = Result.constructor_impl(var6);
      } catch (Throwable var4) {
         var0 = Result.Companion;
         var5 = Result.constructor_impl(ResultKt.createFailure(var4));
         break label32;
      }

      if (Result.isFailure_impl(var5)) {
         var5 = var1;
      }

      Main = (HandlerDispatcher)var5;
   }

   // $FF: synthetic method
   public static final void access$postFrameCallback(Choreographer var0, CancellableContinuation var1) {
      postFrameCallback(var0, var1);
   }

   public static final Handler asHandler(Looper var0, boolean var1) {
      if (var1 && VERSION.SDK_INT >= 16) {
         if (VERSION.SDK_INT >= 28) {
            Object var4 = Handler.class.getDeclaredMethod("createAsync", Looper.class).invoke((Object)null, var0);
            if (var4 != null) {
               return (Handler)var4;
            } else {
               throw new NullPointerException("null cannot be cast to non-null type android.os.Handler");
            }
         } else {
            Constructor var2;
            try {
               var2 = Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE);
            } catch (NoSuchMethodException var3) {
               return new Handler(var0);
            }

            return (Handler)var2.newInstance(var0, null, true);
         }
      } else {
         return new Handler(var0);
      }
   }

   public static final Object awaitFrame(Continuation var0) {
      Choreographer var1 = choreographer;
      Object var4;
      if (var1 != null) {
         CancellableContinuationImpl var5 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var0), 1);
         var5.initCancellability();
         access$postFrameCallback(var1, (CancellableContinuation)var5);
         var4 = var5.getResult();
         if (var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var0);
         }

         return var4;
      } else {
         CancellableContinuationImpl var3 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(var0), 1);
         var3.initCancellability();
         CancellableContinuation var2 = (CancellableContinuation)var3;
         Dispatchers.getMain().dispatch((CoroutineContext)EmptyCoroutineContext.INSTANCE, (Runnable)(new Runnable(var2) {
            final CancellableContinuation $cont$inlined;

            public {
               this.$cont$inlined = var1;
            }

            public final void run() {
               HandlerDispatcherKt.updateChoreographerAndPostFrameCallback(this.$cont$inlined);
            }
         }));
         var4 = var3.getResult();
         if (var4 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(var0);
         }

         return var4;
      }
   }

   public static final HandlerDispatcher from(Handler var0) {
      return from$default(var0, (String)null, 1, (Object)null);
   }

   public static final HandlerDispatcher from(Handler var0, String var1) {
      return (HandlerDispatcher)(new HandlerContext(var0, var1));
   }

   // $FF: synthetic method
   public static HandlerDispatcher from$default(Handler var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
         String var4 = (String)null;
      }

      return from(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      level = DeprecationLevel.HIDDEN,
      message = "Use Dispatchers.Main instead"
   )
   public static void getMain$annotations() {
   }

   private static final void postFrameCallback(Choreographer var0, CancellableContinuation var1) {
      var0.postFrameCallback((Choreographer.FrameCallback)(new Choreographer.FrameCallback(var1) {
         final CancellableContinuation $cont;

         {
            this.$cont = var1;
         }

         public final void doFrame(long var1) {
            this.$cont.resumeUndispatched((CoroutineDispatcher)Dispatchers.getMain(), var1);
         }
      }));
   }

   private static final void updateChoreographerAndPostFrameCallback(CancellableContinuation var0) {
      Choreographer var1 = choreographer;
      if (var1 == null) {
         var1 = Choreographer.getInstance();
         Intrinsics.checkNotNull(var1);
         choreographer = var1;
      }

      postFrameCallback(var1, var0);
   }
}
