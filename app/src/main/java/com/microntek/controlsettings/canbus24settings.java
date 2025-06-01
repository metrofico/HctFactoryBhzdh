package com.microntek.controlsettings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;

public class canbus24settings extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private byte age_full = 0;
   private byte lang = 0;
   private SwitchboxPreference mAge_full;
   private SwitchboxPreference mLang;
   private SwitchboxPreference mRange;
   private SwitchboxPreference mTemp;
   private SwitchboxPreference mTimes;
   private byte range = 0;
   private byte[] setData = new byte[16];
   private SharedPreferences sp;
   private byte temp = 0;
   private byte times = 0;

   private void RequestCmdkey(byte var1, byte var2, byte var3, byte var4, byte var5) {
      this.SendCanBusCmdData2E((byte)-126, new byte[]{var1, var2, var3, var4, var5}, 5);
   }

   private void updasettings4() {
      byte[] var1 = this.setData;
      this.range = var1[0];
      this.lang = var1[1];
      this.age_full = var1[2];
      this.temp = var1[3];
      this.times = var1[4];
      SwitchboxPreference var2 = this.mRange;
      StringBuilder var3 = new StringBuilder();
      var3.append("");
      var3.append(this.setData[0]);
      var2.setValue(var3.toString());
      var2 = this.mLang;
      var3 = new StringBuilder();
      var3.append("");
      var3.append(this.setData[1]);
      var2.setValue(var3.toString());
      var2 = this.mAge_full;
      var3 = new StringBuilder();
      var3.append("");
      var3.append(this.setData[2]);
      var2.setValue(var3.toString());
      SwitchboxPreference var4 = this.mTemp;
      StringBuilder var5 = new StringBuilder();
      var5.append("");
      var5.append(this.setData[3]);
      var4.setValue(var5.toString());
      var4 = this.mTimes;
      var5 = new StringBuilder();
      var5.append("");
      var5.append(this.setData[4]);
      var4.setValue(var5.toString());
      this.sp.edit().putInt("mRange", this.setData[0]).putInt("mLang", this.setData[1]).putInt("mAge_full", this.setData[2]).putInt("mTemp", this.setData[3]).putInt("mTimes", this.setData[4]).commit();
   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         if (var3 == 4) {
            while(var2 < var4 - 1) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings4();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492894);
      this.sp = this.getSharedPreferences("spcanbus24", 0);
      this.mRange = (SwitchboxPreference)this.findPreference("range");
      SwitchboxPreference var3 = this.mRange;
      SwitchboxPreference var2;
      StringBuilder var4;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
         this.range = (byte)this.sp.getInt("mRange", 0);
         var2 = this.mRange;
         var4 = new StringBuilder();
         var4.append("");
         var4.append(this.range);
         var2.setValue(var4.toString());
      }

      this.mLang = (SwitchboxPreference)this.findPreference("lang");
      var3 = this.mLang;
      StringBuilder var5;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
         this.lang = (byte)this.sp.getInt("mLang", 0);
         var3 = this.mLang;
         var5 = new StringBuilder();
         var5.append("");
         var5.append(this.lang);
         var3.setValue(var5.toString());
      }

      this.mAge_full = (SwitchboxPreference)this.findPreference("age_full");
      var3 = this.mAge_full;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
         this.age_full = (byte)this.sp.getInt("mAge_full", 0);
         var3 = this.mAge_full;
         var5 = new StringBuilder();
         var5.append("");
         var5.append(this.age_full);
         var3.setValue(var5.toString());
      }

      this.mTemp = (SwitchboxPreference)this.findPreference("temp");
      var3 = this.mTemp;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
         this.temp = (byte)this.sp.getInt("mTemp", 0);
         var2 = this.mTemp;
         var4 = new StringBuilder();
         var4.append("");
         var4.append(this.temp);
         var2.setValue(var4.toString());
      }

      this.mTimes = (SwitchboxPreference)this.findPreference("times");
      var3 = this.mTimes;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
         this.times = (byte)this.sp.getInt("mTimes", 0);
         var3 = this.mTimes;
         var5 = new StringBuilder();
         var5.append("");
         var5.append(this.times);
         var3.setValue(var5.toString());
         this.getPreferenceScreen().removePreference(this.mTimes);
      }

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
         if (var1 == this.mRange) {
            this.RequestCmdkey((byte)var4, this.lang, this.age_full, this.temp, this.times);
         } else if (var1 == this.mLang) {
            this.RequestCmdkey(this.range, (byte)var4, this.age_full, this.temp, this.times);
         } else if (var1 == this.mAge_full) {
            this.RequestCmdkey(this.range, this.lang, (byte)var4, this.temp, this.times);
         } else if (var1 == this.mTemp) {
            this.RequestCmdkey(this.range, this.lang, this.age_full, (byte)var4, this.times);
         } else if (var1 == this.mTimes) {
            this.RequestCmdkey(this.range, this.lang, this.age_full, this.temp, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      return false;
   }
}
