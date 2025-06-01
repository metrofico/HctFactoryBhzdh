package kotlin.jdk7;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001aH\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"},
   d2 = {"closeFinally", "", "Ljava/lang/AutoCloseable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"},
   k = 2,
   mv = {1, 7, 1},
   pn = "kotlin",
   xi = 48
)
public final class AutoCloseableKt {
   public static final void closeFinally(AutoCloseable var0, Throwable var1) {
      if (var0 != null) {
         if (var1 == null) {
            var0.close();
         } else {
            try {
               var0.close();
            } catch (Throwable var3) {
               ExceptionsKt.addSuppressed(var1, var3);
               return;
            }
         }
      }

   }

   private static final Object use(AutoCloseable var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "block");

      Object var10;
      try {
         var10 = var1.invoke(var0);
      } catch (Throwable var8) {
         Throwable var9 = var8;

         try {
            throw var9;
         } finally {
            InlineMarker.finallyStart(1);
            closeFinally(var0, var8);
            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      closeFinally(var0, (Throwable)null);
      InlineMarker.finallyEnd(1);
      return var10;
   }
}
