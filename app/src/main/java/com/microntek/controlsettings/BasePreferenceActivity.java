package com.microntek.controlsettings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;
import android.util.Log;

public class BasePreferenceActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
   public SerialBroadcast SerialBroadcast = null;
   protected int canbustype = 0;
   protected int cmd = 0;
   protected int length = 0;

   public void AlertDialog(AlertDialogCallBack var1, String var2) {
      (new AlertDialog.Builder(this)).setTitle(this.getString(2131297021)).setMessage(var2).setPositiveButton(this.getString(17039370), new DialogInterface.OnClickListener(this, var1) {
         final BasePreferenceActivity this$0;
         final AlertDialogCallBack val$callBack;

         {
            this.this$0 = var1;
            this.val$callBack = var2;
         }

         public void onClick(DialogInterface var1, int var2) {
            this.val$callBack.OkClick();
            var1.cancel();
         }
      }).setNegativeButton(this.getString(17039360), new DialogInterface.OnClickListener(this) {
         final BasePreferenceActivity this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(DialogInterface var1, int var2) {
            var1.cancel();
         }
      }).create().show();
   }

   protected void BaseProcessData(byte[] var1) {
      this.length = var1[1] & 255;
      this.cmd = var1[0] & 255;
      this.ProcessData(var1);
   }

   public byte[] GetCarByteArrayState(int var1) {
      BaseApplication var2 = BaseApplication.getInstance();
      StringBuilder var3 = new StringBuilder();
      var3.append("CANBUS_DATA_");
      var3.append(var1);
      return var2.getCarByteArrayState(var3.toString());
   }

   protected void ProcessData(byte[] var1) {
   }

   protected void SendCanBusCmdData2E(byte var1, byte[] var2, int var3) {
      this.SerialBroadcast.SendCmdData(var1, var2, var3);
   }

   protected void SendCanBusCmdData5AA5(byte var1, byte[] var2, int var3) {
      this.SerialBroadcast.SendCanBusCmdData2(var1, var2, var3);
   }

   protected void SendCanBusCmdDataAA55(byte var1, byte[] var2, int var3) {
      this.SerialBroadcast.SendCanBusCmdDataAA55(var1, var2, var3);
   }

   protected void SendCanBusCmdDataFD(byte var1, byte[] var2, int var3) {
      this.SerialBroadcast.SendCanBusCmdDataFD(var1, var2, var3);
   }

   protected void addPreference(Preference var1) {
      this.getPreferenceScreen().addPreference(var1);
   }

   protected void addPreferenceGroup(PreferenceGroup var1, Preference var2) {
      if (var1 != null && var2 != null) {
         var1.addPreference(var2);
      }

   }

   protected void addPreferenceScreenGroup(PreferenceScreen var1, Preference var2) {
      if (var1 != null && var2 != null) {
         var1.addPreference(var2);
      }

   }

   protected void enabledPreference(Preference var1, boolean var2) {
      if (var2) {
         this.addPreference(var1);
      } else {
         this.removePreference(var1);
      }

   }

   protected void enabledPreferenceGroup(PreferenceGroup var1, Preference var2, boolean var3) {
      if (var3) {
         this.addPreferenceGroup(var1, var2);
      } else {
         this.removePreferenceGroup(var1, var2);
      }

   }

   public HCheckBoxPreference findHCheckBoxPreference(CharSequence var1) {
      HCheckBoxPreference var2 = (HCheckBoxPreference)this.findPreference(var1);
      var2.setOnPreferenceClickListener(this);
      return var2;
   }

   public HCheckBoxPreference findHCheckBoxPreferenceScreen(CharSequence var1, CharSequence var2) {
      HCheckBoxPreference var3 = (HCheckBoxPreference)this.findPreferenceScreen(var1).findPreference(var2);
      if (var3 == null) {
         Log.e("BaseControlSettings", "findHCheckBoxPreferenceScreen is null !!!");
         return new HCheckBoxPreference(this);
      } else {
         var3.setOnPreferenceClickListener(this);
         return var3;
      }
   }

   public OnSwitchPreference findOnSwitchPreference(CharSequence var1) {
      OnSwitchPreference var2 = (OnSwitchPreference)this.findPreference(var1);
      var2.setOnPreferenceChangeListener(this);
      return var2;
   }

   public PreferenceScreen findPreferenceScreen(CharSequence var1) {
      PreferenceScreen var2 = (PreferenceScreen)this.findPreference(var1);
      var2.setOnPreferenceClickListener(this);
      return var2;
   }

   public SeekBarPreference findSeekBarPreference(CharSequence var1) {
      SeekBarPreference var2 = (SeekBarPreference)this.findPreference(var1);
      var2.setOnPreferenceChangeListener(this);
      return var2;
   }

   public SwitchboxPreference findSwitchboxPreference(CharSequence var1) {
      SwitchboxPreference var2 = (SwitchboxPreference)this.findPreference(var1);
      var2.setOnPreferenceChangeListener(this);
      return var2;
   }

   public SwitchboxPreference findSwitchboxPreferenceScreen(CharSequence var1, CharSequence var2) {
      SwitchboxPreference var3 = (SwitchboxPreference)this.findPreferenceScreen(var1).findPreference(var2);
      if (var3 == null) {
         Log.e("BaseControlSettings", "findSwitchboxPreferenceScreen is null !!!");
         return new SwitchboxPreference(this);
      } else {
         var3.setOnPreferenceChangeListener(this);
         return var3;
      }
   }

   public int getCanbusType() {
      return this.canbustype;
   }

   public int getCarType() {
      return BaseApplication.getInstance().getCarType();
   }

   public int getInt(String var1, int var2) {
      return System.getInt(this.getContentResolver(), var1, var2);
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.canbustype = BaseApplication.getInstance().getCanbustype();
      this.getListView().setItemsCanFocus(true);
      this.SerialBroadcast = new SerialBroadcast(this, new SerialBroadcast.SerialCallBack(this) {
         final BasePreferenceActivity this$0;

         {
            this.this$0 = var1;
         }

         public void receive(byte[] var1) {
            if (var1.length > 2 && this.this$0.getPreferenceScreen() != null) {
               this.this$0.BaseProcessData(var1);
            }

         }
      });
   }

   protected void onDestroy() {
      SerialBroadcast var1 = this.SerialBroadcast;
      if (var1 != null) {
         var1.exit();
      }

      super.onDestroy();
   }

   public void onMultiWindowModeChanged(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append("onMultiWindowModeChanged>>");
      var2.append(var1);
      Log.i("BaseControlSettings", var2.toString());
      if (!var1) {
         Intent var3 = this.getIntent();
         this.overridePendingTransition(0, 0);
         var3.addFlags(65536);
         this.finish();
         this.overridePendingTransition(0, 0);
         this.startActivity(var3);
      }

      super.onMultiWindowModeChanged(var1);
   }

   public void onPictureInPictureModeChanged(boolean var1) {
      super.onPictureInPictureModeChanged(var1);
   }

   public boolean onPreferenceChange(Preference var1, Object var2) {
      return false;
   }

   public boolean onPreferenceClick(Preference var1) {
      return false;
   }

   public void putInt(String var1, int var2) {
      System.putInt(this.getContentResolver(), var1, var2);
   }

   protected void removeAll() {
      this.getPreferenceScreen().removeAll();
   }

   protected void removePreference(Preference var1) {
      this.getPreferenceScreen().removePreference(var1);
   }

   protected void removePreference(String var1) {
      if (this.findPreference(var1) != null) {
         this.removePreference(this.findPreference(var1));
      }

   }

   protected void removePreferenceGroup(PreferenceGroup var1, Preference var2) {
      if (var1 != null && var2 != null) {
         var1.removePreference(var2);
      }

   }

   protected void removePreferenceGroup(String var1, String var2) {
      PreferenceGroup var3 = (PreferenceGroup)this.findPreference(var1);
      if (var3 != null) {
         var3.removePreference(var3.findPreference(var2));
      }

   }

   public interface AlertDialogCallBack {
      void OkClick();
   }
}
