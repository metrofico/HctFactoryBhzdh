package com.hzbhd.canbus.car._273;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.car._18.MyPanoramicView;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private View mMyPanoramiceView;
   private ParkPageUiSet parkPageUiSet;

   public UiMgr(Context var1) {
      if (this.mMyPanoramiceView == null) {
         this.mMyPanoramiceView = new MyPanoramicView(var1);
      }

      this.parkPageUiSet = this.getParkPageUiSet(var1);
   }
}
