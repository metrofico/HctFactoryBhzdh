package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a,\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\nø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"},
   d2 = {"CompletableDeferred", "Lkotlinx/coroutines/CompletableDeferred;", "T", "value", "(Ljava/lang/Object;)Lkotlinx/coroutines/CompletableDeferred;", "parent", "Lkotlinx/coroutines/Job;", "completeWith", "", "result", "Lkotlin/Result;", "(Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Object;)Z", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class CompletableDeferredKt {
   public static final CompletableDeferred CompletableDeferred(Object var0) {
      CompletableDeferredImpl var1 = new CompletableDeferredImpl((Job)null);
      var1.complete(var0);
      return (CompletableDeferred)var1;
   }

   public static final CompletableDeferred CompletableDeferred(Job var0) {
      return (CompletableDeferred)(new CompletableDeferredImpl(var0));
   }

   // $FF: synthetic method
   public static CompletableDeferred CompletableDeferred$default(Job var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = null;
         Job var3 = (Job)null;
      }

      return CompletableDeferred(var0);
   }

   public static final boolean completeWith(CompletableDeferred var0, Object var1) {
      Throwable var3 = Result.exceptionOrNull_impl(var1);
      boolean var2;
      if (var3 == null) {
         var2 = var0.complete(var1);
      } else {
         var2 = var0.completeExceptionally(var3);
      }

      return var2;
   }
}
