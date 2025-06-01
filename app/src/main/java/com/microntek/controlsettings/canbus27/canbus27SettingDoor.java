package com.microntek.controlsettings.canbus27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.Log;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;

public class canbus27SettingDoor extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean dis_mAuto_hand;
   private boolean dis_mAuto_settings;
   private boolean dis_mAutomatic1;
   private boolean dis_mDelay;
   private boolean dis_mOpen;
   private boolean isAuto_st;
   private boolean isData = true;
   private boolean isDelay;
   private boolean isDisplay;
   private boolean isOpen_door;
   private ListPreference mAuto_hand;
   private HCheckBoxPreference mAuto_settings;
   private ListPreference mAutomatic1;
   private HCheckBoxPreference mDelay_locked_set;
   private HCheckBoxPreference mOpen_door_settings;
   private byte[] setData = new byte[20];
   private SharedPreferences sp;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{var1, var2}, 2);
   }

   private void updasettings65() {
      this.isData = false;
      if ((byte)(this.setData[0] & 128) == -128) {
         this.getPreferenceScreen().addItemFromInflater(this.mOpen_door_settings);
         this.dis_mOpen = true;
         if ((byte)(this.setData[1] & 128) == -128) {
            this.isOpen_door = true;
            this.mOpen_door_settings.setChecked(true);
         } else {
            this.isOpen_door = false;
            this.mOpen_door_settings.setChecked(false);
         }
      } else {
         this.mOpen_door_settings.setChecked(false);
         this.dis_mOpen = false;
         this.getPreferenceScreen().removePreference(this.mOpen_door_settings);
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.dis_mAuto_settings = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAuto_settings);
         if ((byte)(this.setData[1] & 64) == 64) {
            this.isAuto_st = true;
            this.mAuto_settings.setChecked(true);
         } else {
            this.isAuto_st = false;
            this.mAuto_settings.setChecked(false);
         }
      } else {
         this.mAuto_settings.setChecked(false);
         this.dis_mAuto_settings = false;
         this.getPreferenceScreen().removePreference(this.mAuto_settings);
      }

      byte[] var1;
      ListPreference var2;
      if ((byte)(this.setData[0] & 32) == 32) {
         this.dis_mAutomatic1 = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAutomatic1);
         var1 = this.setData;
         if ((byte)(var1[1] & 32) == 32) {
            var2 = this.mAutomatic1;
            var2.setSummary(var2.getEntries()[2]);
         } else if ((byte)(var1[1] & 16) == 16) {
            var2 = this.mAutomatic1;
            var2.setSummary(var2.getEntries()[1]);
         } else {
            var2 = this.mAutomatic1;
            var2.setSummary(var2.getEntries()[0]);
         }
      } else {
         this.dis_mAutomatic1 = false;
         this.getPreferenceScreen().removePreference(this.mAutomatic1);
      }

      if ((byte)(this.setData[0] & 16) == 16) {
         this.dis_mDelay = true;
         this.getPreferenceScreen().addItemFromInflater(this.mDelay_locked_set);
         if ((byte)(this.setData[1] & 8) == 8) {
            this.isDelay = true;
            this.mDelay_locked_set.setChecked(true);
         } else {
            this.isDelay = false;
            this.mDelay_locked_set.setChecked(false);
         }
      } else {
         this.mDelay_locked_set.setChecked(false);
         this.dis_mDelay = false;
         this.getPreferenceScreen().removePreference(this.mDelay_locked_set);
      }

      if ((byte)(this.setData[0] & 8) == 8) {
         this.dis_mAuto_hand = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAuto_hand);
         var1 = this.setData;
         if ((byte)(var1[1] & 2) == 2) {
            var2 = this.mAuto_hand;
            var2.setSummary(var2.getEntries()[2]);
         } else if ((byte)(var1[1] & 1) == 1) {
            var2 = this.mAuto_hand;
            var2.setSummary(var2.getEntries()[1]);
         } else {
            var2 = this.mAuto_hand;
            var2.setSummary(var2.getEntries()[0]);
         }
      } else {
         this.dis_mAuto_hand = false;
         this.getPreferenceScreen().removePreference(this.mAuto_hand);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         if (var3 == 101) {
            while(var2 < var4 - 1) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings65();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492908);
      this.mOpen_door_settings = (HCheckBoxPreference)this.findPreference("open_door_settings");
      HCheckBoxPreference var2 = this.mOpen_door_settings;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAuto_settings = (HCheckBoxPreference)this.findPreference("auto_settings");
      var2 = this.mAuto_settings;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mDelay_locked_set = (HCheckBoxPreference)this.findPreference("delay_locked_set");
      var2 = this.mDelay_locked_set;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAuto_hand = (ListPreference)this.findPreference("auto_hand1");
      this.mAuto_hand.setOnPreferenceChangeListener(this);
      this.mAutomatic1 = (ListPreference)this.findPreference("automatic1");
      this.mAutomatic1.setOnPreferenceChangeListener(this);
      this.isOpen_door = this.mOpen_door_settings.isChecked();
      this.isAuto_st = this.mAuto_settings.isChecked();
      this.isDelay = this.mDelay_locked_set.isChecked();
      this.sp = this.getSharedPreferences("spcanbus27", 0);
      this.isDisplay = this.sp.getBoolean("dis_mOpen", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mOpen_door_settings);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAuto_settings", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAuto_settings);
      }

      this.isDisplay = this.sp.getBoolean("dis_mDelay", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mDelay_locked_set);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAutomatic1", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAutomatic1);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAuto_hand", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAuto_hand);
      }

      Log.i("canbus27settingdoor", "syncOn");
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3;
      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var4) {
         return false;
      }

      if (var1 == this.mAutomatic1) {
         this.RequestCmdkey((byte)3, (byte)var3);
         return true;
      } else if (var1 == this.mAuto_hand) {
         this.RequestCmdkey((byte)5, (byte)var3);
         return true;
      } else {
         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mOpen_door_settings) {
         if (this.isOpen_door) {
            this.RequestCmdkey((byte)1, (byte)0);
         } else {
            this.RequestCmdkey((byte)1, (byte)1);
         }
      }

      if (var1 == this.mAuto_settings) {
         if (this.isAuto_st) {
            this.RequestCmdkey((byte)2, (byte)0);
         } else {
            this.RequestCmdkey((byte)2, (byte)1);
         }
      }

      if (var1 == this.mDelay_locked_set) {
         if (this.isDelay) {
            this.RequestCmdkey((byte)4, (byte)0);
         } else {
            this.RequestCmdkey((byte)4, (byte)1);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
      if (!this.isData) {
         this.sp.edit().putBoolean("dis_mOpen", this.dis_mOpen).putBoolean("dis_mAuto_settings", this.dis_mAuto_settings).putBoolean("dis_mDelay", this.dis_mDelay).putBoolean("dis_mAutomatic1", this.dis_mAutomatic1).putBoolean("dis_mAuto_hand", this.dis_mAuto_hand).commit();
      }

   }
}
