package com.hzbhd.canbus.car._133;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;

public class UiMgr extends AbstractUiMgr {
   private OnPanelKeyPositionListener mOnPanelKeyPositionListener = new OnPanelKeyPositionListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void click(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SendMsg(2);
            }
         } else {
            this.this$0.SendMsg(1);
         }

      }
   };

   public UiMgr(Context var1) {
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(this.mOnPanelKeyPositionListener);
   }

   private void SendMsg(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte)var1});
      CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
   }
}
