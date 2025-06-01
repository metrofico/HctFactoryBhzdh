package com.hzbhd.canbus.car._68;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.interfaces.OnOnStarClickListener;
import com.hzbhd.canbus.interfaces.OnOnStartStatusChangeListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemTouchListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OnStartPageUiSet;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   AirPageUiSet airPageUiSet;
   private int differentID;
   private int eachID;
   private Context mContext;
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener mOnAirBtnClickListenerRearBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 104);
                  } else {
                     this.this$0.sendAirKeyMsgE0(52);
                  }
               }
            } else if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 105);
            }
         } else if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 107);
         }

      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerRearTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               if (!this.this$0.isPortrateScreen()) {
                  this.this$0.sendAirKeyMsg82(7, 103);
               } else {
                  this.this$0.sendAirKeyMsgE0(55);
               }
            }
         } else if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 101);
         } else {
            this.this$0.sendAirKeyMsgE0(53);
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 7);
         } else {
            this.this$0.sendAirKeyMsgE0(9);
         }

      }

      public void onClickRight() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 6);
         } else {
            this.this$0.sendAirKeyMsgE0(10);
         }

      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerRear = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 100);
         } else {
            this.this$0.sendAirKeyMsgE0(48);
         }

      }

      public void onClickRight() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 99);
         } else {
            this.this$0.sendAirKeyMsgE0(49);
         }

      }
   };
   private OnHybirdPageStatusListener mOnHybirdPageStatusListener = new OnHybirdPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnLeftSeatHeat = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         if (GeneralAirData.front_left_seat_heat_level == 0) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 22);
            } else {
               this.this$0.sendAirKeyMsgE0(11);
            }
         }

         if (GeneralAirData.front_left_seat_heat_level == 1) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 22);
            } else {
               this.this$0.sendAirKeyMsgE0(11);
            }
         }

         if (GeneralAirData.front_left_seat_heat_level == 2) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 22);
            } else {
               this.this$0.sendAirKeyMsgE0(11);
            }
         }

         if (GeneralAirData.front_left_seat_heat_level == 3) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 22);
            } else {
               this.this$0.sendAirKeyMsgE0(11);
            }
         }

      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnRightSeatHeat = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         if (GeneralAirData.front_right_seat_heat_level == 0) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 24);
            } else {
               this.this$0.sendAirKeyMsgE0(14);
            }
         }

         if (GeneralAirData.front_right_seat_heat_level == 1) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 24);
            } else {
               this.this$0.sendAirKeyMsgE0(14);
            }
         }

         if (GeneralAirData.front_right_seat_heat_level == 2) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 24);
            } else {
               this.this$0.sendAirKeyMsgE0(14);
            }
         }

         if (GeneralAirData.front_right_seat_heat_level == 3) {
            if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 24);
            } else {
               this.this$0.sendAirKeyMsgE0(14);
            }
         }

      }
   };
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         String var5 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var4.getSettingUiSet(var4.mContext).getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var5.hashCode();
         if (var5.equals("warning_volume_set")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -120, (byte)var3});
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var6 = this.this$0;
         String var7 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)var6.getSettingUiSet(var6.mContext).getList().get(var1)).getItemList().get(var2)).getTitleSrn();
         var7.hashCode();
         int var5 = var7.hashCode();
         byte var4 = -1;
         switch (var5) {
            case -2067859824:
               if (var7.equals("adapter_front_light")) {
                  var4 = 0;
               }
               break;
            case -2044134219:
               if (var7.equals("personalization_by_driver")) {
                  var4 = 1;
               }
               break;
            case -2022373321:
               if (var7.equals("auto_mode_wind_set")) {
                  var4 = 2;
               }
               break;
            case -2003824814:
               if (var7.equals("_61_radar_switch")) {
                  var4 = 3;
               }
               break;
            case -1949342696:
               if (var7.equals("prevent_open_door_auto_lock")) {
                  var4 = 4;
               }
               break;
            case -1775953625:
               if (var7.equals("remote_window_control")) {
                  var4 = 5;
               }
               break;
            case -1647100313:
               if (var7.equals("delay_lock")) {
                  var4 = 6;
               }
               break;
            case -1627575416:
               if (var7.equals("leave_car_auto_lock")) {
                  var4 = 7;
               }
               break;
            case -1614395726:
               if (var7.equals("air_quality_sensor_set")) {
                  var4 = 8;
               }
               break;
            case -1538471658:
               if (var7.equals("convenience_get_off_option")) {
                  var4 = 9;
               }
               break;
            case -1535502006:
               if (var7.equals("_68_Pollution_control")) {
                  var4 = 10;
               }
               break;
            case -1466177363:
               if (var7.equals("air_launcher_mode")) {
                  var4 = 11;
               }
               break;
            case -1388141665:
               if (var7.equals("rear_seat_tis")) {
                  var4 = 12;
               }
               break;
            case -1382834175:
               if (var7.equals("remote_control_seat_auto_heat")) {
                  var4 = 13;
               }
               break;
            case -1331709104:
               if (var7.equals("adaptive_cruise_start_reminder")) {
                  var4 = 14;
               }
               break;
            case -1164051160:
               if (var7.equals("key_forgot_remain")) {
                  var4 = 15;
               }
               break;
            case -1101576519:
               if (var7.equals("language_status")) {
                  var4 = 16;
               }
               break;
            case -1045027611:
               if (var7.equals("remote_lock_again")) {
                  var4 = 17;
               }
               break;
            case -993115198:
               if (var7.equals("lane_change_tis")) {
                  var4 = 18;
               }
               break;
            case -743253170:
               if (var7.equals("remote_control_unlock_light_feedback")) {
                  var4 = 19;
               }
               break;
            case -667783574:
               if (var7.equals("air_partition_temperature")) {
                  var4 = 20;
               }
               break;
            case -599641101:
               if (var7.equals("ramp_start_assist")) {
                  var4 = 21;
               }
               break;
            case -539410636:
               if (var7.equals("remote_start_air")) {
                  var4 = 22;
               }
               break;
            case -526007558:
               if (var7.equals("flank_blind_zone_warning")) {
                  var4 = 23;
               }
               break;
            case -440182422:
               if (var7.equals("lock_big_light_delay_set")) {
                  var4 = 24;
               }
               break;
            case -393117929:
               if (var7.equals("stop_auto_unlock")) {
                  var4 = 25;
               }
               break;
            case -333628661:
               if (var7.equals("front_work_man_check")) {
                  var4 = 26;
               }
               break;
            case -174199675:
               if (var7.equals("remote_start_rear_air")) {
                  var4 = 27;
               }
               break;
            case -58415930:
               if (var7.equals("seat_auto_heat")) {
                  var4 = 28;
               }
               break;
            case -36611461:
               if (var7.equals("auto_wiper")) {
                  var4 = 29;
               }
               break;
            case -8742296:
               if (var7.equals("sport_turn")) {
                  var4 = 30;
               }
               break;
            case 25657870:
               if (var7.equals("remote_launcher_car")) {
                  var4 = 31;
               }
               break;
            case 78971422:
               if (var7.equals("start_auto_lock")) {
                  var4 = 32;
               }
               break;
            case 207472786:
               if (var7.equals("auto_rear_view_mirror_fold")) {
                  var4 = 33;
               }
               break;
            case 220749579:
               if (var7.equals("car_status_notify")) {
                  var4 = 34;
               }
               break;
            case 261992690:
               if (var7.equals("find_car_light_function")) {
                  var4 = 35;
               }
               break;
            case 371113033:
               if (var7.equals("reverse_rear_view_mirror_auto_tilt")) {
                  var4 = 36;
               }
               break;
            case 437771727:
               if (var7.equals("_68_add2")) {
                  var4 = 37;
               }
               break;
            case 437771728:
               if (var7.equals("_68_add3")) {
                  var4 = 38;
               }
               break;
            case 437771729:
               if (var7.equals("_68_add4")) {
                  var4 = 39;
               }
               break;
            case 437771730:
               if (var7.equals("_68_add5")) {
                  var4 = 40;
               }
               break;
            case 437771731:
               if (var7.equals("_68_add6")) {
                  var4 = 41;
               }
               break;
            case 437771732:
               if (var7.equals("_68_add7")) {
                  var4 = 42;
               }
               break;
            case 529054061:
               if (var7.equals("auto_re_lock_doors")) {
                  var4 = 43;
               }
               break;
            case 576264281:
               if (var7.equals("driver_seat_auto_identify")) {
                  var4 = 44;
               }
               break;
            case 588625313:
               if (var7.equals("collision_or_check_tis_type")) {
                  var4 = 45;
               }
               break;
            case 593999981:
               if (var7.equals("charging_limit")) {
                  var4 = 46;
               }
               break;
            case 781183872:
               if (var7.equals("revers_back_wiper_auto_launcher")) {
                  var4 = 47;
               }
               break;
            case 932962796:
               if (var7.equals("auto_prevent_ready")) {
                  var4 = 48;
               }
               break;
            case 1137302718:
               if (var7.equals("_68_Automatically_unlock_near_car")) {
                  var4 = 49;
               }
               break;
            case 1172774528:
               if (var7.equals("remote_unlock_set")) {
                  var4 = 50;
               }
               break;
            case 1321211278:
               if (var7.equals("close_unlock_set")) {
                  var4 = 51;
               }
               break;
            case 1439613768:
               if (var7.equals("remote_start_seat_cold")) {
                  var4 = 52;
               }
               break;
            case 1439752788:
               if (var7.equals("remote_start_seat_heat")) {
                  var4 = 53;
               }
               break;
            case 1578036129:
               if (var7.equals("back_windows_auto_defog")) {
                  var4 = 54;
               }
               break;
            case 1690202822:
               if (var7.equals("call_memory_position")) {
                  var4 = 55;
               }
               break;
            case 1837290941:
               if (var7.equals("driver_seat_auto_return")) {
                  var4 = 56;
               }
               break;
            case 1851660334:
               if (var7.equals("remote_control_unlock_light_or_horn_feedback")) {
                  var4 = 57;
               }
               break;
            case 1952102175:
               if (var7.equals("front_windows_auto_defog")) {
                  var4 = 58;
               }
               break;
            case 2024458437:
               if (var7.equals("reverse_rear_view_mirror_help")) {
                  var4 = 59;
               }
               break;
            case 2121005011:
               if (var7.equals("back_car_pass_tis")) {
                  var4 = 60;
               }
               break;
            case 2132082026:
               if (var7.equals("car_auto_keep")) {
                  var4 = 61;
               }
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 94, (byte)var3});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 14, (byte)var3});
               break;
            case 2:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 0, (byte)var3});
               }
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte)var3});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
               break;
            case 5:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 27, (byte)var3});
               break;
            case 6:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
               break;
            case 7:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 23, (byte)var3});
               break;
            case 8:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte)var3});
               }
               break;
            case 9:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 89, (byte)var3});
               break;
            case 10:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var3});
               break;
            case 11:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 6, (byte)var3});
               }
               break;
            case 12:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 83, (byte)var3});
               break;
            case 13:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 5, (byte)var3});
               }
               break;
            case 14:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 82, (byte)var3});
               break;
            case 15:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
               break;
            case 16:
               CanbusMsgSender.sendMsg(new byte[]{22, -121, (byte)var3});
               break;
            case 17:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)var3});
               break;
            case 18:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 87, (byte)var3});
               break;
            case 19:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
               break;
            case 20:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 2, (byte)var3});
               }
               break;
            case 21:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 81, (byte)var3});
               break;
            case 22:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 67, (byte)var3});
               }
               break;
            case 23:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 22, (byte)var3});
               break;
            case 24:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
               break;
            case 25:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
               break;
            case 26:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 85, (byte)var3});
               break;
            case 27:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 68, (byte)var3});
               }
               break;
            case 28:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 8, (byte)var3});
               }
               break;
            case 29:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 26, (byte)var3});
               break;
            case 30:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 93, (byte)var3});
               break;
            case 31:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)var3});
               break;
            case 32:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
               break;
            case 33:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 91, (byte)var3});
               break;
            case 34:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 25, (byte)var3});
               break;
            case 35:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var3});
               break;
            case 36:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 90, (byte)var3});
               break;
            case 37:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte)var3});
               break;
            case 38:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 35, (byte)var3});
               break;
            case 39:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 37, (byte)var3});
               break;
            case 40:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 38, (byte)var3});
               break;
            case 41:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte)var3});
               break;
            case 42:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 32, (byte)var3});
               break;
            case 43:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 15, (byte)var3});
               break;
            case 44:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 21, (byte)var3});
               break;
            case 45:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 84, (byte)var3});
               break;
            case 46:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 34, (byte)var3});
               break;
            case 47:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var3});
               break;
            case 48:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 24, (byte)var3});
               break;
            case 49:
               if (var3 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, 2});
               } else if (var3 == 1) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, 0});
               } else if (var3 == 2) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, 1});
               }
               break;
            case 50:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)var3});
               break;
            case 51:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
               break;
            case 52:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 66, (byte)var3});
               }
               break;
            case 53:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 65, (byte)var3});
               }
               break;
            case 54:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 3, (byte)var3});
               }
               break;
            case 55:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 88, (byte)var3});
               break;
            case 56:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 20, (byte)var3});
               break;
            case 57:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var3});
               break;
            case 58:
               if (!this.this$0.isPortrateScreen()) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, 4, (byte)var3});
               }
               break;
            case 59:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 19, (byte)var3});
               break;
            case 60:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 86, (byte)var3});
               break;
            case 61:
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 92, (byte)var3});
         }

         LogUtil.showLog("leftPos:" + var1);
         LogUtil.showLog("rightPos:" + var2);
         LogUtil.showLog("selectPos:" + var3);
      }
   };
   private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnleftSeatCold = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 23});
         this.this$0.mHandler.postDelayed(new Runnable(this) {
            final <undefinedtype> this$1;

            {
               this.this$1 = var1;
            }

            public void run() {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 0});
            }
         }, 50L);
      }
   };
   OnAirSeatHeatColdMinPlusClickListener mOnrightSeatCold = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
         CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 25});
         this.this$0.mHandler.postDelayed(new Runnable(this) {
            final <undefinedtype> this$1;

            {
               this.this$1 = var1;
            }

            public void run() {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 7, 0});
            }
         }, 50L);
      }
   };
   private MyPanoramicView mPanoramicView;
   OnAirTemperatureUpDownClickListener mUpDownTempCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 98);
         } else {
            this.this$0.sendAirKeyMsgE0(50);
         }

      }

      public void onClickUp() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 97);
         } else {
            this.this$0.sendAirKeyMsgE0(51);
         }

      }
   };
   private OnAirSeatHeatColdMinPlusClickListener onMinPlusClickListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
         LogUtil.showLog("onClickMin");
      }

      public void onClickPlus() {
         LogUtil.showLog("onClickPlus");
      }
   };
   private OnOnStarClickListener onOnStarClickListener = new OnOnStarClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void exit() {
         FutureUtil var2 = FutureUtil.instance;
         Context var1 = this.this$0.mContext;
         var2.reportMcuDataExtra(new byte[]{-123, 0}, var1);
      }

      public void handOff() {
         if (this.this$0.getState() == 2 || this.this$0.getState() == 3 || this.this$0.getState() == 4) {
            FutureUtil var1 = FutureUtil.instance;
            Context var2 = this.this$0.mContext;
            var1.reportMcuDataExtra(new byte[]{-123, 3}, var2);
         }

      }

      public void handOn(String var1) {
         if (this.this$0.getState() == 2) {
            FutureUtil var3 = FutureUtil.instance;
            Context var2 = this.this$0.mContext;
            var3.reportMcuDataExtra(new byte[]{-122, 2}, var2);
         } else if (var1.length() > 0) {
            FutureUtil var5 = FutureUtil.instance;
            byte[] var4 = FutureUtil.instance.str2Bcd(this.this$0.fillSpaces(var1));
            var5.reportMcuDataExtra(DataHandleUtils.byteMerger(new byte[]{-122}, var4), this.this$0.mContext);
         }

      }

      public void init() {
         if (this.this$0.getState() == 0) {
            FutureUtil var2 = FutureUtil.instance;
            Context var1 = this.this$0.mContext;
            var2.reportMcuDataExtra(new byte[]{-123, 1}, var1);
         }

      }

      public void numberClick(int var1) {
         if (this.this$0.getState() == 4) {
            byte var2 = (byte)(var1 | 128);
            FutureUtil var4 = FutureUtil.instance;
            Context var3 = this.this$0.mContext;
            var4.reportMcuDataExtra(new byte[]{-123, var2}, var3);
         }

      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "other_set")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "other_set", "_68_panorama_on")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 1});
            }
         }

      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 5);
         } else {
            this.this$0.sendAirKeyMsgE0(2);
         }

      }

      public void onClickUp() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 4);
         } else {
            this.this$0.sendAirKeyMsgE0(3);
         }

      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 21);
         } else {
            this.this$0.sendAirKeyMsgE0(4);
         }

      }

      public void onClickUp() {
         if (!this.this$0.isPortrateScreen()) {
            this.this$0.sendAirKeyMsg82(7, 20);
         } else {
            this.this$0.sendAirKeyMsgE0(5);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachID = this.getEachId();
      this.differentID = this.getCurrentCarId();
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      var2.setOnSettingItemClickListener(this.onSettingItemClickListener);
      OnStartPageUiSet var5 = this.getOnStartPageUiSet(var1);
      var5.setOnOnStarClickListener(this.onOnStarClickListener);
      var5.setOnOnStartStatusChangeListener(new OnOnStartStatusChangeListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onWirelessSwithc() {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 65});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 66});
         }
      });
      ParkPageUiSet var6 = this.getParkPageUiSet(var1);
      var6.setOnPanoramicItemTouchListener(new OnPanoramicItemTouchListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onTouchItem(MotionEvent var1) {
            byte var6;
            if (var1.getAction() == 0) {
               var6 = 1;
            } else {
               var1.getAction();
               var6 = 0;
            }

            int var8 = (int)var1.getX();
            int var7 = (int)var1.getY();
            if ((int)this.val$context.getResources().getDimension(2131167469) < 801) {
               var8 = var8 * 1023 / 800;
               var7 = var7 * 1000 / 480;
            } else {
               var8 = var8 * 1023 / 1024;
               var7 = var7 * 1000 / 600;
            }

            byte var2 = (byte)(var8 & 255);
            byte var4 = (byte)(var8 >> 8 & 255);
            byte var3 = (byte)(var7 & 255);
            byte var5 = (byte)(var7 >> 8 & 255);
            CanbusMsgSender.sendMsg(new byte[]{22, -104, (byte)var6, var4, var2, var5, var3, 0});
         }
      });
      var6.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this, var6) {
         final UiMgr this$0;
         final ParkPageUiSet val$parkPageUiSet;

         {
            this.this$0 = var1;
            this.val$parkPageUiSet = var2;
         }

         public void onClickItem(int var1) {
            String var2 = ((ParkPageUiSet.Bean)this.val$parkPageUiSet.getPanoramicBtnList().get(var1)).getTitleSrn();
            var2.hashCode();
            if (var2.equals("_68_panorama_off")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -103, 0});
            }

         }
      });
      TimerUtil var3 = new TimerUtil();
      PanelKeyPageUiSet var7 = this.getPanelKeyPageUiSet(var1);
      var7.setOnPanelKeyPositionTouchListener(new OnPanelKeyPositionTouchListener(this, var3, var7) {
         final UiMgr this$0;
         final PanelKeyPageUiSet val$panelKeyPageUiSet;
         final TimerUtil val$timerUtil;

         {
            this.this$0 = var1;
            this.val$timerUtil = var2;
            this.val$panelKeyPageUiSet = var3;
         }

         public void onTouch(int var1, MotionEvent var2) {
            if (var2.getAction() == 0) {
               this.val$timerUtil.startTimer(new TimerTask(this, var1) {
                  final <undefinedtype> this$1;
                  final int val$position;

                  {
                     this.this$1 = var1;
                     this.val$position = var2;
                  }

                  public void run() {
                     String var3 = (String)this.this$1.val$panelKeyPageUiSet.getList().get(this.val$position);
                     var3.hashCode();
                     int var2 = var3.hashCode();
                     byte var1 = -1;
                     switch (var2) {
                        case -1538370707:
                           if (var3.equals("OPEL_KEY_FM_AM")) {
                              var1 = 0;
                           }
                           break;
                        case -539039127:
                           if (var3.equals("OPEL_KEY_CD_MP3")) {
                              var1 = 1;
                           }
                           break;
                        case -49454562:
                           if (var3.equals("OPEL_KEY_LEFT")) {
                              var1 = 2;
                           }
                           break;
                        case -49271953:
                           if (var3.equals("OPEL_KEY_RIGH")) {
                              var1 = 3;
                           }
                           break;
                        case 309675386:
                           if (var3.equals("OPEL_KEY_1")) {
                              var1 = 4;
                           }
                           break;
                        case 309675387:
                           if (var3.equals("OPEL_KEY_2")) {
                              var1 = 5;
                           }
                           break;
                        case 309675388:
                           if (var3.equals("OPEL_KEY_3")) {
                              var1 = 6;
                           }
                           break;
                        case 309675389:
                           if (var3.equals("OPEL_KEY_4")) {
                              var1 = 7;
                           }
                           break;
                        case 309675390:
                           if (var3.equals("OPEL_KEY_5")) {
                              var1 = 8;
                           }
                           break;
                        case 309675391:
                           if (var3.equals("OPEL_KEY_6")) {
                              var1 = 9;
                           }
                           break;
                        case 309675392:
                           if (var3.equals("OPEL_KEY_7")) {
                              var1 = 10;
                           }
                           break;
                        case 309675393:
                           if (var3.equals("OPEL_KEY_8")) {
                              var1 = 11;
                           }
                           break;
                        case 309675394:
                           if (var3.equals("OPEL_KEY_9")) {
                              var1 = 12;
                           }
                           break;
                        case 1010002968:
                           if (var3.equals("OPEL_KEY_BC")) {
                              var1 = 13;
                           }
                           break;
                        case 1010003379:
                           if (var3.equals("OPEL_KEY_OK")) {
                              var1 = 14;
                           }
                           break;
                        case 1803486393:
                           if (var3.equals("OPEL_KEY_SETTING")) {
                              var1 = 15;
                           }
                     }

                     switch (var1) {
                        case 0:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 6});
                           break;
                        case 1:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 7});
                           break;
                        case 2:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 4});
                           break;
                        case 3:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 5});
                           break;
                        case 4:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 8});
                           break;
                        case 5:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 9});
                           break;
                        case 6:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 10});
                           break;
                        case 7:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 11});
                           break;
                        case 8:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 12});
                           break;
                        case 9:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 13});
                           break;
                        case 10:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 14});
                           break;
                        case 11:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 15});
                           break;
                        case 12:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 16});
                           break;
                        case 13:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 3});
                           break;
                        case 14:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 1});
                           break;
                        case 15:
                           CanbusMsgSender.sendMsg(new byte[]{22, -58, 2});
                     }

                  }
               }, 0L, 200L);
            }

            if (var2.getAction() == 1) {
               this.val$timerUtil.stopTimer();
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 0});
            }

         }
      });
      AirPageUiSet var4 = this.getAirUiSet(var1);
      this.airPageUiSet = var4;
      var4.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            byte var4;
            label37: {
               String[][] var3 = this.this$0.airPageUiSet.getFrontArea().getLineBtnAction();
               byte var2 = 0;
               var5.hashCode();
               switch (var5) {
                  case "auto":
                     var4 = var2;
                  case "dual":
                     var4 = 1;
                     break label37;
                  case "in_out_cycle":
                     var4 = 2;
                     break label37;
               }

               var4 = -1;
            }

            switch (var4) {
               case 0:
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 2);
                  } else {
                     this.this$0.sendAirKeyMsgE0(21);
                  }
                  break;
               case 1:
                  this.this$0.isPortrateScreen();
                  break;
               case 2:
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 3);
                  } else {
                     this.this$0.sendAirKeyMsgE0(25);
                  }
            }

         }
      }, new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            String var2 = this.this$0.airPageUiSet.getFrontArea().getLineBtnAction()[1][var1];
            var2.hashCode();
            if (!var2.equals("ac")) {
               if (var2.equals("power")) {
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 26);
                  } else {
                     this.this$0.sendAirKeyMsgE0(1);
                  }
               }
            } else if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 1);
            } else {
               this.this$0.sendAirKeyMsgE0(23);
            }

         }
      }, new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            Log.d("mww", "onClickItem " + var1);
            String var2 = this.this$0.airPageUiSet.getFrontArea().getLineBtnAction()[2][var1];
            var2.hashCode();
            if (!var2.equals("front_defog")) {
               if (var2.equals("rear_defog")) {
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 27);
                  } else {
                     this.this$0.sendAirKeyMsgE0(20);
                  }
               }
            } else if (!this.this$0.isPortrateScreen()) {
               this.this$0.sendAirKeyMsg82(7, 12);
            } else {
               this.this$0.sendAirKeyMsgE0(18);
            }

         }
      }, new OnAirBtnClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            byte var4;
            label56: {
               String[][] var3 = this.this$0.airPageUiSet.getFrontArea().getLineBtnAction();
               byte var2 = 3;
               var5.hashCode();
               switch (var5) {
                  case "blow_window":
                     var4 = 0;
                     break label56;
                  case "blow_head_foot":
                     var4 = 1;
                     break label56;
                  case "blow_foot":
                     var4 = 2;
                     break label56;
                  case "blow_head":
                     var4 = var2;
                  case "blow_window_foot":
                     var4 = 4;
                     break label56;
               }

               var4 = -1;
            }

            switch (var4) {
               case 0:
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 28);
                  } else {
                     this.this$0.sendAirKeyMsgE0(6);
                  }
                  break;
               case 1:
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 9);
                  } else {
                     this.this$0.sendAirKeyMsgE0(33);
                  }
                  break;
               case 2:
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 11);
                  } else {
                     this.this$0.sendAirKeyMsgE0(8);
                  }
                  break;
               case 3:
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 8);
                  } else {
                     this.this$0.sendAirKeyMsgE0(7);
                  }
                  break;
               case 4:
                  if (!this.this$0.isPortrateScreen()) {
                     this.this$0.sendAirKeyMsg82(7, 10);
                  } else {
                     this.this$0.sendAirKeyMsgE0(32);
                  }
            }

         }
      }});
      this.airPageUiSet.getRearArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerRearTop, null, null, this.mOnAirBtnClickListenerRearBottom});
      this.airPageUiSet.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, null, this.onUpDownClickListenerRight});
      this.airPageUiSet.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      this.airPageUiSet.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mOnLeftSeatHeat, this.mOnRightSeatHeat, this.mOnleftSeatCold, this.mOnrightSeatCold});
      this.airPageUiSet.getRearArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerRear);
      this.airPageUiSet.getRearArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{null, this.mUpDownTempCenter, null});
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

   private int getState() {
      return DataHandleUtils.getIntFromByteWithBit(GeneralOnStartData.mOnStarStatus, 0, 7);
   }

   private void sendAirKeyMsg(int var1) {
      if (this.isPortrateScreen()) {
         this.mHandler.postDelayed(new Runnable(this, var1) {
            final UiMgr this$0;
            final int val$cmd;

            {
               this.this$0 = var1;
               this.val$cmd = var2;
            }

            public void run() {
               CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 1});
            }
         }, 30L);
         this.mHandler.postDelayed(new Runnable(this, var1) {
            final UiMgr this$0;
            final int val$cmd;

            {
               this.this$0 = var1;
               this.val$cmd = var2;
            }

            public void run() {
               CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 0});
            }
         }, 30L);
      }
   }

   private void sendAirKeyMsg82(int var1, int var2) {
      this.mHandler.postDelayed(new Runnable(this, var1, var2) {
         final UiMgr this$0;
         final int val$cmd;
         final int val$value;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
            this.val$value = var3;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)this.val$cmd, (byte)this.val$value});
         }
      }, 30L);
      this.mHandler.postDelayed(new Runnable(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)this.val$cmd, 0});
         }
      }, 30L);
   }

   private void sendAirKeyMsgE0(int var1) {
      this.mHandler.postDelayed(new Runnable(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 1});
         }
      }, 30L);
      this.mHandler.postDelayed(new Runnable(this, var1) {
         final UiMgr this$0;
         final int val$cmd;

         {
            this.this$0 = var1;
            this.val$cmd = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -32, (byte)this.val$cmd, 0});
         }
      }, 30L);
   }

   public View getCusPanoramicView(Context var1) {
      if (this.mPanoramicView == null) {
         this.mPanoramicView = new MyPanoramicView(var1);
      }

      return this.mPanoramicView;
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

   public boolean isPortrateScreen() {
      Context var1 = this.mContext;
      if (var1 == null) {
         return false;
      } else {
         return var1.getResources().getConfiguration().orientation == 1;
      }
   }

   public void send0xC0Info(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   public void send0xE2CarModelInfo(int var1) {
      if (var1 != 2) {
         if (var1 != 3) {
            if (var1 != 7) {
               if (var1 != 13) {
                  if (var1 != 9) {
                     if (var1 != 10) {
                        if (var1 != 21) {
                           if (var1 != 22) {
                              if (var1 != 30) {
                                 if (var1 != 31) {
                                    switch (var1) {
                                       case 25:
                                          CanbusMsgSender.sendMsg(new byte[]{22, -30, 0});
                                          break;
                                       case 26:
                                          CanbusMsgSender.sendMsg(new byte[]{22, -30, 21});
                                          break;
                                       case 27:
                                          CanbusMsgSender.sendMsg(new byte[]{22, -30, 12});
                                          break;
                                       case 28:
                                          CanbusMsgSender.sendMsg(new byte[]{22, -30, 13});
                                          break;
                                       default:
                                          CanbusMsgSender.sendMsg(new byte[]{22, -30, -128});
                                    }
                                 } else {
                                    CanbusMsgSender.sendMsg(new byte[]{22, -30, 9});
                                 }
                              } else {
                                 CanbusMsgSender.sendMsg(new byte[]{22, -30, 8});
                              }
                           } else {
                              CanbusMsgSender.sendMsg(new byte[]{22, -30, 70});
                           }
                        } else {
                           CanbusMsgSender.sendMsg(new byte[]{22, -30, 11});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, -30, 3});
                     }
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -30, 2});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -30, 67});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 69});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 68});
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      if (this.differentID != 1001) {
         this.removeSettingRightItemByNameList(this.mContext, new String[]{"_68_Pollution_control"});
      }

   }
}
