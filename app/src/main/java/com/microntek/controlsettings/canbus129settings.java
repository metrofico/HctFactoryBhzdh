package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus129settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchboxPreference mHeadlight_off;
   private SwitchboxPreference mHome_lighting;
   private SwitchboxPreference mPower_saving;
   private SwitchboxPreference mRain_sensor;
   private SwitchboxPreference mRear_view;
   private HCheckBoxPreference mTrailer_settings;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData5AA5((byte)111, new byte[]{(byte)var1, (byte)var2, -1, -1}, 4);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 98 && super.length > 4) {
         SwitchboxPreference var5 = this.mHeadlight_off;
         byte var2 = var1[2];
         boolean var4 = false;
         boolean var3;
         if ((var2 & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mHome_lighting;
         if ((var1[2] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mPower_saving;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mRain_sensor;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         HCheckBoxPreference var6 = this.mTrailer_settings;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var6, var3);
         this.mHeadlight_off.setValue(var1[6] >> 6 & 3);
         this.mHome_lighting.setValue(var1[6] >> 4 & 3);
         this.mPower_saving.setValue(var1[6] >> 2 & 3);
         this.mRain_sensor.setValue(var1[6] >> 1 & 1);
         var6 = this.mTrailer_settings;
         var3 = var4;
         if ((var1[6] & 1) != 0) {
            var3 = true;
         }

         var6.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492874);
      this.mHeadlight_off = this.findSwitchboxPreference("headlight_off");
      this.mHome_lighting = this.findSwitchboxPreference("home_lighting");
      this.mPower_saving = this.findSwitchboxPreference("power_saving");
      this.mRain_sensor = this.findSwitchboxPreference("rain_sensor");
      this.mRear_view = this.findSwitchboxPreference("rear_view");
      this.mTrailer_settings = this.findHCheckBoxPreference("trailer_settings");
      SwitchboxPreference var2 = this.mRear_view;
      var2.setValue(this.getInt(var2.getKey(), 0));
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 98}, 3);
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
         if (var1 == this.mHeadlight_off) {
            this.SetCmdkey(16, var4);
         } else if (var1 == this.mHome_lighting) {
            this.SetCmdkey(17, var4);
         } else if (var1 == this.mPower_saving) {
            this.SetCmdkey(18, var4);
         } else if (var1 == this.mRain_sensor) {
            this.SetCmdkey(19, var4);
         } else if (var1 == this.mRear_view) {
            this.SendCanBusCmdData5AA5((byte)111, new byte[]{0, (byte)var4, -1, -1}, 4);
            this.putInt(this.mRear_view.getKey(), var4);
            this.mRear_view.setValue(var4);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var4 = ((HCheckBoxPreference)var2).mChecked;
      if (var2 == this.mTrailer_settings) {
         byte var3;
         if (var4) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(20, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
