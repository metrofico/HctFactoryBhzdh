package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus85settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Rdjus = 0;
   private HCheckBoxPreference mAcc_car;
   private HCheckBoxPreference mAccess_beep;
   private OnSwitchPreference mAdjust_outside;
   private SwitchboxPreference mAlarm_vol;
   private SwitchboxPreference mAuto_light;
   private HCheckBoxPreference mBacklight;
   private SwitchboxPreference mDanger_ahead;
   private PreferenceScreen mDefault_all;
   private SwitchboxPreference mDeviate;
   private SwitchboxPreference mDoor_unlock_mode;
   private SwitchboxPreference mDriver_monitor;
   private HCheckBoxPreference mDynamic_line;
   private HCheckBoxPreference mHead_up;
   private SwitchboxPreference mHeadlight;
   private HCheckBoxPreference mHeadlight_wiper;
   private SwitchboxPreference mIllumination;
   private SwitchboxPreference mInstrument_style;
   private SwitchboxPreference mInterior;
   private SwitchboxPreference mKey_mode;
   private HCheckBoxPreference mKeyless;
   private HCheckBoxPreference mKeyless_access_light;
   private SwitchboxPreference mLanesystem;
   private SwitchboxPreference mLock_with;
   private HCheckBoxPreference mNotifications;
   private SwitchboxPreference mParking_width;
   private HCheckBoxPreference mPosition_linkage;
   private HCheckBoxPreference mRear_alert;
   private HCheckBoxPreference mRemote_startoff;
   private PreferenceScreen mReset_info;
   private HCheckBoxPreference mReverse_camera;
   private HCheckBoxPreference mReverse_vol;
   private HCheckBoxPreference mSeat_mode;
   private SwitchboxPreference mSecurity;
   private SwitchboxPreference mSpeed_distance_units;
   private HCheckBoxPreference mStart_stop_dis;
   private HCheckBoxPreference mStatic_line;
   private HCheckBoxPreference mStop_lkas;
   private HCheckBoxPreference mSwitch_lock;
   private HCheckBoxPreference mTachometer;
   private HCheckBoxPreference mTachometer_settings;
   private PreferenceScreen mTpms_check;
   private HCheckBoxPreference mTraffic_system;
   private SwitchboxPreference mTrip_a;
   private SwitchboxPreference mTrip_b;
   private HCheckBoxPreference mTurn_display;
   private HCheckBoxPreference mTurn_linkage;
   private SwitchboxPreference mTurn_off_time;
   private SwitchboxPreference mUnlock_with;
   private SwitchboxPreference mVoice_alarm;
   private HCheckBoxPreference mWalk_away_auto_lock;
   private HCheckBoxPreference mWarning_message;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)108, new byte[]{var1, var2}, 2);
   }

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData5AA5(var1, new byte[]{var2, var3}, 2);
   }

   public void ProcessData(byte[] var1) {
      int var2 = super.cmd;
      boolean var8 = false;
      boolean var9 = false;
      boolean var7 = false;
      boolean var4 = false;
      boolean var6 = false;
      boolean var5 = false;
      boolean var3 = false;
      StringBuilder var10;
      SwitchboxPreference var11;
      byte var13;
      SwitchboxPreference var14;
      StringBuilder var15;
      HCheckBoxPreference var16;
      if (var2 == 103) {
         var2 = var1[3] & 3;
         if (var2 != 0) {
            var11 = this.mInterior;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(var2 - 1);
            var11.setValue(var10.toString());
         }

         var13 = var1[3];
         var14 = this.mHeadlight;
         var15 = new StringBuilder();
         var15.append("");
         var15.append(var13 >> 2 & 3);
         var14.setValue(var15.toString());
         var13 = var1[3];
         var11 = this.mAuto_light;
         var10 = new StringBuilder();
         var10.append("");
         var10.append(var13 >> 4 & 3);
         var11.setValue(var10.toString());
         var2 = var1[2] & 7;
         if (var2 <= 4) {
            var14 = this.mIllumination;
            var15 = new StringBuilder();
            var15.append("");
            var15.append(var2);
            var14.setValue(var15.toString());
         }

         var16 = this.mHeadlight_wiper;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         }

         var16.setChecked(var3);
      } else if (var2 == 102) {
         var16 = this.mRemote_startoff;
         if ((var1[3] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var13 = var1[3];
         var11 = this.mVoice_alarm;
         var10 = new StringBuilder();
         var10.append("");
         var10.append(var13 >> 1 & 1);
         var11.setValue(var10.toString());
         var16 = this.mKeyless_access_light;
         if ((var1[3] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mAccess_beep;
         var3 = var8;
         if ((var1[3] & 8) != 0) {
            var3 = true;
         }

         var16.setChecked(var3);
      } else if (var2 == 101) {
         var16 = this.mKeyless;
         if ((var1[3] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var13 = var1[3];
         var14 = this.mSecurity;
         var15 = new StringBuilder();
         var15.append("");
         var15.append(var13 >> 1 & 3);
         var14.setValue(var15.toString());
         var16 = this.mWalk_away_auto_lock;
         var3 = var9;
         if ((var1[3] & 8) != 0) {
            var3 = true;
         }

         var16.setChecked(var3);
      } else if (var2 == 104) {
         var13 = var1[3];
         var14 = this.mDanger_ahead;
         var15 = new StringBuilder();
         var15.append("");
         var15.append((var13 & 3) - 1);
         var14.setValue(var15.toString());
         var16 = this.mAcc_car;
         if ((var1[3] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mStop_lkas;
         if ((8 & var1[3]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var13 = var1[3];
         var11 = this.mDeviate;
         var10 = new StringBuilder();
         var10.append("");
         var10.append((var13 >> 4 & 3) - 1);
         var11.setValue(var10.toString());
         var13 = var1[3];
         var11 = this.mDriver_monitor;
         var10 = new StringBuilder();
         var10.append("");
         var10.append(var13 >> 6 & 3);
         var11.setValue(var10.toString());
         var16 = this.mHead_up;
         var3 = var7;
         if ((var1[2] & 1) != 0) {
            var3 = true;
         }

         var16.setChecked(var3);
         var13 = var1[2];
         var14 = this.mLanesystem;
         StringBuilder var12 = new StringBuilder();
         var12.append("");
         var12.append(var13 >> 1 & 7);
         var14.setValue(var12.toString());
      } else if (var2 == 105) {
         this._Rdjus = var1[3] & 7;
         if (this._Rdjus == 0) {
            this.mAdjust_outside.setValue("0", "");
         } else {
            OnSwitchPreference var17 = this.mAdjust_outside;
            var15 = new StringBuilder();
            var15.append("");
            var15.append(this._Rdjus - 4);
            var17.setValue("0", var15.toString());
         }

         var13 = var1[3];
         var14 = this.mTrip_a;
         var15 = new StringBuilder();
         var15.append("");
         var15.append((var13 >> 3 & 3) - 1);
         var14.setValue(var15.toString());
         var13 = var1[3];
         var14 = this.mTrip_b;
         var15 = new StringBuilder();
         var15.append("");
         var15.append((var13 >> 5 & 3) - 1);
         var14.setValue(var15.toString());
         var13 = var1[2];
         var14 = this.mAlarm_vol;
         var15 = new StringBuilder();
         var15.append("");
         var15.append((var13 & 3) - 1);
         var14.setValue(var15.toString());
         var16 = this.mBacklight;
         if ((var1[2] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mTachometer;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mNotifications;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mReverse_vol;
         if ((var1[2] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mStart_stop_dis;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mSwitch_lock;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mTraffic_system;
         var3 = var4;
         if ((var1[3] & 128) != 0) {
            var3 = true;
         }

         var16.setChecked(var3);
      } else if (var2 == 100) {
         var16 = this.mPosition_linkage;
         if ((var1[4] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mSeat_mode;
         if ((var1[4] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         this.mInstrument_style.setValue(7 & var1[2]);
         var16 = this.mWarning_message;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mTurn_display;
         var3 = var6;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         }

         var16.setChecked(var3);
      } else if (var2 == 232) {
         var16 = this.mTurn_linkage;
         if ((var1[5] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         this.mTurn_off_time.setValue(var1[5] >> 2 & 1);
         var16 = this.mStatic_line;
         if ((var1[8] & 1) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mDynamic_line;
         if ((var1[8] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mReverse_camera;
         if ((4 & var1[8]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var16.setChecked(var3);
         var16 = this.mRear_alert;
         var3 = var5;
         if ((var1[7] & 1) != 0) {
            var3 = true;
         }

         var16.setChecked(var3);
         this.mParking_width.setValue(var1[8] >> 3 & 1);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492947);
      this.mKeyless = this.findHCheckBoxPreference("keyless");
      this.mAccess_beep = this.findHCheckBoxPreference("access_beep");
      this.mBacklight = this.findHCheckBoxPreference("backlight");
      this.mNotifications = this.findHCheckBoxPreference("notifications");
      this.mTachometer = this.findHCheckBoxPreference("tachometer");
      this.mWalk_away_auto_lock = this.findHCheckBoxPreference("walk_away_auto_lock");
      this.mRemote_startoff = this.findHCheckBoxPreference("remote_startoff");
      this.mKeyless_access_light = this.findHCheckBoxPreference("keyless_access_light");
      this.mHeadlight_wiper = this.findHCheckBoxPreference("headlight_wiper");
      this.mStart_stop_dis = this.findHCheckBoxPreference("start_stop_dis");
      this.mAcc_car = this.findHCheckBoxPreference("acc_car");
      this.mStop_lkas = this.findHCheckBoxPreference("stop_lkas");
      this.mTachometer_settings = this.findHCheckBoxPreference("tachometer_settings");
      this.mHead_up = this.findHCheckBoxPreference("head_up");
      this.mReverse_vol = this.findHCheckBoxPreference("reverse_vol");
      this.mSwitch_lock = this.findHCheckBoxPreference("switch_lock");
      this.mPosition_linkage = this.findHCheckBoxPreference("position_linkage");
      this.mSeat_mode = this.findHCheckBoxPreference("seat_mode");
      this.mTraffic_system = this.findHCheckBoxPreference("traffic_system");
      this.mTurn_linkage = this.findHCheckBoxPreference("turn_linkage");
      this.mStatic_line = this.findHCheckBoxPreference("static_line");
      this.mDynamic_line = this.findHCheckBoxPreference("dynamic_line");
      this.mReverse_camera = this.findHCheckBoxPreference("reverse_camera");
      this.mRear_alert = this.findHCheckBoxPreference("rear_alert");
      this.mWarning_message = this.findHCheckBoxPreference("warning_message");
      this.mTurn_display = this.findHCheckBoxPreference("turn_display");
      this.mAdjust_outside = (OnSwitchPreference)this.findPreference("adjust_outside");
      this.mTrip_a = this.findSwitchboxPreference("trip_a");
      this.mTrip_b = this.findSwitchboxPreference("trip_b");
      this.mInterior = this.findSwitchboxPreference("interior");
      this.mHeadlight = this.findSwitchboxPreference("headlight");
      this.mAuto_light = this.findSwitchboxPreference("auto_light");
      this.mLock_with = this.findSwitchboxPreference("lock_with");
      this.mUnlock_with = this.findSwitchboxPreference("unlock_with");
      this.mKey_mode = this.findSwitchboxPreference("key_mode");
      this.mSecurity = this.findSwitchboxPreference("security");
      this.mAlarm_vol = this.findSwitchboxPreference("alarm_vol");
      this.mSpeed_distance_units = this.findSwitchboxPreference("speed_distance_units");
      this.mDoor_unlock_mode = this.findSwitchboxPreference("door_unlock_mode");
      this.mIllumination = this.findSwitchboxPreference("illumination");
      this.mVoice_alarm = this.findSwitchboxPreference("voice_alarm");
      this.mDanger_ahead = this.findSwitchboxPreference("danger_ahead");
      this.mDeviate = this.findSwitchboxPreference("deviate");
      this.mDriver_monitor = this.findSwitchboxPreference("driver_monitor");
      this.mLanesystem = this.findSwitchboxPreference("lanesystem");
      this.mTurn_off_time = this.findSwitchboxPreference("turn_off_time");
      this.mParking_width = this.findSwitchboxPreference("parking_width");
      this.mInstrument_style = this.findSwitchboxPreference("instrument_style");
      this.mTpms_check = (PreferenceScreen)this.findPreference("tpms_check");
      this.mReset_info = (PreferenceScreen)this.findPreference("reset_info");
      this.mDefault_all = (PreferenceScreen)this.findPreference("default_all");
      this.removePreference((Preference)this.mTachometer_settings);
      this.removePreference((Preference)this.mDoor_unlock_mode);
      this.removePreference((Preference)this.mSpeed_distance_units);
      this.removePreference((Preference)this.mDefault_all);
      this.removePreference((Preference)this.mKey_mode);
      this.removePreference((Preference)this.mUnlock_with);
      this.removePreference((Preference)this.mLock_with);
      this.removePreference((Preference)this.mReverse_vol);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 100}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 101}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 102}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 103}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 104}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 105}, 3);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mAdjust_outside;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var3 = this._Rdjus;
         if (var3 + var4 < 0 || var3 + var4 > 7 || !this.mAdjust_outside.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)111, (byte)1, (byte)(this._Rdjus + var4 & 255));
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
            this.SetCmdkey((byte)111, (byte)2, (byte)(var3 + 1));
         } else if (var1 == this.mTrip_b) {
            this.SetCmdkey((byte)111, (byte)3, (byte)(var3 + 1));
         } else if (var1 == this.mInterior) {
            this.SetCmdkey((byte)108, (byte)1, (byte)(var3 + 1));
         } else if (var1 == this.mHeadlight) {
            this.SetCmdkey((byte)108, (byte)2, (byte)var3);
         } else if (var1 == this.mAuto_light) {
            this.SetCmdkey((byte)108, (byte)3, (byte)var3);
         } else if (var1 == this.mLock_with) {
            this.SetCmdkey((byte)7, (byte)var3);
         } else if (var1 == this.mUnlock_with) {
            this.SetCmdkey((byte)8, (byte)var3);
         } else if (var1 == this.mKey_mode) {
            this.SetCmdkey((byte)9, (byte)var3);
         } else if (var1 == this.mSecurity) {
            this.SetCmdkey((byte)109, (byte)2, (byte)(var3 + 1));
         } else if (var1 == this.mAlarm_vol) {
            this.SetCmdkey((byte)111, (byte)4, (byte)(var3 + 1));
         } else if (var1 == this.mSpeed_distance_units) {
            this.SetCmdkey((byte)21, (byte)var3);
         } else if (var1 == this.mDoor_unlock_mode) {
            this.SetCmdkey((byte)25, (byte)var3);
         } else if (var1 == this.mIllumination) {
            this.SetCmdkey((byte)108, (byte)4, (byte)var3);
         } else if (var1 == this.mVoice_alarm) {
            this.SetCmdkey((byte)107, (byte)2, (byte)var3);
         } else if (var1 == this.mDanger_ahead) {
            this.SetCmdkey((byte)110, (byte)1, (byte)(var3 + 1));
         } else if (var1 == this.mDeviate) {
            this.SetCmdkey((byte)110, (byte)4, (byte)(var3 + 1));
         } else if (var1 == this.mDriver_monitor) {
            this.SetCmdkey((byte)110, (byte)7, (byte)var3);
         } else if (var1 == this.mLanesystem) {
            this.SetCmdkey((byte)110, (byte)9, (byte)var3);
         } else if (var1 == this.mTurn_off_time) {
            this.SetCmdkey((byte)-14, (byte)12, (byte)(var3 + 4));
         } else if (var1 == this.mParking_width) {
            this.SetCmdkey((byte)-14, (byte)13, (byte)(var3 + 6));
         } else if (var1 == this.mInstrument_style && var3 < 3) {
            this.SetCmdkey((byte)111, (byte)14, (byte)(var3 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mKeyless;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)109, (byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)109, (byte)1, (byte)1);
         }
      } else {
         var2 = this.mAccess_beep;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)107, (byte)4, (byte)0);
            } else {
               this.SetCmdkey((byte)107, (byte)4, (byte)1);
            }
         } else {
            var2 = this.mBacklight;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)111, (byte)5, (byte)0);
               } else {
                  this.SetCmdkey((byte)111, (byte)5, (byte)1);
               }
            } else {
               var2 = this.mNotifications;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)111, (byte)7, (byte)0);
                  } else {
                     this.SetCmdkey((byte)111, (byte)7, (byte)1);
                  }
               } else {
                  var2 = this.mTachometer;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)111, (byte)6, (byte)0);
                     } else {
                        this.SetCmdkey((byte)111, (byte)6, (byte)1);
                     }
                  } else {
                     var2 = this.mWalk_away_auto_lock;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)109, (byte)3, (byte)0);
                        } else {
                           this.SetCmdkey((byte)109, (byte)3, (byte)1);
                        }
                     } else {
                        var2 = this.mRemote_startoff;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)107, (byte)1, (byte)0);
                           } else {
                              this.SetCmdkey((byte)107, (byte)1, (byte)1);
                           }
                        } else {
                           var2 = this.mKeyless_access_light;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)107, (byte)3, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)107, (byte)3, (byte)1);
                              }
                           } else {
                              var2 = this.mHeadlight_wiper;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)108, (byte)5, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)5, (byte)1);
                                 }
                              } else {
                                 var2 = this.mStart_stop_dis;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)111, (byte)8, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)111, (byte)8, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mAcc_car;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)110, (byte)2, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)110, (byte)2, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mStop_lkas;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)110, (byte)3, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)110, (byte)3, (byte)1);
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
                                             var2 = this.mHead_up;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)110, (byte)8, (byte)0);
                                                } else {
                                                   this.SetCmdkey((byte)110, (byte)8, (byte)1);
                                                }
                                             } else {
                                                var2 = this.mReverse_vol;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)111, (byte)9, (byte)0);
                                                   } else {
                                                      this.SetCmdkey((byte)111, (byte)9, (byte)1);
                                                   }
                                                } else {
                                                   var2 = this.mSwitch_lock;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)111, (byte)10, (byte)0);
                                                      } else {
                                                         this.SetCmdkey((byte)111, (byte)10, (byte)1);
                                                      }
                                                   } else {
                                                      var2 = this.mPosition_linkage;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)111, (byte)11, (byte)0);
                                                         } else {
                                                            this.SetCmdkey((byte)111, (byte)11, (byte)1);
                                                         }
                                                      } else {
                                                         var2 = this.mSeat_mode;
                                                         if (var1 == var2) {
                                                            if (var2.mChecked) {
                                                               this.SetCmdkey((byte)111, (byte)12, (byte)0);
                                                            } else {
                                                               this.SetCmdkey((byte)111, (byte)12, (byte)1);
                                                            }
                                                         } else {
                                                            var2 = this.mTraffic_system;
                                                            if (var1 == var2) {
                                                               if (var2.mChecked) {
                                                                  this.SetCmdkey((byte)111, (byte)13, (byte)0);
                                                               } else {
                                                                  this.SetCmdkey((byte)111, (byte)13, (byte)1);
                                                               }
                                                            } else {
                                                               var2 = this.mTurn_linkage;
                                                               if (var1 == var2) {
                                                                  if (var2.mChecked) {
                                                                     this.SetCmdkey((byte)-14, (byte)12, (byte)0);
                                                                  } else {
                                                                     this.SetCmdkey((byte)-14, (byte)12, (byte)1);
                                                                  }
                                                               } else {
                                                                  var2 = this.mStatic_line;
                                                                  if (var1 == var2) {
                                                                     if (var2.mChecked) {
                                                                        this.SetCmdkey((byte)-14, (byte)13, (byte)0);
                                                                     } else {
                                                                        this.SetCmdkey((byte)-14, (byte)13, (byte)1);
                                                                     }
                                                                  } else {
                                                                     var2 = this.mDynamic_line;
                                                                     if (var1 == var2) {
                                                                        if (var2.mChecked) {
                                                                           this.SetCmdkey((byte)-14, (byte)13, (byte)2);
                                                                        } else {
                                                                           this.SetCmdkey((byte)-14, (byte)13, (byte)3);
                                                                        }
                                                                     } else {
                                                                        var2 = this.mReverse_camera;
                                                                        if (var1 == var2) {
                                                                           if (var2.mChecked) {
                                                                              this.SetCmdkey((byte)-14, (byte)13, (byte)4);
                                                                           } else {
                                                                              this.SetCmdkey((byte)-14, (byte)13, (byte)5);
                                                                           }
                                                                        } else {
                                                                           var2 = this.mRear_alert;
                                                                           if (var1 == var2) {
                                                                              if (var2.mChecked) {
                                                                                 this.SetCmdkey((byte)-14, (byte)14, (byte)0);
                                                                              } else {
                                                                                 this.SetCmdkey((byte)-14, (byte)14, (byte)1);
                                                                              }
                                                                           } else {
                                                                              var2 = this.mTurn_display;
                                                                              if (var1 == var2) {
                                                                                 if (var2.mChecked) {
                                                                                    this.SetCmdkey((byte)111, (byte)16, (byte)0);
                                                                                 } else {
                                                                                    this.SetCmdkey((byte)111, (byte)16, (byte)1);
                                                                                 }
                                                                              } else {
                                                                                 var2 = this.mWarning_message;
                                                                                 if (var1 == var2) {
                                                                                    if (var2.mChecked) {
                                                                                       this.SetCmdkey((byte)111, (byte)15, (byte)0);
                                                                                    } else {
                                                                                       this.SetCmdkey((byte)111, (byte)15, (byte)1);
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
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus85settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SendCanBusCmdData5AA5((byte)75, new byte[]{4, 1}, 2);
            }
         }, this.mTpms_check.getTitle().toString());
      } else {
         PreferenceScreen var3 = this.mReset_info;
         if (var2 == var3) {
            this.AlertDialog(new AlertDialogCallBack(this) {
               final canbus85settings this$0;

               {
                  this.this$0 = var1;
               }

               public void OkClick() {
                  this.this$0.SendCanBusCmdData5AA5((byte)110, new byte[]{6, 1}, 2);
               }
            }, this.mReset_info.getTitle().toString());
         } else if (var2 == var3) {
            this.SetCmdkey((byte)14, (byte)0);
         } else if (var2 == this.mDefault_all) {
            this.SetCmdkey((byte)15, (byte)0);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
