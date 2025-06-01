package com.hzbhd.canbus.car._396;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.SenAirMsg((byte)3);
               }
            } else {
               this.this$0.SenAirMsg((byte)1);
            }
         } else {
            this.this$0.SenAirMsg((byte)4);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SenAirMsg((byte)5);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SenAirMsg((byte)6);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SenAirMsg((byte)7);
            }
         } else {
            this.this$0.SenAirMsg((byte)2);
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SenAirMsg((byte)14);
      }

      public void onClickUp() {
         this.this$0.SenAirMsg((byte)13);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SenAirMsg((byte)16);
      }

      public void onClickUp() {
         this.this$0.SenAirMsg((byte)15);
      }
   };
   int model = 0;
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.model == 0) {
            this.this$0.SenAirMsg((byte)9);
            this.this$0.model = 1;
         } else if (this.this$0.model == 1) {
            this.this$0.SenAirMsg((byte)10);
            this.this$0.model = 2;
         } else if (this.this$0.model == 2) {
            this.this$0.SenAirMsg((byte)27);
            this.this$0.model = 3;
         } else if (this.this$0.model == 3) {
            this.this$0.SenAirMsg((byte)28);
            this.this$0.model = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.model == 0) {
            this.this$0.SenAirMsg((byte)9);
            this.this$0.model = 1;
         } else if (this.this$0.model == 1) {
            this.this$0.SenAirMsg((byte)10);
            this.this$0.model = 2;
         } else if (this.this$0.model == 2) {
            this.this$0.SenAirMsg((byte)27);
            this.this$0.model = 3;
         } else if (this.this$0.model == 3) {
            this.this$0.SenAirMsg((byte)28);
            this.this$0.model = 0;
         }

      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.SenAirMsg((byte)12);
      }

      public void onClickRight() {
         this.this$0.SenAirMsg((byte)11);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerLeft = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.SenAirMsg((byte)17);
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListenerRight = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         this.this$0.SenAirMsg((byte)18);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, this.mOnUpDownClickListenerRight});
      var2.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      var2.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.onMinPlusClickListenerLeft, this.onMinPlusClickListenerRight});
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_396_air")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_air", "_396_air1")) {
                  this.this$0.sendAirSetting0x3A(1, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_air", "_396_air2")) {
                  this.this$0.sendAirSetting0x3A(4, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_air", "_396_air3")) {
                  this.this$0.sendAirSetting0x3A(10, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_air", "_396_air4")) {
                  this.this$0.sendAirSetting0x3A(16, var3);
               }
            }

            var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_396_setting")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting0")) {
                  this.this$0.sendAirSetting0x6F(3, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting1")) {
                  this.this$0.sendAirSetting0x6F(4, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting2")) {
                  this.this$0.sendAirSetting0x6F(6, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting3")) {
                  this.this$0.sendAirSetting0x6F(8, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting4")) {
                  this.this$0.sendAirSetting0x6F(9, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting5")) {
                  this.this$0.sendAirSetting0x6F(18, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting6")) {
                  this.this$0.sendAirSetting0x6F(20, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting7")) {
                  this.this$0.sendAirSetting0x6F(22, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting8")) {
                  this.this$0.sendAirSetting0x6F(23, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting10")) {
                  this.this$0.sendAirSetting0x6F(32, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting11")) {
                  this.this$0.sendAirSetting0x6F(35, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting12")) {
                  this.this$0.sendAirSetting0x6F(36, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting13")) {
                  this.this$0.sendAirSetting0x6F(37, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting14")) {
                  this.this$0.sendAirSetting0x6F(38, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting15")) {
                  this.this$0.sendAirSetting0x6F(39, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting16")) {
                  this.this$0.sendAirSetting0x6F(40, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting17")) {
                  this.this$0.sendAirSetting0x6F(41, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting18")) {
                  this.this$0.sendAirSetting0x6F(42, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting19")) {
                  this.this$0.sendAirSetting0x6F(58, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting20")) {
                  this.this$0.sendAirSetting0x6F(59, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting21")) {
                  this.this$0.sendAirSetting0x6F(60, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting22")) {
                  this.this$0.sendAirSetting0x6F(61, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting23")) {
                  this.this$0.sendAirSetting0x6F(65, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting24")) {
                  this.this$0.sendAirSetting0x6F(66, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting25")) {
                  this.this$0.sendAirSetting0x6F(67, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting26")) {
                  this.this$0.sendAirSetting0x6F(68, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting28")) {
                  this.this$0.sendAirSetting0x6F(70, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting29")) {
                  this.this$0.sendAirSetting0x6F(81, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting30")) {
                  this.this$0.sendAirSetting0x6F(82, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting31")) {
                  this.this$0.sendAirSetting0x6F(83, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting33")) {
                  this.this$0.sendAirSetting0x6F(84, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting34")) {
                  this.this$0.sendAirSetting0x6F(97, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting35")) {
                  this.this$0.sendAirSetting0x6F(98, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting36")) {
                  this.this$0.sendAirSetting0x6F(99, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting37")) {
                  this.this$0.sendAirSetting0x6F(100, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting38")) {
                  this.this$0.sendAirSetting0x6F(101, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting39")) {
                  this.this$0.sendAirSetting0x6F(102, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting40")) {
                  this.this$0.sendAirSetting0x6F(103, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting41")) {
                  this.this$0.sendAirSetting0x6F(104, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting42")) {
                  this.this$0.sendAirSetting0x6F(105, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting43")) {
                  this.this$0.sendAirSetting0x6F(106, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting44")) {
                  this.this$0.sendAirSetting0x6F(107, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting45")) {
                  this.this$0.sendAirSetting0x6F(108, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting46")) {
                  this.this$0.sendAirSetting0x6F(109, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting47")) {
                  this.this$0.sendAirSetting0x6F(110, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting48")) {
                  this.this$0.sendAirSetting0x6F(111, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting49")) {
                  this.this$0.sendAirSetting0x6F(112, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting50")) {
                  this.this$0.sendAirSetting0x6F(113, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting51")) {
                  this.this$0.sendAirSetting0x6F(114, var3);
               }
            }

            var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_396_panoro")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_panoro", "_396_panoro0")) {
                  if (var3 == 0) {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).hideP360Button();
                  } else {
                     var4 = this.this$0;
                     var4.getMsgMgr(var4.mContext).showP360Button();
                  }

                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_panoro", "_396_panoro1")) {
                  this.this$0.sendPanoro0xF2(11, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_panoro", "_396_panoro2")) {
                  this.this$0.sendPanoro0xF2(12, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_panoro", "_396_panoro3")) {
                  this.this$0.sendPanoro0xF2(18, var3);
               }
            }

            var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_396_setting_language")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting_language", "_396_setting_language")) {
                  this.this$0.sendLanguage0x9A(1, var3 + 1);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }
            }

         }
      });
      var3.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_396_setting")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting9")) {
                  this.this$0.sendAirSetting0x6F(24, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting27")) {
                  this.this$0.sendAirSetting0x6F(69, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_396_setting", "_396_setting52")) {
                  this.this$0.sendAirSetting0x6F(115, var3 * 5);
               }
            }

         }
      });
      var3.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            UiMgr var3 = this.this$0;
            if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_396_setting")) {
               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "_396_setting", "_396_setting53")) {
                  this.this$0.sendAirSetting0x6F(169, 1);
               }

               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "_396_setting", "_396_setting54")) {
                  this.this$0.sendAirSetting0x6F(170, 1);
               }
            }

         }
      });
      this.getParkPageUiSet(this.mContext).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            switch (var1) {
               case 0:
                  this.this$0.sendPanoro0xF2(16, 5);
                  break;
               case 1:
                  this.this$0.sendPanoro0xF2(16, 6);
                  break;
               case 2:
                  this.this$0.sendPanoro0xF2(16, 7);
                  break;
               case 3:
                  this.this$0.sendPanoro0xF2(16, 8);
                  break;
               case 4:
                  this.this$0.sendPanoro0xF2(16, 10);
                  break;
               case 5:
                  this.this$0.sendPanoro0xF2(16, 11);
                  break;
               case 6:
                  this.this$0.sendPanoro0xF2(16, 12);
                  break;
               case 7:
                  this.this$0.sendPanoro0xF2(16, 13);
                  break;
               case 8:
                  this.this$0.sendPanoro0xF2(17, 0);
                  break;
               case 9:
                  this.this$0.sendPanoro0xF2(17, 1);
                  break;
               case 10:
                  this.this$0.sendPanoro0xF2(15, 0);
            }

         }
      });
   }

   private void SenAirMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendAirSetting0x3A(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 58, (byte)var1, (byte)var2});
   }

   private void sendAirSetting0x6F(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, 0, 0});
   }

   private void sendLanguage0x9A(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -102, (byte)var1, (byte)var2});
   }

   public void activeRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -102, 5, 1, (byte)var1});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var5.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var5.get(var4)).getTitleSrn().equals(var2)) {
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
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
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

   public boolean isLandscape() {
      boolean var1;
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void sendCarType0x24(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte)var1, 13});
   }

   public void sendPanoro0xF2(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -14, (byte)var1, (byte)var2});
   }

   public void sendTime0xCB(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
      CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, (byte)var8, (byte)var9, (byte)var10});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
