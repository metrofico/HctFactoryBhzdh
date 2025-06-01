package com.microntek.controlsettings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;

public class canbus62settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private PreferenceScreen Tyre_monitor;
   private HCheckBoxPreference mAcoustic_key;
   private SwitchboxPreference mCar_languages;
   private HCheckBoxPreference mControl_system;
   private HCheckBoxPreference mDisc_indicator;
   private HCheckBoxPreference mEnhanced_assistance;
   private HCheckBoxPreference mFive_key;
   private HCheckBoxPreference mIncar_light;
   private HCheckBoxPreference mInformation_tone;
   private SwitchboxPreference mLighteness;
   private HCheckBoxPreference mLock_ctrl;
   private SwitchboxPreference mMileage_unit;
   private HCheckBoxPreference mOff_Warning_info;
   private HCheckBoxPreference mRain_sensor;
   private HCheckBoxPreference mRamp;
   private HCheckBoxPreference mReverse_zoom;
   private SwitchboxPreference mTemp_dis;
   private SwitchboxPreference mTurn_lights_set;
   private SwitchboxPreference mWarning_control;
   private HCheckBoxPreference mWarning_tone;
   private byte[] setData = new byte[20];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   private int getSaveData(int var1) {
      return System.getInt(this.getContentResolver(), "panel_LED", var1);
   }

   private void setSaveData(int var1) {
      System.putInt(this.getContentResolver(), "panel_LED", var1);
   }

   protected void ProcessData(byte[] var1) {
      boolean var5 = false;
      int var2 = var1[0];
      int var3 = var1.length;
      if (var3 > 2) {
         if (var2 == 41) {
            for(var2 = 0; var2 < var3; ++var2) {
               if (var2 >= 1) {
                  this.setData[var2 - 1] = var1[var2];
               }
            }

            SwitchboxPreference var6 = this.mTemp_dis;
            StringBuilder var7 = new StringBuilder();
            var7.append("");
            var7.append(this.setData[0] >> 6 & 1);
            var6.setValue(var7.toString());
         } else {
            SwitchboxPreference var8;
            StringBuilder var11;
            if (var2 == 39) {
               for(var2 = 0; var2 < var3; ++var2) {
                  if (var2 >= 1) {
                     this.setData[var2 - 1] = var1[var2];
                  }
               }

               var3 = this.setData[0] & 127;
               var2 = var3;
               if (var3 > 27) {
                  var2 = 27;
               }

               var8 = this.mCar_languages;
               var11 = new StringBuilder();
               var11.append("");
               var11.append(var2);
               var8.setValue(var11.toString());
            } else if (var2 == 36) {
               for(var2 = 2; var2 < var3; ++var2) {
                  this.setData[var2 - 2] = var1[var2];
               }

               var8 = this.mTurn_lights_set;
               var11 = new StringBuilder();
               var11.append("");
               var11.append(this.setData[2] >> 1 & 1);
               var8.setValue(var11.toString());
               HCheckBoxPreference var9 = this.mControl_system;
               boolean var4;
               if ((this.setData[2] & 1) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mInformation_tone;
               if ((this.setData[2] & 4) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mWarning_tone;
               if ((this.setData[2] & 8) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var8 = this.mWarning_control;
               var11 = new StringBuilder();
               var11.append("");
               var11.append(this.setData[2] >> 4 & 7);
               var8.setValue(var11.toString());
               var8 = this.mMileage_unit;
               var11 = new StringBuilder();
               var11.append("");
               var11.append(this.setData[2] >> 7 & 1);
               var8.setValue(var11.toString());
               var8 = this.mLighteness;
               var11 = new StringBuilder();
               var11.append("");
               var11.append(this.setData[3] >> 7 & 1);
               var8.setValue(var11.toString());
               PreferenceScreen var12 = this.Tyre_monitor;
               String var10;
               if ((this.setData[3] & 64) != 0) {
                  var10 = this.getString(2131296953);
               } else {
                  var10 = " ";
               }

               var12.setSummary(var10);
               var9 = this.mRain_sensor;
               if ((this.setData[5] & 1) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mIncar_light;
               if ((this.setData[5] & 2) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mLock_ctrl;
               if ((this.setData[5] & 4) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mRamp;
               if ((this.setData[5] & 8) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mReverse_zoom;
               if ((this.setData[5] & 64) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var9.setChecked(var4);
               var9 = this.mEnhanced_assistance;
               var4 = var5;
               if ((this.setData[5] & 32) != 0) {
                  var4 = true;
               }

               var9.setChecked(var4);
            }
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492934);
      this.mControl_system = (HCheckBoxPreference)this.findPreference("control_system");
      this.mControl_system.setOnPreferenceClickListener(this);
      this.mInformation_tone = (HCheckBoxPreference)this.findPreference("information_tone");
      this.mInformation_tone.setOnPreferenceClickListener(this);
      this.mWarning_tone = (HCheckBoxPreference)this.findPreference("warning_tone");
      this.mWarning_tone.setOnPreferenceClickListener(this);
      this.mDisc_indicator = (HCheckBoxPreference)this.findPreference("disc_indicator");
      this.mDisc_indicator.setOnPreferenceClickListener(this);
      this.mAcoustic_key = (HCheckBoxPreference)this.findPreference("acoustic_key");
      this.mAcoustic_key.setOnPreferenceClickListener(this);
      this.mFive_key = (HCheckBoxPreference)this.findPreference("five_key");
      this.mFive_key.setOnPreferenceClickListener(this);
      this.mOff_Warning_info = (HCheckBoxPreference)this.findPreference("off_Warning_info");
      this.mOff_Warning_info.setOnPreferenceClickListener(this);
      this.mRain_sensor = (HCheckBoxPreference)this.findPreference("rain_sensor");
      this.mRain_sensor.setOnPreferenceClickListener(this);
      this.mIncar_light = (HCheckBoxPreference)this.findPreference("incar_light");
      this.mIncar_light.setOnPreferenceClickListener(this);
      this.mLock_ctrl = (HCheckBoxPreference)this.findPreference("lock_ctrl");
      this.mLock_ctrl.setOnPreferenceClickListener(this);
      this.mRamp = (HCheckBoxPreference)this.findPreference("ramp");
      this.mRamp.setOnPreferenceClickListener(this);
      this.mReverse_zoom = (HCheckBoxPreference)this.findPreference("reverse_zoom");
      this.mReverse_zoom.setOnPreferenceClickListener(this);
      this.mEnhanced_assistance = (HCheckBoxPreference)this.findPreference("enhanced_assistance");
      this.mEnhanced_assistance.setOnPreferenceClickListener(this);
      this.mTemp_dis = (SwitchboxPreference)this.findPreference("temp_dis");
      this.mTemp_dis.setOnPreferenceChangeListener(this);
      this.mCar_languages = (SwitchboxPreference)this.findPreference("car_languages");
      this.mCar_languages.setOnPreferenceChangeListener(this);
      this.getPreferenceScreen().removePreference(this.mCar_languages);
      this.mTurn_lights_set = (SwitchboxPreference)this.findPreference("turn_lights_set");
      this.mTurn_lights_set.setOnPreferenceChangeListener(this);
      this.mWarning_control = (SwitchboxPreference)this.findPreference("warning_control");
      this.mWarning_control.setOnPreferenceChangeListener(this);
      this.mMileage_unit = (SwitchboxPreference)this.findPreference("mileage_unit");
      this.mMileage_unit.setOnPreferenceChangeListener(this);
      this.mLighteness = (SwitchboxPreference)this.findPreference("lighteness");
      this.mLighteness.setOnPreferenceChangeListener(this);
      this.Tyre_monitor = (PreferenceScreen)this.findPreference("tyre_monitor");
      Intent var5 = this.getIntent();
      boolean var4 = false;
      var5.getIntExtra("cftype", 0);
      byte[] var6 = new byte[]{41, 8};
      this.SendCanBusCmdData2E((byte)-112, var6, 2);
      var6[0] = 39;
      this.SendCanBusCmdData2E((byte)-112, var6, 2);
      var6[0] = 33;
      this.SendCanBusCmdData2E((byte)-112, var6, 2);
      var6[0] = 36;
      this.SendCanBusCmdData2E((byte)-112, var6, 2);
      int var2 = this.getSaveData(0);
      HCheckBoxPreference var7 = this.mDisc_indicator;
      boolean var3;
      if ((var2 & 1) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var7.setChecked(var3);
      var7 = this.mAcoustic_key;
      if ((var2 & 2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var7.setChecked(var3);
      var7 = this.mFive_key;
      var3 = var4;
      if ((var2 & 4) != 0) {
         var3 = true;
      }

      var7.setChecked(var3);
      if (System.getInt(this.getContentResolver(), "mOff_Warning_infor", 1) == 1) {
         this.mOff_Warning_info.setChecked(true);
      }

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
         if (var1 == this.mTemp_dis) {
            this.SetCmdkey((byte)-96, (byte)var3);
         } else if (var1 == this.mCar_languages) {
            this.SetCmdkey((byte)-92, (byte)var3);
         } else if (var1 == this.mTurn_lights_set) {
            this.SetCmdkey((byte)-93, (byte)(var3 + 3));
         } else if (var1 == this.mWarning_control) {
            this.SetCmdkey((byte)-93, (byte)(var3 + 9));
         } else if (var1 == this.mMileage_unit) {
            this.SetCmdkey((byte)-93, (byte)(var3 + 14));
         } else if (var1 == this.mLighteness) {
            this.SetCmdkey((byte)-93, (byte)(var3 + 16));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      int var2 = this.getSaveData(0);
      HCheckBoxPreference var3 = this.mControl_system;
      if (var1 == var3) {
         if (var3.mChecked) {
            this.SetCmdkey((byte)-93, (byte)2);
         } else {
            this.SetCmdkey((byte)-93, (byte)1);
         }
      } else {
         var3 = this.mInformation_tone;
         if (var1 == var3) {
            if (var3.mChecked) {
               this.SetCmdkey((byte)-93, (byte)5);
            } else {
               this.SetCmdkey((byte)-93, (byte)6);
            }
         } else {
            var3 = this.mWarning_tone;
            if (var1 == var3) {
               if (var3.mChecked) {
                  this.SetCmdkey((byte)-93, (byte)7);
               } else {
                  this.SetCmdkey((byte)-93, (byte)8);
               }
            } else {
               var3 = this.mDisc_indicator;
               if (var1 == var3) {
                  if (var3.mChecked) {
                     var2 ^= 1;
                     this.SetCmdkey((byte)-94, (byte)var2);
                     this.mDisc_indicator.setChecked(false);
                     this.setSaveData(var2);
                  } else {
                     var2 |= 1;
                     this.SetCmdkey((byte)-94, (byte)var2);
                     this.mDisc_indicator.setChecked(true);
                     this.setSaveData(var2);
                  }
               } else {
                  var3 = this.mAcoustic_key;
                  if (var1 == var3) {
                     if (var3.mChecked) {
                        var2 ^= 2;
                        this.SetCmdkey((byte)-94, (byte)var2);
                        this.mAcoustic_key.setChecked(false);
                        this.setSaveData(var2);
                     } else {
                        var2 |= 2;
                        this.SetCmdkey((byte)-94, (byte)var2);
                        this.mAcoustic_key.setChecked(true);
                        this.setSaveData(var2);
                     }
                  } else {
                     var3 = this.mFive_key;
                     if (var1 == var3) {
                        if (var3.mChecked) {
                           var2 ^= 4;
                           this.SetCmdkey((byte)-94, (byte)var2);
                           this.mFive_key.setChecked(false);
                           this.setSaveData(var2);
                        } else {
                           var2 |= 4;
                           this.SetCmdkey((byte)-94, (byte)var2);
                           this.mFive_key.setChecked(true);
                           this.setSaveData(var2);
                        }
                     } else {
                        var3 = this.mOff_Warning_info;
                        if (var1 == var3) {
                           if (var3.mChecked) {
                              var3.setChecked(false);
                              System.putInt(this.getContentResolver(), "mOff_Warning_infor", 0);
                           } else {
                              var3.setChecked(true);
                              System.putInt(this.getContentResolver(), "mOff_Warning_infor", 1);
                           }
                        } else {
                           var3 = this.mRain_sensor;
                           if (var1 == var3) {
                              if (var3.mChecked) {
                                 this.SetCmdkey((byte)-91, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)-91, (byte)1);
                              }
                           } else {
                              var3 = this.mIncar_light;
                              if (var1 == var3) {
                                 if (var3.mChecked) {
                                    this.SetCmdkey((byte)-90, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)-90, (byte)1);
                                 }
                              } else {
                                 var3 = this.mLock_ctrl;
                                 if (var1 == var3) {
                                    if (var3.mChecked) {
                                       this.SetCmdkey((byte)-89, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)-89, (byte)1);
                                    }
                                 } else {
                                    var3 = this.mRamp;
                                    if (var1 == var3) {
                                       if (var3.mChecked) {
                                          this.SetCmdkey((byte)-88, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)-88, (byte)1);
                                       }
                                    } else {
                                       var3 = this.mReverse_zoom;
                                       if (var1 == var3) {
                                          if (var3.mChecked) {
                                             this.SetCmdkey((byte)-85, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)-85, (byte)1);
                                          }
                                       } else {
                                          var3 = this.mEnhanced_assistance;
                                          if (var1 == var3) {
                                             if (var3.mChecked) {
                                                this.SetCmdkey((byte)-83, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)-83, (byte)1);
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
      if (var2 == this.Tyre_monitor) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus62settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)-87, (byte)1);
            }
         }, this.Tyre_monitor.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
