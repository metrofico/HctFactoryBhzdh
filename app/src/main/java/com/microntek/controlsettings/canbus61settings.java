package com.microntek.controlsettings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;

public class canbus61settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference Welcome_cmd;
   private byte data0 = 0;
   private int length = 0;
   private HCheckBoxPreference mAdaptive_light;
   private SwitchboxPreference mAtmosphere_lighting;
   private HCheckBoxPreference mAutomatic_head;
   private HCheckBoxPreference mBlind_detection;
   private HCheckBoxPreference mBwiper;
   private HCheckBoxPreference mControl_system;
   private HCheckBoxPreference mDaytime_lights;
   private HCheckBoxPreference mDriving_auto;
   private HCheckBoxPreference mEngine_stop;
   private HCheckBoxPreference mFatigue;
   private SwitchboxPreference mHome_lighting;
   private SwitchboxPreference mInstrument_color;
   private SwitchboxPreference mInstrument_style;
   private SwitchboxPreference mLamp_no;
   private SwitchboxPreference mLanguages;
   private HCheckBoxPreference mMemory_speed;
   private HCheckBoxPreference mOff_Warning_info;
   private SwitchboxPreference mOil_unit;
   private HCheckBoxPreference mParking_assist;
   private HCheckBoxPreference mRadar_stop;
   private SwitchboxPreference mSetdoor;
   private SwitchboxPreference mSound;
   private OnSwitchPreference mSpeed1;
   private OnSwitchPreference mSpeed2;
   private OnSwitchPreference mSpeed3;
   private OnSwitchPreference mSpeed4;
   private OnSwitchPreference mSpeed5;
   private SwitchboxPreference mTemp;
   private PreferenceScreen mTpms_cal;
   private HCheckBoxPreference mUnlock_trunk_only;
   private SwitchboxPreference mWelcome_lig;
   private byte[] setData = new byte[30];
   private int speed1Data = 0;
   private int speed2Data = 0;
   private int speed3Data = 0;
   private int speed4Data = 0;
   private int speed5Data = 0;
   private int type = 0;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-128, new byte[]{var1, var2}, 2);
   }

   private void SetCmdkey88(byte var1, byte var2, byte var3) {
      byte[] var5 = new byte[6];
      byte var4 = 1;
      if ((var1 & 64) != 0) {
         var5[0] = (byte)(var1 | 1 << 0 - (var2 - 5) + 1);
      }

      var5[1] = (byte)(this.speed1Data & 255);
      var5[2] = (byte)(this.speed2Data & 255);
      var5[3] = (byte)(this.speed3Data & 255);
      var5[4] = (byte)(this.speed4Data & 255);
      var5[5] = (byte)(this.speed5Data & 255);

      for(var1 = var4; var1 < 6; ++var1) {
         if (var2 == var1) {
            var5[var1] = var3;
         }
      }

      this.SendCanBusCmdData2E((byte)-120, var5, 6);
   }

   private void SetCmdkey90(byte var1) {
      byte[] var2 = new byte[4];
      var2[0] = var1;
      this.SendCanBusCmdData2E((byte)-112, var2, 4);
   }

   private int sum(OnSwitchPreference var1, Object var2) {
      return Integer.parseInt((String)var1.getEntries()[Integer.parseInt((String)var2)]) * 5;
   }

   private void updataSettings38() {
      HCheckBoxPreference var4 = this.mParking_assist;
      byte var1 = this.setData[2];
      boolean var3 = false;
      boolean var2;
      if ((var1 & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mBwiper;
      if ((this.setData[1] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mRadar_stop;
      if ((this.setData[2] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      SwitchboxPreference var5 = this.mAtmosphere_lighting;
      StringBuilder var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[2] >> 5 & 7);
      var5.setValue(var6.toString());
      var5 = this.mHome_lighting;
      var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[3] >> 6 & 3);
      var5.setValue(var6.toString());
      var5 = this.mWelcome_lig;
      var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[3] >> 4 & 3);
      var5.setValue(var6.toString());
      SwitchboxPreference var7;
      StringBuilder var8;
      if (this.length >= 6) {
         var7 = this.mOil_unit;
         var8 = new StringBuilder();
         var8.append("");
         var8.append(this.setData[5] >> 6 & 3);
         var7.setValue(var8.toString());
         this.putInt("mOil_unit", this.setData[5] >> 6 & 3);
      } else {
         var5 = this.mOil_unit;
         var6 = new StringBuilder();
         var6.append("");
         var6.append(this.setData[3] >> 0 & 1);
         var5.setValue(var6.toString());
         this.putInt("mOil_unit", this.setData[3] >> 0 & 1);
      }

      var7 = this.mLanguages;
      var8 = new StringBuilder();
      var8.append("");
      var8.append(this.setData[4] >> 0 & 15);
      var7.setValue(var8.toString());
      var5 = this.mSound;
      var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[3] >> 1 & 3);
      var5.setValue(var6.toString());
      var4 = this.mDaytime_lights;
      if ((this.setData[3] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mBlind_detection;
      if ((this.setData[4] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mEngine_stop;
      if ((this.setData[4] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.Welcome_cmd;
      if ((this.setData[4] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var7 = this.mSetdoor;
      var8 = new StringBuilder();
      var8.append("");
      var8.append(this.setData[4] >> 4 & 1);
      var7.setValue(var8.toString());
      var5 = this.mLamp_no;
      var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[5] >> 4 & 3);
      var5.setValue(var6.toString());
      var4 = this.mUnlock_trunk_only;
      if ((this.setData[1] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mControl_system;
      if ((this.setData[1] & 8) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mFatigue;
      if ((this.setData[1] & 4) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var5 = this.mInstrument_color;
      var6 = new StringBuilder();
      var6.append("");
      var6.append(this.setData[1] >> 1 & 1);
      var5.setValue(var6.toString());
      var4 = this.mAutomatic_head;
      if ((this.setData[5] & 4) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mDriving_auto;
      if ((this.setData[5] & 2) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4.setChecked(var2);
      var4 = this.mAdaptive_light;
      var2 = var3;
      if ((this.setData[5] & 1) != 0) {
         var2 = true;
      }

      var4.setChecked(var2);
      if ((this.setData[5] & 8) != 0) {
         this.mTpms_cal.setSummary(this.getString(2131297242));
      } else {
         this.mTpms_cal.setSummary("");
      }

   }

   private void updataSettings3B() {
      if (this.type == 1) {
         HCheckBoxPreference var2 = this.mMemory_speed;
         boolean var1;
         if ((this.setData[0] & 128) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         var2.setChecked(var1);
         if ((this.setData[0] & 128) != 0) {
            this.getPreferenceScreen().addPreference(this.mSpeed1);
            this.getPreferenceScreen().addPreference(this.mSpeed2);
            this.getPreferenceScreen().addPreference(this.mSpeed3);
            this.getPreferenceScreen().addPreference(this.mSpeed4);
            this.getPreferenceScreen().addPreference(this.mSpeed5);
            this.data0 = (byte)(this.data0 | 64);
            this.speed1Data = this.setData[1] & 255;
            OnSwitchPreference var3 = this.mSpeed1;
            StringBuilder var4 = new StringBuilder();
            var4.append(this.speed1Data);
            var4.append(" km/h");
            var3.setValue("0", var4.toString());
            this.speed2Data = this.setData[2] & 255;
            OnSwitchPreference var5 = this.mSpeed2;
            StringBuilder var6 = new StringBuilder();
            var6.append(this.speed2Data);
            var6.append(" km/h");
            var5.setValue("0", var6.toString());
            this.speed3Data = this.setData[3] & 255;
            var3 = this.mSpeed3;
            var4 = new StringBuilder();
            var4.append(this.speed3Data);
            var4.append(" km/h");
            var3.setValue("0", var4.toString());
            this.speed4Data = this.setData[4] & 255;
            var5 = this.mSpeed4;
            var6 = new StringBuilder();
            var6.append(this.speed4Data);
            var6.append(" km/h");
            var5.setValue("0", var6.toString());
            this.speed5Data = this.setData[5] & 255;
            var3 = this.mSpeed5;
            var4 = new StringBuilder();
            var4.append(this.speed5Data);
            var4.append(" km/h");
            var3.setValue("0", var4.toString());
         } else {
            this.getPreferenceScreen().removePreference(this.mSpeed1);
            this.getPreferenceScreen().removePreference(this.mSpeed2);
            this.getPreferenceScreen().removePreference(this.mSpeed3);
            this.getPreferenceScreen().removePreference(this.mSpeed4);
            this.getPreferenceScreen().removePreference(this.mSpeed5);
            this.data0 = (byte)(this.data0 ^ 64);
         }

      }
   }

   protected void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      int var3 = var1.length;
      int var2 = 2;
      if (var3 > 2 && var3 <= 29 && this.mTpms_cal != null) {
         if (var4 != 56) {
            if (var4 != 59) {
               if (var4 == 33) {
                  SwitchboxPreference var5 = this.mTemp;
                  StringBuilder var6 = new StringBuilder();
                  var6.append("");
                  var6.append(var1[6] & 1);
                  var5.setValue(var6.toString());
               }
            } else {
               while(var2 < var3) {
                  this.setData[var2 - 2] = var1[var2];
                  ++var2;
               }

               this.updataSettings3B();
            }
         } else {
            while(var2 < var3) {
               this.setData[var2 - 2] = var1[var2];
               ++var2;
            }

            this.length = var1[1] & 255;
            this.updataSettings38();
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492933);
      this.mParking_assist = (HCheckBoxPreference)this.findPreference("parking_assist");
      this.mParking_assist.setOnPreferenceClickListener(this);
      this.mBwiper = (HCheckBoxPreference)this.findPreference("bwiper");
      this.mBwiper.setOnPreferenceClickListener(this);
      this.mRadar_stop = (HCheckBoxPreference)this.findPreference("radar_stop");
      this.mRadar_stop.setOnPreferenceClickListener(this);
      this.mDaytime_lights = (HCheckBoxPreference)this.findPreference("daytime_lights");
      this.mDaytime_lights.setOnPreferenceClickListener(this);
      this.mMemory_speed = (HCheckBoxPreference)this.findPreference("memory_speed");
      this.mMemory_speed.setOnPreferenceClickListener(this);
      this.mBlind_detection = (HCheckBoxPreference)this.findPreference("blind_detection");
      this.mBlind_detection.setOnPreferenceClickListener(this);
      this.mEngine_stop = (HCheckBoxPreference)this.findPreference("engine_stop");
      this.mEngine_stop.setOnPreferenceClickListener(this);
      this.Welcome_cmd = (HCheckBoxPreference)this.findPreference("welcome_cmd");
      this.Welcome_cmd.setOnPreferenceClickListener(this);
      this.mUnlock_trunk_only = (HCheckBoxPreference)this.findPreference("unlock_trunk_only");
      this.mUnlock_trunk_only.setOnPreferenceClickListener(this);
      this.mControl_system = (HCheckBoxPreference)this.findPreference("control_system");
      this.mControl_system.setOnPreferenceClickListener(this);
      this.mFatigue = (HCheckBoxPreference)this.findPreference("fatigue");
      this.mFatigue.setOnPreferenceClickListener(this);
      this.mAutomatic_head = (HCheckBoxPreference)this.findPreference("automatic_head");
      this.mAutomatic_head.setOnPreferenceClickListener(this);
      this.mDriving_auto = (HCheckBoxPreference)this.findPreference("driving_auto");
      this.mDriving_auto.setOnPreferenceClickListener(this);
      this.mOff_Warning_info = this.findHCheckBoxPreference("off_Warning_info");
      this.mAdaptive_light = this.findHCheckBoxPreference("adaptive_light");
      this.mAtmosphere_lighting = (SwitchboxPreference)this.findPreference("atmosphere_lighting");
      this.mAtmosphere_lighting.setOnPreferenceChangeListener(this);
      this.mHome_lighting = (SwitchboxPreference)this.findPreference("home_lighting");
      this.mHome_lighting.setOnPreferenceChangeListener(this);
      this.mWelcome_lig = (SwitchboxPreference)this.findPreference("welcome_lig");
      this.mWelcome_lig.setOnPreferenceChangeListener(this);
      this.mSound = (SwitchboxPreference)this.findPreference("sound");
      this.mSound.setOnPreferenceChangeListener(this);
      this.mOil_unit = (SwitchboxPreference)this.findPreference("oil_unit");
      this.mOil_unit.setOnPreferenceChangeListener(this);
      this.mLanguages = (SwitchboxPreference)this.findPreference("languages");
      this.mLanguages.setOnPreferenceChangeListener(this);
      this.mSpeed1 = (OnSwitchPreference)this.findPreference("speed1");
      this.mSpeed1.setOnPreferenceChangeListener(this);
      this.mSpeed2 = (OnSwitchPreference)this.findPreference("speed2");
      this.mSpeed2.setOnPreferenceChangeListener(this);
      this.mSpeed3 = (OnSwitchPreference)this.findPreference("speed3");
      this.mSpeed3.setOnPreferenceChangeListener(this);
      this.mSpeed4 = (OnSwitchPreference)this.findPreference("speed4");
      this.mSpeed4.setOnPreferenceChangeListener(this);
      this.mSpeed5 = (OnSwitchPreference)this.findPreference("speed5");
      this.mSpeed5.setOnPreferenceChangeListener(this);
      this.mSetdoor = (SwitchboxPreference)this.findPreference("setdoor");
      this.mSetdoor.setOnPreferenceChangeListener(this);
      this.mLamp_no = (SwitchboxPreference)this.findPreference("lamp_no");
      this.mLamp_no.setOnPreferenceChangeListener(this);
      this.mInstrument_color = (SwitchboxPreference)this.findPreference("instrument_color");
      this.mInstrument_color.setOnPreferenceChangeListener(this);
      this.mInstrument_style = (SwitchboxPreference)this.findPreference("instrument_style");
      this.mInstrument_style.setOnPreferenceChangeListener(this);
      this.mTemp = (SwitchboxPreference)this.findPreference("temp");
      this.mTemp.setOnPreferenceChangeListener(this);
      this.mTpms_cal = (PreferenceScreen)this.findPreference("tpms_cal");
      this.mTpms_cal.setOnPreferenceClickListener(this);
      HCheckBoxPreference var5 = this.mOff_Warning_info;
      boolean var3;
      if (this.getInt("mOff_Warning_infor", 0) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      var5.setChecked(var3);
      int var2 = this.getInt("mInstrument_style", 0);
      SwitchboxPreference var4 = this.mInstrument_style;
      StringBuilder var6 = new StringBuilder();
      var6.append(var2);
      var6.append("");
      var4.setValue(var6.toString());
      var2 = this.getInt("mOil_unit", 0);
      SwitchboxPreference var7 = this.mOil_unit;
      StringBuilder var9 = new StringBuilder();
      var9.append(var2);
      var9.append("");
      var7.setValue(var9.toString());
      this.getPreferenceScreen().getPreferenceCount();
      this.type = this.getIntent().getIntExtra("cftype", 0);
      this.SetCmdkey90((byte)59);
      this.SetCmdkey90((byte)56);
      this.SetCmdkey90((byte)33);
      this.getPreferenceScreen().removeAll();
      if (this.type == 1) {
         this.addPreference(this.mMemory_speed);
      } else {
         var2 = this.getCarType();
         if (var2 != 1) {
            if (var2 != 5) {
               if (var2 != 7) {
                  switch (var2) {
                     case 9:
                        this.addPreference(this.mParking_assist);
                        this.addPreference(this.mBwiper);
                        this.addPreference(this.mAtmosphere_lighting);
                        this.addPreference(this.mRadar_stop);
                        this.addPreference(this.mHome_lighting);
                        this.addPreference(this.mWelcome_lig);
                        this.addPreference(this.mDaytime_lights);
                        this.addPreference(this.mSound);
                        this.addPreference(this.mOil_unit);
                        this.addPreference(this.mSetdoor);
                        this.addPreference(this.mTpms_cal);
                        this.addPreference(this.mOff_Warning_info);
                        this.addPreference(this.mLamp_no);
                        this.addPreference(this.mAutomatic_head);
                        this.addPreference(this.mDriving_auto);
                        break;
                     case 10:
                        this.addPreference(this.mParking_assist);
                        this.addPreference(this.mBwiper);
                        this.addPreference(this.mAtmosphere_lighting);
                        this.addPreference(this.mRadar_stop);
                        this.addPreference(this.mHome_lighting);
                        this.addPreference(this.mWelcome_lig);
                        this.addPreference(this.mDaytime_lights);
                        this.addPreference(this.mSound);
                        this.addPreference(this.mOil_unit);
                        this.addPreference(this.mSetdoor);
                        this.addPreference(this.mTpms_cal);
                        this.addPreference(this.mOff_Warning_info);
                        this.addPreference(this.mControl_system);
                        this.addPreference(this.mFatigue);
                        this.addPreference(this.mInstrument_color);
                        this.addPreference(this.mInstrument_style);
                        break;
                     case 11:
                        this.addPreference(this.mParking_assist);
                        this.addPreference(this.mBwiper);
                        this.addPreference(this.mAtmosphere_lighting);
                        this.addPreference(this.mRadar_stop);
                        this.addPreference(this.mHome_lighting);
                        this.addPreference(this.mWelcome_lig);
                        this.addPreference(this.mDaytime_lights);
                        this.addPreference(this.mSound);
                        this.addPreference(this.mOil_unit);
                        this.addPreference(this.mSetdoor);
                        this.addPreference(this.mTpms_cal);
                        this.addPreference(this.mOff_Warning_info);
                        this.addPreference(this.mAdaptive_light);
                        break;
                     default:
                        this.addPreference(this.mParking_assist);
                        this.addPreference(this.mBwiper);
                        this.addPreference(this.mAtmosphere_lighting);
                        this.addPreference(this.mRadar_stop);
                        this.addPreference(this.mHome_lighting);
                        this.addPreference(this.mWelcome_lig);
                        this.addPreference(this.mDaytime_lights);
                        this.addPreference(this.mSound);
                        this.addPreference(this.mOil_unit);
                        this.addPreference(this.mSetdoor);
                        this.addPreference(this.mTpms_cal);
                        this.addPreference(this.mOff_Warning_info);
                  }
               } else {
                  this.addPreference(this.mParking_assist);
                  this.addPreference(this.mBwiper);
                  this.addPreference(this.mAtmosphere_lighting);
                  this.addPreference(this.mRadar_stop);
                  this.addPreference(this.mHome_lighting);
                  this.addPreference(this.mWelcome_lig);
                  this.addPreference(this.mDaytime_lights);
                  this.addPreference(this.mSound);
                  this.addPreference(this.mOil_unit);
                  this.addPreference(this.mSetdoor);
                  this.addPreference(this.mTpms_cal);
                  this.addPreference(this.mOff_Warning_info);
                  this.addPreference(this.mUnlock_trunk_only);
               }
            } else {
               this.addPreference(this.mParking_assist);
               this.addPreference(this.mBwiper);
               this.addPreference(this.mAtmosphere_lighting);
               this.addPreference(this.mRadar_stop);
               this.addPreference(this.mHome_lighting);
               this.addPreference(this.mWelcome_lig);
               this.addPreference(this.mDaytime_lights);
               this.addPreference(this.mSound);
               this.addPreference(this.mOil_unit);
               this.addPreference(this.mSetdoor);
               this.addPreference(this.mTpms_cal);
               this.addPreference(this.mOff_Warning_info);
               this.addPreference(this.mLamp_no);
            }
         } else {
            this.addPreference(this.mParking_assist);
            this.addPreference(this.mBwiper);
            this.addPreference(this.mAtmosphere_lighting);
            this.addPreference(this.mRadar_stop);
            this.addPreference(this.mHome_lighting);
            this.addPreference(this.mWelcome_lig);
            this.addPreference(this.mDaytime_lights);
            this.addPreference(this.mSound);
            this.addPreference(this.mOil_unit);
            this.addPreference(this.mSetdoor);
            this.addPreference(this.mTpms_cal);
            this.addPreference(this.mOff_Warning_info);
            this.addPreference(this.mBlind_detection);
            this.addPreference(this.mEngine_stop);
            this.addPreference(this.Welcome_cmd);
         }

         this.addPreference(this.mTemp);
      }

      byte[] var8 = new byte[7];
      var8[0] = -112;
      this.SendCanBusCmdData2E((byte)-119, var8, 7);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mSpeed1;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = this.sum(var5, var2) + 0;
         var3 = this.speed1Data;
         if (var3 + var4 < 0 || var3 + var4 > 255 || !this.mSpeed1.getIsDow()) {
            return true;
         }

         this.SetCmdkey88((byte)64, (byte)1, (byte)(this.speed1Data + var4 & 255));
      } else {
         var5 = this.mSpeed2;
         if (var1 == var5) {
            var3 = this.sum(var5, var2) + 0;
            var4 = this.speed2Data;
            if (var4 + var3 < 0 || var4 + var3 > 255 || !this.mSpeed2.getIsDow()) {
               return true;
            }

            this.SetCmdkey88((byte)64, (byte)2, (byte)(this.speed2Data + var3 & 255));
         } else {
            var5 = this.mSpeed3;
            if (var1 == var5) {
               var3 = this.sum(var5, var2) + 0;
               var4 = this.speed3Data;
               if (var4 + var3 < 0 || var4 + var3 > 255 || !this.mSpeed3.getIsDow()) {
                  return true;
               }

               this.SetCmdkey88((byte)64, (byte)3, (byte)(this.speed3Data + var3 & 255));
            } else {
               var5 = this.mSpeed4;
               if (var1 == var5) {
                  var3 = this.sum(var5, var2) + 0;
                  var4 = this.speed4Data;
                  if (var4 + var3 < 0 || var4 + var3 > 255 || !this.mSpeed4.getIsDow()) {
                     return true;
                  }

                  this.SetCmdkey88((byte)64, (byte)4, (byte)(this.speed4Data + var3 & 255));
               } else {
                  var5 = this.mSpeed5;
                  if (var1 == var5) {
                     var3 = this.sum(var5, var2) + 0;
                     var4 = this.speed5Data;
                     if (var4 + var3 < 0 || var4 + var3 > 255 || !this.mSpeed5.getIsDow()) {
                        return true;
                     }

                     this.SetCmdkey88((byte)64, (byte)5, (byte)(this.speed5Data + var3 & 255));
                  }
               }
            }
         }
      }

      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var7) {
         return false;
      }

      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mAtmosphere_lighting) {
            this.SetCmdkey((byte)4, (byte)var3);
         } else if (var1 == this.mHome_lighting) {
            this.SetCmdkey((byte)6, (byte)var3);
         } else if (var1 == this.mWelcome_lig) {
            this.SetCmdkey((byte)7, (byte)var3);
         } else if (var1 == this.mSound) {
            this.SetCmdkey((byte)9, (byte)(var3 + 1));
         } else if (var1 == this.mOil_unit) {
            this.SetCmdkey((byte)10, (byte)(var3 + 0));
         } else if (var1 == this.mLanguages) {
            this.SetCmdkey((byte)11, (byte)(var3 + 0));
         } else if (var1 == this.mSetdoor) {
            this.SetCmdkey((byte)15, (byte)(var3 + 0));
         } else if (var1 == this.mLamp_no) {
            this.SetCmdkey((byte)18, (byte)(var3 + 0));
         } else if (var1 == this.mInstrument_color) {
            this.SetCmdkey((byte)22, (byte)(var3 + 0));
         } else if (var1 == this.mInstrument_style) {
            this.SetCmdkey((byte)23, (byte)(var3 + 0));
            SwitchboxPreference var9 = this.mInstrument_style;
            StringBuilder var8 = new StringBuilder();
            var8.append("");
            var8.append(var3);
            var9.setValue(var8.toString());
            System.putInt(this.getContentResolver(), "mInstrument_style", var3);
         } else if (var1 == this.mTemp) {
            this.SendCanBusCmdData2E((byte)-118, new byte[]{12, (byte)var3}, 2);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mParking_assist;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)1, (byte)1);
         }
      } else {
         var2 = this.mBwiper;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)2, (byte)0);
            } else {
               this.SetCmdkey((byte)2, (byte)1);
            }
         } else {
            var2 = this.mRadar_stop;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)5, (byte)0);
               } else {
                  this.SetCmdkey((byte)5, (byte)1);
               }
            } else {
               var2 = this.mDaytime_lights;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)8, (byte)0);
                  } else {
                     this.SetCmdkey((byte)8, (byte)1);
                  }
               } else {
                  var2 = this.mMemory_speed;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey88((byte)0, (byte)0, (byte)0);
                     } else {
                        this.SetCmdkey88((byte)64, (byte)0, (byte)0);
                     }
                  } else {
                     var2 = this.mBlind_detection;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)12, (byte)0);
                        } else {
                           this.SetCmdkey((byte)12, (byte)1);
                        }
                     } else {
                        var2 = this.mEngine_stop;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)13, (byte)0);
                           } else {
                              this.SetCmdkey((byte)13, (byte)1);
                           }
                        } else {
                           var2 = this.Welcome_cmd;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)14, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)14, (byte)1);
                              }
                           } else {
                              var2 = this.mUnlock_trunk_only;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)19, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)19, (byte)1);
                                 }
                              } else {
                                 var2 = this.mControl_system;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)20, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)20, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mFatigue;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)21, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)21, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mAutomatic_head;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)24, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)24, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mDriving_auto;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)25, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)25, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mOff_Warning_info;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   var2.setChecked(false);
                                                   System.putInt(this.getContentResolver(), "mOff_Warning_infor", 0);
                                                } else {
                                                   var2.setChecked(true);
                                                   System.putInt(this.getContentResolver(), "mOff_Warning_infor", 1);
                                                }
                                             } else {
                                                var2 = this.mAdaptive_light;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)26, (byte)0);
                                                   } else {
                                                      this.SetCmdkey((byte)26, (byte)1);
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.mTpms_cal) {
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297021)).setMessage(this.getString(2131297307)).setPositiveButton(this.getString(2131296522), new DialogInterface.OnClickListener(this) {
            final canbus61settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkey((byte)16, (byte)1);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296461), new DialogInterface.OnClickListener(this) {
            final canbus61settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               var1.cancel();
            }
         }).create().show();
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
