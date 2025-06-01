package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus135settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private int _mRange = 0;
   private HCheckBoxPreference mAud_obc;
   private PreferenceScreen mFactory_settings;
   private SwitchboxPreference mLanguages;
   private HCheckBoxPreference mMemo;
   private SwitchboxPreference mMileage_unit;
   private SwitchboxPreference mOil_unit;
   private SwitchboxPreference mRange;
   private HCheckBoxPreference mSpeed_activation;
   private SwitchboxPreference mSpeed_limit;
   private SwitchboxPreference mTemp;
   private OnSwitchPreference mVol;

   private void SetCmdkey(int var1, int var2, int var3) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{(byte)var1, (byte)var2, (byte)var3}, 3);
   }

   protected void ProcessData(byte[] var1) {
      if (super.cmd == 56 && super.length >= 9) {
         SwitchboxPreference var6 = this.mTemp;
         byte var2 = var1[4];
         boolean var5 = true;
         var6.setValue(var2 >> 1 & 1);
         HCheckBoxPreference var7 = this.mSpeed_activation;
         boolean var4;
         if ((var1[4] & 1) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var7.setChecked(var4);
         byte var3 = var1[2];
         var2 = var1[3];
         this.mSpeed_limit.setValue((var3 & 255) << 8 | var2 & 255);
         this.mOil_unit.setValue(var1[4] >> 2 & 3);
         this.mMileage_unit.setValue(var1[4] >> 4 & 1);
         var7 = this.mMemo;
         if ((var1[4] & 32) != 0) {
            var4 = true;
         } else {
            var4 = false;
         }

         var7.setChecked(var4);
         var7 = this.mAud_obc;
         if ((var1[4] & 128) != 0) {
            var4 = var5;
         } else {
            var4 = false;
         }

         var7.setChecked(var4);
         this._mRange = (var1[5] & 255) << 8 | var1[6] & 255;
         this.mRange.setValue(this._mRange);
         this.mLanguages.setValue(var1[9] & 255);
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492877);
      this.mSpeed_limit = this.findSwitchboxPreference("speed_limit");
      CharSequence[] var6 = new CharSequence[295];
      CharSequence[] var4 = new CharSequence[295];
      var4[294] = "65535";
      var6[294] = "---";
      byte var3 = 0;

      int var2;
      StringBuilder var5;
      String var8;
      for(var2 = 0; var2 < var6.length - 1; ++var2) {
         var5 = new StringBuilder();
         var5.append("");
         var5.append(var2 + 6);
         var8 = var5.toString();
         var6[var2] = var8;
         var4[var2] = var8;
      }

      this.mSpeed_limit.setEntries(var6);
      this.mSpeed_limit.setEntryValues(var4);
      this.mTemp = this.findSwitchboxPreference("temp");
      this.mOil_unit = this.findSwitchboxPreference("oil_unit");
      this.mMileage_unit = this.findSwitchboxPreference("mileage_unit");
      this.mRange = this.findSwitchboxPreference("range");
      var4 = new CharSequence[10001];
      var6 = new CharSequence[10001];
      var6[10000] = "65535";
      var4[10000] = "---";

      for(var2 = 0; var2 < var4.length - 1; ++var2) {
         var5 = new StringBuilder();
         var5.append("");
         var5.append(var2);
         var8 = var5.toString();
         var4[var2] = var8;
         var6[var2] = var8;
      }

      this.mRange.setEntries(var4);
      this.mRange.setEntryValues(var6);
      this.mLanguages = this.findSwitchboxPreference("languages");
      var4 = new CharSequence[6];
      CharSequence[] var9 = new CharSequence[6];
      var4[0] = "US";
      var4[1] = "E";
      var4[2] = "I";
      var4[3] = "F";
      var4[4] = "GB";
      var4[5] = "D";

      for(var2 = var3; var2 < var4.length; ++var2) {
         StringBuilder var7 = new StringBuilder();
         var7.append("");
         var7.append(var2);
         var9[var2] = var7.toString();
      }

      this.mLanguages.setEntries(var4);
      this.mLanguages.setEntryValues(var9);
      this.mSpeed_activation = this.findHCheckBoxPreference("speed_activation");
      this.mMemo = this.findHCheckBoxPreference("memo");
      this.mAud_obc = this.findHCheckBoxPreference("aud_obc");
      this.mVol = this.findOnSwitchPreference("vol");
      this.mFactory_settings = this.findPreferenceScreen("factory_settings");
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      int var4;
      try {
         var4 = Integer.parseInt((String)var2);
      } catch (Exception var6) {
         return false;
      }

      OnSwitchPreference var5 = this.mVol;
      int var3;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]);
         if (!this.mVol.getIsDow()) {
            return true;
         }

         OnSwitchPreference var8 = this.mVol;
         StringBuilder var10 = new StringBuilder();
         var10.append("");
         var10.append(var4);
         var8.setValue(var10.toString());
         byte var9;
         if (var3 + 0 == 1) {
            var9 = 1;
         } else {
            var9 = 0;
         }

         this.SendCanBusCmdData2E((byte)-56, new byte[]{0, (byte)var9}, 2);
      }

      try {
         var3 = Integer.parseInt(((SwitchboxPreference)var1).getValue());
      } catch (Exception var7) {
         return true;
      }

      if (var4 == var3) {
         return true;
      } else {
         if (var1 == this.mSpeed_limit) {
            this.SetCmdkey(1, var4 >> 8, var4 & 255);
         } else if (var1 == this.mTemp) {
            this.SetCmdkey(2, var4, 0);
         } else if (var1 == this.mOil_unit) {
            this.SetCmdkey(3, var4, 0);
         } else if (var1 == this.mMileage_unit) {
            this.SetCmdkey(4, var4, 0);
         } else if (var1 == this.mRange) {
            var3 = var4;
            if (var4 > this._mRange) {
               var3 = var4 + 9;
            }

            var4 = var3;
            if (var3 < this._mRange) {
               var4 = var3 - 9;
            }

            this.SetCmdkey(8, var4 >> 8, var4 & 255);
         } else if (var1 == this.mLanguages) {
            this.SetCmdkey(7, var4, 0);
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      boolean var5;
      if (var2 instanceof HCheckBoxPreference) {
         var5 = ((HCheckBoxPreference)var2).mChecked;
      } else {
         var5 = false;
      }

      HCheckBoxPreference var6 = this.mSpeed_activation;
      byte var4 = 1;
      byte var3;
      if (var2 == var6) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(0, var3, 0);
      }

      if (var2 == this.mMemo) {
         if (var5) {
            var3 = 0;
         } else {
            var3 = 1;
         }

         this.SetCmdkey(5, var3, 0);
      }

      if (var2 == this.mAud_obc) {
         var3 = var4;
         if (var5) {
            var3 = 0;
         }

         this.SetCmdkey(6, var3, 0);
      } else if (var2 == this.mFactory_settings) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus135settings this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey(11, 0, 0);
            }
         }, this.mFactory_settings.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
