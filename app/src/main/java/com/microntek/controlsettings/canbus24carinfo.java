package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import java.util.Locale;

public class canbus24carinfo extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchPreference mAveragefuel;
   private SwitchPreference mAveragespeed;
   private SwitchPreference mMileage;
   private byte[] setData = new byte[20];
   private int unit_Averagefuel = 0;
   private int unit_Mileage = 0;

   private void updasettings3() {
      byte[] var3 = this.setData;
      int var1 = (var3[2] & 255) << 8;
      int var2 = var3[3] & 255;
      StringBuilder var4;
      SwitchPreference var10;
      if (this.unit_Mileage == 0) {
         SwitchPreference var8 = this.mMileage;
         var4 = new StringBuilder();
         var4.append(String.format(Locale.US, " %.0f", (float)(var1 * 256 + var2) * 1.0F));
         var4.append(" km");
         var8.setValue("0", var4.toString());
      } else {
         var10 = this.mMileage;
         StringBuilder var9 = new StringBuilder();
         var9.append(String.format(Locale.US, " %.0f", (float)(var1 * 256 + var2) * 1.0F));
         var9.append(" mls");
         var10.setValue("0", var9.toString());
      }

      var3 = this.setData;
      byte var6 = var3[4];
      byte var7 = var3[5];
      String var11 = String.format(Locale.US, " %.1f", (double)((float)(((var6 & 255) << 8) * 256 + (var7 & 255))) * 0.1);
      StringBuilder var5;
      if (this.unit_Mileage == 0) {
         var10 = this.mAveragespeed;
         var5 = new StringBuilder();
         var5.append(var11);
         var5.append(" km/h");
         var10.setValue("0", var5.toString());
      } else {
         var10 = this.mAveragespeed;
         var5 = new StringBuilder();
         var5.append(var11);
         var5.append(" mls/h");
         var10.setValue("0", var5.toString());
      }

      var3 = this.setData;
      var7 = var3[6];
      var6 = var3[7];
      var11 = String.format(Locale.US, " %.1f", (double)((float)(((var7 & 255) << 8) * 256 + (var6 & 255))) * 0.1);
      var1 = this.unit_Averagefuel;
      SwitchPreference var12;
      if (var1 == 3) {
         var12 = this.mAveragefuel;
         var4 = new StringBuilder();
         var4.append(var11);
         var4.append(" km/l");
         var12.setValue("0", var4.toString());
      } else if (var1 == 2) {
         var12 = this.mAveragefuel;
         var4 = new StringBuilder();
         var4.append(var11);
         var4.append(" mpg(UK)");
         var12.setValue("0", var4.toString());
      } else if (var1 == 1) {
         var10 = this.mAveragefuel;
         var5 = new StringBuilder();
         var5.append(var11);
         var5.append(" mpg(US)");
         var10.setValue("0", var5.toString());
      } else {
         var10 = this.mAveragefuel;
         var5 = new StringBuilder();
         var5.append(var11);
         var5.append(" l/100km");
         var10.setValue("0", var5.toString());
      }

   }

   private void updasettings4() {
      byte[] var1 = this.setData;
      this.unit_Mileage = var1[0];
      this.unit_Averagefuel = var1[2];
   }

   protected void ProcessData(byte[] var1) {
      int var3 = 0;
      byte var5 = var1[0];
      if (var1.length > 2) {
         int var2;
         byte[] var6;
         if (var5 == 3) {
            int var4;
            for(var2 = 0; var2 < 8; var2 = var4) {
               var6 = this.setData;
               var4 = var2 + 1;
               var6[var2] = var1[var4];
            }

            this.updasettings3();
         }

         if (var5 == 4) {
            for(var2 = var3; var2 < 5; var2 = var3) {
               var6 = this.setData;
               var3 = var2 + 1;
               var6[var2] = var1[var3];
            }

            this.updasettings4();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492895);
      this.mMileage = (SwitchPreference)this.findPreference("mileage");
      SwitchPreference var2 = this.mMileage;
      if (var2 != null) {
         var2.setLine(1);
         this.mMileage.setSelectable(false);
      }

      this.mAveragespeed = (SwitchPreference)this.findPreference("averagespeed");
      var2 = this.mAveragespeed;
      if (var2 != null) {
         var2.setLine(1);
         this.mAveragespeed.setSelectable(false);
      }

      this.mAveragefuel = (SwitchPreference)this.findPreference("averagefuel");
      var2 = this.mAveragefuel;
      if (var2 != null) {
         var2.setLine(1);
         this.mAveragefuel.setSelectable(false);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return true;
   }
}
