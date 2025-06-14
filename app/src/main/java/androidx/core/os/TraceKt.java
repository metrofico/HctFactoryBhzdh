package androidx.core.os;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0086\b¢\u0006\u0002\u0010\u0006¨\u0006\u0007"},
   d2 = {"trace", "T", "sectionName", "", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class TraceKt {
   public static final Object trace(String var0, Function0 var1) {
      Intrinsics.checkParameterIsNotNull(var0, "sectionName");
      Intrinsics.checkParameterIsNotNull(var1, "block");
      TraceCompat.beginSection(var0);

      Object var4;
      try {
         var4 = var1.invoke();
      } finally {
         InlineMarker.finallyStart(1);
         TraceCompat.endSection();
         InlineMarker.finallyEnd(1);
      }

      return var4;
   }
}
