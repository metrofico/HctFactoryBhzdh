package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings.System;

public class canbus64settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private SwitchboxPreference mCar_type;
   private byte[] setData = new byte[30];

   private void SetCmdkey(byte var1) {
      this.SendCanBusCmdData5AA5((byte)45, new byte[]{2, var1}, 2);
   }

   protected void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      int var3 = var1.length;
      int var2 = 2;
      if (var3 > 2 && var3 <= 29 && var4 == 82) {
         while(var2 < var3) {
            this.setData[var2 - 2] = var1[var2];
            ++var2;
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492936);
      this.mCar_type = (SwitchboxPreference)this.findPreference("car_type");
      this.mCar_type.setOnPreferenceChangeListener(this);
      int var2 = System.getInt(this.getContentResolver(), "com.microntek.control64settings", 1);
      SwitchboxPreference var3 = this.mCar_type;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(var2);
      var3.setValue(var4.toString());
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var3;
      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var5) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mCar_type) {
            this.SetCmdkey((byte)var4);
            System.putInt(this.getContentResolver(), "com.microntek.control64settings", var4);
            SwitchboxPreference var7 = this.mCar_type;
            StringBuilder var8 = new StringBuilder();
            var8.append("");
            var8.append(var4);
            var7.setValue(var8.toString());
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      return false;
   }
}
