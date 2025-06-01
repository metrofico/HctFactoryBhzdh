package com.hzbhd.canbus.car._444;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONException;
import org.json.JSONObject;

public class UiMgr extends AbstractUiMgr {
   private static boolean canHand;
   private static boolean launchgerHand;
   private int[] canBusInfo = new int[14];
   private byte[] canBusInfoByte = new byte[14];
   private HwCarSettings hwCarSettings;

   public UiMgr(Context var1) {
   }

   private void sendAirControlCmd(int[] var1) {
      int var2 = 0;
      if (var1[0] == 129) {
         this.hand("Launcher_OK");
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
      if (var1.equals("Launcher_OK")) {
         launchgerHand = true;
      }

      if (var1.equals("CanBus_OK")) {
         canHand = true;
      }

      if (canHand && launchgerHand) {
         CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
      }

   }

   // $FF: synthetic method
   void lambda$registerCanBusAirControlListener$0$com_hzbhd_canbus_car__444_UiMgr(String var1) {
      if (var1 != null && !var1.equals("[]")) {
         if (this.hwCarSettings == null) {
            this.hwCarSettings = new HwCarSettings();
         }

         this.hwCarSettings.setData(var1);

         JSONException var10000;
         label49: {
            boolean var10001;
            JSONObject var3;
            try {
               var3 = new JSONObject(var1);
            } catch (JSONException var7) {
               var10000 = var7;
               var10001 = false;
               break label49;
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
                  break label49;
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
      ShareDataManager.getInstance().registerShareStringListener("can.request.data.share", new UiMgr$$ExternalSyntheticLambda0(this));
   }
}
