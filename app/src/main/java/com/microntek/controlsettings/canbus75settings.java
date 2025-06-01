package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus75settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Car_in_light = 0;
   private int _Front_light = 1;
   private boolean isRefresh = true;
   private HCheckBoxPreference mAuto_adjustment;
   private HCheckBoxPreference mAuto_electric;
   private HCheckBoxPreference mAuto_parking;
   private SwitchboxPreference mB_parksense;
   private HCheckBoxPreference mBackview;
   private HCheckBoxPreference mBeep_lock;
   private PreferenceScreen mBrake_service;
   private SwitchboxPreference mBright_headlights;
   private SwitchboxPreference mBusy_warning;
   private HCheckBoxPreference mBuzzer;
   private OnSwitchPreference mCar_in_light;
   private SwitchboxPreference mCar_type;
   private SwitchboxPreference mCollision_sens;
   private SwitchboxPreference mDeviation_correction;
   private SwitchboxPreference mDis_suspension;
   private HCheckBoxPreference mDoor_alarm;
   private PreferenceScreen mDoor_lock;
   private HCheckBoxPreference mDriving_auto;
   private HCheckBoxPreference mElectronic_park;
   private HCheckBoxPreference mEps_light;
   private SwitchboxPreference mF_parksense;
   private HCheckBoxPreference mFor_outo_warning;
   private SwitchboxPreference mFor_warning;
   private SwitchboxPreference mFor_warning_security;
   private OnSwitchPreference mFront_light;
   private SwitchboxPreference mFulecons;
   private SwitchboxPreference mFulecons_2;
   private HCheckBoxPreference mHeadlights;
   private SwitchboxPreference mHeadlights_off;
   private HCheckBoxPreference mImage_parkView;
   private SwitchboxPreference mKey_unlock;
   private HCheckBoxPreference mKeyless_entry;
   private SwitchboxPreference mLane_warning;
   private PreferenceScreen mLights;
   private HCheckBoxPreference mLights_flash;
   private SwitchboxPreference mLock_beep;
   private PreferenceScreen mOther;
   private HCheckBoxPreference mOuthigh_beam;
   private SwitchboxPreference mOutseat_heating;
   private HCheckBoxPreference mPaddle_shifting;
   private HCheckBoxPreference mParkView;
   private HCheckBoxPreference mParking_brake;
   private HCheckBoxPreference mParking_delays;
   private SwitchboxPreference mParksense;
   private HCheckBoxPreference mPersonalise;
   private HCheckBoxPreference mPower_alarm;
   private SwitchboxPreference mPower_off;
   private SwitchboxPreference mPower_steering;
   private HCheckBoxPreference mRadar_parking;
   private HCheckBoxPreference mRamp;
   private SwitchboxPreference mRange;
   private HCheckBoxPreference mRear_parkSense;
   private HCheckBoxPreference mRear_view;
   private HCheckBoxPreference mRearview_dimming;
   private HCheckBoxPreference mRemote_beep;
   private HCheckBoxPreference mRemote_control;
   private HCheckBoxPreference mRunning_lights;
   private PreferenceScreen mSafe_driving;
   private HCheckBoxPreference mSeat;
   private HCheckBoxPreference mService_mode;
   private SwitchboxPreference mSpeed_unit;
   private PreferenceScreen mSuspension;
   private PreferenceScreen mTail_off;
   private SwitchboxPreference mTemperature;
   private HCheckBoxPreference mTire_mode;
   private SwitchboxPreference mTireunit;
   private HCheckBoxPreference mTpms;
   private HCheckBoxPreference mTransport_mode;
   private HCheckBoxPreference mTurn_lights_set;
   private PreferenceScreen mUnit_grep;
   private SwitchboxPreference mUnit_set;
   private HCheckBoxPreference mUnlock_driving;
   private HCheckBoxPreference mUnlock_light;
   private HCheckBoxPreference mWarning_active;
   private HCheckBoxPreference mWelcome_light;
   private HCheckBoxPreference mWheel_mode;
   private HCheckBoxPreference mWipers_induction;
   private HCheckBoxPreference mWipers_start;
   private byte[] setData = new byte[20];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-105, new byte[]{var1, var2}, 2);
   }

   private int getData(int var1) {
      int var2;
      if (var1 == 1) {
         var2 = 3;
      } else if (var1 == 2) {
         var2 = 20;
      } else {
         var2 = var1;
         if (var1 == 3) {
            var2 = 40;
         }
      }

      return var2;
   }

   private void removeAllPreferenceScreen() {
      for(int var1 = 0; var1 < this.getPreferenceScreen().getPreferenceCount(); ++var1) {
         Preference var2 = this.getPreferenceScreen().getPreference(var1);
         if (var2 instanceof PreferenceScreen) {
            ((PreferenceScreen)var2).removeAll();
         }
      }

      this.removeAll();
   }

   private void showListItem(int var1) {
      this.removeAllPreferenceScreen();
      if (var1 == 0) {
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mF_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mB_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParkView);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mWipers_induction);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mRamp);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mImage_parkView);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mRadar_parking);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mBrake_service);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParking_brake);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mLane_warning);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mDeviation_correction);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mBusy_warning);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mFor_outo_warning);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mFor_warning);
         this.addPreferenceScreenGroup(this.mLights, this.mHeadlights_off);
         this.addPreferenceScreenGroup(this.mLights, this.mBright_headlights);
         this.addPreferenceScreenGroup(this.mLights, this.mWipers_start);
         this.addPreferenceScreenGroup(this.mLights, this.mRunning_lights);
         this.addPreferenceScreenGroup(this.mLights, this.mLights_flash);
         this.addPreferenceScreenGroup(this.mLights, this.mOuthigh_beam);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mDriving_auto);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mUnlock_driving);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKey_unlock);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKeyless_entry);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mDoor_alarm);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mUnit_set);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mOutseat_heating);
         this.addPreferenceScreenGroup(this.mTail_off, this.mPower_off);
         this.addPreferenceScreenGroup(this.mOther, this.mRearview_dimming);
         this.addPreferenceScreenGroup(this.mOther, this.mBuzzer);
         this.addPreferenceScreenGroup(this.mOther, this.mParking_delays);
      } else if (var1 == 1) {
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mB_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParkView);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mElectronic_park);
         this.addPreferenceScreenGroup(this.mLights, this.mHeadlights_off);
         this.addPreferenceScreenGroup(this.mLights, this.mRunning_lights);
         this.addPreferenceScreenGroup(this.mLights, this.mLights_flash);
         this.addPreferenceScreenGroup(this.mLights, this.mWelcome_light);
         this.addPreferenceScreenGroup(this.mLights, this.mTurn_lights_set);
         this.addPreferenceScreenGroup(this.mLights, this.mCar_in_light);
         this.addPreferenceScreenGroup(this.mLights, this.mFront_light);
         this.addPreferenceScreenGroup(this.mLights, this.mUnlock_light);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mDriving_auto);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKey_unlock);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKeyless_entry);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mLock_beep);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mUnit_set);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mFulecons);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTireunit);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTemperature);
         this.addPreferenceScreenGroup(this.mOther, this.mBuzzer);
      } else if (var1 == 2) {
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mB_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParkView);
         this.addPreferenceScreenGroup(this.mLights, this.mHeadlights_off);
         this.addPreferenceScreenGroup(this.mLights, this.mRunning_lights);
         this.addPreferenceScreenGroup(this.mLights, this.mLights_flash);
         this.addPreferenceScreenGroup(this.mLights, this.mCar_in_light);
         this.addPreferenceScreenGroup(this.mLights, this.mFront_light);
         this.addPreferenceScreenGroup(this.mLights, this.mUnlock_light);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mDriving_auto);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mUnlock_driving);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKey_unlock);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKeyless_entry);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mRemote_beep);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mLock_beep);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mUnit_set);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTireunit);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTemperature);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mRange);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mFulecons_2);
         this.addPreferenceScreenGroup(this.mOther, this.mBuzzer);
         this.addPreferenceScreenGroup(this.mOther, this.mParking_delays);
         this.addPreferenceScreenGroup(this.mOther, this.mAuto_parking);
         this.addPreferenceScreenGroup(this.mOther, this.mService_mode);
      } else if (var1 == 3) {
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mF_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mB_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mBackview);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParkView);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mWipers_induction);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mRamp);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mImage_parkView);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mBrake_service);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mLane_warning);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mDeviation_correction);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mBusy_warning);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mFor_warning_security);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mCollision_sens);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mWarning_active);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mRear_parkSense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mTpms);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mRear_view);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mHeadlights);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mAuto_electric);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mRemote_control);
         this.addPreferenceScreenGroup(this.mLights, this.mHeadlights_off);
         this.addPreferenceScreenGroup(this.mLights, this.mBright_headlights);
         this.addPreferenceScreenGroup(this.mLights, this.mWipers_start);
         this.addPreferenceScreenGroup(this.mLights, this.mRunning_lights);
         this.addPreferenceScreenGroup(this.mLights, this.mLights_flash);
         this.addPreferenceScreenGroup(this.mLights, this.mOuthigh_beam);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mUnlock_driving);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKey_unlock);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKeyless_entry);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mPersonalise);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mPower_alarm);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mRemote_beep);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mUnit_set);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mOutseat_heating);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTireunit);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTemperature);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mRange);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mFulecons_2);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mSpeed_unit);
         this.addPreferenceScreenGroup(this.mTail_off, this.mSeat);
         this.addPreferenceScreenGroup(this.mOther, this.mParking_delays);
         this.addPreferenceScreenGroup(this.mOther, this.mAuto_parking);
      } else if (var1 == 4) {
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mBackview);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mBeep_lock);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mPower_alarm);
         this.addPreferenceScreenGroup(this.mTail_off, this.mSeat);
         this.addPreferenceScreenGroup(this.mSuspension, this.mAuto_adjustment);
         this.addPreferenceScreenGroup(this.mSuspension, this.mDis_suspension);
         this.addPreferenceScreenGroup(this.mSuspension, this.mTire_mode);
         this.addPreferenceScreenGroup(this.mSuspension, this.mTransport_mode);
         this.addPreferenceScreenGroup(this.mSuspension, this.mWheel_mode);
         this.addPreferenceScreenGroup(this.mSuspension, this.mPower_steering);
         this.addPreferenceScreenGroup(this.mSuspension, this.mPaddle_shifting);
      } else if (var1 == 5) {
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mF_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mB_parksense);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mParkView);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mRamp);
         this.addPreferenceScreenGroup(this.mSafe_driving, this.mImage_parkView);
         this.addPreferenceScreenGroup(this.mLights, this.mHeadlights_off);
         this.addPreferenceScreenGroup(this.mLights, this.mBright_headlights);
         this.addPreferenceScreenGroup(this.mLights, this.mWipers_start);
         this.addPreferenceScreenGroup(this.mLights, this.mLights_flash);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mDriving_auto);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mUnlock_driving);
         this.addPreferenceScreenGroup(this.mDoor_lock, this.mKey_unlock);
         this.addPreferenceScreenGroup(this.mTail_off, this.mPower_off);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mUnit_set);
         this.addPreferenceScreenGroup(this.mOther, this.mBuzzer);
         this.addPreferenceScreenGroup(this.mOther, this.mParking_delays);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTireunit);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mTemperature);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mRange);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mFulecons_2);
         this.addPreferenceScreenGroup(this.mUnit_grep, this.mSpeed_unit);
      }

      this.addPreferenceScreenGroup(this.mOther, this.mCar_type);
      if (this.mSafe_driving.getPreferenceCount() > 0) {
         this.addPreference(this.mSafe_driving);
      }

      if (this.mLights.getPreferenceCount() > 0) {
         this.addPreference(this.mLights);
      }

      if (this.mDoor_lock.getPreferenceCount() > 0) {
         this.addPreference(this.mDoor_lock);
      }

      if (this.mTail_off.getPreferenceCount() > 0) {
         this.addPreference(this.mTail_off);
      }

      if (this.mSuspension.getPreferenceCount() > 0) {
         this.addPreference(this.mSuspension);
      }

      if (this.mUnit_grep.getPreferenceCount() > 0) {
         this.addPreference(this.mUnit_grep);
      }

      if (this.mOther.getPreferenceCount() > 0) {
         this.addPreference(this.mOther);
      }

   }

   protected void ProcessData(byte[] var1) {
      boolean var5 = false;
      int var2 = var1[0];
      int var3 = var1.length;
      if (var3 > 2) {
         if (var2 == 7) {
            for(var2 = 2; var2 < var3; ++var2) {
               this.setData[var2 - 2] = var1[var2];
            }

            SwitchboxPreference var6 = this.mParksense;
            StringBuilder var7 = new StringBuilder();
            var7.append("");
            var7.append((this.setData[0] >> 6 & 3) - 1);
            var6.setValue(var7.toString());
            var6 = this.mF_parksense;
            var7 = new StringBuilder();
            var7.append("");
            var7.append((this.setData[0] >> 4 & 3) - 1);
            var6.setValue(var7.toString());
            SwitchboxPreference var8 = this.mB_parksense;
            StringBuilder var10 = new StringBuilder();
            var10.append("");
            var10.append((this.setData[0] >> 2 & 3) - 1);
            var8.setValue(var10.toString());
            var6 = this.mBusy_warning;
            var7 = new StringBuilder();
            var7.append("");
            var7.append((this.setData[1] >> 4 & 3) - 1);
            var6.setValue(var7.toString());
            var6 = this.mFor_warning;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[8] >> 5 & 1);
            var6.setValue(var7.toString());
            var8 = this.mOutseat_heating;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[8] & 3);
            var8.setValue(var10.toString());
            var6 = this.mLane_warning;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[9] >> 3 & 3);
            var6.setValue(var7.toString());
            var8 = this.mDeviation_correction;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[7] & 3);
            var8.setValue(var10.toString());
            var8 = this.mBright_headlights;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[9] >> 5 & 3);
            var8.setValue(var10.toString());
            var6 = this.mKey_unlock;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[3] >> 3 & 1);
            var6.setValue(var7.toString());
            var8 = this.mPower_off;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[4] >> 5 & 3);
            var8.setValue(var10.toString());
            var6 = this.mDis_suspension;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[5] >> 6 & 1);
            var6.setValue(var7.toString());
            var6 = this.mUnit_set;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[6] >> 6 & 1);
            var6.setValue(var7.toString());
            var6 = this.mHeadlights_off;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[2] >> 6 & 3);
            var6.setValue(var7.toString());
            boolean var4;
            HCheckBoxPreference var9;
            if (this.isRefresh) {
               var9 = this.mBackview;
               if ((this.setData[0] & 2) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mParkView;
               if ((this.setData[0] & 1) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mWipers_induction;
               if ((this.setData[1] & 128) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mRamp;
               if ((this.setData[1] & 64) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mImage_parkView;
               if ((this.setData[7] & 128) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mRadar_parking;
               if ((this.setData[7] & 64) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mParking_brake;
               if ((this.setData[7] & 16) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mFor_outo_warning;
               if ((this.setData[8] & 64) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mOuthigh_beam;
               if ((this.setData[8] & 16) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mDoor_alarm;
               if ((this.setData[8] & 4) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mWipers_start;
               if ((this.setData[2] & 8) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mRunning_lights;
               if ((this.setData[2] & 4) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mLights_flash;
               if ((this.setData[2] & 2) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mDriving_auto;
               if ((this.setData[3] & 128) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mUnlock_driving;
               if ((this.setData[3] & 64) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mBeep_lock;
               if ((this.setData[3] & 16) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mKeyless_entry;
               if ((this.setData[3] & 4) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mPersonalise;
               if ((this.setData[3] & 2) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mPower_alarm;
               if ((this.setData[3] & 1) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mSeat;
               if ((this.setData[4] & 128) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mAuto_adjustment;
               if ((this.setData[5] & 128) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mTire_mode;
               if ((this.setData[5] & 32) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mTransport_mode;
               if ((this.setData[5] & 16) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mWheel_mode;
               if ((this.setData[5] & 8) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mRearview_dimming;
               if ((this.setData[6] & 128) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mBuzzer;
               if ((this.setData[9] & 1) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
            }

            var8 = this.mFulecons;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[10] >> 7 & 1);
            var8.setValue(var10.toString());
            var8 = this.mTireunit;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[10] >> 5 & 3);
            var8.setValue(var10.toString());
            var6 = this.mTemperature;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[10] >> 4 & 1);
            var6.setValue(var7.toString());
            var9 = this.mElectronic_park;
            if ((this.setData[10] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mWelcome_light;
            if ((this.setData[10] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mTurn_lights_set;
            if ((this.setData[10] & 2) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var1 = this.setData;
            OnSwitchPreference var11;
            if ((var1[11] & 7) <= 6) {
               this._Car_in_light = var1[11] & 7;
               var11 = this.mCar_in_light;
               var10 = new StringBuilder();
               var10.append("");
               var10.append(this._Car_in_light);
               var11.setValue("0", var10.toString());
            }

            var1 = this.setData;
            if ((var1[11] >> 3 & 3) >= 1 && (var1[11] >> 3 & 3) <= 3) {
               this._Front_light = var1[11] >> 3 & 3;
               var11 = this.mFront_light;
               var10 = new StringBuilder();
               var10.append("");
               var10.append(this._Front_light);
               var11.setValue("0", var10.toString());
            }

            var8 = this.mLock_beep;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[11] >> 5 & 3);
            var8.setValue(var10.toString());
            var9 = this.mUnlock_light;
            if ((this.setData[10] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var8 = this.mFulecons_2;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[12] >> 5 & 3);
            var8.setValue(var10.toString());
            var6 = this.mRange;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[12] >> 7 & 1);
            var6.setValue(var7.toString());
            var9 = this.mRemote_beep;
            if ((this.setData[12] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mParking_delays;
            if ((this.setData[12] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mAuto_parking;
            if ((this.setData[13] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mService_mode;
            if ((this.setData[13] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mEps_light;
            if ((this.setData[12] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var8 = this.mPower_steering;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[5] >> 1 & 3);
            var8.setValue(var10.toString());
            var9 = this.mPaddle_shifting;
            if ((this.setData[5] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var8 = this.mSpeed_unit;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[12] >> 1 & 1);
            var8.setValue(var10.toString());
            var6 = this.mFor_warning_security;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[6] >> 2 & 3);
            var6.setValue(var7.toString());
            var8 = this.mCollision_sens;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[6] & 3);
            var8.setValue(var10.toString());
            var9 = this.mWarning_active;
            if ((this.setData[6] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mTpms;
            if ((this.setData[9] & 2) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mRear_parkSense;
            if ((this.setData[9] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mRear_view;
            if ((this.setData[12] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mHeadlights;
            if ((this.setData[2] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mAuto_electric;
            if ((this.setData[4] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mRemote_control;
            var4 = var5;
            if ((this.setData[4] & 16) != 0) {
               var4 = true;
            }

            var9.setChecked(var4);
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492939);
      this.mBackview = this.findHCheckBoxPreference("backview");
      this.mParkView = this.findHCheckBoxPreference("parkView");
      this.mWipers_induction = this.findHCheckBoxPreference("wipers_induction");
      this.mRamp = this.findHCheckBoxPreference("ramp");
      this.mWipers_start = this.findHCheckBoxPreference("wipers_start");
      this.mRunning_lights = this.findHCheckBoxPreference("running_lights");
      this.mLights_flash = this.findHCheckBoxPreference("lights_flash");
      this.mImage_parkView = this.findHCheckBoxPreference("image_parkView");
      this.mRadar_parking = this.findHCheckBoxPreference("radar_parking");
      this.mParking_brake = this.findHCheckBoxPreference("parking_brake");
      this.mDriving_auto = this.findHCheckBoxPreference("driving_auto");
      this.mUnlock_driving = this.findHCheckBoxPreference("unlock_driving");
      this.mBeep_lock = this.findHCheckBoxPreference("beep_lock");
      this.mKeyless_entry = this.findHCheckBoxPreference("keyless_entry");
      this.mPersonalise = this.findHCheckBoxPreference("personalise");
      this.mPower_alarm = this.findHCheckBoxPreference("power_alarm");
      this.mSeat = this.findHCheckBoxPreference("seat");
      this.mAuto_adjustment = this.findHCheckBoxPreference("auto_adjustment");
      this.mTire_mode = this.findHCheckBoxPreference("tire_mode");
      this.mTransport_mode = this.findHCheckBoxPreference("transport_mode");
      this.mWheel_mode = this.findHCheckBoxPreference("wheel_mode");
      this.mRearview_dimming = this.findHCheckBoxPreference("rearview_dimming");
      this.mFor_outo_warning = this.findHCheckBoxPreference("for_outo_warning");
      this.mOuthigh_beam = this.findHCheckBoxPreference("outhigh_beam");
      this.mDoor_alarm = this.findHCheckBoxPreference("door_alarm");
      this.mBuzzer = this.findHCheckBoxPreference("buzzer");
      this.mElectronic_park = this.findHCheckBoxPreference("electronic_park");
      this.mWelcome_light = this.findHCheckBoxPreference("welcome_light");
      this.mTurn_lights_set = this.findHCheckBoxPreference("turn_lights_set");
      this.mUnlock_light = this.findHCheckBoxPreference("unlock_light");
      this.mRemote_beep = this.findHCheckBoxPreference("remote_beep");
      this.mParking_delays = this.findHCheckBoxPreference("parking_delays");
      this.mAuto_parking = this.findHCheckBoxPreference("auto_parking");
      this.mService_mode = this.findHCheckBoxPreference("service_mode");
      this.mEps_light = this.findHCheckBoxPreference("eps_light");
      this.mPaddle_shifting = this.findHCheckBoxPreference("paddle_shifting");
      this.mWarning_active = this.findHCheckBoxPreference("warning_active");
      this.mTpms = this.findHCheckBoxPreference("tpms");
      this.mRear_parkSense = this.findHCheckBoxPreference("rear_parkSense");
      this.mRear_view = this.findHCheckBoxPreference("rear_view");
      this.mHeadlights = this.findHCheckBoxPreference("headlights");
      this.mAuto_electric = this.findHCheckBoxPreference("auto_electric");
      this.mRemote_control = this.findHCheckBoxPreference("remote_control");
      this.mParksense = this.findSwitchboxPreference("parksense");
      this.mB_parksense = this.findSwitchboxPreference("b_parksense");
      this.mF_parksense = this.findSwitchboxPreference("f_parksense");
      this.mHeadlights_off = this.findSwitchboxPreference("headlights_off");
      this.mBright_headlights = this.findSwitchboxPreference("bright_headlights");
      this.mKey_unlock = this.findSwitchboxPreference("key_unlock");
      this.mPower_off = this.findSwitchboxPreference("power_off");
      this.mUnit_set = this.findSwitchboxPreference("unit_set");
      this.mLane_warning = this.findSwitchboxPreference("lane_warning");
      this.mDeviation_correction = this.findSwitchboxPreference("deviation_correction");
      this.mDis_suspension = this.findSwitchboxPreference("dis_suspension");
      this.mBusy_warning = this.findSwitchboxPreference("busy_warning");
      this.mFor_warning = this.findSwitchboxPreference("for_warning");
      this.mOutseat_heating = this.findSwitchboxPreference("outseat_heating");
      this.mFulecons = this.findSwitchboxPreference("fulecons");
      this.mTireunit = this.findSwitchboxPreference("tireunit");
      this.mTemperature = this.findSwitchboxPreference("temperature");
      this.mLock_beep = this.findSwitchboxPreference("lock_beep");
      this.mFulecons_2 = this.findSwitchboxPreference("fulecons_2");
      this.mRange = this.findSwitchboxPreference("range");
      this.mPower_steering = this.findSwitchboxPreference("power_steering");
      this.mSpeed_unit = this.findSwitchboxPreference("speed_unit");
      this.mFor_warning_security = this.findSwitchboxPreference("for_warning_security");
      this.mCollision_sens = this.findSwitchboxPreference("collision_sens");
      this.mCar_type = this.findSwitchboxPreference("car_type");
      SwitchboxPreference var3 = this.mCar_type;
      StringBuilder var2 = new StringBuilder();
      var2.append("");
      var2.append(this.getInt("mCar_type", 0));
      var3.setValue(var2.toString());
      this.mBrake_service = (PreferenceScreen)this.findPreference("brake_service");
      this.mSafe_driving = this.findPreferenceScreen("safe_driving");
      this.mLights = this.findPreferenceScreen("lights");
      this.mDoor_lock = this.findPreferenceScreen("door_lock");
      this.mTail_off = this.findPreferenceScreen("tail_off");
      this.mSuspension = this.findPreferenceScreen("suspension");
      this.mUnit_grep = this.findPreferenceScreen("unit_grep");
      this.mOther = this.findPreferenceScreen("other");
      this.mCar_in_light = this.findOnSwitchPreference("car_in_light");
      this.mFront_light = this.findOnSwitchPreference("front_light");
      this.SendCanBusCmdData2E((byte)-15, new byte[]{7}, 1);
      this.showListItem(BaseApplication.getInstance().getCarType());
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mCar_in_light;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var3 = this._Car_in_light;
         if (var3 + var4 < 0 || var3 + var4 > 6 || !this.mCar_in_light.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)-110, (byte)(this._Car_in_light + var4 & 255));
      } else {
         var5 = this.mFront_light;
         if (var1 == var5) {
            var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var4 = this._Front_light;
            if (var4 + var3 < 1 || var4 + var3 > 3 || !this.mFront_light.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)-109, (byte)(this._Front_light + var3 & 255));
         }
      }

      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var7) {
         return false;
      }

      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mParksense) {
            this.SetCmdkey((byte)1, (byte)(var4 + 1));
         } else if (var1 == this.mB_parksense) {
            this.SetCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mF_parksense) {
            this.SetCmdkey((byte)2, (byte)var4);
         } else if (var1 == this.mHeadlights_off) {
            this.SetCmdkey((byte)17, (byte)(var4 * 30));
         } else if (var1 == this.mKey_unlock) {
            this.SetCmdkey((byte)36, (byte)(var4 + 1));
         } else if (var1 == this.mPower_off) {
            this.SetCmdkey((byte)50, (byte)this.getData(var4));
         } else if (var1 == this.mUnit_set) {
            this.SetCmdkey((byte)82, (byte)var4);
         } else if (var1 == this.mLane_warning) {
            this.SetCmdkey((byte)12, (byte)var4);
         } else if (var1 == this.mDeviation_correction) {
            this.SetCmdkey((byte)13, (byte)var4);
         } else if (var1 == this.mBright_headlights) {
            this.SetCmdkey((byte)18, (byte)(var4 * 30));
         } else if (var1 == this.mDis_suspension) {
            this.SetCmdkey((byte)66, (byte)(var4 + 1));
         } else if (var1 == this.mBusy_warning) {
            this.SetCmdkey((byte)14, (byte)(var4 + 1));
         } else if (var1 == this.mFor_warning) {
            this.SetCmdkey((byte)16, (byte)(var4 + 1));
         } else if (var1 == this.mOutseat_heating) {
            this.SetCmdkey((byte)84, (byte)(var4 + 1));
         } else if (var1 == this.mFulecons) {
            this.SetCmdkey((byte)112, (byte)(var4 + 0));
         } else if (var1 == this.mTireunit) {
            this.SetCmdkey((byte)113, (byte)(var4 + 0));
         } else if (var1 == this.mTemperature) {
            this.SetCmdkey((byte)114, (byte)(var4 + 0));
         } else if (var1 == this.mLock_beep) {
            this.SetCmdkey((byte)-96, (byte)(var4 + 0));
         } else if (var1 == this.mFulecons_2) {
            this.SetCmdkey((byte)116, (byte)(var4 + 0));
         } else if (var1 == this.mRange) {
            this.SetCmdkey((byte)115, (byte)(var4 + 0));
         } else if (var1 == this.mPower_steering) {
            this.SetCmdkey((byte)70, (byte)(var4 + 0));
         } else if (var1 == this.mSpeed_unit) {
            this.SetCmdkey((byte)117, (byte)(var4 + 1));
         } else if (var1 == this.mFor_warning_security) {
            this.SetCmdkey((byte)-127, (byte)(var4 + 0));
         } else if (var1 == this.mCollision_sens) {
            this.SetCmdkey((byte)-126, (byte)(var4 + 0));
         } else {
            SwitchboxPreference var9 = this.mCar_type;
            if (var1 == var9) {
               StringBuilder var8 = new StringBuilder();
               var8.append("");
               var8.append(var4);
               var9.setValue(var8.toString());
               this.putInt("mCar_type", var4);
               this.SendCanBusCmdData2E((byte)-18, new byte[]{96, (byte)var4}, 2);
            }
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mBackview;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)4, (byte)1);
            this.mBackview.setChecked(false);
         } else {
            this.SetCmdkey((byte)4, (byte)2);
            this.mBackview.setChecked(true);
         }
      } else {
         var2 = this.mParkView;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)5, (byte)0);
               this.mParkView.setChecked(false);
            } else {
               this.SetCmdkey((byte)5, (byte)1);
               this.mParkView.setChecked(true);
            }
         } else {
            var2 = this.mWipers_induction;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)6, (byte)1);
                  this.mWipers_induction.setChecked(false);
               } else {
                  this.SetCmdkey((byte)6, (byte)2);
                  this.mWipers_induction.setChecked(true);
               }
            } else {
               var2 = this.mRamp;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)7, (byte)1);
                     this.mRamp.setChecked(false);
                  } else {
                     this.SetCmdkey((byte)7, (byte)2);
                     this.mRamp.setChecked(true);
                  }
               } else {
                  var2 = this.mWipers_start;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)19, (byte)1);
                        this.mWipers_start.setChecked(false);
                     } else {
                        this.SetCmdkey((byte)19, (byte)2);
                        this.mWipers_start.setChecked(true);
                     }
                  } else {
                     var2 = this.mRunning_lights;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)20, (byte)1);
                           this.mRunning_lights.setChecked(false);
                        } else {
                           this.SetCmdkey((byte)20, (byte)2);
                           this.mRunning_lights.setChecked(true);
                        }
                     } else {
                        var2 = this.mLights_flash;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)21, (byte)1);
                              this.mLights_flash.setChecked(false);
                           } else {
                              this.SetCmdkey((byte)21, (byte)2);
                              this.mLights_flash.setChecked(true);
                           }
                        } else {
                           var2 = this.mDriving_auto;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)33, (byte)1);
                                 this.mDriving_auto.setChecked(false);
                              } else {
                                 this.SetCmdkey((byte)33, (byte)2);
                                 this.mDriving_auto.setChecked(true);
                              }
                           } else {
                              var2 = this.mUnlock_driving;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)34, (byte)1);
                                    this.mUnlock_driving.setChecked(false);
                                 } else {
                                    this.SetCmdkey((byte)34, (byte)2);
                                    this.mUnlock_driving.setChecked(true);
                                 }
                              } else {
                                 var2 = this.mBeep_lock;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)35, (byte)1);
                                       this.mBeep_lock.setChecked(false);
                                    } else {
                                       this.SetCmdkey((byte)35, (byte)2);
                                       this.mBeep_lock.setChecked(true);
                                    }
                                 } else {
                                    var2 = this.mKeyless_entry;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)37, (byte)1);
                                          this.mKeyless_entry.setChecked(false);
                                       } else {
                                          this.SetCmdkey((byte)37, (byte)2);
                                          this.mKeyless_entry.setChecked(true);
                                       }
                                    } else {
                                       var2 = this.mPersonalise;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)38, (byte)1);
                                             this.mPersonalise.setChecked(false);
                                          } else {
                                             this.SetCmdkey((byte)38, (byte)2);
                                             this.mPersonalise.setChecked(true);
                                          }
                                       } else {
                                          var2 = this.mPower_alarm;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)39, (byte)1);
                                                this.mPower_alarm.setChecked(false);
                                             } else {
                                                this.SetCmdkey((byte)39, (byte)2);
                                                this.mPower_alarm.setChecked(true);
                                             }
                                          } else {
                                             var2 = this.mSeat;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)49, (byte)1);
                                                   this.mSeat.setChecked(false);
                                                } else {
                                                   this.SetCmdkey((byte)49, (byte)2);
                                                   this.mSeat.setChecked(true);
                                                }
                                             } else {
                                                var2 = this.mAuto_adjustment;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)65, (byte)1);
                                                      this.mAuto_adjustment.setChecked(false);
                                                   } else {
                                                      this.SetCmdkey((byte)65, (byte)2);
                                                      this.mAuto_adjustment.setChecked(true);
                                                   }
                                                } else {
                                                   var2 = this.mTire_mode;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)67, (byte)1);
                                                         this.mTire_mode.setChecked(false);
                                                      } else {
                                                         this.SetCmdkey((byte)67, (byte)2);
                                                         this.mTire_mode.setChecked(true);
                                                      }
                                                   } else {
                                                      var2 = this.mTransport_mode;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)68, (byte)1);
                                                            this.mTransport_mode.setChecked(false);
                                                         } else {
                                                            this.SetCmdkey((byte)68, (byte)2);
                                                            this.mTransport_mode.setChecked(true);
                                                         }
                                                      } else {
                                                         var2 = this.mWheel_mode;
                                                         if (var1 == var2) {
                                                            if (var2.mChecked) {
                                                               this.SetCmdkey((byte)69, (byte)1);
                                                               this.mWheel_mode.setChecked(false);
                                                            } else {
                                                               this.SetCmdkey((byte)69, (byte)2);
                                                               this.mWheel_mode.setChecked(true);
                                                            }
                                                         } else {
                                                            var2 = this.mRearview_dimming;
                                                            if (var1 == var2) {
                                                               if (var2.mChecked) {
                                                                  this.SetCmdkey((byte)81, (byte)1);
                                                                  this.mRearview_dimming.setChecked(false);
                                                               } else {
                                                                  this.SetCmdkey((byte)81, (byte)2);
                                                                  this.mRearview_dimming.setChecked(true);
                                                               }
                                                            } else {
                                                               var2 = this.mImage_parkView;
                                                               if (var1 == var2) {
                                                                  if (var2.mChecked) {
                                                                     this.SetCmdkey((byte)8, (byte)0);
                                                                     this.mImage_parkView.setChecked(false);
                                                                  } else {
                                                                     this.SetCmdkey((byte)8, (byte)1);
                                                                     this.mImage_parkView.setChecked(true);
                                                                  }
                                                               } else {
                                                                  var2 = this.mRadar_parking;
                                                                  if (var1 == var2) {
                                                                     if (var2.mChecked) {
                                                                        this.SetCmdkey((byte)9, (byte)1);
                                                                        this.mRadar_parking.setChecked(false);
                                                                     } else {
                                                                        this.SetCmdkey((byte)9, (byte)2);
                                                                        this.mRadar_parking.setChecked(true);
                                                                     }
                                                                  } else {
                                                                     var2 = this.mParking_brake;
                                                                     if (var1 == var2) {
                                                                        if (var2.mChecked) {
                                                                           this.SetCmdkey((byte)11, (byte)1);
                                                                           this.mParking_brake.setChecked(false);
                                                                        } else {
                                                                           this.SetCmdkey((byte)11, (byte)2);
                                                                           this.mParking_brake.setChecked(true);
                                                                        }
                                                                     } else {
                                                                        var2 = this.mFor_outo_warning;
                                                                        if (var1 == var2) {
                                                                           if (var2.mChecked) {
                                                                              this.SetCmdkey((byte)15, (byte)1);
                                                                              this.mFor_outo_warning.setChecked(false);
                                                                           } else {
                                                                              this.SetCmdkey((byte)15, (byte)2);
                                                                              this.mFor_outo_warning.setChecked(true);
                                                                           }
                                                                        } else {
                                                                           var2 = this.mOuthigh_beam;
                                                                           if (var1 == var2) {
                                                                              if (var2.mChecked) {
                                                                                 this.SetCmdkey((byte)22, (byte)1);
                                                                                 this.mOuthigh_beam.setChecked(false);
                                                                              } else {
                                                                                 this.SetCmdkey((byte)22, (byte)2);
                                                                                 this.mOuthigh_beam.setChecked(true);
                                                                              }
                                                                           } else {
                                                                              var2 = this.mDoor_alarm;
                                                                              if (var1 == var2) {
                                                                                 if (var2.mChecked) {
                                                                                    this.SetCmdkey((byte)40, (byte)1);
                                                                                    this.mDoor_alarm.setChecked(false);
                                                                                 } else {
                                                                                    this.SetCmdkey((byte)40, (byte)2);
                                                                                    this.mDoor_alarm.setChecked(true);
                                                                                 }
                                                                              } else {
                                                                                 var2 = this.mBuzzer;
                                                                                 if (var1 == var2) {
                                                                                    if (var2.mChecked) {
                                                                                       this.SetCmdkey((byte)96, (byte)0);
                                                                                       this.mBuzzer.setChecked(false);
                                                                                    } else {
                                                                                       this.SetCmdkey((byte)96, (byte)1);
                                                                                       this.mBuzzer.setChecked(true);
                                                                                    }
                                                                                 } else {
                                                                                    var2 = this.mElectronic_park;
                                                                                    if (var1 == var2) {
                                                                                       if (var2.mChecked) {
                                                                                          this.SetCmdkey((byte)-128, (byte)1);
                                                                                          this.mElectronic_park.setChecked(false);
                                                                                       } else {
                                                                                          this.SetCmdkey((byte)-128, (byte)2);
                                                                                          this.mElectronic_park.setChecked(true);
                                                                                       }
                                                                                    } else {
                                                                                       var2 = this.mWelcome_light;
                                                                                       if (var1 == var2) {
                                                                                          if (var2.mChecked) {
                                                                                             this.SetCmdkey((byte)-112, (byte)1);
                                                                                             this.mWelcome_light.setChecked(false);
                                                                                          } else {
                                                                                             this.SetCmdkey((byte)-112, (byte)2);
                                                                                             this.mWelcome_light.setChecked(true);
                                                                                          }
                                                                                       } else {
                                                                                          var2 = this.mTurn_lights_set;
                                                                                          if (var1 == var2) {
                                                                                             if (var2.mChecked) {
                                                                                                this.SetCmdkey((byte)-111, (byte)1);
                                                                                                this.mTurn_lights_set.setChecked(false);
                                                                                             } else {
                                                                                                this.SetCmdkey((byte)-111, (byte)2);
                                                                                                this.mTurn_lights_set.setChecked(true);
                                                                                             }
                                                                                          } else {
                                                                                             var2 = this.mUnlock_light;
                                                                                             if (var1 == var2) {
                                                                                                if (var2.mChecked) {
                                                                                                   this.SetCmdkey((byte)-108, (byte)1);
                                                                                                   this.mUnlock_light.setChecked(false);
                                                                                                } else {
                                                                                                   this.SetCmdkey((byte)-108, (byte)2);
                                                                                                   this.mUnlock_light.setChecked(true);
                                                                                                }
                                                                                             } else {
                                                                                                var2 = this.mRemote_beep;
                                                                                                if (var1 == var2) {
                                                                                                   if (var2.mChecked) {
                                                                                                      this.SetCmdkey((byte)41, (byte)1);
                                                                                                      this.mRemote_beep.setChecked(false);
                                                                                                   } else {
                                                                                                      this.SetCmdkey((byte)41, (byte)2);
                                                                                                      this.mRemote_beep.setChecked(true);
                                                                                                   }
                                                                                                } else {
                                                                                                   var2 = this.mParking_delays;
                                                                                                   if (var1 == var2) {
                                                                                                      if (var2.mChecked) {
                                                                                                         this.SetCmdkey((byte)97, (byte)0);
                                                                                                         this.mParking_delays.setChecked(false);
                                                                                                      } else {
                                                                                                         this.SetCmdkey((byte)97, (byte)1);
                                                                                                         this.mParking_delays.setChecked(true);
                                                                                                      }
                                                                                                   } else {
                                                                                                      var2 = this.mAuto_parking;
                                                                                                      if (var1 == var2) {
                                                                                                         if (var2.mChecked) {
                                                                                                            this.SetCmdkey((byte)98, (byte)0);
                                                                                                            this.mAuto_parking.setChecked(false);
                                                                                                         } else {
                                                                                                            this.SetCmdkey((byte)98, (byte)1);
                                                                                                            this.mAuto_parking.setChecked(true);
                                                                                                         }
                                                                                                      } else {
                                                                                                         var2 = this.mService_mode;
                                                                                                         if (var1 == var2) {
                                                                                                            if (var2.mChecked) {
                                                                                                               this.SetCmdkey((byte)99, (byte)0);
                                                                                                               this.mService_mode.setChecked(false);
                                                                                                            } else {
                                                                                                               this.SetCmdkey((byte)99, (byte)1);
                                                                                                               this.mService_mode.setChecked(true);
                                                                                                            }
                                                                                                         } else {
                                                                                                            var2 = this.mEps_light;
                                                                                                            if (var1 == var2) {
                                                                                                               if (var2.mChecked) {
                                                                                                                  this.SetCmdkey((byte)-107, (byte)1);
                                                                                                                  this.mEps_light.setChecked(false);
                                                                                                               } else {
                                                                                                                  this.SetCmdkey((byte)-107, (byte)2);
                                                                                                                  this.mEps_light.setChecked(true);
                                                                                                               }
                                                                                                            } else {
                                                                                                               var2 = this.mPaddle_shifting;
                                                                                                               if (var1 == var2) {
                                                                                                                  if (var2.mChecked) {
                                                                                                                     this.SetCmdkey((byte)71, (byte)1);
                                                                                                                     this.mPaddle_shifting.setChecked(false);
                                                                                                                  } else {
                                                                                                                     this.SetCmdkey((byte)71, (byte)2);
                                                                                                                     this.mPaddle_shifting.setChecked(true);
                                                                                                                  }
                                                                                                               } else {
                                                                                                                  var2 = this.mWarning_active;
                                                                                                                  if (var1 == var2) {
                                                                                                                     if (var2.mChecked) {
                                                                                                                        this.SetCmdkey((byte)-125, (byte)1);
                                                                                                                        this.mWarning_active.setChecked(false);
                                                                                                                     } else {
                                                                                                                        this.SetCmdkey((byte)-125, (byte)2);
                                                                                                                        this.mWarning_active.setChecked(true);
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     var2 = this.mRear_parkSense;
                                                                                                                     if (var1 == var2) {
                                                                                                                        if (var2.mChecked) {
                                                                                                                           this.SetCmdkey((byte)-124, (byte)1);
                                                                                                                           this.mRear_parkSense.setChecked(false);
                                                                                                                        } else {
                                                                                                                           this.SetCmdkey((byte)-124, (byte)2);
                                                                                                                           this.mRear_parkSense.setChecked(true);
                                                                                                                        }
                                                                                                                     } else {
                                                                                                                        var2 = this.mTpms;
                                                                                                                        if (var1 == var2) {
                                                                                                                           if (var2.mChecked) {
                                                                                                                              this.SetCmdkey((byte)-123, (byte)1);
                                                                                                                              this.mTpms.setChecked(false);
                                                                                                                           } else {
                                                                                                                              this.SetCmdkey((byte)-123, (byte)2);
                                                                                                                              this.mTpms.setChecked(true);
                                                                                                                           }
                                                                                                                        } else {
                                                                                                                           var2 = this.mRear_view;
                                                                                                                           if (var1 == var2) {
                                                                                                                              if (var2.mChecked) {
                                                                                                                                 this.SetCmdkey((byte)-122, (byte)1);
                                                                                                                                 this.mRear_view.setChecked(false);
                                                                                                                              } else {
                                                                                                                                 this.SetCmdkey((byte)-122, (byte)2);
                                                                                                                                 this.mRear_view.setChecked(true);
                                                                                                                              }
                                                                                                                           } else {
                                                                                                                              var2 = this.mHeadlights;
                                                                                                                              if (var1 == var2) {
                                                                                                                                 if (var2.mChecked) {
                                                                                                                                    this.SetCmdkey((byte)-121, (byte)1);
                                                                                                                                    this.mHeadlights.setChecked(false);
                                                                                                                                 } else {
                                                                                                                                    this.SetCmdkey((byte)-121, (byte)2);
                                                                                                                                    this.mHeadlights.setChecked(true);
                                                                                                                                 }
                                                                                                                              } else {
                                                                                                                                 var2 = this.mAuto_electric;
                                                                                                                                 if (var1 == var2) {
                                                                                                                                    if (var2.mChecked) {
                                                                                                                                       this.SetCmdkey((byte)-120, (byte)1);
                                                                                                                                       this.mAuto_electric.setChecked(false);
                                                                                                                                    } else {
                                                                                                                                       this.SetCmdkey((byte)-120, (byte)2);
                                                                                                                                       this.mAuto_electric.setChecked(true);
                                                                                                                                    }
                                                                                                                                 } else {
                                                                                                                                    var2 = this.mRemote_control;
                                                                                                                                    if (var1 == var2) {
                                                                                                                                       if (var2.mChecked) {
                                                                                                                                          this.SetCmdkey((byte)-119, (byte)1);
                                                                                                                                          this.mRemote_control.setChecked(false);
                                                                                                                                       } else {
                                                                                                                                          this.SetCmdkey((byte)-119, (byte)2);
                                                                                                                                          this.mRemote_control.setChecked(true);
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

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.mBrake_service) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus75settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)10, (byte)1);
            }
         }, this.mBrake_service.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
