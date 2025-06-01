package com.hzbhd.canbus.view;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTempTouchListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.LogUtil;
import java.util.Locale;

public class AirFrontView extends RelativeLayout implements View.OnClickListener {
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
   private String mLanguage;
   private ImageView mLeftBlowAuto;
   private ImageView mLeftSeatIv;
   private SetWindSpeedView mLeftWindSpeedWsv;
   private String[][] mLineBtnAction;
   private LinearLayout mLlSeatCold;
   private LinearLayout mLlSeatHeat;
   private Locale mLocale;
   private OnAirPageStatusListener mOnAirPageStatusListener;
   private OnAirSeatClickListener mOnAirSeatClickListener;
   private TextView mPmInValueTv;
   private LinearLayout mPmLayoutLl;
   private TextView mPmOutValueTv;
   private ImageView mRightBlowAuto;
   private ImageView mRightSeatIv;
   private SetWindSpeedView mRightWindSpeedWsv;
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
   private FrontArea set;

   public AirFrontView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.mContext = var1;
      this.mView = LayoutInflater.from(var1).inflate(2131558748, this);
      this.findViews();
   }

   private void findViews() {
      Locale var1 = this.mContext.getResources().getConfiguration().locale;
      this.mLocale = var1;
      this.mLanguage = var1.getLanguage();
      this.mPmLayoutLl = (LinearLayout)this.mView.findViewById(2131362793);
      this.mPmInValueTv = (TextView)this.mView.findViewById(2131363671);
      this.mPmOutValueTv = (TextView)this.mView.findViewById(2131363672);
      this.mLeftWindSpeedWsv = (SetWindSpeedView)this.mView.findViewById(2131363481);
      this.mRightWindSpeedWsv = (SetWindSpeedView)this.mView.findViewById(2131363482);
      this.mTempSetViewLeft = (TempSetView)this.mView.findViewById(2131363576);
      this.mTempSetViewCenter = (TempSetView)this.mView.findViewById(2131363575);
      this.mTempSetViewRight = (TempSetView)this.mView.findViewById(2131363577);
      this.mLlSeatHeat = (LinearLayout)this.mView.findViewById(2131362803);
      this.mLlSeatCold = (LinearLayout)this.mView.findViewById(2131362802);
      this.mHeatLeftShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363450);
      this.mHeatRightShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363451);
      this.mColdLeftShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363445);
      this.mColdRightShhsv = (SeatHeatHotSetView)this.mView.findViewById(2131363446);
      this.mTopLbv = (LineBtnView)this.mView.findViewById(2131362806);
      this.mBottomLbv = (LineBtnView)this.mView.findViewById(2131362712);
      this.mBottomLeftLbv = (LineBtnView)this.mView.findViewById(2131362713);
      this.mBottomRightLbv = (LineBtnView)this.mView.findViewById(2131362714);
      this.mLeftSeatIv = (ImageView)this.mView.findViewById(2131362598);
      this.mBlowWindowsLeftIv = (ImageView)this.mView.findViewById(2131362587);
      this.mBlowHeadLeftIv = (ImageView)this.mView.findViewById(2131362586);
      this.mBlowFootLeftIv = (ImageView)this.mView.findViewById(2131362585);
      this.mLeftBlowAuto = (ImageView)this.mView.findViewById(2131362584);
      this.mSeatBottomLeftIv = (ImageView)this.mView.findViewById(2131362588);
      this.mSeatBackLeftTv = (ImageView)this.mView.findViewById(2131362582);
      this.mRightSeatIv = (ImageView)this.mView.findViewById(2131362628);
      this.mBlowWindowsRightIv = (ImageView)this.mView.findViewById(2131362619);
      this.mBlowHeadRightIv = (ImageView)this.mView.findViewById(2131362618);
      this.mBlowFootRightIv = (ImageView)this.mView.findViewById(2131362617);
      this.mRightBlowAuto = (ImageView)this.mView.findViewById(2131362616);
      this.mSeatBottomRightIv = (ImageView)this.mView.findViewById(2131362620);
      this.mSeatBackRightTv = (ImageView)this.mView.findViewById(2131362615);
      this.mSwitchFrontRearLl = (LinearLayout)this.mView.findViewById(2131362804);
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

   private void switchWindLevelSetView(AirPageUiSet var1) {
      if (var1.getFrontArea().getIsHaveLeftRightWindSpeed()) {
         SetWindSpeedView var2 = this.mLeftWindSpeedWsv;
         this.mLeftWindSpeedWsv = this.mRightWindSpeedWsv;
         this.mRightWindSpeedWsv = var2;
      }

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
         this.switchSeatView();
         this.switchBlowWindowsIv();
         this.switchBlowHeadIv();
         this.switchBlowFootIv();
         this.switchSeatBottomIv();
         this.switchSeatBackIv();
         this.switchBlowAutoIv();
         this.switchWindLevelSetView(var2);
      }

      if (var2 != null) {
         LogUtil.showLog("holdSet.isHaveRearArea():" + var2.isHaveRearArea());
         if (var2.isHaveRearArea()) {
            this.mSwitchFrontRearLl.setVisibility(0);
            this.mSwitchFrontRearLl.setOnClickListener(this);
         } else {
            this.mSwitchFrontRearLl.setVisibility(8);
         }

         FrontArea var9 = var2.getFrontArea();
         this.set = var9;
         if (var9 != null) {
            if (var9.isShowPmValue()) {
               this.mPmLayoutLl.setVisibility(0);
            }

            this.mLineBtnAction = this.set.getLineBtnAction();
            OnAirBtnClickListener[] var4 = this.set.getOnAirBtnClickListeners();
            OnAirBtnClickListener[] var10 = var4;
            if (var4 == null) {
               var10 = new OnAirBtnClickListener[]{null, null, null, null};
            }

            boolean var3 = this.set.isAllBtnCanClick();
            String[] var13 = this.set.getDisableBtnArray();
            String[][] var5 = this.mLineBtnAction;
            if (var5 != null) {
               if (var5.length > 0) {
                  this.mTopLbv.initButton(this.mContext, var5[0], var3, var13, var10[0]);
               }

               var5 = this.mLineBtnAction;
               if (var5.length > 1) {
                  this.mBottomLeftLbv.initButton(this.mContext, var5[1], var3, var13, var10[1]);
               }

               var5 = this.mLineBtnAction;
               if (var5.length > 2) {
                  this.mBottomRightLbv.initButton(this.mContext, var5[2], var3, var13, var10[2]);
               }

               var5 = this.mLineBtnAction;
               if (var5.length > 3) {
                  this.mBottomLbv.initButton(this.mContext, var5[3], var3, var13, var10[3]);
               }
            }

            if (this.set.isShowSeatHeat()) {
               this.mHeatLeftShhsv.setVisibility(0);
               this.mHeatLeftShhsv.setControllable(this.set.isCanSetSeatHeat());
               this.mHeatRightShhsv.setVisibility(0);
               this.mHeatRightShhsv.setControllable(this.set.isCanSetSeatHeat());
               String var14 = this.getStringByName(this.set.getSeatHeatSrnArray()[0]);
               this.mHeatLeftShhsv.setValue(var14);
               this.mHeatRightShhsv.setValue(var14);
               this.mSeatHeatValueRes = this.set.getSeatHeatSrnArray();
               OnAirSeatHeatColdMinPlusClickListener[] var15 = this.set.getSeatHeatColdClickListeners();
               if (var15 != null) {
                  this.mHeatLeftShhsv.setOnUpDownClickListener(var15[0]);
                  this.mHeatRightShhsv.setOnUpDownClickListener(var15[1]);
               }
            } else if (var1.getResources().getConfiguration().orientation == 1) {
               this.mLlSeatHeat.setVisibility(8);
            }

            if (this.set.isShowSeatCold()) {
               this.mColdLeftShhsv.setVisibility(0);
               this.mColdRightShhsv.setVisibility(0);
               this.mColdLeftShhsv.setControllable(this.set.isCanSetSeatCold());
               this.mColdRightShhsv.setControllable(this.set.isCanSetSeatCold());
               String var6 = this.getStringByName(this.set.getSeatColdSrnArray()[0]);
               this.mColdLeftShhsv.setValue(var6);
               this.mColdRightShhsv.setValue(var6);
               this.mSeatColdValueRes = this.set.getSeatColdSrnArray();
               OnAirSeatHeatColdMinPlusClickListener[] var7 = this.set.getSeatHeatColdClickListeners();
               if (var7 != null) {
                  this.mColdLeftShhsv.setOnUpDownClickListener(var7[2]);
                  this.mColdRightShhsv.setOnUpDownClickListener(var7[3]);
               }
            } else if (var1.getResources().getConfiguration().orientation == 1) {
               this.mLlSeatCold.setVisibility(8);
            }

            OnAirTempTouchListener[] var8;
            OnAirTemperatureUpDownClickListener[] var16;
            if (this.set.isCanSetLeftTemp()) {
               var16 = this.set.getTempSetViewOnUpDownClickListeners();
               var8 = this.set.getTempTouchListeners();
               if (var16 != null) {
                  this.mTempSetViewLeft.setOnUpDownClickListener(var16[0]);
               }

               if (var8 != null) {
                  this.mTempSetViewLeft.setOnUpDownTouchListener(var8[0]);
               }
            }

            if (this.set.isCanSetCenterTemp()) {
               var16 = this.set.getTempSetViewOnUpDownClickListeners();
               var8 = this.set.getTempTouchListeners();
               if (var16 != null) {
                  this.mTempSetViewCenter.setOnUpDownClickListener(var16[1]);
               }

               if (var8 != null) {
                  this.mTempSetViewCenter.setOnUpDownTouchListener(var8[1]);
               }
            }

            if (this.set.isCanSetRightTemp()) {
               var16 = this.set.getTempSetViewOnUpDownClickListeners();
               var8 = this.set.getTempTouchListeners();
               if (var16 != null) {
                  this.mTempSetViewRight.setOnUpDownClickListener(var16[2]);
               }

               if (var8 != null) {
                  this.mTempSetViewRight.setOnUpDownTouchListener(var8[2]);
               }
            }

            this.mTempSetViewLeft.setControllable(this.set.isCanSetLeftTemp());
            this.mTempSetViewCenter.setControllable(this.set.isCanSetCenterTemp());
            this.mTempSetViewRight.setControllable(this.set.isCanSetRightTemp());
            this.showOrHide(this.mTempSetViewLeft, this.set.isShowLeftWheel());
            this.showOrHide(this.mTempSetViewCenter, this.set.isShowCenterWheel());
            this.showOrHide(this.mTempSetViewRight, this.set.isShowRightWheel());
            this.mLeftWindSpeedWsv.initViews(this.mContext, this.set.isCanSetWindSpeed(), this.set.getWindMaxLevel(), this.set.getSetWindSpeedViewOnClickListener());
            if (this.set.getIsHaveLeftRightWindSpeed()) {
               if (this.mActivity.isNeedSwitchTemAndSeat()) {
                  this.mLeftWindSpeedWsv.setVisibility(0);
               } else {
                  this.mRightWindSpeedWsv.setVisibility(0);
               }

               this.mRightWindSpeedWsv.initViews(this.mContext, this.set.isCanSetWindSpeed(), this.set.getWindMaxLevel(), this.set.getSetRightWindSpeedViewOnClickListener());
            }

            OnAirSeatClickListener var11 = this.set.getOnAirSeatClickListener();
            this.mOnAirSeatClickListener = var11;
            if (var11 != null) {
               this.mLeftSeatIv.setOnClickListener(new View.OnClickListener(this) {
                  final AirFrontView this$0;

                  {
                     this.this$0 = var1;
                  }

                  public void onClick(View var1) {
                     this.this$0.mOnAirSeatClickListener.onLeftSeatClick();
                  }
               });
               this.mRightSeatIv.setOnClickListener(new View.OnClickListener(this) {
                  final AirFrontView this$0;

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
                  var12.onStatusChange(1);
               }
            }

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

         this.mActivity.switchViewPager(1);
      }

   }

   public void refreshUi() {
      if (this.mView == null) {
         LogUtil.showLog("front fragment not init");
      } else {
         String[][] var6 = this.mLineBtnAction;
         boolean var5 = false;
         boolean var4;
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
                  BtnItemView var9;
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
                     case "pollrn_removal":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.pollrn_removal);
                        break;
                     case "small_wind_light":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.small_wind_light);
                        break;
                     case "windshield_deicing":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.windshield_deicing);
                        break;
                     case "front_defog":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.front_defog);
                        break;
                     case "auto_manual":
                        this.getBtnItemView(var2, var3).turn(true);
                        if (GeneralAirData.auto_manual) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233972);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131234007);
                        }
                        break;
                     case "ac_max":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.ac_max);
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
                     case "ac_econ":
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
                     case "manual":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.manual);
                        break;
                     case "three_zone":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.threeZone);
                        break;
                     case "normal":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.normal);
                        break;
                     case "auto_wind_lv":
                        this.getBtnItemView(var2, var3).turn(true);
                        if (this.mLanguage.contains("zh")) {
                           if (GeneralAirData.auto_wind_lv == 0) {
                              this.getBtnItemView(var2, var3).setImageResource(2131234034);
                           } else if (GeneralAirData.auto_wind_lv == 1) {
                              this.getBtnItemView(var2, var3).setImageResource(2131234058);
                           } else {
                              this.getBtnItemView(var2, var3).setImageResource(2131234029);
                           }
                        } else if (GeneralAirData.auto_wind_lv == 0) {
                           this.getBtnItemView(var2, var3).setImageResource(2131234056);
                        } else if (GeneralAirData.auto_wind_lv == 1) {
                           this.getBtnItemView(var2, var3).setImageResource(2131234054);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131234055);
                        }
                        break;
                     case "rear_lock":
                        this.getBtnItemView(var2, var3).turn(true);
                        if (GeneralAirData.rear_lock) {
                           this.getBtnItemView(var2, var3).setImageResource(2131234031);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131234032);
                        }
                        break;
                     case "rear_defog":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear_defog);
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
                     case "left_seat_heat":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.is_left_seat_heat);
                        break;
                     case "auto":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto);
                        break;
                     case "dual":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.dual);
                        break;
                     case "econ":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.econ);
                        break;
                     case "fast":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.fast);
                        break;
                     case "mono":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.mono);
                        break;
                     case "rear":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rear);
                        break;
                     case "rest":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.rest);
                        break;
                     case "soft":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.soft);
                        break;
                     case "sync":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.sync);
                        break;
                     case "sync_temperature":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.sync_temperature);
                        break;
                     case "in_out_auto_cycle":
                        var9 = this.getBtnItemView(var2, var3);
                        if (GeneralAirData.in_out_auto_cycle != 0) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var9.turn(var4);
                        if (GeneralAirData.in_out_auto_cycle == 0) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233992);
                        } else if (GeneralAirData.in_out_auto_cycle == 1) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233991);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131232333);
                        }
                        break;
                     case "power":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.power);
                        break;
                     case "swing":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.swing);
                        break;
                     case "cycle_in_out_close":
                        this.getBtnItemView(var2, var3).turn(true);
                        if (GeneralAirData.cycle_in_out_close == 0) {
                           this.getBtnItemView(var2, var3).turn(false);
                        } else if (GeneralAirData.cycle_in_out_close == 1) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233992);
                        } else {
                           this.getBtnItemView(var2, var3).setImageResource(2131233991);
                        }
                        break;
                     case "negative_ion":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.negative_ion);
                        break;
                     case "max_cool":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.max_cool);
                        break;
                     case "max_heat":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.max_heat);
                        break;
                     case "in_out_cycle":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.in_out_cycle ^ true);
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
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.front_auto_wind_model);
                        break;
                     case "auto_wind_light":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto_wind_light);
                        break;
                     case "auto_wind_strong":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.auto_wind_strong);
                        break;
                     case "right_seat_heat":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.is_right_seat_heat);
                        break;
                     case "auto_1_2":
                        var9 = this.getBtnItemView(var2, var3);
                        if (GeneralAirData.auto_1_2 != 0) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        var9.turn(var4);
                        if (GeneralAirData.auto_1_2 != 0 && GeneralAirData.auto_1_2 != 1) {
                           this.getBtnItemView(var2, var3).setImageResource(2131233971);
                           break;
                        }

                        this.getBtnItemView(var2, var3).setImageResource(2131233972);
                        break;
                     case "hybrid_ac":
                        this.getBtnItemView(var2, var3).turn(GeneralAirData.hybrid_ac);
                  }

                  ++var3;
               }
            }
         }

         if (this.set.isShowPmValue()) {
            this.mPmInValueTv.setText(GeneralAirData.pm_value_level_in_car);
            this.mPmOutValueTv.setText(GeneralAirData.pm_value_level_out_car);
         }

         this.mLeftWindSpeedWsv.setCurWindSpeed(GeneralAirData.front_wind_level);
         this.mLeftWindSpeedWsv.setAuto(GeneralAirData.front_auto_wind_speed);
         if (this.set.getIsHaveLeftRightWindSpeed()) {
            this.mRightWindSpeedWsv.setCurWindSpeed(GeneralAirData.front_right_wind_level);
            this.mRightWindSpeedWsv.setAuto(GeneralAirData.front_right_auto_wind_speed);
         }

         this.mTempSetViewLeft.setValue(GeneralAirData.front_left_temperature);
         this.mTempSetViewRight.setValue(GeneralAirData.front_right_temperature);
         this.mTempSetViewCenter.setValue(GeneralAirData.center_wheel);
         ImageView var10;
         if (this.set.isShowSeatHeat()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
               var10 = this.mSeatBackLeftTv;
               if (GeneralAirData.front_left_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var10, var4, 2131234234);
               var10 = this.mSeatBackRightTv;
               if (GeneralAirData.front_right_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var10, var4, 2131234233);
            } else {
               var10 = this.mSeatBackLeftTv;
               if (GeneralAirData.front_left_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var10, var4, 2131234233);
               var10 = this.mSeatBackRightTv;
               if (GeneralAirData.front_right_seat_heat_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var10, var4, 2131234234);
            }

            var10 = this.mSeatBottomLeftIv;
            if (GeneralAirData.front_left_seat_heat_level > 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.setIvShowImgOrNot(var10, var4, 2131234232);
            var10 = this.mSeatBottomRightIv;
            if (GeneralAirData.front_right_seat_heat_level > 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.setIvShowImgOrNot(var10, var4, 2131234232);
            this.mHeatLeftShhsv.setValue(this.getStringByName(this.mSeatHeatValueRes[GeneralAirData.front_left_seat_heat_level]));
            this.mHeatRightShhsv.setValue(this.getStringByName(this.mSeatHeatValueRes[GeneralAirData.front_right_seat_heat_level]));
         }

         if (this.set.isShowSeatCold()) {
            if (this.mActivity.isNeedSwitchTemAndSeat()) {
               if (GeneralAirData.front_left_seat_heat_level <= 0) {
                  var10 = this.mSeatBackLeftTv;
                  if (GeneralAirData.front_left_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var10, var4, 2131234231);
               }

               if (GeneralAirData.front_right_seat_heat_level <= 0) {
                  var10 = this.mSeatBackRightTv;
                  if (GeneralAirData.front_right_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var10, var4, 2131234230);
               }
            } else {
               if (GeneralAirData.front_left_seat_heat_level <= 0) {
                  var10 = this.mSeatBackLeftTv;
                  if (GeneralAirData.front_left_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var10, var4, 2131234230);
               }

               if (GeneralAirData.front_right_seat_heat_level <= 0) {
                  var10 = this.mSeatBackRightTv;
                  if (GeneralAirData.front_right_seat_cold_level > 0) {
                     var4 = true;
                  } else {
                     var4 = false;
                  }

                  this.setIvShowImgOrNot(var10, var4, 2131234231);
               }
            }

            if (GeneralAirData.front_left_seat_heat_level <= 0) {
               var10 = this.mSeatBottomLeftIv;
               if (GeneralAirData.front_left_seat_cold_level > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               this.setIvShowImgOrNot(var10, var4, 2131234229);
            }

            if (GeneralAirData.front_right_seat_heat_level <= 0) {
               var10 = this.mSeatBottomRightIv;
               var4 = var5;
               if (GeneralAirData.front_right_seat_cold_level > 0) {
                  var4 = true;
               }

               this.setIvShowImgOrNot(var10, var4, 2131234229);
            }

            this.mColdLeftShhsv.setValue(this.getStringByName(this.mSeatColdValueRes[GeneralAirData.front_left_seat_cold_level]));
            this.mColdRightShhsv.setValue(this.getStringByName(this.mSeatColdValueRes[GeneralAirData.front_right_seat_cold_level]));
         }

         if (this.mActivity.isNeedSwitchTemAndSeat()) {
            this.setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.front_left_blow_head, 2131234241);
            this.setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.front_right_blow_head, 2131234237);
         } else {
            this.setIvShowImgOrNot(this.mBlowHeadLeftIv, GeneralAirData.front_left_blow_head, 2131234237);
            this.setIvShowImgOrNot(this.mBlowHeadRightIv, GeneralAirData.front_right_blow_head, 2131234241);
         }

         this.setIvShowImgOrNot(this.mBlowWindowsLeftIv, GeneralAirData.front_left_blow_window, 2131234235);
         this.setIvShowImgOrNot(this.mBlowFootLeftIv, GeneralAirData.front_left_blow_foot, 2131234238);
         this.setIvShowImgOrNot(this.mBlowWindowsRightIv, GeneralAirData.front_right_blow_window, 2131234235);
         this.setIvShowImgOrNot(this.mBlowFootRightIv, GeneralAirData.front_right_blow_foot, 2131234238);
         this.setIvShowImgOrNot(this.mLeftBlowAuto, GeneralAirData.front_left_auto_wind, 2131234226);
         this.setIvShowImgOrNot(this.mRightBlowAuto, GeneralAirData.front_right_auto_wind, 2131234226);
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
