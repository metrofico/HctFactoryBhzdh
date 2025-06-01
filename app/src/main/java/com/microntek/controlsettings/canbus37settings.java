package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus37settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean isGetData = false;
   private HCheckBoxPreference mBlind_spot;
   private HCheckBoxPreference mLane_departure;
   private HCheckBoxPreference mMoving_object;

   private void SetCmdkey(byte var1) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1, 1}, 2);
   }

   private void SetEnabled(boolean var1) {
      this.mLane_departure.setEnabled(var1);
      this.mBlind_spot.setEnabled(var1);
      this.mMoving_object.setEnabled(var1);
   }

   protected void ProcessData(byte[] var1) {
      boolean var3 = false;
      if (var1[0] == -107) {
         this.isGetData = true;
         HCheckBoxPreference var4 = this.mLane_departure;
         boolean var2;
         if ((var1[2] & 128) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mBlind_spot;
         if ((var1[2] & 64) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mMoving_object;
         var2 = var3;
         if ((var1[2] & 32) != 0) {
            var2 = true;
         }

         var4.setChecked(var2);
         this.SetEnabled(true);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492916);
      this.mLane_departure = (HCheckBoxPreference)this.findPreference("lane_departure");
      this.mLane_departure.setOnPreferenceClickListener(this);
      this.mBlind_spot = (HCheckBoxPreference)this.findPreference("blind_spot");
      this.mBlind_spot.setOnPreferenceClickListener(this);
      this.mMoving_object = (HCheckBoxPreference)this.findPreference("moving_object");
      this.mMoving_object.setOnPreferenceClickListener(this);
      this.SetEnabled(false);
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
      if (var1 == this.mLane_departure) {
         this.SetCmdkey((byte)82);
      } else if (var1 == this.mBlind_spot) {
         this.SetCmdkey((byte)83);
      } else if (var1 == this.mMoving_object) {
         this.SetCmdkey((byte)81);
      }

      return false;
   }
}
