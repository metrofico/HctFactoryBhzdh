package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus137settings extends Base2PreferenceActivity {
   private void SetCmdkey(int var1, int var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{(byte)var1, (byte)var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 56) {
         this.findSwitchboxPreference("exterior_time").setValue(var1[5] >> 0 & 15);
         HCheckBoxPreference var5 = this.findHCheckBoxPreference("running_lights");
         byte var2 = var1[3];
         boolean var4 = true;
         boolean var3;
         if ((var2 & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.findSwitchboxPreference("interior_time").setValue(var1[2] >> 0 & 31);
         this.findSwitchboxPreference("interior_light").setValue(var1[3] >> 4 & 15);
         this.findSwitchboxPreference("raining").setValue(var1[3] >> 2 & 1);
         this.findSwitchboxPreference("rear_wiper").setValue(var1[3] >> 1 & 1);
         this.findSwitchboxPreference("reversing_opt").setValue(var1[3] >> 0 & 1);
         this.findSwitchboxPreference("door_unlock_mode").setValue(var1[4] >> 7 & 1);
         this.findSwitchboxPreference("door_lock").setValue(var1[4] >> 5 & 3);
         var5 = this.findHCheckBoxPreference("air_panel");
         if ((var1[4] & 8) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.findHCheckBoxPreference("comfort_entry");
         if ((var1[4] & 16) != 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.findHCheckBoxPreference("air_circ");
         if ((var1[4] & 4) != 0) {
            var3 = var4;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         this.findSwitchboxPreference("air_style").setValue(var1[4] >> 0 & 3);
      }

   }

   protected int getPreferencesResId() {
      return 2131492878;
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      SwitchboxPreference var5 = this.findSwitchboxPreference("exterior_time");
      CharSequence[] var7 = new CharSequence[12];
      CharSequence[] var4 = new CharSequence[12];

      int var2;
      int var3;
      StringBuilder var6;
      for(var2 = 0; var2 < var7.length; var2 = var3) {
         var6 = new StringBuilder();
         var6.append("");
         var3 = var2 + 1;
         var6.append(var3);
         var4[var2] = var6.toString();
         var6 = new StringBuilder();
         var6.append(var3 * 10);
         var6.append("S");
         var7[var2] = var6.toString();
      }

      var5.setEntries(var7);
      var5.setEntryValues(var4);
      SwitchboxPreference var8 = this.findSwitchboxPreference("interior_time");
      CharSequence[] var9 = new CharSequence[31];
      var7 = new CharSequence[31];

      for(var2 = 0; var2 < var9.length; ++var2) {
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var2);
         var7[var2] = var6.toString();
         var6 = new StringBuilder();
         var6.append(var2 * 10);
         var6.append("S");
         var9[var2] = var6.toString();
      }

      var8.setEntries(var9);
      var8.setEntryValues(var7);
      var8 = this.findSwitchboxPreference("interior_light");
      var7 = new CharSequence[11];
      var9 = new CharSequence[11];

      for(var2 = 0; var2 < var7.length; ++var2) {
         var6 = new StringBuilder();
         var6.append("");
         var6.append(var2);
         var9[var2] = var6.toString();
         var6 = new StringBuilder();
         var6.append(var2 * 10);
         var6.append(this.getString(2131296425));
         var7[var2] = var6.toString();
      }

      var8.setEntries(var7);
      var8.setEntryValues(var9);
      this.SendCanBusCmdData2E((byte)-1, new byte[]{56}, 1);
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
         String var7 = var1.getKey();
         if ("exterior_time".equals(var7)) {
            this.SetCmdkey(1, var4);
         } else if ("interior_time".equals(var7)) {
            this.SetCmdkey(2, var4);
         } else if ("interior_light".equals(var7)) {
            this.SetCmdkey(3, var4);
         } else if ("raining".equals(var7)) {
            this.SetCmdkey(4, var4);
         } else if ("rear_wiper".equals(var7)) {
            this.SetCmdkey(5, var4);
         } else if ("reversing_opt".equals(var7)) {
            this.SetCmdkey(6, var4);
         } else if ("door_unlock_mode".equals(var7)) {
            this.SetCmdkey(7, var4);
         } else if ("door_lock".equals(var7)) {
            this.SetCmdkey(8, var4);
         } else if ("air_style".equals(var7)) {
            this.SetCmdkey(17, var4);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      String var9 = var2.getKey();
      if (var2 instanceof HCheckBoxPreference) {
         boolean var7 = ((HCheckBoxPreference)var2).mChecked;
         boolean var8 = "running_lights".equals(var9);
         byte var5 = 1;
         byte var4 = 1;
         byte var6 = 1;
         byte var3 = 1;
         if (var8) {
            if (var7) {
               var3 = 0;
            }

            this.SetCmdkey(0, var3);
         } else if ("air_panel".equals(var9)) {
            var3 = var5;
            if (var7) {
               var3 = 0;
            }

            this.SetCmdkey(9, var3);
         } else if ("comfort_entry".equals(var9)) {
            var3 = var4;
            if (var7) {
               var3 = 0;
            }

            this.SetCmdkey(10, var3);
         } else if ("air_circ".equals(var9)) {
            var3 = var6;
            if (var7) {
               var3 = 0;
            }

            this.SetCmdkey(11, var3);
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
