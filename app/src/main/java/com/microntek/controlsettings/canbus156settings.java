package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus156settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchboxPreference mActive;
   private SwitchboxPreference mAttention;
   private HCheckBoxPreference mBusy_warning;
   private SwitchboxPreference mCar_light;
   private HCheckBoxPreference mDoor_sound;
   private HCheckBoxPreference mEsp;
   private HCheckBoxPreference mIncar_light;
   private HCheckBoxPreference mLanesystem;
   private HCheckBoxPreference mLight;
   private SwitchboxPreference mMileage_unit;
   private HCheckBoxPreference mOutlock;
   private SwitchboxPreference mSpeed_activation;
   private HCheckBoxPreference mSpeedometer;
   private HCheckBoxPreference mStandby_mode;

   private void SetCmdkey(int var1, int var2) {
      byte[] var3 = new byte[]{(byte)var1, (byte)var2, 0, 0};
      this.SendCanBusCmdData5AA5((byte)111, var3, 4);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 97) {
         SwitchboxPreference var5 = this.mActive;
         byte var2 = var1[2];
         boolean var4 = false;
         var5.setValue(var2 >> 0 & 3);
         this.mAttention.setValue(var1[3] >> 0 & 3);
         this.mSpeed_activation.setValue(var1[5] >> 4 & 7);
         this.mCar_light.setValue(var1[7] >> 0 & 7);
         this.mMileage_unit.setValue(var1[9] >> 4 & 1);
         HCheckBoxPreference var6 = this.mEsp;
         boolean var3;
         if ((var1[2] & 16) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mBusy_warning;
         if ((var1[3] & 16) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mLanesystem;
         if ((var1[4] & 16) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mStandby_mode;
         if ((var1[5] & 1) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mDoor_sound;
         if ((var1[6] & 16) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mOutlock;
         if ((var1[6] & 1) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mLight;
         if ((var1[7] & 16) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mIncar_light;
         if ((var1[8] & 16) > 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mSpeedometer;
         var3 = var4;
         if ((var1[8] & 1) > 0) {
            var3 = true;
         }

         var6.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492886);
      CharSequence[] var6 = new CharSequence[8];
      CharSequence[] var4 = new CharSequence[var6.length];
      String var5 = this.getString(2131296511);
      byte var3 = 0;
      var6[0] = var5;

      int var2;
      StringBuilder var7;
      for(var2 = 0; var2 < var6.length; ++var2) {
         var4[var2] = Integer.toString(var2);
         if (var2 > 0) {
            var7 = new StringBuilder();
            var7.append(Integer.toString(var2 * 10 + 110));
            var7.append("km/h");
            var6[var2] = var7.toString();
         }
      }

      this.mActive = this.findSwitchboxPreference("active");
      this.mAttention = this.findSwitchboxPreference("attention");
      this.mSpeed_activation = this.findSwitchboxPreference("speed_activation");
      this.mSpeed_activation.setEntries(var6);
      this.mSpeed_activation.setEntryValues(var4);
      var4 = new CharSequence[5];
      var6 = new CharSequence[var4.length];
      var4[0] = this.getString(2131296511);

      for(var2 = var3; var2 < var4.length; ++var2) {
         var6[var2] = Integer.toString(var2);
         if (var2 > 0) {
            var7 = new StringBuilder();
            var7.append(Integer.toString(var2 * 15));
            var7.append("s");
            var4[var2] = var7.toString();
         }
      }

      this.mCar_light = this.findSwitchboxPreference("car_light");
      this.mCar_light.setEntries(var4);
      this.mCar_light.setEntryValues(var6);
      this.mMileage_unit = this.findSwitchboxPreference("mileage_unit");
      this.mEsp = this.findHCheckBoxPreference("esp");
      this.mBusy_warning = this.findHCheckBoxPreference("busy_warning");
      this.mLanesystem = this.findHCheckBoxPreference("lanesystem");
      this.mStandby_mode = this.findHCheckBoxPreference("standby_mode");
      this.mDoor_sound = this.findHCheckBoxPreference("door_sound");
      this.mOutlock = this.findHCheckBoxPreference("outlock");
      this.mLight = this.findHCheckBoxPreference("light");
      this.mIncar_light = this.findHCheckBoxPreference("incar_light");
      this.mSpeedometer = this.findHCheckBoxPreference("speedometer");
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
         if (var1 == this.mActive) {
            this.SetCmdkey(2, var3);
         } else if (var1 == this.mAttention) {
            this.SetCmdkey(4, var3);
         } else if (var1 == this.mSpeed_activation) {
            this.SetCmdkey(17, var3);
         } else if (var1 == this.mCar_light) {
            this.SetCmdkey(34, var3);
         } else if (var1 == this.mMileage_unit) {
            this.SetCmdkey(50, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var12 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var13 = this.mEsp;
      byte var8 = 0;
      byte var11 = 0;
      byte var7 = 0;
      byte var4 = 0;
      byte var10 = 0;
      byte var3 = 0;
      byte var5 = 0;
      byte var6 = 0;
      byte var9 = 0;
      if (var2 == var13) {
         if (var12) {
            var3 = var9;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(1, var3);
      } else if (var2 == this.mBusy_warning) {
         if (var12) {
            var3 = var8;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(3, var3);
      } else if (var2 == this.mLanesystem) {
         if (var12) {
            var3 = var11;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(5, var3);
      } else if (var2 == this.mStandby_mode) {
         if (var12) {
            var3 = var7;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(18, var3);
      } else if (var2 == this.mDoor_sound) {
         if (var12) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(19, var3);
      } else if (var2 == this.mOutlock) {
         if (var12) {
            var3 = var10;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(20, var3);
      } else if (var2 == this.mLight) {
         if (!var12) {
            var3 = 1;
         }

         this.SetCmdkey(33, var3);
      } else if (var2 == this.mIncar_light) {
         if (var12) {
            var3 = var5;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(35, var3);
      } else if (var2 == this.mSpeedometer) {
         if (var12) {
            var3 = var6;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(49, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
