package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;

public class canbus82settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _mInstrument_light = 0;
   private HCheckBoxPreference mAir_loop;
   private HCheckBoxPreference mAuto_cabin;
   private HCheckBoxPreference mBack_parking;
   private HCheckBoxPreference mBlind_zone;
   private HCheckBoxPreference mCar_start;
   private HCheckBoxPreference mDoor_aut;
   private HCheckBoxPreference mExternal_welcome;
   private HCheckBoxPreference mFront_parking;
   private HCheckBoxPreference mIndicator;
   private SwitchboxPreference mInstrument_color;
   private OnSwitchPreference mInstrument_light;
   private SwitchboxPreference mInstrument_style;
   private SwitchboxPreference mIon_generator;
   private PreferenceScreen mLanguage_dialog;
   private HCheckBoxPreference mLateral_parking;
   private SwitchboxPreference mParking_system;
   private SwitchboxPreference mPrompt_volume;
   private PreferenceScreen mUser_reset;
   private HCheckBoxPreference mWiper_back;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var3 = false;
      if (var1[0] == 113) {
         HCheckBoxPreference var4 = this.mFront_parking;
         boolean var2;
         if ((var1[2] & 2) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mBack_parking;
         if ((var1[2] & 1) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mAir_loop;
         if ((var1[3] & 8) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         SwitchboxPreference var6 = this.mIon_generator;
         StringBuilder var5 = new StringBuilder();
         var5.append("");
         var5.append(var1[3] & 3);
         var6.setValue(var5.toString());
         var4 = this.mCar_start;
         if ((var1[3] & 4) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mDoor_aut;
         if ((var1[4] & 4) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var6 = this.mPrompt_volume;
         var5 = new StringBuilder();
         var5.append("");
         var5.append(var1[4] & 3);
         var6.setValue(var5.toString());
         var4 = this.mExternal_welcome;
         if ((var1[4] & 16) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mAuto_cabin;
         if ((var1[4] & 32) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mWiper_back;
         if ((var1[4] & 64) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mIndicator;
         if ((var1[4] & 128) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var6 = this.mInstrument_style;
         var5 = new StringBuilder();
         var5.append("");
         var5.append((7 & var1[7]) - 1);
         var6.setValue(var5.toString());
         this._mInstrument_light = var1[8] & 255;
         OnSwitchPreference var7 = this.mInstrument_light;
         StringBuilder var9 = new StringBuilder();
         var9.append("");
         var9.append(this._mInstrument_light);
         var7.setValue("0", var9.toString());
         var4 = this.mBlind_zone;
         if ((var1[2] & 8) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mLateral_parking;
         var2 = var3;
         if ((var1[2] & 4) != 0) {
            var2 = true;
         }

         var4.setChecked(var2);
         SwitchboxPreference var8 = this.mParking_system;
         var9 = new StringBuilder();
         var9.append("");
         var9.append(var1[2] >> 4 & 3);
         var8.setValue(var9.toString());
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492944);
      this.mFront_parking = (HCheckBoxPreference)this.findPreference("front_parking");
      this.mFront_parking.setOnPreferenceClickListener(this);
      this.mBack_parking = (HCheckBoxPreference)this.findPreference("back_parking");
      this.mBack_parking.setOnPreferenceClickListener(this);
      this.mAir_loop = (HCheckBoxPreference)this.findPreference("air_loop");
      this.mAir_loop.setOnPreferenceClickListener(this);
      this.mCar_start = (HCheckBoxPreference)this.findPreference("car_start");
      this.mCar_start.setOnPreferenceClickListener(this);
      this.mDoor_aut = (HCheckBoxPreference)this.findPreference("door_aut");
      this.mDoor_aut.setOnPreferenceClickListener(this);
      this.mExternal_welcome = (HCheckBoxPreference)this.findPreference("external_welcome");
      this.mExternal_welcome.setOnPreferenceClickListener(this);
      this.mAuto_cabin = (HCheckBoxPreference)this.findPreference("auto_cabin");
      this.mAuto_cabin.setOnPreferenceClickListener(this);
      this.mWiper_back = (HCheckBoxPreference)this.findPreference("wiper_back");
      this.mWiper_back.setOnPreferenceClickListener(this);
      this.mIndicator = (HCheckBoxPreference)this.findPreference("indicator");
      this.mIndicator.setOnPreferenceClickListener(this);
      this.mBlind_zone = (HCheckBoxPreference)this.findPreference("blind_zone");
      this.mBlind_zone.setOnPreferenceClickListener(this);
      this.mLateral_parking = (HCheckBoxPreference)this.findPreference("lateral_parking");
      this.mLateral_parking.setOnPreferenceClickListener(this);
      this.mIon_generator = (SwitchboxPreference)this.findPreference("ion_generator");
      this.mIon_generator.setOnPreferenceChangeListener(this);
      this.mPrompt_volume = (SwitchboxPreference)this.findPreference("prompt_volume");
      this.mPrompt_volume.setOnPreferenceChangeListener(this);
      this.mInstrument_color = (SwitchboxPreference)this.findPreference("instrument_color");
      this.mInstrument_color.setOnPreferenceChangeListener(this);
      int var2 = System.getInt(this.getContentResolver(), "mInstrument_color", 0);
      SwitchboxPreference var4 = this.mInstrument_color;
      StringBuilder var3 = new StringBuilder();
      var3.append("");
      var3.append(var2);
      var4.setValue(var3.toString());
      this.mInstrument_style = (SwitchboxPreference)this.findPreference("instrument_style");
      this.mInstrument_style.setOnPreferenceChangeListener(this);
      this.mParking_system = (SwitchboxPreference)this.findPreference("parking_system");
      this.mParking_system.setOnPreferenceChangeListener(this);
      this.mInstrument_light = (OnSwitchPreference)this.findPreference("instrument_light");
      this.mInstrument_light.setOnPreferenceChangeListener(this);
      this.mLanguage_dialog = (PreferenceScreen)this.findPreference("language_dialog");
      this.mUser_reset = (PreferenceScreen)this.findPreference("user_reset");
      this.SetCmdkey((byte)0, (byte)1);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mInstrument_light;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) * 5 + 0;
         var4 = this._mInstrument_light;
         if (var4 + var3 < 0 || var4 + var3 > 100 || !this.mInstrument_light.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)15, (byte)(this._mInstrument_light + var3 & 255));
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
         if (var1 == this.mIon_generator) {
            this.SetCmdkey((byte)4, (byte)var3);
         } else if (var1 == this.mPrompt_volume) {
            this.SetCmdkey((byte)7, (byte)var3);
         } else if (var1 == this.mInstrument_color) {
            this.SetCmdkey((byte)16, (byte)(var3 + 1));
            SwitchboxPreference var9 = this.mInstrument_color;
            StringBuilder var8 = new StringBuilder();
            var8.append("");
            var8.append(var3);
            var9.setValue(var8.toString());
            System.putInt(this.getContentResolver(), "mInstrument_color", var3);
         } else if (var1 == this.mInstrument_style) {
            this.SetCmdkey((byte)14, (byte)(var3 + 1));
         } else if (var1 == this.mParking_system) {
            this.SetCmdkey((byte)19, (byte)(var3 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mFront_parking;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)1, (byte)1);
         }
      } else {
         var2 = this.mBack_parking;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)2, (byte)0);
            } else {
               this.SetCmdkey((byte)2, (byte)1);
            }
         } else {
            var2 = this.mAir_loop;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)3, (byte)0);
               } else {
                  this.SetCmdkey((byte)3, (byte)1);
               }
            } else {
               var2 = this.mCar_start;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)5, (byte)0);
                  } else {
                     this.SetCmdkey((byte)5, (byte)1);
                  }
               } else {
                  var2 = this.mDoor_aut;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)6, (byte)0);
                     } else {
                        this.SetCmdkey((byte)6, (byte)1);
                     }
                  } else {
                     var2 = this.mExternal_welcome;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)9, (byte)0);
                        } else {
                           this.SetCmdkey((byte)9, (byte)1);
                        }
                     } else {
                        var2 = this.mAuto_cabin;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)10, (byte)0);
                           } else {
                              this.SetCmdkey((byte)10, (byte)1);
                           }
                        } else {
                           var2 = this.mWiper_back;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)11, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)11, (byte)1);
                              }
                           } else {
                              var2 = this.mIndicator;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)12, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)12, (byte)1);
                                 }
                              } else {
                                 var2 = this.mBlind_zone;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)17, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)17, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mLateral_parking;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)18, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)18, (byte)1);
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
      if (var2 == this.mLanguage_dialog) {
         this.SetCmdkey((byte)13, (byte)-127);
      } else if (var2 == this.mUser_reset) {
         this.SetCmdkey((byte)-128, (byte)2);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
