package kotlin.internal;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;

@Metadata(
   d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"},
   d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class UProgressionUtilKt {
   private static final int differenceModulo_WZ9TVnA(int var0, int var1, int var2) {
      var0 = UnsignedKt.uintRemainder_J1ME1BU(var0, var2);
      int var3 = UnsignedKt.uintRemainder_J1ME1BU(var1, var2);
      var1 = UnsignedKt.uintCompare(var0, var3);
      var0 = UInt.constructor_impl(var0 - var3);
      if (var1 < 0) {
         var0 = UInt.constructor_impl(var0 + var2);
      }

      return var0;
   }

   private static final long differenceModulo_sambcqE(long var0, long var2, long var4) {
      var0 = UnsignedKt.ulongRemainder_eb3DHEI(var0, var4);
      var2 = UnsignedKt.ulongRemainder_eb3DHEI(var2, var4);
      int var6 = UnsignedKt.ulongCompare(var0, var2);
      var0 = ULong.constructor_impl(var0 - var2);
      if (var6 < 0) {
         var0 = ULong.constructor_impl(var0 + var4);
      }

      return var0;
   }

   public static final long getProgressionLastElement_7ftBX0g(long var0, long var2, long var4) {
      long var7;
      int var6 = (var7 = var4 - 0L) == 0L ? 0 : (var7 < 0L ? -1 : 1);
      if (var6 > 0) {
         if (UnsignedKt.ulongCompare(var0, var2) < 0) {
            var2 = ULong.constructor_impl(var2 - differenceModulo_sambcqE(var2, var0, ULong.constructor_impl(var4)));
         }
      } else {
         if (var6 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
         }

         if (UnsignedKt.ulongCompare(var0, var2) > 0) {
            var2 = ULong.constructor_impl(var2 + differenceModulo_sambcqE(var0, var2, ULong.constructor_impl(-var4)));
         }
      }

      return var2;
   }

   public static final int getProgressionLastElement_Nkh28Cs(int var0, int var1, int var2) {
      if (var2 > 0) {
         if (UnsignedKt.uintCompare(var0, var1) < 0) {
            var1 = UInt.constructor_impl(var1 - differenceModulo_WZ9TVnA(var1, var0, UInt.constructor_impl(var2)));
         }
      } else {
         if (var2 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
         }

         if (UnsignedKt.uintCompare(var0, var1) > 0) {
            var1 = UInt.constructor_impl(var1 + differenceModulo_WZ9TVnA(var0, var1, UInt.constructor_impl(-var2)));
         }
      }

      return var1;
   }
}
