package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus63settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int data_mLong_power;
   private int data_mOver_speed = 0;
   private int data_mPower_time;
   private PreferenceScreen factory_settings;
   private SwitchboxPreference mAirqualitysensor;
   private HCheckBoxPreference mAngle_auto;
   private HCheckBoxPreference mAngle_manual;
   private SwitchboxPreference mAri_comfortable;
   private HCheckBoxPreference mAuto_heat;
   private SwitchboxPreference mAuto_loop;
   private HCheckBoxPreference mAuto_state;
   private HCheckBoxPreference mAuto_unlock63;
   private HCheckBoxPreference mBack_wiper;
   private HCheckBoxPreference mCar_in_light;
   private HCheckBoxPreference mDriving_lights;
   private HCheckBoxPreference mFront_wiper;
   private HCheckBoxPreference mFu_auto_heat;
   private HCheckBoxPreference mFunction_seat;
   private HCheckBoxPreference mIntelligent_key;
   private HCheckBoxPreference mIntelligent_lock1;
   private HCheckBoxPreference mIntelligent_unlock1;
   private HCheckBoxPreference mIon_mode;
   private HCheckBoxPreference mLamp_steering;
   private HCheckBoxPreference mLeft_side;
   private SwitchboxPreference mLight;
   private OnSwitchPreference mLong_power;
   private SwitchboxPreference mMeter_vol;
   private SwitchboxPreference mMyhome;
   private OnSwitchPreference mOver_speed;
   private OnSwitchPreference mPower_time;
   private HCheckBoxPreference mRear_view;
   private SwitchboxPreference mRemote_unlock;
   private HCheckBoxPreference mRemote_window;
   private HCheckBoxPreference mRight_side;
   private HCheckBoxPreference mSpeed_lock;
   private SwitchboxPreference mSteering_mode;
   private HCheckBoxPreference mUnlock_lock_beep;
   private HCheckBoxPreference mWelcome_light;
   private byte[] setData = new byte[30];

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1, var2}, 2);
   }

   private void updataSettings52() {
      StringBuilder var2 = new StringBuilder();
      var2.append("");
      var2.append((this.setData[1] & 127) - 1);
      String var3 = var2.toString();
      boolean var1;
      if ((this.setData[1] & 1) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      byte[] var4 = this.setData;
      OnSwitchPreference var5;
      StringBuilder var6;
      switch (var4[0] & 255) {
         case 2:
            this.mAuto_state.setChecked(var1);
            break;
         case 3:
            this.mAuto_loop.setValue(var3);
            break;
         case 4:
            this.mAri_comfortable.setValue(var3);
            break;
         case 5:
            this.mAuto_heat.setChecked(var1);
            break;
         case 6:
            this.mFu_auto_heat.setChecked(var1);
            break;
         case 7:
            this.data_mOver_speed = var4[1] & 127;
            var5 = this.mOver_speed;
            var6 = new StringBuilder();
            var6.append("");
            var6.append(this.data_mOver_speed * 10);
            var5.setValue("0", var6.toString());
            break;
         case 8:
            this.mMeter_vol.setValue(var3);
            break;
         case 9:
            this.data_mLong_power = var4[1] & 127;
            var5 = this.mLong_power;
            var6 = new StringBuilder();
            var6.append("");
            var6.append(this.data_mLong_power);
            var5.setValue("0", var6.toString());
            break;
         case 10:
            this.data_mPower_time = var4[1] & 127;
            var5 = this.mPower_time;
            var6 = new StringBuilder();
            var6.append("");
            var6.append(this.data_mPower_time);
            var5.setValue("0", var6.toString());
            break;
         case 11:
            this.mSteering_mode.setValue(var3);
            break;
         case 12:
            this.mRemote_unlock.setValue(var3);
            break;
         case 13:
            this.mSpeed_lock.setChecked(var1);
            break;
         case 14:
            this.mAuto_unlock63.setChecked(var1);
            break;
         case 15:
            this.mRemote_window.setChecked(var1);
            break;
         case 16:
            this.mFront_wiper.setChecked(var1);
            break;
         case 17:
            this.mBack_wiper.setChecked(var1);
            break;
         case 18:
            this.mMyhome.setValue(var3);
            break;
         case 19:
            this.mLamp_steering.setChecked(var1);
            break;
         case 20:
            this.mDriving_lights.setChecked(var1);
            break;
         case 21:
            this.mLight.setValue(var3);
         case 22:
         case 23:
         default:
            break;
         case 24:
            this.mIon_mode.setChecked(var1);
            break;
         case 25:
            this.mFunction_seat.setChecked(var1);
            break;
         case 26:
            this.mIntelligent_key.setChecked(var1);
            break;
         case 27:
            this.mRear_view.setChecked(var1);
            break;
         case 28:
            this.mUnlock_lock_beep.setChecked(var1);
            break;
         case 29:
            this.mIntelligent_lock1.setChecked(var1);
            break;
         case 30:
            this.mIntelligent_unlock1.setChecked(var1);
            break;
         case 31:
            this.mAngle_manual.setChecked(var1);
            break;
         case 32:
            this.mAngle_auto.setChecked(var1);
            break;
         case 33:
            this.mWelcome_light.setChecked(var1);
            break;
         case 34:
            this.mRight_side.setChecked(var1);
            break;
         case 35:
            this.mLeft_side.setChecked(var1);
            break;
         case 36:
            this.mCar_in_light.setChecked(var1);
            break;
         case 37:
            this.mAirqualitysensor.setValue(var3);
      }

   }

   public void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      int var3 = var1.length;
      int var2 = 2;
      if (var3 > 2 && var3 <= 29 && var4 == 82) {
         while(var2 < var3) {
            this.setData[var2 - 2] = var1[var2];
            ++var2;
         }

         this.updataSettings52();
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492935);
      this.mAuto_loop = (SwitchboxPreference)this.findPreference("auto_loop");
      this.mAuto_loop.setOnPreferenceChangeListener(this);
      this.mAri_comfortable = (SwitchboxPreference)this.findPreference("ari_comfortable");
      this.mAri_comfortable.setOnPreferenceChangeListener(this);
      this.mMeter_vol = (SwitchboxPreference)this.findPreference("meter_vol");
      this.mMeter_vol.setOnPreferenceChangeListener(this);
      this.mSteering_mode = (SwitchboxPreference)this.findPreference("steering_mode");
      this.mSteering_mode.setOnPreferenceChangeListener(this);
      this.mRemote_unlock = (SwitchboxPreference)this.findPreference("remote_unlock");
      this.mRemote_unlock.setOnPreferenceChangeListener(this);
      this.mMyhome = (SwitchboxPreference)this.findPreference("myhome");
      this.mMyhome.setOnPreferenceChangeListener(this);
      this.mLight = (SwitchboxPreference)this.findPreference("light");
      this.mLight.setOnPreferenceChangeListener(this);
      this.mOver_speed = (OnSwitchPreference)this.findPreference("over_speed");
      this.mOver_speed.setOnPreferenceChangeListener(this);
      this.mLong_power = (OnSwitchPreference)this.findPreference("long_power");
      this.mLong_power.setOnPreferenceChangeListener(this);
      this.mPower_time = (OnSwitchPreference)this.findPreference("power_time");
      this.mPower_time.setOnPreferenceChangeListener(this);
      this.mAuto_state = (HCheckBoxPreference)this.findPreference("auto_state");
      this.mAuto_state.setOnPreferenceClickListener(this);
      this.mAuto_heat = (HCheckBoxPreference)this.findPreference("auto_heat");
      this.mAuto_heat.setOnPreferenceClickListener(this);
      this.mFu_auto_heat = (HCheckBoxPreference)this.findPreference("fu_auto_heat");
      this.mFu_auto_heat.setOnPreferenceClickListener(this);
      this.mSpeed_lock = (HCheckBoxPreference)this.findPreference("speed_lock");
      this.mSpeed_lock.setOnPreferenceClickListener(this);
      this.mAuto_unlock63 = (HCheckBoxPreference)this.findPreference("auto_unlock63");
      this.mAuto_unlock63.setOnPreferenceClickListener(this);
      this.mRemote_window = (HCheckBoxPreference)this.findPreference("remote_window");
      this.mRemote_window.setOnPreferenceClickListener(this);
      this.mFront_wiper = (HCheckBoxPreference)this.findPreference("front_wiper");
      this.mFront_wiper.setOnPreferenceClickListener(this);
      this.mBack_wiper = (HCheckBoxPreference)this.findPreference("back_wiper");
      this.mBack_wiper.setOnPreferenceClickListener(this);
      this.mLamp_steering = (HCheckBoxPreference)this.findPreference("lamp_steering");
      this.mLamp_steering.setOnPreferenceClickListener(this);
      this.mDriving_lights = (HCheckBoxPreference)this.findPreference("driving_lights");
      this.mDriving_lights.setOnPreferenceClickListener(this);
      this.mIon_mode = (HCheckBoxPreference)this.findPreference("ion_mode");
      this.mIon_mode.setOnPreferenceClickListener(this);
      this.mFunction_seat = (HCheckBoxPreference)this.findPreference("function_seat");
      this.mFunction_seat.setOnPreferenceClickListener(this);
      this.mIntelligent_key = (HCheckBoxPreference)this.findPreference("intelligent_key");
      this.mIntelligent_key.setOnPreferenceClickListener(this);
      this.mRear_view = (HCheckBoxPreference)this.findPreference("rear_view");
      this.mRear_view.setOnPreferenceClickListener(this);
      this.factory_settings = (PreferenceScreen)this.findPreference("factory_settings");
      this.mUnlock_lock_beep = (HCheckBoxPreference)this.findPreference("unlock_lock_beep");
      this.mUnlock_lock_beep.setOnPreferenceClickListener(this);
      this.mIntelligent_lock1 = (HCheckBoxPreference)this.findPreference("intelligent_lock1");
      this.mIntelligent_lock1.setOnPreferenceClickListener(this);
      this.mIntelligent_unlock1 = (HCheckBoxPreference)this.findPreference("intelligent_unlock1");
      this.mIntelligent_unlock1.setOnPreferenceClickListener(this);
      this.mAngle_manual = this.findHCheckBoxPreference("angle_manual");
      this.mAngle_auto = this.findHCheckBoxPreference("angle_auto");
      this.mWelcome_light = this.findHCheckBoxPreference("welcome_light");
      this.mRight_side = this.findHCheckBoxPreference("right_side");
      this.mLeft_side = this.findHCheckBoxPreference("left_side");
      this.mCar_in_light = this.findHCheckBoxPreference("car_in_light");
      this.mAirqualitysensor = this.findSwitchboxPreference("airqualitysensor");
      (new Thread(new Runnable(this) {
         final canbus63settings this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            for(int var1 = 1; var1 <= 37; ++var1) {
               this.this$0.SendCanBusCmdData2E((byte)-112, new byte[]{82, (byte)var1}, 2);

               try {
                  Thread.sleep(50L);
               } catch (Exception var3) {
               }
            }

         }
      })).start();
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mOver_speed;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this.data_mOver_speed;
         if (var4 + var3 < 0 || var4 + var3 > 20 || !this.mOver_speed.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)7, (byte)(this.data_mOver_speed + var3 & 255));
      } else {
         var5 = this.mLong_power;
         if (var1 == var5) {
            var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var4 = this.data_mLong_power;
            if (var4 + var3 < 0 || var4 + var3 > 30 || !this.mLong_power.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)9, (byte)(this.data_mLong_power + var3 & 255));
         } else {
            var5 = this.mPower_time;
            if (var1 == var5) {
               var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
               var4 = this.data_mPower_time;
               if (var4 + var3 < 0 || var4 + var3 > 30 || !this.mPower_time.getIsDow()) {
                  return true;
               }

               this.SetCmdkey((byte)10, (byte)(this.data_mPower_time + var3 & 255));
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
         if (var1 == this.mAuto_loop) {
            this.SetCmdkey((byte)3, (byte)(var4 + 1));
         } else if (var1 == this.mAri_comfortable) {
            this.SetCmdkey((byte)4, (byte)(var4 + 1));
         } else if (var1 == this.mMeter_vol) {
            this.SetCmdkey((byte)8, (byte)(var4 + 1));
         } else if (var1 == this.mSteering_mode) {
            this.SetCmdkey((byte)11, (byte)(var4 + 1));
         } else if (var1 == this.mRemote_unlock) {
            this.SetCmdkey((byte)12, (byte)(var4 + 1));
         } else if (var1 == this.mMyhome) {
            this.SetCmdkey((byte)18, (byte)(var4 + 1));
         } else if (var1 == this.mLight) {
            this.SetCmdkey((byte)21, (byte)(var4 + 1));
         } else if (var1 == this.mAirqualitysensor) {
            this.SetCmdkey((byte)37, (byte)(var4 + 1));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mAuto_state;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)2, (byte)2);
         } else {
            this.SetCmdkey((byte)2, (byte)1);
         }
      } else {
         var2 = this.mAuto_heat;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)5, (byte)2);
            } else {
               this.SetCmdkey((byte)5, (byte)1);
            }
         } else {
            var2 = this.mFu_auto_heat;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)6, (byte)2);
               } else {
                  this.SetCmdkey((byte)6, (byte)1);
               }
            } else {
               var2 = this.mSpeed_lock;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)13, (byte)2);
                  } else {
                     this.SetCmdkey((byte)13, (byte)1);
                  }
               } else {
                  var2 = this.mAuto_unlock63;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)14, (byte)2);
                     } else {
                        this.SetCmdkey((byte)14, (byte)1);
                     }
                  } else {
                     var2 = this.mRemote_window;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)15, (byte)2);
                        } else {
                           this.SetCmdkey((byte)15, (byte)1);
                        }
                     } else {
                        var2 = this.mFront_wiper;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)16, (byte)2);
                           } else {
                              this.SetCmdkey((byte)16, (byte)1);
                           }
                        } else {
                           var2 = this.mBack_wiper;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)17, (byte)2);
                              } else {
                                 this.SetCmdkey((byte)17, (byte)1);
                              }
                           } else {
                              var2 = this.mLamp_steering;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)19, (byte)2);
                                 } else {
                                    this.SetCmdkey((byte)19, (byte)1);
                                 }
                              } else {
                                 var2 = this.mDriving_lights;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)20, (byte)2);
                                    } else {
                                       this.SetCmdkey((byte)20, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mIon_mode;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)24, (byte)2);
                                       } else {
                                          this.SetCmdkey((byte)24, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mFunction_seat;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)25, (byte)2);
                                          } else {
                                             this.SetCmdkey((byte)25, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mIntelligent_key;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)26, (byte)2);
                                             } else {
                                                this.SetCmdkey((byte)26, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mRear_view;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)27, (byte)2);
                                                } else {
                                                   this.SetCmdkey((byte)27, (byte)1);
                                                }
                                             } else {
                                                var2 = this.mUnlock_lock_beep;
                                                if (var1 == var2) {
                                                   if (var2.mChecked) {
                                                      this.SetCmdkey((byte)28, (byte)2);
                                                   } else {
                                                      this.SetCmdkey((byte)28, (byte)1);
                                                   }
                                                } else {
                                                   var2 = this.mIntelligent_lock1;
                                                   if (var1 == var2) {
                                                      if (var2.mChecked) {
                                                         this.SetCmdkey((byte)29, (byte)2);
                                                      } else {
                                                         this.SetCmdkey((byte)29, (byte)1);
                                                      }
                                                   } else {
                                                      var2 = this.mIntelligent_unlock1;
                                                      if (var1 == var2) {
                                                         if (var2.mChecked) {
                                                            this.SetCmdkey((byte)30, (byte)2);
                                                         } else {
                                                            this.SetCmdkey((byte)30, (byte)1);
                                                         }
                                                      } else {
                                                         var2 = this.mAngle_manual;
                                                         if (var1 == var2) {
                                                            if (var2.mChecked) {
                                                               this.SetCmdkey((byte)31, (byte)2);
                                                            } else {
                                                               this.SetCmdkey((byte)31, (byte)1);
                                                            }
                                                         } else {
                                                            var2 = this.mAngle_auto;
                                                            if (var1 == var2) {
                                                               if (var2.mChecked) {
                                                                  this.SetCmdkey((byte)32, (byte)2);
                                                               } else {
                                                                  this.SetCmdkey((byte)32, (byte)1);
                                                               }
                                                            } else {
                                                               var2 = this.mWelcome_light;
                                                               if (var1 == var2) {
                                                                  if (var2.mChecked) {
                                                                     this.SetCmdkey((byte)33, (byte)2);
                                                                  } else {
                                                                     this.SetCmdkey((byte)33, (byte)1);
                                                                  }
                                                               } else {
                                                                  var2 = this.mRight_side;
                                                                  if (var1 == var2) {
                                                                     if (var2.mChecked) {
                                                                        this.SetCmdkey((byte)34, (byte)2);
                                                                     } else {
                                                                        this.SetCmdkey((byte)34, (byte)1);
                                                                     }
                                                                  } else {
                                                                     var2 = this.mLeft_side;
                                                                     if (var1 == var2) {
                                                                        if (var2.mChecked) {
                                                                           this.SetCmdkey((byte)35, (byte)2);
                                                                        } else {
                                                                           this.SetCmdkey((byte)35, (byte)1);
                                                                        }
                                                                     } else {
                                                                        var2 = this.mCar_in_light;
                                                                        if (var1 == var2) {
                                                                           if (var2.mChecked) {
                                                                              this.SetCmdkey((byte)36, (byte)2);
                                                                           } else {
                                                                              this.SetCmdkey((byte)36, (byte)1);
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
               }
            }
         }
      }

      return false;
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.factory_settings) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus63settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)0, (byte)-1);
            }
         }, this.factory_settings.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
