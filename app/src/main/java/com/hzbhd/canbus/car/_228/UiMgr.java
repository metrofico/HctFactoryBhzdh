package com.hzbhd.canbus.car._228;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private int eachId;
   Context mContext;
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.SendAirFrontMsg((byte)4);
               }
            } else {
               this.this$0.SendAirFrontMsg((byte)17);
            }
         } else {
            this.this$0.SendAirFrontMsg((byte)3);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SendAirFrontMsg((byte)18);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  this.this$0.SendAirFrontMsg((byte)2);
               }
            } else {
               this.this$0.SendAirFrontMsg((byte)0);
            }
         } else {
            this.this$0.SendAirFrontMsg((byte)1);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerFrontleft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SendAirFrontMsg((byte)6);
         }

      }
   };
   private MsgMgr msgMgr;
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendAirFrontMsg((byte)14);
      }

      public void onClickUp() {
         this.this$0.SendAirFrontMsg((byte)13);
      }
   };
   private OnAirTemperatureUpDownClickListener onAirTemperatureUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SendAirFrontMsg((byte)16);
      }

      public void onClickUp() {
         this.this$0.SendAirFrontMsg((byte)15);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      SettingPageUiSet var2 = this.getSettingUiSet(this.mContext);
      var2.setOnSettingItemSelectListener(new OnSettingItemSelectListener(this, var2) {
         final UiMgr this$0;
         final SettingPageUiSet val$settingPageUiSet;

         {
            this.this$0 = var1;
            this.val$settingPageUiSet = var2;
         }

         public void onClickItem(int var1, int var2, int var3) {
            String var4 = ((SettingPageUiSet.ListBean.ItemListBean)((SettingPageUiSet.ListBean)this.val$settingPageUiSet.getList().get(var1)).getItemList().get(var2)).getTitleSrn();
            var4.hashCode();
            if (var4.equals("_228_initial_perspective")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte)var3});
            }

         }
      });
      AirPageUiSet var3 = this.getAirUiSet(this.mContext);
      if (this.eachId == 4) {
         var3.getFrontArea().setWindMaxLevel(6);
      }

      var3.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         int a;
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            int var1 = this.a;
            if (var1 == 0) {
               this.this$0.SendAirFrontMsg((byte)7);
               this.a = 1;
            } else if (var1 == 1) {
               this.this$0.SendAirFrontMsg((byte)8);
               this.a = 2;
            } else if (var1 == 2) {
               this.this$0.SendAirFrontMsg((byte)9);
               this.a = 3;
            } else if (var1 == 3) {
               this.this$0.SendAirFrontMsg((byte)10);
               this.a = 0;
            }

         }

         public void onRightSeatClick() {
            int var1 = this.a;
            if (var1 == 0) {
               this.this$0.SendAirFrontMsg((byte)7);
               this.a = 1;
            } else if (var1 == 1) {
               this.this$0.SendAirFrontMsg((byte)8);
               this.a = 2;
            } else if (var1 == 2) {
               this.this$0.SendAirFrontMsg((byte)9);
               this.a = 3;
            } else if (var1 == 3) {
               this.this$0.SendAirFrontMsg((byte)10);
               this.a = 0;
            }

         }
      });
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFrontleft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.SendAirFrontMsg((byte)11);
         }

         public void onClickRight() {
            this.this$0.SendAirFrontMsg((byte)12);
         }
      });
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onAirTemperatureUpDownClickListenerLeft, null, this.onAirTemperatureUpDownClickListenerRight});
   }

   private void SendAirFrontMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, -88, var1, 0});
   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   protected int getDrivingItemIndexes(Context var1, String var2) {
      List var7 = this.getPageUiSet(var1).getDriverDataPageUiSet().getList();

      for(int var3 = 0; var3 < var7.size(); ++var3) {
         Iterator var5 = var7.iterator();

         while(var5.hasNext()) {
            List var6 = ((DriverDataPageUiSet.Page)var5.next()).getItemList();

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
      List var9 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var6 = var9.iterator();

      for(int var4 = 0; var4 < var9.size(); ++var4) {
         SettingPageUiSet.ListBean var7 = (SettingPageUiSet.ListBean)var6.next();
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
      int var2 = this.eachId;
      if (var2 != 3 && var2 != 5 && var2 != 4) {
         this.removeMainPrjBtnByName(var1, "air");
      }

   }
}
