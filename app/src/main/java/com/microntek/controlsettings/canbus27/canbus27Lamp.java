package com.microntek.controlsettings.canbus27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27Lamp extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean dis_mHeadlight;
   private boolean dis_mLights;
   private boolean isData = true;
   private boolean isDisplay;
   private boolean ismLights;
   private SwitchboxPreference mHeadlight;
   private HCheckBoxPreference mLights;
   private SwitchPreference mSversion;
   private byte[] setData = new byte[16];
   private SharedPreferences sp;
   private String string = "";

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)108, new byte[]{var1, var2}, 2);
   }

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

   private void updasettings67() {
      this.isData = false;
      if ((byte)(this.setData[0] & 128) == -128) {
         this.dis_mLights = true;
         this.getPreferenceScreen().addItemFromInflater(this.mHeadlight);
         byte[] var1 = this.setData;
         if ((byte)(var1[1] & 96) == 96) {
            this.mHeadlight.setValue("3");
         } else if ((byte)(var1[1] & 64) == 64) {
            this.mHeadlight.setValue("2");
         } else if ((byte)(var1[1] & 32) == 32) {
            this.mHeadlight.setValue("1");
         } else {
            this.mHeadlight.setValue("0");
         }
      } else {
         this.dis_mLights = false;
         this.getPreferenceScreen().removePreference(this.mHeadlight);
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.dis_mHeadlight = true;
         this.getPreferenceScreen().addItemFromInflater(this.mLights);
         if ((byte)(this.setData[1] & 128) == -128) {
            this.ismLights = true;
            this.mLights.setChecked(true);
         } else {
            this.ismLights = false;
            this.mLights.setChecked(false);
         }
      } else {
         this.dis_mHeadlight = false;
         this.getPreferenceScreen().removePreference(this.mLights);
      }

   }

   private void updasettingsf0() {
      this.string = this.ascii2String(this.setData).toString().trim();
      if (this.string.equals("")) {
         this.string = this.getString(2131296818);
      }

      SwitchPreference var1 = this.mSversion;
      StringBuilder var2 = new StringBuilder();
      var2.append(this.string);
      var2.append("");
      var1.setValue("0", var2.toString());
   }

   protected void ProcessData(byte[] var1) {
      int var3 = 0;
      int var2 = 0;
      byte var5 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         byte[] var6;
         if (var5 != 103) {
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

            this.updasettings67();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492909);
      this.mLights = (HCheckBoxPreference)this.findPreference("lights");
      HCheckBoxPreference var2 = this.mLights;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mHeadlight = (SwitchboxPreference)this.findPreference("headlight");
      SwitchboxPreference var3 = this.mHeadlight;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mSversion = (SwitchPreference)this.findPreference("versionlm");
      SwitchPreference var4 = this.mSversion;
      if (var4 != null) {
         var4.setValue("0");
      }

      this.ismLights = this.mLights.isChecked();
      this.getPreferenceScreen().removePreference(this.mSversion);
      this.sp = this.getSharedPreferences("spcanbus27", 0);
      this.isDisplay = this.sp.getBoolean("dis_mLights", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mLights);
      }

      this.isDisplay = this.sp.getBoolean("dis_mHeadlight", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mHeadlight);
      }

      Log.i("canbus27lamp", "syncOn");
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

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mHeadlight) {
            this.RequestCmdkey((byte)2, (byte)var3);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mLights) {
         if (this.ismLights) {
            this.RequestCmdkey((byte)1, (byte)0);
         } else {
            this.RequestCmdkey((byte)1, (byte)1);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
      if (!this.isData) {
         this.sp.edit().putBoolean("dis_mLights", this.dis_mLights).putBoolean("dis_mHeadlight", this.dis_mHeadlight).commit();
      }

   }
}
