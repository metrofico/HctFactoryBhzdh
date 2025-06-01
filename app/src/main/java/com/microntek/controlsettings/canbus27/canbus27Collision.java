package com.microntek.controlsettings.canbus27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27Collision extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean dis_mAnti_collision_alarm;
   private boolean dis_mBlind_zone_alarm;
   private boolean dis_mParking_set;
   private boolean dis_mRadar_set;
   private boolean dis_mTrailer;
   private boolean isData = true;
   private boolean isDisplay;
   private boolean ismBlind_zone_alarm;
   private boolean ismParking_set;
   private boolean ismRadar_set;
   private SwitchboxPreference mAnti_collision_alarm;
   private HCheckBoxPreference mBlind_zone_alarm;
   private HCheckBoxPreference mParking_set;
   private HCheckBoxPreference mRadar_set;
   private SwitchboxPreference mTrailer;
   private byte[] setData = new byte[16];
   private SharedPreferences sp;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)74, new byte[]{var1, var2}, 2);
   }

   private void updasettings45() {
      this.isData = false;
      if ((byte)(this.setData[0] & 16) == 16) {
         this.dis_mTrailer = true;
         this.getPreferenceScreen().addItemFromInflater(this.mTrailer);
         byte[] var1 = this.setData;
         if ((byte)(var1[1] & 24) == 24) {
            this.mTrailer.setValue("3");
         } else if ((byte)(var1[1] & 16) == 16) {
            this.mTrailer.setValue("2");
         } else if ((byte)(var1[1] & 8) == 8) {
            this.mTrailer.setValue("1");
         } else {
            this.mTrailer.setValue("0");
         }
      } else {
         this.dis_mTrailer = false;
         this.getPreferenceScreen().removePreference(this.mTrailer);
      }

      if ((byte)(this.setData[0] & 128) == -128) {
         this.dis_mParking_set = true;
         this.getPreferenceScreen().addItemFromInflater(this.mParking_set);
         if ((byte)(this.setData[1] & 128) == -128) {
            this.ismParking_set = true;
            this.mParking_set.setChecked(true);
         } else {
            this.ismParking_set = false;
            this.mParking_set.setChecked(false);
         }
      } else {
         this.dis_mParking_set = false;
         this.getPreferenceScreen().removePreference(this.mParking_set);
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.dis_mBlind_zone_alarm = true;
         this.getPreferenceScreen().addItemFromInflater(this.mBlind_zone_alarm);
         if ((byte)(this.setData[1] & 64) == 64) {
            this.ismBlind_zone_alarm = true;
            this.mBlind_zone_alarm.setChecked(true);
         } else {
            this.ismBlind_zone_alarm = false;
            this.mBlind_zone_alarm.setChecked(false);
         }
      } else {
         this.dis_mBlind_zone_alarm = false;
         this.getPreferenceScreen().removePreference(this.mBlind_zone_alarm);
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.dis_mAnti_collision_alarm = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAnti_collision_alarm);
         if ((byte)(this.setData[1] & 32) == 32) {
            this.mAnti_collision_alarm.setValue("1");
         } else {
            this.mAnti_collision_alarm.setValue("0");
         }
      } else {
         this.dis_mAnti_collision_alarm = false;
         this.getPreferenceScreen().removePreference(this.mAnti_collision_alarm);
      }

      if ((byte)(this.setData[0] & 8) == 8) {
         this.dis_mRadar_set = true;
         this.getPreferenceScreen().addItemFromInflater(this.mRadar_set);
         if ((byte)(this.setData[1] & 4) == 4) {
            this.ismRadar_set = true;
            this.mRadar_set.setChecked(true);
         } else {
            this.ismRadar_set = false;
            this.mRadar_set.setChecked(false);
         }
      } else {
         this.dis_mRadar_set = false;
         this.getPreferenceScreen().removePreference(this.mRadar_set);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         if (var3 == 69) {
            while(var2 < var4 - 1) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings45();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492905);
      this.mParking_set = (HCheckBoxPreference)this.findPreference("parking_set");
      HCheckBoxPreference var2 = this.mParking_set;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mBlind_zone_alarm = (HCheckBoxPreference)this.findPreference("blind_zone_alarm");
      var2 = this.mBlind_zone_alarm;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRadar_set = (HCheckBoxPreference)this.findPreference("radar_set");
      var2 = this.mRadar_set;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mTrailer = (SwitchboxPreference)this.findPreference("trailer");
      SwitchboxPreference var3 = this.mTrailer;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mAnti_collision_alarm = (SwitchboxPreference)this.findPreference("anti_collision_alarm");
      var3 = this.mAnti_collision_alarm;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.ismParking_set = this.mParking_set.isChecked();
      this.ismBlind_zone_alarm = this.mBlind_zone_alarm.isChecked();
      this.ismRadar_set = this.mRadar_set.isChecked();
      this.sp = this.getSharedPreferences("spcanbus27", 0);
      this.isDisplay = this.sp.getBoolean("dis_mAnti_collision_alarm", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAnti_collision_alarm);
      }

      this.isDisplay = this.sp.getBoolean("dis_mTrailer", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mTrailer);
      }

      this.isDisplay = this.sp.getBoolean("dis_mRadar_set", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mRadar_set);
      }

      this.isDisplay = this.sp.getBoolean("dis_mBlind_zone_alarm", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mBlind_zone_alarm);
      }

      this.isDisplay = this.sp.getBoolean("dis_mParking_set", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mParking_set);
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
         if (var1 == this.mTrailer) {
            this.RequestCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mAnti_collision_alarm) {
            this.RequestCmdkey((byte)4, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mParking_set) {
         if (this.ismParking_set) {
            this.RequestCmdkey((byte)1, (byte)0);
         } else {
            this.RequestCmdkey((byte)1, (byte)1);
         }
      }

      if (var1 == this.mBlind_zone_alarm) {
         if (this.ismBlind_zone_alarm) {
            this.RequestCmdkey((byte)2, (byte)0);
         } else {
            this.RequestCmdkey((byte)2, (byte)1);
         }
      }

      if (var1 == this.mRadar_set) {
         if (this.ismRadar_set) {
            this.RequestCmdkey((byte)5, (byte)0);
         } else {
            this.RequestCmdkey((byte)5, (byte)1);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
      if (!this.isData) {
         this.sp.edit().putBoolean("dis_mParking_set", this.dis_mParking_set).putBoolean("dis_mBlind_zone_alarm", this.dis_mBlind_zone_alarm).putBoolean("dis_mRadar_set", this.dis_mRadar_set).putBoolean("dis_mTrailer", this.dis_mTrailer).putBoolean("dis_mAnti_collision_alarm", this.dis_mAnti_collision_alarm).commit();
      }

   }
}
