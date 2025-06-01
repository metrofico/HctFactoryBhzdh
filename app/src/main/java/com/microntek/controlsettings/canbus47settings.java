package com.microntek.controlsettings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class canbus47settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int data_mFoot = 256;
   private int data_mInCar_light = 256;
   private int data_mLeft_tempe = 256;
   private int data_mRight_tempe = 256;
   private int data_mWarning_at = 256;
   private HCheckBoxPreference mAir_switch;
   private HCheckBoxPreference mAuto_active;
   private HCheckBoxPreference mAuto_air;
   private SwitchboxPreference mAuto_cmd;
   private HCheckBoxPreference mAutolockcmd;
   private PreferenceCategory mAuxiliary;
   private HCheckBoxPreference mBlow_window;
   private HCheckBoxPreference mBlowing;
   private SwitchboxPreference mBvol;
   private SwitchboxPreference mBvol_on;
   private HCheckBoxPreference mBwindow;
   private HCheckBoxPreference mCar_light;
   private PreferenceCategory mCar_stop;
   private PreferenceCategory mCar_window_sync;
   private SwitchboxPreference mDoorlock;
   private SwitchboxPreference mEnergy;
   private HCheckBoxPreference mFeet;
   private OnSwitchPreference mFoot;
   private SwitchboxPreference mFvol;
   private SwitchboxPreference mFvol_on;
   private HCheckBoxPreference mFwindow;
   private SwitchboxPreference mGohome;
   private HCheckBoxPreference mHwindow;
   private OnSwitchPreference mInCar_light;
   private SwitchboxPreference mLeavehome;
   private OnSwitchPreference mLeft_tempe;
   private PreferenceCategory mLigh_cmd;
   private PreferenceCategory mMaintain;
   private PreferenceScreen mMnull;
   private SwitchboxPreference mRaining;
   private SwitchboxPreference mRange;
   private HCheckBoxPreference mRearview_mirror;
   private PreferenceScreen mReset;
   private OnSwitchPreference mRight_tempe;
   private SwitchboxPreference mSpeed;
   private HCheckBoxPreference mSpeed_warning;
   private SwitchboxPreference mTemp;
   private SwitchboxPreference mTpms;
   private HCheckBoxPreference mTrunklock;
   private PreferenceCategory mTry_cmd;
   private PreferenceCategory mUnit_cmd;
   private SwitchboxPreference mVolume;
   private OnSwitchPreference mWarning_at;
   private SwitchboxPreference mWind_speed;
   private SwitchboxPreference mWiper;
   private byte[] setData = new byte[20];
   private SharedPreferences sp;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)75, new byte[]{var1, var2}, 2);
   }

   private void SetCmdkeyID(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData5AA5(var1, new byte[]{var2, var3}, 2);
   }

   private void itemShow() {
      if (!this.sp.getBoolean("mRaining", true)) {
         this.getPreferenceScreen().removePreference(this.mRaining);
         this.getPreferenceScreen().removePreference(this.mAuxiliary);
      }

      if (!this.sp.getBoolean("tyres", true)) {
         this.getPreferenceScreen().removePreference(this.mWarning_at);
         this.getPreferenceScreen().removePreference(this.mSpeed_warning);
         this.getPreferenceScreen().removePreference(this.mTry_cmd);
      }

      if (!this.sp.getBoolean("mCar_light", true)) {
         this.getPreferenceScreen().removePreference(this.mCar_light);
      }

      if (!this.sp.getBoolean("mFoot", true)) {
         this.getPreferenceScreen().removePreference(this.mFoot);
      }

      if (!this.sp.getBoolean("mGohome", true)) {
         this.getPreferenceScreen().removePreference(this.mGohome);
      }

      if (!this.sp.getBoolean("mLeavehome", true)) {
         this.getPreferenceScreen().removePreference(this.mLeavehome);
      }

      if (!this.sp.getBoolean("mAuto_active", true)) {
         this.getPreferenceScreen().removePreference(this.mAuto_active);
      }

      boolean var4 = this.sp.getBoolean("mFwindow", true);
      byte var3 = 0;
      int var2;
      if (!var4) {
         this.getPreferenceScreen().removePreference(this.mFwindow);
         var2 = 1;
      } else {
         var2 = 0;
      }

      int var1 = var2;
      if (!this.sp.getBoolean("mBwindow", true)) {
         this.getPreferenceScreen().removePreference(this.mBwindow);
         var1 = var2 + 1;
      }

      var2 = var1;
      if (!this.sp.getBoolean("mHwindow", true)) {
         this.getPreferenceScreen().removePreference(this.mHwindow);
         var2 = var1 + 1;
      }

      var1 = var2;
      if (!this.sp.getBoolean("mAutolockcmd", true)) {
         this.getPreferenceScreen().removePreference(this.mAutolockcmd);
         var1 = var2 + 1;
      }

      var2 = var1;
      if (!this.sp.getBoolean("mRearview_mirror", true)) {
         this.getPreferenceScreen().removePreference(this.mRearview_mirror);
         var2 = var1 + 1;
      }

      var1 = var2;
      if (!this.sp.getBoolean("mTrunklock", true)) {
         this.getPreferenceScreen().removePreference(this.mTrunklock);
         var1 = var2 + 1;
      }

      var2 = var1;
      if (!this.sp.getBoolean("mDoorlock", true)) {
         this.getPreferenceScreen().removePreference(this.mDoorlock);
         var2 = var1 + 1;
      }

      if (var2 >= 7) {
         this.getPreferenceScreen().removePreference(this.mCar_window_sync);
      }

      byte var5 = var3;
      if (!this.sp.getBoolean("mRange", true)) {
         this.getPreferenceScreen().removePreference(this.mRange);
         var5 = 1;
      }

      var2 = var5;
      if (!this.sp.getBoolean("mSpeed", true)) {
         this.getPreferenceScreen().removePreference(this.mSpeed);
         var2 = var5 + 1;
      }

      var1 = var2;
      if (!this.sp.getBoolean("mTemp", true)) {
         this.getPreferenceScreen().removePreference(this.mTemp);
         var1 = var2 + 1;
      }

      var2 = var1;
      if (!this.sp.getBoolean("mVolume", true)) {
         this.getPreferenceScreen().removePreference(this.mVolume);
         var2 = var1 + 1;
      }

      var1 = var2;
      if (!this.sp.getBoolean("mEnergy", true)) {
         this.getPreferenceScreen().removePreference(this.mEnergy);
         var1 = var2 + 1;
      }

      var2 = var1;
      if (!this.sp.getBoolean("mTpms", true)) {
         this.getPreferenceScreen().removePreference(this.mTpms);
         var2 = var1 + 1;
      }

      if (var2 >= 6) {
         this.getPreferenceScreen().removePreference(this.mUnit_cmd);
      }

   }

   private void updataAri() {
      if ((this.setData[1] & 8) != 0) {
         this.mAuto_air.setChecked(true);
      } else {
         this.mAuto_air.setChecked(false);
      }

      byte[] var2 = this.setData;
      if ((var2[0] & 2) != 0) {
         this.mAuto_cmd.setValue("2");
      } else if ((var2[0] & 1) != 0) {
         this.mAuto_cmd.setValue("1");
      } else {
         this.mAuto_cmd.setValue("0");
      }

      var2 = this.setData;
      if (var2[4] == 3) {
         this.mFeet.setChecked(true);
         this.mBlow_window.setChecked(false);
         this.mBlowing.setChecked(false);
      } else if (var2[4] == 5) {
         this.mFeet.setChecked(true);
         this.mBlowing.setChecked(true);
         this.mBlow_window.setChecked(false);
      } else if (var2[4] == 6) {
         this.mBlowing.setChecked(true);
         this.mBlow_window.setChecked(false);
         this.mFeet.setChecked(false);
      } else if (var2[4] == 11) {
         this.mBlow_window.setChecked(true);
         this.mBlowing.setChecked(false);
         this.mFeet.setChecked(false);
      } else if (var2[4] == 12) {
         this.mBlow_window.setChecked(true);
         this.mFeet.setChecked(true);
         this.mBlowing.setChecked(false);
      } else if (var2[4] == 13) {
         this.mBlow_window.setChecked(true);
         this.mBlowing.setChecked(true);
         this.mFeet.setChecked(false);
      } else if (var2[4] == 14) {
         this.mBlow_window.setChecked(true);
         this.mBlowing.setChecked(true);
         this.mFeet.setChecked(true);
      } else {
         this.mBlow_window.setChecked(false);
         this.mBlowing.setChecked(false);
         this.mFeet.setChecked(false);
      }

      if ((this.setData[0] & 64) != 0) {
         this.mAir_switch.setChecked(true);
      } else {
         this.mAir_switch.setChecked(false);
      }

      SwitchboxPreference var4 = this.mWind_speed;
      StringBuilder var3 = new StringBuilder();
      var3.append("");
      var3.append(this.setData[5] & 7);
      var4.setValue(var3.toString());
      this.data_mLeft_tempe = this.setData[6] & 255;
      int var1 = this.data_mLeft_tempe;
      if (var1 > 31 && var1 != 254) {
         if (var1 >= 65) {
            this.mLeft_tempe.setValue("0", " HI ");
            this.data_mLeft_tempe = 65;
         } else {
            OnSwitchPreference var5 = this.mLeft_tempe;
            var3 = new StringBuilder();
            var3.append((double)this.data_mLeft_tempe * 0.5);
            var3.append(" ");
            var3.append(this.getString(2131296562));
            var5.setValue("0", var3.toString());
         }
      } else {
         this.mLeft_tempe.setValue("0", " LO ");
         this.data_mLeft_tempe = 31;
      }

      this.data_mRight_tempe = this.setData[7] & 255;
      var1 = this.data_mRight_tempe;
      if (var1 > 31 && var1 != 254) {
         if (var1 >= 65) {
            this.mRight_tempe.setValue("0", " HI ");
            this.data_mRight_tempe = 65;
         } else {
            OnSwitchPreference var7 = this.mRight_tempe;
            StringBuilder var6 = new StringBuilder();
            var6.append((double)this.data_mRight_tempe * 0.5);
            var6.append(" ");
            var6.append(this.getString(2131296562));
            var7.setValue("0", var6.toString());
         }
      } else {
         this.mRight_tempe.setValue("0", " LO ");
         this.data_mRight_tempe = 31;
      }

   }

   private void updataLight() {
      byte[] var1 = this.setData;
      if ((byte)(var1[3] & 12) == 12) {
         this.mGohome.setValue("2");
      } else if ((byte)(var1[3] & 8) == 8) {
         this.mGohome.setValue("1");
      } else if ((byte)(var1[3] & 4) == 4) {
         this.mGohome.setValue("0");
      }

      var1 = this.setData;
      if ((byte)(var1[3] & 3) == 3) {
         this.mLeavehome.setValue("3");
      } else if ((byte)(var1[3] & 2) == 2) {
         this.mLeavehome.setValue("2");
      } else if ((byte)(var1[3] & 1) == 1) {
         this.mLeavehome.setValue("1");
      } else {
         this.mLeavehome.setValue("0");
      }

      this.data_mFoot = this.setData[4] & 255;
      OnSwitchPreference var2 = this.mFoot;
      StringBuilder var3 = new StringBuilder();
      var3.append(this.data_mFoot);
      var3.append(" ");
      var3.append(this.getString(2131296425));
      var2.setValue("0", var3.toString());
      if ((byte)(this.setData[3] & 16) == 16) {
         this.mCar_light.setChecked(true);
      } else {
         this.mCar_light.setChecked(false);
      }

      this.data_mInCar_light = this.setData[5] & 255;
      var2 = this.mInCar_light;
      var3 = new StringBuilder();
      var3.append(this.data_mInCar_light);
      var3.append(" ");
      var3.append(this.getString(2131296425));
      var2.setValue("0", var3.toString());
      if ((this.setData[0] & 8) != 0) {
         this.sp.edit().putBoolean("mCar_light", true).commit();
      } else {
         this.sp.edit().putBoolean("mCar_light", false).commit();
      }

      if ((this.setData[0] & 4) != 0) {
         this.sp.edit().putBoolean("mFoot", true).commit();
      } else {
         this.sp.edit().putBoolean("mFoot", false).commit();
      }

      if ((this.setData[0] & 2) != 0) {
         this.sp.edit().putBoolean("mGohome", true).commit();
      } else {
         this.sp.edit().putBoolean("mGohome", false).commit();
      }

      if ((this.setData[0] & 1) != 0) {
         this.sp.edit().putBoolean("mLeavehome", true).commit();
      } else {
         this.sp.edit().putBoolean("mLeavehome", false).commit();
      }

   }

   private void updataParking() {
      byte var1 = this.setData[2];
      SwitchboxPreference var4 = this.mFvol;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      var5.append((var1 & 255) - 1);
      var4.setValue(var5.toString());
      var1 = this.setData[3];
      var4 = this.mFvol_on;
      var5 = new StringBuilder();
      var5.append("");
      var5.append((var1 & 255) - 1);
      var4.setValue(var5.toString());
      var1 = this.setData[4];
      SwitchboxPreference var8 = this.mBvol;
      StringBuilder var6 = new StringBuilder();
      var6.append("");
      var6.append((var1 & 255) - 1);
      var8.setValue(var6.toString());
      var1 = this.setData[5];
      var8 = this.mBvol_on;
      var6 = new StringBuilder();
      var6.append("");
      var6.append((var1 & 255) - 1);
      var8.setValue(var6.toString());
      HCheckBoxPreference var7 = this.mAuto_active;
      var1 = this.setData[1];
      boolean var3 = false;
      boolean var2;
      if ((var1 & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var7.setChecked(var2);
      SharedPreferences.Editor var9 = this.sp.edit();
      var2 = var3;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      }

      var9.putBoolean("mAuto_active", var2).commit();
   }

   private void updataRaining() {
      if ((this.setData[1] & 128) != 0) {
         this.mRaining.setValue("1");
      } else {
         this.mRaining.setValue("0");
      }

      if ((this.setData[0] & 128) != 0) {
         this.sp.edit().putBoolean("mRaining", true).commit();
      } else {
         this.sp.edit().putBoolean("mRaining", false).commit();
      }

   }

   private void updataTyres() {
      if ((this.setData[1] & 128) != 0) {
         this.mSpeed_warning.setChecked(true);
      } else {
         this.mSpeed_warning.setChecked(false);
      }

      this.data_mWarning_at = this.setData[2] & 255;
      OnSwitchPreference var1 = this.mWarning_at;
      StringBuilder var2 = new StringBuilder();
      var2.append(this.data_mWarning_at);
      var2.append(" km/h");
      var1.setValue("0", var2.toString());
      if ((this.setData[0] & 64) != 0) {
         this.sp.edit().putBoolean("tyres", true).commit();
      } else {
         this.sp.edit().putBoolean("tyres", false).commit();
      }

   }

   private void updataUnit() {
      byte[] var3 = this.setData;
      boolean var2 = true;
      if ((var3[1] & 128) != 0) {
         this.mRange.setValue("0");
      } else {
         this.mRange.setValue("1");
      }

      if ((this.setData[1] & 64) != 0) {
         this.mSpeed.setValue("0");
      } else {
         this.mSpeed.setValue("1");
      }

      if ((this.setData[1] & 32) != 0) {
         this.mTemp.setValue("0");
      } else {
         this.mTemp.setValue("1");
      }

      var3 = this.setData;
      if ((byte)(var3[1] >> 4 & 1) == 1) {
         this.mVolume.setValue("2");
      } else if ((byte)(var3[1] >> 3 & 1) == 1) {
         this.mVolume.setValue("1");
      } else {
         this.mVolume.setValue("0");
      }

      var3 = this.setData;
      if ((byte)(var3[1] & 6) == 6) {
         this.mEnergy.setValue("3");
      } else if ((byte)(var3[1] & 4) == 4) {
         this.mEnergy.setValue("2");
      } else if ((byte)(var3[1] & 2) == 2) {
         this.mEnergy.setValue("1");
      } else {
         this.mEnergy.setValue("0");
      }

      var3 = this.setData;
      if ((byte)(var3[2] & 128) == -128) {
         this.mTpms.setValue("2");
      } else if ((byte)(var3[2] & 64) == 64) {
         this.mTpms.setValue("1");
      } else {
         this.mTpms.setValue("0");
      }

      SharedPreferences.Editor var4 = this.sp.edit();
      boolean var1;
      if ((this.setData[0] & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var4 = var4.putBoolean("mRange", var1);
      if ((this.setData[0] & 64) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var4 = var4.putBoolean("mSpeed", var1);
      if ((this.setData[0] & 32) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var4 = var4.putBoolean("mTemp", var1);
      if ((this.setData[0] & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var4 = var4.putBoolean("mVolume", var1);
      if ((this.setData[0] & 8) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var4 = var4.putBoolean("mEnergy", var1);
      if ((this.setData[0] & 4) != 0) {
         var1 = var2;
      } else {
         var1 = false;
      }

      var4.putBoolean("mTpms", var1).commit();
   }

   private void updataWindow() {
      byte var1 = this.setData[2];
      boolean var3 = false;
      if ((var1 & 128) != 0) {
         this.mFwindow.setChecked(true);
      } else {
         this.mFwindow.setChecked(false);
      }

      if ((this.setData[2] & 64) != 0) {
         this.mBwindow.setChecked(true);
      } else {
         this.mBwindow.setChecked(false);
      }

      if ((this.setData[2] & 32) != 0) {
         this.mHwindow.setChecked(true);
      } else {
         this.mHwindow.setChecked(false);
      }

      if ((this.setData[3] & 128) != 0) {
         this.mAutolockcmd.setChecked(true);
      } else {
         this.mAutolockcmd.setChecked(false);
      }

      if ((this.setData[3] & 64) != 0) {
         this.mRearview_mirror.setChecked(true);
      } else {
         this.mRearview_mirror.setChecked(false);
      }

      if ((this.setData[3] & 16) != 0) {
         this.mTrunklock.setChecked(true);
      } else {
         this.mTrunklock.setChecked(false);
      }

      if ((this.setData[3] & 32) != 0) {
         this.mDoorlock.setValue("1");
      } else {
         this.mDoorlock.setValue("0");
      }

      SharedPreferences.Editor var4 = this.sp.edit();
      boolean var2;
      if ((this.setData[0] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4 = var4.putBoolean("mFwindow", var2);
      if ((this.setData[0] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4 = var4.putBoolean("mBwindow", var2);
      if ((this.setData[0] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4 = var4.putBoolean("mHwindow", var2);
      if ((this.setData[1] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4 = var4.putBoolean("mAutolockcmd", var2);
      if ((this.setData[1] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4 = var4.putBoolean("mRearview_mirror", var2);
      if ((this.setData[1] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var4 = var4.putBoolean("mTrunklock", var2);
      var2 = var3;
      if ((this.setData[1] & 32) != 0) {
         var2 = true;
      }

      var4.putBoolean("mDoorlock", var2).commit();
   }

   protected void ProcessData(byte[] var1) {
      byte var3 = 0;
      byte var5 = var1[0];
      int var4 = var1.length;
      if (var4 > 2) {
         int var2;
         if (var5 == 70) {
            for(var2 = 0; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            this.updataTyres();
         }

         if (var5 == 72) {
            for(var2 = 0; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            this.updataRaining();
         }

         if (var5 == 104) {
            for(var2 = 0; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            this.updataLight();
         }

         if (var5 == 100) {
            for(var2 = 0; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            this.updataWindow();
         }

         if (var5 == 113) {
            for(var2 = 0; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            this.updataParking();
         }

         if (var5 == -63) {
            for(var2 = 0; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            this.updataUnit();
         }

         if (var5 == -61) {
            for(var2 = 0; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            if ((this.setData[9] & 128) != 0) {
               this.mWiper.setValue("1");
            } else {
               this.mWiper.setValue("0");
            }
         }

         if (var5 == 49) {
            for(var2 = var3; var2 < var4; ++var2) {
               if (var2 >= 2) {
                  this.setData[var2 - 2] = var1[var2];
               }
            }

            this.updataAri();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492921);
      this.sp = this.getSharedPreferences("Shared47", 0);
      this.mRaining = (SwitchboxPreference)this.findPreference("raining");
      this.mGohome = (SwitchboxPreference)this.findPreference("gohome");
      this.mLeavehome = (SwitchboxPreference)this.findPreference("leavehome");
      this.mDoorlock = (SwitchboxPreference)this.findPreference("doorlock");
      this.mFvol = (SwitchboxPreference)this.findPreference("fvol");
      this.mFvol_on = (SwitchboxPreference)this.findPreference("fvol_on");
      this.mBvol = (SwitchboxPreference)this.findPreference("bvol");
      this.mBvol_on = (SwitchboxPreference)this.findPreference("bvol_on");
      this.mRange = (SwitchboxPreference)this.findPreference("range");
      this.mSpeed = (SwitchboxPreference)this.findPreference("speed");
      this.mTemp = (SwitchboxPreference)this.findPreference("temp");
      this.mVolume = (SwitchboxPreference)this.findPreference("volume");
      this.mEnergy = (SwitchboxPreference)this.findPreference("energy");
      this.mTpms = (SwitchboxPreference)this.findPreference("tpms");
      this.mWiper = (SwitchboxPreference)this.findPreference("wiper");
      this.mAuto_cmd = (SwitchboxPreference)this.findPreference("auto_cmd");
      this.mWind_speed = (SwitchboxPreference)this.findPreference("wind_speed");
      this.mWarning_at = (OnSwitchPreference)this.findPreference("warning_at");
      this.mFoot = (OnSwitchPreference)this.findPreference("foot");
      this.mInCar_light = (OnSwitchPreference)this.findPreference("incar_light");
      this.mLeft_tempe = (OnSwitchPreference)this.findPreference("left_tempe");
      this.mRight_tempe = (OnSwitchPreference)this.findPreference("right_tempe");
      this.mSpeed_warning = (HCheckBoxPreference)this.findPreference("speed_warning");
      HCheckBoxPreference var2 = this.mSpeed_warning;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mCar_light = (HCheckBoxPreference)this.findPreference("car_light");
      var2 = this.mCar_light;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mFwindow = (HCheckBoxPreference)this.findPreference("fwindow");
      var2 = this.mFwindow;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mBwindow = (HCheckBoxPreference)this.findPreference("bwindow");
      var2 = this.mBwindow;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mHwindow = (HCheckBoxPreference)this.findPreference("hwindow");
      var2 = this.mHwindow;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAuto_active = (HCheckBoxPreference)this.findPreference("auto_active");
      var2 = this.mAuto_active;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAutolockcmd = (HCheckBoxPreference)this.findPreference("autolockcmd");
      var2 = this.mAutolockcmd;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRearview_mirror = (HCheckBoxPreference)this.findPreference("rearview_mirror");
      var2 = this.mRearview_mirror;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mTrunklock = (HCheckBoxPreference)this.findPreference("trunklock");
      var2 = this.mTrunklock;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAuto_air = (HCheckBoxPreference)this.findPreference("auto_air");
      var2 = this.mAuto_air;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mBlow_window = (HCheckBoxPreference)this.findPreference("blow_window");
      var2 = this.mBlow_window;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mBlowing = (HCheckBoxPreference)this.findPreference("blowing");
      var2 = this.mBlowing;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mFeet = (HCheckBoxPreference)this.findPreference("feet");
      var2 = this.mFeet;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAir_switch = (HCheckBoxPreference)this.findPreference("air_switch");
      var2 = this.mAir_switch;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mReset = (PreferenceScreen)this.findPreference("reset");
      this.mMnull = (PreferenceScreen)this.findPreference("mnull");
      SwitchboxPreference var3 = this.mRaining;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mGohome;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mLeavehome;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mDoorlock;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mFvol;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mFvol_on;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mBvol;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mBvol_on;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mRange;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mSpeed;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mTemp;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mVolume;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mEnergy;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mTpms;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mWiper;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mAuto_cmd;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mWind_speed;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      OnSwitchPreference var4 = this.mWarning_at;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      var4 = this.mFoot;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      var4 = this.mInCar_light;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      var4 = this.mLeft_tempe;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      var4 = this.mRight_tempe;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      this.mTry_cmd = (PreferenceCategory)this.findPreference("try_cmd");
      this.mAuxiliary = (PreferenceCategory)this.findPreference("auxiliary");
      this.mCar_window_sync = (PreferenceCategory)this.findPreference("car_window_sync");
      this.mUnit_cmd = (PreferenceCategory)this.findPreference("unit_cmd");
      this.mLigh_cmd = (PreferenceCategory)this.findPreference("ligh_cmd");
      this.mCar_stop = (PreferenceCategory)this.findPreference("car_stop");
      this.mMaintain = (PreferenceCategory)this.findPreference("maintain");
      if (this.getIntent().getIntExtra("cftype", 0) == 1) {
         this.getPreferenceScreen().removePreference(this.mRaining);
         this.getPreferenceScreen().removePreference(this.mGohome);
         this.getPreferenceScreen().removePreference(this.mLeavehome);
         this.getPreferenceScreen().removePreference(this.mDoorlock);
         this.getPreferenceScreen().removePreference(this.mFvol);
         this.getPreferenceScreen().removePreference(this.mFvol_on);
         this.getPreferenceScreen().removePreference(this.mBvol);
         this.getPreferenceScreen().removePreference(this.mBvol_on);
         this.getPreferenceScreen().removePreference(this.mRange);
         this.getPreferenceScreen().removePreference(this.mSpeed);
         this.getPreferenceScreen().removePreference(this.mTemp);
         this.getPreferenceScreen().removePreference(this.mVolume);
         this.getPreferenceScreen().removePreference(this.mEnergy);
         this.getPreferenceScreen().removePreference(this.mTpms);
         this.getPreferenceScreen().removePreference(this.mWiper);
         this.getPreferenceScreen().removePreference(this.mSpeed_warning);
         this.getPreferenceScreen().removePreference(this.mCar_light);
         this.getPreferenceScreen().removePreference(this.mFwindow);
         this.getPreferenceScreen().removePreference(this.mBwindow);
         this.getPreferenceScreen().removePreference(this.mHwindow);
         this.getPreferenceScreen().removePreference(this.mAutolockcmd);
         this.getPreferenceScreen().removePreference(this.mRearview_mirror);
         this.getPreferenceScreen().removePreference(this.mTrunklock);
         this.getPreferenceScreen().removePreference(this.mAuto_active);
         this.getPreferenceScreen().removePreference(this.mWarning_at);
         this.getPreferenceScreen().removePreference(this.mFoot);
         this.getPreferenceScreen().removePreference(this.mInCar_light);
         this.getPreferenceScreen().removePreference(this.mTry_cmd);
         this.getPreferenceScreen().removePreference(this.mAuxiliary);
         this.getPreferenceScreen().removePreference(this.mCar_window_sync);
         this.getPreferenceScreen().removePreference(this.mUnit_cmd);
         this.getPreferenceScreen().removePreference(this.mLigh_cmd);
         this.getPreferenceScreen().removePreference(this.mCar_stop);
         this.getPreferenceScreen().removePreference(this.mMaintain);
         this.getPreferenceScreen().removePreference(this.mReset);
      } else {
         this.itemShow();
         this.getPreferenceScreen().removePreference(this.mAuto_air);
         this.getPreferenceScreen().removePreference(this.mAuto_cmd);
         this.getPreferenceScreen().removePreference(this.mBlow_window);
         this.getPreferenceScreen().removePreference(this.mBlowing);
         this.getPreferenceScreen().removePreference(this.mFeet);
         this.getPreferenceScreen().removePreference(this.mMnull);
         this.getPreferenceScreen().removePreference(this.mWind_speed);
         this.getPreferenceScreen().removePreference(this.mAir_switch);
         this.getPreferenceScreen().removePreference(this.mLeft_tempe);
         this.getPreferenceScreen().removePreference(this.mRight_tempe);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mWarning_at;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var3 = this.data_mWarning_at;
         if (var3 + var4 < 0 || var3 + var4 > 255 || !this.mWarning_at.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)3, (byte)(this.data_mWarning_at + var4 & 255));
      } else {
         var5 = this.mFoot;
         if (var1 == var5) {
            var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var4 = this.data_mFoot;
            if (var4 + var3 < 0 || var4 + var3 > 100 || !this.mFoot.getIsDow()) {
               return true;
            }

            this.SetCmdkeyID((byte)109, (byte)15, (byte)(this.data_mFoot + var3 & 255));
         } else {
            var5 = this.mInCar_light;
            if (var1 == var5) {
               var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
               var3 = this.data_mInCar_light;
               if (var3 + var4 < 0 || var3 + var4 > 100 || !this.mInCar_light.getIsDow()) {
                  return true;
               }

               this.SetCmdkeyID((byte)109, (byte)17, (byte)(this.data_mInCar_light + var4 & 255));
            } else {
               var5 = this.mLeft_tempe;
               if (var1 == var5) {
                  if (!var5.getIsDow()) {
                     return true;
                  }

                  var3 = Integer.parseInt((String)this.mLeft_tempe.getEntries()[Integer.parseInt((String)var2)]) + 0;
                  var4 = this.data_mLeft_tempe;
                  if (var4 + var3 <= 31) {
                     this.SetCmdkeyID((byte)58, (byte)8, (byte)-2);
                  } else if (var4 + var3 >= 65) {
                     this.SetCmdkeyID((byte)58, (byte)8, (byte)-1);
                  } else {
                     this.SetCmdkeyID((byte)58, (byte)8, (byte)(var4 + var3 & 255));
                  }
               } else {
                  var5 = this.mRight_tempe;
                  if (var1 == var5) {
                     if (!var5.getIsDow()) {
                        return true;
                     }

                     var3 = Integer.parseInt((String)this.mRight_tempe.getEntries()[Integer.parseInt((String)var2)]) + 0;
                     var4 = this.data_mRight_tempe;
                     if (var4 + var3 <= 31) {
                        this.SetCmdkeyID((byte)58, (byte)9, (byte)-2);
                     } else if (var4 + var3 >= 65) {
                        this.SetCmdkeyID((byte)58, (byte)9, (byte)-1);
                     } else {
                        this.SetCmdkeyID((byte)58, (byte)9, (byte)(var4 + var3 & 255));
                     }
                  }
               }
            }
         }
      }

      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var7) {
         return false;
      }

      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mRaining) {
            this.SendCanBusCmdData5AA5((byte)77, new byte[]{1, (byte)var4}, 2);
         } else if (var1 == this.mWarning_at) {
            this.SetCmdkey((byte)1, (byte)var4);
         } else if (var1 == this.mGohome) {
            this.SetCmdkeyID((byte)109, (byte)13, (byte)(var4 + 1));
         } else if (var1 == this.mLeavehome) {
            this.SetCmdkeyID((byte)109, (byte)14, (byte)(var4 + 0));
         } else if (var1 == this.mDoorlock) {
            this.SetCmdkeyID((byte)111, (byte)7, (byte)var4);
         } else if (var1 == this.mFvol) {
            this.SetCmdkeyID((byte)122, (byte)1, (byte)(var4 + 1));
         } else if (var1 == this.mFvol_on) {
            this.SetCmdkeyID((byte)122, (byte)2, (byte)(var4 + 1));
         } else if (var1 == this.mBvol) {
            this.SetCmdkeyID((byte)122, (byte)3, (byte)(var4 + 1));
         } else if (var1 == this.mBvol_on) {
            this.SetCmdkeyID((byte)122, (byte)4, (byte)(var4 + 1));
         } else if (var1 == this.mRange) {
            this.SetCmdkeyID((byte)-54, (byte)1, (byte)(var4 + 1));
         } else if (var1 == this.mSpeed) {
            this.SetCmdkeyID((byte)-54, (byte)2, (byte)(var4 + 1));
         } else if (var1 == this.mTemp) {
            this.SetCmdkeyID((byte)-54, (byte)3, (byte)(var4 + 1));
         } else if (var1 == this.mVolume) {
            this.SetCmdkeyID((byte)-54, (byte)4, (byte)(var4 + 1));
         } else if (var1 == this.mEnergy) {
            this.SetCmdkeyID((byte)-54, (byte)5, (byte)(var4 + 1));
         } else if (var1 == this.mTpms) {
            this.SetCmdkeyID((byte)-54, (byte)6, (byte)(var4 + 1));
         } else if (var1 == this.mWiper) {
            this.SetCmdkeyID((byte)-52, (byte)2, (byte)(var4 + 0));
         } else if (var1 == this.mAuto_cmd) {
            this.SetCmdkeyID((byte)58, (byte)2, (byte)(var4 + 0));
         } else if (var1 == this.mWind_speed) {
            this.SetCmdkeyID((byte)58, (byte)7, (byte)(var4 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mSpeed_warning;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)2, (byte)0);
         } else {
            this.SetCmdkey((byte)2, (byte)1);
         }
      } else {
         var2 = this.mCar_light;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkeyID((byte)109, (byte)16, (byte)0);
            } else {
               this.SetCmdkeyID((byte)109, (byte)16, (byte)1);
            }
         } else {
            var2 = this.mFwindow;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkeyID((byte)111, (byte)1, (byte)0);
               } else {
                  this.SetCmdkeyID((byte)111, (byte)1, (byte)1);
               }
            } else {
               var2 = this.mBwindow;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkeyID((byte)111, (byte)2, (byte)0);
                  } else {
                     this.SetCmdkeyID((byte)111, (byte)2, (byte)1);
                  }
               } else {
                  var2 = this.mHwindow;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkeyID((byte)111, (byte)3, (byte)0);
                     } else {
                        this.SetCmdkeyID((byte)111, (byte)3, (byte)1);
                     }
                  } else {
                     var2 = this.mAutolockcmd;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkeyID((byte)111, (byte)5, (byte)0);
                        } else {
                           this.SetCmdkeyID((byte)111, (byte)5, (byte)1);
                        }
                     } else {
                        var2 = this.mRearview_mirror;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkeyID((byte)111, (byte)6, (byte)0);
                           } else {
                              this.SetCmdkeyID((byte)111, (byte)6, (byte)1);
                           }
                        } else {
                           var2 = this.mTrunklock;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkeyID((byte)111, (byte)8, (byte)0);
                              } else {
                                 this.SetCmdkeyID((byte)111, (byte)8, (byte)1);
                              }
                           } else {
                              var2 = this.mAuto_active;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkeyID((byte)122, (byte)5, (byte)0);
                                 } else {
                                    this.SetCmdkeyID((byte)122, (byte)5, (byte)1);
                                 }
                              } else {
                                 var2 = this.mAuto_air;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkeyID((byte)58, (byte)1, (byte)0);
                                    } else {
                                       this.SetCmdkeyID((byte)58, (byte)1, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mBlow_window;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkeyID((byte)58, (byte)3, (byte)0);
                                       } else {
                                          this.SetCmdkeyID((byte)58, (byte)3, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mBlowing;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkeyID((byte)58, (byte)4, (byte)0);
                                          } else {
                                             this.SetCmdkeyID((byte)58, (byte)4, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mFeet;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkeyID((byte)58, (byte)5, (byte)0);
                                             } else {
                                                this.SetCmdkeyID((byte)58, (byte)5, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mAir_switch;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkeyID((byte)58, (byte)6, (byte)0);
                                                } else {
                                                   this.SetCmdkeyID((byte)58, (byte)6, (byte)1);
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
      if (var2 == this.mReset) {
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297022)).setMessage(this.getString(2131297176)).setPositiveButton(this.getString(2131296523), new DialogInterface.OnClickListener(this) {
            final canbus47settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkeyID((byte)-52, (byte)1, (byte)1);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296462), new DialogInterface.OnClickListener(this) {
            final canbus47settings this$0;

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
