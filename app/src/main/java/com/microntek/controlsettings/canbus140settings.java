package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus140settings extends BasePreferenceActivity {
   private SwitchboxPreference mAuto_door;
   private SwitchboxPreference mAuto_unlock;
   private SwitchboxPreference mDefogger;
   private SwitchboxPreference mInterior;
   private HCheckBoxPreference mRap_func;
   private HCheckBoxPreference mRedar;
   private SwitchboxPreference mSecurity_lock;
   private HCheckBoxPreference mTouch_changer;
   private HCheckBoxPreference mWarning_flashe;
   private SwitchboxPreference mWheel_type;

   private void SetCmdkey(int var1, int var2) {
      byte[] var3 = new byte[]{(byte)var1, (byte)var2, 0};
      this.SendCanBusCmdData2E((byte)-58, var3, 3);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = super.cmd;
      boolean var4 = false;
      boolean var5 = false;
      boolean var3;
      HCheckBoxPreference var6;
      if (var2 == 64) {
         this.mInterior.setValue(var1[2] >> 6 & 3);
         this.mDefogger.setValue(var1[2] >> 5 & 1);
         this.mSecurity_lock.setValue(var1[2] >> 0 & 7);
         this.mAuto_door.setValue(var1[3] >> 6 & 3);
         this.mAuto_unlock.setValue(var1[3] >> 4 & 3);
         this.mWheel_type.setValue(var1[3] >> 0 & 1);
         var6 = this.mTouch_changer;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mWarning_flashe;
         var3 = var5;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         }

         var6.setChecked(var3);
      } else if (var2 == 37) {
         var6 = this.mRedar;
         if ((var1[2] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mRap_func;
         var3 = var4;
         if ((var1[2] & 1) != 0) {
            var3 = true;
         }

         var6.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492881);
      CharSequence[] var4 = new CharSequence[4];
      CharSequence[] var6 = new CharSequence[var4.length];
      String var5 = this.getString(2131296511);
      byte var3 = 0;
      var4[0] = var5;
      var4[1] = "10s";
      var4[2] = "20s";
      var4[3] = "30s";

      int var2;
      for(var2 = 0; var2 < var4.length; ++var2) {
         StringBuilder var8 = new StringBuilder();
         var8.append("");
         var8.append(var2);
         var6[var2] = var8.toString();
      }

      this.mInterior = this.findSwitchboxPreference("interior");
      this.mInterior.setEntryValues(var6);
      this.mInterior.setEntries(var4);
      this.mDefogger = this.findSwitchboxPreference("defogger");
      this.mSecurity_lock = this.findSwitchboxPreference("security_lock");
      CharSequence[] var9 = new CharSequence[6];
      var4 = new CharSequence[var9.length];
      var9[0] = this.getString(2131296511);
      var9[1] = "20s";
      var9[2] = "30s";
      var9[3] = "40s";
      var9[4] = "50s";
      var9[5] = "60s";

      for(var2 = var3; var2 < var9.length; ++var2) {
         StringBuilder var7 = new StringBuilder();
         var7.append("");
         var7.append(var2);
         var4[var2] = var7.toString();
      }

      this.mSecurity_lock.setEntryValues(var4);
      this.mSecurity_lock.setEntries(var9);
      this.mAuto_door = this.findSwitchboxPreference("auto_door");
      this.mAuto_unlock = this.findSwitchboxPreference("auto_unlock");
      this.mWheel_type = this.findSwitchboxPreference("wheel_type");
      this.mRedar = this.findHCheckBoxPreference("redar");
      this.mRap_func = this.findHCheckBoxPreference("rap_func");
      this.mTouch_changer = this.findHCheckBoxPreference("touch_changer");
      this.mWarning_flashe = this.findHCheckBoxPreference("warning_flashe");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{64, 0}, 2);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{37, 0}, 2);
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
         if (var1 == this.mInterior) {
            this.SetCmdkey(3, var4);
         } else if (var1 == this.mDefogger) {
            this.SetCmdkey(4, var4);
         } else if (var1 == this.mSecurity_lock) {
            this.SetCmdkey(7, var4);
         } else if (var1 == this.mAuto_door) {
            this.SetCmdkey(8, var4);
         } else if (var1 == this.mAuto_unlock) {
            this.SetCmdkey(9, var4);
         } else if (var1 == this.mWheel_type) {
            this.SetCmdkey(10, var4);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 instanceof HCheckBoxPreference) {
         boolean var7 = ((HCheckBoxPreference)var2).mChecked;
         HCheckBoxPreference var8 = this.mRedar;
         byte var6 = 0;
         byte var3 = 0;
         byte var5 = 0;
         byte var4 = 0;
         if (var2 == var8) {
            if (var7) {
               var3 = var4;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(1, var3);
         } else if (var2 == this.mRap_func) {
            if (var7) {
               var3 = var6;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(2, var3);
         } else if (var2 == this.mTouch_changer) {
            if (!var7) {
               var3 = 1;
            }

            this.SetCmdkey(5, var3);
         } else if (var2 == this.mWarning_flashe) {
            if (var7) {
               var3 = var5;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(6, var3);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
