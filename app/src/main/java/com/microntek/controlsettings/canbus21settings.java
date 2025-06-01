package com.microntek.controlsettings;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;

public class canbus21settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Rdjus = 0;
   private HCheckBoxPreference mAcc_car;
   private HCheckBoxPreference mAccess_beep;
   private OnSwitchPreference mAdjust_outside;
   private SwitchboxPreference mAlarm_vol;
   private SwitchboxPreference mAuto_light;
   private HCheckBoxPreference mBacklight;
   private HCheckBoxPreference mCtm_system;
   private SwitchboxPreference mDanger_ahead;
   private PreferenceScreen mDefault_all;
   private SwitchboxPreference mDeviate;
   private SwitchboxPreference mDoor_unlock_mode;
   private SwitchboxPreference mDriver_monitor;
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
   private SwitchboxPreference mRemote_setting;
   private HCheckBoxPreference mRemote_startoff;
   private PreferenceScreen mReset_info;
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

   protected void ProcessData(byte[] var1) {
      boolean var6 = false;
      boolean var5 = false;
      boolean var4 = false;
      int var2 = var1[0];
      int var3 = var1.length;
      if (var3 > 2) {
         if (var2 == -48) {
            if (var1[2] == 96) {
               System.putInt(this.getContentResolver(), "com.microntek.control36settings", var1[3] & 255);
               HCheckBoxPreference var7 = this.mCtm_system;
               if (var1[3] == 0) {
                  var4 = true;
               }

               var7.setChecked(var4);
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
            OnSwitchPreference var12 = this.mAdjust_outside;
            StringBuilder var9 = new StringBuilder();
            var9.append("");
            var9.append(this._Rdjus - 5);
            var12.setValue("0", var9.toString());
            SwitchboxPreference var10 = this.mTrip_a;
            StringBuilder var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[0] >> 4 & 3);
            var10.setValue(var13.toString());
            var10 = this.mTrip_b;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[0] >> 6 & 3);
            var10.setValue(var13.toString());
            SwitchboxPreference var14 = this.mInterior;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[1] & 3);
            var14.setValue(var9.toString());
            var10 = this.mHeadlight;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[1] >> 2 & 3);
            var10.setValue(var13.toString());
            var10 = this.mAuto_light;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[1] >> 4 & 7);
            var10.setValue(var13.toString());
            var14 = this.mLock_with;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[2] & 3);
            var14.setValue(var9.toString());
            var14 = this.mUnlock_with;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[2] >> 2 & 3);
            var14.setValue(var9.toString());
            var10 = this.mKey_mode;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[2] >> 6 & 1);
            var10.setValue(var13.toString());
            HCheckBoxPreference var11 = this.mKeyless;
            if ((this.setData[2] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var14 = this.mSecurity;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[2] >> 4 & 3);
            var14.setValue(var9.toString());
            var11 = this.mAccess_beep;
            if ((this.setData[3] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var10 = this.mAlarm_vol;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[4] >> 6 & 3);
            var10.setValue(var13.toString());
            var11 = this.mBacklight;
            if ((this.setData[4] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var11 = this.mNotifications;
            if ((this.setData[4] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var14 = this.mSpeed_distance_units;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[4] >> 3 & 1);
            var14.setValue(var9.toString());
            var11 = this.mTachometer;
            if ((this.setData[4] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var11 = this.mWalk_away_auto_lock;
            if ((this.setData[4] & 2) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var11 = this.mRemote_startoff;
            if ((this.setData[3] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var14 = this.mDoor_unlock_mode;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[3] >> 4 & 1);
            var14.setValue(var9.toString());
            var11 = this.mKeyless_access_light;
            if ((this.setData[3] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var14 = this.mIllumination;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[3] & 7);
            var14.setValue(var9.toString());
            var11 = this.mHeadlight_wiper;
            if ((this.setData[4] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var11 = this.mStart_stop_dis;
            if ((this.setData[5] & 64) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var10 = this.mVoice_alarm;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[5] >> 7 & 1);
            var10.setValue(var13.toString());
            var10 = this.mDanger_ahead;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this.setData[5] >> 2 & 3);
            var10.setValue(var13.toString());
            var11 = this.mAcc_car;
            if ((this.setData[5] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var11 = this.mStop_lkas;
            if ((this.setData[5] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var14 = this.mDeviate;
            var9 = new StringBuilder();
            var9.append("");
            var9.append(this.setData[5] & 3);
            var14.setValue(var9.toString());
            var11 = this.mTachometer_settings;
            if ((this.setData[6] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var11.setChecked(var4);
            var1 = this.setData;
            var3 = (var1[7] & 255) << 8 | var1[8] & 255;
            var2 = var3;
            if ((var1[6] & 1) != 0) {
               var2 = 0 - var3;
            }

            String var15;
            if ((this.setData[6] & 2) != 0) {
               var15 = " MI";
            } else {
               var15 = " KM";
            }

            PreferenceScreen var16 = this.mReset_info;
            StringBuilder var8 = new StringBuilder();
            var8.append(var2);
            var8.append(var15);
            var16.setSummary(var8.toString());
            if (super.canbustype == 36) {
               var14 = this.mDriver_monitor;
               var9 = new StringBuilder();
               var9.append("");
               var9.append(this.setData[6] >> 5 & 3);
               var14.setValue(var9.toString());
               var10 = this.mRemote_setting;
               var13 = new StringBuilder();
               var13.append("");
               var13.append(this.setData[6] >> 4 & 1);
               var10.setValue(var13.toString());
               var11 = this.mElectric_open;
               var4 = var6;
               if ((this.setData[6] & 8) != 0) {
                  var4 = true;
               }

               var11.setChecked(var4);
            } else {
               var14 = this.mDriver_monitor;
               var9 = new StringBuilder();
               var9.append("");
               var9.append(this.setData[9] >> 2 & 3);
               var14.setValue(var9.toString());
               var14 = this.mRemote_setting;
               var9 = new StringBuilder();
               var9.append("");
               var9.append(this.setData[9] & 1);
               var14.setValue(var9.toString());
               var11 = this.mElectric_open;
               var4 = var5;
               if ((this.setData[9] & 2) != 0) {
                  var4 = true;
               }

               var11.setChecked(var4);
            }
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492893);
      this.mCtm_system = (HCheckBoxPreference)this.findPreference("ctm_system");
      ContentResolver var4 = this.getContentResolver();
      boolean var3 = true;
      int var2 = System.getInt(var4, "com.microntek.control36settings", 1);
      HCheckBoxPreference var5 = this.mCtm_system;
      if (var5 != null) {
         var5.setOnPreferenceClickListener(this);
         var5 = this.mCtm_system;
         if (var2 != 0) {
            var3 = false;
         }

         var5.setChecked(var3);
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
      this.SendCanBusCmdData2E((byte)-112, new byte[]{50, 0}, 2);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mAdjust_outside;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this._Rdjus;
         if (var4 + var3 < 0 || var4 + var3 > 10 || !this.mAdjust_outside.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)0, (byte)(this._Rdjus + var3 & 255));
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
            final canbus21settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)17, (byte)0);
            }
         }, this.mTpms_check.getTitle().toString());
      } else if (var2 == this.mReset_info) {
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297021)).setMessage(this.getString(2131297119)).setPositiveButton(this.getString(2131296522), new DialogInterface.OnClickListener(this) {
            final canbus21settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkey((byte)14, (byte)0);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296461), new DialogInterface.OnClickListener(this) {
            final canbus21settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               var1.cancel();
            }
         }).create().show();
      } else if (var2 == this.mDefault_all) {
         this.SetCmdkey((byte)15, (byte)0);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
