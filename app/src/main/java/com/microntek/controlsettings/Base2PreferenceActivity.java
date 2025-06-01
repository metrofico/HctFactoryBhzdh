package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class Base2PreferenceActivity extends BasePreferenceActivity {
   protected int getPreferencesResId() {
      throw null;
   }

   protected void initPreferenceScreen(PreferenceScreen var1) {
      for(int var2 = 0; var2 < var1.getPreferenceCount(); ++var2) {
         Preference var3 = var1.getPreference(var2);
         if (var3 instanceof SwitchboxPreference) {
            var3.setOnPreferenceChangeListener(this);
         } else if (var3 instanceof HCheckBoxPreference) {
            var3.setOnPreferenceClickListener(this);
         } else if (var3 instanceof PreferenceScreen) {
            var3.setOnPreferenceClickListener(this);
            this.initPreferenceScreen((PreferenceScreen)var3);
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(this.getPreferencesResId());
      this.initPreferenceScreen(this.getPreferenceScreen());
   }
}
