package androidx.core.text;

import android.icu.util.ULocale;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class ICUCompat {
   private static final String TAG = "ICUCompat";
   private static Method sAddLikelySubtagsMethod;
   private static Method sGetScriptMethod;

   static {
      if (VERSION.SDK_INT < 21) {
         label31: {
            Exception var10000;
            label34: {
               Class var0;
               boolean var10001;
               try {
                  var0 = Class.forName("libcore.icu.ICU");
               } catch (Exception var3) {
                  var10000 = var3;
                  var10001 = false;
                  break label34;
               }

               if (var0 == null) {
                  break label31;
               }

               try {
                  sGetScriptMethod = var0.getMethod("getScript", String.class);
                  sAddLikelySubtagsMethod = var0.getMethod("addLikelySubtags", String.class);
                  break label31;
               } catch (Exception var2) {
                  var10000 = var2;
                  var10001 = false;
               }
            }

            Exception var4 = var10000;
            sGetScriptMethod = null;
            sAddLikelySubtagsMethod = null;
            Log.w("ICUCompat", var4);
         }
      } else if (VERSION.SDK_INT < 24) {
         try {
            sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", Locale.class);
         } catch (Exception var1) {
            throw new IllegalStateException(var1);
         }
      }

   }

   private ICUCompat() {
   }

   private static String addLikelySubtags(Locale var0) {
      String var6 = var0.toString();

      IllegalAccessException var10;
      label43: {
         InvocationTargetException var10000;
         label36: {
            Method var1;
            boolean var10001;
            try {
               var1 = sAddLikelySubtagsMethod;
            } catch (IllegalAccessException var4) {
               var10 = var4;
               var10001 = false;
               break label43;
            } catch (InvocationTargetException var5) {
               var10000 = var5;
               var10001 = false;
               break label36;
            }

            if (var1 == null) {
               return var6;
            }

            try {
               String var9 = (String)var1.invoke((Object)null, var6);
               return var9;
            } catch (IllegalAccessException var2) {
               var10 = var2;
               var10001 = false;
               break label43;
            } catch (InvocationTargetException var3) {
               var10000 = var3;
               var10001 = false;
            }
         }

         InvocationTargetException var7 = var10000;
         Log.w("ICUCompat", var7);
         return var6;
      }

      IllegalAccessException var8 = var10;
      Log.w("ICUCompat", var8);
      return var6;
   }

   private static String getScript(String param0) {
      // $FF: Couldn't be decompiled
   }

   public static String maximizeAndGetScript(Locale var0) {
      if (VERSION.SDK_INT >= 24) {
         return ULocale.addLikelySubtags(ULocale.forLocale(var0)).getScript();
      } else if (VERSION.SDK_INT >= 21) {
         try {
            String var1 = ((Locale)sAddLikelySubtagsMethod.invoke((Object)null, var0)).getScript();
            return var1;
         } catch (InvocationTargetException var2) {
            Log.w("ICUCompat", var2);
         } catch (IllegalAccessException var3) {
            Log.w("ICUCompat", var3);
         }

         return var0.getScript();
      } else {
         String var4 = addLikelySubtags(var0);
         return var4 != null ? getScript(var4) : null;
      }
   }
}
