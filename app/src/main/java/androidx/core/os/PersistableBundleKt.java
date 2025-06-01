package androidx.core.os;

import android.os.PersistableBundle;
import android.os.Build.VERSION;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a=\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0007¢\u0006\u0002\u0010\u0007¨\u0006\b"},
   d2 = {"persistableBundleOf", "Landroid/os/PersistableBundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/PersistableBundle;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class PersistableBundleKt {
   public static final PersistableBundle persistableBundleOf(Pair... var0) {
      Intrinsics.checkParameterIsNotNull(var0, "pairs");
      PersistableBundle var4 = new PersistableBundle(var0.length);
      int var2 = var0.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         Pair var5 = var0[var1];
         String var3 = (String)var5.component1();
         Object var8 = var5.component2();
         if (var8 == null) {
            var4.putString(var3, (String)null);
         } else if (var8 instanceof Boolean) {
            if (VERSION.SDK_INT < 22) {
               throw (Throwable)(new IllegalArgumentException("Illegal value type boolean for key \"" + var3 + '"'));
            }

            var4.putBoolean(var3, (Boolean)var8);
         } else if (var8 instanceof Double) {
            var4.putDouble(var3, ((Number)var8).doubleValue());
         } else if (var8 instanceof Integer) {
            var4.putInt(var3, ((Number)var8).intValue());
         } else if (var8 instanceof Long) {
            var4.putLong(var3, ((Number)var8).longValue());
         } else if (var8 instanceof String) {
            var4.putString(var3, (String)var8);
         } else if (var8 instanceof boolean[]) {
            if (VERSION.SDK_INT < 22) {
               throw (Throwable)(new IllegalArgumentException("Illegal value type boolean[] for key \"" + var3 + '"'));
            }

            var4.putBooleanArray(var3, (boolean[])var8);
         } else if (var8 instanceof double[]) {
            var4.putDoubleArray(var3, (double[])var8);
         } else if (var8 instanceof int[]) {
            var4.putIntArray(var3, (int[])var8);
         } else if (var8 instanceof long[]) {
            var4.putLongArray(var3, (long[])var8);
         } else {
            String var7;
            if (!(var8 instanceof Object[])) {
               var7 = var8.getClass().getCanonicalName();
               throw (Throwable)(new IllegalArgumentException("Illegal value type " + var7 + " for key \"" + var3 + '"'));
            }

            Class var6 = var8.getClass().getComponentType();
            if (var6 == null) {
               Intrinsics.throwNpe();
            }

            if (!String.class.isAssignableFrom(var6)) {
               var7 = var6.getCanonicalName();
               throw (Throwable)(new IllegalArgumentException("Illegal value array type " + var7 + " for key \"" + var3 + '"'));
            }

            if (var8 == null) {
               throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
            }

            var4.putStringArray(var3, (String[])var8);
         }
      }

      return var4;
   }
}
