package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus157settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mAutomatic_seat_heating;
   private SwitchboxPreference mBright_headlights;
   private SwitchboxPreference mEngine_power;
   private SwitchboxPreference mHeadlights_off;
   private SwitchboxPreference mKey_unlock;
   private HCheckBoxPreference mKeyless_entry;
   private HCheckBoxPreference mLights_flash;
   private SwitchboxPreference mParking_system;
   private SwitchboxPreference mUnit_set;
   private HCheckBoxPreference mUnlock_driving;
   private HCheckBoxPreference mWipers_start;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-105, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 7 && super.length >= 7) {
         this.mParking_system.setValue(var1[2] >> 6 & 3);
         this.mHeadlights_off.setValue(var1[4] >> 6 & 3);
         this.mBright_headlights.setValue(var1[4] >> 4 & 3);
         HCheckBoxPreference var5 = this.mWipers_start;
         byte var2 = var1[4];
         boolean var4 = false;
         boolean var3;
         if ((var2 & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mLights_flash;
         if ((2 & var1[4]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mUnlock_driving;
         if ((var1[5] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.mKey_unlock.setValue(var1[5] >> 3 & 1);
         var5 = this.mKeyless_entry;
         if ((var1[5] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAutomatic_seat_heating;
         var3 = var4;
         if ((var1[3] & 1) != 0) {
            var3 = true;
         }

         var5.setChecked(var3);
         this.mEngine_power.setValue(var1[6] >> 5 & 3);
         this.mUnit_set.setValue(var1[4] >> 4 & 1);
      }

      if (super.cmd == 112 && super.length >= 9) {
         this.mUnit_set.setValue(var1[9] & 1);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492887);
      this.mParking_system = this.findSwitchboxPreference("parking_system");
      this.mHeadlights_off = this.findSwitchboxPreference("headlights_off");
      this.mBright_headlights = this.findSwitchboxPreference("bright_headlights");
      this.mKey_unlock = this.findSwitchboxPreference("key_unlock");
      this.mEngine_power = this.findSwitchboxPreference("engine_power");
      this.mUnit_set = this.findSwitchboxPreference("unit_set");
      this.mWipers_start = this.findHCheckBoxPreference("wipers_start");
      this.mLights_flash = this.findHCheckBoxPreference("lights_flash");
      this.mUnlock_driving = this.findHCheckBoxPreference("unlock_driving");
      this.mKeyless_entry = this.findHCheckBoxPreference("keyless_entry");
      this.mAutomatic_seat_heating = this.findHCheckBoxPreference("automatic_seat_heating");
      this.SendCanBusCmdData2E((byte)-15, new byte[]{7}, 1);
      this.SendCanBusCmdData2E((byte)-15, new byte[]{112}, 1);
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
         if (var1 == this.mParking_system) {
            this.SetCmdkey(1, var4);
         } else if (var1 == this.mHeadlights_off) {
            if (var4 == 1) {
               var3 = 30;
            } else if (var4 == 2) {
               var3 = 60;
            } else {
               var3 = var4;
               if (var4 == 3) {
                  var3 = 90;
               }
            }

            this.SetCmdkey(17, var3);
         } else if (var1 == this.mBright_headlights) {
            if (var4 == 1) {
               var3 = 30;
            } else if (var4 == 2) {
               var3 = 60;
            } else {
               var3 = var4;
               if (var4 == 3) {
                  var3 = 90;
               }
            }

            this.SetCmdkey(18, var3);
         } else if (var1 == this.mKey_unlock) {
            this.SetCmdkey(36, var4);
         } else if (var1 == this.mEngine_power) {
            if (var4 == 1) {
               var3 = 3;
            } else if (var4 == 2) {
               var3 = 20;
            } else {
               var3 = var4;
               if (var4 == 3) {
                  var3 = 40;
               }
            }

            this.SetCmdkey(50, var3);
         } else if (var1 == this.mUnit_set) {
            this.SetCmdkey(82, var4);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var8 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var9 = this.mWipers_start;
      byte var5 = 1;
      byte var3 = 1;
      byte var4 = 1;
      byte var6 = 1;
      byte var7 = 1;
      if (var2 == var9) {
         if (var8) {
            var3 = var7;
         } else {
            var3 = 2;
         }

         this.SetCmdkey(19, var3);
      } else if (var2 == this.mLights_flash) {
         if (var8) {
            var3 = var5;
         } else {
            var3 = 2;
         }

         this.SetCmdkey(21, var3);
      } else if (var2 == this.mUnlock_driving) {
         if (!var8) {
            var3 = 2;
         }

         this.SetCmdkey(34, var3);
      } else if (var2 == this.mKeyless_entry) {
         if (var8) {
            var3 = var4;
         } else {
            var3 = 2;
         }

         this.SetCmdkey(37, var3);
      } else if (var2 == this.mAutomatic_seat_heating) {
         if (var8) {
            var3 = var6;
         } else {
            var3 = 2;
         }

         this.SetCmdkey(39, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
