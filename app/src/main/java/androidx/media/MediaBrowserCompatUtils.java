package androidx.media;

import android.os.Bundle;

public class MediaBrowserCompatUtils {
   private MediaBrowserCompatUtils() {
   }

   public static boolean areSameOptions(Bundle var0, Bundle var1) {
      boolean var4 = true;
      boolean var2 = true;
      boolean var3 = true;
      if (var0 == var1) {
         return true;
      } else if (var0 == null) {
         if (var1.getInt("android.media.browse.extra.PAGE", -1) == -1 && var1.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
            var2 = var3;
         } else {
            var2 = false;
         }

         return var2;
      } else if (var1 == null) {
         if (var0.getInt("android.media.browse.extra.PAGE", -1) == -1 && var0.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
            var2 = var4;
         } else {
            var2 = false;
         }

         return var2;
      } else {
         if (var0.getInt("android.media.browse.extra.PAGE", -1) != var1.getInt("android.media.browse.extra.PAGE", -1) || var0.getInt("android.media.browse.extra.PAGE_SIZE", -1) != var1.getInt("android.media.browse.extra.PAGE_SIZE", -1)) {
            var2 = false;
         }

         return var2;
      }
   }

   public static boolean hasDuplicatedItems(Bundle var0, Bundle var1) {
      int var4;
      if (var0 == null) {
         var4 = -1;
      } else {
         var4 = var0.getInt("android.media.browse.extra.PAGE", -1);
      }

      int var2;
      if (var1 == null) {
         var2 = -1;
      } else {
         var2 = var1.getInt("android.media.browse.extra.PAGE", -1);
      }

      int var5;
      if (var0 == null) {
         var5 = -1;
      } else {
         var5 = var0.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      }

      int var3;
      if (var1 == null) {
         var3 = -1;
      } else {
         var3 = var1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      }

      int var6 = Integer.MAX_VALUE;
      boolean var8 = true;
      if (var4 != -1 && var5 != -1) {
         int var7 = var4 * var5;
         var4 = var5 + var7 - 1;
         var5 = var7;
      } else {
         var4 = Integer.MAX_VALUE;
         var5 = 0;
      }

      if (var2 != -1 && var3 != -1) {
         var2 *= var3;
         var3 = var3 + var2 - 1;
      } else {
         var2 = 0;
         var3 = var6;
      }

      if (var4 < var2 || var3 < var5) {
         var8 = false;
      }

      return var8;
   }
}
