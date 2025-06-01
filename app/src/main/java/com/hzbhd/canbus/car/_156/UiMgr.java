package com.hzbhd.canbus.car._156;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendMsg((byte)34);
               }
            } else {
               this.this$0.sendMsg((byte)19);
            }
         } else {
            this.this$0.sendMsg((byte)20);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendMsg((byte)17);
      }
   };
   private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendMsg((byte)23);
               }
            } else {
               this.this$0.sendMsg((byte)21);
            }
         } else {
            this.this$0.sendMsg((byte)22);
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendMsg((byte)28);
      }

      public void onClickRight() {
         this.this$0.sendMsg((byte)29);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         String var3 = GeneralAirData.center_wheel;
         var3.hashCode();
         int var2 = var3.hashCode();
         byte var1 = -1;
         switch (var2) {
            case -1955878649:
               if (var3.equals("Normal")) {
                  var1 = 0;
               }
               break;
            case 0:
               if (var3.equals("")) {
                  var1 = 1;
               }
               break;
            case 2182268:
               if (var3.equals("Fast")) {
                  var1 = 2;
               }
               break;
            case 2582602:
               if (var3.equals("Soft")) {
                  var1 = 3;
               }
         }

         switch (var1) {
            case 0:
               this.this$0.sendMsg((byte)66);
               break;
            case 1:
               this.this$0.sendMsg((byte)65);
               break;
            case 2:
               this.this$0.sendMsg((byte)64);
               break;
            case 3:
               this.this$0.sendMsg((byte)65);
         }

      }

      public void onClickUp() {
         String var3 = GeneralAirData.center_wheel;
         var3.hashCode();
         int var2 = var3.hashCode();
         byte var1 = -1;
         switch (var2) {
            case -1955878649:
               if (var3.equals("Normal")) {
                  var1 = 0;
               }
               break;
            case 0:
               if (var3.equals("")) {
                  var1 = 1;
               }
               break;
            case 2182268:
               if (var3.equals("Fast")) {
                  var1 = 2;
               }
               break;
            case 2582602:
               if (var3.equals("Soft")) {
                  var1 = 3;
               }
         }

         switch (var1) {
            case 0:
               this.this$0.sendMsg((byte)64);
               break;
            case 1:
               this.this$0.sendMsg((byte)65);
               break;
            case 2:
               this.this$0.sendMsg((byte)65);
               break;
            case 3:
               this.this$0.sendMsg((byte)66);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendMsg((byte)30);
      }

      public void onClickUp() {
         this.this$0.sendMsg((byte)31);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendMsg((byte)32);
      }

      public void onClickUp() {
         this.this$0.sendMsg((byte)33);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, null, this.mOnAirBottomBtnClickListener});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, this.mTempSetViewOnUpDownClickListenerCenter, this.mTempSetViewOnUpDownClickListenerRight});
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.sendMsg((byte)25);
         }

         public void onRightSeatClick() {
            this.this$0.sendMsg((byte)25);
         }
      });
   }

   private void sendMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -126, var1, 0});
   }
}
