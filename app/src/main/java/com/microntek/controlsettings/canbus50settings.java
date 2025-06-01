package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus50settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mBlind_detection;
   private HCheckBoxPreference mBwiper;
   private HCheckBoxPreference mDaytime_lights;
   private HCheckBoxPreference mEngine_stop;
   private HCheckBoxPreference mHeadlights;
   private SwitchboxPreference mHome_lighting;
   private SwitchboxPreference mLamp_no;
   private SwitchboxPreference mLanguages;
   private SwitchboxPreference mLighting;
   private SwitchboxPreference mOil_unit;
   private HCheckBoxPreference mParking_assist;
   private HCheckBoxPreference mPuzhu;
   private HCheckBoxPreference mRadar_stop;
   private SwitchboxPreference mSetdoor;
   private SwitchboxPreference mSound;
   private SwitchboxPreference mTemp;
   private PreferenceScreen mTpms_cal;
   private HCheckBoxPreference mUnlock_trunk_only;
   private HCheckBoxPreference mWelcome_cmd;
   private SwitchboxPreference mWelcome_lig;
   private byte[] setData = new byte[20];

   private void SetCmdkeyID_80(byte var1, byte var2) {
      this.SendCanBusCmdDataFD((byte)-128, new byte[]{var1, var2}, 2);
   }

   private void carData() {
      this.getPreferenceScreen().removePreference(this.mLamp_no);
      this.getPreferenceScreen().removePreference(this.mLighting);
      this.getPreferenceScreen().removePreference(this.mHome_lighting);
      this.getPreferenceScreen().removePreference(this.mWelcome_lig);
      this.getPreferenceScreen().removePreference(this.mSound);
      this.getPreferenceScreen().removePreference(this.mOil_unit);
      this.getPreferenceScreen().removePreference(this.mLanguages);
      this.getPreferenceScreen().removePreference(this.mSetdoor);
      this.getPreferenceScreen().removePreference(this.mParking_assist);
      this.getPreferenceScreen().removePreference(this.mBwiper);
      this.getPreferenceScreen().removePreference(this.mDaytime_lights);
      this.getPreferenceScreen().removePreference(this.mRadar_stop);
      this.getPreferenceScreen().removePreference(this.mBlind_detection);
      this.getPreferenceScreen().removePreference(this.mEngine_stop);
      this.getPreferenceScreen().removePreference(this.mWelcome_cmd);
   }

   private void updataCarCMD() {
      byte var1 = this.setData[1];
      boolean var3 = false;
      if ((var1 & 8) != 0) {
         this.mParking_assist.setChecked(true);
      } else {
         this.mParking_assist.setChecked(false);
      }

      if ((this.setData[1] & 128) != 0) {
         this.mBwiper.setChecked(true);
      } else {
         this.mBwiper.setChecked(false);
      }

      if ((this.setData[1] & 64) != 0) {
         this.mPuzhu.setChecked(true);
      } else {
         this.mPuzhu.setChecked(false);
      }

      if ((this.setData[2] & 128) != 0) {
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

      var1 = this.setData[3];
      SwitchboxPreference var6 = this.mLighting;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      var5.append(var1 >> 5 & 7);
      var6.setValue(var5.toString());
      if ((this.setData[3] & 8) != 0) {
         this.mRadar_stop.setChecked(true);
      } else {
         this.mRadar_stop.setChecked(false);
      }

      var1 = this.setData[4];
      SwitchboxPreference var8 = this.mHome_lighting;
      StringBuilder var7 = new StringBuilder();
      var7.append("");
      var7.append(var1 >> 6 & 3);
      var8.setValue(var7.toString());
      var1 = this.setData[4];
      var6 = this.mWelcome_lig;
      var5 = new StringBuilder();
      var5.append("");
      var5.append(var1 >> 4 & 3);
      var6.setValue(var5.toString());
      var1 = this.setData[4];
      var6 = this.mSound;
      var5 = new StringBuilder();
      var5.append("");
      var5.append(var1 >> 1 & 3);
      var6.setValue(var5.toString());
      var1 = this.setData[4];
      var8 = this.mOil_unit;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(var1 >> 0 & 1);
      var8.setValue(var7.toString());
      var1 = this.setData[5];
      var8 = this.mLanguages;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(var1 >> 0 & 1);
      var8.setValue(var7.toString());
      HCheckBoxPreference var9 = this.mBlind_detection;
      boolean var2;
      if ((this.setData[5] & 128) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var9.setChecked(var2);
      var9 = this.mEngine_stop;
      if ((64 & this.setData[5]) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var9.setChecked(var2);
      var9 = this.mWelcome_cmd;
      if ((this.setData[5] & 32) != 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var9.setChecked(var2);
      var1 = this.setData[5];
      var8 = this.mSetdoor;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(var1 >> 4 & 1);
      var8.setValue(var7.toString());
      var9 = this.mHeadlights;
      if ((this.setData[2] & 1) == 0) {
         var2 = true;
      } else {
         var2 = false;
      }

      var9.setChecked(var2);
      var9 = this.mUnlock_trunk_only;
      var2 = var3;
      if ((this.setData[3] & 16) != 0) {
         var2 = true;
      }

      var9.setChecked(var2);
      var1 = this.setData[1];
      var8 = this.mTemp;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(var1 >> 5 & 1);
      var8.setValue(var7.toString());
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
      this.addPreferencesFromResource(2131492924);
      this.mParking_assist = (HCheckBoxPreference)this.findPreference("parking_assist");
      HCheckBoxPreference var3 = this.mParking_assist;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mBwiper = (HCheckBoxPreference)this.findPreference("bwiper");
      var3 = this.mBwiper;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mDaytime_lights = (HCheckBoxPreference)this.findPreference("daytime_lights");
      var3 = this.mDaytime_lights;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mRadar_stop = (HCheckBoxPreference)this.findPreference("radar_stop");
      var3 = this.mRadar_stop;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mBlind_detection = (HCheckBoxPreference)this.findPreference("blind_detection");
      var3 = this.mBlind_detection;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mEngine_stop = (HCheckBoxPreference)this.findPreference("engine_stop");
      var3 = this.mEngine_stop;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mWelcome_cmd = (HCheckBoxPreference)this.findPreference("welcome_cmd");
      var3 = this.mWelcome_cmd;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mPuzhu = (HCheckBoxPreference)this.findPreference("puzhu");
      var3 = this.mPuzhu;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mUnlock_trunk_only = (HCheckBoxPreference)this.findPreference("unlock_trunk_only");
      var3 = this.mUnlock_trunk_only;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mTpms_cal = (PreferenceScreen)this.findPreference("tpms_cal");
      PreferenceScreen var4 = this.mTpms_cal;
      if (var4 != null) {
         var4.setOnPreferenceClickListener(this);
      }

      this.mHeadlights = (HCheckBoxPreference)this.findPreference("headlights");
      var3 = this.mHeadlights;
      if (var3 != null) {
         var3.setOnPreferenceClickListener(this);
      }

      this.mLamp_no = (SwitchboxPreference)this.findPreference("lamp_no");
      SwitchboxPreference var5 = this.mLamp_no;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mLighting = (SwitchboxPreference)this.findPreference("atmosphere_lighting");
      var5 = this.mLighting;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mHome_lighting = (SwitchboxPreference)this.findPreference("home_lighting");
      var5 = this.mHome_lighting;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mWelcome_lig = (SwitchboxPreference)this.findPreference("welcome_lig");
      var5 = this.mWelcome_lig;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mSound = (SwitchboxPreference)this.findPreference("sound");
      var5 = this.mSound;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mOil_unit = (SwitchboxPreference)this.findPreference("oil_unit");
      var5 = this.mOil_unit;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mLanguages = (SwitchboxPreference)this.findPreference("languages");
      var5 = this.mLanguages;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mSetdoor = (SwitchboxPreference)this.findPreference("setdoor");
      var5 = this.mSetdoor;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      this.mTemp = (SwitchboxPreference)this.findPreference("temp");
      var5 = this.mTemp;
      if (var5 != null) {
         var5.setOnPreferenceChangeListener(this);
      }

      int var2 = this.getIntent().getIntExtra("cftype", 0);
      byte[] var6 = new byte[1];
      if (var2 == 88) {
         this.carData();
         var6[0] = 59;
      } else {
         var6[0] = 56;
      }

      this.SendCanBusCmdDataFD((byte)-113, var6, 1);
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
         if (var1 == this.mLamp_no) {
            this.SetCmdkeyID_80((byte)4, (byte)var3);
         } else if (var1 == this.mLighting) {
            this.SetCmdkeyID_80((byte)5, (byte)var3);
         } else if (var1 == this.mHome_lighting) {
            this.SetCmdkeyID_80((byte)7, (byte)(var3 + 0));
         } else if (var1 == this.mWelcome_lig) {
            this.SetCmdkeyID_80((byte)8, (byte)(var3 + 0));
         } else if (var1 == this.mSound) {
            this.SetCmdkeyID_80((byte)9, (byte)(var3 + 1));
         } else if (var1 == this.mOil_unit) {
            this.SetCmdkeyID_80((byte)10, (byte)(var3 + 0));
         } else if (var1 == this.mLanguages) {
            this.SetCmdkeyID_80((byte)11, (byte)(var3 + 0));
         } else if (var1 == this.mSetdoor) {
            this.SetCmdkeyID_80((byte)15, (byte)(var3 + 0));
         } else if (var1 == this.mTemp) {
            this.SetCmdkeyID_80((byte)20, (byte)(var3 + 0));
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
                  this.SetCmdkeyID_80((byte)3, (byte)0);
               } else {
                  this.SetCmdkeyID_80((byte)3, (byte)1);
               }
            } else {
               var2 = this.mRadar_stop;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkeyID_80((byte)6, (byte)0);
                  } else {
                     this.SetCmdkeyID_80((byte)6, (byte)1);
                  }
               } else {
                  var2 = this.mBlind_detection;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkeyID_80((byte)12, (byte)0);
                     } else {
                        this.SetCmdkeyID_80((byte)12, (byte)1);
                     }
                  } else {
                     var2 = this.mEngine_stop;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkeyID_80((byte)13, (byte)0);
                        } else {
                           this.SetCmdkeyID_80((byte)13, (byte)1);
                        }
                     } else {
                        var2 = this.mWelcome_cmd;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkeyID_80((byte)14, (byte)0);
                           } else {
                              this.SetCmdkeyID_80((byte)14, (byte)1);
                           }
                        } else {
                           var2 = this.mPuzhu;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkeyID_80((byte)17, (byte)0);
                              } else {
                                 this.SetCmdkeyID_80((byte)17, (byte)1);
                              }
                           } else {
                              var2 = this.mHeadlights;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkeyID_80((byte)18, (byte)0);
                                 } else {
                                    this.SetCmdkeyID_80((byte)18, (byte)1);
                                 }
                              } else {
                                 var2 = this.mUnlock_trunk_only;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkeyID_80((byte)19, (byte)0);
                                    } else {
                                       this.SetCmdkeyID_80((byte)19, (byte)1);
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
         this.SetCmdkeyID_80((byte)16, (byte)1);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
