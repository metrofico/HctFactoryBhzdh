package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus105settings extends BasePreferenceActivity {
   private int _Backlighting = 1;
   private int _Instrument_volume = 0;
   private int _Over_speed = 0;
   private HCheckBoxPreference mAvm_animan;
   private OnSwitchPreference mBacklighting;
   private HCheckBoxPreference mBrake_alarm;
   private SwitchboxPreference mBrake_alarm_mode;
   private SwitchboxPreference mCar_line;
   private HCheckBoxPreference mDriving_auto;
   private OnSwitchPreference mInstrument_volume;
   private HCheckBoxPreference mLamp_no;
   private OnSwitchPreference mOver_speed;
   private HCheckBoxPreference mRunning_lights;
   private HCheckBoxPreference mStart_avm;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)-116, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var4 = false;
      if ((var1[0] & 255) == 135) {
         HCheckBoxPreference var5 = this.mBrake_alarm;
         boolean var3;
         if ((var1[4] & 128) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         if ((var1[2] & 128) == 0) {
            this.removePreference((Preference)this.mBrake_alarm);
         }

         byte var2 = var1[4];
         SwitchboxPreference var6 = this.mBrake_alarm_mode;
         StringBuilder var7 = new StringBuilder();
         var7.append("");
         var7.append(var2 >> 5 & 3);
         var6.setValue(var7.toString());
         var5 = this.mDriving_auto;
         if ((var1[4] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         if ((var1[2] & 64) == 0) {
            this.removePreference((Preference)this.mDriving_auto);
         }

         var5 = this.mLamp_no;
         if ((var1[4] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         if ((var1[2] & 32) == 0) {
            this.removePreference((Preference)this.mLamp_no);
         }

         var5 = this.mRunning_lights;
         if ((var1[4] & 4) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         if ((var1[2] & 16) == 0) {
            this.removePreference((Preference)this.mRunning_lights);
         }

         this._Over_speed = var1[5] & 31;
         OnSwitchPreference var8 = this.mOver_speed;
         var7 = new StringBuilder();
         var7.append("");
         var7.append(this._Over_speed * 5 + 25);
         var8.setValue("0", var7.toString());
         if ((var1[2] & 1) == 0) {
            this.removePreference((Preference)this.mOver_speed);
         }

         this._Backlighting = (var1[6] & 240) >> 4;
         OnSwitchPreference var10 = this.mBacklighting;
         StringBuilder var9 = new StringBuilder();
         var9.append("");
         var9.append(this._Backlighting);
         var10.setValue("0", var9.toString());
         if ((var1[3] & 128) == 0) {
            this.removePreference((Preference)this.mBacklighting);
         }

         var2 = var1[5];
         SwitchboxPreference var11 = this.mCar_line;
         var9 = new StringBuilder();
         var9.append("");
         var9.append(var2 >> 6 & 3);
         var11.setValue(var9.toString());
         if ((var1[2] & 2) == 0) {
            this.removePreference((Preference)this.mCar_line);
         }

         var5 = this.mStart_avm;
         if ((var1[4] & 2) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         if ((var1[2] & 8) == 0) {
            this.removePreference((Preference)this.mStart_avm);
         }

         var5 = this.mAvm_animan;
         var3 = var4;
         if ((var1[4] & 2) != 0) {
            var3 = true;
         }

         var5.setChecked(var3);
         if ((var1[2] & 4) == 0) {
            this.removePreference((Preference)this.mAvm_animan);
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492866);
      this.mBrake_alarm = this.findHCheckBoxPreference("brake_alarm");
      this.mDriving_auto = this.findHCheckBoxPreference("driving_auto");
      this.mLamp_no = this.findHCheckBoxPreference("lamp_no");
      this.mRunning_lights = this.findHCheckBoxPreference("running_lights");
      this.mStart_avm = this.findHCheckBoxPreference("start_avm");
      this.mAvm_animan = this.findHCheckBoxPreference("avm_animan");
      this.mBrake_alarm_mode = this.findSwitchboxPreference("brake_alarm_mode");
      this.mCar_line = this.findSwitchboxPreference("car_line");
      this.mInstrument_volume = this.findOnSwitchPreference("instrument_volume");
      OnSwitchPreference var2 = this.mInstrument_volume;
      StringBuilder var3 = new StringBuilder();
      var3.append("");
      var3.append(this.getInt("mInstrument_volume", 0));
      var2.setValue("0", var3.toString());
      this.mOver_speed = this.findOnSwitchPreference("over_speed");
      this.mBacklighting = this.findOnSwitchPreference("backlighting");
      this.SendCanBusCmdData5AA5((byte)106, new byte[]{5, 1, -121}, 3);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mInstrument_volume;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this._Instrument_volume;
         if (var4 + var3 < 0 || var4 + var3 > 30 || !this.mInstrument_volume.getIsDow()) {
            return true;
         }

         this._Instrument_volume += var3;
         this.SetCmdkey((byte)3, (byte)(this._Instrument_volume & 255));
         var5 = this.mInstrument_volume;
         StringBuilder var6 = new StringBuilder();
         var6.append("");
         var6.append(this._Instrument_volume);
         var5.setValue("0", var6.toString());
         this.putInt("mInstrument_volume", this._Instrument_volume);
      } else {
         var5 = this.mOver_speed;
         if (var1 == var5) {
            var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var4 = this._Over_speed;
            if (var4 + var3 < 0 || var4 + var3 > 21 || !this.mOver_speed.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)7, (byte)(this._Over_speed + var3 & 255));
         } else {
            var5 = this.mBacklighting;
            if (var1 == var5) {
               var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
               var4 = this._Backlighting;
               if (var4 + var3 < 1 || var4 + var3 > 10 || !this.mBacklighting.getIsDow()) {
                  return true;
               }

               this.SetCmdkey((byte)8, (byte)(this._Backlighting + var3 & 255));
            }
         }
      }

      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var8) {
         return false;
      }

      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var7) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mBrake_alarm_mode) {
            this.SetCmdkey((byte)2, (byte)(var4 + 0));
         } else if (var1 == this.mCar_line) {
            this.SetCmdkey((byte)11, (byte)(var4 + 0));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mBrake_alarm;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)1, (byte)0);
         } else {
            this.SetCmdkey((byte)1, (byte)1);
         }
      } else {
         var2 = this.mDriving_auto;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)4, (byte)0);
            } else {
               this.SetCmdkey((byte)4, (byte)1);
            }
         } else {
            var2 = this.mLamp_no;
            if (var1 == var2) {
               if (var2.mChecked) {
                  this.SetCmdkey((byte)5, (byte)0);
               } else {
                  this.SetCmdkey((byte)5, (byte)1);
               }
            } else {
               var2 = this.mRunning_lights;
               if (var1 == var2) {
                  if (var2.mChecked) {
                     this.SetCmdkey((byte)6, (byte)0);
                  } else {
                     this.SetCmdkey((byte)6, (byte)1);
                  }
               } else {
                  var2 = this.mStart_avm;
                  if (var1 == var2) {
                     if (var2.mChecked) {
                        this.SetCmdkey((byte)9, (byte)0);
                     } else {
                        this.SetCmdkey((byte)9, (byte)1);
                     }
                  } else {
                     var2 = this.mAvm_animan;
                     if (var1 == var2) {
                        if (var2.mChecked) {
                           this.SetCmdkey((byte)10, (byte)0);
                        } else {
                           this.SetCmdkey((byte)10, (byte)1);
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
