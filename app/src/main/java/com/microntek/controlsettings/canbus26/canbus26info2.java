package com.microntek.controlsettings.canbus26;

import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.SwitchPreference;
import java.util.Locale;

public class canbus26info2 extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchPreference engine_mSpeed;
   private SwitchPreference mMileage;
   private SwitchPreference mPrecision_speed;
   private byte[] setData = new byte[20];

   private void updasettings13() {
      byte[] var4 = this.setData;
      byte var3 = var4[0];
      byte var1 = var4[1];
      byte var2 = var4[2];
      SwitchPreference var6 = this.mMileage;
      StringBuilder var5 = new StringBuilder();
      var5.append((var3 << 16 & 16777215) + (var1 << 8 & '\uffff') + (var2 & 255));
      var5.append(" KM");
      var6.setValue("0", var5.toString());
      var4 = this.setData;
      var2 = var4[3];
      var1 = var4[4];
      var6 = this.mPrecision_speed;
      var5 = new StringBuilder();
      var5.append(String.format(Locale.US, " %.1f", (float)((var2 << 8 & '\uffff') + (var1 & 255)) * 0.1F));
      var5.append(" Km/h");
      var6.setValue("0", var5.toString());
      var4 = this.setData;
      var2 = var4[8];
      var1 = var4[9];
      SwitchPreference var7 = this.engine_mSpeed;
      StringBuilder var8 = new StringBuilder();
      var8.append((var2 << 8 & '\uffff') + (var1 & 255));
      var8.append(" RPM");
      var7.setValue("0", var8.toString());
   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      if (var1.length > 1) {
         if (var3 == 19) {
            while(var2 < 10) {
               byte[] var4 = this.setData;
               var3 = var2 + 1;
               var4[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings13();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492900);
      this.mMileage = (SwitchPreference)this.findPreference("mileage");
      SwitchPreference var2 = this.mMileage;
      String var3;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mMileage.getSharedPreferences().getString("timeswitchlight", "0");
         this.mMileage.setValue(var3);
      }

      this.mPrecision_speed = (SwitchPreference)this.findPreference("precision_speed");
      var2 = this.mPrecision_speed;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.mPrecision_speed.getSharedPreferences().getString("timeswitchlight", "0");
         this.mPrecision_speed.setValue(var3);
      }

      this.engine_mSpeed = (SwitchPreference)this.findPreference("engine_speed");
      var2 = this.engine_mSpeed;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
         var3 = this.engine_mSpeed.getSharedPreferences().getString("timeswitchlight", "0");
         this.engine_mSpeed.setValue(var3);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return true;
   }
}
