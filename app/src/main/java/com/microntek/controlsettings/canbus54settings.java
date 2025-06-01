package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings.System;

public class canbus54settings extends BasePreferenceActivity {
   private SwitchboxPreference mInstrument_style;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-103, new byte[]{var1, var2, 0, 0}, 4);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = var1[0];
      var2 = var1.length;
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492925);
      this.mInstrument_style = (SwitchboxPreference)this.findPreference("instrument_style");
      this.mInstrument_style.setOnPreferenceChangeListener(this);
      this.SetCmdkey((byte)0, (byte)System.getInt(this.getContentResolver(), "canbus54_mInstrument_style", 0));
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
         if (var1 == this.mInstrument_style) {
            this.SetCmdkey((byte)0, (byte)var4);
            SwitchboxPreference var7 = this.mInstrument_style;
            StringBuilder var8 = new StringBuilder();
            var8.append(var4);
            var8.append("");
            var7.setValue(var8.toString());
            System.putInt(this.getContentResolver(), "canbus54_mInstrument_style", var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      return false;
   }
}
