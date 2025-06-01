package com.microntek.controlsettings.canbus27;

import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27VehiclePanel extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mGl;
   private HCheckBoxPreference mKeluzi;
   private HCheckBoxPreference mOkola;
   private byte[] setData = new byte[16];
   private String string = "";

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)42, new byte[]{var1, var2}, 2);
   }

   private void updasettings623() {
      if (this.setData[0] == 1) {
         this.mKeluzi.setChecked(true);
      } else {
         this.mKeluzi.setChecked(false);
      }

      if (this.setData[0] == 2) {
         this.mGl.setChecked(true);
      } else {
         this.mGl.setChecked(false);
      }

      if (this.setData[0] == 5) {
         this.mOkola.setChecked(true);
      } else {
         this.mOkola.setChecked(false);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         if (var3 == 35) {
            while(var2 < var4 - 1) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings623();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492912);
      this.mKeluzi = (HCheckBoxPreference)this.findPreference("keluzi");
      HCheckBoxPreference var2 = this.mKeluzi;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mGl = (HCheckBoxPreference)this.findPreference("gl");
      var2 = this.mGl;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mOkola = (HCheckBoxPreference)this.findPreference("okola");
      var2 = this.mOkola;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

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

      return var3 == var4;
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mKeluzi) {
         this.RequestCmdkey((byte)1, (byte)0);
      } else if (var1 == this.mGl) {
         this.RequestCmdkey((byte)2, (byte)0);
      } else if (var1 == this.mOkola) {
         this.RequestCmdkey((byte)5, (byte)0);
      } else {
         this.RequestCmdkey((byte)0, (byte)0);
      }

      return false;
   }
}
