package kotlin.collections.unsigned;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000h\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b \n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a2\u0010\u0012\u001a\u00020\u0013*\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000e2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001f\u0010\u001f\u001a\u00020\u0002*\u00020\u00032\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001f\u0010\u001f\u001a\u00020\u0006*\u00020\u00072\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u001f\u0010\u001f\u001a\u00020\n*\u00020\u000b2\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u001f\u0010\u001f\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010 \u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u0018\u0010)\u001a\u0004\u0018\u00010\u0002*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a\u0018\u0010)\u001a\u0004\u0018\u00010\u0006*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a\u0018\u0010)\u001a\u0004\u0018\u00010\n*\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001a\u0018\u0010)\u001a\u0004\u0018\u00010\u000e*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001a@\u00102\u001a\u0004\u0018\u00010\u0002\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00032\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108\u001a@\u00102\u001a\u0004\u0018\u00010\u0006\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00072\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a@\u00102\u001a\u0004\u0018\u00010\n\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a@\u00102\u001a\u0004\u0018\u00010\u000e\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a4\u0010?\u001a\u0004\u0018\u00010\u0002*\u00020\u00032\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00020Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0002`BH\u0007ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001a4\u0010?\u001a\u0004\u0018\u00010\u0006*\u00020\u00072\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00060Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0006`BH\u0007ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a4\u0010?\u001a\u0004\u0018\u00010\n*\u00020\u000b2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\n0Aj\n\u0012\u0006\b\u0000\u0012\u00020\n`BH\u0007ø\u0001\u0000¢\u0006\u0004\bG\u0010H\u001a4\u0010?\u001a\u0004\u0018\u00010\u000e*\u00020\u000f2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000e0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000e`BH\u0007ø\u0001\u0000¢\u0006\u0004\bI\u0010J\u001a\u0018\u0010K\u001a\u0004\u0018\u00010\u0002*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\bL\u0010+\u001a\u0018\u0010K\u001a\u0004\u0018\u00010\u0006*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010-\u001a\u0018\u0010K\u001a\u0004\u0018\u00010\n*\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\bN\u0010/\u001a\u0018\u0010K\u001a\u0004\u0018\u00010\u000e*\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\bO\u00101\u001a@\u0010P\u001a\u0004\u0018\u00010\u0002\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00032\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bQ\u00108\u001a@\u0010P\u001a\u0004\u0018\u00010\u0006\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00072\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bR\u0010:\u001a@\u0010P\u001a\u0004\u0018\u00010\n\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bS\u0010<\u001a@\u0010P\u001a\u0004\u0018\u00010\u000e\"\u000e\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002H306H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bT\u0010>\u001a4\u0010U\u001a\u0004\u0018\u00010\u0002*\u00020\u00032\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00020Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0002`BH\u0007ø\u0001\u0000¢\u0006\u0004\bV\u0010D\u001a4\u0010U\u001a\u0004\u0018\u00010\u0006*\u00020\u00072\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00060Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0006`BH\u0007ø\u0001\u0000¢\u0006\u0004\bW\u0010F\u001a4\u0010U\u001a\u0004\u0018\u00010\n*\u00020\u000b2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\n0Aj\n\u0012\u0006\b\u0000\u0012\u00020\n`BH\u0007ø\u0001\u0000¢\u0006\u0004\bX\u0010H\u001a4\u0010U\u001a\u0004\u0018\u00010\u000e*\u00020\u000f2\u001a\u0010@\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u000e0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000e`BH\u0007ø\u0001\u0000¢\u0006\u0004\bY\u0010J\u001a.\u0010Z\u001a\u00020[*\u00020\u00032\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\\\u0010]\u001a.\u0010Z\u001a\u00020^*\u00020\u00032\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020^06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b_\u0010`\u001a.\u0010Z\u001a\u00020[*\u00020\u00072\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\\\u0010a\u001a.\u0010Z\u001a\u00020^*\u00020\u00072\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020^06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b_\u0010b\u001a.\u0010Z\u001a\u00020[*\u00020\u000b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\\\u0010c\u001a.\u0010Z\u001a\u00020^*\u00020\u000b2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020^06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b_\u0010d\u001a.\u0010Z\u001a\u00020[*\u00020\u000f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020[06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\\\u0010e\u001a.\u0010Z\u001a\u00020^*\u00020\u000f2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020^06H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b_\u0010f\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006g"},
   d2 = {"asList", "", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "asList-GBYM_sE", "([B)Ljava/util/List;", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "asList--ajY-9A", "([I)Ljava/util/List;", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "asList-QwZRm1k", "([J)Ljava/util/List;", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "", "element", "fromIndex", "toIndex", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-EtDCXyQ", "([SSII)I", "elementAt", "index", "elementAt-PpDY95g", "([BI)B", "elementAt-qFRl0hI", "([II)I", "elementAt-r7IrZao", "([JI)J", "elementAt-nggk6HY", "([SI)S", "max", "max-GBYM_sE", "([B)Lkotlin/UByte;", "max--ajY-9A", "([I)Lkotlin/UInt;", "max-QwZRm1k", "([J)Lkotlin/ULong;", "max-rL5Bavg", "([S)Lkotlin/UShort;", "maxBy", "R", "", "selector", "Lkotlin/Function1;", "maxBy-JOV_ifY", "([BLkotlin/jvm/functions/Function1;)Lkotlin/UByte;", "maxBy-jgv0xPQ", "([ILkotlin/jvm/functions/Function1;)Lkotlin/UInt;", "maxBy-MShoTSo", "([JLkotlin/jvm/functions/Function1;)Lkotlin/ULong;", "maxBy-xTcfx_M", "([SLkotlin/jvm/functions/Function1;)Lkotlin/UShort;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "maxWith-XMRcp5o", "([BLjava/util/Comparator;)Lkotlin/UByte;", "maxWith-YmdZ_VM", "([ILjava/util/Comparator;)Lkotlin/UInt;", "maxWith-zrEWJaI", "([JLjava/util/Comparator;)Lkotlin/ULong;", "maxWith-eOHTfZs", "([SLjava/util/Comparator;)Lkotlin/UShort;", "min", "min-GBYM_sE", "min--ajY-9A", "min-QwZRm1k", "min-rL5Bavg", "minBy", "minBy-JOV_ifY", "minBy-jgv0xPQ", "minBy-MShoTSo", "minBy-xTcfx_M", "minWith", "minWith-XMRcp5o", "minWith-YmdZ_VM", "minWith-zrEWJaI", "minWith-eOHTfZs", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   pn = "kotlin.collections",
   xi = 49,
   xs = "kotlin/collections/unsigned/UArraysKt"
)
class UArraysKt___UArraysJvmKt {
   public UArraysKt___UArraysJvmKt() {
   }

   public static final List asList__ajY_9A(int[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$asList");
      return (List)(new RandomAccess(var0) {
         final int[] $this_asList;

         {
            this.$this_asList = var1;
         }

         public boolean contains_WZ4Q5Ns(int var1) {
            return UIntArray.contains_WZ4Q5Ns(this.$this_asList, var1);
         }

         public int get_pVg5ArA(int var1) {
            return UIntArray.get_pVg5ArA(this.$this_asList, var1);
         }

         public int getSize() {
            return UIntArray.getSize_impl(this.$this_asList);
         }

         public int indexOf_WZ4Q5Ns(int var1) {
            return ArraysKt.indexOf(this.$this_asList, var1);
         }

         public boolean isEmpty() {
            return UIntArray.isEmpty_impl(this.$this_asList);
         }

         public int lastIndexOf_WZ4Q5Ns(int var1) {
            return ArraysKt.lastIndexOf(this.$this_asList, var1);
         }
      });
   }

   public static final List asList_GBYM_sE(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$asList");
      return (List)(new RandomAccess(var0) {
         final byte[] $this_asList;

         {
            this.$this_asList = var1;
         }

         public boolean contains_7apg3OU(byte var1) {
            return UByteArray.contains_7apg3OU(this.$this_asList, var1);
         }

         public byte get_w2LRezQ(int var1) {
            return UByteArray.get_w2LRezQ(this.$this_asList, var1);
         }

         public int getSize() {
            return UByteArray.getSize_impl(this.$this_asList);
         }

         public int indexOf_7apg3OU(byte var1) {
            return ArraysKt.indexOf(this.$this_asList, var1);
         }

         public boolean isEmpty() {
            return UByteArray.isEmpty_impl(this.$this_asList);
         }

         public int lastIndexOf_7apg3OU(byte var1) {
            return ArraysKt.lastIndexOf(this.$this_asList, var1);
         }
      });
   }

   public static final List asList_QwZRm1k(long[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$asList");
      return (List)(new RandomAccess(var0) {
         final long[] $this_asList;

         {
            this.$this_asList = var1;
         }

         public boolean contains_VKZWuLQ(long var1) {
            return ULongArray.contains_VKZWuLQ(this.$this_asList, var1);
         }

         public long get_s_VKNKU(int var1) {
            return ULongArray.get_s_VKNKU(this.$this_asList, var1);
         }

         public int getSize() {
            return ULongArray.getSize_impl(this.$this_asList);
         }

         public int indexOf_VKZWuLQ(long var1) {
            return ArraysKt.indexOf(this.$this_asList, var1);
         }

         public boolean isEmpty() {
            return ULongArray.isEmpty_impl(this.$this_asList);
         }

         public int lastIndexOf_VKZWuLQ(long var1) {
            return ArraysKt.lastIndexOf(this.$this_asList, var1);
         }
      });
   }

   public static final List asList_rL5Bavg(short[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$asList");
      return (List)(new RandomAccess(var0) {
         final short[] $this_asList;

         {
            this.$this_asList = var1;
         }

         public boolean contains_xj2QHRw(short var1) {
            return UShortArray.contains_xj2QHRw(this.$this_asList, var1);
         }

         public short get_Mh2AYeg(int var1) {
            return UShortArray.get_Mh2AYeg(this.$this_asList, var1);
         }

         public int getSize() {
            return UShortArray.getSize_impl(this.$this_asList);
         }

         public int indexOf_xj2QHRw(short var1) {
            return ArraysKt.indexOf(this.$this_asList, var1);
         }

         public boolean isEmpty() {
            return UShortArray.isEmpty_impl(this.$this_asList);
         }

         public int lastIndexOf_xj2QHRw(short var1) {
            return ArraysKt.lastIndexOf(this.$this_asList, var1);
         }
      });
   }

   public static final int binarySearch_2fe2U9s(int[] var0, int var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(var2, var3, UIntArray.getSize_impl(var0));
      --var3;

      while(var2 <= var3) {
         int var5 = var2 + var3 >>> 1;
         int var4 = UnsignedKt.uintCompare(var0[var5], var1);
         if (var4 < 0) {
            var2 = var5 + 1;
         } else {
            if (var4 <= 0) {
               return var5;
            }

            var3 = var5 - 1;
         }
      }

      return -(var2 + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_2fe2U9s$default(int[] var0, int var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = UIntArray.getSize_impl(var0);
      }

      return UArraysKt.binarySearch_2fe2U9s(var0, var1, var2, var3);
   }

   public static final int binarySearch_EtDCXyQ(short[] var0, short var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(var2, var3, UShortArray.getSize_impl(var0));
      --var3;

      while(var2 <= var3) {
         int var4 = var2 + var3 >>> 1;
         int var5 = UnsignedKt.uintCompare(var0[var4], var1 & '\uffff');
         if (var5 < 0) {
            var2 = var4 + 1;
         } else {
            if (var5 <= 0) {
               return var4;
            }

            var3 = var4 - 1;
         }
      }

      return -(var2 + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_EtDCXyQ$default(short[] var0, short var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = UShortArray.getSize_impl(var0);
      }

      return UArraysKt.binarySearch_EtDCXyQ(var0, var1, var2, var3);
   }

   public static final int binarySearch_K6DWlUc(long[] var0, long var1, int var3, int var4) {
      Intrinsics.checkNotNullParameter(var0, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(var3, var4, ULongArray.getSize_impl(var0));
      --var4;

      while(var3 <= var4) {
         int var6 = var3 + var4 >>> 1;
         int var5 = UnsignedKt.ulongCompare(var0[var6], var1);
         if (var5 < 0) {
            var3 = var6 + 1;
         } else {
            if (var5 <= 0) {
               return var6;
            }

            var4 = var6 - 1;
         }
      }

      return -(var3 + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_K6DWlUc$default(long[] var0, long var1, int var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var3 = 0;
      }

      if ((var5 & 4) != 0) {
         var4 = ULongArray.getSize_impl(var0);
      }

      return UArraysKt.binarySearch_K6DWlUc(var0, var1, var3, var4);
   }

   public static final int binarySearch_WpHrYlw(byte[] var0, byte var1, int var2, int var3) {
      Intrinsics.checkNotNullParameter(var0, "$this$binarySearch");
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(var2, var3, UByteArray.getSize_impl(var0));
      --var3;

      while(var2 <= var3) {
         int var5 = var2 + var3 >>> 1;
         int var4 = UnsignedKt.uintCompare(var0[var5], var1 & 255);
         if (var4 < 0) {
            var2 = var5 + 1;
         } else {
            if (var4 <= 0) {
               return var5;
            }

            var3 = var5 - 1;
         }
      }

      return -(var2 + 1);
   }

   // $FF: synthetic method
   public static int binarySearch_WpHrYlw$default(byte[] var0, byte var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = UByteArray.getSize_impl(var0);
      }

      return UArraysKt.binarySearch_WpHrYlw(var0, var1, var2, var3);
   }

   private static final byte elementAt_PpDY95g(byte[] var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$elementAt");
      return UByteArray.get_w2LRezQ(var0, var1);
   }

   private static final short elementAt_nggk6HY(short[] var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$elementAt");
      return UShortArray.get_Mh2AYeg(var0, var1);
   }

   private static final int elementAt_qFRl0hI(int[] var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$elementAt");
      return UIntArray.get_pVg5ArA(var0, var1);
   }

   private static final long elementAt_r7IrZao(long[] var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$elementAt");
      return ULongArray.get_s_VKNKU(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UInt max__ajY_9A(int[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$max");
      return UArraysKt.maxOrNull__ajY_9A(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UByte max_GBYM_sE(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$max");
      return UArraysKt.maxOrNull_GBYM_sE(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final ULong max_QwZRm1k(long[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$max");
      return UArraysKt.maxOrNull_QwZRm1k(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UShort max_rL5Bavg(short[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$max");
      return UArraysKt.maxOrNull_rL5Bavg(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final UByte maxBy_JOV_ifY(byte[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      UByte var8;
      if (UByteArray.isEmpty_impl(var0)) {
         var8 = null;
      } else {
         byte var2 = UByteArray.get_w2LRezQ(var0, 0);
         int var4 = ArraysKt.getLastIndex(var0);
         byte var3;
         if (var4 == 0) {
            var3 = var2;
         } else {
            Comparable var5 = (Comparable)var1.invoke(UByte.box_impl(var2));
            IntIterator var7 = (new IntRange(1, var4)).iterator();

            while(true) {
               var3 = var2;
               if (!var7.hasNext()) {
                  break;
               }

               var3 = UByteArray.get_w2LRezQ(var0, var7.nextInt());
               Comparable var6 = (Comparable)var1.invoke(UByte.box_impl(var3));
               if (var5.compareTo(var6) < 0) {
                  var2 = var3;
                  var5 = var6;
               }
            }
         }

         var8 = UByte.box_impl(var3);
      }

      return var8;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final ULong maxBy_MShoTSo(long[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      ULong var10;
      if (ULongArray.isEmpty_impl(var0)) {
         var10 = null;
      } else {
         long var3 = ULongArray.get_s_VKNKU(var0, 0);
         int var2 = ArraysKt.getLastIndex(var0);
         long var5;
         if (var2 == 0) {
            var5 = var3;
         } else {
            Comparable var7 = (Comparable)var1.invoke(ULong.box_impl(var3));
            IntIterator var9 = (new IntRange(1, var2)).iterator();

            while(true) {
               var5 = var3;
               if (!var9.hasNext()) {
                  break;
               }

               var5 = ULongArray.get_s_VKNKU(var0, var9.nextInt());
               Comparable var8 = (Comparable)var1.invoke(ULong.box_impl(var5));
               if (var7.compareTo(var8) < 0) {
                  var3 = var5;
                  var7 = var8;
               }
            }
         }

         var10 = ULong.box_impl(var5);
      }

      return var10;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final UInt maxBy_jgv0xPQ(int[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      UInt var7;
      if (UIntArray.isEmpty_impl(var0)) {
         var7 = null;
      } else {
         int var2 = UIntArray.get_pVg5ArA(var0, 0);
         int var3 = ArraysKt.getLastIndex(var0);
         if (var3 == 0) {
            var3 = var2;
         } else {
            Comparable var4 = (Comparable)var1.invoke(UInt.box_impl(var2));
            IntIterator var6 = (new IntRange(1, var3)).iterator();

            while(true) {
               var3 = var2;
               if (!var6.hasNext()) {
                  break;
               }

               var3 = UIntArray.get_pVg5ArA(var0, var6.nextInt());
               Comparable var5 = (Comparable)var1.invoke(UInt.box_impl(var3));
               if (var4.compareTo(var5) < 0) {
                  var2 = var3;
                  var4 = var5;
               }
            }
         }

         var7 = UInt.box_impl(var3);
      }

      return var7;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final UShort maxBy_xTcfx_M(short[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      UShort var8;
      if (UShortArray.isEmpty_impl(var0)) {
         var8 = null;
      } else {
         short var2 = UShortArray.get_Mh2AYeg(var0, 0);
         int var4 = ArraysKt.getLastIndex(var0);
         short var3;
         if (var4 == 0) {
            var3 = var2;
         } else {
            Comparable var5 = (Comparable)var1.invoke(UShort.box_impl(var2));
            IntIterator var7 = (new IntRange(1, var4)).iterator();

            while(true) {
               var3 = var2;
               if (!var7.hasNext()) {
                  break;
               }

               var3 = UShortArray.get_Mh2AYeg(var0, var7.nextInt());
               Comparable var6 = (Comparable)var1.invoke(UShort.box_impl(var3));
               if (var5.compareTo(var6) < 0) {
                  var2 = var3;
                  var5 = var6;
               }
            }
         }

         var8 = UShort.box_impl(var3);
      }

      return var8;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UByte maxWith_XMRcp5o(byte[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.maxWithOrNull_XMRcp5o(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UInt maxWith_YmdZ_VM(int[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.maxWithOrNull_YmdZ_VM(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UShort maxWith_eOHTfZs(short[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.maxWithOrNull_eOHTfZs(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use maxWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.maxWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final ULong maxWith_zrEWJaI(long[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$maxWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.maxWithOrNull_zrEWJaI(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UInt min__ajY_9A(int[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$min");
      return UArraysKt.minOrNull__ajY_9A(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UByte min_GBYM_sE(byte[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$min");
      return UArraysKt.minOrNull_GBYM_sE(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final ULong min_QwZRm1k(long[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$min");
      return UArraysKt.minOrNull_QwZRm1k(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minOrNull()",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UShort min_rL5Bavg(short[] var0) {
      Intrinsics.checkNotNullParameter(var0, "$this$min");
      return UArraysKt.minOrNull_rL5Bavg(var0);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final UByte minBy_JOV_ifY(byte[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      UByte var8;
      if (UByteArray.isEmpty_impl(var0)) {
         var8 = null;
      } else {
         byte var2 = UByteArray.get_w2LRezQ(var0, 0);
         int var4 = ArraysKt.getLastIndex(var0);
         byte var3;
         if (var4 == 0) {
            var3 = var2;
         } else {
            Comparable var5 = (Comparable)var1.invoke(UByte.box_impl(var2));
            IntIterator var7 = (new IntRange(1, var4)).iterator();

            while(true) {
               var3 = var2;
               if (!var7.hasNext()) {
                  break;
               }

               var3 = UByteArray.get_w2LRezQ(var0, var7.nextInt());
               Comparable var6 = (Comparable)var1.invoke(UByte.box_impl(var3));
               if (var5.compareTo(var6) > 0) {
                  var2 = var3;
                  var5 = var6;
               }
            }
         }

         var8 = UByte.box_impl(var3);
      }

      return var8;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final ULong minBy_MShoTSo(long[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      ULong var10;
      if (ULongArray.isEmpty_impl(var0)) {
         var10 = null;
      } else {
         long var3 = ULongArray.get_s_VKNKU(var0, 0);
         int var2 = ArraysKt.getLastIndex(var0);
         long var5;
         if (var2 == 0) {
            var5 = var3;
         } else {
            Comparable var7 = (Comparable)var1.invoke(ULong.box_impl(var3));
            IntIterator var9 = (new IntRange(1, var2)).iterator();

            while(true) {
               var5 = var3;
               if (!var9.hasNext()) {
                  break;
               }

               var5 = ULongArray.get_s_VKNKU(var0, var9.nextInt());
               Comparable var8 = (Comparable)var1.invoke(ULong.box_impl(var5));
               if (var7.compareTo(var8) > 0) {
                  var3 = var5;
                  var7 = var8;
               }
            }
         }

         var10 = ULong.box_impl(var5);
      }

      return var10;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final UInt minBy_jgv0xPQ(int[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      UInt var7;
      if (UIntArray.isEmpty_impl(var0)) {
         var7 = null;
      } else {
         int var2 = UIntArray.get_pVg5ArA(var0, 0);
         int var3 = ArraysKt.getLastIndex(var0);
         if (var3 == 0) {
            var3 = var2;
         } else {
            Comparable var4 = (Comparable)var1.invoke(UInt.box_impl(var2));
            IntIterator var6 = (new IntRange(1, var3)).iterator();

            while(true) {
               var3 = var2;
               if (!var6.hasNext()) {
                  break;
               }

               var3 = UIntArray.get_pVg5ArA(var0, var6.nextInt());
               Comparable var5 = (Comparable)var1.invoke(UInt.box_impl(var3));
               if (var4.compareTo(var5) > 0) {
                  var2 = var3;
                  var4 = var5;
               }
            }
         }

         var7 = UInt.box_impl(var3);
      }

      return var7;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minByOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minByOrNull(selector)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   private static final UShort minBy_xTcfx_M(short[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minBy");
      Intrinsics.checkNotNullParameter(var1, "selector");
      UShort var8;
      if (UShortArray.isEmpty_impl(var0)) {
         var8 = null;
      } else {
         short var2 = UShortArray.get_Mh2AYeg(var0, 0);
         int var4 = ArraysKt.getLastIndex(var0);
         short var3;
         if (var4 == 0) {
            var3 = var2;
         } else {
            Comparable var5 = (Comparable)var1.invoke(UShort.box_impl(var2));
            IntIterator var7 = (new IntRange(1, var4)).iterator();

            while(true) {
               var3 = var2;
               if (!var7.hasNext()) {
                  break;
               }

               var3 = UShortArray.get_Mh2AYeg(var0, var7.nextInt());
               Comparable var6 = (Comparable)var1.invoke(UShort.box_impl(var3));
               if (var5.compareTo(var6) > 0) {
                  var2 = var3;
                  var5 = var6;
               }
            }
         }

         var8 = UShort.box_impl(var3);
      }

      return var8;
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UByte minWith_XMRcp5o(byte[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.minWithOrNull_XMRcp5o(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UInt minWith_YmdZ_VM(int[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.minWithOrNull_YmdZ_VM(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final UShort minWith_eOHTfZs(short[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.minWithOrNull_eOHTfZs(var0, var1);
   }

   // $FF: synthetic method
   @Deprecated(
      message = "Use minWithOrNull instead.",
      replaceWith = @ReplaceWith(
   expression = "this.minWithOrNull(comparator)",
   imports = {}
)
   )
   @DeprecatedSinceKotlin(
      errorSince = "1.5",
      hiddenSince = "1.6",
      warningSince = "1.4"
   )
   public static final ULong minWith_zrEWJaI(long[] var0, Comparator var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$minWith");
      Intrinsics.checkNotNullParameter(var1, "comparator");
      return UArraysKt.minWithOrNull_zrEWJaI(var0, var1);
   }

   private static final BigDecimal sumOfBigDecimal(byte[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigDecimal var4 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = UByteArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigDecimal)var1.invoke(UByte.box_impl(UByteArray.get_w2LRezQ(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigDecimal sumOfBigDecimal(int[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigDecimal var4 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = UIntArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigDecimal)var1.invoke(UInt.box_impl(UIntArray.get_pVg5ArA(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigDecimal sumOfBigDecimal(long[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigDecimal var4 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = ULongArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigDecimal)var1.invoke(ULong.box_impl(ULongArray.get_s_VKNKU(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigDecimal sumOfBigDecimal(short[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigDecimal var4 = BigDecimal.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = UShortArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigDecimal)var1.invoke(UShort.box_impl(UShortArray.get_Mh2AYeg(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigInteger sumOfBigInteger(byte[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigInteger var4 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = UByteArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigInteger)var1.invoke(UByte.box_impl(UByteArray.get_w2LRezQ(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigInteger sumOfBigInteger(int[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigInteger var4 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = UIntArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigInteger)var1.invoke(UInt.box_impl(UIntArray.get_pVg5ArA(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigInteger sumOfBigInteger(long[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigInteger var4 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = ULongArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigInteger)var1.invoke(ULong.box_impl(ULongArray.get_s_VKNKU(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }

   private static final BigInteger sumOfBigInteger(short[] var0, Function1 var1) {
      Intrinsics.checkNotNullParameter(var0, "$this$sumOf");
      Intrinsics.checkNotNullParameter(var1, "selector");
      BigInteger var4 = BigInteger.valueOf(0L);
      Intrinsics.checkNotNullExpressionValue(var4, "valueOf(this.toLong())");
      int var3 = UShortArray.getSize_impl(var0);

      for(int var2 = 0; var2 < var3; ++var2) {
         var4 = var4.add((BigInteger)var1.invoke(UShort.box_impl(UShortArray.get_Mh2AYeg(var0, var2))));
         Intrinsics.checkNotNullExpressionValue(var4, "this.add(other)");
      }

      return var4;
   }
}
