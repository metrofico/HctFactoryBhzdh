package com.microntek.controlsettings;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;

public class canbus88settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private final byte[] CMD_DATA = new byte[]{1, 2, 3, 16, 32, 48, 64, 80, 96, 112};
   private int cmdNumber = 0;
   private HCheckBoxPreference mAuto_door;
   private HCheckBoxPreference mAuto_unlock_on;
   private HCheckBoxPreference mAutoclose;
   private HCheckBoxPreference mDaytime;
   private HCheckBoxPreference mDis_trip_b;
   private HCheckBoxPreference mDoor_flash_lights;
   private SwitchboxPreference mEngine_power;
   private HCheckBoxPreference mFlash_lights;
   private HCheckBoxPreference mFuel_saver;
   private Handler mHandler = new Handler(this) {
      final canbus88settings this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (this.this$0.cmdNumber < this.this$0.CMD_DATA.length) {
            canbus88settings var2 = this.this$0;
            var2.SendCanBusCmdData2E((byte)-112, new byte[]{64, var2.CMD_DATA[this.this$0.cmdNumber], 0, 0}, 4);
            this.this$0.mHandler.removeMessages(0);
            this.this$0.mHandler.sendEmptyMessageDelayed(0, 20L);
            canbus88settings.access$008(this.this$0);
         }

      }
   };
   private SwitchboxPreference mHeadlight_off;
   private SwitchboxPreference mHeadlight_off1;
   private HCheckBoxPreference mHill_start;
   private HCheckBoxPreference mHorn_lock;
   private SwitchboxPreference mIlluminated;
   private HCheckBoxPreference mLights_wipers;
   private SwitchboxPreference mMileage_unit;
   private SwitchboxPreference mOil_unit;
   private HCheckBoxPreference mRemote_door;
   private HCheckBoxPreference mShow_time;
   private HCheckBoxPreference mSync_time;
   private SwitchboxPreference mTemp;
   private SwitchboxPreference mTrailer_m;
   private SwitchboxPreference mTrailer_type;
   private SwitchboxPreference mUnit;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   // $FF: synthetic method
   static int access$008(canbus88settings var0) {
      int var1 = var0.cmdNumber++;
      return var1;
   }

   private int getLight(int var1) {
      if (var1 == 90) {
         return 3;
      } else if (var1 == 60) {
         return 2;
      } else {
         return var1 == 30 ? 1 : 0;
      }
   }

   protected void ProcessData(byte[] var1) {
      boolean var4 = false;
      boolean var5 = false;
      boolean var8 = false;
      boolean var3 = false;
      boolean var7 = false;
      boolean var6 = false;
      if (var1[0] == 64 && var1.length >= 5) {
         int var2 = var1[2] & 255;
         StringBuilder var10;
         SwitchboxPreference var11;
         if (var2 != 1) {
            HCheckBoxPreference var9;
            if (var2 != 2) {
               if (var2 != 3) {
                  if (var2 != 16) {
                     StringBuilder var12;
                     SwitchboxPreference var13;
                     if (var2 != 32) {
                        if (var2 != 48) {
                           if (var2 != 64) {
                              if (var2 != 80) {
                                 if (var2 != 96 && var2 == 112) {
                                    var9 = this.mShow_time;
                                    if ((var1[3] & 1) != 0) {
                                       var3 = true;
                                    } else {
                                       var3 = false;
                                    }

                                    var9.setChecked(var3);
                                    var9 = this.mSync_time;
                                    var3 = var6;
                                    if ((var1[3] & 2) != 0) {
                                       var3 = true;
                                    }

                                    var9.setChecked(var3);
                                 }
                              } else {
                                 var11 = this.mTrailer_m;
                                 var10 = new StringBuilder();
                                 var10.append("");
                                 var10.append((var1[3] >> 4 & 7) - 1);
                                 var11.setValue(var10.toString());
                                 var13 = this.mTrailer_type;
                                 var12 = new StringBuilder();
                                 var12.append("");
                                 var12.append(var1[3] & 7);
                                 var13.setValue(var12.toString());
                              }
                           } else {
                              var13 = this.mHeadlight_off1;
                              var12 = new StringBuilder();
                              var12.append("");
                              var12.append(this.getLight(var1[3] & 127));
                              var13.setValue(var12.toString());
                              var11 = this.mEngine_power;
                              var10 = new StringBuilder();
                              var10.append("");
                              var10.append(var1[4] & 127);
                              var11.setValue(var10.toString());
                           }
                        } else {
                           var9 = this.mAuto_door;
                           if ((var1[3] & 1) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var9.setChecked(var3);
                           var9 = this.mAuto_unlock_on;
                           if ((var1[3] & 2) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var9.setChecked(var3);
                           var9 = this.mDoor_flash_lights;
                           if ((var1[3] & 4) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var9.setChecked(var3);
                           var9 = this.mHorn_lock;
                           if ((var1[3] & 8) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var9.setChecked(var3);
                           var9 = this.mRemote_door;
                           if ((var1[4] & 1) != 0) {
                              var3 = true;
                           } else {
                              var3 = false;
                           }

                           var9.setChecked(var3);
                           var9 = this.mAutoclose;
                           var3 = var4;
                           if ((var1[4] & 2) != 0) {
                              var3 = true;
                           }

                           var9.setChecked(var3);
                        }
                     } else {
                        var13 = this.mHeadlight_off;
                        var12 = new StringBuilder();
                        var12.append("");
                        var12.append(this.getLight(var1[3] & 127));
                        var13.setValue(var12.toString());
                        var13 = this.mIlluminated;
                        var12 = new StringBuilder();
                        var12.append("");
                        var12.append(this.getLight(var1[4] & 127));
                        var13.setValue(var12.toString());
                        var9 = this.mLights_wipers;
                        if ((var1[3] & 128) != 0) {
                           var3 = true;
                        } else {
                           var3 = false;
                        }

                        var9.setChecked(var3);
                        var9 = this.mFlash_lights;
                        if ((var1[4] & 128) != 0) {
                           var3 = true;
                        } else {
                           var3 = false;
                        }

                        var9.setChecked(var3);
                        var9 = this.mDaytime;
                        var3 = var5;
                        if ((var1[5] & 1) != 0) {
                           var3 = true;
                        }

                        var9.setChecked(var3);
                     }
                  } else {
                     var9 = this.mHill_start;
                     var3 = var8;
                     if ((var1[3] & 1) != 0) {
                        var3 = true;
                     }

                     var9.setChecked(var3);
                  }
               } else {
                  var9 = this.mDis_trip_b;
                  if ((var1[3] & 1) != 0) {
                     var3 = true;
                  }

                  var9.setChecked(var3);
               }
            } else {
               var9 = this.mFuel_saver;
               var3 = var7;
               if ((var1[3] & 1) != 0) {
                  var3 = true;
               }

               var9.setChecked(var3);
            }
         } else {
            var11 = this.mUnit;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(var1[3] & 1);
            var11.setValue(var10.toString());
            var11 = this.mMileage_unit;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(var1[3] >> 3 & 1);
            var11.setValue(var10.toString());
            var11 = this.mTemp;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(var1[3] >> 4 & 1);
            var11.setValue(var10.toString());
            var11 = this.mOil_unit;
            var10 = new StringBuilder();
            var10.append("");
            var10.append(var1[3] >> 1 & 3);
            var11.setValue(var10.toString());
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492948);
      this.mFuel_saver = (HCheckBoxPreference)this.findPreference("fuel_saver");
      this.mFuel_saver.setOnPreferenceClickListener(this);
      this.mDis_trip_b = (HCheckBoxPreference)this.findPreference("dis_trip_b");
      this.mDis_trip_b.setOnPreferenceClickListener(this);
      this.mHill_start = (HCheckBoxPreference)this.findPreference("hill_start");
      this.mHill_start.setOnPreferenceClickListener(this);
      this.mLights_wipers = (HCheckBoxPreference)this.findPreference("lights_wipers");
      this.mLights_wipers.setOnPreferenceClickListener(this);
      this.mFlash_lights = (HCheckBoxPreference)this.findPreference("flash_lights");
      this.mFlash_lights.setOnPreferenceClickListener(this);
      this.mDaytime = (HCheckBoxPreference)this.findPreference("daytime");
      this.mDaytime.setOnPreferenceClickListener(this);
      this.mAuto_door = (HCheckBoxPreference)this.findPreference("auto_door");
      this.mAuto_door.setOnPreferenceClickListener(this);
      this.mAuto_unlock_on = (HCheckBoxPreference)this.findPreference("auto_unlock_on");
      this.mAuto_unlock_on.setOnPreferenceClickListener(this);
      this.mDoor_flash_lights = (HCheckBoxPreference)this.findPreference("door_flash_lights");
      this.mDoor_flash_lights.setOnPreferenceClickListener(this);
      this.mHorn_lock = (HCheckBoxPreference)this.findPreference("horn_lock");
      this.mHorn_lock.setOnPreferenceClickListener(this);
      this.mRemote_door = (HCheckBoxPreference)this.findPreference("remote_door");
      this.mRemote_door.setOnPreferenceClickListener(this);
      this.mAutoclose = (HCheckBoxPreference)this.findPreference("autoclose");
      this.mAutoclose.setOnPreferenceClickListener(this);
      this.mShow_time = (HCheckBoxPreference)this.findPreference("show_time");
      this.mShow_time.setOnPreferenceClickListener(this);
      this.mSync_time = (HCheckBoxPreference)this.findPreference("sync_time");
      this.mSync_time.setOnPreferenceClickListener(this);
      this.mUnit = (SwitchboxPreference)this.findPreference("unit");
      this.mUnit.setOnPreferenceChangeListener(this);
      this.mMileage_unit = (SwitchboxPreference)this.findPreference("mileage_unit");
      this.mMileage_unit.setOnPreferenceChangeListener(this);
      this.mTemp = (SwitchboxPreference)this.findPreference("temp");
      this.mTemp.setOnPreferenceChangeListener(this);
      this.mOil_unit = (SwitchboxPreference)this.findPreference("oil_unit");
      this.mOil_unit.setOnPreferenceChangeListener(this);
      this.mHeadlight_off = (SwitchboxPreference)this.findPreference("headlight_off");
      this.mHeadlight_off.setOnPreferenceChangeListener(this);
      this.mIlluminated = (SwitchboxPreference)this.findPreference("illuminated");
      this.mIlluminated.setOnPreferenceChangeListener(this);
      this.mHeadlight_off1 = (SwitchboxPreference)this.findPreference("headlight_off1");
      this.mHeadlight_off1.setOnPreferenceChangeListener(this);
      this.mEngine_power = (SwitchboxPreference)this.findPreference("engine_power");
      this.mEngine_power.setOnPreferenceChangeListener(this);
      this.mTrailer_m = (SwitchboxPreference)this.findPreference("trailer_m");
      this.mTrailer_m.setOnPreferenceChangeListener(this);
      this.mTrailer_type = (SwitchboxPreference)this.findPreference("trailer_type");
      this.mTrailer_type.setOnPreferenceChangeListener(this);
      this.mHandler.sendEmptyMessageDelayed(0, 0L);
   }

   protected void onDestroy() {
      this.mHandler.removeCallbacks((Runnable)null);
      super.onDestroy();
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
         if (var1 == this.mUnit) {
            this.SetCmdkey((byte)1, (byte)var4);
         } else if (var1 == this.mMileage_unit) {
            this.SetCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mTemp) {
            this.SetCmdkey((byte)4, (byte)var4);
         } else if (var1 == this.mOil_unit) {
            this.SetCmdkey((byte)5, (byte)var4);
         } else if (var1 == this.mHeadlight_off) {
            this.SetCmdkey((byte)32, (byte)(var4 * 30));
         } else if (var1 == this.mIlluminated) {
            this.SetCmdkey((byte)33, (byte)(var4 * 30));
         } else if (var1 == this.mHeadlight_off1) {
            this.SetCmdkey((byte)64, (byte)(var4 * 30));
         } else if (var1 == this.mEngine_power) {
            this.SetCmdkey((byte)65, (byte)var4);
         } else if (var1 == this.mTrailer_m) {
            this.SetCmdkey((byte)80, (byte)(var4 + 1));
         } else if (var1 == this.mTrailer_type) {
            this.SetCmdkey((byte)81, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mFuel_saver;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)2, (byte)0);
         } else {
            this.SetCmdkey((byte)2, (byte)1);
         }
      } else {
         var2 = this.mDis_trip_b;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)6, (byte)0);
            } else {
               this.SetCmdkey((byte)6, (byte)1);
            }
         } else {
            var2 = this.mHill_start;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)16, (byte)0);
               } else {
                  this.SetCmdkey((byte)16, (byte)1);
               }
            } else {
               var2 = this.mLights_wipers;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)34, (byte)0);
                  } else {
                     this.SetCmdkey((byte)34, (byte)1);
                  }
               } else {
                  var2 = this.mFlash_lights;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)35, (byte)0);
                     } else {
                        this.SetCmdkey((byte)35, (byte)1);
                     }
                  } else {
                     var2 = this.mDaytime;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)36, (byte)0);
                        } else {
                           this.SetCmdkey((byte)36, (byte)1);
                        }
                     } else {
                        var2 = this.mAuto_door;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)48, (byte)0);
                           } else {
                              this.SetCmdkey((byte)48, (byte)1);
                           }
                        } else {
                           var2 = this.mAuto_unlock_on;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)49, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)49, (byte)1);
                              }
                           } else {
                              var2 = this.mDoor_flash_lights;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)50, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)50, (byte)1);
                                 }
                              } else {
                                 var2 = this.mHorn_lock;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)51, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)51, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mRemote_door;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)52, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)52, (byte)1);
                                       }
                                    } else {
                                       var2 = this.mAutoclose;
                                       if (var1 == var2) {
                                          if (var2.mChecked) {
                                             this.SetCmdkey((byte)53, (byte)0);
                                          } else {
                                             this.SetCmdkey((byte)53, (byte)1);
                                          }
                                       } else {
                                          var2 = this.mShow_time;
                                          if (var1 == var2) {
                                             if (var2.mChecked) {
                                                this.SetCmdkey((byte)112, (byte)0);
                                             } else {
                                                this.SetCmdkey((byte)112, (byte)1);
                                             }
                                          } else {
                                             var2 = this.mSync_time;
                                             if (var1 == var2) {
                                                if (var2.mChecked) {
                                                   this.SetCmdkey((byte)113, (byte)0);
                                                } else {
                                                   this.SetCmdkey((byte)113, (byte)1);
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

   protected void onResume() {
      super.onResume();
   }

   protected void onStop() {
      super.onStop();
   }
}
