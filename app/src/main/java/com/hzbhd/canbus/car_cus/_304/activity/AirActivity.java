package com.hzbhd.canbus.car_cus._304.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.car_cus._304.view.AirTemperatureView;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;

public class AirActivity extends AbstractBaseActivity implements View.OnClickListener, AirTemperatureView.OnTemperatureAdjustListener {
   public static final int GEAR_COUNT = 15;
   public static final int MAX_GEAR = 15;
   public static final float SINGLE_ANGLE = 10.0F;
   public static boolean mIsMsgStart;
   private final int MSG_FINISH_ACTIVITY = 0;
   private final int POSITION_AC = 5;
   private final int POSITION_BLOW_FOOT = 2;
   private final int POSITION_BLOW_HEAD = 0;
   private final int POSITION_BLOW_HEAD_FOOT = 1;
   private final int POSITION_BLOW_WINDOW_FOOT = 3;
   private final int POSITION_CYCLE = 7;
   private final int POSITION_POWER = 4;
   private final int POSITION_PTC_HEATING = 6;
   private final String TAG = "_304_AirActivity";
   private AirPageUiSet mAirPageUiSet;
   private int mGear;
   private ImageButton mIbAc;
   private ImageButton mIbCylce;
   private ImageButton mIbPower;
   private ImageButton mIbPtc;
   private ImageView mIvBtnWindFoot;
   private ImageView mIvBtnWindHead;
   private ImageView mIvBtnWindHeadFoot;
   private ImageView mIvBtnWindWindowFoot;
   private ImageView mIvImgWindFoot;
   private ImageView mIvImgWindHead;
   private ImageView mIvImgWindIn;
   private ImageView mIvImgWindWindow;
   private OnAirBtnClickListener mOnAirBtnClickListener;
   private OnAirTemperatureUpDownClickListener mOnAirTemperatureUpDownClickListener;
   private AirTemperatureView mTemperatureView;

   private boolean compare(Object var1, Object... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var2[var3].equals(var1)) {
            return true;
         }
      }

      return false;
   }

   private void findViews() {
      this.mIbPower = (ImageButton)this.findViewById(2131362412);
      this.mIbAc = (ImageButton)this.findViewById(2131362382);
      this.mIbPtc = (ImageButton)this.findViewById(2131362413);
      this.mIbCylce = (ImageButton)this.findViewById(2131362385);
      this.mIvImgWindIn = (ImageView)this.findViewById(2131362575);
      this.mIvImgWindWindow = (ImageView)this.findViewById(2131362576);
      this.mIvImgWindHead = (ImageView)this.findViewById(2131362574);
      this.mIvImgWindFoot = (ImageView)this.findViewById(2131362573);
      this.mIvBtnWindHead = (ImageView)this.findViewById(2131362547);
      this.mIvBtnWindHeadFoot = (ImageView)this.findViewById(2131362548);
      this.mIvBtnWindFoot = (ImageView)this.findViewById(2131362546);
      this.mIvBtnWindWindowFoot = (ImageView)this.findViewById(2131362549);
      this.mTemperatureView = (AirTemperatureView)this.findViewById(2131363505);
   }

   public static int getGear(float var0) {
      return 15 - (int)((var0 - 20.0F) / 10.0F);
   }

   private void initViews() {
      AirPageUiSet var1 = UiMgrFactory.getCanUiMgr(this).getAirUiSet(this);
      this.mAirPageUiSet = var1;
      this.mOnAirBtnClickListener = var1.getFrontArea().getOnAirBtnClickListeners()[0];
      this.mOnAirTemperatureUpDownClickListener = this.mAirPageUiSet.getFrontArea().getTempSetViewOnUpDownClickListeners()[0];
      this.mTemperatureView.initViews(this);
      this.refreshUi((Bundle)null);
   }

   private void setInvisible(View var1, boolean var2) {
      if (var2) {
         var1.setVisibility(0);
      } else {
         var1.setVisibility(4);
      }

   }

   private void setIvImageWind() {
      ImageView var4 = this.mIvImgWindWindow;
      int var1 = MyGeneralData.mBlowMode;
      Integer var2 = 3;
      Integer var3 = 1;
      this.setInvisible(var4, this.compare(var1, var2, 4));
      this.setInvisible(this.mIvImgWindHead, this.compare(MyGeneralData.mBlowMode, 0, var3));
      this.setInvisible(this.mIvImgWindFoot, this.compare(MyGeneralData.mBlowMode, var3, 2, var2));
   }

   private void setIvImgWindIn() {
      if (GeneralAirData.in_out_cycle) {
         this.mIbCylce.setImageResource(2131231154);
      } else {
         this.mIbCylce.setImageResource(2131231155);
      }

      this.setInvisible(this.mIvImgWindIn, GeneralAirData.in_out_cycle ^ true);
   }

   private void setWindBtnsPosition(int var1) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  this.mIvBtnWindHead.setSelected(false);
                  this.mIvBtnWindHeadFoot.setSelected(false);
                  this.mIvBtnWindFoot.setSelected(false);
                  this.mIvBtnWindWindowFoot.setSelected(true);
               }
            } else {
               this.mIvBtnWindHead.setSelected(false);
               this.mIvBtnWindHeadFoot.setSelected(false);
               this.mIvBtnWindFoot.setSelected(true);
               this.mIvBtnWindWindowFoot.setSelected(false);
            }
         } else {
            this.mIvBtnWindHead.setSelected(false);
            this.mIvBtnWindHeadFoot.setSelected(true);
            this.mIvBtnWindFoot.setSelected(false);
            this.mIvBtnWindWindowFoot.setSelected(false);
         }
      } else {
         this.mIvBtnWindHead.setSelected(true);
         this.mIvBtnWindHeadFoot.setSelected(false);
         this.mIvBtnWindFoot.setSelected(false);
         this.mIvBtnWindWindowFoot.setSelected(false);
      }

   }

   public void onAdjust(float var1) {
      if (this.mOnAirTemperatureUpDownClickListener != null) {
         Log.i("_304_AirActivity", "onAdjust: angle - > " + var1);
         int var2 = getGear(var1);
         if (this.mGear != var2) {
            Log.i("_304_AirActivity", "onAdjust: gear - > " + var2);
            this.mGear = var2;
            CanbusMsgSender.sendMsg(new byte[]{22, -78, 0, 0, (byte)(var2 << 3), 0});
         }

      }
   }

   public void onClick(View var1) {
      switch (var1.getId()) {
         case 2131362140:
            this.mOnAirBtnClickListener.onClickItem(2);
            this.setWindBtnsPosition(2);
            MyGeneralData.mBlowMode = 2;
            this.setIvImageWind();
            break;
         case 2131362141:
            this.mOnAirBtnClickListener.onClickItem(0);
            this.setWindBtnsPosition(0);
            MyGeneralData.mBlowMode = 0;
            this.setIvImageWind();
            break;
         case 2131362142:
            this.mOnAirBtnClickListener.onClickItem(1);
            this.setWindBtnsPosition(1);
            MyGeneralData.mBlowMode = 1;
            this.setIvImageWind();
            break;
         case 2131362143:
            this.mOnAirBtnClickListener.onClickItem(3);
            this.setWindBtnsPosition(3);
            MyGeneralData.mBlowMode = 3;
            this.setIvImageWind();
            break;
         case 2131362382:
            this.mOnAirBtnClickListener.onClickItem(5);
            break;
         case 2131362385:
            this.mOnAirBtnClickListener.onClickItem(7);
            GeneralAirData.in_out_cycle ^= true;
            this.setIvImgWindIn();
            break;
         case 2131362412:
            this.mOnAirBtnClickListener.onClickItem(4);
            break;
         case 2131362413:
            this.mOnAirBtnClickListener.onClickItem(6);
      }

   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setContentView(2131558499);
      this.findViews();
      this.initViews();
   }

   protected void onStop() {
      mIsMsgStart = false;
      super.onStop();
   }

   public void refreshUi(Bundle var1) {
      int var3 = MyGeneralData.mTemperatureGear;
      boolean var5 = true;
      Integer var6 = 1;
      float var2 = (float)(var3 - 1);
      this.mTemperatureView.refresh(MyGeneralData.mTemperature, 180.0F - (var2 * 10.0F + 20.0F));
      this.setInvisible(this.mIvImgWindIn, GeneralAirData.in_out_cycle ^ true);
      this.setInvisible(this.mIvImgWindWindow, this.compare(MyGeneralData.mBlowMode, 3, 4));
      this.setInvisible(this.mIvImgWindHead, this.compare(MyGeneralData.mBlowMode, 0, var6));
      this.setInvisible(this.mIvImgWindFoot, this.compare(MyGeneralData.mBlowMode, var6, 2, 3));
      ImageView var7 = this.mIvBtnWindHead;
      boolean var4;
      if (MyGeneralData.mBlowMode == 0) {
         var4 = true;
      } else {
         var4 = false;
      }

      var7.setSelected(var4);
      var7 = this.mIvBtnWindHeadFoot;
      if (MyGeneralData.mBlowMode == 1) {
         var4 = true;
      } else {
         var4 = false;
      }

      var7.setSelected(var4);
      var7 = this.mIvBtnWindFoot;
      if (MyGeneralData.mBlowMode == 2) {
         var4 = true;
      } else {
         var4 = false;
      }

      var7.setSelected(var4);
      var7 = this.mIvBtnWindWindowFoot;
      if (MyGeneralData.mBlowMode == 3) {
         var4 = var5;
      } else {
         var4 = false;
      }

      var7.setSelected(var4);
      this.mIbPower.setSelected(MyGeneralData.etc_power);
      this.mIbAc.setSelected(GeneralAirData.ac);
      this.mIbPtc.setSelected(MyGeneralData.ptc);
      if (GeneralAirData.in_out_cycle) {
         this.mIbCylce.setImageResource(2131231154);
      } else {
         this.mIbCylce.setImageResource(2131231155);
      }

   }
}
