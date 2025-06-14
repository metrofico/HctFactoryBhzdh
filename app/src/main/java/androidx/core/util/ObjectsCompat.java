package androidx.core.util;

import android.os.Build.VERSION;
import java.util.Arrays;
import java.util.Objects;

public class ObjectsCompat {
   private ObjectsCompat() {
   }

   public static boolean equals(Object var0, Object var1) {
      if (VERSION.SDK_INT >= 19) {
         return Objects.equals(var0, var1);
      } else {
         boolean var2;
         if (var0 == var1 || var0 != null && var0.equals(var1)) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }
   }

   public static int hash(Object... var0) {
      return VERSION.SDK_INT >= 19 ? Objects.hash(var0) : Arrays.hashCode(var0);
   }

   public static int hashCode(Object var0) {
      int var1;
      if (var0 != null) {
         var1 = var0.hashCode();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public static Object requireNonNull(Object var0) {
      var0.getClass();
      return var0;
   }

   public static Object requireNonNull(Object var0, String var1) {
      if (var0 != null) {
         return var0;
      } else {
         throw new NullPointerException(var1);
      }
   }

   public static String toString(Object var0, String var1) {
      if (var0 != null) {
         var1 = var0.toString();
      }

      return var1;
   }
}
