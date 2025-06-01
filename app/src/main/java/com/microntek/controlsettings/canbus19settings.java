package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus19settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private SwitchboxPreference mFrontradar;
   private HCheckBoxPreference mRadardisplay;
   private SwitchboxPreference mRadarvol;
   private SwitchboxPreference mRearradar;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-111, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      byte var2 = var1[0];
      if (var1.length > 2) {
         if (var2 == 37) {
            if (var1[0] < 0) {
               this.mRadardisplay.setChecked(true);
            } else {
               this.mRadardisplay.setChecked(false);
            }

            var2 = var1[0];
            SwitchboxPreference var3 = this.mRadarvol;
            StringBuilder var4 = new StringBuilder();
            var4.append("");
            var4.append(var2 & 7);
            var3.setValue(var4.toString());
            var2 = var1[0];
            SwitchboxPreference var7 = this.mFrontradar;
            StringBuilder var6 = new StringBuilder();
            var6.append("");
            var6.append((var2 & 15) >> 2);
            var7.setValue(var6.toString());
            var2 = var1[0];
            SwitchboxPreference var5 = this.mRearradar;
            var6 = new StringBuilder();
            var6.append("");
            var6.append(var2 & 3);
            var5.setValue(var6.toString());
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492892);
      this.mFrontradar = (SwitchboxPreference)this.findPreference("frontradar");
      this.mRearradar = (SwitchboxPreference)this.findPreference("rearradar");
      this.mRadarvol = (SwitchboxPreference)this.findPreference("radarvol");
      this.mRadardisplay = (HCheckBoxPreference)this.findPreference("radardisplay");
      HCheckBoxPreference var2 = this.mRadardisplay;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      SwitchboxPreference var3 = this.mFrontradar;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mRearradar;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mRadarvol;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.SetCmdkey((byte)4, (byte)37);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var3;
      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var5) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mFrontradar) {
            this.SetCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mRearradar) {
            this.SetCmdkey((byte)4, (byte)var4);
         } else if (var1 == this.mRadarvol) {
            this.SetCmdkey((byte)1, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mRadardisplay;
      if (var1 == var2) {
         if (var2.isChecked()) {
            this.SetCmdkey((byte)2, (byte)1);
         } else {
            this.SetCmdkey((byte)2, (byte)0);
         }
      }

      return false;
   }
}
