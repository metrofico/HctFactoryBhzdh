package com.hzbhd.canbus.car._237;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatHeatColdMinPlusClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSeekbarSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import java.util.Iterator;
import java.util.List;

public class UiMgr extends AbstractUiMgr {
   String Ambient_light_Lightness = "Ambient_light_Lightness";
   String Ambient_light_mode1 = "Ambient_light_mode1";
   int ProgressLightness;
   int ProgressModel = 0;
   int Selectpos;
   String Temperature_Unit = "Temperature_Unit";
   private Context mContext;
   private OnAirSeatHeatColdMinPlusClickListener mFrontLeftSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
      }
   };
   private OnAirSeatHeatColdMinPlusClickListener mFrontRightSeatHeatColdListener = new OnAirSeatHeatColdMinPlusClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickMin() {
      }

      public void onClickPlus() {
      }
   };
   private OnAirBtnClickListener mOnAirBottomBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 == 2) {
                  LogUtil.showLog("in out");
                  this.this$0.airBtnClick(2, 0);
               }
            } else {
               this.this$0.airBtnClick(0, 5);
            }
         } else {
            this.this$0.airBtnClick(0, 1);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomLeftBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            this.this$0.airBtnClick(0, 0);
         }

      }
   };
   private OnAirBtnClickListener mOnAirBottomRightBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            LogUtil.showLog("Power");
            this.this$0.airBtnClick(0, 7);
         }

      }
   };
   private OnAirPageStatusListener mOnAirPageStatusListener = new OnAirPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (var1 == 1) {
            this.this$0.sendData(new byte[]{22, -112, 36});
            this.this$0.sendData(new byte[]{22, -112, 54});
         }

      }
   };
   private OnAirBtnClickListener mOnAirTopBtnClickListener = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 != 0) {
            if (var1 == 1) {
               this.this$0.airBtnClick(1, 2);
            }
         } else {
            this.this$0.airBtnClick(0, 4);
         }

      }
   };
   private OnSettingItemSeekbarSelectListener mOnSettingItemSeekbarSelectListener = new OnSettingItemSeekbarSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         UiMgr var4 = this.this$0;
         if (var1 == var4.getSettingLeftIndexes(var4.mContext, "car_set")) {
            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "car_set", "_253_Ambient_light_Lightness")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 1, (byte)var3});
               this.this$0.ProgressLightness = var3;
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.Ambient_light_Lightness, var3);
               return;
            }

            var4 = this.this$0;
            if (var2 == var4.getSettingRightIndex(var4.mContext, "car_set", "_253_Ambient_light_mode1")) {
               CanbusMsgSender.sendMsg(new byte[]{22, -56, 2, (byte)var3});
               this.this$0.ProgressModel = var3;
               var4 = this.this$0;
               var4.getMsgMgr(var4.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.Ambient_light_mode1, var3);
            }
         }

      }
   };
   private OnSettingItemSelectListener mOnSettingItemSelectListener = new OnSettingItemSelectListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1, int var2, int var3) {
         if (var1 == 0 && var2 == 0) {
            byte var4 = 0;
            if (var3 == 0) {
               var4 = 1;
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, (byte)var4});
            SharePreUtil.setStringValue(this.this$0.mContext, "share_pre_is_use_f_unit", var3 + "");
            CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, 1});
            CanbusMsgSender.sendMsg(new byte[]{22, -56, 0, 0});
            UiMgr var5 = this.this$0;
            var5.getMsgMgr(var5.mContext).updateSettings(this.this$0.mContext, var1, var2, this.this$0.Temperature_Unit, var3);
            this.this$0.Selectpos = var3;
         }

      }
   };
   private OnSettingPageStatusListener mOnSettingPageStatusListener = new OnSettingPageStatusListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onStatusChange(int var1) {
         if (var1 == 0) {
            this.this$0.sendData(new byte[]{22, -112, 127});
            this.this$0.mContext.sendBroadcast(new Intent(MsgMgr.UPDATE_SETTING_ACTION));
         }

      }
   };
   private OnAirWindSpeedUpDownClickListener mSetWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         this.this$0.airBtnClick(1, 0);
      }

      public void onClickRight() {
         this.this$0.airBtnClick(1, 1);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.airBtnClick(3, 0);
      }

      public void onClickUp() {
         this.this$0.airBtnClick(3, 1);
      }
   };
   private OnAirTemperatureUpDownClickListener mTempSetViewOnUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.airBtnClick(3, 0);
      }

      public void onClickUp() {
         this.this$0.airBtnClick(3, 1);
      }
   };
   MsgMgr msgMgr;

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
      var2.setOnSettingItemSeekbarSelectListener(this.mOnSettingItemSeekbarSelectListener);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirTopBtnClickListener, this.mOnAirBottomLeftBtnClickListener, this.mOnAirBottomRightBtnClickListener, this.mOnAirBottomBtnClickListener});
      var3.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
      var3.getFrontArea().setSeatHeatColdClickListeners(new OnAirSeatHeatColdMinPlusClickListener[]{this.mFrontLeftSeatHeatColdListener, this.mFrontRightSeatHeatColdListener});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.mSetWindSpeedViewOnClickListener);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mTempSetViewOnUpDownClickListenerLeft, null, this.mTempSetViewOnUpDownClickListenerRight});
      var3.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.airBtnClick(0, 6);
         }

         public void onRightSeatClick() {
            this.this$0.airBtnClick(0, 6);
         }
      });
      this.getTireUiSet(var1).setOnTirePageStatusListener(new OnTirePageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == 1) {
               CanbusMsgSender.sendMsg(new byte[]{22, -112, 96});
            }

         }
      });
   }

   private void airBtnClick(int var1, int var2) {
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 == 3) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true)});
                  CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false)});
               }
            } else {
               LogUtil.showLog("here");
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0});
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0, 0});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true), 0, 0, 0});
         CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false), 0, 0, 0});
      }

   }

   private MsgMgr getMsgMgr(Context var1) {
      if (this.msgMgr == null) {
         this.msgMgr = (MsgMgr)MsgMgrFactory.getCanMsgMgr(var1);
      }

      return this.msgMgr;
   }

   private void initData() {
      MsgMgr var1;
      Context var2;
      if (!this.getMsgMgr(this.mContext).is404("car_set", "_253_Ambient_light_Lightness")) {
         var1 = this.getMsgMgr(this.mContext);
         var2 = this.mContext;
         var1.updateSettings(var2, this.getSettingLeftIndexes(var2, "car_set"), this.getSettingRightIndex(this.mContext, "car_set", "_253_Ambient_light_Lightness"), this.Ambient_light_Lightness, this.ProgressLightness);
      }

      if (!this.getMsgMgr(this.mContext).is404("car_set", "Ambient_light_mode1")) {
         MsgMgr var4 = this.getMsgMgr(this.mContext);
         Context var3 = this.mContext;
         var4.updateSettings(var3, this.getSettingLeftIndexes(var3, "car_set"), this.getSettingRightIndex(this.mContext, "car_set", "Ambient_light_mode1"), this.Ambient_light_mode1, this.ProgressModel);
      }

      if (!this.getMsgMgr(this.mContext).is404("car_set", "temperature_unit")) {
         var1 = this.getMsgMgr(this.mContext);
         var2 = this.mContext;
         var1.updateSettings(var2, this.getSettingLeftIndexes(var2, "car_set"), this.getSettingRightIndex(this.mContext, "car_set", "temperature_unit"), this.Temperature_Unit, this.Selectpos);
      }

   }

   protected int getSettingLeftIndexes(Context var1, String var2) {
      List var5 = this.getPageUiSet(var1).getSettingPageUiSet().getList();
      Iterator var4 = var5.iterator();

      for(int var3 = 0; var3 < var5.size(); ++var3) {
         if (var2.equals(((SettingPageUiSet.ListBean)var4.next()).getTitleSrn())) {
            return var3;
         }
      }

      return 404;
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

      return 404;
   }

   public void send0xC6(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte)var1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      this.initData();
   }
}
