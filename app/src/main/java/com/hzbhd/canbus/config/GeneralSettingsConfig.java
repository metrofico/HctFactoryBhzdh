package com.hzbhd.canbus.config;

public class GeneralSettingsConfig {
   public boolean AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG;
   public boolean AIR_SHOW_TAG;
   public boolean BACK_TRAJECTORY_REVERSAL;
   public boolean CAN_BUS_TEST_TAG;
   public boolean COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH;
   public boolean DOOR_SHOW_TAG;
   public boolean FRONT_CAMERA_TAG;
   public boolean FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG;
   public boolean GENERAL_SETTING_PAGE_SHOW;
   public boolean OPEN_CAN_DATA_LOG;
   public boolean OUT_TEMPERATURE_SHOW_TAG;
   public boolean RADAR_SHOW_TAG;
   public boolean REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG;
   public boolean SHOW_HIDE_SMART_PANEL_PAGE;
   public boolean SHOW_P_KEY_RADAR_WINDOW;
   public boolean SWC_STUDY_TAG;
   public boolean TEMPERATURE_UNIT_TAG;
   public boolean THE_HOOD_SHOW_TAG;
   public boolean TOUCH_PAD_SETTING_TAG;
   public boolean TRUNK_SHOW_TAG;
   public boolean WIKA_CAR_SELECT;

   private GeneralSettingsConfig() {
      this.GENERAL_SETTING_PAGE_SHOW = true;
      this.RADAR_SHOW_TAG = true;
      this.BACK_TRAJECTORY_REVERSAL = true;
      this.DOOR_SHOW_TAG = true;
      this.FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG = true;
      this.REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG = true;
      this.THE_HOOD_SHOW_TAG = true;
      this.TRUNK_SHOW_TAG = true;
      this.AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG = true;
      this.AIR_SHOW_TAG = true;
      this.OUT_TEMPERATURE_SHOW_TAG = true;
      this.TEMPERATURE_UNIT_TAG = true;
      this.SWC_STUDY_TAG = true;
      this.FRONT_CAMERA_TAG = true;
      this.TOUCH_PAD_SETTING_TAG = false;
      this.CAN_BUS_TEST_TAG = false;
      this.COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH = true;
      this.SHOW_HIDE_SMART_PANEL_PAGE = false;
      this.SHOW_P_KEY_RADAR_WINDOW = false;
      this.WIKA_CAR_SELECT = false;
      this.OPEN_CAN_DATA_LOG = true;
   }

   // $FF: synthetic method
   GeneralSettingsConfig(Object var1) {
      this();
   }

   public static GeneralSettingsConfig getInstance(int var0) {
      GeneralSettingsConfig.Holder.INSTANCE.setConfig(var0);
      return GeneralSettingsConfig.Holder.INSTANCE;
   }

   private void set0x453Config() {
      this.OUT_TEMPERATURE_SHOW_TAG = false;
      this.FRONT_CAMERA_TAG = false;
   }

   private void set0x455Config() {
      this.RADAR_SHOW_TAG = false;
      this.OUT_TEMPERATURE_SHOW_TAG = false;
      this.FRONT_CAMERA_TAG = false;
   }

   private void set0x461Config() {
      this.OUT_TEMPERATURE_SHOW_TAG = false;
      this.FRONT_CAMERA_TAG = false;
   }

   private void set161Config() {
      this.TOUCH_PAD_SETTING_TAG = false;
   }

   private void set1Config() {
      this.SHOW_P_KEY_RADAR_WINDOW = true;
   }

   private void set213Config() {
      this.TOUCH_PAD_SETTING_TAG = true;
   }

   private void set2Config() {
      this.SHOW_P_KEY_RADAR_WINDOW = true;
   }

   private void set3Config() {
      this.SHOW_P_KEY_RADAR_WINDOW = true;
   }

   private void set436Config() {
      this.RADAR_SHOW_TAG = false;
      this.DOOR_SHOW_TAG = false;
      this.FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
      this.REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
      this.THE_HOOD_SHOW_TAG = false;
      this.TRUNK_SHOW_TAG = false;
      this.SWC_STUDY_TAG = false;
      this.TOUCH_PAD_SETTING_TAG = false;
      this.CAN_BUS_TEST_TAG = false;
   }

   private void set437Config() {
      this.FRONT_CAMERA_TAG = false;
   }

   private void set439Config() {
      this.COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH = false;
      this.OUT_TEMPERATURE_SHOW_TAG = false;
      this.AIR_SHOW_TAG = false;
      this.RADAR_SHOW_TAG = false;
      this.DOOR_SHOW_TAG = false;
      this.FRONT_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
      this.REAR_DOOR_LEFT_RIGHT_EXCHANGE_TAG = false;
      this.AIR_LEFT_RIGHT_TEMPERATURE_EXCHANGE_TAG = false;
      this.THE_HOOD_SHOW_TAG = false;
      this.TRUNK_SHOW_TAG = false;
      this.TEMPERATURE_UNIT_TAG = false;
      this.FRONT_CAMERA_TAG = false;
      this.SHOW_HIDE_SMART_PANEL_PAGE = true;
      this.WIKA_CAR_SELECT = true;
   }

   private void set445Config() {
      this.GENERAL_SETTING_PAGE_SHOW = false;
   }

   private void set451Config() {
      this.OUT_TEMPERATURE_SHOW_TAG = false;
      this.FRONT_CAMERA_TAG = false;
   }

   private void set452Config() {
      this.OUT_TEMPERATURE_SHOW_TAG = false;
      this.FRONT_CAMERA_TAG = false;
   }

   private void set4Config() {
      this.SHOW_P_KEY_RADAR_WINDOW = true;
   }

   private void setConfig(int var1) {
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 != 4) {
                  if (var1 != 161) {
                     if (var1 != 213) {
                        if (var1 != 434) {
                           if (var1 != 437) {
                              if (var1 != 439) {
                                 if (var1 != 445) {
                                    if (var1 != 455) {
                                       if (var1 != 461) {
                                          switch (var1) {
                                             case 451:
                                                this.set451Config();
                                                return;
                                             case 452:
                                                this.set452Config();
                                                return;
                                             case 453:
                                                this.set0x453Config();
                                                return;
                                             default:
                                                return;
                                          }
                                       }

                                       this.set0x461Config();
                                    }

                                    this.set0x455Config();
                                 } else {
                                    this.set445Config();
                                 }
                              } else {
                                 this.set439Config();
                              }
                           } else {
                              this.set437Config();
                           }
                        } else {
                           this.set436Config();
                        }
                     } else {
                        this.set213Config();
                     }
                  } else {
                     this.set161Config();
                  }
               } else {
                  this.set4Config();
               }
            } else {
               this.set3Config();
            }
         } else {
            this.set2Config();
         }
      } else {
         this.set1Config();
      }

   }

   private static class Holder {
      private static final GeneralSettingsConfig INSTANCE = new GeneralSettingsConfig();
   }
}
