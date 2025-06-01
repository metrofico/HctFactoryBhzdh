package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.car._283.MsgMgr;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingView;
import com.hzbhd.canbus.util.DataHandleUtils;

public class CarSettingRevertView extends RelativeLayout implements View.OnClickListener {
   private Context mContext;
   private View mView;
   private SettingView sv_revert_all;
   private SettingView sv_revert_assistance;
   private SettingView sv_revert_combination;
   private SettingView sv_revert_door;
   private SettingView sv_revert_light;
   private SettingView sv_revert_parking;
   private SettingView sv_revert_rear;
   private SettingView sv_revert_time;

   public CarSettingRevertView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public CarSettingRevertView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public CarSettingRevertView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558448, this, true);
      this.initView();
   }

   private String getContent(int var1) {
      return this.mContext.getString(2131760728) + this.mContext.getString(var1) + this.mContext.getString(2131760729);
   }

   private void initView() {
      this.sv_revert_light = (SettingView)this.mView.findViewById(2131363463);
      this.sv_revert_all = (SettingView)this.mView.findViewById(2131363459);
      this.sv_revert_assistance = (SettingView)this.mView.findViewById(2131363460);
      this.sv_revert_parking = (SettingView)this.mView.findViewById(2131363464);
      this.sv_revert_rear = (SettingView)this.mView.findViewById(2131363465);
      this.sv_revert_door = (SettingView)this.mView.findViewById(2131363462);
      this.sv_revert_combination = (SettingView)this.mView.findViewById(2131363461);
      this.sv_revert_time = (SettingView)this.mView.findViewById(2131363466);
      this.sv_revert_light.setOnClickListener(this);
      this.sv_revert_all.setOnClickListener(this);
      this.sv_revert_assistance.setOnClickListener(this);
      this.sv_revert_parking.setOnClickListener(this);
      this.sv_revert_rear.setOnClickListener(this);
      this.sv_revert_door.setOnClickListener(this);
      this.sv_revert_combination.setOnClickListener(this);
      this.sv_revert_time.setOnClickListener(this);
   }

   private void setTime() {
      MsgMgr.CHANG_TIME = true;
   }

   // $FF: synthetic method
   void lambda$onClick$0$com_hzbhd_canbus_car_cus__283_view_carsetting_CarSettingRevertView(int var1, View var2) {
      if (var1 == -1) {
         this.setTime();
      } else {
         MessageSender.sendMsg(new byte[]{22, 26, (byte)DataHandleUtils.setIntByteWithBit(0, var1, true)});
      }

   }

   public void onClick(View var1) {
      int var6 = var1.getId();
      byte var4 = 7;
      int var5 = 2131760730;
      byte var2 = var4;
      int var3 = var5;
      switch (var6) {
         case 2131363459:
            break;
         case 2131363460:
            var3 = 2131760746;
            var2 = 6;
            break;
         case 2131363461:
            var3 = 2131760750;
            var2 = 1;
            break;
         case 2131363462:
            var3 = 2131760749;
            var2 = 2;
            break;
         case 2131363463:
            var3 = 2131760745;
            var2 = 4;
            break;
         case 2131363464:
            var3 = 2131760747;
            var2 = 5;
            break;
         case 2131363465:
            var3 = 2131760748;
            var2 = 3;
            break;
         case 2131363466:
            var3 = 2131760743;
            var2 = -1;
            break;
         default:
            var2 = var4;
            var3 = var5;
      }

      DialogUtils.showTipsDialog(this.mContext, this.getContent(var3), new CarSettingRevertView$$ExternalSyntheticLambda0(this, var2));
   }

   public void refreshUi() {
   }
}
