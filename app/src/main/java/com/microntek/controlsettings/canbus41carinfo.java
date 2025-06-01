package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus41carinfo extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchPreference mAveragespeed;
   private SwitchPreference mOnstar;
   private byte[] setData = new byte[20];

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-112, new byte[]{var1, var2}, 2);
   }

   private void updasettings16() {
      byte[] var2 = this.setData;
      int var1 = ((var2[1] & 255) * 256 + (var2[0] & 255)) / 16;
      SwitchPreference var3 = this.mAveragespeed;
      StringBuilder var4 = new StringBuilder();
      var4.append(var1);
      var4.append(" KM/H");
      var3.setValue("0", var4.toString());
   }

   private void updasettings39() {
      byte[] var1 = this.setData;
      if (var1[0] == 1) {
         this.mOnstar.setValue("1");
      } else if (var1[0] == 0) {
         this.mOnstar.setValue("0");
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      if (var1.length > 1) {
         if (var3 == 57) {
            this.setData[0] = var1[1];
            this.updasettings39();
         }

         if (var3 == 22) {
            while(var2 < 2) {
               byte[] var4 = this.setData;
               var3 = var2 + 1;
               var4[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings16();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492918);
      this.mOnstar = (SwitchPreference)this.findPreference("onstar");
      SwitchPreference var2 = this.mOnstar;
      if (var2 != null) {
         var2.setLine(1);
         this.mOnstar.setSelectable(false);
      }

      this.mAveragespeed = (SwitchPreference)this.findPreference("averagespeed");
      var2 = this.mAveragespeed;
      if (var2 != null) {
         var2.setLine(1);
         this.mAveragespeed.setSelectable(false);
      }

      this.RequestCmdkey((byte)57, (byte)0);
      this.RequestCmdkey((byte)22, (byte)0);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return true;
   }
}
