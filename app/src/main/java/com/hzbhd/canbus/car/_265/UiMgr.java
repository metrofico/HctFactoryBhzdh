package com.hzbhd.canbus.car._265;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   int differentId;
   int eachId;
   Context mContext;
   private MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var3 = this.getAirUiSet(this.mContext);
      int var2 = this.differentId;
      if (var2 == 13) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 1});
      } else if (var2 == 14) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 2});
      } else if (var2 == 15) {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 3});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -123, 0});
      }

      var2 = this.eachId;
      if (var2 != 12 && var2 != 13 && var2 != 14 && var2 != 15) {
         var2 = this.differentId;
         if (var2 != 12 && var2 != 13 && var2 != 14) {
            this.removeMainPrjBtnByName(var1, "setting");
         }
      }

      var2 = this.eachId;
      if (var2 != 1 && var2 != 2 && var2 != 10 && var2 != 11 && var2 != 12 && var2 != 13 && var2 != 14 && var2 != 15) {
         this.removeMainPrjBtn(var1, 0, 1);
         this.getParkPageUiSet(var1).setShowRadar(false);
      }

      var2 = this.eachId;
      if (var2 != 2 && var2 != 10 && var2 != 11 && var2 != 12 && var2 != 13 && var2 != 14 && var2 != 15) {
         this.removeDriveDateItemForTitles(var1, new String[]{"a_current_speed"});
         this.removeDriveDateItemForTitles(var1, new String[]{"engine_speed"});
      }

      if (this.eachId == 1) {
         this.removeDriveData(var1, 1, 1);
      }

      if (this.eachId == 12) {
         this.removeDriveDateItemForTitles(var1, new String[]{"_246_car_info16"});
         this.removeDriveDateItemForTitles(var1, new String[]{"_41_park_assist_system_status"});
         this.removeSettingRightItemByNameList(var1, new String[]{"_246_paring_info2"});
      }

      var2 = this.eachId;
      if (var2 != 13 && var2 != 14 && var2 != 15) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"car_set1"});
      }

      if (this.eachId != 12) {
         this.removeSettingLeftItemByNameList(var1, new String[]{"_246_setting_car_info32"});
      }

      var2 = this.eachId;
      if (var2 == 13 || var2 == 14 || var2 == 15) {
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"light_info"});
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"gear_position"});
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"reverse_state"});
      }

      var2 = this.eachId;
      if (var2 != 2 && var2 != 10 && var2 != 11 && var2 != 12 && var2 != 13 && var2 != 14 && var2 != 15) {
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"a_current_speed"});
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"engine_speed"});
      }

      var2 = this.eachId;
      if (var2 == 12 || var2 == 13 || var2 == 14 || var2 == 15) {
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"keyboard_backlight_adjustment"});
      }

      var2 = this.eachId;
      if (var2 == 1 || var2 == 12) {
         this.removeDriveDateItemForTitles(this.mContext, new String[]{"_246_car_info16"});
      }

      var2 = this.eachId;
      if (var2 != 10 && var2 != 12 && var2 != 13 && var2 != 14 && var2 != 15) {
         var2 = this.differentId;
         if (var2 != 12 && var2 != 13 && var2 != 14) {
            this.removeDriveDateItemForTitles(this.mContext, new String[]{"_370_Time"});
         }
      }

      var2 = this.eachId;
      if (var2 != 1 && var2 != 2 && var2 != 10) {
         var3.setHaveRearArea(false);
      } else {
         var3.setHaveRearArea(true);
      }

      SettingPageUiSet var4 = this.getSettingUiSet(this.mContext);
      var4.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var4) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var5 = this.this$0;
            String var6;
            if (var1 == var5.getSettingLeftIndexes(var5.mContext, "_246_setting_car_info32")) {
               var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var6.hashCode();
               switch (var6) {
                  case "_337_setting_42":
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 3, 1, (byte)var3});
                     break;
                  case "_332_setting_5":
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 2, 3, (byte)var3});
                     break;
                  case "_332_setting_6":
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 2, 2, (byte)var3});
                     break;
                  case "_332_setting_9":
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 2, 1, (byte)var3});
                     break;
                  case "_189_car_setting_A":
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 3, 2, (byte)var3});
                     break;
                  case "language_setup":
                     CanbusMsgSender.sendMsg(new byte[]{22, -57, 1, 1, (byte)var3});
               }
            }

            var5 = this.this$0;
            if (var1 == var5.getSettingLeftIndexes(var5.mContext, "car_set1")) {
               var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var6.hashCode();
               if (!var6.equals("_246_setting_car_info28")) {
                  if (var6.equals("_246_paring_info2")) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 0, (byte)var3});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 3, (byte)var3});
               }
            }

            var5 = this.this$0;
            if (var1 == var5.getSettingLeftIndexes(var5.mContext, "_265_oreginal_camera_setting")) {
               var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
               var6.hashCode();
               if (!var6.equals("_265_oreginal_camera_setting1")) {
                  if (var6.equals("_265_oreginal_camera_setting2")) {
                     CanbusMsgSender.sendMsg(new byte[]{22, -125, 6, (byte)(var3 + 6)});
                  }
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, 5, (byte)(var3 + 1)});
               }
            }

         }
      });
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequestData(7);
            this.this$0.activeRequestData(8);
            this.this$0.activeRequestData(20);
            this.this$0.activeRequestData(36);
            this.this$0.activeRequestData(37);
            this.this$0.activeRequestData(57);
            this.this$0.activeRequestData(65);
         }
      });
   }

   private void activeRequestData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -112, (byte)var1});
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var6 = var5.iterator();

         while(var6.hasNext()) {
            List var7 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

            for(int var4 = 0; var4 < var7.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var7.get(var4)).getTitleSrn().equals(var2)) {
                  return var4;
               }
            }
         }
      }

      return -1;
   }

   protected int getDrivingPageIndexes(Context var1, String var2) {
      List var4 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (((DriverDataPageUiSet.Page)var4.get(var3)).getHeadTitleSrn().equals(var2)) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   protected int getSettingRightIndex(Context var1, String var2, String var3) {
      List var6 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var9 = var6.iterator();

      for(int var4 = 0; var4 < var6.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var9.next();
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
}
