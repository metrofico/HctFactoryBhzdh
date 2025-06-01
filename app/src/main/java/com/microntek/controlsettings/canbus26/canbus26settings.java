package com.microntek.controlsettings.canbus26;

import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.SwitchPreference;

public class canbus26settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchPreference mSide_parking;
   private SwitchPreference mStorage_in_the_car;
   private SwitchPreference mSversion;
   private SwitchPreference sRadar_mute;
   private byte[] setData = new byte[20];

   private void updasettings() {
      String var2 = this.ascii2String(this.setData).toString().trim();
      String var1 = var2;
      if (var2.equals("")) {
         var1 = this.getString(2131296818);
      }

      SwitchPreference var3 = this.mSversion;
      StringBuilder var4 = new StringBuilder();
      var4.append(var1);
      var4.append("");
      var3.setValue("0", var4.toString());
   }

   private void updasettings47() {
      if ((byte)(this.setData[0] & 128) == -128) {
         this.mSide_parking.setValue("1");
      } else {
         this.mSide_parking.setValue("0");
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.mStorage_in_the_car.setValue("1");
      } else {
         this.mStorage_in_the_car.setValue("0");
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.sRadar_mute.setValue("1");
      } else {
         this.sRadar_mute.setValue("0");
      }

   }

   protected void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      if (var1.length > 1) {
         if (var4 == -16) {
            int var3;
            for(int var2 = 0; var2 < 17; var2 = var3) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
            }

            this.updasettings();
         }

         if (var4 == 71) {
            this.setData[0] = var1[1];
            this.updasettings47();
         }

      }
   }

   public char ascii2Char(int var1) {
      return (char)var1;
   }

   public String ascii2String(byte[] var1) {
      StringBuffer var3 = new StringBuffer();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3.append(this.ascii2Char(var1[var2]));
      }

      return var3.toString();
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492897);
      this.mSide_parking = (SwitchPreference)this.findPreference("side_parking");
      SwitchPreference var2 = this.mSide_parking;
      String var3;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mSide_parking.getSharedPreferences().getString("timeswitchlight", "0");
         this.mSide_parking.setValue(var3);
      }

      this.getPreferenceScreen().removePreference(this.mSide_parking);
      this.mStorage_in_the_car = (SwitchPreference)this.findPreference("storage_in_the_car");
      var2 = this.mStorage_in_the_car;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mStorage_in_the_car.getSharedPreferences().getString("timeswitchlight", "0");
         this.mStorage_in_the_car.setValue(var3);
      }

      this.getPreferenceScreen().removePreference(this.mStorage_in_the_car);
      this.sRadar_mute = (SwitchPreference)this.findPreference("radar_mute");
      var2 = this.sRadar_mute;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.sRadar_mute.getSharedPreferences().getString("timeswitchlight", "0");
         this.sRadar_mute.setValue(var3);
      }

      this.getPreferenceScreen().removePreference(this.sRadar_mute);
      this.mSversion = (SwitchPreference)this.findPreference("version1");
      var2 = this.mSversion;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mSversion.getSharedPreferences().getString("timeswitchlight", "0");
         this.mSversion.setValue(var3);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return true;
   }
}
