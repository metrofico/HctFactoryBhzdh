package com.hzbhd.canbus.car._238;

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
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.interfaces.OnTirePageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
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
            this.this$0.mContext.sendBroadcast(new Intent(com.hzbhd.canbus.car._237.MsgMgr.UPDATE_SETTING_ACTION));
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 36});
            CanbusMsgSender.sendMsg(new byte[]{22, -112, 54});
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
            this.this$0.mContext.sendBroadcast(new Intent(com.hzbhd.canbus.car._237.MsgMgr.UPDATE_SETTING_ACTION));
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

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
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
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true)});
               CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false)});
            }
         } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true)});
            CanbusMsgSender.sendMsg(new byte[]{22, -57, 0, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false)});
         }
      } else {
         CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)DataHandleUtils.setIntByteWithBit(0, var2, true)});
         CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte)DataHandleUtils.setIntByteWithBit(0, var2, false)});
      }

   }
}
