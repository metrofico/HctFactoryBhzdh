package com.microntek.controlsettings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;

public class canbus115settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Rdjus = 0;
   private HCheckBoxPreference mAcc_car;
   private HCheckBoxPreference mAccess_beep;
   private OnSwitchPreference mAdjust_outside;
   private SwitchboxPreference mAlarm_vol;
   private SwitchboxPreference mAttention;
   private SwitchboxPreference mAuto_light;
   private HCheckBoxPreference mAwd_info;
   private HCheckBoxPreference mBacklight;
   private HCheckBoxPreference mCtm_system;
   private SwitchboxPreference mDanger_ahead;
   private PreferenceScreen mDefault_all;
   private SwitchboxPreference mDeviate;
   private SwitchboxPreference mDoor_unlock_mode;
   private SwitchboxPreference mDriver_monitor;
   private HCheckBoxPreference mElectric_open;
   private HCheckBoxPreference mExit_seat;
   private HCheckBoxPreference mFatigue_info;
   private SwitchboxPreference mHeadlight;
   private HCheckBoxPreference mHeadlight_wiper;
   private SwitchboxPreference mIllumination;
   private SwitchboxPreference mInterior;
   private SwitchboxPreference mKey_mode;
   private HCheckBoxPreference mKeyless;
   private HCheckBoxPreference mKeyless_access_light;
   private SwitchboxPreference mLock_with;
   private HCheckBoxPreference mNotifications;
   private HCheckBoxPreference mPosition_linkage;
   private SwitchboxPreference mRemote_setting;
   private HCheckBoxPreference mRemote_startoff;
   private PreferenceScreen mReset_info;
   private HCheckBoxPreference mReverse_vol;
   private SwitchboxPreference mSecurity;
   private SwitchboxPreference mSpeed_distance_units;
   private HCheckBoxPreference mStart_stop_dis;
   private HCheckBoxPreference mStop_lkas;
   private HCheckBoxPreference mTachometer;
   private HCheckBoxPreference mTachometer_settings;
   private PreferenceScreen mTpms_check;
   private SwitchboxPreference mTrip_a;
   private SwitchboxPreference mTrip_b;
   private SwitchboxPreference mUnlock_with;
   private SwitchboxPreference mVoice_alarm;
   private HCheckBoxPreference mWalk_away_auto_lock;
   private byte[] setData = new byte[20];

   private void SetCmdkey(byte var1) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{96, var1}, 2);
   }

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   public void InitUi() {
      this.addPreferencesFromResource(2131492869);
      this.mCtm_system = (HCheckBoxPreference)this.findPreference("ctm_system");
      int var1 = System.getInt(this.getContentResolver(), "com.microntek.control36settings", 1);
      HCheckBoxPreference var3 = this.mCtm_system;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
         var3 = this.mCtm_system;
         boolean var2;
         if (var1 == 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var3.setChecked(var2);
      }

      this.mKeyless = (HCheckBoxPreference)this.findPreference("keyless");
      this.mKeyless.setOnPreferenceClickListener(this);
      this.mAccess_beep = (HCheckBoxPreference)this.findPreference("access_beep");
      this.mAccess_beep.setOnPreferenceClickListener(this);
      this.mBacklight = (HCheckBoxPreference)this.findPreference("backlight");
      this.mBacklight.setOnPreferenceClickListener(this);
      this.mNotifications = (HCheckBoxPreference)this.findPreference("notifications");
      this.mNotifications.setOnPreferenceClickListener(this);
      this.mTachometer = (HCheckBoxPreference)this.findPreference("tachometer");
      this.mTachometer.setOnPreferenceClickListener(this);
      this.mWalk_away_auto_lock = (HCheckBoxPreference)this.findPreference("walk_away_auto_lock");
      this.mWalk_away_auto_lock.setOnPreferenceClickListener(this);
      this.mRemote_startoff = (HCheckBoxPreference)this.findPreference("remote_startoff");
      this.mRemote_startoff.setOnPreferenceClickListener(this);
      this.mKeyless_access_light = (HCheckBoxPreference)this.findPreference("keyless_access_light");
      this.mKeyless_access_light.setOnPreferenceClickListener(this);
      this.mHeadlight_wiper = (HCheckBoxPreference)this.findPreference("headlight_wiper");
      this.mHeadlight_wiper.setOnPreferenceClickListener(this);
      this.mStart_stop_dis = (HCheckBoxPreference)this.findPreference("start_stop_dis");
      this.mStart_stop_dis.setOnPreferenceClickListener(this);
      this.mAcc_car = (HCheckBoxPreference)this.findPreference("acc_car");
      this.mAcc_car.setOnPreferenceClickListener(this);
      this.mStop_lkas = (HCheckBoxPreference)this.findPreference("stop_lkas");
      this.mStop_lkas.setOnPreferenceClickListener(this);
      this.mTachometer_settings = (HCheckBoxPreference)this.findPreference("tachometer_settings");
      this.mTachometer_settings.setOnPreferenceClickListener(this);
      this.mElectric_open = (HCheckBoxPreference)this.findPreference("electric_open");
      this.mElectric_open.setOnPreferenceClickListener(this);
      this.mAdjust_outside = (OnSwitchPreference)this.findPreference("adjust_outside");
      this.mAdjust_outside.setOnPreferenceChangeListener(this);
      this.mTrip_a = (SwitchboxPreference)this.findPreference("trip_a");
      this.mTrip_a.setOnPreferenceChangeListener(this);
      this.mTrip_b = (SwitchboxPreference)this.findPreference("trip_b");
      this.mTrip_b.setOnPreferenceChangeListener(this);
      this.mInterior = (SwitchboxPreference)this.findPreference("interior");
      this.mInterior.setOnPreferenceChangeListener(this);
      this.mHeadlight = (SwitchboxPreference)this.findPreference("headlight");
      this.mHeadlight.setOnPreferenceChangeListener(this);
      this.mAuto_light = (SwitchboxPreference)this.findPreference("auto_light");
      this.mAuto_light.setOnPreferenceChangeListener(this);
      this.mLock_with = (SwitchboxPreference)this.findPreference("lock_with");
      this.mLock_with.setOnPreferenceChangeListener(this);
      this.mUnlock_with = (SwitchboxPreference)this.findPreference("unlock_with");
      this.mUnlock_with.setOnPreferenceChangeListener(this);
      this.mKey_mode = (SwitchboxPreference)this.findPreference("key_mode");
      this.mKey_mode.setOnPreferenceChangeListener(this);
      this.mSecurity = (SwitchboxPreference)this.findPreference("security");
      this.mSecurity.setOnPreferenceChangeListener(this);
      this.mAlarm_vol = (SwitchboxPreference)this.findPreference("alarm_vol");
      this.mAlarm_vol.setOnPreferenceChangeListener(this);
      this.mSpeed_distance_units = (SwitchboxPreference)this.findPreference("speed_distance_units");
      this.mSpeed_distance_units.setOnPreferenceChangeListener(this);
      this.mDoor_unlock_mode = (SwitchboxPreference)this.findPreference("door_unlock_mode");
      this.mDoor_unlock_mode.setOnPreferenceChangeListener(this);
      this.mIllumination = (SwitchboxPreference)this.findPreference("illumination");
      this.mIllumination.setOnPreferenceChangeListener(this);
      this.mVoice_alarm = (SwitchboxPreference)this.findPreference("voice_alarm");
      this.mVoice_alarm.setOnPreferenceChangeListener(this);
      this.mDanger_ahead = (SwitchboxPreference)this.findPreference("danger_ahead");
      this.mDanger_ahead.setOnPreferenceChangeListener(this);
      this.mDeviate = (SwitchboxPreference)this.findPreference("deviate");
      this.mDeviate.setOnPreferenceChangeListener(this);
      this.mDriver_monitor = (SwitchboxPreference)this.findPreference("driver_monitor");
      this.mDriver_monitor.setOnPreferenceChangeListener(this);
      this.mRemote_setting = (SwitchboxPreference)this.findPreference("remote_setting");
      this.mRemote_setting.setOnPreferenceChangeListener(this);
      this.mTpms_check = (PreferenceScreen)this.findPreference("tpms_check");
      this.mReset_info = (PreferenceScreen)this.findPreference("reset_info");
      this.mDefault_all = (PreferenceScreen)this.findPreference("default_all");
      this.mReverse_vol = this.findHCheckBoxPreference("reverse_vol");
      this.mPosition_linkage = this.findHCheckBoxPreference("position_linkage");
      this.mExit_seat = this.findHCheckBoxPreference("exit_seat");
      this.mFatigue_info = this.findHCheckBoxPreference("fatigue_info");
      this.mAwd_info = this.findHCheckBoxPreference("awd_info");
      this.mAttention = this.findSwitchboxPreference("attention");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{50, 0}, 2);
      this.removePreference((Preference)this.mCtm_system);
      this.removePreference((Preference)this.mDriver_monitor);
      var1 = this.getCarType();
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 == 3) {
               this.removeAll();
               this.publicSeting();
               this.addPreference(this.mReset_info);
               this.addPreference(this.mTpms_check);
               this.addPreference(this.mAlarm_vol);
               this.addPreference(this.mBacklight);
               this.addPreference(this.mWalk_away_auto_lock);
               this.addPreference(this.mRemote_startoff);
               this.addPreference(this.mDoor_unlock_mode);
               this.addPreference(this.mKeyless_access_light);
               this.addPreference(this.mIllumination);
               this.addPreference(this.mHeadlight_wiper);
               this.addPreference(this.mStart_stop_dis);
               this.addPreference(this.mVoice_alarm);
               this.addPreference(this.mTachometer_settings);
               this.addPreference(this.mRemote_setting);
               this.addPreference(this.mElectric_open);
               this.addPreference(this.mAttention);
               this.addPreference(this.mFatigue_info);
               this.addPreference(this.mAwd_info);
               return;
            }

            if (var1 != 4) {
               this.removeAll();
               this.publicSeting();
               return;
            }
         }

         this.removeAll();
         this.publicSeting();
         this.addPreference(this.mReset_info);
         this.addPreference(this.mTpms_check);
         this.addPreference(this.mAlarm_vol);
         this.addPreference(this.mBacklight);
         this.addPreference(this.mWalk_away_auto_lock);
         this.addPreference(this.mRemote_startoff);
         this.addPreference(this.mDoor_unlock_mode);
         this.addPreference(this.mKeyless_access_light);
         this.addPreference(this.mIllumination);
         this.addPreference(this.mHeadlight_wiper);
         this.addPreference(this.mStart_stop_dis);
         this.addPreference(this.mVoice_alarm);
         this.addPreference(this.mReverse_vol);
         this.addPreference(this.mRemote_setting);
         this.addPreference(this.mElectric_open);
         this.addPreference(this.mPosition_linkage);
         this.addPreference(this.mExit_seat);
      } else {
         this.removeAll();
         this.publicSeting();
         this.addPreference(this.mReset_info);
         this.addPreference(this.mTpms_check);
         this.addPreference(this.mAlarm_vol);
         this.addPreference(this.mBacklight);
         this.addPreference(this.mNotifications);
         this.addPreference(this.mSpeed_distance_units);
         this.addPreference(this.mTachometer);
         this.addPreference(this.mWalk_away_auto_lock);
         this.addPreference(this.mRemote_startoff);
         this.addPreference(this.mDoor_unlock_mode);
         this.addPreference(this.mKeyless_access_light);
         this.addPreference(this.mIllumination);
         this.addPreference(this.mHeadlight_wiper);
         this.addPreference(this.mStart_stop_dis);
         this.addPreference(this.mVoice_alarm);
         this.addPreference(this.mDanger_ahead);
         this.addPreference(this.mAcc_car);
         this.addPreference(this.mStop_lkas);
         this.addPreference(this.mDeviate);
         this.addPreference(this.mTachometer_settings);
      }

   }

   protected void ProcessData(byte[] var1) {
      boolean var5 = false;
      boolean var4 = false;
      int var2 = var1[0];
      int var3 = var1.length;
      if (var3 > 2) {
         if (var2 == -48) {
            if (var1[2] == 96) {
               System.putInt(this.getContentResolver(), "com.microntek.control36settings", var1[3]);
               HCheckBoxPreference var6 = this.mCtm_system;
               if (var1[3] == 0) {
                  var4 = true;
               }

               var6.setChecked(var4);
            }
         } else if (var2 == 50) {
            for(var2 = 2; var2 < var3; ++var2) {
               this.setData[var2 - 2] = var1[var2];
            }

            var1 = this.setData;
            if ((var1[0] & 15) > 10) {
               var2 = 0;
            } else {
               var2 = var1[0] & 15;
            }

            this._Rdjus = var2;
            OnSwitchPreference var7 = this.mAdjust_outside;
            StringBuilder var11 = new StringBuilder();
            var11.append("");
            var11.append(this._Rdjus - 5);
            var7.setValue("0", var11.toString());
            SwitchboxPreference var8 = this.mTrip_a;
            var11 = new StringBuilder();
            var11.append("");
            var11.append(this.setData[0] >> 4 & 3);
            var8.setValue(var11.toString());
            SwitchboxPreference var12 = this.mTrip_b;
            StringBuilder var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[0] >> 6 & 3);
            var12.setValue(var9.toString());
            var12 = this.mInterior;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[1] & 3);
            var12.setValue(var9.toString());
            var8 = this.mHeadlight;
            var11 = new StringBuilder();
            var11.append("");
            var11.append(this.setData[1] >> 2 & 3);
            var8.setValue(var11.toString());
            var12 = this.mAuto_light;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[1] >> 4 & 7);
            var12.setValue(var9.toString());
            var8 = this.mLock_with;
            var11 = new StringBuilder();
            var11.append("");
            var11.append(this.setData[2] & 3);
            var8.setValue(var11.toString());
            var12 = this.mUnlock_with;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[2] >> 2 & 3);
            var12.setValue(var9.toString());
            var12 = this.mKey_mode;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[2] >> 6 & 1);
            var12.setValue(var9.toString());
            HCheckBoxPreference var10 = this.mKeyless;
            if ((this.setData[2] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var12 = this.mSecurity;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[2] >> 4 & 3);
            var12.setValue(var9.toString());
            var10 = this.mAccess_beep;
            if ((this.setData[3] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var12 = this.mAlarm_vol;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[4] >> 6 & 3);
            var12.setValue(var9.toString());
            var10 = this.mBacklight;
            if ((this.setData[4] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mNotifications;
            if ((this.setData[4] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var12 = this.mSpeed_distance_units;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[4] >> 3 & 1);
            var12.setValue(var9.toString());
            var10 = this.mTachometer;
            if ((this.setData[4] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mWalk_away_auto_lock;
            if ((this.setData[4] & 2) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mRemote_startoff;
            if ((this.setData[3] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var12 = this.mDoor_unlock_mode;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[3] >> 4 & 1);
            var12.setValue(var9.toString());
            var10 = this.mKeyless_access_light;
            if ((this.setData[3] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var8 = this.mIllumination;
            var11 = new StringBuilder();
            var11.append("");
            var11.append(this.setData[3] & 7);
            var8.setValue(var11.toString());
            var10 = this.mHeadlight_wiper;
            if ((this.setData[4] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mStart_stop_dis;
            if ((this.setData[5] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var8 = this.mVoice_alarm;
            var11 = new StringBuilder();
            var11.append("");
            var11.append(this.setData[5] >> 7 & 1);
            var8.setValue(var11.toString());
            var8 = this.mDanger_ahead;
            var11 = new StringBuilder();
            var11.append("");
            var11.append(this.setData[5] >> 2 & 3);
            var8.setValue(var11.toString());
            var10 = this.mAcc_car;
            if ((this.setData[5] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mStop_lkas;
            if ((this.setData[5] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var12 = this.mDeviate;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[5] & 3);
            var12.setValue(var9.toString());
            var10 = this.mTachometer_settings;
            if ((this.setData[6] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var8 = this.mRemote_setting;
            var11 = new StringBuilder();
            var11.append("");
            var11.append(this.setData[6] >> 5 & 1);
            var8.setValue(var11.toString());
            var10 = this.mElectric_open;
            if ((this.setData[6] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mReverse_vol;
            if ((this.setData[6] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mPosition_linkage;
            if ((this.setData[6] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mExit_seat;
            if ((this.setData[6] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mFatigue_info;
            if ((this.setData[7] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var10.setChecked(var4);
            var10 = this.mAwd_info;
            var4 = var5;
            if ((this.setData[7] & 16) != 0) {
               var4 = true;
            }

            var10.setChecked(var4);
            var8 = this.mAttention;
            var11 = new StringBuilder();
            var11.append("");
            var11.append((this.setData[7] & 192) >> 6);
            var8.setValue(var11.toString());
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.InitUi();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mAdjust_outside;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var3 = this._Rdjus;
         if (var3 + var4 < 0 || var3 + var4 > 10 || !this.mAdjust_outside.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)0, (byte)(this._Rdjus + var4 & 255));
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
         if (var1 == this.mTrip_a) {
            this.SetCmdkey((byte)2, (byte)var3);
         } else if (var1 == this.mTrip_b) {
            this.SetCmdkey((byte)3, (byte)var3);
         } else if (var1 == this.mInterior) {
            this.SetCmdkey((byte)4, (byte)var3);
         } else if (var1 == this.mHeadlight) {
            this.SetCmdkey((byte)5, (byte)var3);
         } else if (var1 == this.mAuto_light) {
            this.SetCmdkey((byte)6, (byte)var3);
         } else if (var1 == this.mLock_with) {
            this.SetCmdkey((byte)7, (byte)var3);
         } else if (var1 == this.mUnlock_with) {
            this.SetCmdkey((byte)8, (byte)var3);
         } else if (var1 == this.mKey_mode) {
            this.SetCmdkey((byte)9, (byte)var3);
         } else if (var1 == this.mSecurity) {
            this.SetCmdkey((byte)11, (byte)var3);
         } else if (var1 == this.mAlarm_vol) {
            this.SetCmdkey((byte)18, (byte)var3);
         } else if (var1 == this.mSpeed_distance_units) {
            this.SetCmdkey((byte)21, (byte)var3);
         } else if (var1 == this.mDoor_unlock_mode) {
            this.SetCmdkey((byte)25, (byte)var3);
         } else if (var1 == this.mIllumination) {
            this.SetCmdkey((byte)27, (byte)var3);
         } else if (var1 == this.mVoice_alarm) {
            this.SetCmdkey((byte)30, (byte)var3);
         } else if (var1 == this.mDanger_ahead) {
            this.SetCmdkey((byte)31, (byte)var3);
         } else if (var1 == this.mDeviate) {
            this.SetCmdkey((byte)34, (byte)var3);
         } else if (var1 == this.mDriver_monitor) {
            this.SetCmdkey((byte)36, (byte)var3);
         } else if (var1 == this.mRemote_setting) {
            this.SetCmdkey((byte)37, (byte)var3);
         } else if (var1 == this.mAttention) {
            this.SetCmdkey((byte)41, (byte)var3);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mCtm_system;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)0);
         } else {
            this.SetCmdkey((byte)1);
         }
      } else {
         var2 = this.mKeyless;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)10, (byte)0);
            } else {
               this.SetCmdkey((byte)10, (byte)1);
            }
         } else {
            var2 = this.mAccess_beep;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)13, (byte)0);
               } else {
                  this.SetCmdkey((byte)13, (byte)1);
               }
            } else {
               var2 = this.mBacklight;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)19, (byte)0);
                  } else {
                     this.SetCmdkey((byte)19, (byte)1);
                  }
               } else {
                  var2 = this.mNotifications;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)20, (byte)0);
                     } else {
                        this.SetCmdkey((byte)20, (byte)1);
                     }
                  } else {
                     var2 = this.mTachometer;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)22, (byte)0);
                        } else {
                           this.SetCmdkey((byte)22, (byte)1);
                        }
                     } else {
                        var2 = this.mWalk_away_auto_lock;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)23, (byte)0);
                           } else {
                              this.SetCmdkey((byte)23, (byte)1);
                           }
                        } else {
                           var2 = this.mRemote_startoff;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)24, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)24, (byte)1);
                              }
                           } else {
                              var2 = this.mKeyless_access_light;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)26, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)26, (byte)1);
                                 }
                              } else {
                                 var2 = this.mHeadlight_wiper;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)28, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)28, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mStart_stop_dis;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)29, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)29, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mAcc_car;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)32, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)32, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mStop_lkas;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)33, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)33, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mTachometer_settings;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)35, (byte)0);
                                                } else {
                                                   this.SetCmdkey((byte)35, (byte)1);
                                                }
                                             } else {
                                                var2 = this.mElectric_open;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)38, (byte)0);
                                                   } else {
                                                      this.SetCmdkey((byte)38, (byte)1);
                                                   }
                                                } else {
                                                   var2 = this.mReverse_vol;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)36, (byte)0);
                                                      } else {
                                                         this.SetCmdkey((byte)36, (byte)1);
                                                      }
                                                   } else {
                                                      var2 = this.mPosition_linkage;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)39, (byte)0);
                                                         } else {
                                                            this.SetCmdkey((byte)39, (byte)1);
                                                         }
                                                      } else {
                                                         var2 = this.mExit_seat;
                                                         if (var1 == var2) {
                                                            if (var2.mChecked) {
                                                               this.SetCmdkey((byte)40, (byte)0);
                                                            } else {
                                                               this.SetCmdkey((byte)40, (byte)1);
                                                            }
                                                         } else {
                                                            var2 = this.mFatigue_info;
                                                            if (var1 == var2) {
                                                               if (var2.mChecked) {
                                                                  this.SetCmdkey((byte)42, (byte)0);
                                                               } else {
                                                                  this.SetCmdkey((byte)42, (byte)1);
                                                               }
                                                            } else {
                                                               var2 = this.mAwd_info;
                                                               if (var1 == var2) {
                                                                  if (var2.mChecked) {
                                                                     this.SetCmdkey((byte)43, (byte)0);
                                                                  } else {
                                                                     this.SetCmdkey((byte)43, (byte)1);
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
                  }
               }
            }
         }
      }

      return false;
   }

   @Deprecated
   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.mTpms_check) {
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297021)).setMessage(this.getString(2131297307)).setPositiveButton(this.getString(2131296522), new DialogInterface.OnClickListener(this) {
            final canbus115settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkey((byte)17, (byte)0);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296461), new DialogInterface.OnClickListener(this) {
            final canbus115settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               var1.cancel();
            }
         }).create().show();
      } else if (var2 == this.mReset_info) {
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297021)).setMessage(this.getString(2131297119)).setPositiveButton(this.getString(2131296522), new DialogInterface.OnClickListener(this) {
            final canbus115settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkey((byte)14, (byte)0);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296461), new DialogInterface.OnClickListener(this) {
            final canbus115settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               var1.cancel();
            }
         }).create().show();
      } else if (var2 == this.mDefault_all) {
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297021)).setMessage(this.getString(2131296560)).setPositiveButton(this.getString(2131296522), new DialogInterface.OnClickListener(this) {
            final canbus115settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkey((byte)15, (byte)0);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296461), new DialogInterface.OnClickListener(this) {
            final canbus115settings this$0;

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

   public void publicSeting() {
      this.addPreference(this.mAdjust_outside);
      this.addPreference(this.mTrip_a);
      this.addPreference(this.mTrip_b);
      this.addPreference(this.mInterior);
      this.addPreference(this.mHeadlight);
      this.addPreference(this.mAuto_light);
      this.addPreference(this.mLock_with);
      this.addPreference(this.mUnlock_with);
      this.addPreference(this.mKey_mode);
      this.addPreference(this.mKeyless);
      this.addPreference(this.mSecurity);
      this.addPreference(this.mAccess_beep);
      this.addPreference(this.mDefault_all);
   }
}
