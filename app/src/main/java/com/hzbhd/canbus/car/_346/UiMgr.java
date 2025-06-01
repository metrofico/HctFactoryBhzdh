package com.hzbhd.canbus.car._346;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   int[] mCanBusInfoInt;
   Context mContext;
   MsgMgr msgMgr;
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 0 && var2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var3});
         }

      }
   };
   SettingPageUiSet settingPageUiSet;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.selectCarModel();
      if (this.eachId != 3) {
         this.removeMainPrjBtnByName(this.mContext, "air");
      }

      if (this.eachId != 2) {
         this.removeMainPrjBtnByName(this.mContext, "setting");
      }

      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      if (this.eachId == 3) {
         var2.getRearArea().setWindMaxLevel(3);
      }

      var2.setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 38, 0});
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
         }
      });
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
         if (var2.equals(var7.getTitleSrn())) {
            List var8 = var7.getItemList();
            Iterator var10 = var8.iterator();

            for(int var5 = 0; var5 < var8.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var10.next()).getTitleSrn())) {
                  return var5;
               }
            }
         }
      }

      return -1;
   }

   public void selectCarModel() {
      int var1 = this.getEachId();
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 3) {
               if (var1 == 4) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -30, 5});
               }
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -30, 4});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 2});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -30, 1});
      }

   }
}
