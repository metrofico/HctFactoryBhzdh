package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus57settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = super.cmd;
      boolean var4 = true;
      if (var2 == 64) {
         this.findSwitchboxPreference("turn_answerback").setValue(var1[2] >> 5 & 7);
         this.findSwitchboxPreference("re_locking").setValue(var1[2] >> 2 & 3);
         this.findSwitchboxPreference("windshield").setValue(var1[3] >> 6 & 3);
         HCheckBoxPreference var5 = this.findHCheckBoxPreference("wipers_washer");
         boolean var3;
         if ((var1[3] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.findHCheckBoxPreference("automatic_wash");
         if ((var1[3] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.findSwitchboxPreference("rear_interval").setValue(var1[3] >> 2 & 3);
         this.findSwitchboxPreference("rear_reverse").setValue(var1[3] >> 1 & 1);
         var5 = this.findHCheckBoxPreference("headlight_auto_off");
         if ((var1[4] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.findSwitchboxPreference("auto_timing").setValue(var1[4] >> 4 & 7);
         this.findSwitchboxPreference("headlight_vehicle").setValue(var1[4] >> 1 & 7);
         this.findSwitchboxPreference("exterior_unlock").setValue(var1[5] >> 6 & 3);
         this.findSwitchboxPreference("interior_timeout").setValue(var1[5] >> 4 & 3);
         this.findSwitchboxPreference("interior_closed").setValue(var1[5] >> 0 & 3);
         this.findSwitchboxPreference("signal_operation").setValue(var1[6] >> 7 & 1);
         this.findSwitchboxPreference("operating").setValue(var1[6] >> 5 & 1);
         this.findSwitchboxPreference("timing_of").setValue(var1[6] >> 3 & 3);
         var5 = this.findHCheckBoxPreference("one_touch");
         if ((64 & var1[6]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.findSwitchboxPreference("doors_unlocked").setValue(var1[6] >> 2 & 1);
         this.findSwitchboxPreference("automatic_unlock").setValue(var1[6] >> 0 & 3);
         this.findSwitchboxPreference("rec_control").setValue(var1[7] >> 7 & 1);
         this.findSwitchboxPreference("airc_control").setValue(var1[7] >> 6 & 1);
         this.findSwitchboxPreference("foot_ratio").setValue(var1[7] >> 4 & 3);
         this.findSwitchboxPreference("face_ratio").setValue(var1[7] >> 2 & 3);
         this.findSwitchboxPreference("auto_mirrors").setValue(var1[8] >> 6 & 3);
         this.findSwitchboxPreference("accessory_power").setValue(var1[8] >> 4 & 3);
         var5 = this.findHCheckBoxPreference("auto_start");
         if ((var1[7] & 2) != 0) {
            var3 = var4;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
      } else if (var2 == 203) {
         this.findSwitchboxPreference("car_type").setValue(var1[2] & 1);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492927);

      for(int var2 = 0; var2 < this.getPreferenceScreen().getPreferenceCount(); ++var2) {
         Preference var3 = this.getPreferenceScreen().getPreference(var2);
         if (var3 instanceof SwitchboxPreference) {
            var3.setOnPreferenceChangeListener(this);
         } else if (var3 instanceof HCheckBoxPreference) {
            var3.setOnPreferenceClickListener(this);
         } else if (var3 instanceof PreferenceScreen) {
            var3.setOnPreferenceClickListener(this);
         }
      }

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
         String var7 = var1.getKey();
         if ("turn_answerback".equals(var7)) {
            this.SetCmdkey(1, var3);
         } else if ("re_locking".equals(var7)) {
            this.SetCmdkey(2, var3);
         } else if ("windshield".equals(var7)) {
            this.SetCmdkey(16, var3);
         } else if ("rear_interval".equals(var7)) {
            this.SetCmdkey(19, var3);
         } else if ("rear_reverse".equals(var7)) {
            this.SetCmdkey(20, var3);
         } else if ("auto_timing".equals(var7)) {
            this.SetCmdkey(33, var3);
         } else if ("headlight_vehicle".equals(var7)) {
            this.SetCmdkey(34, var3);
         } else if ("exterior_unlock".equals(var7)) {
            this.SetCmdkey(35, var3);
         } else if ("interior_timeout".equals(var7)) {
            this.SetCmdkey(36, var3);
         } else if ("interior_closed".equals(var7)) {
            this.SetCmdkey(37, var3);
         } else if ("signal_operation".equals(var7)) {
            this.SetCmdkey(48, var3);
         } else if ("operating".equals(var7)) {
            this.SetCmdkey(50, var3);
         } else if ("timing_of".equals(var7)) {
            this.SetCmdkey(64, var3);
         } else if ("doors_unlocked".equals(var7)) {
            this.SetCmdkey(65, var3);
         } else if ("automatic_unlock".equals(var7)) {
            this.SetCmdkey(66, var3);
         } else if ("rec_control".equals(var7)) {
            this.SetCmdkey(80, var3);
         } else if ("airc_control".equals(var7)) {
            this.SetCmdkey(81, var3);
         } else if ("foot_ratio".equals(var7)) {
            this.SetCmdkey(82, var3);
         } else if ("face_ratio".equals(var7)) {
            this.SetCmdkey(83, var3);
         } else if ("auto_mirrors".equals(var7)) {
            this.SetCmdkey(96, var3);
         } else if ("accessory_power".equals(var7)) {
            this.SetCmdkey(97, var3);
         } else if ("car_type".equals(var7)) {
            this.SendCanBusCmdData2E((byte)-54, new byte[]{(byte)var3, 0}, 2);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      String var10 = var2.getKey();
      if (var2 instanceof HCheckBoxPreference) {
         boolean var8 = ((HCheckBoxPreference)var2).mChecked;
         boolean var9 = "wipers_washer".equals(var10);
         byte var3 = 0;
         byte var7 = 0;
         byte var6 = 0;
         byte var4 = 0;
         byte var5 = 0;
         if (var9) {
            if (var8) {
               var3 = var5;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(17, var3);
         } else if ("automatic_wash".equals(var10)) {
            if (!var8) {
               var3 = 1;
            }

            this.SetCmdkey(18, var3);
         } else if ("headlight_auto_off".equals(var10)) {
            if (var8) {
               var3 = var7;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(32, var3);
         } else if ("one_touch".equals(var10)) {
            if (var8) {
               var3 = var6;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(49, var3);
         } else if ("auto_start".equals(var10)) {
            if (var8) {
               var3 = var4;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(84, var3);
         }
      } else if (var2 instanceof PreferenceScreen && "factory_settings".equals(var10)) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus57settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey(0, 1);
            }
         }, var2.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
