package com.hzbhd.canbus.factory.proxy;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.commontools.utils.DocumentTool;
import com.hzbhd.commontools.utils.bhdJsonUtils;
import com.hzbhd.config.use.CanBusDefault;

public class CanSettingProxy {
   private static final String TAG = "CanSettingProxy";
   private final String AIR_DISPLAY_SETUP = "air_display_setup";
   private final String AIR_OUTDOOR_TEMPERATURE_DISPLAY = "air_outdoor_temperature_display";
   private final String AIR_SWITCH_TEMPERATURE = "air_switch_temperature";
   private final String AIR_TEMPERATURE_UNIT = "air_temperature_unit";
   private final String BACK_TRAJECTORY_REVERSAL = "back_trajectiry_reversal";
   private final String CAN_SETTINGS_SHOW = "can_settings_show";
   private final String COUNT_DOWN_TIMER_REMOVE_DOOR_VIEW_SWITCH = "count_down_timer_remove_door_view_switch";
   private final String DOOR_FRONT_SWAP = "door_front_swap";
   private final String DOOR_REAR_SWAP = "door_rear_swap";
   private final String DOOR_SHOW_INFO = "door_show_info";
   private final String DOOR_SHOW_THE_HOOD = "door_show_the_hood";
   private final String DOOR_SHOW_THE_TRUNK = "door_show_the_trunk";
   private final String FILE_NAME = "can_settings.json";
   private final String KEY_SWITCH_RIGHT_LEFT = "key_switch_right_left";
   private final String KEY_SWITCH_UP_DN = "key_switch_up_dn";
   private final String OPEN_CAN_DATA_LOG = "open.can.data.log";
   private final String PATH = "/bhd/appconfig/CanBus/";
   private final String P_KEY_RADAR_DISPLAY = "p.key.radar.display";
   private final String RADAR_DISPLAY = "radar_display";
   private bhdJsonUtils mUtils = null;

   public CanSettingProxy(Context var1) {
      synchronized(CanSettingProxy.class){}

      Throwable var10000;
      boolean var10001;
      label195: {
         label189: {
            String var23;
            try {
               var23 = DocumentTool.readFileContent("/bhd/appconfig/CanBus/can_settings.json");
               if (TextUtils.isEmpty(var23)) {
                  bhdJsonUtils var24 = new bhdJsonUtils("");
                  this.mUtils = var24;
                  Log.e("CanSettingProxy", "CanSettingProxy: init --> mUtils=null");
                  break label189;
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label195;
            }

            try {
               bhdJsonUtils var2 = new bhdJsonUtils(var23);
               this.mUtils = var2;
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label195;
            }
         }

         label180:
         try {
            return;
         } catch (Throwable var20) {
            var10000 = var20;
            var10001 = false;
            break label180;
         }
      }

      while(true) {
         Throwable var25 = var10000;

         try {
            throw var25;
         } catch (Throwable var19) {
            var10000 = var19;
            var10001 = false;
            continue;
         }
      }
   }

   private void save() {
      if (this.mUtils.checkMainJson()) {
         DocumentTool.writeData("/bhd/appconfig/CanBus/can_settings.json", this.mUtils.ObjectToString());
      }

   }

   public int getAirDisplaySetup() {
      return this.mUtils.optInt("air_display_setup", 0);
   }

   public boolean getBackTrajectiryDispCheck() {
      return this.mUtils.optBoolean("back_trajectiry_reversal", false);
   }

   public boolean getCanDataLogSwith() {
      return this.mUtils.optBoolean("open.can.data.log", false);
   }

   public boolean getCanSettingShow() {
      return CanBusDefault.INSTANCE.getCanSettingShow();
   }

   public boolean getDoorCountDownTimerState() {
      return this.mUtils.optBoolean("count_down_timer_remove_door_view_switch", false);
   }

   public boolean getDoorSwapFront() {
      return this.mUtils.optBoolean("door_front_swap", false);
   }

   public boolean getDoorSwapRear() {
      return this.mUtils.optBoolean("door_rear_swap", false);
   }

   public boolean getPKeyRadarDispCheck() {
      return this.mUtils.optBoolean("p.key.radar.display", false);
   }

   public boolean getRadarDispCheck() {
      return this.mUtils.optBoolean("radar_display", false);
   }

   public boolean getShowDoorInfo() {
      return this.mUtils.optBoolean("door_show_info", false);
   }

   public boolean getShowHood() {
      return this.mUtils.optBoolean("door_show_the_hood", false);
   }

   public boolean getShowOutdoorTemperature() {
      return this.mUtils.optBoolean("air_outdoor_temperature_display", false);
   }

   public boolean getShowTrunk() {
      return this.mUtils.optBoolean("door_show_the_trunk", false);
   }

   public boolean getSwitchAcTemperature() {
      return this.mUtils.optBoolean("air_switch_temperature", false);
   }

   public boolean getSwitchKeyRightLeft() {
      return this.mUtils.optBoolean("key_switch_right_left", false);
   }

   public boolean getSwitchKeyUpDn() {
      return this.mUtils.optBoolean("key_switch_up_dn", false);
   }

   public int getTemperatureUnit() {
      return this.mUtils.optInt("air_temperature_unit", 0);
   }

   public void setAirDisplaySetup(int param1) {
      // $FF: Couldn't be decompiled
   }

   public void setBackTrajectiryDispCheck(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setCanDataLogSwith(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setDoorCountDownTimerState(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setDoorSwapFront(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setDoorSwapRear(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setPKeyRadarDispCheck(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setRadarDispCheck(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setShowDoorInfo(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setShowHood(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setShowOutdoorTemperature(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setShowTrunk(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setSwitchAcTemperature(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setSwitchKeyRightLeft(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setSwitchKeyUpDn(boolean param1) {
      // $FF: Couldn't be decompiled
   }

   public void setTemperatureUnit(int var1) {
      synchronized(CanSettingProxy.class){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            this.mUtils.putObject("air_temperature_unit", var1);
            this.save();
            if (this.getShowOutdoorTemperature()) {
               CanbusInfoChangeListener.getInstance().reportOutdoorTemperature();
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            return;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label119;
         }
      }

      while(true) {
         Throwable var2 = var10000;

         try {
            throw var2;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }
}
