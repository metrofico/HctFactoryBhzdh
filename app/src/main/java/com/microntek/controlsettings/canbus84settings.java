package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus84settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int _Rdjus = 2;
   private int _Screen3 = 0;
   private int _Screen4 = 0;
   private int _Screen5 = 0;
   private OnSwitchPreference mAdjust_outside;
   private HCheckBoxPreference mF_backlight;
   private SwitchboxPreference mHeadlight;
   private SwitchboxPreference mInterior;
   private HCheckBoxPreference mKeyless;
   private PreferenceScreen mRestore;
   private OnSwitchPreference mScreen3;
   private OnSwitchPreference mScreen4;
   private OnSwitchPreference mScreen5;
   private SwitchboxPreference mSecurity;
   private SwitchboxPreference mTrip_a;
   private SwitchboxPreference mTrip_b;

   private void SetCmdkey(byte var1, byte var2, byte var3) {
      this.SendCanBusCmdData5AA5(var1, new byte[]{var2, var3}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var3 = false;
      boolean var4 = false;
      byte var2 = var1[0];
      StringBuilder var5;
      StringBuilder var6;
      OnSwitchPreference var7;
      if (var2 != -24) {
         SwitchboxPreference var10;
         HCheckBoxPreference var11;
         if (var2 != 101) {
            if (var2 != 117) {
               if (var2 != 103) {
                  if (var2 == 104) {
                     this._Rdjus = var1[3] & 15;
                     var7 = this.mAdjust_outside;
                     var5 = new StringBuilder();
                     var5.append(this._Rdjus - 5);
                     var5.append(this.getString(2131296562));
                     var7.setValue("0", var5.toString());
                  }
               } else {
                  var2 = var1[3];
                  var10 = this.mInterior;
                  var6 = new StringBuilder();
                  var6.append("");
                  var6.append((var2 & 3) - 1);
                  var10.setValue(var6.toString());
                  var2 = var1[3];
                  var10 = this.mHeadlight;
                  StringBuilder var8 = new StringBuilder();
                  var8.append("");
                  var8.append(var2 >> 2 & 3);
                  var10.setValue(var8.toString());
               }
            } else {
               var11 = this.mF_backlight;
               var3 = var4;
               if ((var1[3] & 16) != 0) {
                  var3 = true;
               }

               var11.setChecked(var3);
               var2 = var1[3];
               var10 = this.mTrip_a;
               var6 = new StringBuilder();
               var6.append("");
               var6.append((var2 & 3) - 1);
               var10.setValue(var6.toString());
               var2 = var1[3];
               SwitchboxPreference var9 = this.mTrip_b;
               var5 = new StringBuilder();
               var5.append("");
               var5.append((var2 >> 2 & 3) - 1);
               var9.setValue(var5.toString());
            }
         } else {
            var2 = var1[3];
            var10 = this.mSecurity;
            var6 = new StringBuilder();
            var6.append("");
            var6.append((var2 >> 4 & 3) - 1);
            var10.setValue(var6.toString());
            var11 = this.mKeyless;
            if ((var1[3] & 64) != 0) {
               var3 = true;
            }

            var11.setChecked(var3);
         }
      } else {
         this._Screen3 = var1[2] & 255;
         OnSwitchPreference var12 = this.mScreen3;
         var5 = new StringBuilder();
         var5.append("");
         var5.append(this._Screen3);
         var12.setValue("0", var5.toString());
         this._Screen4 = var1[3];
         OnSwitchPreference var13 = this.mScreen4;
         var6 = new StringBuilder();
         var6.append("");
         var6.append(this._Screen4);
         var13.setValue("0", var6.toString());
         this._Screen5 = var1[4];
         var7 = this.mScreen5;
         var5 = new StringBuilder();
         var5.append("");
         var5.append(this._Screen5);
         var7.setValue("0", var5.toString());
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492946);
      this.mKeyless = (HCheckBoxPreference)this.findPreference("keyless");
      this.mKeyless.setOnPreferenceClickListener(this);
      this.mF_backlight = (HCheckBoxPreference)this.findPreference("f_backlight");
      this.mF_backlight.setOnPreferenceClickListener(this);
      this.mSecurity = (SwitchboxPreference)this.findPreference("security");
      this.mSecurity.setOnPreferenceChangeListener(this);
      this.mInterior = (SwitchboxPreference)this.findPreference("interior");
      this.mInterior.setOnPreferenceChangeListener(this);
      this.mHeadlight = (SwitchboxPreference)this.findPreference("headlight");
      this.mHeadlight.setOnPreferenceChangeListener(this);
      this.mTrip_a = (SwitchboxPreference)this.findPreference("trip_a");
      this.mTrip_a.setOnPreferenceChangeListener(this);
      this.mTrip_b = (SwitchboxPreference)this.findPreference("trip_b");
      this.mTrip_b.setOnPreferenceChangeListener(this);
      this.mAdjust_outside = (OnSwitchPreference)this.findPreference("adjust_outside");
      this.mAdjust_outside.setOnPreferenceChangeListener(this);
      this.mScreen3 = (OnSwitchPreference)this.findPreference("screen3");
      this.mScreen3.setOnPreferenceChangeListener(this);
      this.mScreen4 = (OnSwitchPreference)this.findPreference("screen4");
      this.mScreen4.setOnPreferenceChangeListener(this);
      this.mScreen5 = (OnSwitchPreference)this.findPreference("screen5");
      this.mScreen5.setOnPreferenceChangeListener(this);
      this.mRestore = (PreferenceScreen)this.findPreference("restore");
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      OnSwitchPreference var5 = this.mAdjust_outside;
      int var3;
      int var4;
      if (var1 == var5) {
         var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
         var4 = this._Rdjus;
         if (var4 + var3 < 2 || var4 + var3 > 8 || !this.mAdjust_outside.getIsDow()) {
            return true;
         }

         this.SetCmdkey((byte)109, (byte)1, (byte)(this._Rdjus + var3 & 255));
      } else {
         var5 = this.mScreen3;
         if (var1 == var5) {
            var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
            var3 = this._Screen3;
            if (var3 + var4 < 0 || var3 + var4 > 255 || !this.mScreen3.getIsDow()) {
               return true;
            }

            this.SetCmdkey((byte)-14, (byte)1, (byte)(this._Screen3 + var4));
         } else {
            var5 = this.mScreen4;
            if (var1 == var5) {
               var3 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
               var4 = this._Screen4;
               if (var4 + var3 < -5 || var4 + var3 > 5 || !this.mScreen4.getIsDow()) {
                  return true;
               }

               this.SetCmdkey((byte)-14, (byte)2, (byte)(this._Screen4 + var3));
            } else {
               var5 = this.mScreen5;
               if (var1 == var5) {
                  var4 = Integer.parseInt((String)var5.getEntries()[Integer.parseInt((String)var2)]) + 0;
                  var3 = this._Screen5;
                  if (var3 + var4 < -5 || var3 + var4 > 5 || !this.mScreen5.getIsDow()) {
                     return true;
                  }

                  this.SetCmdkey((byte)-14, (byte)3, (byte)(this._Screen5 + var4));
               }
            }
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
         if (var1 == this.mSecurity) {
            this.SetCmdkey((byte)106, (byte)3, (byte)(var4 + 1));
         } else if (var1 == this.mInterior) {
            this.SetCmdkey((byte)108, (byte)1, (byte)(var4 + 1));
         } else if (var1 == this.mHeadlight) {
            this.SetCmdkey((byte)108, (byte)2, (byte)(var4 + 0));
         } else if (var1 == this.mTrip_a) {
            this.SetCmdkey((byte)122, (byte)1, (byte)(var4 + 1));
         } else if (var1 == this.mTrip_b) {
            this.SetCmdkey((byte)122, (byte)2, (byte)(var4 + 1));
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      HCheckBoxPreference var2 = this.mKeyless;
      if (var1 == var2) {
         if (var2.mChecked) {
            this.SetCmdkey((byte)106, (byte)4, (byte)0);
         } else {
            this.SetCmdkey((byte)106, (byte)4, (byte)1);
         }
      } else {
         var2 = this.mF_backlight;
         if (var1 == var2) {
            if (var2.mChecked) {
               this.SetCmdkey((byte)122, (byte)3, (byte)0);
            } else {
               this.SetCmdkey((byte)122, (byte)3, (byte)1);
            }
         }
      }

      return false;
   }

   @Deprecated
   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.mRestore) {
         this.SetCmdkey((byte)110, (byte)3, (byte)0);
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
