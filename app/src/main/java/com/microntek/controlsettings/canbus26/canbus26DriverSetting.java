package com.microntek.controlsettings.canbus26;

import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;

public class canbus26DriverSetting extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean isChecked_lu;
   private boolean isChecked_ru;
   private HCheckBoxPreference mRadar_mute;
   private HCheckBoxPreference mSide_parking;
   private HCheckBoxPreference mStorage_in_the_car;
   private byte[] setData = new byte[20];

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdDataAA55((byte)76, new byte[]{var1, var2}, 2);
   }

   private void updasettings47() {
      if ((byte)(this.setData[0] & 128) == -128) {
         this.isChecked_lu = true;
         this.mSide_parking.setChecked(true);
      } else {
         this.isChecked_lu = false;
         this.mSide_parking.setChecked(false);
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.isChecked_ru = true;
         this.mStorage_in_the_car.setChecked(true);
      } else {
         this.isChecked_ru = false;
         this.mStorage_in_the_car.setChecked(false);
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.mRadar_mute.setChecked(true);
      } else {
         this.mRadar_mute.setChecked(false);
      }

   }

   protected void ProcessData(byte[] var1) {
      byte var2 = var1[0];
      if (var1.length > 1) {
         if (var2 == 71) {
            this.setData[0] = var1[1];
            this.updasettings47();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492898);
      this.mSide_parking = (HCheckBoxPreference)this.findPreference("side_parking");
      HCheckBoxPreference var2 = this.mSide_parking;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mStorage_in_the_car = (HCheckBoxPreference)this.findPreference("storage_in_the_car");
      var2 = this.mStorage_in_the_car;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRadar_mute = (HCheckBoxPreference)this.findPreference("radar_mute");
      var2 = this.mRadar_mute;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.isChecked_lu = this.mSide_parking.isChecked();
      this.isChecked_ru = this.mStorage_in_the_car.isChecked();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return true;
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mSide_parking) {
         if (this.isChecked_lu) {
            this.RequestCmdkey((byte)9, (byte)0);
         } else {
            this.RequestCmdkey((byte)9, (byte)1);
         }
      }

      if (var1 == this.mStorage_in_the_car) {
         if (this.isChecked_ru) {
            this.RequestCmdkey((byte)10, (byte)0);
         } else {
            this.RequestCmdkey((byte)10, (byte)1);
         }
      }

      HCheckBoxPreference var2 = this.mRadar_mute;
      if (var1 == var2) {
         if (var2.isChecked()) {
            this.RequestCmdkey((byte)11, (byte)1);
         } else {
            this.RequestCmdkey((byte)11, (byte)0);
         }
      }

      return false;
   }
}
