package kotlin.io;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001aK\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u0082\u0002\u000f\n\u0005\b\u009920\u0001\n\u0006\b\u0011(\u000b0\u0001¨\u0006\f"},
   d2 = {"closeFinally", "", "Ljava/io/Closeable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Requires newer compiler version to be inlined correctly.", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class CloseableKt {
   public static final void closeFinally(Closeable var0, Throwable var1) {
      if (var0 != null) {
         if (var1 == null) {
            var0.close();
         } else {
            try {
               var0.close();
            } catch (Throwable var3) {
               kotlin.ExceptionsKt.addSuppressed(var1, var3);
               return;
            }
         }
      }

   }

   private static final Object use(Closeable var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "block");

      Object var15;
      try {
         var15 = var1.invoke(var0);
      } catch (Throwable var14) {
         Throwable var2 = var14;

         try {
            throw var2;
         } finally {
            InlineMarker.finallyStart(1);
            if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
               if (var0 != null) {
                  label130:
                  try {
                     var0.close();
                  } finally {
                     break label130;
                  }
               }
            } else {
               closeFinally(var0, var14);
            }

            InlineMarker.finallyEnd(1);
         }
      }

      InlineMarker.finallyStart(1);
      if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0)) {
         closeFinally(var0, (Throwable)null);
      } else if (var0 != null) {
         var0.close();
      }

      InlineMarker.finallyEnd(1);
      return var15;
   }
}
