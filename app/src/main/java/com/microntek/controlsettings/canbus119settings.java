package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class canbus119settings extends BasePreferenceActivity {
   private int canbustype = 0;
   private boolean isKLD8 = false;
   private HCheckBoxPreference mAirautokey;
   private HCheckBoxPreference mAirswitchautokey;
   private SwitchboxPreference mAutolight;
   private SwitchboxPreference mBack_camera_path;
   private HCheckBoxPreference mCardoorautomatic;
   private HCheckBoxPreference mCardoorspeed;
   private SwitchboxPreference mCell_back_door;
   private PreferenceCategory mClimate_settings;
   private SwitchboxPreference mCloseDoorTime;
   private SwitchboxPreference mConvenience;
   private HCheckBoxPreference mDaylight;
   private SwitchboxPreference mDoor_lock;
   private SwitchboxPreference mDoor_unlock;
   private SwitchboxPreference mLightingTime;
   private SwitchboxPreference mLightingTime_wai;
   private PreferenceCategory mLights_setting;
   private HCheckBoxPreference mLinkagedoorlock;
   private HCheckBoxPreference mLockakey;
   private SwitchboxPreference mLockvol;
   private HCheckBoxPreference mOpendoorflash;
   private HCheckBoxPreference mPlinkage;
   private HCheckBoxPreference mRadardisplay;
   private SwitchboxPreference mRadarrange;
   private SwitchboxPreference mRadarvol;
   private PreferenceCategory mRemote_cmd;
   private HCheckBoxPreference mRemoteunlock;
   private SwitchboxPreference mSetings_unit;
   private SwitchboxPreference mSetting_color;
   private SwitchboxPreference mSmartDoor;
   private SwitchboxPreference mTimeSwitchLight;
   private HCheckBoxPreference mTwokeyunlock;
   private HCheckBoxPreference mWireless_lock;
   private byte[] setData = new byte[31];

   private void RequestCmdkey(byte var1) {
      this.SendCanBusCmdData2E((byte)-112, new byte[]{var1}, 1);
   }

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1, var2}, 2);
   }

   private void updasettings() {
      byte[] var4 = this.setData;
      boolean var3 = false;
      if (var4[0] < 0) {
         this.mDaylight.setChecked(true);
      } else {
         this.mDaylight.setChecked(false);
      }

      int var2 = (this.setData[0] & 112) >> 4 & 15;
      int var1 = var2;
      if (var2 > 4) {
         var1 = 4;
      }

      SwitchboxPreference var5 = this.mAutolight;
      StringBuilder var6 = new StringBuilder();
      var6.append(var1);
      var6.append("");
      var5.setValue(var6.toString());
      var2 = (this.setData[0] & 12) >> 2 & 3;
      var1 = var2;
      if (var2 > 4) {
         var1 = 4;
      }

      var5 = this.mLightingTime;
      var6 = new StringBuilder();
      var6.append(var1);
      var6.append("");
      var5.setValue(var6.toString());
      var2 = this.setData[0] & 3;
      var1 = var2;
      if (var2 > 4) {
         var1 = 4;
      }

      var5 = this.mTimeSwitchLight;
      var6 = new StringBuilder();
      var6.append(var1);
      var6.append("");
      var5.setValue(var6.toString());
      if (this.setData[1] < 0) {
         this.mCardoorspeed.setChecked(true);
      } else {
         this.mCardoorspeed.setChecked(false);
      }

      if ((byte)(this.setData[1] & 64) == 64) {
         this.mCardoorautomatic.setChecked(true);
      } else {
         this.mCardoorautomatic.setChecked(false);
      }

      if ((byte)(this.setData[1] & 32) == 32) {
         this.mPlinkage.setChecked(true);
      } else {
         this.mPlinkage.setChecked(false);
      }

      if ((byte)(this.setData[1] & 16) == 16) {
         this.mRemoteunlock.setChecked(true);
      } else {
         this.mRemoteunlock.setChecked(false);
      }

      var2 = this.setData[1] & 7;
      var1 = var2;
      if (var2 > 6) {
         var1 = 6;
      }

      var5 = this.mLockvol;
      var6 = new StringBuilder();
      var6.append(var1);
      var6.append("");
      var5.setValue(var6.toString());
      if (this.setData[2] < 0) {
         this.mTwokeyunlock.setChecked(true);
      } else {
         this.mTwokeyunlock.setChecked(false);
      }

      if ((byte)(this.setData[2] & 64) == 64) {
         this.mLinkagedoorlock.setChecked(true);
      } else {
         this.mLinkagedoorlock.setChecked(false);
      }

      if ((byte)(this.setData[2] & 32) == 32) {
         this.mSmartDoor.setValue("1");
      } else {
         this.mSmartDoor.setValue("0");
      }

      if ((byte)(this.setData[2] & 16) == 16) {
         this.mLockakey.setChecked(true);
      } else {
         this.mLockakey.setChecked(false);
      }

      if ((byte)(this.setData[2] & 8) == 8) {
         this.mOpendoorflash.setChecked(true);
      } else {
         this.mOpendoorflash.setChecked(false);
      }

      if (this.setData[3] < 0) {
         this.mAirautokey.setChecked(true);
      } else {
         this.mAirautokey.setChecked(false);
      }

      if ((byte)(this.setData[3] & 64) == 64) {
         this.mAirswitchautokey.setChecked(true);
      } else {
         this.mAirswitchautokey.setChecked(false);
      }

      var2 = this.setData[3] & 3;
      var1 = var2;
      if (var2 > 3) {
         var1 = 3;
      }

      var5 = this.mCloseDoorTime;
      var6 = new StringBuilder();
      var6.append(var1);
      var6.append("");
      var5.setValue(var6.toString());
      byte var7 = this.setData[3];
      SwitchboxPreference var8 = this.mSetting_color;
      StringBuilder var9 = new StringBuilder();
      var9.append(var7 >> 4 & 3);
      var9.append("");
      var8.setValue(var9.toString());
      var7 = this.setData[1];
      var8 = this.mSetings_unit;
      var9 = new StringBuilder();
      var9.append(var7 >> 3 & 1);
      var9.append("");
      var8.setValue(var9.toString());
      var7 = this.setData[3];
      var5 = this.mBack_camera_path;
      var6 = new StringBuilder();
      var6.append(var7 >> 2 & 3);
      var6.append("");
      var5.setValue(var6.toString());
      var7 = this.setData[2];
      var8 = this.mCell_back_door;
      var9 = new StringBuilder();
      var9.append(var7 & 7);
      var9.append("");
      var8.setValue(var9.toString());
      var7 = this.setData[4];
      var8 = this.mDoor_lock;
      var9 = new StringBuilder();
      var9.append(var7 & 3);
      var9.append("");
      var8.setValue(var9.toString());
      var7 = this.setData[4];
      var5 = this.mDoor_unlock;
      var6 = new StringBuilder();
      var6.append(var7 >> 2 & 3);
      var6.append("");
      var5.setValue(var6.toString());
      var7 = this.setData[4];
      var5 = this.mConvenience;
      var6 = new StringBuilder();
      var6.append(var7 >> 5 & 3);
      var6.append("");
      var5.setValue(var6.toString());
      HCheckBoxPreference var10 = this.mWireless_lock;
      if ((this.setData[4] & 16) != 0) {
         var3 = true;
      }

      var10.setChecked(var3);
   }

   private void updasettings50() {
      int var2 = this.setData[0] & 3;
      int var1 = var2;
      if (var2 > 4) {
         var1 = 4;
      }

      SwitchboxPreference var4 = this.mLightingTime_wai;
      StringBuilder var3 = new StringBuilder();
      var3.append(var1);
      var3.append("");
      var4.setValue(var3.toString());
   }

   protected void ProcessData(byte[] var1) {
      boolean var5 = false;
      int var2 = 0;
      byte var4 = var1[0];
      byte var3 = var1[1];
      if (var3 != 0) {
         if (var4 != 38) {
            if (var4 == 80) {
               this.setData[0] = var1[1];
               this.updasettings50();
            } else if (var4 == 30) {
               byte var9 = var1[6];
               SwitchboxPreference var6 = this.mRadarvol;
               StringBuilder var7 = new StringBuilder();
               var7.append(var9 & 7);
               var7.append("");
               var6.setValue(var7.toString());
               HCheckBoxPreference var10 = this.mRadardisplay;
               if ((var1[6] & 128) != 0) {
                  var5 = true;
               }

               var10.setChecked(var5);
               var9 = var1[6];
               var6 = this.mRadarrange;
               StringBuilder var8 = new StringBuilder();
               var8.append(var9 >> 6 & 1);
               var8.append("");
               var6.setValue(var8.toString());
            }
         } else {
            while(var2 < var3) {
               this.setData[var2] = var1[var2 + 2];
               ++var2;
            }

            this.updasettings();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492870);
      this.mLockvol = this.findSwitchboxPreference("doorlockvol");
      this.mSmartDoor = this.findSwitchboxPreference("smartdoorlock");
      this.mAutolight = this.findSwitchboxPreference("autolight");
      this.mTimeSwitchLight = this.findSwitchboxPreference("timeswitchlight");
      this.mLightingTime = this.findSwitchboxPreference("lightingtime");
      this.mLightingTime_wai = this.findSwitchboxPreference("lightingtime_wai");
      this.mCloseDoorTime = this.findSwitchboxPreference("closedoortime");
      this.mRadarvol = this.findSwitchboxPreference("radarvol");
      this.mRadarrange = this.findSwitchboxPreference("radarrange");
      this.mSetting_color = this.findSwitchboxPreference("setting_color");
      this.mSetings_unit = this.findSwitchboxPreference("setings_unit");
      this.mBack_camera_path = this.findSwitchboxPreference("back_camera_path");
      this.mCell_back_door = this.findSwitchboxPreference("cell_back_door");
      this.mDoor_lock = this.findSwitchboxPreference("door_lock");
      this.mDoor_unlock = this.findSwitchboxPreference("door_unlock");
      this.mConvenience = this.findSwitchboxPreference("convenience");
      this.mRadardisplay = this.findHCheckBoxPreference("radardisplay");
      this.mWireless_lock = this.findHCheckBoxPreference("wireless_lock");
      this.mCardoorspeed = this.findHCheckBoxPreference("cardoorspeed");
      this.mCardoorautomatic = this.findHCheckBoxPreference("cardoorautomatic");
      this.mPlinkage = this.findHCheckBoxPreference("plinkage");
      this.mRemoteunlock = this.findHCheckBoxPreference("remoteunlock");
      this.mLockakey = this.findHCheckBoxPreference("lockakey");
      this.mTwokeyunlock = this.findHCheckBoxPreference("twokeyunlock");
      this.mLinkagedoorlock = this.findHCheckBoxPreference("linkagedoorlock");
      this.mOpendoorflash = this.findHCheckBoxPreference("opendoorflash");
      this.mAirautokey = this.findHCheckBoxPreference("airautokey");
      this.mAirswitchautokey = this.findHCheckBoxPreference("airswitchautokey");
      this.mDaylight = this.findHCheckBoxPreference("daylight");
      this.mClimate_settings = (PreferenceCategory)this.findPreference("climate_settings");
      this.mLights_setting = (PreferenceCategory)this.findPreference("lights_setting");
      this.mRemote_cmd = (PreferenceCategory)this.findPreference("remote_cmd");
      this.RequestCmdkey((byte)38);
      this.RequestCmdkey((byte)30);
      this.removePreference((Preference)this.mLightingTime_wai);
      this.removePreference((Preference)this.mSetting_color);
      this.removePreference((Preference)this.mSetings_unit);
      this.removePreference((Preference)this.mDoor_lock);
      this.removePreference((Preference)this.mDoor_unlock);
      this.removePreference((Preference)this.mConvenience);
      this.removePreference((Preference)this.mWireless_lock);
      this.removePreference((Preference)this.mCloseDoorTime);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var5) {
         return false;
      }

      int var3;
      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mLockvol) {
            this.SetCmdkey((byte)5, (byte)var4);
         } else if (var1 == this.mSmartDoor) {
            this.SetCmdkey((byte)15, (byte)var4);
         } else if (var1 == this.mAutolight) {
            this.SetCmdkey((byte)6, (byte)var4);
         } else if (var1 == this.mTimeSwitchLight) {
            this.SetCmdkey((byte)7, (byte)var4);
         } else if (var1 == this.mLightingTime) {
            this.SetCmdkey((byte)12, (byte)var4);
         } else if (var1 == this.mCloseDoorTime) {
            this.SetCmdkey((byte)20, (byte)var4);
         } else if (var1 == this.mLightingTime_wai) {
            this.SetCmdkey((byte)12, (byte)(var4 + 4));
         } else if (var1 == this.mRadarvol) {
            this.SetCmdkey((byte)21, (byte)(var4 + 0));
         } else if (var1 == this.mRadarrange) {
            this.SetCmdkey((byte)23, (byte)(var4 + 0));
         } else if (var1 == this.mSetting_color) {
            this.SetCmdkey((byte)24, (byte)(var4 + 0));
         } else if (var1 == this.mSetings_unit) {
            this.SetCmdkey((byte)25, (byte)(var4 + 0));
         } else if (var1 == this.mBack_camera_path) {
            this.SetCmdkey((byte)34, (byte)(var4 + 0));
         } else if (var1 == this.mCell_back_door) {
            this.SetCmdkey((byte)35, (byte)(var4 + 0));
         } else if (var1 == this.mDoor_lock) {
            this.SetCmdkey((byte)38, (byte)(var4 + 0));
         } else if (var1 == this.mDoor_unlock) {
            this.SetCmdkey((byte)39, (byte)(var4 + 0));
         } else if (var1 == this.mConvenience) {
            this.SetCmdkey((byte)40, (byte)(var4 + 0));
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      HCheckBoxPreference var3 = this.mCardoorspeed;
      if (var2 == var3) {
         if (var3.mChecked) {
            this.SetCmdkey((byte)0, (byte)0);
         } else {
            this.SetCmdkey((byte)0, (byte)1);
         }
      } else {
         var3 = this.mCardoorautomatic;
         if (var2 == var3) {
            if (var3.mChecked) {
               this.SetCmdkey((byte)1, (byte)0);
            } else {
               this.SetCmdkey((byte)1, (byte)1);
            }
         } else {
            var3 = this.mPlinkage;
            if (var2 == var3) {
               if (var3.mChecked) {
                  this.SetCmdkey((byte)2, (byte)0);
               } else {
                  this.SetCmdkey((byte)2, (byte)1);
               }
            } else {
               var3 = this.mRemoteunlock;
               if (var2 == var3) {
                  if (var3.mChecked) {
                     this.SetCmdkey((byte)3, (byte)0);
                  } else {
                     this.SetCmdkey((byte)3, (byte)1);
                  }
               } else {
                  var3 = this.mLockakey;
                  if (var2 == var3) {
                     if (var3.mChecked) {
                        this.SetCmdkey((byte)16, (byte)0);
                     } else {
                        this.SetCmdkey((byte)16, (byte)1);
                     }
                  } else {
                     var3 = this.mTwokeyunlock;
                     if (var2 == var3) {
                        if (var3.mChecked) {
                           this.SetCmdkey((byte)13, (byte)0);
                        } else {
                           this.SetCmdkey((byte)13, (byte)1);
                        }
                     } else {
                        var3 = this.mLinkagedoorlock;
                        if (var2 == var3) {
                           if (var3.mChecked) {
                              this.SetCmdkey((byte)14, (byte)0);
                           } else {
                              this.SetCmdkey((byte)14, (byte)1);
                           }
                        } else {
                           var3 = this.mOpendoorflash;
                           if (var2 == var3) {
                              if (var3.mChecked) {
                                 this.SetCmdkey((byte)17, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)17, (byte)1);
                              }
                           } else {
                              var3 = this.mAirautokey;
                              if (var2 == var3) {
                                 if (var3.mChecked) {
                                    this.SetCmdkey((byte)18, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)18, (byte)1);
                                 }
                              } else {
                                 var3 = this.mAirswitchautokey;
                                 if (var2 == var3) {
                                    if (var3.mChecked) {
                                       this.SetCmdkey((byte)19, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)19, (byte)1);
                                    }
                                 } else {
                                    var3 = this.mDaylight;
                                    if (var2 == var3) {
                                       if (var3.mChecked) {
                                          this.SetCmdkey((byte)4, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)4, (byte)1);
                                       }
                                    } else {
                                       var3 = this.mRadardisplay;
                                       if (var2 == var3) {
                                          if (var3.mChecked) {
                                             this.SetCmdkey((byte)22, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)22, (byte)1);
                                          }
                                       } else {
                                          var3 = this.mWireless_lock;
                                          if (var2 == var3) {
                                             if (var3.mChecked) {
                                                this.SetCmdkey((byte)37, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)37, (byte)1);
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

      return super.onPreferenceTreeClick(var1, var2);
   }
}
