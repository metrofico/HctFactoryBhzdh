package androidx.core.content;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH\u0087\b¨\u0006\t"},
   d2 = {"edit", "", "Landroid/content/SharedPreferences;", "commit", "", "action", "Lkotlin/Function1;", "Landroid/content/SharedPreferences$Editor;", "Lkotlin/ExtensionFunctionType;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class SharedPreferencesKt {
   public static final void edit(SharedPreferences var0, boolean var1, Function1 var2) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$edit");
      Intrinsics.checkParameterIsNotNull(var2, "action");
      SharedPreferences.Editor var3 = var0.edit();
      Intrinsics.checkExpressionValueIsNotNull(var3, "editor");
      var2.invoke(var3);
      if (var1) {
         var3.commit();
      } else {
         var3.apply();
      }

   }

   // $FF: synthetic method
   public static void edit$default(SharedPreferences var0, boolean var1, Function1 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = false;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$edit");
      Intrinsics.checkParameterIsNotNull(var2, "action");
      SharedPreferences.Editor var5 = var0.edit();
      Intrinsics.checkExpressionValueIsNotNull(var5, "editor");
      var2.invoke(var5);
      if (var1) {
         var5.commit();
      } else {
         var5.apply();
      }

   }
}
