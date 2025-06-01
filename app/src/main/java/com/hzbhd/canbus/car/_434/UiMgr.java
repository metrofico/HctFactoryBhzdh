package com.hzbhd.canbus.car._434;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._434.impl.MsAbstractUiMgr;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONException;
import org.json.JSONObject;

public class UiMgr extends MsAbstractUiMgr {
   private int[] canBusInfo = new int[14];
   private byte[] canBusInfoByte = new byte[14];

   public UiMgr(Context var1) {
   }

   private void sendAirControlCmd(int[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         this.canBusInfoByte[var2] = (byte)var1[var2];
      }

      CanbusMsgSender.sendMsg(this.canBusInfoByte);
   }

   // $FF: synthetic method
   void lambda$registerCanBusAirControlListener$0$com_hzbhd_canbus_car__434_UiMgr(String var1) {
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
   void lambda$registerCanBusBasicControlListener$1$com_hzbhd_canbus_car__434_UiMgr(String var1) {
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

   public void registerCanBusAirControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.air.control.json.string", new UiMgr$$ExternalSyntheticLambda0(this));
   }

   public void registerCanBusBasicControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.basic.control.json.string", new UiMgr$$ExternalSyntheticLambda1(this));
   }
}
