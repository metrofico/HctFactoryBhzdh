package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus158settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchboxPreference mAvm_speed;
   private HCheckBoxPreference mForti_vol;
   private SwitchboxPreference mLightingime;
   private SwitchboxPreference mLightingime_wai;
   private HCheckBoxPreference mPosit_light;
   private SwitchboxPreference mPrompt_sound;
   private SwitchboxPreference mSpeed_lock;
   private HCheckBoxPreference mTurning_lighting;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-89, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 64) {
         this.mPrompt_sound.setValue(var1[2] & 3);
         this.mLightingime_wai.setValue(var1[3] & 7);
         this.mLightingime.setValue(var1[4] & 7);
         this.mSpeed_lock.setValue(var1[5] & 3);
         HCheckBoxPreference var5 = this.mForti_vol;
         byte var2 = var1[6];
         boolean var4 = true;
         boolean var3;
         if ((var2 & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mPosit_light;
         if ((var1[7] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         if (super.length >= 8) {
            var5 = this.mTurning_lighting;
            if ((var1[8] & 32) != 0) {
               var3 = var4;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            this.mAvm_speed.setValue(var1[9] & 3);
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492888);
      this.mPrompt_sound = this.findSwitchboxPreference("prompt_sound");
      this.mLightingime_wai = this.findSwitchboxPreference("lightingime_wai");
      this.mLightingime = this.findSwitchboxPreference("lightingime");
      this.mSpeed_lock = this.findSwitchboxPreference("speed_lock");
      this.mAvm_speed = this.findSwitchboxPreference("avm_speed");
      this.mForti_vol = this.findHCheckBoxPreference("forti_vol");
      this.mPosit_light = this.findHCheckBoxPreference("posit_light");
      this.mTurning_lighting = this.findHCheckBoxPreference("turning_lighting");
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
         if (var1 == this.mPrompt_sound) {
            this.SetCmdkey(1, var3);
         } else if (var1 == this.mLightingime_wai) {
            this.SetCmdkey(2, var3);
         } else if (var1 == this.mLightingime) {
            this.SetCmdkey(3, var3);
         } else if (var1 == this.mSpeed_lock) {
            this.SetCmdkey(4, var3);
         } else if (var1 == this.mAvm_speed) {
            this.SetCmdkey(8, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var6 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var7 = this.mForti_vol;
      byte var3 = 0;
      byte var4 = 0;
      byte var5 = 0;
      if (var2 == var7) {
         if (var6) {
            var3 = var5;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(5, var3);
      } else if (var2 == this.mPosit_light) {
         if (!var6) {
            var3 = 1;
         }

         this.SetCmdkey(6, var3);
      } else if (var2 == this.mTurning_lighting) {
         if (var6) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(7, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
