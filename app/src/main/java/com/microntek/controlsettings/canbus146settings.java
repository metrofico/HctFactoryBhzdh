package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus146settings extends BasePreferenceActivity {
   private HCheckBoxPreference mBuzzer;
   private HCheckBoxPreference mCamera_delay;
   private SwitchboxPreference mCar_in_light;
   private HCheckBoxPreference mControl_system;
   private SwitchboxPreference mElectron;
   private HCheckBoxPreference mInformation_tone;
   private HCheckBoxPreference mLight;
   private SwitchboxPreference mMileage_unit;
   private SwitchboxPreference mTemp;
   private SwitchboxPreference mTurn_lights_set;
   private HCheckBoxPreference mWarning_control;

   private void SetCmdkey(int var1, int var2, int var3) {
      byte var5 = (byte)var2;
      byte var4 = (byte)var3;
      this.SendCanBusCmdData5AA5((byte)var1, new byte[]{var5, var4}, 2);
   }

   private void SetCmdkey(int var1, int var2, int var3, int var4, int var5) {
      byte var7 = (byte)var2;
      byte var9 = (byte)var3;
      byte var8 = (byte)var4;
      byte var6 = (byte)var5;
      this.SendCanBusCmdData5AA5((byte)var1, new byte[]{var7, var9, var8, var6}, 4);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = super.cmd;
      boolean var3 = false;
      boolean var6 = false;
      boolean var5 = false;
      boolean var4 = false;
      HCheckBoxPreference var7;
      if (var2 == 103) {
         this.mTurn_lights_set.setValue(var1[3] >> 0 & 1);
         var7 = this.mLight;
         if ((var1[3] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         this.mMileage_unit.setValue(var1[3] >> 2 & 1);
         var7 = this.mInformation_tone;
         if ((var1[3] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mWarning_control;
         var3 = var4;
         if ((var1[3] & 16) != 0) {
            var3 = true;
         }

         var7.setChecked(var3);
      } else if (var2 == 133) {
         var7 = this.mControl_system;
         if ((var1[3] & 1) != 0) {
            var3 = true;
         }

         var7.setChecked(var3);
         this.mElectron.setValue(var1[3] >> 1 & 3);
      } else if (var2 == 104) {
         this.mTemp.setValue(var1[3] >> 4 & 1);
         var7 = this.mBuzzer;
         var3 = var6;
         if ((var1[3] & 1) != 0) {
            var3 = true;
         }

         var7.setChecked(var3);
      } else if (var2 == 97) {
         this.mCar_in_light.setValue(var1[4] >> 0 & 15);
      } else if (var2 == 232) {
         var7 = this.mCamera_delay;
         var3 = var5;
         if ((var1[4] & 1) != 0) {
            var3 = true;
         }

         var7.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492882);
      this.mTurn_lights_set = this.findSwitchboxPreference("turn_lights_set");
      this.mMileage_unit = this.findSwitchboxPreference("mileage_unit");
      this.mElectron = this.findSwitchboxPreference("electron");
      this.mTemp = this.findSwitchboxPreference("temp");
      this.mCar_in_light = this.findSwitchboxPreference("car_in_light");
      this.mLight = this.findHCheckBoxPreference("light");
      this.mInformation_tone = this.findHCheckBoxPreference("information_tone");
      this.mWarning_control = this.findHCheckBoxPreference("warning_control");
      this.mControl_system = this.findHCheckBoxPreference("control_system");
      this.mBuzzer = this.findHCheckBoxPreference("buzzer");
      this.mCamera_delay = this.findHCheckBoxPreference("camera_delay");
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 103}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, -123}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 97}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 104}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, -24}, 3);
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
         if (var1 == this.mTurn_lights_set) {
            this.SetCmdkey(108, 3, var4);
         } else if (var1 == this.mMileage_unit) {
            this.SetCmdkey(108, 5, var4);
         } else if (var1 == this.mElectron) {
            this.SetCmdkey(138, 4, var4);
         } else if (var1 == this.mTemp) {
            this.SetCmdkey(109, 4, var4);
         } else if (var1 == this.mCar_in_light) {
            this.SetCmdkey(111, 4, var4, 0, 0);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 instanceof HCheckBoxPreference) {
         boolean var9 = ((HCheckBoxPreference)var2).mChecked;
         HCheckBoxPreference var10 = this.mLight;
         byte var8 = 0;
         byte var5 = 0;
         byte var6 = 0;
         byte var4 = 0;
         byte var7 = 0;
         byte var3 = 0;
         if (var2 == var10) {
            if (!var9) {
               var3 = 1;
            }

            this.SetCmdkey(108, 4, var3);
         } else if (var2 == this.mInformation_tone) {
            if (var9) {
               var3 = var8;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(108, 6, var3);
         } else if (var2 == this.mWarning_control) {
            if (var9) {
               var3 = var5;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(108, 7, var3);
         } else if (var2 == this.mControl_system) {
            if (var9) {
               var3 = var6;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(138, 3, var3);
         } else if (var2 == this.mBuzzer) {
            if (var9) {
               var3 = var4;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(109, 6, var3);
         } else if (var2 == this.mCamera_delay) {
            if (var9) {
               var3 = var7;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(242, 6, var3);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
