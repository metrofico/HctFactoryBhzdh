package androidx.core.content;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0086\b¢\u0006\u0002\u0010\u0004\u001aN\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\f2\b\b\u0003\u0010\r\u001a\u00020\f2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b\u001a8\u0010\u0005\u001a\u00020\u0006*\u00020\u00032\b\b\u0001\u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000f¢\u0006\u0002\b\u0011H\u0086\b¨\u0006\u0013"},
   d2 = {"getSystemService", "T", "", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Object;", "withStyledAttributes", "", "set", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "", "defStyleRes", "block", "Lkotlin/Function1;", "Landroid/content/res/TypedArray;", "Lkotlin/ExtensionFunctionType;", "resourceId", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class ContextKt {
   private static final Object getSystemService(Context var0) {
      Intrinsics.reifiedOperationMarker(4, "T");
      return ContextCompat.getSystemService(var0, Object.class);
   }

   public static final void withStyledAttributes(Context var0, int var1, int[] var2, Function1 var3) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withStyledAttributes");
      Intrinsics.checkParameterIsNotNull(var2, "attrs");
      Intrinsics.checkParameterIsNotNull(var3, "block");
      TypedArray var4 = var0.obtainStyledAttributes(var1, var2);
      var3.invoke(var4);
      var4.recycle();
   }

   public static final void withStyledAttributes(Context var0, AttributeSet var1, int[] var2, int var3, int var4, Function1 var5) {
      Intrinsics.checkParameterIsNotNull(var0, "$this$withStyledAttributes");
      Intrinsics.checkParameterIsNotNull(var2, "attrs");
      Intrinsics.checkParameterIsNotNull(var5, "block");
      TypedArray var6 = var0.obtainStyledAttributes(var1, var2, var3, var4);
      var5.invoke(var6);
      var6.recycle();
   }

   // $FF: synthetic method
   public static void withStyledAttributes$default(Context var0, AttributeSet var1, int[] var2, int var3, int var4, Function1 var5, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = null;
         AttributeSet var9 = (AttributeSet)null;
      }

      if ((var6 & 4) != 0) {
         var3 = 0;
      }

      if ((var6 & 8) != 0) {
         var4 = 0;
      }

      Intrinsics.checkParameterIsNotNull(var0, "$this$withStyledAttributes");
      Intrinsics.checkParameterIsNotNull(var2, "attrs");
      Intrinsics.checkParameterIsNotNull(var5, "block");
      TypedArray var8 = var0.obtainStyledAttributes(var1, var2, var3, var4);
      var5.invoke(var8);
      var8.recycle();
   }
}
