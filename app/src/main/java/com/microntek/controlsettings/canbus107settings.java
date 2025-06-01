package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus107settings extends BasePreferenceActivity {
   private int _Alarm_distance2 = 0;
   private int _Calibration = 0;
   private int _Height = 0;
   private int _Height2 = 0;
   private HCheckBoxPreference mActive_display;
   private HCheckBoxPreference mAdaptive;
   private SwitchboxPreference mAlarm_distance;
   private OnSwitchPreference mAlarm_distance2;
   private SwitchboxPreference mAlarm_volume;
   private SwitchboxPreference mAlarm_volume2;
   private SwitchboxPreference mAuto_light_open;
   private SwitchboxPreference mAuto_lights_off;
   private SwitchboxPreference mAuto_lights_off2;
   private HCheckBoxPreference mAutomatic_latch;
   private HCheckBoxPreference mBlind_spot;
   private HCheckBoxPreference mBose_audiopilot;
   private HCheckBoxPreference mBose_centerpoint;
   private SwitchboxPreference mBrightness_control;
   private OnSwitchPreference mCalibration;
   private SwitchboxPreference mCar_light_off;
   private PreferenceScreen mDefault_all;
   private HCheckBoxPreference mDistance_system;
   private SwitchboxPreference mGohome;
   private SwitchboxPreference mHeadlight_off_timer;
   private OnSwitchPreference mHeight;
   private OnSwitchPreference mHeight2;
   private HCheckBoxPreference mHigh_beam;
   private HCheckBoxPreference mIntelligent_brake;
   private HCheckBoxPreference mIntervene;
   private SwitchboxPreference mIntervene_timing;
   private SwitchboxPreference mLock_beep;
   private SwitchboxPreference mLock_mode;
   private HCheckBoxPreference mMode;
   private SwitchboxPreference mMonitor_volume;
   private HCheckBoxPreference mNavigation;
   private SwitchboxPreference mOuto_lock_mode;
   private PreferenceScreen mReset_avg;
   private SwitchboxPreference mSecurity;
   private SwitchboxPreference mSensitivity;
   private HCheckBoxPreference mSpeed_dis;
   private SwitchboxPreference mSpeed_warning;
   private HCheckBoxPreference mSync_avg;
   private HCheckBoxPreference mThree_signal;
   private SwitchboxPreference mTurn_volume;
   private HCheckBoxPreference mWarning;
   private SwitchboxPreference mWarning_threshold;
   private SwitchboxPreference mWarning_type;
   private HCheckBoxPreference mWipers_induction;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-124, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var4 = false;
      boolean var5 = false;
      int var2 = var1[0] & 255;
      boolean var3;
      HCheckBoxPreference var6;
      if (var2 != 9) {
         if (var2 == 13) {
            var6 = this.mBlind_spot;
            if ((var1[2] & 128) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.setChecked(var3);
            var6 = this.mHigh_beam;
            if ((var1[2] & 64) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.setChecked(var3);
            this.mGohome.setValue(var1[2] >> 3 & 7);
            var6 = this.mWarning;
            if ((var1[3] & 32) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.setChecked(var3);
            var6 = this.mIntervene;
            if ((var1[2] & 2) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.setChecked(var3);
            this.mIntervene_timing.setValue(var1[2] & 1);
            this.mSensitivity.setValue(var1[3] >> 6 & 3);
            this.mWarning_type.setValue(var1[3] >> 3 & 3);
            this.mAlarm_volume2.setValue(var1[3] >> 1 & 3);
            this.mSpeed_warning.setValue(var1[4] >> 5 & 3);
            this.mWarning_threshold.setValue(var1[4] >> 3 & 3);
            var6 = this.mSpeed_dis;
            if ((var1[4] & 128) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.setChecked(var3);
            var6 = this.mBose_centerpoint;
            if ((var1[4] & 4) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var6.setChecked(var3);
            var6 = this.mBose_audiopilot;
            var3 = var5;
            if ((var1[4] & 2) != 0) {
               var3 = true;
            }

            var6.setChecked(var3);
         }
      } else {
         var6 = this.mWipers_induction;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         byte var9 = var1[2];
         SwitchboxPreference var11 = this.mLock_beep;
         StringBuilder var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 2 & 3);
         var11.setValue(var7.toString());
         var9 = var1[2];
         SwitchboxPreference var10 = this.mOuto_lock_mode;
         StringBuilder var12 = new StringBuilder();
         var12.append("");
         var12.append(var9 >> 4 & 3);
         var10.setValue(var12.toString());
         var9 = var1[2];
         var11 = this.mSecurity;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 0 & 3);
         var11.setValue(var7.toString());
         var9 = var1[3];
         var10 = this.mLock_mode;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(var9 >> 7 & 1);
         var10.setValue(var12.toString());
         var6 = this.mAutomatic_latch;
         if ((var1[3] & 64) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mThree_signal;
         if ((var1[3] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var9 = var1[3];
         var10 = this.mTurn_volume;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(var9 >> 4 & 1);
         var10.setValue(var12.toString());
         var9 = var1[3];
         var10 = this.mAuto_lights_off;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(var9 >> 2 & 3);
         var10.setValue(var12.toString());
         var9 = var1[3];
         var11 = this.mAuto_lights_off2;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 0 & 3);
         var11.setValue(var7.toString());
         var6 = this.mAdaptive;
         if ((var1[4] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var9 = var1[5];
         var11 = this.mCar_light_off;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 6 & 3);
         var11.setValue(var7.toString());
         var9 = var1[5];
         var11 = this.mHeadlight_off_timer;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 3 & 7);
         var11.setValue(var7.toString());
         var9 = var1[5];
         var11 = this.mAuto_light_open;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 0 & 7);
         var11.setValue(var7.toString());
         this._Height = var1[6] & 255;
         OnSwitchPreference var14 = this.mHeight;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(this._Height - 13);
         var14.setValue("0", var7.toString());
         this._Height2 = var1[7] & 255;
         OnSwitchPreference var13 = this.mHeight2;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(this._Height2 - 20);
         var13.setValue("0", var12.toString());
         var9 = var1[4];
         var10 = this.mBrightness_control;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(var9 >> 6 & 1);
         var10.setValue(var12.toString());
         var6 = this.mActive_display;
         if ((var1[4] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mNavigation;
         if ((var1[4] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         this._Calibration = var1[8] & 255;
         var13 = this.mCalibration;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(this._Calibration - 2);
         var13.setValue("0", var12.toString());
         var6 = this.mIntelligent_brake;
         if ((var1[4] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var9 = var1[4];
         var11 = this.mMonitor_volume;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 0 & 3);
         var11.setValue(var7.toString());
         var6 = this.mSync_avg;
         if ((var1[4] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var6 = this.mMode;
         if ((var1[9] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var6.setChecked(var3);
         var9 = var1[9];
         var11 = this.mAlarm_distance;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(var9 >> 6 & 1);
         var11.setValue(var7.toString());
         var9 = var1[9];
         var10 = this.mAlarm_volume;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(var9 >> 4 & 3);
         var10.setValue(var12.toString());
         var6 = this.mDistance_system;
         var3 = var4;
         if ((8 & var1[9]) != 0) {
            var3 = true;
         }

         var6.setChecked(var3);
         this._Alarm_distance2 = var1[9] >> 1 & 3;
         OnSwitchPreference var8 = this.mAlarm_distance2;
         var12 = new StringBuilder();
         var12.append("");
         var12.append(this._Alarm_distance2);
         var8.setValue("0", var12.toString());
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492867);
      this.mWipers_induction = this.findHCheckBoxPreference("wipers_induction");
      this.mAutomatic_latch = this.findHCheckBoxPreference("automatic_latch");
      this.mThree_signal = this.findHCheckBoxPreference("three_signal");
      this.mAdaptive = this.findHCheckBoxPreference("adaptive");
      this.mActive_display = this.findHCheckBoxPreference("active_display");
      this.mNavigation = this.findHCheckBoxPreference("navigation");
      this.mIntelligent_brake = this.findHCheckBoxPreference("intelligent_brake");
      this.mSync_avg = this.findHCheckBoxPreference("sync_avg");
      this.mMode = this.findHCheckBoxPreference("mode");
      this.mDistance_system = this.findHCheckBoxPreference("distance_system");
      this.mBlind_spot = this.findHCheckBoxPreference("blind_spot");
      this.mHigh_beam = this.findHCheckBoxPreference("high_beam");
      this.mIntervene = this.findHCheckBoxPreference("intervene");
      this.mWarning = this.findHCheckBoxPreference("warning");
      this.mSpeed_dis = this.findHCheckBoxPreference("speed_dis");
      this.mBose_centerpoint = this.findHCheckBoxPreference("bose_centerpoint");
      this.mBose_audiopilot = this.findHCheckBoxPreference("bose_audiopilot");
      this.mLock_beep = this.findSwitchboxPreference("lock_beep");
      this.mOuto_lock_mode = this.findSwitchboxPreference("outo_lock_mode");
      this.mSecurity = this.findSwitchboxPreference("security");
      this.mLock_mode = this.findSwitchboxPreference("lock_mode");
      this.mTurn_volume = this.findSwitchboxPreference("turn_volume");
      this.mAuto_lights_off = this.findSwitchboxPreference("auto_lights_off");
      this.mAuto_lights_off2 = this.findSwitchboxPreference("auto_lights_off2");
      this.mCar_light_off = this.findSwitchboxPreference("car_light_off");
      this.mHeadlight_off_timer = this.findSwitchboxPreference("headlight_off_timer");
      this.mAuto_light_open = this.findSwitchboxPreference("auto_light_open");
      this.mBrightness_control = this.findSwitchboxPreference("brightness_control");
      this.mMonitor_volume = this.findSwitchboxPreference("monitor_volume");
      this.mAlarm_distance = this.findSwitchboxPreference("alarm_distance");
      this.mAlarm_volume = this.findSwitchboxPreference("alarm_volume");
      this.mGohome = this.findSwitchboxPreference("gohome");
      this.mIntervene_timing = this.findSwitchboxPreference("intervene_timing");
      this.mSensitivity = this.findSwitchboxPreference("sensitivity");
      this.mWarning_type = this.findSwitchboxPreference("warning_type");
      this.mAlarm_volume2 = this.findSwitchboxPreference("alarm_volume2");
      this.mSpeed_warning = this.findSwitchboxPreference("speed_warning");
      this.mWarning_threshold = this.findSwitchboxPreference("warning_threshold");
      this.mHeight = this.findOnSwitchPreference("height");
      this.mHeight2 = this.findOnSwitchPreference("height2");
      this.mCalibration = this.findOnSwitchPreference("calibration");
      this.mAlarm_distance2 = this.findOnSwitchPreference("alarm_distance2");
      this.mDefault_all = this.findPreferenceScreen("default_all");
      this.mReset_avg = this.findPreferenceScreen("reset_avg");
      this.SendCanBusCmdData2E((byte)-125, new byte[]{9, 0}, 2);
      this.SendCanBusCmdData2E((byte)-125, new byte[]{13, 0}, 2);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mHeight;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var3 = this._Height;
         if (var3 + var4 < 0 || var3 + var4 > 26 || !this.mHeight.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)14, (byte)(this._Height + var4));
      } else {
         var5 = this.mHeight2;
         if (var1 == var5) {
            var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var3 = this._Height2;
            if (var3 + var4 < 0 || var3 + var4 > 40 || !this.mHeight2.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)16, (byte)(this._Height2 + var4));
         } else {
            var5 = this.mCalibration;
            if (var1 == var5) {
               var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
               var3 = this._Calibration;
               if (var3 + var4 < 0 || var3 + var4 > 4 || !this.mCalibration.getIsDow()) {
                  return true;
               }

               this.SetCmdkey((byte)19, (byte)(this._Calibration + var4));
            } else {
               var5 = this.mAlarm_distance2;
               if (var1 == var5) {
                  var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
                  var3 = this._Alarm_distance2;
                  if (var3 + var4 < 0 || var3 + var4 > 2 || !this.mAlarm_distance2.getIsDow()) {
                     return true;
                  }

                  this.SetCmdkey((byte)29, (byte)(this._Alarm_distance2 + var4));
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
         if (var1 == this.mLock_beep) {
            this.SetCmdkey((byte)1, (byte)(var3 + 0));
         } else if (var1 == this.mOuto_lock_mode) {
            this.SetCmdkey((byte)2, (byte)(var3 + 0));
         } else if (var1 == this.mSecurity) {
            this.SetCmdkey((byte)3, (byte)(var3 + 0));
         } else if (var1 == this.mLock_mode) {
            this.SetCmdkey((byte)4, (byte)(var3 + 0));
         } else if (var1 == this.mTurn_volume) {
            this.SetCmdkey((byte)7, (byte)(var3 + 0));
         } else if (var1 == this.mAuto_lights_off) {
            this.SetCmdkey((byte)8, (byte)(var3 + 0));
         } else if (var1 == this.mAuto_lights_off2) {
            this.SetCmdkey((byte)9, (byte)(var3 + 0));
         } else if (var1 == this.mCar_light_off) {
            this.SetCmdkey((byte)11, (byte)(var3 + 0));
         } else if (var1 == this.mHeadlight_off_timer) {
            this.SetCmdkey((byte)12, (byte)(var3 + 0));
         } else if (var1 == this.mAuto_light_open) {
            this.SetCmdkey((byte)13, (byte)(var3 + 0));
         } else if (var1 == this.mBrightness_control) {
            this.SetCmdkey((byte)15, (byte)(var3 + 0));
         } else if (var1 == this.mMonitor_volume) {
            this.SetCmdkey((byte)21, (byte)(var3 + 0));
         } else if (var1 == this.mAlarm_distance) {
            this.SetCmdkey((byte)26, (byte)(var3 + 0));
         } else if (var1 == this.mAlarm_volume) {
            this.SetCmdkey((byte)27, (byte)(var3 + 0));
         } else if (var1 == this.mGohome) {
            this.SetCmdkey((byte)33, (byte)(var3 + 0));
         } else if (var1 == this.mIntervene_timing) {
            this.SetCmdkey((byte)49, (byte)(var3 + 0));
         } else if (var1 == this.mSensitivity) {
            this.SetCmdkey((byte)50, (byte)(var3 + 0));
         } else if (var1 == this.mWarning_type) {
            this.SetCmdkey((byte)52, (byte)(var3 + 0));
         } else if (var1 == this.mAlarm_volume2) {
            this.SetCmdkey((byte)53, (byte)(var3 + 0));
         } else if (var1 == this.mSpeed_warning) {
            this.SetCmdkey((byte)65, (byte)(var3 + 0));
         } else if (var1 == this.mWarning_threshold) {
            this.SetCmdkey((byte)66, (byte)(var3 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mWipers_induction;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)0, (byte)0);
         } else {
            this.SetCmdkey((byte)0, (byte)1);
         }
      } else {
         var2 = this.mAutomatic_latch;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)5, (byte)0);
            } else {
               this.SetCmdkey((byte)5, (byte)1);
            }
         } else {
            var2 = this.mThree_signal;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)6, (byte)0);
               } else {
                  this.SetCmdkey((byte)6, (byte)1);
               }
            } else {
               var2 = this.mAdaptive;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)10, (byte)0);
                  } else {
                     this.SetCmdkey((byte)10, (byte)1);
                  }
               } else {
                  var2 = this.mActive_display;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)17, (byte)0);
                     } else {
                        this.SetCmdkey((byte)17, (byte)1);
                     }
                  } else {
                     var2 = this.mNavigation;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)18, (byte)0);
                        } else {
                           this.SetCmdkey((byte)18, (byte)1);
                        }
                     } else {
                        var2 = this.mIntelligent_brake;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)20, (byte)0);
                           } else {
                              this.SetCmdkey((byte)20, (byte)1);
                           }
                        } else {
                           var2 = this.mSync_avg;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)22, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)22, (byte)1);
                              }
                           } else if (var1 == this.mReset_avg) {
                              this.AlertDialog(new AlertDialogCallBack(this) {
                                 final canbus107settings this$0;

                                 {
                                    this.this$0 = var1;
                                 }

                                 public void OkClick() {
                                    this.this$0.SetCmdkey((byte)23, (byte)0);
                                 }
                              }, this.mReset_avg.getTitle().toString());
                           } else if (var1 == this.mDefault_all) {
                              this.AlertDialog(new AlertDialogCallBack(this) {
                                 final canbus107settings this$0;

                                 {
                                    this.this$0 = var1;
                                 }

                                 public void OkClick() {
                                    this.this$0.SetCmdkey((byte)24, (byte)0);
                                 }
                              }, this.mDefault_all.getTitle().toString());
                           } else {
                              var2 = this.mMode;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)25, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)25, (byte)1);
                                 }
                              } else {
                                 var2 = this.mDistance_system;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)28, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)28, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mBlind_spot;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)30, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)30, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mHigh_beam;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)32, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)32, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mIntervene;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)48, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)48, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mWarning;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)51, (byte)0);
                                                } else {
                                                   this.SetCmdkey((byte)51, (byte)1);
                                                }
                                             } else {
                                                var2 = this.mSpeed_dis;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)64, (byte)0);
                                                   } else {
                                                      this.SetCmdkey((byte)64, (byte)1);
                                                   }
                                                } else {
                                                   var2 = this.mBose_centerpoint;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)80, (byte)0);
                                                      } else {
                                                         this.SetCmdkey((byte)80, (byte)1);
                                                      }
                                                   } else {
                                                      var2 = this.mBose_audiopilot;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)81, (byte)0);
                                                         } else {
                                                            this.SetCmdkey((byte)81, (byte)1);
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
}
