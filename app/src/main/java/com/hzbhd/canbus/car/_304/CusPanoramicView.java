package com.hzbhd.canbus.car._304;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car_cus._304.view.MyRadarView;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

public class CusPanoramicView extends RelativeLayout {
   private final String TAG = "_304_CusPanoramicView";
   private Button mBtnExit;
   private MyRadarView mRadarView;

   public CusPanoramicView(Context var1) {
      super(var1);
      this.findViews(var1);
   }

   private void findViews(Context var1) {
      View var2 = LayoutInflater.from(var1).inflate(2131558803, this);
      this.mRadarView = (MyRadarView)var2.findViewById(2131363022);
      this.mBtnExit = (Button)var2.findViewById(2131362023);
   }

   // $FF: synthetic method
   static void lambda$initView$0(ParkPageUiSet var0, View var1) {
      var0.getOnPanoramicItemClickListener().onClickItem(6);
   }

   public void initView(ParkPageUiSet var1) {
      this.mRadarView.initViews(var1.getOnPanoramicItemClickListener());
      this.mBtnExit.setOnClickListener(new CusPanoramicView$$ExternalSyntheticLambda0(var1));
   }

   public void refreshRadar() {
      this.mRadarView.refreshRadar();
   }
}
