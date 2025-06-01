package com.microntek.controlsettings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class canbus55settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private byte[] airdata = new byte[5];
   private byte[] contrldata = new byte[13];
   private int data_mAlarm_vol = 0;
   private PreferenceScreen factory_settings;
   private SwitchboxPreference mAdaptive;
   private SwitchboxPreference mAdaptive1e;
   private SwitchboxPreference mAirpartition;
   private SwitchboxPreference mAirqualitysensor;
   private OnSwitchPreference mAlarm_vol;
   private SwitchboxPreference mAlert_type;
   private PreferenceCategory mAriSettings;
   private SwitchboxPreference mAri_mode;
   private SwitchboxPreference mAuto_collision;
   private HCheckBoxPreference mAuto_heating;
   private HCheckBoxPreference mAuto_relock;
   private HCheckBoxPreference mAuto_ventilation;
   private HCheckBoxPreference mAuto_wipers;
   private SwitchboxPreference mAutomatic_latch;
   private SwitchboxPreference mAutomodewindlevel;
   private HCheckBoxPreference mBackdefog;
   private HCheckBoxPreference mBlind;
   private HCheckBoxPreference mBy_drive;
   private SwitchboxPreference mCar_unlocked;
   private HCheckBoxPreference mChange_warning;
   private HCheckBoxPreference mDelaylock;
   private PreferenceScreen mDetection;
   private HCheckBoxPreference mDooropenlock;
   private HCheckBoxPreference mEasyeeat;
   private HCheckBoxPreference mFindlight;
   private HCheckBoxPreference mFolding;
   private SwitchboxPreference mForward_system;
   private HCheckBoxPreference mFrontdefog;
   private SwitchboxPreference mHands_free;
   private SwitchboxPreference mHeadlightdelay;
   private HCheckBoxPreference mIndividual_acc;
   private SwitchboxPreference mLfRight;
   private HCheckBoxPreference mMemory;
   private HCheckBoxPreference mMotion_steering;
   private SwitchboxPreference mParkunlock;
   private SwitchboxPreference mRamp;
   private SwitchboxPreference mRear_air;
   private HCheckBoxPreference mRear_alarm;
   private HCheckBoxPreference mRear_seat;
   private SwitchboxPreference mRemote_air;
   private SwitchboxPreference mRemote_seat;
   private HCheckBoxPreference mRemote_start;
   private SwitchboxPreference mRemotelock5;
   private HCheckBoxPreference mRemoteunlock5;
   private SwitchboxPreference mRemoteunlocksettings;
   private HCheckBoxPreference mRtmirror;
   private HCheckBoxPreference mSeat_heating;
   private HCheckBoxPreference mStartlatch;
   private HCheckBoxPreference mStatus_notifi;
   private SwitchboxPreference mTemp;
   private HCheckBoxPreference mUnkey;
   private HCheckBoxPreference mWindow_control;
   private HCheckBoxPreference mWipers;
   private SwitchboxPreference radarswitch;
   private SharedPreferences sp;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E(var1, new byte[]{var2}, 1);
   }

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData2E(var1, new byte[]{var2, var3}, 2);
   }

   private void initPreferenceShow(Preference var1) {
      if (var1 != null && !this.sp.getBoolean(var1.getKey(), true)) {
         this.removePreference(var1);
      }

   }

   private boolean isShow(Preference var1, boolean var2) {
      if (var1 == null) {
         return true;
      } else if ((var1 == this.mDelaylock || var1 == this.mWipers) && BaseApplication.getInstance().getCarType() == 2) {
         return false;
      } else if (var2) {
         this.addPreference(var1);
         return true;
      } else {
         this.removePreference(var1);
         return false;
      }
   }

   protected void ProcessData(byte[] var1) {
      boolean var6 = false;
      boolean var4 = false;
      boolean var12 = false;
      boolean var7 = false;
      boolean var9 = false;
      boolean var13 = false;
      boolean var8 = false;
      boolean var10 = false;
      boolean var5 = false;
      boolean var11 = false;
      int var2 = var1[0];
      if (var1.length > 2) {
         if (var2 == 4) {
            var2 = var1[2];
         } else {
            boolean var3;
            byte[] var14;
            SwitchboxPreference var15;
            StringBuilder var22;
            SwitchboxPreference var23;
            StringBuilder var24;
            HCheckBoxPreference var25;
            if (var2 == 5) {
               var2 = var1[2];
               var14 = this.airdata;
               var14[0] = (byte)((var2 & 192) >> 6 & 3);
               var14[1] = (byte)((var2 & 48) >> 4 & 3);
               var14[2] = (byte)((var2 & 12) >> 2 & 3);
               var14[3] = (byte)((var2 & 2) >> 1 & 1);
               var14[4] = (byte)(var2 & 1);
               var15 = this.mAutomodewindlevel;
               var22 = new StringBuilder();
               var22.append(this.airdata[0]);
               var22.append("");
               var15.setValue(var22.toString());
               var23 = this.mAirqualitysensor;
               var24 = new StringBuilder();
               var24.append(this.airdata[1]);
               var24.append("");
               var23.setValue(var24.toString());
               var23 = this.mAirpartition;
               var24 = new StringBuilder();
               var24.append(this.airdata[2]);
               var24.append("");
               var23.setValue(var24.toString());
               var25 = this.mBackdefog;
               if (this.airdata[3] > 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var25.setChecked(var3);
               var25 = this.mFrontdefog;
               if (this.airdata[4] > 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var25.setChecked(var3);
               if ((var1[3] & 64) != 0) {
                  var2 = var1[3] >> 4 & 3;
                  if (var2 == 3) {
                     this.mRemote_seat.setValue("2");
                  } else {
                     var15 = this.mRemote_seat;
                     var22 = new StringBuilder();
                     var22.append("");
                     var22.append(var2);
                     var15.setValue(var22.toString());
                  }
               } else {
                  var15 = this.mRemote_seat;
                  var22 = new StringBuilder();
                  var22.append("");
                  var22.append(var1[3] >> 3 & 1);
                  var15.setValue(var22.toString());
               }

               var23 = this.mAri_mode;
               var24 = new StringBuilder();
               var24.append("");
               var24.append(var1[3] & 3);
               var23.setValue(var24.toString());
               var25 = this.mSeat_heating;
               var3 = var11;
               if ((var1[3] & 128) != 0) {
                  var3 = true;
               }

               var25.setChecked(var3);
            } else {
               byte var20;
               if (var2 == 6) {
                  var20 = var1[2];
                  if (var1[2] < 0) {
                     this.contrldata[0] = 1;
                  } else {
                     this.contrldata[0] = 0;
                  }

                  var14 = this.contrldata;
                  var14[1] = (byte)((var20 & 96) >> 5 & 3);
                  var14[2] = (byte)((var20 & 16) >> 4 & 1);
                  var14[3] = (byte)((var20 & 8) >> 3 & 1);
                  var14[4] = (byte)((var20 & 6) >> 1 & 3);
                  var14[5] = (byte)(var20 & 1);
                  var20 = var1[3];
                  if (var1[3] < 0) {
                     var14[6] = 1;
                  } else {
                     var14[6] = 0;
                  }

                  byte[] var28 = this.contrldata;
                  var28[7] = (byte)((var20 & 96) >> 5 & 3);
                  var28[8] = (byte)((var20 & 16) >> 4 & 1);
                  var28[9] = (byte)((var20 & 8) >> 3 & 1);
                  var28[10] = (byte)((var20 & 4) >> 2 & 1);
                  var28[11] = (byte)((var20 & 2) >> 1 & 1);
                  var28[12] = (byte)(var20 & 1);
                  var25 = this.mFindlight;
                  if (var28[0] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var23 = this.mHeadlightdelay;
                  var24 = new StringBuilder();
                  var24.append(this.contrldata[1]);
                  var24.append("");
                  var23.setValue(var24.toString());
                  var25 = this.mDooropenlock;
                  if (this.contrldata[2] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mStartlatch;
                  if (this.contrldata[3] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var15 = this.mParkunlock;
                  var22 = new StringBuilder();
                  var22.append(this.contrldata[4]);
                  var22.append("");
                  var15.setValue(var22.toString());
                  var25 = this.mDelaylock;
                  if (this.contrldata[5] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mRemoteunlock5;
                  if (this.contrldata[6] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var23 = this.mRemotelock5;
                  var24 = new StringBuilder();
                  var24.append(this.contrldata[7]);
                  var24.append("");
                  var23.setValue(var24.toString());
                  var23 = this.mRemoteunlocksettings;
                  var24 = new StringBuilder();
                  var24.append(this.contrldata[8]);
                  var24.append("");
                  var23.setValue(var24.toString());
                  var25 = this.mWipers;
                  if ((var1[3] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mRemote_start;
                  var3 = var6;
                  if ((var1[3] & 2) != 0) {
                     var3 = true;
                  }

                  var25.setChecked(var3);
               } else if (var2 == 10) {
                  if (var1[1] < 3) {
                     return;
                  }

                  var25 = this.mUnkey;
                  if ((var1[2] & 64) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mBy_drive;
                  if ((var1[2] & 32) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mAuto_relock;
                  if ((var1[2] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mBlind;
                  if ((var1[2] & 4) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  if ((var1[2] & 128) == 128) {
                     this.mCar_unlocked.setValue("1");
                  } else {
                     this.mCar_unlocked.setValue("0");
                  }

                  if ((var1[2] & 2) == 2) {
                     this.mAutomatic_latch.setValue("2");
                  } else if ((var1[2] & 1) == 1) {
                     this.mAutomatic_latch.setValue("1");
                  } else {
                     this.mAutomatic_latch.setValue("0");
                  }

                  if ((var1[3] & 128) != 0) {
                     this.mLfRight.setValue("1");
                  } else {
                     this.mLfRight.setValue("0");
                  }

                  if ((var1[3] & 64) != 0) {
                     this.mAdaptive.setValue("1");
                  } else {
                     this.mAdaptive.setValue("0");
                  }

                  var25 = this.mFolding;
                  if ((var1[3] & 32) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mRtmirror;
                  if ((var1[3] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mEasyeeat;
                  if ((var1[3] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mMemory;
                  if ((var1[3] & 4) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mAuto_wipers;
                  if ((var1[2] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var2 = var1[3] & 3;
                  if (var2 == 3) {
                     this.mAuto_collision.setValue("2");
                  } else if (var2 == 1) {
                     this.mAuto_collision.setValue("1");
                  } else {
                     this.mAuto_collision.setValue("0");
                  }

                  var25 = this.mStatus_notifi;
                  if ((var1[4] & 1) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mWindow_control;
                  if ((var1[4] & 4) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var23 = this.mRamp;
                  var24 = new StringBuilder();
                  if ((var1[4] & 8) != 0) {
                     var20 = 0;
                  } else {
                     var20 = 1;
                  }

                  var24.append(var20);
                  var24.append("");
                  var23.setValue(var24.toString());
                  var25 = this.mIndividual_acc;
                  if ((var1[4] & 64) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  if (var1[1] < 4) {
                     return;
                  }

                  var15 = this.mAdaptive1e;
                  var22 = new StringBuilder();
                  var22.append((var1[5] & 192) >> 6);
                  var22.append("");
                  var15.setValue(var22.toString());
                  var25 = this.mMotion_steering;
                  if ((var1[5] & 32) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mRear_alarm;
                  if ((var1[5] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var25 = this.mChange_warning;
                  if ((var1[5] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var25.setChecked(var3);
                  var23 = this.mAlert_type;
                  var24 = new StringBuilder();
                  var24.append((var1[5] & 4) >> 2);
                  var24.append("");
                  var23.setValue(var24.toString());
                  var15 = this.mForward_system;
                  var22 = new StringBuilder();
                  var22.append(var1[5] & 3);
                  var22.append("");
                  var15.setValue(var22.toString());
                  var25 = this.mRear_seat;
                  var3 = var4;
                  if ((var1[4] & 128) != 0) {
                     var3 = true;
                  }

                  var25.setChecked(var3);
                  var23 = this.mHands_free;
                  var24 = new StringBuilder();
                  var24.append(var1[4] & 3);
                  var24.append("");
                  var23.setValue(var24.toString());
               } else {
                  SwitchboxPreference var17;
                  byte var21;
                  if (var2 == 7) {
                     var21 = var1[2];
                     var17 = this.radarswitch;
                     var22 = new StringBuilder();
                     var22.append(var21 & 3);
                     var22.append("");
                     var17.setValue(var22.toString());
                  } else if (var2 == 3) {
                     var21 = var1[3];
                     var17 = this.mTemp;
                     var22 = new StringBuilder();
                     var22.append((var21 & 128) >> 7);
                     var22.append("");
                     var17.setValue(var22.toString());
                  } else if (var2 == 68) {
                     var25 = this.mAuto_ventilation;
                     if ((var1[2] & 64) != 0) {
                        var3 = true;
                     } else {
                        var3 = false;
                     }

                     var25.setChecked(var3);
                     var25 = this.mAuto_heating;
                     var3 = var12;
                     if ((var1[2] & 128) != 0) {
                        var3 = true;
                     }

                     var25.setChecked(var3);
                     var21 = var1[2];
                     var15 = this.mRemote_air;
                     var22 = new StringBuilder();
                     var22.append((var21 & 32) >> 5);
                     var22.append("");
                     var15.setValue(var22.toString());
                     var21 = var1[2];
                     var23 = this.mRear_air;
                     StringBuilder var18 = new StringBuilder();
                     var18.append((var21 & 24) >> 3);
                     var18.append("");
                     var23.setValue(var18.toString());
                  } else if (var2 != 9) {
                     if (var2 == 11) {
                        var21 = var1[2];
                        var21 = var1[3];
                     } else if (var2 == 13) {
                        this.data_mAlarm_vol = var1[2] & 15;
                        OnSwitchPreference var19 = this.mAlarm_vol;
                        var22 = new StringBuilder();
                        var22.append("");
                        var22.append(this.data_mAlarm_vol);
                        var19.setValue("0", var22.toString());
                     } else if (var2 == 26) {
                        var21 = var1[2];
                        SharedPreferences.Editor var26;
                        SwitchboxPreference var27;
                        HCheckBoxPreference var29;
                        String var31;
                        SharedPreferences.Editor var32;
                        SharedPreferences.Editor var34;
                        String var35;
                        if (var21 != 0) {
                           String var16;
                           HCheckBoxPreference var30;
                           if (var21 != 1) {
                              if (var21 != 2) {
                                 if (var21 != 3) {
                                    if (var21 != 4) {
                                       if (var21 == 5) {
                                          var34 = this.sp.edit();
                                          var16 = this.mRemoteunlock5.getKey();
                                          var30 = this.mRemoteunlock5;
                                          if ((var1[3] & 1) != 0) {
                                             var3 = true;
                                          } else {
                                             var3 = false;
                                          }

                                          var26 = var34.putBoolean(var16, this.isShow(var30, var3));
                                          var31 = this.mRemotelock5.getKey();
                                          var23 = this.mRemotelock5;
                                          if ((2 & var1[3]) != 0) {
                                             var3 = true;
                                          } else {
                                             var3 = false;
                                          }

                                          var34 = var26.putBoolean(var31, this.isShow(var23, var3));
                                          var31 = this.mRemoteunlocksettings.getKey();
                                          var27 = this.mRemoteunlocksettings;
                                          if ((4 & var1[3]) != 0) {
                                             var3 = true;
                                          } else {
                                             var3 = false;
                                          }

                                          var26 = var34.putBoolean(var31, this.isShow(var27, var3));
                                          var35 = this.mCar_unlocked.getKey();
                                          var15 = this.mCar_unlocked;
                                          if ((var1[3] & 16) != 0) {
                                             var3 = true;
                                          } else {
                                             var3 = false;
                                          }

                                          var32 = var26.putBoolean(var35, this.isShow(var15, var3));
                                          var16 = this.mUnkey.getKey();
                                          var25 = this.mUnkey;
                                          if ((var1[3] & 32) != 0) {
                                             var3 = true;
                                          } else {
                                             var3 = false;
                                          }

                                          var26 = var32.putBoolean(var16, this.isShow(var25, var3));
                                          var35 = this.mAutomatic_latch.getKey();
                                          var15 = this.mAutomatic_latch;
                                          var3 = var7;
                                          if ((var1[3] & 64) != 0) {
                                             var3 = true;
                                          }

                                          var26.putBoolean(var35, this.isShow(var15, var3)).commit();
                                       }
                                    } else {
                                       var26 = this.sp.edit();
                                       var35 = this.mDooropenlock.getKey();
                                       var30 = this.mDooropenlock;
                                       if ((var1[3] & 1) != 0) {
                                          var3 = true;
                                       } else {
                                          var3 = false;
                                       }

                                       var26 = var26.putBoolean(var35, this.isShow(var30, var3));
                                       var31 = this.mParkunlock.getKey();
                                       var23 = this.mParkunlock;
                                       if ((2 & var1[3]) != 0) {
                                          var3 = true;
                                       } else {
                                          var3 = false;
                                       }

                                       var26 = var26.putBoolean(var31, this.isShow(var23, var3));
                                       var35 = this.mStartlatch.getKey();
                                       var30 = this.mStartlatch;
                                       if ((4 & var1[3]) != 0) {
                                          var3 = true;
                                       } else {
                                          var3 = false;
                                       }

                                       var26 = var26.putBoolean(var35, this.isShow(var30, var3));
                                       var35 = this.mDelaylock.getKey();
                                       var30 = this.mDelaylock;
                                       var3 = var9;
                                       if ((var1[3] & 8) != 0) {
                                          var3 = true;
                                       }

                                       var26.putBoolean(var35, this.isShow(var30, var3)).commit();
                                    }
                                 } else {
                                    var34 = this.sp.edit();
                                    var31 = this.mFindlight.getKey();
                                    var29 = this.mFindlight;
                                    if ((var1[3] & 1) != 0) {
                                       var3 = true;
                                    } else {
                                       var3 = false;
                                    }

                                    var34 = var34.putBoolean(var31, this.isShow(var29, var3));
                                    var31 = this.mHeadlightdelay.getKey();
                                    var27 = this.mHeadlightdelay;
                                    var3 = var13;
                                    if ((var1[3] & 2) != 0) {
                                       var3 = true;
                                    }

                                    var34.putBoolean(var31, this.isShow(var27, var3)).commit();
                                 }
                              } else {
                                 var26 = this.sp.edit();
                                 var35 = this.mWipers.getKey();
                                 var30 = this.mWipers;
                                 if ((var1[3] & 1) != 0) {
                                    var3 = true;
                                 } else {
                                    var3 = false;
                                 }

                                 var26 = var26.putBoolean(var35, this.isShow(var30, var3));
                                 var31 = this.mMemory.getKey();
                                 var25 = this.mMemory;
                                 if ((4 & var1[3]) != 0) {
                                    var3 = true;
                                 } else {
                                    var3 = false;
                                 }

                                 var32 = var26.putBoolean(var31, this.isShow(var25, var3));
                                 var35 = this.mEasyeeat.getKey();
                                 var29 = this.mEasyeeat;
                                 if ((var1[3] & 8) != 0) {
                                    var3 = true;
                                 } else {
                                    var3 = false;
                                 }

                                 var26 = var32.putBoolean(var35, this.isShow(var29, var3));
                                 var31 = this.mRtmirror.getKey();
                                 var25 = this.mRtmirror;
                                 if ((var1[3] & 16) != 0) {
                                    var3 = true;
                                 } else {
                                    var3 = false;
                                 }

                                 var34 = var26.putBoolean(var31, this.isShow(var25, var3));
                                 var16 = this.mFolding.getKey();
                                 var30 = this.mFolding;
                                 if ((var1[3] & 32) != 0) {
                                    var3 = true;
                                 } else {
                                    var3 = false;
                                 }

                                 var32 = var34.putBoolean(var16, this.isShow(var30, var3));
                                 var16 = this.mAuto_wipers.getKey();
                                 var25 = this.mAuto_wipers;
                                 var3 = var8;
                                 if ((var1[3] & 64) != 0) {
                                    var3 = true;
                                 }

                                 var32.putBoolean(var16, this.isShow(var25, var3)).commit();
                              }
                           } else {
                              var32 = this.sp.edit();
                              var16 = this.mBlind.getKey();
                              var25 = this.mBlind;
                              if ((2 & var1[3]) != 0) {
                                 var3 = true;
                              } else {
                                 var3 = false;
                              }

                              var32 = var32.putBoolean(var16, this.isShow(var25, var3));
                              var16 = this.mAuto_collision.getKey();
                              var23 = this.mAuto_collision;
                              if ((4 & var1[3]) != 0) {
                                 var3 = true;
                              } else {
                                 var3 = false;
                              }

                              var34 = var32.putBoolean(var16, this.isShow(var23, var3));
                              var16 = this.mStatus_notifi.getKey();
                              var30 = this.mStatus_notifi;
                              var3 = var10;
                              if ((var1[3] & 8) != 0) {
                                 var3 = true;
                              }

                              var34.putBoolean(var16, this.isShow(var30, var3)).commit();
                           }
                        } else {
                           var34 = this.sp.edit();
                           var31 = this.mAutomodewindlevel.getKey();
                           var27 = this.mAutomodewindlevel;
                           if ((var1[3] & 1) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var26 = var34.putBoolean(var31, this.isShow(var27, var3));
                           var35 = this.mAirqualitysensor.getKey();
                           var15 = this.mAirqualitysensor;
                           if ((2 & var1[3]) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var32 = var26.putBoolean(var35, this.isShow(var15, var3));
                           var35 = this.mAri_mode.getKey();
                           var27 = this.mAri_mode;
                           if ((4 & var1[3]) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var26 = var32.putBoolean(var35, this.isShow(var27, var3));
                           var31 = this.mFrontdefog.getKey();
                           var25 = this.mFrontdefog;
                           if ((var1[3] & 8) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var32 = var26.putBoolean(var31, this.isShow(var25, var3));
                           var35 = this.mBackdefog.getKey();
                           var29 = this.mBackdefog;
                           if ((var1[3] & 16) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var34 = var32.putBoolean(var35, this.isShow(var29, var3));
                           var31 = this.mAirpartition.getKey();
                           var27 = this.mAirpartition;
                           if ((var1[3] & 32) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var32 = var34.putBoolean(var31, this.isShow(var27, var3));
                           var35 = this.mRemote_seat.getKey();
                           var27 = this.mRemote_seat;
                           if ((var1[3] & 64) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var32 = var32.putBoolean(var35, this.isShow(var27, var3));
                           var35 = this.mAriSettings.getKey();
                           PreferenceCategory var33 = this.mAriSettings;
                           var3 = var5;
                           if ((var1[3] & 255) > 0) {
                              var3 = true;
                           }

                           var32.putBoolean(var35, this.isShow(var33, var3)).commit();
                        }
                     }
                  }
               }
            }
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492926);
      this.mAutomodewindlevel = this.findSwitchboxPreference("automodewindlevel");
      this.mAirqualitysensor = this.findSwitchboxPreference("airqualitysensor");
      this.mAirpartition = this.findSwitchboxPreference("airpartition");
      this.mHeadlightdelay = this.findSwitchboxPreference("headlightdelay");
      this.mParkunlock = this.findSwitchboxPreference("parkunlock");
      this.mRemotelock5 = this.findSwitchboxPreference("remotelock5");
      this.mRemoteunlocksettings = this.findSwitchboxPreference("remoteunlocksettings");
      this.mCar_unlocked = this.findSwitchboxPreference("car_unlocked");
      this.mAutomatic_latch = this.findSwitchboxPreference("automatic_latch");
      this.mLfRight = this.findSwitchboxPreference("lfRight");
      this.mAdaptive = this.findSwitchboxPreference("adaptive");
      this.mAri_mode = this.findSwitchboxPreference("ari_mode");
      this.mRemote_seat = this.findSwitchboxPreference("remote_seat");
      this.mAuto_collision = this.findSwitchboxPreference("auto_collision");
      this.radarswitch = this.findSwitchboxPreference("radarswitch");
      this.mRamp = this.findSwitchboxPreference("ramp");
      this.mAdaptive1e = this.findSwitchboxPreference("adaptive1e");
      this.mAlert_type = this.findSwitchboxPreference("alert_type");
      this.mForward_system = this.findSwitchboxPreference("forward_system");
      this.mHands_free = this.findSwitchboxPreference("hands_free");
      this.mTemp = this.findSwitchboxPreference("temp");
      this.mRemote_air = this.findSwitchboxPreference("remote_air");
      this.mRear_air = this.findSwitchboxPreference("rear_air");
      this.mBackdefog = this.findHCheckBoxPreference("backwindowdefog");
      this.mFrontdefog = this.findHCheckBoxPreference("frontwindowdefog");
      this.mFindlight = this.findHCheckBoxPreference("findlight");
      this.mDooropenlock = this.findHCheckBoxPreference("dooropenlock");
      this.mStartlatch = this.findHCheckBoxPreference("startlatch");
      this.mDelaylock = this.findHCheckBoxPreference("delaylock");
      this.mRemoteunlock5 = this.findHCheckBoxPreference("remoteunlock5");
      this.mWipers = this.findHCheckBoxPreference("wipers");
      this.mRemote_start = this.findHCheckBoxPreference("remote_start");
      this.mUnkey = this.findHCheckBoxPreference("unkey");
      this.mBy_drive = this.findHCheckBoxPreference("by_drive");
      this.mAuto_relock = this.findHCheckBoxPreference("auto_relock");
      this.mBlind = this.findHCheckBoxPreference("blind");
      this.mFolding = this.findHCheckBoxPreference("folding");
      this.mRtmirror = this.findHCheckBoxPreference("rtmirror");
      this.mEasyeeat = this.findHCheckBoxPreference("easyeeat");
      this.mMemory = this.findHCheckBoxPreference("memory");
      this.mAuto_wipers = this.findHCheckBoxPreference("auto_wipers");
      this.mStatus_notifi = this.findHCheckBoxPreference("status_notifi");
      this.mWindow_control = this.findHCheckBoxPreference("window_control");
      this.mIndividual_acc = this.findHCheckBoxPreference("individual_acc");
      this.mMotion_steering = this.findHCheckBoxPreference("motion_steering");
      this.mRear_alarm = this.findHCheckBoxPreference("rear_alarm");
      this.mChange_warning = this.findHCheckBoxPreference("change_warning");
      this.mRear_seat = this.findHCheckBoxPreference("rear_seat");
      this.mAuto_ventilation = this.findHCheckBoxPreference("auto_ventilation");
      this.mAuto_heating = this.findHCheckBoxPreference("auto_heating");
      this.mSeat_heating = this.findHCheckBoxPreference("seat_heating");
      this.mAriSettings = (PreferenceCategory)this.findPreference("ari_setting");
      this.factory_settings = this.findPreferenceScreen("factory_settings");
      this.mDetection = this.findPreferenceScreen("detection");
      this.mAlarm_vol = this.findOnSwitchPreference("alarm_vol");
      this.SetCmdkey((byte)-112, (byte)68);
      this.SetCmdkey((byte)-127, (byte)0);
      this.SetCmdkey((byte)-127, (byte)1);
      this.SetCmdkey((byte)-112, (byte)10);
      this.SetCmdkey((byte)-112, (byte)26);
      this.SetCmdkey((byte)-112, (byte)5);
      this.SetCmdkey((byte)-112, (byte)6);
      this.SetCmdkey((byte)-112, (byte)7);
      this.SetCmdkey((byte)-112, (byte)13);
      this.sp = this.getSharedPreferences("canbus55settings", 0);
      this.initPreferenceShow(this.mAutomodewindlevel);
      this.initPreferenceShow(this.mAirqualitysensor);
      this.initPreferenceShow(this.mAri_mode);
      this.initPreferenceShow(this.mFrontdefog);
      this.initPreferenceShow(this.mBackdefog);
      this.initPreferenceShow(this.mAirpartition);
      this.initPreferenceShow(this.mRemote_seat);
      this.initPreferenceShow(this.mAriSettings);
      this.initPreferenceShow(this.mBlind);
      this.initPreferenceShow(this.mWipers);
      this.initPreferenceShow(this.mMemory);
      this.initPreferenceShow(this.mEasyeeat);
      this.initPreferenceShow(this.mRtmirror);
      this.initPreferenceShow(this.mFolding);
      this.initPreferenceShow(this.mAuto_wipers);
      this.initPreferenceShow(this.mFindlight);
      this.initPreferenceShow(this.mHeadlightdelay);
      this.initPreferenceShow(this.mDooropenlock);
      this.initPreferenceShow(this.mParkunlock);
      this.initPreferenceShow(this.mStartlatch);
      this.initPreferenceShow(this.mDelaylock);
      this.initPreferenceShow(this.mRemoteunlock5);
      this.initPreferenceShow(this.mRemotelock5);
      this.initPreferenceShow(this.mRemoteunlocksettings);
      this.initPreferenceShow(this.mCar_unlocked);
      this.initPreferenceShow(this.mUnkey);
      this.initPreferenceShow(this.mAutomatic_latch);
      this.initPreferenceShow(this.mAuto_collision);
      this.initPreferenceShow(this.mStatus_notifi);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var6 = this.mAlarm_vol;
      byte var3 = 1;
      int var4;
      int var5;
      if (var1 == var6) {
         var4 = Integer.parseInt((String)var6.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var5 = this.data_mAlarm_vol;
         if (var5 + var4 < 0 || var5 + var4 > 15 || !this.mAlarm_vol.getIsDow()) {
            return true;
         }

         this.SendCanBusCmdData2E((byte)-120, new byte[]{(byte)(this.data_mAlarm_vol + var4 & 255)}, 1);
      }

      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var8) {
         return false;
      }

      try {
         var5 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var7) {
         return true;
      }

      if (var4 == var5) {
         return true;
      } else {
         if (var1 == this.mAutomodewindlevel) {
            this.SetCmdkey((byte)-126, (byte)0, (byte)var4);
         } else if (var1 == this.mAirqualitysensor) {
            this.SetCmdkey((byte)-126, (byte)1, (byte)var4);
         } else if (var1 == this.mAirpartition) {
            this.SetCmdkey((byte)-126, (byte)2, (byte)var4);
         } else if (var1 == this.mHeadlightdelay) {
            this.SetCmdkey((byte)-125, (byte)1, (byte)var4);
         } else if (var1 == this.mParkunlock) {
            this.SetCmdkey((byte)-125, (byte)4, (byte)var4);
         } else if (var1 == this.mRemotelock5) {
            this.SetCmdkey((byte)-125, (byte)7, (byte)var4);
         } else if (var1 == this.mRemoteunlocksettings) {
            this.SetCmdkey((byte)-125, (byte)8, (byte)var4);
         } else if (var1 == this.mCar_unlocked) {
            this.SetCmdkey((byte)-125, (byte)12, (byte)var4);
         } else if (var1 == this.mAutomatic_latch) {
            this.SetCmdkey((byte)-125, (byte)23, (byte)var4);
         } else if (var1 == this.mLfRight) {
            this.SetCmdkey((byte)-125, (byte)16, (byte)var4);
         } else if (var1 == this.mAdaptive) {
            this.SetCmdkey((byte)-125, (byte)17, (byte)var4);
         } else if (var1 == this.mAri_mode) {
            this.SetCmdkey((byte)-126, (byte)6, (byte)var4);
         } else if (var1 == this.mRemote_seat) {
            this.SetCmdkey((byte)-126, (byte)5, (byte)var4);
         } else if (var1 == this.mAuto_collision) {
            this.SetCmdkey((byte)-125, (byte)25, (byte)var4);
         } else if (var1 == this.radarswitch) {
            this.SetCmdkey((byte)-124, (byte)var4);
         } else if (var1 == this.mRamp) {
            if (var4 != 0) {
               var3 = 0;
            }

            this.SetCmdkey((byte)-125, (byte)28, (byte)var3);
         } else if (var1 == this.mAdaptive1e) {
            this.SetCmdkey((byte)-125, (byte)30, (byte)var4);
         } else if (var1 == this.mAlert_type) {
            this.SetCmdkey((byte)-125, (byte)34, (byte)var4);
         } else if (var1 == this.mForward_system) {
            this.SetCmdkey((byte)-125, (byte)35, (byte)var4);
         } else if (var1 == this.mHands_free) {
            this.SetCmdkey((byte)-125, (byte)37, (byte)var4);
         } else if (var1 == this.mTemp) {
            this.SetCmdkey((byte)-126, (byte)9, (byte)var4);
         } else if (var1 == this.mRemote_air) {
            this.SetCmdkey((byte)-126, (byte)67, (byte)var4);
         } else if (var1 == this.mRear_air) {
            this.SetCmdkey((byte)-126, (byte)68, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mBackdefog;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)-126, (byte)3, (byte)0);
         } else {
            this.SetCmdkey((byte)-126, (byte)3, (byte)1);
         }
      } else {
         var2 = this.mFrontdefog;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)-126, (byte)4, (byte)0);
            } else {
               this.SetCmdkey((byte)-126, (byte)4, (byte)1);
            }
         } else {
            var2 = this.mFindlight;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)-125, (byte)0, (byte)0);
               } else {
                  this.SetCmdkey((byte)-125, (byte)0, (byte)1);
               }
            } else {
               var2 = this.mDooropenlock;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)-125, (byte)2, (byte)0);
                  } else {
                     this.SetCmdkey((byte)-125, (byte)2, (byte)1);
                  }
               } else {
                  var2 = this.mStartlatch;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)-125, (byte)3, (byte)0);
                     } else {
                        this.SetCmdkey((byte)-125, (byte)3, (byte)1);
                     }
                  } else {
                     var2 = this.mDelaylock;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)-125, (byte)5, (byte)0);
                        } else {
                           this.SetCmdkey((byte)-125, (byte)5, (byte)1);
                        }
                     } else {
                        var2 = this.mRemoteunlock5;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)-125, (byte)6, (byte)0);
                           } else {
                              this.SetCmdkey((byte)-125, (byte)6, (byte)1);
                           }
                        } else {
                           var2 = this.mWipers;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)-125, (byte)9, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)-125, (byte)9, (byte)1);
                              }
                           } else {
                              var2 = this.mRemote_start;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)-125, (byte)11, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)-125, (byte)11, (byte)1);
                                 }
                              } else {
                                 var2 = this.mUnkey;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)-125, (byte)13, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)-125, (byte)13, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mBy_drive;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)-125, (byte)14, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)-125, (byte)14, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mAuto_relock;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)-125, (byte)15, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)-125, (byte)15, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mBlind;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)-125, (byte)22, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)-125, (byte)22, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mFolding;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)-125, (byte)18, (byte)0);
                                                } else {
                                                   this.SetCmdkey((byte)-125, (byte)18, (byte)1);
                                                }
                                             } else {
                                                var2 = this.mRtmirror;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)-125, (byte)19, (byte)0);
                                                   } else {
                                                      this.SetCmdkey((byte)-125, (byte)19, (byte)1);
                                                   }
                                                } else {
                                                   var2 = this.mEasyeeat;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)-125, (byte)20, (byte)0);
                                                      } else {
                                                         this.SetCmdkey((byte)-125, (byte)20, (byte)1);
                                                      }
                                                   } else {
                                                      var2 = this.mMemory;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)-125, (byte)21, (byte)0);
                                                         } else {
                                                            this.SetCmdkey((byte)-125, (byte)21, (byte)1);
                                                         }
                                                      } else {
                                                         var2 = this.mAuto_wipers;
                                                         if (var1 == var2) {
                                                            if (var2.mChecked) {
                                                               this.SetCmdkey((byte)-125, (byte)24, (byte)0);
                                                            } else {
                                                               this.SetCmdkey((byte)-125, (byte)24, (byte)1);
                                                            }
                                                         } else {
                                                            var2 = this.mStatus_notifi;
                                                            if (var1 == var2) {
                                                               if (var2.mChecked) {
                                                                  this.SetCmdkey((byte)-125, (byte)26, (byte)0);
                                                               } else {
                                                                  this.SetCmdkey((byte)-125, (byte)26, (byte)1);
                                                               }
                                                            } else {
                                                               var2 = this.mWindow_control;
                                                               if (var1 == var2) {
                                                                  if (var2.mChecked) {
                                                                     this.SetCmdkey((byte)-125, (byte)27, (byte)0);
                                                                  } else {
                                                                     this.SetCmdkey((byte)-125, (byte)27, (byte)1);
                                                                  }
                                                               } else {
                                                                  var2 = this.mIndividual_acc;
                                                                  if (var1 == var2) {
                                                                     if (var2.mChecked) {
                                                                        this.SetCmdkey((byte)-125, (byte)29, (byte)0);
                                                                     } else {
                                                                        this.SetCmdkey((byte)-125, (byte)29, (byte)1);
                                                                     }
                                                                  } else {
                                                                     var2 = this.mMotion_steering;
                                                                     if (var1 == var2) {
                                                                        if (var2.mChecked) {
                                                                           this.SetCmdkey((byte)-125, (byte)31, (byte)0);
                                                                        } else {
                                                                           this.SetCmdkey((byte)-125, (byte)31, (byte)1);
                                                                        }
                                                                     } else {
                                                                        var2 = this.mRear_alarm;
                                                                        if (var1 == var2) {
                                                                           if (var2.mChecked) {
                                                                              this.SetCmdkey((byte)-125, (byte)32, (byte)0);
                                                                           } else {
                                                                              this.SetCmdkey((byte)-125, (byte)32, (byte)1);
                                                                           }
                                                                        } else {
                                                                           var2 = this.mChange_warning;
                                                                           if (var1 == var2) {
                                                                              if (var2.mChecked) {
                                                                                 this.SetCmdkey((byte)-125, (byte)33, (byte)0);
                                                                              } else {
                                                                                 this.SetCmdkey((byte)-125, (byte)33, (byte)1);
                                                                              }
                                                                           } else {
                                                                              var2 = this.mRear_seat;
                                                                              if (var1 == var2) {
                                                                                 if (var2.mChecked) {
                                                                                    this.SetCmdkey((byte)-125, (byte)36, (byte)0);
                                                                                 } else {
                                                                                    this.SetCmdkey((byte)-125, (byte)36, (byte)1);
                                                                                 }
                                                                              } else {
                                                                                 var2 = this.mSeat_heating;
                                                                                 if (var1 == var2) {
                                                                                    if (var2.mChecked) {
                                                                                       this.SetCmdkey((byte)-126, (byte)8, (byte)0);
                                                                                    } else {
                                                                                       this.SetCmdkey((byte)-126, (byte)8, (byte)1);
                                                                                    }
                                                                                 } else {
                                                                                    var2 = this.mAuto_ventilation;
                                                                                    if (var1 == var2) {
                                                                                       if (var2.mChecked) {
                                                                                          this.SetCmdkey((byte)-126, (byte)66, (byte)0);
                                                                                       } else {
                                                                                          this.SetCmdkey((byte)-126, (byte)66, (byte)1);
                                                                                       }
                                                                                    } else {
                                                                                       var2 = this.mAuto_heating;
                                                                                       if (var1 == var2) {
                                                                                          if (var2.mChecked) {
                                                                                             this.SetCmdkey((byte)-126, (byte)65, (byte)0);
                                                                                          } else {
                                                                                             this.SetCmdkey((byte)-126, (byte)65, (byte)1);
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
         }
      }

      return false;
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.factory_settings) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus55settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)-125, (byte)-128, (byte)1);
            }
         }, this.factory_settings.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
