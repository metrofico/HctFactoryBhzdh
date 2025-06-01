package com.hzbhd.canbus.car._236;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private static final String SHARE_236_PARK_ASSIST = "share_236_park_assist";
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2, var1) {
         final UiMgr this$0;
         final Context val$context;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
            this.val$context = var3;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            if (var1 == 0) {
               switch (var2) {
                  case 0:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var3});
                     break;
                  case 1:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
                     break;
                  case 2:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 2, (byte)var3});
                     break;
                  case 3:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
                     break;
                  case 4:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 4, (byte)var3});
                     break;
                  case 5:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)var3});
                     break;
                  case 6:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)var3});
                     break;
                  case 7:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 7, (byte)var3});
                     break;
                  case 8:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 8, (byte)var3});
                     break;
                  case 9:
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 10, (byte)var3});
                  case 10:
                  default:
                     break;
                  case 11:
                     SharePreUtil.setIntValue(this.val$context, "share_236_park_assist", var3);
                     Log.i("ljq", "onClickItem: get: " + SharePreUtil.getIntValue(this.val$context, "share_236_park_assist", 0));
                     this.this$0.getMsgMgr(this.val$context).updateSettings(var1, var2, var3);
               }
            }

            var4.hashCode();
            if (!var4.equals("_236_Forward_collision_assist_mode")) {
               if (var4.equals("_236_Sensitivity")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 13, (byte)var3});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 12, (byte)var3});
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 10) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 9, (byte)var3});
            }

         }
      });
      var2.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == 0) {
               this.this$0.sendData(new byte[]{22, -112, 81});
               this.this$0.sendData(new byte[]{22, -112, 127});
            }

         }
      });
      var2.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_236_Front_Collision_Assist_System")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, 11, (byte)var3});
            }

         }
      });
      this.getAirUiSet(var1).setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == 1) {
               this.this$0.sendData(new byte[]{22, -112, 35});
               this.this$0.sendData(new byte[]{22, -112, 54});
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

   public void updateUiByDifferentCar(Context var1) {
      int var2 = this.getCurrentCarId();
      if (var2 != 0) {
         if (var2 == 1) {
            this.removeMainPrjBtn(var1, 0, 1);
            return;
         }

         if (var2 != 4 && var2 != 5) {
            return;
         }
      }

      this.removeSettingRightItem(var1, 0, 11, 11);
   }
}
