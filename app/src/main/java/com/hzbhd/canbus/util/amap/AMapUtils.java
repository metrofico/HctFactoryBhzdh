package com.hzbhd.canbus.util.amap;

import android.content.Context;
import android.content.IntentFilter;

public class AMapUtils {
   public static final String AUTONAVI_STANDARD_BROADCAST_SEND = "AUTONAVI_STANDARD_BROADCAST_SEND";
   private static AMapUtils mAMapUtils;
   private AMapBroadcast mAMapBroadcast;

   private AMapUtils() {
   }

   public static AMapUtils getInstance() {
      if (mAMapUtils == null) {
         mAMapUtils = new AMapUtils();
      }

      return mAMapUtils;
   }

   public void startAMap(Context var1) {
      if (this.mAMapBroadcast == null) {
         this.mAMapBroadcast = new AMapBroadcast();
         IntentFilter var2 = new IntentFilter();
         var2.addAction("AUTONAVI_STANDARD_BROADCAST_SEND");
         var1.registerReceiver(this.mAMapBroadcast, var2);
      }

   }
}
