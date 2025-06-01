package com.microntek.controlsettings.canbus27;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import com.microntek.controlsettings.BasePreferenceActivity;
import com.microntek.controlsettings.HCheckBoxPreference;
import com.microntek.controlsettings.SwitchboxPreference;

public class canbus27RemoteControl extends BasePreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   private boolean A;
   private boolean B;
   private boolean C;
   private boolean D;
   private boolean E;
   private boolean F;
   private boolean G;
   private boolean H;
   private boolean I;
   private boolean ISONE = true;
   private boolean J;
   private boolean K;
   private boolean ismAuto_recognition;
   private boolean ismForget_key;
   private boolean ismOpen_remote_door;
   private boolean ismRemote_boot;
   private boolean ismRemote_door;
   private boolean ismRemote_unlocking;
   private HCheckBoxPreference mAuto_recognition;
   private HCheckBoxPreference mForget_key;
   private SwitchboxPreference mIntelligence_car;
   private SwitchboxPreference mIntelligent;
   private HCheckBoxPreference mOpen_remote_door;
   private HCheckBoxPreference mRemote_boot;
   private HCheckBoxPreference mRemote_door;
   private SwitchboxPreference mRemote_lock;
   private SwitchboxPreference mRemote_unlock;
   private HCheckBoxPreference mRemote_unlocking;
   private SwitchboxPreference mSliding_door;
   private byte[] setData = new byte[16];
   private SharedPreferences sp;
   private boolean status;

   private void RequestCmdkey(byte var1, byte var2) {
      this.SendCanBusCmdData5AA5((byte)107, new byte[]{var1, var2}, 2);
   }

   private void updasettings66() {
      this.ISONE = false;
      byte[] var1;
      if ((byte)(this.setData[0] & 128) == -128) {
         this.getPreferenceScreen().addItemFromInflater(this.mRemote_lock);
         this.A = true;
         var1 = this.setData;
         if ((byte)(var1[2] & 192) == -64) {
            this.mRemote_lock.setValue("3");
         } else if ((byte)(var1[2] & 128) == -128) {
            this.mRemote_lock.setValue("2");
         } else if ((byte)(var1[2] & 64) == 64) {
            this.mRemote_lock.setValue("1");
         } else {
            this.mRemote_lock.setValue("0");
         }
      } else {
         this.getPreferenceScreen().removePreference(this.mRemote_lock);
         this.A = false;
      }

      if ((byte)(this.setData[0] & 64) == 64) {
         this.getPreferenceScreen().addItemFromInflater(this.mRemote_unlocking);
         this.B = true;
         if ((byte)(this.setData[2] & 32) == 32) {
            this.ismRemote_unlocking = true;
            this.mRemote_unlocking.setChecked(true);
         } else {
            this.ismRemote_unlocking = false;
            this.mRemote_unlocking.setChecked(false);
         }
      } else {
         this.getPreferenceScreen().removePreference(this.mRemote_unlocking);
         this.B = false;
      }

      if ((byte)(this.setData[0] & 32) == 32) {
         this.getPreferenceScreen().addItemFromInflater(this.mRemote_unlock);
         this.C = true;
         if ((byte)(this.setData[2] & 16) == 16) {
            this.mRemote_unlock.setValue("1");
         } else {
            this.mRemote_unlock.setValue("0");
         }
      } else {
         this.getPreferenceScreen().removePreference(this.mRemote_unlock);
         this.C = false;
      }

      if ((byte)(this.setData[0] & 16) == 16) {
         this.getPreferenceScreen().addItemFromInflater(this.mRemote_door);
         this.D = true;
         if ((byte)(this.setData[2] & 8) == 8) {
            this.ismRemote_door = true;
            this.mRemote_door.setChecked(true);
         } else {
            this.ismRemote_door = false;
            this.mRemote_door.setChecked(false);
         }
      } else {
         this.mRemote_door.setChecked(false);
         this.getPreferenceScreen().removePreference(this.mRemote_door);
         this.D = false;
      }

      if ((byte)(this.setData[0] & 8) == 8) {
         this.getPreferenceScreen().addItemFromInflater(this.mOpen_remote_door);
         this.E = true;
         if ((byte)(this.setData[2] & 4) == 4) {
            this.ismOpen_remote_door = true;
            this.mOpen_remote_door.setChecked(true);
         } else {
            this.ismOpen_remote_door = false;
            this.mOpen_remote_door.setChecked(false);
         }
      } else {
         this.mOpen_remote_door.setChecked(false);
         this.getPreferenceScreen().removePreference(this.mOpen_remote_door);
         this.E = false;
      }

      if ((byte)(this.setData[0] & 4) == 4) {
         this.F = true;
         this.getPreferenceScreen().addItemFromInflater(this.mAuto_recognition);
         if ((byte)(this.setData[2] & 2) == 2) {
            this.ismAuto_recognition = true;
            this.mAuto_recognition.setChecked(true);
         } else {
            this.ismAuto_recognition = false;
            this.mAuto_recognition.setChecked(false);
         }
      } else {
         this.mAuto_recognition.setChecked(false);
         this.getPreferenceScreen().removePreference(this.mAuto_recognition);
         this.F = false;
      }

      if ((byte)(this.setData[0] & 2) == 2) {
         this.G = true;
         this.getPreferenceScreen().addItemFromInflater(this.mRemote_boot);
         if ((byte)(this.setData[2] & 1) == 1) {
            this.ismRemote_boot = true;
            this.mRemote_boot.setChecked(true);
         } else {
            this.ismRemote_boot = false;
            this.mRemote_boot.setChecked(false);
         }
      } else {
         this.mRemote_boot.setChecked(false);
         this.getPreferenceScreen().removePreference(this.mRemote_boot);
         this.G = false;
      }

      if ((byte)(this.setData[0] & 1) == 1) {
         this.H = true;
         this.getPreferenceScreen().addItemFromInflater(this.mIntelligent);
         if ((byte)(this.setData[3] & 128) == -128) {
            this.mIntelligent.setValue("1");
         } else {
            this.mIntelligent.setValue("0");
         }
      } else {
         this.getPreferenceScreen().removePreference(this.mIntelligent);
         this.H = false;
      }

      if ((byte)(this.setData[1] & 64) == 64) {
         this.getPreferenceScreen().addItemFromInflater(this.mForget_key);
         this.I = true;
         if ((byte)(this.setData[3] & 64) == 64) {
            this.ismForget_key = true;
            this.mForget_key.setChecked(true);
         } else {
            this.ismForget_key = false;
            this.mForget_key.setChecked(false);
         }
      } else {
         this.mForget_key.setChecked(false);
         this.getPreferenceScreen().removePreference(this.mForget_key);
         this.I = false;
      }

      if ((byte)(this.setData[1] & 128) == -128) {
         this.getPreferenceScreen().addItemFromInflater(this.mIntelligence_car);
         this.J = true;
         var1 = this.setData;
         if ((byte)(var1[3] & 32) == 32) {
            this.mIntelligence_car.setValue("2");
         } else if ((byte)(var1[3] & 16) == 16) {
            this.mIntelligence_car.setValue("1");
         } else {
            this.mIntelligence_car.setValue("0");
         }
      } else {
         this.getPreferenceScreen().removePreference(this.mIntelligence_car);
         this.J = false;
      }

      if ((byte)(this.setData[1] & 32) == 32) {
         this.getPreferenceScreen().addItemFromInflater(this.mSliding_door);
         this.K = true;
         if ((byte)(this.setData[3] & 8) == 8) {
            this.mSliding_door.setValue("1");
         } else {
            this.mSliding_door.setValue("0");
         }
      } else {
         this.getPreferenceScreen().removePreference(this.mSliding_door);
         this.K = false;
      }

   }

   protected void ProcessData(byte[] var1) {
      int var2 = 0;
      int var3 = var1[0];
      int var4 = var1.length;
      if (var4 > 1) {
         if (var3 == 102) {
            while(var2 < var4 - 1) {
               byte[] var5 = this.setData;
               var3 = var2 + 1;
               var5[var2] = var1[var3];
               var2 = var3;
            }

            this.updasettings66();
         }

      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.addPreferencesFromResource(2131492907);
      this.sp = this.getSharedPreferences("spcanbus27", 0);
      this.mRemote_unlocking = (HCheckBoxPreference)this.findPreference("remote_unlocking");
      HCheckBoxPreference var2 = this.mRemote_unlocking;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRemote_door = (HCheckBoxPreference)this.findPreference("remote_door");
      var2 = this.mRemote_door;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mOpen_remote_door = (HCheckBoxPreference)this.findPreference("open_remote_door");
      var2 = this.mOpen_remote_door;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mAuto_recognition = (HCheckBoxPreference)this.findPreference("auto_recognition");
      var2 = this.mAuto_recognition;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRemote_boot = (HCheckBoxPreference)this.findPreference("remote_boot");
      var2 = this.mRemote_boot;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mForget_key = (HCheckBoxPreference)this.findPreference("forget_key");
      var2 = this.mForget_key;
      if (var2 != null) {
         var2.setOnPreferenceClickListener(this);
      }

      this.mRemote_lock = (SwitchboxPreference)this.findPreference("remote_lock1");
      SwitchboxPreference var3 = this.mRemote_lock;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mRemote_unlock = (SwitchboxPreference)this.findPreference("remote_unlock");
      var3 = this.mRemote_unlock;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mIntelligent = (SwitchboxPreference)this.findPreference("intelligent");
      var3 = this.mIntelligent;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mIntelligence_car = (SwitchboxPreference)this.findPreference("intelligence_car");
      var3 = this.mIntelligence_car;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.mSliding_door = (SwitchboxPreference)this.findPreference("sliding_door");
      var3 = this.mSliding_door;
      if (var3 != null) {
         var3.setOnPreferenceChangeListener(this);
      }

      this.ismRemote_unlocking = this.mRemote_unlocking.isChecked();
      this.ismRemote_door = this.mRemote_door.isChecked();
      this.ismOpen_remote_door = this.mOpen_remote_door.isChecked();
      this.ismAuto_recognition = this.mAuto_recognition.isChecked();
      this.ismRemote_boot = this.mRemote_boot.isChecked();
      this.ismForget_key = this.mForget_key.isChecked();
      this.status = this.sp.getBoolean("B", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mRemote_unlocking);
      }

      this.status = this.sp.getBoolean("D", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mRemote_door);
      }

      this.status = this.sp.getBoolean("E", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mOpen_remote_door);
      }

      this.status = this.sp.getBoolean("F", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mAuto_recognition);
      }

      this.status = this.sp.getBoolean("G", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mRemote_boot);
      }

      this.status = this.sp.getBoolean("I", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mForget_key);
      }

      this.status = this.sp.getBoolean("A", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mRemote_lock);
      }

      this.status = this.sp.getBoolean("C", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mRemote_unlock);
      }

      this.status = this.sp.getBoolean("H", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mIntelligent);
      }

      this.status = this.sp.getBoolean("J", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mIntelligence_car);
      }

      this.status = this.sp.getBoolean("K", true);
      if (!this.status) {
         this.getPreferenceScreen().removePreference(this.mSliding_door);
      }

      Log.i("canbus27RemoteControl", "syncOn");
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
         if (var1 == this.mRemote_lock) {
            this.RequestCmdkey((byte)1, (byte)var4);
         } else if (var1 == this.mRemote_unlock) {
            this.RequestCmdkey((byte)3, (byte)var4);
         } else if (var1 == this.mIntelligent) {
            this.RequestCmdkey((byte)8, (byte)var4);
         } else if (var1 == this.mIntelligence_car) {
            this.RequestCmdkey((byte)10, (byte)var4);
         } else if (var1 == this.mSliding_door) {
            this.RequestCmdkey((byte)11, (byte)var4);
         }

         return false;
      }
   }

   public boolean onPreferenceClick(Preference var1) {
      if (var1 == this.mRemote_unlocking) {
         if (this.ismRemote_unlocking) {
            this.RequestCmdkey((byte)2, (byte)0);
         } else {
            this.RequestCmdkey((byte)2, (byte)1);
         }
      }

      if (var1 == this.mRemote_door) {
         if (this.ismRemote_door) {
            this.RequestCmdkey((byte)4, (byte)0);
         } else {
            this.RequestCmdkey((byte)4, (byte)1);
         }
      }

      if (var1 == this.mOpen_remote_door) {
         if (this.ismOpen_remote_door) {
            this.RequestCmdkey((byte)5, (byte)0);
         } else {
            this.RequestCmdkey((byte)5, (byte)1);
         }
      }

      if (var1 == this.mAuto_recognition) {
         if (this.ismAuto_recognition) {
            this.RequestCmdkey((byte)6, (byte)0);
         } else {
            this.RequestCmdkey((byte)6, (byte)1);
         }
      }

      if (var1 == this.mRemote_boot) {
         if (this.ismRemote_boot) {
            this.RequestCmdkey((byte)7, (byte)0);
         } else {
            this.RequestCmdkey((byte)7, (byte)1);
         }
      }

      if (var1 == this.mForget_key) {
         if (this.ismForget_key) {
            this.RequestCmdkey((byte)9, (byte)0);
         } else {
            this.RequestCmdkey((byte)9, (byte)1);
         }
      }

      return false;
   }

   protected void onStop() {
      super.onStop();
      if (!this.ISONE) {
         this.sp.edit().putBoolean("A", this.A).putBoolean("B", this.B).putBoolean("C", this.C).putBoolean("D", this.D).putBoolean("E", this.E).putBoolean("F", this.F).putBoolean("G", this.G).putBoolean("H", this.H).putBoolean("I", this.I).putBoolean("J", this.J).putBoolean("K", this.K).commit();
      }

   }
}
