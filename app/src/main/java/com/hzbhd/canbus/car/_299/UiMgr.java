package com.hzbhd.canbus.car._299;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private CusPanoramicView mMyPanoramicView;

   public UiMgr(Context var1) {
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mMyPanoramicView == null) {
         this.mMyPanoramicView = new CusPanoramicView(var1);
      }

      this.mMyPanoramicView.setVisible(var1);
      return this.mMyPanoramicView;
   }
}
