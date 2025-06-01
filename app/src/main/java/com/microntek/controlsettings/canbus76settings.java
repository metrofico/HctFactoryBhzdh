package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;
import java.io.PrintStream;

public class canbus76settings extends BasePreferenceActivity {
   private int _Rdjus = 0;
   private int _Screen3 = 0;
   private int _Screen4 = 0;
   private int _Screen5 = 0;
   private HCheckBoxPreference mAcc_car;
   private HCheckBoxPreference mAccess_beep;
   private OnSwitchPreference mAdjust_outside;
   private SwitchboxPreference mAuto_light;
   private HCheckBoxPreference mBeep_vol;
   private SwitchboxPreference mDanger_ahead;
   private PreferenceScreen mDefault_all;
   private SwitchboxPreference mDeviate;
   private SwitchboxPreference mDoor_unlock_mode;
   private HCheckBoxPreference mF_backlight;
   private SwitchboxPreference mHeadlight;
   private SwitchboxPreference mInterior;
   private SwitchboxPreference mKey_mode;
   private HCheckBoxPreference mKeyles_flash;
   private HCheckBoxPreference mKeyless;
   private SwitchboxPreference mLock_with;
   private HCheckBoxPreference mRemote_startoff;
   private PreferenceScreen mReset_info;
   private SwitchboxPreference mScreen1;
   private SwitchboxPreference mScreen2;
   private OnSwitchPreference mScreen3;
   private OnSwitchPreference mScreen4;
   private OnSwitchPreference mScreen5;
   private SwitchboxPreference mSecurity;
   private HCheckBoxPreference mStart_stop_dis;
   private HCheckBoxPreference mStop_lkas;
   private PreferenceScreen mTpms_check;
   private SwitchboxPreference mTrip_a;
   private SwitchboxPreference mTrip_b;
   private SwitchboxPreference mUnlock_with;
   private SwitchboxPreference mVoice_alarm;
   private byte[] setData = new byte[20];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   private int getSettingData(String var1) {
      return System.getInt(this.getContentResolver(), var1, 0);
   }

   private void setSettingData(String var1, int var2) {
      System.putInt(this.getContentResolver(), var1, var2);
   }

   public void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      int var3 = var1.length;
      if (var3 > 2) {
         int var2;
         if (var4 == 50) {
            for(var2 = 2; var2 < var3; ++var2) {
               this.setData[var2 - 2] = var1[var2];
            }

            byte[] var6 = this.setData;
            if ((var6[0] & 15) > 6) {
               var2 = 0;
            } else {
               var2 = var6[0] & 15;
            }

            this._Rdjus = var2;
            OnSwitchPreference var11 = this.mAdjust_outside;
            StringBuilder var7 = new StringBuilder();
            var7.append("");
            var7.append(this._Rdjus - 3);
            var11.setValue("0", var7.toString());
            HCheckBoxPreference var12 = this.mF_backlight;
            boolean var5;
            if ((this.setData[1] & 128) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            this.mTrip_a.setValue(this.setData[0] >> 4 & 3);
            this.mTrip_b.setValue(this.setData[0] >> 6 & 3);
            this.mInterior.setValue(this.setData[1] & 3);
            this.mHeadlight.setValue(this.setData[1] >> 2 & 3);
            this.mAuto_light.setValue(this.setData[1] >> 4 & 7);
            this.mLock_with.setValue(this.setData[2] & 3);
            this.mUnlock_with.setValue(this.setData[2] >> 2 & 3);
            this.mKey_mode.setValue(this.setData[2] >> 6 & 1);
            this.mSecurity.setValue(this.setData[2] >> 4 & 3);
            var12 = this.mKeyless;
            if ((this.setData[2] & 128) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            var12 = this.mBeep_vol;
            if ((this.setData[3] & 128) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            var12 = this.mAccess_beep;
            if ((this.setData[3] & 64) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            this.mDoor_unlock_mode.setValue(this.setData[3] >> 5 & 1);
            var12 = this.mRemote_startoff;
            if ((this.setData[3] & 8) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            var12 = this.mKeyles_flash;
            if ((this.setData[3] & 16) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            var12 = this.mStart_stop_dis;
            if ((this.setData[5] & 64) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            this.mVoice_alarm.setValue(this.setData[5] >> 7 & 1);
            this.mDanger_ahead.setValue(this.setData[5] >> 2 & 3);
            var12 = this.mAcc_car;
            if ((this.setData[5] & 32) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            var12 = this.mStop_lkas;
            if ((this.setData[5] & 16) != 0) {
               var5 = true;
            } else {
               var5 = false;
            }

            var12.setChecked(var5);
            this.mDeviate.setValue(this.setData[5] & 3);
         }

         if (var4 == -45) {
            for(var2 = 2; var2 < var3; ++var2) {
               this.setData[var2 - 2] = var1[var2];
            }

            var1 = this.setData;
            if ((var1[1] & 15) > 10) {
               var2 = 0;
            } else {
               var2 = var1[1] & 15;
            }

            this._Screen3 = var2;
            OnSwitchPreference var8 = this.mScreen3;
            StringBuilder var13 = new StringBuilder();
            var13.append("");
            var13.append(this._Screen3 - 5);
            var8.setValue("0", var13.toString());
            PrintStream var9 = java.lang.System.out;
            var13 = new StringBuilder();
            var13.append("_Screen3>>");
            var13.append(this._Screen3);
            var9.println(var13.toString());
            var1 = this.setData;
            if ((var1[2] & 15) > 10) {
               var2 = 0;
            } else {
               var2 = var1[2] & 15;
            }

            this._Screen4 = var2;
            var8 = this.mScreen4;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this._Screen4 - 5);
            var8.setValue("0", var13.toString());
            var1 = this.setData;
            if ((var1[3] & 15) > 10) {
               var2 = 0;
            } else {
               var2 = var1[3] & 15;
            }

            this._Screen5 = var2;
            var8 = this.mScreen5;
            var13 = new StringBuilder();
            var13.append("");
            var13.append(this._Screen5 - 5);
            var8.setValue("0", var13.toString());
            this.setSettingData("mScreen2", this.setData[0] & 3);
            SwitchboxPreference var14 = this.mScreen2;
            StringBuilder var10 = new StringBuilder();
            var10.append("");
            var10.append(this.setData[0] & 3);
            var14.setValue(var10.toString());
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492940);
      this.mF_backlight = this.findHCheckBoxPreference("f_backlight");
      this.mKeyless = this.findHCheckBoxPreference("keyless");
      this.mBeep_vol = this.findHCheckBoxPreference("beep_vol");
      this.mAccess_beep = this.findHCheckBoxPreference("access_beep");
      this.mKeyles_flash = this.findHCheckBoxPreference("keyles_flash");
      this.mRemote_startoff = this.findHCheckBoxPreference("remote_startoff");
      this.mStart_stop_dis = this.findHCheckBoxPreference("start_stop_dis");
      this.mAcc_car = this.findHCheckBoxPreference("acc_car");
      this.mStop_lkas = this.findHCheckBoxPreference("stop_lkas");
      this.mTrip_a = this.findSwitchboxPreference("trip_a");
      this.mTrip_b = this.findSwitchboxPreference("trip_b");
      this.mInterior = this.findSwitchboxPreference("interior");
      this.mHeadlight = this.findSwitchboxPreference("headlight");
      this.mAuto_light = this.findSwitchboxPreference("auto_light");
      this.mLock_with = this.findSwitchboxPreference("lock_with");
      this.mUnlock_with = this.findSwitchboxPreference("unlock_with");
      this.mKey_mode = this.findSwitchboxPreference("key_mode");
      this.mSecurity = this.findSwitchboxPreference("security");
      this.mScreen1 = this.findSwitchboxPreference("screen1");
      SwitchboxPreference var2 = this.mScreen1;
      StringBuilder var3 = new StringBuilder();
      var3.append("");
      var3.append(this.getSettingData("mScreen1"));
      var2.setValue(var3.toString());
      this.mScreen2 = this.findSwitchboxPreference("screen2");
      var2 = this.mScreen2;
      var3 = new StringBuilder();
      var3.append("");
      var3.append(this.getSettingData("mScreen2"));
      var2.setValue(var3.toString());
      this.mDoor_unlock_mode = this.findSwitchboxPreference("door_unlock_mode");
      this.mVoice_alarm = this.findSwitchboxPreference("voice_alarm");
      this.mDanger_ahead = this.findSwitchboxPreference("danger_ahead");
      this.mDeviate = this.findSwitchboxPreference("deviate");
      this.mAdjust_outside = this.findOnSwitchPreference("adjust_outside");
      this.mScreen3 = this.findOnSwitchPreference("screen3");
      this.mScreen4 = this.findOnSwitchPreference("screen4");
      this.mScreen5 = this.findOnSwitchPreference("screen5");
      this.mTpms_check = this.findPreferenceScreen("tpms_check");
      this.mReset_info = this.findPreferenceScreen("reset_info");
      this.mDefault_all = this.findPreferenceScreen("default_all");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{50, 0}, 2);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{-45, 0}, 2);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mAdjust_outside;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this._Rdjus;
         if (var4 + var3 < 0 || var4 + var3 > 6 || !this.mAdjust_outside.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)0, (byte)(this._Rdjus + var3 & 255));
      } else {
         var5 = this.mScreen3;
         if (var1 == var5) {
            var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var4 = this._Screen3;
            if (var4 + var3 < 0 || var4 + var3 > 10 || !this.mScreen3.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)67, (byte)(this._Screen3 + var3 & 255));
         } else {
            var5 = this.mScreen4;
            if (var1 == var5) {
               var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
               var4 = this._Screen4;
               if (var4 + var3 < 0 || var4 + var3 > 10 || !this.mScreen4.getIsDow()) {
                  return true;
               }

               this.SetCmdkey((byte)68, (byte)(this._Screen4 + var3 & 255));
            } else {
               var5 = this.mScreen5;
               if (var1 == var5) {
                  var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
                  var4 = this._Screen5;
                  if (var4 + var3 < 0 || var4 + var3 > 10 || !this.mScreen5.getIsDow()) {
                     return true;
                  }

                  this.SetCmdkey((byte)69, (byte)(this._Screen5 + var3 & 255));
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
         if (var1 == this.mTrip_a) {
            this.SetCmdkey((byte)2, (byte)var4);
         } else if (var1 == this.mTrip_b) {
            this.SetCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mInterior) {
            this.SetCmdkey((byte)4, (byte)var4);
         } else if (var1 == this.mHeadlight) {
            this.SetCmdkey((byte)5, (byte)var4);
         } else if (var1 == this.mAuto_light) {
            this.SetCmdkey((byte)6, (byte)var4);
         } else if (var1 == this.mLock_with) {
            this.SetCmdkey((byte)7, (byte)var4);
         } else if (var1 == this.mUnlock_with) {
            this.SetCmdkey((byte)8, (byte)var4);
         } else if (var1 == this.mKey_mode) {
            this.SetCmdkey((byte)9, (byte)var4);
         } else if (var1 == this.mSecurity) {
            this.SetCmdkey((byte)11, (byte)var4);
         } else if (var1 == this.mScreen1) {
            this.SetCmdkey((byte)65, (byte)var4);
            this.setSettingData("mScreen1", var4);
            SwitchboxPreference var9 = this.mScreen1;
            StringBuilder var8 = new StringBuilder();
            var8.append("");
            var8.append(var4);
            var9.setValue(var8.toString());
         } else if (var1 == this.mScreen2) {
            this.SetCmdkey((byte)66, (byte)var4);
         } else if (var1 == this.mDoor_unlock_mode) {
            this.SetCmdkey((byte)18, (byte)var4);
         } else if (var1 == this.mVoice_alarm) {
            this.SetCmdkey((byte)30, (byte)var4);
         } else if (var1 == this.mDanger_ahead) {
            this.SetCmdkey((byte)31, (byte)var4);
         } else if (var1 == this.mDeviate) {
            this.SetCmdkey((byte)34, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mTpms_check) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus76settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)17, (byte)0);
            }
         }, this.mTpms_check.getTitle().toString());
      } else if (var1 == this.mReset_info) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus76settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)14, (byte)0);
            }
         }, this.mReset_info.getTitle().toString());
      } else if (var1 == this.mDefault_all) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus76settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)15, (byte)0);
            }
         }, this.mDefault_all.getTitle().toString());
      }

      HCheckBoxPreference var2 = this.mF_backlight;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)1, (byte)1);
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
            var2 = this.mBeep_vol;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)12, (byte)0);
               } else {
                  this.SetCmdkey((byte)12, (byte)1);
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
                  var2 = this.mRemote_startoff;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)24, (byte)0);
                     } else {
                        this.SetCmdkey((byte)24, (byte)1);
                     }
                  } else {
                     var2 = this.mKeyles_flash;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)19, (byte)0);
                        } else {
                           this.SetCmdkey((byte)19, (byte)1);
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
