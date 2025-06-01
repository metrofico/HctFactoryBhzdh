package com.hzbhd.canbus.car._61;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   public static final String KEY_LANGUAGE_POS_TAG = "key.laguage.pos.tag";
   private final int MALIBU_MAX_WIND_SPEED = 8;
   private final int MSG_AIR_KEY_UP = 0;
   private Context mContext;
   private int mDifferentId;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 0});
         }

      }
   };
   private MsgMgr mMsgMgr;
   private TimerUtil mTimerUtil;
   private byte[] mTrackRequestCommand;
   private TimerTask mTrackRequestTimerTask;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mDifferentId = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemClickListener(new UiMgr$$ExternalSyntheticLambda0(var2));
      var2.setOnSettingItemSeekbarSelectListener(new UiMgr$$ExternalSyntheticLambda3(var2));
      var2.setOnSettingConfirmDialogListener(new UiMgr$$ExternalSyntheticLambda4(var2));
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda5(this, var2));
      this.getParkPageUiSet(var1).setOnBackCameraStatusListener(new UiMgr$$ExternalSyntheticLambda6());
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda7(this, var3), new UiMgr$$ExternalSyntheticLambda8(this, var3), new UiMgr$$ExternalSyntheticLambda9(this, var3), new UiMgr$$ExternalSyntheticLambda10(this, var3)});
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(5);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(4);
         }
      }, null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(21);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(20);
         }
      }});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(7);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(6);
         }
      });
      var3.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new UiMgr$$ExternalSyntheticLambda1(this), null, null, new UiMgr$$ExternalSyntheticLambda2(this)});
      var3.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, new OnAirTemperatureUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickDown() {
            this.this$0.sendAirCommand(98);
         }

         public void onClickUp() {
            this.this$0.sendAirCommand(97);
         }
      }, null});
      var3.getRearArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommand(100);
         }

         public void onClickRight() {
            this.this$0.sendAirCommand(99);
         }
      });
      OnStartPageUiSet var4 = this.getOnStartPageUiSet(var1);
      var4.setOnOnStartStatusChangeListener(new OnOnStartStatusChangeListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onWirelessSwithc() {
            MyLog.temporaryTracking("进入安吉星");
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 65});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 66});
         }
      });
      var4.setOnOnStarClickListener(new OnOnStarClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void exit() {
            CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
         }

         public void handOff() {
            if (this.this$0.getState() == 2 || this.this$0.getState() == 3 || this.this$0.getState() == 4) {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
            }

         }

         public void handOn(String var1) {
            if (this.this$0.getState() == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, -122, 2});
            } else if (var1.length() > 0) {
               byte[] var2 = FutureUtil.instance.str2Bcd(this.this$0.fillSpaces(var1));
               CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -122}, var2));
            }

         }

         public void init() {
            if (this.this$0.getState() == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
            }

         }

         public void numberClick(int var1) {
            if (this.this$0.getState() == 4) {
               CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)(var1 | 128)});
            }

         }
      });
      this.setUiDifferent(var1, var3);
   }

   private String fillSpaces(String var1) {
      String var4 = var1;
      if (var1.length() < 20) {
         int var3 = var1.length();
         int var2 = 0;

         while(true) {
            var4 = var1;
            if (var2 >= 20 - var3) {
               break;
            }

            var1 = var1 + " ";
            ++var2;
         }
      }

      return var4;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private int getState() {
      return GeneralOnStartData.mOnStarStatus;
   }

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   private byte[] getTrackRequestCommand() {
      if (this.mTrackRequestCommand == null) {
         this.mTrackRequestCommand = new byte[]{22, -112, 38};
      }

      return this.mTrackRequestCommand;
   }

   private TimerTask getTrackRequestTimerTask(Context var1) {
      if (this.mTrackRequestTimerTask == null) {
         this.mTrackRequestTimerTask = new TimerTask(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               if (CommUtil.isMiscReverse()) {
                  CanbusMsgSender.sendMsg(this.this$0.getTrackRequestCommand());
               } else {
                  this.this$0.getTimerUtil().stopTimer();
               }

            }
         };
      }

      return this.mTrackRequestTimerTask;
   }

   // $FF: synthetic method
   static void lambda$new$0(SettingPageUiSet var0, int var1, int var2) {
      Objects.requireNonNull(((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn());
   }

   // $FF: synthetic method
   static void lambda$new$1(SettingPageUiSet var0, int var1, int var2, int var3) {
      byte var5;
      label23: {
         String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var1 = var4.hashCode();
         if (var1 != 478200384) {
            if (var1 == 1401027296 && var4.equals("compass_zoom")) {
               var5 = 1;
               break label23;
            }
         } else if (var4.equals("warning_volume_set")) {
            var5 = 0;
            break label23;
         }

         var5 = -1;
      }

      if (var5 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte)var3});
      }

   }

   // $FF: synthetic method
   static void lambda$new$2(SettingPageUiSet var0, int var1, int var2) {
      String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var0.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
      var3.hashCode();
      if (var3.equals("_279_restore_factory_default")) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -128, 1});
      }

   }

   // $FF: synthetic method
   static void lambda$new$4() {
   }

   private void sendAirCommand(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, (byte)var1});
      this.mHandler.sendEmptyMessageDelayed(0, 100L);
   }

   private void sendAirCommand(int var1, int var2) {
      String var3 = this.getAirUiSet(this.mContext).getRearArea().getLineBtnAction()[var1][var2];
      var3.hashCode();
      var2 = var3.hashCode();
      byte var4 = -1;
      switch (var2) {
         case -713186454:
            if (var3.equals("rear_auto")) {
               var4 = 0;
            }
            break;
         case -712865050:
            if (var3.equals("rear_lock")) {
               var4 = 1;
            }
            break;
         case -620266838:
            if (var3.equals("rear_power")) {
               var4 = 2;
            }
            break;
         case 1018451744:
            if (var3.equals("blow_head_foot")) {
               var4 = 3;
            }
            break;
         case 1434490075:
            if (var3.equals("blow_foot")) {
               var4 = 4;
            }
            break;
         case 1434539597:
            if (var3.equals("blow_head")) {
               var4 = 5;
            }
      }

      switch (var4) {
         case 0:
            this.sendAirCommand(104);
            break;
         case 1:
            this.sendAirCommand(106);
            break;
         case 2:
            this.sendAirCommand(105);
            break;
         case 3:
            this.sendAirCommand(102);
            break;
         case 4:
            this.sendAirCommand(103);
            break;
         case 5:
            this.sendAirCommand(101);
      }

   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case -1181429844:
            if (var1.equals("ac_auto")) {
               var2 = 1;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 2;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 3;
            }
            break;
         case 3545755:
            if (var1.equals("sync")) {
               var2 = 4;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 5;
            }
            break;
         case 109329021:
            if (var1.equals("setup")) {
               var2 = 6;
            }
            break;
         case 341572893:
            if (var1.equals("blow_window")) {
               var2 = 7;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 8;
            }
            break;
         case 1018451744:
            if (var1.equals("blow_head_foot")) {
               var2 = 9;
            }
            break;
         case 1434490075:
            if (var1.equals("blow_foot")) {
               var2 = 10;
            }
            break;
         case 1434539597:
            if (var1.equals("blow_head")) {
               var2 = 11;
            }
            break;
         case 1568764496:
            if (var1.equals("blow_window_foot")) {
               var2 = 12;
            }
      }

      switch (var2) {
         case 0:
            this.sendAirCommand(22);
            break;
         case 1:
            this.sendAirCommand(2);
            break;
         case 2:
            this.sendAirCommand(23);
            break;
         case 3:
            this.sendAirCommand(1);
            break;
         case 4:
            this.sendAirCommand(13);
            break;
         case 5:
            this.sendAirCommand(24);
            break;
         case 6:
            this.getMsgMgr(this.mContext).startSettingActivity();
            break;
         case 7:
            this.sendAirCommand(12);
            break;
         case 8:
            this.sendAirCommand(3);
            break;
         case 9:
            this.sendAirCommand(9);
            break;
         case 10:
            this.sendAirCommand(11);
            break;
         case 11:
            this.sendAirCommand(8);
            break;
         case 12:
            this.sendAirCommand(10);
      }

   }

   private void setUiDifferent(Context var1, AirPageUiSet var2) {
      this.removeSettingLeftItemByNameList(var1, new String[]{"compass"});
      if (this.mDifferentId == 20) {
         var2.getFrontArea().setWindMaxLevel(8);
         this.removeFrontAirButtonByName(var1, "ac_auto");
      } else {
         this.removeFrontAirButtonByName(var1, "sync");
      }

      if (this.mDifferentId == 21) {
         this.removeSettingRightItemByNameList(var1, new String[]{"_143_0x76_d0_b6"});
      } else {
         this.removeSettingRightItemByNameList(var1, new String[]{"_61_radar_switch", "_61_rear_seat_reminder", "_61_hands_free_liftgate_control", "_61_alert_type", "_61_forward_collision_system"});
      }

      if (this.mDifferentId == 22) {
         this.removeSettingRightItemByNameList(var1, new String[]{"remote_control_seat_auto_heat", "_1168_set_ac_system_ac_rmote_start_seat_auto_heat", "adaptive_forward_lighting"});
      } else {
         this.removeSettingRightItemByNameList(var1, new String[]{"seat_auto_heat", "_61_adapter_front_light", "_61_movement_steering", "_61_rear_pass_warning", "_61_line_change_warning"});
      }

      if (this.mDifferentId != 24) {
         this.removeSettingRightItemByNameList(var1, new String[]{"adaptive_cruise_start_reminder"});
      }

      int var3 = this.mDifferentId;
      if (var3 != 24 && var3 != 21) {
         this.removeSettingRightItemByNameList(var1, new String[]{"remote_start_seat_heat", "remote_start_seat_cold", "remote_start_air", "remote_start_rear_air", "_61_evlevated_idle"});
      }

      var3 = this.mDifferentId;
      if (var3 != 23 && var3 != 20) {
         this.removeSettingRightItemByNameList(var1, new String[]{"remote_control_seat_auto_heat"});
      } else {
         this.removeSettingRightItemByNameList(var1, new String[]{"_1168_set_ac_system_ac_rmote_start_seat_auto_heat"});
      }

      var3 = this.mDifferentId;
      if (var3 != 20 && var3 != 25) {
         this.removeSettingRightItemByNameList(var1, new String[]{"reverse_rear_view_mirror_auto_tilt"});
      } else {
         this.removeSettingRightItemByNameList(var1, new String[]{"_61_reverse_tilt_mirror"});
      }

   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
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

   // $FF: synthetic method
   void lambda$new$10$com_hzbhd_canbus_car__61_UiMgr(int var1) {
      this.sendAirCommand(3, var1);
   }

   // $FF: synthetic method
   void lambda$new$3$com_hzbhd_canbus_car__61_UiMgr(SettingPageUiSet var1, int var2, int var3, int var4) {
      String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList().get(var3)).getTitleSrn();
      var5.hashCode();
      var3 = var5.hashCode();
      byte var6 = -1;
      switch (var3) {
         case -2044134219:
            if (var5.equals("personalization_by_driver")) {
               var6 = 0;
            }
            break;
         case -2003824814:
            if (var5.equals("_61_radar_switch")) {
               var6 = 1;
            }
            break;
         case -1949342696:
            if (var5.equals("prevent_open_door_auto_lock")) {
               var6 = 2;
            }
            break;
         case -1775953625:
            if (var5.equals("remote_window_control")) {
               var6 = 3;
            }
            break;
         case -1647100313:
            if (var5.equals("delay_lock")) {
               var6 = 4;
            }
            break;
         case -1627575416:
            if (var5.equals("leave_car_auto_lock")) {
               var6 = 5;
            }
            break;
         case -1538471658:
            if (var5.equals("convenience_get_off_option")) {
               var6 = 6;
            }
            break;
         case -1466177363:
            if (var5.equals("air_launcher_mode")) {
               var6 = 7;
            }
            break;
         case -1387219473:
            if (var5.equals("adaptive_forward_lighting")) {
               var6 = 8;
            }
            break;
         case -1382834175:
            if (var5.equals("remote_control_seat_auto_heat")) {
               var6 = 9;
            }
            break;
         case -1337774785:
            if (var5.equals("_61_reverse_tilt_mirror")) {
               var6 = 10;
            }
            break;
         case -1331709104:
            if (var5.equals("adaptive_cruise_start_reminder")) {
               var6 = 11;
            }
            break;
         case -1226835875:
            if (var5.equals("_220_air_quality_sensor")) {
               var6 = 12;
            }
            break;
         case -1164051160:
            if (var5.equals("key_forgot_remain")) {
               var6 = 13;
            }
            break;
         case -877543893:
            if (var5.equals("_61_adapter_front_light")) {
               var6 = 14;
            }
            break;
         case -873936231:
            if (var5.equals("_279_temperature_unit")) {
               var6 = 15;
            }
            break;
         case -743253170:
            if (var5.equals("remote_control_unlock_light_feedback")) {
               var6 = 16;
            }
            break;
         case -703666556:
            if (var5.equals("_61_rear_pass_warning")) {
               var6 = 17;
            }
            break;
         case -667783574:
            if (var5.equals("air_partition_temperature")) {
               var6 = 18;
            }
            break;
         case -572185957:
            if (var5.equals("_61_revers_back_wiper_auto_launcher")) {
               var6 = 19;
            }
            break;
         case -539410636:
            if (var5.equals("remote_start_air")) {
               var6 = 20;
            }
            break;
         case -526007558:
            if (var5.equals("flank_blind_zone_warning")) {
               var6 = 21;
            }
            break;
         case -501414644:
            if (var5.equals("_1168_set_ac_system_ac_rmote_start_seat_auto_heat")) {
               var6 = 22;
            }
            break;
         case -440182422:
            if (var5.equals("lock_big_light_delay_set")) {
               var6 = 23;
            }
            break;
         case -393117929:
            if (var5.equals("stop_auto_unlock")) {
               var6 = 24;
            }
            break;
         case -300418790:
            if (var5.equals("_61_evlevated_idle")) {
               var6 = 25;
            }
            break;
         case -174199675:
            if (var5.equals("remote_start_rear_air")) {
               var6 = 26;
            }
            break;
         case -163192997:
            if (var5.equals("_61_forward_collision_system")) {
               var6 = 27;
            }
            break;
         case -58415930:
            if (var5.equals("seat_auto_heat")) {
               var6 = 28;
            }
            break;
         case -36611461:
            if (var5.equals("auto_wiper")) {
               var6 = 29;
            }
            break;
         case 16748135:
            if (var5.equals("_61_hands_free_liftgate_control")) {
               var6 = 30;
            }
            break;
         case 78971422:
            if (var5.equals("start_auto_lock")) {
               var6 = 31;
            }
            break;
         case 102584022:
            if (var5.equals("language_setup")) {
               var6 = 32;
            }
            break;
         case 207472786:
            if (var5.equals("auto_rear_view_mirror_fold")) {
               var6 = 33;
            }
            break;
         case 261992690:
            if (var5.equals("find_car_light_function")) {
               var6 = 34;
            }
            break;
         case 364441366:
            if (var5.equals("_61_rear_seat_reminder")) {
               var6 = 35;
            }
            break;
         case 371113033:
            if (var5.equals("reverse_rear_view_mirror_auto_tilt")) {
               var6 = 36;
            }
            break;
         case 529054061:
            if (var5.equals("auto_re_lock_doors")) {
               var6 = 37;
            }
            break;
         case 591765596:
            if (var5.equals("_61_movement_steering")) {
               var6 = 38;
            }
            break;
         case 626049822:
            if (var5.equals("_194_automatic_mode_wind")) {
               var6 = 39;
            }
            break;
         case 683849548:
            if (var5.equals("_61_advanced_hill_start_assist")) {
               var6 = 40;
            }
            break;
         case 708150576:
            if (var5.equals("_61_vehicle_type_setup")) {
               var6 = 41;
            }
            break;
         case 1061082397:
            if (var5.equals("_143_0x76_d0_b6")) {
               var6 = 42;
            }
            break;
         case 1172774528:
            if (var5.equals("remote_unlock_set")) {
               var6 = 43;
            }
            break;
         case 1186455828:
            if (var5.equals("left_or_right_hand_traffic")) {
               var6 = 44;
            }
            break;
         case 1296262722:
            if (var5.equals("_61_alert_type")) {
               var6 = 45;
            }
            break;
         case 1321211278:
            if (var5.equals("close_unlock_set")) {
               var6 = 46;
            }
            break;
         case 1439613768:
            if (var5.equals("remote_start_seat_cold")) {
               var6 = 47;
            }
            break;
         case 1439752788:
            if (var5.equals("remote_start_seat_heat")) {
               var6 = 48;
            }
            break;
         case 1578036129:
            if (var5.equals("back_windows_auto_defog")) {
               var6 = 49;
            }
            break;
         case 1690202822:
            if (var5.equals("call_memory_position")) {
               var6 = 50;
            }
            break;
         case 1700125011:
            if (var5.equals("_61_line_change_warning")) {
               var6 = 51;
            }
            break;
         case 1851660334:
            if (var5.equals("remote_control_unlock_light_or_horn_feedback")) {
               var6 = 52;
            }
            break;
         case 1952102175:
            if (var5.equals("front_windows_auto_defog")) {
               var6 = 53;
            }
      }

      switch (var6) {
         case 0:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var4});
            break;
         case 1:
         case 42:
            CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var4});
            break;
         case 2:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var4});
            break;
         case 3:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)var4});
            break;
         case 4:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var4});
            break;
         case 5:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var4});
            break;
         case 6:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var4});
            break;
         case 7:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 6, (byte)var4});
            break;
         case 8:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 17, (byte)var4});
            break;
         case 9:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte)var4});
            break;
         case 10:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var4});
            break;
         case 11:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 29, (byte)var4});
            break;
         case 12:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte)var4});
            break;
         case 13:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var4});
            break;
         case 14:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 30, (byte)var4});
            break;
         case 15:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 9, (byte)var4});
            break;
         case 16:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var4});
            break;
         case 17:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte)var4});
            break;
         case 18:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 2, (byte)var4});
            break;
         case 19:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var4});
            break;
         case 20:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 67, (byte)var4});
            break;
         case 21:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var4});
            break;
         case 22:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte)var4});
            break;
         case 23:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var4});
            break;
         case 24:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var4});
            break;
         case 25:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 10, (byte)var4});
            break;
         case 26:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 68, (byte)var4});
            break;
         case 27:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)var4});
            break;
         case 28:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 8, (byte)var4});
            break;
         case 29:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)var4});
            break;
         case 30:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)var4});
            break;
         case 31:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var4});
            break;
         case 32:
            this.sendLanguageCmd(var4);
            break;
         case 33:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 18, (byte)var4});
            break;
         case 34:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var4});
            break;
         case 35:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)var4});
            break;
         case 36:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var4});
            break;
         case 37:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var4});
            break;
         case 38:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 31, (byte)var4});
            break;
         case 39:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 0, (byte)var4});
            break;
         case 40:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 28, (byte)var4});
            break;
         case 41:
            CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte)var4});
            break;
         case 43:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)var4});
            break;
         case 44:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 16, (byte)var4});
            break;
         case 45:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var4});
            break;
         case 46:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var4});
            break;
         case 47:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 66, (byte)var4});
            break;
         case 48:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 65, (byte)var4});
            break;
         case 49:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 3, (byte)var4});
            break;
         case 50:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var4});
            break;
         case 51:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 33, (byte)var4});
            break;
         case 52:
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var4});
            break;
         case 53:
            CanbusMsgSender.sendMsg(new byte[]{22, -126, 4, (byte)var4});
      }

   }

   // $FF: synthetic method
   void lambda$new$5$com_hzbhd_canbus_car__61_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[0][var2]);
   }

   // $FF: synthetic method
   void lambda$new$6$com_hzbhd_canbus_car__61_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[1][var2]);
   }

   // $FF: synthetic method
   void lambda$new$7$com_hzbhd_canbus_car__61_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[2][var2]);
   }

   // $FF: synthetic method
   void lambda$new$8$com_hzbhd_canbus_car__61_UiMgr(AirPageUiSet var1, int var2) {
      this.sendAirCommand(var1.getFrontArea().getLineBtnAction()[3][var2]);
   }

   // $FF: synthetic method
   void lambda$new$9$com_hzbhd_canbus_car__61_UiMgr(int var1) {
      this.sendAirCommand(0, var1);
   }

   public void sendLanguageCmd(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var1});
      SharePreUtil.setIntValue(this.mContext, "key.laguage.pos.tag", var1);
   }
}
