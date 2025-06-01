package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus83settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mAuto_parking;
   private HCheckBoxPreference mBwiper;
   private HCheckBoxPreference mDaytime_lights;
   private HCheckBoxPreference mEngine_stop;
   private SwitchboxPreference mLamp_no;
   private HCheckBoxPreference mParking_assist;
   private HCheckBoxPreference mRadar_sound;
   private SwitchboxPreference mSetdoor;
   private PreferenceScreen mTpms_cal;
   private PreferenceScreen mTpms_calibration;
   private HCheckBoxPreference mTrunkdoor;
   private HCheckBoxPreference mU_auto_parking;
   private HCheckBoxPreference mU_daytime_lights;
   private HCheckBoxPreference mU_headlights;
   private SwitchboxPreference mU_oil_unit;
   private SwitchboxPreference mU_temp;
   private SwitchboxPreference mU_welcome_lig;
   private byte[] setData = new byte[20];

   private void SetCmdkeyID_80(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-128, new byte[]{var1, var2}, 2);
   }

   private void updataCarCMD() {
      byte var1 = this.setData[2];
      boolean var3 = false;
      if ((var1 & 128) != 0) {
         this.mParking_assist.setChecked(true);
      } else {
         this.mParking_assist.setChecked(false);
      }

      if ((this.setData[1] & 128) != 0) {
         this.mBwiper.setChecked(true);
      } else {
         this.mBwiper.setChecked(false);
      }

      if ((this.setData[2] & 1) != 0) {
         this.mDaytime_lights.setChecked(true);
      } else {
         this.mDaytime_lights.setChecked(false);
      }

      byte[] var4 = this.setData;
      if ((byte)(var4[2] & 96) == 96) {
         this.mLamp_no.setValue("3");
      } else if ((byte)(var4[2] & 64) == 64) {
         this.mLamp_no.setValue("2");
      } else if ((byte)(var4[2] & 32) == 32) {
         this.mLamp_no.setValue("1");
      } else {
         this.mLamp_no.setValue("0");
      }

      HCheckBoxPreference var6 = this.mEngine_stop;
      boolean var2;
      if ((this.setData[1] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.setChecked(var2);
      var1 = this.setData[3];
      SwitchboxPreference var5 = this.mSetdoor;
      StringBuilder var7 = new StringBuilder();
      var7.append("");
      var7.append(var1 >> 7 & 1);
      var5.setValue(var7.toString());
      var6 = this.mTrunkdoor;
      if ((this.setData[1] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.setChecked(var2);
      var6 = this.mRadar_sound;
      if ((this.setData[3] & 1) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.setChecked(var2);
      var6 = this.mAuto_parking;
      if ((this.setData[3] & 64) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.setChecked(var2);
      var6 = this.mU_daytime_lights;
      if ((this.setData[2] & 16) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.setChecked(var2);
      var6 = this.mU_headlights;
      if ((this.setData[1] & 2) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var6.setChecked(var2);
      var6 = this.mU_auto_parking;
      var2 = var3;
      if ((this.setData[3] & 64) != 0) {
         var2 = true;
      }

      var6.setChecked(var2);
      var1 = this.setData[2];
      var5 = this.mU_oil_unit;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(var1 >> 2 & 3);
      var5.setValue(var7.toString());
      var1 = this.setData[2];
      SwitchboxPreference var9 = this.mU_temp;
      StringBuilder var8 = new StringBuilder();
      var8.append("");
      var8.append(var1 >> 1 & 1);
      var9.setValue(var8.toString());
      var1 = this.setData[1];
      var9 = this.mU_welcome_lig;
      var8 = new StringBuilder();
      var8.append("");
      var8.append(var1 >> 2 & 3);
      var9.setValue(var8.toString());
   }

   protected void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      int var3 = var1.length;
      int var2 = 2;
      if (var3 > 2) {
         if (var4 == 56) {
            while(var2 < var3) {
               this.setData[var2 - 2] = var1[var2];
               ++var2;
            }

            this.updataCarCMD();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492945);
      this.mParking_assist = (HCheckBoxPreference)this.findPreference("parking_assist");
      HCheckBoxPreference var2 = this.mParking_assist;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mBwiper = (HCheckBoxPreference)this.findPreference("bwiper");
      var2 = this.mBwiper;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mDaytime_lights = (HCheckBoxPreference)this.findPreference("daytime_lights");
      var2 = this.mDaytime_lights;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mEngine_stop = (HCheckBoxPreference)this.findPreference("engine_stop");
      var2 = this.mEngine_stop;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mTrunkdoor = (HCheckBoxPreference)this.findPreference("trunkdoor");
      var2 = this.mTrunkdoor;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRadar_sound = (HCheckBoxPreference)this.findPreference("radar_sound");
      var2 = this.mRadar_sound;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAuto_parking = (HCheckBoxPreference)this.findPreference("auto_parking");
      var2 = this.mAuto_parking;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mTpms_cal = (PreferenceScreen)this.findPreference("tpms_cal");
      PreferenceScreen var3 = this.mTpms_cal;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mTpms_calibration = (PreferenceScreen)this.findPreference("tpms_calibration");
      var3 = this.mTpms_calibration;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mLamp_no = (SwitchboxPreference)this.findPreference("lamp_no");
      SwitchboxPreference var4 = this.mLamp_no;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      this.mSetdoor = (SwitchboxPreference)this.findPreference("setdoor");
      var4 = this.mSetdoor;
      if (var4 != null) {
         var4.setOnPreferenceChangeListener(this);
      }

      this.mU_daytime_lights = (HCheckBoxPreference)this.findPreference("u_daytime_lights");
      this.mU_daytime_lights.setOnPreferenceClickListener(this);
      this.mU_headlights = (HCheckBoxPreference)this.findPreference("u_headlights");
      this.mU_headlights.setOnPreferenceClickListener(this);
      this.mU_auto_parking = (HCheckBoxPreference)this.findPreference("u_auto_parking");
      this.mU_auto_parking.setOnPreferenceClickListener(this);
      this.mU_oil_unit = (SwitchboxPreference)this.findPreference("u_oil_unit");
      this.mU_oil_unit.setOnPreferenceChangeListener(this);
      this.mU_temp = (SwitchboxPreference)this.findPreference("u_temp");
      this.mU_temp.setOnPreferenceChangeListener(this);
      this.mU_welcome_lig = (SwitchboxPreference)this.findPreference("u_welcome_lig");
      this.mU_welcome_lig.setOnPreferenceChangeListener(this);
      if (this.getCarType() == 1) {
         this.removePreference((Preference)this.mDaytime_lights);
         this.removePreference((Preference)this.mAuto_parking);
         this.removePreference((Preference)this.mTpms_calibration);
      } else {
         this.removePreference((Preference)this.mU_oil_unit);
         this.removePreference((Preference)this.mU_temp);
         this.removePreference((Preference)this.mU_welcome_lig);
         this.removePreference((Preference)this.mU_daytime_lights);
         this.removePreference((Preference)this.mU_headlights);
         this.removePreference((Preference)this.mU_auto_parking);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var3;
      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var5) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mLamp_no) {
            this.SetCmdkeyID_80((byte)4, (byte)var4);
         } else if (var1 == this.mSetdoor) {
            this.SetCmdkeyID_80((byte)9, (byte)(var4 + 0));
         } else if (var1 == this.mU_oil_unit) {
            this.SetCmdkeyID_80((byte)6, (byte)(var4 + 0));
         } else if (var1 == this.mU_temp) {
            this.SetCmdkeyID_80((byte)7, (byte)(var4 + 0));
         } else if (var1 == this.mU_welcome_lig) {
            this.SetCmdkeyID_80((byte)11, (byte)(var4 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mParking_assist;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkeyID_80((byte)1, (byte)0);
         } else {
            this.SetCmdkeyID_80((byte)1, (byte)1);
         }
      } else {
         var2 = this.mBwiper;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkeyID_80((byte)2, (byte)0);
            } else {
               this.SetCmdkeyID_80((byte)2, (byte)1);
            }
         } else {
            var2 = this.mDaytime_lights;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkeyID_80((byte)8, (byte)0);
               } else {
                  this.SetCmdkeyID_80((byte)8, (byte)1);
               }
            } else {
               var2 = this.mEngine_stop;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkeyID_80((byte)11, (byte)0);
                  } else {
                     this.SetCmdkeyID_80((byte)11, (byte)1);
                  }
               } else {
                  var2 = this.mTrunkdoor;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkeyID_80((byte)3, (byte)0);
                     } else {
                        this.SetCmdkeyID_80((byte)3, (byte)1);
                     }
                  } else {
                     var2 = this.mRadar_sound;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkeyID_80((byte)-1, (byte)0);
                        } else {
                           this.SetCmdkeyID_80((byte)-1, (byte)1);
                        }
                     } else {
                        var2 = this.mAuto_parking;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkeyID_80((byte)10, (byte)0);
                           } else {
                              this.SetCmdkeyID_80((byte)10, (byte)1);
                           }
                        } else {
                           var2 = this.mU_daytime_lights;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkeyID_80((byte)5, (byte)0);
                              } else {
                                 this.SetCmdkeyID_80((byte)5, (byte)1);
                              }
                           } else {
                              var2 = this.mU_headlights;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkeyID_80((byte)12, (byte)0);
                                 } else {
                                    this.SetCmdkeyID_80((byte)12, (byte)1);
                                 }
                              } else {
                                 var2 = this.mU_auto_parking;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkeyID_80((byte)13, (byte)0);
                                    } else {
                                       this.SetCmdkeyID_80((byte)13, (byte)1);
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
      if (var2 == this.mTpms_cal) {
         this.SendCanBusCmdData2E((byte)-80, new byte[]{1}, 1);
      } else if (var2 == this.mTpms_calibration) {
         this.SetCmdkeyID_80((byte)34, (byte)1);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
