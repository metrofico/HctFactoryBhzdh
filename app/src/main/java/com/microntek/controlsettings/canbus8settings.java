package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class canbus8settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private int canbustype = 0;
   private boolean isHZC = false;
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
   private PreferenceCategory mLight_cmd;
   private SwitchboxPreference mLightingTime;
   private SwitchboxPreference mLightingTime_wai;
   private PreferenceCategory mLights_setting;
   private HCheckBoxPreference mLinkagedoorlock;
   private HCheckBoxPreference mLockakey;
   private SwitchboxPreference mLockvol;
   private HCheckBoxPreference mOpendoorflash;
   private HCheckBoxPreference mPlinkage;
   private HCheckBoxPreference mRadar_sound;
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

   private void removePreference(Preference var1, boolean var2) {
      if (!var2) {
         this.getPreferenceScreen().removePreference(var1);
      } else {
         this.getPreferenceScreen().addPreference(var1);
      }

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

      SwitchboxPreference var7 = this.mLockvol;
      StringBuilder var8 = new StringBuilder();
      var8.append(var1);
      var8.append("");
      var7.setValue(var8.toString());
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
      byte var9 = this.setData[3];
      var7 = this.mSetting_color;
      var8 = new StringBuilder();
      var8.append(var9 >> 4 & 3);
      var8.append("");
      var7.setValue(var8.toString());
      var9 = this.setData[5];
      var5 = this.mSetings_unit;
      var6 = new StringBuilder();
      var6.append(var9 >> 6 & 3);
      var6.append("");
      var5.setValue(var6.toString());
      var9 = this.setData[3];
      var5 = this.mBack_camera_path;
      var6 = new StringBuilder();
      var6.append(var9 >> 2 & 3);
      var6.append("");
      var5.setValue(var6.toString());
      var9 = this.setData[2];
      var5 = this.mCell_back_door;
      var6 = new StringBuilder();
      var6.append(var9 & 7);
      var6.append("");
      var5.setValue(var6.toString());
      var9 = this.setData[4];
      var5 = this.mDoor_lock;
      var6 = new StringBuilder();
      var6.append(var9 & 3);
      var6.append("");
      var5.setValue(var6.toString());
      var9 = this.setData[4];
      var5 = this.mDoor_unlock;
      var6 = new StringBuilder();
      var6.append(var9 >> 2 & 3);
      var6.append("");
      var5.setValue(var6.toString());
      var9 = this.setData[4];
      var7 = this.mConvenience;
      var8 = new StringBuilder();
      var8.append(var9 >> 5 & 3);
      var8.append("");
      var7.setValue(var8.toString());
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
      boolean var7 = false;
      boolean var6 = false;
      int var2 = 0;
      byte var4 = var1[0];
      byte var3 = var1[1];
      if (var3 != 0) {
         if (var4 != 38) {
            if (var4 == 80) {
               this.setData[0] = var1[2];
               this.updasettings50();
            } else {
               boolean var5;
               SwitchboxPreference var8;
               HCheckBoxPreference var11;
               if (var4 == 30) {
                  byte var10 = var1[6];
                  var8 = this.mRadarvol;
                  StringBuilder var9 = new StringBuilder();
                  var9.append(var10 & 7);
                  var9.append("");
                  var8.setValue(var9.toString());
                  var11 = this.mRadardisplay;
                  if ((var1[6] & 128) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  var11.setChecked(var5);
                  var10 = var1[6];
                  SwitchboxPreference var13 = this.mRadarrange;
                  StringBuilder var12 = new StringBuilder();
                  var12.append(var10 >> 6 & 1);
                  var12.append("");
                  var13.setValue(var12.toString());
                  var11 = this.mRadar_sound;
                  var5 = var7;
                  if ((var1[6] & 16) != 0) {
                     var5 = true;
                  }

                  var11.setChecked(var5);
               } else if (var4 == 26 && !this.isKLD8 && !this.isHZC) {
                  var11 = this.mCardoorspeed;
                  if ((var1[2] & 1) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mCardoorautomatic;
                  if ((var1[2] & 2) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mPlinkage;
                  if ((var1[2] & 4) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mLinkagedoorlock;
                  if ((var1[2] & 8) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mTwokeyunlock;
                  if ((var1[2] & 16) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mRemoteunlock;
                  if ((var1[2] & 32) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mOpendoorflash;
                  if ((var1[2] & 64) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var8 = this.mLightingTime;
                  if ((var1[2] & 128) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var8, var5);
                  var8 = this.mTimeSwitchLight;
                  if ((var1[3] & 1) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var8, var5);
                  var8 = this.mAutolight;
                  if ((2 & var1[3]) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var8, var5);
                  var8 = this.mSmartDoor;
                  if ((var1[3] & 4) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var8, var5);
                  var11 = this.mLockakey;
                  if ((var1[3] & 8) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mAirautokey;
                  if ((var1[3] & 16) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var11 = this.mAirswitchautokey;
                  if ((var1[3] & 32) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var11, var5);
                  var8 = this.mBack_camera_path;
                  if ((var1[3] & 64) != 0) {
                     var5 = true;
                  } else {
                     var5 = false;
                  }

                  this.removePreference(var8, var5);
                  var8 = this.mCell_back_door;
                  var5 = var6;
                  if ((var1[3] & 64) != 0) {
                     var5 = true;
                  }

                  this.removePreference(var8, var5);
               }
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
      this.addPreferencesFromResource(2131492943);
      this.mLockvol = (SwitchboxPreference)this.findPreference("doorlockvol");
      SwitchboxPreference var4 = this.mLockvol;
      String var5;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
         var5 = this.mLockvol.getSharedPreferences().getString("doorlockvol", "0");
         this.mLockvol.setValue(var5);
      }

      this.mSmartDoor = (SwitchboxPreference)this.findPreference("smartdoorlock");
      var4 = this.mSmartDoor;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
         var5 = this.mSmartDoor.getSharedPreferences().getString("smartdoorlock", "0");
         this.mSmartDoor.setValue(var5);
      }

      this.mAutolight = (SwitchboxPreference)this.findPreference("autolight");
      var4 = this.mAutolight;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
         var5 = this.mAutolight.getSharedPreferences().getString("autolight", "0");
         this.mAutolight.setValue(var5);
      }

      this.mTimeSwitchLight = (SwitchboxPreference)this.findPreference("timeswitchlight");
      var4 = this.mTimeSwitchLight;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
         var5 = this.mTimeSwitchLight.getSharedPreferences().getString("timeswitchlight", "0");
         this.mTimeSwitchLight.setValue(var5);
      }

      this.mLightingTime = (SwitchboxPreference)this.findPreference("lightingtime");
      var4 = this.mLightingTime;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
         var5 = this.mLightingTime.getSharedPreferences().getString("lightingtime", "0");
         this.mLightingTime.setValue(var5);
      }

      this.mLightingTime_wai = (SwitchboxPreference)this.findPreference("lightingtime_wai");
      var4 = this.mLightingTime_wai;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
         var5 = this.mLightingTime_wai.getSharedPreferences().getString("lightingtime_wai", "0");
         this.mLightingTime_wai.setValue(var5);
      }

      this.mCloseDoorTime = (SwitchboxPreference)this.findPreference("closedoortime");
      var4 = this.mCloseDoorTime;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
         var5 = this.mCloseDoorTime.getSharedPreferences().getString("closedoortime", "0");
         this.mCloseDoorTime.setValue(var5);
      }

      this.mRadarvol = (SwitchboxPreference)this.findPreference("radarvol");
      this.mRadarvol.setOnPreferenceChangeListener(this);
      this.mRadarrange = (SwitchboxPreference)this.findPreference("radarrange");
      this.mRadarrange.setOnPreferenceChangeListener(this);
      this.mSetting_color = (SwitchboxPreference)this.findPreference("setting_color");
      this.mSetting_color.setOnPreferenceChangeListener(this);
      this.mSetings_unit = (SwitchboxPreference)this.findPreference("setings_unit");
      this.mSetings_unit.setOnPreferenceChangeListener(this);
      this.mBack_camera_path = (SwitchboxPreference)this.findPreference("back_camera_path");
      this.mBack_camera_path.setOnPreferenceChangeListener(this);
      this.mCell_back_door = (SwitchboxPreference)this.findPreference("cell_back_door");
      this.mCell_back_door.setOnPreferenceChangeListener(this);
      this.mDoor_lock = (SwitchboxPreference)this.findPreference("door_lock");
      this.mDoor_lock.setOnPreferenceChangeListener(this);
      this.mDoor_unlock = (SwitchboxPreference)this.findPreference("door_unlock");
      this.mDoor_unlock.setOnPreferenceChangeListener(this);
      this.mConvenience = (SwitchboxPreference)this.findPreference("convenience");
      this.mConvenience.setOnPreferenceChangeListener(this);
      this.mRadardisplay = (HCheckBoxPreference)this.findPreference("radardisplay");
      this.mWireless_lock = (HCheckBoxPreference)this.findPreference("wireless_lock");
      this.mRadar_sound = (HCheckBoxPreference)this.findPreference("radar_sound");
      this.mCardoorspeed = (HCheckBoxPreference)this.findPreference("cardoorspeed");
      this.mCardoorautomatic = (HCheckBoxPreference)this.findPreference("cardoorautomatic");
      this.mPlinkage = (HCheckBoxPreference)this.findPreference("plinkage");
      this.mRemoteunlock = (HCheckBoxPreference)this.findPreference("remoteunlock");
      this.mLockakey = (HCheckBoxPreference)this.findPreference("lockakey");
      this.mTwokeyunlock = (HCheckBoxPreference)this.findPreference("twokeyunlock");
      this.mLinkagedoorlock = (HCheckBoxPreference)this.findPreference("linkagedoorlock");
      this.mOpendoorflash = (HCheckBoxPreference)this.findPreference("opendoorflash");
      this.mAirautokey = (HCheckBoxPreference)this.findPreference("airautokey");
      this.mAirswitchautokey = (HCheckBoxPreference)this.findPreference("airswitchautokey");
      this.mDaylight = (HCheckBoxPreference)this.findPreference("daylight");
      this.mClimate_settings = (PreferenceCategory)this.findPreference("climate_settings");
      this.mLights_setting = (PreferenceCategory)this.findPreference("lights_setting");
      this.mRemote_cmd = (PreferenceCategory)this.findPreference("remote_cmd");
      this.mLight_cmd = (PreferenceCategory)this.findPreference("light_cmd");
      var5 = RkSystemProp.get("ro.product.customer.sub", "HCT");
      this.isKLD8 = var5.equals("KLD8");
      boolean var3;
      if (var5.contains("HZC") && BaseApplication.getInstance().getCanbustypeCustomer() == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isHZC = var3;
      if (this.isKLD8) {
         int var2 = BaseApplication.getInstance().getCarType();
         if (var2 != 1) {
            if (var2 == 2) {
               this.getPreferenceScreen().removeAll();
               this.addPreference(this.mRemoteunlock);
               this.addPreference(this.mSmartDoor);
               this.addPreference(this.mLockakey);
               this.addPreference(this.mOpendoorflash);
               this.addPreference(this.mAirautokey);
               this.addPreference(this.mAirswitchautokey);
               this.addPreference(this.mAutolight);
               this.addPreference(this.mLightingTime);
               this.addPreference(this.mRadarvol);
               this.addPreference(this.mRadardisplay);
               this.addPreference(this.mTimeSwitchLight);
            }
         } else {
            this.getPreferenceScreen().removeAll();
            this.addPreference(this.mLights_setting);
            this.addPreference(this.mRadarvol);
            this.addPreference(this.mRadardisplay);
            this.addPreference(this.mRadarrange);
         }
      } else if (this.isHZC) {
         if (BaseApplication.getInstance().getCanbustypeCustomer() == 1) {
            this.getPreferenceScreen().removeAll();
            this.addPreference(this.mRemote_cmd);
            this.addPreference(this.mCardoorspeed);
            this.addPreference(this.mCardoorautomatic);
            this.addPreference(this.mPlinkage);
            this.addPreference(this.mRemoteunlock);
            this.addPreference(this.mSmartDoor);
            this.addPreference(this.mLockakey);
            this.addPreference(this.mOpendoorflash);
            this.addPreference(this.mLinkagedoorlock);
            this.addPreference(this.mClimate_settings);
            this.addPreference(this.mAirautokey);
            this.addPreference(this.mAirswitchautokey);
            this.addPreference(this.mLight_cmd);
            this.addPreference(this.mAutolight);
            this.addPreference(this.mTimeSwitchLight);
            this.addPreference(this.mLightingTime);
            this.addPreference(this.mLights_setting);
            this.addPreference(this.mRadarvol);
            this.addPreference(this.mRadardisplay);
            this.addPreference(this.mRadarrange);
         }
      } else {
         this.getPreferenceScreen().removePreference(this.mClimate_settings);
         this.getPreferenceScreen().removePreference(this.mLights_setting);
         this.getPreferenceScreen().removePreference(this.mRemote_cmd);
         this.getPreferenceScreen().removePreference(this.mLight_cmd);
      }

      if (BaseApplication.getInstance().getCanbustype() != 33) {
         this.getPreferenceScreen().removePreference(this.mLightingTime_wai);
      }

      this.RequestCmdkey((byte)38);
      this.RequestCmdkey((byte)30);
      this.RequestCmdkey((byte)26);
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
         if (var3.isChecked()) {
            this.SetCmdkey((byte)0, (byte)1);
         } else {
            this.SetCmdkey((byte)0, (byte)0);
         }
      } else {
         var3 = this.mCardoorautomatic;
         if (var2 == var3) {
            if (var3.isChecked()) {
               this.SetCmdkey((byte)1, (byte)1);
            } else {
               this.SetCmdkey((byte)1, (byte)0);
            }
         } else {
            var3 = this.mPlinkage;
            if (var2 == var3) {
               if (var3.isChecked()) {
                  this.SetCmdkey((byte)2, (byte)1);
               } else {
                  this.SetCmdkey((byte)2, (byte)0);
               }
            } else {
               var3 = this.mRemoteunlock;
               if (var2 == var3) {
                  if (var3.isChecked()) {
                     this.SetCmdkey((byte)3, (byte)1);
                  } else {
                     this.SetCmdkey((byte)3, (byte)0);
                  }
               } else {
                  var3 = this.mLockakey;
                  if (var2 == var3) {
                     if (var3.isChecked()) {
                        this.SetCmdkey((byte)16, (byte)1);
                     } else {
                        this.SetCmdkey((byte)16, (byte)0);
                     }
                  } else {
                     var3 = this.mTwokeyunlock;
                     if (var2 == var3) {
                        if (var3.isChecked()) {
                           this.SetCmdkey((byte)13, (byte)1);
                        } else {
                           this.SetCmdkey((byte)13, (byte)0);
                        }
                     } else {
                        var3 = this.mLinkagedoorlock;
                        if (var2 == var3) {
                           if (var3.isChecked()) {
                              this.SetCmdkey((byte)14, (byte)1);
                           } else {
                              this.SetCmdkey((byte)14, (byte)0);
                           }
                        } else {
                           var3 = this.mOpendoorflash;
                           if (var2 == var3) {
                              if (var3.isChecked()) {
                                 this.SetCmdkey((byte)17, (byte)1);
                              } else {
                                 this.SetCmdkey((byte)17, (byte)0);
                              }
                           } else {
                              var3 = this.mAirautokey;
                              if (var2 == var3) {
                                 if (var3.isChecked()) {
                                    this.SetCmdkey((byte)18, (byte)1);
                                 } else {
                                    this.SetCmdkey((byte)18, (byte)0);
                                 }
                              } else {
                                 var3 = this.mAirswitchautokey;
                                 if (var2 == var3) {
                                    if (var3.isChecked()) {
                                       this.SetCmdkey((byte)19, (byte)1);
                                    } else {
                                       this.SetCmdkey((byte)19, (byte)0);
                                    }
                                 } else {
                                    var3 = this.mDaylight;
                                    if (var2 == var3) {
                                       if (var3.isChecked()) {
                                          this.SetCmdkey((byte)4, (byte)1);
                                       } else {
                                          this.SetCmdkey((byte)4, (byte)0);
                                       }
                                    } else {
                                       var3 = this.mRadardisplay;
                                       if (var2 == var3) {
                                          if (var3.isChecked()) {
                                             this.SetCmdkey((byte)22, (byte)1);
                                          } else {
                                             this.SetCmdkey((byte)22, (byte)0);
                                          }
                                       } else {
                                          var3 = this.mWireless_lock;
                                          if (var2 == var3) {
                                             if (var3.isChecked()) {
                                                this.SetCmdkey((byte)37, (byte)1);
                                             } else {
                                                this.SetCmdkey((byte)37, (byte)0);
                                             }
                                          } else {
                                             var3 = this.mRadar_sound;
                                             if (var2 == var3) {
                                                if (var3.isChecked()) {
                                                   this.SetCmdkey((byte)26, (byte)1);
                                                } else {
                                                   this.SetCmdkey((byte)26, (byte)0);
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

      return super.onPreferenceTreeClick(var1, var2);
   }
}
