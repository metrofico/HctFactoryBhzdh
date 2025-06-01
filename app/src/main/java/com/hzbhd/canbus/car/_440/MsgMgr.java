package com.hzbhd.canbus.car._440;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgMgr extends AbstractMsgMgr {
   private String airJsonStr;
   private UiMgr mUiMgr;

   private UiMgr getUiMgr(Context var1) {
      if (this.mUiMgr == null) {
         this.mUiMgr = (UiMgr)UiMgrFactory.getCanUiMgr(var1);
      }

      return this.mUiMgr;
   }

   private String intArrayToJsonStr(int[] var1) {
      JSONObject var3 = new JSONObject();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         try {
            StringBuilder var4 = new StringBuilder();
            var3.put(var4.append("Data").append(var2).toString(), var1[var2]);
         } catch (JSONException var5) {
            var5.printStackTrace();
         }
      }

      return var3.toString();
   }

   protected void ShareBasicInfo(int[] var1) {
      CanbusInfoChangeListener.getInstance().reportAllCanBusData(this.intArrayToJsonStr(var1));
   }

   public void canbusInfoChange(Context var1, byte[] var2) {
      super.canbusInfoChange(var1, var2);
      this.ShareBasicInfo(this.getByteArrayToIntArray(var2));
   }

   public void initCommand(Context var1) {
      super.initCommand(var1);
      this.getUiMgr(var1).registerCanBusAirControlListener();
      this.updateVersionInfo(var1, "V.1.0.1");
   }

   public void onAccOn() {
      super.onAccOn();
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
   }

   public void onHandshake(Context var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -127, -127, -127, -127, 1, 1, 1, 1});
   }
}
