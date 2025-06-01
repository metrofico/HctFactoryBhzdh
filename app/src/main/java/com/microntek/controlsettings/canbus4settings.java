package com.microntek.controlsettings;

import android.content.ContentResolver;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings.System;

public class canbus4settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mCar_type;

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492917);
      this.mCar_type = (HCheckBoxPreference)this.findPreference("car_type");
      ContentResolver var4 = this.getContentResolver();
      boolean var3 = false;
      int var2 = System.getInt(var4, "com.microntek.control4settings", 0);
      HCheckBoxPreference var5 = this.mCar_type;
      if (var5 != null) {
         var5.setOnPreferenceClickListener(this);
         var5 = this.mCar_type;
         if (var2 == 1) {
            var3 = true;
         }

         var5.setChecked(var3);
      }

   }

   protected void onDestroy() {
      super.onDestroy();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3;
      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var4;
      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var5) {
         return true;
      }

      return var3 == var4;
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mCar_type;
      if (var1 == var2) {
         if (var2.mChecked) {
            System.putInt(this.getContentResolver(), "com.microntek.control4settings", 0);
            this.mCar_type.setChecked(false);
         } else {
            System.putInt(this.getContentResolver(), "com.microntek.control4settings", 1);
            this.mCar_type.setChecked(true);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
   }
}
