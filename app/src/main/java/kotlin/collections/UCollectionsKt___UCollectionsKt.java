package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u001a\u001a\u0010\f\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u001a\u0010\u0013\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u00070\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001a\u0010\u0016\u001a\u00020\u0017*\b\u0012\u0004\u0012\u00020\n0\u000eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"},
   d2 = {"sum", "Lkotlin/UInt;", "", "Lkotlin/UByte;", "sumOfUByte", "(Ljava/lang/Iterable;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Ljava/lang/Iterable;)J", "Lkotlin/UShort;", "sumOfUShort", "toUByteArray", "Lkotlin/UByteArray;", "", "(Ljava/util/Collection;)[B", "toUIntArray", "Lkotlin/UIntArray;", "(Ljava/util/Collection;)[I", "toULongArray", "Lkotlin/ULongArray;", "(Ljava/util/Collection;)[J", "toUShortArray", "Lkotlin/UShortArray;", "(Ljava/util/Collection;)[S", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/UCollectionsKt"
)
class UCollectionsKt___UCollectionsKt {
   public UCollectionsKt___UCollectionsKt() {
   }

   public static final int sumOfUByte(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var2 = var0.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 = UInt.constructor_impl(var1 + UInt.constructor_impl(((UByte)var2.next()).unbox_impl() & 255))) {
      }

      return var1;
   }

   public static final int sumOfUInt(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var2 = var0.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 = UInt.constructor_impl(var1 + ((UInt)var2.next()).unbox_impl())) {
      }

      return var1;
   }

   public static final long sumOfULong(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var3 = var0.iterator();

      long var1;
      for(var1 = 0L; var3.hasNext(); var1 = ULong.constructor_impl(var1 + ((ULong)var3.next()).unbox_impl())) {
      }

      return var1;
   }

   public static final int sumOfUShort(Iterable var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var2 = var0.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 = UInt.constructor_impl(var1 + UInt.constructor_impl(((UShort)var2.next()).unbox_impl() & '\uffff'))) {
      }

      return var1;
   }

   public static final byte[] toUByteArray(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      byte[] var2 = UByteArray.constructor_impl(var0.size());
      Iterator var3 = var0.iterator();

      for(int var1 = 0; var3.hasNext(); ++var1) {
         UByteArray.set_VurrAj0(var2, var1, ((UByte)var3.next()).unbox_impl());
      }

      return var2;
   }

   public static final int[] toUIntArray(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      int[] var2 = UIntArray.constructor_impl(var0.size());
      Iterator var3 = var0.iterator();

      for(int var1 = 0; var3.hasNext(); ++var1) {
         UIntArray.set_VXSXFK8(var2, var1, ((UInt)var3.next()).unbox_impl());
      }

      return var2;
   }

   public static final long[] toULongArray(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      long[] var2 = ULongArray.constructor_impl(var0.size());
      Iterator var3 = var0.iterator();

      for(int var1 = 0; var3.hasNext(); ++var1) {
         ULongArray.set_k8EXiF4(var2, var1, ((ULong)var3.next()).unbox_impl());
      }

      return var2;
   }

   public static final short[] toUShortArray(Collection var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      short[] var2 = UShortArray.constructor_impl(var0.size());
      Iterator var3 = var0.iterator();

      for(int var1 = 0; var3.hasNext(); ++var1) {
         UShortArray.set_01HTLdE(var2, var1, ((UShort)var3.next()).unbox_impl());
      }

      return var2;
   }
}
