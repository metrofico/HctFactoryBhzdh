package com.hzbhd.canbus.car._336;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
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
   MsgMgr mMsgMgr;
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(49);
      }
   };
   OnDriveDataPageStatusListener onDriveDataPageStatusListener = new OnDriveDataPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequestData(17);
         this.this$0.activeRequestData(33);
         this.this$0.activeRequestData(97);
      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_336_car_settings")) {
            switch (var2) {
               case 0:
                  this.this$0.sendCarSettingsInfo(16, var3);
                  break;
               case 1:
                  this.this$0.sendCarSettingsInfo(17, var3);
                  break;
               case 2:
                  this.this$0.sendCarSettingsInfo(18, var3);
                  break;
               case 3:
                  this.this$0.sendCarSettingsInfo(19, var3);
                  break;
               case 4:
                  this.this$0.sendCarSettingsInfo(32, var3);
                  break;
               case 5:
                  this.this$0.sendCarSettingsInfo(33, var3);
                  break;
               case 6:
                  this.this$0.sendCarSettingsInfo(34, var3);
                  break;
               case 7:
                  this.this$0.sendCarSettingsInfo(35, var3);
                  break;
               case 8:
                  this.this$0.sendCarSettingsInfo(48, var3);
                  break;
               case 9:
                  this.this$0.sendCarSettingsInfo(49, var3);
                  break;
               case 10:
                  this.this$0.sendCarSettingsInfo(50, var3);
            }
         }

      }
   };
   OnSettingPageStatusListener onSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         UiMgr var2 = this.this$0;
         if (var1 == var2.getSettingLeftIndexes(var2.mContext, "_336_car_settings")) {
            this.this$0.activeRequestData(97);
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.onSettingPageStatusListener);
      this.getAirUiSet(this.mContext).setOnAirPageStatusListener(this.onAirPageStatusListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(this.onDriveDataPageStatusListener);
   }

   private int getDecimalFrom8Bit(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      return Integer.parseInt(var1 + "" + var2 + "" + var3 + "" + var4 + "" + var5 + "" + var6 + "" + var7 + "" + var8, 2);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void initUi() {
   }

   private void intiData() {
      this.activeRequestData(240);
      this.sendCarModelInfo();
   }

   private void sendCarModelInfo() {
      int var1 = this.eachId;
      if (var1 == 1) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 1, 40});
      } else if (var1 == 2) {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 2, 40});
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, 36, 3, 40});
      }

   }

   private void sendCarSettingsInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, -1, -1});
   }

   public void activeRequestData(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 1, (byte)var1});
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         Iterator var7 = var5.iterator();

         while(var7.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var6.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var6.get(var4)).getTitleSrn().equals(var2)) {
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
      List var4 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var5 = var4.iterator();

      for(int var3 = 0; var3 < var4.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var5.next()).getTitleSrn())) {
            return var3;
         }
      }

      return -1;
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initUi();
      this.intiData();
   }
}
