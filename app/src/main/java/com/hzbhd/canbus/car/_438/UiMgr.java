package com.hzbhd.canbus.car._438;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._438.impl.MsAbstractUiMgr;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONException;
import org.json.JSONObject;

public class UiMgr extends MsAbstractUiMgr {
   private static boolean msgmgrTagHand;
   private static boolean systemUiTagHand;
   private int[] canBusInfo = new int[14];
   private byte[] canBusInfoByte = new byte[14];

   public UiMgr(Context var1) {
   }

   private void sendAirControlCmd(int[] var1) {
      int var2 = 0;
      if (var1[0] == 129) {
         this.hand("SystemUI_OK");
      } else {
         while(true) {
            if (var2 >= var1.length) {
               CanbusMsgSender.sendMsg(this.canBusInfoByte);
               break;
            }

            this.canBusInfoByte[var2] = (byte)var1[var2];
            ++var2;
         }
      }

   }

   public void hand(String var1) {
      if (var1.equals("SystemUI_OK")) {
         systemUiTagHand = true;
      }

      if (var1.equals("MysMgr")) {
         msgmgrTagHand = true;
      }

      if (msgmgrTagHand && systemUiTagHand) {
         CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
      }

   }

   // $FF: synthetic method
   void lambda$registerCanBusAirControlListener$0$com_hzbhd_canbus_car__438_UiMgr(String var1) {
      if (var1 != null && !var1.equals("[]")) {
         JSONException var10000;
         label42: {
            boolean var10001;
            JSONObject var3;
            try {
               var3 = new JSONObject(var1);
            } catch (JSONException var7) {
               var10000 = var7;
               var10001 = false;
               break label42;
            }

            int var2 = 0;

            while(true) {
               try {
                  if (var2 >= var3.length()) {
                     break;
                  }

                  int[] var8 = this.canBusInfo;
                  StringBuilder var4 = new StringBuilder();
                  var8[var2] = (Integer)var3.get(var4.append("Data").append(var2).toString());
               } catch (JSONException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label42;
               }

               ++var2;
            }

            try {
               this.sendAirControlCmd(this.canBusInfo);
               return;
            } catch (JSONException var5) {
               var10000 = var5;
               var10001 = false;
            }
         }

         JSONException var9 = var10000;
         var9.printStackTrace();
      }

   }

   // $FF: synthetic method
   void lambda$registerCanBusBasicControlListener$1$com_hzbhd_canbus_car__438_UiMgr(String var1) {
      if (var1 != null && !var1.equals("[]")) {
         JSONException var10000;
         label42: {
            boolean var10001;
            JSONObject var3;
            try {
               var3 = new JSONObject(var1);
            } catch (JSONException var7) {
               var10000 = var7;
               var10001 = false;
               break label42;
            }

            int var2 = 0;

            while(true) {
               try {
                  if (var2 >= var3.length()) {
                     break;
                  }

                  int[] var4 = this.canBusInfo;
                  StringBuilder var8 = new StringBuilder();
                  var4[var2] = (Integer)var3.get(var8.append("Data").append(var2).toString());
               } catch (JSONException var6) {
                  var10000 = var6;
                  var10001 = false;
                  break label42;
               }

               ++var2;
            }

            try {
               this.sendAirControlCmd(this.canBusInfo);
               return;
            } catch (JSONException var5) {
               var10000 = var5;
               var10001 = false;
            }
         }

         JSONException var9 = var10000;
         var9.printStackTrace();
      }

   }

   public void registerCanBusAirControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.air.control.json.string", new UiMgr$$ExternalSyntheticLambda1(this));
   }

   public void registerCanBusBasicControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.basic.control.json.string", new UiMgr$$ExternalSyntheticLambda0(this));
   }
}
