package com.microntek.controlsettings;

import android.content.ContentResolver;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;

public class canbus36settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mAcc_car;
   private HCheckBoxPreference mAccess_beep;
   private SwitchboxPreference mAdjust_outside;
   private SwitchboxPreference mAlarm_vol;
   private SwitchboxPreference mAuto_light;
   private HCheckBoxPreference mAuto_tunk;
   private SwitchboxPreference mBack_frame;
   private SwitchboxPreference mBack_mode;
   private HCheckBoxPreference mBacklight;
   private HCheckBoxPreference mCamera_low_speed;
   private SwitchboxPreference mCamera_mode;
   private HCheckBoxPreference mCrossroads_monitor;
   private HCheckBoxPreference mCtm_system;
   private SwitchboxPreference mDanger_ahead;
   private PreferenceScreen mDefault_all;
   private SwitchboxPreference mDeviate;
   private SwitchboxPreference mDoor_unlock_mode;
   private SwitchboxPreference mDriver_monitor;
   private HCheckBoxPreference mDynamic_line;
   private HCheckBoxPreference mElectric_open;
   private SwitchboxPreference mHeadlight;
   private HCheckBoxPreference mHeadlight_wiper;
   private SwitchboxPreference mIllumination;
   private SwitchboxPreference mInterior;
   private SwitchboxPreference mKey_mode;
   private HCheckBoxPreference mKeyless;
   private HCheckBoxPreference mKeyless_access_light;
   private SwitchboxPreference mLock_with;
   private HCheckBoxPreference mNotifications;
   private SwitchboxPreference mParking_width;
   private SwitchboxPreference mPreset_parking;
   private HCheckBoxPreference mRear_alert;
   private SwitchboxPreference mRear_view;
   private SwitchboxPreference mRemote_setting;
   private HCheckBoxPreference mRemote_startoff;
   private PreferenceScreen mReset_info;
   private HCheckBoxPreference mReverse_camera;
   private SwitchboxPreference mSecurity;
   private SwitchboxPreference mSpeed_distance_units;
   private HCheckBoxPreference mStart_stop_dis;
   private HCheckBoxPreference mStatic_line;
   private HCheckBoxPreference mStop_lkas;
   private HCheckBoxPreference mTachometer;
   private HCheckBoxPreference mTachometer_settings;
   private PreferenceScreen mTpms_check;
   private SwitchboxPreference mTrip_a;
   private SwitchboxPreference mTrip_b;
   private HCheckBoxPreference mTunk;
   private SwitchboxPreference mUnlock_with;
   private SwitchboxPreference mVoice_alarm;
   private HCheckBoxPreference mWalk_away_auto_lock;
   private byte[] setData = new byte[20];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
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

            SwitchboxPreference var7 = this.mAdjust_outside;
            StringBuilder var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[0] & 15);
            var7.setValue(var10.toString());
            SwitchboxPreference var11 = this.mTrip_a;
            StringBuilder var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[0] >> 4 & 3);
            var11.setValue(var8.toString());
            var11 = this.mTrip_b;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[0] >> 6 & 3);
            var11.setValue(var8.toString());
            var11 = this.mInterior;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[1] & 3);
            var11.setValue(var8.toString());
            var7 = this.mHeadlight;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[1] >> 2 & 3);
            var7.setValue(var10.toString());
            var11 = this.mAuto_light;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[1] >> 4 & 7);
            var11.setValue(var8.toString());
            var11 = this.mLock_with;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[2] & 3);
            var11.setValue(var8.toString());
            var11 = this.mUnlock_with;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[2] >> 2 & 3);
            var11.setValue(var8.toString());
            var7 = this.mKey_mode;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[2] >> 6 & 1);
            var7.setValue(var10.toString());
            HCheckBoxPreference var9 = this.mKeyless;
            if ((this.setData[2] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var11 = this.mSecurity;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[2] >> 4 & 3);
            var11.setValue(var8.toString());
            var9 = this.mAccess_beep;
            if ((this.setData[3] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var11 = this.mAlarm_vol;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[4] >> 6 & 3);
            var11.setValue(var8.toString());
            var9 = this.mBacklight;
            if ((this.setData[4] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mNotifications;
            if ((this.setData[4] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var7 = this.mSpeed_distance_units;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[4] >> 3 & 1);
            var7.setValue(var10.toString());
            var9 = this.mTachometer;
            if ((this.setData[4] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mWalk_away_auto_lock;
            if ((this.setData[4] & 2) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mRemote_startoff;
            if ((this.setData[3] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var11 = this.mDoor_unlock_mode;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[3] >> 4 & 1);
            var11.setValue(var8.toString());
            var9 = this.mKeyless_access_light;
            if ((this.setData[3] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var11 = this.mIllumination;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[3] & 7);
            var11.setValue(var8.toString());
            var9 = this.mHeadlight_wiper;
            if ((this.setData[4] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mStart_stop_dis;
            if ((this.setData[5] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var7 = this.mVoice_alarm;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[5] >> 7 & 1);
            var7.setValue(var10.toString());
            var7 = this.mDanger_ahead;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[5] >> 2 & 3);
            var7.setValue(var10.toString());
            var9 = this.mAcc_car;
            if ((this.setData[5] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mStop_lkas;
            if ((this.setData[5] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var7 = this.mDeviate;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[5] & 3);
            var7.setValue(var10.toString());
            var9 = this.mTachometer_settings;
            if ((this.setData[6] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var11 = this.mDriver_monitor;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(this.setData[6] >> 5 & 3);
            var11.setValue(var8.toString());
            var7 = this.mRemote_setting;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[6] >> 4 & 1);
            var7.setValue(var10.toString());
            var9 = this.mElectric_open;
            if ((this.setData[6] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            this.mCamera_mode.setValue(this.setData[9] >> 6 & 3);
            this.mBack_frame.setValue(this.setData[9] >> 4 & 3);
            var9 = this.mCamera_low_speed;
            if ((this.setData[9] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            this.mPreset_parking.setValue(this.setData[9] >> 2 & 1);
            this.mBack_mode.setValue(this.setData[9] >> 1 & 1);
            var9 = this.mCrossroads_monitor;
            if ((this.setData[9] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mReverse_camera;
            if ((this.setData[7] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mStatic_line;
            if ((this.setData[7] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mDynamic_line;
            if ((this.setData[7] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mRear_alert;
            if ((this.setData[7] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            this.mParking_width.setValue(this.setData[7] >> 3 & 1);
            var9 = this.mTunk;
            if ((this.setData[10] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            this.mRear_view.setValue(this.setData[10] >> 6 & 1);
            var9 = this.mAuto_tunk;
            var4 = var5;
            if ((this.setData[8] & 128) != 0) {
               var4 = true;
            }

            var9.setChecked(var4);
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492915);
      this.mCtm_system = this.findHCheckBoxPreference("ctm_system");
      ContentResolver var7 = this.getContentResolver();
      boolean var4 = true;
      int var3 = System.getInt(var7, "com.microntek.control36settings", 1);
      HCheckBoxPreference var8 = this.mCtm_system;
      int var2 = 0;
      if (var8 != null) {
         if (var3 != 0) {
            var4 = false;
         }

         var8.setChecked(var4);
      }

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
      this.mTunk = this.findHCheckBoxPreference("tunk");
      this.mAuto_tunk = this.findHCheckBoxPreference("auto_tunk");
      this.mAcc_car = this.findHCheckBoxPreference("acc_car");
      this.mStop_lkas = this.findHCheckBoxPreference("stop_lkas");
      this.mTachometer_settings = this.findHCheckBoxPreference("tachometer_settings");
      this.mElectric_open = this.findHCheckBoxPreference("electric_open");
      this.mCamera_low_speed = this.findHCheckBoxPreference("camera_low_speed");
      this.mCrossroads_monitor = this.findHCheckBoxPreference("crossroads_monitor");
      this.mReverse_camera = this.findHCheckBoxPreference("reverse_camera");
      this.mStatic_line = this.findHCheckBoxPreference("static_line");
      this.mDynamic_line = this.findHCheckBoxPreference("dynamic_line");
      this.mRear_alert = this.findHCheckBoxPreference("rear_alert");
      this.mAdjust_outside = this.findSwitchboxPreference("adjust_outside");
      CharSequence[] var5 = new CharSequence[11];

      CharSequence[] var9;
      for(var9 = new CharSequence[11]; var2 < var5.length; ++var2) {
         StringBuilder var6 = new StringBuilder();
         var6.append("");
         var6.append(var2 - 5);
         var5[var2] = var6.toString();
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var2);
         var9[var2] = var6.toString();
      }

      this.mAdjust_outside.setEntries(var5);
      this.mAdjust_outside.setEntryValues(var9);
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
      this.mRemote_setting = this.findSwitchboxPreference("remote_setting");
      this.mCamera_mode = this.findSwitchboxPreference("camera_mode");
      this.mBack_frame = this.findSwitchboxPreference("back_frame");
      this.mPreset_parking = this.findSwitchboxPreference("preset_parking");
      this.mBack_mode = this.findSwitchboxPreference("back_mode");
      this.mParking_width = this.findSwitchboxPreference("parking_width");
      this.mRear_view = this.findSwitchboxPreference("rear_view");
      this.mTpms_check = this.findPreferenceScreen("tpms_check");
      this.mReset_info = this.findPreferenceScreen("reset_info");
      this.mDefault_all = this.findPreferenceScreen("default_all");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{50, 0}, 2);
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
         if (var1 == this.mAdjust_outside) {
            this.SetCmdkey((byte)0, (byte)var3);
         } else if (var1 == this.mTrip_a) {
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
         } else if (var1 == this.mCamera_mode) {
            this.SetCmdkey((byte)53, (byte)var3);
         } else if (var1 == this.mBack_frame) {
            this.SetCmdkey((byte)54, (byte)var3);
         } else if (var1 == this.mPreset_parking) {
            this.SetCmdkey((byte)56, (byte)var3);
         } else if (var1 == this.mBack_mode) {
            this.SetCmdkey((byte)57, (byte)var3);
         } else if (var1 == this.mParking_width) {
            this.SetCmdkey((byte)46, (byte)var3);
         } else if (var1 == this.mRear_view) {
            this.SetCmdkey((byte)71, (byte)var3);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mCtm_system;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)96, (byte)0);
         } else {
            this.SetCmdkey((byte)96, (byte)1);
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
                                                   var2 = this.mCamera_low_speed;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)55, (byte)0);
                                                      } else {
                                                         this.SetCmdkey((byte)55, (byte)1);
                                                      }
                                                   } else {
                                                      var2 = this.mCrossroads_monitor;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)58, (byte)0);
                                                         } else {
                                                            this.SetCmdkey((byte)58, (byte)1);
                                                         }
                                                      } else {
                                                         var2 = this.mReverse_camera;
                                                         if (var1 == var2) {
                                                            if (var2.mChecked) {
                                                               this.SetCmdkey((byte)45, (byte)0);
                                                            } else {
                                                               this.SetCmdkey((byte)45, (byte)1);
                                                            }
                                                         } else {
                                                            var2 = this.mStatic_line;
                                                            if (var1 == var2) {
                                                               if (var2.mChecked) {
                                                                  this.SetCmdkey((byte)43, (byte)0);
                                                               } else {
                                                                  this.SetCmdkey((byte)43, (byte)1);
                                                               }
                                                            } else {
                                                               var2 = this.mDynamic_line;
                                                               if (var1 == var2) {
                                                                  if (var2.mChecked) {
                                                                     this.SetCmdkey((byte)44, (byte)0);
                                                                  } else {
                                                                     this.SetCmdkey((byte)44, (byte)1);
                                                                  }
                                                               } else {
                                                                  var2 = this.mRear_alert;
                                                                  if (var1 == var2) {
                                                                     if (var2.mChecked) {
                                                                        this.SetCmdkey((byte)47, (byte)0);
                                                                     } else {
                                                                        this.SetCmdkey((byte)47, (byte)1);
                                                                     }
                                                                  } else {
                                                                     var2 = this.mTunk;
                                                                     if (var1 == var2) {
                                                                        if (var2.mChecked) {
                                                                           this.SetCmdkey((byte)70, (byte)0);
                                                                        } else {
                                                                           this.SetCmdkey((byte)70, (byte)1);
                                                                        }
                                                                     } else {
                                                                        var2 = this.mAuto_tunk;
                                                                        if (var1 == var2) {
                                                                           if (var2.mChecked) {
                                                                              this.SetCmdkey((byte)51, (byte)0);
                                                                           } else {
                                                                              this.SetCmdkey((byte)51, (byte)1);
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
            final canbus36settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)17, (byte)0);
            }
         }, this.mTpms_check.getTitle().toString());
      } else if (var2 == this.mReset_info) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus36settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)14, (byte)0);
            }
         }, this.mReset_info.getTitle().toString());
      } else if (var2 == this.mDefault_all) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus36settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)15, (byte)0);
            }
         }, this.mDefault_all.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
