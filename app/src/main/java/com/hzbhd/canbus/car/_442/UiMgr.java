package com.hzbhd.canbus.car._442;

import android.content.Context;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   OnAirBtnClickListener bottomAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(1, 0, 1, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               }
            } else {
               this.this$0.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(1, 128, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            }
         } else {
            this.this$0.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(9, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   OnAirTemperatureUpDownClickListener centerTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(129, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }

      public void onClickUp() {
         this.this$0.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(65, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }
   };
   OnAirBtnClickListener leftAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 1, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.windModeTag == 0) {
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 1;
         } else if (this.this$0.windModeTag == 1) {
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 2;
         } else if (this.this$0.windModeTag == 2) {
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 3;
         } else if (this.this$0.windModeTag == 3) {
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.windModeTag == 0) {
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 28, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 27, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 1;
         } else if (this.this$0.windModeTag == 1) {
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 4, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 2;
         } else if (this.this$0.windModeTag == 2) {
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 12, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 8, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 3;
         } else if (this.this$0.windModeTag == 3) {
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 20, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 16, 0, 0, 0, 0, 0, 0);
            this.this$0.windModeTag = 0;
         }

      }
   };
   RadarView radarView;
   OnAirBtnClickListener rightAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(1, 64, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   OnAirBtnClickListener topAir = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(1, 2, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
                  this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               }
            } else {
               this.this$0.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(5, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
               this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            }
         } else {
            this.this$0.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(3, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
            this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         }

      }
   };
   private int windModeTag = 0;
   OnAirWindSpeedUpDownClickListener windSpeedControl = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(33, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }

      public void onClickRight() {
         this.this$0.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(17, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
         this.this$0.sendAir(0, 0, 0, 0, 0, 0, 0, 0);
      }
   };

   public UiMgr(Context var1) {
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topAir, this.leftAir, this.rightAir, this.bottomAir});
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.centerTemp, null});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.windSpeedControl);
   }

   public View getCusPanoramicView(Context var1) {
      if (this.radarView == null) {
         this.radarView = new RadarView(var1);
      }

      this.radarView.refreshRadar();
      return this.radarView;
   }

   public void refreshRadar() {
      RadarView var1 = this.radarView;
      if (var1 != null) {
         var1.refreshRadar();
      }

   }

   public void sendAir(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      CanbusMsgSender.sendMsg(new byte[]{22, 0, 0, 3, -108, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8, -64});
   }
}
