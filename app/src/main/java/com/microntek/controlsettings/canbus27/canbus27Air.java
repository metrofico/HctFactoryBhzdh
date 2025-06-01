package com.microntek.controlsettings.canbus27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27Air extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean dis_mAfter_heating;
   private boolean dis_mAir_onoff;
   private boolean dis_mAir_quality;
   private boolean dis_mAir_quality2;
   private boolean dis_mAir_register;
   private boolean dis_mAutomatic;
   private boolean dis_mBack_temperature;
   private boolean dis_mRear_window;
   private boolean dis_mRemote_start_air;
   private boolean dis_mSeat_auto;
   private boolean dis_mSeat_heating;
   private boolean dis_mSeat_ventilation;
   private boolean dis_mWindow_auto;
   private boolean isData = true;
   private boolean isDisplay;
   private boolean ismAfter_heating;
   private boolean ismSeat_auto;
   private boolean ismSeat_heating;
   private boolean ismSeat_ventilation;
   private boolean ismWindow_auto;
   private HCheckBoxPreference mAfter_heating;
   private SwitchboxPreference mAir_onoff;
   private SwitchboxPreference mAir_quality;
   private SwitchboxPreference mAir_quality2;
   private SwitchboxPreference mAir_register;
   private SwitchboxPreference mAutomatic;
   private SwitchboxPreference mBack_temperature;
   private SwitchboxPreference mRear_window;
   private SwitchboxPreference mRemote_start_air;
   private HCheckBoxPreference mSeat_auto;
   private HCheckBoxPreference mSeat_heating;
   private HCheckBoxPreference mSeat_ventilation;
   private HCheckBoxPreference mWindow_auto;
   private byte[] setData = new byte[16];
   private SharedPreferences sp;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)58, new byte[]{var1, var2}, 2);
   }

   private void updasettings35() {
      this.isData = false;
      byte[] var1;
      if ((byte)(this.setData[0] & 128) == -128) {
         this.dis_mAir_register = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAir_register);
         var1 = this.setData;
         if ((byte)(var1[2] & 128) == -128) {
            this.mAir_register.setValue("2");
         } else if ((byte)(var1[2] & 64) == 64) {
            this.mAir_register.setValue("1");
         } else {
            this.mAir_register.setValue("0");
         }
      } else {
         this.dis_mAir_register = false;
         this.getPreferenceScreen().removePreference(this.mAir_register);
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.dis_mAir_onoff = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAir_onoff);
         var1 = this.setData;
         if ((byte)(var1[2] & 32) == 32) {
            this.mAir_onoff.setValue("2");
         } else if ((byte)(var1[2] & 16) == 16) {
            this.mAir_onoff.setValue("1");
         } else {
            this.mAir_onoff.setValue("0");
         }
      } else {
         this.dis_mAir_onoff = false;
         this.getPreferenceScreen().removePreference(this.mAir_onoff);
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.dis_mAir_quality = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAir_quality);
         if ((byte)(this.setData[2] & 4) == 4) {
            this.mAir_quality.setValue("1");
         } else {
            this.mAir_quality.setValue("0");
         }
      } else {
         this.dis_mAir_quality = false;
         this.getPreferenceScreen().removePreference(this.mAir_quality);
      }

      if ((byte)(this.setData[0] & 16) == 16) {
         this.dis_mAutomatic = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAutomatic);
         var1 = this.setData;
         if ((byte)(var1[2] & 2) == 2) {
            this.mAutomatic.setValue("2");
         } else if ((byte)(var1[2] & 1) == 1) {
            this.mAutomatic.setValue("1");
         } else {
            this.mAutomatic.setValue("0");
         }
      } else {
         this.dis_mAutomatic = false;
         this.getPreferenceScreen().removePreference(this.mAutomatic);
      }

      if ((byte)(this.setData[0] & 8) == 8) {
         this.dis_mSeat_auto = true;
         this.getPreferenceScreen().addItemFromInflater(this.mSeat_auto);
         if ((byte)(this.setData[3] & 128) == -128) {
            this.ismSeat_auto = true;
            this.mSeat_auto.setChecked(true);
         } else {
            this.ismSeat_auto = false;
            this.mSeat_auto.setChecked(false);
         }
      } else {
         this.dis_mSeat_auto = false;
         this.getPreferenceScreen().removePreference(this.mSeat_auto);
      }

      if ((byte)(this.setData[0] & 4) == 4) {
         this.dis_mSeat_heating = true;
         this.getPreferenceScreen().addItemFromInflater(this.mSeat_heating);
         if ((byte)(this.setData[3] & 64) == 64) {
            this.ismSeat_heating = true;
            this.mSeat_heating.setChecked(true);
         } else {
            this.ismSeat_heating = false;
            this.mSeat_heating.setChecked(false);
         }
      } else {
         this.dis_mSeat_heating = false;
         this.getPreferenceScreen().removePreference(this.mSeat_heating);
      }

      if ((byte)(this.setData[0] & 2) == 2) {
         this.dis_mSeat_ventilation = true;
         this.getPreferenceScreen().addItemFromInflater(this.mSeat_ventilation);
         if ((byte)(this.setData[3] & 32) == 32) {
            this.ismSeat_ventilation = true;
            this.mSeat_ventilation.setChecked(true);
         } else {
            this.ismSeat_ventilation = false;
            this.mSeat_ventilation.setChecked(false);
         }
      } else {
         this.dis_mSeat_ventilation = false;
         this.getPreferenceScreen().removePreference(this.mSeat_ventilation);
      }

      if ((byte)(this.setData[0] & 1) == 1) {
         this.dis_mAfter_heating = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAfter_heating);
         if ((byte)(this.setData[3] & 16) == 16) {
            this.ismAfter_heating = true;
            this.mAfter_heating.setChecked(true);
         } else {
            this.ismAfter_heating = false;
            this.mAfter_heating.setChecked(false);
         }
      } else {
         this.dis_mAfter_heating = false;
         this.getPreferenceScreen().removePreference(this.mAfter_heating);
      }

      if ((byte)(this.setData[1] & 128) == -128) {
         this.dis_mBack_temperature = true;
         this.getPreferenceScreen().addItemFromInflater(this.mBack_temperature);
         var1 = this.setData;
         if ((byte)(var1[3] & 8) == 8) {
            this.mBack_temperature.setValue("2");
         } else if ((byte)(var1[3] & 4) == 4) {
            this.mBack_temperature.setValue("1");
         } else {
            this.mBack_temperature.setValue("0");
         }
      } else {
         this.dis_mBack_temperature = false;
         this.getPreferenceScreen().removePreference(this.mBack_temperature);
      }

      if ((byte)(this.setData[1] & 64) == 64) {
         this.dis_mWindow_auto = true;
         this.getPreferenceScreen().addItemFromInflater(this.mWindow_auto);
         if ((byte)(this.setData[3] & 2) == 2) {
            this.ismWindow_auto = true;
            this.mWindow_auto.setChecked(true);
         } else {
            this.ismWindow_auto = false;
            this.mWindow_auto.setChecked(false);
         }
      } else {
         this.dis_mWindow_auto = false;
         this.getPreferenceScreen().removePreference(this.mWindow_auto);
      }

      if ((byte)(this.setData[1] & 32) == 32) {
         this.dis_mRear_window = true;
         this.getPreferenceScreen().addItemFromInflater(this.mRear_window);
         if ((byte)(this.setData[3] & 1) == 1) {
            this.mRear_window.setValue("1");
         } else {
            this.mRear_window.setValue("0");
         }
      } else {
         this.dis_mRear_window = false;
         this.getPreferenceScreen().removePreference(this.mRear_window);
      }

      if ((byte)(this.setData[1] & 16) == 16) {
         this.dis_mRemote_start_air = true;
         this.getPreferenceScreen().addItemFromInflater(this.mRemote_start_air);
         if ((byte)(this.setData[4] & 128) == -128) {
            this.mRemote_start_air.setValue("1");
         } else {
            this.mRemote_start_air.setValue("0");
         }
      } else {
         this.dis_mRemote_start_air = false;
         this.getPreferenceScreen().removePreference(this.mRemote_start_air);
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.dis_mAir_quality2 = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAir_quality2);
         if ((byte)(this.setData[4] & 32) == 32) {
            this.mAir_quality2.setValue("1");
         } else {
            this.mAir_quality2.setValue("0");
         }
      } else {
         this.dis_mAir_quality2 = false;
         this.getPreferenceScreen().removePreference(this.mAir_quality2);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         if (var3 == 53) {
            while(var2 < var4 - 1) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings35();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492902);
      this.mSeat_auto = (HCheckBoxPreference)this.findPreference("seat_auto");
      HCheckBoxPreference var2 = this.mSeat_auto;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mSeat_heating = (HCheckBoxPreference)this.findPreference("seat_heating");
      var2 = this.mSeat_heating;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mSeat_ventilation = (HCheckBoxPreference)this.findPreference("seat_ventilation");
      var2 = this.mSeat_ventilation;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAfter_heating = (HCheckBoxPreference)this.findPreference("after_heating");
      var2 = this.mAfter_heating;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mWindow_auto = (HCheckBoxPreference)this.findPreference("window_auto");
      var2 = this.mWindow_auto;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAir_register = (SwitchboxPreference)this.findPreference("air_register");
      SwitchboxPreference var3 = this.mAir_register;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mAir_onoff = (SwitchboxPreference)this.findPreference("air_onoff");
      var3 = this.mAir_onoff;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mAir_quality = (SwitchboxPreference)this.findPreference("air_quality");
      var3 = this.mAir_quality;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mAutomatic = (SwitchboxPreference)this.findPreference("automatic");
      var3 = this.mAutomatic;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mBack_temperature = (SwitchboxPreference)this.findPreference("back_temperature");
      var3 = this.mBack_temperature;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mRear_window = (SwitchboxPreference)this.findPreference("rear_window");
      var3 = this.mRear_window;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mRemote_start_air = (SwitchboxPreference)this.findPreference("remote_start_air");
      var3 = this.mRemote_start_air;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mAir_quality2 = (SwitchboxPreference)this.findPreference("air_quality2");
      var3 = this.mAir_quality2;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.ismSeat_auto = this.mSeat_auto.isChecked();
      this.ismSeat_heating = this.mSeat_heating.isChecked();
      this.ismSeat_ventilation = this.mSeat_ventilation.isChecked();
      this.ismAfter_heating = this.mAfter_heating.isChecked();
      this.ismWindow_auto = this.mWindow_auto.isChecked();
      this.sp = this.getSharedPreferences("spcanbus27", 0);
      this.isDisplay = this.sp.getBoolean("dis_mAir_quality2", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAir_quality2);
      }

      this.isDisplay = this.sp.getBoolean("dis_mRemote_start_air", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mRemote_start_air);
      }

      this.isDisplay = this.sp.getBoolean("dis_mRear_window", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mRear_window);
      }

      this.isDisplay = this.sp.getBoolean("dis_mBack_temperature", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mBack_temperature);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAutomatic", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAutomatic);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAir_quality", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAir_quality);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAir_onoff", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAir_onoff);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAir_register", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAir_register);
      }

      this.isDisplay = this.sp.getBoolean("dis_mWindow_auto", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mWindow_auto);
      }

      this.isDisplay = this.sp.getBoolean("dis_mAfter_heating", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mAfter_heating);
      }

      this.isDisplay = this.sp.getBoolean("dis_mSeat_ventilation", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mSeat_ventilation);
      }

      this.isDisplay = this.sp.getBoolean("dis_mSeat_heating", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mSeat_heating);
      }

      this.isDisplay = this.sp.getBoolean("dis_mSeat_auto", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mSeat_auto);
      }

      Log.i("canbus27Air", "syncOn");
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
         if (var1 == this.mAir_register) {
            this.RequestCmdkey((byte)1, (byte)var4);
         } else if (var1 == this.mAir_onoff) {
            this.RequestCmdkey((byte)2, (byte)var4);
         } else if (var1 == this.mAir_quality) {
            this.RequestCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mAutomatic) {
            this.RequestCmdkey((byte)4, (byte)var4);
         } else if (var1 == this.mBack_temperature) {
            this.RequestCmdkey((byte)9, (byte)var4);
         } else if (var1 == this.mRear_window) {
            this.RequestCmdkey((byte)11, (byte)var4);
         } else if (var1 == this.mRemote_start_air) {
            this.RequestCmdkey((byte)12, (byte)var4);
         } else if (var1 == this.mAir_quality2) {
            this.RequestCmdkey((byte)13, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mSeat_auto) {
         if (this.ismSeat_auto) {
            this.RequestCmdkey((byte)5, (byte)0);
         } else {
            this.RequestCmdkey((byte)5, (byte)1);
         }
      }

      if (var1 == this.mSeat_heating) {
         if (this.ismSeat_heating) {
            this.RequestCmdkey((byte)6, (byte)0);
         } else {
            this.RequestCmdkey((byte)6, (byte)1);
         }
      }

      if (var1 == this.mSeat_ventilation) {
         if (this.ismSeat_ventilation) {
            this.RequestCmdkey((byte)7, (byte)0);
         } else {
            this.RequestCmdkey((byte)7, (byte)1);
         }
      }

      if (var1 == this.mAfter_heating) {
         if (this.ismAfter_heating) {
            this.RequestCmdkey((byte)8, (byte)0);
         } else {
            this.RequestCmdkey((byte)8, (byte)1);
         }
      }

      if (var1 == this.mWindow_auto) {
         if (this.ismWindow_auto) {
            this.RequestCmdkey((byte)10, (byte)0);
         } else {
            this.RequestCmdkey((byte)10, (byte)1);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
      if (!this.isData) {
         this.sp.edit().putBoolean("dis_mSeat_auto", this.dis_mSeat_auto).putBoolean("dis_mSeat_heating", this.dis_mSeat_heating).putBoolean("dis_mSeat_ventilation", this.dis_mSeat_ventilation).putBoolean("dis_mAfter_heating", this.dis_mAfter_heating).putBoolean("dis_mWindow_auto", this.dis_mWindow_auto).putBoolean("dis_mAir_register", this.dis_mAir_register).putBoolean("dis_mAir_onoff", this.dis_mAir_onoff).putBoolean("dis_mAir_quality", this.dis_mAir_quality).putBoolean("dis_mAutomatic", this.dis_mAutomatic).putBoolean("dis_mBack_temperature", this.dis_mBack_temperature).putBoolean("dis_mRear_window", this.dis_mRear_window).putBoolean("dis_mRemote_start_air", this.dis_mRemote_start_air).putBoolean("dis_mAir_quality2", this.dis_mAir_quality2).commit();
      }

   }
}
