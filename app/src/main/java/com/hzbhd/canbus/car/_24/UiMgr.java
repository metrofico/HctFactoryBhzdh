package com.hzbhd.canbus.car._24;

import android.content.Context;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private AirActivity mActivity;
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
