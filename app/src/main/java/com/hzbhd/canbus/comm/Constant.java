package com.hzbhd.canbus.comm;

import android.content.ComponentName;

public class Constant {
   public static final ComponentName AirActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.AirActivity");
   public static final ComponentName AuxInActivity = new ComponentName("com.hzbhd.ui.activity", "com.hzbhd.ui.activity.AuxInActivity");
   public static final String BUNDLE_KEY_ORINAL_INIT_VIEW = "bundle_key_orinal_init_view";
   public static final String CURRENT_ITEM = "current_item";
   public static final ComponentName CanBusDiagnosisActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.CanBusDiagnosisActivity");
   public static final ComponentName CanBusMainActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.MainActivity");
   public static final ComponentName ChengWeiMainActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._273.MainActivity");
   public static final ComponentName ChengWeiMainActivity290 = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._290.MainActivity");
   public static final String DRIVE_DATA_OPEN_TARGET_PAGE = "drive_data_open_target_page";
   public static final ComponentName DZMainActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._283.MainActivity");
   public static final ComponentName DZMainActivity291 = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._291.MainActivity");
   public static final ComponentName EnergyFeedbackActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._277.activity.EnergyFeedbackActivity");
   public static final ComponentName FCameraActivity = new ComponentName("com.hzbhd.ui.camera", "com.hzbhd.ui.activity.FCameraActivity");
   public static final ComponentName FrontCameraSettingActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.FrontCameraSettingActivity");
   public static final ComponentName ID448DvrActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._448.activity.DvrActivity");
   public static final String INTENT_BROADCAST_FINISH_AIR_ACTIVITY = "intent_broadcast_finish_air_activity";
   public static final String LEFT_INDEX = "left_index ";
   public static final ComponentName Launcher = new ComponentName("com.android.launcher3", "com.android.launcher3.activity.MainActivity");
   public static final ComponentName MdDvrActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._436.activity.DvrActivity");
   public static final ComponentName OnStarActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.OnStarActivity");
   public static final ComponentName OriginalDeviceActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.OriginalCarDeviceActivity");
   public static final String RIGHT_INDEX = "right_index";
   public static final String SETTING_OPEN_TARGET_PAGE = "setting_open_target_page";
   public static final String SHARE_HOR_PROGRESS = "share_hor_progress";
   public static final String SHARE_IS_OPEN_FRONT_CAMERA = "share_is_open_F.Camera";
   public static final String SHARE_IS_PANORAMIC = "share_is_panoramic";
   public static final String SHARE_LR_PROGRESS = "share_lr_progress";
   public static final String SHARE_PRE_CAN_DIFFERENT_ID = "share_pre_can_different_id";
   public static final String SHARE_PRE_CAN_TYPE_ID = "share_pre_can_type_id";
   public static final String SHARE_PRE_EACH_CAN_ID = "share_pre_each_can_id";
   public static final String SHARE_PRE_IS_DEBUG_MODEL = "share_pre_is_debug_model";
   public static final String SHARE_PRE_IS_FIRST_TIME_LAUNCHER = "share_pre_is_first_time_launcher";
   public static final String SHARE_PRE_IS_USE_F_UNIT = "share_pre_is_use_f_unit";
   public static final String SHARE_PRE_IS_USE_NEW_APP = "share_pre_is_use_new_app";
   public static final String SHARE_PRE_LAST_VERSION_CODE = "share_pre_last_version_code";
   public static final String SHARE_SELECT_CAR_POSITION = "share_select_car_position";
   public static final String SHARE_VER_PROGRESS = "share_ver_progress";
   public static final ComponentName SwcActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.SwcActivity");
   public static final ComponentName SyncActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.SyncActivity");
   public static final ComponentName TireInfoActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.activity.TirePressureActivity");
   public static final ComponentName VehicleDiagnosisActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._277.activity.VehicleDiagnosisActivity");
   public static final ComponentName VehicleMonitorActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._277.activity.VehicleMonitorActivity");

   public static enum RadarLocation {
      private static final RadarLocation[] $VALUES;
      FRONT_LEFT,
      FRONT_LEFT_PROBE,
      FRONT_MID_LEFT,
      FRONT_MID_RIGHT,
      FRONT_RIGHT,
      FRONT_RIGHT_PROBE,
      LEFT_FRONT,
      LEFT_MID_FRONT,
      LEFT_MID_REAR,
      LEFT_REAR,
      REAR_LEFT,
      REAR_LEFT_PROBE,
      REAR_MID_LEFT,
      REAR_MID_RIGHT,
      REAR_RIGHT,
      REAR_RIGHT_PROBE,
      RIGHT_FRONT,
      RIGHT_MID_FRONT,
      RIGHT_MID_REAR,
      RIGHT_REAR;

      static {
         RadarLocation var8 = new RadarLocation("FRONT_LEFT", 0);
         FRONT_LEFT = var8;
         RadarLocation var3 = new RadarLocation("FRONT_MID_LEFT", 1);
         FRONT_MID_LEFT = var3;
         RadarLocation var2 = new RadarLocation("FRONT_MID_RIGHT", 2);
         FRONT_MID_RIGHT = var2;
         RadarLocation var12 = new RadarLocation("FRONT_RIGHT", 3);
         FRONT_RIGHT = var12;
         RadarLocation var17 = new RadarLocation("FRONT_LEFT_PROBE", 4);
         FRONT_LEFT_PROBE = var17;
         RadarLocation var0 = new RadarLocation("FRONT_RIGHT_PROBE", 5);
         FRONT_RIGHT_PROBE = var0;
         RadarLocation var9 = new RadarLocation("REAR_LEFT", 6);
         REAR_LEFT = var9;
         RadarLocation var5 = new RadarLocation("REAR_MID_LEFT", 7);
         REAR_MID_LEFT = var5;
         RadarLocation var10 = new RadarLocation("REAR_MID_RIGHT", 8);
         REAR_MID_RIGHT = var10;
         RadarLocation var6 = new RadarLocation("REAR_RIGHT", 9);
         REAR_RIGHT = var6;
         RadarLocation var1 = new RadarLocation("REAR_LEFT_PROBE", 10);
         REAR_LEFT_PROBE = var1;
         RadarLocation var16 = new RadarLocation("REAR_RIGHT_PROBE", 11);
         REAR_RIGHT_PROBE = var16;
         RadarLocation var11 = new RadarLocation("LEFT_FRONT", 12);
         LEFT_FRONT = var11;
         RadarLocation var19 = new RadarLocation("LEFT_MID_FRONT", 13);
         LEFT_MID_FRONT = var19;
         RadarLocation var7 = new RadarLocation("LEFT_MID_REAR", 14);
         LEFT_MID_REAR = var7;
         RadarLocation var18 = new RadarLocation("LEFT_REAR", 15);
         LEFT_REAR = var18;
         RadarLocation var14 = new RadarLocation("RIGHT_FRONT", 16);
         RIGHT_FRONT = var14;
         RadarLocation var13 = new RadarLocation("RIGHT_MID_FRONT", 17);
         RIGHT_MID_FRONT = var13;
         RadarLocation var4 = new RadarLocation("RIGHT_MID_REAR", 18);
         RIGHT_MID_REAR = var4;
         RadarLocation var15 = new RadarLocation("RIGHT_REAR", 19);
         RIGHT_REAR = var15;
         $VALUES = new RadarLocation[]{var8, var3, var2, var12, var17, var0, var9, var5, var10, var6, var1, var16, var11, var19, var7, var18, var14, var13, var4, var15};
      }
   }
}
