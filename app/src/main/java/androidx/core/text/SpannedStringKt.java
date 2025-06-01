package androidx.core.text;

import android.text.Spanned;
import android.text.SpannedString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\u001a:\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\b\u001a\r\u0010\t\u001a\u00020\u0004*\u00020\nH\u0086\b¨\u0006\u000b"},
   d2 = {"getSpans", "", "T", "", "Landroid/text/Spanned;", "start", "", "end", "(Landroid/text/Spanned;II)[Ljava/lang/Object;", "toSpanned", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SpannedStringKt {
   private static final Object[] getSpans(Spanned var0, int var1, int var2) {
      Intrinsics.reifiedOperationMarker(4, "T");
      Object[] var3 = var0.getSpans(var1, var2, Object.class);
      Intrinsics.checkExpressionValueIsNotNull(var3, "getSpans(start, end, T::class.java)");
      return var3;
   }

   // $FF: synthetic method
   static Object[] getSpans$default(Spanned var0, int var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = 0;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.length();
      }

      Intrinsics.reifiedOperationMarker(4, "T");
      Object[] var5 = var0.getSpans(var1, var2, Object.class);
      Intrinsics.checkExpressionValueIsNotNull(var5, "getSpans(start, end, T::class.java)");
      return var5;
   }

   public static final Spanned toSpanned(CharSequence var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toSpanned");
      SpannedString var1 = SpannedString.valueOf(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "SpannedString.valueOf(this)");
      return (Spanned)var1;
   }
}
