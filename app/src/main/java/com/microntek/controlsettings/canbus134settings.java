package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus134settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mActive_rear;
   private HCheckBoxPreference mDaytime_lights;
   private HCheckBoxPreference mDis_trip_b;
   private SwitchboxPreference mHeadlight_delay;
   private HCheckBoxPreference mLights_flash;
   private SwitchboxPreference mMileage_unit;
   private SwitchboxPreference mOil_unit;
   private HCheckBoxPreference mOutlock;
   private SwitchboxPreference mParking_set;
   private HCheckBoxPreference mRear_delay;
   private SwitchboxPreference mRear_vol;
   private SwitchboxPreference mTemp;
   private HCheckBoxPreference mTurn_lights_set;
   private SwitchboxPreference mUnit_measure;
   private HCheckBoxPreference mWelcome_light;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData5AA5((byte)-54, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   private void SetCmdkey7B(int var1, int var2) {
      this.SendCanBusCmdData5AA5((byte)123, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var5 = false;
      boolean var6 = false;
      byte var3 = 0;
      super.cmd = var1[0] & 255;
      int var2 = super.cmd;
      boolean var4;
      SwitchboxPreference var7;
      if (var2 == 193) {
         var7 = this.mMileage_unit;
         if ((var1[2] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var7, var4);
         var7 = this.mTemp;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var7, var4);
         var7 = this.mOil_unit;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var7, var4);
         var7 = this.mUnit_measure;
         if ((var1[2] & 1) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var7, var4);
         var7 = this.mMileage_unit;
         byte var8;
         if ((var1[3] >> 7 & 1) == 1) {
            var8 = 0;
         } else {
            var8 = 1;
         }

         var7.setValue(var8);
         var7 = this.mTemp;
         if ((var1[3] >> 5 & 1) == 1) {
            var8 = var3;
         } else {
            var8 = 1;
         }

         var7.setValue(var8);
         this.mOil_unit.setValue(var1[3] >> 1 & 3);
         this.mUnit_measure.setValue(var1[4] & 3);
      } else {
         HCheckBoxPreference var9;
         if (var2 == 113) {
            var9 = this.mDaytime_lights;
            if ((var1[3] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var9, var4);
            var9 = this.mOutlock;
            if ((var1[3] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var9, var4);
            var7 = this.mParking_set;
            if ((2 & var1[4]) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var7, var4);
            var7 = this.mRear_vol;
            if ((var1[4] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var7, var4);
            var9 = this.mWelcome_light;
            if ((var1[4] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var9, var4);
            var9 = this.mTurn_lights_set;
            if ((var1[4] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var9, var4);
            var9 = this.mLights_flash;
            if ((var1[4] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var9, var4);
            var9 = this.mActive_rear;
            if ((var1[4] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var9, var4);
            var9 = this.mRear_delay;
            if ((var1[5] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var9, var4);
            var7 = this.mHeadlight_delay;
            if ((var1[5] & 1) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            this.enabledPreference(var7, var4);
            var9 = this.mDis_trip_b;
            var4 = var5;
            if ((var1[4] & 1) != 0) {
               var4 = true;
            }

            this.enabledPreference(var9, var4);
         } else if (var2 == 118) {
            var9 = this.mDaytime_lights;
            if ((var1[3] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mOutlock;
            if ((var1[3] & 32) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            this.mParking_set.setValue(var1[4] >> 1 & 1);
            this.mRear_vol.setValue(var1[4] >> 5 & 3);
            var9 = this.mWelcome_light;
            if ((var1[4] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mTurn_lights_set;
            if ((var1[4] & 8) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mLights_flash;
            if ((var1[4] & 16) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mActive_rear;
            if ((var1[4] & 128) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            var9 = this.mRear_delay;
            if ((var1[5] & 4) != 0) {
               var4 = true;
            } else {
               var4 = false;
            }

            var9.setChecked(var4);
            this.mHeadlight_delay.setValue(var1[5] & 3);
            var9 = this.mDis_trip_b;
            var4 = var6;
            if ((var1[4] & 1) != 0) {
               var4 = true;
            }

            var9.setChecked(var4);
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492876);
      this.mMileage_unit = this.findSwitchboxPreference("mileage_unit");
      this.mTemp = this.findSwitchboxPreference("temp");
      this.mOil_unit = this.findSwitchboxPreference("oil_unit");
      this.mUnit_measure = this.findSwitchboxPreference("unit_measure");
      this.mParking_set = this.findSwitchboxPreference("parking_set");
      this.mRear_vol = this.findSwitchboxPreference("rear_vol");
      this.mHeadlight_delay = this.findSwitchboxPreference("headlight_delay");
      this.mDaytime_lights = this.findHCheckBoxPreference("daytime_lights");
      this.mOutlock = this.findHCheckBoxPreference("outlock");
      this.mWelcome_light = this.findHCheckBoxPreference("welcome_light");
      this.mTurn_lights_set = this.findHCheckBoxPreference("turn_lights_set");
      this.mLights_flash = this.findHCheckBoxPreference("lights_flash");
      this.mActive_rear = this.findHCheckBoxPreference("active_rear");
      this.mRear_delay = this.findHCheckBoxPreference("rear_delay");
      this.mDis_trip_b = this.findHCheckBoxPreference("dis_trip_b");
      this.ProcessData(this.GetCarByteArrayState(193));
      this.ProcessData(this.GetCarByteArrayState(113));
      this.ProcessData(this.GetCarByteArrayState(118));
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3;
      try {
         var3 = Integer.parseInt((String)var2);
      } catch (Exception var5) {
         return false;
      }

      int var4;
      try {
         var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mMileage_unit) {
            this.SetCmdkey(1, var3 + 1);
         } else if (var1 == this.mTemp) {
            this.SetCmdkey(3, var3 + 1);
         } else if (var1 == this.mOil_unit) {
            this.SetCmdkey(5, var3 + 1);
         } else if (var1 == this.mUnit_measure) {
            this.SetCmdkey(7, var3);
         } else if (var1 == this.mParking_set) {
            this.SetCmdkey7B(17, var3);
         } else if (var1 == this.mRear_vol) {
            this.SetCmdkey7B(18, var3);
         } else if (var1 == this.mHeadlight_delay) {
            this.SetCmdkey7B(23, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var11 = ((HCheckBoxPreference)var2).mChecked;
      HCheckBoxPreference var12 = this.mDaytime_lights;
      byte var3 = 0;
      byte var8 = 0;
      byte var10 = 0;
      byte var9 = 0;
      byte var4 = 0;
      byte var6 = 0;
      byte var5 = 0;
      byte var7 = 0;
      if (var2 == var12) {
         if (var11) {
            var3 = var7;
         } else {
            var3 = 1;
         }

         this.SetCmdkey7B(5, var3);
      } else if (var2 == this.mOutlock) {
         if (!var11) {
            var3 = 1;
         }

         this.SetCmdkey7B(12, var3);
      } else if (var2 == this.mWelcome_light) {
         if (var11) {
            var3 = var8;
         } else {
            var3 = 1;
         }

         this.SetCmdkey7B(19, var3);
      } else if (var2 == this.mTurn_lights_set) {
         if (var11) {
            var3 = var10;
         } else {
            var3 = 1;
         }

         this.SetCmdkey7B(20, var3);
      } else if (var2 == this.mLights_flash) {
         if (var11) {
            var3 = var9;
         } else {
            var3 = 1;
         }

         this.SetCmdkey7B(21, var3);
      } else if (var2 == this.mActive_rear) {
         if (var11) {
            var3 = var4;
         } else {
            var3 = 1;
         }

         this.SetCmdkey7B(22, var3);
      } else if (var2 == this.mRear_delay) {
         if (var11) {
            var3 = var6;
         } else {
            var3 = 1;
         }

         this.SetCmdkey7B(24, var3);
      } else if (var2 == this.mDis_trip_b) {
         if (var11) {
            var3 = var5;
         } else {
            var3 = 1;
         }

         this.SetCmdkey7B(16, var3);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
