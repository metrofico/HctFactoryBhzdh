package com.microntek.controlsettings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus5settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private byte[] airdata = new byte[5];
   private byte[] contrldata = new byte[13];
   private int data_mAlarm_vol = 0;
   private PreferenceScreen factory_settings;
   private SwitchboxPreference mAirpartition;
   private SwitchboxPreference mAirqualitysensor;
   private OnSwitchPreference mAlarm_vol;
   private SwitchboxPreference mAuto_avoidance;
   private HCheckBoxPreference mAuto_relock;
   private SwitchboxPreference mAutomatic_latch;
   private HCheckBoxPreference mAutomatic_wiper;
   private SwitchboxPreference mAutomodewindlevel;
   private HCheckBoxPreference mBackdefog;
   private HCheckBoxPreference mBlind;
   private HCheckBoxPreference mBy_drive;
   private HCheckBoxPreference mCar_status;
   private SwitchboxPreference mCar_unlocked;
   private HCheckBoxPreference mDelaylock;
   private HCheckBoxPreference mDooropenlock;
   private HCheckBoxPreference mFindlight;
   private HCheckBoxPreference mFrontdefog;
   private SwitchboxPreference mHeadlightdelay;
   private HCheckBoxPreference mOperationflag;
   private SwitchboxPreference mParkunlock;
   private SwitchboxPreference mRamp;
   private HCheckBoxPreference mRemote_control;
   private HCheckBoxPreference mRemote_lock;
   private HCheckBoxPreference mRemote_start;
   private SwitchboxPreference mRemotelock5;
   private HCheckBoxPreference mRemoteunlock5;
   private SwitchboxPreference mRemoteunlocksettings;
   private HCheckBoxPreference mStartlatch;
   private HCheckBoxPreference mUnkey;
   private HCheckBoxPreference mWipers;
   private HCheckBoxPreference radarswitch;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E(var1, new byte[]{var2}, 1);
   }

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData2E(var1, new byte[]{var2, var3}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var4 = false;
      boolean var5 = false;
      boolean var3 = false;
      boolean var6 = false;
      byte var2 = var1[0];
      if (var1.length > 2) {
         if (var2 == 4) {
            var2 = var1[2];
         } else {
            StringBuilder var7;
            HCheckBoxPreference var11;
            SwitchboxPreference var13;
            if (var2 == 5) {
               var2 = var1[2];
               var1 = this.airdata;
               var1[0] = (byte)((var2 & 192) >> 6 & 3);
               var1[1] = (byte)((var2 & 48) >> 4 & 3);
               var1[2] = (byte)((var2 & 12) >> 2 & 3);
               var1[3] = (byte)((var2 & 2) >> 1 & 1);
               var1[4] = (byte)(var2 & 1);
               SwitchboxPreference var9 = this.mAutomodewindlevel;
               var7 = new StringBuilder();
               var7.append(this.airdata[0]);
               var7.append("");
               var9.setValue(var7.toString());
               var13 = this.mAirqualitysensor;
               StringBuilder var10 = new StringBuilder();
               var10.append(this.airdata[1]);
               var10.append("");
               var13.setValue(var10.toString());
               var13 = this.mAirpartition;
               var10 = new StringBuilder();
               var10.append(this.airdata[2]);
               var10.append("");
               var13.setValue(var10.toString());
               var11 = this.mBackdefog;
               if (this.airdata[3] > 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var11.setChecked(var3);
               var11 = this.mFrontdefog;
               var3 = var6;
               if (this.airdata[4] > 0) {
                  var3 = true;
               }

               var11.setChecked(var3);
            } else {
               HCheckBoxPreference var15;
               StringBuilder var16;
               if (var2 == 6) {
                  var2 = var1[2];
                  if (var1[2] < 0) {
                     this.contrldata[0] = 1;
                  } else {
                     this.contrldata[0] = 0;
                  }

                  byte[] var14 = this.contrldata;
                  var14[1] = (byte)((var2 & 96) >> 5 & 3);
                  var14[2] = (byte)((var2 & 16) >> 4 & 1);
                  var14[3] = (byte)((var2 & 8) >> 3 & 1);
                  var14[4] = (byte)((var2 & 6) >> 1 & 3);
                  var14[5] = (byte)(var2 & 1);
                  var2 = var1[3];
                  if (var1[3] < 0) {
                     var14[6] = 1;
                  } else {
                     var14[6] = 0;
                  }

                  byte[] var8 = this.contrldata;
                  var8[7] = (byte)((var2 & 96) >> 5 & 3);
                  var8[8] = (byte)((var2 & 16) >> 4 & 1);
                  var8[9] = (byte)((var2 & 8) >> 3 & 1);
                  var8[10] = (byte)((var2 & 4) >> 2 & 1);
                  var8[11] = (byte)((var2 & 2) >> 1 & 1);
                  var8[12] = (byte)(var2 & 1);
                  var15 = this.mFindlight;
                  if (var8[0] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var13 = this.mHeadlightdelay;
                  var16 = new StringBuilder();
                  var16.append(this.contrldata[1]);
                  var16.append("");
                  var13.setValue(var16.toString());
                  var15 = this.mDooropenlock;
                  if (this.contrldata[2] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mStartlatch;
                  if (this.contrldata[3] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var13 = this.mParkunlock;
                  var16 = new StringBuilder();
                  var16.append(this.contrldata[4]);
                  var16.append("");
                  var13.setValue(var16.toString());
                  var15 = this.mDelaylock;
                  if (this.contrldata[5] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mRemoteunlock5;
                  if (this.contrldata[6] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var13 = this.mRemotelock5;
                  var16 = new StringBuilder();
                  var16.append(this.contrldata[7]);
                  var16.append("");
                  var13.setValue(var16.toString());
                  var13 = this.mRemoteunlocksettings;
                  var16 = new StringBuilder();
                  var16.append(this.contrldata[8]);
                  var16.append("");
                  var13.setValue(var16.toString());
                  var15 = this.mOperationflag;
                  if (this.contrldata[12] > 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mWipers;
                  if ((var1[3] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mRemote_lock;
                  if ((4 & var1[3]) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mRemote_start;
                  var3 = var4;
                  if ((var1[3] & 2) != 0) {
                     var3 = true;
                  }

                  var15.setChecked(var3);
               } else if (var2 == 10) {
                  if (var1[1] != 2) {
                     return;
                  }

                  var15 = this.mUnkey;
                  if ((var1[2] & 64) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mBy_drive;
                  if ((var1[2] & 32) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mAuto_relock;
                  if ((var1[2] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mBlind;
                  if ((4 & var1[2]) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
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

                  var13 = this.mAuto_avoidance;
                  var16 = new StringBuilder();
                  var16.append("");
                  var16.append(var1[3] >> 6 & 3);
                  var13.setValue(var16.toString());
                  var15 = this.mCar_status;
                  if ((var1[3] & 32) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mAutomatic_wiper;
                  if ((var1[3] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var15.setChecked(var3);
                  var15 = this.mRemote_control;
                  var3 = var5;
                  if ((var1[3] & 8) != 0) {
                     var3 = true;
                  }

                  var15.setChecked(var3);
               } else if (var2 == 7) {
                  var2 = var1[2];
                  var11 = this.radarswitch;
                  if (var2 > 0) {
                     var3 = true;
                  }

                  var11.setChecked(var3);
               } else if (var2 != 8 && var2 != 9) {
                  if (var2 == 11) {
                     var2 = var1[2];
                     var2 = var1[3];
                  } else if (var2 == 13) {
                     this.data_mAlarm_vol = var1[2] & 15;
                     OnSwitchPreference var12 = this.mAlarm_vol;
                     var7 = new StringBuilder();
                     var7.append("");
                     var7.append(this.data_mAlarm_vol);
                     var12.setValue("0", var7.toString());
                  } else if (var2 == 67) {
                     SwitchboxPreference var17 = this.mRamp;
                     var7 = new StringBuilder();
                     var7.append("");
                     var7.append(var1[2] >> 7 & 1);
                     var17.setValue(var7.toString());
                  }
               }
            }
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492923);
      this.mAutomodewindlevel = (SwitchboxPreference)this.findPreference("automodewindlevel");
      this.mAirqualitysensor = (SwitchboxPreference)this.findPreference("airqualitysensor");
      this.mAirpartition = (SwitchboxPreference)this.findPreference("airpartition");
      this.mHeadlightdelay = (SwitchboxPreference)this.findPreference("headlightdelay");
      this.mParkunlock = (SwitchboxPreference)this.findPreference("parkunlock");
      this.mRemotelock5 = (SwitchboxPreference)this.findPreference("remotelock5");
      this.mRemoteunlocksettings = (SwitchboxPreference)this.findPreference("remoteunlocksettings");
      this.mCar_unlocked = (SwitchboxPreference)this.findPreference("car_unlocked");
      this.mAutomatic_latch = (SwitchboxPreference)this.findPreference("automatic_latch");
      this.mAuto_avoidance = (SwitchboxPreference)this.findPreference("auto_avoidance");
      this.mRamp = (SwitchboxPreference)this.findPreference("ramp");
      this.mBackdefog = (HCheckBoxPreference)this.findPreference("backwindowdefog");
      this.mFrontdefog = (HCheckBoxPreference)this.findPreference("frontwindowdefog");
      this.mFindlight = (HCheckBoxPreference)this.findPreference("findlight");
      this.mDooropenlock = (HCheckBoxPreference)this.findPreference("dooropenlock");
      this.mStartlatch = (HCheckBoxPreference)this.findPreference("startlatch");
      this.mDelaylock = (HCheckBoxPreference)this.findPreference("delaylock");
      this.mRemoteunlock5 = (HCheckBoxPreference)this.findPreference("remoteunlock5");
      this.mOperationflag = (HCheckBoxPreference)this.findPreference("operationflag");
      this.radarswitch = (HCheckBoxPreference)this.findPreference("radarswitch");
      this.mWipers = (HCheckBoxPreference)this.findPreference("wipers");
      this.mRemote_lock = (HCheckBoxPreference)this.findPreference("remote_lock");
      this.mRemote_start = (HCheckBoxPreference)this.findPreference("remote_start");
      this.mUnkey = (HCheckBoxPreference)this.findPreference("unkey");
      this.mBy_drive = (HCheckBoxPreference)this.findPreference("by_drive");
      this.mAuto_relock = (HCheckBoxPreference)this.findPreference("auto_relock");
      this.mBlind = (HCheckBoxPreference)this.findPreference("blind");
      this.mCar_status = (HCheckBoxPreference)this.findPreference("car_status");
      this.mAutomatic_wiper = (HCheckBoxPreference)this.findPreference("automatic_wiper");
      this.mRemote_control = (HCheckBoxPreference)this.findPreference("remote_control");
      this.mAlarm_vol = (OnSwitchPreference)this.findPreference("alarm_vol");
      HCheckBoxPreference var2 = this.mFrontdefog;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mBackdefog;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mFindlight;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mDooropenlock;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mStartlatch;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mDelaylock;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mRemoteunlock5;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mOperationflag;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.radarswitch;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mWipers;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mRemote_lock;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mRemote_start;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mUnkey;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mBy_drive;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mAuto_relock;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mBlind;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mCar_status;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mAutomatic_wiper;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      var2 = this.mRemote_control;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      SwitchboxPreference var3 = this.mAutomodewindlevel;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mAirqualitysensor;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mAirpartition;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mHeadlightdelay;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mParkunlock;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mRemotelock5;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mRemoteunlocksettings;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mCar_unlocked;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mAutomatic_latch;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mAuto_avoidance;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      var3 = this.mRamp;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      OnSwitchPreference var4 = this.mAlarm_vol;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      this.factory_settings = (PreferenceScreen)this.findPreference("factory_settings");
      this.SetCmdkey((byte)-127, (byte)1);
      this.SetCmdkey((byte)-112, (byte)10);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mAlarm_vol;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this.data_mAlarm_vol;
         if (var4 + var3 < 0 || var4 + var3 > 15 || !this.mAlarm_vol.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)-120, (byte)(this.data_mAlarm_vol + var3 & 15));
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
         } else if (var1 == this.mAuto_avoidance) {
            this.SetCmdkey((byte)-125, (byte)24, (byte)var4);
         } else if (var1 == this.mRamp) {
            this.SetCmdkey((byte)-125, (byte)81, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mBackdefog;
      if (var1 == var2) {
         if (var2.isChecked()) {
            this.SetCmdkey((byte)-126, (byte)3, (byte)1);
         } else {
            this.SetCmdkey((byte)-126, (byte)3, (byte)0);
         }
      } else {
         var2 = this.mFrontdefog;
         if (var1 == var2) {
            if (var2.isChecked()) {
               this.SetCmdkey((byte)-126, (byte)4, (byte)1);
            } else {
               this.SetCmdkey((byte)-126, (byte)4, (byte)0);
            }
         } else {
            var2 = this.mFindlight;
            if (var1 == var2) {
               if (var2.isChecked()) {
                  this.SetCmdkey((byte)-125, (byte)0, (byte)1);
               } else {
                  this.SetCmdkey((byte)-125, (byte)0, (byte)0);
               }
            } else {
               var2 = this.mDooropenlock;
               if (var1 == var2) {
                  if (var2.isChecked()) {
                     this.SetCmdkey((byte)-125, (byte)2, (byte)1);
                  } else {
                     this.SetCmdkey((byte)-125, (byte)2, (byte)0);
                  }
               } else {
                  var2 = this.mStartlatch;
                  if (var1 == var2) {
                     if (var2.isChecked()) {
                        this.SetCmdkey((byte)-125, (byte)3, (byte)1);
                     } else {
                        this.SetCmdkey((byte)-125, (byte)3, (byte)0);
                     }
                  } else {
                     var2 = this.mDelaylock;
                     if (var1 == var2) {
                        if (var2.isChecked()) {
                           this.SetCmdkey((byte)-125, (byte)5, (byte)1);
                        } else {
                           this.SetCmdkey((byte)-125, (byte)5, (byte)0);
                        }
                     } else {
                        var2 = this.mRemoteunlock5;
                        if (var1 == var2) {
                           if (var2.isChecked()) {
                              this.SetCmdkey((byte)-125, (byte)6, (byte)1);
                           } else {
                              this.SetCmdkey((byte)-125, (byte)6, (byte)0);
                           }
                        } else {
                           var2 = this.mOperationflag;
                           if (var1 == var2) {
                              var2.isChecked();
                           } else {
                              var2 = this.radarswitch;
                              if (var1 == var2) {
                                 if (var2.isChecked()) {
                                    this.SetCmdkey((byte)-124, (byte)1);
                                 } else {
                                    this.SetCmdkey((byte)-124, (byte)0);
                                 }
                              } else {
                                 var2 = this.mWipers;
                                 if (var1 == var2) {
                                    if (var2.isChecked()) {
                                       this.SetCmdkey((byte)-125, (byte)9, (byte)1);
                                    } else {
                                       this.SetCmdkey((byte)-125, (byte)9, (byte)0);
                                    }
                                 } else {
                                    var2 = this.mRemote_lock;
                                    if (var1 == var2) {
                                       if (var2.isChecked()) {
                                          this.SetCmdkey((byte)-125, (byte)10, (byte)1);
                                       } else {
                                          this.SetCmdkey((byte)-125, (byte)10, (byte)0);
                                       }
                                    } else {
                                       var2 = this.mRemote_start;
                                       if (var1 == var2) {
                                          if (var2.isChecked()) {
                                             this.SetCmdkey((byte)-125, (byte)11, (byte)1);
                                          } else {
                                             this.SetCmdkey((byte)-125, (byte)11, (byte)0);
                                          }
                                       } else {
                                          var2 = this.mUnkey;
                                          if (var1 == var2) {
                                             if (var2.isChecked()) {
                                                this.SetCmdkey((byte)-125, (byte)13, (byte)1);
                                             } else {
                                                this.SetCmdkey((byte)-125, (byte)13, (byte)0);
                                             }
                                          } else {
                                             var2 = this.mBy_drive;
                                             if (var1 == var2) {
                                                if (var2.isChecked()) {
                                                   this.SetCmdkey((byte)-125, (byte)14, (byte)1);
                                                } else {
                                                   this.SetCmdkey((byte)-125, (byte)14, (byte)0);
                                                }
                                             } else {
                                                var2 = this.mAuto_relock;
                                                if (var1 == var2) {
                                                   if (var2.isChecked()) {
                                                      this.SetCmdkey((byte)-125, (byte)15, (byte)1);
                                                   } else {
                                                      this.SetCmdkey((byte)-125, (byte)15, (byte)0);
                                                   }
                                                } else {
                                                   var2 = this.mBlind;
                                                   if (var1 == var2) {
                                                      if (var2.isChecked()) {
                                                         this.SetCmdkey((byte)-125, (byte)22, (byte)1);
                                                      } else {
                                                         this.SetCmdkey((byte)-125, (byte)22, (byte)0);
                                                      }
                                                   } else {
                                                      var2 = this.mCar_status;
                                                      if (var1 == var2) {
                                                         if (var2.isChecked()) {
                                                            this.SetCmdkey((byte)-125, (byte)25, (byte)1);
                                                         } else {
                                                            this.SetCmdkey((byte)-125, (byte)25, (byte)0);
                                                         }
                                                      } else {
                                                         var2 = this.mAutomatic_wiper;
                                                         if (var1 == var2) {
                                                            if (var2.isChecked()) {
                                                               this.SetCmdkey((byte)-125, (byte)26, (byte)1);
                                                            } else {
                                                               this.SetCmdkey((byte)-125, (byte)26, (byte)0);
                                                            }
                                                         } else {
                                                            var2 = this.mRemote_control;
                                                            if (var1 == var2) {
                                                               if (var2.isChecked()) {
                                                                  this.SetCmdkey((byte)-125, (byte)27, (byte)1);
                                                               } else {
                                                                  this.SetCmdkey((byte)-125, (byte)27, (byte)0);
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
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297023)).setMessage(this.getString(2131296526)).setPositiveButton(this.getString(2131296524), new DialogInterface.OnClickListener(this) {
            final canbus5settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkey((byte)-125, (byte)-128, (byte)1);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296463), new DialogInterface.OnClickListener(this) {
            final canbus5settings this$0;

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
