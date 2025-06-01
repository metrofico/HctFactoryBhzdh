package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.SystemPropsKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u000b\u001a\u00020\fH\u0000\u001a4\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\u0006\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0013H\u0080\b¢\u0006\u0002\u0010\u0014\u001a\u0014\u0010\u0015\u001a\u00020\b*\u00020\u00162\u0006\u0010\u000f\u001a\u00020\bH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0017"},
   d2 = {"COROUTINES_SCHEDULER_PROPERTY_NAME", "", "DEBUG_THREAD_NAME_SEPARATOR", "useCoroutinesScheduler", "", "getUseCoroutinesScheduler", "()Z", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "createDefaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "withCoroutineContext", "T", "context", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "newCoroutineContext", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CoroutineContextKt {
   public static final String COROUTINES_SCHEDULER_PROPERTY_NAME = "kotlinx.coroutines.scheduler";
   private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";
   private static final boolean useCoroutinesScheduler;

   static {
      boolean var1;
      label28: {
         String var2;
         label27: {
            var2 = SystemPropsKt.systemProp("kotlinx.coroutines.scheduler");
            if (var2 != null) {
               int var0 = var2.hashCode();
               if (var0 != 0) {
                  if (var0 != 3551) {
                     if (var0 == 109935 && var2.equals("off")) {
                        var1 = false;
                        break label28;
                     }
                     break label27;
                  }

                  if (!var2.equals("on")) {
                     break label27;
                  }
               } else if (!var2.equals("")) {
                  break label27;
               }
            }

            var1 = true;
            break label28;
         }

         throw (Throwable)(new IllegalStateException(("System property 'kotlinx.coroutines.scheduler' has unrecognized value '" + var2 + '\'').toString()));
      }

      useCoroutinesScheduler = var1;
   }

   public static final CoroutineDispatcher createDefaultDispatcher() {
      Object var0;
      if (useCoroutinesScheduler) {
         var0 = DefaultScheduler.INSTANCE;
      } else {
         var0 = CommonPool.INSTANCE;
      }

      return (CoroutineDispatcher)var0;
   }

   public static final String getCoroutineName(CoroutineContext var0) {
      if (!DebugKt.getDEBUG()) {
         return null;
      } else {
         CoroutineId var1 = (CoroutineId)var0.get((CoroutineContext.Key)CoroutineId.Key);
         if (var1 == null) {
            return null;
         } else {
            CoroutineName var2 = (CoroutineName)var0.get((CoroutineContext.Key)CoroutineName.Key);
            String var3;
            if (var2 != null) {
               var3 = var2.getName();
               if (var3 != null) {
                  return var3 + '#' + var1.getId();
               }
            }

            var3 = "coroutine";
            return var3 + '#' + var1.getId();
         }
      }
   }

   public static final boolean getUseCoroutinesScheduler() {
      return useCoroutinesScheduler;
   }

   public static final CoroutineContext newCoroutineContext(CoroutineScope var0, CoroutineContext var1) {
      CoroutineContext var2 = var0.getCoroutineContext().plus(var1);
      CoroutineContext var3;
      if (DebugKt.getDEBUG()) {
         var3 = var2.plus((CoroutineContext)(new CoroutineId(DebugKt.getCOROUTINE_ID().incrementAndGet())));
      } else {
         var3 = var2;
      }

      var1 = var3;
      if (var2 != Dispatchers.getDefault()) {
         var1 = var3;
         if (var2.get((CoroutineContext.Key)ContinuationInterceptor.Key) == null) {
            var1 = var3.plus((CoroutineContext)Dispatchers.getDefault());
         }
      }

      return var1;
   }

   public static final Object withCoroutineContext(CoroutineContext var0, Object var1, Function0 var2) {
      var1 = ThreadContextKt.updateThreadContext(var0, var1);

      Object var5;
      try {
         var5 = var2.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         ThreadContextKt.restoreThreadContext(var0, var1);
         InlineMarker.finallyEnd(1);
      }

      return var5;
   }
}
