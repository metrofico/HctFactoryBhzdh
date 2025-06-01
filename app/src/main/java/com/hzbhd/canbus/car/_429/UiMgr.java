package com.hzbhd.canbus.car._429;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirData0xE1(6);
            }
         } else {
            this.this$0.sendAirData0xE1(13);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirData0xE1(16);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.sendAirData0xE1(14);
            }
         } else {
            this.this$0.sendAirData0xE1(15);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.sendAirData0xE1(1);
         }

      }
   };
   int nowModel = 0;
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.nowModel == 0) {
            this.this$0.sendAirData0xE1(1);
            this.this$0.nowModel = 1;
         } else if (this.this$0.nowModel == 1) {
            this.this$0.sendAirData0xE1(8);
            this.this$0.nowModel = 2;
         } else if (this.this$0.nowModel == 2) {
            this.this$0.sendAirData0xE1(9);
            this.this$0.nowModel = 3;
         } else if (this.this$0.nowModel == 3) {
            this.this$0.sendAirData0xE1(10);
            this.this$0.nowModel = 4;
         } else if (this.this$0.nowModel == 4) {
            this.this$0.sendAirData0xE1(17);
            this.this$0.nowModel = 0;
         }

      }

      public void onRightSeatClick() {
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData0xE1(3);
      }

      public void onClickUp() {
         this.this$0.sendAirData0xE1(2);
      }
   };
   OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirData0xE1(12);
      }

      public void onClickUp() {
         this.this$0.sendAirData0xE1(11);
      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.sendAirData0xE1(5);
      }

      public void onClickRight() {
         this.this$0.sendAirData0xE1(4);
      }
   };

   public UiMgr(Context var1) {
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
   }

   private void sendAirData0xE1(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -45, (byte)var1, 0});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var5 = var7.iterator();

         while(var5.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
   }

   public void sendMediaInfo0xD2(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -46, (byte)var1, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
   }
}
