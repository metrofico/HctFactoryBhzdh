package androidx.core.telephony;

import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TelephonyManagerCompat {
   private static Method sGetDeviceIdMethod;
   private static Method sGetSubIdMethod;

   private TelephonyManagerCompat() {
   }

   public static String getImei(TelephonyManager var0) {
      if (VERSION.SDK_INT >= 26) {
         return var0.getImei();
      } else {
         if (VERSION.SDK_INT >= 22) {
            int var1 = getSubscriptionId(var0);
            if (var1 != Integer.MAX_VALUE && var1 != -1) {
               var1 = SubscriptionManagerCompat.getSlotIndex(var1);
               if (VERSION.SDK_INT >= 23) {
                  return var0.getDeviceId(var1);
               }

               try {
                  if (sGetDeviceIdMethod == null) {
                     Method var2 = TelephonyManager.class.getDeclaredMethod("getDeviceId", Integer.TYPE);
                     sGetDeviceIdMethod = var2;
                     var2.setAccessible(true);
                  }

                  String var4 = (String)sGetDeviceIdMethod.invoke(var0, var1);
                  return var4;
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var3) {
                  return null;
               }
            }
         }

         return var0.getDeviceId();
      }
   }

   public static int getSubscriptionId(TelephonyManager var0) {
      if (VERSION.SDK_INT >= 30) {
         return var0.getSubscriptionId();
      } else {
         if (VERSION.SDK_INT >= 22) {
            boolean var10001;
            try {
               if (sGetSubIdMethod == null) {
                  Method var2 = TelephonyManager.class.getDeclaredMethod("getSubId");
                  sGetSubIdMethod = var2;
                  var2.setAccessible(true);
               }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException var5) {
               var10001 = false;
               return Integer.MAX_VALUE;
            }

            Integer var6;
            try {
               var6 = (Integer)sGetSubIdMethod.invoke(var0);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException var4) {
               var10001 = false;
               return Integer.MAX_VALUE;
            }

            if (var6 != null) {
               try {
                  if (var6 != -1) {
                     int var1 = var6;
                     return var1;
                  }
               } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException var3) {
                  var10001 = false;
               }
            }
         }

         return Integer.MAX_VALUE;
      }
   }

   private static class Api23Impl {
      static String getDeviceId(TelephonyManager var0, int var1) {
         return var0.getDeviceId(var1);
      }
   }

   private static class Api26Impl {
      static String getImei(TelephonyManager var0) {
         return var0.getImei();
      }
   }

   private static class Api30Impl {
      static int getSubscriptionId(TelephonyManager var0) {
         return var0.getSubscriptionId();
      }
   }
}
