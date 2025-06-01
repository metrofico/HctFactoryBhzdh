package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.util.AttributeSet;
import java.io.PrintStream;

public class canbus78settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Car_in_light = 0;
   private int _For_outo_warning = 0;
   private int _Front_light = 0;
   private int _Interior_light = 0;
   private HCheckBoxPreference mAuto_adjustment;
   private HCheckBoxPreference mAuto_parking;
   private HCheckBoxPreference mAuto_suspension;
   private HCheckBoxPreference mAutoclose;
   private HCheckBoxPreference mAutomatic_lamp;
   private PreferenceScreen mAux_switches;
   private SwitchboxPreference mB_parksense;
   private HCheckBoxPreference mBackview;
   private HCheckBoxPreference mBeep_lock;
   private HCheckBoxPreference mBrake_service;
   private SwitchboxPreference mBright_headlights;
   private SwitchboxPreference mBusy_warning;
   private HCheckBoxPreference mBuzzer;
   private OnSwitchPreference mCar_in_light;
   private SwitchboxPreference mCar_type;
   private SwitchboxPreference mDeviation_correction;
   private SwitchboxPreference mDis_suspension;
   private HCheckBoxPreference mDis_trip_b;
   private HCheckBoxPreference mDoor_alarm;
   private HCheckBoxPreference mDoor_lights_flash;
   private PreferenceCategory mDoor_lock;
   private HCheckBoxPreference mDriving_auto;
   private HCheckBoxPreference mElectronic_park;
   private SwitchboxPreference mF_parksense;
   private SwitchboxPreference mFor_outo_warning;
   private SwitchboxPreference mFor_warning;
   private OnSwitchPreference mFront_light;
   private SwitchboxPreference mFulecons;
   private SwitchboxPreference mHeadlights_off;
   private HCheckBoxPreference mImage_parkView;
   private OnSwitchPreference mInterior_light;
   private SwitchboxPreference mKey_unlock;
   private HCheckBoxPreference mKeyless_entry;
   private SwitchboxPreference mLane_warning;
   private PreferenceCategory mLights;
   private HCheckBoxPreference mLights_flash;
   private SwitchboxPreference mLock_beep;
   private SwitchboxPreference mOffset_alarm;
   private SwitchboxPreference mOffset_alarm_vol;
   private PreferenceCategory mOther;
   private HCheckBoxPreference mOuthigh_beam;
   private SwitchboxPreference mOutseat_heating;
   private HCheckBoxPreference mPaddle_shifting;
   private HCheckBoxPreference mParkView;
   private HCheckBoxPreference mParking_brake;
   private SwitchboxPreference mParksense;
   private HCheckBoxPreference mPersonalise;
   private HCheckBoxPreference mPower_alarm;
   private SwitchboxPreference mPower_mode;
   private SwitchboxPreference mPower_off;
   private SwitchboxPreference mPower_steering;
   private HCheckBoxPreference mRadar_parking;
   private HCheckBoxPreference mRamp;
   private SwitchboxPreference mRange;
   private HCheckBoxPreference mRear_parkSense;
   private HCheckBoxPreference mRear_view;
   private HCheckBoxPreference mRearview_dimming;
   private HCheckBoxPreference mRemote_lock;
   private HCheckBoxPreference mRemote_unlock;
   private HCheckBoxPreference mRunning_lights;
   private PreferenceCategory mSafe_driving;
   private HCheckBoxPreference mSeat;
   private SwitchboxPreference mSpeedunit;
   private SwitchboxPreference mTail_headlights_off;
   private PreferenceCategory mTail_off;
   private SwitchboxPreference mTemperature;
   private HCheckBoxPreference mTire_mode;
   private SwitchboxPreference mTireunit;
   private HCheckBoxPreference mTransport_mode;
   private HCheckBoxPreference mTurn_lights_set;
   private SwitchboxPreference mUnit_set;
   private HCheckBoxPreference mUnlock_driving;
   private HCheckBoxPreference mUnlock_light;
   private HCheckBoxPreference mWelcome_light;
   private HCheckBoxPreference mWheel_mode;
   private HCheckBoxPreference mWipers_induction;
   private HCheckBoxPreference mWipers_start;
   private byte[] setData = new byte[40];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
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

   private void showListItem() {
      if (this.getCarType() == 8) {
         this.getPreferenceScreen().removeAll();
         this.addPreference(this.mLane_warning);
         this.addPreference(this.mDeviation_correction);
         this.addPreference(this.mParksense);
         this.addPreference(this.mB_parksense);
         this.addPreference(this.mParkView);
         this.addPreference(this.mWipers_induction);
         this.addPreference(this.mUnit_set);
         this.addPreference(this.mRange);
         this.addPreference(this.mFulecons);
         this.addPreference(this.mTemperature);
         this.addPreference(this.mAuto_parking);
         this.addPreference(this.mInterior_light);
         this.addPreference(this.mFront_light);
         this.addPreference(this.mHeadlights_off);
         this.addPreference(this.mUnlock_light);
         this.addPreference(this.mTurn_lights_set);
         this.addPreference(this.mLights_flash);
         this.addPreference(this.mDriving_auto);
         this.addPreference(this.mDoor_lights_flash);
         this.addPreference(this.mRemote_unlock);
         this.addPreference(this.mTail_headlights_off);
      } else if (this.getCarType() == 5) {
         this.getPreferenceScreen().removeAll();
         this.addPreference(this.mParksense);
         this.addPreference(this.mParkView);
         this.addPreference(this.mHeadlights_off);
         this.addPreference(this.mRunning_lights);
         this.addPreference(this.mDoor_lights_flash);
         this.addPreference(this.mLights_flash);
         this.addPreference(this.mTurn_lights_set);
         this.addPreference(this.mDriving_auto);
         this.addPreference(this.mBeep_lock);
         this.addPreference(this.mKeyless_entry);
         this.addPreference(this.mUnit_set);
         this.addPreference(this.mFulecons);
         this.addPreference(this.mRange);
         this.addPreference(this.mTemperature);
         this.addPreference(this.mAuto_parking);
         this.addPreference(this.mCar_type);
         this.addPreference(this.mTireunit);
         this.addPreference(this.mUnlock_light);
         this.addPreference(this.mRemote_unlock);
         this.addPreference(this.mSafe_driving);
         this.addPreference(this.mLights);
         this.addPreference(this.mDoor_lock);
         this.addPreference(this.mOther);
      } else if (this.getCarType() == 7) {
         this.getPreferenceScreen().removeAll();
         this.addPreference(this.mUnit_set);
         this.addPreference(this.mRange);
         this.addPreference(this.mTemperature);
         this.addPreference(this.mFulecons);
         this.addPreference(this.mTireunit);
         this.addPreference(this.mHeadlights_off);
         this.addPreference(this.mLights_flash);
         this.addPreference(this.mRunning_lights);
         this.addPreference(this.mTurn_lights_set);
         this.addPreference(this.mDriving_auto);
         this.addPreference(this.mDoor_lights_flash);
         this.addPreference(this.mKeyless_entry);
         this.addPreference(this.mBeep_lock);
         this.addPreference(this.mRemote_unlock);
         this.addPreference(this.mTail_headlights_off);
         this.addPreference(this.mParksense);
         this.addPreference(this.mB_parksense);
         this.addPreference(this.mParkView);
         this.addPreference(this.mAuto_parking);
         this.addPreference(this.mCar_type);
         this.addPreference(this.mSafe_driving);
         this.addPreference(this.mLights);
         this.addPreference(this.mDoor_lock);
         this.addPreference(this.mTail_off);
         this.addPreference(this.mOther);
      } else if (this.getCarType() == 6) {
         this.getPreferenceScreen().removeAll();
         this.addPreference(this.mUnit_set);
         this.addPreference(this.mRange);
         this.addPreference(this.mTemperature);
         this.addPreference(this.mFulecons);
         this.addPreference(this.mTireunit);
         this.addPreference(this.mSpeedunit);
         this.addPreference(this.mBright_headlights);
         this.addPreference(this.mLights_flash);
         this.addPreference(this.mRunning_lights);
         this.addPreference(this.mWipers_start);
         this.addPreference(this.mInterior_light);
         this.addPreference(this.mDriving_auto);
         this.addPreference(this.mUnlock_driving);
         this.addPreference(this.mDoor_lights_flash);
         this.addPreference(this.mKey_unlock);
         this.addPreference(this.mKeyless_entry);
         this.addPreference(this.mBeep_lock);
         this.addPreference(this.mPersonalise);
         this.addPreference(this.mPower_alarm);
         this.addPreference(this.mRemote_lock);
         this.addPreference(this.mTail_headlights_off);
         this.addPreference(this.mPower_off);
         this.addPreference(this.mSeat);
         this.addPreference(this.mOutseat_heating);
         this.addPreference(this.mParksense);
         this.addPreference(this.mF_parksense);
         this.addPreference(this.mB_parksense);
         this.addPreference(this.mParkView);
         this.addPreference(this.mImage_parkView);
         this.addPreference(this.mRamp);
         this.addPreference(this.mBusy_warning);
         this.addPreference(this.mBackview);
         this.addPreference(this.mWipers_induction);
         this.addPreference(this.mAuto_parking);
         this.addPreference(this.mTire_mode);
         this.addPreference(this.mTransport_mode);
         this.addPreference(this.mWheel_mode);
         this.addPreference(this.mDis_suspension);
         this.addPreference(this.mPower_steering);
         this.addPreference(this.mPaddle_shifting);
         this.addPreference(this.mSafe_driving);
         this.addPreference(this.mLights);
         this.addPreference(this.mDoor_lock);
         this.addPreference(this.mTail_off);
         this.addPreference(this.mOther);
         this.addPreference(this.mAutomatic_lamp);
         this.addPreference(this.mRear_view);
         this.addPreference(this.mAuto_suspension);
         this.addPreference(this.mPower_mode);
         if (RkSystemProp.get("ro.product.customer.sub", "HCT").startsWith("CHS")) {
            this.addPreference(this.mAux_switches);
         }
      } else if (this.getCarType() == 4) {
         this.getPreferenceScreen().removeAll();
         this.addPreference(this.mUnit_set);
         this.addPreference(this.mRange);
         this.addPreference(this.mTemperature);
         this.addPreference(this.mFulecons);
         this.addPreference(this.mDis_trip_b);
         this.addPreference(this.mRunning_lights);
         this.addPreference(this.mAutoclose);
         this.addPreference(this.mCar_type);
      } else if (this.getCarType() == 3) {
         this.getPreferenceScreen().removeAll();
         this.addPreference(this.mUnit_set);
         this.addPreference(this.mRange);
         this.addPreference(this.mTemperature);
         this.addPreference(this.mFulecons);
         this.addPreference(this.mTireunit);
         this.addPreference(this.mHeadlights_off);
         this.addPreference(this.mLights_flash);
         this.addPreference(this.mRunning_lights);
         this.addPreference(this.mTurn_lights_set);
         this.addPreference(this.mFront_light);
         this.addPreference(this.mUnlock_light);
         this.addPreference(this.mInterior_light);
         this.addPreference(this.mDriving_auto);
         this.addPreference(this.mUnlock_driving);
         this.addPreference(this.mDoor_lights_flash);
         this.addPreference(this.mKeyless_entry);
         this.addPreference(this.mBeep_lock);
         this.addPreference(this.mRemote_unlock);
         this.addPreference(this.mParksense);
         this.addPreference(this.mB_parksense);
         this.addPreference(this.mParkView);
         this.addPreference(this.mLane_warning);
         this.addPreference(this.mFor_warning);
         this.addPreference(this.mFor_outo_warning);
         this.addPreference(this.mBusy_warning);
         this.addPreference(this.mDeviation_correction);
         this.addPreference(this.mWipers_induction);
         this.addPreference(this.mBrake_service);
         this.addPreference(this.mAuto_parking);
         this.addPreference(this.mCar_type);
         this.addPreference(this.mOffset_alarm);
         this.addPreference(this.mOffset_alarm_vol);
         this.addPreference(this.mSafe_driving);
         this.addPreference(this.mLights);
         this.addPreference(this.mDoor_lock);
         this.addPreference(this.mOther);
      } else if (this.getCarType() == 2) {
         this.getPreferenceScreen().removeAll();
         this.addPreference(this.mUnit_set);
         this.addPreference(this.mRange);
         this.addPreference(this.mTemperature);
         this.addPreference(this.mFulecons);
         this.addPreference(this.mHeadlights_off);
         this.addPreference(this.mLights_flash);
         this.addPreference(this.mTurn_lights_set);
         this.addPreference(this.mFront_light);
         this.addPreference(this.mUnlock_light);
         this.addPreference(this.mDriving_auto);
         this.addPreference(this.mDoor_lights_flash);
         this.addPreference(this.mTail_headlights_off);
         this.addPreference(this.mParksense);
         this.addPreference(this.mB_parksense);
         this.addPreference(this.mParkView);
         this.addPreference(this.mFor_warning);
         this.addPreference(this.mFor_outo_warning);
         this.addPreference(this.mWipers_induction);
         this.addPreference(this.mCar_type);
         this.addPreference(this.mSafe_driving);
         this.addPreference(this.mLights);
         this.addPreference(this.mDoor_lock);
         this.addPreference(this.mTail_off);
         this.addPreference(this.mOther);
      } else if (this.getCarType() != 1) {
         this.getCarType();
      }

   }

   protected void ProcessData(byte[] var1) {
      // $FF: Couldn't be decompiled
   }

   boolean findAuxSwitches(Preference var1) {
      int var2 = 0;

      while(true) {
         boolean var10001;
         PreferenceScreen var4;
         try {
            if (var2 >= this.mAux_switches.getPreferenceCount()) {
               break;
            }

            var4 = (PreferenceScreen)this.mAux_switches.getPreference(var2);
         } catch (Exception var7) {
            var10001 = false;
            break;
         }

         int var3 = 0;

         while(true) {
            label31: {
               try {
                  if (var3 < var4.getPreferenceCount()) {
                     if (!var1.getKey().equals(var4.getPreference(var3).getKey())) {
                        break label31;
                     }

                     PrintStream var5 = System.out;
                     StringBuilder var8 = new StringBuilder();
                     var8.append("meng:");
                     var8.append(var4.getPreference(var3).getKey());
                     var5.println(var8.toString());
                     return true;
                  }
               } catch (Exception var6) {
                  var10001 = false;
                  return false;
               }

               ++var2;
               break;
            }

            ++var3;
         }
      }

      return false;
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492941);
      this.mBackview = (HCheckBoxPreference)this.findPreference("backview");
      this.mBackview.setOnPreferenceClickListener(this);
      this.mParkView = (HCheckBoxPreference)this.findPreference("parkView");
      this.mParkView.setOnPreferenceClickListener(this);
      this.mWipers_induction = (HCheckBoxPreference)this.findPreference("wipers_induction");
      this.mWipers_induction.setOnPreferenceClickListener(this);
      this.mRamp = (HCheckBoxPreference)this.findPreference("ramp");
      this.mRamp.setOnPreferenceClickListener(this);
      this.mWipers_start = (HCheckBoxPreference)this.findPreference("wipers_start");
      this.mWipers_start.setOnPreferenceClickListener(this);
      this.mRunning_lights = (HCheckBoxPreference)this.findPreference("running_lights");
      this.mRunning_lights.setOnPreferenceClickListener(this);
      this.mLights_flash = (HCheckBoxPreference)this.findPreference("lights_flash");
      this.mLights_flash.setOnPreferenceClickListener(this);
      this.mImage_parkView = (HCheckBoxPreference)this.findPreference("image_parkView");
      this.mImage_parkView.setOnPreferenceClickListener(this);
      this.mRadar_parking = (HCheckBoxPreference)this.findPreference("radar_parking");
      this.mRadar_parking.setOnPreferenceClickListener(this);
      this.mParking_brake = (HCheckBoxPreference)this.findPreference("parking_brake");
      this.mParking_brake.setOnPreferenceClickListener(this);
      this.mDriving_auto = (HCheckBoxPreference)this.findPreference("driving_auto");
      this.mDriving_auto.setOnPreferenceClickListener(this);
      this.mUnlock_driving = (HCheckBoxPreference)this.findPreference("unlock_driving");
      this.mUnlock_driving.setOnPreferenceClickListener(this);
      this.mBeep_lock = (HCheckBoxPreference)this.findPreference("beep_lock");
      this.mBeep_lock.setOnPreferenceClickListener(this);
      this.mKeyless_entry = (HCheckBoxPreference)this.findPreference("keyless_entry");
      this.mKeyless_entry.setOnPreferenceClickListener(this);
      this.mPersonalise = (HCheckBoxPreference)this.findPreference("personalise");
      this.mPersonalise.setOnPreferenceClickListener(this);
      this.mPower_alarm = (HCheckBoxPreference)this.findPreference("power_alarm");
      this.mPower_alarm.setOnPreferenceClickListener(this);
      this.mSeat = (HCheckBoxPreference)this.findPreference("seat");
      this.mSeat.setOnPreferenceClickListener(this);
      this.mAuto_adjustment = (HCheckBoxPreference)this.findPreference("auto_adjustment");
      this.mAuto_adjustment.setOnPreferenceClickListener(this);
      this.mTire_mode = (HCheckBoxPreference)this.findPreference("tire_mode");
      this.mTire_mode.setOnPreferenceClickListener(this);
      this.mTransport_mode = (HCheckBoxPreference)this.findPreference("transport_mode");
      this.mTransport_mode.setOnPreferenceClickListener(this);
      this.mWheel_mode = (HCheckBoxPreference)this.findPreference("wheel_mode");
      this.mWheel_mode.setOnPreferenceClickListener(this);
      this.mRearview_dimming = (HCheckBoxPreference)this.findPreference("rearview_dimming");
      this.mRearview_dimming.setOnPreferenceClickListener(this);
      this.mOuthigh_beam = (HCheckBoxPreference)this.findPreference("outhigh_beam");
      this.mOuthigh_beam.setOnPreferenceClickListener(this);
      this.mDoor_alarm = (HCheckBoxPreference)this.findPreference("door_alarm");
      this.mDoor_alarm.setOnPreferenceClickListener(this);
      this.mBuzzer = (HCheckBoxPreference)this.findPreference("buzzer");
      this.mBuzzer.setOnPreferenceClickListener(this);
      this.mElectronic_park = (HCheckBoxPreference)this.findPreference("electronic_park");
      this.mElectronic_park.setOnPreferenceClickListener(this);
      this.mWelcome_light = (HCheckBoxPreference)this.findPreference("welcome_light");
      this.mWelcome_light.setOnPreferenceClickListener(this);
      this.mTurn_lights_set = (HCheckBoxPreference)this.findPreference("turn_lights_set");
      this.mTurn_lights_set.setOnPreferenceClickListener(this);
      this.mUnlock_light = (HCheckBoxPreference)this.findPreference("unlock_light");
      this.mUnlock_light.setOnPreferenceClickListener(this);
      this.mDoor_lights_flash = (HCheckBoxPreference)this.findPreference("door_lights_flash");
      this.mDoor_lights_flash.setOnPreferenceClickListener(this);
      this.mAuto_parking = (HCheckBoxPreference)this.findPreference("auto_parking");
      this.mAuto_parking.setOnPreferenceClickListener(this);
      this.mRemote_lock = (HCheckBoxPreference)this.findPreference("remote_lock");
      this.mRemote_lock.setOnPreferenceClickListener(this);
      this.mRemote_unlock = (HCheckBoxPreference)this.findPreference("remote_unlock");
      this.mRemote_unlock.setOnPreferenceClickListener(this);
      this.mRear_parkSense = (HCheckBoxPreference)this.findPreference("rear_parkSense");
      this.mRear_parkSense.setOnPreferenceClickListener(this);
      this.mDis_trip_b = (HCheckBoxPreference)this.findPreference("dis_trip_b");
      this.mDis_trip_b.setOnPreferenceClickListener(this);
      this.mAutoclose = (HCheckBoxPreference)this.findPreference("autoclose");
      this.mAutoclose.setOnPreferenceClickListener(this);
      this.mPaddle_shifting = this.findHCheckBoxPreference("paddle_shifting");
      this.mAutomatic_lamp = this.findHCheckBoxPreference("automatic_lamp");
      this.mRear_view = this.findHCheckBoxPreference("rear_view");
      this.mAuto_suspension = this.findHCheckBoxPreference("auto_suspension");
      this.mParksense = (SwitchboxPreference)this.findPreference("parksense");
      this.mParksense.setOnPreferenceChangeListener(this);
      this.mB_parksense = (SwitchboxPreference)this.findPreference("b_parksense");
      this.mB_parksense.setOnPreferenceChangeListener(this);
      this.mF_parksense = (SwitchboxPreference)this.findPreference("f_parksense");
      this.mF_parksense.setOnPreferenceChangeListener(this);
      this.mHeadlights_off = (SwitchboxPreference)this.findPreference("headlights_off");
      this.mHeadlights_off.setOnPreferenceChangeListener(this);
      this.mBright_headlights = (SwitchboxPreference)this.findPreference("bright_headlights");
      this.mBright_headlights.setOnPreferenceChangeListener(this);
      this.mKey_unlock = (SwitchboxPreference)this.findPreference("key_unlock");
      this.mKey_unlock.setOnPreferenceChangeListener(this);
      this.mPower_off = (SwitchboxPreference)this.findPreference("power_off");
      this.mPower_off.setOnPreferenceChangeListener(this);
      this.mUnit_set = (SwitchboxPreference)this.findPreference("unit_set");
      this.mUnit_set.setOnPreferenceChangeListener(this);
      this.mLane_warning = (SwitchboxPreference)this.findPreference("lane_warning");
      this.mLane_warning.setOnPreferenceChangeListener(this);
      this.mDeviation_correction = (SwitchboxPreference)this.findPreference("deviation_correction");
      this.mDeviation_correction.setOnPreferenceChangeListener(this);
      this.mDis_suspension = (SwitchboxPreference)this.findPreference("dis_suspension");
      this.mDis_suspension.setOnPreferenceChangeListener(this);
      this.mBusy_warning = (SwitchboxPreference)this.findPreference("busy_warning");
      this.mBusy_warning.setOnPreferenceChangeListener(this);
      this.mFor_warning = (SwitchboxPreference)this.findPreference("for_warning");
      this.mFor_warning.setOnPreferenceChangeListener(this);
      this.mOutseat_heating = (SwitchboxPreference)this.findPreference("outseat_heating");
      this.mOutseat_heating.setOnPreferenceChangeListener(this);
      this.mFulecons = (SwitchboxPreference)this.findPreference("fulecons");
      this.mFulecons.setOnPreferenceChangeListener(this);
      this.mTireunit = (SwitchboxPreference)this.findPreference("tireunit");
      this.mTireunit.setOnPreferenceChangeListener(this);
      this.mTemperature = (SwitchboxPreference)this.findPreference("temperature");
      this.mTemperature.setOnPreferenceChangeListener(this);
      this.mLock_beep = (SwitchboxPreference)this.findPreference("lock_beep");
      this.mLock_beep.setOnPreferenceChangeListener(this);
      this.mTail_headlights_off = (SwitchboxPreference)this.findPreference("tail_headlights_off");
      this.mTail_headlights_off.setOnPreferenceChangeListener(this);
      this.mRange = (SwitchboxPreference)this.findPreference("range");
      this.mRange.setOnPreferenceChangeListener(this);
      this.mCar_type = (SwitchboxPreference)this.findPreference("car_type");
      this.mCar_type.setOnPreferenceChangeListener(this);
      this.mFor_outo_warning = (SwitchboxPreference)this.findPreference("for_outo_warning");
      this.mFor_outo_warning.setOnPreferenceChangeListener(this);
      this.mOffset_alarm = this.findSwitchboxPreference("offset_alarm");
      this.mOffset_alarm_vol = this.findSwitchboxPreference("offset_alarm_vol");
      this.mPower_steering = this.findSwitchboxPreference("power_steering");
      this.mSpeedunit = this.findSwitchboxPreference("speedunit");
      this.mPower_mode = this.findSwitchboxPreference("power_mode");
      this.mBrake_service = (HCheckBoxPreference)this.findPreference("brake_service");
      this.mBrake_service.setOnPreferenceClickListener(this);
      this.mCar_in_light = this.findOnSwitchPreference("car_in_light");
      this.mFront_light = this.findOnSwitchPreference("front_light");
      this.mInterior_light = this.findOnSwitchPreference("interior_light");
      this.mSafe_driving = (PreferenceCategory)this.findPreference("safe_driving");
      this.mLights = (PreferenceCategory)this.findPreference("lights");
      this.mDoor_lock = (PreferenceCategory)this.findPreference("door_lock");
      this.mTail_off = (PreferenceCategory)this.findPreference("tail_off");
      this.mOther = (PreferenceCategory)this.findPreference("other");
      this.mAux_switches = this.findPreferenceScreen("aux_switches");

      int var3;
      StringBuilder var4;
      for(int var2 = 0; var2 < 4; var2 = var3) {
         PreferenceScreen var5 = new PreferenceScreen(this, (AttributeSet)null);
         var4 = new StringBuilder();
         var4.append("aux");
         var3 = var2 + 1;
         var4.append(var3);
         var5.setKey(var4.toString());
         var4 = new StringBuilder();
         var4.append("AUX ");
         var4.append(var3);
         var5.setTitle(var4.toString());
         this.mAux_switches.addPreference(var5);
         SwitchboxPreference var7 = new SwitchboxPreference(this);
         var7.setOnPreferenceChangeListener(this);
         var2 *= 3;
         var7.setKey(Integer.toString(var2 + 0 + 96));
         var7.setTitle(this.getString(2131297317));
         var7.setEntries(2130837622);
         var7.setEntryValues(2130837623);
         var5.addPreference(var7);
         var7 = new SwitchboxPreference(this);
         var7.setOnPreferenceChangeListener(this);
         var2 += 96;
         var7.setKey(Integer.toString(var2 + 1));
         var7.setTitle(this.getString(2131296383));
         var7.setEntries(2130837620);
         var7.setEntryValues(2130837621);
         var5.addPreference(var7);
         HCheckBoxPreference var8 = new HCheckBoxPreference(this);
         var8.setOnPreferenceClickListener(this);
         var8.setKey(Integer.toString(var2 + 2));
         var8.setTitle(this.getString(2131296384));
         var5.addPreference(var8);
      }

      this.removePreference((Preference)this.mAux_switches);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{64, -1, 0, 0}, 4);
      this.mUnit_set.setValue("1");
      this.mTireunit.setValue("3");
      SwitchboxPreference var6 = this.mCar_type;
      var4 = new StringBuilder();
      var4.append("");
      var4.append(this.getInt("mCar_type", 0));
      var6.setValue(var4.toString());
      this.getPreferenceScreen().removePreference(this.mLock_beep);
      this.getPreferenceScreen().removePreference(this.mBuzzer);
      this.getPreferenceScreen().removePreference(this.mElectronic_park);
      this.getPreferenceScreen().removePreference(this.mCar_in_light);
      this.getPreferenceScreen().removePreference(this.mWelcome_light);
      this.showListItem();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var6 = this.mCar_in_light;
      int var3 = 1;
      int var4;
      int var5;
      if (var1 == var6) {
         var5 = Integer.parseInt((String)var6.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this._Car_in_light;
         if (var4 + var5 < 0 || var4 + var5 > 6 || !this.mCar_in_light.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)-110, (byte)(this._Car_in_light + var5 & 255));
      } else {
         var6 = this.mFront_light;
         if (var1 == var6) {
            var5 = Integer.parseInt((String)var6.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var4 = this._Front_light;
            if (var4 + var5 < 0 || var4 + var5 > 2 || !this.mFront_light.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)41, (byte)(this._Front_light + 1 + var5 & 255));
         } else {
            var6 = this.mInterior_light;
            if (var1 == var6) {
               var5 = Integer.parseInt((String)var6.getEntries()[Integer.parseInt((String)var2)]) + 0;
               var4 = this._Interior_light;
               if (var4 + var5 < 0 || var4 + var5 > 6 || !this.mInterior_light.getIsDow()) {
                  return true;
               }

               this.SetCmdkey((byte)43, (byte)(this._Interior_light + var5 & 255));
            }
         }
      }

      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var9) {
         return false;
      }

      try {
         var5 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var8) {
         return true;
      }

      if (var4 == var5) {
         return true;
      } else {
         if (var1 == this.mParksense) {
            this.SetCmdkey((byte)-96, (byte)(var4 + 0));
         } else if (var1 == this.mB_parksense) {
            this.SetCmdkey((byte)-94, (byte)var4);
         } else if (var1 == this.mF_parksense) {
            this.SetCmdkey((byte)-95, (byte)var4);
         } else if (var1 == this.mHeadlights_off) {
            this.SetCmdkey((byte)32, (byte)(var4 * 30));
         } else if (var1 == this.mKey_unlock) {
            this.SetCmdkey((byte)52, (byte)(var4 + 0));
         } else if (var1 == this.mPower_off) {
            this.SetCmdkey((byte)65, (byte)this.getData(var4));
         } else if (var1 == this.mUnit_set) {
            byte var14;
            if (var4 == 1) {
               var14 = 0;
            } else {
               var14 = 1;
            }

            this.SetCmdkey((byte)1, (byte)var14);
         } else if (var1 == this.mLane_warning) {
            this.SetCmdkey((byte)-87, (byte)var4);
         } else if (var1 == this.mDeviation_correction) {
            this.SetCmdkey((byte)-82, (byte)var4);
         } else if (var1 == this.mBright_headlights) {
            this.SetCmdkey((byte)33, (byte)(var4 * 30));
         } else if (var1 == this.mDis_suspension) {
            this.SetCmdkey((byte)-44, (byte)(var4 + 0));
         } else if (var1 == this.mBusy_warning) {
            this.SetCmdkey((byte)-84, (byte)(var4 + 0));
         } else if (var1 == this.mFor_warning) {
            this.SetCmdkey((byte)-86, (byte)(var4 + 0));
         } else if (var1 == this.mOutseat_heating) {
            this.SetCmdkey((byte)-112, (byte)(var4 + 0));
         } else if (var1 == this.mFulecons) {
            this.SetCmdkey((byte)5, (byte)(var4 + 0));
         } else if (var1 == this.mTireunit) {
            this.SetCmdkey((byte)7, (byte)(0 - (var4 - 2)));
         } else if (var1 == this.mTemperature) {
            this.SetCmdkey((byte)4, (byte)(var4 + 0));
         } else if (var1 == this.mLock_beep) {
            this.SetCmdkey((byte)-96, (byte)(var4 + 0));
         } else if (var1 == this.mTail_headlights_off) {
            this.SetCmdkey((byte)64, (byte)(var4 * 30));
         } else if (var1 == this.mRange) {
            this.SetCmdkey((byte)3, (byte)(var4 + 0));
            SwitchboxPreference var10 = this.mRange;
            StringBuilder var12 = new StringBuilder();
            var12.append("");
            var12.append(var4);
            var10.setValue(var12.toString());
         } else {
            SwitchboxPreference var13 = this.mCar_type;
            if (var1 == var13) {
               StringBuilder var11 = new StringBuilder();
               var11.append("");
               var11.append(var4);
               var13.setValue(var11.toString());
               this.putInt("mCar_type", var4);
               this.SendCanBusCmdData2E((byte)-54, new byte[]{(byte)var4}, 1);
            } else if (var1 == this.mFor_outo_warning) {
               label152: {
                  if (this.getCarType() == 3) {
                     if (var4 == 1 && this._For_outo_warning < var4) {
                        var3 = 2;
                        break label152;
                     }

                     if (var4 == 1 && this._For_outo_warning > var4) {
                        var3 = 0;
                        break label152;
                     }
                  } else if (this.getCarType() == 2 && var4 == 2) {
                     if (var4 == 2 && this._For_outo_warning < var4) {
                        var3 = 3;
                        break label152;
                     }

                     if (var4 == 2 && this._For_outo_warning > var4) {
                        break label152;
                     }
                  } else if (this.getCarType() == 0 && var4 >= 1) {
                     break label152;
                  }

                  var3 = var4;
               }

               this.SetCmdkey((byte)-85, (byte)var3);
            } else if (var1 == this.mOffset_alarm) {
               this.SetCmdkey((byte)-98, (byte)var4);
            } else if (var1 == this.mOffset_alarm_vol) {
               this.SetCmdkey((byte)-97, (byte)var4);
            } else if (var1 == this.mPower_steering) {
               this.SetCmdkey((byte)-88, (byte)var4);
            } else if (var1 == this.mSpeedunit) {
               this.SetCmdkey((byte)8, (byte)var4);
            } else if (var1 == this.mPower_mode) {
               this.SetCmdkey((byte)67, (byte)var4);
            } else if (this.findAuxSwitches(var1) && var1 instanceof SwitchboxPreference) {
               try {
                  var3 = Integer.parseInt(var1.getKey());
               } catch (Exception var7) {
                  var3 = 0;
               }

               this.SetCmdkey((byte)var3, (byte)var4);
            }
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var3 = this.mBackview;
      if (var1 == var3) {
         if (var3.mChecked) {
            this.SetCmdkey((byte)-83, (byte)0);
         } else {
            this.SetCmdkey((byte)-83, (byte)1);
         }
      } else {
         var3 = this.mParkView;
         if (var1 == var3) {
            if (var3.mChecked) {
               this.SetCmdkey((byte)-92, (byte)0);
            } else {
               this.SetCmdkey((byte)-92, (byte)1);
            }
         } else {
            var3 = this.mWipers_induction;
            if (var1 == var3) {
               if (var3.mChecked) {
                  this.SetCmdkey((byte)-81, (byte)0);
               } else {
                  this.SetCmdkey((byte)-81, (byte)1);
               }
            } else {
               var3 = this.mRamp;
               if (var1 == var3) {
                  if (var3.mChecked) {
                     this.SetCmdkey((byte)-90, (byte)0);
                  } else {
                     this.SetCmdkey((byte)-90, (byte)1);
                  }
               } else {
                  var3 = this.mWipers_start;
                  if (var1 == var3) {
                     if (var3.mChecked) {
                        this.SetCmdkey((byte)38, (byte)0);
                     } else {
                        this.SetCmdkey((byte)38, (byte)1);
                     }
                  } else {
                     var3 = this.mRunning_lights;
                     if (var1 == var3) {
                        if (var3.mChecked) {
                           this.SetCmdkey((byte)36, (byte)0);
                        } else {
                           this.SetCmdkey((byte)36, (byte)1);
                        }
                     } else {
                        var3 = this.mLights_flash;
                        if (var1 == var3) {
                           if (var3.mChecked) {
                              this.SetCmdkey((byte)35, (byte)0);
                           } else {
                              this.SetCmdkey((byte)35, (byte)1);
                           }
                        } else {
                           var3 = this.mDriving_auto;
                           if (var1 == var3) {
                              if (var3.mChecked) {
                                 this.SetCmdkey((byte)48, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)48, (byte)1);
                              }
                           } else {
                              var3 = this.mUnlock_driving;
                              if (var1 == var3) {
                                 if (var3.mChecked) {
                                    this.SetCmdkey((byte)49, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)49, (byte)1);
                                 }
                              } else {
                                 var3 = this.mBeep_lock;
                                 if (var1 == var3) {
                                    if (var3.mChecked) {
                                       this.SetCmdkey((byte)55, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)55, (byte)1);
                                    }
                                 } else {
                                    var3 = this.mKeyless_entry;
                                    if (var1 == var3) {
                                       if (var3.mChecked) {
                                          this.SetCmdkey((byte)54, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)54, (byte)1);
                                       }
                                    } else {
                                       var3 = this.mPersonalise;
                                       if (var1 == var3) {
                                          if (var3.mChecked) {
                                             this.SetCmdkey((byte)56, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)56, (byte)1);
                                          }
                                       } else {
                                          var3 = this.mPower_alarm;
                                          if (var1 == var3) {
                                             if (var3.mChecked) {
                                                this.SetCmdkey((byte)57, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)57, (byte)1);
                                             }
                                          } else {
                                             var3 = this.mSeat;
                                             if (var1 == var3) {
                                                if (var3.mChecked) {
                                                   this.SetCmdkey((byte)66, (byte)0);
                                                } else {
                                                   this.SetCmdkey((byte)66, (byte)1);
                                                }
                                             } else {
                                                var3 = this.mAuto_adjustment;
                                                if (var1 == var3) {
                                                   if (var3.mChecked) {
                                                      this.SetCmdkey((byte)-48, (byte)0);
                                                   } else {
                                                      this.SetCmdkey((byte)-48, (byte)1);
                                                   }
                                                } else {
                                                   var3 = this.mTire_mode;
                                                   if (var1 == var3) {
                                                      if (var3.mChecked) {
                                                         this.SetCmdkey((byte)-47, (byte)0);
                                                      } else {
                                                         this.SetCmdkey((byte)-47, (byte)1);
                                                      }
                                                   } else {
                                                      var3 = this.mTransport_mode;
                                                      if (var1 == var3) {
                                                         if (var3.mChecked) {
                                                            this.SetCmdkey((byte)-46, (byte)0);
                                                         } else {
                                                            this.SetCmdkey((byte)-46, (byte)1);
                                                         }
                                                      } else {
                                                         var3 = this.mWheel_mode;
                                                         if (var1 == var3) {
                                                            if (var3.mChecked) {
                                                               this.SetCmdkey((byte)-45, (byte)0);
                                                            } else {
                                                               this.SetCmdkey((byte)-45, (byte)1);
                                                            }
                                                         } else {
                                                            var3 = this.mRearview_dimming;
                                                            if (var1 == var3) {
                                                               if (var3.mChecked) {
                                                                  this.SetCmdkey((byte)39, (byte)0);
                                                               } else {
                                                                  this.SetCmdkey((byte)39, (byte)1);
                                                               }
                                                            } else {
                                                               var3 = this.mImage_parkView;
                                                               if (var1 == var3) {
                                                                  if (var3.mChecked) {
                                                                     this.SetCmdkey((byte)-91, (byte)0);
                                                                  } else {
                                                                     this.SetCmdkey((byte)-91, (byte)1);
                                                                  }
                                                               } else {
                                                                  HCheckBoxPreference var4 = this.mRadar_parking;
                                                                  if (var1 == var4) {
                                                                     if (var4.mChecked) {
                                                                        this.SetCmdkey((byte)-16, (byte)0);
                                                                     } else {
                                                                        this.SetCmdkey((byte)-16, (byte)1);
                                                                     }
                                                                  } else {
                                                                     var3 = this.mBrake_service;
                                                                     if (var1 == var3) {
                                                                        if (var3.mChecked) {
                                                                           var4.setChecked(false);
                                                                           this.SetCmdkey((byte)-64, (byte)0);
                                                                        } else {
                                                                           this.SetCmdkey((byte)-64, (byte)1);
                                                                           this.mRadar_parking.setChecked(true);
                                                                        }
                                                                     } else {
                                                                        var3 = this.mParking_brake;
                                                                        if (var1 == var3) {
                                                                           if (var3.mChecked) {
                                                                              this.SetCmdkey((byte)-15, (byte)0);
                                                                           } else {
                                                                              this.SetCmdkey((byte)-15, (byte)1);
                                                                           }
                                                                        } else {
                                                                           var3 = this.mOuthigh_beam;
                                                                           if (var1 == var3) {
                                                                              if (var3.mChecked) {
                                                                                 this.SetCmdkey((byte)40, (byte)0);
                                                                              } else {
                                                                                 this.SetCmdkey((byte)40, (byte)1);
                                                                              }
                                                                           } else {
                                                                              var3 = this.mDoor_alarm;
                                                                              if (var1 == var3) {
                                                                                 if (var3.mChecked) {
                                                                                    this.SetCmdkey((byte)58, (byte)0);
                                                                                 } else {
                                                                                    this.SetCmdkey((byte)58, (byte)1);
                                                                                 }
                                                                              } else {
                                                                                 var3 = this.mBuzzer;
                                                                                 if (var1 == var3) {
                                                                                    if (var3.mChecked) {
                                                                                       this.SetCmdkey((byte)96, (byte)0);
                                                                                    } else {
                                                                                       this.SetCmdkey((byte)96, (byte)1);
                                                                                    }
                                                                                 } else {
                                                                                    var3 = this.mElectronic_park;
                                                                                    if (var1 == var3) {
                                                                                       if (var3.mChecked) {
                                                                                          this.SetCmdkey((byte)-128, (byte)1);
                                                                                       } else {
                                                                                          this.SetCmdkey((byte)-128, (byte)2);
                                                                                       }
                                                                                    } else {
                                                                                       var3 = this.mTurn_lights_set;
                                                                                       if (var1 == var3) {
                                                                                          if (var3.mChecked) {
                                                                                             this.SetCmdkey((byte)37, (byte)0);
                                                                                          } else {
                                                                                             this.SetCmdkey((byte)37, (byte)1);
                                                                                          }
                                                                                       } else {
                                                                                          var3 = this.mUnlock_light;
                                                                                          if (var1 == var3) {
                                                                                             if (var3.mChecked) {
                                                                                                this.SetCmdkey((byte)42, (byte)0);
                                                                                             } else {
                                                                                                this.SetCmdkey((byte)42, (byte)1);
                                                                                             }
                                                                                          } else {
                                                                                             var3 = this.mDoor_lights_flash;
                                                                                             if (var1 == var3) {
                                                                                                if (var3.mChecked) {
                                                                                                   this.SetCmdkey((byte)50, (byte)0);
                                                                                                } else {
                                                                                                   this.SetCmdkey((byte)50, (byte)1);
                                                                                                }
                                                                                             } else {
                                                                                                var3 = this.mAuto_parking;
                                                                                                if (var1 == var3) {
                                                                                                   if (var3.mChecked) {
                                                                                                      this.SetCmdkey((byte)-63, (byte)0);
                                                                                                   } else {
                                                                                                      this.SetCmdkey((byte)-63, (byte)1);
                                                                                                   }
                                                                                                } else {
                                                                                                   var3 = this.mRear_parkSense;
                                                                                                   if (var1 == var3) {
                                                                                                      if (var3.mChecked) {
                                                                                                         this.SetCmdkey((byte)-93, (byte)0);
                                                                                                      } else {
                                                                                                         this.SetCmdkey((byte)-93, (byte)1);
                                                                                                      }
                                                                                                   } else {
                                                                                                      var3 = this.mRemote_lock;
                                                                                                      if (var1 == var3) {
                                                                                                         if (var3.mChecked) {
                                                                                                            this.SetCmdkey((byte)59, (byte)0);
                                                                                                         } else {
                                                                                                            this.SetCmdkey((byte)59, (byte)1);
                                                                                                         }
                                                                                                      } else {
                                                                                                         var3 = this.mRemote_unlock;
                                                                                                         if (var1 == var3) {
                                                                                                            if (var3.mChecked) {
                                                                                                               this.SetCmdkey((byte)60, (byte)0);
                                                                                                            } else {
                                                                                                               this.SetCmdkey((byte)60, (byte)1);
                                                                                                            }
                                                                                                         } else {
                                                                                                            var3 = this.mDis_trip_b;
                                                                                                            if (var1 == var3) {
                                                                                                               if (var3.mChecked) {
                                                                                                                  this.SetCmdkey((byte)6, (byte)0);
                                                                                                               } else {
                                                                                                                  this.SetCmdkey((byte)6, (byte)1);
                                                                                                               }
                                                                                                            } else {
                                                                                                               var3 = this.mAutoclose;
                                                                                                               if (var1 == var3) {
                                                                                                                  if (var3.mChecked) {
                                                                                                                     this.SetCmdkey((byte)53, (byte)0);
                                                                                                                  } else {
                                                                                                                     this.SetCmdkey((byte)53, (byte)1);
                                                                                                                  }
                                                                                                               } else {
                                                                                                                  var3 = this.mPaddle_shifting;
                                                                                                                  if (var1 == var3) {
                                                                                                                     if (var3.mChecked) {
                                                                                                                        this.SetCmdkey((byte)-89, (byte)0);
                                                                                                                     } else {
                                                                                                                        this.SetCmdkey((byte)-89, (byte)1);
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     var3 = this.mAutomatic_lamp;
                                                                                                                     if (var1 == var3) {
                                                                                                                        if (var3.mChecked) {
                                                                                                                           this.SetCmdkey((byte)44, (byte)0);
                                                                                                                        } else {
                                                                                                                           this.SetCmdkey((byte)44, (byte)1);
                                                                                                                        }
                                                                                                                     } else {
                                                                                                                        var3 = this.mRear_view;
                                                                                                                        if (var1 == var3) {
                                                                                                                           if (var3.mChecked) {
                                                                                                                              this.SetCmdkey((byte)-99, (byte)0);
                                                                                                                           } else {
                                                                                                                              this.SetCmdkey((byte)-99, (byte)1);
                                                                                                                           }
                                                                                                                        } else {
                                                                                                                           var3 = this.mAuto_suspension;
                                                                                                                           if (var1 == var3) {
                                                                                                                              if (var3.mChecked) {
                                                                                                                                 this.SetCmdkey((byte)-43, (byte)0);
                                                                                                                              } else {
                                                                                                                                 this.SetCmdkey((byte)-43, (byte)1);
                                                                                                                              }
                                                                                                                           } else if (this.findAuxSwitches(var1) && var1 instanceof HCheckBoxPreference) {
                                                                                                                              int var2;
                                                                                                                              try {
                                                                                                                                 var2 = Integer.parseInt(var1.getKey());
                                                                                                                              } catch (Exception var5) {
                                                                                                                                 var2 = 0;
                                                                                                                              }

                                                                                                                              if (((HCheckBoxPreference)var1).mChecked) {
                                                                                                                                 this.SetCmdkey((byte)var2, (byte)0);
                                                                                                                              } else {
                                                                                                                                 this.SetCmdkey((byte)var2, (byte)1);
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
}
