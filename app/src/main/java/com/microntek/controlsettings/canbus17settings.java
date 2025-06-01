package com.microntek.controlsettings;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus17settings extends BasePreferenceActivity {
   private byte[] InitReqData = new byte[]{0, 16, 32, 48, 49, 64, 80, 81, 82, 96, 112, -128, -112, -96, -95, -80, -64, -1};
   private SwitchboxPreference mAccrdriveprogram;
   private SwitchboxPreference mAccvehicle;
   private HCheckBoxPreference mAcousticcon;
   private SwitchboxPreference mActionstate;
   private HCheckBoxPreference mActivation;
   private HCheckBoxPreference mAdaptiv;
   private HCheckBoxPreference mAir_switch;
   private SwitchboxPreference mAll_lights;
   private HCheckBoxPreference mAqs_auto;
   private HCheckBoxPreference mAutoaction;
   private HCheckBoxPreference mAutolock;
   private SwitchboxPreference mAutomatic_air;
   private HCheckBoxPreference mAutorain;
   private HCheckBoxPreference mAutorunlights;
   private HCheckBoxPreference mAverageapeed;
   private HCheckBoxPreference mAveragefuel;
   private HCheckBoxPreference mBlind_detection;
   private SwitchboxPreference mBrightness;
   private SwitchboxPreference mCentrallock;
   private HCheckBoxPreference mComfort;
   private SwitchboxPreference mContrast;
   private HCheckBoxPreference mCross_follow;
   private HCheckBoxPreference mCurrentfuel;
   private HCheckBoxPreference mDigitalspeed;
   private SwitchboxPreference mDirect_tire;
   private SwitchboxPreference mDoor_light;
   private SwitchboxPreference mDoorloight;
   private HCheckBoxPreference mDynamic_light;
   private HCheckBoxPreference mFatigueystem;
   private SwitchboxPreference mFeet_light;
   private SwitchboxPreference mFootlight;
   private SwitchboxPreference mFront_light;
   private HCheckBoxPreference mFrontasssystem;
   private SwitchboxPreference mFrontpitch;
   private SwitchboxPreference mFrontvol;
   private SwitchboxPreference mFulecons;
   private Handler mHandler = new Handler(this) {
      final canbus17settings this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         canbus17settings var2 = this.this$0;
         var2.RequestCmdkey((byte)64, var2.InitReqData[canbus17settings.access$108(this.this$0)]);
         if (this.this$0.mInitCnt < this.this$0.InitReqData.length) {
            this.this$0.mHandler.sendEmptyMessageDelayed(0, 80L);
         } else {
            this.this$0.RequestCmdkey((byte)37, (byte)0);
         }

      }
   };
   private SwitchboxPreference mHeadlight_range;
   private SwitchboxPreference mHomeinmode;
   private SwitchboxPreference mHomeoutmode;
   private HCheckBoxPreference mInductive_rear;
   private int mInitCnt = 0;
   private HCheckBoxPreference mInterior_monitor;
   private HCheckBoxPreference mLanesystem;
   private SwitchboxPreference mLangauage;
   private HCheckBoxPreference mLastdistance;
   private SwitchboxPreference mLight;
   private HCheckBoxPreference mMileage;
   private HCheckBoxPreference mMirrorlower;
   private HCheckBoxPreference mMirrorsyncadj;
   private SwitchboxPreference mMiunit;
   private SwitchboxPreference mMode;
   private HCheckBoxPreference mOil;
   private HCheckBoxPreference mParking;
   private HCheckBoxPreference mRadar_sound;
   private SwitchboxPreference mRearpitch;
   private HCheckBoxPreference mRearrain;
   private SwitchboxPreference mRearvol;
   private SwitchboxPreference mRoof_light;
   private HCheckBoxPreference mRunning_lights;
   private SwitchboxPreference mSaturation;
   private HCheckBoxPreference mShowdistancecontrol;
   private int mSpeedUnit = -1;
   private HCheckBoxPreference mSpeedalarm;
   private HCheckBoxPreference mSpeedalarm1;
   private SwitchboxPreference mSpeeddata;
   private SwitchboxPreference mSpeedunits;
   private SwitchboxPreference mSwitchlight;
   private SwitchboxPreference mTemperature;
   private HCheckBoxPreference mTipeconomy;
   private SwitchboxPreference mTireunit;
   private PreferenceScreen mTpms;
   private HCheckBoxPreference mTraveltime;
   private SwitchboxPreference mTravlemode;
   private HCheckBoxPreference mTurnlights;
   private SwitchboxPreference mTurnontime;
   private SwitchboxPreference mVolume;
   private HCheckBoxPreference mWarning;
   private SwitchboxPreference mWindowsopen;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-112, new byte[]{var1, var2}, 2);
   }

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   // $FF: synthetic method
   static int access$108(canbus17settings var0) {
      int var1 = var0.mInitCnt++;
      return var1;
   }

   private void updateSpeedEntries(int var1) {
      if (var1 != this.mSpeedUnit) {
         this.mSpeedUnit = var1;
         byte var7;
         if (this.mSpeedUnit == 0) {
            var7 = 22;
         } else {
            var7 = 27;
         }

         CharSequence[] var4 = new CharSequence[var7];
         CharSequence[] var5 = new CharSequence[var4.length];
         int var3 = this.mSpeedUnit;
         var1 = 0;
         byte var2 = 0;
         StringBuilder var6;
         if (var3 == 0) {
            for(var1 = var2; var1 < var4.length; ++var1) {
               var5[var1] = Integer.toString(var1 * 10 + 30);
               var6 = new StringBuilder();
               var6.append(var5[var1]);
               var6.append("km/h");
               var4[var1] = var6.toString();
            }
         } else {
            while(var1 < var4.length) {
               var5[var1] = Integer.toString(var1 * 5 + 20);
               var6 = new StringBuilder();
               var6.append(var5[var1]);
               var6.append("mph");
               var4[var1] = var6.toString();
               ++var1;
            }
         }

         this.mSpeeddata.setEntries(var4);
         this.mSpeeddata.setEntryValues(var5);
      }

   }

   protected void ProcessData(byte[] var1) {
      boolean var10 = false;
      boolean var9 = false;
      boolean var8 = false;
      boolean var7 = false;
      boolean var6 = false;
      boolean var11 = false;
      boolean var4 = false;
      boolean var5 = false;
      boolean var14 = false;
      boolean var13 = false;
      boolean var12 = false;
      int var2 = var1[0];
      if (var1.length > 2) {
         byte var3 = var1[2];
         StringBuilder var15;
         SwitchboxPreference var16;
         HCheckBoxPreference var20;
         if (var2 == 64) {
            HCheckBoxPreference var17;
            byte var19;
            if (var3 != -128) {
               StringBuilder var18;
               SwitchboxPreference var21;
               StringBuilder var22;
               if (var3 != -112) {
                  if (var3 != -48) {
                     if (var3 != 0) {
                        if (var3 != 16) {
                           if (var3 != 32) {
                              if (var3 != 64) {
                                 if (var3 != 96) {
                                    if (var3 != 112) {
                                       if (var3 != 48) {
                                          if (var3 != 49) {
                                             switch (var3) {
                                                case 80:
                                                   var19 = var1[3];
                                                   var21 = this.mTurnontime;
                                                   var22 = new StringBuilder();
                                                   var22.append(var19 & 15);
                                                   var22.append("");
                                                   var21.setValue(var22.toString());
                                                   var19 = var1[3];
                                                   var20 = this.mAutorunlights;
                                                   if ((var19 & 16) > 0) {
                                                      var4 = true;
                                                   } else {
                                                      var4 = false;
                                                   }

                                                   var20.setChecked(var4);
                                                   var19 = var1[3];
                                                   var20 = this.mTurnlights;
                                                   if ((var19 & 32) > 0) {
                                                      var4 = true;
                                                   } else {
                                                      var4 = false;
                                                   }

                                                   var20.setChecked(var4);
                                                   var19 = var1[4];
                                                   var21 = this.mSwitchlight;
                                                   var22 = new StringBuilder();
                                                   var22.append(var19 & 255);
                                                   var22.append("");
                                                   var21.setValue(var22.toString());
                                                   var19 = var1[5];
                                                   var21 = this.mHomeinmode;
                                                   var22 = new StringBuilder();
                                                   var22.append(var19 & 255);
                                                   var22.append("");
                                                   var21.setValue(var22.toString());
                                                   var19 = var1[6];
                                                   var16 = this.mHomeoutmode;
                                                   var15 = new StringBuilder();
                                                   var15.append(var19 & 255);
                                                   var15.append("");
                                                   var16.setValue(var15.toString());
                                                   var19 = var1[3];
                                                   var17 = this.mRunning_lights;
                                                   var4 = var10;
                                                   if ((var19 & 64) > 0) {
                                                      var4 = true;
                                                   }

                                                   var17.setChecked(var4);
                                                   break;
                                                case 81:
                                                   var19 = var1[3];
                                                   var16 = this.mTravlemode;
                                                   var15 = new StringBuilder();
                                                   var15.append(var19 & 1);
                                                   var15.append("");
                                                   var16.setValue(var15.toString());
                                                   var19 = var1[4];
                                                   var16 = this.mDoorloight;
                                                   var15 = new StringBuilder();
                                                   var15.append(var19 & 255);
                                                   var15.append("");
                                                   var16.setValue(var15.toString());
                                                   var19 = var1[5];
                                                   var16 = this.mFootlight;
                                                   var15 = new StringBuilder();
                                                   var15.append(var19 & 255);
                                                   var15.append("");
                                                   var16.setValue(var15.toString());
                                                   var19 = var1[6];
                                                   var16 = this.mHeadlight_range;
                                                   var15 = new StringBuilder();
                                                   var15.append(var19 >> 5 & 255);
                                                   var15.append("");
                                                   var16.setValue(var15.toString());
                                                   var19 = var1[6];
                                                   var20 = this.mCross_follow;
                                                   if ((var19 & 1) > 0) {
                                                      var4 = true;
                                                   } else {
                                                      var4 = false;
                                                   }

                                                   var20.setChecked(var4);
                                                   var19 = var1[6];
                                                   var20 = this.mDynamic_light;
                                                   var4 = var12;
                                                   if ((var19 & 2) > 0) {
                                                      var4 = true;
                                                   }

                                                   var20.setChecked(var4);
                                                   var2 = var1[4] & 255;
                                                   this.mDoor_light.setValue(var2 - var2 % 10);
                                                   var2 = var1[5] & 255;
                                                   this.mFeet_light.setValue(var2 - var2 % 10);
                                                   break;
                                                case 82:
                                                   var2 = var1[5] & 15;
                                                   this.mLight.setValue(var2);
                                                   if (var2 > 0) {
                                                      this.mAll_lights.setEnabled(true);
                                                      this.mRoof_light.setEnabled(true);
                                                      this.mFront_light.setEnabled(true);
                                                      this.mDoor_light.setEnabled(true);
                                                      this.mFeet_light.setEnabled(true);
                                                   } else {
                                                      this.mAll_lights.setEnabled(false);
                                                      this.mRoof_light.setEnabled(false);
                                                      this.mFront_light.setEnabled(false);
                                                      this.mDoor_light.setEnabled(false);
                                                      this.mFeet_light.setEnabled(false);
                                                   }

                                                   var2 = var1[3] & 255;
                                                   this.mRoof_light.setValue(var2 - var2 % 10);
                                                   var2 = var1[4] & 255;
                                                   this.mFront_light.setValue(var2 - var2 % 10);
                                                   var2 = var1[6] & 255;
                                                   this.mAll_lights.setValue(var2 - var2 % 10);
                                             }
                                          } else {
                                             var19 = var1[3];
                                             var20 = this.mLastdistance;
                                             if ((var19 & 1) > 0) {
                                                var4 = true;
                                             } else {
                                                var4 = false;
                                             }

                                             var20.setChecked(var4);
                                             var19 = var1[3];
                                             var20 = this.mFrontasssystem;
                                             if ((var19 & 2) > 0) {
                                                var4 = true;
                                             } else {
                                                var4 = false;
                                             }

                                             var20.setChecked(var4);
                                             var19 = var1[3];
                                             var20 = this.mWarning;
                                             if ((var19 & 4) > 0) {
                                                var4 = true;
                                             } else {
                                                var4 = false;
                                             }

                                             var20.setChecked(var4);
                                             var19 = var1[3];
                                             var20 = this.mShowdistancecontrol;
                                             var4 = var9;
                                             if ((var19 & 8) > 0) {
                                                var4 = true;
                                             }

                                             var20.setChecked(var4);
                                             var19 = var1[4];
                                             var21 = this.mAccrdriveprogram;
                                             var22 = new StringBuilder();
                                             var22.append(var19 & 15);
                                             var22.append("");
                                             var21.setValue(var22.toString());
                                             var19 = var1[4];
                                             var21 = this.mAccvehicle;
                                             var18 = new StringBuilder();
                                             var18.append((var19 & 240) >> 4);
                                             var18.append("");
                                             var21.setValue(var18.toString());
                                          }
                                       } else {
                                          var19 = var1[4];
                                          var20 = this.mLanesystem;
                                          if ((var19 & 1) > 0) {
                                             var4 = true;
                                          } else {
                                             var4 = false;
                                          }

                                          var20.setChecked(var4);
                                          var19 = var1[3];
                                          var20 = this.mFatigueystem;
                                          if ((var19 & 2) == 2) {
                                             var4 = true;
                                          } else {
                                             var4 = false;
                                          }

                                          var20.setChecked(var4);
                                          var20 = this.mBlind_detection;
                                          if ((var1[3] & 4) != 0) {
                                             var4 = true;
                                          } else {
                                             var4 = false;
                                          }

                                          var20.setChecked(var4);
                                          var19 = var1[3];
                                          var17 = this.mAdaptiv;
                                          var4 = var8;
                                          if ((var19 & 1) > 0) {
                                             var4 = true;
                                          }

                                          var17.setChecked(var4);
                                       }
                                    } else {
                                       var19 = var1[3];
                                       var16 = this.mWindowsopen;
                                       var15 = new StringBuilder();
                                       var15.append(var19 & 15);
                                       var15.append("");
                                       var16.setValue(var15.toString());
                                       var19 = var1[3];
                                       var21 = this.mCentrallock;
                                       var22 = new StringBuilder();
                                       var22.append((var19 & 240) >> 4);
                                       var22.append("");
                                       var21.setValue(var22.toString());
                                       var19 = var1[4];
                                       var20 = this.mAutolock;
                                       if ((var19 & 1) > 0) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var20.setChecked(var4);
                                       var19 = var1[5];
                                       var20 = this.mAcousticcon;
                                       if ((var19 & 1) > 0) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var20.setChecked(var4);
                                       var20 = this.mInductive_rear;
                                       if ((var1[4] & 2) != 0) {
                                          var4 = true;
                                       } else {
                                          var4 = false;
                                       }

                                       var20.setChecked(var4);
                                       var20 = this.mInterior_monitor;
                                       var4 = var7;
                                       if ((var1[4] & 4) != 0) {
                                          var4 = true;
                                       }

                                       var20.setChecked(var4);
                                    }
                                 } else {
                                    var19 = var1[3];
                                    var20 = this.mMirrorsyncadj;
                                    if ((var19 & 1) > 0) {
                                       var4 = true;
                                    } else {
                                       var4 = false;
                                    }

                                    var20.setChecked(var4);
                                    var19 = var1[3];
                                    var20 = this.mMirrorlower;
                                    if ((var19 & 2) > 0) {
                                       var4 = true;
                                    } else {
                                       var4 = false;
                                    }

                                    var20.setChecked(var4);
                                    var19 = var1[3];
                                    var20 = this.mAutorain;
                                    if ((var19 & 4) > 0) {
                                       var4 = true;
                                    } else {
                                       var4 = false;
                                    }

                                    var20.setChecked(var4);
                                    var19 = var1[3];
                                    var20 = this.mRearrain;
                                    if ((var19 & 8) > 0) {
                                       var4 = true;
                                    } else {
                                       var4 = false;
                                    }

                                    var20.setChecked(var4);
                                    var19 = var1[4];
                                    var17 = this.mParking;
                                    var4 = var6;
                                    if ((var19 & 1) > 0) {
                                       var4 = true;
                                    }

                                    var17.setChecked(var4);
                                 }
                              } else {
                                 var19 = var1[3];
                                 var20 = this.mAutoaction;
                                 if ((var19 & 1) > 0) {
                                    var4 = true;
                                 } else {
                                    var4 = false;
                                 }

                                 var20.setChecked(var4);
                                 var19 = var1[4];
                                 var21 = this.mFrontvol;
                                 var22 = new StringBuilder();
                                 var22.append(var19 & 15);
                                 var22.append("");
                                 var21.setValue(var22.toString());
                                 var19 = var1[4];
                                 var16 = this.mFrontpitch;
                                 var15 = new StringBuilder();
                                 var15.append((var19 & 240) >> 4);
                                 var15.append("");
                                 var16.setValue(var15.toString());
                                 var19 = var1[5];
                                 var16 = this.mRearvol;
                                 var15 = new StringBuilder();
                                 var15.append(var19 & 15);
                                 var15.append("");
                                 var16.setValue(var15.toString());
                                 var19 = var1[5];
                                 var21 = this.mRearpitch;
                                 var22 = new StringBuilder();
                                 var22.append((var19 & 240) >> 4);
                                 var22.append("");
                                 var21.setValue(var22.toString());
                                 var19 = var1[3];
                                 var17 = this.mActivation;
                                 var4 = var11;
                                 if ((var19 & 2) > 0) {
                                    var4 = true;
                                 }

                                 var17.setChecked(var4);
                              }
                           } else {
                              var19 = var1[3];
                              var20 = this.mSpeedalarm;
                              if ((var19 & 1) > 0) {
                                 var4 = true;
                              }

                              var20.setChecked(var4);
                              this.updateSpeedEntries(var1[3] >> 1 & 1);
                              var19 = var1[4];
                              this.mSpeeddata.setValue(var19 & 255);
                              var19 = var1[5];
                              this.mDirect_tire.setValue(var19 & 3);
                           }
                        } else {
                           var19 = var1[3];
                           var21 = this.mActionstate;
                           var18 = new StringBuilder();
                           var18.append(var19 & 3);
                           var18.append("");
                           var21.setValue(var18.toString());
                        }
                     } else {
                        var21 = this.mLangauage;
                        var22 = new StringBuilder();
                        var22.append(var1[3]);
                        var22.append("");
                        var21.setValue(var22.toString());
                     }
                  } else {
                     this.mMode.setValue(var1[3] & 3);
                     this.mBrightness.setValue(var1[4] & 255);
                     this.mContrast.setValue(var1[5] & 255);
                     this.mSaturation.setValue(var1[6] & 255);
                  }
               } else {
                  var19 = var1[3];
                  var16 = this.mMiunit;
                  var15 = new StringBuilder();
                  var15.append(var19 & 1);
                  var15.append("");
                  var16.setValue(var15.toString());
                  var19 = var1[3];
                  var16 = this.mSpeedunits;
                  var15 = new StringBuilder();
                  var15.append((var19 & 2) >> 1);
                  var15.append("");
                  var16.setValue(var15.toString());
                  var19 = var1[3];
                  var16 = this.mTemperature;
                  var15 = new StringBuilder();
                  var15.append((var19 & 4) >> 2);
                  var15.append("");
                  var16.setValue(var15.toString());
                  var19 = var1[3];
                  var21 = this.mVolume;
                  var22 = new StringBuilder();
                  var22.append((var19 & 240) >> 4);
                  var22.append("");
                  var21.setValue(var22.toString());
                  var19 = var1[4];
                  var21 = this.mFulecons;
                  var22 = new StringBuilder();
                  var22.append(var19 & 15);
                  var22.append("");
                  var21.setValue(var22.toString());
                  var19 = var1[4];
                  var21 = this.mTireunit;
                  var18 = new StringBuilder();
                  var18.append((var19 & 240) >> 4);
                  var18.append("");
                  var21.setValue(var18.toString());
               }
            } else {
               var19 = var1[3];
               var20 = this.mCurrentfuel;
               if ((var19 & 1) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[3];
               var20 = this.mAveragefuel;
               if ((var19 & 2) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[3];
               var20 = this.mComfort;
               if ((var19 & 4) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[3];
               var20 = this.mTipeconomy;
               if ((var19 & 8) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[3];
               var20 = this.mTraveltime;
               if ((var19 & 16) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[3];
               var20 = this.mMileage;
               if ((var19 & 32) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[3];
               var20 = this.mAverageapeed;
               if ((var19 & 64) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var20 = this.mDigitalspeed;
               if (var1[3] < 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[4];
               var20 = this.mSpeedalarm1;
               if ((var19 & 1) > 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var19 = var1[4];
               var17 = this.mOil;
               var4 = var5;
               if ((var19 & 2) > 0) {
                  var4 = true;
               }

               var17.setChecked(var4);
            }
         } else if (var2 != 80 && var2 != 96 && var2 != 97) {
            if (var2 == 37) {
               var20 = this.mRadar_sound;
               var4 = var14;
               if ((var1[2] & 1) != 0) {
                  var4 = true;
               }

               var20.setChecked(var4);
            } else if (var2 == 33) {
               var20 = this.mAir_switch;
               if ((var1[2] & 128) != 0) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               var20.setChecked(var4);
               var20 = this.mAqs_auto;
               var4 = var13;
               if ((var1[6] & 32) != 0) {
                  var4 = true;
               }

               var20.setChecked(var4);
               var16 = this.mAutomatic_air;
               var15 = new StringBuilder();
               var15.append(var1[8] & 3);
               var15.append("");
               var16.setValue(var15.toString());
            }
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492890);
      this.mLanesystem = this.findHCheckBoxPreference("lanesystem");
      this.mFatigueystem = this.findHCheckBoxPreference("fatigueystem");
      this.mAutoaction = this.findHCheckBoxPreference("autoaction");
      this.mAutorunlights = this.findHCheckBoxPreference("autorunlights");
      this.mTurnlights = this.findHCheckBoxPreference("turnlights");
      this.mLastdistance = this.findHCheckBoxPreference("lastdistance");
      this.mFrontasssystem = this.findHCheckBoxPreference("frontasssystem");
      this.mWarning = this.findHCheckBoxPreference("warning");
      this.mShowdistancecontrol = this.findHCheckBoxPreference("showdistancecontrol");
      this.mMirrorsyncadj = this.findHCheckBoxPreference("mirrorsyncadj");
      this.mMirrorlower = this.findHCheckBoxPreference("mirrorlower");
      this.mAutorain = this.findHCheckBoxPreference("autorain");
      this.mRearrain = this.findHCheckBoxPreference("rearrain");
      this.mAcousticcon = this.findHCheckBoxPreference("acousticconfirmation");
      this.mAutolock = this.findHCheckBoxPreference("autolock");
      this.mSpeedalarm = this.findHCheckBoxPreference("speedalarm");
      this.mCurrentfuel = this.findHCheckBoxPreference("currentfuel");
      this.mAveragefuel = this.findHCheckBoxPreference("averagefuel");
      this.mAverageapeed = this.findHCheckBoxPreference("averageapeed");
      this.mComfort = this.findHCheckBoxPreference("comfort");
      this.mTipeconomy = this.findHCheckBoxPreference("tipeconomy");
      this.mDigitalspeed = this.findHCheckBoxPreference("digitalspeed");
      this.mOil = this.findHCheckBoxPreference("oil");
      this.mMileage = this.findHCheckBoxPreference("mileage");
      this.mTraveltime = this.findHCheckBoxPreference("traveltime");
      this.mSpeedalarm1 = this.findHCheckBoxPreference("speedalarm1");
      this.mRadar_sound = this.findHCheckBoxPreference("radar_sound");
      this.mAir_switch = this.findHCheckBoxPreference("air_switch");
      this.mAqs_auto = this.findHCheckBoxPreference("aqs_auto");
      this.mParking = this.findHCheckBoxPreference("parking");
      this.mRunning_lights = this.findHCheckBoxPreference("running_lights");
      this.mBlind_detection = this.findHCheckBoxPreference("blind_detection");
      this.mInductive_rear = this.findHCheckBoxPreference("inductive_rear");
      this.mInterior_monitor = this.findHCheckBoxPreference("interior_monitor");
      this.mAdaptiv = this.findHCheckBoxPreference("adaptiv");
      this.mActivation = this.findHCheckBoxPreference("activation");
      this.mCross_follow = this.findHCheckBoxPreference("cross_follow");
      this.mDynamic_light = this.findHCheckBoxPreference("dynamic_light");
      this.mSpeeddata = this.findSwitchboxPreference("speeddata");
      this.updateSpeedEntries(0);
      CharSequence[] var6 = new CharSequence[7];
      CharSequence[] var5 = new CharSequence[7];
      this.mFrontvol = this.findSwitchboxPreference("frontvol");
      this.mFrontpitch = this.findSwitchboxPreference("frontpitch");
      this.mRearvol = this.findSwitchboxPreference("rearvol");
      this.mRearpitch = this.findSwitchboxPreference("rearpitch");
      this.mTurnontime = this.findSwitchboxPreference("turnontime");
      this.mSwitchlight = this.findSwitchboxPreference("switchlight");
      this.mHomeinmode = this.findSwitchboxPreference("homeinmode");
      this.mHomeoutmode = this.findSwitchboxPreference("homeoutmode");
      this.mWindowsopen = this.findSwitchboxPreference("windowsopen");
      this.mCentrallock = this.findSwitchboxPreference("centrallock");
      this.mSpeedunits = this.findSwitchboxPreference("speedunits");
      this.mVolume = this.findSwitchboxPreference("volume");
      this.mMiunit = this.findSwitchboxPreference("miunit");
      this.mTireunit = this.findSwitchboxPreference("tireunit");
      this.mFulecons = this.findSwitchboxPreference("fulecons");
      this.mTemperature = this.findSwitchboxPreference("temperature");
      this.mLangauage = this.findSwitchboxPreference("langauage");
      this.mTravlemode = this.findSwitchboxPreference("travlemode");
      this.mDoorloight = this.findSwitchboxPreference("doorloight");
      this.mFootlight = this.findSwitchboxPreference("footlight");
      this.mAccrdriveprogram = this.findSwitchboxPreference("accrdriveprogram");
      this.mAccvehicle = this.findSwitchboxPreference("accvehicle");
      this.mAutomatic_air = this.findSwitchboxPreference("automatic_air");
      this.mActionstate = this.findSwitchboxPreference("actionstate");
      this.mHeadlight_range = this.findSwitchboxPreference("headlight_range");

      int var2;
      String var4;
      for(var2 = 0; var2 < var6.length; ++var2) {
         var4 = Integer.toString(var2);
         var5[var2] = var4;
         var6[var2] = var4;
      }

      this.mHeadlight_range.setEntries(var6);
      this.mHeadlight_range.setEntryValues(var5);
      this.mDirect_tire = this.findSwitchboxPreference("direct_tire");
      this.mMode = this.findSwitchboxPreference("mode");
      var5 = new CharSequence[9];
      var6 = new CharSequence[9];

      for(var2 = 0; var2 < var5.length; ++var2) {
         var4 = Integer.toString(var2 * 5 + 30);
         var6[var2] = var4;
         var5[var2] = var4;
      }

      this.mBrightness = this.findSwitchboxPreference("brightness");
      this.mBrightness.setEntries(var5);
      this.mBrightness.setEntryValues(var6);
      this.mContrast = this.findSwitchboxPreference("contrast");
      this.mContrast.setEntries(var5);
      this.mContrast.setEntryValues(var6);
      this.mSaturation = this.findSwitchboxPreference("saturation");
      this.mSaturation.setEntries(var5);
      this.mSaturation.setEntryValues(var6);
      var5 = new CharSequence[11];
      CharSequence[] var9 = new CharSequence[11];

      int var3;
      for(var2 = 0; var2 < var5.length; ++var2) {
         String var7;
         if (var2 == 0) {
            var7 = this.getString(2131296511);
         } else {
            StringBuilder var8 = new StringBuilder();
            var8.append(this.getString(2131297241));
            var8.append(var2);
            var7 = var8.toString();
         }

         var5[var2] = var7;
         if (var2 > 0) {
            var3 = var2 + 4;
         } else {
            var3 = 0;
         }

         var9[var2] = Integer.toString(var3);
      }

      this.mLight = this.findSwitchboxPreference("light");
      this.mLight.setEntries(var5);
      this.mLight.setEntryValues(var9);
      var9 = new CharSequence[11];
      var6 = new CharSequence[11];

      for(var2 = 0; var2 < var9.length; ++var2) {
         StringBuilder var10 = new StringBuilder();
         var3 = var2 * 10;
         var10.append(var3);
         var10.append(this.getString(2131296425));
         var9[var2] = var10.toString();
         var6[var2] = Integer.toString(var3);
      }

      this.mAll_lights = this.findSwitchboxPreference("all_lights");
      this.mAll_lights.setEntries(var9);
      this.mAll_lights.setEntryValues(var6);
      this.mRoof_light = this.findSwitchboxPreference("roof_light");
      this.mRoof_light.setEntries(var9);
      this.mRoof_light.setEntryValues(var6);
      this.mFront_light = this.findSwitchboxPreference("front_light");
      this.mFront_light.setEntries(var9);
      this.mFront_light.setEntryValues(var6);
      this.mDoor_light = this.findSwitchboxPreference("door_light");
      this.mDoor_light.setEntries(var9);
      this.mDoor_light.setEntryValues(var6);
      this.mFeet_light = this.findSwitchboxPreference("feet_light");
      this.mFeet_light.setEntries(var9);
      this.mFeet_light.setEntryValues(var6);
      this.mTpms = (PreferenceScreen)this.findPreference("tpms");
      this.mHandler.sendEmptyMessage(0);
      this.RequestCmdkey((byte)33, (byte)0);
   }

   protected void onDestroy() {
      this.mHandler.removeCallbacksAndMessages((Object)null);
      super.onDestroy();
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
         if (var1 == this.mFrontvol) {
            this.SetCmdkey((byte)65, (byte)var3);
         } else if (var1 == this.mFrontpitch) {
            this.SetCmdkey((byte)66, (byte)var3);
         } else if (var1 == this.mRearvol) {
            this.SetCmdkey((byte)67, (byte)var3);
         } else if (var1 == this.mRearpitch) {
            this.SetCmdkey((byte)68, (byte)var3);
         } else if (var1 == this.mTurnontime) {
            this.SetCmdkey((byte)80, (byte)var3);
         } else if (var1 == this.mSwitchlight) {
            this.SetCmdkey((byte)83, (byte)var3);
         } else if (var1 == this.mHomeinmode) {
            this.SetCmdkey((byte)84, (byte)var3);
         } else if (var1 == this.mHomeoutmode) {
            this.SetCmdkey((byte)85, (byte)var3);
         } else if (var1 == this.mWindowsopen) {
            this.SetCmdkey((byte)112, (byte)var3);
         } else if (var1 == this.mCentrallock) {
            this.SetCmdkey((byte)113, (byte)var3);
         } else if (var1 == this.mSpeedunits) {
            this.SetCmdkey((byte)-111, (byte)var3);
         } else if (var1 == this.mVolume) {
            this.SetCmdkey((byte)-109, (byte)var3);
         } else if (var1 == this.mMiunit) {
            this.SetCmdkey((byte)-112, (byte)var3);
         } else if (var1 == this.mTireunit) {
            this.SetCmdkey((byte)-107, (byte)var3);
         } else if (var1 == this.mFulecons) {
            this.SetCmdkey((byte)-108, (byte)var3);
         } else if (var1 == this.mTemperature) {
            this.SetCmdkey((byte)-110, (byte)var3);
         } else if (var1 == this.mLangauage) {
            this.SetCmdkey((byte)0, (byte)var3);
         } else if (var1 == this.mTravlemode) {
            this.SetCmdkey((byte)86, (byte)var3);
         } else if (var1 == this.mDoorloight) {
            this.SetCmdkey((byte)87, (byte)var3);
         } else if (var1 == this.mFootlight) {
            this.SetCmdkey((byte)88, (byte)var3);
         } else if (var1 == this.mAccrdriveprogram) {
            this.SetCmdkey((byte)55, (byte)var3);
         } else if (var1 == this.mAccvehicle) {
            this.SetCmdkey((byte)56, (byte)var3);
         } else if (var1 == this.mAutomatic_air) {
            this.SetCmdkey((byte)-79, (byte)var3);
         } else if (var1 == this.mActionstate) {
            this.SetCmdkey((byte)16, (byte)var3);
         } else if (var1 == this.mHeadlight_range) {
            this.SetCmdkey((byte)95, (byte)var3);
         } else if (var1 == this.mSpeeddata) {
            this.SetCmdkey((byte)33, (byte)var3);
         } else if (var1 == this.mDirect_tire) {
            this.SetCmdkey((byte)35, (byte)var3);
         } else if (var1 == this.mMode) {
            this.SetCmdkey((byte)70, (byte)var3);
         } else if (var1 == this.mBrightness) {
            this.SetCmdkey((byte)71, (byte)var3);
         } else if (var1 == this.mContrast) {
            this.SetCmdkey((byte)72, (byte)var3);
         } else if (var1 == this.mSaturation) {
            this.SetCmdkey((byte)73, (byte)var3);
         } else if (var1 == this.mLight) {
            this.SetCmdkey((byte)94, (byte)var3);
         } else if (var1 == this.mAll_lights) {
            this.SetCmdkey((byte)93, (byte)var3);
         } else if (var1 == this.mRoof_light) {
            this.SetCmdkey((byte)91, (byte)var3);
         } else if (var1 == this.mFront_light) {
            this.SetCmdkey((byte)92, (byte)var3);
         } else if (var1 == this.mDoor_light) {
            this.SetCmdkey((byte)87, (byte)var3);
         } else if (var1 == this.mFeet_light) {
            this.SetCmdkey((byte)88, (byte)var3);
         }

         return false;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      HCheckBoxPreference var3 = this.mLanesystem;
      if (var2 == var3) {
         if (var3.isChecked()) {
            this.SetCmdkey((byte)54, (byte)1);
         } else {
            this.SetCmdkey((byte)54, (byte)0);
         }
      } else {
         var3 = this.mFatigueystem;
         if (var2 == var3) {
            if (var3.isChecked()) {
               this.SetCmdkey((byte)49, (byte)1);
            } else {
               this.SetCmdkey((byte)49, (byte)0);
            }
         } else {
            var3 = this.mAutoaction;
            if (var2 == var3) {
               if (var3.isChecked()) {
                  this.SetCmdkey((byte)64, (byte)1);
               } else {
                  this.SetCmdkey((byte)64, (byte)0);
               }
            } else {
               var3 = this.mAutorunlights;
               if (var2 == var3) {
                  if (var3.isChecked()) {
                     this.SetCmdkey((byte)81, (byte)1);
                  } else {
                     this.SetCmdkey((byte)81, (byte)0);
                  }
               } else {
                  var3 = this.mTurnlights;
                  if (var2 == var3) {
                     if (var3.isChecked()) {
                        this.SetCmdkey((byte)82, (byte)1);
                     } else {
                        this.SetCmdkey((byte)82, (byte)0);
                     }
                  } else {
                     var3 = this.mLastdistance;
                     if (var2 == var3) {
                        if (var3.isChecked()) {
                           this.SetCmdkey((byte)50, (byte)1);
                        } else {
                           this.SetCmdkey((byte)50, (byte)0);
                        }
                     } else {
                        var3 = this.mFrontasssystem;
                        if (var2 == var3) {
                           if (var3.isChecked()) {
                              this.SetCmdkey((byte)51, (byte)1);
                           } else {
                              this.SetCmdkey((byte)51, (byte)0);
                           }
                        } else {
                           var3 = this.mWarning;
                           if (var2 == var3) {
                              if (var3.isChecked()) {
                                 this.SetCmdkey((byte)52, (byte)1);
                              } else {
                                 this.SetCmdkey((byte)52, (byte)0);
                              }
                           } else {
                              var3 = this.mShowdistancecontrol;
                              if (var2 == var3) {
                                 if (var3.isChecked()) {
                                    this.SetCmdkey((byte)53, (byte)1);
                                 } else {
                                    this.SetCmdkey((byte)53, (byte)0);
                                 }
                              } else {
                                 var3 = this.mMirrorsyncadj;
                                 if (var2 == var3) {
                                    if (var3.isChecked()) {
                                       this.SetCmdkey((byte)96, (byte)1);
                                    } else {
                                       this.SetCmdkey((byte)96, (byte)0);
                                    }
                                 } else {
                                    var3 = this.mMirrorlower;
                                    if (var2 == var3) {
                                       if (var3.isChecked()) {
                                          this.SetCmdkey((byte)97, (byte)1);
                                       } else {
                                          this.SetCmdkey((byte)97, (byte)0);
                                       }
                                    } else {
                                       var3 = this.mAutorain;
                                       if (var2 == var3) {
                                          if (var3.isChecked()) {
                                             this.SetCmdkey((byte)98, (byte)1);
                                          } else {
                                             this.SetCmdkey((byte)98, (byte)0);
                                          }
                                       } else {
                                          var3 = this.mRearrain;
                                          if (var2 == var3) {
                                             if (var3.isChecked()) {
                                                this.SetCmdkey((byte)99, (byte)1);
                                             } else {
                                                this.SetCmdkey((byte)99, (byte)0);
                                             }
                                          } else {
                                             var3 = this.mParking;
                                             if (var2 == var3) {
                                                if (var3.isChecked()) {
                                                   this.SetCmdkey((byte)100, (byte)1);
                                                } else {
                                                   this.SetCmdkey((byte)100, (byte)0);
                                                }
                                             } else {
                                                var3 = this.mAcousticcon;
                                                if (var2 == var3) {
                                                   if (var3.isChecked()) {
                                                      this.SetCmdkey((byte)-55, (byte)1);
                                                   } else {
                                                      this.SetCmdkey((byte)-55, (byte)0);
                                                   }
                                                } else {
                                                   var3 = this.mAutolock;
                                                   if (var2 == var3) {
                                                      if (var3.isChecked()) {
                                                         this.SetCmdkey((byte)114, (byte)1);
                                                      } else {
                                                         this.SetCmdkey((byte)114, (byte)0);
                                                      }
                                                   } else {
                                                      var3 = this.mSpeedalarm;
                                                      if (var2 == var3) {
                                                         if (var3.isChecked()) {
                                                            this.SetCmdkey((byte)32, (byte)1);
                                                         } else {
                                                            this.SetCmdkey((byte)32, (byte)0);
                                                         }
                                                      } else {
                                                         var3 = this.mCurrentfuel;
                                                         if (var2 == var3) {
                                                            if (var3.isChecked()) {
                                                               this.SetCmdkey((byte)-128, (byte)1);
                                                            } else {
                                                               this.SetCmdkey((byte)-128, (byte)0);
                                                            }
                                                         } else {
                                                            var3 = this.mAveragefuel;
                                                            if (var2 == var3) {
                                                               if (var3.isChecked()) {
                                                                  this.SetCmdkey((byte)-127, (byte)1);
                                                               } else {
                                                                  this.SetCmdkey((byte)-127, (byte)0);
                                                               }
                                                            } else {
                                                               var3 = this.mAverageapeed;
                                                               if (var2 == var3) {
                                                                  if (var3.isChecked()) {
                                                                     this.SetCmdkey((byte)-122, (byte)1);
                                                                  } else {
                                                                     this.SetCmdkey((byte)-122, (byte)0);
                                                                  }
                                                               } else {
                                                                  var3 = this.mComfort;
                                                                  if (var2 == var3) {
                                                                     if (var3.isChecked()) {
                                                                        this.SetCmdkey((byte)-126, (byte)1);
                                                                     } else {
                                                                        this.SetCmdkey((byte)-126, (byte)0);
                                                                     }
                                                                  } else {
                                                                     var3 = this.mTipeconomy;
                                                                     if (var2 == var3) {
                                                                        if (var3.isChecked()) {
                                                                           this.SetCmdkey((byte)-125, (byte)1);
                                                                        } else {
                                                                           this.SetCmdkey((byte)-125, (byte)0);
                                                                        }
                                                                     } else {
                                                                        var3 = this.mDigitalspeed;
                                                                        if (var2 == var3) {
                                                                           if (var3.isChecked()) {
                                                                              this.SetCmdkey((byte)-121, (byte)1);
                                                                           } else {
                                                                              this.SetCmdkey((byte)-121, (byte)0);
                                                                           }
                                                                        } else {
                                                                           var3 = this.mOil;
                                                                           if (var2 == var3) {
                                                                              if (var3.isChecked()) {
                                                                                 this.SetCmdkey((byte)-119, (byte)1);
                                                                              } else {
                                                                                 this.SetCmdkey((byte)-119, (byte)0);
                                                                              }
                                                                           } else {
                                                                              var3 = this.mMileage;
                                                                              if (var2 == var3) {
                                                                                 if (var3.isChecked()) {
                                                                                    this.SetCmdkey((byte)-123, (byte)1);
                                                                                 } else {
                                                                                    this.SetCmdkey((byte)-123, (byte)0);
                                                                                 }
                                                                              } else {
                                                                                 var3 = this.mTraveltime;
                                                                                 if (var2 == var3) {
                                                                                    if (var3.isChecked()) {
                                                                                       this.SetCmdkey((byte)-124, (byte)1);
                                                                                    } else {
                                                                                       this.SetCmdkey((byte)-124, (byte)0);
                                                                                    }
                                                                                 } else {
                                                                                    var3 = this.mSpeedalarm1;
                                                                                    if (var2 == var3) {
                                                                                       if (var3.isChecked()) {
                                                                                          this.SetCmdkey((byte)-120, (byte)1);
                                                                                       } else {
                                                                                          this.SetCmdkey((byte)-120, (byte)0);
                                                                                       }
                                                                                    } else {
                                                                                       var3 = this.mRadar_sound;
                                                                                       if (var2 == var3) {
                                                                                          if (var3.isChecked()) {
                                                                                             this.SetCmdkey((byte)-85, (byte)1);
                                                                                          } else {
                                                                                             this.SetCmdkey((byte)-85, (byte)0);
                                                                                          }
                                                                                       } else {
                                                                                          var3 = this.mAir_switch;
                                                                                          if (var2 == var3) {
                                                                                             if (var3.isChecked()) {
                                                                                                this.SetCmdkey((byte)-78, (byte)1);
                                                                                             } else {
                                                                                                this.SetCmdkey((byte)-78, (byte)0);
                                                                                             }
                                                                                          } else {
                                                                                             var3 = this.mAqs_auto;
                                                                                             if (var2 == var3) {
                                                                                                if (var3.isChecked()) {
                                                                                                   this.SetCmdkey((byte)-80, (byte)1);
                                                                                                } else {
                                                                                                   this.SetCmdkey((byte)-80, (byte)0);
                                                                                                }
                                                                                             } else if (var2 == this.mTpms) {
                                                                                                this.AlertDialog(new AlertDialogCallBack(this) {
                                                                                                   final canbus17settings this$0;

                                                                                                   {
                                                                                                      this.this$0 = var1;
                                                                                                   }

                                                                                                   public void OkClick() {
                                                                                                      this.this$0.SetCmdkey((byte)34, (byte)1);
                                                                                                   }
                                                                                                }, this.mTpms.getTitle().toString());
                                                                                             } else {
                                                                                                var3 = this.mRunning_lights;
                                                                                                if (var2 == var3) {
                                                                                                   if (var3.isChecked()) {
                                                                                                      this.SetCmdkey((byte)-56, (byte)1);
                                                                                                   } else {
                                                                                                      this.SetCmdkey((byte)-56, (byte)0);
                                                                                                   }
                                                                                                } else {
                                                                                                   var3 = this.mBlind_detection;
                                                                                                   if (var2 == var3) {
                                                                                                      if (var3.isChecked()) {
                                                                                                         this.SetCmdkey((byte)57, (byte)1);
                                                                                                      } else {
                                                                                                         this.SetCmdkey((byte)57, (byte)0);
                                                                                                      }
                                                                                                   } else {
                                                                                                      var3 = this.mInductive_rear;
                                                                                                      if (var2 == var3) {
                                                                                                         if (var3.isChecked()) {
                                                                                                            this.SetCmdkey((byte)115, (byte)1);
                                                                                                         } else {
                                                                                                            this.SetCmdkey((byte)115, (byte)0);
                                                                                                         }
                                                                                                      } else {
                                                                                                         var3 = this.mInterior_monitor;
                                                                                                         if (var2 == var3) {
                                                                                                            if (var3.isChecked()) {
                                                                                                               this.SetCmdkey((byte)116, (byte)1);
                                                                                                            } else {
                                                                                                               this.SetCmdkey((byte)116, (byte)0);
                                                                                                            }
                                                                                                         } else {
                                                                                                            var3 = this.mAdaptiv;
                                                                                                            if (var2 == var3) {
                                                                                                               if (var3.isChecked()) {
                                                                                                                  this.SetCmdkey((byte)48, (byte)1);
                                                                                                               } else {
                                                                                                                  this.SetCmdkey((byte)48, (byte)0);
                                                                                                               }
                                                                                                            } else {
                                                                                                               var3 = this.mActivation;
                                                                                                               if (var2 == var3) {
                                                                                                                  if (var3.isChecked()) {
                                                                                                                     this.SetCmdkey((byte)69, (byte)1);
                                                                                                                  } else {
                                                                                                                     this.SetCmdkey((byte)69, (byte)0);
                                                                                                                  }
                                                                                                               } else {
                                                                                                                  var3 = this.mCross_follow;
                                                                                                                  if (var2 == var3) {
                                                                                                                     if (var3.isChecked()) {
                                                                                                                        this.SetCmdkey((byte)89, (byte)1);
                                                                                                                     } else {
                                                                                                                        this.SetCmdkey((byte)89, (byte)0);
                                                                                                                     }
                                                                                                                  } else {
                                                                                                                     var3 = this.mDynamic_light;
                                                                                                                     if (var2 == var3) {
                                                                                                                        if (var3.isChecked()) {
                                                                                                                           this.SetCmdkey((byte)90, (byte)1);
                                                                                                                        } else {
                                                                                                                           this.SetCmdkey((byte)90, (byte)0);
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
