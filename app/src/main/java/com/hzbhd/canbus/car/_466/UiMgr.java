package com.hzbhd.canbus.car._466;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private View mCusPanoramicView;

   public UiMgr(Context var1) {
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mCusPanoramicView == null) {
         this.mCusPanoramicView = new MyCameraBackView(var1);
      }

      return this.mCusPanoramicView;
   }
}
