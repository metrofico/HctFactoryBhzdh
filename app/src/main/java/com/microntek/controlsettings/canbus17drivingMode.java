package com.microntek.controlsettings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

public class canbus17drivingMode extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private int cftype = 0;
   private HCheckBoxPreference mDriv_eco;
   private HCheckBoxPreference mIndividual;
   private SwitchboxPreference mIndividual_climate;
   private SwitchboxPreference mIndividual_engine;
   private PreferenceScreen mIndividual_reset;
   private SwitchboxPreference mIndividual_steering;
   private HCheckBoxPreference mNormal;
   private HCheckBoxPreference mSport;

   private void SetCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData2E((byte)-58, new byte[]{var1, var2}, 2);
   }

   protected void ProcessData(byte[] var1) {
      boolean var4 = false;
      if (var1[0] == 64 || var1[2] == -96) {
         int var2 = var1[3] & 15;
         HCheckBoxPreference var5 = this.mNormal;
         boolean var3;
         if (var2 == 0) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mSport;
         if (var2 == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mDriv_eco;
         if (var2 == 2) {
            var3 = true;
         } else {
            var3 = false;
         }

         var5.setChecked(var3);
         var5 = this.mIndividual;
         var3 = var4;
         if (var2 == 3) {
            var3 = true;
         }

         var5.setChecked(var3);
         if (var2 == 3) {
            this.getPreferenceScreen().addPreference(this.mIndividual_steering);
            this.getPreferenceScreen().addPreference(this.mIndividual_engine);
            this.getPreferenceScreen().addPreference(this.mIndividual_climate);
            this.getPreferenceScreen().addPreference(this.mIndividual_reset);
            SwitchboxPreference var7 = this.mIndividual_steering;
            StringBuilder var6 = new StringBuilder();
            var6.append("");
            var6.append(var1[4] & 3);
            var7.setValue(var6.toString());
            SwitchboxPreference var8 = this.mIndividual_engine;
            StringBuilder var9 = new StringBuilder();
            var9.append("");
            var9.append(var1[4] >> 4 & 3);
            var8.setValue(var9.toString());
            var7 = this.mIndividual_climate;
            var6 = new StringBuilder();
            var6.append("");
            var6.append(var1[5] & 3);
            var7.setValue(var6.toString());
         } else {
            this.getPreferenceScreen().removePreference(this.mIndividual_climate);
            this.getPreferenceScreen().removePreference(this.mIndividual_engine);
            this.getPreferenceScreen().removePreference(this.mIndividual_steering);
            this.getPreferenceScreen().removePreference(this.mIndividual_reset);
         }
      }

   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492891);
      this.mNormal = (HCheckBoxPreference)this.findPreference("normal");
      this.mNormal.setOnPreferenceClickListener(this);
      this.mSport = (HCheckBoxPreference)this.findPreference("sport");
      this.mSport.setOnPreferenceClickListener(this);
      this.mDriv_eco = (HCheckBoxPreference)this.findPreference("driv_eco");
      this.mDriv_eco.setOnPreferenceClickListener(this);
      this.mIndividual = (HCheckBoxPreference)this.findPreference("individual");
      this.mIndividual.setOnPreferenceClickListener(this);
      this.mIndividual_steering = (SwitchboxPreference)this.findPreference("individual_steering");
      this.mIndividual_steering.setOnPreferenceChangeListener(this);
      this.mIndividual_engine = (SwitchboxPreference)this.findPreference("individual_engine");
      this.mIndividual_engine.setOnPreferenceChangeListener(this);
      this.mIndividual_climate = (SwitchboxPreference)this.findPreference("individual_climate");
      this.mIndividual_climate.setOnPreferenceChangeListener(this);
      this.mIndividual_reset = (PreferenceScreen)this.findPreference("individual_reset");
      this.mIndividual_reset.setOnPreferenceClickListener(this);
      this.SendCanBusCmdData2E((byte)-15, new byte[]{7}, 1);
      byte[] var2 = this.getIntent().getByteArrayExtra("buf");
      if (var2 != null) {
         this.ProcessData(var2);
      }

   }

   protected void onNewIntent(Intent var1) {
      super.onNewIntent(var1);
      this.cftype = var1.getIntExtra("cftype", 0);
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
         if (var1 == this.mIndividual_steering) {
            this.SetCmdkey((byte)-47, (byte)var4);
         } else if (var1 == this.mIndividual_engine) {
            this.SetCmdkey((byte)-46, (byte)var4);
         } else if (var1 == this.mIndividual_climate) {
            this.SetCmdkey((byte)-45, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mNormal) {
         this.SetCmdkey((byte)-48, (byte)0);
      } else if (var1 == this.mSport) {
         this.SetCmdkey((byte)-48, (byte)1);
      } else if (var1 == this.mDriv_eco) {
         this.SetCmdkey((byte)-48, (byte)2);
      } else if (var1 == this.mIndividual) {
         this.SetCmdkey((byte)-48, (byte)3);
      }

      return false;
   }

   public boolean onPreferenceTreeClick(PreferenceScreen var1, Preference var2) {
      if (var2 == this.mIndividual_reset) {
         this.AlertDialog(new AlertDialogCallBack(this) {
            final canbus17drivingMode this$0;

            {
               this.this$0 = var1;
            }

            public void OkClick() {
               this.this$0.SetCmdkey((byte)-44, (byte)1);
            }
         }, this.mIndividual_reset.getTitle().toString());
      }

      return super.onPreferenceTreeClick(var1, var2);
   }
}
