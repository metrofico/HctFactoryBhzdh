package com.hzbhd.canbus.car._289;

import android.content.Context;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getParkPageUiSet(var1);
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.mContext = var1;
   }
}
