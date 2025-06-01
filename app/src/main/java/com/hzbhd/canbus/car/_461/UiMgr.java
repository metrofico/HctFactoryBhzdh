package com.hzbhd.canbus.car._461;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "_461_setting_config")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_config", "_461_setting_config_language")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var3, -1, -1, -1, -1});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_config", "_461_setting_config_fuel_unit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, (byte)var3, -1, -1, -1});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_config", "_461_setting_config_temp_unit")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, -1, (byte)var3, -1, -1});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_config", "_461_setting_config_date")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, -1, -1, (byte)var3, -1});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_config", "_461_setting_config_time")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -126, -1, -1, -1, -1, (byte)var3});
               }
            }

            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "_461_setting_set")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_three_lighting")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 4, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_running_lights")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_welcome_light")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 6, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_car_door_locks")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_relock")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_interlock")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, (byte)var3});
               }
            }

         }
      });
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "_461_setting_set")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_tire_reset")) {
                  Toast.makeText(this.val$context, "RESETTING...", 0).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_measurement")) {
                  Toast.makeText(this.val$context, "MEASURING...", 0).show();
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 10, 1});
               }
            }

         }
      });
      var2.setOnSettingItemSeekbarSelectListener(new OnSettingItemSeekbarSelectListener(this, var1) {
         final UiMgr this$0;
         final Context val$context;

         {
            this.this$0 = var1;
            this.val$context = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == this.this$0.getSettingLeftIndexes(this.val$context, "_461_setting_set")) {
               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_speed")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, (byte)var3});
               }

               if (var2 == this.this$0.getSettingRightIndex(this.val$context, "_461_setting_set", "_461_setting_set_home_lighting")) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, (byte)var3});
               }
            }

         }
      });
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var6 = var7.iterator();

         while(var6.hasNext()) {
            List var5 = ((DriverDataPageUiSet.Page)var6.next()).getItemList();

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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
