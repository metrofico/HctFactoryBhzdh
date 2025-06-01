package com.microntek.controlsettings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus45settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private PreferenceScreen mFuelclear;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   public void InitUi() {
      this.addPreferencesFromResource(2131492920);
      this.mFuelclear = (PreferenceScreen)this.findPreference("fuelclear");
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.InitUi();
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

      return var3 == var4;
   }

   public boolean onPreferenceClick(Preference var1) {
      return false;
   }

   @Deprecated
   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.mFuelclear) {
         (new AlertDialog.Builder(this)).setTitle(this.getString(2131297021)).setMessage(this.getString(2131296704)).setPositiveButton(this.getString(2131296522), new DialogInterface.OnClickListener(this) {
            final canbus45settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               this.this$0.SetCmdkey((byte)1, (byte)1);
               var1.cancel();
            }
         }).setNegativeButton(this.getString(2131296461), new DialogInterface.OnClickListener(this) {
            final canbus45settings this$0;

            {
               this.this$0 = var1;
            }

            public void onClick(DialogInterface var1, int var2) {
               var1.cancel();
            }
         }).create().show();
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
