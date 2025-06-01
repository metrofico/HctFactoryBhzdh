package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000b\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\b*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\f\u001a\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0010\u001a\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"},
   d2 = {"numberFormatError", "", "input", "", "toByteOrNull", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/StringsKt"
)
class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
   public StringsKt__StringNumberConversionsKt() {
   }

   public static final Void numberFormatError(String var0) {
      Intrinsics.checkNotNullParameter(var0, "input");
      throw new NumberFormatException("Invalid number format: '" + var0 + '\'');
   }

   public static final Byte toByteOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.toByteOrNull(var0, 10);
   }

   public static final Byte toByteOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var2 = StringsKt.toIntOrNull(var0, var1);
      if (var2 != null) {
         var1 = var2;
         if (var1 >= -128 && var1 <= 127) {
            return (byte)var1;
         }
      }

      return null;
   }

   public static final Integer toIntOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.toIntOrNull(var0, 10);
   }

   public static final Integer toIntOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      CharsKt.checkRadix(var1);
      int var8 = var0.length();
      if (var8 == 0) {
         return null;
      } else {
         int var7 = 0;
         int var5 = var0.charAt(0);
         int var2 = Intrinsics.compare(var5, 48);
         int var4 = -2147483647;
         int var3 = 1;
         boolean var11;
         if (var2 < 0) {
            if (var8 == 1) {
               return null;
            }

            if (var5 == 45) {
               var4 = Integer.MIN_VALUE;
               var11 = true;
            } else {
               if (var5 != 43) {
                  return null;
               }

               var11 = false;
            }
         } else {
            var11 = false;
            var3 = 0;
         }

         int var6 = -59652323;
         var5 = var3;

         while(true) {
            if (var5 >= var8) {
               Integer var10;
               if (var11) {
                  var10 = var7;
               } else {
                  var10 = -var7;
               }

               return var10;
            }

            int var9 = CharsKt.digitOf(var0.charAt(var5), var1);
            if (var9 < 0) {
               return null;
            }

            var3 = var6;
            if (var7 < var6) {
               if (var6 != -59652323) {
                  break;
               }

               var6 = var4 / var1;
               var3 = var6;
               if (var7 < var6) {
                  break;
               }
            }

            var6 = var7 * var1;
            if (var6 < var4 + var9) {
               return null;
            }

            var7 = var6 - var9;
            ++var5;
            var6 = var3;
         }

         return null;
      }
   }

   public static final Long toLongOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.toLongOrNull(var0, 10);
   }

   public static final Long toLongOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      CharsKt.checkRadix(var1);
      int var4 = var0.length();
      if (var4 == 0) {
         return null;
      } else {
         int var2 = 0;
         char var6 = var0.charAt(0);
         int var5 = Intrinsics.compare(var6, 48);
         long var7 = -9223372036854775807L;
         boolean var3 = true;
         if (var5 < 0) {
            if (var4 == 1) {
               return null;
            }

            if (var6 == '-') {
               var7 = Long.MIN_VALUE;
               var2 = 1;
            } else {
               if (var6 != '+') {
                  return null;
               }

               var3 = false;
               var2 = 1;
            }
         } else {
            var3 = false;
         }

         long var11 = 0L;
         long var13 = -256204778801521550L;

         while(true) {
            if (var2 >= var4) {
               Long var15;
               if (var3) {
                  var15 = var11;
               } else {
                  var15 = -var11;
               }

               return var15;
            }

            var5 = CharsKt.digitOf(var0.charAt(var2), var1);
            if (var5 < 0) {
               return null;
            }

            long var9 = var13;
            if (var11 < var13) {
               if (var13 != -256204778801521550L) {
                  break;
               }

               var13 = var7 / (long)var1;
               var9 = var13;
               if (var11 < var13) {
                  break;
               }
            }

            var13 = var11 * (long)var1;
            var11 = (long)var5;
            if (var13 < var7 + var11) {
               return null;
            }

            var11 = var13 - var11;
            ++var2;
            var13 = var9;
         }

         return null;
      }
   }

   public static final Short toShortOrNull(String var0) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      return StringsKt.toShortOrNull(var0, 10);
   }

   public static final Short toShortOrNull(String var0, int var1) {
      Intrinsics.checkNotNullParameter(var0, "<this>");
      Integer var2 = StringsKt.toIntOrNull(var0, var1);
      if (var2 != null) {
         var1 = var2;
         if (var1 >= -32768 && var1 <= 32767) {
            return (short)var1;
         }
      }

      return null;
   }
}
