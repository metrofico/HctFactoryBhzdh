package com.hzbhd.canbus.car._242;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.getCurrentCarId();
      if (var2 != 0) {
         if (var2 == 1) {
            this.getParkPageUiSet(var1).setShowRadar(true);
            this.removeFrontAirButton(var1, 3, 0, 2);
            this.removeFrontAirButton(var1, 3, 1, 1);
         }
      } else {
         this.getParkPageUiSet(var1).setShowRadar(false);
      }

   }
}
