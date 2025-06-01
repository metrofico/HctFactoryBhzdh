package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\"\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a+\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a&\u0010\u0000\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\n\u0010\t\u001a\u00020\u0012\"\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\"\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a+\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a&\u0010\u0000\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u001a\"\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\"\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a+\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u001dH\u0087\bø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a&\u0010\u0000\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\n\u0010\t\u001a\u00020\"\"\u00020\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\"\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010\u0005\u001a+\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\b\u001a&\u0010%\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\n\u0010\t\u001a\u00020\n\"\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010\f\u001a\"\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010\u000f\u001a+\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0011\u001a&\u0010%\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\r2\n\u0010\t\u001a\u00020\u0012\"\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010\u0014\u001a\"\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010\u0017\u001a+\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0015H\u0087\bø\u0001\u0000¢\u0006\u0004\b-\u0010\u0019\u001a&\u0010%\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u00152\n\u0010\t\u001a\u00020\u001a\"\u00020\u0015H\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010\u001c\u001a\"\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b/\u0010\u001f\u001a+\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u001dH\u0087\bø\u0001\u0000¢\u0006\u0004\b0\u0010!\u001a&\u0010%\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u001d2\n\u0010\t\u001a\u00020\"\"\u00020\u001dH\u0007ø\u0001\u0000¢\u0006\u0004\b1\u0010$\u0082\u0002\u0004\n\u0002\b\u0019¨\u00062"},
   d2 = {"maxOf", "Lkotlin/UByte;", "a", "b", "maxOf-Kr8caGY", "(BB)B", "c", "maxOf-b33U2AM", "(BBB)B", "other", "Lkotlin/UByteArray;", "maxOf-Wr6uiD8", "(B[B)B", "Lkotlin/UInt;", "maxOf-J1ME1BU", "(II)I", "maxOf-WZ9TVnA", "(III)I", "Lkotlin/UIntArray;", "maxOf-Md2H83M", "(I[I)I", "Lkotlin/ULong;", "maxOf-eb3DHEI", "(JJ)J", "maxOf-sambcqE", "(JJJ)J", "Lkotlin/ULongArray;", "maxOf-R03FKyM", "(J[J)J", "Lkotlin/UShort;", "maxOf-5PvTz6A", "(SS)S", "maxOf-VKSA0NQ", "(SSS)S", "Lkotlin/UShortArray;", "maxOf-t1qELG4", "(S[S)S", "minOf", "minOf-Kr8caGY", "minOf-b33U2AM", "minOf-Wr6uiD8", "minOf-J1ME1BU", "minOf-WZ9TVnA", "minOf-Md2H83M", "minOf-eb3DHEI", "minOf-sambcqE", "minOf-R03FKyM", "minOf-5PvTz6A", "minOf-VKSA0NQ", "minOf-t1qELG4", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/comparisons/UComparisonsKt"
)
class UComparisonsKt___UComparisonsKt {
   public UComparisonsKt___UComparisonsKt() {
   }

   public static final short maxOf_5PvTz6A(short var0, short var1) {
      if (Intrinsics.compare(var0 & '\uffff', '\uffff' & var1) < 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final int maxOf_J1ME1BU(int var0, int var1) {
      if (UnsignedKt.uintCompare(var0, var1) < 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final byte maxOf_Kr8caGY(byte var0, byte var1) {
      if (Intrinsics.compare(var0 & 255, var1 & 255) < 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final int maxOf_Md2H83M(int var0, int... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = UIntArray.getSize_impl(var1);

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = UComparisonsKt.maxOf_J1ME1BU(var0, UIntArray.get_pVg5ArA(var1, var2));
      }

      return var0;
   }

   public static final long maxOf_R03FKyM(long var0, long... var2) {
      Intrinsics.checkNotNullParameter(var2, "other");
      int var4 = ULongArray.getSize_impl(var2);

      for(int var3 = 0; var3 < var4; ++var3) {
         var0 = UComparisonsKt.maxOf_eb3DHEI(var0, ULongArray.get_s_VKNKU(var2, var3));
      }

      return var0;
   }

   private static final short maxOf_VKSA0NQ(short var0, short var1, short var2) {
      return UComparisonsKt.maxOf_5PvTz6A(var0, UComparisonsKt.maxOf_5PvTz6A(var1, var2));
   }

   private static final int maxOf_WZ9TVnA(int var0, int var1, int var2) {
      return UComparisonsKt.maxOf_J1ME1BU(var0, UComparisonsKt.maxOf_J1ME1BU(var1, var2));
   }

   public static final byte maxOf_Wr6uiD8(byte var0, byte... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = UByteArray.getSize_impl(var1);

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = UComparisonsKt.maxOf_Kr8caGY(var0, UByteArray.get_w2LRezQ(var1, var2));
      }

      return var0;
   }

   private static final byte maxOf_b33U2AM(byte var0, byte var1, byte var2) {
      return UComparisonsKt.maxOf_Kr8caGY(var0, UComparisonsKt.maxOf_Kr8caGY(var1, var2));
   }

   public static final long maxOf_eb3DHEI(long var0, long var2) {
      if (UnsignedKt.ulongCompare(var0, var2) < 0) {
         var0 = var2;
      }

      return var0;
   }

   private static final long maxOf_sambcqE(long var0, long var2, long var4) {
      return UComparisonsKt.maxOf_eb3DHEI(var0, UComparisonsKt.maxOf_eb3DHEI(var2, var4));
   }

   public static final short maxOf_t1qELG4(short var0, short... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = UShortArray.getSize_impl(var1);

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = UComparisonsKt.maxOf_5PvTz6A(var0, UShortArray.get_Mh2AYeg(var1, var2));
      }

      return var0;
   }

   public static final short minOf_5PvTz6A(short var0, short var1) {
      if (Intrinsics.compare(var0 & '\uffff', '\uffff' & var1) > 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final int minOf_J1ME1BU(int var0, int var1) {
      if (UnsignedKt.uintCompare(var0, var1) > 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final byte minOf_Kr8caGY(byte var0, byte var1) {
      if (Intrinsics.compare(var0 & 255, var1 & 255) > 0) {
         var0 = var1;
      }

      return var0;
   }

   public static final int minOf_Md2H83M(int var0, int... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var4 = UIntArray.getSize_impl(var1);
      byte var3 = 0;
      int var2 = var0;

      for(var0 = var3; var0 < var4; ++var0) {
         var2 = UComparisonsKt.minOf_J1ME1BU(var2, UIntArray.get_pVg5ArA(var1, var0));
      }

      return var2;
   }

   public static final long minOf_R03FKyM(long var0, long... var2) {
      Intrinsics.checkNotNullParameter(var2, "other");
      int var4 = ULongArray.getSize_impl(var2);

      for(int var3 = 0; var3 < var4; ++var3) {
         var0 = UComparisonsKt.minOf_eb3DHEI(var0, ULongArray.get_s_VKNKU(var2, var3));
      }

      return var0;
   }

   private static final short minOf_VKSA0NQ(short var0, short var1, short var2) {
      return UComparisonsKt.minOf_5PvTz6A(var0, UComparisonsKt.minOf_5PvTz6A(var1, var2));
   }

   private static final int minOf_WZ9TVnA(int var0, int var1, int var2) {
      return UComparisonsKt.minOf_J1ME1BU(var0, UComparisonsKt.minOf_J1ME1BU(var1, var2));
   }

   public static final byte minOf_Wr6uiD8(byte var0, byte... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = UByteArray.getSize_impl(var1);

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = UComparisonsKt.minOf_Kr8caGY(var0, UByteArray.get_w2LRezQ(var1, var2));
      }

      return var0;
   }

   private static final byte minOf_b33U2AM(byte var0, byte var1, byte var2) {
      return UComparisonsKt.minOf_Kr8caGY(var0, UComparisonsKt.minOf_Kr8caGY(var1, var2));
   }

   public static final long minOf_eb3DHEI(long var0, long var2) {
      if (UnsignedKt.ulongCompare(var0, var2) > 0) {
         var0 = var2;
      }

      return var0;
   }

   private static final long minOf_sambcqE(long var0, long var2, long var4) {
      return UComparisonsKt.minOf_eb3DHEI(var0, UComparisonsKt.minOf_eb3DHEI(var2, var4));
   }

   public static final short minOf_t1qELG4(short var0, short... var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      int var3 = UShortArray.getSize_impl(var1);

      for(int var2 = 0; var2 < var3; ++var2) {
         var0 = UComparisonsKt.minOf_5PvTz6A(var0, UShortArray.get_Mh2AYeg(var1, var2));
      }

      return var0;
   }
}
