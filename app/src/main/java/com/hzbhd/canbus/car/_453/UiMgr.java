package com.hzbhd.canbus.car._453;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 13});
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 11});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 12});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
         }

      }
   };
   private int differentId;
   private int eachId;
   OnAirBtnClickListener leftBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 10});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
         }

      }
   };
   OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 2});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
      }
   };
   private Context mContext;
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_453_setting_modular_1")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_1", "_453_park_assist_tone")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_1", "_453_park_assist_delay_timer")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_1", "_453_park_assist")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_453_setting_modular_2")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_2", "_453_vehicle_auto_relock")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_2", "_453_door_unlocking")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_453_setting_modular_3")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_3", "_453_exterior_litghts_approach_lamps")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_3", "_453_mood_lighting_mode")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_3", "_453_mood_lighting_front")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_3", "_453_mood_lighting_rear")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_3", "_453_mood_lighting_color")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_3", "_453_mood_lighting_lumim")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)var3});
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_453_setting_modular_3", "_453_drive_away_locking")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3});
            }
         }

      }
   };
   OnAirBtnClickListener rightBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 7});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
         }

      }
   };
   OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 4});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 3});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
      }
   };
   OnAirBtnClickListener topBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 1) {
            if (var1 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 9});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 8});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 6});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 5});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, 0});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, this.leftBtn, this.rightBtn, this.bottomBtn});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
