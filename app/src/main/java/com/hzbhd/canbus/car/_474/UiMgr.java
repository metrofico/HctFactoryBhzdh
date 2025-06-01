package com.hzbhd.canbus.car._474;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   OnAirBtnClickListener bottomBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var4;
         label41: {
            UiMgr var3 = this.this$0;
            String[][] var5 = var3.getAirUiSet(var3.mContext).getFrontArea().getLineBtnAction();
            byte var2 = 3;
            var6.hashCode();
            switch (var6) {
               case "front_defog":
                  var4 = 0;
                  break label41;
               case "rear_defog":
                  var4 = 1;
                  break label41;
               case "blow_window":
                  var4 = 2;
                  break label41;
               case "blow_foot":
                  var4 = var2;
               case "blow_head":
                  var4 = 4;
                  break label41;
            }

            var4 = -1;
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 12});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 13});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 16});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 19});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 15});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 18});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
               break;
            case 4:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 14});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 17});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
         }

      }
   };
   OnAirBtnClickListener leftBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         String var3 = var2.getAirUiSet(var2.mContext).getFrontArea().getLineBtnAction()[1][var1];
         var3.hashCode();
         if (var3.equals("auto")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 10});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
         }

      }
   };
   OnAirTemperatureUpDownClickListener leftTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 2});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 1});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
      }
   };
   Context mContext;
   private MsgMgr mMsgMgr;
   OnAirBtnClickListener rightBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         String var3 = var2.getAirUiSet(var2.mContext).getFrontArea().getLineBtnAction()[2][var1];
         var3.hashCode();
         if (var3.equals("auto_2")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 11});
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
         }

      }
   };
   OnAirTemperatureUpDownClickListener rightTemp = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 4});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 3});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
      }
   };
   OnAirBtnClickListener topBtn = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var4;
         label36: {
            UiMgr var3 = this.this$0;
            String[][] var5 = var3.getAirUiSet(var3.mContext).getFrontArea().getLineBtnAction();
            byte var2 = 0;
            var6.hashCode();
            switch (var6) {
               case "ac":
                  var4 = var2;
               case "mono":
                  var4 = 1;
                  break label36;
               case "rear":
                  var4 = 2;
                  break label36;
               case "in_out_cycle":
                  var4 = 3;
                  break label36;
            }

            var4 = -1;
         }

         switch (var4) {
            case 0:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 8});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
               break;
            case 1:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 7});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
               break;
            case 2:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 20});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
               break;
            case 3:
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 9});
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
         }

      }
   };
   OnAirWindSpeedUpDownClickListener windControl = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 6});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 5});
         CanbusMsgSender.sendMsg(new byte[]{22, -58, 14, 0});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.topBtn, this.leftBtn, this.rightBtn, this.bottomBtn});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.leftTemp, null, this.rightTemp});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.windControl);
      var2.setOnAirPageStatusListener(new OnAirPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -1, 3});
         }
      });
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -1, 53});
            CanbusMsgSender.sendMsg(new byte[]{22, -1, 55});
            CanbusMsgSender.sendMsg(new byte[]{22, -1, 57});
         }
      });
      SettingPageUiSet var3 = this.getSettingUiSet(var1);
      var3.setOnSettingPageStatusListener(new OnSettingPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -1, 56});
         }
      });
      var3.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "car_set") && var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_456_settings_1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, (byte)var3});
            }

            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "car_set") && var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_474_buzzer_volume")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, (byte)var3});
            }

            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "car_set") && var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_474_photosensitivity")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -58, 8, (byte)var3});
            }

         }
      });
      var3.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "car_set")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_456_settings_0")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_456_settings_2")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 2, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_456_settings_3")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 3, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_474_automatic_door_lock")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 5, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_474_independent_unlocking_of_driver_door")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 6, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_474_independent_trunk_lid_unlocking")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 7, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_61_radar_switch")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 9, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_143_setting_5")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 10, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "light_ctrl_3")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 11, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_456_settings_5")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 12, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "car_set", "_474_trip_reset")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -58, 13, (byte)var3});
               }
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

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var6 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var6.size(); ++var3) {
         Iterator var7 = var6.iterator();

         while(var7.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var7.next()).getItemList();

            for(int var4 = 0; var4 < var5.size(); ++var4) {
               if (((DriverDataPageUiSet.Page.Item)var5.get(var4)).getTitleSrn().equals(var2)) {
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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
