package com.microntek.controlsettings.canbus27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27MeterDis extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean dis_mEngine_state;
   private boolean dis_mGps_display;
   private boolean dis_mMixed_eco;
   private boolean dis_mSet_backlight;
   private boolean dis_mSpeed_display;
   private boolean isData75 = true;
   private boolean isData85 = true;
   private boolean isDisplay;
   private boolean ismGps_display;
   private boolean ismMixed_eco;
   private boolean ismSet_backlight;
   private SwitchboxPreference mEngine_state;
   private HCheckBoxPreference mGps_display;
   private HCheckBoxPreference mMixed_eco;
   private HCheckBoxPreference mSet_backlight;
   private SwitchboxPreference mSpeed_display;
   private byte[] setData = new byte[16];
   private SharedPreferences sp;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)122, new byte[]{var1, var2}, 2);
   }

   private void RequestCmdkey8A(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)-118, new byte[]{var1, var2}, 2);
   }

   private void updasettings75() {
      this.isData75 = false;
      if ((byte)(this.setData[0] & 32) == 32) {
         this.dis_mSpeed_display = true;
         this.getPreferenceScreen().addItemFromInflater(this.mSpeed_display);
         if ((byte)(this.setData[1] & 32) == 32) {
            this.mSpeed_display.setValue("1");
         } else {
            this.mSpeed_display.setValue("0");
         }
      } else {
         this.dis_mSpeed_display = false;
         this.getPreferenceScreen().removePreference(this.mSpeed_display);
      }

      if ((byte)(this.setData[0] & 128) == -128) {
         this.dis_mMixed_eco = true;
         this.getPreferenceScreen().addItemFromInflater(this.mMixed_eco);
         if ((byte)(this.setData[1] & 128) == -128) {
            this.ismMixed_eco = true;
            this.mMixed_eco.setChecked(true);
         } else {
            this.ismMixed_eco = false;
            this.mMixed_eco.setChecked(false);
         }
      } else {
         this.dis_mMixed_eco = false;
         this.getPreferenceScreen().removePreference(this.mMixed_eco);
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.dis_mGps_display = true;
         this.getPreferenceScreen().addItemFromInflater(this.mGps_display);
         if ((byte)(this.setData[1] & 64) == 64) {
            this.ismGps_display = true;
            this.mGps_display.setChecked(true);
         } else {
            this.ismGps_display = false;
            this.mGps_display.setChecked(false);
         }
      } else {
         this.dis_mGps_display = false;
         this.getPreferenceScreen().removePreference(this.mGps_display);
      }

   }

   private void updasettings85() {
      this.isData85 = false;
      if ((byte)(this.setData[0] & 64) == 64) {
         this.dis_mSet_backlight = true;
         this.getPreferenceScreen().addItemFromInflater(this.mSet_backlight);
         if ((byte)(this.setData[1] & 64) == 64) {
            this.ismSet_backlight = true;
            this.mSet_backlight.setChecked(true);
         } else {
            this.ismSet_backlight = false;
            this.mSet_backlight.setChecked(false);
         }
      } else {
         this.dis_mSet_backlight = false;
         this.getPreferenceScreen().removePreference(this.mSet_backlight);
      }

      if ((byte)(this.setData[0] & 128) == -128) {
         this.dis_mEngine_state = true;
         this.getPreferenceScreen().addItemFromInflater(this.mEngine_state);
         if ((byte)(this.setData[1] & 128) != -128) {
            this.mEngine_state.setValue("1");
         } else {
            this.mEngine_state.setValue("0");
         }
      } else {
         this.dis_mEngine_state = false;
         this.getPreferenceScreen().removePreference(this.mEngine_state);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var3 = 0;
      int var2 = 0;
      byte var5 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         byte[] var6;
         if (var5 != 117) {
            if (var5 == -123) {
               for(var2 = var3; var2 < var4 - 1; var2 = var3) {
                  var6 = this.setData;
                  var3 = var2 + 1;
                  var6[var2] = var1[var3];
               }

               this.updasettings85();
            }
         } else {
            while(var2 < var4 - 1) {
               var6 = this.setData;
               var3 = var2 + 1;
               var6[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings75();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492910);
      this.mMixed_eco = (HCheckBoxPreference)this.findPreference("mixed_eco");
      HCheckBoxPreference var2 = this.mMixed_eco;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mGps_display = (HCheckBoxPreference)this.findPreference("gps_display");
      var2 = this.mGps_display;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mSet_backlight = (HCheckBoxPreference)this.findPreference("set_backlight");
      var2 = this.mSet_backlight;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mSpeed_display = (SwitchboxPreference)this.findPreference("speed_display");
      SwitchboxPreference var3 = this.mSpeed_display;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mEngine_state = (SwitchboxPreference)this.findPreference("engine_state");
      var3 = this.mEngine_state;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.ismMixed_eco = this.mMixed_eco.isChecked();
      this.ismGps_display = this.mGps_display.isChecked();
      this.ismSet_backlight = this.mSet_backlight.isChecked();
      this.isDisplay = this.sp.getBoolean("dis_mEngine_state", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mEngine_state);
      }

      this.isDisplay = this.sp.getBoolean("dis_mSpeed_display", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mSpeed_display);
      }

      this.isDisplay = this.sp.getBoolean("dis_mSet_backlight", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mSet_backlight);
      }

      this.isDisplay = this.sp.getBoolean("dis_mGps_display", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mGps_display);
      }

      this.isDisplay = this.sp.getBoolean("dis_mMixed_eco", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mMixed_eco);
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

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mSpeed_display) {
            this.RequestCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mEngine_state) {
            this.RequestCmdkey8A((byte)1, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mMixed_eco) {
         if (this.ismMixed_eco) {
            this.RequestCmdkey((byte)1, (byte)0);
         } else {
            this.RequestCmdkey((byte)1, (byte)1);
         }
      }

      if (var1 == this.mGps_display) {
         if (this.ismGps_display) {
            this.RequestCmdkey((byte)2, (byte)0);
         } else {
            this.RequestCmdkey((byte)2, (byte)1);
         }
      }

      if (var1 == this.mSet_backlight) {
         if (this.ismSet_backlight) {
            this.RequestCmdkey8A((byte)2, (byte)0);
         } else {
            this.RequestCmdkey8A((byte)2, (byte)1);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
      if (!this.isData75 && !this.isData85) {
         this.sp.edit().putBoolean("dis_mMixed_eco", this.dis_mMixed_eco).putBoolean("dis_mGps_display", this.dis_mGps_display).putBoolean("dis_mSet_backlight", this.dis_mSet_backlight).putBoolean("dis_mSpeed_display", this.dis_mSpeed_display).putBoolean("dis_mEngine_state", this.dis_mEngine_state).commit();
      }

   }
}
