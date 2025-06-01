package com.hzbhd.canbus.car._5;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0());
      var2.setOnSettingPageStatusListener(new UiMgr$$ExternalSyntheticLambda1(this));
   }

   // $FF: synthetic method
   static void lambda$new$0(int var0, int var1, int var2) {
      if (var0 == 0) {
         if (var1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 76, 9, (byte)var2});
         } else if (var1 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 76, 10, (byte)var2});
         } else if (var1 == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 76, 11, (byte)var2});
         }
      }

   }

   // $FF: synthetic method
   void lambda$new$1$com_hzbhd_canbus_car__5_UiMgr(int var1) {
      try {
         this.mMsgMgr.updateSettingData(var1);
      } catch (Exception var3) {
         var3.printStackTrace();
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
