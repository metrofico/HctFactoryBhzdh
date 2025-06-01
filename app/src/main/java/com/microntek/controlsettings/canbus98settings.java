package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus98settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Backlighting = 1;
   private int _Over_speed = 0;
   private HCheckBoxPreference mAvm_animan;
   private OnSwitchPreference mBacklighting;
   private HCheckBoxPreference mBoot_display_flow;
   private HCheckBoxPreference mBrake_alarm;
   private SwitchboxPreference mCar_line;
   private HCheckBoxPreference mDriving_auto;
   private SwitchboxPreference mFort_tips;
   private HCheckBoxPreference mLamp_no;
   private OnSwitchPreference mOver_speed;
   private HCheckBoxPreference mRear_view;
   private HCheckBoxPreference mRemote_open_trunk;
   private HCheckBoxPreference mRunning_lights;
   private HCheckBoxPreference mStart_avm;
   private HCheckBoxPreference mTurning_lighting;
   private HCheckBoxPreference mUnlock;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var6 = false;
      boolean var12 = false;
      boolean var10 = false;
      boolean var7 = false;
      boolean var5 = false;
      boolean var11 = false;
      boolean var4 = false;
      boolean var13 = false;
      boolean var9 = false;
      boolean var8 = false;
      boolean var14 = false;
      byte var3 = var1[0];
      int var2 = var1.length;
      if (var3 == 64) {
         byte var19 = var1[2];
         HCheckBoxPreference var15;
         if (var19 != 17) {
            StringBuilder var16;
            SwitchboxPreference var22;
            switch (var19) {
               case 1:
                  var22 = this.mFort_tips;
                  var16 = new StringBuilder();
                  var16.append("");
                  var16.append(var1[3] & 3);
                  var22.setValue(var16.toString());
                  break;
               case 2:
                  var15 = this.mBrake_alarm;
                  var4 = var9;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 3:
                  var15 = this.mDriving_auto;
                  var4 = var13;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 4:
                  var15 = this.mLamp_no;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 5:
                  var15 = this.mRunning_lights;
                  var4 = var11;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 6:
                  var15 = this.mStart_avm;
                  var4 = var5;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 7:
                  var15 = this.mAvm_animan;
                  var4 = var7;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 8:
                  var22 = this.mCar_line;
                  var16 = new StringBuilder();
                  var16.append("");
                  var16.append(var1[3] & 3);
                  var22.setValue(var16.toString());
                  break;
               case 9:
                  this._Over_speed = var1[3] & 31;
                  if (this._Over_speed == 0) {
                     this.mOver_speed.setValue("0", this.getString(2131296511));
                  } else {
                     OnSwitchPreference var18 = this.mOver_speed;
                     StringBuilder var21 = new StringBuilder();
                     var21.append(this._Over_speed * 5 + 25);
                     var21.append(" km/h");
                     var18.setValue("0", var21.toString());
                  }
                  break;
               case 10:
                  this._Backlighting = var1[3] & 31;
                  var2 = this._Backlighting;
                  if (var2 >= 1 && var2 <= 10) {
                     OnSwitchPreference var20 = this.mBacklighting;
                     StringBuilder var17 = new StringBuilder();
                     var17.append("");
                     var17.append(this._Backlighting);
                     var20.setValue("0", var17.toString());
                  }
                  break;
               case 11:
                  var15 = this.mTurning_lighting;
                  var4 = var10;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 12:
                  var15 = this.mUnlock;
                  var4 = var12;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 13:
                  var15 = this.mRemote_open_trunk;
                  var4 = var6;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
                  break;
               case 14:
                  var15 = this.mBoot_display_flow;
                  var4 = var14;
                  if ((var1[3] & 1) != 0) {
                     var4 = true;
                  }

                  var15.setChecked(var4);
            }
         } else {
            var15 = this.mRear_view;
            var4 = var8;
            if ((var1[3] & 1) != 0) {
               var4 = true;
            }

            var15.setChecked(var4);
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492951);
      this.mBrake_alarm = (HCheckBoxPreference)this.findPreference("brake_alarm");
      this.mBrake_alarm.setOnPreferenceClickListener(this);
      this.mDriving_auto = (HCheckBoxPreference)this.findPreference("driving_auto");
      this.mDriving_auto.setOnPreferenceClickListener(this);
      this.mLamp_no = (HCheckBoxPreference)this.findPreference("lamp_no");
      this.mLamp_no.setOnPreferenceClickListener(this);
      this.mRunning_lights = (HCheckBoxPreference)this.findPreference("running_lights");
      this.mRunning_lights.setOnPreferenceClickListener(this);
      this.mStart_avm = (HCheckBoxPreference)this.findPreference("start_avm");
      this.mStart_avm.setOnPreferenceClickListener(this);
      this.mAvm_animan = (HCheckBoxPreference)this.findPreference("avm_animan");
      this.mAvm_animan.setOnPreferenceClickListener(this);
      this.mTurning_lighting = (HCheckBoxPreference)this.findPreference("turning_lighting");
      this.mTurning_lighting.setOnPreferenceClickListener(this);
      this.mUnlock = (HCheckBoxPreference)this.findPreference("unlock");
      this.mUnlock.setOnPreferenceClickListener(this);
      this.mRemote_open_trunk = (HCheckBoxPreference)this.findPreference("remote_open_trunk");
      this.mRemote_open_trunk.setOnPreferenceClickListener(this);
      this.mBoot_display_flow = (HCheckBoxPreference)this.findPreference("boot_display_flow");
      this.mBoot_display_flow.setOnPreferenceClickListener(this);
      this.mRear_view = this.findHCheckBoxPreference("rear_view");
      this.mFort_tips = (SwitchboxPreference)this.findPreference("fort_tips");
      this.mFort_tips.setOnPreferenceChangeListener(this);
      this.mCar_line = (SwitchboxPreference)this.findPreference("car_line");
      this.mCar_line.setOnPreferenceChangeListener(this);
      this.mOver_speed = (OnSwitchPreference)this.findPreference("over_speed");
      this.mOver_speed.setOnPreferenceChangeListener(this);
      this.mBacklighting = (OnSwitchPreference)this.findPreference("backlighting");
      this.mBacklighting.setOnPreferenceChangeListener(this);
      (new Thread(new Runnable(this) {
         final canbus98settings this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            for(int var1 = 1; var1 < 32; ++var1) {
               this.this$0.SendCanBusCmdData2E((byte)-112, new byte[]{64, (byte)var1}, 2);

               try {
                  Thread.sleep(100L);
               } catch (InterruptedException var3) {
                  var3.printStackTrace();
               }
            }

         }
      })).start();
      int var2 = BaseApplication.getInstance().getCarType();
      if (var2 != 1) {
         if (var2 != 2) {
            if (var2 != 3) {
               if (var2 == 4) {
                  this.removePreference((Preference)this.mBrake_alarm);
                  this.removePreference((Preference)this.mFort_tips);
                  this.removePreference((Preference)this.mBacklighting);
               }
            } else {
               this.removePreference((Preference)this.mBrake_alarm);
               this.removePreference((Preference)this.mFort_tips);
               this.removePreference((Preference)this.mTurning_lighting);
               this.removePreference((Preference)this.mUnlock);
               this.removePreference((Preference)this.mRemote_open_trunk);
               this.removePreference((Preference)this.mBoot_display_flow);
            }
         } else {
            this.removePreference((Preference)this.mBrake_alarm);
            this.removePreference((Preference)this.mFort_tips);
            this.removePreference((Preference)this.mTurning_lighting);
            this.removePreference((Preference)this.mUnlock);
            this.removePreference((Preference)this.mRemote_open_trunk);
            this.removePreference((Preference)this.mBoot_display_flow);
         }
      } else {
         this.removePreference((Preference)this.mBacklighting);
         this.removePreference((Preference)this.mTurning_lighting);
         this.removePreference((Preference)this.mUnlock);
         this.removePreference((Preference)this.mRemote_open_trunk);
         this.removePreference((Preference)this.mBoot_display_flow);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mOver_speed;
      int var3;
      int var4;
      if (var1 == var5) {
         var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var3 = this._Over_speed;
         if (var3 + var4 < 0 || var3 + var4 > 21 || !this.mOver_speed.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)9, (byte)(this._Over_speed + var4 & 255));
      } else {
         var5 = this.mBacklighting;
         if (var1 == var5) {
            var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var3 = this._Backlighting;
            if (var3 + var4 < 1 || var3 + var4 > 10 || !this.mBacklighting.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)10, (byte)(this._Backlighting + var4 & 255));
         }
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
         if (var1 == this.mFort_tips) {
            this.SetCmdkey((byte)1, (byte)var3);
         } else if (var1 == this.mCar_line) {
            this.SetCmdkey((byte)8, (byte)var3);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mBrake_alarm;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)2, (byte)0);
         } else {
            this.SetCmdkey((byte)2, (byte)1);
         }
      } else {
         var2 = this.mDriving_auto;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)3, (byte)0);
            } else {
               this.SetCmdkey((byte)3, (byte)1);
            }
         } else {
            var2 = this.mLamp_no;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)4, (byte)0);
               } else {
                  this.SetCmdkey((byte)4, (byte)1);
               }
            } else {
               var2 = this.mRunning_lights;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)5, (byte)0);
                  } else {
                     this.SetCmdkey((byte)5, (byte)1);
                  }
               } else {
                  var2 = this.mStart_avm;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)6, (byte)0);
                     } else {
                        this.SetCmdkey((byte)6, (byte)1);
                     }
                  } else {
                     var2 = this.mAvm_animan;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)7, (byte)0);
                        } else {
                           this.SetCmdkey((byte)7, (byte)1);
                        }
                     } else {
                        var2 = this.mTurning_lighting;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)11, (byte)0);
                           } else {
                              this.SetCmdkey((byte)11, (byte)1);
                           }
                        } else {
                           var2 = this.mUnlock;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)12, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)12, (byte)1);
                              }
                           } else {
                              var2 = this.mRemote_open_trunk;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)13, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)13, (byte)1);
                                 }
                              } else {
                                 var2 = this.mBoot_display_flow;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)14, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)14, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mRear_view;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)17, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)17, (byte)1);
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
