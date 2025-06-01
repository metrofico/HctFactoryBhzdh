package com.microntek.controlsettings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import java.util.Locale;

public class SerialBroadcast {
   public static boolean LOG;
   private BroadcastReceiver SyncReceiver = new BroadcastReceiver(this) {
      final SerialBroadcast this$0;

      {
         this.this$0 = var1;
      }

      public void onReceive(Context var1, Intent var2) {
         if (var2.getAction().equals("com.microntek.sync")) {
            byte[] var3 = var2.getByteArrayExtra("syncdata");
            if (var3 == null) {
               return;
            }

            if (SerialBroadcast.LOG) {
               this.this$0.dbug("read:", var3);
            }

            if (this.this$0.mCallBack != null) {
               this.this$0.mCallBack.receive(var3);
            }
         }

      }
   };
   private String TAG = "SerialBroadcast";
   private IntentFilter itfl = null;
   private SerialCallBack mCallBack = null;
   private Context mContext;

   SerialBroadcast(Context var1, SerialCallBack var2) {
      this.mContext = var1;
      this.mCallBack = var2;
      this.itfl = new IntentFilter();
      this.itfl.addAction("com.microntek.sync");
      this.mContext.registerReceiver(this.SyncReceiver, this.itfl);
   }

   private void WritePort(byte[] var1) {
      int var3 = var1.length;
      String var4 = "";

      StringBuilder var5;
      for(int var2 = 0; var2 < var3; ++var2) {
         var5 = new StringBuilder();
         var5.append(var4);
         var5.append(this.getNumberValue(var1[var2] & 255));
         String var8 = var5.toString();
         var4 = var8;
         if (var2 < var3 - 1) {
            StringBuilder var7 = new StringBuilder();
            var7.append(var8);
            var7.append(",");
            var4 = var7.toString();
         }
      }

      BaseApplication var6 = BaseApplication.getInstance();
      var5 = new StringBuilder();
      var5.append("canbus_rsp=");
      var5.append(var4);
      var6.setParameters(var5.toString());
   }

   private void dbug(String var1, byte[] var2) {
      int var4 = var2.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         StringBuilder var5 = new StringBuilder();
         var5.append(var1);
         var5.append(String.format(Locale.US, "%02x", var2[var3] & 255));
         String var7 = var5.toString();
         var1 = var7;
         if (var3 < var4 - 1) {
            StringBuilder var6 = new StringBuilder();
            var6.append(var7);
            var6.append(",");
            var1 = var6.toString();
         }
      }

      Log.i(this.TAG, var1);
   }

   private String getNumberValue(int var1) {
      int var2 = var1 / 10;
      var1 %= 10;
      StringBuilder var3;
      if (var2 == 0) {
         var3 = new StringBuilder();
         var3.append("");
         var3.append(var1);
         return var3.toString();
      } else {
         var3 = new StringBuilder();
         var3.append("");
         var3.append(var2);
         var3.append(var1);
         return var3.toString();
      }
   }

   public void SendCanBusCmdData2(byte var1, byte[] var2, int var3) {
      byte[] var7 = new byte[var3 + 5];
      int var5 = 0;
      var7[0] = 90;
      var7[1] = -91;
      var7[2] = (byte)(var3 & 255);
      var7[3] = var1;

      short var4;
      for(var4 = (short)(var7[2] + var7[3]); var5 < var3; ++var5) {
         int var6 = var5 + 4;
         var7[var6] = var2[var5];
         var4 = (short)(var4 + var7[var6]);
      }

      var7[var3 + 4] = (byte)(var4 - 1 & 255);
      this.WritePort(var7);
      if (LOG) {
         this.dbug("write:", var7);
      }

   }

   public void SendCanBusCmdDataAA55(byte var1, byte[] var2, int var3) {
      byte[] var7 = new byte[var3 + 5];
      int var4 = 0;
      var7[0] = -86;
      var7[1] = 85;
      var7[2] = (byte)(var3 & 255);
      var7[3] = var1;

      short var5;
      for(var5 = (short)(var7[2] + var7[3]); var4 < var3; ++var4) {
         int var6 = var4 + 4;
         var7[var6] = var2[var4];
         var5 = (short)(var5 + var7[var6]);
      }

      var7[var3 + 4] = (byte)(var5 - 1 & 255);
      this.WritePort(var7);
   }

   public void SendCanBusCmdDataFD(byte var1, byte[] var2, int var3) {
      byte[] var7 = new byte[var3 + 4];
      int var5 = 0;
      var7[0] = -3;
      int var6 = var3 + 3;
      var7[1] = (byte)(var6 & 255);
      var7[2] = (byte)(var1 & 255);
      short var4 = (short)(var7[1] + var7[2]);

      for(var1 = var5; var1 < var3; ++var1) {
         var5 = var1 + 3;
         var7[var5] = var2[var1];
         var4 = (short)(var4 + var7[var5]);
      }

      var7[var6] = (byte)(var4 & 255);
      this.WritePort(var7);
   }

   public void SendCmdData(byte var1, byte[] var2, int var3) {
      byte[] var7 = new byte[var3 + 4];
      int var4 = 0;
      var7[0] = 46;
      var7[1] = var1;
      var7[2] = (byte)(var3 & 255);

      short var5;
      for(var5 = (short)(var7[1] + var7[2]); var4 < var3; ++var4) {
         int var6 = var4 + 3;
         var7[var6] = var2[var4];
         var5 = (short)(var5 + var7[var6]);
      }

      var7[var3 + 3] = (byte)(var5 & 255 ^ 255);
      this.WritePort(var7);
      if (LOG) {
         this.dbug("write:", var7);
      }

   }

   public void exit() {
      if (this.itfl != null) {
         this.mContext.unregisterReceiver(this.SyncReceiver);
      }

   }

   public interface SerialCallBack {
      void receive(byte[] var1);
   }
}
