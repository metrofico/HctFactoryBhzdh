package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.os.Build.VERSION;
import androidx.core.util.Pair;

public final class PaintCompat {
   private static final String EM_STRING = "m";
   private static final String TOFU_STRING = "\udb3f\udffd";
   private static final ThreadLocal sRectThreadLocal = new ThreadLocal();

   private PaintCompat() {
   }

   public static boolean hasGlyph(Paint var0, String var1) {
      if (VERSION.SDK_INT >= 23) {
         return var0.hasGlyph(var1);
      } else {
         int var8 = var1.length();
         if (var8 == 1 && Character.isWhitespace(var1.charAt(0))) {
            return true;
         } else {
            float var4 = var0.measureText("\udb3f\udffd");
            float var5 = var0.measureText("m");
            float var3 = var0.measureText(var1);
            float var2 = 0.0F;
            if (var3 == 0.0F) {
               return false;
            } else {
               if (var1.codePointCount(0, var1.length()) > 1) {
                  if (var3 > var5 * 2.0F) {
                     return false;
                  }

                  int var7;
                  for(int var6 = 0; var6 < var8; var6 = var7) {
                     var7 = Character.charCount(var1.codePointAt(var6)) + var6;
                     var2 += var0.measureText(var1, var6, var7);
                  }

                  if (var3 >= var2) {
                     return false;
                  }
               }

               if (var3 != var4) {
                  return true;
               } else {
                  Pair var9 = obtainEmptyRects();
                  var0.getTextBounds("\udb3f\udffd", 0, 2, (Rect)var9.first);
                  var0.getTextBounds(var1, 0, var8, (Rect)var9.second);
                  return ((Rect)var9.first).equals(var9.second) ^ true;
               }
            }
         }
      }
   }

   private static Pair obtainEmptyRects() {
      ThreadLocal var1 = sRectThreadLocal;
      Pair var0 = (Pair)var1.get();
      if (var0 == null) {
         var0 = new Pair(new Rect(), new Rect());
         var1.set(var0);
      } else {
         ((Rect)var0.first).setEmpty();
         ((Rect)var0.second).setEmpty();
      }

      return var0;
   }

   public static boolean setBlendMode(Paint var0, BlendModeCompat var1) {
      int var2 = VERSION.SDK_INT;
      boolean var3 = true;
      BlendMode var4 = null;
      PorterDuff.Mode var5 = null;
      if (var2 >= 29) {
         var4 = var5;
         if (var1 != null) {
            var4 = BlendModeUtils.obtainBlendModeFromCompat(var1);
         }

         var0.setBlendMode(var4);
         return true;
      } else if (var1 != null) {
         var5 = BlendModeUtils.obtainPorterDuffFromCompat(var1);
         PorterDuffXfermode var6 = var4;
         if (var5 != null) {
            var6 = new PorterDuffXfermode(var5);
         }

         var0.setXfermode(var6);
         if (var5 == null) {
            var3 = false;
         }

         return var3;
      } else {
         var0.setXfermode((Xfermode)null);
         return true;
      }
   }
}
