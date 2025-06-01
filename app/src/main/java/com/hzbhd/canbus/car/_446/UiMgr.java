package com.hzbhd.canbus.car._446;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.proxy.share.ShareDataManager;
import org.json.JSONException;
import org.json.JSONObject;

public class UiMgr extends AbstractUiMgr {
   private static boolean canHand;
   private static boolean launchgerHand;
   private int[] canBusInfo = new int[14];
   private byte[] canBusInfoByte = new byte[13];
   Context mContext;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
   }

   private byte[] SplicingByte(byte[] var1, byte[] var2) {
      byte[] var3 = new byte[var1.length + var2.length];
      System.arraycopy(var1, 0, var3, 0, var1.length);
      System.arraycopy(var2, 0, var3, var1.length, var2.length);
      return var3;
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendAirControlCmd(int[] var1) {
      if (var1[0] == 129 && var1[1] == 129 && var1[2] == 129 && var1[3] == 129) {
         this.hand("SystemUi_OK");
      } else {
         int var2 = 0;

         while(true) {
            try {
               if (var2 >= var1.length) {
                  break;
               }

               this.canBusInfoByte[var2] = (byte)var1[var2];
            } catch (Exception var3) {
               Log.d("Exception", "error：" + var3.toString());
               break;
            }

            ++var2;
         }

         CanbusMsgSender.sendMsg(this.SplicingByte(this.canBusInfoByte, new byte[]{1}));
      }

   }

   public void hand(String var1) {
      if (var1.equals("SystemUi_OK")) {
         launchgerHand = true;
         this.getMsgMgr(this.mContext).ShareCanInfo(new int[]{85, 85, 128, 128, 128, 128, 0, 0, 0, 0, 0, 0, 0, 0});
      }

      if (var1.equals("CanBus_OK")) {
         canHand = true;
      }

      if (canHand && launchgerHand) {
         CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
      }

   }

   // $FF: synthetic method
   void lambda$registerCanBusAirControlListener$0$com_hzbhd_canbus_car__446_UiMgr(String var1) {
      Log.d("fxHouShare", "CanBusGotAirControl=" + var1);
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
      Log.d("fxHouShare", "Register Air Control");
      ShareDataManager.getInstance().registerShareStringListener("can.request.data.share", new UiMgr$$ExternalSyntheticLambda0(this));
   }
}
