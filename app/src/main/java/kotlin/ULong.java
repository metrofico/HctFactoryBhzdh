package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;

@Metadata(
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 |2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001|B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u000bJ\u001b\u0010\u001b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u001a\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010%HÖ\u0003¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001dJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u001fJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b+\u0010\u000bJ\u001b\u0010(\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b,\u0010\"J\u0010\u0010-\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b.\u0010/J\u0016\u00100\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u0016\u00102\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0005J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001dJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u001fJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\"J\u001b\u00109\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010;J\u001b\u00109\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u0013J\u001b\u00109\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b=\u0010\u000bJ\u001b\u00109\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bA\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u001dJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u001fJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u000bJ\u001b\u0010B\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\"J\u001b\u0010G\u001a\u00020H2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u001dJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bM\u0010\u001fJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000bJ\u001b\u0010K\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\"J\u001e\u0010P\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bR\u0010\u001fJ\u001e\u0010S\u001a\u00020\u00002\u0006\u0010Q\u001a\u00020\rH\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bT\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bV\u0010\u001dJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bW\u0010\u001fJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bX\u0010\u000bJ\u001b\u0010U\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bY\u0010\"J\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020_H\u0087\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001a\u00020\rH\u0087\b¢\u0006\u0004\bg\u0010/J\u0010\u0010h\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bi\u0010\u0005J\u0010\u0010j\u001a\u00020kH\u0087\b¢\u0006\u0004\bl\u0010mJ\u000f\u0010n\u001a\u00020oH\u0016¢\u0006\u0004\bp\u0010qJ\u0016\u0010r\u001a\u00020\u000eH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bs\u0010]J\u0016\u0010t\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bu\u0010/J\u0016\u0010v\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bw\u0010\u0005J\u0016\u0010x\u001a\u00020\u0016H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\by\u0010mJ\u001b\u0010z\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b{\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006}"},
   d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "getData$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-s-VKNKU", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(J)I", "inc", "inc-s-VKNKU", "inv", "inv-s-VKNKU", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(JB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(JS)S", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
@JvmInline
public final class ULong implements Comparable {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final long MAX_VALUE = -1L;
   public static final long MIN_VALUE = 0L;
   public static final int SIZE_BITS = 64;
   public static final int SIZE_BYTES = 8;
   private final long data;

   // $FF: synthetic method
   private ULong(long var1) {
      this.data = var1;
   }

   private static final long and_VKZWuLQ(long var0, long var2) {
      return constructor_impl(var0 & var2);
   }

   // $FF: synthetic method
   public static final ULong box_impl(long var0) {
      return new ULong(var0);
   }

   private static final int compareTo_7apg3OU(long var0, byte var2) {
      return UnsignedKt.ulongCompare(var0, constructor_impl((long)var2 & 255L));
   }

   private int compareTo_VKZWuLQ(long var1) {
      return UnsignedKt.ulongCompare(this.unbox_impl(), var1);
   }

   private static int compareTo_VKZWuLQ(long var0, long var2) {
      return UnsignedKt.ulongCompare(var0, var2);
   }

   private static final int compareTo_WZ4Q5Ns(long var0, int var2) {
      return UnsignedKt.ulongCompare(var0, constructor_impl((long)var2 & 4294967295L));
   }

   private static final int compareTo_xj2QHRw(long var0, short var2) {
      return UnsignedKt.ulongCompare(var0, constructor_impl((long)var2 & 65535L));
   }

   public static long constructor_impl(long var0) {
      return var0;
   }

   private static final long dec_s_VKNKU(long var0) {
      return constructor_impl(var0 - 1L);
   }

   private static final long div_7apg3OU(long var0, byte var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, constructor_impl((long)var2 & 255L));
   }

   private static final long div_VKZWuLQ(long var0, long var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, var2);
   }

   private static final long div_WZ4Q5Ns(long var0, int var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, constructor_impl((long)var2 & 4294967295L));
   }

   private static final long div_xj2QHRw(long var0, short var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, constructor_impl((long)var2 & 65535L));
   }

   public static boolean equals_impl(long var0, Object var2) {
      if (!(var2 instanceof ULong)) {
         return false;
      } else {
         return var0 == ((ULong)var2).unbox_impl();
      }
   }

   public static final boolean equals_impl0(long var0, long var2) {
      boolean var4;
      if (var0 == var2) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4;
   }

   private static final long floorDiv_7apg3OU(long var0, byte var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, constructor_impl((long)var2 & 255L));
   }

   private static final long floorDiv_VKZWuLQ(long var0, long var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, var2);
   }

   private static final long floorDiv_WZ4Q5Ns(long var0, int var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, constructor_impl((long)var2 & 4294967295L));
   }

   private static final long floorDiv_xj2QHRw(long var0, short var2) {
      return UnsignedKt.ulongDivide_eb3DHEI(var0, constructor_impl((long)var2 & 65535L));
   }

   // $FF: synthetic method
   public static void getData$annotations() {
   }

   public static int hashCode_impl(long var0) {
      return (int)(var0 ^ var0 >>> 32);
   }

   private static final long inc_s_VKNKU(long var0) {
      return constructor_impl(var0 + 1L);
   }

   private static final long inv_s_VKNKU(long var0) {
      return constructor_impl(~var0);
   }

   private static final long minus_7apg3OU(long var0, byte var2) {
      return constructor_impl(var0 - constructor_impl((long)var2 & 255L));
   }

   private static final long minus_VKZWuLQ(long var0, long var2) {
      return constructor_impl(var0 - var2);
   }

   private static final long minus_WZ4Q5Ns(long var0, int var2) {
      return constructor_impl(var0 - constructor_impl((long)var2 & 4294967295L));
   }

   private static final long minus_xj2QHRw(long var0, short var2) {
      return constructor_impl(var0 - constructor_impl((long)var2 & 65535L));
   }

   private static final byte mod_7apg3OU(long var0, byte var2) {
      return UByte.constructor_impl((byte)((int)UnsignedKt.ulongRemainder_eb3DHEI(var0, constructor_impl((long)var2 & 255L))));
   }

   private static final long mod_VKZWuLQ(long var0, long var2) {
      return UnsignedKt.ulongRemainder_eb3DHEI(var0, var2);
   }

   private static final int mod_WZ4Q5Ns(long var0, int var2) {
      return UInt.constructor_impl((int)UnsignedKt.ulongRemainder_eb3DHEI(var0, constructor_impl((long)var2 & 4294967295L)));
   }

   private static final short mod_xj2QHRw(long var0, short var2) {
      return UShort.constructor_impl((short)((int)UnsignedKt.ulongRemainder_eb3DHEI(var0, constructor_impl((long)var2 & 65535L))));
   }

   private static final long or_VKZWuLQ(long var0, long var2) {
      return constructor_impl(var0 | var2);
   }

   private static final long plus_7apg3OU(long var0, byte var2) {
      return constructor_impl(var0 + constructor_impl((long)var2 & 255L));
   }

   private static final long plus_VKZWuLQ(long var0, long var2) {
      return constructor_impl(var0 + var2);
   }

   private static final long plus_WZ4Q5Ns(long var0, int var2) {
      return constructor_impl(var0 + constructor_impl((long)var2 & 4294967295L));
   }

   private static final long plus_xj2QHRw(long var0, short var2) {
      return constructor_impl(var0 + constructor_impl((long)var2 & 65535L));
   }

   private static final ULongRange rangeTo_VKZWuLQ(long var0, long var2) {
      return new ULongRange(var0, var2, (DefaultConstructorMarker)null);
   }

   private static final long rem_7apg3OU(long var0, byte var2) {
      return UnsignedKt.ulongRemainder_eb3DHEI(var0, constructor_impl((long)var2 & 255L));
   }

   private static final long rem_VKZWuLQ(long var0, long var2) {
      return UnsignedKt.ulongRemainder_eb3DHEI(var0, var2);
   }

   private static final long rem_WZ4Q5Ns(long var0, int var2) {
      return UnsignedKt.ulongRemainder_eb3DHEI(var0, constructor_impl((long)var2 & 4294967295L));
   }

   private static final long rem_xj2QHRw(long var0, short var2) {
      return UnsignedKt.ulongRemainder_eb3DHEI(var0, constructor_impl((long)var2 & 65535L));
   }

   private static final long shl_s_VKNKU(long var0, int var2) {
      return constructor_impl(var0 << var2);
   }

   private static final long shr_s_VKNKU(long var0, int var2) {
      return constructor_impl(var0 >>> var2);
   }

   private static final long times_7apg3OU(long var0, byte var2) {
      return constructor_impl(var0 * constructor_impl((long)var2 & 255L));
   }

   private static final long times_VKZWuLQ(long var0, long var2) {
      return constructor_impl(var0 * var2);
   }

   private static final long times_WZ4Q5Ns(long var0, int var2) {
      return constructor_impl(var0 * constructor_impl((long)var2 & 4294967295L));
   }

   private static final long times_xj2QHRw(long var0, short var2) {
      return constructor_impl(var0 * constructor_impl((long)var2 & 65535L));
   }

   private static final byte toByte_impl(long var0) {
      return (byte)((int)var0);
   }

   private static final double toDouble_impl(long var0) {
      return UnsignedKt.ulongToDouble(var0);
   }

   private static final float toFloat_impl(long var0) {
      return (float)UnsignedKt.ulongToDouble(var0);
   }

   private static final int toInt_impl(long var0) {
      return (int)var0;
   }

   private static final long toLong_impl(long var0) {
      return var0;
   }

   private static final short toShort_impl(long var0) {
      return (short)((int)var0);
   }

   public static String toString_impl(long var0) {
      return UnsignedKt.ulongToString(var0);
   }

   private static final byte toUByte_w2LRezQ(long var0) {
      return UByte.constructor_impl((byte)((int)var0));
   }

   private static final int toUInt_pVg5ArA(long var0) {
      return UInt.constructor_impl((int)var0);
   }

   private static final long toULong_s_VKNKU(long var0) {
      return var0;
   }

   private static final short toUShort_Mh2AYeg(long var0) {
      return UShort.constructor_impl((short)((int)var0));
   }

   private static final long xor_VKZWuLQ(long var0, long var2) {
      return constructor_impl(var0 ^ var2);
   }

   public boolean equals(Object var1) {
      return equals_impl(this.data, var1);
   }

   public int hashCode() {
      return hashCode_impl(this.data);
   }

   public String toString() {
      return toString_impl(this.data);
   }

   // $FF: synthetic method
   public final long unbox_impl() {
      return this.data;
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"},
      d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"},
      k = 1,
      mv = {1, 7, 1},
      xi = 48
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker var1) {
         this();
      }
   }
}
