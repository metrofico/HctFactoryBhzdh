package com.hzbhd.canbus.car._459.tbox_wifi;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.ui.util.BaseUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class WifiManager {
   private String macAddress;
   public String wifiName;
   public String wifiPassword;

   private WifiManager() {
      this.wifiName = "ZERON TBOX";
      this.wifiPassword = "12345678";
   }

   // $FF: synthetic method
   WifiManager(Object var1) {
      this();
   }

   public static WifiManager getInstance() {
      return WifiManager.Holder.INSTANCE;
   }

   public int getAsciiValue(char var1) {
      return var1;
   }

   public void init(Context var1) {
      this.macAddress = ((android.net.wifi.WifiManager)var1.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
      Log.d("DEVICE_CONFIG", "产品唯一标识符=" + this.macAddress);
   }

   public void modifyWiFiName() {
      int var35 = this.wifiName.length();
      int var1;
      if (var35 > 0) {
         var1 = this.getAsciiValue(this.wifiName.charAt(0));
      } else {
         var1 = 255;
      }

      int var2;
      if (var35 > 1) {
         var2 = this.getAsciiValue(this.wifiName.charAt(1));
      } else {
         var2 = 255;
      }

      int var3;
      if (var35 > 2) {
         var3 = this.getAsciiValue(this.wifiName.charAt(2));
      } else {
         var3 = 255;
      }

      int var4;
      if (var35 > 3) {
         var4 = this.getAsciiValue(this.wifiName.charAt(3));
      } else {
         var4 = 255;
      }

      int var5;
      if (var35 > 4) {
         var5 = this.getAsciiValue(this.wifiName.charAt(4));
      } else {
         var5 = 255;
      }

      int var6;
      if (var35 > 5) {
         var6 = this.getAsciiValue(this.wifiName.charAt(5));
      } else {
         var6 = 255;
      }

      int var7;
      if (var35 > 6) {
         var7 = this.getAsciiValue(this.wifiName.charAt(6));
      } else {
         var7 = 255;
      }

      int var8;
      if (var35 > 7) {
         var8 = this.getAsciiValue(this.wifiName.charAt(7));
      } else {
         var8 = 255;
      }

      int var9;
      if (var35 > 8) {
         var9 = this.getAsciiValue(this.wifiName.charAt(8));
      } else {
         var9 = 255;
      }

      int var10;
      if (var35 > 9) {
         var10 = this.getAsciiValue(this.wifiName.charAt(9));
      } else {
         var10 = 255;
      }

      int var11;
      if (var35 > 10) {
         var11 = this.getAsciiValue(this.wifiName.charAt(10));
      } else {
         var11 = 255;
      }

      int var12;
      if (var35 > 12) {
         var12 = this.getAsciiValue(this.wifiName.charAt(11));
      } else {
         var12 = 255;
      }

      int var13;
      if (var35 > 12) {
         var13 = this.getAsciiValue(this.wifiName.charAt(12));
      } else {
         var13 = 255;
      }

      int var14;
      if (var35 > 13) {
         var14 = this.getAsciiValue(this.wifiName.charAt(13));
      } else {
         var14 = 255;
      }

      int var15;
      if (var35 > 14) {
         var15 = this.getAsciiValue(this.wifiName.charAt(14));
      } else {
         var15 = 255;
      }

      int var16;
      if (var35 > 15) {
         var16 = this.getAsciiValue(this.wifiName.charAt(15));
      } else {
         var16 = 255;
      }

      int var17;
      if (var35 > 16) {
         var17 = this.getAsciiValue(this.wifiName.charAt(16));
      } else {
         var17 = 255;
      }

      int var18;
      if (var35 > 17) {
         var18 = this.getAsciiValue(this.wifiName.charAt(17));
      } else {
         var18 = 255;
      }

      int var19;
      if (var35 > 18) {
         var19 = this.getAsciiValue(this.wifiName.charAt(18));
      } else {
         var19 = 255;
      }

      int var20;
      if (var35 > 19) {
         var20 = this.getAsciiValue(this.wifiName.charAt(19));
      } else {
         var20 = 255;
      }

      int var21;
      if (var35 > 20) {
         var21 = this.getAsciiValue(this.wifiName.charAt(20));
      } else {
         var21 = 255;
      }

      int var22;
      if (var35 > 21) {
         var22 = this.getAsciiValue(this.wifiName.charAt(21));
      } else {
         var22 = 255;
      }

      int var23;
      if (var35 > 22) {
         var23 = this.getAsciiValue(this.wifiName.charAt(22));
      } else {
         var23 = 255;
      }

      int var24;
      if (var35 > 23) {
         var24 = this.getAsciiValue(this.wifiName.charAt(23));
      } else {
         var24 = 255;
      }

      int var25;
      if (var35 > 24) {
         var25 = this.getAsciiValue(this.wifiName.charAt(24));
      } else {
         var25 = 255;
      }

      int var26;
      if (var35 > 25) {
         var26 = this.getAsciiValue(this.wifiName.charAt(25));
      } else {
         var26 = 255;
      }

      int var27;
      if (var35 > 26) {
         var27 = this.getAsciiValue(this.wifiName.charAt(26));
      } else {
         var27 = 255;
      }

      int var28;
      if (var35 > 27) {
         var28 = this.getAsciiValue(this.wifiName.charAt(27));
      } else {
         var28 = 255;
      }

      int var29;
      if (var35 > 28) {
         var29 = this.getAsciiValue(this.wifiName.charAt(28));
      } else {
         var29 = 255;
      }

      int var30;
      if (var35 > 29) {
         var30 = this.getAsciiValue(this.wifiName.charAt(29));
      } else {
         var30 = 255;
      }

      int var31;
      if (var35 > 30) {
         var31 = this.getAsciiValue(this.wifiName.charAt(30));
      } else {
         var31 = 255;
      }

      int var32;
      if (var35 > 31) {
         var32 = this.getAsciiValue(this.wifiName.charAt(31));
      } else {
         var32 = 255;
      }

      int var33;
      if (var35 > 32) {
         var33 = this.getAsciiValue(this.wifiName.charAt(32));
      } else {
         var33 = 255;
      }

      int var34;
      if (var35 > 33) {
         var34 = this.getAsciiValue(this.wifiName.charAt(33));
      } else {
         var34 = 255;
      }

      if (var35 > 34) {
         var35 = this.getAsciiValue(this.wifiName.charAt(34));
      } else {
         var35 = 255;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 32, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 33, (byte)var8, (byte)var9, (byte)var10, (byte)var11, (byte)var12, (byte)var13, (byte)var14, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 34, (byte)var15, (byte)var16, (byte)var17, (byte)var18, (byte)var19, (byte)var20, (byte)var21, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 35, (byte)var22, (byte)var23, (byte)var24, (byte)var25, (byte)var26, (byte)var27, (byte)var28, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 36, (byte)var29, (byte)var30, (byte)var31, (byte)var32, (byte)var33, (byte)var34, (byte)var35, 1});
   }

   public void modifyWiFiPassword() {
      int var21 = this.wifiPassword.length();
      int var1;
      if (var21 > 0) {
         var1 = this.getAsciiValue(this.wifiPassword.charAt(0));
      } else {
         var1 = 255;
      }

      int var2;
      if (var21 > 1) {
         var2 = this.getAsciiValue(this.wifiPassword.charAt(1));
      } else {
         var2 = 255;
      }

      int var3;
      if (var21 > 2) {
         var3 = this.getAsciiValue(this.wifiPassword.charAt(2));
      } else {
         var3 = 255;
      }

      int var4;
      if (var21 > 3) {
         var4 = this.getAsciiValue(this.wifiPassword.charAt(3));
      } else {
         var4 = 255;
      }

      int var5;
      if (var21 > 4) {
         var5 = this.getAsciiValue(this.wifiPassword.charAt(4));
      } else {
         var5 = 255;
      }

      int var6;
      if (var21 > 5) {
         var6 = this.getAsciiValue(this.wifiPassword.charAt(5));
      } else {
         var6 = 255;
      }

      int var7;
      if (var21 > 6) {
         var7 = this.getAsciiValue(this.wifiPassword.charAt(6));
      } else {
         var7 = 255;
      }

      int var8;
      if (var21 > 7) {
         var8 = this.getAsciiValue(this.wifiPassword.charAt(7));
      } else {
         var8 = 255;
      }

      int var9;
      if (var21 > 8) {
         var9 = this.getAsciiValue(this.wifiPassword.charAt(8));
      } else {
         var9 = 255;
      }

      int var10;
      if (var21 > 9) {
         var10 = this.getAsciiValue(this.wifiPassword.charAt(9));
      } else {
         var10 = 255;
      }

      int var11;
      if (var21 > 10) {
         var11 = this.getAsciiValue(this.wifiPassword.charAt(10));
      } else {
         var11 = 255;
      }

      int var12;
      if (var21 > 12) {
         var12 = this.getAsciiValue(this.wifiPassword.charAt(11));
      } else {
         var12 = 255;
      }

      int var13;
      if (var21 > 12) {
         var13 = this.getAsciiValue(this.wifiPassword.charAt(12));
      } else {
         var13 = 255;
      }

      int var14;
      if (var21 > 13) {
         var14 = this.getAsciiValue(this.wifiPassword.charAt(13));
      } else {
         var14 = 255;
      }

      int var15;
      if (var21 > 14) {
         var15 = this.getAsciiValue(this.wifiPassword.charAt(14));
      } else {
         var15 = 255;
      }

      int var16;
      if (var21 > 15) {
         var16 = this.getAsciiValue(this.wifiPassword.charAt(15));
      } else {
         var16 = 255;
      }

      int var17;
      if (var21 > 16) {
         var17 = this.getAsciiValue(this.wifiPassword.charAt(16));
      } else {
         var17 = 255;
      }

      int var18;
      if (var21 > 17) {
         var18 = this.getAsciiValue(this.wifiPassword.charAt(17));
      } else {
         var18 = 255;
      }

      int var19;
      if (var21 > 18) {
         var19 = this.getAsciiValue(this.wifiPassword.charAt(18));
      } else {
         var19 = 255;
      }

      int var20;
      if (var21 > 19) {
         var20 = this.getAsciiValue(this.wifiPassword.charAt(19));
      } else {
         var20 = 255;
      }

      if (var21 > 20) {
         var21 = this.getAsciiValue(this.wifiPassword.charAt(20));
      } else {
         var21 = 255;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 48, (byte)var1, (byte)var2, (byte)var3, (byte)var4, (byte)var5, (byte)var6, (byte)var7, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 49, (byte)var8, (byte)var9, (byte)var10, (byte)var11, (byte)var12, (byte)var13, (byte)var14, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 50, (byte)var15, (byte)var16, (byte)var17, (byte)var18, (byte)var19, (byte)var20, (byte)var21, 1});
   }

   public void register() {
      BaseUtil.INSTANCE.runBack(new Function0(this) {
         final WifiManager this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            for(int var1 = 0; var1 < 20; ++var1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 1, 1, 1, 16, 16, 16, 0, 0, 1});

               try {
                  Thread.sleep(1000L);
               } catch (InterruptedException var3) {
                  var3.printStackTrace();
               }
            }

            return null;
         }
      });
   }

   public void requestAction(int var1) {
      String var8 = this.macAddress;
      if (var8 != null) {
         int var2 = Integer.parseInt(var8.substring(0, 2), 16);
         int var6 = Integer.parseInt(this.macAddress.substring(3, 5), 16);
         int var3 = Integer.parseInt(this.macAddress.substring(6, 8), 16);
         int var5 = Integer.parseInt(this.macAddress.substring(9, 11), 16);
         int var4 = Integer.parseInt(this.macAddress.substring(12, 14), 16);
         int var7 = Integer.parseInt(this.macAddress.substring(15, 17), 16);
         CanbusMsgSender.sendMsg(new byte[]{22, 24, -11, 114, 40, 2, (byte)var2, (byte)var6, (byte)var3, (byte)var5, (byte)var4, (byte)var7, (byte)var1, 1});
      }
   }

   private static class Holder {
      private static final WifiManager INSTANCE = new WifiManager();
   }
}
