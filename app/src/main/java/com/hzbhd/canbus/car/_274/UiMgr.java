package com.hzbhd.canbus.car._274;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getMsgMgr(var1).updateSettings(1, 0, SharePreUtil.getIntValue(var1, "share_274_left_camera_input", 0));
      this.getMsgMgr(var1).updateSettings(1, 1, SharePreUtil.getIntValue(var1, "share_274_right_camera_input", 0));
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0) {
               if (var2 == 0) {
                  CanbusMsgSender.sendMsg(new byte[]{22, 110, 9, (byte)var3});
               }
            } else if (var1 == 1) {
               if (var2 == 0) {
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  this.this$0.getMsgMgr(this.val$context).setCameraInput("share_274_left_camera_input", var3);
               } else if (var2 == 1) {
                  this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
                  this.this$0.getMsgMgr(this.val$context).setCameraInput("share_274_right_camera_input", var3);
               }
            }

         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onConfirmClick(int var1, int var2) {
            if (var1 == 0 && var2 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, 110, 10, 1});
               Toast.makeText(this.this$0.mContext, 2131768706, 0).show();
            }

         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }
}
