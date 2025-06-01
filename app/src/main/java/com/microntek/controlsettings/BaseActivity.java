package com.microntek.controlsettings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;

public class BaseActivity extends Activity {
   public SerialBroadcast SerialBroadcast = null;

   protected void ProcessData(byte[] var1) {
   }

   protected void SendCanBusCmdData2E(byte var1, byte[] var2, int var3) {
      this.SerialBroadcast.SendCmdData(var1, var2, var3);
   }

   protected void SendCanBusCmdData5AA5(byte var1, byte[] var2, int var3) {
      this.SerialBroadcast.SendCanBusCmdData2(var1, var2, var3);
   }

   protected void SendCanBusCmdDataFD(byte var1, byte[] var2, int var3) {
      this.SerialBroadcast.SendCanBusCmdDataFD(var1, var2, var3);
   }

   public int getCarType() {
      return BaseApplication.getInstance().getCarType();
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.SerialBroadcast = new SerialBroadcast(this, new SerialBroadcast.SerialCallBack(this) {
         final BaseActivity this$0;

         {
            this.this$0 = var1;
         }

         public void receive(byte[] var1) {
            this.this$0.ProcessData(var1);
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
      var2.append("BaseActivity onMultiWindowModeChanged>>");
      var2.append(var1);
      Log.i("BaseControlinfo", var2.toString());
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

   public void putSettingsInt(String var1, int var2) {
      System.putInt(this.getContentResolver(), var1, var2);
   }
}
