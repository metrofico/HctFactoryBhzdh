package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;

@Metadata(
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0002*\u00020\u0001H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\t\u001a\n\u0010\f\u001a\u00020\t*\u00020\u0001\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000eH\u0087\n\u001a\f\u0010\u000f\u001a\u00020\u000e*\u00020\u0001H\u0007¨\u0006\u0010"},
   d2 = {"digitToChar", "", "", "radix", "digitToInt", "digitToIntOrNull", "(C)Ljava/lang/Integer;", "(CI)Ljava/lang/Integer;", "equals", "", "other", "ignoreCase", "isSurrogate", "plus", "", "titlecase", "kotlin-stdlib"},
   k = 5,
   mv = {1, 7, 1},
   xi = 49,
   xs = "kotlin/text/CharsKt"
)
class CharsKt__CharKt extends CharsKt__CharJVMKt {
   public CharsKt__CharKt() {
   }

   public static final char digitToChar(int var0) {
      if ((new IntRange(0, 9)).contains(var0)) {
         return (char)(var0 + 48);
      } else {
         throw new IllegalArgumentException("Int " + var0 + " is not a decimal digit");
      }
   }

   public static final char digitToChar(int var0, int var1) {
      if ((new IntRange(2, 36)).contains(var1)) {
         if (var0 >= 0 && var0 < var1) {
            if (var0 < 10) {
               var0 += 48;
            } else {
               var0 = (char)(var0 + 65) - 10;
            }

            return (char)var0;
         } else {
            throw new IllegalArgumentException("Digit " + var0 + " does not represent a valid digit in radix " + var1);
         }
      } else {
         throw new IllegalArgumentException("Invalid radix: " + var1 + ". Valid radix values are in range 2..36");
      }
   }

   public static final int digitToInt(char var0) {
      int var1 = CharsKt.digitOf(var0, 10);
      if (var1 >= 0) {
         return var1;
      } else {
         throw new IllegalArgumentException("Char " + var0 + " is not a decimal digit");
      }
   }

   public static final int digitToInt(char var0, int var1) {
      Integer var2 = CharsKt.digitToIntOrNull(var0, var1);
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalArgumentException("Char " + var0 + " is not a digit in the given radix=" + var1);
      }
   }

   public static final Integer digitToIntOrNull(char var0) {
      Integer var2 = CharsKt.digitOf(var0, 10);
      boolean var1;
      if (((Number)var2).intValue() >= 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      if (!var1) {
         var2 = null;
      }

      return var2;
   }

   public static final Integer digitToIntOrNull(char var0, int var1) {
      CharsKt.checkRadix(var1);
      Integer var2 = CharsKt.digitOf(var0, var1);
      boolean var3;
      if (((Number)var2).intValue() >= 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      if (!var3) {
         var2 = null;
      }

      return var2;
   }

   public static final boolean equals(char var0, char var1, boolean var2) {
      boolean var3 = true;
      if (var0 == var1) {
         return true;
      } else if (!var2) {
         return false;
      } else {
         var0 = Character.toUpperCase(var0);
         var1 = Character.toUpperCase(var1);
         var2 = var3;
         if (var0 != var1) {
            if (Character.toLowerCase(var0) == Character.toLowerCase(var1)) {
               var2 = var3;
            } else {
               var2 = false;
            }
         }

         return var2;
      }
   }

   // $FF: synthetic method
   public static boolean equals$default(char var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return CharsKt.equals(var0, var1, var2);
   }

   public static final boolean isSurrogate(char var0) {
      return (new CharRange('\ud800', '\udfff')).contains(var0);
   }

   private static final String plus(char var0, String var1) {
      Intrinsics.checkNotNullParameter(var1, "other");
      return var0 + var1;
   }

   public static final String titlecase(char var0) {
      return _OneToManyTitlecaseMappingsKt.titlecaseImpl(var0);
   }
}
