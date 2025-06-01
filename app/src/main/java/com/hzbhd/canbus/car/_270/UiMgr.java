package com.hzbhd.canbus.car._270;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 2, (byte)var3, -1, -1});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 111, 1, (byte)var3, -1, -1});
               }
            }

         }
      });
   }
}
