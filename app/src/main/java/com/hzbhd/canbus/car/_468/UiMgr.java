package com.hzbhd.canbus.car._468;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
   DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
   DecimalFormat formatInteger2 = new DecimalFormat("00");
   Context mContext;
   private MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionListener(new OnPanelKeyPositionListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void click(int var1) {
            switch (var1) {
               case 0:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 1});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 1:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 2});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 2:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 8});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 3:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 4});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 4:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 3});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 5:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 10});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 6:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 12});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 7:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 11});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 8:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 7});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 9:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 13});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 10:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 9});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 11:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 14});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 12:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 5});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
                  break;
               case 13:
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 6});
                  CanbusMsgSender.sendMsg(new byte[]{22, -62, 0});
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
            List var10 = var7.getItemList();
            Iterator var8 = var10.iterator();

            for(int var5 = 0; var5 < var10.size(); ++var5) {
               if (var3.equals(((SettingPageUiSet.ListBean.ItemListBean)var8.next()).getTitleSrn())) {
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
