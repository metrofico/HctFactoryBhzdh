package com.hzbhd.canbus.car._447;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   public ID447RadarView id447RadarView;
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getParkPageUiSet(var1).setShowCusPanoramicView(true);
   }

   public View getCusPanoramicView(Context var1) {
      if (this.id447RadarView == null) {
         this.id447RadarView = new ID447RadarView(var1);
      }

      return this.id447RadarView;
   }
}
