package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus132settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mBack_lateral;
   private HCheckBoxPreference mDoor_warning;
   private HCheckBoxPreference mDoubling;
   private HCheckBoxPreference mEngine_stop;
   private HCheckBoxPreference mFatigue;
   private HCheckBoxPreference mForward_system;
   private SwitchboxPreference mHeadlight_off;
   private SwitchboxPreference mHome_lighting;
   private SwitchboxPreference mInstrument_color;
   private HCheckBoxPreference mLane_warning;
   private SwitchboxPreference mPower_saving;
   private SwitchboxPreference mRain_sensor;
   private SwitchboxPreference mRear_view;
   private HCheckBoxPreference mSafe_driving;
   private HCheckBoxPreference mTraffic_system;
   private HCheckBoxPreference mTrailer_settings;
   private SwitchboxPreference mWarning_sensi;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData5AA5((byte)108, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   private void SetCmdkey6D(int var1, int var2) {
      this.SendCanBusCmdData5AA5((byte)109, new byte[]{(byte)var1, (byte)var2, -1, -1}, 4);
   }

   protected void ProcessData(byte[] var1) {
      boolean var3 = true;
      boolean var2 = true;
      super.length = var1[1] & 255;
      super.cmd = var1[0] & 255;
      HCheckBoxPreference var4;
      if (super.cmd == 103 && super.length >= 2) {
         this.mHeadlight_off.setValue(var1[3] >> 2 & 3);
         this.mHome_lighting.setValue(var1[3] >> 5 & 3);
         this.mPower_saving.setValue(var1[3] >> 0 & 3);
         this.mRain_sensor.setValue(var1[5] >> 1 & 1);
         var4 = this.mTrailer_settings;
         if ((var1[5] & 1) == 0) {
            var2 = false;
         }

         var4.setChecked(var2);
      } else if (super.cmd == 104 && super.length >= 8) {
         this.mRear_view.setValue(var1[2] >> 1 & 1);
         var4 = this.mEngine_stop;
         if ((var1[2] & 1) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mFatigue;
         if ((var1[2] & 4) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mForward_system;
         if ((var1[3] & 128) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mSafe_driving;
         if ((var1[3] & 64) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         this.mWarning_sensi.setValue(var1[3] >> 4 & 3);
         var4 = this.mLane_warning;
         if ((var1[2] & 16) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mTraffic_system;
         if ((var1[2] & 8) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mDoubling;
         if ((8 & var1[3]) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mBack_lateral;
         if ((var1[3] & 4) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mDoor_warning;
         if ((2 & var1[3]) != 0) {
            var2 = var3;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         this.mInstrument_color.setValue(var1[4] & 3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492875);
      this.mHeadlight_off = this.findSwitchboxPreference("headlight_off");
      this.mHome_lighting = this.findSwitchboxPreference("home_lighting");
      this.mPower_saving = this.findSwitchboxPreference("power_saving");
      this.mRain_sensor = this.findSwitchboxPreference("rain_sensor");
      this.mRear_view = this.findSwitchboxPreference("rear_view");
      this.mWarning_sensi = this.findSwitchboxPreference("warning_sensi");
      this.mInstrument_color = this.findSwitchboxPreference("instrument_color");
      this.mTrailer_settings = this.findHCheckBoxPreference("trailer_settings");
      this.mEngine_stop = this.findHCheckBoxPreference("engine_stop");
      this.mFatigue = this.findHCheckBoxPreference("fatigue");
      this.mForward_system = this.findHCheckBoxPreference("forward_system");
      this.mSafe_driving = this.findHCheckBoxPreference("safe_driving");
      this.mLane_warning = this.findHCheckBoxPreference("lane_warning");
      this.mTraffic_system = this.findHCheckBoxPreference("traffic_system");
      this.mDoubling = this.findHCheckBoxPreference("doubling");
      this.mBack_lateral = this.findHCheckBoxPreference("back_lateral");
      this.mDoor_warning = this.findHCheckBoxPreference("door_warning");
      this.ProcessData(this.GetCarByteArrayState(103));
      this.ProcessData(this.GetCarByteArrayState(104));
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3;
      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var5) {
         return false;
      }

      int var4;
      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mHeadlight_off) {
            this.SetCmdkey(5, var3);
         } else if (var1 == this.mHome_lighting) {
            this.SetCmdkey(6, var3);
         } else if (var1 == this.mPower_saving) {
            this.SetCmdkey(7, var3);
         } else if (var1 == this.mRain_sensor) {
            this.SetCmdkey6D(18, var3);
         } else if (var1 == this.mRear_view) {
            this.SetCmdkey6D(0, var3);
         } else if (var1 == this.mWarning_sensi) {
            this.SetCmdkey6D(11, var3);
         } else if (var1 == this.mInstrument_color) {
            this.SetCmdkey6D(17, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var13 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var14 = this.mTrailer_settings;
      byte var7 = 0;
      byte var4 = 0;
      byte var9 = 0;
      byte var11 = 0;
      byte var3 = 0;
      byte var6 = 0;
      byte var8 = 0;
      byte var12 = 0;
      byte var5 = 0;
      byte var10 = 0;
      if (var2 == var14) {
         if (var13) {
            var3 = var10;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(19, var3);
      } else if (var2 == this.mEngine_stop) {
         if (var13) {
            var3 = var7;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(1, var3);
      } else if (var2 == this.mFatigue) {
         if (var13) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(8, var3);
      } else if (var2 == this.mForward_system) {
         if (var13) {
            var3 = var9;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(9, var3);
      } else if (var2 == this.mSafe_driving) {
         if (var13) {
            var3 = var11;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(10, var3);
      } else if (var2 == this.mLane_warning) {
         if (!var13) {
            var3 = 1;
         }

         this.SetCmdkey6D(12, var3);
      } else if (var2 == this.mTraffic_system) {
         if (var13) {
            var3 = var6;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(13, var3);
      } else if (var2 == this.mDoubling) {
         if (var13) {
            var3 = var8;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(14, var3);
      } else if (var2 == this.mBack_lateral) {
         if (var13) {
            var3 = var12;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(15, var3);
      } else if (var2 == this.mDoor_warning) {
         if (var13) {
            var3 = var5;
         } else {
            var3 = 1;
         }

         this.SetCmdkey6D(16, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
