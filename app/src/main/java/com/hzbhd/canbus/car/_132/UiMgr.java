package com.hzbhd.canbus.car._132;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnOriginalBottomBtnClickListener;
import com.hzbhd.canbus.interfaces.OnOriginalTopBtnClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private int currentButtomBtn = 0;
   private int currentTopBtn1 = 0;
   private int currentTopBtn2 = 0;
   private Context mContext;
   private OnOriginalTopBtnClickListener mOnOriginalTopBtnClickListener = new OnOriginalTopBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickTopBtnItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  if (var1 == 3) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, -16});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -57, 0});
               }
            } else if (this.this$0.currentTopBtn2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 9});
               this.this$0.currentTopBtn2 = 1;
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 10});
               this.this$0.currentTopBtn2 = 0;
            }
         } else if (this.this$0.currentTopBtn1 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 7});
            this.this$0.currentTopBtn1 = 1;
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 8});
            this.this$0.currentTopBtn1 = 0;
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 0) {
            switch (var2) {
               case 0:
                  this.this$0.settingMsg(1, var3);
                  break;
               case 1:
                  this.this$0.settingMsg(2, var3);
                  break;
               case 2:
                  this.this$0.settingMsg(3, var3 + 1);
                  break;
               case 3:
                  this.this$0.settingMsg(4, var3 + 1);
                  break;
               case 4:
                  this.this$0.settingMsg(5, var3);
                  break;
               case 5:
                  this.this$0.settingMsg(17, var3);
                  break;
               case 6:
                  this.this$0.settingMsg(18, var3 + 1);
                  break;
               case 7:
                  this.this$0.settingMsg(33, var3 + 1);
                  break;
               case 8:
                  this.this$0.settingMsg(34, var3 + 1);
                  break;
               case 9:
                  this.this$0.settingMsg(35, var3);
            }
         }

      }
   };
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      this.getSettingUiSet(this.mContext).setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      ParkPageUiSet var2 = this.getParkPageUiSet(this.mContext);
      if (this.getCurrentCarId() == 0) {
         this.removeDrivigDateItem(this.mContext, 0, 0, 2);
         this.removeMainPrjBtn(this.mContext, 0, 2);
         var2.setShowTrack(false);
      } else {
         var2.setShowTrack(true);
      }

      OriginalCarDevicePageUiSet var3 = this.getOriginalCarDevicePageUiSet(var1);
      var3.setOnOriginalTopBtnClickListeners(this.mOnOriginalTopBtnClickListener);
      var3.setOnOriginalBottomBtnClickListeners(new OnOriginalBottomBtnClickListener(this, var3) {
         final UiMgr this$0;
         final OriginalCarDevicePageUiSet val$originalCarDevicePageUiSet;

         {
            this.this$0 = var1;
            this.val$originalCarDevicePageUiSet = var2;
         }

         public void onClickBottomBtnItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 3});
                  }
               } else {
                  Bundle var2;
                  if (this.this$0.currentButtomBtn == 0) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 1});
                     this.this$0.currentButtomBtn = 1;
                     this.val$originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"left", "play", "right"});
                     var2 = new Bundle();
                     var2.putBoolean("bundle_key_orinal_init_view", true);
                     this.this$0.msgMgr.updateOriginalCarDevice(var2);
                  } else {
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 2});
                     this.this$0.currentButtomBtn = 0;
                     this.val$originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"left", "pause", "right"});
                     var2 = new Bundle();
                     var2.putBoolean("bundle_key_orinal_init_view", true);
                     this.this$0.msgMgr.updateOriginalCarDevice(var2);
                  }
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 4});
            }

         }
      });
   }

   private void settingMsg(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, (byte)var2, 0});
   }
}
