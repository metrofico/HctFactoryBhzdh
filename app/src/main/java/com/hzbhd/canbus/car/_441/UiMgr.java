package com.hzbhd.canbus.car._441;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   RadarView radarView;

   public UiMgr(Context var1) {
   }

   public View getCusPanoramicView(Context var1) {
      if (this.radarView == null) {
         this.radarView = new RadarView(var1);
      }

      this.radarView.refreshRadar();
      return this.radarView;
   }

   public void refreshRadar() {
      RadarView var1 = this.radarView;
      if (var1 != null) {
         var1.refreshRadar();
      }

   }
}
