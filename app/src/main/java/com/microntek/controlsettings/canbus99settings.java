package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.provider.Settings.System;
import android.text.TextUtils;

public class canbus99settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Current_speed = 6;
   private int _Gohome = 0;
   private HCheckBoxPreference mAlarm_switch;
   private HCheckBoxPreference mAvg_oil_reset;
   private HCheckBoxPreference mAvg_speed_reset;
   private OnSwitchPreference mCurrent_speed;
   private HCheckBoxPreference mDriving_latch;
   private OnSwitchPreference mGohome;
   private HCheckBoxPreference mLock_signal;
   private SwitchboxPreference mOil_unit;
   private HCheckBoxPreference mOuto_security;
   private SwitchboxPreference mRear_door;
   private SwitchboxPreference mRemote_unlock;
   private HCheckBoxPreference mRpa_reset;
   private HCheckBoxPreference mRunning_lights;
   private SwitchboxPreference mTemp;
   private HCheckBoxPreference mTurn_signal;
   private HCheckBoxPreference mWelcome_light;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1, var2}, 2);
   }

   private void SetCmdkey0x83(byte[] var1, int var2) {
      var1[var2 >> 8] = (byte)(var2 & 255);
      this.SendCanBusCmdData2E((byte)-126, var1, 5);
   }

   private void SetCmdkey0x84(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-124, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   private void SetCmdkey0x9C(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-100, new byte[]{(byte)var1, (byte)(var2 >> 8), (byte)(var2 & 255)}, 3);
   }

   private byte[] getSettingCMD() {
      // $FF: Couldn't be decompiled
   }

   private byte[] getSettingCMD(String var1) {
      byte[] var4 = new byte[5];
      String var5 = System.getString(this.getContentResolver(), var1);
      byte[] var3 = var4;
      byte[] var11 = var4;

      label56: {
         boolean var10001;
         try {
            if (TextUtils.isEmpty(var5)) {
               return var3;
            }
         } catch (Exception var10) {
            var10001 = false;
            break label56;
         }

         var11 = var4;

         String[] var12;
         try {
            var12 = var5.split(",");
         } catch (Exception var9) {
            var10001 = false;
            break label56;
         }

         var11 = var4;

         try {
            var4 = new byte[var12.length];
         } catch (Exception var8) {
            var10001 = false;
            break label56;
         }

         int var2 = 0;

         while(true) {
            var3 = var4;
            var11 = var4;

            try {
               if (var2 >= var12.length) {
                  return var3;
               }
            } catch (Exception var7) {
               var10001 = false;
               break;
            }

            var11 = var4;

            try {
               var4[var2] = (byte)Integer.parseInt(var12[var2]);
            } catch (Exception var6) {
               var10001 = false;
               break;
            }

            ++var2;
         }
      }

      var3 = var11;
      return var3;
   }

   private void putSettingCMD(byte[] var1) {
      synchronized(this){}

      try {
         if (var1.length == 5) {
            StringBuilder var2 = new StringBuilder();
            var2.append(var1[0] & 255);
            var2.append(",");
            var2.append(var1[1] & 255);
            var2.append(",");
            var2.append(var1[2] & 255);
            var2.append(",");
            var2.append(var1[3] & 255);
            var2.append(",");
            var2.append(var1[4] & 255);
            var2.append(",");
            String var5 = var2.toString();
            System.putString(this.getContentResolver(), "Setting_0x82_CMD", var5);
         }
      } finally {
         ;
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      boolean var5 = false;
      boolean var6 = false;
      byte var4 = var1[0];
      int var3 = var1.length;
      HCheckBoxPreference var7;
      SwitchboxPreference var8;
      StringBuilder var13;
      if (var4 == 5 && var3 > 3) {
         this._Gohome = var1[2] >> 3 & 31;
         OnSwitchPreference var16 = this.mGohome;
         var13 = new StringBuilder();
         var13.append("");
         var13.append(this._Gohome * 10);
         var16.setValue("0", var13.toString());
         var7 = this.mTurn_signal;
         if ((var1[2] & 4) != 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         var7.setChecked(var5);
         var7 = this.mWelcome_light;
         if ((var1[2] & 1) != 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         var7.setChecked(var5);
         var7 = this.mRunning_lights;
         if ((var1[2] & 2) != 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         var7.setChecked(var5);
         var8 = this.mRemote_unlock;
         var13 = new StringBuilder();
         var13.append("");
         var13.append(var1[3] >> 4 & 1);
         var8.setValue(var13.toString());
         var8 = this.mRear_door;
         var13 = new StringBuilder();
         var13.append("");
         var13.append(var1[3] >> 3 & 1);
         var8.setValue(var13.toString());
         var7 = this.mOuto_security;
         if ((var1[3] & 4) != 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         var7.setChecked(var5);
         var7 = this.mDriving_latch;
         if ((var1[3] & 2) != 0) {
            var5 = true;
         } else {
            var5 = false;
         }

         var7.setChecked(var5);
         var7 = this.mLock_signal;
         var5 = var6;
         if ((var1[3] & 1) != 0) {
            var5 = true;
         }

         var7.setChecked(var5);
      } else if (var4 == 4 && var3 > 6) {
         byte[] var14 = this.getSettingCMD();
         var14[2] = var1[4];
         var14[3] = var1[5];
         var8 = this.mOil_unit;
         StringBuilder var10 = new StringBuilder();
         var10.append("");
         if (var14[2] != 0) {
            var2 = 1;
         }

         var10.append(var2);
         var8.setValue(var10.toString());
         SwitchboxPreference var11 = this.mTemp;
         StringBuilder var15 = new StringBuilder();
         var15.append("");
         var15.append(var14[3] & 1);
         var11.setValue(var15.toString());
         this.putSettingCMD(var14);
      } else if (var4 == 60 && var3 > 4) {
         var7 = this.mAlarm_switch;
         if ((var1[2] & 1) != 0) {
            var5 = true;
         }

         var7.setChecked(var5);
         byte var12 = var1[3];
         this._Current_speed = var1[4] & 255 | (var12 & 255) << 8;
         var2 = this._Current_speed;
         if (var2 >= 6 && var2 <= 260) {
            OnSwitchPreference var9 = this.mCurrent_speed;
            var13 = new StringBuilder();
            var13.append(this._Current_speed);
            var13.append(" km/h");
            var9.setValue("0", var13.toString());
         } else {
            this.mCurrent_speed.setValue("0", "");
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492952);
      this.mTurn_signal = (HCheckBoxPreference)this.findPreference("turn_signal");
      this.mTurn_signal.setOnPreferenceClickListener(this);
      this.mRunning_lights = (HCheckBoxPreference)this.findPreference("running_lights");
      this.mRunning_lights.setOnPreferenceClickListener(this);
      this.mWelcome_light = (HCheckBoxPreference)this.findPreference("welcome_light");
      this.mWelcome_light.setOnPreferenceClickListener(this);
      this.mOuto_security = (HCheckBoxPreference)this.findPreference("outo_security");
      this.mOuto_security.setOnPreferenceClickListener(this);
      this.mDriving_latch = (HCheckBoxPreference)this.findPreference("driving_latch");
      this.mDriving_latch.setOnPreferenceClickListener(this);
      this.mLock_signal = (HCheckBoxPreference)this.findPreference("lock_signal");
      this.mLock_signal.setOnPreferenceClickListener(this);
      this.mAlarm_switch = (HCheckBoxPreference)this.findPreference("alarm_switch");
      this.mAlarm_switch.setOnPreferenceClickListener(this);
      this.mRpa_reset = (HCheckBoxPreference)this.findPreference("rpa_reset");
      this.mRpa_reset.setOnPreferenceClickListener(this);
      this.mAvg_oil_reset = (HCheckBoxPreference)this.findPreference("avg_oil_reset");
      this.mAvg_oil_reset.setOnPreferenceClickListener(this);
      this.mAvg_speed_reset = (HCheckBoxPreference)this.findPreference("avg_speed_reset");
      this.mAvg_speed_reset.setOnPreferenceClickListener(this);
      this.mRemote_unlock = (SwitchboxPreference)this.findPreference("remote_unlock");
      this.mRemote_unlock.setOnPreferenceChangeListener(this);
      this.mRear_door = (SwitchboxPreference)this.findPreference("rear_door");
      this.mRear_door.setOnPreferenceChangeListener(this);
      this.mOil_unit = (SwitchboxPreference)this.findPreference("oil_unit");
      this.mOil_unit.setOnPreferenceChangeListener(this);
      this.mTemp = (SwitchboxPreference)this.findPreference("temp");
      this.mTemp.setOnPreferenceChangeListener(this);
      this.mGohome = (OnSwitchPreference)this.findPreference("gohome");
      this.mGohome.setOnPreferenceChangeListener(this);
      this.mCurrent_speed = (OnSwitchPreference)this.findPreference("current_speed");
      this.mCurrent_speed.setOnPreferenceChangeListener(this);
      this.ProcessData(this.getSettingCMD("canbus99_cmd_0x05"));
      this.ProcessData(this.getSettingCMD("canbus99_cmd_0x04"));
      this.ProcessData(this.getSettingCMD("canbus99_cmd_0x3C"));
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mGohome;
      int var3;
      int var4;
      byte var11;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this._Gohome;
         if (var4 + var3 < 0 || var4 + var3 > 24 || !this.mGohome.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)1, (byte)(this._Gohome + var3 & 255));
      } else {
         var5 = this.mCurrent_speed;
         if (var1 == var5) {
            var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var3 = this._Current_speed;
            if (var3 + var4 < 0 || var3 + var4 > 260 || !this.mCurrent_speed.getIsDow()) {
               return true;
            }

            if (this.mAlarm_switch.mChecked) {
               var11 = 1;
            } else {
               var11 = 0;
            }

            this.SetCmdkey0x9C(var11, this._Current_speed + var4);
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
         if (var1 == this.mRemote_unlock) {
            this.SetCmdkey((byte)5, (byte)var4);
         } else if (var1 == this.mRear_door) {
            this.SetCmdkey((byte)6, (byte)var4);
         } else {
            StringBuilder var9;
            SwitchboxPreference var10;
            if (var1 == this.mOil_unit) {
               byte[] var8 = this.getSettingCMD();
               if (var4 == 0) {
                  var11 = 0;
               } else {
                  var11 = 3;
               }

               this.SetCmdkey0x83(var8, var11 | 512);
               var10 = this.mOil_unit;
               var9 = new StringBuilder();
               var9.append("");
               var9.append(var4);
               var10.setValue(var9.toString());
            } else if (var1 == this.mTemp) {
               this.SetCmdkey0x83(this.getSettingCMD(), var4 | 768);
               var10 = this.mTemp;
               var9 = new StringBuilder();
               var9.append("");
               var9.append(var4);
               var10.setValue(var9.toString());
            }
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      boolean var9 = ((HCheckBoxPreference)var1).mChecked;
      HCheckBoxPreference var10 = this.mTurn_signal;
      byte var3 = 1;
      byte var7 = 1;
      byte var5 = 1;
      byte var6 = 1;
      byte var4 = 1;
      byte var8 = 1;
      byte var2 = 1;
      if (var1 == var10) {
         if (var9) {
            var2 = 0;
         }

         this.SetCmdkey((byte)2, var2);
      } else if (var1 == this.mRunning_lights) {
         var2 = var3;
         if (var9) {
            var2 = 0;
         }

         this.SetCmdkey((byte)3, var2);
      } else if (var1 == this.mWelcome_light) {
         var2 = var7;
         if (var9) {
            var2 = 0;
         }

         this.SetCmdkey((byte)4, var2);
      } else if (var1 == this.mOuto_security) {
         var2 = var5;
         if (var9) {
            var2 = 0;
         }

         this.SetCmdkey((byte)7, var2);
      } else if (var1 == this.mDriving_latch) {
         var2 = var6;
         if (var9) {
            var2 = 0;
         }

         this.SetCmdkey((byte)8, var2);
      } else if (var1 == this.mLock_signal) {
         var2 = var4;
         if (var9) {
            var2 = 0;
         }

         this.SetCmdkey((byte)9, var2);
      } else if (var1 == this.mAlarm_switch) {
         if (var9) {
            var8 = 0;
         }

         this.SetCmdkey0x9C(var8, this._Current_speed);
      } else {
         var10 = this.mRpa_reset;
         if (var1 == var10) {
            if (!var9) {
               var9 = true;
            } else {
               var9 = false;
            }

            var10.setChecked(var9);
            this.SetCmdkey0x84(1, 1);
         } else {
            var10 = this.mAvg_oil_reset;
            if (var1 == var10) {
               if (!var9) {
                  var9 = true;
               } else {
                  var9 = false;
               }

               var10.setChecked(var9);
               this.SetCmdkey0x84(5, 1);
            } else {
               var10 = this.mAvg_speed_reset;
               if (var1 == var10) {
                  if (!var9) {
                     var9 = true;
                  } else {
                     var9 = false;
                  }

                  var10.setChecked(var9);
                  this.SetCmdkey0x84(6, 1);
               }
            }
         }
      }

      return false;
   }
}
