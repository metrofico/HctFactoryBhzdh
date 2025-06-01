package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

@Metadata(
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001tB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u0010J\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0013J\u001b\u0010&\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001b\u0010&\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0005J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0010J\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u0013J\u001b\u00102\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u001fJ\u001b\u00102\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0018J\u001b\u00107\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\u000eH\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001b\u00107\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b:\u0010\u0013J\u001b\u00107\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b;\u0010\u001fJ\u001b\u00107\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b>\u0010\u000bJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u0010J\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0013J\u001b\u0010?\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bB\u0010\u001fJ\u001b\u0010?\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0018J\u001b\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010GJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u0010J\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u0013J\u001b\u0010H\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001fJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0018J\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bN\u0010\u0010J\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bO\u0010\u0013J\u001b\u0010M\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bP\u0010\u001fJ\u001b\u0010M\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bQ\u0010\u0018J\u0010\u0010R\u001a\u00020SH\u0087\b¢\u0006\u0004\bT\u0010UJ\u0010\u0010V\u001a\u00020WH\u0087\b¢\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001a\u00020[H\u0087\b¢\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001a\u00020\rH\u0087\b¢\u0006\u0004\b_\u0010-J\u0010\u0010`\u001a\u00020aH\u0087\b¢\u0006\u0004\bb\u0010cJ\u0010\u0010d\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\be\u0010\u0005J\u000f\u0010f\u001a\u00020gH\u0016¢\u0006\u0004\bh\u0010iJ\u0016\u0010j\u001a\u00020\u000eH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bk\u0010UJ\u0016\u0010l\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bm\u0010-J\u0016\u0010n\u001a\u00020\u0014H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bo\u0010cJ\u0016\u0010p\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bq\u0010\u0005J\u001b\u0010r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bs\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006u"},
   d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-Mh2AYeg", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(SLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(S)I", "inc", "inc-Mh2AYeg", "inv", "inv-Mh2AYeg", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(SB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toDouble", "", "toDouble-impl", "(S)D", "toFloat", "", "toFloat-impl", "(S)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
@JvmInline
public final class UShort implements Comparable {
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   public static final short MAX_VALUE = -1;
   public static final short MIN_VALUE = 0;
   public static final int SIZE_BITS = 16;
   public static final int SIZE_BYTES = 2;
   private final short data;

   // $FF: synthetic method
   private UShort(short var1) {
      this.data = var1;
   }

   private static final short and_xj2QHRw(short var0, short var1) {
      return constructor_impl((short)(var0 & var1));
   }

   // $FF: synthetic method
   public static final UShort box_impl(short var0) {
      return new UShort(var0);
   }

   private static final int compareTo_7apg3OU(short var0, byte var1) {
      return Intrinsics.compare(var0 & '\uffff', var1 & 255);
   }

   private static final int compareTo_VKZWuLQ(short var0, long var1) {
      return UnsignedKt.ulongCompare(ULong.constructor_impl((long)var0 & 65535L), var1);
   }

   private static final int compareTo_WZ4Q5Ns(short var0, int var1) {
      return UnsignedKt.uintCompare(UInt.constructor_impl(var0 & '\uffff'), var1);
   }

   private int compareTo_xj2QHRw(short var1) {
      return Intrinsics.compare(this.unbox_impl() & '\uffff', var1 & '\uffff');
   }

   private static int compareTo_xj2QHRw(short var0, short var1) {
      return Intrinsics.compare(var0 & '\uffff', var1 & '\uffff');
   }

   public static short constructor_impl(short var0) {
      return var0;
   }

   private static final short dec_Mh2AYeg(short var0) {
      return constructor_impl((short)(var0 - 1));
   }

   private static final int div_7apg3OU(short var0, byte var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & 255));
   }

   private static final long div_VKZWuLQ(short var0, long var1) {
      return UnsignedKt.ulongDivide_eb3DHEI(ULong.constructor_impl((long)var0 & 65535L), var1);
   }

   private static final int div_WZ4Q5Ns(short var0, int var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), var1);
   }

   private static final int div_xj2QHRw(short var0, short var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & '\uffff'));
   }

   public static boolean equals_impl(short var0, Object var1) {
      if (!(var1 instanceof UShort)) {
         return false;
      } else {
         return var0 == ((UShort)var1).unbox_impl();
      }
   }

   public static final boolean equals_impl0(short var0, short var1) {
      boolean var2;
      if (var0 == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private static final int floorDiv_7apg3OU(short var0, byte var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & 255));
   }

   private static final long floorDiv_VKZWuLQ(short var0, long var1) {
      return UnsignedKt.ulongDivide_eb3DHEI(ULong.constructor_impl((long)var0 & 65535L), var1);
   }

   private static final int floorDiv_WZ4Q5Ns(short var0, int var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), var1);
   }

   private static final int floorDiv_xj2QHRw(short var0, short var1) {
      return UnsignedKt.uintDivide_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & '\uffff'));
   }

   // $FF: synthetic method
   public static void getData$annotations() {
   }

   public static int hashCode_impl(short var0) {
      return var0;
   }

   private static final short inc_Mh2AYeg(short var0) {
      return constructor_impl((short)(var0 + 1));
   }

   private static final short inv_Mh2AYeg(short var0) {
      return constructor_impl((short)(~var0));
   }

   private static final int minus_7apg3OU(short var0, byte var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') - UInt.constructor_impl(var1 & 255));
   }

   private static final long minus_VKZWuLQ(short var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 65535L) - var1);
   }

   private static final int minus_WZ4Q5Ns(short var0, int var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') - var1);
   }

   private static final int minus_xj2QHRw(short var0, short var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') - UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final byte mod_7apg3OU(short var0, byte var1) {
      return UByte.constructor_impl((byte)UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & 255)));
   }

   private static final long mod_VKZWuLQ(short var0, long var1) {
      return UnsignedKt.ulongRemainder_eb3DHEI(ULong.constructor_impl((long)var0 & 65535L), var1);
   }

   private static final int mod_WZ4Q5Ns(short var0, int var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), var1);
   }

   private static final short mod_xj2QHRw(short var0, short var1) {
      return constructor_impl((short)UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & '\uffff')));
   }

   private static final short or_xj2QHRw(short var0, short var1) {
      return constructor_impl((short)(var0 | var1));
   }

   private static final int plus_7apg3OU(short var0, byte var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') + UInt.constructor_impl(var1 & 255));
   }

   private static final long plus_VKZWuLQ(short var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 65535L) + var1);
   }

   private static final int plus_WZ4Q5Ns(short var0, int var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') + var1);
   }

   private static final int plus_xj2QHRw(short var0, short var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') + UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final UIntRange rangeTo_xj2QHRw(short var0, short var1) {
      return new UIntRange(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & '\uffff'), (DefaultConstructorMarker)null);
   }

   private static final int rem_7apg3OU(short var0, byte var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & 255));
   }

   private static final long rem_VKZWuLQ(short var0, long var1) {
      return UnsignedKt.ulongRemainder_eb3DHEI(ULong.constructor_impl((long)var0 & 65535L), var1);
   }

   private static final int rem_WZ4Q5Ns(short var0, int var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), var1);
   }

   private static final int rem_xj2QHRw(short var0, short var1) {
      return UnsignedKt.uintRemainder_J1ME1BU(UInt.constructor_impl(var0 & '\uffff'), UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final int times_7apg3OU(short var0, byte var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') * UInt.constructor_impl(var1 & 255));
   }

   private static final long times_VKZWuLQ(short var0, long var1) {
      return ULong.constructor_impl(ULong.constructor_impl((long)var0 & 65535L) * var1);
   }

   private static final int times_WZ4Q5Ns(short var0, int var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') * var1);
   }

   private static final int times_xj2QHRw(short var0, short var1) {
      return UInt.constructor_impl(UInt.constructor_impl(var0 & '\uffff') * UInt.constructor_impl(var1 & '\uffff'));
   }

   private static final byte toByte_impl(short var0) {
      return (byte)var0;
   }

   private static final double toDouble_impl(short var0) {
      return (double)(var0 & '\uffff');
   }

   private static final float toFloat_impl(short var0) {
      return (float)(var0 & '\uffff');
   }

   private static final int toInt_impl(short var0) {
      return var0 & '\uffff';
   }

   private static final long toLong_impl(short var0) {
      return (long)var0 & 65535L;
   }

   private static final short toShort_impl(short var0) {
      return var0;
   }

   public static String toString_impl(short var0) {
      return String.valueOf(var0 & '\uffff');
   }

   private static final byte toUByte_w2LRezQ(short var0) {
      return UByte.constructor_impl((byte)var0);
   }

   private static final int toUInt_pVg5ArA(short var0) {
      return UInt.constructor_impl(var0 & '\uffff');
   }

   private static final long toULong_s_VKNKU(short var0) {
      return ULong.constructor_impl((long)var0 & 65535L);
   }

   private static final short toUShort_Mh2AYeg(short var0) {
      return var0;
   }

   private static final short xor_xj2QHRw(short var0, short var1) {
      return constructor_impl((short)(var0 ^ var1));
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
   public final short unbox_impl() {
      return this.data;
   }

   @Metadata(
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"},
      d2 = {"Lkotlin/UShort$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UShort;", "S", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"},
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
