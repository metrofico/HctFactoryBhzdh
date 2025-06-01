package com.hzbhd.canbus.car._159;

import android.content.Context;
import android.text.TextUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private int mDifferent;
   private com.hzbhd.canbus.car._97.MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(3, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(2, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(0, var1);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.resolveAirBtn(1, var1);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(3);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(2);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommand(12);
      }

      public void onClickUp() {
         this.this$0.sendAirCommand(11);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(5);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(4);
         }
      });
   }

   private void resolveAirBtn(int var1, int var2) {
      String var3 = this.getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[var1][var2];
      if (!TextUtils.isEmpty(var3)) {
         var3.hashCode();
         switch (var3) {
            case "front_defog":
               this.sendAirCommand(1);
               break;
            case "ac":
               this.sendAirCommand(14);
               break;
            case "auto":
               this.sendAirCommand(13);
               break;
            case "mono":
               this.sendAirCommand(15);
               break;
            case "power":
               this.sendAirCommand(7);
               break;
            case "blow_window":
               this.sendAirCommand(10);
               break;
            case "in_out_cycle":
               this.sendAirCommand(6);
               break;
            case "blow_foot":
               this.sendAirCommand(8);
               break;
            case "blow_head":
               this.sendAirCommand(9);
         }

      }
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte)var1, 0});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.mDifferent != 1) {
         this.removeFrontAirButtonByName(var1, "steering_wheel_heating");
      }

   }
}
