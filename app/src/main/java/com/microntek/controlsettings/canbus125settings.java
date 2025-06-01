package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus125settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchboxPreference mAir_register;
   private HCheckBoxPreference mAngle_auto;
   private SwitchboxPreference mAri_auto_mode;
   private HCheckBoxPreference mCharging_port;
   private HCheckBoxPreference mDown_window;
   private SwitchboxPreference mEnergy_mode;
   private HCheckBoxPreference mLock_window;
   private HCheckBoxPreference mParking_loop;
   private SwitchboxPreference mPm_check;
   private SwitchboxPreference mPower_steering;
   private HCheckBoxPreference mUnlock_window;

   private void RequestCmdkey(int var1) {
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, (byte)var1}, 3);
   }

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData5AA5((byte)111, new byte[]{(byte)var1, (byte)var2, -1, -1}, 4);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 97) {
         SwitchboxPreference var5 = this.mAir_register;
         StringBuilder var6 = new StringBuilder();
         var6.append("");
         var6.append(var1[7] >> 6 & 3);
         var5.setValue(var6.toString());
         var5 = this.mAri_auto_mode;
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var1[7] >> 5 & 1);
         var5.setValue(var6.toString());
         var5 = this.mPm_check;
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var1[7] >> 3 & 3);
         var5.setValue(var6.toString());
         HCheckBoxPreference var7 = this.mParking_loop;
         byte var2 = var1[7];
         boolean var4 = false;
         boolean var3;
         if ((var2 & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mUnlock_window;
         if ((var1[9] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mLock_window;
         if ((var1[9] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mDown_window;
         if ((var1[9] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mAngle_auto;
         if ((var1[9] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mCharging_port;
         if ((var1[10] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var5 = this.mEnergy_mode;
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var1[10] >> 6 & 1);
         var5.setValue(var6.toString());
         var5 = this.mPower_steering;
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var1[10] >> 5 & 1);
         var5.setValue(var6.toString());
         var5 = this.mAir_register;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mAri_auto_mode;
         if ((var1[2] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mPm_check;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var7 = this.mParking_loop;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var7 = this.mUnlock_window;
         if ((var1[4] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var7 = this.mLock_window;
         if ((var1[4] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var7 = this.mDown_window;
         if ((var1[4] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var7 = this.mAngle_auto;
         if ((var1[4] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var7 = this.mCharging_port;
         if ((var1[5] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var7, var3);
         var5 = this.mEnergy_mode;
         if ((var1[5] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.enabledPreference(var5, var3);
         var5 = this.mPower_steering;
         var3 = var4;
         if ((var1[5] & 32) != 0) {
            var3 = true;
         }

         this.enabledPreference(var5, var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492872);
      this.mAir_register = this.findSwitchboxPreference("air_register");
      this.mAri_auto_mode = this.findSwitchboxPreference("ari_auto_mode");
      this.mPm_check = this.findSwitchboxPreference("pm_check");
      this.mEnergy_mode = this.findSwitchboxPreference("energy_mode");
      this.mPower_steering = this.findSwitchboxPreference("power_steering");
      this.mParking_loop = this.findHCheckBoxPreference("parking_loop");
      this.mUnlock_window = this.findHCheckBoxPreference("unlock_window");
      this.mLock_window = this.findHCheckBoxPreference("lock_window");
      this.mDown_window = this.findHCheckBoxPreference("down_window");
      this.mAngle_auto = this.findHCheckBoxPreference("angle_auto");
      this.mCharging_port = this.findHCheckBoxPreference("charging_port");
      this.RequestCmdkey(97);
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
         if (var1 == this.mAir_register) {
            this.SetCmdkey(16, var4);
         } else if (var1 == this.mAri_auto_mode) {
            this.SetCmdkey(17, var4);
         } else if (var1 == this.mPm_check) {
            this.SetCmdkey(18, var4);
         } else if (var1 == this.mEnergy_mode) {
            this.SetCmdkey(49, var4);
         } else if (var1 == this.mPower_steering) {
            this.SetCmdkey(50, var4);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var5 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var6 = this.mParking_loop;
      byte var4 = 0;
      byte var3;
      if (var2 == var6) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(19, var3);
      }

      if (var2 == this.mUnlock_window) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(32, var3);
      }

      if (var2 == this.mLock_window) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(33, var3);
      }

      if (var2 == this.mDown_window) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(34, var3);
      }

      if (var2 == this.mAngle_auto) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(35, var3);
      }

      if (var2 == this.mCharging_port) {
         if (var5) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(48, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
