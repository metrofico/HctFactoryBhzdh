package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus155settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchboxPreference mBack_dis;
   private HCheckBoxPreference mBlind_detection;
   private HCheckBoxPreference mSteering_photo;
   private HCheckBoxPreference mTurning_lighting;
   private HCheckBoxPreference mWipers_window;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 64) {
         SwitchboxPreference var5 = this.mBack_dis;
         byte var2 = var1[2];
         boolean var4 = true;
         var5.setValue(var2 >> 4 & 1);
         HCheckBoxPreference var6 = this.mSteering_photo;
         boolean var3;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mTurning_lighting;
         if ((64 & var1[2]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mBlind_detection;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mWipers_window;
         if ((var1[2] & 8) != 0) {
            var3 = var4;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492885);
      this.mBack_dis = this.findSwitchboxPreference("back_dis");
      this.mSteering_photo = this.findHCheckBoxPreference("steering_photo");
      this.mTurning_lighting = this.findHCheckBoxPreference("turning_lighting");
      this.mBlind_detection = this.findHCheckBoxPreference("blind_detection");
      this.mWipers_window = this.findHCheckBoxPreference("wipers_window");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{64, 0}, 2);
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
         if (var1 == this.mBack_dis) {
            this.SetCmdkey(4, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var7 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var8 = this.mSteering_photo;
      byte var5 = 0;
      byte var3 = 0;
      byte var4 = 0;
      byte var6 = 0;
      if (var2 == var8) {
         if (var7) {
            var3 = var6;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(1, var3);
      } else if (var2 == this.mTurning_lighting) {
         if (var7) {
            var3 = var5;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(2, var3);
      } else if (var2 == this.mBlind_detection) {
         if (!var7) {
            var3 = 1;
         }

         this.SetCmdkey(3, var3);
      } else if (var2 == this.mWipers_window) {
         if (var7) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(5, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
