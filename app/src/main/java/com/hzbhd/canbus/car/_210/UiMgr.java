package com.hzbhd.canbus.car._210;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private int currentClickFront;
   private int frontRear;
   private int leftRight;
   private OnAmplifierSeekBarListener mAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         Log.d("cwh", "progress = " + var2);
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte)var2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 5, (byte)var2});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 6, (byte)var2});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -83, 4, (byte)var2});
         }

      }
   };
   private Context mContext;
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 14, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 13, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 13, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mFrontTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 16, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 15, 0});
      }
   };
   private MsgMgr mMsgMgr;
   private OnAmplifierPositionListener mOnAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         if (var3 != 1) {
            if (var3 == 2) {
               if (this.this$0.frontRear != var2) {
                  this.this$0.frontRear = var2;
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 3, (byte)(var2 + 7)});
               }

               Log.d("cwh", "value  = " + var2);
               Log.d("cwh", "frontRear  = " + this.this$0.frontRear);
            }
         } else {
            if (this.this$0.leftRight != var2) {
               this.this$0.leftRight = var2;
               CanbusMsgSender.sendMsg(new byte[]{22, -83, 2, (byte)(var2 + 7)});
            }

            Log.d("cwh", "value  = " + var2);
            Log.d("cwh", "leftRight  = " + this.this$0.leftRight);
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 2, 0});
         } else if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 23, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 24, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 1, 0});
         }

      }
   };
   private OnAirSeatClickListener mOnFrontAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         int var1 = this.this$0.currentClickFront;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.currentClickFront = 0;
                  this.this$0.sendAirClick(10);
               }
            } else {
               this.this$0.currentClickFront = 2;
               this.this$0.sendAirClick(9);
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(8);
         }

      }

      public void onRightSeatClick() {
         int var1 = this.this$0.currentClickFront;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.currentClickFront = 0;
                  this.this$0.sendAirClick(10);
               }
            } else {
               this.this$0.currentClickFront = 2;
               this.this$0.sendAirClick(9);
            }
         } else {
            this.this$0.currentClickFront = 1;
            this.this$0.sendAirClick(8);
         }

      }
   };
   private OnAirBtnClickListener mOnFrontAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 1});
                           CanbusMsgSender.sendMsg(new byte[]{22, 61, 3, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 1});
                        CanbusMsgSender.sendMsg(new byte[]{22, 61, 44, 0});
                     }
                  } else if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
                  }
               } else if (((CanSettingProxy)Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 18, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 17, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 4, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 26, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnFrontOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 1});
                     CanbusMsgSender.sendMsg(new byte[]{22, 61, 45, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, 61, 7, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, 61, 6, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 5, 0});
         }

      }
   };
   private OnSettingItemSelectListener mOnItemSelectedListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 != 3) {
                     if (var1 != 4) {
                        if (var1 == 5) {
                           if (var2 == 0) {
                              CanbusMsgSender.sendMsg(new byte[]{22, -83, 7, (byte)var3});
                           } else {
                              CanbusMsgSender.sendMsg(new byte[]{22, -83, 8, (byte)var3});
                           }
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 111, 3, (byte)var3, 32, 0});
                     }
                  } else if (var2 == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -14, 6, (byte)var3});
                  } else {
                     SharePreUtil.setIntValue(this.this$0.mContext, "_210_park_assess_item", var3);
                     this.this$0.mMsgMgr.updateOrignalSetting();
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 108, 5, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, 109, 4, (byte)var3});
            }
         } else if (var3 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, 27});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var3 + 1)});
         }

      }
   };
   private OnPanoramicItemClickListener mOnPanoramicItemClickListener = new OnPanoramicItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               if (MsgMgr.overlook) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -14, 9, 1});
               }
            }
         } else if (MsgMgr.angleWide) {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -14, 8, 1});
         }

      }
   };
   private OnAirBtnClickListener mOnRearAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 34, 0});
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, 61, 46, 0});
         }

      }
   };
   private int mParkAccessInt;
   private OnAirTemperatureUpDownClickListener mRearTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 33, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 32, 0});
      }
   };
   private OnAirWindSpeedUpDownClickListener mSetFrontWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 12, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 12, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 11, 0});
      }
   };
   private OnAirWindSpeedUpDownClickListener mSetRearWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 43, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, 61, 42, 0});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnItemSelectedListener);
      ParkPageUiSet var3 = this.getParkPageUiSet(var1);
      var3.setOnPanoramicItemClickListener(this.mOnPanoramicItemClickListener);
      var3.setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var3) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void addViewToWindows() {
            ParkPageUiSet var2 = this.val$parkPageUiSet;
            Context var3 = this.this$0.mContext;
            boolean var1 = false;
            if (SharePreUtil.getIntValue(var3, "_210_park_assess_item", 0) == 1) {
               var1 = true;
            }

            var2.setShowRadar(var1);
         }
      });
      AmplifierPageUiSet var5 = this.getAmplifierPageUiSet(var1);
      var5.setOnAmplifierSeekBarListener(this.mAmplifierSeekBarListener);
      var5.setOnAmplifierPositionListener(this.mOnAmplifierPositionListener);
      AirPageUiSet var4 = this.getAirUiSet(var1);
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnFrontAirTopBtnClickListener, this.mOnFrontAirBottomLeftBtnClickListener, this.mOnFrontAirBottomRightBtnClickListener, this.mOnFrontOnAirBottomBtnClickListener});
      var4.getFrontArea().setOnAirSeatClickListener(this.mOnFrontAirSeatClickListener);
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mFrontTempSetViewOnUpDownClickListenerLeft, null, this.mFrontTempSetViewOnUpDownClickListenerRight});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetFrontWindSpeedViewOnClickListener);
      var4.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
      var4.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mRearTempSetViewOnUpDownClickListenerCenter, null});
      var4.getRearArea().setSetWindSpeedViewOnClickListener(this.mSetRearWindSpeedViewOnClickListener);
      var4.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, null, null, this.mOnRearAirBottomBtnClickListener});
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 5) {
               if (var2 == 2) {
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 10, 1});
               } else {
                  Toast.makeText(this.this$0.mContext, this.this$0.mContext.getText(2131769831), 0).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, -83, 11, 1});
               }
            }

         }
      });
   }

   private void sendAirClick(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, (byte)var1, 1});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
