package com.hzbhd.canbus.car._291;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private CusPanoramicView mMyPanoramicView;

   public UiMgr(Context var1) {
      this.mContext = var1;
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new CusPanoramicView(var1);
      }

      this.mMyPanoramicView.refreshUiLine();
      return this.mMyPanoramicView;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
