package com.hzbhd.canbus.car_cus._304.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.car._304.MsgMgr;
import com.hzbhd.canbus.car_cus._304.util.Util;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.SystemUtil;

public class AirBottomView extends RelativeLayout implements View.OnClickListener {
   private final String TAG = "_304_MeidaBottomViewljqdebug";
   private ImageButton mIbHome;
   private ImageButton mIbMediaDown;
   private ImageButton mIbMediaUp;
   private ImageButton mIbMeida;
   private ImageButton mIbPhone;
   private ImageButton mIbWindDecrease;
   private ImageButton mIbWindIncrease;
   private ImageView mIvWindSpeed;
   private OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListener;
   private View mView;

   public AirBottomView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mView = LayoutInflater.from(var1).inflate(2131558505, this);
      this.findViews();
      this.initViews(var1);
      this.refreshWindSpeed();
   }

   private void findViews() {
      this.mIvWindSpeed = (ImageView)this.mView.findViewById(2131362680);
      this.mIbWindIncrease = (ImageButton)this.mView.findViewById(2131362428);
      this.mIbWindDecrease = (ImageButton)this.mView.findViewById(2131362427);
      this.mIbPhone = (ImageButton)this.mView.findViewById(2131362411);
      this.mIbHome = (ImageButton)this.mView.findViewById(2131362399);
      this.mIbMediaUp = (ImageButton)this.mView.findViewById(2131362406);
      this.mIbMediaDown = (ImageButton)this.mView.findViewById(2131362405);
   }

   private void sendHome() {
      (new Thread(this) {
         final AirBottomView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            super.run();

            while(!CommUtil.isMiscReverse()) {
            }

            RealKeyUtil.realKeyClick(this.this$0.getContext(), 52);
         }
      }).start();
   }

   public void initViews(Context var1) {
      this.mOnAirWindSpeedUpDownClickListener = UiMgrFactory.getCanUiMgr(var1).getAirUiSet(var1).getFrontArea().getSetWindSpeedViewOnClickListener();
      this.mIbWindIncrease.setOnClickListener(this);
      this.mIbWindDecrease.setOnClickListener(this);
      this.mIbPhone.setOnClickListener(this);
      this.mIbHome.setOnClickListener(this);
      this.mIbMediaUp.setOnClickListener(this);
      this.mIbMediaDown.setOnClickListener(this);
      ((MsgMgr)MsgMgrFactory.getCanMsgMgr(var1)).registOnWindLevelChangeListener(new AirBottomView$$ExternalSyntheticLambda0(this));
   }

   // $FF: synthetic method
   void lambda$initViews$0$com_hzbhd_canbus_car_cus__304_view_AirBottomView() {
      int var1 = DataHandleUtils.rangeNumber(GeneralAirData.front_wind_level, 0, 7);
      this.mIvWindSpeed.setImageLevel(var1);
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      boolean var1 = SystemUtil.isForeground(this.getContext(), "com.hzbhd.canbus.car_cus._304.activity.AirActivity");
      Log.i("_304_MeidaBottomViewljqdebug", "onAttachedToWindow: isAir <--> " + var1);
      if (var1) {
         this.mView.findViewById(2131363196).setVisibility(0);
      }

   }

   public void onClick(View var1) {
      OnAirWindSpeedUpDownClickListener var2;
      switch (var1.getId()) {
         case 2131362399:
            Util.sendAvmCommand(0);
            this.sendHome();
            break;
         case 2131362404:
            RealKeyUtil.realKeyClick(this.getContext(), 59);
            break;
         case 2131362405:
            RealKeyUtil.realKeyClick(this.getContext(), 46);
            break;
         case 2131362406:
            RealKeyUtil.realKeyClick(this.getContext(), 45);
            break;
         case 2131362411:
            Util.sendAvmCommand(0);
            RealKeyUtil.realKeyClick(this.getContext(), 14);
            break;
         case 2131362427:
            var2 = this.mOnAirWindSpeedUpDownClickListener;
            if (var2 != null) {
               var2.onClickLeft();
            }
            break;
         case 2131362428:
            var2 = this.mOnAirWindSpeedUpDownClickListener;
            if (var2 != null) {
               var2.onClickRight();
            }
      }

   }

   public void refreshWindSpeed() {
      this.mIvWindSpeed.setImageLevel(GeneralAirData.front_wind_level);
   }
}
