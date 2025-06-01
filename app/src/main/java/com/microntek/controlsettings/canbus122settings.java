package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus122settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchboxPreference mAls;
   private HCheckBoxPreference mEngine_stop;
   private SwitchboxPreference mHeadlight_off;
   private SwitchboxPreference mHome_lighting;
   private SwitchboxPreference mPower_saving;
   private SwitchboxPreference mRain_sensor;
   private SwitchboxPreference mRear_view;
   private byte[] setData = new byte[31];

   private void RequestCmdkey(byte var1) {
      byte[] var2 = new byte[]{var1, 0};
      this.SendCanBusCmdData2E((byte)-112, var2, 2);
   }

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-125, new byte[]{var1, var2}, 2);
   }

   private void updasettings() {
      SwitchboxPreference var3 = this.mHeadlight_off;
      StringBuilder var4 = new StringBuilder();
      var4.append("");
      byte[] var2 = this.setData;
      boolean var1 = false;
      var4.append((var2[0] >> 4 & 7) - 1);
      var3.setValue(var4.toString());
      var3 = this.mHome_lighting;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      var5.append((this.setData[0] & 7) - 1);
      var3.setValue(var5.toString());
      var3 = this.mPower_saving;
      var5 = new StringBuilder();
      var5.append("");
      var5.append((this.setData[1] >> 4 & 7) - 1);
      var3.setValue(var5.toString());
      SwitchboxPreference var6 = this.mRain_sensor;
      StringBuilder var7 = new StringBuilder();
      var7.append("");
      var7.append(this.setData[1] >> 3 & 1);
      var6.setValue(var7.toString());
      var6 = this.mAls;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(this.setData[1] >> 2 & 1);
      var6.setValue(var7.toString());
      var6 = this.mRear_view;
      var7 = new StringBuilder();
      var7.append("");
      var7.append(this.setData[1] >> 1 & 1);
      var6.setValue(var7.toString());
      HCheckBoxPreference var8 = this.mEngine_stop;
      if ((this.setData[3] & 32) != 0) {
         var1 = true;
      }

      var8.setChecked(var1);
   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      byte var4 = var1[0];
      byte var3 = var1[1];
      if (var3 != 0) {
         if (var4 == 49) {
            while(var2 < var3) {
               this.setData[var2] = var1[var2 + 2];
               ++var2;
            }

            this.updasettings();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492871);
      this.mHeadlight_off = this.findSwitchboxPreference("headlight_off");
      this.mHome_lighting = this.findSwitchboxPreference("home_lighting");
      this.mPower_saving = this.findSwitchboxPreference("power_saving");
      this.mRain_sensor = this.findSwitchboxPreference("rain_sensor");
      this.mAls = this.findSwitchboxPreference("als");
      this.mRear_view = this.findSwitchboxPreference("rear_view");
      this.mEngine_stop = this.findHCheckBoxPreference("engine_stop");
      this.RequestCmdkey((byte)49);
      switch (this.getCarType()) {
         case 0:
         default:
            break;
         case 1:
            this.removeAll();
            this.addPreference(this.mRear_view);
            break;
         case 2:
            this.removeAll();
            this.addPreference(this.mRear_view);
            break;
         case 3:
            this.removeAll();
            this.addPreference(this.mRear_view);
            break;
         case 4:
            this.removeAll();
            break;
         case 5:
            this.removeAll();
            break;
         case 6:
            this.removeAll();
            this.addPreference(this.mHeadlight_off);
            this.addPreference(this.mHome_lighting);
            this.addPreference(this.mPower_saving);
            this.addPreference(this.mRear_view);
            break;
         case 7:
            this.removeAll();
            this.addPreference(this.mHeadlight_off);
            this.addPreference(this.mHome_lighting);
            this.addPreference(this.mPower_saving);
            break;
         case 8:
            this.removeAll();
            this.addPreference(this.mHeadlight_off);
            this.addPreference(this.mHome_lighting);
            this.addPreference(this.mPower_saving);
            this.addPreference(this.mRain_sensor);
            this.addPreference(this.mAls);
            break;
         case 9:
            this.removeAll();
            this.addPreference(this.mHeadlight_off);
            this.addPreference(this.mHome_lighting);
            this.addPreference(this.mPower_saving);
            this.addPreference(this.mRain_sensor);
            this.addPreference(this.mAls);
            break;
         case 10:
            this.removeAll();
            this.addPreference(this.mHeadlight_off);
            this.addPreference(this.mHome_lighting);
            this.addPreference(this.mPower_saving);
            break;
         case 11:
            this.removeAll();
            this.addPreference(this.mHeadlight_off);
            this.addPreference(this.mHome_lighting);
            this.addPreference(this.mPower_saving);
            this.addPreference(this.mRain_sensor);
            this.addPreference(this.mRear_view);
      }

   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var5) {
         return false;
      }

      int var3;
      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var6) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mHeadlight_off) {
            this.SetCmdkey((byte)4, (byte)(var4 + 1));
         } else if (var1 == this.mHome_lighting) {
            this.SetCmdkey((byte)5, (byte)(var4 + 1));
         } else if (var1 == this.mPower_saving) {
            this.SetCmdkey((byte)6, (byte)(var4 + 1));
         } else if (var1 == this.mRain_sensor) {
            this.SetCmdkey((byte)7, (byte)(var4 + 1));
         } else if (var1 == this.mAls) {
            this.SetCmdkey((byte)8, (byte)(var4 + 1));
         } else if (var1 == this.mRear_view) {
            this.SetCmdkey((byte)9, (byte)(var4 + 0));
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      HCheckBoxPreference var3 = this.mEngine_stop;
      if (var2 == var3) {
         if (var3.mChecked) {
            this.SetCmdkey((byte)11, (byte)0);
         } else {
            this.SetCmdkey((byte)11, (byte)1);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
