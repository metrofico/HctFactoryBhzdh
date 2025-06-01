package com.hzbhd.canbus.car._283;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import java.util.Calendar;
import java.util.Iterator;

public class GPSTimeManager {
   private static GPSTimeManager mGPSTimeManager;
   private LocationManager locationManager;
   private Context mContext;
   GpsStatus.Listener mGpsStatus = new GpsStatus.Listener(this) {
      final GPSTimeManager this$0;

      {
         this.this$0 = var1;
      }

      public void onGpsStatusChanged(int var1) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     GpsStatus var3 = this.this$0.locationManager.getGpsStatus((GpsStatus)null);
                     int var2 = var3.getMaxSatellites();
                     Iterator var4 = var3.getSatellites().iterator();
                     var1 = 0;

                     while(var4.hasNext() && var1 <= var2) {
                        if (((GpsSatellite)var4.next()).getSnr() != 0.0F) {
                           ++var1;
                        }
                     }

                     if (ActivityCompat.checkSelfPermission(this.this$0.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this.this$0.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                        return;
                     }

                     Log.d("scy", "卫星状态改变------->" + var1);
                  }
               } else {
                  Log.d("scy", "------->第一次定位");
               }
            } else {
               Log.d("scy", "------->定位结束");
            }
         } else {
            Log.d("scy", "------->定位启动");
         }

      }
   };
   LocationListener mLocationListener = new LocationListener(this) {
      final GPSTimeManager this$0;

      {
         this.this$0 = var1;
      }

      public void onLocationChanged(Location var1) {
         Log.d("scy", "onLocationChanged--GPS获取定位第一次获取到时间----->" + var1.getTime());
         long var8 = var1.getTime();
         Calendar var10 = Calendar.getInstance();
         var10.setTimeInMillis(var8);
         int var6 = var10.get(1);
         int var4 = var10.get(2);
         int var7 = var10.get(5);
         int var5 = var10.get(11);
         int var3 = var10.get(12);
         int var2 = var10.get(13);
         if (this.this$0.mOnGPSTimeCallBack != null) {
            this.this$0.mOnGPSTimeCallBack.onTimeCallBack(var6, var4 + 1, var7, var5, var3, var2);
         }

         this.this$0.unregister();
      }

      public void onProviderDisabled(String var1) {
         Log.d("scy", "onProviderDisabled------->" + var1);
      }

      public void onProviderEnabled(String var1) {
         Log.d("scy", "onProviderDisabled------->" + var1);
      }

      public void onStatusChanged(String var1, int var2, Bundle var3) {
         Log.d("scy", "onStatusChanged------->" + var1 + "<----->" + var2);
      }
   };
   private OnGPSTimeCallBack mOnGPSTimeCallBack;

   public static GPSTimeManager getInstance() {
      if (mGPSTimeManager == null) {
         mGPSTimeManager = new GPSTimeManager();
      }

      return mGPSTimeManager;
   }

   public void setOnGPSTimeCallBack(OnGPSTimeCallBack var1) {
      this.mOnGPSTimeCallBack = var1;
   }

   public void start(Context var1) {
      this.mContext = var1;
      this.locationManager = (LocationManager)var1.getSystemService("location");
      if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
         this.locationManager.addGpsStatusListener(this.mGpsStatus);
         this.locationManager.requestLocationUpdates("gps", 1000L, 1.0F, this.mLocationListener);
      }
   }

   public void unregister() {
      try {
         this.locationManager.removeGpsStatusListener(this.mGpsStatus);
         this.locationManager.removeUpdates(this.mLocationListener);
      } catch (Exception var2) {
         var2.printStackTrace();
      }

   }

   public interface OnGPSTimeCallBack {
      void onTimeCallBack(int var1, int var2, int var3, int var4, int var5, int var6);
   }
}
