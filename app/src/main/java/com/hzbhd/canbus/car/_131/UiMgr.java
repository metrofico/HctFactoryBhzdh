package com.hzbhd.canbus.car._131;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               this.this$0.sendMsg(var2, var3);
            } else if (var1 == 1) {
               this.this$0.sendMsg(var2 + 5, var3);
            } else if (var2 != 0) {
               if (var2 == 1) {
                  this.this$0.sendMsg(11, var3);
               }
            } else {
               this.this$0.sendMsg(10, var3);
            }

         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 2 && var2 == 2) {
               this.this$0.sendMsg(12, 0);
            }

            if (this.this$0.msgMgr._0x33_data0 == 12) {
               Context var3;
               if (this.this$0.msgMgr._0x33_data1 == 0) {
                  var3 = this.val$context;
                  Toast.makeText(var3, var3.getText(2131757438), 0);
               } else {
                  var3 = this.val$context;
                  Toast.makeText(var3, var3.getText(2131757437), 0);
               }
            }

         }
      });
   }

   private void sendMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2});
   }
}
