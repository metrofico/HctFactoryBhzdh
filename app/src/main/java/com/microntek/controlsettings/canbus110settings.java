package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus110settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mActive;
   private HCheckBoxPreference mAir_loop;
   private HCheckBoxPreference mAlarm_distance;
   private HCheckBoxPreference mAuto_cabin;
   private HCheckBoxPreference mAutomatic_lamp;
   private HCheckBoxPreference mBack_parking;
   private HCheckBoxPreference mBlind_zone;
   private HCheckBoxPreference mDoor_aut;
   private HCheckBoxPreference mDrive_style;
   private HCheckBoxPreference mFatigue_warning;
   private HCheckBoxPreference mFront_parking;
   private SwitchboxPreference mIndicator_sound;
   private SwitchboxPreference mInstrument_color;
   private SwitchboxPreference mInstrument_light;
   private SwitchboxPreference mInstrument_style;
   private HCheckBoxPreference mLane_warning;
   private SwitchboxPreference mLane_warning_sens;
   private HCheckBoxPreference mLateral_parking;
   private SwitchboxPreference mParking_system;
   private HCheckBoxPreference mWarning_tone;
   private HCheckBoxPreference mWelcome_light;
   private HCheckBoxPreference mWiper_back;
   private HCheckBoxPreference mkey_system;

   private void RequestCmdkey(int var1) {
      byte[] var2 = new byte[]{(byte)var1, 0};
      this.SendCanBusCmdData2E((byte)-112, var2, 2);
   }

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = super.cmd;
      boolean var6 = false;
      boolean var4 = false;
      boolean var5 = false;
      boolean var3;
      HCheckBoxPreference var7;
      if (var2 == 50) {
         this.mLane_warning_sens.setValue((3 & var1[2] >> 5) - 1);
         var7 = this.mLane_warning;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mAlarm_distance;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mActive;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mBlind_zone;
         if ((4 & var1[2]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mAutomatic_lamp;
         if ((var1[2] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mFatigue_warning;
         var3 = var5;
         if ((var1[2] & 1) != 0) {
            var3 = true;
         }

         var7.setChecked(var3);
      } else if (var2 == 51) {
         var7 = this.mFront_parking;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mLateral_parking;
         if ((var1[2] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mBack_parking;
         var3 = var6;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         }

         var7.setChecked(var3);
         this.mParking_system.setValue((var1[2] >> 3 & 3) - 1);
         this.mIndicator_sound.setValue(var1[2] >> 1 & 3);
      } else if (var2 == 52) {
         var7 = this.mDoor_aut;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mkey_system;
         if ((var1[2] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mWelcome_light;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mWiper_back;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mAuto_cabin;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mWarning_tone;
         if ((4 & var1[2]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         var7 = this.mAir_loop;
         if ((var1[2] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var7.setChecked(var3);
         this.putInt("mWarning_tone", var1[2] >> 2 & 1);
         var7 = this.mDrive_style;
         var3 = var4;
         if ((var1[2] & 1) != 0) {
            var3 = true;
         }

         var7.setChecked(var3);
      } else if (var2 == 53 && super.length >= 3) {
         this.mInstrument_style.setValue(var1[3] & 255);
         this.mInstrument_light.setValue(var1[4] & 255);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492868);
      this.mLane_warning_sens = this.findSwitchboxPreference("lane_warning_sens");
      this.mParking_system = this.findSwitchboxPreference("parking_system");
      this.mLane_warning = this.findHCheckBoxPreference("lane_warning");
      this.mAlarm_distance = this.findHCheckBoxPreference("alarm_distance");
      this.mActive = this.findHCheckBoxPreference("active");
      this.mBlind_zone = this.findHCheckBoxPreference("blind_zone");
      this.mAutomatic_lamp = this.findHCheckBoxPreference("automatic_lamp");
      this.mFatigue_warning = this.findHCheckBoxPreference("fatigue_warning");
      this.mFront_parking = this.findHCheckBoxPreference("front_parking");
      this.mLateral_parking = this.findHCheckBoxPreference("lateral_parking");
      this.mBack_parking = this.findHCheckBoxPreference("back_parking");
      this.mDoor_aut = this.findHCheckBoxPreference("door_aut");
      this.mkey_system = this.findHCheckBoxPreference("key_system");
      this.mWelcome_light = this.findHCheckBoxPreference("welcome_light");
      this.mWiper_back = this.findHCheckBoxPreference("wiper_back");
      this.mAuto_cabin = this.findHCheckBoxPreference("auto_cabin");
      this.mWarning_tone = this.findHCheckBoxPreference("warning_tone");
      this.mAir_loop = this.findHCheckBoxPreference("air_loop");
      CharSequence[] var7 = new CharSequence[4];
      CharSequence[] var5 = new CharSequence[var7.length];
      this.mInstrument_color = this.findSwitchboxPreference("instrument_color");
      SwitchboxPreference var6 = this.mInstrument_color;
      byte var3 = 0;
      var6.setValue(this.getInt("mInstrument_color", 0));
      this.mInstrument_style = this.findSwitchboxPreference("instrument_style");

      int var2;
      int var4;
      for(var2 = 0; var2 < var7.length; var2 = var4) {
         StringBuilder var8 = new StringBuilder();
         var8.append(this.getString(2131297241));
         var4 = var2 + 1;
         var8.append(Integer.toString(var4));
         var7[var2] = var8.toString();
         var5[var2] = Integer.toString(var2);
      }

      this.mInstrument_style.setEntries(var7);
      this.mInstrument_style.setEntryValues(var5);
      this.mInstrument_style.setValue("1");
      this.mInstrument_light = this.findSwitchboxPreference("instrument_light");
      var5 = new CharSequence[21];
      var7 = new CharSequence[var5.length];

      for(var2 = var3; var2 < var5.length; ++var2) {
         var5[var2] = Integer.toString(var2 * 5);
         var7[var2] = Integer.toString(var2);
      }

      this.mInstrument_light.setEntries(var5);
      this.mInstrument_light.setEntryValues(var7);
      this.mIndicator_sound = this.findSwitchboxPreference("indicator_sound");
      this.mDrive_style = this.findHCheckBoxPreference("drive_style");
      if (BaseApplication.getInstance().getCustomer().contains("HZC") && this.getCarType() == 2) {
         this.removePreference((Preference)this.mAutomatic_lamp);
         this.removePreference((Preference)this.mParking_system);
         this.removePreference((Preference)this.mFatigue_warning);
      } else {
         this.removePreference((Preference)this.mWarning_tone);
         this.removePreference((Preference)this.mAir_loop);
         this.removePreference((Preference)this.mInstrument_color);
         this.removePreference((Preference)this.mInstrument_style);
         this.removePreference((Preference)this.mInstrument_light);
         this.removePreference((Preference)this.mDrive_style);
         this.removePreference((Preference)this.mIndicator_sound);
      }

      this.RequestCmdkey(50);
      this.RequestCmdkey(51);
      this.RequestCmdkey(52);
      this.RequestCmdkey(53);
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
         if (var1 == this.mLane_warning_sens) {
            this.SetCmdkey(1, var3 + 1);
         } else if (var1 == this.mParking_system) {
            this.SetCmdkey(10, var3 + 1);
         } else if (var1 == this.mInstrument_color) {
            this.SetCmdkey(19, var3);
            this.mInstrument_color.setValue(var3);
            this.putInt("mInstrument_color", var3);
         } else if (var1 == this.mInstrument_style) {
            this.SetCmdkey(20, var3);
         } else if (var1 == this.mInstrument_light) {
            this.SetCmdkey(21, var3 + 0);
         } else if (var1 == this.mIndicator_sound) {
            this.SetCmdkey(24, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var20 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var21 = this.mLane_warning;
      byte var8 = 1;
      byte var17 = 1;
      byte var10 = 1;
      byte var7 = 1;
      byte var9 = 1;
      byte var15 = 1;
      byte var4 = 1;
      byte var14 = 1;
      byte var6 = 1;
      byte var12 = 1;
      byte var19 = 1;
      byte var11 = 1;
      byte var3 = 1;
      byte var16 = 1;
      byte var13 = 1;
      byte var5 = 1;
      byte var18 = 1;
      if (var2 == var21) {
         var3 = var18;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(0, var3);
      } else if (var2 == this.mAlarm_distance) {
         var3 = var8;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(2, var3);
      } else if (var2 == this.mActive) {
         var3 = var17;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(3, var3);
      } else if (var2 == this.mBlind_zone) {
         var3 = var10;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(4, var3);
      } else if (var2 == this.mAutomatic_lamp) {
         var3 = var7;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(5, var3);
      } else if (var2 == this.mFatigue_warning) {
         var3 = var9;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(6, var3);
      } else if (var2 == this.mFront_parking) {
         var3 = var15;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(7, var3);
      } else if (var2 == this.mLateral_parking) {
         var3 = var4;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(8, var3);
      } else if (var2 == this.mBack_parking) {
         var3 = var14;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(9, var3);
      } else if (var2 == this.mDoor_aut) {
         var3 = var6;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(11, var3);
      } else if (var2 == this.mkey_system) {
         var3 = var12;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(12, var3);
      } else if (var2 == this.mWelcome_light) {
         var3 = var19;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(13, var3);
      } else if (var2 == this.mWiper_back) {
         var3 = var11;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(15, var3);
      } else if (var2 == this.mAuto_cabin) {
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(14, var3);
      } else if (var2 == this.mWarning_tone) {
         var3 = var16;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(16, var3);
      } else if (var2 == this.mAir_loop) {
         var3 = var13;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(17, var3);
      } else if (var2 == this.mDrive_style) {
         var3 = var5;
         if (var20) {
            var3 = 0;
         }

         this.SetCmdkey(23, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
