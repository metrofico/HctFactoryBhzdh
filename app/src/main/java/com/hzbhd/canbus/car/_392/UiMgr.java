package com.hzbhd.canbus.car._392;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
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
   MsgMgr mMsgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
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

   private void send0x2D(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 45, 2, (byte)var1});
   }

   private void send0xCAUnitInfo(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -54, (byte)var1, (byte)var2});
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

   public boolean isLandscape() {
      boolean var1;
      if (this.mContext.getResources().getConfiguration().orientation == 2) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isPortrait() {
      int var1 = this.mContext.getResources().getConfiguration().orientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void send0xC8BtInfo(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -53});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingItemClickListener(new OnSettingItemClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2) {
            UiMgr var3 = this.this$0;
            if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_392_setting")) {
               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "_392_setting", "_392_setting2")) {
                  this.this$0.send0xCAUnitInfo(2, 0);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).toast("Reduce Success");
               }

               var3 = this.this$0;
               if (var2 == var3.getSettingRightIndex(var3.mContext, "_392_setting", "_392_setting3")) {
                  this.this$0.send0xCAUnitInfo(3, 0);
                  var3 = this.this$0;
                  var3.getMsgMgr(var3.mContext).toast("Restore Success");
               }
            }

         }
      });
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            UiMgr var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_392_setting")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_392_setting", "_392_setting1")) {
                  this.this$0.send0xCAUnitInfo(1, var3);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_392_setting", "_392_setting4")) {
                  this.this$0.send0xCAUnitInfo(4, var3);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }

               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_392_setting", "_392_setting5")) {
                  this.this$0.send0xCAUnitInfo(5, var3);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }
            }

            var4 = this.this$0;
            if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_392_car_type")) {
               var4 = this.this$0;
               if (var2 == var4.getSettingRightIndex(var4.mContext, "_392_car_type", "_392_car_type")) {
                  this.this$0.send0x2D(var3 + 1);
                  var4 = this.this$0;
                  var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
               }
            }

         }
      });
   }
}
