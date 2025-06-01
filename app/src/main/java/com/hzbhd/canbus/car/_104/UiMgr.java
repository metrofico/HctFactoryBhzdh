package com.hzbhd.canbus.car._104;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Handler mHandler = new Handler(Looper.getMainLooper());
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
            String var6 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var6.hashCode();
            int var5 = var6.hashCode();
            byte var4 = -1;
            switch (var5) {
               case -873936231:
                  if (var6.equals("_279_temperature_unit")) {
                     var4 = 0;
                  }
                  break;
               case -745633376:
                  if (var6.equals("_283_meter_value_5")) {
                     var4 = 1;
                  }
                  break;
               case -614358364:
                  if (var6.equals("_279_distance_unit")) {
                     var4 = 2;
                  }
                  break;
               case -542733205:
                  if (var6.equals("vm_golf7_vehicle_setup_hour_format")) {
                     var4 = 3;
                  }
                  break;
               case 761305127:
                  if (var6.equals("_1104_window_switch")) {
                     var4 = 4;
                  }
                  break;
               case 1235727081:
                  if (var6.equals("_250_language")) {
                     var4 = 5;
                  }
            }

            switch (var4) {
               case 0:
                  this.this$0.set0x82Datas(this.val$context, 5, var3);
                  break;
               case 1:
                  this.this$0.set0x82Datas(this.val$context, 4, var3);
                  break;
               case 2:
                  this.this$0.set0x82Datas(this.val$context, 2, var3);
                  break;
               case 3:
                  this.this$0.set0x82Datas(this.val$context, 6, var3);
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 4, (byte)var3});
                  this.this$0.getMsgMgr(this.val$context).updateSettingsItem(var1, var2, var3);
                  break;
               case 5:
                  this.this$0.set0x82Datas(this.val$context, 3, var3);
            }

         }
      });
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2) {
            String var3 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var3.hashCode();
            var2 = var3.hashCode();
            byte var4 = -1;
            switch (var2) {
               case -1644746165:
                  if (var3.equals("_1104_right_seat_heat")) {
                     var4 = 0;
                  }
                  break;
               case 1016716398:
                  if (var3.equals("_1104_left_seat_heat")) {
                     var4 = 1;
                  }
                  break;
               case 1214765374:
                  if (var3.equals("_283_radar_switch")) {
                     var4 = 2;
                  }
            }

            switch (var4) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 2, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 1, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -123, 3, 0});
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

   private void set0x82Datas(Context var1, int var2, int var3) {
      byte[] var4 = this.getMsgMgr(var1).get0x04Datas();
      var4[0] = 22;
      var4[1] = -126;
      var4[var2] = (byte)var3;
      CanbusMsgSender.sendMsg(var4);
   }
}
