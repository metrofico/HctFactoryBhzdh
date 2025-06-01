package androidx.core.content;

import android.content.ContentValues;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   bv = {1, 0, 3},
   d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007¨\u0006\b"},
   d2 = {"contentValuesOf", "Landroid/content/ContentValues;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/content/ContentValues;", "core-ktx_release"},
   k = 2,
   mv = {1, 1, 15}
)
public final class ContentValuesKt {
   public static final ContentValues contentValuesOf(Pair... var0) {
      Intrinsics.checkParameterIsNotNull(var0, "pairs");
      ContentValues var4 = new ContentValues(var0.length);
      int var2 = var0.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         Pair var5 = var0[var1];
         String var3 = (String)var5.component1();
         Object var7 = var5.component2();
         if (var7 == null) {
            var4.putNull(var3);
         } else if (var7 instanceof String) {
            var4.put(var3, (String)var7);
         } else if (var7 instanceof Integer) {
            var4.put(var3, (Integer)var7);
         } else if (var7 instanceof Long) {
            var4.put(var3, (Long)var7);
         } else if (var7 instanceof Boolean) {
            var4.put(var3, (Boolean)var7);
         } else if (var7 instanceof Float) {
            var4.put(var3, (Float)var7);
         } else if (var7 instanceof Double) {
            var4.put(var3, (Double)var7);
         } else if (var7 instanceof byte[]) {
            var4.put(var3, (byte[])var7);
         } else if (var7 instanceof Byte) {
            var4.put(var3, (Byte)var7);
         } else {
            if (!(var7 instanceof Short)) {
               String var6 = var7.getClass().getCanonicalName();
               throw (Throwable)(new IllegalArgumentException("Illegal value type " + var6 + " for key \"" + var3 + '"'));
            }

            var4.put(var3, (Short)var7);
         }
      }

      return var4;
   }
}
