package androidx.core.location;

import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Build.VERSION;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public final class LocationCompat {
   public static final String EXTRA_BEARING_ACCURACY = "bearingAccuracy";
   public static final String EXTRA_IS_MOCK = "mockLocation";
   public static final String EXTRA_SPEED_ACCURACY = "speedAccuracy";
   public static final String EXTRA_VERTICAL_ACCURACY = "verticalAccuracy";
   private static Method sSetIsFromMockProviderMethod;

   private LocationCompat() {
   }

   public static float getBearingAccuracyDegrees(Location var0) {
      if (VERSION.SDK_INT >= 26) {
         return var0.getBearingAccuracyDegrees();
      } else {
         Bundle var1 = var0.getExtras();
         return var1 == null ? 0.0F : var1.getFloat("bearingAccuracy", 0.0F);
      }
   }

   public static long getElapsedRealtimeMillis(Location var0) {
      if (VERSION.SDK_INT >= 17) {
         return TimeUnit.NANOSECONDS.toMillis(var0.getElapsedRealtimeNanos());
      } else {
         long var1 = System.currentTimeMillis() - var0.getTime();
         long var3 = SystemClock.elapsedRealtime();
         if (var1 < 0L) {
            return var3;
         } else {
            return var1 > var3 ? 0L : var3 - var1;
         }
      }
   }

   public static long getElapsedRealtimeNanos(Location var0) {
      return VERSION.SDK_INT >= 17 ? var0.getElapsedRealtimeNanos() : TimeUnit.MILLISECONDS.toNanos(getElapsedRealtimeMillis(var0));
   }

   private static Method getSetIsFromMockProviderMethod() throws NoSuchMethodException {
      if (sSetIsFromMockProviderMethod == null) {
         Method var0 = Location.class.getDeclaredMethod("setIsFromMockProvider", Boolean.TYPE);
         sSetIsFromMockProviderMethod = var0;
         var0.setAccessible(true);
      }

      return sSetIsFromMockProviderMethod;
   }

   public static float getSpeedAccuracyMetersPerSecond(Location var0) {
      if (VERSION.SDK_INT >= 26) {
         return var0.getSpeedAccuracyMetersPerSecond();
      } else {
         Bundle var1 = var0.getExtras();
         return var1 == null ? 0.0F : var1.getFloat("speedAccuracy", 0.0F);
      }
   }

   public static float getVerticalAccuracyMeters(Location var0) {
      if (VERSION.SDK_INT >= 26) {
         return var0.getVerticalAccuracyMeters();
      } else {
         Bundle var1 = var0.getExtras();
         return var1 == null ? 0.0F : var1.getFloat("verticalAccuracy", 0.0F);
      }
   }

   public static boolean hasBearingAccuracy(Location var0) {
      if (VERSION.SDK_INT >= 26) {
         return var0.hasBearingAccuracy();
      } else {
         Bundle var1 = var0.getExtras();
         return var1 == null ? false : var1.containsKey("bearingAccuracy");
      }
   }

   public static boolean hasSpeedAccuracy(Location var0) {
      if (VERSION.SDK_INT >= 26) {
         return var0.hasSpeedAccuracy();
      } else {
         Bundle var1 = var0.getExtras();
         return var1 == null ? false : var1.containsKey("speedAccuracy");
      }
   }

   public static boolean hasVerticalAccuracy(Location var0) {
      if (VERSION.SDK_INT >= 26) {
         return var0.hasVerticalAccuracy();
      } else {
         Bundle var1 = var0.getExtras();
         return var1 == null ? false : var1.containsKey("verticalAccuracy");
      }
   }

   public static boolean isMock(Location var0) {
      if (VERSION.SDK_INT >= 18) {
         return var0.isFromMockProvider();
      } else {
         Bundle var1 = var0.getExtras();
         return var1 == null ? false : var1.getBoolean("mockLocation", false);
      }
   }

   public static void setBearingAccuracyDegrees(Location var0, float var1) {
      if (VERSION.SDK_INT >= 26) {
         var0.setBearingAccuracyDegrees(var1);
      } else {
         Bundle var3 = var0.getExtras();
         Bundle var2 = var3;
         if (var3 == null) {
            var0.setExtras(new Bundle());
            var2 = var0.getExtras();
         }

         var2.putFloat("bearingAccuracy", var1);
      }

   }

   public static void setMock(Location var0, boolean var1) {
      if (VERSION.SDK_INT >= 18) {
         try {
            getSetIsFromMockProviderMethod().invoke(var0, var1);
         } catch (NoSuchMethodException var3) {
            NoSuchMethodError var7 = new NoSuchMethodError();
            var7.initCause(var3);
            throw var7;
         } catch (IllegalAccessException var4) {
            IllegalAccessError var6 = new IllegalAccessError();
            var6.initCause(var4);
            throw var6;
         } catch (InvocationTargetException var5) {
            throw new RuntimeException(var5);
         }
      } else {
         Bundle var2 = var0.getExtras();
         if (var2 == null) {
            if (var1) {
               var2 = new Bundle();
               var2.putBoolean("mockLocation", true);
               var0.setExtras(var2);
            }
         } else if (var1) {
            var2.putBoolean("mockLocation", true);
         } else {
            var2.remove("mockLocation");
            if (var2.isEmpty()) {
               var0.setExtras((Bundle)null);
            }
         }
      }

   }

   public static void setSpeedAccuracyMetersPerSecond(Location var0, float var1) {
      if (VERSION.SDK_INT >= 26) {
         var0.setSpeedAccuracyMetersPerSecond(var1);
      } else {
         Bundle var3 = var0.getExtras();
         Bundle var2 = var3;
         if (var3 == null) {
            var0.setExtras(new Bundle());
            var2 = var0.getExtras();
         }

         var2.putFloat("speedAccuracy", var1);
      }

   }

   public static void setVerticalAccuracyMeters(Location var0, float var1) {
      if (VERSION.SDK_INT >= 26) {
         var0.setVerticalAccuracyMeters(var1);
      } else {
         Bundle var3 = var0.getExtras();
         Bundle var2 = var3;
         if (var3 == null) {
            var0.setExtras(new Bundle());
            var2 = var0.getExtras();
         }

         var2.putFloat("verticalAccuracy", var1);
      }

   }

   private static class Api17Impl {
      static long getElapsedRealtimeNanos(Location var0) {
         return var0.getElapsedRealtimeNanos();
      }
   }

   private static class Api18Impl {
      static boolean isMock(Location var0) {
         return var0.isFromMockProvider();
      }
   }

   private static class Api26Impl {
      static float getBearingAccuracyDegrees(Location var0) {
         return var0.getBearingAccuracyDegrees();
      }

      static float getSpeedAccuracyMetersPerSecond(Location var0) {
         return var0.getSpeedAccuracyMetersPerSecond();
      }

      static float getVerticalAccuracyMeters(Location var0) {
         return var0.getVerticalAccuracyMeters();
      }

      static boolean hasBearingAccuracy(Location var0) {
         return var0.hasBearingAccuracy();
      }

      static boolean hasSpeedAccuracy(Location var0) {
         return var0.hasSpeedAccuracy();
      }

      static boolean hasVerticalAccuracy(Location var0) {
         return var0.hasVerticalAccuracy();
      }

      static void setBearingAccuracyDegrees(Location var0, float var1) {
         var0.setBearingAccuracyDegrees(var1);
      }

      static void setSpeedAccuracyMetersPerSecond(Location var0, float var1) {
         var0.setSpeedAccuracyMetersPerSecond(var1);
      }

      static void setVerticalAccuracyMeters(Location var0, float var1) {
         var0.setVerticalAccuracyMeters(var1);
      }
   }
}
