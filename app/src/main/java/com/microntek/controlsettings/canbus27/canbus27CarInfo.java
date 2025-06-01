package com.microntek.controlsettings.canbus27;

import android.os.Bundle;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchPreference;

public class canbus27CarInfo extends BasePreferenceActivity {
   private HCheckBoxPreference mAcc;
   private HCheckBoxPreference mAnjixing;
   private SwitchPreference mFuel_con;
   private SwitchPreference mGear;
   private SwitchPreference mIg_conditions;
   private HCheckBoxPreference mIll;
   private HCheckBoxPreference mIs_key;
   private HCheckBoxPreference mKey_in;
   private HCheckBoxPreference mNo_electricity;
   private HCheckBoxPreference mNo_oil;
   private HCheckBoxPreference mPark;
   private HCheckBoxPreference mRev;
   private SwitchPreference mSpeed;
   private byte[] setData = new byte[16];
   private String string = "";

   private void updasettings11() {
      byte var1 = this.setData[1];
      SwitchPreference var3 = this.mSpeed;
      StringBuilder var2 = new StringBuilder();
      var2.append(var1 & 255);
      var2.append("Km/h");
      var3.setValue("0", var2.toString());
      if ((byte)(this.setData[0] & 128) == -128) {
         this.mAnjixing.setChecked(true);
      } else {
         this.mAnjixing.setChecked(false);
      }

      if ((byte)(this.setData[0] & 16) == 16) {
         this.mKey_in.setChecked(true);
      } else {
         this.mKey_in.setChecked(false);
      }

      if ((byte)(this.setData[0] & 8) == 8) {
         this.mPark.setChecked(true);
      } else {
         this.mPark.setChecked(false);
      }

      if ((byte)(this.setData[0] & 4) == 4) {
         this.mRev.setChecked(true);
      } else {
         this.mRev.setChecked(false);
      }

      if ((byte)(this.setData[0] & 2) == 2) {
         this.mIll.setChecked(true);
      } else {
         this.mIll.setChecked(false);
      }

      if ((byte)(this.setData[0] & 1) == 1) {
         this.mAcc.setChecked(true);
      } else {
         this.mAcc.setChecked(false);
      }

   }

   private void updasettings12() {
      String var3;
      if (this.setData[0] == -1) {
         var3 = "4";
      } else {
         var3 = "0";
      }

      if (this.setData[0] == 3) {
         var3 = "3";
      }

      if (this.setData[0] == 2) {
         var3 = "2";
      }

      if (this.setData[0] == 1) {
         var3 = "1";
      }

      this.mIg_conditions.setValue(var3);
      if (this.setData[1] == 4) {
         var3 = "4";
      }

      if (this.setData[1] == 3) {
         var3 = "3";
      }

      if (this.setData[1] == 2) {
         var3 = "2";
      }

      if (this.setData[1] == 1) {
         var3 = "1";
      }

      if (this.setData[1] == 0) {
         var3 = "0";
      }

      this.mGear.setValue(var3);
      byte[] var5 = this.setData;
      byte var1 = var5[3];
      byte var2 = var5[4];
      SwitchPreference var6 = this.mFuel_con;
      StringBuilder var4 = new StringBuilder();
      var4.append((double)(var1 & 255) + (double)(var2 & 255) * 0.01);
      var4.append("L/100km");
      var6.setValue("0", var4.toString());
      if (this.setData[5] == 1) {
         this.mIs_key.setChecked(true);
      } else {
         this.mIs_key.setChecked(false);
      }

      if ((byte)(this.setData[6] & 128) == -128) {
         this.mNo_oil.setChecked(true);
      } else {
         this.mNo_oil.setChecked(false);
      }

      if ((byte)(this.setData[6] & 64) == 64) {
         this.mNo_electricity.setChecked(true);
      } else {
         this.mNo_electricity.setChecked(false);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = 0;
      byte var5 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         byte[] var6;
         if (var5 == 17) {
            for(var2 = var3; var2 < var4 - 1; var2 = var3) {
               var6 = this.setData;
               var3 = var2 + 1;
               var6[var2] = var1[var3];
            }

            this.updasettings11();
         } else if (var5 == 18) {
            while(var2 < var4 - 1) {
               var6 = this.setData;
               var3 = var2 + 1;
               var6[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings12();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492904);
      this.mIs_key = (HCheckBoxPreference)this.findPreference("is_key");
      this.mNo_oil = (HCheckBoxPreference)this.findPreference("no_oil");
      this.mNo_electricity = (HCheckBoxPreference)this.findPreference("no_electricity");
      this.mAnjixing = (HCheckBoxPreference)this.findPreference("anjixing");
      this.mKey_in = (HCheckBoxPreference)this.findPreference("key_in");
      this.mPark = (HCheckBoxPreference)this.findPreference("park");
      this.mRev = (HCheckBoxPreference)this.findPreference("rev");
      this.mIll = (HCheckBoxPreference)this.findPreference("ill");
      this.mAcc = (HCheckBoxPreference)this.findPreference("acc");
      this.mIg_conditions = (SwitchPreference)this.findPreference("ig_conditions");
      SwitchPreference var2 = this.mIg_conditions;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mGear = (SwitchPreference)this.findPreference("gear");
      var2 = this.mGear;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mFuel_con = (SwitchPreference)this.findPreference("fuel_con");
      var2 = this.mFuel_con;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mSpeed = (SwitchPreference)this.findPreference("speed");
      var2 = this.mSpeed;
      if (var2 != null) {
         var2.setValue("0");
      }

   }
}
