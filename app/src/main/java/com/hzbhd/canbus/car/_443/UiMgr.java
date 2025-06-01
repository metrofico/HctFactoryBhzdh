package com.hzbhd.canbus.car._443;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._443.impl.MsAbstractUiMgr;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONException;
import org.json.JSONObject;

public class UiMgr extends MsAbstractUiMgr {
   private byte[] calibrationInfoByte = new byte[2];
   private int[] calibrationInfoInt = new int[2];
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

   private void sendCalibrationControlCmd(int[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         this.calibrationInfoByte[var2] = (byte)var1[var2];
      }

      CanbusMsgSender.sendMsg(this.calibrationInfoByte);
   }

   // $FF: synthetic method
   void lambda$registerCanBusAirControlListener$0$com_hzbhd_canbus_car__443_UiMgr(String var1) {
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

   // $FF: synthetic method
   void lambda$registerCanBusBasicControlListener$1$com_hzbhd_canbus_car__443_UiMgr(String var1) {
      if (var1 != null && !var1.equals("[]")) {
         JSONException var10000;
         label64: {
            int var4;
            JSONObject var5;
            boolean var10001;
            try {
               var5 = new JSONObject(var1);
               var4 = (Integer)var5.get("Data0");
            } catch (JSONException var11) {
               var10000 = var11;
               var10001 = false;
               break label64;
            }

            int var2 = 0;
            byte var3 = 0;
            if (var4 == 34) {
               label65: {
                  var2 = var3;

                  while(true) {
                     try {
                        if (var2 >= var5.length()) {
                           break;
                        }

                        int[] var13 = this.calibrationInfoInt;
                        StringBuilder var15 = new StringBuilder();
                        var13[var2] = (Integer)var5.get(var15.append("Data").append(var2).toString());
                     } catch (JSONException var8) {
                        var10000 = var8;
                        var10001 = false;
                        break label65;
                     }

                     ++var2;
                  }

                  try {
                     this.sendCalibrationControlCmd(this.calibrationInfoInt);
                     return;
                  } catch (JSONException var7) {
                     var10000 = var7;
                     var10001 = false;
                  }
               }
            } else {
               label66: {
                  while(true) {
                     try {
                        if (var2 >= var5.length()) {
                           break;
                        }

                        int[] var6 = this.canBusInfo;
                        StringBuilder var12 = new StringBuilder();
                        var6[var2] = (Integer)var5.get(var12.append("Data").append(var2).toString());
                     } catch (JSONException var10) {
                        var10000 = var10;
                        var10001 = false;
                        break label66;
                     }

                     ++var2;
                  }

                  try {
                     this.sendAirControlCmd(this.canBusInfo);
                     return;
                  } catch (JSONException var9) {
                     var10000 = var9;
                     var10001 = false;
                  }
               }
            }
         }

         JSONException var14 = var10000;
         var14.printStackTrace();
      }

   }

   public void registerCanBusAirControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.air.control.json.string", new UiMgr$$ExternalSyntheticLambda1(this));
   }

   public void registerCanBusBasicControlListener() {
      ShareDataManager.getInstance().registerShareStringListener("canbus.ms.basic.control.json.string", new UiMgr$$ExternalSyntheticLambda0(this));
   }
}
