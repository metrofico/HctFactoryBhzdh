package com.hzbhd.canbus.car._286;

import android.content.Context;
import android.provider.Settings.System;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnBackCameraStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private MsgMgr mMsgMgr;
   private TimerUtil mTimerUtil;
   private byte[] mTrackRequestCommand;
   private TimerTask mTrackRequestTimerTask;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
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
               case -1721813160:
                  if (var4.equals("_286_settings_item_6")) {
                     var5 = 0;
                  }
                  break;
               case -1721813159:
                  if (var4.equals("_286_settings_item_7")) {
                     var5 = 1;
                  }
                  break;
               case -1721813158:
                  if (var4.equals("_286_settings_item_8")) {
                     var5 = 2;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
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
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -1721813161:
                  if (var4.equals("_286_settings_item_5")) {
                     var5 = 0;
                  }
                  break;
               case -1721813157:
                  if (var4.equals("_286_settings_item_9")) {
                     var5 = 1;
                  }
                  break;
               case -1721813117:
                  if (var4.equals("_286_settings_item_a")) {
                     var5 = 2;
                  }
                  break;
               case -1721813116:
                  if (var4.equals("_286_settings_item_b")) {
                     var5 = 3;
                  }
                  break;
               case -1721813115:
                  if (var4.equals("_286_settings_item_c")) {
                     var5 = 4;
                  }
                  break;
               case -1721813114:
                  if (var4.equals("_286_settings_item_d")) {
                     var5 = 5;
                  }
                  break;
               case -1721813113:
                  if (var4.equals("_286_settings_item_e")) {
                     var5 = 6;
                  }
            }

            switch (var5) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)var3});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, (byte)var3});
            }

         }
      });
      this.getParkPageUiSet(var1).setOnBackCameraStatusListener(new OnBackCameraStatusListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void addViewToWindows() {
            this.this$0.getTimerUtil().startTimer(this.this$0.getTrackRequestTimerTask(this.val$context), 0L, 150L);
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   private byte[] getTrackRequestCommand() {
      if (this.mTrackRequestCommand == null) {
         this.mTrackRequestCommand = new byte[]{22, -112, 41, 0};
      }

      return this.mTrackRequestCommand;
   }

   private TimerTask getTrackRequestTimerTask(Context var1) {
      if (this.mTrackRequestTimerTask == null) {
         this.mTrackRequestTimerTask = new TimerTask(this, var1) {
            final UiMgr this$0;
            final Context val$context;

            {
               this.this$0 = var1;
               this.val$context = var2;
            }

            public void run() {
               if (System.getInt(this.val$context.getContentResolver(), "gpioLevel", 1) == 0) {
                  CanbusMsgSender.sendMsg(this.this$0.getTrackRequestCommand());
               } else {
                  this.this$0.getTimerUtil().stopTimer();
               }

            }
         };
      }

      return this.mTrackRequestTimerTask;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.mContext = var1;
   }
}
