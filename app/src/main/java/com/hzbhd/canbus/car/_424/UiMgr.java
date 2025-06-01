package com.hzbhd.canbus.car._424;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   AirPageUiSet airPageUiSet;
   int i = 26;
   private OnAirBtnClickListener mFrontBottomBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(7);
         }

      }
   };
   private OnAirBtnClickListener mFrontLeftBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(5);
         }

      }
   };
   private OnAirBtnClickListener mFrontRightBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirMessage(6);
         }

      }
   };
   private OnAirBtnClickListener mFrontTopBtnListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAirMessage(2);
               }
            } else {
               this.this$0.sendAirMessage(4);
            }
         } else {
            this.this$0.sendAirMessage(1);
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mLeftTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(14);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(13);
      }
   };
   private OnAirTemperatureUpDownClickListener mRightTempListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirMessage(14);
      }

      public void onClickUp() {
         this.this$0.sendAirMessage(13);
      }
   };

   public UiMgr(Context var1) {
      AirPageUiSet var2 = this.getAirUiSet(var1);
      this.airPageUiSet = var2;
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            UiMgr var1;
            switch (this.this$0.i) {
               case 26:
               case 27:
               case 28:
                  var1 = this.this$0;
                  ++var1.i;
                  break;
               case 29:
                  this.this$0.i = 26;
            }

            var1 = this.this$0;
            var1.sendAirMessage(var1.i);
         }

         public void onRightSeatClick() {
            UiMgr var1;
            switch (this.this$0.i) {
               case 26:
               case 27:
               case 28:
                  var1 = this.this$0;
                  ++var1.i;
                  break;
               case 29:
                  this.this$0.i = 26;
            }

            var1 = this.this$0;
            var1.sendAirMessage(var1.i);
         }
      });
      this.airPageUiSet.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mFrontTopBtnListener, this.mFrontLeftBtnListener, this.mFrontRightBtnListener, this.mFrontBottomBtnListener});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mLeftTempListener, null, this.mRightTempListener});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirMessage(12);
         }

         public void onClickRight() {
            this.this$0.sendAirMessage(11);
         }
      });
      this.airPageUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.ActiveRequest(49);
         }
      });
      this.getTireUiSet(var1).setOnTirePageStatusListener(new OnTirePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.ActiveRequest(72);
         }
      });
   }

   private void ActiveRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
   }

   private void sendAirMessage(int var1) {
      byte var2 = (byte)var1;
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var2, 0});
   }
}
