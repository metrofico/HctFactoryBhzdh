package kotlin.random;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.ULongRange;

@Metadata(
   d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001c\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a2\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0014\u0010\u0018\u001a\u00020\u0003*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001e\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a&\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001c\u0010\u0018\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007ø\u0001\u0000¢\u0006\u0002\u0010 \u001a\u0014\u0010!\u001a\u00020\b*\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\u001a\u001e\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a&\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010!\u001a\u00020\b*\u00020\r2\u0006\u0010\u001e\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"},
   d2 = {"checkUIntRangeBounds", "", "from", "Lkotlin/UInt;", "until", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkULongRangeBounds", "Lkotlin/ULong;", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "nextUBytes", "Lkotlin/UByteArray;", "Lkotlin/random/Random;", "size", "", "(Lkotlin/random/Random;I)[B", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "nextUInt", "(Lkotlin/random/Random;)I", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "range", "Lkotlin/ranges/UIntRange;", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class URandomKt {
   public static final void checkUIntRangeBounds_J1ME1BU(int var0, int var1) {
      boolean var2;
      if (UnsignedKt.uintCompare(var1, var0) > 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (!var2) {
         throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.box_impl(var0), UInt.box_impl(var1)).toString());
      }
   }

   public static final void checkULongRangeBounds_eb3DHEI(long var0, long var2) {
      boolean var4;
      if (UnsignedKt.ulongCompare(var2, var0) > 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      if (!var4) {
         throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.box_impl(var0), ULong.box_impl(var2)).toString());
      }
   }

   public static final byte[] nextUBytes(Random var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return UByteArray.constructor_impl(var0.nextBytes(var1));
   }

   public static final byte[] nextUBytes_EVgfTAA(Random var0, byte[] var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$nextUBytes");
      Intrinsics.checkNotNullParameter(var1, "array");
      var0.nextBytes(var1);
      return var1;
   }

   public static final byte[] nextUBytes_Wvrt4B4(Random var0, byte[] var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "$this$nextUBytes");
      Intrinsics.checkNotNullParameter(var1, "array");
      var0.nextBytes(var1, var2, var3);
      return var1;
   }

   // $FF: synthetic method
   public static byte[] nextUBytes_Wvrt4B4$default(Random var0, byte[] var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = UByteArray.getSize_impl(var1);
      }

      return nextUBytes_Wvrt4B4(var0, var1, var2, var3);
   }

   public static final int nextUInt(Random var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return UInt.constructor_impl(var0.nextInt());
   }

   public static final int nextUInt(Random var0, UIntRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      if (!var1.isEmpty()) {
         int var2;
         if (UnsignedKt.uintCompare(var1.getLast_pVg5ArA(), -1) < 0) {
            var2 = nextUInt_a8DCA5k(var0, var1.getFirst_pVg5ArA(), UInt.constructor_impl(var1.getLast_pVg5ArA() + 1));
         } else if (UnsignedKt.uintCompare(var1.getFirst_pVg5ArA(), 0) > 0) {
            var2 = UInt.constructor_impl(nextUInt_a8DCA5k(var0, UInt.constructor_impl(var1.getFirst_pVg5ArA() - 1), var1.getLast_pVg5ArA()) + 1);
         } else {
            var2 = nextUInt(var0);
         }

         return var2;
      } else {
         throw new IllegalArgumentException("Cannot get random in empty range: " + var1);
      }
   }

   public static final int nextUInt_a8DCA5k(Random var0, int var1, int var2) {
      Intrinsics.checkNotNullParameter(var0, "$this$nextUInt");
      checkUIntRangeBounds_J1ME1BU(var1, var2);
      return UInt.constructor_impl(var0.nextInt(var1 ^ Integer.MIN_VALUE, var2 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
   }

   public static final int nextUInt_qCasIEU(Random var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$nextUInt");
      return nextUInt_a8DCA5k(var0, 0, var1);
   }

   public static final long nextULong(Random var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return ULong.constructor_impl(var0.nextLong());
   }

   public static final long nextULong(Random var0, ULongRange var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Intrinsics.checkNotNullParameter(var1, "range");
      if (!var1.isEmpty()) {
         long var2;
         if (UnsignedKt.ulongCompare(var1.getLast_s_VKNKU(), -1L) < 0) {
            var2 = nextULong_jmpaW_c(var0, var1.getFirst_s_VKNKU(), ULong.constructor_impl(var1.getLast_s_VKNKU() + ULong.constructor_impl((long)1 & 4294967295L)));
         } else if (UnsignedKt.ulongCompare(var1.getFirst_s_VKNKU(), 0L) > 0) {
            var2 = var1.getFirst_s_VKNKU();
            long var4 = (long)1 & 4294967295L;
            var2 = ULong.constructor_impl(nextULong_jmpaW_c(var0, ULong.constructor_impl(var2 - ULong.constructor_impl(var4)), var1.getLast_s_VKNKU()) + ULong.constructor_impl(var4));
         } else {
            var2 = nextULong(var0);
         }

         return var2;
      } else {
         throw new IllegalArgumentException("Cannot get random in empty range: " + var1);
      }
   }

   public static final long nextULong_V1Xi4fY(Random var0, long var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$nextULong");
      return nextULong_jmpaW_c(var0, 0L, var1);
   }

   public static final long nextULong_jmpaW_c(Random var0, long var1, long var3) {
      Intrinsics.checkNotNullParameter(var0, "$this$nextULong");
      checkULongRangeBounds_eb3DHEI(var1, var3);
      return ULong.constructor_impl(var0.nextLong(var1 ^ Long.MIN_VALUE, var3 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
   }
}
