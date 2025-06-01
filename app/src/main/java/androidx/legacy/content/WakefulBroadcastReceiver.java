package androidx.legacy.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
   private static final String EXTRA_WAKE_LOCK_ID = "androidx.contentpager.content.wakelockid";
   private static int mNextId = 1;
   private static final SparseArray sActiveWakeLocks = new SparseArray();

   public static boolean completeWakefulIntent(Intent var0) {
      int var1 = var0.getIntExtra("androidx.contentpager.content.wakelockid", 0);
      if (var1 == 0) {
         return false;
      } else {
         SparseArray var23 = sActiveWakeLocks;
         synchronized(var23){}

         Throwable var10000;
         boolean var10001;
         label182: {
            PowerManager.WakeLock var2;
            try {
               var2 = (PowerManager.WakeLock)var23.get(var1);
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label182;
            }

            if (var2 != null) {
               label176:
               try {
                  var2.release();
                  var23.remove(var1);
                  return true;
               } catch (Throwable var20) {
                  var10000 = var20;
                  var10001 = false;
                  break label176;
               }
            } else {
               label178:
               try {
                  StringBuilder var25 = new StringBuilder();
                  Log.w("WakefulBroadcastReceiv.", var25.append("No active wake lock id #").append(var1).toString());
                  return true;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label178;
               }
            }
         }

         while(true) {
            Throwable var24 = var10000;

            try {
               throw var24;
            } catch (Throwable var19) {
               var10000 = var19;
               var10001 = false;
               continue;
            }
         }
      }
   }

   public static ComponentName startWakefulService(Context var0, Intent var1) {
      SparseArray var4 = sActiveWakeLocks;
      synchronized(var4){}

      Throwable var10000;
      boolean var10001;
      label426: {
         int var2;
         try {
            var2 = mNextId;
         } catch (Throwable var61) {
            var10000 = var61;
            var10001 = false;
            break label426;
         }

         int var3 = var2 + 1;

         try {
            mNextId = var3;
         } catch (Throwable var60) {
            var10000 = var60;
            var10001 = false;
            break label426;
         }

         if (var3 <= 0) {
            try {
               mNextId = 1;
            } catch (Throwable var59) {
               var10000 = var59;
               var10001 = false;
               break label426;
            }
         }

         ComponentName var65;
         try {
            var1.putExtra("androidx.contentpager.content.wakelockid", var2);
            var65 = var0.startService(var1);
         } catch (Throwable var58) {
            var10000 = var58;
            var10001 = false;
            break label426;
         }

         if (var65 == null) {
            label405:
            try {
               return null;
            } catch (Throwable var56) {
               var10000 = var56;
               var10001 = false;
               break label405;
            }
         } else {
            label407:
            try {
               PowerManager var63 = (PowerManager)var0.getSystemService("power");
               StringBuilder var5 = new StringBuilder();
               PowerManager.WakeLock var64 = var63.newWakeLock(1, var5.append("androidx.core:wake:").append(var65.flattenToShortString()).toString());
               var64.setReferenceCounted(false);
               var64.acquire(60000L);
               var4.put(var2, var64);
               return var65;
            } catch (Throwable var57) {
               var10000 = var57;
               var10001 = false;
               break label407;
            }
         }
      }

      while(true) {
         Throwable var62 = var10000;

         try {
            throw var62;
         } catch (Throwable var55) {
            var10000 = var55;
            var10001 = false;
            continue;
         }
      }
   }
}
