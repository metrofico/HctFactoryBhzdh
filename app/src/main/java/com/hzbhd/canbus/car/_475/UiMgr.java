package com.hzbhd.canbus.car._475;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AmplifierActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnAmplifierPositionListener;
import com.hzbhd.canbus.interfaces.OnAmplifierSeekBarListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.AmplifierPageUiSet;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int bass = 0;
   OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var4;
         label61: {
            UiMgr var3 = this.this$0;
            String[][] var5 = var3.getAirUiSet(var3.mContext).getFrontArea().getLineBtnAction();
            byte var2 = 3;
            var6.hashCode();
            switch (var6) {
               case "right_set_seat_cold":
                  var4 = 0;
                  break label61;
               case "right_set_seat_heat":
                  var4 = 1;
                  break label61;
               case "left_set_seat_cold":
                  var4 = 2;
                  break label61;
               case "left_set_seat_heat":
                  var4 = var2;
               case "blow_window":
                  var4 = 4;
                  break label61;
               case "blow_head_foot":
                  var4 = 5;
                  break label61;
               case "blow_foot":
                  var4 = 6;
                  break label61;
               case "blow_head":
                  var4 = 7;
                  break label61;
               case "blow_window_foot":
                  var4 = 8;
                  break label61;
            }

            var4 = -1;
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 21});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 20});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 23});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 22});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 39});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 42});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 16});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 17});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 41});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 15});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 40});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 8:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 18});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
         }

      }
   };
   int data7 = 0;
   int dataFrontRear = 0;
   int dataLeftRight = 0;
   OnAirBtnClickListener leftBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         String var3 = var2.getAirUiSet(var2.mContext).getFrontArea().getLineBtnAction()[1][var1];
         var3.hashCode();
         if (var3.equals("ac_max")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 9});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
         }

      }
   };
   OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 2});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }
   };
   Context mContext;
   private int mDifferent;
   private MsgMgr mMsgMgr;
   int middle = 0;
   private OnAmplifierCreateAndDestroyListener onAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
      }

      public void destroy() {
      }
   };
   private OnAmplifierPositionListener onAmplifierPositionListener = new OnAmplifierPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void position(AmplifierActivity.AmplifierPosition var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierPosition[var1.ordinal()];
         UiMgr var4;
         if (var3 != 1) {
            if (var3 == 2) {
               Log.d("AMPL", "左右=" + var2);
               this.this$0.dataLeftRight = var2;
               var4 = this.this$0;
               var4.sendAmplifierData(var4.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
            }
         } else {
            Log.d("AMPL", "前后=" + var2);
            this.this$0.dataFrontRear = var2;
            var4 = this.this$0;
            var4.sendAmplifierData(var4.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
         }

      }
   };
   private OnAmplifierSeekBarListener onAmplifierSeekBarListener = new OnAmplifierSeekBarListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void progress(AmplifierActivity.AmplifierBand var1, int var2) {
         int var3 = null.$SwitchMap$com$hzbhd$canbus$activity$AmplifierActivity$AmplifierBand[var1.ordinal()];
         UiMgr var4;
         if (var3 != 1) {
            if (var3 != 2) {
               if (var3 != 3) {
                  if (var3 == 4) {
                     Log.d("AMPL", "低音=" + var2);
                     this.this$0.bass = var2;
                     var4 = this.this$0;
                     var4.sendAmplifierData(var4.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
                  }
               } else {
                  Log.d("AMPL", "中音=" + var2);
                  this.this$0.middle = var2;
                  var4 = this.this$0;
                  var4.sendAmplifierData(var4.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
               }
            } else {
               Log.d("AMPL", "高音=" + var2);
               this.this$0.treble = var2;
               var4 = this.this$0;
               var4.sendAmplifierData(var4.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
            }
         } else {
            Log.d("AMPL", "音量=" + var2);
            this.this$0.volume = var2;
            var4 = this.this$0;
            var4.sendAmplifierData(var4.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
         }

      }
   };
   OnAirTemperatureUpDownClickListener rearLeftTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 26});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 25});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }
   };
   OnAirTemperatureUpDownClickListener rearRightTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 28});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 27});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }
   };
   OnAirBtnClickListener rearTopBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var4;
         label56: {
            UiMgr var3 = this.this$0;
            String[][] var5 = var3.getAirUiSet(var3.mContext).getRearArea().getLineBtnAction();
            byte var2 = 0;
            var6.hashCode();
            switch (var6) {
               case "rear_blow_head_foot":
                  var4 = var2;
               case "rear_auto":
                  var4 = 1;
                  break label56;
               case "rear_lock":
                  var4 = 2;
                  break label56;
               case "rear_sync":
                  var4 = 3;
                  break label56;
               case "rear_power":
                  var4 = 4;
                  break label56;
               case "rear_blow_foot":
                  var4 = 5;
                  break label56;
               case "rear_blow_head":
                  var4 = 6;
                  break label56;
               case "rear_ac":
                  var4 = 7;
                  break label56;
            }

            var4 = -1;
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 36});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 34});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 38});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 32});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 31});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 37});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 35});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 33});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener rearWindControl = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 30});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 29});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }
   };
   OnAirBtnClickListener rightBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         String var3 = var2.getAirUiSet(var2.mContext).getFrontArea().getLineBtnAction()[2][var1];
         var3.hashCode();
         if (!var3.equals("steering_wheel_heating")) {
            if (var3.equals("max_front")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 44});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 24});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
         }

      }
   };
   OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 4});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 3});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }
   };
   OnAirBtnClickListener topBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var4;
         label56: {
            UiMgr var3 = this.this$0;
            String[][] var5 = var3.getAirUiSet(var3.mContext).getFrontArea().getLineBtnAction();
            byte var2 = 0;
            var6.hashCode();
            switch (var6) {
               case "front_defog":
                  var4 = var2;
               case "auto_2":
                  var4 = 1;
                  break label56;
               case "rear_defog":
                  var4 = 2;
                  break label56;
               case "ac":
                  var4 = 3;
                  break label56;
               case "auto":
                  var4 = 4;
                  break label56;
               case "sync":
                  var4 = 5;
                  break label56;
               case "power":
                  var4 = 6;
                  break label56;
               case "in_out_cycle":
                  var4 = 7;
                  break label56;
            }

            var4 = -1;
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 13});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 43});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 14});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 10});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 12});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 8});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 7});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 11});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
         }

      }
   };
   int treble = 0;
   int volume = 0;
   OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 6});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 5});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 24, 0});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferent = this.getCurrentCarId();
      AmplifierPageUiSet var2 = this.getAmplifierPageUiSet(var1);
      var2.setOnAmplifierPositionListener(this.onAmplifierPositionListener);
      var2.setOnAmplifierSeekBarListener(this.onAmplifierSeekBarListener);
      var2.setOnAmplifierCreateAndDestroyListener(this.onAmplifierCreateAndDestroyListener);
      AirPageUiSet var4 = this.getAirUiSet(var1);
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, this.leftBtn, this.rightBtn, this.bottomBtn});
      var4.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
      var4.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
      var4.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.rearTopBtn});
      var4.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.rearLeftTemp, null, this.rearRightTemp});
      var4.getRearArea().setSetWindSpeedViewOnClickListener(this.rearWindControl);
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -1, 53});
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -1, 56});
         }
      });
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var3) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -2093370173:
                  if (var4.equals("_475_start_stop_system")) {
                     var5 = 0;
                  }
                  break;
               case -2065696613:
                  if (var4.equals("_118_setting_title_10")) {
                     var5 = 1;
                  }
                  break;
               case -2065696605:
                  if (var4.equals("_118_setting_title_18")) {
                     var5 = 2;
                  }
                  break;
               case -2065696604:
                  if (var4.equals("_118_setting_title_19")) {
                     var5 = 3;
                  }
                  break;
               case -2065696578:
                  if (var4.equals("_118_setting_title_24")) {
                     var5 = 4;
                  }
                  break;
               case -2065696577:
                  if (var4.equals("_118_setting_title_25")) {
                     var5 = 5;
                  }
                  break;
               case -2065696576:
                  if (var4.equals("_118_setting_title_26")) {
                     var5 = 6;
                  }
                  break;
               case -2065696575:
                  if (var4.equals("_118_setting_title_27")) {
                     var5 = 7;
                  }
                  break;
               case -2065696574:
                  if (var4.equals("_118_setting_title_28")) {
                     var5 = 8;
                  }
                  break;
               case -2065696573:
                  if (var4.equals("_118_setting_title_29")) {
                     var5 = 9;
                  }
                  break;
               case -2065696396:
                  if (var4.equals("_118_setting_title_80")) {
                     var5 = 10;
                  }
                  break;
               case -2065696395:
                  if (var4.equals("_118_setting_title_81")) {
                     var5 = 11;
                  }
                  break;
               case -2055183645:
                  if (var4.equals("_2_setting_12_3_2")) {
                     var5 = 12;
                  }
                  break;
               case -2023122657:
                  if (var4.equals("_370_Engine_Off_Light_delay")) {
                     var5 = 13;
                  }
                  break;
               case -1856514051:
                  if (var4.equals("_475_sound_the_horn_lock")) {
                     var5 = 14;
                  }
                  break;
               case -1836538809:
                  if (var4.equals("auto_locking")) {
                     var5 = 15;
                  }
                  break;
               case -1818972390:
                  if (var4.equals("_475_auto_start_headlights_when_unlocking")) {
                     var5 = 16;
                  }
                  break;
               case -1711605204:
                  if (var4.equals("_123_auxiliary_1")) {
                     var5 = 17;
                  }
                  break;
               case -1590596702:
                  if (var4.equals("_250_automatic_folding_mirror")) {
                     var5 = 18;
                  }
                  break;
               case -1535730024:
                  if (var4.equals("_475_sound_the_horn_remote")) {
                     var5 = 19;
                  }
                  break;
               case -1357084752:
                  if (var4.equals("_475_rearview_mirror_reverse_assist_adjustment")) {
                     var5 = 20;
                  }
                  break;
               case -1297749980:
                  if (var4.equals("_475_p_key")) {
                     var5 = 21;
                  }
                  break;
               case -1154303868:
                  if (var4.equals("_475_front_sensor_driving")) {
                     var5 = 22;
                  }
                  break;
               case -1074566328:
                  if (var4.equals("_475_high_beam_assist")) {
                     var5 = 23;
                  }
                  break;
               case -1006589135:
                  if (var4.equals("_94_key_free")) {
                     var5 = 24;
                  }
                  break;
               case -811343875:
                  if (var4.equals("_475_capacity_unit")) {
                     var5 = 25;
                  }
                  break;
               case -599641101:
                  if (var4.equals("ramp_start_assist")) {
                     var5 = 26;
                  }
                  break;
               case -585729983:
                  if (var4.equals("parkingAssist")) {
                     var5 = 27;
                  }
                  break;
               case -566809151:
                  if (var4.equals("_475_auto_unlocking_get_off")) {
                     var5 = 28;
                  }
                  break;
               case -560842866:
                  if (var4.equals("distance_unit")) {
                     var5 = 29;
                  }
                  break;
               case -454334994:
                  if (var4.equals("_123_rear_mirror")) {
                     var5 = 30;
                  }
                  break;
               case -392737071:
                  if (var4.equals("hiworld_jeep_123_0xCA_0X08")) {
                     var5 = 31;
                  }
                  break;
               case -250079212:
                  if (var4.equals("_1193_setting_1_7")) {
                     var5 = 32;
                  }
                  break;
               case -229100667:
                  if (var4.equals("_475_bending_mode")) {
                     var5 = 33;
                  }
                  break;
               case -26602129:
                  if (var4.equals("temperature_unit")) {
                     var5 = 34;
                  }
                  break;
               case -538431:
                  if (var4.equals("_475_display_fuel_efficient_mode")) {
                     var5 = 35;
                  }
                  break;
               case 102584022:
                  if (var4.equals("language_setup")) {
                     var5 = 36;
                  }
                  break;
               case 300606309:
                  if (var4.equals("_373_setting_car_control9")) {
                     var5 = 37;
                  }
                  break;
               case 387914516:
                  if (var4.equals("_118_setting_title_110")) {
                     var5 = 38;
                  }
                  break;
               case 548772759:
                  if (var4.equals("_475_auto_activate")) {
                     var5 = 39;
                  }
                  break;
               case 664425103:
                  if (var4.equals("_475_guidance_line")) {
                     var5 = 40;
                  }
                  break;
               case 709018697:
                  if (var4.equals("_475_intelligent_electric_tailgate")) {
                     var5 = 41;
                  }
                  break;
               case 741052632:
                  if (var4.equals("_475_lights_flashing_when_locked")) {
                     var5 = 42;
                  }
                  break;
               case 828452275:
                  if (var4.equals("_475_lanesense_warning")) {
                     var5 = 43;
                  }
                  break;
               case 909800750:
                  if (var4.equals("_475_press_key_control_unlock")) {
                     var5 = 44;
                  }
                  break;
               case 1170379802:
                  if (var4.equals("hiworld_jeep_123_0x43_data3_0")) {
                     var5 = 45;
                  }
                  break;
               case 1170379803:
                  if (var4.equals("hiworld_jeep_123_0x43_data3_1")) {
                     var5 = 46;
                  }
                  break;
               case 1180290614:
                  if (var4.equals("_118_setting_title_2")) {
                     var5 = 47;
                  }
                  break;
               case 1180290615:
                  if (var4.equals("_118_setting_title_3")) {
                     var5 = 48;
                  }
                  break;
               case 1180290617:
                  if (var4.equals("_118_setting_title_5")) {
                     var5 = 49;
                  }
                  break;
               case 1180290618:
                  if (var4.equals("_118_setting_title_6")) {
                     var5 = 50;
                  }
                  break;
               case 1180290621:
                  if (var4.equals("_118_setting_title_9")) {
                     var5 = 51;
                  }
                  break;
               case 1205441802:
                  if (var4.equals("_475_lanesense_strength")) {
                     var5 = 52;
                  }
                  break;
               case 1511876815:
                  if (var4.equals("_337_amplifier_info2")) {
                     var5 = 53;
                  }
                  break;
               case 1787707114:
                  if (var4.equals("_475_power_unit")) {
                     var5 = 54;
                  }
                  break;
               case 1904746967:
                  if (var4.equals("hiworld_jeep_123_0x60_data1_4")) {
                     var5 = 55;
                  }
                  break;
               case 1962120554:
                  if (var4.equals("_475_blind_spot_alarm")) {
                     var5 = 56;
                  }
                  break;
               case 2076384769:
                  if (var4.equals("_220_daytime_running_lights")) {
                     var5 = 57;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 43, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 18, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 20, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 47, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 48, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 49, (byte)var3});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 50, (byte)var3});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 51, (byte)var3});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 35, (byte)var3});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 54, (byte)var3});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 53, (byte)var3});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 38, (byte)var3});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 22, (byte)var3});
                  break;
               case 14:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 26, (byte)var3});
                  break;
               case 15:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, (byte)var3});
                  break;
               case 16:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
                  break;
               case 17:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 40, (byte)var3});
                  break;
               case 18:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 44, (byte)var3});
                  break;
               case 19:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 27, (byte)var3});
                  break;
               case 20:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
                  break;
               case 21:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 41, (byte)var3});
                  break;
               case 22:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 45, (byte)var3});
                  break;
               case 23:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                  break;
               case 24:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 17, (byte)var3});
                  break;
               case 25:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 55, (byte)var3});
                  break;
               case 26:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                  break;
               case 27:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
                  break;
               case 28:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 15, (byte)var3});
                  break;
               case 29:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
                  break;
               case 30:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 28, (byte)var3});
                  break;
               case 31:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 56, (byte)var3});
                  break;
               case 32:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 39, (byte)var3});
                  break;
               case 33:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
                  break;
               case 34:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 57, (byte)var3});
                  break;
               case 35:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var3});
                  break;
               case 36:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3});
                  break;
               case 37:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 21, (byte)var3});
                  break;
               case 38:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 59, (byte)var3});
                  break;
               case 39:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 19, (byte)var3});
                  break;
               case 40:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 52, (byte)var3});
                  break;
               case 41:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 46, (byte)var3});
                  break;
               case 42:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
                  break;
               case 43:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 31, (byte)var3});
                  break;
               case 44:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 16, (byte)var3});
                  break;
               case 45:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 34, (byte)var3});
                  break;
               case 46:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 33, (byte)var3});
                  break;
               case 47:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 29, (byte)var3});
                  break;
               case 48:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 30, (byte)var3});
                  break;
               case 49:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 37, (byte)var3});
                  break;
               case 50:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
                  break;
               case 51:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                  break;
               case 52:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 32, (byte)var3});
                  break;
               case 53:
                  UiMgr var6 = this.this$0;
                  var6.data7 = var3 << 7 | var6.data7 & -129;
                  var6 = this.this$0;
                  var6.sendAmplifierData(var6.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
                  break;
               case 54:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 58, (byte)var3});
                  break;
               case 55:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 60, (byte)var3});
                  break;
               case 56:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 36, (byte)var3});
                  break;
               case 57:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)var3});
            }

         }
      });
      var3.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var3) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_370_Speed_adjustment_volume")) {
               UiMgr var5 = this.this$0;
               var5.data7 = var5.data7 & -16 | var3 & 15;
               var5 = this.this$0;
               var5.sendAmplifierData(var5.volume, this.this$0.dataFrontRear, this.this$0.dataLeftRight, this.this$0.bass, this.this$0.middle, this.this$0.treble, this.this$0.data7);
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var7 = var5.iterator();

         while(var7.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

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

   public void sendAmplifierData(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.mDifferent;
      if (var2 != 0) {
         if (var2 == 1) {
            this.removeFrontAirButtonByName(var1, "blow_head_foot");
            this.removeFrontAirButtonByName(var1, "blow_window_foot");
         }
      } else {
         this.removeFrontAirButtonByName(var1, "blow_window");
         this.removeFrontAirButtonByName(var1, "auto_2");
      }

   }
}
