package androidx.core.util;

import android.util.Half;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"},
   d2 = {"toHalf", "Landroid/util/Half;", "", "", "", "", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class HalfKt {
   public static final Half toHalf(double var0) {
      Half var2 = Half.valueOf((float)var0);
      Intrinsics.checkExpressionValueIsNotNull(var2, "Half.valueOf(this)");
      return var2;
   }

   public static final Half toHalf(float var0) {
      Half var1 = Half.valueOf(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Half.valueOf(this)");
      return var1;
   }

   public static final Half toHalf(String var0) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$toHalf");
      Half var1 = Half.valueOf(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Half.valueOf(this)");
      return var1;
   }

   public static final Half toHalf(short var0) {
      Half var1 = Half.valueOf(var0);
      Intrinsics.checkExpressionValueIsNotNull(var1, "Half.valueOf(this)");
      return var1;
   }
}
