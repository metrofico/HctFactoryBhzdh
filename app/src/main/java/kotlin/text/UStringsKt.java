package kotlin.text;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "},
   d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"},
   k = 2,
   mv = {1, 7, 1},
   xi = 48
)
public final class UStringsKt {
   public static final String toString_JSWoG40(long var0, int var2) {
      return UnsignedKt.ulongToString(var0, CharsKt.checkRadix(var2));
   }

   public static final String toString_LxnNnR4(byte var0, int var1) {
      String var2 = Integer.toString(var0 & 255, CharsKt.checkRadix(var1));
      Intrinsics.checkNotNullExpressionValue(var2, "toString(this, checkRadix(radix))");
      return var2;
   }

   public static final String toString_V7xB4Y4(int var0, int var1) {
      String var2 = Long.toString((long)var0 & 4294967295L, CharsKt.checkRadix(var1));
      Intrinsics.checkNotNullExpressionValue(var2, "toString(this, checkRadix(radix))");
      return var2;
   }

   public static final String toString_olVBNx4(short var0, int var1) {
      String var2 = Integer.toString(var0 & '\uffff', CharsKt.checkRadix(var1));
      Intrinsics.checkNotNullExpressionValue(var2, "toString(this, checkRadix(radix))");
      return var2;
   }

   public static final byte toUByte(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UByte var1 = toUByteOrNull(var0);
      if (var1 != null) {
         return var1.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final byte toUByte(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UByte var2 = toUByteOrNull(var0, var1);
      if (var2 != null) {
         return var2.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final UByte toUByteOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return toUByteOrNull(var0, 10);
   }

   public static final UByte toUByteOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UInt var2 = toUIntOrNull(var0, var1);
      if (var2 != null) {
         var1 = var2.unbox_impl();
         return UnsignedKt.uintCompare(var1, UInt.constructor_impl(255)) > 0 ? null : UByte.box_impl(UByte.constructor_impl((byte)var1));
      } else {
         return null;
      }
   }

   public static final int toUInt(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UInt var1 = toUIntOrNull(var0);
      if (var1 != null) {
         return var1.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final int toUInt(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UInt var2 = toUIntOrNull(var0, var1);
      if (var2 != null) {
         return var2.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final UInt toUIntOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return toUIntOrNull(var0, 10);
   }

   public static final UInt toUIntOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      CharsKt.checkRadix(var1);
      int var6 = var0.length();
      if (var6 == 0) {
         return null;
      } else {
         int var4 = 0;
         int var3 = var0.charAt(0);
         int var5 = Intrinsics.compare(var3, 48);
         int var2 = 1;
         if (var5 < 0) {
            if (var6 == 1 || var3 != 43) {
               return null;
            }
         } else {
            var2 = 0;
         }

         int var7 = UInt.constructor_impl(var1);
         var5 = 119304647;
         var3 = var2;

         while(true) {
            if (var3 >= var6) {
               return UInt.box_impl(var4);
            }

            int var8 = CharsKt.digitOf(var0.charAt(var3), var1);
            if (var8 < 0) {
               return null;
            }

            var2 = var5;
            if (UnsignedKt.uintCompare(var4, var5) > 0) {
               if (var5 != 119304647) {
                  break;
               }

               var5 = UnsignedKt.uintDivide_J1ME1BU(-1, var7);
               var2 = var5;
               if (UnsignedKt.uintCompare(var4, var5) > 0) {
                  break;
               }
            }

            var5 = UInt.constructor_impl(var4 * var7);
            var4 = UInt.constructor_impl(UInt.constructor_impl(var8) + var5);
            if (UnsignedKt.uintCompare(var4, var5) < 0) {
               return null;
            }

            ++var3;
            var5 = var2;
         }

         return null;
      }
   }

   public static final long toULong(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ULong var1 = toULongOrNull(var0);
      if (var1 != null) {
         return var1.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final long toULong(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ULong var2 = toULongOrNull(var0, var1);
      if (var2 != null) {
         return var2.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final ULong toULongOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return toULongOrNull(var0, 10);
   }

   public static final ULong toULongOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      CharsKt.checkRadix(var1);
      int var3 = var0.length();
      if (var3 == 0) {
         return null;
      } else {
         int var2 = 0;
         int var4 = var0.charAt(0);
         if (Intrinsics.compare(var4, 48) < 0) {
            if (var3 == 1 || var4 != 43) {
               return null;
            }

            var2 = 1;
         }

         long var11 = ULong.constructor_impl((long)var1);
         long var7 = 0L;
         long var9 = 512409557603043100L;

         while(true) {
            if (var2 >= var3) {
               return ULong.box_impl(var7);
            }

            var4 = CharsKt.digitOf(var0.charAt(var2), var1);
            if (var4 < 0) {
               return null;
            }

            long var5 = var9;
            if (UnsignedKt.ulongCompare(var7, var9) > 0) {
               if (var9 != 512409557603043100L) {
                  break;
               }

               var9 = UnsignedKt.ulongDivide_eb3DHEI(-1L, var11);
               var5 = var9;
               if (UnsignedKt.ulongCompare(var7, var9) > 0) {
                  break;
               }
            }

            var9 = ULong.constructor_impl(var7 * var11);
            var7 = ULong.constructor_impl(ULong.constructor_impl((long)UInt.constructor_impl(var4) & 4294967295L) + var9);
            if (UnsignedKt.ulongCompare(var7, var9) < 0) {
               return null;
            }

            ++var2;
            var9 = var5;
         }

         return null;
      }
   }

   public static final short toUShort(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UShort var1 = toUShortOrNull(var0);
      if (var1 != null) {
         return var1.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final short toUShort(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UShort var2 = toUShortOrNull(var0, var1);
      if (var2 != null) {
         return var2.unbox_impl();
      } else {
         StringsKt.numberFormatError(var0);
         throw new KotlinNothingValueException();
      }
   }

   public static final UShort toUShortOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return toUShortOrNull(var0, 10);
   }

   public static final UShort toUShortOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      UInt var2 = toUIntOrNull(var0, var1);
      if (var2 != null) {
         var1 = var2.unbox_impl();
         return UnsignedKt.uintCompare(var1, UInt.constructor_impl(65535)) > 0 ? null : UShort.box_impl(UShort.constructor_impl((short)var1));
      } else {
         return null;
      }
   }
}
