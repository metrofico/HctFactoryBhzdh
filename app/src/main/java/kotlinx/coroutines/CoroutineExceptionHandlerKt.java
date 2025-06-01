package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a%\u0010\u0000\u001a\u00020\u00012\u001a\b\u0004\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003H\u0086\b\u001a\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0000¨\u0006\r"},
   d2 = {"CoroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/CoroutineContext;", "", "", "handleCoroutineException", "context", "exception", "handlerException", "originalException", "thrownException", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CoroutineExceptionHandlerKt {
   public static final CoroutineExceptionHandler CoroutineExceptionHandler(Function2 var0) {
      return (CoroutineExceptionHandler)(new CoroutineExceptionHandler(var0, (CoroutineContext.Key)CoroutineExceptionHandler.Key) {
         final Function2 $handler;

         public {
            this.$handler = var1;
         }

         public void handleException(CoroutineContext var1, Throwable var2) {
            this.$handler.invoke(var1, var2);
         }
      });
   }

   public static final void handleCoroutineException(CoroutineContext param0, Throwable param1) {
      // $FF: Couldn't be decompiled
   }

   public static final Throwable handlerException(Throwable var0, Throwable var1) {
      if (var0 == var1) {
         return var0;
      } else {
         var1 = (Throwable)(new RuntimeException("Exception while trying to handle coroutine exception", var1));
         kotlin.ExceptionsKt.addSuppressed(var1, var0);
         return var1;
      }
   }
}
