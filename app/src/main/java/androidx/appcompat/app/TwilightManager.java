package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;

class TwilightManager {
   private static final int SUNRISE = 6;
   private static final int SUNSET = 22;
   private static final String TAG = "TwilightManager";
   private static TwilightManager sInstance;
   private final Context mContext;
   private final LocationManager mLocationManager;
   private final TwilightState mTwilightState = new TwilightState();

   TwilightManager(Context var1, LocationManager var2) {
      this.mContext = var1;
      this.mLocationManager = var2;
   }

   static TwilightManager getInstance(Context var0) {
      if (sInstance == null) {
         var0 = var0.getApplicationContext();
         sInstance = new TwilightManager(var0, (LocationManager)var0.getSystemService("location"));
      }

      return sInstance;
   }

   private Location getLastKnownLocation() {
      int var1 = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION");
      Location var3 = null;
      Location var2;
      if (var1 == 0) {
         var2 = this.getLastKnownLocationForProvider("network");
      } else {
         var2 = null;
      }

      if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
         var3 = this.getLastKnownLocationForProvider("gps");
      }

      if (var3 != null && var2 != null) {
         Location var4 = var2;
         if (var3.getTime() > var2.getTime()) {
            var4 = var3;
         }

         return var4;
      } else {
         if (var3 != null) {
            var2 = var3;
         }

         return var2;
      }
   }

   private Location getLastKnownLocationForProvider(String var1) {
      try {
         if (this.mLocationManager.isProviderEnabled(var1)) {
            Location var3 = this.mLocationManager.getLastKnownLocation(var1);
            return var3;
         }
      } catch (Exception var2) {
         Log.d("TwilightManager", "Failed to get last known location", var2);
      }

      return null;
   }

   private boolean isStateValid() {
      boolean var1;
      if (this.mTwilightState.nextUpdate > System.currentTimeMillis()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   static void setInstance(TwilightManager var0) {
      sInstance = var0;
   }

   private void updateState(Location var1) {
      TwilightState var15 = this.mTwilightState;
      long var4 = System.currentTimeMillis();
      TwilightCalculator var14 = TwilightCalculator.getInstance();
      var14.calculateTwilight(var4 - 86400000L, var1.getLatitude(), var1.getLongitude());
      long var10 = var14.sunset;
      var14.calculateTwilight(var4, var1.getLatitude(), var1.getLongitude());
      int var2 = var14.state;
      boolean var3 = true;
      if (var2 != 1) {
         var3 = false;
      }

      long var12 = var14.sunrise;
      long var6 = var14.sunset;
      var14.calculateTwilight(86400000L + var4, var1.getLatitude(), var1.getLongitude());
      long var8 = var14.sunrise;
      if (var12 != -1L && var6 != -1L) {
         if (var4 > var6) {
            var4 = 0L + var8;
         } else if (var4 > var12) {
            var4 = 0L + var6;
         } else {
            var4 = 0L + var12;
         }

         var4 += 60000L;
      } else {
         var4 += 43200000L;
      }

      var15.isNight = var3;
      var15.yesterdaySunset = var10;
      var15.todaySunrise = var12;
      var15.todaySunset = var6;
      var15.tomorrowSunrise = var8;
      var15.nextUpdate = var4;
   }

   boolean isNight() {
      TwilightState var4 = this.mTwilightState;
      if (this.isStateValid()) {
         return var4.isNight;
      } else {
         Location var3 = this.getLastKnownLocation();
         if (var3 != null) {
            this.updateState(var3);
            return var4.isNight;
         } else {
            Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
            int var1 = Calendar.getInstance().get(11);
            boolean var2;
            if (var1 >= 6 && var1 < 22) {
               var2 = false;
            } else {
               var2 = true;
            }

            return var2;
         }
      }
   }

   private static class TwilightState {
      boolean isNight;
      long nextUpdate;
      long todaySunrise;
      long todaySunset;
      long tomorrowSunrise;
      long yesterdaySunset;

      TwilightState() {
      }
   }
}
