package androidx.core.math;

public class MathUtils {
   private MathUtils() {
   }

   public static int addExact(int var0, int var1) {
      int var2 = var0 + var1;
      if (((var0 ^ var2) & (var1 ^ var2)) >= 0) {
         return var2;
      } else {
         throw new ArithmeticException("integer overflow");
      }
   }

   public static long addExact(long var0, long var2) {
      long var4 = var0 + var2;
      if (((var0 ^ var4) & (var2 ^ var4)) >= 0L) {
         return var4;
      } else {
         throw new ArithmeticException("long overflow");
      }
   }

   public static double clamp(double var0, double var2, double var4) {
      if (var0 < var2) {
         return var2;
      } else {
         return var0 > var4 ? var4 : var0;
      }
   }

   public static float clamp(float var0, float var1, float var2) {
      if (var0 < var1) {
         return var1;
      } else {
         return var0 > var2 ? var2 : var0;
      }
   }

   public static int clamp(int var0, int var1, int var2) {
      if (var0 < var1) {
         return var1;
      } else {
         return var0 > var2 ? var2 : var0;
      }
   }

   public static long clamp(long var0, long var2, long var4) {
      if (var0 < var2) {
         return var2;
      } else {
         return var0 > var4 ? var4 : var0;
      }
   }

   public static int decrementExact(int var0) {
      if (var0 != Integer.MIN_VALUE) {
         return var0 - 1;
      } else {
         throw new ArithmeticException("integer overflow");
      }
   }

   public static long decrementExact(long var0) {
      if (var0 != Long.MIN_VALUE) {
         return var0 - 1L;
      } else {
         throw new ArithmeticException("long overflow");
      }
   }

   public static int incrementExact(int var0) {
      if (var0 != Integer.MAX_VALUE) {
         return var0 + 1;
      } else {
         throw new ArithmeticException("integer overflow");
      }
   }

   public static long incrementExact(long var0) {
      if (var0 != Long.MAX_VALUE) {
         return var0 + 1L;
      } else {
         throw new ArithmeticException("long overflow");
      }
   }

   public static int multiplyExact(int var0, int var1) {
      long var2 = (long)var0 * (long)var1;
      var0 = (int)var2;
      if ((long)var0 == var2) {
         return var0;
      } else {
         throw new ArithmeticException("integer overflow");
      }
   }

   public static long multiplyExact(long var0, long var2) {
      long var4 = var0 * var2;
      if ((Math.abs(var0) | Math.abs(var2)) >>> 31 == 0L || (var2 == 0L || var4 / var2 == var0) && (var0 != Long.MIN_VALUE || var2 != -1L)) {
         return var4;
      } else {
         throw new ArithmeticException("long overflow");
      }
   }

   public static int negateExact(int var0) {
      if (var0 != Integer.MIN_VALUE) {
         return -var0;
      } else {
         throw new ArithmeticException("integer overflow");
      }
   }

   public static long negateExact(long var0) {
      if (var0 != Long.MIN_VALUE) {
         return -var0;
      } else {
         throw new ArithmeticException("long overflow");
      }
   }

   public static int subtractExact(int var0, int var1) {
      int var2 = var0 - var1;
      if (((var0 ^ var2) & (var1 ^ var0)) >= 0) {
         return var2;
      } else {
         throw new ArithmeticException("integer overflow");
      }
   }

   public static long subtractExact(long var0, long var2) {
      long var4 = var0 - var2;
      if (((var0 ^ var4) & (var2 ^ var0)) >= 0L) {
         return var4;
      } else {
         throw new ArithmeticException("long overflow");
      }
   }

   public static int toIntExact(long var0) {
      int var2 = (int)var0;
      if ((long)var2 == var0) {
         return var2;
      } else {
         throw new ArithmeticException("integer overflow");
      }
   }
}
