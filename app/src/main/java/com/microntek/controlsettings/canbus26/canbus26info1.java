package com.microntek.controlsettings.canbus26;

import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchPreference;

public class canbus26info1 extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference cleaning_liquid_alarm;
   private HCheckBoxPreference engine_oil_alarm;
   private HCheckBoxPreference fuel_low;
   private SwitchPreference mConsumption;
   private SwitchPreference mGear_remind;
   private SwitchPreference mInnage;
   private SwitchPreference mMileage;
   private SwitchPreference mTemperature_value;
   private SwitchPreference mVbatt;
   private HCheckBoxPreference safety_belt_warning;
   private byte[] setData = new byte[20];
   private HCheckBoxPreference voltage_low;

   private void updasettings12() {
      int var1 = this.setData[2] & 255;
      StringBuilder var3;
      SwitchPreference var4;
      if (var1 < 255) {
         var4 = this.mTemperature_value;
         var3 = new StringBuilder();
         var3.append(var1);
         var3.append(this.getString(2131296624));
         var4.setValue("0", var3.toString());
      } else {
         this.mTemperature_value.setValue("0", this.getString(2131296800));
      }

      byte[] var6 = this.setData;
      byte var5 = var6[3];
      byte var2 = var6[4];
      SwitchPreference var7 = this.mConsumption;
      StringBuilder var8 = new StringBuilder();
      var8.append(var5 & 255);
      var8.append(".");
      var8.append(var2 & 255);
      var8.append("L/100KM");
      var7.setValue("0", var8.toString());
      var6 = this.setData;
      if (var6[5] == 1) {
         this.mGear_remind.setValue("0", this.getString(2131296980));
      } else if (var6[5] == 2) {
         this.mGear_remind.setValue("0", this.getString(2131296926));
      } else if (var6[5] == 3) {
         this.mGear_remind.setValue("0", this.getString(2131297030));
      } else if (var6[5] == 4) {
         this.mGear_remind.setValue("0", this.getString(2131296552));
      } else {
         this.mGear_remind.setValue("0", this.getString(2131296800));
      }

      var5 = this.setData[7];
      var4 = this.mInnage;
      var3 = new StringBuilder();
      var3.append(var5 & 255);
      var3.append(" L");
      var4.setValue("0", var3.toString());
      var6 = this.setData;
      var5 = var6[8];
      var2 = var6[9];
      var4 = this.mVbatt;
      var3 = new StringBuilder();
      var3.append(var5 & 255);
      var3.append(".");
      var3.append(var2 & 255);
      var3.append("V");
      var4.setValue("0", var3.toString());
      if ((byte)(this.setData[6] & 128) == -128) {
         this.fuel_low.setChecked(true);
      } else {
         this.fuel_low.setChecked(false);
      }

      if ((byte)(this.setData[6] & 64) == 64) {
         this.voltage_low.setChecked(true);
      } else {
         this.voltage_low.setChecked(false);
      }

      if ((byte)(this.setData[6] & 32) == 32) {
         this.safety_belt_warning.setChecked(true);
      } else {
         this.safety_belt_warning.setChecked(false);
      }

      if ((byte)(this.setData[6] & 16) == 16) {
         this.cleaning_liquid_alarm.setChecked(true);
      } else {
         this.cleaning_liquid_alarm.setChecked(false);
      }

      if ((byte)(this.setData[6] & 8) == 8) {
         this.engine_oil_alarm.setChecked(true);
      } else {
         this.engine_oil_alarm.setChecked(false);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      if (var1.length > 1) {
         if (var3 == 18) {
            while(var2 < 10) {
               byte[] var4 = this.setData;
               var3 = var2 + 1;
               var4[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings12();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492899);
      this.mTemperature_value = (SwitchPreference)this.findPreference("temperature_value");
      SwitchPreference var2 = this.mTemperature_value;
      String var3;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mTemperature_value.getSharedPreferences().getString("timeswitchlight", "0");
         this.mTemperature_value.setValue(var3);
      }

      this.mConsumption = (SwitchPreference)this.findPreference("consumption");
      var2 = this.mConsumption;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mConsumption.getSharedPreferences().getString("timeswitchlight", "0");
         this.mConsumption.setValue(var3);
      }

      this.mGear_remind = (SwitchPreference)this.findPreference("gear_remind");
      var2 = this.mGear_remind;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mGear_remind.getSharedPreferences().getString("timeswitchlight", "0");
         this.mGear_remind.setValue(var3);
      }

      this.mInnage = (SwitchPreference)this.findPreference("innage");
      var2 = this.mInnage;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mInnage.getSharedPreferences().getString("timeswitchlight", "0");
         this.mInnage.setValue(var3);
      }

      this.mVbatt = (SwitchPreference)this.findPreference("vbatt");
      var2 = this.mVbatt;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mVbatt.getSharedPreferences().getString("timeswitchlight", "0");
         this.mVbatt.setValue(var3);
      }

      this.fuel_low = (HCheckBoxPreference)this.findPreference("fuel_low");
      this.voltage_low = (HCheckBoxPreference)this.findPreference("voltage_low");
      this.safety_belt_warning = (HCheckBoxPreference)this.findPreference("safety_belt_warning");
      this.cleaning_liquid_alarm = (HCheckBoxPreference)this.findPreference("cleaning_liquid_alarm");
      this.engine_oil_alarm = (HCheckBoxPreference)this.findPreference("engine_oil_alarm");
      this.mMileage = (SwitchPreference)this.findPreference("mileage");
      var2 = this.mMileage;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mMileage.getSharedPreferences().getString("timeswitchlight", "0");
         this.mMileage.setValue(var3);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return true;
   }
}
