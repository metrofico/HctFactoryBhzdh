package com.hzbhd.canbus.car._61;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MsgMgr extends AbstractMsgMgr {
   static final int VEHICLE_TYPE_17_REGAL = 22;
   static final int VEHICLE_TYPE_ASTRA = 7;
   static final int VEHICLE_TYPE_CRUZ = 1;
   static final int VEHICLE_TYPE_ENCORE_LOW = 8;
   static final int VEHICLE_TYPE_EXCELLE = 2;
   static final int VEHICLE_TYPE_GL8_HIGH = 24;
   static final int VEHICLE_TYPE_GMC = 25;
   static final int VEHICLE_TYPE_GM_SERIES = 0;
   static final int VEHICLE_TYPE_MALIBU_XL = 20;
   static final int VEHICLE_TYPE_MOKKA = 6;
   static final int VEHICLE_TYPE_NEW_LACROSSE = 5;
   static final int VEHICLE_TYPE_NEW_REGAL = 4;
   static final int VEHICLE_TYPE_ORLANDO = 3;
   static final int VEHICLE_TYPE_TAHOE = 21;
   static final int VEHICLE_TYPE_VERANO = 23;
   private final String CHARSET_GB2312 = "GB2312";
   private final int DATA_TYPE = 1;
   private final int INVAILE_VALUE = -1;
   private String TAG = "ljqdebug";
   private String[] m0x05ItemTitleArray;
   private String[] m0x06ItemTitleArray;
   private String[] m0x0AItemTitleArray;
   private SparseArray m0x1AItemTitleArray;
   private String[] m0x44ItemTitleArray;
   private String[] m0xD2ItemTitleArray;
   private int mAcType;
   private boolean mAirTmeperatureUnit;
   private boolean mBackStatus;
   private byte[] mCanBusInfoByte;
   private int[] mCanBusInfoInt;
   private SparseArray mCanbusDataArray;
   private Context mContext;
   private DecimalFormat mDecimalFormat0p0;
   private int mDifferentId;
   private int mDiscExist;
   private int mEachId;
   private boolean mFrontStatus;
   private boolean mLeftFrontStatus;
   private boolean mLeftRearStatus;
   private int mOutDoorTemperature;
   private SparseArray mPanelKeyArray;
   private boolean mRightFrontStatus;
   private boolean mRightRearStatus;
   private HashMap mSettingItemIndeHashMap;
   private SparseArray mSettingRightItemArray;
   private String[] mTireAlarm;
   private UiMgr mUiMgr;
   private UiMgrInterface mUiMgrInterface;

   private boolean getAirAcStatus() {
      if (this.mDifferentId == 20) {
         boolean var1;
         if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      } else {
         return DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
      }
   }

   private byte[] getAsciiDatas(byte[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var1[var2] == 0) {
            return Arrays.copyOfRange(var1, 2, var2);
         }
      }

      return Arrays.copyOfRange(var1, 2, var1.length);
   }

   private int getData(int... var1) {
      int var2 = 0;

      int var3;
      for(var3 = 0; var2 < var1.length; ++var2) {
         var3 += var1[var2] << var2 * 8;
      }

      return var3;
   }

   private int[] getLeftAndRight(String var1) {
      int[] var3 = new int[2];
      if (this.mSettingItemIndeHashMap.containsKey(var1)) {
         int var2 = (Integer)this.mSettingItemIndeHashMap.get(var1);
         var3[0] = var2 >> 8;
         var3[1] = var2 & 255;
      } else {
         var3[0] = -1;
         var3[1] = -1;
      }

      Log.i(this.TAG, "getLeftAndRigth: left:" + var3[0] + ", right:" + var3[1]);
      return var3;
   }

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private UiMgrInterface getUiMgrInterface() {
      if (this.mUiMgrInterface == null) {
         this.mUiMgrInterface = UiMgrFactory.getCanUiMgr(this.mContext);
      }

      return this.mUiMgrInterface;
   }

   private void initData(Context var1) {
      this.m0x05ItemTitleArray = new String[]{"_194_automatic_mode_wind", "_220_air_quality_sensor", "air_partition_temperature", "back_windows_auto_defog", "front_windows_auto_defog", "seat_auto_heat", "remote_control_seat_auto_heat", "_1168_set_ac_system_ac_rmote_start_seat_auto_heat", "air_launcher_mode"};
      this.m0x06ItemTitleArray = new String[]{"find_car_light_function", "lock_big_light_delay_set", "prevent_open_door_auto_lock", "start_auto_lock", "stop_auto_unlock", "delay_lock", "remote_control_unlock_light_feedback", "remote_control_unlock_light_or_horn_feedback", "remote_unlock_set", "_61_revers_back_wiper_auto_launcher"};
      this.m0x0AItemTitleArray = new String[]{"close_unlock_set", "key_forgot_remain", "personalization_by_driver", "auto_re_lock_doors", "auto_wiper", "flank_blind_zone_warning", "leave_car_auto_lock", "left_or_right_hand_traffic", "adaptive_forward_lighting", "auto_rear_view_mirror_fold", "_61_reverse_tilt_mirror", "convenience_get_off_option", "call_memory_position", "_61_rear_seat_reminder", "adaptive_cruise_start_reminder", "reverse_rear_view_mirror_auto_tilt", "_61_advanced_hill_start_assist", "remote_window_control", "_61_hands_free_liftgate_control", "_61_adapter_front_light", "_61_movement_steering", "_61_rear_pass_warning", "_61_line_change_warning", "_61_alert_type", "_61_forward_collision_system"};
      this.m0xD2ItemTitleArray = new String[]{"compass_calibration_status", "compass_zoom", "magnetic_field_angle"};
      this.m0x44ItemTitleArray = new String[]{"remote_start_seat_heat", "remote_start_seat_cold", "remote_start_air", "remote_start_rear_air", "_61_evlevated_idle"};
      SparseArray var2 = new SparseArray();
      this.m0x1AItemTitleArray = var2;
      var2.put(0, new String[]{"_194_automatic_mode_wind", "_220_air_quality_sensor", "air_launcher_mode", "front_windows_auto_defog", "back_windows_auto_defog", "air_partition_temperature"});
      this.m0x1AItemTitleArray.append(1, new String[]{"flank_blind_zone_warning"});
      this.m0x1AItemTitleArray.append(2, new String[]{"_61_revers_back_wiper_auto_launcher", "personalization_by_driver", "call_memory_position", "convenience_get_off_option", "_61_reverse_tilt_mirror", "reverse_rear_view_mirror_auto_tilt", "auto_rear_view_mirror_fold", "auto_wiper"});
      this.m0x1AItemTitleArray.append(3, new String[]{"find_car_light_function", "lock_big_light_delay_set"});
      this.m0x1AItemTitleArray.append(4, new String[]{"prevent_open_door_auto_lock", "stop_auto_unlock", "start_auto_lock", "delay_lock"});
      this.m0x1AItemTitleArray.append(5, new String[]{"remote_control_unlock_light_feedback", "remote_control_unlock_light_or_horn_feedback", "remote_unlock_set", "auto_re_lock_doors", "close_unlock_set", "key_forgot_remain", "leave_car_auto_lock", "remote_window_control"});
      this.mDecimalFormat0p0 = new DecimalFormat("0.0");
      this.mTireAlarm = new String[]{CommUtil.getStrByResId(var1, "_61_tire_pressure_was_higher"), CommUtil.getStrByResId(var1, "_61_tire_pressure_was_lower"), CommUtil.getStrByResId(var1, "_61_please_check_tire_pressure")};
      this.mCanbusDataArray = new SparseArray();
   }

   private void initSparseArray() {
      this.mPanelKeyArray = new SparseArray();
      SparseIntArray var1 = new SparseIntArray();
      var1.put(1, 134);
      var1.append(2, 21);
      var1.append(3, 20);
      var1.append(4, 58);
      var1.append(5, 4);
      var1.append(6, 50);
      var1.append(7, 129);
      var1.append(8, 130);
      var1.append(9, 3);
      var1.append(10, 33);
      var1.append(11, 34);
      var1.append(12, 35);
      var1.append(13, 36);
      var1.append(14, 37);
      var1.append(15, 38);
      var1.append(16, 31);
      var1.append(17, 31);
      var1.append(18, 185);
      var1.append(19, 196);
      var1.append(20, 129);
      var1.append(21, 75);
      var1.append(22, 49);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 46);
      var1.append(26, 45);
      var1.append(27, 17);
      var1.append(28, 45);
      var1.append(29, 46);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 141);
      var1.append(52, 48);
      var1.append(53, 47);
      var1.append(80, 52);
      var1.append(81, 2);
      var1.append(82, 94);
      var1.append(83, 52);
      var1.append(84, 59);
      var1.append(85, 128);
      var1.append(86, 128);
      var1.append(87, 128);
      var1.append(88, 47);
      var1.append(89, 48);
      var1.append(90, 45);
      var1.append(91, 46);
      this.mPanelKeyArray.put(0, var1);
      var1 = new SparseIntArray();
      var1.put(1, 134);
      var1.append(2, 21);
      var1.append(3, 20);
      var1.append(4, 58);
      var1.append(5, 4);
      var1.append(6, 50);
      var1.append(7, 129);
      var1.append(8, 130);
      var1.append(9, 184);
      var1.append(10, 33);
      var1.append(11, 34);
      var1.append(12, 35);
      var1.append(13, 36);
      var1.append(14, 37);
      var1.append(15, 38);
      var1.append(16, 31);
      var1.append(17, 31);
      var1.append(18, 185);
      var1.append(19, 196);
      var1.append(20, 129);
      var1.append(21, 75);
      var1.append(22, 49);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 17);
      var1.append(28, 45);
      var1.append(29, 46);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 0);
      var1.append(52, 0);
      var1.append(53, 0);
      var1.append(80, 0);
      var1.append(81, 0);
      var1.append(82, 0);
      var1.append(83, 0);
      var1.append(84, 0);
      var1.append(85, 0);
      var1.append(86, 0);
      var1.append(87, 0);
      var1.append(88, 0);
      var1.append(89, 0);
      var1.append(90, 0);
      var1.append(91, 0);
      this.mPanelKeyArray.append(1, var1);
      var1 = new SparseIntArray();
      var1.put(1, 134);
      var1.append(2, 21);
      var1.append(3, 20);
      var1.append(4, 58);
      var1.append(5, 4);
      var1.append(6, 50);
      var1.append(7, 129);
      var1.append(8, 130);
      var1.append(9, 184);
      var1.append(10, 33);
      var1.append(11, 34);
      var1.append(12, 35);
      var1.append(13, 36);
      var1.append(14, 37);
      var1.append(15, 38);
      var1.append(16, 31);
      var1.append(17, 31);
      var1.append(18, 185);
      var1.append(19, 196);
      var1.append(20, 129);
      var1.append(21, 75);
      var1.append(22, 49);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 0);
      var1.append(28, 0);
      var1.append(29, 0);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 141);
      var1.append(52, 0);
      var1.append(53, 0);
      var1.append(80, 0);
      var1.append(81, 0);
      var1.append(82, 0);
      var1.append(83, 52);
      var1.append(84, 59);
      var1.append(85, 0);
      var1.append(86, 0);
      var1.append(87, 0);
      var1.append(88, 0);
      var1.append(89, 0);
      var1.append(90, 0);
      var1.append(91, 0);
      this.mPanelKeyArray.append(2, var1);
      var1 = new SparseIntArray();
      var1.put(1, 134);
      var1.append(2, 21);
      var1.append(3, 20);
      var1.append(4, 58);
      var1.append(5, 4);
      var1.append(6, 50);
      var1.append(7, 129);
      var1.append(8, 130);
      var1.append(9, 184);
      var1.append(10, 33);
      var1.append(11, 34);
      var1.append(12, 35);
      var1.append(13, 36);
      var1.append(14, 37);
      var1.append(15, 38);
      var1.append(16, 31);
      var1.append(17, 31);
      var1.append(18, 185);
      var1.append(19, 196);
      var1.append(20, 129);
      var1.append(21, 75);
      var1.append(22, 49);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 17);
      var1.append(28, 45);
      var1.append(29, 46);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 0);
      var1.append(52, 0);
      var1.append(53, 0);
      var1.append(80, 0);
      var1.append(81, 0);
      var1.append(82, 0);
      var1.append(83, 0);
      var1.append(84, 0);
      var1.append(85, 0);
      var1.append(86, 0);
      var1.append(87, 0);
      var1.append(88, 0);
      var1.append(89, 0);
      var1.append(90, 0);
      var1.append(91, 0);
      this.mPanelKeyArray.append(3, var1);
      var1 = new SparseIntArray();
      var1.put(1, 134);
      var1.append(2, 21);
      var1.append(3, 20);
      var1.append(4, 58);
      var1.append(5, 4);
      var1.append(6, 50);
      var1.append(7, 129);
      var1.append(8, 130);
      var1.append(9, 3);
      var1.append(10, 33);
      var1.append(11, 34);
      var1.append(12, 35);
      var1.append(13, 36);
      var1.append(14, 37);
      var1.append(15, 38);
      var1.append(16, 31);
      var1.append(17, 31);
      var1.append(18, 185);
      var1.append(19, 196);
      var1.append(20, 129);
      var1.append(21, 75);
      var1.append(22, 49);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 0);
      var1.append(28, 0);
      var1.append(29, 0);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 0);
      var1.append(52, 0);
      var1.append(53, 0);
      var1.append(80, 0);
      var1.append(81, 0);
      var1.append(82, 0);
      var1.append(83, 0);
      var1.append(84, 0);
      var1.append(85, 0);
      var1.append(86, 0);
      var1.append(87, 0);
      var1.append(88, 0);
      var1.append(89, 0);
      var1.append(90, 0);
      var1.append(91, 0);
      this.mPanelKeyArray.append(4, var1);
      var1 = new SparseIntArray();
      var1.put(1, 21);
      var1.append(2, 37);
      var1.append(3, 36);
      var1.append(4, 35);
      var1.append(5, 134);
      var1.append(6, 185);
      var1.append(7, 129);
      var1.append(8, 33);
      var1.append(9, 34);
      var1.append(10, 20);
      var1.append(11, 49);
      var1.append(12, 50);
      var1.append(13, 4);
      var1.append(14, 31);
      var1.append(15, 31);
      var1.append(16, 0);
      var1.append(17, 58);
      var1.append(18, 2);
      var1.append(19, 0);
      var1.append(20, 129);
      var1.append(21, 184);
      var1.append(22, 38);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 0);
      var1.append(28, 0);
      var1.append(29, 0);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 0);
      var1.append(52, 48);
      var1.append(53, 47);
      var1.append(80, 0);
      var1.append(81, 0);
      var1.append(82, 0);
      var1.append(83, 0);
      var1.append(84, 0);
      var1.append(85, 0);
      var1.append(86, 0);
      var1.append(87, 0);
      var1.append(88, 0);
      var1.append(89, 0);
      var1.append(90, 0);
      var1.append(91, 0);
      this.mPanelKeyArray.append(5, var1);
      var1 = new SparseIntArray();
      var1.put(1, 134);
      var1.append(2, 21);
      var1.append(3, 20);
      var1.append(4, 58);
      var1.append(5, 17);
      var1.append(6, 50);
      var1.append(7, 0);
      var1.append(8, 0);
      var1.append(9, 184);
      var1.append(10, 33);
      var1.append(11, 34);
      var1.append(12, 35);
      var1.append(13, 36);
      var1.append(14, 37);
      var1.append(15, 38);
      var1.append(16, 0);
      var1.append(17, 31);
      var1.append(18, 185);
      var1.append(19, 0);
      var1.append(20, 129);
      var1.append(21, 75);
      var1.append(22, 49);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 0);
      var1.append(28, 0);
      var1.append(29, 0);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 0);
      var1.append(52, 0);
      var1.append(53, 0);
      var1.append(80, 52);
      var1.append(81, 2);
      var1.append(82, 94);
      var1.append(83, 0);
      var1.append(84, 0);
      var1.append(85, 128);
      var1.append(86, 128);
      var1.append(87, 128);
      var1.append(88, 47);
      var1.append(89, 48);
      var1.append(90, 45);
      var1.append(91, 46);
      this.mPanelKeyArray.append(6, var1);
      var1 = new SparseIntArray();
      var1.append(1, 52);
      var1.append(2, 50);
      var1.append(3, 49);
      var1.append(4, 185);
      var1.append(5, 129);
      var1.append(6, 20);
      var1.append(7, 0);
      var1.append(8, 0);
      var1.append(9, 21);
      var1.append(10, 75);
      var1.append(11, 134);
      var1.append(12, 33);
      var1.append(13, 34);
      var1.append(14, 35);
      var1.append(15, 36);
      var1.append(16, 0);
      var1.append(17, 31);
      var1.append(18, 14);
      var1.append(19, 0);
      var1.append(20, 58);
      var1.append(21, 0);
      var1.append(22, 2);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 17);
      var1.append(28, 0);
      var1.append(29, 0);
      var1.append(30, 37);
      var1.append(31, 38);
      var1.append(64, 0);
      var1.append(52, 0);
      var1.append(53, 0);
      var1.append(80, 4);
      var1.append(81, 0);
      var1.append(82, 94);
      var1.append(83, 0);
      var1.append(84, 0);
      var1.append(85, 0);
      var1.append(86, 0);
      var1.append(87, 0);
      var1.append(88, 0);
      var1.append(89, 0);
      var1.append(90, 0);
      var1.append(91, 0);
      this.mPanelKeyArray.append(7, var1);
      var1 = new SparseIntArray();
      var1.put(1, 34);
      var1.append(2, 3);
      var1.append(3, 50);
      var1.append(4, 129);
      var1.append(5, 129);
      var1.append(6, 21);
      var1.append(7, 134);
      var1.append(8, 49);
      var1.append(9, 185);
      var1.append(10, 35);
      var1.append(11, 36);
      var1.append(12, 37);
      var1.append(13, 38);
      var1.append(14, 0);
      var1.append(15, 0);
      var1.append(16, 75);
      var1.append(17, 31);
      var1.append(18, 58);
      var1.append(19, 196);
      var1.append(20, 4);
      var1.append(21, 33);
      var1.append(22, 20);
      var1.append(23, 7);
      var1.append(24, 8);
      var1.append(25, 48);
      var1.append(26, 47);
      var1.append(27, 17);
      var1.append(28, 0);
      var1.append(29, 0);
      var1.append(30, 0);
      var1.append(31, 0);
      var1.append(64, 141);
      var1.append(52, 0);
      var1.append(53, 0);
      var1.append(80, 0);
      var1.append(81, 0);
      var1.append(82, 0);
      var1.append(83, 0);
      var1.append(84, 0);
      var1.append(85, 0);
      var1.append(86, 0);
      var1.append(87, 0);
      var1.append(88, 0);
      var1.append(89, 0);
      var1.append(90, 0);
      var1.append(91, 0);
      this.mPanelKeyArray.append(8, var1);
   }

   private boolean isBlowModeMatch(int var1, int... var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         if (var1 == var2[var3]) {
            return true;
         }
      }

      return false;
   }

   private boolean isDoorDataChange() {
      if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
         return false;
      } else {
         this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
         this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
         this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
         this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
         this.mBackStatus = GeneralDoorData.isBackOpen;
         this.mFrontStatus = GeneralDoorData.isFrontOpen;
         return true;
      }
   }

   private void panelKeyKnobSel(int var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[3];
      if (var2 != 0) {
         this.realKeyClick3_2(this.mContext, var1, var3[2], var2);
      }
   }

   private void panelKeyKnobVol(int var1) {
      int[] var3 = this.mCanBusInfoInt;
      int var2 = var3[3];
      if (var2 != 0) {
         this.realKeyClick3_1(this.mContext, var1, var3[2], var2);
      }
   }

   private String resolveAirTemperature(int var1) {
      if (var1 == 0) {
         return "LO";
      } else {
         if (GeneralAirData.fahrenheit_celsius) {
            if (var1 == 254) {
               return "HI";
            }

            if (var1 >= 1 && var1 <= 253) {
               return var1 + this.getTempUnitF(this.mContext);
            }
         } else {
            if (var1 == 30) {
               return "HI";
            }

            if (var1 == 29) {
               return "16.0" + this.getTempUnitC(this.mContext);
            }

            if (var1 == 31) {
               return "16.5" + this.getTempUnitC(this.mContext);
            }

            if (var1 == 32) {
               return "15.0" + this.getTempUnitC(this.mContext);
            }

            if (var1 == 33) {
               return "15.5" + this.getTempUnitC(this.mContext);
            }

            if (var1 == 34) {
               return "31.0" + this.getTempUnitC(this.mContext);
            }

            if (var1 >= 1 && var1 <= 28) {
               return (float)(var1 + 33) / 2.0F + this.getTempUnitC(this.mContext);
            }
         }

         return "";
      }
   }

   private String resolveAirTemperatureRear(int var1) {
      if (var1 == 0) {
         return "LO";
      } else if (var1 == 254) {
         return "HI";
      } else {
         if (GeneralAirData.fahrenheit_celsius) {
            if (var1 >= 1 && var1 <= 253) {
               return var1 + this.getTempUnitF(this.mContext);
            }
         } else if (var1 >= 1 && var1 <= 128) {
            return (float)var1 / 2.0F + this.getTempUnitC(this.mContext);
         }

         return "";
      }
   }

   private void setAirData0x03() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = this.mCanBusInfoInt[3];
         boolean var4 = true;
         int var2 = DataHandleUtils.getIntFromByteWithBit(var1, 7, 1);
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1);
         this.setSettingData(new String[]{"_279_temperature_unit", "_61_vehicle_type_setup"}, new Object[]{var2, var1});
         GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
         this.setOutDoorTemperature();
         byte[] var5 = this.mCanBusInfoByte;
         var5[7] = 0;
         var5[8] = (byte)(var5[8] & 127);
         if (this.isAirMsgRepeat(var5)) {
            return;
         }

         GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralAirData.ac = this.getAirAcStatus();
         GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]) ^ true;
         GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
         GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3) | DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1) << 3;
         GeneralAirData.eco = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
         GeneralAirData.sync = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         boolean var3;
         if (var1 == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         GeneralAirData.front_left_auto_wind = var3;
         GeneralAirData.front_right_auto_wind = var3;
         if (var1 == 2) {
            var3 = var4;
         } else {
            var3 = false;
         }

         GeneralAirData.front_defog = var3;
         var3 = this.isBlowModeMatch(var1, 3, 4, 8, 9);
         GeneralAirData.front_left_blow_foot = var3;
         GeneralAirData.front_right_blow_foot = var3;
         var3 = this.isBlowModeMatch(var1, 4, 5, 6, 9);
         GeneralAirData.front_left_blow_head = var3;
         GeneralAirData.front_right_blow_head = var3;
         var3 = this.isBlowModeMatch(var1, 6, 7, 8, 9);
         GeneralAirData.front_left_blow_window = var3;
         GeneralAirData.front_right_blow_window = var3;
         GeneralAirData.front_left_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[4]);
         GeneralAirData.front_right_temperature = this.resolveAirTemperature(this.mCanBusInfoInt[5]);
         GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
         GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
         GeneralAirData.ac_auto = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]);
         GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]);
         this.updateAirActivity(this.mContext, 1001);
      }

   }

   private void setAirSettings0x05() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
         int var1 = var2;
         if (var2 == 3) {
            var1 = 2;
         }

         var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
         int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
         int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1);
         int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
         int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
         this.setSettingData(this.m0x05ItemTitleArray, new Object[]{var2, var5, var7, var8, var9, var6, var1, var3, var4});
      }

   }

   private void setAoLanDuo(int[] var1) {
      int var2 = var1[3];
      if (var2 != 2) {
         switch (var1[2]) {
            case 0:
               this.realKeyLongClick1(this.mContext, 0, var2);
               break;
            case 1:
               this.realKeyLongClick1(this.mContext, 1, var2);
               break;
            case 2:
               this.realKeyLongClick1(this.mContext, 21, var2);
               break;
            case 3:
               this.realKeyLongClick1(this.mContext, 20, var2);
               break;
            case 4:
               this.realKeyLongClick1(this.mContext, 58, var2);
               break;
            case 5:
               this.realKeyLongClick1(this.mContext, 4122, var2);
               break;
            case 6:
               this.realKeyLongClick1(this.mContext, 50, var2);
               break;
            case 7:
               this.realKeyLongClick1(this.mContext, 62, var2);
               break;
            case 8:
               this.realKeyLongClick1(this.mContext, 141, var2);
               break;
            case 9:
               this.realKeyLongClick1(this.mContext, 184, var2);
               break;
            case 10:
               this.realKeyLongClick1(this.mContext, 33, var2);
               break;
            case 11:
               this.realKeyLongClick1(this.mContext, 34, var2);
               break;
            case 12:
               this.realKeyLongClick1(this.mContext, 35, var2);
               break;
            case 13:
               this.realKeyLongClick1(this.mContext, 36, var2);
               break;
            case 14:
               this.realKeyLongClick1(this.mContext, 37, var2);
               break;
            case 15:
               this.realKeyLongClick1(this.mContext, 38, var2);
               break;
            case 16:
               this.realKeyLongClick1(this.mContext, 128, var2);
               break;
            case 17:
               this.realKeyLongClick1(this.mContext, 31, var2);
               break;
            case 18:
               this.realKeyLongClick1(this.mContext, 39, var2);
               break;
            case 19:
               this.realKeyLongClick1(this.mContext, 196, var2);
               break;
            case 20:
               this.realKeyLongClick1(this.mContext, 0, var2);
               break;
            case 21:
               this.realKeyLongClick1(this.mContext, 75, var2);
               break;
            case 22:
               this.realKeyLongClick1(this.mContext, 49, var2);
               break;
            case 23:
               this.realKeyClick4(this.mContext, 7);
               break;
            case 24:
               this.realKeyClick4(this.mContext, 8);
               break;
            case 25:
               this.realKeyClick4(this.mContext, 47);
               break;
            case 26:
               this.realKeyClick4(this.mContext, 48);
               break;
            case 27:
               this.realKeyLongClick1(this.mContext, 95, var2);
               break;
            case 28:
               this.realKeyLongClick1(this.mContext, 45, var2);
               break;
            case 29:
               this.realKeyLongClick1(this.mContext, 46, var2);
         }

      }
   }

   private void setCanInfo() {
      int[] var2 = this.mCanBusInfoInt;
      int var1 = var2[1];
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 26) {
                  if (var1 != 31) {
                     if (var1 != 38) {
                        if (var1 != 210) {
                           if (var1 != 65) {
                              if (var1 != 66) {
                                 if (var1 != 68) {
                                    if (var1 != 69) {
                                       switch (var1) {
                                          case 5:
                                             this.setAirSettings0x05();
                                             break;
                                          case 6:
                                             this.setVehicleSettings0x06();
                                             break;
                                          case 7:
                                             this.setRadarSettings0x07();
                                             break;
                                          case 8:
                                             this.setOnStarPhoneInfo0x08();
                                             break;
                                          case 9:
                                             this.setOnStarStatus0x09();
                                             break;
                                          case 10:
                                             this.setVehicleSettings0x0A();
                                             break;
                                          case 11:
                                             this.setVehicleSpeed0x0B();
                                             break;
                                          case 12:
                                             this.setLanguageData0x0C();
                                             break;
                                          case 13:
                                             this.setWarnVolume0x0D();
                                             break;
                                          default:
                                             switch (var1) {
                                                case 34:
                                                   this.setRearRadarData0x22();
                                                   break;
                                                case 35:
                                                   this.setFrontRadarData0x23();
                                                   break;
                                                case 36:
                                                   this.setDoorData0x24();
                                                   break;
                                                default:
                                                   switch (var1) {
                                                      case 48:
                                                         this.setVersionInfo0x30();
                                                         break;
                                                      case 49:
                                                         this.setVehicleInfo0x31();
                                                         break;
                                                      case 50:
                                                         this.setVehicleInfo0x32();
                                                         break;
                                                      case 51:
                                                         this.setTirePressure0x33();
                                                   }
                                             }
                                       }
                                    } else {
                                       this.setRearAirData0x45();
                                    }
                                 } else {
                                    this.setVehicleAirSetting0x44();
                                 }
                              } else {
                                 this.setWirelessPassword0x42();
                              }
                           } else {
                              this.setWirelessPoint0x41();
                           }
                        } else {
                           this.setCompassData0xD2();
                        }
                     } else {
                        this.setTrackData0x26();
                     }
                  } else {
                     this.setWirelessInfo0x1F();
                  }
               } else {
                  this.setSettingEable0x1A();
               }
            } else {
               this.setAirData0x03();
            }
         } else {
            this.setPanelkey0x02(var2);
         }
      } else {
         this.setWheelKey0x01();
      }

   }

   private void setCompassData0xD2() {
      Context var3 = this.mContext;
      String var2;
      if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
         var2 = "compass_calibrating";
      } else {
         var2 = "compass_calibration_finish";
      }

      String var6 = CommUtil.getStrByResId(var3, var2);
      String var5 = Integer.toString(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
      String var7 = (float)(this.mCanBusInfoInt[3] * 3) / 2.0F + "°";
      ArrayList var4 = new ArrayList();
      int var1 = 0;

      while(true) {
         String[] var10 = this.m0xD2ItemTitleArray;
         if (var1 >= var10.length) {
            this.updateGeneralSettingData(var4);
            this.updateSettingActivity((Bundle)null);
            return;
         }

         String var8 = var10[var1];
         int[] var11 = this.getLeftAndRight(var8);
         if (var11[0] != -1 && var11[1] != -1) {
            Object var9 = (new Object[]{var6, var5, var7})[var1];
            SettingUpdateEntity var12 = new SettingUpdateEntity(var11[0], var11[1], var9);
            SettingUpdateEntity var13 = var12;
            if ("compass_zoom".equals(var8)) {
               var13 = var12.setProgress((Integer)var9);
            }

            var4.add(var13);
         }

         ++var1;
      }
   }

   private void setDoorData0x24() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
         GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
         GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
         boolean var1;
         if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])) {
            var1 = true;
         } else {
            var1 = false;
         }

         GeneralDoorData.isBackOpen = var1;
         if (this.isDoorDataChange()) {
            this.updateDoorView(this.mContext);
         }

         DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
      }

   }

   private void setFrontRadarData0x23() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setFrontRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setKeLuZi(int[] var1) {
      int var2 = var1[3];
      if (var2 != 2) {
         switch (var1[2]) {
            case 0:
               this.realKeyLongClick1(this.mContext, 0, var2);
               break;
            case 1:
               this.realKeyLongClick1(this.mContext, 21, var2);
               break;
            case 2:
               this.realKeyLongClick1(this.mContext, 21, var2);
               break;
            case 3:
               this.realKeyLongClick1(this.mContext, 20, var2);
               break;
            case 4:
               this.realKeyLongClick1(this.mContext, 58, var2);
               break;
            case 5:
               this.realKeyLongClick1(this.mContext, 4122, var2);
               break;
            case 6:
               this.realKeyLongClick1(this.mContext, 50, var2);
               break;
            case 7:
               this.realKeyLongClick1(this.mContext, 62, var2);
               break;
            case 8:
               this.realKeyLongClick1(this.mContext, 141, var2);
               break;
            case 9:
               this.realKeyLongClick1(this.mContext, 184, var2);
               break;
            case 10:
               this.realKeyLongClick1(this.mContext, 33, var2);
               break;
            case 11:
               this.realKeyLongClick1(this.mContext, 34, var2);
               break;
            case 12:
               this.realKeyLongClick1(this.mContext, 35, var2);
               break;
            case 13:
               this.realKeyLongClick1(this.mContext, 36, var2);
               break;
            case 14:
               this.realKeyLongClick1(this.mContext, 37, var2);
               break;
            case 15:
               this.realKeyLongClick1(this.mContext, 38, var2);
               break;
            case 16:
               this.realKeyLongClick1(this.mContext, 89, var2);
               break;
            case 17:
               this.realKeyLongClick1(this.mContext, 31, var2);
               break;
            case 18:
               this.realKeyLongClick1(this.mContext, 128, var2);
               break;
            case 19:
               this.realKeyLongClick1(this.mContext, 196, var2);
               break;
            case 20:
               this.realKeyLongClick1(this.mContext, 4123, var2);
               break;
            case 21:
               this.realKeyLongClick1(this.mContext, 75, var2);
               break;
            case 22:
               this.realKeyLongClick1(this.mContext, 49, var2);
               break;
            case 23:
               this.realKeyClick4(this.mContext, 7);
               break;
            case 24:
               this.realKeyClick4(this.mContext, 8);
               break;
            case 25:
               this.realKeyClick4(this.mContext, 47);
               break;
            case 26:
               this.realKeyClick4(this.mContext, 48);
               break;
            case 27:
               this.realKeyLongClick1(this.mContext, 95, var2);
               break;
            case 28:
               this.realKeyLongClick1(this.mContext, 45, var2);
               break;
            case 29:
               this.realKeyLongClick1(this.mContext, 46, var2);
         }

      }
   }

   private void setLanguageData0x0C() {
      ArrayList var1 = new ArrayList();
      var1.add(new SettingUpdateEntity(this.getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), this.getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), this.mCanBusInfoInt[2]));
      this.updateGeneralSettingData(var1);
      this.updateSettingActivity((Bundle)null);
   }

   private void setLeFeng(int[] var1) {
      int var2 = var1[3];
      if (var2 != 2) {
         int var3 = var1[2];
         if (var3 != 0) {
            if (var3 != 1) {
               if (var3 != 6) {
                  if (var3 != 7) {
                     if (var3 != 28) {
                        if (var3 != 29) {
                           if (var3 != 80) {
                              if (var3 != 83) {
                                 if (var3 != 84) {
                                    switch (var3) {
                                       case 23:
                                          this.realKeyClick4(this.mContext, 7);
                                          break;
                                       case 24:
                                          this.realKeyClick4(this.mContext, 8);
                                          break;
                                       case 25:
                                          this.realKeyClick4(this.mContext, 47);
                                          break;
                                       case 26:
                                          this.realKeyClick4(this.mContext, 48);
                                    }
                                 } else {
                                    this.realKeyLongClick1(this.mContext, 2, var2);
                                 }
                              } else {
                                 this.realKeyLongClick1(this.mContext, 151, var2);
                              }
                           } else {
                              this.realKeyLongClick1(this.mContext, 52, var2);
                           }
                        } else {
                           this.realKeyLongClick1(this.mContext, 46, var2);
                        }
                     } else {
                        this.realKeyLongClick1(this.mContext, 45, var2);
                     }
                  } else {
                     this.realKeyLongClick1(this.mContext, 62, var2);
                  }
               } else {
                  this.realKeyLongClick1(this.mContext, 50, var2);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 1, var2);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 0, var2);
         }

      }
   }

   private void setNewJunWei(int[] var1) {
      int var2 = var1[3];
      if (var2 != 2) {
         switch (var1[2]) {
            case 0:
               this.realKeyLongClick1(this.mContext, 0, var2);
               break;
            case 1:
               this.realKeyLongClick1(this.mContext, 1, var2);
               break;
            case 2:
               this.realKeyLongClick1(this.mContext, 21, var2);
               break;
            case 3:
               this.realKeyLongClick1(this.mContext, 20, var2);
               break;
            case 4:
               this.realKeyLongClick1(this.mContext, 58, var2);
               break;
            case 5:
               this.realKeyLongClick1(this.mContext, 4122, var2);
               break;
            case 6:
               this.realKeyLongClick1(this.mContext, 50, var2);
               break;
            case 7:
               this.realKeyLongClick1(this.mContext, 62, var2);
               break;
            case 8:
               this.realKeyLongClick1(this.mContext, 141, var2);
               break;
            case 9:
               this.realKeyLongClick1(this.mContext, 3, var2);
               break;
            case 10:
               this.realKeyLongClick1(this.mContext, 33, var2);
               break;
            case 11:
               this.realKeyLongClick1(this.mContext, 34, var2);
               break;
            case 12:
               this.realKeyLongClick1(this.mContext, 35, var2);
               break;
            case 13:
               this.realKeyLongClick1(this.mContext, 36, var2);
               break;
            case 14:
               this.realKeyLongClick1(this.mContext, 37, var2);
               break;
            case 15:
               this.realKeyLongClick1(this.mContext, 38, var2);
               break;
            case 16:
               this.realKeyLongClick1(this.mContext, 128, var2);
               break;
            case 17:
               this.realKeyLongClick1(this.mContext, 31, var2);
               break;
            case 18:
               this.realKeyLongClick1(this.mContext, 39, var2);
               break;
            case 19:
               this.realKeyLongClick1(this.mContext, 196, var2);
               break;
            case 20:
               this.realKeyLongClick1(this.mContext, 76, var2);
               break;
            case 21:
               this.realKeyLongClick1(this.mContext, 75, var2);
               break;
            case 22:
               this.realKeyLongClick1(this.mContext, 49, var2);
               break;
            case 23:
               this.realKeyClick4(this.mContext, 7);
               break;
            case 24:
               this.realKeyClick4(this.mContext, 8);
               break;
            case 25:
               this.realKeyClick4(this.mContext, 47);
               break;
            case 26:
               this.realKeyClick4(this.mContext, 48);
         }

      }
   }

   private void setNewJunYun(int[] var1) {
      int var2 = var1[3];
      if (var2 != 2) {
         int var3 = var1[2];
         if (var3 != 17) {
            if (var3 != 18) {
               if (var3 != 52) {
                  if (var3 != 53) {
                     switch (var3) {
                        case 0:
                           this.realKeyLongClick1(this.mContext, 0, var2);
                           break;
                        case 1:
                           this.realKeyLongClick1(this.mContext, 21, var2);
                           break;
                        case 2:
                           this.realKeyLongClick1(this.mContext, 37, var2);
                           break;
                        case 3:
                           this.realKeyLongClick1(this.mContext, 36, var2);
                           break;
                        case 4:
                           this.realKeyLongClick1(this.mContext, 35, var2);
                           break;
                        case 5:
                           this.realKeyLongClick1(this.mContext, 1, var2);
                           break;
                        case 6:
                           this.realKeyLongClick1(this.mContext, 58, var2);
                           break;
                        case 7:
                           this.realKeyLongClick1(this.mContext, 62, var2);
                           break;
                        case 8:
                           this.realKeyLongClick1(this.mContext, 33, var2);
                           break;
                        case 9:
                           this.realKeyLongClick1(this.mContext, 34, var2);
                           break;
                        case 10:
                           this.realKeyLongClick1(this.mContext, 20, var2);
                           break;
                        case 11:
                           this.realKeyLongClick1(this.mContext, 128, var2);
                           break;
                        case 12:
                           this.realKeyLongClick1(this.mContext, 50, var2);
                           break;
                        case 13:
                           this.realKeyLongClick1(this.mContext, 4122, var2);
                           break;
                        case 14:
                           this.realKeyLongClick1(this.mContext, 31, var2);
                           break;
                        case 15:
                           this.realKeyLongClick1(this.mContext, 31, var2);
                           break;
                        default:
                           switch (var3) {
                              case 20:
                                 this.realKeyLongClick1(this.mContext, 62, var2);
                                 break;
                              case 21:
                                 this.realKeyLongClick1(this.mContext, 184, var2);
                                 break;
                              case 22:
                                 this.realKeyLongClick1(this.mContext, 38, var2);
                                 break;
                              case 23:
                                 this.realKeyClick4(this.mContext, 7);
                                 break;
                              case 24:
                                 this.realKeyClick4(this.mContext, 8);
                                 break;
                              case 25:
                                 this.realKeyClick4(this.mContext, 47);
                                 break;
                              case 26:
                                 this.realKeyClick4(this.mContext, 48);
                           }
                     }
                  } else {
                     this.realKeyClick4(this.mContext, 46);
                  }
               } else {
                  this.realKeyClick4(this.mContext, 45);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 2, var2);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 227, var2);
         }

      }
   }

   private void setOnStarPhoneInfo0x08() {
      byte[] var1 = this.mCanBusInfoByte;
      GeneralOnStartData.mOnStarPhoneNum = DataHandleUtils.bcd2Str(Arrays.copyOfRange(var1, 2, var1.length));
      this.updateOnStarActivity(1001);
   }

   private void setOnStarStatus0x09() {
      GeneralOnStartData.mOnStarStatus = this.mCanBusInfoInt[2];
      this.updateOnStarActivity(1001);
   }

   private void setOutDoorTemperature() {
      if (this.mOutDoorTemperature != this.mCanBusInfoInt[7] || this.mAirTmeperatureUnit != GeneralAirData.fahrenheit_celsius) {
         this.mOutDoorTemperature = this.mCanBusInfoInt[7];
         this.mAirTmeperatureUnit = GeneralAirData.fahrenheit_celsius;
         if (GeneralAirData.fahrenheit_celsius) {
            this.updateOutDoorTemp(this.mContext, this.mCanBusInfoInt[7] + this.getTempUnitF(this.mContext));
         } else {
            this.updateOutDoorTemp(this.mContext, this.mCanBusInfoByte[7] + this.getTempUnitC(this.mContext));
         }

      }
   }

   private void setPanelkey0x02(int[] var1) {
      if (this.getCurrentEachCanId() == 9) {
         this.setYingLangPanel(var1);
      } else if (this.getCurrentEachCanId() == 10) {
         this.setKeLuZi(var1);
      } else if (this.getCurrentEachCanId() == 11) {
         this.setAoLanDuo(var1);
      } else if (this.getCurrentEachCanId() == 12) {
         this.setNewJunWei(var1);
      } else if (this.getCurrentEachCanId() == 13) {
         this.setNewJunYun(var1);
      } else if (this.getCurrentEachCanId() == 14) {
         this.setLeFeng(var1);
      } else {
         int var2 = ((SparseIntArray)this.mPanelKeyArray.get(this.mEachId)).get(var1[2]);
         Log.i(this.TAG, "setPanelkey0x02: diff:" + this.mEachId + ", key:" + var2);
         int var3 = var1[2];
         if (var3 != 52 && var3 != 53) {
            switch (var3) {
               case 23:
               case 24:
                  this.panelKeyKnobVol(var2);
                  return;
               case 25:
               case 26:
                  break;
               default:
                  this.wheelKeyClick(var2);
                  return;
            }
         }

         this.panelKeyKnobSel(var2);
      }

   }

   private void setRadarSettings0x07() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = this.mCanBusInfoInt[2];
         int var2 = this.mCanBusInfoInt[2];
         this.setSettingData(new String[]{"_143_0x76_d0_b6", "_61_radar_switch"}, new Object[]{var1, var2});
      }

   }

   private void setRearAirData0x45() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         GeneralAirData.rear_temperature = this.resolveAirTemperatureRear(this.mCanBusInfoInt[2]);
         GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
         int var1 = GeneralAirData.rear_wind_level;
         boolean var3 = true;
         boolean var2;
         if (var1 == 15) {
            var2 = true;
         } else {
            var2 = false;
         }

         GeneralAirData.rear_auto_wind_speed = var2;
         var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
         if (var1 == 1) {
            var2 = var3;
         } else {
            var2 = false;
         }

         GeneralAirData.rear_left_auto_wind = var2;
         GeneralAirData.rear_left_blow_head = this.isBlowModeMatch(var1, 2, 3);
         GeneralAirData.rear_left_blow_foot = this.isBlowModeMatch(var1, 3, 4);
         GeneralAirData.rear_right_auto_wind = GeneralAirData.rear_left_auto_wind;
         GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
         GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
         GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
         GeneralAirData.rear_lock = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
         this.updateAirActivity(this.mContext, 1002);
      }

   }

   private void setRearRadarData0x22() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var1 = this.mCanBusInfoInt;
         RadarInfoUtil.setRearRadarLocationData(7, var1[2], var1[3], var1[4], var1[5]);
         GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setSettingData(String[] var1, Object[] var2) {
      ArrayList var5 = new ArrayList();

      for(int var3 = 0; var3 < var1.length; ++var3) {
         int[] var6 = this.getLeftAndRight(var1[var3]);
         int var4 = var6[0];
         if (var4 != -1 && var6[1] != -1) {
            Object var7 = var2[var3];
            SettingRightItem var8 = (SettingRightItem)((List)this.mSettingRightItemArray.get(var4)).get(var6[1]);
            var8.setValue(var7);
            var5.add((new SettingUpdateEntity(var6[0], var6[1], var7)).setEnable(var8.isEnable()));
         }
      }

      this.updateGeneralSettingData(var5);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingData(String[] var1, boolean[] var2) {
      ArrayList var7 = new ArrayList();

      for(int var3 = 0; var3 < var1.length; ++var3) {
         int[] var8 = this.getLeftAndRight(var1[var3]);
         int var4 = var8[0];
         if (var4 != -1 && var8[1] != -1) {
            boolean var5 = var2[var3];
            SettingRightItem var6 = (SettingRightItem)((List)this.mSettingRightItemArray.get(var4)).get(var8[1]);
            var6.setEnable(var5);
            var7.add((new SettingUpdateEntity(var8[0], var8[1], var6.getValue())).setEnable(var5));
         }
      }

      this.updateGeneralSettingData(var7);
      this.updateSettingActivity((Bundle)null);
   }

   private void setSettingEable0x1A() {
      int[] var3 = this.mCanBusInfoInt;
      int var1 = var3[2];
      boolean[] var2;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 5) {
                        return;
                     }

                     var2 = new boolean[]{DataHandleUtils.getBoolBit0(var3[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])};
                  } else {
                     var2 = new boolean[]{DataHandleUtils.getBoolBit0(var3[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])};
                  }
               } else {
                  var2 = new boolean[]{DataHandleUtils.getBoolBit0(var3[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])};
               }
            } else {
               var2 = new boolean[]{DataHandleUtils.getBoolBit0(var3[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])};
            }
         } else {
            var2 = new boolean[]{DataHandleUtils.getBoolBit1(var3[3])};
         }
      } else {
         var2 = new boolean[]{DataHandleUtils.getBoolBit0(var3[3]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]), DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])};
      }

      this.setSettingData((String[])this.m0x1AItemTitleArray.get(this.mCanBusInfoInt[2]), var2);
   }

   private void setTirePressure0x33() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         ArrayList var5 = new ArrayList();

         for(int var1 = 0; var1 < 4; ++var1) {
            ArrayList var6 = new ArrayList();
            StringBuilder var7 = new StringBuilder();
            int[] var8 = this.mCanBusInfoInt;
            int var3 = var1 * 2;
            int var2 = var8[var3 + 3];
            int var4 = var8[var3 + 2];
            byte var10 = 1;
            var6.add(var7.append(this.getData(var2, var4)).append("").toString());
            var4 = this.mCanBusInfoInt[var1 + 12];
            var2 = 0;

            while(true) {
               String[] var11 = this.mTireAlarm;
               if (var2 >= var11.length) {
                  if (var6.size() == 1) {
                     var6.add("");
                  }

                  byte var9 = var10;
                  if (var4 == 0) {
                     var9 = 0;
                  }

                  var5.add(new TireUpdateEntity(var1, var9, (String[])var6.toArray(new String[0])));
                  break;
               }

               if ((1 << var2 & var4) != 0) {
                  var6.add(var11[var2]);
               }

               ++var2;
            }
         }

         GeneralTireData.dataList = var5;
         this.updateTirePressureActivity((Bundle)null);
      }

   }

   private void setTrackData0x26() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(var1[2], var1[3], 0, 7800, 16);
         this.updateParkUi((Bundle)null, this.mContext);
      }

   }

   private void setVehicleAirSetting0x44() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 2);
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
         this.setSettingData(this.m0x44ItemTitleArray, new Object[]{var1, var5, var3, var2, var4});
      }

   }

   private void setVehicleInfo0x31() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         String var1 = (float)this.mCanBusInfoInt[3] / 10.0F + CommUtil.getStrByResId(this.mContext, "unit_v");
         StringBuilder var2 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         String var5 = var2.append(this.getData(var3[9], var3[8], var3[7], var3[6])).append(" km/h").toString();
         ArrayList var4 = new ArrayList();
         var4.add(new DriverUpdateEntity(0, 1, var1));
         var4.add(new DriverUpdateEntity(0, 2, var5));
         this.updateGeneralDriveData(var4);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setVehicleInfo0x32() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         StringBuilder var2 = new StringBuilder();
         int[] var1 = this.mCanBusInfoInt;
         String var4 = var2.append(this.getData(var1[3], var1[2])).append(" rpm").toString();
         var2 = new StringBuilder();
         int[] var3 = this.mCanBusInfoInt;
         String var6 = var2.append((float)this.getData(var3[5], var3[4]) / 10.0F).append(" l").toString();
         ArrayList var5 = new ArrayList();
         var5.add(new DriverUpdateEntity(0, 3, var4));
         var5.add(new DriverUpdateEntity(0, 4, var6));
         this.updateGeneralDriveData(var5);
         this.updateDriveDataActivity((Bundle)null);
      }

   }

   private void setVehicleSettings0x06() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
         int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
         int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 2);
         int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1);
         int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
         int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
         this.setSettingData(this.m0x06ItemTitleArray, new Object[]{var5, var2, var9, var10, var4, var6, var7, var8, var3, var1});
      }

   }

   private void setVehicleSettings0x0A() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int var16 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
         int var23 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
         int var9 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
         int var3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1);
         int var6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1);
         int var11 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1);
         int var8 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
         int var21 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
         int var22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
         int var19 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
         int var2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1);
         int var13 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
         int var18 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1);
         int var20 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1);
         int var5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1);
         int var10 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2);
         int var12 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1);
         int var14 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1);
         int var25 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
         int var17 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
         int var24 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1);
         int var1 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1);
         int var15 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1);
         int var7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1);
         int var4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
         this.setSettingData(this.m0x0AItemTitleArray, new Object[]{var16, var23, var9, var3, var6, var11, var8, var21, var22, var19, var2, var13, var18, var20, var5, var10, var12, var14, var25, var17, var24, var1, var15, var7, var4});
      }

   }

   private void setVehicleSpeed0x0B() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var2 = this.mCanBusInfoInt;
         int var1 = this.getData(var2[3], var2[2]) / 16;
         ArrayList var3 = new ArrayList();
         var3.add(new DriverUpdateEntity(0, 0, var1 + " km/h"));
         this.updateGeneralDriveData(var3);
         this.updateDriveDataActivity((Bundle)null);
         this.updateSpeedInfo(var1);
      }

   }

   private void setVersionInfo0x30() {
      this.updateVersionInfo(this.mContext, this.getVersionStr(this.mCanBusInfoByte));
   }

   private void setWarnVolume0x0D() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         int[] var2 = this.getLeftAndRight("warning_volume_set");
         if (var2[0] != -1 && var2[1] != -1) {
            ArrayList var1 = new ArrayList();
            var1.add((new SettingUpdateEntity(var2[0], var2[1], this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[2]));
            this.updateGeneralSettingData(var1);
            this.updateSettingActivity((Bundle)null);
         }
      }

   }

   private void setWheelKey0x01() {
      switch (this.mCanBusInfoInt[2]) {
         case 0:
            this.wheelKeyClick(0);
            break;
         case 1:
            this.wheelKeyClick(7);
            break;
         case 2:
            this.wheelKeyClick(8);
            break;
         case 3:
            this.wheelKeyClick(45);
            break;
         case 4:
            this.wheelKeyClick(46);
            break;
         case 5:
            this.wheelKeyClick(2);
            break;
         case 6:
            this.wheelKeyClick(201);
            break;
         case 7:
            this.wheelKeyClick(184);
            break;
         case 8:
            this.wheelKeyClick(187);
            break;
         case 9:
            this.wheelKeyClick(184);
      }

   }

   private void setWirelessInfo0x1F() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         byte[] var2 = Arrays.copyOfRange(var1, 2, var1.length);

         try {
            String var4 = new String(var2, "GB2312");
            GeneralOnStartData.mOnStarWirelessInfo = var4;
         } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
         }

         this.updateOnStarActivity(1002);
      }

   }

   private void setWirelessPassword0x42() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         try {
            String var1 = new String(this.getAsciiDatas(this.mCanBusInfoByte), "GB2312");
            GeneralOnStartData.mOnStarWirelessPassword = var1;
         } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
         }

         this.updateOnStarActivity(1002);
      }

   }

   private void setWirelessPoint0x41() {
      if (this.isDataChange(this.mCanBusInfoInt)) {
         byte[] var1 = this.mCanBusInfoByte;
         byte[] var2 = Arrays.copyOfRange(var1, 2, var1.length);

         try {
            String var4 = new String(var2, "GB2312");
            GeneralOnStartData.mOnStarWirelessPoint = var4;
         } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
         }

         this.updateOnStarActivity(1002);
      }

   }

   private void setYingLangPanel(int[] var1) {
      int var2 = var1[3];
      if (var2 != 2) {
         int var3 = var1[2];
         if (var3 != 0) {
            if (var3 != 64) {
               switch (var3) {
                  case 2:
                     this.realKeyLongClick1(this.mContext, 1, var2);
                     break;
                  case 3:
                     this.realKeyLongClick1(this.mContext, 21, var2);
                     break;
                  case 4:
                     this.realKeyLongClick1(this.mContext, 20, var2);
                     break;
                  case 5:
                     this.realKeyLongClick1(this.mContext, 58, var2);
                     break;
                  case 6:
                     this.realKeyLongClick1(this.mContext, 50, var2);
                     break;
                  case 7:
                     this.realKeyLongClick1(this.mContext, 62, var2);
                     break;
                  case 8:
                     this.realKeyLongClick1(this.mContext, 4113, var2);
                     break;
                  case 9:
                     this.realKeyLongClick1(this.mContext, 184, var2);
                     break;
                  case 10:
                     this.realKeyLongClick1(this.mContext, 33, var2);
                     break;
                  case 11:
                     this.realKeyLongClick1(this.mContext, 34, var2);
                     break;
                  case 12:
                     this.realKeyLongClick1(this.mContext, 35, var2);
                     break;
                  case 13:
                     this.realKeyLongClick1(this.mContext, 36, var2);
                     break;
                  case 14:
                     this.realKeyLongClick1(this.mContext, 37, var2);
                     break;
                  case 15:
                     this.realKeyLongClick1(this.mContext, 38, var2);
                     break;
                  case 16:
                     this.realKeyLongClick1(this.mContext, 4, var2);
                     break;
                  case 17:
                     this.realKeyLongClick1(this.mContext, 31, var2);
                     break;
                  case 18:
                     this.realKeyLongClick1(this.mContext, 128, var2);
                     break;
                  case 19:
                     this.realKeyLongClick1(this.mContext, 196, var2);
                     break;
                  case 20:
                     this.realKeyLongClick1(this.mContext, 76, var2);
                     break;
                  case 21:
                     this.realKeyLongClick1(this.mContext, 75, var2);
                     break;
                  case 22:
                     this.realKeyLongClick1(this.mContext, 49, var2);
                     break;
                  case 23:
                     this.realKeyClick4(this.mContext, 7);
                     break;
                  case 24:
                     this.realKeyClick4(this.mContext, 8);
                     break;
                  case 25:
                     this.realKeyClick4(this.mContext, 45);
                     break;
                  case 26:
                     this.realKeyClick4(this.mContext, 46);
               }
            } else {
               this.realKeyLongClick1(this.mContext, 141, var2);
            }
         } else {
            this.realKeyLongClick1(this.mContext, 0, var2);
         }

      }
   }

   private void wheelKeyClick(int var1) {
      if (this.mCanBusInfoInt[3] == 1) {
         this.realKeyClick4(this.mContext, var1);
      }

   }

   public void afterServiceNormalSetting(Context var1) {
      super.afterServiceNormalSetting(var1);
      SelectCanTypeUtil.enableApp(var1, Constant.OnStarActivity);
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.mCanBusInfoByte = var2;
      this.mCanBusInfoInt = this.getByteArrayToIntArray(var2);
      this.mContext = var1;

      try {
         this.setCanInfo();
      } catch (Exception var3) {
         Log.e("CanBusError", var3.toString());
      }

   }

   public void deviceStatusInfoChange(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12) {
      super.deviceStatusInfoChange(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12);
      if (this.mDiscExist != var4) {
         this.mDiscExist = var4;
         CanbusMsgSender.sendMsg(new byte[]{22, -117, (byte)var4});
      }

   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
      this.mContext = var1;
      this.mDifferentId = this.getCurrentCanDifferentId();
      this.mEachId = this.getCurrentEachCanId();
      GeneralTireData.isHaveSpareTire = false;
      RadarInfoUtil.mMinIsClose = true;
      this.initSparseArray();
      this.initData(var1);
      this.initSettingsItem(this.getUiMgrInterface().getSettingUiSet(var1));
   }

   void initSettingsItem(SettingPageUiSet var1) {
      Log.i(this.TAG, "initSettingsItem: ");
      this.mSettingRightItemArray = new SparseArray();
      this.mSettingItemIndeHashMap = new HashMap();

      for(int var2 = 0; var2 < var1.getList().size(); ++var2) {
         List var5 = ((SettingPageUiSet.ListBean)var1.getList().get(var2)).getItemList();
         ArrayList var4 = new ArrayList();

         for(int var3 = 0; var3 < var5.size(); ++var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)var5.get(var3)).getTitleSrn();
            this.mSettingItemIndeHashMap.put(var6, var2 << 8 & '\uff00' | var3);
            var4.add(new SettingRightItem(this, var3));
         }

         this.mSettingRightItemArray.append(var2, var4);
      }

   }

   void startSettingActivity() {
      int[] var1 = this.getLeftAndRight("_194_automatic_mode_wind");
      this.startSettingActivity(this.mContext, var1[0], var1[1]);
   }

   void updateSettings(int var1, int var2, int var3) {
      ArrayList var4 = new ArrayList();
      var4.add(new SettingUpdateEntity(var1, var2, var3));
      this.updateGeneralSettingData(var4);
      this.updateSettingActivity((Bundle)null);
   }

   private class SettingRightItem {
      boolean enable;
      int index;
      final MsgMgr this$0;
      Object value;

      private SettingRightItem(MsgMgr var1, int var2) {
         this.this$0 = var1;
         this.index = var2;
         this.value = new Object();
         this.enable = true;
      }

      // $FF: synthetic method
      SettingRightItem(MsgMgr var1, int var2, Object var3) {
         this(var1, var2);
      }

      public int getIndex() {
         return this.index;
      }

      public Object getValue() {
         return this.value;
      }

      public boolean isEnable() {
         return this.enable;
      }

      public SettingRightItem setEnable(boolean var1) {
         this.enable = var1;
         return this;
      }

      public SettingRightItem setValue(Object var1) {
         this.value = var1;
         return this;
      }
   }
}
