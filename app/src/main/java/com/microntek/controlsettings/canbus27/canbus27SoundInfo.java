package com.microntek.controlsettings.canbus27;

import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.SwitchPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27SoundInfo extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private SwitchPreference mBeep;
   private SwitchPreference mClack;
   private SwitchPreference mClick;
   private SwitchPreference mClick_clack;
   private SwitchPreference mGong;
   private SwitchPreference mSversion;
   private byte[] setData = new byte[16];
   private String string = "";

   private char ascii2Char(int var1) {
      return (char)var1;
   }

   private String ascii2String(byte[] var1) {
      StringBuffer var3 = new StringBuffer();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var3.append(this.ascii2Char(var1[var2]));
      }

      return var3.toString();
   }

   private void updasettings90() {
      byte[] var2 = this.setData;
      int var1 = var2[1] & 255;
      StringBuilder var3;
      SwitchPreference var4;
      if (var2[0] == 1) {
         var4 = this.mClack;
         var3 = new StringBuilder();
         var3.append(var1);
         var3.append("");
         var4.setValue("0", var3.toString());
      } else if (var2[0] == 2) {
         var4 = this.mClick;
         var3 = new StringBuilder();
         var3.append(var1);
         var3.append("");
         var4.setValue("0", var3.toString());
      } else if (var2[0] == 3) {
         var4 = this.mClick_clack;
         var3 = new StringBuilder();
         var3.append(var1);
         var3.append("");
         var4.setValue("0", var3.toString());
      } else if (var2[0] == 4) {
         SwitchPreference var6 = this.mBeep;
         StringBuilder var5 = new StringBuilder();
         var5.append(var1);
         var5.append("");
         var6.setValue("0", var5.toString());
      } else if (var2[0] == 5) {
         var4 = this.mGong;
         var3 = new StringBuilder();
         var3.append(var1);
         var3.append("");
         var4.setValue("0", var3.toString());
      }

   }

   private void updasettingsf0() {
      this.string = this.ascii2String(this.setData).toString().trim();
      if (this.string.equals("")) {
         this.string = this.getString(2131296818);
      }

      SwitchPreference var2 = this.mSversion;
      StringBuilder var1 = new StringBuilder();
      var1.append(this.string);
      var1.append("");
      var2.setValue("0", var1.toString());
   }

   protected void ProcessData(byte[] var1) {
      int var3 = 0;
      int var2 = 0;
      byte var5 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         byte[] var6;
         if (var5 != -112) {
            if (var5 == -16) {
               for(var2 = var3; var2 < var4 - 1; var2 = var3) {
                  var6 = this.setData;
                  var3 = var2 + 1;
                  var6[var2] = var1[var3];
               }

               this.updasettingsf0();
            }
         } else {
            while(var2 < var4 - 1) {
               var6 = this.setData;
               var3 = var2 + 1;
               var6[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings90();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492911);
      this.mSversion = (SwitchPreference)this.findPreference("versionlm");
      SwitchPreference var2 = this.mSversion;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mClack = (SwitchPreference)this.findPreference("clack");
      var2 = this.mClack;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mClick = (SwitchPreference)this.findPreference("click");
      var2 = this.mClick;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mClick_clack = (SwitchPreference)this.findPreference("click_clack");
      var2 = this.mClick_clack;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mBeep = (SwitchPreference)this.findPreference("beep");
      var2 = this.mBeep;
      if (var2 != null) {
         var2.setValue("0");
      }

      this.mGong = (SwitchPreference)this.findPreference("gong");
      var2 = this.mGong;
      if (var2 != null) {
         var2.setValue("0");
      }

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

      return var4 == var3;
   }

   public boolean onPreferenceClick(Preference var1) {
      return false;
   }
}
