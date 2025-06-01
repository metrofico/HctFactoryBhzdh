package com.microntek.controlsettings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus14settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener {
   private SwitchboxPreference mAutodoorlockmode;
   private SwitchboxPreference mAutoheadlighton;
   private SwitchboxPreference mAutorelocktime;
   private SwitchboxPreference mClosedoortime;
   private HCheckBoxPreference mFlashturnsigal;
   private SwitchboxPreference mHeadlightofftime;
   private SwitchboxPreference mLangauage;
   private SwitchboxPreference mLockbeepvol;
   private SwitchboxPreference mOpendoortime;
   private SwitchboxPreference mUnlockmode;
   private HCheckBoxPreference mWalkalwaylock;
   private HCheckBoxPreference mWipers;
   private byte[] setData = new byte[3];
   private byte[] setdataval = new byte[13];

   private void RequestCmdkey(byte var1) {
      this.SendCanBusCmdData2E((byte)-112, new byte[]{var1}, 1);
   }

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   private void updasettings() {
      byte[] var2 = this.setData;
      int var1;
      if (var2[0] < 0) {
         var1 = 4;
      } else {
         var1 = (var2[0] & 96) >> 5 & 3;
      }

      SwitchboxPreference var3 = this.mAutodoorlockmode;
      StringBuilder var5 = new StringBuilder();
      var5.append(var1);
      var5.append("");
      var3.setValue(var5.toString());
      byte var4;
      if ((byte)(this.setData[0] & 16) == 16) {
         var4 = 1;
      } else {
         var4 = 0;
      }

      SwitchboxPreference var6 = this.mUnlockmode;
      StringBuilder var8 = new StringBuilder();
      var8.append(var4);
      var8.append("");
      var6.setValue(var8.toString());
      byte var7 = this.setData[0];
      var3 = this.mAutorelocktime;
      var5 = new StringBuilder();
      var5.append((var7 & 12) >> 2 & 3);
      var5.append("");
      var3.setValue(var5.toString());
      var7 = this.setData[0];
      var6 = this.mLockbeepvol;
      var8 = new StringBuilder();
      var8.append(var7 & 3);
      var8.append("");
      var6.setValue(var8.toString());
      if (this.setData[1] < 0) {
         this.mWalkalwaylock.setChecked(true);
      } else {
         this.mWalkalwaylock.setChecked(false);
      }

      var7 = this.setData[1];
      var3 = this.mOpendoortime;
      var5 = new StringBuilder();
      var5.append((var7 & 96) >> 5 & 3);
      var5.append("");
      var3.setValue(var5.toString());
      var7 = this.setData[1];
      var3 = this.mClosedoortime;
      var5 = new StringBuilder();
      var5.append((var7 & 24) >> 3 & 3);
      var5.append("");
      var3.setValue(var5.toString());
      var7 = this.setData[1];
      var6 = this.mHeadlightofftime;
      var8 = new StringBuilder();
      var8.append(var7 & 7);
      var8.append("");
      var6.setValue(var8.toString());
      if (this.setData[2] < 0) {
         this.mFlashturnsigal.setChecked(true);
      } else {
         this.mFlashturnsigal.setChecked(false);
      }

      var7 = this.setData[2];
      var6 = this.mAutoheadlighton;
      var8 = new StringBuilder();
      var8.append((var7 & 112) >> 4 & 7);
      var8.append("");
      var6.setValue(var8.toString());
      if ((byte)(this.setData[2] & 8) == 8) {
         this.mWipers.setChecked(true);
      } else {
         this.mWipers.setChecked(false);
      }

      var7 = this.setData[2];
      var6 = this.mLangauage;
      var8 = new StringBuilder();
      var8.append(var7 & 7);
      var8.append("");
      var6.setValue(var8.toString());
   }

   private void updasettings(byte var1, byte var2) {
      if (var2 != 1) {
         SwitchboxPreference var3;
         StringBuilder var4;
         StringBuilder var5;
         SwitchboxPreference var6;
         switch (var1) {
            case 0:
               var6 = this.mAutodoorlockmode;
               var5 = new StringBuilder();
               var5.append(this.setdataval[0]);
               var5.append("");
               var6.setValue(var5.toString());
               break;
            case 1:
               var3 = this.mUnlockmode;
               var4 = new StringBuilder();
               var4.append(this.setdataval[1]);
               var4.append("");
               var3.setValue(var4.toString());
               break;
            case 2:
               var3 = this.mAutorelocktime;
               var4 = new StringBuilder();
               var4.append(this.setdataval[2]);
               var4.append("");
               var3.setValue(var4.toString());
               break;
            case 3:
               var3 = this.mLockbeepvol;
               var4 = new StringBuilder();
               var4.append(this.setdataval[3]);
               var4.append("");
               var3.setValue(var4.toString());
               break;
            case 4:
               if (this.setdataval[4] == 1) {
                  this.mWalkalwaylock.setChecked(true);
               } else {
                  this.mWalkalwaylock.setChecked(false);
               }
               break;
            case 5:
               var6 = this.mOpendoortime;
               var5 = new StringBuilder();
               var5.append(this.setdataval[5]);
               var5.append("");
               var6.setValue(var5.toString());
               break;
            case 6:
               var6 = this.mClosedoortime;
               var5 = new StringBuilder();
               var5.append(this.setdataval[6]);
               var5.append("");
               var6.setValue(var5.toString());
               break;
            case 7:
               var3 = this.mHeadlightofftime;
               var4 = new StringBuilder();
               var4.append(this.setdataval[7]);
               var4.append("");
               var3.setValue(var4.toString());
               break;
            case 8:
               if (this.setdataval[8] == 1) {
                  this.mFlashturnsigal.setChecked(true);
               } else {
                  this.mFlashturnsigal.setChecked(false);
               }
               break;
            case 9:
               var6 = this.mAutoheadlighton;
               var5 = new StringBuilder();
               var5.append(this.setdataval[9]);
               var5.append("");
               var6.setValue(var5.toString());
               break;
            case 10:
               if (this.setdataval[10] == 1) {
                  this.mWipers.setChecked(true);
               } else {
                  this.mWipers.setChecked(false);
               }
               break;
            case 11:
               var3 = this.mAutoheadlighton;
               var4 = new StringBuilder();
               var4.append(this.setdataval[11]);
               var4.append("");
               var3.setValue(var4.toString());
         }

      }
   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      byte var3 = var1[0];
      if (var1.length > 2) {
         if (var3 != 50) {
            if (var3 == 51) {
               this.updasettings(var1[2], var1[3]);
            }
         } else {
            while(var2 < 3) {
               this.setData[var2] = var1[var2 + 2];
               ++var2;
            }

            this.updasettings();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492880);
      this.mAutodoorlockmode = (SwitchboxPreference)this.findPreference("autodoorlockmode");
      SwitchboxPreference var2 = this.mAutodoorlockmode;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mUnlockmode = (SwitchboxPreference)this.findPreference("unlockmode");
      var2 = this.mUnlockmode;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mAutorelocktime = (SwitchboxPreference)this.findPreference("autorelocktime");
      var2 = this.mAutorelocktime;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mLockbeepvol = (SwitchboxPreference)this.findPreference("lockbeepvol");
      var2 = this.mLockbeepvol;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mOpendoortime = (SwitchboxPreference)this.findPreference("opendoortime");
      var2 = this.mOpendoortime;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mClosedoortime = (SwitchboxPreference)this.findPreference("closedoortime");
      var2 = this.mClosedoortime;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mHeadlightofftime = (SwitchboxPreference)this.findPreference("headlightofftime");
      var2 = this.mHeadlightofftime;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mAutoheadlighton = (SwitchboxPreference)this.findPreference("autoheadlighton");
      var2 = this.mAutoheadlighton;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mLangauage = (SwitchboxPreference)this.findPreference("langauage");
      var2 = this.mLangauage;
      if (var2 != null) {
         var2.setOnPreferenceChangeListener(this);
      }

      this.mWalkalwaylock = (HCheckBoxPreference)this.findPreference("walkalwaylock");
      this.mFlashturnsigal = (HCheckBoxPreference)this.findPreference("flashturnsigal");
      this.mWipers = (HCheckBoxPreference)this.findPreference("wipers");
      this.RequestCmdkey((byte)50);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
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
         byte var3;
         if (var1 == this.mAutodoorlockmode) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)0, var3);
            this.setdataval[0] = var3;
         } else if (var1 == this.mUnlockmode) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)1, var3);
            this.setdataval[1] = var3;
         } else if (var1 == this.mAutorelocktime) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)2, var3);
            this.setdataval[2] = var3;
         } else if (var1 == this.mLockbeepvol) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)3, var3);
            this.setdataval[3] = var3;
         } else if (var1 == this.mOpendoortime) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)5, var3);
            this.setdataval[5] = var3;
         } else if (var1 == this.mClosedoortime) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)6, var3);
            this.setdataval[6] = var3;
         } else if (var1 == this.mHeadlightofftime) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)8, var3);
            this.setdataval[8] = var3;
         } else if (var1 == this.mAutoheadlighton) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)9, var3);
            this.setdataval[9] = var3;
         } else if (var1 == this.mLangauage) {
            var3 = (byte)var4;
            this.SetCmdkey((byte)11, var3);
            this.setdataval[11] = var3;
         }

         return true;
      }
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      HCheckBoxPreference var3 = this.mWalkalwaylock;
      if (var2 == var3) {
         if (var3.isChecked()) {
            this.SetCmdkey((byte)4, (byte)1);
            this.setdataval[4] = 1;
         } else {
            this.SetCmdkey((byte)4, (byte)0);
            this.setdataval[4] = 0;
         }
      } else {
         var3 = this.mFlashturnsigal;
         if (var2 == var3) {
            if (var3.isChecked()) {
               this.SetCmdkey((byte)7, (byte)1);
               this.setdataval[7] = 1;
            } else {
               this.SetCmdkey((byte)7, (byte)0);
               this.setdataval[7] = 0;
            }
         } else {
            var3 = this.mWipers;
            if (var2 == var3) {
               if (var3.isChecked()) {
                  this.SetCmdkey((byte)10, (byte)1);
                  this.setdataval[10] = 1;
               } else {
                  this.SetCmdkey((byte)10, (byte)0);
                  this.setdataval[10] = 0;
               }
            }
         }
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
