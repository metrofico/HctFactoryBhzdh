package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus59settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private CharSequence[] entries;
   private SwitchboxPreference mAro_concen;
   private SwitchboxPreference mAro_type;
   private SwitchboxPreference mAtmosphere_lighting;
   private HCheckBoxPreference mAuto_door;
   private HCheckBoxPreference mAuto_parking;
   private PreferenceScreen mCarCmd;
   private HCheckBoxPreference mCruising1;
   private SwitchboxPreference mCruising1_values;
   private HCheckBoxPreference mCruising2;
   private SwitchboxPreference mCruising2_values;
   private HCheckBoxPreference mCruising3;
   private SwitchboxPreference mCruising3_values;
   private HCheckBoxPreference mCruising4;
   private SwitchboxPreference mCruising4_values;
   private HCheckBoxPreference mCruising5;
   private SwitchboxPreference mCruising5_values;
   private HCheckBoxPreference mCruising_cmd;
   private PreferenceScreen mCruising_screen;
   private HCheckBoxPreference mDoor_lock;
   private SwitchboxPreference mDoorlock;
   private SwitchboxPreference mDriving_mode;
   private PreferenceScreen mEngine_stop;
   private HCheckBoxPreference mFatigueystem;
   private HCheckBoxPreference mHeadlights;
   private SwitchboxPreference mHome_lighting;
   private SwitchboxPreference mIn_light;
   private PreferenceScreen mInit_mode;
   private SwitchboxPreference mInstrument_color;
   private SwitchboxPreference mIon_purifier;
   private HCheckBoxPreference mLane_change;
   private HCheckBoxPreference mLanesystem;
   private SwitchboxPreference mLeft_dashboard;
   private SwitchboxPreference mOil_unit;
   private HCheckBoxPreference mParking_assist;
   private HCheckBoxPreference mRadarswitch;
   private HCheckBoxPreference mReverse_vol;
   private SwitchboxPreference mRight_dashboard;
   private HCheckBoxPreference mRunning_lights;
   private SwitchboxPreference mScreen3;
   private HCheckBoxPreference mSpeed1;
   private SwitchboxPreference mSpeed1_values;
   private HCheckBoxPreference mSpeed2;
   private SwitchboxPreference mSpeed2_values;
   private HCheckBoxPreference mSpeed3;
   private SwitchboxPreference mSpeed3_values;
   private HCheckBoxPreference mSpeed4;
   private SwitchboxPreference mSpeed4_values;
   private HCheckBoxPreference mSpeed5;
   private SwitchboxPreference mSpeed5_values;
   private HCheckBoxPreference mSpeed_cmd;
   private PreferenceScreen mSpeed_screen;
   private HCheckBoxPreference mSpeed_warning;
   private SwitchboxPreference mTemp;
   private PreferenceScreen mTpmsCheck;
   private PreferenceScreen mUnit_set;
   private HCheckBoxPreference mUnlock_trunk_only;
   private HCheckBoxPreference mWelcome_cmd;
   private SwitchboxPreference mWelcome_lig;
   private HCheckBoxPreference mWipers;
   private CharSequence[] values;

   private void SetCmdkey(int var1, int var2, int var3) {
      byte var5 = (byte)var2;
      byte var4 = (byte)var3;
      this.SendCanBusCmdData5AA5((byte)var1, new byte[]{var5, var4}, 2);
   }

   private void SetCruisingCmdkey(Preference var1, int var2) {
      byte[] var9 = new byte[10];
      HCheckBoxPreference var10 = this.mCruising_cmd;
      int var3;
      if (var1 == var10) {
         var3 = var2;
      } else if (var10.mChecked) {
         var3 = 128;
      } else {
         var3 = 0;
      }

      var10 = this.mCruising1;
      int var4;
      if (var1 == var10) {
         var4 = var2;
      } else if (var10.mChecked) {
         var4 = 64;
      } else {
         var4 = 0;
      }

      var10 = this.mCruising2;
      int var5;
      if (var1 == var10) {
         var5 = var2;
      } else if (var10.mChecked) {
         var5 = 32;
      } else {
         var5 = 0;
      }

      var10 = this.mCruising3;
      int var6;
      if (var1 == var10) {
         var6 = var2;
      } else if (var10.mChecked) {
         var6 = 16;
      } else {
         var6 = 0;
      }

      var10 = this.mCruising4;
      int var7;
      if (var1 == var10) {
         var7 = var2;
      } else if (var10.mChecked) {
         var7 = 8;
      } else {
         var7 = 0;
      }

      var10 = this.mCruising5;
      int var8;
      if (var1 == var10) {
         var8 = var2;
      } else if (var10.mChecked) {
         var8 = 4;
      } else {
         var8 = 0;
      }

      var9[0] = (byte)(var3 | var4 | var5 | var6 | var7 | var8);
      SwitchboxPreference var11 = this.mCruising1_values;
      if (var1 == var11) {
         var3 = var2;
      } else {
         var3 = Integer.parseInt(var11.getValue());
      }

      var9[1] = (byte)var3;
      var11 = this.mCruising2_values;
      if (var1 == var11) {
         var3 = var2;
      } else {
         var3 = Integer.parseInt(var11.getValue());
      }

      var9[2] = (byte)var3;
      var11 = this.mCruising3_values;
      if (var1 == var11) {
         var3 = var2;
      } else {
         var3 = Integer.parseInt(var11.getValue());
      }

      var9[3] = (byte)var3;
      var11 = this.mCruising4_values;
      if (var1 == var11) {
         var3 = var2;
      } else {
         var3 = Integer.parseInt(var11.getValue());
      }

      var9[4] = (byte)var3;
      var11 = this.mCruising5_values;
      if (var1 != var11) {
         var2 = Integer.parseInt(var11.getValue());
      }

      var9[5] = (byte)var2;
      this.SendCanBusCmdData5AA5((byte)-117, var9, 10);
   }

   private void SetSpeedCmdkey(Preference var1, int var2) {
      boolean var10001;
      int var3;
      byte[] var9;
      label239: {
         label244: {
            try {
               var9 = new byte[10];
               if (var1 == this.mSpeed_cmd) {
                  break label244;
               }
            } catch (Exception var32) {
               var10001 = false;
               return;
            }

            label233: {
               try {
                  if (this.mSpeed_cmd.mChecked) {
                     break label233;
                  }
               } catch (Exception var31) {
                  var10001 = false;
                  return;
               }

               var3 = 0;
               break label239;
            }

            var3 = 128;
            break label239;
         }

         var3 = var2;
      }

      int var4;
      label225: {
         label245: {
            try {
               if (var1 == this.mSpeed1) {
                  break label245;
               }
            } catch (Exception var30) {
               var10001 = false;
               return;
            }

            label218: {
               try {
                  if (this.mSpeed1.mChecked) {
                     break label218;
                  }
               } catch (Exception var29) {
                  var10001 = false;
                  return;
               }

               var4 = 0;
               break label225;
            }

            var4 = 64;
            break label225;
         }

         var4 = var2;
      }

      int var5;
      label211: {
         label246: {
            try {
               if (var1 == this.mSpeed2) {
                  break label246;
               }
            } catch (Exception var28) {
               var10001 = false;
               return;
            }

            label204: {
               try {
                  if (this.mSpeed2.mChecked) {
                     break label204;
                  }
               } catch (Exception var27) {
                  var10001 = false;
                  return;
               }

               var5 = 0;
               break label211;
            }

            var5 = 32;
            break label211;
         }

         var5 = var2;
      }

      int var6;
      label197: {
         label247: {
            try {
               if (var1 == this.mSpeed3) {
                  break label247;
               }
            } catch (Exception var26) {
               var10001 = false;
               return;
            }

            label190: {
               try {
                  if (this.mSpeed3.mChecked) {
                     break label190;
                  }
               } catch (Exception var25) {
                  var10001 = false;
                  return;
               }

               var6 = 0;
               break label197;
            }

            var6 = 16;
            break label197;
         }

         var6 = var2;
      }

      int var7;
      label248: {
         label182: {
            try {
               if (var1 != this.mSpeed4) {
                  break label182;
               }
            } catch (Exception var24) {
               var10001 = false;
               return;
            }

            var7 = var2;
            break label248;
         }

         label176: {
            try {
               if (this.mSpeed4.mChecked) {
                  break label176;
               }
            } catch (Exception var23) {
               var10001 = false;
               return;
            }

            var7 = 0;
            break label248;
         }

         var7 = 8;
      }

      int var8;
      label249: {
         label168: {
            try {
               if (var1 != this.mSpeed5) {
                  break label168;
               }
            } catch (Exception var22) {
               var10001 = false;
               return;
            }

            var8 = var2;
            break label249;
         }

         label162: {
            try {
               if (this.mSpeed5.mChecked) {
                  break label162;
               }
            } catch (Exception var21) {
               var10001 = false;
               return;
            }

            var8 = 0;
            break label249;
         }

         var8 = 4;
      }

      var9[0] = (byte)(var3 | var4 | var5 | var6 | var7 | var8);

      label154: {
         label153: {
            try {
               if (var1 == this.mSpeed1_values) {
                  break label153;
               }
            } catch (Exception var20) {
               var10001 = false;
               return;
            }

            try {
               var3 = Integer.parseInt(this.mSpeed1_values.getValue());
               break label154;
            } catch (Exception var19) {
               var10001 = false;
               return;
            }
         }

         var3 = var2;
      }

      var9[1] = (byte)var3;

      label144: {
         label143: {
            try {
               if (var1 == this.mSpeed2_values) {
                  break label143;
               }
            } catch (Exception var18) {
               var10001 = false;
               return;
            }

            try {
               var3 = Integer.parseInt(this.mSpeed2_values.getValue());
               break label144;
            } catch (Exception var17) {
               var10001 = false;
               return;
            }
         }

         var3 = var2;
      }

      var9[2] = (byte)var3;

      label134: {
         label133: {
            try {
               if (var1 != this.mSpeed3_values) {
                  break label133;
               }
            } catch (Exception var16) {
               var10001 = false;
               return;
            }

            var3 = var2;
            break label134;
         }

         try {
            var3 = Integer.parseInt(this.mSpeed3_values.getValue());
         } catch (Exception var15) {
            var10001 = false;
            return;
         }
      }

      var9[3] = (byte)var3;

      label124: {
         label123: {
            try {
               if (var1 != this.mSpeed4_values) {
                  break label123;
               }
            } catch (Exception var14) {
               var10001 = false;
               return;
            }

            var3 = var2;
            break label124;
         }

         try {
            var3 = Integer.parseInt(this.mSpeed4_values.getValue());
         } catch (Exception var13) {
            var10001 = false;
            return;
         }
      }

      var9[4] = (byte)var3;

      label114: {
         try {
            if (var1 == this.mSpeed5_values) {
               break label114;
            }
         } catch (Exception var12) {
            var10001 = false;
            return;
         }

         try {
            var2 = Integer.parseInt(this.mSpeed5_values.getValue());
         } catch (Exception var11) {
            var10001 = false;
            return;
         }
      }

      var9[5] = (byte)var2;

      try {
         this.SendCanBusCmdData5AA5((byte)-118, var9, 10);
      } catch (Exception var10) {
         var10001 = false;
      }

   }

   private void initCarCmd() {
      this.mCarCmd = this.findPreferenceScreen("car_cmd");
      this.mWipers = this.findHCheckBoxPreference("wipers");
      this.mParking_assist = this.findHCheckBoxPreference("parking_assist");
      this.mReverse_vol = this.findHCheckBoxPreference("reverse_vol");
      HCheckBoxPreference var4 = this.mReverse_vol;
      int var2 = this.getInt("mReverse_vol", 0);
      int var1 = 1;
      boolean var3;
      if (var2 == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      var4.setChecked(var3);
      this.mRunning_lights = this.findHCheckBoxPreference("running_lights");
      this.mAuto_parking = this.findHCheckBoxPreference("auto_parking");
      this.mRunning_lights = this.findHCheckBoxPreference("running_lights");
      this.mAuto_parking = this.findHCheckBoxPreference("auto_parking");
      this.mRadarswitch = this.findHCheckBoxPreference("radarswitch");
      this.mAuto_door = this.findHCheckBoxPreference("auto_door");
      this.mDoor_lock = this.findHCheckBoxPreference("door_lock");
      this.mDoorlock = this.findSwitchboxPreference("doorlock");
      this.mHome_lighting = this.findSwitchboxPreference("home_lighting");
      this.mWelcome_lig = this.findSwitchboxPreference("welcome_lig");
      this.mIn_light = this.findSwitchboxPreference("in_light");
      this.entries = new CharSequence[7];
      CharSequence[] var7 = this.entries;
      this.values = new CharSequence[var7.length];
      this.values[0] = "0";
      var7[0] = this.getString(2131296511);

      while(true) {
         var7 = this.entries;
         CharSequence[] var5;
         StringBuilder var6;
         String var8;
         if (var1 >= var7.length) {
            this.mIn_light.setEntries(var7);
            this.mIn_light.setEntryValues(this.values);
            this.mTpmsCheck = this.findPreferenceScreen("tpms_check");
            this.mInit_mode = this.findPreferenceScreen("init_mode");
            this.mEngine_stop = this.findPreferenceScreen("engine_stop");
            this.mUnlock_trunk_only = this.findHCheckBoxPreference("unlock_trunk_only");
            this.mHeadlights = this.findHCheckBoxPreference("headlights");
            this.mLane_change = this.findHCheckBoxPreference("lane_change");
            this.mWelcome_cmd = this.findHCheckBoxPreference("welcome_cmd");
            this.mWelcome_cmd = this.findHCheckBoxPreference("welcome_cmd");
            this.mLanesystem = this.findHCheckBoxPreference("lanesystem");
            this.mFatigueystem = this.findHCheckBoxPreference("fatigueystem");
            this.mSpeed_warning = this.findHCheckBoxPreference("speed_warning");
            this.mInstrument_color = this.findSwitchboxPreference("instrument_color");
            this.mAtmosphere_lighting = this.findSwitchboxPreference("atmosphere_lighting");
            this.mDriving_mode = this.findSwitchboxPreference("driving_mode");
            this.mIon_purifier = this.findSwitchboxPreference("ion_purifier");
            this.mAro_type = this.findSwitchboxPreference("aro_type");
            this.mAro_concen = this.findSwitchboxPreference("aro_concen");
            this.mScreen3 = this.findSwitchboxPreference("screen3");
            this.entries = new CharSequence[16];
            this.values = new CharSequence[this.entries.length];
            var1 = 0;

            while(true) {
               var5 = this.entries;
               if (var1 >= var5.length) {
                  this.mScreen3.setEntries(var5);
                  this.mScreen3.setEntryValues(this.values);
                  this.mLeft_dashboard = this.findSwitchboxPreference("left_dashboard");
                  this.mRight_dashboard = this.findSwitchboxPreference("right_dashboard");
                  this.mLeft_dashboard.setValue(this.getInt("mLeft_dashboard", 0));
                  this.mRight_dashboard.setValue(this.getInt("mRight_dashboard", 0));
                  if (this.getCarType() != 20) {
                     this.enabledPreferenceGroup(this.mCarCmd, this.mInit_mode, false);
                     this.enabledPreferenceGroup(this.mCarCmd, this.mLeft_dashboard, false);
                     this.enabledPreferenceGroup(this.mCarCmd, this.mRight_dashboard, false);
                  }

                  return;
               }

               var7 = this.values;
               var6 = new StringBuilder();
               var6.append("");
               var6.append(var1);
               var8 = var6.toString();
               var5[var1] = var8;
               var7[var1] = var8;
               ++var1;
            }
         }

         var5 = this.values;
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var1);
         var8 = var6.toString();
         var7[var1] = var8;
         var5[var1] = var8;
         ++var1;
      }
   }

   private void initCruising() {
      this.mCruising_screen = this.findPreferenceScreen("cruising_screen");
      this.mCruising_cmd = this.findHCheckBoxPreference("cruising_cmd");
      this.mCruising1 = this.findHCheckBoxPreference("cruising1");
      this.mCruising2 = this.findHCheckBoxPreference("cruising2");
      this.mCruising3 = this.findHCheckBoxPreference("cruising3");
      this.mCruising4 = this.findHCheckBoxPreference("cruising4");
      this.mCruising5 = this.findHCheckBoxPreference("cruising5");
      this.entries = new CharSequence[26];
      this.values = new CharSequence[this.entries.length];
      int var1 = 0;

      while(true) {
         CharSequence[] var3 = this.entries;
         if (var1 >= var3.length) {
            this.mCruising1_values = this.findSwitchboxPreference("cruising1_values");
            this.mCruising2_values = this.findSwitchboxPreference("cruising2_values");
            this.mCruising3_values = this.findSwitchboxPreference("cruising3_values");
            this.mCruising4_values = this.findSwitchboxPreference("cruising4_values");
            this.mCruising5_values = this.findSwitchboxPreference("cruising5_values");
            this.mCruising1_values.setEntries(this.entries);
            this.mCruising1_values.setEntryValues(this.values);
            this.mCruising2_values.setEntries(this.entries);
            this.mCruising2_values.setEntryValues(this.values);
            this.mCruising3_values.setEntries(this.entries);
            this.mCruising3_values.setEntryValues(this.values);
            this.mCruising4_values.setEntries(this.entries);
            this.mCruising4_values.setEntryValues(this.values);
            this.mCruising5_values.setEntries(this.entries);
            this.mCruising5_values.setEntryValues(this.values);
            return;
         }

         CharSequence[] var2 = this.values;
         StringBuilder var4 = new StringBuilder();
         var4.append("");
         var4.append(var1 * 10);
         String var5 = var4.toString();
         var3[var1] = var5;
         var2[var1] = var5;
         ++var1;
      }
   }

   private void initSpeed() {
      this.mSpeed_screen = this.findPreferenceScreen("speed_screen");
      this.mSpeed_cmd = this.findHCheckBoxPreference("speed_cmd");
      this.mSpeed1 = this.findHCheckBoxPreference("speed1");
      this.mSpeed2 = this.findHCheckBoxPreference("speed2");
      this.mSpeed3 = this.findHCheckBoxPreference("speed3");
      this.mSpeed4 = this.findHCheckBoxPreference("speed4");
      this.mSpeed5 = this.findHCheckBoxPreference("speed5");
      this.entries = new CharSequence[26];
      this.values = new CharSequence[this.entries.length];
      int var1 = 0;

      while(true) {
         CharSequence[] var2 = this.entries;
         if (var1 >= var2.length) {
            this.mSpeed1_values = this.findSwitchboxPreference("speed1_values");
            this.mSpeed2_values = this.findSwitchboxPreference("speed2_values");
            this.mSpeed3_values = this.findSwitchboxPreference("speed3_values");
            this.mSpeed4_values = this.findSwitchboxPreference("speed4_values");
            this.mSpeed5_values = this.findSwitchboxPreference("speed5_values");
            this.mSpeed1_values.setEntries(this.entries);
            this.mSpeed1_values.setEntryValues(this.values);
            this.mSpeed2_values.setEntries(this.entries);
            this.mSpeed2_values.setEntryValues(this.values);
            this.mSpeed3_values.setEntries(this.entries);
            this.mSpeed3_values.setEntryValues(this.values);
            this.mSpeed4_values.setEntries(this.entries);
            this.mSpeed4_values.setEntryValues(this.values);
            this.mSpeed5_values.setEntries(this.entries);
            this.mSpeed5_values.setEntryValues(this.values);
            return;
         }

         CharSequence[] var3 = this.values;
         StringBuilder var4 = new StringBuilder();
         var4.append("");
         var4.append(var1 * 10);
         String var5 = var4.toString();
         var2[var1] = var5;
         var3[var1] = var5;
         ++var1;
      }
   }

   private void initUnit() {
      this.mUnit_set = this.findPreferenceScreen("unit_set");
      this.mTemp = this.findSwitchboxPreference("temp");
      this.mOil_unit = this.findSwitchboxPreference("oil_unit");
   }

   protected void ProcessData(byte[] var1) {
      int var3 = super.cmd;
      boolean var7 = false;
      boolean var5 = false;
      boolean var8 = false;
      boolean var4 = false;
      int var2 = 0;
      boolean var9 = false;
      boolean var10 = false;
      boolean var6 = false;
      PreferenceScreen var11;
      HCheckBoxPreference var12;
      HCheckBoxPreference var13;
      SwitchboxPreference var14;
      PreferenceScreen var15;
      SwitchboxPreference var16;
      if (var3 == 113) {
         var11 = this.mCarCmd;
         var12 = this.mWipers;
         if ((var1[3] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var12, var4);
         var15 = this.mCarCmd;
         var13 = this.mParking_assist;
         if ((var1[3] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var14 = this.mDoorlock;
         if ((var1[3] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var11 = this.mCarCmd;
         var12 = this.mRunning_lights;
         if ((4 & var1[3]) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var12, var4);
         var11 = this.mCarCmd;
         var12 = this.mAuto_parking;
         if ((var1[2] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var12, var4);
         var15 = this.mCarCmd;
         var14 = this.mHome_lighting;
         if ((var1[3] & 2) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var11 = this.mCarCmd;
         var16 = this.mWelcome_lig;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var16, var4);
         var11 = this.mCarCmd;
         var12 = this.mRadarswitch;
         if ((var1[2] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var12, var4);
         var15 = this.mCarCmd;
         var13 = this.mAuto_door;
         if ((var1[3] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var13 = this.mDoor_lock;
         if ((var1[3] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var11 = this.mCarCmd;
         var16 = this.mIn_light;
         var4 = var6;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         }

         this.enabledPreferenceGroup(var11, var16, var4);
      } else if (var3 == 118) {
         var13 = this.mWipers;
         if ((var1[3] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mParking_assist;
         if ((var1[3] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         this.mDoorlock.setValue((var1[3] & 8) >> 3);
         this.mHome_lighting.setValue(var1[3] & 3);
         this.mWelcome_lig.setValue((var1[2] & 48) >> 4);
         var13 = this.mRunning_lights;
         if ((4 & var1[3]) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mAuto_parking;
         if ((var1[2] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mRadarswitch;
         if ((var1[2] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mAuto_door;
         if ((var1[3] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mDoor_lock;
         var4 = var7;
         if ((var1[3] & 16) != 0) {
            var4 = true;
         }

         var13.setChecked(var4);
         this.mIn_light.setValue(var1[2] & 7);
      } else if (var3 == 114) {
         var11 = this.mCarCmd;
         var12 = this.mUnlock_trunk_only;
         if ((var1[2] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var12, var4);
         var15 = this.mCarCmd;
         var13 = this.mHeadlights;
         if ((var1[2] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var13 = this.mLane_change;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var13 = this.mWelcome_cmd;
         if ((var1[2] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var13 = this.mLanesystem;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var13 = this.mFatigueystem;
         if ((4 & var1[2]) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var13 = this.mSpeed_warning;
         if ((var1[2] & 2) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var13, var4);
         var15 = this.mCarCmd;
         var14 = this.mInstrument_color;
         if ((var1[2] & 1) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var15 = this.mCarCmd;
         var14 = this.mAtmosphere_lighting;
         if ((var1[3] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var15 = this.mCarCmd;
         var14 = this.mDriving_mode;
         if ((var1[3] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var15 = this.mCarCmd;
         var14 = this.mIon_purifier;
         if ((var1[3] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var15 = this.mCarCmd;
         var14 = this.mAro_type;
         if ((var1[3] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var11 = this.mCarCmd;
         var16 = this.mAro_concen;
         if ((var1[3] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var16, var4);
         var15 = this.mCarCmd;
         var14 = this.mScreen3;
         var4 = var5;
         if ((var1[3] & 8) != 0) {
            var4 = true;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
      } else if (var3 == 121) {
         var13 = this.mUnlock_trunk_only;
         if ((var1[2] & 128) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mHeadlights;
         if ((var1[2] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mLane_change;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mWelcome_cmd;
         if ((var1[2] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mLanesystem;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mFatigueystem;
         if ((var1[2] & 4) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mSpeed_warning;
         var4 = var8;
         if ((var1[2] & 2) != 0) {
            var4 = true;
         }

         var13.setChecked(var4);
         this.mInstrument_color.setValue(var1[2] & 1);
         this.mAtmosphere_lighting.setValue((var1[3] & 192) >> 6);
         this.mDriving_mode.setValue((var1[3] & 32) >> 5);
         this.mIon_purifier.setValue((var1[3] & 24) >> 3);
         this.mAro_type.setValue((var1[3] & 6) >> 1);
         this.mAro_concen.setValue((var1[4] & 192) >> 6);
         this.mScreen3.setValue(var1[4] >> 2 & 15);
      } else if (var3 == 133) {
         var15 = this.mCarCmd;
         var11 = this.mEngine_stop;
         if ((var1[2] & 128) != 0) {
            var4 = true;
         }

         this.enabledPreferenceGroup(var15, var11, var4);
         var11 = this.mEngine_stop;
         if ((var1[3] & 128) != 0) {
            var2 = 2131296959;
         } else {
            var2 = 2131296511;
         }

         var11.setSummary(this.getString(var2));
      } else if (var3 == 193) {
         var15 = this.mUnit_set;
         var14 = this.mTemp;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var15, var14, var4);
         var11 = this.mUnit_set;
         var16 = this.mOil_unit;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreferenceGroup(var11, var16, var4);
         var11 = this.mUnit_set;
         if ((var1[2] & 40) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         this.enabledPreference(var11, var4);
         var14 = this.mTemp;
         if ((var1[3] & 32) == 0) {
            var2 = 1;
         }

         var14.setValue(var2);
         this.mOil_unit.setValue(var1[3] >> 1 & 3);
      } else if (var3 == 129) {
         this.mSpeed1_values.setValue(var1[3] & 255);
         this.mSpeed2_values.setValue(var1[4] & 255);
         this.mSpeed3_values.setValue(var1[5] & 255);
         this.mSpeed4_values.setValue(var1[6] & 255);
         this.mSpeed5_values.setValue(var1[7] & 255);
         if ((var1[2] & 128) != 0) {
            this.mSpeed_cmd.setChecked(true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed1_values, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed2_values, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed3_values, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed4_values, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed5_values, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed1, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed2, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed3, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed4, true);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed5, true);
         } else {
            this.mSpeed_cmd.setChecked(false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed1_values, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed2_values, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed3_values, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed4_values, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed5_values, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed1, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed2, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed3, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed4, false);
            this.enabledPreferenceGroup(this.mSpeed_screen, this.mSpeed5, false);
         }

         var13 = this.mSpeed1;
         if ((var1[2] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mSpeed2;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mSpeed3;
         if ((var1[2] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mSpeed4;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mSpeed5;
         if ((4 & var1[2]) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var11 = this.mSpeed_screen;
         var4 = var9;
         if ((var1[11] & 128) != 0) {
            var4 = true;
         }

         this.enabledPreference(var11, var4);
      } else if (var3 == 130) {
         this.mCruising1_values.setValue(var1[3] & 255);
         this.mCruising2_values.setValue(var1[4] & 255);
         this.mCruising3_values.setValue(var1[5] & 255);
         this.mCruising4_values.setValue(var1[6] & 255);
         this.mCruising5_values.setValue(var1[7] & 255);
         if ((var1[2] & 128) != 0) {
            this.mCruising_cmd.setChecked(true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising1_values, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising2_values, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising3_values, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising4_values, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising5_values, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising1, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising2, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising3, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising4, true);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising5, true);
         } else {
            this.mCruising_cmd.setChecked(false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising1_values, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising2_values, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising3_values, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising4_values, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising5_values, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising1, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising2, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising3, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising4, false);
            this.enabledPreferenceGroup(this.mCruising_screen, this.mCruising5, false);
         }

         var13 = this.mCruising1;
         if ((var1[2] & 64) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mCruising2;
         if ((var1[2] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mCruising3;
         if ((var1[2] & 16) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mCruising4;
         if ((var1[2] & 8) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var13 = this.mCruising5;
         if ((4 & var1[2]) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var13.setChecked(var4);
         var11 = this.mCruising_screen;
         var4 = var10;
         if ((var1[11] & 128) != 0) {
            var4 = true;
         }

         this.enabledPreference(var11, var4);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492930);
      this.initCarCmd();
      this.initUnit();
      this.initSpeed();
      this.initCruising();
      this.BaseProcessData(this.GetCarByteArrayState(113));
      this.BaseProcessData(this.GetCarByteArrayState(118));
      this.BaseProcessData(this.GetCarByteArrayState(114));
      this.BaseProcessData(this.GetCarByteArrayState(121));
      this.BaseProcessData(this.GetCarByteArrayState(129));
      this.BaseProcessData(this.GetCarByteArrayState(130));
      this.BaseProcessData(this.GetCarByteArrayState(193));
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3 = 0;

      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      int var5;
      try {
         var5 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var7) {
         return true;
      }

      if (var4 == var5) {
         return true;
      } else {
         if (var1 == this.mDoorlock) {
            this.SetCmdkey(123, 4, var4);
         } else if (var1 == this.mHome_lighting) {
            this.SetCmdkey(123, 6, var4);
         } else if (var1 == this.mWelcome_lig) {
            this.SetCmdkey(123, 9, var4);
         } else if (var1 == this.mIn_light) {
            if (var4 > 0) {
               var3 = var4 | 128;
            }

            this.SetCmdkey(123, 10, var3);
         } else if (var1 == this.mInstrument_color) {
            this.SetCmdkey(125, 9, var4);
         } else if (var1 == this.mAtmosphere_lighting) {
            this.SetCmdkey(125, 10, var4);
         } else if (var1 == this.mDriving_mode) {
            this.SetCmdkey(125, 11, var4);
         } else if (var1 == this.mIon_purifier) {
            this.SetCmdkey(125, 12, var4);
         } else if (var1 == this.mAro_type) {
            this.SetCmdkey(125, 13, var4);
         } else if (var1 == this.mAro_concen) {
            this.SetCmdkey(125, 14, var4);
         } else if (var1 == this.mScreen3) {
            this.SetCmdkey(125, 16, var4);
         } else if (var1 == this.mLeft_dashboard) {
            this.SetCmdkey(125, 17, var4);
            this.putInt("mLeft_dashboard", var4);
            this.mLeft_dashboard.setValue(var4);
         } else if (var1 == this.mRight_dashboard) {
            this.SetCmdkey(125, 18, var4);
            this.putInt("mRight_dashboard", var4);
            this.mRight_dashboard.setValue(var4);
         } else if (var1 == this.mTemp) {
            this.SetCmdkey(202, 3, var4 + 1);
         } else if (var1 == this.mOil_unit) {
            this.SetCmdkey(202, 5, var4 + 1);
         } else {
            SwitchboxPreference var8 = this.mSpeed1_values;
            if (var1 == var8) {
               this.SetSpeedCmdkey(var8, var4);
            } else {
               var8 = this.mSpeed2_values;
               if (var1 == var8) {
                  this.SetSpeedCmdkey(var8, var4);
               } else {
                  var8 = this.mSpeed3_values;
                  if (var1 == var8) {
                     this.SetSpeedCmdkey(var8, var4);
                  } else {
                     var8 = this.mSpeed4_values;
                     if (var1 == var8) {
                        this.SetSpeedCmdkey(var8, var4);
                     } else {
                        var8 = this.mSpeed5_values;
                        if (var1 == var8) {
                           this.SetSpeedCmdkey(var8, var4);
                        } else {
                           var8 = this.mCruising1_values;
                           if (var1 == var8) {
                              this.SetCruisingCmdkey(var8, var4);
                           } else {
                              var8 = this.mCruising2_values;
                              if (var1 == var8) {
                                 this.SetCruisingCmdkey(var8, var4);
                              } else {
                                 var8 = this.mCruising3_values;
                                 if (var1 == var8) {
                                    this.SetCruisingCmdkey(var8, var4);
                                 } else {
                                    var8 = this.mCruising4_values;
                                    if (var1 == var8) {
                                       this.SetCruisingCmdkey(var8, var4);
                                    } else {
                                       var8 = this.mCruising5_values;
                                       if (var1 == var8) {
                                          this.SetCruisingCmdkey(var8, var4);
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

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 instanceof PreferenceScreen) {
         if (this.mTpmsCheck == var2) {
            this.AlertDialog(new AlertDialogCallBack(this) {
               final canbus59settings this$0;

               {
                  this.this$0 = var1;
               }

               public void OkClick() {
                  this.this$0.SetCmdkey(125, 3, 1);
                  this.this$0.mTpmsCheck.setSummary(this.this$0.getString(17039370));
               }
            }, this.mTpmsCheck.getTitle().toString());
         } else if (var2 == this.mInit_mode) {
            this.AlertDialog(new AlertDialogCallBack(this) {
               final canbus59settings this$0;

               {
                  this.this$0 = var1;
               }

               public void OkClick() {
                  this.this$0.SetCmdkey(125, 15, 1);
               }
            }, this.mInit_mode.getTitle().toString());
         } else if (var2 == this.mEngine_stop) {
            this.AlertDialog(new AlertDialogCallBack(this) {
               final canbus59settings this$0;

               {
                  this.this$0 = var1;
               }

               public void OkClick() {
                  this.this$0.SetCmdkey(140, 1, 255);
               }
            }, this.mEngine_stop.getTitle().toString());
         }

         return super.onPreferenceTreeClick(var1, var2);
      } else {
         boolean var27 = ((HCheckBoxPreference)var2).mChecked;
         HCheckBoxPreference var28 = this.mWipers;
         short var4 = 1;
         byte var3 = 1;
         byte var23 = 1;
         byte var20 = 1;
         byte var21 = 1;
         byte var25 = 1;
         byte var14 = 1;
         byte var15 = 1;
         byte var13 = 1;
         byte var18 = 1;
         byte var24 = 1;
         byte var17 = 1;
         byte var16 = 1;
         byte var22 = 0;
         byte var5 = 0;
         byte var11 = 0;
         byte var12 = 0;
         byte var10 = 0;
         byte var8 = 0;
         byte var6 = 0;
         byte var7 = 0;
         byte var9 = 0;
         byte var19 = 0;
         if (var2 == var28) {
            if (var27) {
               var3 = var19;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(123, 1, var3);
         } else if (var2 == this.mParking_assist) {
            var3 = var16;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(123, 2, var3);
         } else if (var2 == this.mReverse_vol) {
            if (var27) {
               var3 = 0;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(123, 3, var3);
            var28 = this.mReverse_vol;
            boolean var26;
            if (!var27) {
               var26 = true;
            } else {
               var26 = false;
            }

            var28.setChecked(var26);
            var3 = (byte)var4;
            if (var27) {
               var3 = 0;
            }

            this.putInt("mReverse_vol", var3);
         } else if (var2 == this.mRunning_lights) {
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(123, 5, var3);
         } else if (var2 == this.mAuto_parking) {
            var3 = var23;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(123, 8, var3);
         } else if (var2 == this.mRadarswitch) {
            var3 = var20;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(123, 11, var3);
         } else if (var2 == this.mAuto_door) {
            var3 = var21;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(123, 12, var3);
         } else if (var2 == this.mDoor_lock) {
            var3 = var25;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(123, 13, var3);
         } else if (var2 == this.mUnlock_trunk_only) {
            if (var27) {
               var3 = var22;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(125, 1, var3);
         } else if (var2 == this.mHeadlights) {
            var3 = var14;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(125, 2, var3);
         } else if (var2 == this.mLane_change) {
            var3 = var15;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(125, 4, var3);
         } else if (var2 == this.mWelcome_cmd) {
            var3 = var13;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(125, 5, var3);
         } else if (var2 == this.mLanesystem) {
            var3 = var18;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(125, 6, var3);
         } else if (var2 == this.mFatigueystem) {
            var3 = var24;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(125, 7, var3);
         } else if (var2 == this.mSpeed_warning) {
            var3 = var17;
            if (var27) {
               var3 = 0;
            }

            this.SetCmdkey(125, 8, var3);
         } else {
            var28 = this.mSpeed_cmd;
            var4 = 128;
            if (var2 == var28) {
               if (var27) {
                  var4 = 0;
               }

               this.SetSpeedCmdkey(var28, var4);
            } else {
               var28 = this.mSpeed1;
               var3 = 64;
               if (var2 == var28) {
                  if (var27) {
                     var3 = 0;
                  }

                  this.SetSpeedCmdkey(var28, var3);
               } else {
                  var28 = this.mSpeed2;
                  if (var2 == var28) {
                     if (var27) {
                        var3 = var5;
                     } else {
                        var3 = 32;
                     }

                     this.SetSpeedCmdkey(var28, var3);
                  } else {
                     var28 = this.mSpeed3;
                     if (var2 == var28) {
                        if (var27) {
                           var3 = var11;
                        } else {
                           var3 = 16;
                        }

                        this.SetSpeedCmdkey(var28, var3);
                     } else {
                        var28 = this.mSpeed4;
                        if (var2 == var28) {
                           if (var27) {
                              var3 = var12;
                           } else {
                              var3 = 8;
                           }

                           this.SetSpeedCmdkey(var28, var3);
                        } else {
                           var28 = this.mSpeed5;
                           if (var2 == var28) {
                              if (var27) {
                                 var3 = var10;
                              } else {
                                 var3 = 4;
                              }

                              this.SetSpeedCmdkey(var28, var3);
                           } else {
                              var28 = this.mCruising_cmd;
                              if (var2 == var28) {
                                 if (var27) {
                                    var4 = 0;
                                 }

                                 this.SetCruisingCmdkey(var28, var4);
                              } else {
                                 var28 = this.mCruising1;
                                 if (var2 == var28) {
                                    if (var27) {
                                       var3 = 0;
                                    }

                                    this.SetCruisingCmdkey(var28, var3);
                                 } else {
                                    var28 = this.mCruising2;
                                    if (var2 == var28) {
                                       if (var27) {
                                          var3 = var8;
                                       } else {
                                          var3 = 32;
                                       }

                                       this.SetCruisingCmdkey(var28, var3);
                                    } else {
                                       var28 = this.mCruising3;
                                       if (var2 == var28) {
                                          if (var27) {
                                             var3 = var6;
                                          } else {
                                             var3 = 16;
                                          }

                                          this.SetCruisingCmdkey(var28, var3);
                                       } else {
                                          var28 = this.mCruising4;
                                          if (var2 == var28) {
                                             if (var27) {
                                                var3 = var7;
                                             } else {
                                                var3 = 8;
                                             }

                                             this.SetCruisingCmdkey(var28, var3);
                                          } else {
                                             var28 = this.mCruising5;
                                             if (var2 == var28) {
                                                if (var27) {
                                                   var3 = var9;
                                                } else {
                                                   var3 = 4;
                                                }

                                                this.SetCruisingCmdkey(var28, var3);
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
}
