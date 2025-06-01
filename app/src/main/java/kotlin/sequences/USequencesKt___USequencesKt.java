package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00020\u00070\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"},
   d2 = {"sum", "Lkotlin/UInt;", "Lkotlin/sequences/Sequence;", "Lkotlin/UByte;", "sumOfUByte", "(Lkotlin/sequences/Sequence;)I", "sumOfUInt", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/sequences/USequencesKt"
)
class USequencesKt___USequencesKt {
   public USequencesKt___USequencesKt() {
   }

   public static final int sumOfUByte(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var2 = var0.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 = UInt.constructor_impl(var1 + UInt.constructor_impl(((UByte)var2.next()).unbox_impl() & 255))) {
      }

      return var1;
   }

   public static final int sumOfUInt(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var2 = var0.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 = UInt.constructor_impl(var1 + ((UInt)var2.next()).unbox_impl())) {
      }

      return var1;
   }

   public static final long sumOfULong(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var3 = var0.iterator();

      long var1;
      for(var1 = 0L; var3.hasNext(); var1 = ULong.constructor_impl(var1 + ((ULong)var3.next()).unbox_impl())) {
      }

      return var1;
   }

   public static final int sumOfUShort(Sequence var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Iterator var2 = var0.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 = UInt.constructor_impl(var1 + UInt.constructor_impl(((UShort)var2.next()).unbox_impl() & '\uffff'))) {
      }

      return var1;
   }
}
