package com.microntek.controlsettings.canbus26;

import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.SwitchPreference;

public class canbus26info3 extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchPreference mBrake_lamp;
   private SwitchPreference mDaytime_lights;
   private SwitchPreference mHigh_beam;
   private SwitchPreference mLeft_lights;
   private SwitchPreference mLeft_steering_light;
   private SwitchPreference mLight_turn_right;
   private SwitchPreference mLights_right;
   private SwitchPreference mLow_light;
   private SwitchPreference mQielamp;
   private SwitchPreference mRear_fog_lamp;
   private SwitchPreference mReversing_lamp;
   private SwitchPreference mWidth_lamp;
   private byte[] setData = new byte[20];

   private void updasettings18() {
      if ((byte)(this.setData[0] & 128) == -128) {
         this.mLow_light.setValue("1");
      } else {
         this.mLow_light.setValue("0");
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.mHigh_beam.setValue("1");
      } else {
         this.mHigh_beam.setValue("0");
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.mWidth_lamp.setValue("1");
      } else {
         this.mWidth_lamp.setValue("0");
      }

      if ((byte)(this.setData[0] & 16) == 16) {
         this.mQielamp.setValue("1");
      } else {
         this.mQielamp.setValue("0");
      }

      if ((byte)(this.setData[0] & 8) == 8) {
         this.mRear_fog_lamp.setValue("1");
      } else {
         this.mRear_fog_lamp.setValue("0");
      }

      if ((byte)(this.setData[0] & 4) == 4) {
         this.mBrake_lamp.setValue("1");
      } else {
         this.mBrake_lamp.setValue("0");
      }

      if ((byte)(this.setData[0] & 2) == 2) {
         this.mReversing_lamp.setValue("1");
      } else {
         this.mReversing_lamp.setValue("0");
      }

      if ((byte)(this.setData[0] & 1) == 1) {
         this.mDaytime_lights.setValue("1");
      } else {
         this.mDaytime_lights.setValue("0");
      }

      if ((byte)(this.setData[1] & 64) == 64) {
         this.mLeft_lights.setValue("1");
      } else {
         this.mLeft_lights.setValue("0");
      }

      if ((byte)(this.setData[1] & 128) == -128) {
         this.mLights_right.setValue("1");
      } else {
         this.mLights_right.setValue("0");
      }

      if ((byte)(this.setData[1] & 16) == 16) {
         this.mLeft_steering_light.setValue("1");
      } else {
         this.mLeft_steering_light.setValue("0");
      }

      if ((byte)(this.setData[1] & 32) == 32) {
         this.mLight_turn_right.setValue("1");
      } else {
         this.mLight_turn_right.setValue("0");
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      if (var1.length > 1) {
         if (var3 == 24) {
            while(var2 < 10) {
               byte[] var4 = this.setData;
               var3 = var2 + 1;
               var4[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings18();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492901);
      this.mLow_light = (SwitchPreference)this.findPreference("low_light");
      SwitchPreference var2 = this.mLow_light;
      String var3;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mLow_light.getSharedPreferences().getString("timeswitchlight", "0");
         this.mLow_light.setValue(var3);
      }

      this.mHigh_beam = (SwitchPreference)this.findPreference("high_beam");
      var2 = this.mHigh_beam;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mHigh_beam.getSharedPreferences().getString("timeswitchlight", "0");
         this.mHigh_beam.setValue(var3);
      }

      this.mWidth_lamp = (SwitchPreference)this.findPreference("width_lamp");
      var2 = this.mWidth_lamp;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mWidth_lamp.getSharedPreferences().getString("timeswitchlight", "0");
         this.mWidth_lamp.setValue(var3);
      }

      this.mQielamp = (SwitchPreference)this.findPreference("qielamp");
      var2 = this.mQielamp;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mQielamp.getSharedPreferences().getString("timeswitchlight", "0");
         this.mQielamp.setValue(var3);
      }

      this.mRear_fog_lamp = (SwitchPreference)this.findPreference("rear_fog_lamp");
      var2 = this.mRear_fog_lamp;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mRear_fog_lamp.getSharedPreferences().getString("timeswitchlight", "0");
         this.mRear_fog_lamp.setValue(var3);
      }

      this.mBrake_lamp = (SwitchPreference)this.findPreference("brake_lamp");
      var2 = this.mBrake_lamp;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mBrake_lamp.getSharedPreferences().getString("timeswitchlight", "0");
         this.mBrake_lamp.setValue(var3);
      }

      this.mReversing_lamp = (SwitchPreference)this.findPreference("reversing_lamp");
      var2 = this.mReversing_lamp;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mReversing_lamp.getSharedPreferences().getString("timeswitchlight", "0");
         this.mReversing_lamp.setValue(var3);
      }

      this.mDaytime_lights = (SwitchPreference)this.findPreference("daytime_lights");
      var2 = this.mDaytime_lights;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mDaytime_lights.getSharedPreferences().getString("timeswitchlight", "0");
         this.mDaytime_lights.setValue(var3);
      }

      this.mLeft_lights = (SwitchPreference)this.findPreference("left_lights");
      var2 = this.mLeft_lights;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mLeft_lights.getSharedPreferences().getString("timeswitchlight", "0");
         this.mLeft_lights.setValue(var3);
      }

      this.mLights_right = (SwitchPreference)this.findPreference("lights_right");
      var2 = this.mLights_right;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mLights_right.getSharedPreferences().getString("timeswitchlight", "0");
         this.mLights_right.setValue(var3);
      }

      this.mLeft_steering_light = (SwitchPreference)this.findPreference("left_steering_light");
      var2 = this.mLeft_steering_light;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mLeft_steering_light.getSharedPreferences().getString("timeswitchlight", "0");
         this.mLeft_steering_light.setValue(var3);
      }

      this.mLight_turn_right = (SwitchPreference)this.findPreference("light_turn_right");
      var2 = this.mLight_turn_right;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mLight_turn_right.getSharedPreferences().getString("timeswitchlight", "0");
         this.mLight_turn_right.setValue(var3);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return true;
   }
}
