package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car_cus._304.util.Util;

public class VehiclePanoramicView extends RelativeLayout {
   private ImageView mIvPanoramic;
   private View mView;

   public VehiclePanoramicView(Context var1) {
      super(var1);
      this.mView = LayoutInflater.from(var1).inflate(2131558510, this);
      this.findViews();
      this.initViews();
   }

   private void findViews() {
      this.mIvPanoramic = (ImageView)this.mView.findViewById(2131362605);
   }

   private void initViews() {
      this.mIvPanoramic.setOnClickListener(new VehiclePanoramicView$$ExternalSyntheticLambda0(this));
   }

   private void sendEnterPanoramic() {
      Util.sendAvmCommand(1);
      CanbusMsgSender.sendMsg(new byte[]{22, -80, 64, 0});
   }

   // $FF: synthetic method
   void lambda$initViews$0$com_hzbhd_canbus_car_cus__304_view_VehiclePanoramicView(View var1) {
      this.sendEnterPanoramic();
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      Log.i("ljqdebug", "onAttachedToWindow: ");
      this.sendEnterPanoramic();
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      Log.i("ljqdebug", "onDetachedFromWindow: ");
   }
}
