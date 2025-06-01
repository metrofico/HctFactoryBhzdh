package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings.System;

public class canbus60settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mAri_auto;
   private HCheckBoxPreference mAuto_lock;
   private HCheckBoxPreference mAuto_unlock;
   private SwitchboxPreference mB_radar;
   private HCheckBoxPreference mCycle_auto;
   private HCheckBoxPreference mDriver_door;
   private SwitchboxPreference mF_radar;
   private SwitchboxPreference mHeadlamps;
   private HCheckBoxPreference mIntelligent_lock;
   private SwitchboxPreference mIntelligent_unlock;
   private HCheckBoxPreference mKey2press;
   private SwitchboxPreference mLight_Off_time;
   private HCheckBoxPreference mLock_by_speed;
   private HCheckBoxPreference mLock_emergency;
   private HCheckBoxPreference mRadar_dis;
   private HCheckBoxPreference mRemote2press;
   private SwitchboxPreference mToyota;
   private SwitchboxPreference mVol_radar;
   private byte[] setData = new byte[30];

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{var1, var2, var3}, 3);
   }

   private void updataSettings62() {
      HCheckBoxPreference var3 = this.mLock_by_speed;
      byte[] var4 = this.setData;
      boolean var2 = true;
      boolean var1;
      if ((var4[1] & 64) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      SwitchboxPreference var6 = this.mIntelligent_unlock;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      var5.append(this.setData[1] >> 5 & 1);
      var6.setValue(var5.toString());
      var3 = this.mDriver_door;
      if ((this.setData[1] & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mAuto_unlock;
      if ((this.setData[1] & 8) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mAuto_lock;
      if ((this.setData[1] & 4) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mAri_auto;
      if ((this.setData[1] & 2) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mCycle_auto;
      if ((this.setData[1] & 1) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mRadar_dis;
      if ((this.setData[0] & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var6 = this.mVol_radar;
      var5 = new StringBuilder();
      var5.append("");
      var5.append((this.setData[0] >> 4 & 7) - 1);
      var6.setValue(var5.toString());
      SwitchboxPreference var8 = this.mF_radar;
      StringBuilder var7 = new StringBuilder();
      var7.append("");
      var7.append((this.setData[0] >> 2 & 3) - 1);
      var8.setValue(var7.toString());
      var8 = this.mB_radar;
      var7 = new StringBuilder();
      var7.append("");
      var7.append((this.setData[0] >> 0 & 3) - 1);
      var8.setValue(var7.toString());
      var3 = this.mLock_emergency;
      if ((this.setData[2] & 128) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mIntelligent_lock;
      if ((this.setData[2] & 32) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mKey2press;
      if ((this.setData[2] & 16) != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var3 = this.mRemote2press;
      if ((this.setData[2] & 64) != 0) {
         var1 = var2;
      } else {
         var1 = false;
      }

      var3.setChecked(var1);
      var8 = this.mLight_Off_time;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(this.setData[3] >> 3 & 3);
      var8.setValue(var7.toString());
      var6 = this.mHeadlamps;
      var5 = new StringBuilder();
      var5.append("");
      var5.append(this.setData[3] >> 0 & 7);
      var6.setValue(var5.toString());
   }

   protected void ProcessData(byte[] var1) {
      byte var4 = var1[0];
      int var3 = var1.length;
      int var2 = 2;
      if (var3 > 2 && var3 <= 29 && var4 == 98) {
         while(var2 < var3) {
            this.setData[var2 - 2] = var1[var2];
            ++var2;
         }

         this.updataSettings62();
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492932);
      this.mLock_by_speed = (HCheckBoxPreference)this.findPreference("lock_by_speed");
      this.mLock_by_speed.setOnPreferenceClickListener(this);
      this.mDriver_door = (HCheckBoxPreference)this.findPreference("driver_door");
      this.mDriver_door.setOnPreferenceClickListener(this);
      this.mAuto_unlock = (HCheckBoxPreference)this.findPreference("auto_unlock");
      this.mAuto_unlock.setOnPreferenceClickListener(this);
      this.mAuto_lock = (HCheckBoxPreference)this.findPreference("auto_lock");
      this.mAuto_lock.setOnPreferenceClickListener(this);
      this.mAri_auto = (HCheckBoxPreference)this.findPreference("ari_auto");
      this.mAri_auto.setOnPreferenceClickListener(this);
      this.mCycle_auto = (HCheckBoxPreference)this.findPreference("cycle_auto");
      this.mCycle_auto.setOnPreferenceClickListener(this);
      this.mRadar_dis = (HCheckBoxPreference)this.findPreference("radar_dis");
      this.mRadar_dis.setOnPreferenceClickListener(this);
      this.mLock_emergency = (HCheckBoxPreference)this.findPreference("lock_emergency");
      this.mLock_emergency.setOnPreferenceClickListener(this);
      this.mIntelligent_lock = (HCheckBoxPreference)this.findPreference("intelligent_lock");
      this.mIntelligent_lock.setOnPreferenceClickListener(this);
      this.mKey2press = (HCheckBoxPreference)this.findPreference("key2press");
      this.mKey2press.setOnPreferenceClickListener(this);
      this.mRemote2press = (HCheckBoxPreference)this.findPreference("remote2press");
      this.mRemote2press.setOnPreferenceClickListener(this);
      this.mIntelligent_unlock = (SwitchboxPreference)this.findPreference("intelligent_unlock");
      this.mIntelligent_unlock.setOnPreferenceChangeListener(this);
      this.mVol_radar = (SwitchboxPreference)this.findPreference("vol_radar");
      this.mVol_radar.setOnPreferenceChangeListener(this);
      this.mF_radar = (SwitchboxPreference)this.findPreference("f_radar");
      this.mF_radar.setOnPreferenceChangeListener(this);
      this.mB_radar = (SwitchboxPreference)this.findPreference("b_radar");
      this.mB_radar.setOnPreferenceChangeListener(this);
      this.mHeadlamps = (SwitchboxPreference)this.findPreference("headlamps");
      this.mHeadlamps.setOnPreferenceChangeListener(this);
      this.mLight_Off_time = (SwitchboxPreference)this.findPreference("light_Off_time");
      this.mLight_Off_time.setOnPreferenceChangeListener(this);
      this.mToyota = (SwitchboxPreference)this.findPreference("toyota");
      this.mToyota.setOnPreferenceChangeListener(this);
      int var2 = System.getInt(this.getContentResolver(), "com.microntek.controlsettings.car.type", 1);
      SwitchboxPreference var3 = this.mToyota;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      var4.append(var2 - 1);
      var3.setValue(var4.toString());
      this.removePreference(this.findPreference("car_type_cmd"));
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
         if (var1 == this.mIntelligent_unlock) {
            this.SetCmdkey((byte)1, (byte)2, (byte)var3);
         } else if (var1 == this.mVol_radar) {
            this.SetCmdkey((byte)1, (byte)9, (byte)(var3 + 1));
         } else if (var1 == this.mF_radar) {
            this.SetCmdkey((byte)1, (byte)10, (byte)1);
         } else if (var1 == this.mB_radar) {
            this.SetCmdkey((byte)1, (byte)10, (byte)2);
         } else if (var1 == this.mHeadlamps) {
            this.SetCmdkey((byte)3, (byte)1, (byte)var3);
         } else if (var1 == this.mLight_Off_time) {
            this.SetCmdkey((byte)3, (byte)2, (byte)var3);
         } else if (var1 == this.mToyota) {
            var4 = var3 + 1;
            this.SendCanBusCmdData5AA5((byte)45, new byte[]{1, (byte)var4}, 2);
            SwitchboxPreference var7 = this.mToyota;
            StringBuilder var8 = new StringBuilder();
            var8.append("");
            var8.append(var3);
            var7.setValue(var8.toString());
            System.putInt(this.getContentResolver(), "com.microntek.controlsettings.car.type", var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mLock_by_speed;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)1, (byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)1, (byte)1, (byte)1);
         }
      } else {
         var2 = this.mDriver_door;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)1, (byte)3, (byte)0);
            } else {
               this.SetCmdkey((byte)1, (byte)3, (byte)1);
            }
         } else {
            var2 = this.mAuto_unlock;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)1, (byte)4, (byte)0);
               } else {
                  this.SetCmdkey((byte)1, (byte)4, (byte)1);
               }
            } else {
               var2 = this.mAuto_lock;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)1, (byte)5, (byte)0);
                  } else {
                     this.SetCmdkey((byte)1, (byte)5, (byte)1);
                  }
               } else {
                  var2 = this.mCycle_auto;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)1, (byte)7, (byte)0);
                     } else {
                        this.SetCmdkey((byte)1, (byte)7, (byte)1);
                     }
                  } else {
                     var2 = this.mRadar_dis;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)1, (byte)8, (byte)0);
                        } else {
                           this.SetCmdkey((byte)1, (byte)8, (byte)1);
                        }
                     } else {
                        var2 = this.mLock_emergency;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.SetCmdkey((byte)2, (byte)1, (byte)0);
                           } else {
                              this.SetCmdkey((byte)2, (byte)1, (byte)1);
                           }
                        } else {
                           var2 = this.mIntelligent_lock;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.SetCmdkey((byte)2, (byte)2, (byte)0);
                              } else {
                                 this.SetCmdkey((byte)2, (byte)2, (byte)1);
                              }
                           } else {
                              var2 = this.mKey2press;
                              if (var1 == var2) {
                                 if (var2.mChecked) {
                                    this.SetCmdkey((byte)2, (byte)3, (byte)0);
                                 } else {
                                    this.SetCmdkey((byte)2, (byte)3, (byte)1);
                                 }
                              } else {
                                 var2 = this.mRemote2press;
                                 if (var1 == var2) {
                                    if (var2.mChecked) {
                                       this.SetCmdkey((byte)2, (byte)4, (byte)0);
                                    } else {
                                       this.SetCmdkey((byte)2, (byte)4, (byte)1);
                                    }
                                 } else {
                                    var2 = this.mAri_auto;
                                    if (var1 == var2) {
                                       if (var2.mChecked) {
                                          this.SetCmdkey((byte)1, (byte)6, (byte)0);
                                       } else {
                                          this.SetCmdkey((byte)1, (byte)6, (byte)1);
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
