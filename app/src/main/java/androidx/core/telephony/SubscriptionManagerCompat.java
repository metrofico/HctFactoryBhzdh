package androidx.core.telephony;

import android.os.Build.VERSION;
import android.telephony.SubscriptionManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SubscriptionManagerCompat {
   private static Method sGetSlotIndexMethod;

   private SubscriptionManagerCompat() {
   }

   public static int getSlotIndex(int var0) {
      if (var0 == -1) {
         return -1;
      } else if (VERSION.SDK_INT >= 29) {
         return SubscriptionManager.getSlotIndex(var0);
      } else {
         boolean var10001;
         label65: {
            label53: {
               try {
                  if (sGetSlotIndexMethod != null) {
                     break label65;
                  }

                  if (VERSION.SDK_INT >= 26) {
                     sGetSlotIndexMethod = SubscriptionManager.class.getDeclaredMethod("getSlotIndex", Integer.TYPE);
                     break label53;
                  }
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var6) {
                  var10001 = false;
                  return -1;
               }

               try {
                  sGetSlotIndexMethod = SubscriptionManager.class.getDeclaredMethod("getSlotId", Integer.TYPE);
               } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var5) {
                  var10001 = false;
                  return -1;
               }
            }

            try {
               sGetSlotIndexMethod.setAccessible(true);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var4) {
               var10001 = false;
               return -1;
            }
         }

         Integer var1;
         try {
            var1 = (Integer)sGetSlotIndexMethod.invoke((Object)null, var0);
         } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var3) {
            var10001 = false;
            return -1;
         }

         if (var1 != null) {
            try {
               var0 = var1;
               return var0;
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var2) {
               var10001 = false;
            }
         }

         return -1;
      }
   }

   private static class Api29Impl {
      static int getSlotIndex(int var0) {
         return SubscriptionManager.getSlotIndex(var0);
      }
   }
}
