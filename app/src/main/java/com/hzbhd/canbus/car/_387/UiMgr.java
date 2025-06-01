package com.hzbhd.canbus.car._387;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.interfaces.OnPanoramicItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   AirPageUiSet airPageUiSet;
   int differentId;
   int eachId;
   Context mContext;
   ParkPageUiSet parkPageUiSet;
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      this.airPageUiSet = this.getAirUiSet(this.mContext);
      this.settingPageUiSet = this.getSettingUiSet(this.mContext);
      this.parkPageUiSet = this.getParkPageUiSet(this.mContext);
      int var2 = this.differentId;
      if (var2 == 3 || var2 == 1) {
         this.airPageUiSet.getFrontArea().setWindMaxLevel(8);
      }

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
               case -638391263:
                  if (var4.equals("_387_item_01")) {
                     var5 = 0;
                  }
                  break;
               case -638391262:
                  if (var4.equals("_387_item_02")) {
                     var5 = 1;
                  }
                  break;
               case -638391261:
                  if (var4.equals("_387_item_03")) {
                     var5 = 2;
                  }
                  break;
               case -638391260:
                  if (var4.equals("_387_item_04")) {
                     var5 = 3;
                  }
                  break;
               case -638391259:
                  if (var4.equals("_387_item_05")) {
                     var5 = 4;
                  }
                  break;
               case -638391258:
                  if (var4.equals("_387_item_06")) {
                     var5 = 5;
                  }
                  break;
               case -638391257:
                  if (var4.equals("_387_item_07")) {
                     var5 = 6;
                  }
                  break;
               case -638391256:
                  if (var4.equals("_387_item_08")) {
                     var5 = 7;
                  }
                  break;
               case -638391255:
                  if (var4.equals("_387_item_09")) {
                     var5 = 8;
                  }
                  break;
               case -638391233:
                  if (var4.equals("_387_item_10")) {
                     var5 = 9;
                  }
                  break;
               case -638391232:
                  if (var4.equals("_387_item_11")) {
                     var5 = 10;
                  }
            }

            switch (var5) {
               case 0:
                  this.this$0.sendSettingMessage(7, var3);
                  break;
               case 1:
                  this.this$0.sendSettingMessage(6, var3);
                  break;
               case 2:
                  this.this$0.sendSettingMessage(5, var3);
                  break;
               case 3:
                  this.this$0.sendSettingMessage(3, var3);
                  break;
               case 4:
                  this.this$0.sendSettingMessage(2, var3);
                  break;
               case 5:
                  this.this$0.sendSettingMessage(1, var3);
                  break;
               case 6:
                  this.this$0.sendSettingMessage(12, var3);
                  break;
               case 7:
                  this.this$0.sendSettingMessage(11, var3);
                  break;
               case 8:
                  this.this$0.sendSettingMessage(8, var3);
                  break;
               case 9:
                  this.this$0.sendSettingMessage(10, var3);
                  break;
               case 10:
                  this.this$0.sendSettingMessage(9, var3);
            }

         }
      });
      this.settingPageUiSet.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.ActiveRequest(120);
         }
      });
      this.parkPageUiSet.setOnPanoramicItemClickListener(new OnPanoramicItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1) {
            if (var1 != 0) {
               if (var1 != 1) {
                  if (var1 != 2) {
                     if (var1 != 3) {
                        if (var1 == 4) {
                           this.this$0.sendPanoramicMessage(9);
                        }
                     } else {
                        this.this$0.sendPanoramicMessage(8);
                     }
                  } else {
                     this.this$0.sendPanoramicMessage(7);
                  }
               } else {
                  this.this$0.sendPanoramicMessage(6);
               }
            } else {
               this.this$0.sendPanoramicMessage(5);
            }

         }
      });
   }

   private void ActiveRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
   }

   private void sendPanoramicMessage(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -14, 16, (byte)var1});
   }

   private void sendSettingMessage(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 126, 13, 0, (byte)var1, (byte)var2});
   }
}
