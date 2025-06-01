package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000H&¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\n¨\u0006\u000b"},
   d2 = {"Lkotlinx/coroutines/ThreadContextElement;", "S", "Lkotlin/coroutines/CoroutineContext$Element;", "restoreThreadContext", "", "context", "Lkotlin/coroutines/CoroutineContext;", "oldState", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "updateThreadContext", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/Object;", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface ThreadContextElement extends CoroutineContext.Element {
   void restoreThreadContext(CoroutineContext var1, Object var2);

   Object updateThreadContext(CoroutineContext var1);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      public static Object fold(ThreadContextElement var0, Object var1, Function2 var2) {
         return Element.DefaultImpls.fold((Element)var0, var1, var2);
      }

      public static Element get(ThreadContextElement var0, Key var1) {
         return Element.DefaultImpls.get((Element)var0, var1);
      }

      public static CoroutineContext minusKey(ThreadContextElement var0, Key var1) {
         return Element.DefaultImpls.minusKey((Element)var0, var1);
      }

      public static CoroutineContext plus(ThreadContextElement var0, CoroutineContext var1) {
         return Element.DefaultImpls.plus((Element)var0, var1);
      }
   }
}
