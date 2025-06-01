package com.hzbhd.canbus.car._211;

import android.content.Context;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private static boolean isExplorer;
   private AirActivity mActivity;
   private int mBlowMode = 19;
   private Context mContext;
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendData(new byte[]{22, 60, 25, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendData(new byte[]{22, 60, 16, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendData(new byte[]{22, 60, 32, 0});
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.sendData(new byte[]{22, 60, 17, 0});
      }
   };
   private OnAirBtnClickListener mOnAirBottomBtnClickListenerFront = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendData(new byte[]{22, 60, 37, 0});
                  }
               } else {
                  this.this$0.sendData(new byte[]{22, 60, 5, 0});
               }
            } else {
               this.this$0.sendData(new byte[]{22, 60, 14, 0});
            }
         } else {
            this.this$0.sendData(new byte[]{22, 60, 15, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomBtnClickListenerRear = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1 && GeneralAirData.rear) {
               this.this$0.sendData(new byte[]{22, 60, 36, 0});
            }
         } else {
            this.this$0.sendData(new byte[]{22, 60, 35, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListenerFront = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendData(new byte[]{22, 60, 10, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListenerRear = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirBtnClickListener mOnAirBottomRightBtnClickListenerFront = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendData(new byte[]{22, 60, 6, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomRightBtnClickListenerRear = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirBtnClickListener mOnAirTopBtnClickListenerFront = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendData(new byte[]{22, 60, 8, 0});
               }
            } else {
               this.this$0.sendData(new byte[]{22, 60, 9, 0});
            }
         } else {
            this.this$0.sendData(new byte[]{22, 60, 7, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirTopBtnClickListenerRear = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerFrontCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
      }

      public void onClickUp() {
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendData(new byte[]{22, 60, 1, 0});
      }

      public void onClickUp() {
         this.this$0.sendData(new byte[]{22, 60, 2, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendData(new byte[]{22, 60, 3, 0});
      }

      public void onClickUp() {
         this.this$0.sendData(new byte[]{22, 60, 4, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRearCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (GeneralAirData.rear && UiMgr.isExplorer) {
            this.this$0.sendData(new byte[]{22, 60, 33, 0});
         }

      }

      public void onClickUp() {
         if (GeneralAirData.rear && UiMgr.isExplorer) {
            this.this$0.sendData(new byte[]{22, 60, 34, 0});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRearLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
      }

      public void onClickUp() {
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRearRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
      }

      public void onClickUp() {
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListenerFront, this.mOnAirBottomLeftBtnClickListenerFront, this.mOnAirBottomRightBtnClickListenerFront, this.mOnAirBottomBtnClickListenerFront});
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatListener, this.mFrontRightSeatHeatListener, this.mFrontLeftSeatColdListener, this.mFrontRightSeatColdListener});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerFrontLeft, this.mTempSetViewOnUpDownClickListenerFrontCenter, this.mTempSetViewOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendData(new byte[]{22, 60, 13, 0});
         }

         public void onClickRight() {
            this.this$0.sendData(new byte[]{22, 60, 12, 0});
         }
      });
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            UiMgr var1 = this.this$0;
            var1.sendData(new byte[]{22, 60, (byte)UiMgr.access$208(var1), 0});
            if (this.this$0.mBlowMode > 22) {
               this.this$0.mBlowMode = 19;
            }

         }

         public void onRightSeatClick() {
            UiMgr var1 = this.this$0;
            var1.sendData(new byte[]{22, 60, (byte)UiMgr.access$208(var1), 0});
            if (this.this$0.mBlowMode > 22) {
               this.this$0.mBlowMode = 19;
            }

         }
      });
      var2.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListenerRear, this.mOnAirBottomLeftBtnClickListenerRear, this.mOnAirBottomRightBtnClickListenerRear, this.mOnAirBottomBtnClickListenerRear});
      var2.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerRearLeft, this.mTempSetViewOnUpDownClickListenerRearCenter, this.mTempSetViewOnUpDownClickListenerRearRight});
      var2.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            if (UiMgr.isExplorer) {
               if (GeneralAirData.rear) {
                  this.this$0.sendData(new byte[]{22, 60, 24, 0});
               }
            } else {
               this.this$0.sendData(new byte[]{22, 60, 24, 0});
            }

         }

         public void onClickRight() {
            if (UiMgr.isExplorer) {
               if (GeneralAirData.rear) {
                  this.this$0.sendData(new byte[]{22, 60, 23, 0});
               }
            } else {
               this.this$0.sendData(new byte[]{22, 60, 23, 0});
            }

         }
      });
   }

   // $FF: synthetic method
   static int access$208(UiMgr var0) {
      int var1 = var0.mBlowMode++;
      return var1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.getCurrentCarId();
      if (var2 != 0) {
         if (var2 == 1) {
            isExplorer = true;
         }
      } else {
         isExplorer = false;
         this.removeRearAirButton(this.mContext, 3, new int[]{0, 1});
      }

   }
}
