package com.hzbhd.canbus.car_cus._290.view;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.canbus.car_cus._290.GeneralCwData;
import com.hzbhd.canbus.car_cus._290.MessageSender;
import com.hzbhd.canbus.car_cus._290.adapter.TemLvAdapter;

public class AirView extends RelativeLayout implements View.OnClickListener, ViewInterface {
   private static final String TAG = "AirView";
   private BtnView mBtnBottom0;
   private BtnView mBtnBottom1;
   private BtnView mBtnBottom2;
   private BtnView mBtnBottom3;
   private BtnView mBtnBottom4;
   private BtnView mBtnBottom5;
   private BtnView mBtnCenter;
   private BtnView mBtnLeft0;
   private BtnView mBtnLeft1;
   private BtnView mBtnLeft2;
   private BtnView mBtnLeft3;
   private BtnView mBtnRight0;
   private BtnView mBtnRight1;
   private BtnView mBtnRight2;
   private BtnView mBtnRight3;
   private BtnView mBtnViewTemperatureAdd;
   private BtnView mBtnViewTemperatureSub;
   private BtnView mBtnWindPowerAdd;
   private BtnView mBtnWindPowerSub;
   private Context mContext;
   private ImageView mIvWindPower;
   private RecyclerView mRv;
   private int[] mTemArray;
   private TemLvAdapter mTemLvAdpter;
   private TextView mTvError;
   private View mView;

   public AirView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558486, this);
      this.findViews();
      this.initViews();
   }

   private void findViews() {
      this.mRv = (RecyclerView)this.mView.findViewById(2131363226);
      this.mTvError = (TextView)this.mView.findViewById(2131363620);
      this.mBtnLeft0 = (BtnView)this.mView.findViewById(2131362116);
      this.mBtnLeft1 = (BtnView)this.mView.findViewById(2131362117);
      this.mBtnLeft2 = (BtnView)this.mView.findViewById(2131362118);
      this.mBtnLeft3 = (BtnView)this.mView.findViewById(2131362119);
      this.mBtnRight0 = (BtnView)this.mView.findViewById(2131362120);
      this.mBtnRight1 = (BtnView)this.mView.findViewById(2131362121);
      this.mBtnRight2 = (BtnView)this.mView.findViewById(2131362122);
      this.mBtnRight3 = (BtnView)this.mView.findViewById(2131362123);
      this.mBtnBottom0 = (BtnView)this.mView.findViewById(2131362110);
      this.mBtnBottom1 = (BtnView)this.mView.findViewById(2131362111);
      this.mBtnBottom2 = (BtnView)this.mView.findViewById(2131362112);
      this.mBtnBottom3 = (BtnView)this.mView.findViewById(2131362113);
      this.mBtnBottom4 = (BtnView)this.mView.findViewById(2131362114);
      this.mBtnBottom5 = (BtnView)this.mView.findViewById(2131362115);
      this.mBtnCenter = (BtnView)this.mView.findViewById(2131362553);
      this.mBtnViewTemperatureAdd = (BtnView)this.mView.findViewById(2131362124);
      this.mBtnViewTemperatureSub = (BtnView)this.mView.findViewById(2131362125);
      this.mBtnWindPowerAdd = (BtnView)this.mView.findViewById(2131362126);
      this.mBtnWindPowerSub = (BtnView)this.mView.findViewById(2131362127);
      this.mIvWindPower = (ImageView)this.mView.findViewById(2131362679);
      this.mBtnLeft0.setOnClickListener(this);
      this.mBtnLeft1.setOnClickListener(this);
      this.mBtnLeft2.setOnClickListener(this);
      this.mBtnLeft3.setOnClickListener(this);
      this.mBtnRight0.setOnClickListener(this);
      this.mBtnRight1.setOnClickListener(this);
      this.mBtnRight2.setOnClickListener(this);
      this.mBtnRight3.setOnClickListener(this);
      this.mBtnBottom0.setOnClickListener(this);
      this.mBtnBottom1.setOnClickListener(this);
      this.mBtnBottom2.setOnClickListener(this);
      this.mBtnBottom3.setOnClickListener(this);
      this.mBtnBottom4.setOnClickListener(this);
      this.mBtnBottom5.setOnClickListener(this);
      this.mBtnCenter.setOnClickListener(this);
      this.mBtnViewTemperatureAdd.setOnClickListener(this);
      this.mBtnViewTemperatureSub.setOnClickListener(this);
      this.mBtnWindPowerAdd.setOnClickListener(this);
      this.mBtnWindPowerSub.setOnClickListener(this);
   }

   private int[] getTemperatureList(int var1) {
      int var3 = this.mTemArray.length / 2;
      int var2 = 0;

      while(true) {
         int[] var4 = this.mTemArray;
         if (var2 >= var4.length) {
            return var4;
         }

         var4[var2] = var1 + var3 - var2;
         ++var2;
      }
   }

   private void initViews() {
      this.mTemArray = new int[7];
      this.mTemLvAdpter = new TemLvAdapter(this.mContext, this.mTemArray);
      LinearLayoutManager var1 = new LinearLayoutManager(this.mContext);
      this.mRv.setLayoutManager(var1);
      this.mRv.setAdapter(this.mTemLvAdpter);
   }

   private void msgSender(int var1, int var2) {
      MessageSender.showAirSender(var1, var2, true);
      MessageSender.showAirSender(var1, var2, false);
   }

   private void selectTemperature() {
      this.mTemArray = this.getTemperatureList(GeneralCwData.air_temperature);
      this.mTemLvAdpter.notifyDataSetChanged();
   }

   private void selectWindPower() {
      switch (GeneralCwData.air_wind_power) {
         case 0:
            this.mIvWindPower.setImageResource(2131232714);
            break;
         case 1:
            this.mIvWindPower.setImageResource(2131232715);
            break;
         case 2:
            this.mIvWindPower.setImageResource(2131232716);
            break;
         case 3:
            this.mIvWindPower.setImageResource(2131232717);
            break;
         case 4:
            this.mIvWindPower.setImageResource(2131232718);
            break;
         case 5:
            this.mIvWindPower.setImageResource(2131232719);
            break;
         case 6:
            this.mIvWindPower.setImageResource(2131232720);
            break;
         case 7:
            this.mIvWindPower.setImageResource(2131232721);
      }

   }

   public void onClick(View var1) {
      int var2 = var1.getId();
      BtnView var3;
      if (var2 != 2131362553) {
         switch (var2) {
            case 2131362110:
               var3 = this.mBtnBottom0;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_auto = this.mBtnBottom0.isSelect();
               this.msgSender(4, 0);
               break;
            case 2131362111:
               var3 = this.mBtnBottom1;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_cold = this.mBtnBottom1.isSelect();
               this.msgSender(1, 0);
               break;
            case 2131362112:
               var3 = this.mBtnBottom2;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_light = this.mBtnBottom2.isSelect();
               this.msgSender(2, 0);
               break;
            case 2131362113:
               var3 = this.mBtnBottom3;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_wind = this.mBtnBottom3.isSelect();
               this.msgSender(0, 2);
               break;
            case 2131362114:
               if (GeneralCwData.air_in_out) {
                  this.mBtnBottom4.setSelectIcon(2131232677);
                  this.mBtnBottom4.setNoSelectIcon(2131232676);
                  GeneralCwData.air_in_out = false;
               } else {
                  this.mBtnBottom4.setSelectIcon(2131232675);
                  this.mBtnBottom4.setNoSelectIcon(2131232674);
                  GeneralCwData.air_in_out = true;
               }
               break;
            case 2131362115:
               var3 = this.mBtnBottom5;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_window_wind = this.mBtnBottom5.isSelect();
               this.msgSender(2, 2);
               break;
            case 2131362116:
               if (!this.mBtnLeft1.isSelect()) {
                  var3 = this.mBtnLeft0;
                  var3.setSelect(var3.isSelect() ^ true);
                  GeneralCwData.air_sb = this.mBtnLeft0.isSelect();
                  MessageSender.showCommonSwitch(6, 4, this.mBtnLeft0.isSelect());
               }
               break;
            case 2131362117:
               if (this.mBtnLeft0.isSelect()) {
                  var3 = this.mBtnLeft1;
                  var3.setSelect(var3.isSelect() ^ true);
                  GeneralCwData.air_dh = this.mBtnLeft1.isSelect();
                  MessageSender.showCommonSwitch(6, 6, this.mBtnLeft1.isSelect());
               }
               break;
            case 2131362118:
               var3 = this.mBtnLeft2;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_sj = this.mBtnLeft2.isSelect();
               MessageSender.showCommonSwitch(7, 4, this.mBtnLeft2.isSelect());
               break;
            case 2131362119:
               var3 = this.mBtnLeft3;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_ck = this.mBtnLeft3.isSelect();
               MessageSender.showCommonSwitch(7, 6, this.mBtnLeft3.isSelect());
               break;
            case 2131362120:
               if (!this.mBtnRight1.isSelect()) {
                  var3 = this.mBtnRight0;
                  var3.setSelect(var3.isSelect() ^ true);
                  GeneralCwData.air_a = this.mBtnRight0.isSelect();
                  MessageSender.showCommonSwitch(6, 0, this.mBtnRight0.isSelect());
               }
               break;
            case 2131362121:
               if (this.mBtnRight0.isSelect()) {
                  var3 = this.mBtnRight1;
                  var3.setSelect(var3.isSelect() ^ true);
                  GeneralCwData.air_b = this.mBtnRight1.isSelect();
                  MessageSender.showCommonSwitch(6, 2, this.mBtnRight1.isSelect());
               }
               break;
            case 2131362122:
               var3 = this.mBtnRight2;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_hfw = this.mBtnRight2.isSelect();
               MessageSender.showCommonSwitch(7, 0, this.mBtnRight2.isSelect());
               break;
            case 2131362123:
               var3 = this.mBtnRight3;
               var3.setSelect(var3.isSelect() ^ true);
               GeneralCwData.air_hfn = this.mBtnRight3.isSelect();
               MessageSender.showCommonSwitch(7, 2, this.mBtnRight3.isSelect());
               break;
            case 2131362124:
               var2 = GeneralCwData.air_temperature + 1;
               if (var2 > 52 || var2 < 16) {
                  return;
               }

               ++GeneralCwData.air_temperature;
               Log.w("AirView", "---------发送温度can---------------->" + GeneralCwData.air_temperature);
               this.msgSender(3, 0);
               this.selectTemperature();
               break;
            case 2131362125:
               var2 = GeneralCwData.air_temperature - 1;
               if (var2 > 52 || var2 < 16) {
                  return;
               }

               --GeneralCwData.air_temperature;
               Log.w("AirView", "---------发送温度can---------------->" + GeneralCwData.air_temperature);
               this.msgSender(3, 2);
               this.selectTemperature();
               break;
            case 2131362126:
               var2 = GeneralCwData.air_wind_power + 1;
               if (var2 > 7 || var2 < 0) {
                  return;
               }

               ++GeneralCwData.air_wind_power;
               Log.w("AirView", "---------发送风量can---------------->" + GeneralCwData.air_wind_power);
               this.msgSender(0, 4);
               this.selectWindPower();
               break;
            case 2131362127:
               var2 = GeneralCwData.air_wind_power - 1;
               if (var2 > 7 || var2 < 0) {
                  return;
               }

               --GeneralCwData.air_wind_power;
               Log.w("AirView", "---------发送风量can---------------->" + GeneralCwData.air_wind_power);
               this.msgSender(0, 6);
               this.selectWindPower();
         }
      } else {
         var3 = this.mBtnCenter;
         var3.setSelect(var3.isSelect() ^ true);
         GeneralCwData.air_power = this.mBtnCenter.isSelect();
         this.msgSender(0, 0);
      }

   }

   public void onDestroy() {
   }

   public void refreshUi(Bundle var1) {
      this.mBtnLeft0.setSelect(GeneralCwData.air_sb);
      this.mBtnLeft1.setSelect(GeneralCwData.air_dh);
      this.mBtnLeft2.setSelect(GeneralCwData.air_sj);
      this.mBtnLeft3.setSelect(GeneralCwData.air_ck);
      this.mBtnRight0.setSelect(GeneralCwData.air_a);
      this.mBtnRight1.setSelect(GeneralCwData.air_b);
      this.mBtnRight2.setSelect(GeneralCwData.air_hfw);
      this.mBtnRight3.setSelect(GeneralCwData.air_hfn);
      this.mBtnBottom0.setSelect(GeneralCwData.air_auto);
      this.mBtnBottom1.setSelect(GeneralCwData.air_cold);
      this.mBtnBottom2.setSelect(GeneralCwData.air_light);
      this.mBtnBottom3.setSelect(GeneralCwData.air_wind);
      if (GeneralCwData.air_in_out) {
         this.mBtnBottom4.setSelectIcon(2131232677);
         this.mBtnBottom4.setNoSelectIcon(2131232676);
      } else {
         this.mBtnBottom4.setSelectIcon(2131232675);
         this.mBtnBottom4.setNoSelectIcon(2131232674);
      }

      this.mBtnBottom5.setSelect(GeneralCwData.air_window_wind);
      this.mBtnCenter.setSelect(GeneralCwData.air_power);
      if (GeneralCwData.air_temperature != 0) {
         this.selectTemperature();
      }

      this.selectWindPower();
      if (!TextUtils.isEmpty(GeneralCwData.error)) {
         this.mTvError.setText("Err  " + GeneralCwData.error);
      } else {
         this.mTvError.setText("");
      }

   }
}
