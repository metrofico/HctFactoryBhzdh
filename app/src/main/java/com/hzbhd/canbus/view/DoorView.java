package com.hzbhd.canbus.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.adapter.util.DispUtility;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.LogUtil;

public class DoorView {
   private boolean isShowing;
   private ImageView mBackIv;
   private TextView mBatteryWarningTv;
   private Context mContext;
   private TextView mEspTv;
   private RelativeLayout mFloatView;
   private ImageView mFrontIv;
   private final DoorItem mFrontLeftDoor;
   private final DoorItem mFrontRightDoor;
   private TextView mFuelWarningTv;
   private TextView mHandBrakeTv;
   private final DoorItem mHood;
   private final DoorItem mIsBatteryWarning;
   private boolean mIsDataChange;
   private final DoorItem mIsEspOn;
   private final DoorItem mIsFuelWarning;
   private final DoorItem mIsHandBrakeUp;
   private final DoorItem mIsIsTopOn;
   private final DoorItem mIsLittleLightOn;
   private final DoorItem mIsSeatBeltTie;
   private final DoorItem mIsSeatCoPilotBeltTie;
   private final DoorItem mIsSeatMasterDriverBeltTie;
   private final DoorItem mIsSeatRLBeltTie;
   private final DoorItem mIsSeatRMBeltTie;
   private final DoorItem mIsSeatRRBeltTie;
   private final DoorItem mIsSubSeatBeltTie;
   private final DoorItem mIsWashingFluidWarning;
   private final DoorItem mIsWaterTempWarning;
   private TextView mIstopTv;
   private WindowManager.LayoutParams mLayoutParams;
   private ImageView mLeftFrontIv;
   private ImageView mLeftRearIv;
   private TextView mLittleLightTv;
   CanSettingProxy mProxy;
   private final DoorItem mRearLeftDoor;
   private final DoorItem mRearRightDoor;
   private ImageView mRightFrontIv;
   private ImageView mRightRearIv;
   private RelativeLayout mRlCarBody;
   private Runnable mRunnable;
   private TextView mSeatBeltCoPilotTv;
   private TextView mSeatBeltMasterTv;
   private TextView mSeatBeltRearLeftTv;
   private TextView mSeatBeltRearMidTv;
   private TextView mSeatBeltRearRightTv;
   private TextView mSeatBeltSubTv;
   private TextView mSeatBeltTv;
   private ImageView mSkyWindowIv;
   private final DoorItem mSkyWindowOpenLevel;
   private final DoorItem mTrunk;
   private TextView mWashingFluidWarning;
   private TextView mWaterTempTv;
   private WindowManager mWindowManager;

   public DoorView(Context var1) {
      Boolean var2 = false;
      this.isShowing = false;
      this.mFrontLeftDoor = new DoorItem(this, var2);
      this.mFrontRightDoor = new DoorItem(this, var2);
      this.mRearLeftDoor = new DoorItem(this, var2);
      this.mRearRightDoor = new DoorItem(this, var2);
      this.mHood = new DoorItem(this, var2);
      this.mTrunk = new DoorItem(this, var2);
      this.mSkyWindowOpenLevel = new DoorItem(this, 0);
      this.mIsHandBrakeUp = new DoorItem(this, var2);
      this.mIsSeatBeltTie = new DoorItem(this, var2);
      this.mIsSubSeatBeltTie = new DoorItem(this, var2);
      this.mIsSeatMasterDriverBeltTie = new DoorItem(this, var2);
      this.mIsSeatCoPilotBeltTie = new DoorItem(this, var2);
      this.mIsSeatRLBeltTie = new DoorItem(this, var2);
      this.mIsSeatRMBeltTie = new DoorItem(this, var2);
      this.mIsSeatRRBeltTie = new DoorItem(this, var2);
      this.mIsEspOn = new DoorItem(this, var2);
      this.mIsIsTopOn = new DoorItem(this, var2);
      this.mIsLittleLightOn = new DoorItem(this, var2);
      this.mIsWaterTempWarning = new DoorItem(this, var2);
      this.mIsBatteryWarning = new DoorItem(this, var2);
      this.mIsFuelWarning = new DoorItem(this, var2);
      this.mIsWashingFluidWarning = new DoorItem(this, var2);
      this.mProxy = (CanSettingProxy)Dependency.get(CanSettingProxy.class);
      this.mRunnable = new Runnable(this) {
         final DoorView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.dismissView();
         }
      };
      this.mContext = var1;
      this.mWindowManager = (WindowManager)var1.getSystemService("window");
      this.initView();
   }

   private void addViewToWindow() {
      if (this.mLayoutParams == null) {
         WindowManager.LayoutParams var1 = new WindowManager.LayoutParams();
         this.mLayoutParams = var1;
         var1.type = 2002;
         this.mLayoutParams.format = 1;
         this.mLayoutParams.gravity = 17;
         this.mLayoutParams.width = -2;
         this.mLayoutParams.height = -2;
      }

      if (!this.isShowing) {
         this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
         this.isShowing = true;
      }

      if (!((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getDoorCountDownTimerState()) {
         this.mFloatView.removeCallbacks(this.mRunnable);
         this.mFloatView.postDelayed(this.mRunnable, 5000L);
      }

   }

   private void dismissView() {
      WindowManager var2 = this.mWindowManager;
      if (var2 != null) {
         RelativeLayout var1 = this.mFloatView;
         if (var1 != null) {
            var2.removeView(var1);
            this.isShowing = false;
         }
      }

   }

   private void initView() {
      DispUtility.disabledDisplayDpiChange(this.mContext.getResources());
      RelativeLayout var1 = (RelativeLayout)LayoutInflater.from(this.mContext).inflate(2131558761, (ViewGroup)null);
      this.mFloatView = var1;
      this.mLeftFrontIv = (ImageView)var1.findViewById(2131362592);
      this.mRightFrontIv = (ImageView)this.mFloatView.findViewById(2131362623);
      this.mLeftRearIv = (ImageView)this.mFloatView.findViewById(2131362597);
      this.mRightRearIv = (ImageView)this.mFloatView.findViewById(2131362627);
      this.mBackIv = (ImageView)this.mFloatView.findViewById(2131362534);
      this.mSkyWindowIv = (ImageView)this.mFloatView.findViewById(2131362644);
      this.mFrontIv = (ImageView)this.mFloatView.findViewById(2131362570);
      this.mHandBrakeTv = (TextView)this.mFloatView.findViewById(2131363627);
      this.mSeatBeltTv = (TextView)this.mFloatView.findViewById(2131363692);
      this.mSeatBeltSubTv = (TextView)this.mFloatView.findViewById(2131363698);
      this.mEspTv = (TextView)this.mFloatView.findViewById(2131363621);
      this.mIstopTv = (TextView)this.mFloatView.findViewById(2131363633);
      this.mLittleLightTv = (TextView)this.mFloatView.findViewById(2131363648);
      this.mWaterTempTv = (TextView)this.mFloatView.findViewById(2131363718);
      this.mBatteryWarningTv = (TextView)this.mFloatView.findViewById(2131363591);
      this.mFuelWarningTv = (TextView)this.mFloatView.findViewById(2131363625);
      this.mWashingFluidWarning = (TextView)this.mFloatView.findViewById(2131363717);
      this.mSeatBeltMasterTv = (TextView)this.mFloatView.findViewById(2131363694);
      this.mSeatBeltCoPilotTv = (TextView)this.mFloatView.findViewById(2131363693);
      this.mSeatBeltRearLeftTv = (TextView)this.mFloatView.findViewById(2131363695);
      this.mSeatBeltRearMidTv = (TextView)this.mFloatView.findViewById(2131363696);
      this.mSeatBeltRearRightTv = (TextView)this.mFloatView.findViewById(2131363697);
      this.mRlCarBody = (RelativeLayout)this.mFloatView.findViewById(2131363190);
      if (!GeneralDoorData.isShowCarBody) {
         this.mRlCarBody.setVisibility(8);
      }

      this.mFloatView.setOnClickListener(new View.OnClickListener(this) {
         final DoorView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            this.this$0.mFloatView.removeCallbacks(this.this$0.mRunnable);
            this.this$0.mFloatView.post(this.this$0.mRunnable);
         }
      });
      LogUtil.showLog("door", "11 door_right_front_margin_end:" + this.mContext.getResources().getDimension(2131167406));
   }

   private boolean isDataChange() {
      if (this.mProxy.getDoorSwapFront()) {
         this.mFrontLeftDoor.setValue(GeneralDoorData.isRightFrontDoorOpen);
         this.mFrontRightDoor.setValue(GeneralDoorData.isLeftFrontDoorOpen);
      } else {
         this.mFrontLeftDoor.setValue(GeneralDoorData.isLeftFrontDoorOpen);
         this.mFrontRightDoor.setValue(GeneralDoorData.isRightFrontDoorOpen);
      }

      if (this.mProxy.getDoorSwapRear()) {
         this.mRearLeftDoor.setValue(GeneralDoorData.isRightRearDoorOpen);
         this.mRearRightDoor.setValue(GeneralDoorData.isLeftRearDoorOpen);
      } else {
         this.mRearLeftDoor.setValue(GeneralDoorData.isLeftRearDoorOpen);
         this.mRearRightDoor.setValue(GeneralDoorData.isRightRearDoorOpen);
      }

      Log.i("DoorView", "isDataChange: mHood");
      if (this.mProxy.getShowHood()) {
         this.mHood.setValue(GeneralDoorData.isFrontOpen);
      } else {
         this.mHood.setValue(false);
      }

      Log.i("DoorView", "isDataChange: mTrunk");
      if (this.mProxy.getShowTrunk()) {
         this.mTrunk.setValue(GeneralDoorData.isBackOpen);
      } else {
         this.mTrunk.setValue(false);
      }

      this.mSkyWindowOpenLevel.setValue(GeneralDoorData.skyWindowOpenLevel);
      if (GeneralDoorData.isShowHandBrake) {
         this.mIsHandBrakeUp.setValue(GeneralDoorData.isHandBrakeUp);
      }

      if (GeneralDoorData.isShowSeatBelt) {
         this.mIsSeatBeltTie.setValue(GeneralDoorData.isSeatBeltTie);
      }

      if (GeneralDoorData.isSubShowSeatBelt) {
         this.mIsSubSeatBeltTie.setValue(GeneralDoorData.isSubSeatBeltTie);
      }

      if (GeneralDoorData.isShowMasterDriverSeatBelt) {
         this.mIsSeatMasterDriverBeltTie.setValue(GeneralDoorData.isSeatMasterDriverBeltTie);
      }

      if (GeneralDoorData.isShowCoPilotSeatBelt) {
         this.mIsSeatCoPilotBeltTie.setValue(GeneralDoorData.isSeatCoPilotBeltTie);
      }

      if (GeneralDoorData.isShowRLSeatBelt) {
         this.mIsSeatRLBeltTie.setValue(GeneralDoorData.isSeatRLBeltTie);
      }

      if (GeneralDoorData.isShowRMSeatBelt) {
         this.mIsSeatRMBeltTie.setValue(GeneralDoorData.isSeatRMBeltTie);
      }

      if (GeneralDoorData.isShowRRSeatBelt) {
         this.mIsSeatRRBeltTie.setValue(GeneralDoorData.isSeatRRBeltTie);
      }

      if (GeneralDoorData.isShowEsp) {
         this.mIsEspOn.setValue(GeneralDoorData.isEspOn);
      }

      if (GeneralDoorData.isShowIstop) {
         this.mIsIsTopOn.setValue(GeneralDoorData.isIstopOn);
      }

      if (GeneralDoorData.isShowLittleLight) {
         this.mIsLittleLightOn.setValue(GeneralDoorData.isLittleLightOn);
      }

      if (GeneralDoorData.isShowWaterTemp) {
         this.mIsWaterTempWarning.setValue(GeneralDoorData.isWaterTempWarning);
      }

      if (GeneralDoorData.isShowBatteryWarning) {
         this.mIsBatteryWarning.setValue(GeneralDoorData.isBatteryWarning);
      }

      if (GeneralDoorData.isShowFuelWarning) {
         this.mIsFuelWarning.setValue(GeneralDoorData.isFuelWarning);
      }

      if (GeneralDoorData.isShowWashingFluidWarning) {
         this.mIsWashingFluidWarning.setValue(GeneralDoorData.isWashingFluidWarning);
      }

      return this.mIsDataChange;
   }

   private void setImageViewStatus(ImageView var1, Boolean var2, int var3, int var4) {
      if (!var2) {
         var3 = var4;
      }

      var1.setImageResource(var3);
   }

   public void refreshUi() {
      if (this.mProxy.getShowDoorInfo() && this.isDataChange()) {
         this.mIsDataChange = false;
         this.setImageViewStatus(this.mLeftFrontIv, (Boolean)this.mFrontLeftDoor.getValue(), 2131232600, 2131232599);
         this.setImageViewStatus(this.mRightFrontIv, (Boolean)this.mFrontRightDoor.getValue(), 2131232602, 2131232601);
         this.setImageViewStatus(this.mLeftRearIv, (Boolean)this.mRearLeftDoor.getValue(), 2131232604, 2131232603);
         this.setImageViewStatus(this.mRightRearIv, (Boolean)this.mRearRightDoor.getValue(), 2131232606, 2131232605);
         if ((Boolean)this.mHood.getValue()) {
            this.mFrontIv.setVisibility(0);
         } else {
            this.mFrontIv.setVisibility(4);
         }

         if ((Boolean)this.mTrunk.getValue()) {
            this.mBackIv.setVisibility(0);
         } else {
            this.mBackIv.setVisibility(4);
         }

         int var1 = (Integer)this.mSkyWindowOpenLevel.getValue();
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.mSkyWindowIv.setImageResource(2131232609);
               }
            } else {
               this.mSkyWindowIv.setImageResource(2131232607);
            }
         } else {
            this.mSkyWindowIv.setImageResource(2131232608);
         }

         if (GeneralDoorData.isShowHandBrake) {
            this.mHandBrakeTv.setVisibility(0);
            if ((Boolean)this.mIsHandBrakeUp.getValue()) {
               this.mHandBrakeTv.setText(2131768701);
               this.mHandBrakeTv.setCompoundDrawablesWithIntrinsicBounds(2131233907, 0, 0, 0);
            } else {
               this.mHandBrakeTv.setText(2131768699);
               this.mHandBrakeTv.setCompoundDrawablesWithIntrinsicBounds(2131233906, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowSeatBelt) {
            this.mSeatBeltTv.setVisibility(0);
            TextView var2;
            if ((Boolean)this.mIsSeatBeltTie.getValue()) {
               var2 = this.mSeatBeltTv;
               if (GeneralDoorData.isSubShowSeatBelt) {
                  var1 = 2131769172;
               } else {
                  var1 = 2131769883;
               }

               var2.setText(var1);
               this.mSeatBeltTv.setCompoundDrawablesWithIntrinsicBounds(2131232488, 0, 0, 0);
            } else {
               var2 = this.mSeatBeltTv;
               if (GeneralDoorData.isSubShowSeatBelt) {
                  var1 = 2131769173;
               } else {
                  var1 = 2131769884;
               }

               var2.setText(var1);
               this.mSeatBeltTv.setCompoundDrawablesWithIntrinsicBounds(2131232487, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isSubShowSeatBelt) {
            this.mSeatBeltSubTv.setVisibility(0);
            if ((Boolean)this.mIsSubSeatBeltTie.getValue()) {
               this.mSeatBeltSubTv.setText(2131768048);
               this.mSeatBeltSubTv.setCompoundDrawablesWithIntrinsicBounds(2131232488, 0, 0, 0);
            } else {
               this.mSeatBeltSubTv.setText(2131768049);
               this.mSeatBeltSubTv.setCompoundDrawablesWithIntrinsicBounds(2131232487, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowMasterDriverSeatBelt) {
            this.mSeatBeltMasterTv.setVisibility(0);
            this.mSeatBeltMasterTv.setText(2131769171);
            if ((Boolean)this.mIsSeatMasterDriverBeltTie.getValue()) {
               this.mSeatBeltMasterTv.setCompoundDrawablesWithIntrinsicBounds(2131232488, 0, 0, 0);
            } else {
               this.mSeatBeltMasterTv.setCompoundDrawablesWithIntrinsicBounds(2131232487, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowCoPilotSeatBelt) {
            this.mSeatBeltCoPilotTv.setVisibility(0);
            this.mSeatBeltCoPilotTv.setText(2131768047);
            if ((Boolean)this.mIsSeatCoPilotBeltTie.getValue()) {
               this.mSeatBeltCoPilotTv.setCompoundDrawablesWithIntrinsicBounds(2131232488, 0, 0, 0);
            } else {
               this.mSeatBeltCoPilotTv.setCompoundDrawablesWithIntrinsicBounds(2131232487, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowRLSeatBelt) {
            this.mSeatBeltRearLeftTv.setVisibility(0);
            this.mSeatBeltRearLeftTv.setText(2131767851);
            if ((Boolean)this.mIsSeatRLBeltTie.getValue()) {
               this.mSeatBeltRearLeftTv.setCompoundDrawablesWithIntrinsicBounds(2131232488, 0, 0, 0);
            } else {
               this.mSeatBeltRearLeftTv.setCompoundDrawablesWithIntrinsicBounds(2131232487, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowRMSeatBelt) {
            this.mSeatBeltRearMidTv.setVisibility(0);
            this.mSeatBeltRearMidTv.setText(2131767852);
            if ((Boolean)this.mIsSeatRMBeltTie.getValue()) {
               this.mSeatBeltRearMidTv.setCompoundDrawablesWithIntrinsicBounds(2131232488, 0, 0, 0);
            } else {
               this.mSeatBeltRearMidTv.setCompoundDrawablesWithIntrinsicBounds(2131232487, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowRRSeatBelt) {
            this.mSeatBeltRearRightTv.setVisibility(0);
            this.mSeatBeltRearRightTv.setText(2131767853);
            if ((Boolean)this.mIsSeatRRBeltTie.getValue()) {
               this.mSeatBeltRearRightTv.setCompoundDrawablesWithIntrinsicBounds(2131232488, 0, 0, 0);
            } else {
               this.mSeatBeltRearRightTv.setCompoundDrawablesWithIntrinsicBounds(2131232487, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowEsp) {
            this.mEspTv.setVisibility(0);
            if ((Boolean)this.mIsEspOn.getValue()) {
               this.mEspTv.setText(2131768229);
               this.mEspTv.setCompoundDrawablesWithIntrinsicBounds(2131233384, 0, 0, 0);
            } else {
               this.mEspTv.setVisibility(8);
            }
         }

         if (GeneralDoorData.isShowIstop) {
            this.mIstopTv.setVisibility(0);
            if ((Boolean)this.mIsIsTopOn.getValue()) {
               this.mIstopTv.setText(2131768912);
               this.mIstopTv.setCompoundDrawablesWithIntrinsicBounds(2131234249, 0, 0, 0);
            } else {
               this.mIstopTv.setText(2131768911);
               this.mIstopTv.setCompoundDrawablesWithIntrinsicBounds(2131234248, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowLittleLight) {
            this.mLittleLightTv.setVisibility(0);
            if ((Boolean)this.mIsLittleLightOn.getValue()) {
               this.mLittleLightTv.setText(2131769134);
               this.mLittleLightTv.setCompoundDrawablesWithIntrinsicBounds(2131234811, 0, 0, 0);
            } else {
               this.mLittleLightTv.setText(2131769133);
               this.mLittleLightTv.setCompoundDrawablesWithIntrinsicBounds(2131234810, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowWaterTemp) {
            this.mWaterTempTv.setVisibility(0);
            if ((Boolean)this.mIsWaterTempWarning.getValue()) {
               this.mWaterTempTv.setText(2131770846);
               this.mWaterTempTv.setCompoundDrawablesWithIntrinsicBounds(2131234948, 0, 0, 0);
            } else {
               this.mWaterTempTv.setText(2131770845);
               this.mWaterTempTv.setCompoundDrawablesWithIntrinsicBounds(2131234947, 0, 0, 0);
            }
         }

         if (GeneralDoorData.isShowBatteryWarning) {
            if ((Boolean)this.mIsBatteryWarning.getValue()) {
               this.mBatteryWarningTv.setVisibility(0);
               this.mBatteryWarningTv.setText(2131767866);
               this.mBatteryWarningTv.setCompoundDrawablesWithIntrinsicBounds(2131232486, 0, 0, 0);
            } else {
               this.mBatteryWarningTv.setVisibility(8);
            }
         }

         if (GeneralDoorData.isShowFuelWarning) {
            if ((Boolean)this.mIsFuelWarning.getValue()) {
               this.mFuelWarningTv.setVisibility(0);
               this.mFuelWarningTv.setText(2131768416);
               this.mFuelWarningTv.setCompoundDrawablesWithIntrinsicBounds(2131233755, 0, 0, 0);
            } else {
               this.mFuelWarningTv.setVisibility(8);
            }
         }

         if (GeneralDoorData.isShowWashingFluidWarning) {
            if ((Boolean)this.mIsWashingFluidWarning.getValue()) {
               this.mWashingFluidWarning.setVisibility(0);
               this.mWashingFluidWarning.setText(2131770844);
               this.mWashingFluidWarning.setCompoundDrawablesWithIntrinsicBounds(2131234946, 0, 0, 0);
            } else {
               this.mWashingFluidWarning.setVisibility(8);
            }
         }

         this.addViewToWindow();
      }

   }

   private class DoorItem {
      final DoorView this$0;
      private Object value;

      public DoorItem(DoorView var1, Object var2) {
         this.this$0 = var1;
         this.value = var2;
      }

      Object getValue() {
         return this.value;
      }

      void setValue(Object var1) {
         if (this.value != var1) {
            this.value = var1;
            this.this$0.mIsDataChange = true;
         }

      }
   }
}
