package com.hzbhd.canbus.park.panoramic;

import android.view.MotionEvent;
import com.hzbhd.canbus.adapter.park.ParkManager;

public class ParkPanoramic {
   private static final boolean ENABLE_PARK_PANORAMIC = true;
   private ParkManager mParkManager;

   public static boolean isEnableParkPanoramic() {
      return true;
   }

   public void constructParkPanoramic() {
      ParkManager var1 = ParkManager.getAtvManager();
      this.mParkManager = var1;
      var1.sendPanoramicParkOn(true);
      this.mParkManager.sendPanoramicParkWH(800, 480);
   }

   public void destroyParkPanoramic() {
      this.mParkManager.sendPanoramicParkOn(false);
   }

   public void sendTouch(MotionEvent var1) {
      int var4 = (int)var1.getX();
      int var3 = (int)var1.getY();
      int var5 = var1.getAction();
      byte var2 = 2;
      if (var5 != 0) {
         if (var5 != 1) {
            if (var5 != 2) {
               var2 = 0;
            }
         } else {
            var2 = 3;
         }
      } else {
         var2 = 1;
      }

      this.mParkManager.sendPanoramicParkPos(var2, var4, var3);
   }
}
