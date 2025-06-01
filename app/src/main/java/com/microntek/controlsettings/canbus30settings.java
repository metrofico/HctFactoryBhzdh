package com.microntek.controlsettings;

import android.os.Bundle;

public class canbus30settings extends BasePreferenceActivity {
   private HCheckBoxPreference mPower_amplifier;
   private SwitchPreference mSpeed;
   private byte[] setData = new byte[20];

   private void updasettings() {
      if (this.setData[2] == 1) {
         this.mPower_amplifier.setChecked(true);
      } else {
         this.mPower_amplifier.setChecked(false);
      }

      byte[] var3 = this.setData;
      byte var2 = var3[0];
      byte var1 = var3[1];
      SwitchPreference var5 = this.mSpeed;
      StringBuilder var4 = new StringBuilder();
      var4.append(((var2 & 255) << 8) + (var1 & 255));
      var4.append(" Km/h");
      var5.setValue("1", var4.toString());
   }

   protected void ProcessData(byte[] var1) {
      if (var1.length > 1) {
         for(int var2 = 0; var2 < 3; ++var2) {
            this.setData[var2] = var1[var2];
         }

         this.updasettings();
      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492913);
      this.mSpeed = (SwitchPreference)this.findPreference("speed");
      SwitchPreference var2 = this.mSpeed;
      if (var2 != null) {
         String var3 = var2.getSharedPreferences().getString("timeswitchlight", "0");
         this.mSpeed.setValue(var3);
      }

      this.mPower_amplifier = (HCheckBoxPreference)this.findPreference("power_amplifier");
   }
}
