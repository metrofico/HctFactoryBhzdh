package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus73settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mDoor_aut;
   private HCheckBoxPreference mWiperssettings;

   private void RequestCmdkey(int var1) {
      byte[] var2 = new byte[]{(byte)var1, 0};
      this.SendCanBusCmdData2E((byte)-112, var2, 2);
   }

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 56) {
         HCheckBoxPreference var5 = this.mDoor_aut;
         byte var2 = var1[2];
         boolean var4 = true;
         boolean var3;
         if ((var2 & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mWiperssettings;
         if ((var1[2] & 2) != 0) {
            var3 = var4;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492938);
      this.mWiperssettings = this.findHCheckBoxPreference("wiperssettings");
      this.mDoor_aut = this.findHCheckBoxPreference("door_aut");
      this.RequestCmdkey(56);
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
      }

      return true;
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var5 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var6 = this.mDoor_aut;
      byte var3 = 1;
      byte var4 = 0;
      if (var2 == var6) {
         if (var5) {
            var3 = 0;
         }

         this.SetCmdkey(0, var3);
      } else if (var2 == this.mWiperssettings) {
         if (var5) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(1, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
