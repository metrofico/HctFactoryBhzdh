package com.hzbhd.canbus.car._208;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SetTimeView;

public class UiMgr extends AbstractUiMgr {
   int data;
   private Context mContext;
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getParkPageUiSet(var1).setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     this.this$0.SendParkPageMessage(11);
                  }
               } else {
                  this.this$0.SendParkPageMessage(10);
               }
            } else {
               this.this$0.SendParkPageMessage(9);
            }

         }
      });
      this.getSettingUiSet(var1).setOnSettingItemClickListener(new OnSettingItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == 2 && var2 == 0) {
               (new SetTimeView()).showDialog(this.this$0.getMsgMgr(this.val$context).getCurrentActivity(), CommUtil.getStrByResId(this.val$context, "_283_setting_title_9"), true, true, true, true, true, new SetTimeView.TimeResultListener(this) {
                  final <undefinedtype> this$1;

                  {
                     this.this$1 = var1;
                  }

                  public void result(int var1, int var2, int var3, int var4, int var5) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte)var4, (byte)var5, 0, 0, 1, (byte)var1, (byte)var2, (byte)var3, 2});
                  }
               });
            }

         }
      });
   }

   private void SendParkPageMessage(int var1) {
      switch (var1) {
         case 9:
            if (this.getMsgMgr(this.mContext).a) {
               this.data = 0;
            } else {
               this.data = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte)var1, (byte)this.data});
            break;
         case 10:
            if (this.getMsgMgr(this.mContext).b) {
               this.data = 0;
            } else {
               this.data = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte)var1, (byte)this.data});
            break;
         case 11:
            if (this.getMsgMgr(this.mContext).c) {
               this.data = 0;
            } else {
               this.data = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 76, (byte)var1, (byte)this.data});
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }
}
