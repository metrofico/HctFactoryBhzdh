package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus160settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private HCheckBoxPreference mAuto3change;
   private HCheckBoxPreference mAuto3jump;
   private HCheckBoxPreference mAuto7change;
   private HCheckBoxPreference mAuto7jump;
   private SeekBarPreference mB_val;
   private SwitchboxPreference mColor_set;
   private SeekBarPreference mG_val;
   private SwitchboxPreference mMic_sel;
   private SeekBarPreference mR_val;
   private HCheckBoxPreference mRel_radar;
   private HCheckBoxPreference mRel_rpm;
   private HCheckBoxPreference mRel_speed;
   private HCheckBoxPreference mRel_throttle;
   private HCheckBoxPreference mSwc_air;
   private SwitchboxPreference mUnit;

   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-33, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = super.cmd;
      boolean var3 = false;
      boolean var4 = false;
      HCheckBoxPreference var5;
      if (var2 == 95) {
         var5 = this.mRel_speed;
         if ((var1[2] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mRel_rpm;
         if ((64 & var1[2]) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mRel_throttle;
         if ((var1[2] & 32) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mRel_radar;
         if ((var1[2] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto7change;
         if ((var1[2] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto7jump;
         if ((var1[2] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto3change;
         if ((var1[2] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mAuto3jump;
         var3 = var4;
         if ((var1[2] & 1) != 0) {
            var3 = true;
         }

         var5.setChecked(var3);
         this.mR_val.setProgress(var1[4] & 255);
         this.mG_val.setProgress(var1[5] & 255);
         this.mB_val.setProgress(var1[6] & 255);
         this.mColor_set.setValue(var1[3] & 15);
      } else if (var2 == 64) {
         if ((var1[2] & 255) == 1) {
            this.mUnit.setValue(var1[3] & 1);
         }
      } else if (var2 == 93) {
         this.mMic_sel.setValue(var1[2] & 1);
         var5 = this.mSwc_air;
         if ((var1[2] & 2) != 0) {
            var3 = true;
         }

         var5.setChecked(var3);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492889);
      this.mUnit = this.findSwitchboxPreference("unit");
      this.mMic_sel = this.findSwitchboxPreference("mic_sel");
      this.mColor_set = this.findSwitchboxPreference("color_set");
      this.mR_val = this.findSeekBarPreference("r_val");
      this.mG_val = this.findSeekBarPreference("g_val");
      this.mB_val = this.findSeekBarPreference("b_val");
      this.mRel_speed = this.findHCheckBoxPreference("rel_speed");
      this.mRel_rpm = this.findHCheckBoxPreference("rel_rpm");
      this.mRel_throttle = this.findHCheckBoxPreference("rel_throttle");
      this.mRel_radar = this.findHCheckBoxPreference("rel_radar");
      this.mAuto7change = this.findHCheckBoxPreference("auto7change");
      this.mAuto7jump = this.findHCheckBoxPreference("auto7jump");
      this.mAuto3change = this.findHCheckBoxPreference("auto3change");
      this.mAuto3jump = this.findHCheckBoxPreference("auto3jump");
      this.mSwc_air = this.findHCheckBoxPreference("swc_air");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{95, 0}, 2);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{64, 1}, 2);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var3;
      try {
         if (var1 instanceof SeekBarPreference) {
            var3 = (Integer)var2;
         } else {
            var3 = Integer.parseInt((String)var2);
         }
      } catch (Exception var5) {
         return false;
      }

      int var4;
      try {
         if (var1 instanceof SeekBarPreference) {
            var4 = ((SeekBarPreference)var1).getProgress();
         } else {
            var4 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
         }
      } catch (Exception var6) {
         return true;
      }

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mUnit) {
            this.SendCanBusCmdData2E((byte)-58, new byte[]{1, (byte)var3}, 2);
         } else if (var1 == this.mMic_sel) {
            this.SendCanBusCmdData2E((byte)-35, new byte[]{1, (byte)var3}, 2);
         } else if (var1 == this.mR_val) {
            this.SetCmdkey(10, var3);
         } else if (var1 == this.mG_val) {
            this.SetCmdkey(11, var3);
         } else if (var1 == this.mB_val) {
            this.SetCmdkey(12, var3);
         } else if (var1 == this.mColor_set) {
            this.SetCmdkey(9, var3);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 instanceof HCheckBoxPreference) {
         boolean var12 = ((HCheckBoxPreference)var2).mChecked;
         HCheckBoxPreference var13 = this.mRel_speed;
         byte var11 = 0;
         byte var8 = 0;
         byte var7 = 0;
         byte var6 = 0;
         byte var4 = 0;
         byte var3 = 0;
         byte var9 = 0;
         byte var5 = 0;
         byte var10 = 0;
         if (var2 == var13) {
            if (var12) {
               var3 = var10;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(1, var3);
         } else if (var2 == this.mRel_rpm) {
            if (var12) {
               var3 = var11;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(2, var3);
         } else if (var2 == this.mRel_throttle) {
            if (var12) {
               var3 = var8;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(3, var3);
         } else if (var2 == this.mRel_radar) {
            if (var12) {
               var3 = var7;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(4, var3);
         } else if (var2 == this.mAuto7change) {
            if (var12) {
               var3 = var6;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(5, var3);
         } else if (var2 == this.mAuto7jump) {
            if (var12) {
               var3 = var4;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(6, var3);
         } else if (var2 == this.mAuto3change) {
            if (!var12) {
               var3 = 1;
            }

            this.SetCmdkey(7, var3);
         } else if (var2 == this.mAuto3jump) {
            if (var12) {
               var3 = var9;
            } else {
               var3 = 1;
            }

            this.SetCmdkey(8, var3);
         } else if (var2 == this.mSwc_air) {
            if (var12) {
               var3 = var5;
            } else {
               var3 = 1;
            }

            this.SendCanBusCmdData2E((byte)-35, new byte[]{3, (byte)var3}, 2);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
