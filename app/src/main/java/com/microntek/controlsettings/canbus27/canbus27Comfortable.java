package com.microntek.controlsettings.canbus27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27Comfortable extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean dis_mFolding_mirrors;
   private boolean dis_mParking_shift;
   private boolean dis_mPilot_personality;
   private boolean dis_mRearview_mirror_tilt;
   private boolean dis_mThe_rear_window_wiper;
   private boolean dis_mTilt_steering;
   private boolean dis_mTo_turn_to;
   private boolean isData = true;
   private boolean isDisplay;
   private boolean ismFolding_mirrors;
   private boolean ismParking_shift;
   private boolean ismPilot_personality;
   private boolean ismRearview_mirror_tilt;
   private boolean ismThe_rear_window_wiper;
   private boolean ismTilt_steering;
   private HCheckBoxPreference mFolding_mirrors;
   private HCheckBoxPreference mParking_shift;
   private HCheckBoxPreference mPilot_personality;
   private HCheckBoxPreference mRearview_mirror_tilt;
   private HCheckBoxPreference mThe_rear_window_wiper;
   private HCheckBoxPreference mTilt_steering;
   private SwitchboxPreference mTo_turn_to;
   private byte[] setData = new byte[16];
   private SharedPreferences sp;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)90, new byte[]{var1, var2}, 2);
   }

   private void updasettings55() {
      this.isData = false;
      if ((byte)(this.setData[0] & 64) == 64) {
         this.dis_mTo_turn_to = true;
         this.getPreferenceScreen().addItemFromInflater(this.mTo_turn_to);
         byte[] var1 = this.setData;
         if ((byte)(var1[1] & 96) == 96) {
            this.mTo_turn_to.setValue("3");
         } else if ((byte)(var1[1] & 64) == 64) {
            this.mTo_turn_to.setValue("2");
         } else if ((byte)(var1[1] & 32) == 32) {
            this.mTo_turn_to.setValue("1");
         } else {
            this.mTo_turn_to.setValue("0");
         }
      } else {
         this.dis_mTo_turn_to = false;
         this.getPreferenceScreen().removePreference(this.mTo_turn_to);
      }

      if ((byte)(this.setData[0] & 128) == -128) {
         this.dis_mParking_shift = true;
         this.getPreferenceScreen().addItemFromInflater(this.mParking_shift);
         if ((byte)(this.setData[1] & 128) == -128) {
            this.ismParking_shift = true;
            this.mParking_shift.setChecked(true);
         } else {
            this.ismParking_shift = false;
            this.mParking_shift.setChecked(false);
         }
      } else {
         this.dis_mParking_shift = false;
         this.getPreferenceScreen().removePreference(this.mParking_shift);
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.dis_mRearview_mirror_tilt = true;
         this.getPreferenceScreen().addItemFromInflater(this.mRearview_mirror_tilt);
         if ((byte)(this.setData[1] & 16) == 16) {
            this.ismRearview_mirror_tilt = true;
            this.mRearview_mirror_tilt.setChecked(true);
         } else {
            this.ismRearview_mirror_tilt = false;
            this.mRearview_mirror_tilt.setChecked(false);
         }
      } else {
         this.dis_mRearview_mirror_tilt = false;
         this.getPreferenceScreen().removePreference(this.mRearview_mirror_tilt);
      }

      if ((byte)(this.setData[0] & 16) == 16) {
         this.dis_mFolding_mirrors = true;
         this.getPreferenceScreen().addItemFromInflater(this.mFolding_mirrors);
         if ((byte)(this.setData[1] & 8) == 8) {
            this.ismFolding_mirrors = true;
            this.mFolding_mirrors.setChecked(true);
         } else {
            this.ismFolding_mirrors = false;
            this.mFolding_mirrors.setChecked(false);
         }
      } else {
         this.dis_mFolding_mirrors = false;
         this.getPreferenceScreen().removePreference(this.mFolding_mirrors);
      }

      if ((byte)(this.setData[0] & 8) == 8) {
         this.dis_mPilot_personality = true;
         this.getPreferenceScreen().addItemFromInflater(this.mPilot_personality);
         if ((byte)(this.setData[1] & 4) == 4) {
            this.ismPilot_personality = true;
            this.mPilot_personality.setChecked(true);
         } else {
            this.ismPilot_personality = false;
            this.mPilot_personality.setChecked(false);
         }
      } else {
         this.dis_mPilot_personality = false;
         this.getPreferenceScreen().removePreference(this.mPilot_personality);
      }

      if ((byte)(this.setData[0] & 4) == 4) {
         this.dis_mThe_rear_window_wiper = true;
         this.getPreferenceScreen().addItemFromInflater(this.mThe_rear_window_wiper);
         if ((byte)(this.setData[1] & 2) == 2) {
            this.ismThe_rear_window_wiper = true;
            this.mThe_rear_window_wiper.setChecked(true);
         } else {
            this.ismThe_rear_window_wiper = false;
            this.mThe_rear_window_wiper.setChecked(false);
         }
      } else {
         this.dis_mThe_rear_window_wiper = false;
         this.getPreferenceScreen().removePreference(this.mThe_rear_window_wiper);
      }

      if ((byte)(this.setData[0] & 2) == 2) {
         this.dis_mTilt_steering = true;
         this.getPreferenceScreen().addItemFromInflater(this.mTilt_steering);
         if ((byte)(this.setData[1] & 1) == 1) {
            this.ismTilt_steering = true;
            this.mTilt_steering.setChecked(true);
         } else {
            this.ismTilt_steering = false;
            this.mTilt_steering.setChecked(false);
         }
      } else {
         this.dis_mTilt_steering = false;
         this.getPreferenceScreen().removePreference(this.mTilt_steering);
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         if (var3 == 85) {
            while(var2 < var4 - 1) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings55();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492906);
      this.mParking_shift = (HCheckBoxPreference)this.findPreference("parking_shift");
      HCheckBoxPreference var2 = this.mParking_shift;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRearview_mirror_tilt = (HCheckBoxPreference)this.findPreference("rearview_mirror_tilt");
      var2 = this.mRearview_mirror_tilt;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mFolding_mirrors = (HCheckBoxPreference)this.findPreference("folding_mirrors");
      var2 = this.mFolding_mirrors;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mPilot_personality = (HCheckBoxPreference)this.findPreference("pilot_personality");
      var2 = this.mPilot_personality;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mThe_rear_window_wiper = (HCheckBoxPreference)this.findPreference("the_rear_window_wiper");
      var2 = this.mThe_rear_window_wiper;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mTilt_steering = (HCheckBoxPreference)this.findPreference("tilt_steering");
      var2 = this.mTilt_steering;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mTo_turn_to = (SwitchboxPreference)this.findPreference("to_turn_to");
      SwitchboxPreference var3 = this.mTo_turn_to;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.ismParking_shift = this.mParking_shift.isChecked();
      this.ismRearview_mirror_tilt = this.mRearview_mirror_tilt.isChecked();
      this.ismFolding_mirrors = this.mFolding_mirrors.isChecked();
      this.ismPilot_personality = this.mPilot_personality.isChecked();
      this.ismThe_rear_window_wiper = this.mThe_rear_window_wiper.isChecked();
      this.ismTilt_steering = this.mTilt_steering.isChecked();
      this.sp = this.getSharedPreferences("spcanbus27", 0);
      this.isDisplay = this.sp.getBoolean("dis_mTo_turn_to", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mTo_turn_to);
      }

      this.isDisplay = this.sp.getBoolean("dis_mTilt_steering", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mTilt_steering);
      }

      this.isDisplay = this.sp.getBoolean("dis_mThe_rear_window_wiper", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mThe_rear_window_wiper);
      }

      this.isDisplay = this.sp.getBoolean("dis_mPilot_personality", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mPilot_personality);
      }

      this.isDisplay = this.sp.getBoolean("dis_mFolding_mirrors", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mFolding_mirrors);
      }

      this.isDisplay = this.sp.getBoolean("dis_mRearview_mirror_tilt", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mRearview_mirror_tilt);
      }

      this.isDisplay = this.sp.getBoolean("dis_mParking_shift", true);
      if (!this.isDisplay) {
         this.getPreferenceScreen().removePreference(this.mParking_shift);
      }

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

      if (var3 == var4) {
         return true;
      } else {
         if (var1 == this.mTo_turn_to) {
            this.RequestCmdkey((byte)3, (byte)var3);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mParking_shift) {
         if (this.ismParking_shift) {
            this.RequestCmdkey((byte)1, (byte)0);
         } else {
            this.RequestCmdkey((byte)1, (byte)1);
         }
      }

      if (var1 == this.mRearview_mirror_tilt) {
         if (this.ismRearview_mirror_tilt) {
            this.RequestCmdkey((byte)2, (byte)0);
         } else {
            this.RequestCmdkey((byte)2, (byte)1);
         }
      }

      if (var1 == this.mFolding_mirrors) {
         if (this.ismFolding_mirrors) {
            this.RequestCmdkey((byte)4, (byte)0);
         } else {
            this.RequestCmdkey((byte)4, (byte)1);
         }
      }

      if (var1 == this.mPilot_personality) {
         if (this.ismPilot_personality) {
            this.RequestCmdkey((byte)5, (byte)0);
         } else {
            this.RequestCmdkey((byte)5, (byte)1);
         }
      }

      if (var1 == this.mThe_rear_window_wiper) {
         if (this.ismThe_rear_window_wiper) {
            this.RequestCmdkey((byte)6, (byte)0);
         } else {
            this.RequestCmdkey((byte)6, (byte)1);
         }
      }

      if (var1 == this.mTilt_steering) {
         if (this.ismTilt_steering) {
            this.RequestCmdkey((byte)7, (byte)0);
         } else {
            this.RequestCmdkey((byte)7, (byte)1);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
      if (!this.isData) {
         this.sp.edit().putBoolean("dis_mParking_shift", this.dis_mParking_shift).putBoolean("dis_mRearview_mirror_tilt", this.dis_mRearview_mirror_tilt).putBoolean("dis_mFolding_mirrors", this.dis_mFolding_mirrors).putBoolean("dis_mPilot_personality", this.dis_mPilot_personality).putBoolean("dis_mThe_rear_window_wiper", this.dis_mThe_rear_window_wiper).putBoolean("dis_mTilt_steering", this.dis_mTilt_steering).putBoolean("dis_mTo_turn_to", this.dis_mTo_turn_to).commit();
      }

   }
}
