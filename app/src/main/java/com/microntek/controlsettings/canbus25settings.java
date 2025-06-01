package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus25settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mBlind_detection;
   private HCheckBoxPreference mCar_in_light;
   private HCheckBoxPreference mDoor_warning;
   private SwitchboxPreference mFor_warning;
   private SwitchboxPreference mHome_lighting;
   private SwitchboxPreference mLane_warning;
   private HCheckBoxPreference mMove_warning;
   private SwitchboxPreference mPower_steering;
   private HCheckBoxPreference mTheme_relation;
   private SwitchboxPreference mTheme_setting;

   private void RequestCmdkey(int var1) {
      byte[] var2 = new byte[]{(byte)var1, 0};
      this.SendCanBusCmdData2E((byte)-112, var2, 2);
   }

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 65) {
         this.mFor_warning.setValue(var1[3] >> 5 & 3);
         this.mLane_warning.setValue(var1[3] >> 3 & 3);
         SwitchboxPreference var5 = this.mTheme_setting;
         byte var2 = var1[2];
         boolean var4 = false;
         var5.setValue(var2 >> 0 & 3);
         this.mHome_lighting.setValue(var1[2] >> 5 & 7);
         this.mPower_steering.setValue(var1[3] >> 7 & 1);
         HCheckBoxPreference var6 = this.mMove_warning;
         boolean var3;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mDoor_warning;
         if ((var1[2] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mBlind_detection;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mCar_in_light;
         if ((2 & var1[3]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mTheme_relation;
         var3 = var4;
         if ((var1[3] & 1) != 0) {
            var3 = true;
         }

         var6.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492896);
      this.mFor_warning = this.findSwitchboxPreference("for_warning");
      this.mLane_warning = this.findSwitchboxPreference("lane_warning");
      this.mTheme_setting = this.findSwitchboxPreference("theme_setting");
      this.mHome_lighting = this.findSwitchboxPreference("home_lighting");
      this.mPower_steering = this.findSwitchboxPreference("power_steering");
      this.mMove_warning = this.findHCheckBoxPreference("move_warning");
      this.mDoor_warning = this.findHCheckBoxPreference("door_warning");
      this.mBlind_detection = this.findHCheckBoxPreference("blind_detection");
      this.mCar_in_light = this.findHCheckBoxPreference("car_in_light");
      this.mTheme_relation = this.findHCheckBoxPreference("theme_relation");
      CharSequence[] var6 = new CharSequence[5];
      CharSequence[] var4 = new CharSequence[var6.length];
      String var5 = this.getString(2131296511);
      byte var3 = 0;
      var6[0] = var5;
      var6[1] = "15s";
      var6[2] = "30s";
      var6[3] = "45s";
      var6[4] = "60s";

      int var2;
      for(var2 = 0; var2 < var6.length; ++var2) {
         var4[var2] = Integer.toString(var2);
      }

      this.mHome_lighting.setEntries(var6);
      this.mHome_lighting.setEntryValues(var4);
      var4 = new CharSequence[2];
      var6 = new CharSequence[var4.length];
      var4[0] = this.getString(2131296520);
      var4[1] = this.getString(2131297219);

      for(var2 = var3; var2 < var4.length; ++var2) {
         var6[var2] = Integer.toString(var2);
      }

      this.mPower_steering.setEntries(var4);
      this.mPower_steering.setEntryValues(var6);
      this.RequestCmdkey(65);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      byte var3 = 0;

      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var5;
      try {
         var5 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var7) {
         return true;
      }

      if (var4 == var5) {
         return true;
      } else {
         if (var1 == this.mFor_warning) {
            this.SetCmdkey(6, var4);
         } else if (var1 == this.mLane_warning) {
            this.SetCmdkey(7, var4);
         } else if (var1 == this.mTheme_setting) {
            this.SetCmdkey(8, var4);
         } else if (var1 == this.mHome_lighting) {
            this.SetCmdkey(12, var4);
         } else if (var1 == this.mPower_steering) {
            if (var4 == 0) {
               var3 = 1;
            }

            this.SetCmdkey(13, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 instanceof HCheckBoxPreference) {
         boolean var8 = ((HCheckBoxPreference)var2).mChecked;
         HCheckBoxPreference var9 = this.mMove_warning;
         byte var5 = 0;
         byte var4 = 0;
         byte var7 = 0;
         byte var6 = 0;
         byte var3 = 0;
         if (var2 == var9) {
            if (!var8) {
               var3 = 1;
            }

            this.SetCmdkey(3, var3);
         } else if (var2 == this.mDoor_warning) {
            if (var8) {
               var3 = var5;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(4, var3);
         } else if (var2 == this.mBlind_detection) {
            if (var8) {
               var3 = var4;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(5, var3);
         } else if (var2 == this.mCar_in_light) {
            if (var8) {
               var3 = var7;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(11, var3);
         } else if (var2 == this.mTheme_relation) {
            if (var8) {
               var3 = var6;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(14, var3);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
