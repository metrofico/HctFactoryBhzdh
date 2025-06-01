package com.hzbhd.canbus.car._454;

import android.content.Context;
import android.widget.Toast;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private Context context;
   private MsgMgr mMsgMgr;
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.context, "_454_reset")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.context, "_454_reset", "_454_reset0")) {
               this.this$0.sendData0x82(0, 0);
               Toast.makeText(this.this$0.context, "Reset...", 0).show();
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.context, "_454_reset", "_454_reset1")) {
               this.this$0.sendData0x82(1, 0);
               Toast.makeText(this.this$0.context, "Reset...", 0).show();
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.context, "_454_reset", "_454_reset2")) {
               this.this$0.sendData0x82(2, 0);
               Toast.makeText(this.this$0.context, "Reset...", 0).show();
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.context, "_454_reset", "_454_reset3")) {
               this.this$0.sendData0x82(3, 0);
               Toast.makeText(this.this$0.context, "Reset...", 0).show();
            }
         }

      }
   };
   OnSettingItemSeekbarSelectListener onSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.context, "_454_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting10")) {
               this.this$0.sendData0x82(6, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting11")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -126, 1, (byte)DataHandleUtils.getMsb(var3), (byte)DataHandleUtils.getLsb(var3), 0, 0, 0});
            }
         }

      }
   };
   OnSettingItemSelectListener onSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.context, "_454_setting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting0")) {
               this.this$0.sendData0x82(5, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting1")) {
               this.this$0.sendData0x82(7, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting2")) {
               this.this$0.sendData0x82(8, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting3")) {
               this.this$0.sendData0x82(9, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting4")) {
               this.this$0.sendData0x82(10, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting5")) {
               this.this$0.sendData0x82(11, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting6")) {
               this.this$0.sendData0x82(12, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting7")) {
               this.this$0.sendData0x82(15, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting8")) {
               this.this$0.sendData0x82(16, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting9")) {
               this.this$0.sendData0x82(14, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.context, "_454_setting", "_454_setting12")) {
               this.this$0.sendData0x82(13, var3);
            }
         }

      }
   };

   public UiMgr(Context var1) {
      this.context = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var2.setOnSettingItemSeekbarSelectListener(this.onSettingItemSeekbarSelectListener);
      var2.setOnSettingItemClickListener(this.onSettingItemClickListener);
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.mMsgMgr == null) {
         this.mMsgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.mMsgMgr;
   }

   private void sendData0x82(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte)var1, (byte)var2});
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
