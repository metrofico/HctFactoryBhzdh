package com.hzbhd.canbus.car._305;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      if (this.getCurrentCarId() != 1) {
         this.removeMainPrjBtnByName(var1, "setting");
      } else {
         this.getSettingUiSet(var1).setOnSettingItemSelectListener(new UiMgr$$ExternalSyntheticLambda0());
      }

   }

   // $FF: synthetic method
   static void lambda$new$0(int var0, int var1, int var2) {
      if (var0 == 0 && var1 == 0) {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)(var2 + 1)});
      }

   }
}
