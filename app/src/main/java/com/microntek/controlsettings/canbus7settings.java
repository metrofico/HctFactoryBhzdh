package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings.System;

public class canbus7settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _mFront_shot = 0;
   private int _mFront_speed = 0;
   private int _mMap_view = 0;
   private HCheckBoxPreference mFront_shot;
   private OnSwitchPreference mFront_speed;
   private HCheckBoxPreference mMap_view;

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData2E((byte)-126, new byte[]{var1, var2, var3}, 3);
   }

   private int getSettingsData(String var1) {
      return System.getInt(this.getContentResolver(), var1, 0);
   }

   private void saveSettingsData(String var1, int var2) {
      System.putInt(this.getContentResolver(), var1, var2);
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492937);
      this._mMap_view = this.getSettingsData("mMap_view");
      this._mFront_shot = this.getSettingsData("mFront_shot");
      this._mFront_speed = this.getSettingsData("mFront_speed");
      this.mMap_view = (HCheckBoxPreference)this.findPreference("map_view");
      this.mMap_view.setOnPreferenceClickListener(this);
      HCheckBoxPreference var6 = this.mMap_view;
      int var2 = this._mMap_view;
      boolean var4 = false;
      boolean var3;
      if (var2 == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      var6.setChecked(var3);
      this.mFront_shot = (HCheckBoxPreference)this.findPreference("front_shot");
      this.mFront_shot.setOnPreferenceClickListener(this);
      var6 = this.mFront_shot;
      var3 = var4;
      if (this._mFront_shot == 1) {
         var3 = true;
      }

      var6.setChecked(var3);
      this.mFront_speed = (OnSwitchPreference)this.findPreference("front_speed");
      this.mFront_speed.setOnPreferenceChangeListener(this);
      OnSwitchPreference var7 = this.mFront_speed;
      StringBuilder var5 = new StringBuilder();
      var5.append(this._mFront_speed);
      var5.append(" km/h");
      var7.setValue("0", var5.toString());
   }

   protected void onDestroy() {
      this.saveSettingsData("mMap_view", this._mMap_view);
      this.saveSettingsData("mFront_shot", this._mFront_shot);
      this.saveSettingsData("mFront_speed", this._mFront_speed);
      super.onDestroy();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mFront_speed;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this._mFront_speed;
         if (var4 + var3 < 0 || var4 + var3 > 30 || !this.mFront_speed.getIsDow()) {
            return true;
         }

         this._mFront_speed += var3;
         var5 = this.mFront_speed;
         StringBuilder var6 = new StringBuilder();
         var6.append(this._mFront_speed);
         var6.append(" km/h");
         var5.setValue("0", var6.toString());
         this.SetCmdkey((byte)this._mMap_view, (byte)this._mFront_shot, (byte)this._mFront_speed);
      }

      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var8) {
         return false;
      }

      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var7) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mMap_view;
      if (var1 == var2) {
         if (var2.mChecked) {
            this._mMap_view = 0;
            var2.setChecked(false);
         } else {
            this._mMap_view = 1;
            var2.setChecked(true);
         }

         this.SetCmdkey((byte)this._mMap_view, (byte)this._mFront_shot, (byte)this._mFront_speed);
      } else {
         var2 = this.mFront_shot;
         if (var1 == var2) {
            if (var2.mChecked) {
               this._mFront_shot = 0;
               var2.setChecked(false);
            } else {
               this._mFront_shot = 1;
               var2.setChecked(true);
            }

            this.SetCmdkey((byte)this._mMap_view, (byte)this._mFront_shot, (byte)this._mFront_speed);
         }
      }

      return false;
   }
}
