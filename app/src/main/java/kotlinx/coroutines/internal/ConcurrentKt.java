package kotlinx.coroutines.internal;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0080\b\u001a\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u0002H\u00040\fj\b\u0012\u0004\u0012\u0002H\u0004`\r\"\u0004\b\u0000\u0010\u0004H\u0000\u001a*\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f*\u00060\u0010j\u0002`\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0013H\u0080\b¢\u0006\u0002\u0010\u0014\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*\f\b\u0000\u0010\u0015\"\u00020\u00102\u00020\u0010¨\u0006\u0016"},
   d2 = {"REMOVE_FUTURE_ON_CANCEL", "Ljava/lang/reflect/Method;", "identitySet", "", "E", "expectedSize", "", "removeFutureOnCancel", "", "executor", "Ljava/util/concurrent/Executor;", "subscriberList", "", "Lkotlinx/coroutines/internal/SubscribersList;", "withLock", "T", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ReentrantLock", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class ConcurrentKt {
   private static final Method REMOVE_FUTURE_ON_CANCEL;

   static {
      Method var0;
      try {
         var0 = ScheduledThreadPoolExecutor.class.getMethod("setRemoveOnCancelPolicy", Boolean.TYPE);
      } finally {
         ;
      }

      REMOVE_FUTURE_ON_CANCEL = var0;
   }

   // $FF: synthetic method
   public static void ReentrantLock$annotations() {
   }

   public static final Set identitySet(int var0) {
      return Collections.newSetFromMap((Map)(new IdentityHashMap(var0)));
   }

   public static final boolean removeFutureOnCancel(Executor var0) {
      Executor var1 = var0;

      boolean var10001;
      label210: {
         try {
            if (var0 instanceof ScheduledThreadPoolExecutor) {
               break label210;
            }
         } catch (Throwable var21) {
            var10001 = false;
            return false;
         }

         var1 = null;
      }

      ScheduledThreadPoolExecutor var22;
      try {
         var22 = (ScheduledThreadPoolExecutor)var1;
      } catch (Throwable var20) {
         var10001 = false;
         return false;
      }

      if (var22 != null) {
         Method var23;
         try {
            var23 = REMOVE_FUTURE_ON_CANCEL;
         } catch (Throwable var19) {
            var10001 = false;
            return false;
         }

         if (var23 != null) {
            try {
               var23.invoke(var22, true);
               return true;
            } catch (Throwable var18) {
               var10001 = false;
               return false;
            }
         }
      }

      return false;
   }

   public static final List subscriberList() {
      return (List)(new CopyOnWriteArrayList());
   }

   public static final Object withLock(ReentrantLock var0, Function0 var1) {
      Lock var4 = (Lock)var0;
      var4.lock();

      Object var5;
      try {
         var5 = var1.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         var4.unlock();
         InlineMarker.finallyEnd(1);
      }

      return var5;
   }
}
