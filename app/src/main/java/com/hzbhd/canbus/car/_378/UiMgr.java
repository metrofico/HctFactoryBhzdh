package com.hzbhd.canbus.car._378;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;
   private OnAirBtnClickListener mFrontBottombtnclicklistener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     this.this$0.sendAirMessage(6);
                  }
               } else {
                  this.this$0.sendAirMessage(8);
               }
            } else {
               this.this$0.sendAirMessage(10);
            }
         } else {
            this.this$0.sendAirMessage(9);
         }

      }
   };
   private OnAirBtnClickListener mFrontLeftbtnclicklistener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(1);
         }

      }
   };
   private OnAirBtnClickListener mFrontRightbtnclicklistener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(15);
         }

      }
   };
   private OnAirBtnClickListener mFrontTopbtnclicklistener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirMessage(13);
               }
            } else {
               this.this$0.sendAirMessage(7);
            }
         } else {
            this.this$0.sendAirMessage(14);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mTempRightlistener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(12);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(11);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempleftlistener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(3);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(2);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopbtnclicklistener, this.mFrontLeftbtnclicklistener, this.mFrontRightbtnclicklistener, this.mFrontBottombtnclicklistener});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempleftlistener, null, this.mTempRightlistener});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirMessage(5);
         }

         public void onClickRight() {
            this.this$0.sendAirMessage(4);
         }
      });
   }

   private void sendAirMessage(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte)var1});
   }
}
