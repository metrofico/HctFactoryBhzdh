package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;

public class canbus58settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private static final int[] DATA = new int[]{69, 70, 71, 104, 105, 100, 133, 118, 134, 193};
   private int alarmData = 30;
   private HCheckBoxPreference mAdaptiv;
   private OnSwitchPreference mAlarm_data;
   private HCheckBoxPreference mAuto_locking;
   private HCheckBoxPreference mAutoaction;
   private HCheckBoxPreference mAverage_fuel;
   private HCheckBoxPreference mAvg_speed;
   private HCheckBoxPreference mBack_glass;
   private HCheckBoxPreference mBack_reduce;
   private SwitchboxPreference mConnect_time;
   private SwitchboxPreference mConvenient_open;
   private HCheckBoxPreference mCurrent_fuel;
   private HCheckBoxPreference mData_dis;
   private HCheckBoxPreference mDis_istance;
   private SwitchboxPreference mDistance;
   private SwitchboxPreference mDoor_unlock;
   private PreferenceCategory mDriver;
   private HCheckBoxPreference mDriving_time;
   private HCheckBoxPreference mDynamic_bend;
   private HCheckBoxPreference mDynamic_light;
   private HCheckBoxPreference mEarly_warning;
   private SwitchboxPreference mEnvironmental;
   private SwitchboxPreference mEsc_setting;
   private SwitchboxPreference mFoot_pace;
   private HCheckBoxPreference mFront_assistant;
   private SwitchboxPreference mFrontpitch;
   private SwitchboxPreference mFrontvol;
   private SwitchboxPreference mFulecons;
   private PreferenceCategory mFunction_cmd;
   private SwitchboxPreference mHome_mod;
   private HCheckBoxPreference mLastselect;
   private PreferenceCategory mLight_cmd;
   private SwitchboxPreference mMeter_switch;
   private HCheckBoxPreference mMileagex;
   private PreferenceCategory mMirror_wiper;
   private SwitchboxPreference mMiunit;
   private HCheckBoxPreference mOil_setting;
   private PreferenceCategory mOpen_close;
   private SwitchboxPreference mOut_home;
   private HCheckBoxPreference mOutcar;
   private HCheckBoxPreference mParking;
   private SwitchboxPreference mParking_mode;
   private PreferenceCategory mParkshunt;
   private HCheckBoxPreference mPractical_appliance;
   private HCheckBoxPreference mProtection;
   private HCheckBoxPreference mRadar_sound;
   private SwitchboxPreference mRearpitch;
   private SwitchboxPreference mRearvol;
   private PreferenceCategory mRun_mod;
   private HCheckBoxPreference mRun_tip;
   private SwitchboxPreference mRunning;
   private HCheckBoxPreference mScraping;
   private HCheckBoxPreference mShift_light;
   private HCheckBoxPreference mSpeed_alarm;
   private HCheckBoxPreference mSpeed_alarms;
   private SwitchboxPreference mSpeedunits;
   private HCheckBoxPreference mSynchronous;
   private HCheckBoxPreference mSystem;
   private SwitchboxPreference mTemperature;
   private SwitchboxPreference mTireunit;
   private HCheckBoxPreference mTraffice;
   private SwitchboxPreference mTravel_pattern;
   private PreferenceCategory mTyres;
   private PreferenceCategory mUnit;
   private SwitchboxPreference mVolume;
   private byte[] setData = new byte[30];

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData5AA5(var1, new byte[]{var2, var3}, 2);
   }

   private void updataSettings45() {
      HCheckBoxPreference var4 = this.mAutoaction;
      byte[] var3 = this.setData;
      boolean var2 = true;
      boolean var1;
      if ((var3[1] & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var4.setChecked(var1);
      SwitchboxPreference var7 = this.mFrontvol;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      var5.append(this.setData[2] & 15);
      var7.setValue(var5.toString());
      SwitchboxPreference var6 = this.mFrontpitch;
      StringBuilder var8 = new StringBuilder();
      var8.append("");
      var8.append(this.setData[3] & 15);
      var6.setValue(var8.toString());
      var7 = this.mRearvol;
      var5 = new StringBuilder();
      var5.append("");
      var5.append(this.setData[4] & 15);
      var7.setValue(var5.toString());
      var7 = this.mRearpitch;
      var5 = new StringBuilder();
      var5.append("");
      var5.append(this.setData[5] & 15);
      var7.setValue(var5.toString());
      var7 = this.mParking_mode;
      var5 = new StringBuilder();
      var5.append("");
      var5.append((this.setData[6] & 255) >> 6 & 3);
      var7.setValue(var5.toString());
      HCheckBoxPreference var9 = this.mRadar_sound;
      if ((this.setData[6] & 32) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var9.setChecked(var1);
      PreferenceCategory var10 = this.mParkshunt;
      var9 = this.mAutoaction;
      if ((this.setData[0] & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var10, var9, var1);
      var10 = this.mParkshunt;
      var6 = this.mFrontvol;
      if ((this.setData[0] & 64) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var10, var6, var1);
      PreferenceCategory var11 = this.mParkshunt;
      var7 = this.mFrontpitch;
      if ((this.setData[0] & 32) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var11, var7, var1);
      var11 = this.mParkshunt;
      var7 = this.mRearvol;
      if ((this.setData[0] & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var11, var7, var1);
      var10 = this.mParkshunt;
      var6 = this.mRearpitch;
      if ((this.setData[0] & 8) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var10, var6, var1);
      var10 = this.mParkshunt;
      var6 = this.mParking_mode;
      if ((this.setData[0] & 2) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var10, var6, var1);
      var11 = this.mParkshunt;
      var4 = this.mRadar_sound;
      if ((this.setData[0] & 1) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var11, var4, var1);
      var11 = this.mParkshunt;
      if (var11.getPreferenceCount() > 0) {
         var1 = var2;
      } else {
         var1 = false;
      }

      this.enabledPreference(var11, var1);
   }

   private void updataSettings46() {
      HCheckBoxPreference var3 = this.mSpeed_alarm;
      byte[] var4 = this.setData;
      boolean var2 = true;
      boolean var1;
      if ((var4[1] & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      this.alarmData = this.setData[2] & 255;
      if (this.alarmData <= 30) {
         this.alarmData = 30;
      }

      OnSwitchPreference var7 = this.mAlarm_data;
      StringBuilder var5 = new StringBuilder();
      var5.append(this.alarmData);
      var5.append("");
      var7.setValue("0", var5.toString());
      PreferenceCategory var6 = this.mTyres;
      if ((this.setData[0] & 64) != 0) {
         var1 = var2;
      } else {
         var1 = false;
      }

      this.enabledPreference(var6, var1);
   }

   private void updataSettings47() {
      HCheckBoxPreference var4 = this.mFront_assistant;
      byte var1 = this.setData[8];
      boolean var3 = false;
      boolean var2;
      if ((var1 & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mEarly_warning;
      if ((this.setData[8] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mDis_istance;
      if ((this.setData[8] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mAdaptiv;
      if ((this.setData[9] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mTraffice;
      if ((this.setData[10] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mSystem;
      if ((this.setData[11] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mProtection;
      if ((this.setData[12] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mLastselect;
      if ((this.setData[7] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      SwitchboxPreference var5 = this.mDistance;
      StringBuilder var9 = new StringBuilder();
      var9.append("");
      var9.append(this.setData[7] & 31);
      var5.setValue(var9.toString());
      SwitchboxPreference var10 = this.mRunning;
      StringBuilder var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[7] >> 6 & 3);
      var10.setValue(var6.toString());
      PreferenceCategory var7 = this.mDriver;
      var4 = this.mFront_assistant;
      if ((this.setData[1] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var4, var2);
      PreferenceCategory var11 = this.mDriver;
      HCheckBoxPreference var8 = this.mEarly_warning;
      if ((this.setData[1] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var8, var2);
      var11 = this.mDriver;
      var8 = this.mDis_istance;
      if ((this.setData[1] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var8, var2);
      var11 = this.mDriver;
      var8 = this.mAdaptiv;
      if ((this.setData[2] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var8, var2);
      var7 = this.mDriver;
      var4 = this.mTraffice;
      if ((this.setData[3] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var4, var2);
      var7 = this.mDriver;
      var4 = this.mSystem;
      if ((this.setData[4] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var4, var2);
      var7 = this.mDriver;
      var4 = this.mProtection;
      if ((this.setData[5] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var4, var2);
      var11 = this.mDriver;
      var8 = this.mLastselect;
      if ((this.setData[0] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var8, var2);
      var11 = this.mDriver;
      var5 = this.mRunning;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var5, var2);
      var11 = this.mDriver;
      var5 = this.mDistance;
      if ((this.setData[7] & 32) == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var5, var2);
      var11 = this.mDriver;
      var2 = var3;
      if (var11.getPreferenceCount() > 0) {
         var2 = true;
      }

      this.enabledPreference(var11, var2);
   }

   private void updataSettings64() {
      SwitchboxPreference var5 = this.mConvenient_open;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(this.setData[2] >> 6 & 3);
      var5.setValue(var4.toString());
      SwitchboxPreference var6 = this.mDoor_unlock;
      StringBuilder var8 = new StringBuilder();
      var8.append("");
      var8.append(this.setData[3] >> 6 & 3);
      var6.setValue(var8.toString());
      HCheckBoxPreference var7 = this.mAuto_locking;
      byte var1 = this.setData[3];
      boolean var3 = false;
      boolean var2;
      if ((var1 & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var7.setChecked(var2);
      PreferenceCategory var10 = this.mOpen_close;
      var6 = this.mConvenient_open;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var10, var6, var2);
      PreferenceCategory var9 = this.mOpen_close;
      var5 = this.mDoor_unlock;
      if ((this.setData[1] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var9, var5, var2);
      var9 = this.mOpen_close;
      HCheckBoxPreference var11 = this.mAuto_locking;
      if ((this.setData[1] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var9, var11, var2);
      var9 = this.mOpen_close;
      var2 = var3;
      if (var9.getPreferenceCount() > 0) {
         var2 = true;
      }

      this.enabledPreference(var9, var2);
   }

   private void updataSettings68() {
      HCheckBoxPreference var4 = this.mDynamic_light;
      byte var1 = this.setData[3];
      boolean var3 = true;
      boolean var2;
      if ((var1 & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mDynamic_bend;
      if ((this.setData[3] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      SwitchboxPreference var5 = this.mConnect_time;
      StringBuilder var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[3] >> 4 & 3);
      var5.setValue(var6.toString());
      var4 = this.mOutcar;
      if ((this.setData[3] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mShift_light;
      if ((this.setData[3] & 4) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var5 = this.mTravel_pattern;
      String var7;
      if ((this.setData[3] & 2) == 2) {
         var7 = "1";
      } else {
         var7 = "0";
      }

      var5.setValue(var7);
      var5 = this.mMeter_switch;
      var6 = new StringBuilder();
      var6.append(this.setData[4] & 127);
      var6.append("");
      var5.setValue(var6.toString());
      var5 = this.mEnvironmental;
      var6 = new StringBuilder();
      var6.append(this.setData[5] & 127);
      var6.append("");
      var5.setValue(var6.toString());
      SwitchboxPreference var9 = this.mFoot_pace;
      StringBuilder var8 = new StringBuilder();
      var8.append(this.setData[6] & 127);
      var8.append("");
      var9.setValue(var8.toString());
      var5 = this.mHome_mod;
      var6 = new StringBuilder();
      var6.append(this.setData[7] & 31);
      var6.append("");
      var5.setValue(var6.toString());
      var5 = this.mOut_home;
      var6 = new StringBuilder();
      var6.append(this.setData[8] & 31);
      var6.append("");
      var5.setValue(var6.toString());
      PreferenceCategory var12 = this.mLight_cmd;
      HCheckBoxPreference var10 = this.mDynamic_light;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var12, var10, var2);
      PreferenceCategory var11 = this.mLight_cmd;
      var4 = this.mDynamic_bend;
      if ((this.setData[0] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var4, var2);
      var12 = this.mLight_cmd;
      var5 = this.mConnect_time;
      if ((this.setData[0] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var12, var5, var2);
      var12 = this.mLight_cmd;
      var10 = this.mOutcar;
      if ((this.setData[0] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var12, var10, var2);
      var11 = this.mLight_cmd;
      var4 = this.mShift_light;
      if ((this.setData[0] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var4, var2);
      var11 = this.mLight_cmd;
      var9 = this.mTravel_pattern;
      if ((this.setData[0] & 4) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var9, var2);
      var11 = this.mLight_cmd;
      var9 = this.mMeter_switch;
      if ((this.setData[1] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var9, var2);
      var11 = this.mLight_cmd;
      var9 = this.mEnvironmental;
      if ((this.setData[1] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var9, var2);
      var11 = this.mLight_cmd;
      var9 = this.mFoot_pace;
      if ((this.setData[1] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var9, var2);
      var12 = this.mLight_cmd;
      var5 = this.mHome_mod;
      if ((this.setData[2] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var12, var5, var2);
      var11 = this.mLight_cmd;
      var9 = this.mOut_home;
      if ((this.setData[2] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var11, var9, var2);
      var12 = this.mLight_cmd;
      if (var12.getPreferenceCount() > 0) {
         var2 = var3;
      } else {
         var2 = false;
      }

      this.enabledPreference(var12, var2);
   }

   private void updataSettings69() {
      HCheckBoxPreference var4 = this.mSynchronous;
      byte var1 = this.setData[2];
      boolean var3 = true;
      boolean var2;
      if ((var1 & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mBack_reduce;
      if ((this.setData[2] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mParking;
      if ((this.setData[2] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mScraping;
      if ((this.setData[3] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mBack_glass;
      if ((this.setData[3] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      PreferenceCategory var5 = this.mMirror_wiper;
      var4 = this.mSynchronous;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var5, var4, var2);
      var5 = this.mMirror_wiper;
      var4 = this.mBack_reduce;
      if ((this.setData[0] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var5, var4, var2);
      var5 = this.mMirror_wiper;
      var4 = this.mParking;
      if ((this.setData[0] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var5, var4, var2);
      var5 = this.mMirror_wiper;
      var4 = this.mScraping;
      if ((this.setData[1] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var5, var4, var2);
      PreferenceCategory var7 = this.mMirror_wiper;
      HCheckBoxPreference var6 = this.mBack_glass;
      if ((this.setData[1] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var6, var2);
      var7 = this.mMirror_wiper;
      if (var7.getPreferenceCount() > 0) {
         var2 = var3;
      } else {
         var2 = false;
      }

      this.enabledPreference(var7, var2);
   }

   private void updataSettings76() {
      HCheckBoxPreference var4 = this.mCurrent_fuel;
      byte var1 = this.setData[2];
      boolean var3 = true;
      boolean var2;
      if ((var1 & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mAverage_fuel;
      if ((this.setData[2] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mPractical_appliance;
      if ((this.setData[2] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mRun_tip;
      if ((this.setData[2] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mDriving_time;
      if ((this.setData[2] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mMileagex;
      if ((this.setData[2] & 4) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mAvg_speed;
      if ((this.setData[2] & 2) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mData_dis;
      if ((this.setData[2] & 1) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mSpeed_alarms;
      if ((this.setData[3] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mOil_setting;
      if ((this.setData[3] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      PreferenceCategory var7 = this.mFunction_cmd;
      HCheckBoxPreference var5 = this.mCurrent_fuel;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      var7 = this.mFunction_cmd;
      var5 = this.mAverage_fuel;
      if ((this.setData[0] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      var7 = this.mFunction_cmd;
      var5 = this.mPractical_appliance;
      if ((this.setData[0] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      var7 = this.mFunction_cmd;
      var5 = this.mRun_tip;
      if ((this.setData[0] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      PreferenceCategory var6 = this.mFunction_cmd;
      var4 = this.mDriving_time;
      if ((this.setData[0] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var6, var4, var2);
      var7 = this.mFunction_cmd;
      var5 = this.mMileagex;
      if ((this.setData[0] & 4) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      var6 = this.mFunction_cmd;
      var4 = this.mAvg_speed;
      if ((2 & this.setData[0]) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var6, var4, var2);
      var7 = this.mFunction_cmd;
      var5 = this.mData_dis;
      if ((this.setData[0] & 1) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      var7 = this.mFunction_cmd;
      var5 = this.mSpeed_alarms;
      if ((this.setData[1] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      var7 = this.mFunction_cmd;
      var5 = this.mOil_setting;
      if ((this.setData[1] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var7, var5, var2);
      var7 = this.mFunction_cmd;
      if (var7.getPreferenceCount() > 0) {
         var2 = var3;
      } else {
         var2 = false;
      }

      this.enabledPreference(var7, var2);
   }

   private void updataSettings85() {
      SwitchboxPreference var4 = this.mEsc_setting;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      byte[] var3 = this.setData;
      boolean var2 = true;
      var5.append(var3[1] >> 5 & 3);
      var4.setValue(var5.toString());
      PreferenceCategory var6 = this.mRun_mod;
      var4 = this.mEsc_setting;
      boolean var1;
      if ((this.setData[0] & 32) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      this.enabledPreferenceGroup(var6, var4, var1);
      var6 = this.mRun_mod;
      if (var6.getPreferenceCount() > 0) {
         var1 = var2;
      } else {
         var1 = false;
      }

      this.enabledPreference(var6, var1);
   }

   private void updataSettingsC1() {
      SwitchboxPreference var5 = this.mVolume;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(this.setData[1] >> 3 & 3);
      var5.setValue(var4.toString());
      SwitchboxPreference var6 = this.mMiunit;
      StringBuilder var7 = new StringBuilder();
      var7.append("");
      byte var1 = this.setData[1];
      String var9 = "0";
      String var8;
      if ((var1 & 128) != 0) {
         var8 = "0";
      } else {
         var8 = "1";
      }

      var7.append(var8);
      var6.setValue(var7.toString());
      SwitchboxPreference var10 = this.mTireunit;
      StringBuilder var13 = new StringBuilder();
      var13.append("");
      var13.append(this.setData[2] >> 6 & 3);
      var10.setValue(var13.toString());
      SwitchboxPreference var14 = this.mSpeedunits;
      var13 = new StringBuilder();
      var13.append("");
      if ((this.setData[1] & 64) != 0) {
         var8 = "0";
      } else {
         var8 = "1";
      }

      var13.append(var8);
      var14.setValue(var13.toString());
      var10 = this.mFulecons;
      var13 = new StringBuilder();
      var13.append("");
      var13.append(this.setData[1] >> 1 & 3);
      var10.setValue(var13.toString());
      var6 = this.mTemperature;
      var7 = new StringBuilder();
      var7.append("");
      if ((this.setData[1] & 32) != 0) {
         var8 = var9;
      } else {
         var8 = "1";
      }

      var7.append(var8);
      var6.setValue(var7.toString());
      PreferenceCategory var15 = this.mUnit;
      var6 = this.mVolume;
      byte[] var11 = this.setData;
      boolean var3 = false;
      boolean var2;
      if ((var11[0] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var15, var6, var2);
      PreferenceCategory var12 = this.mUnit;
      var10 = this.mMiunit;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var12, var10, var2);
      var12 = this.mUnit;
      var10 = this.mTireunit;
      if ((this.setData[0] & 4) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var12, var10, var2);
      var15 = this.mUnit;
      var5 = this.mSpeedunits;
      if ((this.setData[0] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var15, var5, var2);
      var12 = this.mUnit;
      var10 = this.mFulecons;
      if ((this.setData[0] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var12, var10, var2);
      var15 = this.mUnit;
      var5 = this.mTemperature;
      if ((this.setData[0] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.enabledPreferenceGroup(var15, var5, var2);
      var15 = this.mUnit;
      var2 = var3;
      if (var15.getPreferenceCount() > 0) {
         var2 = true;
      }

      this.enabledPreference(var15, var2);
   }

   protected void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      int var3 = var1.length;
      int var2 = 2;
      if (var3 > 2 && var3 <= 29) {
         if (var4 != 69) {
            if (var4 != 70) {
               if (var4 != 71) {
                  if (var4 != 104) {
                     if (var4 != 105) {
                        if (var4 != 100) {
                           if (var4 != 118) {
                              if (var4 != -123) {
                                 if (var4 == -63) {
                                    while(var2 < var3) {
                                       this.setData[var2 - 2] = var1[var2];
                                       ++var2;
                                    }

                                    this.updataSettingsC1();
                                 }
                              } else {
                                 while(var2 < var3) {
                                    this.setData[var2 - 2] = var1[var2];
                                    ++var2;
                                 }

                                 this.updataSettings85();
                              }
                           } else {
                              while(var2 < var3) {
                                 this.setData[var2 - 2] = var1[var2];
                                 ++var2;
                              }

                              this.updataSettings76();
                           }
                        } else {
                           while(var2 < var3) {
                              this.setData[var2 - 2] = var1[var2];
                              ++var2;
                           }

                           this.updataSettings64();
                        }
                     } else {
                        while(var2 < var3) {
                           this.setData[var2 - 2] = var1[var2];
                           ++var2;
                        }

                        this.updataSettings69();
                     }
                  } else {
                     while(var2 < var3) {
                        this.setData[var2 - 2] = var1[var2];
                        ++var2;
                     }

                     this.updataSettings68();
                  }
               } else {
                  while(var2 < var3) {
                     this.setData[var2 - 2] = var1[var2];
                     ++var2;
                  }

                  this.updataSettings47();
               }
            } else {
               while(var2 < var3) {
                  this.setData[var2 - 2] = var1[var2];
                  ++var2;
               }

               this.updataSettings46();
            }
         } else {
            while(var2 < var3) {
               this.setData[var2 - 2] = var1[var2];
               ++var2;
            }

            this.updataSettings45();
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492928);
      this.mAutoaction = (HCheckBoxPreference)this.findPreference("autoaction");
      this.mAutoaction.setOnPreferenceClickListener(this);
      this.mRadar_sound = (HCheckBoxPreference)this.findPreference("radar_sound");
      this.mRadar_sound.setOnPreferenceClickListener(this);
      this.mSpeed_alarm = (HCheckBoxPreference)this.findPreference("speed_alarm");
      this.mSpeed_alarm.setOnPreferenceClickListener(this);
      this.mFront_assistant = (HCheckBoxPreference)this.findPreference("front_assistant");
      this.mFront_assistant.setOnPreferenceClickListener(this);
      this.mEarly_warning = (HCheckBoxPreference)this.findPreference("early_warning");
      this.mEarly_warning.setOnPreferenceClickListener(this);
      this.mDis_istance = (HCheckBoxPreference)this.findPreference("dis_istance");
      this.mDis_istance.setOnPreferenceClickListener(this);
      this.mAdaptiv = (HCheckBoxPreference)this.findPreference("adaptiv");
      this.mAdaptiv.setOnPreferenceClickListener(this);
      this.mTraffice = (HCheckBoxPreference)this.findPreference("traffice");
      this.mTraffice.setOnPreferenceClickListener(this);
      this.mSystem = (HCheckBoxPreference)this.findPreference("system");
      this.mSystem.setOnPreferenceClickListener(this);
      this.mProtection = (HCheckBoxPreference)this.findPreference("protection");
      this.mProtection.setOnPreferenceClickListener(this);
      this.mLastselect = (HCheckBoxPreference)this.findPreference("lastselect");
      this.mLastselect.setOnPreferenceClickListener(this);
      this.mDynamic_light = (HCheckBoxPreference)this.findPreference("dynamic_light");
      this.mDynamic_light.setOnPreferenceClickListener(this);
      this.mDynamic_bend = (HCheckBoxPreference)this.findPreference("dynamic_bend");
      this.mDynamic_bend.setOnPreferenceClickListener(this);
      this.mOutcar = (HCheckBoxPreference)this.findPreference("outcar");
      this.mOutcar.setOnPreferenceClickListener(this);
      this.mShift_light = (HCheckBoxPreference)this.findPreference("shift_light");
      this.mShift_light.setOnPreferenceClickListener(this);
      this.mSynchronous = (HCheckBoxPreference)this.findPreference("synchronous");
      this.mSynchronous.setOnPreferenceClickListener(this);
      this.mBack_reduce = (HCheckBoxPreference)this.findPreference("back_reduce");
      this.mBack_reduce.setOnPreferenceClickListener(this);
      this.mParking = (HCheckBoxPreference)this.findPreference("parking");
      this.mParking.setOnPreferenceClickListener(this);
      this.mScraping = (HCheckBoxPreference)this.findPreference("scraping");
      this.mScraping.setOnPreferenceClickListener(this);
      this.mBack_glass = (HCheckBoxPreference)this.findPreference("back_glass");
      this.mBack_glass.setOnPreferenceClickListener(this);
      this.mAuto_locking = (HCheckBoxPreference)this.findPreference("auto_locking");
      this.mAuto_locking.setOnPreferenceClickListener(this);
      this.mCurrent_fuel = (HCheckBoxPreference)this.findPreference("current_fuel");
      this.mCurrent_fuel.setOnPreferenceClickListener(this);
      this.mAverage_fuel = (HCheckBoxPreference)this.findPreference("average_fuel");
      this.mAverage_fuel.setOnPreferenceClickListener(this);
      this.mPractical_appliance = (HCheckBoxPreference)this.findPreference("practical_appliance");
      this.mPractical_appliance.setOnPreferenceClickListener(this);
      this.mRun_tip = (HCheckBoxPreference)this.findPreference("run_tip");
      this.mRun_tip.setOnPreferenceClickListener(this);
      this.mDriving_time = (HCheckBoxPreference)this.findPreference("driving_time");
      this.mDriving_time.setOnPreferenceClickListener(this);
      this.mMileagex = (HCheckBoxPreference)this.findPreference("mileagex");
      this.mMileagex.setOnPreferenceClickListener(this);
      this.mAvg_speed = (HCheckBoxPreference)this.findPreference("avg_speed");
      this.mAvg_speed.setOnPreferenceClickListener(this);
      this.mData_dis = (HCheckBoxPreference)this.findPreference("data_dis");
      this.mData_dis.setOnPreferenceClickListener(this);
      this.mSpeed_alarms = (HCheckBoxPreference)this.findPreference("speed_alarms");
      this.mSpeed_alarms.setOnPreferenceClickListener(this);
      this.mOil_setting = (HCheckBoxPreference)this.findPreference("oil_setting");
      this.mOil_setting.setOnPreferenceClickListener(this);
      this.mEsc_setting = this.findSwitchboxPreference("esc_setting");
      this.mFrontvol = (SwitchboxPreference)this.findPreference("frontvol");
      this.mFrontvol.setOnPreferenceChangeListener(this);
      this.mFrontpitch = (SwitchboxPreference)this.findPreference("frontpitch");
      this.mFrontpitch.setOnPreferenceChangeListener(this);
      this.mRearvol = (SwitchboxPreference)this.findPreference("rearvol");
      this.mRearvol.setOnPreferenceChangeListener(this);
      this.mRearpitch = (SwitchboxPreference)this.findPreference("rearpitch");
      this.mRearpitch.setOnPreferenceChangeListener(this);
      this.mParking_mode = (SwitchboxPreference)this.findPreference("parking_mode");
      this.mParking_mode.setOnPreferenceChangeListener(this);
      this.mDistance = (SwitchboxPreference)this.findPreference("distance");
      this.mDistance.setOnPreferenceChangeListener(this);
      this.mRunning = (SwitchboxPreference)this.findPreference("running");
      this.mRunning.setOnPreferenceChangeListener(this);
      this.mConnect_time = (SwitchboxPreference)this.findPreference("connect_time");
      this.mConnect_time.setOnPreferenceChangeListener(this);
      this.mTravel_pattern = (SwitchboxPreference)this.findPreference("travel_pattern");
      this.mTravel_pattern.setOnPreferenceChangeListener(this);
      this.mMeter_switch = (SwitchboxPreference)this.findPreference("meter_switch");
      this.mMeter_switch.setOnPreferenceChangeListener(this);
      this.mEnvironmental = (SwitchboxPreference)this.findPreference("environmental");
      this.mEnvironmental.setOnPreferenceChangeListener(this);
      this.mFoot_pace = (SwitchboxPreference)this.findPreference("foot_pace");
      this.mFoot_pace.setOnPreferenceChangeListener(this);
      this.mHome_mod = (SwitchboxPreference)this.findPreference("home_mod");
      this.mHome_mod.setOnPreferenceChangeListener(this);
      this.mOut_home = (SwitchboxPreference)this.findPreference("out_home");
      this.mOut_home.setOnPreferenceChangeListener(this);
      this.mConvenient_open = (SwitchboxPreference)this.findPreference("convenient_open");
      this.mConvenient_open.setOnPreferenceChangeListener(this);
      this.mDoor_unlock = (SwitchboxPreference)this.findPreference("door_unlock");
      this.mDoor_unlock.setOnPreferenceChangeListener(this);
      this.mVolume = (SwitchboxPreference)this.findPreference("volume");
      this.mVolume.setOnPreferenceChangeListener(this);
      this.mMiunit = (SwitchboxPreference)this.findPreference("miunit");
      this.mMiunit.setOnPreferenceChangeListener(this);
      this.mTireunit = (SwitchboxPreference)this.findPreference("tireunit");
      this.mTireunit.setOnPreferenceChangeListener(this);
      this.mSpeedunits = (SwitchboxPreference)this.findPreference("speedunits");
      this.mSpeedunits.setOnPreferenceChangeListener(this);
      this.mFulecons = (SwitchboxPreference)this.findPreference("fulecons");
      this.mFulecons.setOnPreferenceChangeListener(this);
      this.mTemperature = (SwitchboxPreference)this.findPreference("temperature");
      this.mTemperature.setOnPreferenceChangeListener(this);
      this.mAlarm_data = (OnSwitchPreference)this.findPreference("alarm_data");
      this.mAlarm_data.setOnPreferenceChangeListener(this);
      OnSwitchPreference var4 = this.mAlarm_data;
      StringBuilder var3 = new StringBuilder();
      var3.append("");
      var3.append(this.alarmData);
      var4.setValue("0", var3.toString());
      this.mParkshunt = (PreferenceCategory)this.findPreference("parkshunt");
      this.mTyres = (PreferenceCategory)this.findPreference("tyres");
      this.mDriver = (PreferenceCategory)this.findPreference("driver");
      this.mLight_cmd = (PreferenceCategory)this.findPreference("light_cmd");
      this.mMirror_wiper = (PreferenceCategory)this.findPreference("mirror_wiper");
      this.mOpen_close = (PreferenceCategory)this.findPreference("open_close");
      this.mFunction_cmd = (PreferenceCategory)this.findPreference("function_cmd");
      this.mRun_mod = (PreferenceCategory)this.findPreference("run_mod");
      this.mUnit = (PreferenceCategory)this.findPreference("unit");
      int var2 = 0;

      while(true) {
         int[] var5 = DATA;
         if (var2 >= var5.length) {
            return;
         }

         this.ProcessData(this.GetCarByteArrayState(var5[var2]));
         this.SendCanBusCmdData5AA5((byte)10, new byte[]{1, (byte)DATA[var2]}, 2);
         ++var2;
      }
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mAlarm_data;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) * 10 + 0;
         var4 = this.alarmData;
         if (var4 + var3 < 30 || var4 + var3 > 240 || !this.mAlarm_data.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)75, (byte)3, (byte)(this.alarmData + var3 & 255));
      }

      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var7) {
         return false;
      }

      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mFrontvol) {
            this.SetCmdkey((byte)74, (byte)2, (byte)var3);
         } else if (var1 == this.mFrontpitch) {
            this.SetCmdkey((byte)74, (byte)3, (byte)var3);
         } else if (var1 == this.mRearvol) {
            this.SetCmdkey((byte)74, (byte)4, (byte)var3);
         } else if (var1 == this.mRearpitch) {
            this.SetCmdkey((byte)74, (byte)5, (byte)var3);
         } else if (var1 == this.mParking_mode) {
            this.SetCmdkey((byte)74, (byte)7, (byte)var3);
         } else if (var1 == this.mDistance) {
            this.SetCmdkey((byte)76, (byte)8, (byte)(var3 + 1 & 255));
         } else if (var1 == this.mRunning) {
            this.SetCmdkey((byte)76, (byte)9, (byte)(var3 + 1 & 255));
         } else if (var1 == this.mConnect_time) {
            this.SetCmdkey((byte)109, (byte)3, (byte)var3);
         } else if (var1 == this.mTravel_pattern) {
            this.SetCmdkey((byte)109, (byte)6, (byte)var3);
         } else if (var1 == this.mMeter_switch) {
            this.SetCmdkey((byte)109, (byte)7, (byte)var3);
         } else if (var1 == this.mEnvironmental) {
            this.SetCmdkey((byte)109, (byte)8, (byte)var3);
         } else if (var1 == this.mFoot_pace) {
            this.SetCmdkey((byte)109, (byte)9, (byte)var3);
         } else if (var1 == this.mHome_mod) {
            this.SetCmdkey((byte)109, (byte)10, (byte)var3);
         } else if (var1 == this.mOut_home) {
            this.SetCmdkey((byte)109, (byte)11, (byte)var3);
         } else if (var1 == this.mConvenient_open) {
            this.SetCmdkey((byte)111, (byte)1, (byte)var3);
         } else if (var1 == this.mDoor_unlock) {
            this.SetCmdkey((byte)111, (byte)2, (byte)var3);
         } else if (var1 == this.mVolume) {
            this.SetCmdkey((byte)-54, (byte)4, (byte)(var3 + 1));
         } else if (var1 == this.mMiunit) {
            this.SetCmdkey((byte)-54, (byte)1, (byte)(var3 + 1));
         } else if (var1 == this.mTireunit) {
            this.SetCmdkey((byte)-54, (byte)6, (byte)(var3 + 1));
         } else if (var1 == this.mSpeedunits) {
            this.SetCmdkey((byte)-54, (byte)2, (byte)(var3 + 1));
         } else if (var1 == this.mFulecons) {
            this.SetCmdkey((byte)-54, (byte)5, (byte)(var3 + 1));
         } else if (var1 == this.mTemperature) {
            this.SetCmdkey((byte)-54, (byte)3, (byte)(var3 + 1));
         } else if (var1 == this.mEsc_setting) {
            this.SetCmdkey((byte)-118, (byte)3, (byte)(var3 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mAutoaction;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)74, (byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)74, (byte)1, (byte)1);
         }
      } else {
         var2 = this.mRadar_sound;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)74, (byte)8, (byte)0);
            } else {
               this.SetCmdkey((byte)74, (byte)8, (byte)1);
            }
         } else {
            var2 = this.mSpeed_alarm;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)75, (byte)2, (byte)0);
               } else {
                  this.SetCmdkey((byte)75, (byte)2, (byte)1);
               }
            } else {
               var2 = this.mFront_assistant;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)76, (byte)1, (byte)0);
                  } else {
                     this.SetCmdkey((byte)76, (byte)1, (byte)1);
                  }
               } else {
                  var2 = this.mEarly_warning;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)76, (byte)2, (byte)0);
                     } else {
                        this.SetCmdkey((byte)76, (byte)2, (byte)1);
                     }
                  } else {
                     var2 = this.mDis_istance;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)76, (byte)3, (byte)0);
                        } else {
                           this.SetCmdkey((byte)76, (byte)3, (byte)1);
                        }
                     } else {
                        var2 = this.mAdaptiv;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)76, (byte)4, (byte)0);
                           } else {
                              this.SetCmdkey((byte)76, (byte)4, (byte)1);
                           }
                        } else {
                           var2 = this.mTraffice;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)76, (byte)5, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)76, (byte)5, (byte)1);
                              }
                           } else {
                              var2 = this.mSystem;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)76, (byte)6, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)76, (byte)6, (byte)1);
                                 }
                              } else {
                                 var2 = this.mProtection;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)76, (byte)7, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)76, (byte)7, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mLastselect;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)76, (byte)11, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)76, (byte)11, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mDynamic_light;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)109, (byte)1, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)109, (byte)1, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mDynamic_bend;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)109, (byte)2, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)109, (byte)2, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mOutcar;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)109, (byte)4, (byte)0);
                                                } else {
                                                   this.SetCmdkey((byte)109, (byte)4, (byte)1);
                                                }
                                             } else {
                                                var2 = this.mShift_light;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)109, (byte)5, (byte)0);
                                                   } else {
                                                      this.SetCmdkey((byte)109, (byte)5, (byte)1);
                                                   }
                                                } else {
                                                   var2 = this.mSynchronous;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)110, (byte)1, (byte)0);
                                                      } else {
                                                         this.SetCmdkey((byte)110, (byte)1, (byte)1);
                                                      }
                                                   } else {
                                                      var2 = this.mBack_reduce;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)110, (byte)2, (byte)0);
                                                         } else {
                                                            this.SetCmdkey((byte)110, (byte)2, (byte)1);
                                                         }
                                                      } else {
                                                         var2 = this.mParking;
                                                         if (var1 == var2) {
                                                            if (var2.mChecked) {
                                                               this.SetCmdkey((byte)110, (byte)3, (byte)0);
                                                            } else {
                                                               this.SetCmdkey((byte)110, (byte)3, (byte)1);
                                                            }
                                                         } else {
                                                            var2 = this.mScraping;
                                                            if (var1 == var2) {
                                                               if (var2.mChecked) {
                                                                  this.SetCmdkey((byte)110, (byte)4, (byte)0);
                                                               } else {
                                                                  this.SetCmdkey((byte)110, (byte)4, (byte)1);
                                                               }
                                                            } else {
                                                               var2 = this.mBack_glass;
                                                               if (var1 == var2) {
                                                                  if (var2.mChecked) {
                                                                     this.SetCmdkey((byte)110, (byte)5, (byte)0);
                                                                  } else {
                                                                     this.SetCmdkey((byte)110, (byte)5, (byte)1);
                                                                  }
                                                               } else {
                                                                  var2 = this.mAuto_locking;
                                                                  if (var1 == var2) {
                                                                     if (var2.mChecked) {
                                                                        this.SetCmdkey((byte)111, (byte)3, (byte)0);
                                                                     } else {
                                                                        this.SetCmdkey((byte)111, (byte)3, (byte)1);
                                                                     }
                                                                  } else {
                                                                     var2 = this.mCurrent_fuel;
                                                                     if (var1 == var2) {
                                                                        if (var2.mChecked) {
                                                                           this.SetCmdkey((byte)123, (byte)1, (byte)0);
                                                                        } else {
                                                                           this.SetCmdkey((byte)123, (byte)1, (byte)1);
                                                                        }
                                                                     } else {
                                                                        var2 = this.mAverage_fuel;
                                                                        if (var1 == var2) {
                                                                           if (var2.mChecked) {
                                                                              this.SetCmdkey((byte)123, (byte)2, (byte)0);
                                                                           } else {
                                                                              this.SetCmdkey((byte)123, (byte)2, (byte)1);
                                                                           }
                                                                        } else {
                                                                           var2 = this.mPractical_appliance;
                                                                           if (var1 == var2) {
                                                                              if (var2.mChecked) {
                                                                                 this.SetCmdkey((byte)123, (byte)3, (byte)0);
                                                                              } else {
                                                                                 this.SetCmdkey((byte)123, (byte)3, (byte)1);
                                                                              }
                                                                           } else {
                                                                              var2 = this.mRun_tip;
                                                                              if (var1 == var2) {
                                                                                 if (var2.mChecked) {
                                                                                    this.SetCmdkey((byte)123, (byte)4, (byte)0);
                                                                                 } else {
                                                                                    this.SetCmdkey((byte)123, (byte)4, (byte)1);
                                                                                 }
                                                                              } else {
                                                                                 var2 = this.mDriving_time;
                                                                                 if (var1 == var2) {
                                                                                    if (var2.mChecked) {
                                                                                       this.SetCmdkey((byte)123, (byte)5, (byte)0);
                                                                                    } else {
                                                                                       this.SetCmdkey((byte)123, (byte)5, (byte)1);
                                                                                    }
                                                                                 } else {
                                                                                    var2 = this.mMileagex;
                                                                                    if (var1 == var2) {
                                                                                       if (var2.mChecked) {
                                                                                          this.SetCmdkey((byte)123, (byte)6, (byte)0);
                                                                                       } else {
                                                                                          this.SetCmdkey((byte)123, (byte)6, (byte)1);
                                                                                       }
                                                                                    } else {
                                                                                       var2 = this.mAvg_speed;
                                                                                       if (var1 == var2) {
                                                                                          if (var2.mChecked) {
                                                                                             this.SetCmdkey((byte)123, (byte)7, (byte)0);
                                                                                          } else {
                                                                                             this.SetCmdkey((byte)123, (byte)7, (byte)1);
                                                                                          }
                                                                                       } else {
                                                                                          var2 = this.mData_dis;
                                                                                          if (var1 == var2) {
                                                                                             if (var2.mChecked) {
                                                                                                this.SetCmdkey((byte)123, (byte)8, (byte)0);
                                                                                             } else {
                                                                                                this.SetCmdkey((byte)123, (byte)8, (byte)1);
                                                                                             }
                                                                                          } else {
                                                                                             var2 = this.mSpeed_alarms;
                                                                                             if (var1 == var2) {
                                                                                                if (var2.mChecked) {
                                                                                                   this.SetCmdkey((byte)123, (byte)9, (byte)0);
                                                                                                } else {
                                                                                                   this.SetCmdkey((byte)123, (byte)9, (byte)1);
                                                                                                }
                                                                                             } else {
                                                                                                var2 = this.mOil_setting;
                                                                                                if (var1 == var2) {
                                                                                                   if (var2.mChecked) {
                                                                                                      this.SetCmdkey((byte)123, (byte)10, (byte)0);
                                                                                                   } else {
                                                                                                      this.SetCmdkey((byte)123, (byte)10, (byte)1);
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                          }
                                                                                       }
                                                                                    }
                                                                                 }
                                                                              }
                                                                           }
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }
}
