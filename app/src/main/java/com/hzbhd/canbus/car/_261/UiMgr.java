package com.hzbhd.canbus.car._261;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
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
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 21, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 21, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 22, 1});
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 22, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 19, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 19, 0});
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 17, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 17, 0});
      }
   };
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 28, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 28, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 29, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 29, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 30, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 30, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 31, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 31, 0});
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 32, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 32, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 33, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 33, 0});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{null, this.mOnAirBottomLeftBtnClickListener, null, this.mOnAirBottomBtnClickListener});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 0});
         }

         public void onRightSeatClick() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 25, 0});
         }
      });
   }
}
