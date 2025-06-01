package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus153settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mActive;
   private HCheckBoxPreference mAhead;
   private HCheckBoxPreference mAir_loop;
   private HCheckBoxPreference mApproach_lock;
   private HCheckBoxPreference mAtmo_switch;
   private HCheckBoxPreference mAuto_air;
   private HCheckBoxPreference mAuto_in_light;
   private HCheckBoxPreference mAuto_wipers;
   private HCheckBoxPreference mAutomatic_latch;
   private HCheckBoxPreference mBlind_detection;
   private SwitchboxPreference mBrightness;
   private SwitchboxPreference mBrightness_C5H;
   private PreferenceScreen mClean_page;
   private SwitchboxPreference mClimate;
   private SwitchboxPreference mDisplay_style;
   private HCheckBoxPreference mDriving_auto;
   private HCheckBoxPreference mDriving_style;
   private HCheckBoxPreference mExternal_welcome;
   private HCheckBoxPreference mFatigue_warning;
   private HCheckBoxPreference mFront_park;
   private HCheckBoxPreference mFront_switch;
   private HCheckBoxPreference mFront_switch_C5H;
   private HCheckBoxPreference mHands_button;
   private HCheckBoxPreference mIdentification;
   private SwitchboxPreference mInstrument_bright;
   private HCheckBoxPreference mInstrument_switch;
   private HCheckBoxPreference mKey_lock;
   private SwitchboxPreference mLight_color;
   private SwitchboxPreference mLight_color_C5h;
   private PreferenceScreen mMulti_reset;
   private SwitchboxPreference mMulti_sense;
   private SwitchboxPreference mMulti_sense2;
   private HCheckBoxPreference mMute_mode;
   private HCheckBoxPreference mOver_speed;
   private SwitchboxPreference mPowertrain;
   private SwitchboxPreference mPrompt_sound;
   private HCheckBoxPreference mRadar_sound;
   private SwitchboxPreference mRadar_type;
   private HCheckBoxPreference mRadarswitch;
   private HCheckBoxPreference mRear_switch;
   private HCheckBoxPreference mRear_switch_C5H;
   private HCheckBoxPreference mRear_view;
   private PreferenceScreen mRestore;
   private HCheckBoxPreference mReversing_switch;
   private SwitchboxPreference mSeat_mode;
   private SwitchboxPreference mSeat_speed;
   private SwitchboxPreference mSeat_strength;
   private HCheckBoxPreference mSeat_switch;
   private SwitchboxPreference mSteering;
   private PreferenceScreen mTpms_check;
   private SwitchboxPreference mTurn_vol;
   private SwitchboxPreference mVibration;
   private SwitchboxPreference mVol_radar;
   private SwitchboxPreference mWarning;
   private HCheckBoxPreference mWelcome_voice;
   private HCheckBoxPreference mWiper_back;
   private HCheckBoxPreference mWipers_del;

   private void SetCmdkey(int var1, int var2) {
      byte[] var3 = new byte[]{(byte)var1, (byte)var2, 0, 0};
      this.SendCanBusCmdData5AA5((byte)111, var3, 4);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 97) {
         this.mBrightness.setValue(var1[2] & 31);
         this.mBrightness_C5H.setValue(var1[2] & 31);
         HCheckBoxPreference var5 = this.mInstrument_switch;
         byte var2 = var1[3];
         boolean var4 = false;
         boolean var3;
         if ((var2 & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mFront_switch;
         if ((var1[3] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mFront_switch_C5H;
         if ((var1[3] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mRear_switch;
         if ((var1[3] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mRear_switch_C5H;
         if ((var1[3] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAtmo_switch;
         if ((var1[3] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mMulti_sense.setValue(var1[4] >> 5 & 7);
         this.mClimate.setValue(var1[5] >> 2 & 3);
         var5 = this.mSeat_switch;
         if ((var1[7] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mSeat_mode.setValue(var1[6] >> 6 & 3);
         this.mSeat_strength.setValue(var1[6] >> 3 & 7);
         this.mSeat_speed.setValue(var1[6] & 7);
         var5 = this.mFatigue_warning;
         if ((var1[8] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mWelcome_voice;
         if ((var1[8] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mDriving_auto;
         if ((var1[8] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mHands_button;
         if ((var1[8] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mPrompt_sound.setValue(var1[8] >> 2 & 3);
         var5 = this.mExternal_welcome;
         if ((var1[8] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto_in_light;
         if ((var1[8] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mDriving_style;
         if ((var1[9] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mInstrument_bright.setValue(var1[9] & 31);
         var5 = this.mRadar_sound;
         if ((var1[10] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mRadarswitch;
         if ((var1[11] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mVol_radar.setValue(var1[10] & 31);
         this.mRadar_type.setValue(var1[10] >> 5 & 3);
         var5 = this.mReversing_switch;
         if ((var1[11] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mWarning.setValue(var1[11] >> 2 & 3);
         this.mVibration.setValue(var1[11] >> 4 & 3);
         var5 = this.mActive;
         if ((var1[11] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAhead;
         if ((var1[11] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mTurn_vol.setValue(3 & var1[12] >> 6);
         var5 = this.mKey_lock;
         if ((var1[12] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mApproach_lock;
         if ((var1[12] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mMute_mode;
         if ((var1[12] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAutomatic_latch;
         if ((var1[12] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mWiper_back;
         if ((var1[12] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto_wipers;
         if ((var1[12] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mWipers_del;
         if ((var1[13] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mRear_view;
         if ((var1[13] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto_air;
         if ((var1[13] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAir_loop;
         if ((var1[13] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mLight_color.setValue(var1[2] >> 5 & 7);
         this.mLight_color_C5h.setValue(var1[2] >> 5 & 7);
         var5 = this.mIdentification;
         if ((var1[13] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mFront_park;
         if ((var1[13] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mOver_speed;
         if ((2 & var1[5]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mBlind_detection;
         var3 = var4;
         if ((var1[5] & 1) != 0) {
            var3 = true;
         }

         var5.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492884);
      CharSequence[] var6 = new CharSequence[21];
      CharSequence[] var4 = new CharSequence[var6.length];

      int var2;
      for(var2 = 0; var2 < var6.length; ++var2) {
         var4[var2] = Integer.toString(var2);
         var6[var2] = Integer.toString(var2 * 5);
      }

      this.mBrightness = this.findSwitchboxPreference("brightness");
      this.mBrightness.setEntries(var6);
      this.mBrightness.setEntryValues(var4);
      this.mBrightness_C5H = this.findSwitchboxPreference("brightness_c5h");
      this.mBrightness_C5H.setEntries(var6);
      this.mBrightness_C5H.setEntryValues(var4);
      this.mInstrument_switch = this.findHCheckBoxPreference("instrument_switch");
      this.mFront_switch = this.findHCheckBoxPreference("front_switch");
      this.mFront_switch_C5H = this.findHCheckBoxPreference("front_switch_c5h");
      this.mRear_switch = this.findHCheckBoxPreference("rear_switch");
      this.mRear_switch_C5H = this.findHCheckBoxPreference("rear_switch_c5h");
      this.mAtmo_switch = this.findHCheckBoxPreference("atmo_switch");
      this.mMulti_sense = this.findSwitchboxPreference("multi_sense");
      var6 = new CharSequence[5];
      var4 = new CharSequence[var6.length];
      var6[0] = this.getString(2131297219);
      var6[1] = this.getString(2131296520);
      var6[2] = this.getString(2131296930);
      var6[3] = this.getString(2131296602);
      var6[4] = this.getString(2131297004);

      for(var2 = 0; var2 < var6.length; ++var2) {
         var4[var2] = Integer.toString(var2);
      }

      this.mMulti_sense.setEntries(var6);
      this.mMulti_sense.setEntryValues(var4);
      this.mDisplay_style = this.findSwitchboxPreference("display_style");
      var6 = new CharSequence[4];
      var4 = new CharSequence[var6.length];
      var6[0] = this.getString(2131296602);
      var6[1] = this.getString(2131296520);
      var6[2] = this.getString(2131297082);
      var6[3] = this.getString(2131297219);

      for(var2 = 0; var2 < var6.length; ++var2) {
         var4[var2] = Integer.toString(var2);
      }

      this.mDisplay_style.setEntries(var6);
      this.mDisplay_style.setEntryValues(var4);
      this.mSteering = this.findSwitchboxPreference("steering");
      var4 = new CharSequence[3];
      var6 = new CharSequence[var4.length];
      var4[0] = this.getString(2131297219);
      var4[1] = this.getString(2131296520);
      var4[2] = this.getString(2131297082);

      for(var2 = 0; var2 < var4.length; ++var2) {
         var6[var2] = Integer.toString(var2);
      }

      this.mSteering.setEntries(var4);
      this.mSteering.setEntryValues(var6);
      this.mPowertrain = this.findSwitchboxPreference("powertrain");
      var4 = new CharSequence[3];
      var6 = new CharSequence[var4.length];
      var4[0] = this.getString(2131297219);
      var4[1] = this.getString(2131296602);
      var4[2] = this.getString(2131297082);
      var6[0] = "0";
      var6[1] = "3";
      var6[2] = "4";
      this.mPowertrain.setEntries(var4);
      this.mPowertrain.setEntryValues(var6);
      this.mClimate = this.findSwitchboxPreference("climate");
      var4 = new CharSequence[2];
      var6 = new CharSequence[var4.length];
      var4[0] = this.getString(2131296930);
      var4[1] = this.getString(2131296602);
      var6[0] = Integer.toString(2);
      var6[1] = Integer.toString(3);
      this.mClimate.setEntries(var4);
      this.mClimate.setEntryValues(var6);
      this.mSeat_switch = this.findHCheckBoxPreference("seat_switch");
      this.mSeat_mode = this.findSwitchboxPreference("seat_mode");
      this.mSeat_strength = this.findSwitchboxPreference("seat_strength");
      this.mSeat_speed = this.findSwitchboxPreference("seat_speed");
      var4 = new CharSequence[6];
      var6 = new CharSequence[var4.length];

      String var5;
      for(var2 = 0; var2 < var4.length; ++var2) {
         var5 = Integer.toString(var2);
         var6[var2] = var5;
         var4[var2] = var5;
      }

      this.mSeat_strength.setEntries(var4);
      this.mSeat_strength.setEntryValues(var6);
      this.mSeat_speed.setEntries(var4);
      this.mSeat_speed.setEntryValues(var6);
      this.mFatigue_warning = this.findHCheckBoxPreference("fatigue_warning");
      this.mWelcome_voice = this.findHCheckBoxPreference("welcome_voice");
      this.mDriving_auto = this.findHCheckBoxPreference("driving_auto");
      this.mHands_button = this.findHCheckBoxPreference("hands_button");
      this.mPrompt_sound = this.findSwitchboxPreference("prompt_sound");
      var4 = new CharSequence[21];
      var6 = new CharSequence[var4.length];

      int var3;
      for(var2 = 0; var2 < var4.length; var2 = var3) {
         var6[var2] = Integer.toString(var2);
         var3 = var2 + 1;
         var4[var2] = Integer.toString(var3);
      }

      this.mPrompt_sound.setEntries(var4);
      this.mPrompt_sound.setEntryValues(var6);
      this.mExternal_welcome = this.findHCheckBoxPreference("external_welcome");
      this.mAuto_in_light = this.findHCheckBoxPreference("auto_in_light");
      this.mDriving_style = this.findHCheckBoxPreference("driving_style");
      this.mInstrument_bright = this.findSwitchboxPreference("instrument_bright");
      var6 = new CharSequence[3];
      var4 = new CharSequence[var6.length];

      for(var2 = 0; var2 < var6.length; ++var2) {
         var4[var2] = Integer.toString(var2);
         var6[var2] = Integer.toString(var2 * 5);
      }

      this.mInstrument_bright.setEntries(var6);
      this.mInstrument_bright.setEntryValues(var4);
      this.mRadar_sound = this.findHCheckBoxPreference("radar_sound");
      this.mRadarswitch = this.findHCheckBoxPreference("radarswitch");
      this.mVol_radar = this.findSwitchboxPreference("vol_radar");
      CharSequence[] var7 = new CharSequence[32];
      var6 = new CharSequence[var7.length];

      for(var2 = 0; var2 < var7.length; ++var2) {
         String var8 = Integer.toString(var2);
         var6[var2] = var8;
         var7[var2] = var8;
      }

      this.mVol_radar.setEntries(var7);
      this.mVol_radar.setEntryValues(var6);
      this.mTpms_check = this.findPreferenceScreen("tpms_check");
      this.mRestore = this.findPreferenceScreen("restore");
      this.mMulti_reset = this.findPreferenceScreen("multi_reset");
      this.mClean_page = this.findPreferenceScreen("clean_page");
      this.mRadar_type = this.findSwitchboxPreference("radar_type");
      var6 = new CharSequence[3];
      var4 = new CharSequence[var6.length];

      for(var2 = 0; var2 < var6.length; ++var2) {
         var5 = Integer.toString(var2);
         var4[var2] = var5;
         var6[var2] = var5;
      }

      this.mRadar_type.setEntries(var6);
      this.mRadar_type.setEntryValues(var4);
      this.mReversing_switch = this.findHCheckBoxPreference("reversing_switch");
      this.mWarning = this.findSwitchboxPreference("warning");
      this.mVibration = this.findSwitchboxPreference("vibration");
      this.mActive = this.findHCheckBoxPreference("active");
      this.mAhead = this.findHCheckBoxPreference("ahead");
      this.mTurn_vol = this.findSwitchboxPreference("turn_vol");
      this.mKey_lock = this.findHCheckBoxPreference("key_lock");
      this.mApproach_lock = this.findHCheckBoxPreference("approach_lock");
      this.mMute_mode = this.findHCheckBoxPreference("mute_mode");
      this.mAutomatic_latch = this.findHCheckBoxPreference("automatic_latch");
      this.mWiper_back = this.findHCheckBoxPreference("wiper_back");
      this.mAuto_wipers = this.findHCheckBoxPreference("auto_wipers");
      this.mWipers_del = this.findHCheckBoxPreference("wipers_del");
      this.mRear_view = this.findHCheckBoxPreference("rear_view");
      this.mAuto_air = this.findHCheckBoxPreference("auto_air");
      this.mAir_loop = this.findHCheckBoxPreference("air_loop");
      this.mIdentification = this.findHCheckBoxPreference("identification");
      this.mFront_park = this.findHCheckBoxPreference("front_park");
      this.mLight_color = this.findSwitchboxPreference("light_color");
      this.mLight_color_C5h = this.findSwitchboxPreference("light_color_c5h");
      var4 = new CharSequence[8];
      var6 = new CharSequence[var4.length];
      var4[0] = this.getString(2131297485);
      var4[1] = this.getString(2131297080);
      var4[2] = this.getString(2131296435);
      var4[3] = this.getString(2131297027);
      var4[4] = this.getString(2131296448);
      var4[5] = this.getString(2131296850);
      var4[6] = this.getString(2131297507);
      var4[7] = this.getString(2131296967);

      for(var2 = 0; var2 < var4.length; ++var2) {
         var6[var2] = Integer.toString(var2);
      }

      this.mLight_color.setEntries(var4);
      this.mLight_color.setEntryValues(var6);
      this.mLight_color_C5h.setEntries(var4);
      this.mLight_color_C5h.setEntryValues(var6);
      this.mMulti_sense2 = this.findSwitchboxPreference("multi_sense2");
      var6 = new CharSequence[3];
      var4 = new CharSequence[var6.length];
      var6[0] = this.getString(2131297219);
      var6[1] = this.getString(2131296602);
      var6[2] = this.getString(2131296924);
      var4[0] = "0";
      var4[1] = "3";
      var4[2] = "4";
      this.mMulti_sense2.setEntries(var6);
      this.mMulti_sense2.setEntryValues(var4);
      var2 = this.getInt("mMulti_sense2", 4);
      this.mMulti_sense2.setValue(var2);
      this.mPowertrain.setValue(var2);
      if (var2 != 0 && var2 != 3) {
         this.mDisplay_style.setEnabled(true);
         this.mSteering.setEnabled(true);
      } else {
         this.mDisplay_style.setEnabled(false);
         this.mSteering.setEnabled(false);
      }

      this.mSteering.setValue(this.getInt("mSteering", 0));
      this.mOver_speed = this.findHCheckBoxPreference("over_speed");
      this.mBlind_detection = this.findHCheckBoxPreference("blind_detection");
      this.mDisplay_style.setValue(this.getInt("mDisplay_style", 0));
      this.ProcessData(this.GetCarByteArrayState(97));
      if (!BaseApplication.getInstance().getCustomer().contains("HZC")) {
         this.removePreferenceGroup("driver_no", "identification");
         this.removePreference("front_park");
         this.removePreferenceGroup("multi_sense_group", "multi_sense2");
         this.removePreference("over_speed");
         this.removePreferenceGroup("driver_no", "blind_detection");
      }

      if (4 == this.getCarType()) {
         this.removePreference("multi_sense_group");
         this.removePreference("seat_mas");
         this.removePreference("fatigue_warning");
         this.removePreference("prompt_sound");
         this.removePreference("driving_style");
         this.removePreference("instrument_bright");
         this.removePreference("turn_vol");
         this.removePreference("key_lock");
         this.removePreference("auto_air");
         this.removePreference("air_loop");
         this.removePreference("clean_page");
         this.removePreference("tpms_check");
         this.removePreference("restore");
         this.removePreferenceGroup("atmo_lamp", "brightness");
         this.removePreferenceGroup("atmo_lamp", "instrument_switch");
         this.removePreferenceGroup("atmo_lamp", "front_switch");
         this.removePreferenceGroup("atmo_lamp", "rear_switch");
         this.removePreferenceGroup("atmo_lamp", "atmo_switch");
         this.removePreference("welcome_voice");
         this.removePreference("vol_radar");
         this.removePreference("radar_type");
         this.removePreference("reversing_switch");
         this.removePreferenceGroup("multi_sense_group", "multi_sense2");
         this.removePreference("over_speed");
         this.removePreferenceGroup("driver_no", "blind_detection");
      } else if (5 == this.getCarType()) {
         this.removePreferenceGroup("multi_sense_group", "multi_sense");
         this.removePreferenceGroup("multi_sense_group", "multi_reset");
         this.removePreferenceGroup("multi_sense_group", "climate");
         this.removePreference("seat_mas");
         this.removePreference("fatigue_warning");
         this.removePreference("prompt_sound");
         this.removePreference("driving_style");
         this.removePreference("instrument_bright");
         this.removePreference("key_lock");
         this.removePreference("auto_air");
         this.removePreference("air_loop");
         this.removePreference("clean_page");
         this.removePreference("tpms_check");
         this.removePreference("restore");
         this.removePreferenceGroup("atmo_lamp", "instrument_switch");
         this.removePreferenceGroup("atmo_lamp", "atmo_switch");
         this.removePreference("welcome_voice");
         this.removePreference("vol_radar");
         this.removePreference("radar_type");
         this.removePreference("reversing_switch");
         this.removePreference("over_speed");
         this.removePreference("atmo_lamp");
      }

      if (5 != this.getCarType()) {
         this.removePreferenceGroup("multi_sense_group", "brightness_c5h");
         this.removePreferenceGroup("multi_sense_group", "light_color_c5h");
         this.removePreferenceGroup("multi_sense_group", "front_switch_c5h");
         this.removePreferenceGroup("multi_sense_group", "rear_switch_c5h");
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var5) {
         return false;
      }

      int var3;
      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 != this.mBrightness && var1 != this.mBrightness_C5H) {
            if (var1 == this.mMulti_sense) {
               this.SetCmdkey(7, var4);
            } else if (var1 == this.mMulti_sense2) {
               this.SetCmdkey(7, var4);
               this.putInt("mMulti_sense2", var4);
               this.mMulti_sense2.setValue(var4);
               this.mPowertrain.setValue(var4);
               if (var4 != 0 && var4 != 3) {
                  this.mDisplay_style.setEnabled(true);
                  this.mSteering.setEnabled(true);
               } else {
                  this.mDisplay_style.setEnabled(false);
                  this.mSteering.setEnabled(false);
               }

               if (var4 == 0) {
                  this.putInt("mDisplay_style", 3);
                  this.mDisplay_style.setValue(3);
                  this.putInt("mSteering", 0);
                  this.mSteering.setValue(0);
               } else if (var4 == 3) {
                  this.putInt("mDisplay_style", 0);
                  this.mDisplay_style.setValue(0);
                  this.putInt("mSteering", 1);
                  this.mSteering.setValue(1);
               }
            } else if (var1 == this.mDisplay_style) {
               this.SetCmdkey(8, var4);
               this.putInt("mDisplay_style", var4);
               this.mDisplay_style.setValue(var4);
            } else if (var1 == this.mSteering) {
               this.SetCmdkey(9, var4);
               this.putInt("mSteering", var4);
               this.mSteering.setValue(var4);
            } else if (var1 == this.mPowertrain) {
               this.SetCmdkey(10, var4);
            } else if (var1 == this.mClimate) {
               this.SetCmdkey(11, var4);
            } else if (var1 == this.mSeat_mode) {
               this.SetCmdkey(13, var4);
            } else if (var1 == this.mSeat_strength) {
               this.SetCmdkey(14, var4);
            } else if (var1 == this.mSeat_speed) {
               this.SetCmdkey(15, var4);
            } else if (var1 == this.mPrompt_sound) {
               this.SetCmdkey(20, var4);
            } else if (var1 == this.mVol_radar) {
               this.SetCmdkey(28, var4);
            } else if (var1 == this.mRadar_type) {
               this.SetCmdkey(27, var4);
            } else if (var1 == this.mWarning) {
               this.SetCmdkey(33, var4);
            } else if (var1 == this.mVibration) {
               this.SetCmdkey(32, var4);
            } else if (var1 == this.mTurn_vol) {
               this.SetCmdkey(36, var4);
            } else if (var1 == this.mLight_color || var1 == this.mLight_color_C5h) {
               this.SetCmdkey(1, var4);
            }
         } else {
            this.SetCmdkey(2, var4);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 instanceof HCheckBoxPreference) {
         boolean var34 = ((HCheckBoxPreference)var2).mChecked;
         HCheckBoxPreference var35 = this.mInstrument_switch;
         byte var22 = 0;
         byte var8 = 0;
         byte var10 = 0;
         byte var3 = 0;
         byte var4 = 0;
         byte var11 = 0;
         byte var6 = 0;
         byte var30 = 0;
         byte var9 = 0;
         byte var19 = 0;
         byte var12 = 0;
         byte var17 = 0;
         byte var5 = 0;
         byte var25 = 0;
         byte var29 = 0;
         byte var20 = 0;
         byte var26 = 0;
         byte var31 = 0;
         byte var27 = 0;
         byte var28 = 0;
         byte var7 = 0;
         byte var14 = 0;
         byte var32 = 0;
         byte var15 = 0;
         byte var23 = 0;
         byte var13 = 0;
         byte var24 = 0;
         byte var18 = 0;
         byte var33 = 0;
         byte var21 = 0;
         byte var16 = 0;
         if (var2 == var35) {
            if (var34) {
               var3 = var16;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(3, var3);
         } else if (var2 != this.mFront_switch && var2 != this.mFront_switch_C5H) {
            if (var2 != this.mRear_switch && var2 != this.mRear_switch_C5H) {
               if (var2 == this.mAtmo_switch) {
                  if (var34) {
                     var3 = var22;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(6, var3);
               } else if (var2 == this.mSeat_switch) {
                  if (var34) {
                     var3 = var8;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(12, var3);
               } else if (var2 == this.mFatigue_warning) {
                  if (var34) {
                     var3 = var10;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(16, var3);
               } else if (var2 == this.mWelcome_voice) {
                  if (!var34) {
                     var3 = 1;
                  }

                  this.SetCmdkey(17, var3);
               } else if (var2 == this.mDriving_auto) {
                  if (var34) {
                     var3 = var4;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(18, var3);
               } else if (var2 == this.mHands_button) {
                  if (var34) {
                     var3 = var11;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(19, var3);
               } else if (var2 == this.mExternal_welcome) {
                  if (var34) {
                     var3 = var6;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(21, var3);
               } else if (var2 == this.mAuto_in_light) {
                  if (var34) {
                     var3 = var30;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(22, var3);
               } else if (var2 == this.mDriving_style) {
                  if (var34) {
                     var3 = var9;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(23, var3);
               } else if (var2 == this.mRadar_sound) {
                  if (var34) {
                     var3 = var19;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(26, var3);
               } else if (var2 == this.mRadarswitch) {
                  if (var34) {
                     var3 = var12;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(29, var3);
               } else if (var2 == this.mReversing_switch) {
                  if (var34) {
                     var3 = var17;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(30, var3);
               } else if (var2 == this.mActive) {
                  if (var34) {
                     var3 = var5;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(34, var3);
               } else if (var2 == this.mAhead) {
                  if (var34) {
                     var3 = var25;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(35, var3);
               } else if (var2 == this.mKey_lock) {
                  if (var34) {
                     var3 = var29;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(37, var3);
               } else if (var2 == this.mApproach_lock) {
                  if (var34) {
                     var3 = var20;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(38, var3);
               } else if (var2 == this.mMute_mode) {
                  if (var34) {
                     var3 = var26;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(39, var3);
               } else if (var2 == this.mAutomatic_latch) {
                  if (var34) {
                     var3 = var31;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(40, var3);
               } else if (var2 == this.mWiper_back) {
                  if (var34) {
                     var3 = var27;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(41, var3);
               } else if (var2 == this.mAuto_wipers) {
                  if (var34) {
                     var3 = var28;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(42, var3);
               } else if (var2 == this.mWipers_del) {
                  if (var34) {
                     var3 = var7;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(43, var3);
               } else if (var2 == this.mRear_view) {
                  if (var34) {
                     var3 = var14;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(44, var3);
               } else if (var2 == this.mAuto_air) {
                  if (var34) {
                     var3 = var32;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(45, var3);
               } else if (var2 == this.mAir_loop) {
                  if (var34) {
                     var3 = var15;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(46, var3);
               } else if (var2 == this.mIdentification) {
                  if (var34) {
                     var3 = var23;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(53, var3);
               } else if (var2 == this.mFront_park) {
                  if (var34) {
                     var3 = var13;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(54, var3);
               } else if (var2 == this.mOver_speed) {
                  if (var34) {
                     var3 = var24;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(55, var3);
               } else if (var2 == this.mBlind_detection) {
                  if (var34) {
                     var3 = var18;
                  } else {
                     var3 = 1;
                  }

                  this.SetCmdkey(58, var3);
               }
            } else {
               if (var34) {
                  var3 = var33;
               } else {
                  var3 = 1;
               }

               this.SetCmdkey(5, var3);
            }
         } else {
            if (var34) {
               var3 = var21;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(4, var3);
         }
      }

      if (var2 == this.mTpms_check) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus153settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey(25, 1);
            }
         }, this.mTpms_check.getTitle().toString());
      }

      if (var2 == this.mRestore) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus153settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey(31, 1);
            }
         }, this.mRestore.getTitle().toString());
      }

      if (var2 == this.mMulti_reset) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus153settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey(52, 1);
            }
         }, this.mMulti_reset.getTitle().toString());
      }

      if (var2 == this.mClean_page) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus153settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SendCanBusCmdData5AA5((byte)27, new byte[]{2, 2, 1, -1}, 4);
            }
         }, this.mClean_page.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
