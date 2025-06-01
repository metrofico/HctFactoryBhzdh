package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"},
   d2 = {"Lkotlinx/coroutines/CompletableDeferred;", "T", "Lkotlinx/coroutines/Deferred;", "complete", "", "value", "(Ljava/lang/Object;)Z", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"},
   k = 1,
   mv = {1, 4, 0}
)
public interface CompletableDeferred extends Deferred {
   boolean complete(Object var1);

   boolean completeExceptionally(Throwable var1);

   @Metadata(
      bv = {1, 0, 3},
      k = 3,
      mv = {1, 4, 0}
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      @Deprecated(
         level = DeprecationLevel.HIDDEN,
         message = "Since 1.2.0, binary compatibility with versions <= 1.1.x"
      )
      public static void cancel(CompletableDeferred var0) {
         Deferred.DefaultImpls.cancel((Deferred)var0);
      }

      public static Object fold(CompletableDeferred var0, Object var1, Function2 var2) {
         return Deferred.DefaultImpls.fold((Deferred)var0, var1, var2);
      }

      public static Element get(CompletableDeferred var0, CoroutineContext.Key var1) {
         return Deferred.DefaultImpls.get((Deferred)var0, var1);
      }

      public static CoroutineContext minusKey(CompletableDeferred var0, CoroutineContext.Key var1) {
         return Deferred.DefaultImpls.minusKey((Deferred)var0, var1);
      }

      public static CoroutineContext plus(CompletableDeferred var0, CoroutineContext var1) {
         return Deferred.DefaultImpls.plus((Deferred)var0, var1);
      }

      @Deprecated(
         level = DeprecationLevel.ERROR,
         message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`."
      )
      public static Job plus(CompletableDeferred var0, Job var1) {
         return Deferred.DefaultImpls.plus((Deferred)var0, var1);
      }
   }
}
