package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus152settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mAuto_light_open;
   private HCheckBoxPreference mAuto_wipers;
   private SwitchboxPreference mS;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData5AA5((byte)111, new byte[]{(byte)var1, (byte)var2, -1, -1}, 4);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 97) {
         HCheckBoxPreference var5 = this.mAuto_light_open;
         byte var2 = var1[2];
         boolean var4 = true;
         boolean var3;
         if ((var2 & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto_wipers;
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
      this.addPreferencesFromResource(2131492883);
      this.mAuto_light_open = this.findHCheckBoxPreference("auto_light_open");
      this.mAuto_wipers = this.findHCheckBoxPreference("auto_wipers");
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 97}, 3);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3;
      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var5) {
         return false;
      }

      int var4;
      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mS) {
            this.SetCmdkey(4, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var5 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var6 = this.mAuto_light_open;
      byte var4 = 0;
      byte var3;
      if (var2 == var6) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(1, var3);
      }

      if (var2 == this.mAuto_wipers) {
         if (var5) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(2, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
