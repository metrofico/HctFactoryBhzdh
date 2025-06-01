package com.hzbhd.canbus.car._395;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.interfaces.OnSettingItemClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   private int TireState = 0;
   int differentId;
   int eachId;
   Context mContext;
   MsgMgr mMsgMgr;
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SenAirMsg((byte)1);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SenAirMsg((byte)5);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.SenAirMsg((byte)6);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.SenAirMsg((byte)7);
            }
         } else {
            this.this$0.SenAirMsg((byte)2);
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.SenAirMsg((byte)14);
      }

      public void onClickUp() {
         this.this$0.SenAirMsg((byte)13);
      }
   };
   int model = 0;
   OnAirPageStatusListener onAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         this.this$0.activeRequest(49);
      }
   };
   OnAirSeatClickListener onAirSeatClickListener = new OnAirSeatClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onLeftSeatClick() {
         if (this.this$0.model == 0) {
            this.this$0.SenAirMsg((byte)9);
            this.this$0.model = 1;
         } else if (this.this$0.model == 1) {
            this.this$0.SenAirMsg((byte)10);
            this.this$0.model = 2;
         } else if (this.this$0.model == 2) {
            this.this$0.SenAirMsg((byte)27);
            this.this$0.model = 3;
         } else if (this.this$0.model == 3) {
            this.this$0.SenAirMsg((byte)28);
            this.this$0.model = 0;
         }

      }

      public void onRightSeatClick() {
         if (this.this$0.model == 0) {
            this.this$0.SenAirMsg((byte)9);
            this.this$0.model = 1;
         } else if (this.this$0.model == 1) {
            this.this$0.SenAirMsg((byte)10);
            this.this$0.model = 2;
         } else if (this.this$0.model == 2) {
            this.this$0.SenAirMsg((byte)27);
            this.this$0.model = 3;
         } else if (this.this$0.model == 3) {
            this.this$0.SenAirMsg((byte)28);
            this.this$0.model = 0;
         }

      }
   };
   OnAirWindSpeedUpDownClickListener onAirWindSpeedUpDownClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.SenAirMsg((byte)12);
      }

      public void onClickRight() {
         this.this$0.SenAirMsg((byte)11);
      }
   };
   OnSettingItemClickListener onSettingItemClickListener = new OnSettingItemClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2) {
         UiMgr var3 = this.this$0;
         if (var1 == var3.getSettingLeftIndexes(var3.mContext, "_394_seting")) {
            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_394_seting", "_394_seting5")) {
               if (this.this$0.TireState == 0) {
                  this.this$0.sendCarSetting0x6F(169, 1);
                  this.this$0.TireState = 1;
               } else {
                  this.this$0.TireState = 0;
                  this.this$0.sendCarSetting0x6F(169, 0);
               }
            }

            var3 = this.this$0;
            if (var2 == var3.getSettingRightIndex(var3.mContext, "_394_seting", "_394_seting8")) {
               this.this$0.sendCarSetting0x6F(170, 1);
               var3 = this.this$0;
               var3.getMsgMgr(var3.mContext).toast("success");
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
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_394_seting")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_394_seting", "_394_seting1")) {
               this.this$0.sendCarSetting0x6F(18, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_394_seting", "_394_seting2")) {
               this.this$0.sendCarSetting0x6F(32, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_394_seting", "_394_seting3")) {
               this.this$0.sendCarSetting0x6F(58, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_394_seting", "_394_seting4")) {
               this.this$0.sendCarSetting0x6F(59, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_394_seting", "_394_seting6")) {
               this.this$0.sendCarSetting0x6F(60, var3);
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_394_seting", "_394_seting7")) {
               this.this$0.sendCarSetting0x6F(61, var3);
            }
         }

         var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "_394_language")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "_394_language", "_394_language")) {
               this.this$0.sendLanguage0x9A(var3);
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(var1, var2, var3);
            }
         }

      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.eachId = this.getEachId();
      this.differentId = this.getCurrentCarId();
      AirPageUiSet var2 = this.getAirUiSet(this.mContext);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.onAirWindSpeedUpDownClickListener);
      var2.getFrontArea().setOnAirSeatClickListener(this.onAirSeatClickListener);
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerLeft, null, null});
      var2.getFrontArea().setOnAirPageStatusListener(this.onAirPageStatusListener);
      SettingPageUiSet var3 = this.getSettingUiSet(this.mContext);
      var3.setOnSettingItemSelectListener(this.onSettingItemSelectListener);
      var3.setOnSettingItemClickListener(this.onSettingItemClickListener);
      this.getDriverDataPageUiSet(this.mContext).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            this.this$0.activeRequest(50);
         }
      });
   }

   private void SenAirMsg(byte var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 1});
      CanbusMsgSender.sendMsg(new byte[]{22, 61, var1, 0});
   }

   private void activeRequest(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, (byte)var1});
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

   private void sendCarSetting0x6F(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, 111, (byte)var1, (byte)var2, 0, 0});
   }

   private void sendLanguage0x9A(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -102, 1, (byte)(var1 + 1)});
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

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
   }
}
