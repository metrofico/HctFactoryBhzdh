package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus138settings extends BasePreferenceActivity {
   private HCheckBoxPreference mActive_display;
   private HCheckBoxPreference mAdaptive;
   private SwitchboxPreference mAlarm_distance;
   private SwitchboxPreference mAlarm_timing;
   private SwitchboxPreference mAlarm_volume;
   private SwitchboxPreference mAuto_light_open;
   private SwitchboxPreference mAuto_lights_off;
   private SwitchboxPreference mAuto_lights_off2;
   private HCheckBoxPreference mAutomatic_latch;
   private SwitchboxPreference mBackwindowdefog;
   private HCheckBoxPreference mBlind_spot;
   private SwitchboxPreference mBlind_spot2;
   private SwitchboxPreference mBrightness;
   private SwitchboxPreference mBrightness_control;
   private SwitchboxPreference mCar_light_off;
   private HCheckBoxPreference mCruise_conf;
   private PreferenceScreen mDriving_display;
   private PreferenceScreen mDrss;
   private SwitchboxPreference mDrss_alarm_distance;
   private HCheckBoxPreference mDrss_mode;
   private PreferenceScreen mFuel_economy;
   private SwitchboxPreference mHeadlight_off_timer;
   private SwitchboxPreference mHeight;
   private HCheckBoxPreference mHome_lighting;
   private PreferenceScreen mHud;
   private HCheckBoxPreference mHud_active_display;
   private SwitchboxPreference mInclination;
   private SwitchboxPreference mInstrument_style;
   private HCheckBoxPreference mIntelligent_brake;
   private SwitchboxPreference mKeyless_lock_vol;
   private SwitchboxPreference mLock_mode;
   private HCheckBoxPreference mMode;
   private SwitchboxPreference mMonitor_volume;
   private HCheckBoxPreference mNavigation;
   private SwitchboxPreference mOuto_lock_mode;
   private HCheckBoxPreference mParking_guidance;
   private HCheckBoxPreference mPedal_alarm;
   private SwitchboxPreference mRange;
   private SwitchboxPreference mRear_park_vol;
   private HCheckBoxPreference mRear_traffic;
   private HCheckBoxPreference mRear_view;
   private SwitchboxPreference mRumbling_vol;
   private HCheckBoxPreference mRunning_lights;
   private SwitchboxPreference mSecurity;
   private PreferenceScreen mSecurity_sbs;
   private SwitchboxPreference mSound;
   private HCheckBoxPreference mSpeed_warning;
   private HCheckBoxPreference mSync_avg;
   private SwitchboxPreference mTank_lock;
   private SwitchboxPreference mTemp;
   private HCheckBoxPreference mThree_signal;
   private SwitchboxPreference mTimer;
   private SwitchboxPreference mTurn_volume;
   private SwitchboxPreference mVehicle_warning_vol;
   private SwitchboxPreference mWarning2;
   private SwitchboxPreference mWarning_distance;
   private SwitchboxPreference mWarning_mode;
   private SwitchboxPreference mWhispering_vol;
   private HCheckBoxPreference mWipers_induction;

   private void SetCmdkey(int var1, int var2) {
      byte[] var3 = new byte[]{10, 0, (byte)var1, (byte)var2};
      this.SendCanBusCmdData5AA5((byte)126, var3, 4);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = super.cmd;
      boolean var4 = false;
      boolean var3 = true;
      HCheckBoxPreference var6;
      if (var2 == 120) {
         SwitchboxPreference var5 = this.mOuto_lock_mode;
         if ((var1[4] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mAuto_lights_off;
         if ((var1[4] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var6 = this.mAutomatic_latch;
         if ((var1[4] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var5 = this.mKeyless_lock_vol;
         if ((var1[4] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mSecurity;
         if ((var1[4] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mLock_mode;
         if ((var1[4] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var6 = this.mThree_signal;
         if ((var1[4] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var5 = this.mTurn_volume;
         if ((var1[4] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var6 = this.mWipers_induction;
         if ((var1[5] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var5 = this.mAuto_lights_off2;
         if ((var1[5] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mAuto_light_open;
         if ((var1[5] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mCar_light_off;
         if ((var1[5] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var6 = this.mAdaptive;
         if ((var1[5] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         PreferenceScreen var7 = this.mDriving_display;
         if ((var1[5] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var6 = this.mIntelligent_brake;
         if ((var1[5] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var7 = this.mFuel_economy;
         if ((var1[5] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var5 = this.mMonitor_volume;
         if ((var1[6] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mHeadlight_off_timer;
         if ((var1[6] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var7 = this.mSecurity_sbs;
         if ((var1[6] & 56) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var7 = this.mDrss;
         if ((var1[6] & 6) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var6 = this.mBlind_spot;
         if ((var1[6] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var5 = this.mBlind_spot2;
         if ((var1[6] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var6 = this.mRear_traffic;
         if ((var1[7] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var5 = this.mAlarm_timing;
         if ((var1[7] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var6 = this.mParking_guidance;
         if ((var1[7] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var5 = this.mRear_park_vol;
         if ((var1[7] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var6 = this.mPedal_alarm;
         if ((var1[7] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var6 = this.mCruise_conf;
         if ((var1[7] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var6 = this.mRear_view;
         if ((var1[7] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         var5 = this.mBackwindowdefog;
         if ((var1[7] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mTank_lock;
         if ((var1[8] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mInstrument_style;
         if ((var1[8] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mVehicle_warning_vol;
         if ((var1[8] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var7 = this.mHud;
         if ((var1[8] & 30) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         this.mOuto_lock_mode.setValue(var1[9] >> 5 & 7);
         this.mAuto_lights_off.setValue(var1[9] >> 3 & 3);
         this.mKeyless_lock_vol.setValue(var1[9] >> 0 & 3);
         this.mSecurity.setValue(var1[10] >> 6 & 3);
         this.mLock_mode.setValue(var1[10] >> 5 & 1);
         this.mTurn_volume.setValue(var1[10] >> 3 & 1);
         this.mAuto_lights_off2.setValue(var1[10] >> 0 & 3);
         this.mAuto_light_open.setValue(var1[11] >> 5 & 7);
         this.mCar_light_off.setValue(var1[11] >> 2 & 3);
         this.mBrightness_control.setValue(var1[12] >> 6 & 1);
         this.mMonitor_volume.setValue(var1[12] >> 0 & 3);
         this.mHeadlight_off_timer.setValue(7 & var1[13] >> 5);
         this.mAlarm_distance.setValue(var1[13] >> 3 & 1);
         this.mAlarm_volume.setValue(var1[13] >> 1 & 3);
         this.mDrss_alarm_distance.setValue(var1[14] >> 5 & 3);
         this.mWarning_mode.setValue(var1[14] >> 1 & 3);
         this.mWarning_distance.setValue(var1[14] >> 3 & 3);
         this.mWarning2.setValue(var1[15] >> 6 & 3);
         this.mTimer.setValue(var1[15] >> 4 & 3);
         this.mSound.setValue(var1[15] >> 3 & 1);
         this.mWhispering_vol.setValue(var1[15] >> 2 & 1);
         this.mRumbling_vol.setValue(var1[15] >> 0 & 3);
         this.mRange.setValue(var1[16] >> 5 & 1);
         this.mTemp.setValue(var1[16] >> 4 & 1);
         this.mBlind_spot2.setValue(var1[17] >> 6 & 3);
         this.mAlarm_timing.setValue(var1[17] >> 3 & 3);
         this.mRear_park_vol.setValue(var1[17] >> 0 & 3);
         this.mBackwindowdefog.setValue(var1[16] >> 2 & 1);
         this.mTank_lock.setValue(var1[17] >> 5 & 3);
         this.mInstrument_style.setValue(var1[16] >> 0 & 3);
         this.mVehicle_warning_vol.setValue(3 & var1[17] >> 3);
         var6 = this.mAutomatic_latch;
         if ((var1[9] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mThree_signal;
         if ((var1[10] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mWipers_induction;
         if ((var1[10] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mAdaptive;
         if ((2 & var1[11]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mActive_display;
         if ((var1[12] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mNavigation;
         if ((var1[12] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mIntelligent_brake;
         if ((var1[12] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mSync_avg;
         if ((var1[12] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mMode;
         if ((var1[13] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mDrss_mode;
         if ((var1[14] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mBlind_spot;
         if ((var1[12] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mSpeed_warning;
         if ((var1[14] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mHome_lighting;
         if ((var1[16] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mRunning_lights;
         if ((var1[16] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mRear_traffic;
         if ((var1[17] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mParking_guidance;
         if ((var1[17] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mPedal_alarm;
         if ((var1[11] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mCruise_conf;
         if ((var1[13] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mRear_view;
         var3 = var4;
         if ((var1[16] & 8) != 0) {
            var3 = true;
         }

         var6.setChecked(var3);
      } else if (var2 == 121) {
         var6 = this.mHud_active_display;
         if ((var1[2] & 128) == 0) {
            var3 = false;
         }

         var6.setChecked(var3);
         this.mHeight.setValue(var1[2] >> 2 & 31);
         this.mBrightness.setValue(var1[3] >> 4 & 15);
         this.mInclination.setValue(var1[3] >> 0 & 15);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492879);
      CharSequence[] var6 = new CharSequence[31];
      CharSequence[] var4 = new CharSequence[var6.length];
      this.mAutomatic_latch = this.findHCheckBoxPreference("automatic_latch");
      this.mThree_signal = this.findHCheckBoxPreference("three_signal");
      this.mWipers_induction = this.findHCheckBoxPreference("wipers_induction");
      this.mAdaptive = this.findHCheckBoxPreference("adaptive");
      this.mActive_display = this.findHCheckBoxPreference("active_display");
      this.mNavigation = this.findHCheckBoxPreference("navigation");
      this.mIntelligent_brake = this.findHCheckBoxPreference("intelligent_brake");
      this.mSync_avg = this.findHCheckBoxPreference("sync_avg");
      this.mMode = this.findHCheckBoxPreference("mode");
      this.mDrss_mode = this.findHCheckBoxPreference("drss_mode");
      this.mBlind_spot = this.findHCheckBoxPreference("blind_spot");
      this.mSpeed_warning = this.findHCheckBoxPreference("speed_warning");
      this.mHome_lighting = this.findHCheckBoxPreference("home_lighting");
      this.mRunning_lights = this.findHCheckBoxPreference("running_lights");
      this.mRear_traffic = this.findHCheckBoxPreference("rear_traffic");
      this.mParking_guidance = this.findHCheckBoxPreference("parking_guidance");
      this.mPedal_alarm = this.findHCheckBoxPreference("pedal_alarm");
      this.mCruise_conf = this.findHCheckBoxPreference("cruise_conf");
      this.mRear_view = this.findHCheckBoxPreference("rear_view");
      this.mHud_active_display = this.findHCheckBoxPreference("hud_active_display");
      this.mOuto_lock_mode = this.findSwitchboxPreference("outo_lock_mode");
      this.mAuto_lights_off = this.findSwitchboxPreference("auto_lights_off");
      this.mKeyless_lock_vol = this.findSwitchboxPreference("keyless_lock_vol");
      this.mSecurity = this.findSwitchboxPreference("security");
      this.mLock_mode = this.findSwitchboxPreference("lock_mode");
      this.mTurn_volume = this.findSwitchboxPreference("turn_volume");
      this.mAuto_lights_off2 = this.findSwitchboxPreference("auto_lights_off2");
      this.mAuto_light_open = this.findSwitchboxPreference("auto_light_open");
      this.mCar_light_off = this.findSwitchboxPreference("car_light_off");
      this.mBrightness_control = this.findSwitchboxPreference("brightness_control");
      this.mMonitor_volume = this.findSwitchboxPreference("monitor_volume");
      this.mHeadlight_off_timer = this.findSwitchboxPreference("headlight_off_timer");
      this.mAlarm_distance = this.findSwitchboxPreference("alarm_distance");
      this.mAlarm_volume = this.findSwitchboxPreference("alarm_volume");
      this.mDrss_alarm_distance = this.findSwitchboxPreference("drss_alarm_distance");
      this.mWarning_mode = this.findSwitchboxPreference("warning_mode");
      this.mWarning_distance = this.findSwitchboxPreference("warning_distance");
      this.mWarning2 = this.findSwitchboxPreference("warning2");
      this.mTimer = this.findSwitchboxPreference("timer");
      this.mSound = this.findSwitchboxPreference("sound");
      this.mWhispering_vol = this.findSwitchboxPreference("whispering_vol");
      this.mRumbling_vol = this.findSwitchboxPreference("rumbling_vol");
      this.mRange = this.findSwitchboxPreference("range");
      this.mTemp = this.findSwitchboxPreference("temp");
      this.mBlind_spot2 = this.findSwitchboxPreference("blind_spot2");
      this.mAlarm_timing = this.findSwitchboxPreference("alarm_timing");
      this.mRear_park_vol = this.findSwitchboxPreference("rear_park_vol");
      this.mTank_lock = this.findSwitchboxPreference("tank_lock");
      this.mBackwindowdefog = this.findSwitchboxPreference("backwindowdefog");
      this.mInstrument_style = this.findSwitchboxPreference("instrument_style");
      this.mVehicle_warning_vol = this.findSwitchboxPreference("vehicle_warning_vol");
      this.mHeight = this.findSwitchboxPreference("height");
      byte var3 = 0;

      int var2;
      StringBuilder var5;
      for(var2 = 0; var2 < var6.length; ++var2) {
         var5 = new StringBuilder();
         var5.append("");
         var5.append(var2 - 15);
         var6[var2] = var5.toString();
         var5 = new StringBuilder();
         var5.append("");
         var5.append(var2);
         var4[var2] = var5.toString();
      }

      this.mHeight.setEntries(var6);
      this.mHeight.setEntryValues(var4);
      var4 = new CharSequence[11];
      var6 = new CharSequence[var4.length];

      for(var2 = var3; var2 < var4.length; ++var2) {
         var5 = new StringBuilder();
         var5.append("");
         var5.append(var2 - 5);
         var4[var2] = var5.toString();
         var5 = new StringBuilder();
         var5.append("");
         var5.append(var2);
         var6[var2] = var5.toString();
      }

      this.mBrightness = this.findSwitchboxPreference("brightness");
      this.mBrightness.setEntries(var4);
      this.mBrightness.setEntryValues(var6);
      this.mInclination = this.findSwitchboxPreference("inclination");
      this.mInclination.setEntries(var4);
      this.mInclination.setEntryValues(var6);
      this.mDriving_display = this.findPreferenceScreen("driving_display");
      this.mFuel_economy = this.findPreferenceScreen("fuel_economy");
      this.mSecurity_sbs = this.findPreferenceScreen("security_sbs");
      this.mDrss = this.findPreferenceScreen("drss");
      this.mHud = this.findPreferenceScreen("hud");
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 120}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 121}, 3);
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
         String var7 = var1.getKey();
         if ("outo_lock_mode".equals(var7)) {
            this.SetCmdkey(1, var3);
         } else if ("auto_lights_off".equals(var7)) {
            this.SetCmdkey(2, var3);
         } else if ("keyless_lock_vol".equals(var7)) {
            this.SetCmdkey(4, var3);
         } else if ("security".equals(var7)) {
            this.SetCmdkey(5, var3);
         } else if ("lock_mode".equals(var7)) {
            this.SetCmdkey(6, var3);
         } else if ("turn_volume".equals(var7)) {
            this.SetCmdkey(8, var3);
         } else if ("auto_lights_off2".equals(var7)) {
            this.SetCmdkey(10, var3);
         } else if (this.mAuto_light_open == var1) {
            this.SetCmdkey(11, var3);
         } else if (this.mCar_light_off == var1) {
            this.SetCmdkey(12, var3);
         } else if (this.mBrightness_control == var1) {
            this.SetCmdkey(14, var3);
         } else if (this.mMonitor_volume == var1) {
            this.SetCmdkey(19, var3);
         } else if (this.mHeadlight_off_timer == var1) {
            this.SetCmdkey(20, var3);
         } else if (this.mAlarm_distance == var1) {
            this.SetCmdkey(22, var3);
         } else if (this.mAlarm_volume == var1) {
            this.SetCmdkey(23, var3);
         } else if (this.mDrss_alarm_distance == var1) {
            this.SetCmdkey(25, var3);
         } else if (this.mWarning_mode == var1) {
            this.SetCmdkey(29, var3);
         } else if (this.mWarning_distance == var1) {
            this.SetCmdkey(30, var3);
         } else if (this.mWarning2 == var1) {
            this.SetCmdkey(31, var3);
         } else if (this.mTimer == var1) {
            this.SetCmdkey(32, var3);
         } else if (this.mSound == var1) {
            this.SetCmdkey(33, var3);
         } else if (this.mWhispering_vol == var1) {
            this.SetCmdkey(34, var3);
         } else if (this.mRumbling_vol == var1) {
            this.SetCmdkey(35, var3);
         } else if (this.mRange == var1) {
            this.SetCmdkey(38, var3);
         } else if (this.mTemp == var1) {
            this.SetCmdkey(39, var3);
         } else if (this.mBlind_spot2 == var1) {
            this.SetCmdkey(40, var3);
         } else if (this.mAlarm_timing == var1) {
            this.SetCmdkey(42, var3);
         } else if (this.mRear_park_vol == var1) {
            this.SetCmdkey(44, var3);
         } else if (this.mBackwindowdefog == var1) {
            this.SetCmdkey(48, var3);
         } else if (this.mTank_lock == var1) {
            this.SetCmdkey(49, var3);
         } else if (this.mInstrument_style == var1) {
            this.SetCmdkey(50, var3);
         } else if (this.mVehicle_warning_vol == var1) {
            this.SetCmdkey(51, var3);
         } else if (this.mHeight == var1) {
            this.SetCmdkey(53, var3);
         } else if (this.mBrightness == var1) {
            this.SetCmdkey(54, var3);
         } else if (this.mInclination == var1) {
            this.SetCmdkey(55, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      String var25 = var2.getKey();
      if (var2 instanceof HCheckBoxPreference) {
         boolean var23 = ((HCheckBoxPreference)var2).mChecked;
         boolean var24 = "automatic_latch".equals(var25);
         byte var7 = 0;
         byte var20 = 0;
         byte var3 = 0;
         byte var17 = 0;
         byte var10 = 0;
         byte var16 = 0;
         byte var12 = 0;
         byte var8 = 0;
         byte var13 = 0;
         byte var15 = 0;
         byte var22 = 0;
         byte var18 = 0;
         byte var19 = 0;
         byte var14 = 0;
         byte var9 = 0;
         byte var5 = 0;
         byte var11 = 0;
         byte var6 = 0;
         byte var4 = 0;
         byte var21 = 0;
         if (var24) {
            if (var23) {
               var3 = var21;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(3, var3);
         } else if ("three_signal".equals(var25)) {
            if (var23) {
               var3 = var7;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(7, var3);
         } else if ("wipers_induction".equals(var25)) {
            if (var23) {
               var3 = var20;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(9, var3);
         } else if (this.mAdaptive == var2) {
            if (!var23) {
               var3 = 1;
            }

            this.SetCmdkey(13, var3);
         } else if (this.mActive_display == var2) {
            if (var23) {
               var3 = var17;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(15, var3);
         } else if (this.mNavigation == var2) {
            if (var23) {
               var3 = var10;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(16, var3);
         } else if (this.mIntelligent_brake == var2) {
            if (var23) {
               var3 = var16;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(17, var3);
         } else if (this.mSync_avg == var2) {
            if (var23) {
               var3 = var12;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(18, var3);
         } else if (this.mMode == var2) {
            if (var23) {
               var3 = var8;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(21, var3);
         } else if (this.mDrss_mode == var2) {
            if (var23) {
               var3 = var13;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(24, var3);
         } else if (this.mBlind_spot == var2) {
            if (var23) {
               var3 = var15;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(27, var3);
         } else if (this.mSpeed_warning == var2) {
            if (var23) {
               var3 = var22;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(28, var3);
         } else if (this.mHome_lighting == var2) {
            if (var23) {
               var3 = var18;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(36, var3);
         } else if (this.mRunning_lights == var2) {
            if (var23) {
               var3 = var19;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(37, var3);
         } else if (this.mRear_traffic == var2) {
            if (var23) {
               var3 = var14;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(41, var3);
         } else if (this.mParking_guidance == var2) {
            if (var23) {
               var3 = var9;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(43, var3);
         } else if (this.mPedal_alarm == var2) {
            if (var23) {
               var3 = var5;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(45, var3);
         } else if (this.mCruise_conf == var2) {
            if (var23) {
               var3 = var11;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(46, var3);
         } else if (this.mRear_view == var2) {
            if (var23) {
               var3 = var6;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(47, var3);
         } else if (this.mHud_active_display == var2) {
            if (var23) {
               var3 = var4;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(52, var3);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
