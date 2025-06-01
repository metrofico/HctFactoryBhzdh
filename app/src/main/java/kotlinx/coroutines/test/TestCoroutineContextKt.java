package kotlinx.coroutines.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¨\u0006\u0007"},
   d2 = {"withTestContext", "", "testContext", "Lkotlinx/coroutines/test/TestCoroutineContext;", "testBody", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "kotlinx-coroutines-core"},
   k = 2,
   mv = {1, 4, 0}
)
public final class TestCoroutineContextKt {
   @Deprecated(
      level = DeprecationLevel.WARNING,
      message = "This API has been deprecated to integrate with Structured Concurrency.",
      replaceWith = @ReplaceWith(
   expression = "testContext.runBlockingTest(testBody)",
   imports = {"kotlin.coroutines.test"}
)
   )
   public static final void withTestContext(TestCoroutineContext var0, Function1 var1) {
      var1.invoke(var0);
      Iterable var5 = (Iterable)var0.getExceptions();
      boolean var4 = var5 instanceof Collection;
      boolean var3 = true;
      boolean var2;
      if (var4 && ((Collection)var5).isEmpty()) {
         var2 = var3;
      } else {
         Iterator var6 = var5.iterator();

         while(true) {
            var2 = var3;
            if (!var6.hasNext()) {
               break;
            }

            if (!((Throwable)var6.next() instanceof CancellationException)) {
               var2 = false;
               break;
            }
         }
      }

      if (!var2) {
         throw (Throwable)(new AssertionError("Coroutine encountered unhandled exceptions:\n" + var0.getExceptions()));
      }
   }

   // $FF: synthetic method
   public static void withTestContext$default(TestCoroutineContext var0, Function1 var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var0 = new TestCoroutineContext((String)null, 1, (DefaultConstructorMarker)null);
      }

      withTestContext(var0, var1);
   }
}
