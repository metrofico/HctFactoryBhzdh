package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"},
   d2 = {"Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlin/coroutines/CoroutineContext$Element;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "Key", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface CoroutineExceptionHandler extends CoroutineContext.Element {
   Key Key = CoroutineExceptionHandler.Key.$$INSTANCE;

   void handleException(CoroutineContext var1, Throwable var2);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      public static Object fold(CoroutineExceptionHandler var0, Object var1, Function2 var2) {
         return Element.DefaultImpls.fold((Element)var0, var1, var2);
      }

      public static Element get(CoroutineExceptionHandler var0, CoroutineContext.Key var1) {
         return Element.DefaultImpls.get((Element)var0, var1);
      }

      public static CoroutineContext minusKey(CoroutineExceptionHandler var0, CoroutineContext.Key var1) {
         return Element.DefaultImpls.minusKey((Element)var0, var1);
      }

      public static CoroutineContext plus(CoroutineExceptionHandler var0, CoroutineContext var1) {
         return Element.DefaultImpls.plus((Element)var0, var1);
      }
   }

   @Metadata(
      bv = {1, 0, 3},
      d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"},
      d2 = {"Lkotlinx/coroutines/CoroutineExceptionHandler$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "()V", "kotlinx-coroutines-core"},
      k = 1,
      mv = {1, 4, 0}
   )
   public static final class Key implements CoroutineContext.Key {
      static final Key $$INSTANCE = new Key();

      private Key() {
      }
   }
}
