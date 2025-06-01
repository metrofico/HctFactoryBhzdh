package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus79settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private HCheckBoxPreference mBBfog;
   private HCheckBoxPreference mBNear_lig;
   private HCheckBoxPreference mBReversing_lamp;
   private HCheckBoxPreference mBfog;
   private int mCar = 0;
   private SwitchboxPreference mCar_time;
   private HCheckBoxPreference mDriving_latch;
   private int mHome = 0;
   private SwitchboxPreference mHome_time;
   private SwitchboxPreference mLock_mode;
   private HCheckBoxPreference mNear_lig;
   private SwitchboxPreference mNear_unlock;
   private HCheckBoxPreference mReversing_lamp;
   private HCheckBoxPreference mUnlock;

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2, var3}, 3);
   }

   protected void ProcessData(byte[] var1) {
      boolean var3 = false;
      if (var1[0] == 64) {
         HCheckBoxPreference var4 = this.mDriving_latch;
         boolean var2;
         if ((var1[2] & 128) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mUnlock;
         if ((var1[2] & 64) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         SwitchboxPreference var5 = this.mLock_mode;
         StringBuilder var6 = new StringBuilder();
         var6.append("");
         var6.append(var1[2] >> 5 & 1);
         var5.setValue(var6.toString());
         SwitchboxPreference var7 = this.mNear_unlock;
         StringBuilder var8 = new StringBuilder();
         var8.append("");
         var8.append(var1[2] >> 4 & 1);
         var7.setValue(var8.toString());
         this.mHome = var1[3] & 224;
         var4 = this.mReversing_lamp;
         if ((var1[3] & 128) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mNear_lig;
         if ((var1[3] & 64) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mBfog;
         if ((var1[3] & 32) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var7 = this.mHome_time;
         var8 = new StringBuilder();
         var8.append("");
         var8.append(var1[3] & 15);
         var7.setValue(var8.toString());
         this.mCar = var1[4] & 224;
         var4 = this.mBReversing_lamp;
         if ((var1[4] & 128) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mBNear_lig;
         if ((64 & var1[4]) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         var4.setChecked(var2);
         var4 = this.mBBfog;
         var2 = var3;
         if ((var1[4] & 32) != 0) {
            var2 = true;
         }

         var4.setChecked(var2);
         var7 = this.mCar_time;
         var8 = new StringBuilder();
         var8.append("");
         var8.append(var1[4] & 15);
         var7.setValue(var8.toString());
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492942);
      this.mDriving_latch = (HCheckBoxPreference)this.findPreference("driving_latch");
      this.mDriving_latch.setOnPreferenceClickListener(this);
      this.mUnlock = (HCheckBoxPreference)this.findPreference("unlock");
      this.mUnlock.setOnPreferenceClickListener(this);
      this.mReversing_lamp = (HCheckBoxPreference)this.findPreference("reversing_lamp");
      this.mReversing_lamp.setOnPreferenceClickListener(this);
      this.mNear_lig = (HCheckBoxPreference)this.findPreference("near_lig");
      this.mNear_lig.setOnPreferenceClickListener(this);
      this.mBfog = (HCheckBoxPreference)this.findPreference("bfog");
      this.mBfog.setOnPreferenceClickListener(this);
      this.mBReversing_lamp = (HCheckBoxPreference)this.findPreference("b_reversing_lamp");
      this.mBReversing_lamp.setOnPreferenceClickListener(this);
      this.mBNear_lig = (HCheckBoxPreference)this.findPreference("b_near_lig");
      this.mBNear_lig.setOnPreferenceClickListener(this);
      this.mBBfog = (HCheckBoxPreference)this.findPreference("b_bfog");
      this.mBBfog.setOnPreferenceClickListener(this);
      this.mLock_mode = (SwitchboxPreference)this.findPreference("lock_mode");
      this.mLock_mode.setOnPreferenceChangeListener(this);
      this.mNear_unlock = (SwitchboxPreference)this.findPreference("near_unlock");
      this.mNear_unlock.setOnPreferenceChangeListener(this);
      this.mHome_time = (SwitchboxPreference)this.findPreference("home_time");
      this.mHome_time.setOnPreferenceChangeListener(this);
      this.mCar_time = (SwitchboxPreference)this.findPreference("car_time");
      this.mCar_time.setOnPreferenceChangeListener(this);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{64}, 1);
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
         if (var1 == this.mLock_mode) {
            this.SetCmdkey((byte)1, (byte)3, (byte)var4);
         } else if (var1 == this.mNear_unlock) {
            this.SetCmdkey((byte)1, (byte)4, (byte)var4);
         } else if (var1 == this.mHome_time) {
            this.SetCmdkey((byte)2, (byte)2, (byte)var4);
         } else if (var1 == this.mCar_time) {
            this.SetCmdkey((byte)2, (byte)4, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mDriving_latch;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)1, (byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)1, (byte)1, (byte)1);
         }
      } else {
         var2 = this.mUnlock;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)1, (byte)2, (byte)0);
            } else {
               this.SetCmdkey((byte)1, (byte)2, (byte)2);
            }
         } else {
            var2 = this.mReversing_lamp;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.mHome ^= 128;
                  this.SetCmdkey((byte)2, (byte)1, (byte)this.mHome);
               } else {
                  this.mHome |= 128;
                  this.SetCmdkey((byte)2, (byte)1, (byte)this.mHome);
               }
            } else {
               var2 = this.mNear_lig;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.mHome ^= 64;
                     this.SetCmdkey((byte)2, (byte)1, (byte)this.mHome);
                  } else {
                     this.mHome |= 64;
                     this.SetCmdkey((byte)2, (byte)1, (byte)this.mHome);
                  }
               } else {
                  var2 = this.mBfog;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.mHome ^= 32;
                        this.SetCmdkey((byte)2, (byte)1, (byte)this.mHome);
                     } else {
                        this.mHome |= 32;
                        this.SetCmdkey((byte)2, (byte)1, (byte)this.mHome);
                     }
                  } else {
                     var2 = this.mBReversing_lamp;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.mCar ^= 128;
                           this.SetCmdkey((byte)2, (byte)3, (byte)this.mCar);
                        } else {
                           this.mCar |= 128;
                           this.SetCmdkey((byte)2, (byte)3, (byte)this.mCar);
                        }
                     } else {
                        var2 = this.mBNear_lig;
                        if (var1 == var2) {
                           if (var2.mChecked) {
                              this.mCar ^= 64;
                              this.SetCmdkey((byte)2, (byte)3, (byte)this.mCar);
                           } else {
                              this.mCar |= 64;
                              this.SetCmdkey((byte)2, (byte)3, (byte)this.mCar);
                           }
                        } else {
                           var2 = this.mBBfog;
                           if (var1 == var2) {
                              if (var2.mChecked) {
                                 this.mCar ^= 32;
                                 this.SetCmdkey((byte)2, (byte)3, (byte)this.mCar);
                              } else {
                                 this.mCar |= 32;
                                 this.SetCmdkey((byte)2, (byte)3, (byte)this.mCar);
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
