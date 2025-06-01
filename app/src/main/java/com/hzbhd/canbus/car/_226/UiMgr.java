package com.hzbhd.canbus.car._226;

import android.content.Context;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      AirPageUiSet var2 = this.getAirUiSet(var1);
      if (this.getCurrentCarId() == 1) {
         var2.getFrontArea().setShowLeftWheel(false);
         var2.getFrontArea().setShowRightWheel(false);
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.getCurrentCarId() == 1) {
         this.removeFrontAirButtonByName(var1, "power");
         this.removeFrontAirButtonByName(var1, "front_defog");
         this.removeFrontAirButtonByName(var1, "dual");
         this.removeFrontAirButtonByName(var1, "auto");
         this.removeFrontAirButtonByName(var1, "eco");
      }

   }
}
