package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aT\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"},
   d2 = {"runBlocking", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 5,
   mv = {1, 4, 0},
   xs = "kotlinx/coroutines/BuildersKt"
)
final class BuildersKt__BuildersKt {
   public static final Object runBlocking(CoroutineContext var0, Function2 var1) throws InterruptedException {
      Thread var6 = Thread.currentThread();
      ContinuationInterceptor var3 = (ContinuationInterceptor)var0.get((CoroutineContext.Key)ContinuationInterceptor.Key);
      EventLoop var8;
      if (var3 == null) {
         var8 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
         var0 = CoroutineContextKt.newCoroutineContext((CoroutineScope)GlobalScope.INSTANCE, var0.plus((CoroutineContext)var8));
      } else {
         boolean var2 = var3 instanceof EventLoop;
         Object var4 = null;
         if (!var2) {
            var3 = null;
         }

         label22: {
            EventLoop var5 = (EventLoop)var3;
            if (var5 != null) {
               var8 = (EventLoop)var4;
               if (var5.shouldBeProcessedFromContext()) {
                  var8 = var5;
               }

               if (var8 != null) {
                  break label22;
               }
            }

            var8 = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
         }

         var0 = CoroutineContextKt.newCoroutineContext((CoroutineScope)GlobalScope.INSTANCE, var0);
      }

      BlockingCoroutine var7 = new BlockingCoroutine(var0, var6, var8);
      var7.start(CoroutineStart.DEFAULT, var7, var1);
      return var7.joinBlocking();
   }

   // $FF: synthetic method
   public static Object runBlocking$default(CoroutineContext var0, Function2 var1, int var2, Object var3) throws InterruptedException {
      if ((var2 & 1) != 0) {
         var0 = (CoroutineContext)EmptyCoroutineContext.INSTANCE;
      }

      return BuildersKt.runBlocking(var0, var1);
   }
}
