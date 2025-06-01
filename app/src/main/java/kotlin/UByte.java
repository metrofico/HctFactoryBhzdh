package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

@Metadata(
   d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u000fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0012J\u001b\u0010&\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u000fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0012J\u001b\u00102\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u000bJ\u001b\u00107\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010\u0012J\u001b\u00107\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010\u001fJ\u001b\u00107\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\u0016H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u000fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0012J\u001b\u0010?\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u000fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0012J\u001b\u0010H\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u000fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\u0012J\u001b\u0010M\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bS\u0010\u0005J\u0010\u0010T\u001a\u00020UH\u0087\b¢\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001a\u00020YH\u0087\b¢\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001a\u00020\rH\u0087\b¢\u0006\u0004\b]\u0010-J\u0010\u0010^\u001a\u00020_H\u0087\b¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020cH\u0087\b¢\u0006\u0004\bd\u0010eJ\u000f\u0010f\u001a\u00020gH\u0016¢\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010\u0005J\u0016\u0010l\u001a\u00020\u0010H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0013H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010aJ\u0016\u0010p\u001a\u00020\u0016H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bq\u0010eJ\u001b\u0010r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006u"},
   d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
@JvmInline
public final class UByte implements Comparable {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final byte MAX_VALUE = -1;
   public static final byte MIN_VALUE = 0;
   public static final int SIZE_BITS = 8;
   public static final int SIZE_BYTES = 1;
   private final byte data;

   // $FF: synthetic method
   private UByte(byte var1) {
      this.data = var1;
   }

   private static final byte and_7apg3OU(byte var0, byte var1) {
      return constructor_impl((byte)(var0 & var1));
   }

   // $FF: synthetic method
   public static final UByte box_impl(byte var0) {
      return new UByte(var0);
   }

   private int compareTo_7apg3OU(byte var1) {
      return Intrinsics.compare(this.unbox_impl() & 255, var1 & 255);
   }

   private static int compareTo_7apg3OU(byte var0, byte var1) {
      return Intrinsics.compare(var0 & 255, var1 & 255);
   }

   private static final int compareTo_VKZWuLQ(byte var0, long var1) {
      return UnsignedKt.ulongCompare(ULong.constructor_impl((long)var0 & 255L), var1);
   }

   private static final int compareTo_WZ4Q5Ns(byte var0, int var1) {
      return UnsignedKt.uintCompare(UInt.constructor_impl(var0 & 255), var1);
   }

   private static final int compareTo_xj2QHRw(byte var0, short var1) {
      return Intrinsics.compare(var0 & 255, var1 & '\uffff');
   }

   public static byte constructor_impl(byte var0) {
      return var0;
   }

   private static final byte dec_w2LRezQ(byte var0) {
      return constructor_impl((byte)(var0 - 1));
   }

   private static final int div_7apg3OU(byte var0, byte var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & 255));
   }

   private static final long div_VKZWuLQ(byte var0, long var1) {
      return UnsignedKt.ulongDivide_eb3DHEI(ULong.constructor_impl((long)var0 & 255L), var1);
   }

   private static final int div_WZ4Q5Ns(byte var0, int var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & 255), var1);
   }

   private static final int div_xj2QHRw(byte var0, short var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & '\uffff'));
   }

   public static boolean equals_impl(byte var0, Object var1) {
      if (!(var1 instanceof UByte)) {
         return false;
      } else {
         return var0 == ((UByte)var1).unbox_impl();
      }
   }

   public static final boolean equals_impl0(byte var0, byte var1) {
      boolean var2;
      if (var0 == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private static final int floorDiv_7apg3OU(byte var0, byte var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & 255));
   }

   private static final long floorDiv_VKZWuLQ(byte var0, long var1) {
      return UnsignedKt.ulongDivide_eb3DHEI(ULong.constructor_impl((long)var0 & 255L), var1);
   }

   private static final int floorDiv_WZ4Q5Ns(byte var0, int var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & 255), var1);
   }

   private static final int floorDiv_xj2QHRw(byte var0, short var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & '\uffff'));
   }

   // $FF: synthetic method
   public static void getData$annotations() {
   }

   public static int hashCode_impl(byte var0) {
      return var0;
   }

   private static final byte inc_w2LRezQ(byte var0) {
      return constructor_impl((byte)(var0 + 1));
   }

   private static final byte inv_w2LRezQ(byte var0) {
      return constructor_impl((byte)(~var0));
   }

   private static final int minus_7apg3OU(byte var0, byte var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) - UInt.constructor_impl(var1 & 255));
   }

   private static final long minus_VKZWuLQ(byte var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 255L) - var1);
   }

   private static final int minus_WZ4Q5Ns(byte var0, int var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) - var1);
   }

   private static final int minus_xj2QHRw(byte var0, short var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) - UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final byte mod_7apg3OU(byte var0, byte var1) {
      return constructor_impl((byte)UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & 255)));
   }

   private static final long mod_VKZWuLQ(byte var0, long var1) {
      return UnsignedKt.ulongRemainder_eb3DHEI(ULong.constructor_impl((long)var0 & 255L), var1);
   }

   private static final int mod_WZ4Q5Ns(byte var0, int var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & 255), var1);
   }

   private static final short mod_xj2QHRw(byte var0, short var1) {
      return UShort.constructor_impl((short)UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & '\uffff')));
   }

   private static final byte or_7apg3OU(byte var0, byte var1) {
      return constructor_impl((byte)(var0 | var1));
   }

   private static final int plus_7apg3OU(byte var0, byte var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) + UInt.constructor_impl(var1 & 255));
   }

   private static final long plus_VKZWuLQ(byte var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 255L) + var1);
   }

   private static final int plus_WZ4Q5Ns(byte var0, int var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) + var1);
   }

   private static final int plus_xj2QHRw(byte var0, short var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) + UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final UIntRange rangeTo_7apg3OU(byte var0, byte var1) {
      return new UIntRange(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & 255), (DefaultConstructorMarker)null);
   }

   private static final int rem_7apg3OU(byte var0, byte var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & 255));
   }

   private static final long rem_VKZWuLQ(byte var0, long var1) {
      return UnsignedKt.ulongRemainder_eb3DHEI(ULong.constructor_impl((long)var0 & 255L), var1);
   }

   private static final int rem_WZ4Q5Ns(byte var0, int var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & 255), var1);
   }

   private static final int rem_xj2QHRw(byte var0, short var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & 255), UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final int times_7apg3OU(byte var0, byte var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) * UInt.constructor_impl(var1 & 255));
   }

   private static final long times_VKZWuLQ(byte var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 255L) * var1);
   }

   private static final int times_WZ4Q5Ns(byte var0, int var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) * var1);
   }

   private static final int times_xj2QHRw(byte var0, short var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & 255) * UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final byte toByte_impl(byte var0) {
      return var0;
   }

   private static final double toDouble_impl(byte var0) {
      return (double)(var0 & 255);
   }

   private static final float toFloat_impl(byte var0) {
      return (float)(var0 & 255);
   }

   private static final int toInt_impl(byte var0) {
      return var0 & 255;
   }

   private static final long toLong_impl(byte var0) {
      return (long)var0 & 255L;
   }

   private static final short toShort_impl(byte var0) {
      return (short)((short)var0 & 255);
   }

   public static String toString_impl(byte var0) {
      return String.valueOf(var0 & 255);
   }

   private static final byte toUByte_w2LRezQ(byte var0) {
      return var0;
   }

   private static final int toUInt_pVg5ArA(byte var0) {
      return UInt.constructor_impl(var0 & 255);
   }

   private static final long toULong_s_VKNKU(byte var0) {
      return ULong.constructor_impl((long)var0 & 255L);
   }

   private static final short toUShort_Mh2AYeg(byte var0) {
      return UShort.constructor_impl((short)((short)var0 & 255));
   }

   private static final byte xor_7apg3OU(byte var0, byte var1) {
      return constructor_impl((byte)(var0 ^ var1));
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
   public final byte unbox_impl() {
      return this.data;
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"},
      d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"},
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
