package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus103settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Car_in_light = 0;
   private int _Front_light = 1;
   public int canbustype_items = 0;
   private SwitchboxPreference mB_parksense;
   private HCheckBoxPreference mBeep_lock;
   private HCheckBoxPreference mBrake_service;
   private OnSwitchPreference mCar_in_light;
   private OnSwitchPreference mFront_light;
   private SwitchboxPreference mHeadlights_off;
   private SwitchboxPreference mKey_unlock;
   private HCheckBoxPreference mKeyless_entry;
   private HCheckBoxPreference mLights_flash;
   private SwitchboxPreference mLock_beep;
   private SwitchboxPreference mMileage_unit;
   private SwitchboxPreference mOil_unit;
   private HCheckBoxPreference mOutlock;
   private HCheckBoxPreference mParkView;
   private HCheckBoxPreference mParking_brake;
   private HCheckBoxPreference mParking_delays;
   private SwitchboxPreference mParksense;
   private HCheckBoxPreference mRemote_beep;
   private HCheckBoxPreference mRunning_lights;
   private SwitchboxPreference mTemp;
   private SwitchboxPreference mTireunit;
   private HCheckBoxPreference mTurn_lights_set;
   private HCheckBoxPreference mUnlock_driving;
   private HCheckBoxPreference mUnlock_light;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)68, new byte[]{var1, var2}, 2);
   }

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData5AA5(var1, new byte[]{var2, var3}, 2);
   }

   private void removePreference(Preference var1, boolean var2) {
      if (!var2) {
         this.getPreferenceScreen().removePreference(var1);
      } else {
         this.getPreferenceScreen().addPreference(var1);
      }

   }

   protected void ProcessData(byte[] var1) {
      boolean var6 = false;
      boolean var5 = false;
      boolean var4 = false;
      int var2 = var1[0] & 255;
      if (var1.length > 2) {
         boolean var3;
         SwitchboxPreference var7;
         StringBuilder var8;
         byte var10;
         HCheckBoxPreference var13;
         if (var2 != 67) {
            if (var2 != 96) {
               StringBuilder var11;
               SwitchboxPreference var12;
               if (var2 != 98) {
                  if (var2 == 193) {
                     var10 = var1[2];
                     var7 = this.mMileage_unit;
                     var8 = new StringBuilder();
                     var8.append("");
                     var8.append((var10 >> 4 & 3) - 1);
                     var7.setValue(var8.toString());
                     var10 = var1[3];
                     var12 = this.mTemp;
                     var11 = new StringBuilder();
                     var11.append("");
                     var11.append((var10 >> 0 & 3) - 1);
                     var12.setValue(var11.toString());
                     var10 = var1[4];
                     var12 = this.mOil_unit;
                     var11 = new StringBuilder();
                     var11.append("");
                     var11.append((var10 >> 0 & 7) - 1);
                     var12.setValue(var11.toString());
                     var10 = var1[2];
                     var7 = this.mTireunit;
                     StringBuilder var9 = new StringBuilder();
                     var9.append("");
                     var9.append(var10 >> 0 & 3);
                     var7.setValue(var9.toString());
                  }
               } else {
                  var10 = var1[4];
                  var12 = this.mHeadlights_off;
                  var11 = new StringBuilder();
                  var11.append("");
                  var11.append(var10 & 3);
                  var12.setValue(var11.toString());
                  var7 = this.mHeadlights_off;
                  if ((var1[2] & 1) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  this.removePreference(var7, var3);
                  var13 = this.mRunning_lights;
                  if ((var1[5] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var13.setChecked(var3);
                  var13 = this.mRunning_lights;
                  if ((var1[3] & 4) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  this.removePreference(var13, var3);
                  var13 = this.mLights_flash;
                  if ((var1[5] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var13.setChecked(var3);
                  var13 = this.mLights_flash;
                  if ((var1[3] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  this.removePreference(var13, var3);
                  this._Front_light = var1[4] >> 6 & 3;
                  OnSwitchPreference var14 = this.mFront_light;
                  var11 = new StringBuilder();
                  var11.append("");
                  var11.append(this._Front_light);
                  var14.setValue("0", var11.toString());
                  OnSwitchPreference var15 = this.mFront_light;
                  if ((var1[2] & 32) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  this.removePreference(var15, var3);
                  var13 = this.mUnlock_light;
                  if ((var1[5] & 4) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var13.setChecked(var3);
                  var13 = this.mUnlock_light;
                  if ((var1[2] & 8) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  this.removePreference(var13, var3);
                  var13 = this.mTurn_lights_set;
                  if ((var1[3] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  var13.setChecked(var3);
                  var13 = this.mTurn_lights_set;
                  if ((var1[2] & 16) != 0) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  this.removePreference(var13, var3);
                  this._Car_in_light = var1[5] >> 5 & 3;
                  var14 = this.mCar_in_light;
                  var11 = new StringBuilder();
                  var11.append("");
                  var11.append(this._Car_in_light);
                  var14.setValue("0", var11.toString());
                  var15 = this.mCar_in_light;
                  var3 = var4;
                  if ((var1[2] & 64) != 0) {
                     var3 = true;
                  }

                  this.removePreference(var15, var3);
               }
            } else {
               var13 = this.mOutlock;
               if ((var1[3] & 1) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var13.setChecked(var3);
               var13 = this.mOutlock;
               if ((var1[2] & 1) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               this.removePreference(var13, var3);
               var13 = this.mUnlock_driving;
               if ((var1[3] & 2) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var13.setChecked(var3);
               var13 = this.mUnlock_driving;
               if ((var1[2] & 2) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               this.removePreference(var13, var3);
               var10 = var1[3];
               var7 = this.mKey_unlock;
               var8 = new StringBuilder();
               var8.append("");
               var8.append(var10 >> 2 & 1);
               var7.setValue(var8.toString());
               var7 = this.mKey_unlock;
               if ((var1[2] & 4) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               this.removePreference(var7, var3);
               var13 = this.mKeyless_entry;
               if ((var1[3] & 8) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var13.setChecked(var3);
               var13 = this.mKeyless_entry;
               if ((var1[2] & 8) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               this.removePreference(var13, var3);
               var10 = var1[3];
               var7 = this.mLock_beep;
               var8 = new StringBuilder();
               var8.append("");
               var8.append(var10 >> 5 & 3);
               var7.setValue(var8.toString());
               var7 = this.mLock_beep;
               if ((var1[2] & 32) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               this.removePreference(var7, var3);
               var13 = this.mRemote_beep;
               if ((var1[3] & 128) != 0) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var13.setChecked(var3);
               var13 = this.mRemote_beep;
               var3 = var6;
               if ((var1[2] & 64) != 0) {
                  var3 = true;
               }

               this.removePreference(var13, var3);
            }
         } else {
            var10 = var1[5];
            var7 = this.mParksense;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(var10 >> 6 & 1);
            var7.setValue(var8.toString());
            var7 = this.mParksense;
            if ((var1[2] & 16) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            this.removePreference(var7, var3);
            var13 = this.mParkView;
            if ((var1[6] & 8) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var13.setChecked(var3);
            var13 = this.mParkView;
            if ((var1[3] & 4) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            this.removePreference(var13, var3);
            var13 = this.mParking_delays;
            if ((var1[6] & 16) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var13.setChecked(var3);
            var13 = this.mParking_delays;
            if ((var1[3] & 8) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            this.removePreference(var13, var3);
            var13 = this.mParking_brake;
            if ((var1[6] & 128) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var13.setChecked(var3);
            var13 = this.mParking_brake;
            if ((var1[3] & 64) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            this.removePreference(var13, var3);
            var10 = var1[7];
            var7 = this.mB_parksense;
            var8 = new StringBuilder();
            var8.append("");
            var8.append(var10 >> 2 & 3);
            var7.setValue(var8.toString());
            var7 = this.mB_parksense;
            if ((var1[4] & 2) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            this.removePreference(var7, var3);
            var13 = this.mBrake_service;
            if ((var1[7] & 32) != 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var13.setChecked(var3);
            var13 = this.mBrake_service;
            var3 = var5;
            if ((var1[4] & 8) != 0) {
               var3 = true;
            }

            this.removePreference(var13, var3);
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492865);
      this.mParkView = (HCheckBoxPreference)this.findPreference("parkView");
      this.mParkView.setOnPreferenceClickListener(this);
      this.mRunning_lights = (HCheckBoxPreference)this.findPreference("running_lights");
      this.mRunning_lights.setOnPreferenceClickListener(this);
      this.mLights_flash = (HCheckBoxPreference)this.findPreference("lights_flash");
      this.mLights_flash.setOnPreferenceClickListener(this);
      this.mParking_brake = (HCheckBoxPreference)this.findPreference("parking_brake");
      this.mParking_brake.setOnPreferenceClickListener(this);
      this.mUnlock_driving = (HCheckBoxPreference)this.findPreference("unlock_driving");
      this.mUnlock_driving.setOnPreferenceClickListener(this);
      this.mKeyless_entry = (HCheckBoxPreference)this.findPreference("keyless_entry");
      this.mKeyless_entry.setOnPreferenceClickListener(this);
      this.mTurn_lights_set = (HCheckBoxPreference)this.findPreference("turn_lights_set");
      this.mTurn_lights_set.setOnPreferenceClickListener(this);
      this.mUnlock_light = (HCheckBoxPreference)this.findPreference("unlock_light");
      this.mUnlock_light.setOnPreferenceClickListener(this);
      this.mParking_delays = (HCheckBoxPreference)this.findPreference("parking_delays");
      this.mParking_delays.setOnPreferenceClickListener(this);
      this.mOutlock = (HCheckBoxPreference)this.findPreference("outlock");
      this.mOutlock.setOnPreferenceClickListener(this);
      this.mRemote_beep = (HCheckBoxPreference)this.findPreference("remote_beep");
      this.mRemote_beep.setOnPreferenceClickListener(this);
      this.mParksense = (SwitchboxPreference)this.findPreference("parksense");
      this.mParksense.setOnPreferenceChangeListener(this);
      this.mB_parksense = (SwitchboxPreference)this.findPreference("b_parksense");
      this.mB_parksense.setOnPreferenceChangeListener(this);
      this.mHeadlights_off = (SwitchboxPreference)this.findPreference("headlights_off");
      this.mHeadlights_off.setOnPreferenceChangeListener(this);
      this.mKey_unlock = (SwitchboxPreference)this.findPreference("key_unlock");
      this.mKey_unlock.setOnPreferenceChangeListener(this);
      this.mTireunit = (SwitchboxPreference)this.findPreference("tireunit");
      this.mTireunit.setOnPreferenceChangeListener(this);
      this.mLock_beep = (SwitchboxPreference)this.findPreference("lock_beep");
      this.mLock_beep.setOnPreferenceChangeListener(this);
      this.mMileage_unit = (SwitchboxPreference)this.findPreference("mileage_unit");
      this.mMileage_unit.setOnPreferenceChangeListener(this);
      this.mTemp = (SwitchboxPreference)this.findPreference("temp");
      this.mTemp.setOnPreferenceChangeListener(this);
      this.mOil_unit = (SwitchboxPreference)this.findPreference("oil_unit");
      this.mOil_unit.setOnPreferenceChangeListener(this);
      this.mTireunit = (SwitchboxPreference)this.findPreference("tireunit");
      this.mTireunit.setOnPreferenceChangeListener(this);
      this.mBrake_service = (HCheckBoxPreference)this.findPreference("brake_service");
      this.mBrake_service.setOnPreferenceClickListener(this);
      this.mCar_in_light = (OnSwitchPreference)this.findPreference("car_in_light");
      this.mCar_in_light.setOnPreferenceChangeListener(this);
      this.mFront_light = (OnSwitchPreference)this.findPreference("front_light");
      this.mFront_light.setOnPreferenceChangeListener(this);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 97}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 96}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, 67}, 3);
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, -63}, 3);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mCar_in_light;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var3 = this._Car_in_light;
         if (var3 + var4 < 0 || var3 + var4 > 6 || !this.mCar_in_light.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)99, (byte)11, (byte)(this._Car_in_light + var4 & 255));
      } else {
         var5 = this.mFront_light;
         if (var1 == var5) {
            var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var4 = this._Front_light;
            if (var4 + var3 < 1 || var4 + var3 > 3 || !this.mFront_light.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)99, (byte)8, (byte)(this._Front_light + var3 & 255));
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
         if (var1 == this.mParksense) {
            this.SetCmdkey((byte)5, (byte)(var4 + 0));
         } else if (var1 == this.mB_parksense) {
            this.SetCmdkey((byte)14, (byte)var4);
         } else if (var1 == this.mHeadlights_off) {
            this.SetCmdkey((byte)99, (byte)1, (byte)var4);
         } else if (var1 == this.mKey_unlock) {
            this.SetCmdkey((byte)97, (byte)3, (byte)(var4 + 0));
         } else if (var1 == this.mLock_beep) {
            this.SetCmdkey((byte)97, (byte)6, (byte)(var4 + 0));
         } else if (var1 == this.mMileage_unit) {
            this.SetCmdkey((byte)-54, (byte)1, (byte)(var4 + 1));
         } else if (var1 == this.mTemp) {
            this.SetCmdkey((byte)-54, (byte)3, (byte)(var4 + 1));
         } else if (var1 == this.mOil_unit) {
            this.SetCmdkey((byte)-54, (byte)5, (byte)(var4 + 1));
         } else if (var1 == this.mTireunit) {
            this.SetCmdkey((byte)-54, (byte)6, (byte)(var4 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mParkView;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)7, (byte)0);
         } else {
            this.SetCmdkey((byte)7, (byte)1);
         }
      } else {
         var2 = this.mRunning_lights;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)99, (byte)5, (byte)0);
            } else {
               this.SetCmdkey((byte)99, (byte)5, (byte)1);
            }
         } else {
            var2 = this.mLights_flash;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)99, (byte)6, (byte)0);
               } else {
                  this.SetCmdkey((byte)99, (byte)6, (byte)1);
               }
            } else {
               var2 = this.mUnlock_driving;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)97, (byte)2, (byte)0);
                  } else {
                     this.SetCmdkey((byte)97, (byte)2, (byte)1);
                  }
               } else {
                  var2 = this.mBeep_lock;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)55, (byte)0);
                     } else {
                        this.SetCmdkey((byte)55, (byte)1);
                     }
                  } else {
                     var2 = this.mKeyless_entry;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)97, (byte)4, (byte)0);
                        } else {
                           this.SetCmdkey((byte)97, (byte)4, (byte)1);
                        }
                     } else if (var1 == this.mBrake_service) {
                        this.SetCmdkey((byte)16, (byte)1);
                     } else {
                        var2 = this.mParking_brake;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)12, (byte)0);
                           } else {
                              this.SetCmdkey((byte)12, (byte)1);
                           }
                        } else {
                           var2 = this.mTurn_lights_set;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)99, (byte)10, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)99, (byte)10, (byte)1);
                              }
                           } else {
                              var2 = this.mUnlock_light;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)99, (byte)9, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)99, (byte)9, (byte)1);
                                 }
                              } else {
                                 var2 = this.mParking_delays;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)9, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)9, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mOutlock;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)97, (byte)1, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)97, (byte)1, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mRemote_beep;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)97, (byte)7, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)97, (byte)7, (byte)1);
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
}
