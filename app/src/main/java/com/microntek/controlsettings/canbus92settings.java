package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus92settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int data = 0;
   private HCheckBoxPreference mInformation_tone;
   private HCheckBoxPreference mLights;
   private SwitchboxPreference mTurn_lights_set;
   private SwitchboxPreference mUnit;
   private HCheckBoxPreference mWarning_tone;

   private void SetCmdkey(byte var1) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1}, 1);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = var1[0];
      var2 = var1.length;
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492949);
      boolean var3 = false;
      this.data = this.getInt("canbus92settings", 0);
      this.mUnit = this.findSwitchboxPreference("unit");
      SwitchboxPreference var5 = this.mUnit;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(this.data >> 7 & 1);
      var5.setValue(var4.toString());
      this.mTurn_lights_set = this.findSwitchboxPreference("turn_lights_set");
      SwitchboxPreference var8 = this.mTurn_lights_set;
      StringBuilder var6 = new StringBuilder();
      var6.append("");
      var6.append(this.data >> 6 & 1);
      var8.setValue(var6.toString());
      this.mWarning_tone = this.findHCheckBoxPreference("warning_tone");
      HCheckBoxPreference var7 = this.mWarning_tone;
      boolean var2;
      if ((this.data & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var7.setChecked(var2);
      this.mInformation_tone = this.findHCheckBoxPreference("information_tone");
      var7 = this.mInformation_tone;
      if ((this.data & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var7.setChecked(var2);
      this.mLights = this.findHCheckBoxPreference("lights");
      var7 = this.mLights;
      var2 = var3;
      if ((this.data & 8) != 0) {
         var2 = true;
      }

      var7.setChecked(var2);
      this.SetCmdkey((byte)this.data);
   }

   protected void onPause() {
      super.onPause();
      this.putInt("canbus92settings", this.data);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3;
      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var4;
      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var5) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mUnit) {
            this.data ^= 128;
            this.SetCmdkey((byte)this.data);
            SwitchboxPreference var9 = this.mUnit;
            StringBuilder var7 = new StringBuilder();
            var7.append("");
            var7.append(var3);
            var9.setValue(var7.toString());
         } else if (var1 == this.mTurn_lights_set) {
            this.data ^= 64;
            this.SetCmdkey((byte)this.data);
            SwitchboxPreference var8 = this.mTurn_lights_set;
            StringBuilder var10 = new StringBuilder();
            var10.append("");
            var10.append(var3);
            var8.setValue(var10.toString());
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var5 = this.mWarning_tone;
      boolean var4 = true;
      boolean var3 = true;
      boolean var2 = true;
      if (var1 == var5) {
         this.data ^= 32;
         if (var5.mChecked) {
            var2 = false;
         }

         var5.setChecked(var2);
         this.SetCmdkey((byte)this.data);
      } else {
         var5 = this.mInformation_tone;
         if (var1 == var5) {
            this.data ^= 16;
            if (!var5.mChecked) {
               var2 = var4;
            } else {
               var2 = false;
            }

            var5.setChecked(var2);
            this.SetCmdkey((byte)this.data);
         } else {
            var5 = this.mLights;
            if (var1 == var5) {
               this.data ^= 8;
               if (!var5.mChecked) {
                  var2 = var3;
               } else {
                  var2 = false;
               }

               var5.setChecked(var2);
               this.SetCmdkey((byte)this.data);
            }
         }
      }

      return false;
   }
}
