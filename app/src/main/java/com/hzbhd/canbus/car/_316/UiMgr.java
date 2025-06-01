package com.hzbhd.canbus.car._316;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private int mDifferent;

   public UiMgr(Context var1) {
      if (this.getCurrentCarId() == 0) {
         this.removeFrontAirButtonByName(var1, "eco");
         this.removeFrontAirButtonByName(var1, "auto");
         this.removeFrontAirButtonByName(var1, "dual");
      }

   }
}
