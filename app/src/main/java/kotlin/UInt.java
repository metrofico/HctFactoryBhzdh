package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;

@Metadata(
   d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 y2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001yB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u000fJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u000bJ\u001b\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u001dJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010\u0005J\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000fJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u0010/\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u001dJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0016J\u001b\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u001dJ\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0016J\u001b\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u000fJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u000bJ\u001b\u0010F\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u001dJ\u001b\u0010F\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0016J\u001e\u0010K\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bM\u0010\u000bJ\u001e\u0010N\u001a\u00020\u00002\u0006\u0010L\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u000fJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bR\u0010\u000bJ\u001b\u0010P\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bS\u0010\u001dJ\u001b\u0010P\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bT\u0010\u0016J\u0010\u0010U\u001a\u00020VH\u0087\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020ZH\u0087\b¢\u0006\u0004\b[\u0010\\J\u0010\u0010]\u001a\u00020^H\u0087\b¢\u0006\u0004\b_\u0010`J\u0010\u0010a\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bb\u0010\u0005J\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0016\u0010o\u001a\u00020\rH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bp\u0010XJ\u0016\u0010q\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\br\u0010\u0005J\u0016\u0010s\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bt\u0010fJ\u0016\u0010u\u001a\u00020\u0014H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bv\u0010jJ\u001b\u0010w\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bx\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006z"},
   d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
@JvmInline
public final class UInt implements Comparable {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final int MAX_VALUE = -1;
   public static final int MIN_VALUE = 0;
   public static final int SIZE_BITS = 32;
   public static final int SIZE_BYTES = 4;
   private final int data;

   // $FF: synthetic method
   private UInt(int var1) {
      this.data = var1;
   }

   private static final int and_WZ4Q5Ns(int var0, int var1) {
      return constructor_impl(var0 & var1);
   }

   // $FF: synthetic method
   public static final UInt box_impl(int var0) {
      return new UInt(var0);
   }

   private static final int compareTo_7apg3OU(int var0, byte var1) {
      return UnsignedKt.uintCompare(var0, constructor_impl(var1 & 255));
   }

   private static final int compareTo_VKZWuLQ(int var0, long var1) {
      return UnsignedKt.ulongCompare(ULong.constructor_impl((long)var0 & 4294967295L), var1);
   }

   private int compareTo_WZ4Q5Ns(int var1) {
      return UnsignedKt.uintCompare(this.unbox_impl(), var1);
   }

   private static int compareTo_WZ4Q5Ns(int var0, int var1) {
      return UnsignedKt.uintCompare(var0, var1);
   }

   private static final int compareTo_xj2QHRw(int var0, short var1) {
      return UnsignedKt.uintCompare(var0, constructor_impl(var1 & '\uffff'));
   }

   public static int constructor_impl(int var0) {
      return var0;
   }

   private static final int dec_pVg5ArA(int var0) {
      return constructor_impl(var0 - 1);
   }

   private static final int div_7apg3OU(int var0, byte var1) {
      return UnsignedKt.uintDivide_J1ME1BU(var0, constructor_impl(var1 & 255));
   }

   private static final long div_VKZWuLQ(int var0, long var1) {
      return UnsignedKt.ulongDivide_eb3DHEI(ULong.constructor_impl((long)var0 & 4294967295L), var1);
   }

   private static final int div_WZ4Q5Ns(int var0, int var1) {
      return UnsignedKt.uintDivide_J1ME1BU(var0, var1);
   }

   private static final int div_xj2QHRw(int var0, short var1) {
      return UnsignedKt.uintDivide_J1ME1BU(var0, constructor_impl(var1 & '\uffff'));
   }

   public static boolean equals_impl(int var0, Object var1) {
      if (!(var1 instanceof UInt)) {
         return false;
      } else {
         return var0 == ((UInt)var1).unbox_impl();
      }
   }

   public static final boolean equals_impl0(int var0, int var1) {
      boolean var2;
      if (var0 == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private static final int floorDiv_7apg3OU(int var0, byte var1) {
      return UnsignedKt.uintDivide_J1ME1BU(var0, constructor_impl(var1 & 255));
   }

   private static final long floorDiv_VKZWuLQ(int var0, long var1) {
      return UnsignedKt.ulongDivide_eb3DHEI(ULong.constructor_impl((long)var0 & 4294967295L), var1);
   }

   private static final int floorDiv_WZ4Q5Ns(int var0, int var1) {
      return UnsignedKt.uintDivide_J1ME1BU(var0, var1);
   }

   private static final int floorDiv_xj2QHRw(int var0, short var1) {
      return UnsignedKt.uintDivide_J1ME1BU(var0, constructor_impl(var1 & '\uffff'));
   }

   // $FF: synthetic method
   public static void getData$annotations() {
   }

   public static int hashCode_impl(int var0) {
      return var0;
   }

   private static final int inc_pVg5ArA(int var0) {
      return constructor_impl(var0 + 1);
   }

   private static final int inv_pVg5ArA(int var0) {
      return constructor_impl(~var0);
   }

   private static final int minus_7apg3OU(int var0, byte var1) {
      return constructor_impl(var0 - constructor_impl(var1 & 255));
   }

   private static final long minus_VKZWuLQ(int var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 4294967295L) - var1);
   }

   private static final int minus_WZ4Q5Ns(int var0, int var1) {
      return constructor_impl(var0 - var1);
   }

   private static final int minus_xj2QHRw(int var0, short var1) {
      return constructor_impl(var0 - constructor_impl(var1 & '\uffff'));
   }

   private static final byte mod_7apg3OU(int var0, byte var1) {
      return UByte.constructor_impl((byte)UnsignedKt.uintRemainder_J1ME1BU(var0, constructor_impl(var1 & 255)));
   }

   private static final long mod_VKZWuLQ(int var0, long var1) {
      return UnsignedKt.ulongRemainder_eb3DHEI(ULong.constructor_impl((long)var0 & 4294967295L), var1);
   }

   private static final int mod_WZ4Q5Ns(int var0, int var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(var0, var1);
   }

   private static final short mod_xj2QHRw(int var0, short var1) {
      return UShort.constructor_impl((short)UnsignedKt.uintRemainder_J1ME1BU(var0, constructor_impl(var1 & '\uffff')));
   }

   private static final int or_WZ4Q5Ns(int var0, int var1) {
      return constructor_impl(var0 | var1);
   }

   private static final int plus_7apg3OU(int var0, byte var1) {
      return constructor_impl(var0 + constructor_impl(var1 & 255));
   }

   private static final long plus_VKZWuLQ(int var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 4294967295L) + var1);
   }

   private static final int plus_WZ4Q5Ns(int var0, int var1) {
      return constructor_impl(var0 + var1);
   }

   private static final int plus_xj2QHRw(int var0, short var1) {
      return constructor_impl(var0 + constructor_impl(var1 & '\uffff'));
   }

   private static final UIntRange rangeTo_WZ4Q5Ns(int var0, int var1) {
      return new UIntRange(var0, var1, (DefaultConstructorMarker)null);
   }

   private static final int rem_7apg3OU(int var0, byte var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(var0, constructor_impl(var1 & 255));
   }

   private static final long rem_VKZWuLQ(int var0, long var1) {
      return UnsignedKt.ulongRemainder_eb3DHEI(ULong.constructor_impl((long)var0 & 4294967295L), var1);
   }

   private static final int rem_WZ4Q5Ns(int var0, int var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(var0, var1);
   }

   private static final int rem_xj2QHRw(int var0, short var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(var0, constructor_impl(var1 & '\uffff'));
   }

   private static final int shl_pVg5ArA(int var0, int var1) {
      return constructor_impl(var0 << var1);
   }

   private static final int shr_pVg5ArA(int var0, int var1) {
      return constructor_impl(var0 >>> var1);
   }

   private static final int times_7apg3OU(int var0, byte var1) {
      return constructor_impl(var0 * constructor_impl(var1 & 255));
   }

   private static final long times_VKZWuLQ(int var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 4294967295L) * var1);
   }

   private static final int times_WZ4Q5Ns(int var0, int var1) {
      return constructor_impl(var0 * var1);
   }

   private static final int times_xj2QHRw(int var0, short var1) {
      return constructor_impl(var0 * constructor_impl(var1 & '\uffff'));
   }

   private static final byte toByte_impl(int var0) {
      return (byte)var0;
   }

   private static final double toDouble_impl(int var0) {
      return UnsignedKt.uintToDouble(var0);
   }

   private static final float toFloat_impl(int var0) {
      return (float)UnsignedKt.uintToDouble(var0);
   }

   private static final int toInt_impl(int var0) {
      return var0;
   }

   private static final long toLong_impl(int var0) {
      return (long)var0 & 4294967295L;
   }

   private static final short toShort_impl(int var0) {
      return (short)var0;
   }

   public static String toString_impl(int var0) {
      return String.valueOf((long)var0 & 4294967295L);
   }

   private static final byte toUByte_w2LRezQ(int var0) {
      return UByte.constructor_impl((byte)var0);
   }

   private static final int toUInt_pVg5ArA(int var0) {
      return var0;
   }

   private static final long toULong_s_VKNKU(int var0) {
      return ULong.constructor_impl((long)var0 & 4294967295L);
   }

   private static final short toUShort_Mh2AYeg(int var0) {
      return UShort.constructor_impl((short)var0);
   }

   private static final int xor_WZ4Q5Ns(int var0, int var1) {
      return constructor_impl(var0 ^ var1);
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
   public final int unbox_impl() {
      return this.data;
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"},
      d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"},
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
