package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;

public class canbus6settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private PreferenceScreen Tyre_monitor;
   private HCheckBoxPreference mAcoustic_key;
   private SwitchboxPreference mBrightness;
   private HCheckBoxPreference mBuzzer;
   private HCheckBoxPreference mControl_system;
   private HCheckBoxPreference mDisc_indicator;
   private HCheckBoxPreference mEnhanced_assistance;
   private HCheckBoxPreference mFive_key;
   private HCheckBoxPreference mIncar_light;
   private HCheckBoxPreference mInformation_tone;
   private SwitchboxPreference mMileage_unit;
   private HCheckBoxPreference mRain_sensor;
   private HCheckBoxPreference mRamp;
   private HCheckBoxPreference mRear_maint;
   private HCheckBoxPreference mReverse_zoom;
   private SwitchboxPreference mTemp_dis;
   private SwitchboxPreference mTurn_lights_set;
   private SwitchboxPreference mWarning_control;
   private HCheckBoxPreference mWarning_tone;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   private int getSaveData(int var1) {
      return System.getInt(this.getContentResolver(), "panel_LED", var1);
   }

   private void setSaveData(int var1) {
      System.putInt(this.getContentResolver(), "panel_LED", var1);
   }

   public void ProcessData(byte[] var1) {
      boolean var4 = false;
      int var2 = var1[0] & 255;
      if (var2 != 33) {
         if (var2 == 36) {
            HCheckBoxPreference var5 = this.mControl_system;
            boolean var3;
            if ((var1[4] & 1) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            SwitchboxPreference var6 = this.mTurn_lights_set;
            StringBuilder var7 = new StringBuilder();
            var7.append("");
            var7.append(var1[4] >> 1 & 1);
            var6.setValue(var7.toString());
            var5 = this.mInformation_tone;
            if ((var1[4] & 4) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            var5 = this.mWarning_tone;
            if ((var1[4] & 8) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            var6 = this.mMileage_unit;
            var7 = new StringBuilder();
            var7.append("");
            var7.append(var1[4] >> 7 & 1);
            var6.setValue(var7.toString());
            var5 = this.mRain_sensor;
            if ((var1[7] & 1) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            var5 = this.mReverse_zoom;
            if ((var1[4] & 32) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            var5 = this.mEnhanced_assistance;
            if ((var1[4] & 16) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            var5 = this.mBuzzer;
            if ((var1[7] & 2) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var5.setChecked(var3);
            var5 = this.mRear_maint;
            var3 = var4;
            if ((var1[4] & 64) != 0) {
               var3 = true;
            }

            var5.setChecked(var3);
         }
      } else {
         SwitchboxPreference var9 = this.mTemp_dis;
         StringBuilder var8 = new StringBuilder();
         var8.append("");
         var8.append(var1[6] >> 6 & 1);
         var9.setValue(var8.toString());
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492931);
      this.mControl_system = this.findHCheckBoxPreference("control_system");
      this.mInformation_tone = this.findHCheckBoxPreference("information_tone");
      this.mWarning_tone = this.findHCheckBoxPreference("warning_tone");
      this.mDisc_indicator = this.findHCheckBoxPreference("disc_indicator");
      this.mAcoustic_key = this.findHCheckBoxPreference("acoustic_key");
      this.mFive_key = this.findHCheckBoxPreference("five_key");
      this.mBuzzer = this.findHCheckBoxPreference("buzzer");
      this.mRear_maint = this.findHCheckBoxPreference("rear_maint");
      this.mRain_sensor = this.findHCheckBoxPreference("rain_sensor");
      this.mIncar_light = this.findHCheckBoxPreference("incar_light");
      this.mRamp = this.findHCheckBoxPreference("ramp");
      this.mReverse_zoom = this.findHCheckBoxPreference("reverse_zoom");
      this.mEnhanced_assistance = this.findHCheckBoxPreference("enhanced_assistance");
      this.mTemp_dis = this.findSwitchboxPreference("temp_dis");
      this.mTurn_lights_set = this.findSwitchboxPreference("turn_lights_set");
      this.mWarning_control = this.findSwitchboxPreference("warning_control");
      this.mMileage_unit = this.findSwitchboxPreference("mileage_unit");
      this.mBrightness = this.findSwitchboxPreference("brightness");
      this.Tyre_monitor = this.findPreferenceScreen("tyre_monitor");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{33, 0}, 2);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{36, 0}, 2);
      int var2 = this.getSaveData(0);
      HCheckBoxPreference var6 = this.mDisc_indicator;
      boolean var4 = true;
      boolean var3;
      if ((var2 & 1) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var6.setChecked(var3);
      var6 = this.mAcoustic_key;
      if ((var2 & 2) != 0) {
         var3 = true;
      } else {
         var3 = false;
      }

      var6.setChecked(var3);
      var6 = this.mFive_key;
      if ((var2 & 4) != 0) {
         var3 = var4;
      } else {
         var3 = false;
      }

      var6.setChecked(var3);
      SwitchboxPreference var5 = this.mWarning_control;
      StringBuilder var7 = new StringBuilder();
      var7.append("");
      var7.append(this.getInt("mWarning_control", 0));
      var5.setValue(var7.toString());
      var5 = this.mBrightness;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(this.getInt("mBrightness", 0));
      var5.setValue(var7.toString());
      this.removePreference((Preference)this.mRamp);
      this.removePreference((Preference)this.mIncar_light);
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
         } else if (var1 == this.mTurn_lights_set) {
            this.SetCmdkey((byte)-93, (byte)(var3 + 3));
         } else {
            StringBuilder var7;
            SwitchboxPreference var8;
            if (var1 == this.mWarning_control) {
               this.SetCmdkey((byte)-93, (byte)(var3 + 9));
               var8 = this.mWarning_control;
               var7 = new StringBuilder();
               var7.append("");
               var7.append(var3);
               var8.setValue(var7.toString());
               this.putInt("mWarning_control", var3);
            } else if (var1 == this.mMileage_unit) {
               this.SetCmdkey((byte)-93, (byte)(var3 + 14));
            } else if (var1 == this.mBrightness) {
               this.SetCmdkey((byte)-93, (byte)(var3 + 16));
               var8 = this.mBrightness;
               var7 = new StringBuilder();
               var7.append("");
               var7.append(var3);
               var8.setValue(var7.toString());
               this.putInt("mBrightness", var3);
            }
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
                     var2 &= -2;
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
                        var2 &= -3;
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
                           var2 &= -5;
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
                                       this.SetCmdkey((byte)-85, (byte)18);
                                    } else {
                                       this.SetCmdkey((byte)-85, (byte)19);
                                    }
                                 } else {
                                    var3 = this.mEnhanced_assistance;
                                    if (var1 == var3) {
                                       if (var3.mChecked) {
                                          this.SetCmdkey((byte)-83, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)-83, (byte)1);
                                       }
                                    } else {
                                       var3 = this.mBuzzer;
                                       if (var1 == var3) {
                                          if (var3.mChecked) {
                                             this.SetCmdkey((byte)-90, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)-90, (byte)1);
                                          }
                                       } else {
                                          var3 = this.mRear_maint;
                                          if (var1 == var3) {
                                             if (var3.mChecked) {
                                                this.SetCmdkey((byte)-82, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)-82, (byte)1);
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
            final canbus6settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)-93, (byte)18);
            }
         }, this.Tyre_monitor.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
