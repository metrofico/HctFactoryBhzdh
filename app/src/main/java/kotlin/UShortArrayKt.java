package kotlin;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u0007\u001a\u001f\u0010\b\u001a\u00020\u00012\n\u0010\t\u001a\u00020\u0001\"\u00020\u0006H\u0087\bø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\f"},
   d2 = {"UShortArray", "Lkotlin/UShortArray;", "size", "", "init", "Lkotlin/Function1;", "Lkotlin/UShort;", "(ILkotlin/jvm/functions/Function1;)[S", "ushortArrayOf", "elements", "ushortArrayOf-rL5Bavg", "([S)[S", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class UShortArrayKt {
   private static final short[] UShortArray(int var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var1, "init");
      short[] var3 = new short[var0];

      for(int var2 = 0; var2 < var0; ++var2) {
         var3[var2] = ((UShort)var1.invoke(var2)).unbox_impl();
      }

      return UShortArray.constructor_impl(var3);
   }

   private static final short[] ushortArrayOf_rL5Bavg(short... var0) {
      Intrinsics.checkNotNullParameter(var0, "elements");
      return var0;
   }
}
