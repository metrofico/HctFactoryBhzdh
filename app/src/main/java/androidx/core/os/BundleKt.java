package androidx.core.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.Size;
import android.util.SizeF;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"},
   d2 = {"bundleOf", "Landroid/os/Bundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class BundleKt {
   public static final Bundle bundleOf(Pair... var0) {
      Intrinsics.checkParameterIsNotNull(var0, "pairs");
      Bundle var4 = new Bundle(var0.length);
      int var2 = var0.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         Pair var5 = var0[var1];
         String var3 = (String)var5.component1();
         Object var6 = var5.component2();
         if (var6 == null) {
            var4.putString(var3, (String)null);
         } else if (var6 instanceof Boolean) {
            var4.putBoolean(var3, (Boolean)var6);
         } else if (var6 instanceof Byte) {
            var4.putByte(var3, ((Number)var6).byteValue());
         } else if (var6 instanceof Character) {
            var4.putChar(var3, (Character)var6);
         } else if (var6 instanceof Double) {
            var4.putDouble(var3, ((Number)var6).doubleValue());
         } else if (var6 instanceof Float) {
            var4.putFloat(var3, ((Number)var6).floatValue());
         } else if (var6 instanceof Integer) {
            var4.putInt(var3, ((Number)var6).intValue());
         } else if (var6 instanceof Long) {
            var4.putLong(var3, ((Number)var6).longValue());
         } else if (var6 instanceof Short) {
            var4.putShort(var3, ((Number)var6).shortValue());
         } else if (var6 instanceof Bundle) {
            var4.putBundle(var3, (Bundle)var6);
         } else if (var6 instanceof CharSequence) {
            var4.putCharSequence(var3, (CharSequence)var6);
         } else if (var6 instanceof Parcelable) {
            var4.putParcelable(var3, (Parcelable)var6);
         } else if (var6 instanceof boolean[]) {
            var4.putBooleanArray(var3, (boolean[])var6);
         } else if (var6 instanceof byte[]) {
            var4.putByteArray(var3, (byte[])var6);
         } else if (var6 instanceof char[]) {
            var4.putCharArray(var3, (char[])var6);
         } else if (var6 instanceof double[]) {
            var4.putDoubleArray(var3, (double[])var6);
         } else if (var6 instanceof float[]) {
            var4.putFloatArray(var3, (float[])var6);
         } else if (var6 instanceof int[]) {
            var4.putIntArray(var3, (int[])var6);
         } else if (var6 instanceof long[]) {
            var4.putLongArray(var3, (long[])var6);
         } else if (var6 instanceof short[]) {
            var4.putShortArray(var3, (short[])var6);
         } else {
            String var7;
            if (var6 instanceof Object[]) {
               Class var8 = var6.getClass().getComponentType();
               if (var8 == null) {
                  Intrinsics.throwNpe();
               }

               if (Parcelable.class.isAssignableFrom(var8)) {
                  if (var6 == null) {
                     throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                  }

                  var4.putParcelableArray(var3, (Parcelable[])var6);
               } else if (String.class.isAssignableFrom(var8)) {
                  if (var6 == null) {
                     throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                  }

                  var4.putStringArray(var3, (String[])var6);
               } else if (CharSequence.class.isAssignableFrom(var8)) {
                  if (var6 == null) {
                     throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                  }

                  var4.putCharSequenceArray(var3, (CharSequence[])var6);
               } else {
                  if (!Serializable.class.isAssignableFrom(var8)) {
                     var7 = var8.getCanonicalName();
                     throw (Throwable)(new IllegalArgumentException("Illegal value array type " + var7 + " for key \"" + var3 + '"'));
                  }

                  var4.putSerializable(var3, (Serializable)var6);
               }
            } else if (var6 instanceof Serializable) {
               var4.putSerializable(var3, (Serializable)var6);
            } else if (VERSION.SDK_INT >= 18 && var6 instanceof Binder) {
               var4.putBinder(var3, (IBinder)var6);
            } else if (VERSION.SDK_INT >= 21 && var6 instanceof Size) {
               var4.putSize(var3, (Size)var6);
            } else {
               if (VERSION.SDK_INT < 21 || !(var6 instanceof SizeF)) {
                  var7 = var6.getClass().getCanonicalName();
                  throw (Throwable)(new IllegalArgumentException("Illegal value type " + var7 + " for key \"" + var3 + '"'));
               }

               var4.putSizeF(var3, (SizeF)var6);
            }
         }
      }

      return var4;
   }
}
