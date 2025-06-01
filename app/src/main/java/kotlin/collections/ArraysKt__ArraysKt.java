package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(
   d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001a?\u0010\u000b\u001a\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001a+\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001a;\u0010\u0017\u001a\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u0010\u001d\u001a\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001e\u001aG\u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""},
   d2 = {"contentDeepEqualsImpl", "", "T", "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/collections/ArraysKt"
)
class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
   public ArraysKt__ArraysKt() {
   }

   public static final boolean contentDeepEquals(Object[] var0, Object[] var1) {
      if (var0 == var1) {
         return true;
      } else if (var0 != null && var1 != null && var0.length == var1.length) {
         int var3 = var0.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            Object var4 = var0[var2];
            Object var5 = var1[var2];
            if (var4 != var5) {
               if (var4 == null || var5 == null) {
                  return false;
               }

               if (var4 instanceof Object[] && var5 instanceof Object[]) {
                  if (!ArraysKt.contentDeepEquals((Object[])var4, (Object[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof byte[] && var5 instanceof byte[]) {
                  if (!Arrays.equals((byte[])var4, (byte[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof short[] && var5 instanceof short[]) {
                  if (!Arrays.equals((short[])var4, (short[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof int[] && var5 instanceof int[]) {
                  if (!Arrays.equals((int[])var4, (int[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof long[] && var5 instanceof long[]) {
                  if (!Arrays.equals((long[])var4, (long[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof float[] && var5 instanceof float[]) {
                  if (!Arrays.equals((float[])var4, (float[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof double[] && var5 instanceof double[]) {
                  if (!Arrays.equals((double[])var4, (double[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof char[] && var5 instanceof char[]) {
                  if (!Arrays.equals((char[])var4, (char[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof boolean[] && var5 instanceof boolean[]) {
                  if (!Arrays.equals((boolean[])var4, (boolean[])var5)) {
                     return false;
                  }
               } else if (var4 instanceof UByteArray && var5 instanceof UByteArray) {
                  if (!UArraysKt.contentEquals_kV0jMPg(((UByteArray)var4).unbox_impl(), ((UByteArray)var5).unbox_impl())) {
                     return false;
                  }
               } else if (var4 instanceof UShortArray && var5 instanceof UShortArray) {
                  if (!UArraysKt.contentEquals_FGO6Aew(((UShortArray)var4).unbox_impl(), ((UShortArray)var5).unbox_impl())) {
                     return false;
                  }
               } else if (var4 instanceof UIntArray && var5 instanceof UIntArray) {
                  if (!UArraysKt.contentEquals_KJPZfPQ(((UIntArray)var4).unbox_impl(), ((UIntArray)var5).unbox_impl())) {
                     return false;
                  }
               } else if (var4 instanceof ULongArray && var5 instanceof ULongArray) {
                  if (!UArraysKt.contentEquals_lec5QzE(((ULongArray)var4).unbox_impl(), ((ULongArray)var5).unbox_impl())) {
                     return false;
                  }
               } else if (!Intrinsics.areEqual(var4, var5)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final String contentDeepToString(Object[] var0) {
      if (var0 == null) {
         return "null";
      } else {
         StringBuilder var1 = new StringBuilder(RangesKt.coerceAtMost(var0.length, 429496729) * 5 + 2);
         contentDeepToStringInternal$ArraysKt__ArraysKt(var0, var1, (List)(new ArrayList()));
         String var2 = var1.toString();
         Intrinsics.checkNotNullExpressionValue(var2, "StringBuilder(capacity).…builderAction).toString()");
         return var2;
      }
   }

   private static final void contentDeepToStringInternal$ArraysKt__ArraysKt(Object[] var0, StringBuilder var1, List var2) {
      if (var2.contains(var0)) {
         var1.append("[...]");
      } else {
         var2.add(var0);
         var1.append('[');
         int var3 = 0;

         for(int var4 = var0.length; var3 < var4; ++var3) {
            if (var3 != 0) {
               var1.append(", ");
            }

            Object var10 = var0[var3];
            if (var10 == null) {
               var1.append("null");
            } else if (var10 instanceof Object[]) {
               contentDeepToStringInternal$ArraysKt__ArraysKt((Object[])var10, var1, var2);
            } else {
               String var6;
               if (var10 instanceof byte[]) {
                  var6 = Arrays.toString((byte[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else if (var10 instanceof short[]) {
                  var6 = Arrays.toString((short[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else if (var10 instanceof int[]) {
                  var6 = Arrays.toString((int[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else if (var10 instanceof long[]) {
                  var6 = Arrays.toString((long[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else if (var10 instanceof float[]) {
                  var6 = Arrays.toString((float[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else if (var10 instanceof double[]) {
                  var6 = Arrays.toString((double[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else if (var10 instanceof char[]) {
                  var6 = Arrays.toString((char[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else if (var10 instanceof boolean[]) {
                  var6 = Arrays.toString((boolean[])var10);
                  Intrinsics.checkNotNullExpressionValue(var6, "toString(this)");
                  var1.append(var6);
               } else {
                  boolean var5 = var10 instanceof UByteArray;
                  ULongArray var8 = null;
                  Object var9 = null;
                  UByteArray var7 = null;
                  byte[] var13 = null;
                  if (var5) {
                     var7 = (UByteArray)var10;
                     if (var7 != null) {
                        var13 = var7.unbox_impl();
                     }

                     var1.append(UArraysKt.contentToString_2csIQuQ(var13));
                  } else if (var10 instanceof UShortArray) {
                     UShortArray var11 = (UShortArray)var10;
                     short[] var14 = (short[])var8;
                     if (var11 != null) {
                        var14 = var11.unbox_impl();
                     }

                     var1.append(UArraysKt.contentToString_d_6D3K8(var14));
                  } else if (var10 instanceof UIntArray) {
                     UIntArray var12 = (UIntArray)var10;
                     int[] var15 = (int[])var9;
                     if (var12 != null) {
                        var15 = var12.unbox_impl();
                     }

                     var1.append(UArraysKt.contentToString_XUkPCBk(var15));
                  } else if (var10 instanceof ULongArray) {
                     var8 = (ULongArray)var10;
                     long[] var16 = (long[])var7;
                     if (var8 != null) {
                        var16 = var8.unbox_impl();
                     }

                     var1.append(UArraysKt.contentToString_uLth9ew(var16));
                  } else {
                     var1.append(var10.toString());
                  }
               }
            }
         }

         var1.append(']');
         var2.remove(CollectionsKt.getLastIndex(var2));
      }
   }

   public static final List flatten(Object[][] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Object[] var6 = (Object[])var0;
      int var4 = var6.length;
      byte var3 = 0;
      int var2 = 0;

      int var1;
      for(var1 = 0; var2 < var4; ++var2) {
         var1 += ((Object[])var6[var2]).length;
      }

      ArrayList var5 = new ArrayList(var1);
      var2 = var6.length;

      for(var1 = var3; var1 < var2; ++var1) {
         var6 = var0[var1];
         CollectionsKt.addAll((Collection)var5, var6);
      }

      return (List)var5;
   }

   private static final Object ifEmpty(Object[] var0, Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "defaultValue");
      boolean var2;
      if (((Object[])var0).length == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      if (var2) {
         var0 = var1.invoke();
      }

      return var0;
   }

   private static final boolean isNullOrEmpty(Object[] var0) {
      boolean var2 = false;
      if (var0 != null) {
         boolean var1;
         if (var0.length == 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         if (!var1) {
            return var2;
         }
      }

      var2 = true;
      return var2;
   }

   public static final Pair unzip(Pair[] var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      ArrayList var4 = new ArrayList(var0.length);
      ArrayList var3 = new ArrayList(var0.length);
      int var2 = var0.length;

      for(int var1 = 0; var1 < var2; ++var1) {
         Pair var5 = var0[var1];
         var4.add(var5.getFirst());
         var3.add(var5.getSecond());
      }

      return TuplesKt.to(var4, var3);
   }
}
