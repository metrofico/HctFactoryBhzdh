package com.hzbhd.canbus.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.RearArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;

public class AirRearView extends RelativeLayout implements View.OnClickListener {
   private AirActivity mActivity;
   private ImageView mBlowFootLeftIv;
   private ImageView mBlowFootRightIv;
   private ImageView mBlowHeadLeftIv;
   private ImageView mBlowHeadRightIv;
   private ImageView mBlowWindowsLeftIv;
   private ImageView mBlowWindowsRightIv;
   private LineBtnView mBottomLbv;
   private LineBtnView mBottomLeftLbv;
   private LineBtnView mBottomRightLbv;
   private SeatHeatHotSetView mColdLeftShhsv;
   private SeatHeatHotSetView mColdRightShhsv;
   private Context mContext;
   private SeatHeatHotSetView mHeatLeftShhsv;
   private SeatHeatHotSetView mHeatRightShhsv;
   private ImageView mLeftBlowAuto;
   private ImageView mLeftSeatIv;
   private String[][] mLineBtnAction;
   private OnAirPageStatusListener mOnAirPageStatusListener;
   private OnAirSeatClickListener mOnAirSeatClickListener;
   private ImageView mRightBlowAuto;
   private ImageView mRightSeatIv;
   private ImageView mSeatBackLeftTv;
   private ImageView mSeatBackRightTv;
   private ImageView mSeatBottomLeftIv;
   private ImageView mSeatBottomRightIv;
   private String[] mSeatColdValueRes;
   private String[] mSeatHeatValueRes;
   private LinearLayout mSwitchFrontRearLl;
   private TempSetView mTempSetViewCenter;
   private TempSetView mTempSetViewLeft;
   private TempSetView mTempSetViewRight;
   private LineBtnView mTopLbv;
   private View mView;
   private SetWindSpeedView mWindSpeedWsv;
   private RearArea set;

   public AirRearView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558751, this);
      this.findViews();
   }

   private void findViews() {
      this.mWindSpeedWsv = (SetWindSpeedView)this.mView.findViewById(2131363481);
      this.mTempSetViewLeft = (TempSetView)this.mView.findViewById(2131363576);
      this.mTempSetViewCenter = (TempSetView)this.mView.findViewById(2131363575);
      this.mTempSetViewRight = (TempSetView)this.mView.findViewById(2131363577);
      this.mHeatLeftShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363450);
      this.mHeatRightShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363451);
      this.mColdLeftShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363445);
      this.mColdRightShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363446);
      this.mTopLbv = (LineBtnView)this.mView.findViewById(2131362806);
      this.mBottomLbv = (LineBtnView)this.mView.findViewById(2131362712);
      this.mBottomLeftLbv = (LineBtnView)this.mView.findViewById(2131362713);
      this.mBottomRightLbv = (LineBtnView)this.mView.findViewById(2131362714);
      this.mBlowWindowsLeftIv = (ImageView)this.mView.findViewById(2131362587);
      this.mBlowHeadLeftIv = (ImageView)this.mView.findViewById(2131362586);
      this.mBlowFootLeftIv = (ImageView)this.mView.findViewById(2131362585);
      this.mSeatBottomLeftIv = (ImageView)this.mView.findViewById(2131362588);
      this.mSeatBackLeftTv = (ImageView)this.mView.findViewById(2131362582);
      this.mLeftBlowAuto = (ImageView)this.mView.findViewById(2131362584);
      this.mBlowWindowsRightIv = (ImageView)this.mView.findViewById(2131362619);
      this.mBlowHeadRightIv = (ImageView)this.mView.findViewById(2131362618);
      this.mBlowFootRightIv = (ImageView)this.mView.findViewById(2131362617);
      this.mSeatBottomRightIv = (ImageView)this.mView.findViewById(2131362620);
      this.mSeatBackRightTv = (ImageView)this.mView.findViewById(2131362615);
      this.mRightBlowAuto = (ImageView)this.mView.findViewById(2131362616);
      this.mSwitchFrontRearLl = (LinearLayout)this.mView.findViewById(2131362804);
      this.mLeftSeatIv = (ImageView)this.mView.findViewById(2131362598);
      this.mRightSeatIv = (ImageView)this.mView.findViewById(2131362628);
   }

   private BtnItemView getBtnItemView(int var1, int var2) {
      BtnItemView var3;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  var3 = null;
               } else {
                  var3 = this.mBottomLbv.getBtnItemView(var2);
               }
            } else {
               var3 = this.mBottomRightLbv.getBtnItemView(var2);
            }
         } else {
            var3 = this.mBottomLeftLbv.getBtnItemView(var2);
         }
      } else {
         var3 = this.mTopLbv.getBtnItemView(var2);
      }

      return var3;
   }

   private void setIvShowImgOrNot(ImageView var1, boolean var2, int var3) {
      if (var2) {
         var1.setVisibility(0);
         var1.setImageResource(var3);
      } else {
         var1.setVisibility(4);
      }

   }

   private void switchBlowAutoIv() {
      ImageView var1 = this.mLeftBlowAuto;
      this.mLeftBlowAuto = this.mRightBlowAuto;
      this.mRightBlowAuto = var1;
   }

   private void switchBlowFootIv() {
      ImageView var1 = this.mBlowFootLeftIv;
      this.mBlowFootLeftIv = this.mBlowFootRightIv;
      this.mBlowFootRightIv = var1;
   }

   private void switchBlowHeadIv() {
      ImageView var1 = this.mBlowHeadLeftIv;
      this.mBlowHeadLeftIv = this.mBlowHeadRightIv;
      this.mBlowHeadRightIv = var1;
   }

   private void switchBlowWindowsIv() {
      ImageView var1 = this.mBlowWindowsLeftIv;
      this.mBlowWindowsLeftIv = this.mBlowWindowsRightIv;
      this.mBlowWindowsRightIv = var1;
   }

   private void switchSeatBackIv() {
      ImageView var1 = this.mSeatBackLeftTv;
      this.mSeatBackLeftTv = this.mSeatBackRightTv;
      this.mSeatBackRightTv = var1;
      Matrix var2 = new Matrix();
      var2.setRotate(90.0F);
      this.mSeatBackLeftTv.setImageMatrix(var2);
      this.mSeatBackRightTv.setImageMatrix(var2);
   }

   private void switchSeatBottomIv() {
      ImageView var1 = this.mSeatBottomLeftIv;
      this.mSeatBottomLeftIv = this.mSeatBottomRightIv;
      this.mSeatBottomRightIv = var1;
   }

   private void switchSeatHeatHotSetView() {
      SeatHeatHotSetView var1 = this.mHeatLeftShhsv;
      this.mHeatLeftShhsv = this.mHeatRightShhsv;
      this.mHeatRightShhsv = var1;
   }

   private void switchSeatHeatHotSetView2() {
      SeatHeatHotSetView var1 = this.mColdLeftShhsv;
      this.mColdLeftShhsv = this.mColdRightShhsv;
      this.mColdRightShhsv = var1;
   }

   private void switchSeatView() {
      ImageView var1 = this.mLeftSeatIv;
      this.mLeftSeatIv = this.mRightSeatIv;
      this.mRightSeatIv = var1;
   }

   private void switchTempSetView() {
      TempSetView var1 = this.mTempSetViewLeft;
      this.mTempSetViewLeft = this.mTempSetViewRight;
      this.mTempSetViewRight = var1;
   }

   protected String getStringByName(String var1) {
      Context var2 = this.mContext;
      return var2.getString(CommUtil.getStrIdByResId(var2, var1));
   }

   public void initViews(AirActivity var1, AirPageUiSet var2) {
      this.mActivity = var1;
      if (var1.isNeedSwitchTemAndSeat()) {
         this.switchTempSetView();
         this.switchSeatHeatHotSetView();
         this.switchSeatHeatHotSetView2();
         this.switchBlowWindowsIv();
         this.switchBlowHeadIv();
         this.switchBlowFootIv();
         this.switchSeatBottomIv();
         this.switchSeatBackIv();
         this.switchSeatView();
         this.switchBlowAutoIv();
      }

      this.set = var2.getRearArea();
      this.mSwitchFrontRearLl.setOnClickListener(this);
      this.mLineBtnAction = this.set.getLineBtnAction();
      OnAirBtnClickListener[] var6 = this.set.getOnAirBtnClickListeners();
      OnAirBtnClickListener[] var5 = var6;
      if (var6 == null) {
         var5 = new OnAirBtnClickListener[]{null, null, null, null};
      }

      boolean var3 = this.set.isAllBtnCanClick();
      String[] var7 = this.set.getDisableBtnS();
      String[][] var4 = this.mLineBtnAction;
      if (var4 != null) {
         if (var4.length > 0) {
            this.mTopLbv.initButton(this.mContext, var4[0], var3, var7, var5[0]);
         }

         var4 = this.mLineBtnAction;
         if (var4.length > 1) {
            this.mBottomLeftLbv.initButton(this.mContext, var4[1], var3, var7, var5[1]);
         }

         var4 = this.mLineBtnAction;
         if (var4.length > 2) {
            this.mBottomRightLbv.initButton(this.mContext, var4[2], var3, var7, var5[2]);
         }

         var4 = this.mLineBtnAction;
         if (var4.length > 3) {
            this.mBottomLbv.initButton(this.mContext, var4[3], var3, var7, var5[3]);
         }
      }

      String var8;
      OnAirSeatHeatColdMinPlusClickListener[] var9;
      if (this.set.isShowSeatHeat()) {
         this.mHeatLeftShhsv.setVisibility(0);
         this.mHeatLeftShhsv.setControllable(this.set.isCanSetSeatHeat());
         this.mHeatRightShhsv.setVisibility(0);
         this.mHeatRightShhsv.setControllable(this.set.isCanSetSeatHeat());
         var8 = this.getStringByName(this.set.getSeatHeatSrnArray()[0]);
         this.mHeatLeftShhsv.setValue(var8);
         this.mHeatRightShhsv.setValue(var8);
         this.mSeatHeatValueRes = this.set.getSeatHeatSrnArray();
         var9 = this.set.getSeatHeatColdClickListeners();
         if (var9 != null) {
            this.mHeatLeftShhsv.setOnUpDownClickListener(var9[0]);
            this.mHeatRightShhsv.setOnUpDownClickListener(var9[1]);
         }
      }

      if (this.set.isShowSeatCold()) {
         this.mColdLeftShhsv.setVisibility(0);
         this.mColdLeftShhsv.setControllable(this.set.isCanSetSeatCold());
         this.mColdRightShhsv.setVisibility(0);
         this.mColdRightShhsv.setControllable(this.set.isCanSetSeatCold());
         var8 = this.getStringByName(this.set.getSeatColdSrnArray()[0]);
         this.mColdLeftShhsv.setValue(var8);
         this.mColdRightShhsv.setValue(var8);
         this.mSeatColdValueRes = this.set.getSeatColdSrnArray();
         var9 = this.set.getSeatHeatColdClickListeners();
         if (var9 != null) {
            this.mColdLeftShhsv.setOnUpDownClickListener(var9[2]);
            this.mColdRightShhsv.setOnUpDownClickListener(var9[3]);
         }
      }

      if (this.set.isCanSetTemp()) {
         OnAirTemperatureUpDownClickListener[] var10 = this.set.getTempSetViewOnUpDownClickListeners();
         if (var10 != null) {
            this.mTempSetViewLeft.setOnUpDownClickListener(var10[0]);
            this.mTempSetViewCenter.setOnUpDownClickListener(var10[1]);
            this.mTempSetViewRight.setOnUpDownClickListener(var10[2]);
         }
      }

      this.mTempSetViewLeft.setControllable(this.set.isCanSetTemp());
      this.mTempSetViewCenter.setControllable(this.set.isCanSetTemp());
      this.mTempSetViewRight.setControllable(this.set.isCanSetTemp());
      this.showOrHide(this.mTempSetViewLeft, this.set.isShowLeftWheel());
      this.showOrHide(this.mTempSetViewCenter, this.set.isShowCenterWheel());
      this.showOrHide(this.mTempSetViewRight, this.set.isShowRightWheel());
      this.mWindSpeedWsv.initViews(this.mContext, this.set.isCanSetWindSpeed(), this.set.getWindMaxLevel(), this.set.getSetWindSpeedViewOnClickListener());
      OnAirSeatClickListener var11 = this.set.getOnAirSeatClickListener();
      this.mOnAirSeatClickListener = var11;
      if (var11 != null) {
         this.mLeftSeatIv.setOnClickListener(new View.OnClickListener(this) {
            final AirRearView this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mOnAirSeatClickListener.onLeftSeatClick();
            }
         });
         this.mRightSeatIv.setOnClickListener(new View.OnClickListener(this) {
            final AirRearView this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(View var1) {
               this.this$0.mOnAirSeatClickListener.onRightSeatClick();
            }
         });
      }

      this.refreshUi();
      if (AirActivity.mIsClickOpen) {
         OnAirPageStatusListener var12 = this.set.getOnAirPageStatusListener();
         this.mOnAirPageStatusListener = var12;
         if (var12 != null) {
            var12.onStatusChange(5);
         }
      }

   }

   public void onClick(View var1) {
      if (var1.getId() == 2131362804) {
         AirActivity var2 = this.mActivity;
         if (var2 == null) {
            return;
         }

         if (var2.isFinishing()) {
            return;
         }

         if (this.mActivity.isDestroyed()) {
            return;
         }

         this.mActivity.switchViewPager(0);
      }

   }

   public void refreshUi() {
      if (this.mView == null) {
         LogUtil.showLog("rear fragment not init");
      } else {
         String[][] var6 = this.mLineBtnAction;
         boolean var5 = false;
         if (var6 != null) {
            for(int var2 = 0; var2 < this.mLineBtnAction.length; ++var2) {
               int var3 = 0;

               while(true) {
                  String[] var7 = this.mLineBtnAction[var2];
                  if (var3 >= var7.length) {
                     break;
                  }

                  String var8 = var7[var3];
                  var8.hashCode();
                  switch (var8) {
                     case "right_set_seat_cold":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.right_set_seat_cold);
                        break;
                     case "right_set_seat_heat":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.right_set_seat_heat);
                        break;
                     case "steering_wheel_heating":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.steering_wheel_heating);
                        break;
                     case "small_wind_light":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.small_wind_light);
                        break;
                     case "auto_manual":
                        this.getBtnItemView(var2, var3).turn(true);
                        if (GeneralAirData.auto_manual) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233972);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131234007);
                        }
                        break;
                     case "auto_2":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto_2);
                        break;
                     case "clean_air":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.clean_air);
                        break;
                     case "ac_auto":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.ac_auto);
                        break;
                     case "rear_auto":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear_auto);
                        break;
                     case "rear_dual":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear_dual);
                        break;
                     case "rear_lock":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear_lock);
                        if (GeneralAirData.rear_lock) {
                           this.getBtnItemView(var2, var3).setImageResource(2131234031);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131234032);
                        }
                        break;
                     case "rear_sync":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear_sync);
                        break;
                     case "rear_power":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear_power);
                        break;
                     case "blow_positive":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.blow_positive);
                        break;
                     case "left_set_seat_cold":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.left_set_seat_cold);
                        break;
                     case "left_set_seat_heat":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.left_set_seat_heat);
                        break;
                     case "blow_negative":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.blow_negative);
                        break;
                     case "seat_steering_wheel_synchronization":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.seat_steering_wheel_synchronization);
                        break;
                     case "max_front":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.max_front);
                        break;
                     case "big_wind_light":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.big_wind_light);
                        break;
                     case "front_window_heat":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.front_window_heat);
                        break;
                     case "auto_cycle":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto_cycle);
                        break;
                     case "auto_defog":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto_defog);
                        break;
                     case "ac":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.ac);
                        break;
                     case "amb":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.amb);
                        break;
                     case "aqs":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.aqs);
                        break;
                     case "eco":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.eco);
                        break;
                     case "ion":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.ion);
                        break;
                     case "auto":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto);
                        break;
                     case "dual":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.dual);
                        break;
                     case "econ":
                        this.getBtnItemView(var2, var3).turn(true);
                        this.getBtnItemView(var2, var3).setImageResource(2131233967);
                        if (GeneralAirData.ac_econ == 0) {
                           this.getBtnItemView(var2, var3).turn(false);
                        } else if (GeneralAirData.ac_econ == 1) {
                           this.getBtnItemView(var2, var3).turn(true);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131233998);
                        }
                        break;
                     case "mono":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.mono);
                        break;
                     case "rear":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear);
                        break;
                     case "sync":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.sync);
                        break;
                     case "sync_temperature":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.sync_temperature);
                        break;
                     case "in_out_auto_cycle":
                        if (GeneralAirData.in_out_auto_cycle == 0) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233992);
                        } else if (GeneralAirData.in_out_auto_cycle == 1) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233991);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131232333);
                        }
                        break;
                     case "max_cool":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.max_cool);
                        break;
                     case "max_heat":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.max_heat);
                        break;
                     case "in_out_cycle":
                        if (GeneralAirData.in_out_cycle) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233992);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131233991);
                        }
                        break;
                     case "climate":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.climate);
                        break;
                     case "auto_wind_mode":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear_auto_wind_model);
                        break;
                     case "auto_wind_light":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto_wind_light);
                        break;
                     case "auto_wind_strong":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto_wind_strong);
                  }

                  ++var3;
               }
            }
         }

         this.mWindSpeedWsv.setAuto(GeneralAirData.rear_auto_wind_speed);
         this.mWindSpeedWsv.setCurWindSpeed(GeneralAirData.rear_wind_level);
         this.mTempSetViewLeft.setValue(GeneralAirData.rear_left_temperature);
         this.mTempSetViewRight.setValue(GeneralAirData.rear_right_temperature);
         this.mTempSetViewCenter.setValue(GeneralAirData.rear_temperature);
         boolean var4;
         ImageView var9;
         if (this.set.isShowSeatHeat()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
               var9 = this.mSeatBackLeftTv;
               if (GeneralAirData.rear_left_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var9, var4, 2131234234);
               var9 = this.mSeatBackRightTv;
               if (GeneralAirData.rear_right_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var9, var4, 2131234233);
            } else {
               var9 = this.mSeatBackLeftTv;
               if (GeneralAirData.rear_left_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var9, var4, 2131234233);
               var9 = this.mSeatBackRightTv;
               if (GeneralAirData.rear_right_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var9, var4, 2131234234);
            }

            var9 = this.mSeatBottomLeftIv;
            if (GeneralAirData.rear_left_seat_heat_level > 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.setIvShowImgOrNot(var9, var4, 2131234232);
            var9 = this.mSeatBottomRightIv;
            if (GeneralAirData.rear_right_seat_heat_level > 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.setIvShowImgOrNot(var9, var4, 2131234232);
            this.mHeatLeftShhsv.setValue(this.getStringByName(this.mSeatHeatValueRes[GeneralAirData.rear_left_seat_heat_level]));
            this.mHeatRightShhsv.setValue(this.getStringByName(this.mSeatHeatValueRes[GeneralAirData.rear_right_seat_heat_level]));
         }

         if (this.set.isShowSeatCold()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
               if (GeneralAirData.rear_left_seat_heat_level <= 0) {
                  var9 = this.mSeatBackLeftTv;
                  if (GeneralAirData.rear_left_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var9, var4, 2131234231);
               }

               if (GeneralAirData.rear_right_seat_heat_level <= 0) {
                  var9 = this.mSeatBackRightTv;
                  if (GeneralAirData.rear_right_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var9, var4, 2131234230);
               }
            } else {
               if (GeneralAirData.rear_left_seat_heat_level <= 0) {
                  var9 = this.mSeatBackLeftTv;
                  if (GeneralAirData.rear_left_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var9, var4, 2131234230);
               }

               if (GeneralAirData.rear_right_seat_heat_level <= 0) {
                  var9 = this.mSeatBackRightTv;
                  if (GeneralAirData.rear_right_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var9, var4, 2131234231);
               }
            }

            if (GeneralAirData.rear_left_seat_heat_level <= 0) {
               var9 = this.mSeatBottomLeftIv;
               if (GeneralAirData.rear_left_seat_cold_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var9, var4, 2131234229);
            }

            if (GeneralAirData.rear_right_seat_heat_level <= 0) {
               var9 = this.mSeatBottomRightIv;
               var4 = var5;
               if (GeneralAirData.rear_right_seat_cold_level > 0) {
                  var4 = true;
               }

               this.setIvShowImgOrNot(var9, var4, 2131234229);
            }

            this.mColdLeftShhsv.setValue(this.getStringByName(this.mSeatColdValueRes[GeneralAirData.rear_left_seat_cold_level]));
            this.mColdRightShhsv.setValue(this.getStringByName(this.mSeatColdValueRes[GeneralAirData.rear_right_seat_cold_level]));
         }

         if (this.mActivity.isNeedSwitchTemAndSeat()) {
            this.setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.rear_left_blow_head, 2131234241);
            this.setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.rear_right_blow_head, 2131234237);
         } else {
            this.setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.rear_left_blow_head, 2131234237);
            this.setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.rear_right_blow_head, 2131234241);
         }

         this.setIvShowImgOrNot(this.mBlowWindowsLeftIv, GeneralAirData.rear_left_blow_window, 2131234235);
         this.setIvShowImgOrNot(this.mBlowFootLeftIv, GeneralAirData.rear_left_blow_foot, 2131234238);
         this.setIvShowImgOrNot(this.mBlowWindowsRightIv, GeneralAirData.rear_right_blow_window, 2131234235);
         this.setIvShowImgOrNot(this.mBlowFootRightIv, GeneralAirData.rear_right_blow_foot, 2131234238);
         this.setIvShowImgOrNot(this.mLeftBlowAuto, GeneralAirData.rear_left_auto_wind, 2131234226);
         this.setIvShowImgOrNot(this.mRightBlowAuto, GeneralAirData.rear_right_auto_wind, 2131234226);
      }
   }

   protected void showOrHide(View var1, boolean var2) {
      if (var2) {
         var1.setVisibility(0);
      } else {
         var1.setVisibility(4);
      }

   }
}
