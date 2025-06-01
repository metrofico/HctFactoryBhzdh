package androidx.core.util;

import android.text.TextUtils;
import java.util.Locale;

public final class Preconditions {
   private Preconditions() {
   }

   public static void checkArgument(boolean var0) {
      if (!var0) {
         throw new IllegalArgumentException();
      }
   }

   public static void checkArgument(boolean var0, Object var1) {
      if (!var0) {
         throw new IllegalArgumentException(String.valueOf(var1));
      }
   }

   public static void checkArgument(boolean var0, String var1, Object... var2) {
      if (!var0) {
         throw new IllegalArgumentException(String.format(var1, var2));
      }
   }

   public static double checkArgumentInRange(double var0, double var2, double var4, String var6) {
      if (!(var0 < var2)) {
         if (!(var0 > var4)) {
            return var0;
         } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", var6, var2, var4));
         }
      } else {
         throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", var6, var2, var4));
      }
   }

   public static float checkArgumentInRange(float var0, float var1, float var2, String var3) {
      if (!(var0 < var1)) {
         if (!(var0 > var2)) {
            return var0;
         } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", var3, var1, var2));
         }
      } else {
         throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", var3, var1, var2));
      }
   }

   public static int checkArgumentInRange(int var0, int var1, int var2, String var3) {
      if (var0 >= var1) {
         if (var0 <= var2) {
            return var0;
         } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", var3, var1, var2));
         }
      } else {
         throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", var3, var1, var2));
      }
   }

   public static long checkArgumentInRange(long var0, long var2, long var4, String var6) {
      if (var0 >= var2) {
         if (var0 <= var4) {
            return var0;
         } else {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", var6, var2, var4));
         }
      } else {
         throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", var6, var2, var4));
      }
   }

   public static int checkArgumentNonnegative(int var0) {
      if (var0 >= 0) {
         return var0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static int checkArgumentNonnegative(int var0, String var1) {
      if (var0 >= 0) {
         return var0;
      } else {
         throw new IllegalArgumentException(var1);
      }
   }

   public static int checkFlagsArgument(int var0, int var1) {
      if ((var0 & var1) == var0) {
         return var0;
      } else {
         throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(var0) + ", but only 0x" + Integer.toHexString(var1) + " are allowed");
      }
   }

   public static Object checkNotNull(Object var0) {
      var0.getClass();
      return var0;
   }

   public static Object checkNotNull(Object var0, Object var1) {
      if (var0 != null) {
         return var0;
      } else {
         throw new NullPointerException(String.valueOf(var1));
      }
   }

   public static void checkState(boolean var0) {
      checkState(var0, (String)null);
   }

   public static void checkState(boolean var0, String var1) {
      if (!var0) {
         throw new IllegalStateException(var1);
      }
   }

   public static CharSequence checkStringNotEmpty(CharSequence var0) {
      if (!TextUtils.isEmpty(var0)) {
         return var0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public static CharSequence checkStringNotEmpty(CharSequence var0, Object var1) {
      if (!TextUtils.isEmpty(var0)) {
         return var0;
      } else {
         throw new IllegalArgumentException(String.valueOf(var1));
      }
   }

   public static CharSequence checkStringNotEmpty(CharSequence var0, String var1, Object... var2) {
      if (!TextUtils.isEmpty(var0)) {
         return var0;
      } else {
         throw new IllegalArgumentException(String.format(var1, var2));
      }
   }
}
