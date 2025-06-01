package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;

public class canbus49settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private SwitchboxPreference mCar_in_light;
   private SwitchboxPreference mTemp_dis;
   private SwitchboxPreference mUnit_set;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      byte var2 = var1[0];
      if (var1.length > 2) {
         if (var2 == 41) {
            this.mTemp_dis.setValue(var1[6] >> 6 & 1);
         }

         if (var2 == 36) {
            this.mUnit_set.setValue(var1[4] >> 7 & 1);
         }

         if (var2 == 72) {
            this.mCar_in_light.setValue(var1[2] & 15);
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492922);
      this.mTemp_dis = this.findSwitchboxPreference("temp_dis");
      this.mUnit_set = this.findSwitchboxPreference("unit_set");
      this.mCar_in_light = this.findSwitchboxPreference("car_in_light");
      this.SendCanBusCmdData2E((byte)-112, new byte[]{72, 0}, 2);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{41, 0}, 2);
      this.SendCanBusCmdData2E((byte)-112, new byte[]{36, 0}, 2);
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
         if (var1 == this.mTemp_dis) {
            this.SetCmdkey((byte)-96, (byte)var4);
         } else if (var1 == this.mUnit_set) {
            this.SetCmdkey((byte)-93, (byte)(var4 + 14));
         } else if (var1 == this.mCar_in_light) {
            this.SetCmdkey((byte)-79, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      return false;
   }
}
