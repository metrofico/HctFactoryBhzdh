package com.hzbhd.canbus.car._421;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSwitchListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   AirPageUiSet airPageUiSet;
   Context mContext;
   private MsgMgr msgMgr;
   ParkPageUiSet parkPageUiSet;
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.parkPageUiSet = this.getParkPageUiSet(var1);
      this.settingPageUiSet = this.getSettingUiSet(var1);
      this.mContext = var1;
      this.airPageUiSet = this.getAirUiSet(var1);
      this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 == 2) {
                     this.this$0.sendPanoramicItemMessage(2);
                  }
               } else {
                  this.this$0.sendPanoramicItemMessage(1);
               }
            } else {
               this.this$0.sendPanoramicItemMessage(0);
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            var2 = var4.hashCode();
            byte var5 = -1;
            switch (var2) {
               case -1631783422:
                  if (var4.equals("_421_Item_01")) {
                     var5 = 0;
                  }
                  break;
               case -1631783421:
                  if (var4.equals("_421_Item_02")) {
                     var5 = 1;
                  }
               case -1631783420:
               case -1631783419:
               default:
                  break;
               case -1631783418:
                  if (var4.equals("_421_Item_05")) {
                     var5 = 2;
                  }
            }

            switch (var5) {
               case 0:
                  this.this$0.sendSettingItemMessage(5, var3);
                  break;
               case 1:
                  this.this$0.sendSettingItemMessage(4, var3);
                  break;
               case 2:
                  this.this$0.sendSettingItemMessage(1, var3);
            }

         }
      });
      this.settingPageUiSet.setOnSettingItemSwitchListener(new OnSettingItemSwitchListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onSwitch(int var1, int var2, int var3) {
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.this$0.settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            int var5 = var6.hashCode();
            byte var4 = -1;
            switch (var5) {
               case -1631783420:
                  if (var6.equals("_421_Item_03")) {
                     var4 = 0;
                  }
                  break;
               case -1631783419:
                  if (var6.equals("_421_Item_04")) {
                     var4 = 1;
                  }
                  break;
               case -1631783417:
                  if (var6.equals("_421_Item_06")) {
                     var4 = 2;
                  }
                  break;
               case -1631783416:
                  if (var6.equals("_421_Item_07")) {
                     var4 = 3;
                  }
                  break;
               case -1631783415:
                  if (var6.equals("_421_Item_08")) {
                     var4 = 4;
                  }
                  break;
               case -1631783414:
                  if (var6.equals("_421_Item_09")) {
                     var4 = 5;
                  }
                  break;
               case -1631783392:
                  if (var6.equals("_421_Item_10")) {
                     var4 = 6;
                  }
                  break;
               case -1216608962:
                  if (var6.equals("F_camera")) {
                     var4 = 7;
                  }
            }

            switch (var4) {
               case 0:
                  this.this$0.sendSettingItemMessage(3, var3);
                  break;
               case 1:
                  this.this$0.sendSettingItemMessage(2, var3);
                  break;
               case 2:
                  this.this$0.sendSettingItemMessage(16, var3);
                  break;
               case 3:
                  this.this$0.sendSettingItemMessage(9, var3);
                  break;
               case 4:
                  this.this$0.sendSettingItemMessage(8, var3);
                  break;
               case 5:
                  this.this$0.sendSettingItemMessage(7, var3);
                  break;
               case 6:
                  this.this$0.sendSettingItemMessage(6, var3);
                  Log.d("lai", "onSwitch: " + var3);
                  break;
               case 7:
                  UiMgr var7 = this.this$0;
                  var7.getMsgMgr(var7.mContext).updateSetting(var1, var2, var3);
                  if (var3 == 0) {
                     SharePreUtil.setBoolValue(this.val$context, "F_camera", false);
                  }

                  if (var3 == 1) {
                     SharePreUtil.setBoolValue(this.val$context, "F_camera", true);
                  }
            }

         }
      });
      this.airPageUiSet.getFrontArea().setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 49});
         }
      });
      this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -121});
         }
      });
   }

   private MsgMgr getMsgMgr(Context var1) {
      MsgMgr var2 = (MsgMgr)MsgMgrFactory.getCanMsgMgr(this.mContext);
      this.msgMgr = var2;
      return var2;
   }

   private void sendPanoramicItemMessage(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 74, 1, (byte)var1});
   }

   private void sendSettingItemMessage(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -116, (byte)var1, (byte)var2});
   }
}
