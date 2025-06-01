package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus128settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mActive_yaw;
   private SwitchboxPreference mAlarm_distance;
   private HCheckBoxPreference mAuto_braking;
   private HCheckBoxPreference mAutomatic_lamp;
   private HCheckBoxPreference mClos_door_window;
   private HCheckBoxPreference mClos_sunshade;
   private HCheckBoxPreference mFor_warning;
   private HCheckBoxPreference mLock_lamp;
   private HCheckBoxPreference mOff_unlock;
   private HCheckBoxPreference mPermeability_mode;
   private SwitchboxPreference mPower_steering;
   private HCheckBoxPreference mRemote_lock;
   private HCheckBoxPreference mRunning_lights;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-128, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 80 && super.length >= 6) {
         SwitchboxPreference var5 = this.mPower_steering;
         byte var2 = var1[4];
         boolean var4 = true;
         var5.setValue(var2 >> 7 & 1);
         HCheckBoxPreference var6 = this.mRemote_lock;
         boolean var3;
         if ((var1[4] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mClos_door_window;
         if ((var1[4] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mClos_sunshade;
         if ((var1[4] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mRunning_lights;
         if ((var1[4] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mPermeability_mode;
         if ((4 & var1[4]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mOff_unlock;
         if ((var1[7] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mLock_lamp;
         if ((var1[7] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mAuto_braking;
         if ((var1[7] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mFor_warning;
         if ((var1[7] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mAutomatic_lamp;
         if ((var1[7] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mActive_yaw;
         if ((var1[7] & 16) != 0) {
            var3 = var4;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         this.mAlarm_distance.setValue(var1[7] >> 2 & 3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492873);
      this.mPower_steering = this.findSwitchboxPreference("power_steering");
      this.mAlarm_distance = this.findSwitchboxPreference("alarm_distance");
      this.mRemote_lock = this.findHCheckBoxPreference("remote_lock");
      this.mClos_door_window = this.findHCheckBoxPreference("clos_door_window");
      this.mClos_sunshade = this.findHCheckBoxPreference("clos_sunshade");
      this.mRunning_lights = this.findHCheckBoxPreference("running_lights");
      this.mPermeability_mode = this.findHCheckBoxPreference("permeability_mode");
      this.mOff_unlock = this.findHCheckBoxPreference("off_unlock");
      this.mLock_lamp = this.findHCheckBoxPreference("lock_lamp");
      this.mAuto_braking = this.findHCheckBoxPreference("auto_braking");
      this.mFor_warning = this.findHCheckBoxPreference("for_warning");
      this.mAutomatic_lamp = this.findHCheckBoxPreference("automatic_lamp");
      this.mActive_yaw = this.findHCheckBoxPreference("active_yaw");
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
         if (var1 == this.mPower_steering) {
            this.SetCmdkey(6, var3);
         } else if (var1 == this.mAlarm_distance) {
            this.SetCmdkey(25, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var14 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var15 = this.mRemote_lock;
      byte var6 = 0;
      byte var9 = 0;
      byte var13 = 0;
      byte var10 = 0;
      byte var7 = 0;
      byte var4 = 0;
      byte var5 = 0;
      byte var11 = 0;
      byte var3 = 0;
      byte var8 = 0;
      byte var12 = 0;
      if (var2 == var15) {
         if (var14) {
            var3 = var12;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(7, var3);
      } else if (var2 == this.mClos_door_window) {
         if (var14) {
            var3 = var6;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(8, var3);
      } else if (var2 == this.mClos_sunshade) {
         if (var14) {
            var3 = var9;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(9, var3);
      } else if (var2 == this.mRunning_lights) {
         if (var14) {
            var3 = var13;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(10, var3);
      } else if (var2 == this.mPermeability_mode) {
         if (var14) {
            var3 = var10;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(18, var3);
      } else if (var2 == this.mOff_unlock) {
         if (var14) {
            var3 = var7;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(1, var3);
      } else if (var2 == this.mLock_lamp) {
         if (var14) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(4, var3);
      } else if (var2 == this.mAuto_braking) {
         if (var14) {
            var3 = var5;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(21, var3);
      } else if (var2 == this.mFor_warning) {
         if (var14) {
            var3 = var11;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(22, var3);
      } else if (var2 == this.mAutomatic_lamp) {
         if (!var14) {
            var3 = 1;
         }

         this.SetCmdkey(23, var3);
      } else if (var2 == this.mActive_yaw) {
         if (var14) {
            var3 = var8;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(24, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
