package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus94settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mAlertvolumesetting;
   private HCheckBoxPreference mRedar;
   private byte[] setData = new byte[20];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E(var1, new byte[]{var2}, 1);
   }

   protected void ProcessData(byte[] var1) {
      boolean var4 = false;
      boolean var5 = false;
      byte var2 = var1[0];
      int var3 = var1.length;
      HCheckBoxPreference var6;
      if (var2 == 7) {
         var6 = this.mRedar;
         var4 = var5;
         if ((var1[2] & 1) != 0) {
            var4 = true;
         }

         var6.setChecked(var4);
      } else if (var2 == 13) {
         var6 = this.mAlertvolumesetting;
         if ((var1[2] & 255) != 0) {
            var4 = true;
         }

         var6.setChecked(var4);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492950);
      this.mRedar = (HCheckBoxPreference)this.findPreference("redar");
      this.mRedar.setOnPreferenceClickListener(this);
      this.mAlertvolumesetting = (HCheckBoxPreference)this.findPreference("alertvolumesetting");
      this.mAlertvolumesetting.setOnPreferenceClickListener(this);
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

      return var4 == var3;
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mRedar;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)-124, (byte)0);
         } else {
            this.SetCmdkey((byte)-124, (byte)1);
         }
      } else {
         var2 = this.mAlertvolumesetting;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)-120, (byte)0);
            } else {
               this.SetCmdkey((byte)-120, (byte)-1);
            }
         }
      }

      return false;
   }
}
