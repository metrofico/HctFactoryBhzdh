package kotlinx.coroutines;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.sequences.SequencesKt;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"},
   d2 = {"handlers", "", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleCoroutineExceptionImpl", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CoroutineExceptionHandlerImplKt {
   private static final List handlers = SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(CoroutineExceptionHandler.class, CoroutineExceptionHandler.class.getClassLoader()).iterator()));

   public static final void handleCoroutineExceptionImpl(CoroutineContext var0, Throwable var1) {
      Iterator var2 = handlers.iterator();

      while(var2.hasNext()) {
         CoroutineExceptionHandler var3 = (CoroutineExceptionHandler)var2.next();

         try {
            var3.handleException(var0, var1);
         } catch (Throwable var6) {
            Thread var4 = Thread.currentThread();
            var4.getUncaughtExceptionHandler().uncaughtException(var4, CoroutineExceptionHandlerKt.handlerException(var1, var6));
            continue;
         }
      }

      Thread var7 = Thread.currentThread();
      var7.getUncaughtExceptionHandler().uncaughtException(var7, var1);
   }
}
