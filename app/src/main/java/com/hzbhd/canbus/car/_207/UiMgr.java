package com.hzbhd.canbus.car._207;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnConfirmDialogListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     if (var2 != 0) {
                        if (var2 == 1) {
                           CanbusMsgSender.sendMsg(new byte[]{22, 124, 5, 0, 0});
                        }
                     } else {
                        CanbusMsgSender.sendMsg(new byte[]{22, 124, 4, 0, 0});
                     }
                  }
               } else if (var2 != 0) {
                  if (var2 == 1) {
                     CanbusMsgSender.sendMsg(new byte[]{22, 124, 6, 0, 0});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, 124, 7, 0, 0});
               }
            } else if (var2 == 2) {
               CanbusMsgSender.sendMsg(new byte[]{22, 124, 3, 0, 0});
            }

         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_207_setting_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -51, 1, (byte)var3, 0});
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -680771328:
                  if (var4.equals("_207_setting_4")) {
                     var5 = 0;
                  }
                  break;
               case -680771327:
                  if (var4.equals("_207_setting_5")) {
                     var5 = 1;
                  }
                  break;
               case 370925267:
                  if (var4.equals("_207_setting_10")) {
                     var5 = 2;
                  }
                  break;
               case 370925268:
                  if (var4.equals("_207_setting_11")) {
                     var5 = 3;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 4, (byte)var3, (byte)this.this$0.mMsgMgr.mTime1Minute});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 4, (byte)this.this$0.mMsgMgr.mTime1Hour, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 7, (byte)var3, (byte)this.this$0.mMsgMgr.mTime2Minute});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 7, (byte)this.this$0.mMsgMgr.mTime2Hour, (byte)var3});
            }

         }
      });
      var2.setOnSettingConfirmDialogListener(new OnConfirmDialogListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onConfirmClick(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1386218530:
                  if (var3.equals("_207_setting_112")) {
                     var4 = 0;
                  }
                  break;
               case -1386218529:
                  if (var3.equals("_207_setting_113")) {
                     var4 = 1;
                  }
                  break;
               case 370925269:
                  if (var3.equals("_207_setting_12")) {
                     var4 = 2;
                  }
                  break;
               case 370925270:
                  if (var3.equals("_207_setting_13")) {
                     var4 = 3;
                  }
                  break;
               case 370925271:
                  if (var3.equals("_207_setting_14")) {
                     var4 = 4;
                  }
                  break;
               case 370925272:
                  if (var3.equals("_207_setting_15")) {
                     var4 = 5;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 5, 0, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 6, 0, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 2, 0, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -51, 3, 0, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, 124, 1, 0, 0});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, 124, 2, 0, 0});
            }

         }
      });
   }
}
