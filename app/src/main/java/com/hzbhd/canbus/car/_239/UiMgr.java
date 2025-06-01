package com.hzbhd.canbus.car._239;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirPageStatusListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.interfaces.OnSettingPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   private int mData = 0;
   private OnAirBtnClickListener mOnAirBtnClickListenerButtom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerCenter1 = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            if (GeneralAirData.ac) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -92, 1});
            }
         }

      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerCenter2 = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         if (var1 == 0) {
            if (GeneralAirData.in_out_cycle) {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -91, 0});
            } else {
               CanbusMsgSender.sendMsg(new byte[]{22, -125, -91, 1});
            }
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
               if (GeneralAirData.rear_defog) {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -89, 0});
               } else {
                  CanbusMsgSender.sendMsg(new byte[]{22, -125, -89, 1});
               }
            }
         } else if (GeneralAirData.front_defog) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, 4});
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
            this.this$0.sendData(new byte[]{22, -126, 35});
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
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 1, (byte)var3});
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
            this.this$0.sendData(new byte[]{22, -126, 49});
         }

      }
   };
   private OnAirTemperatureUpDownClickListener mUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -95, 1});
      }
   };
   private OnAirTemperatureUpDownClickListener mUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, 0});
      }

      public void onClickUp() {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -94, 1});
      }
   };
   private OnAirWindSpeedUpDownClickListener setWindSpeedViewOnClickListener = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, 0});
      }

      public void onClickRight() {
         CanbusMsgSender.sendMsg(new byte[]{22, -125, -96, 1});
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      SettingPageUiSet var2 = this.getSettingUiSet(var1);
      var2.setOnSettingItemSelectListener(this.mOnSettingItemSelectListener);
      var2.setOnSettingPageStatusListener(this.mOnSettingPageStatusListener);
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.setOnAirPageStatusListener(this.mOnAirPageStatusListener);
      var3.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mUpDownClickListenerLeft, null, this.mUpDownClickListenerRight});
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerCenter1, this.mOnAirBtnClickListenerCenter2, this.mOnAirBtnClickListenerButtom});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(this.setWindSpeedViewOnClickListener);
      var3.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.seatClick();
         }

         public void onRightSeatClick() {
            this.this$0.seatClick();
         }
      });
   }

   private void seatClick() {
      int var1 = this.mData;
      if (var1 != 0) {
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 == 4) {
                     this.mData = 0;
                  }
               } else {
                  this.mData = 4;
               }
            } else {
               this.mData = 3;
            }
         } else {
            this.mData = 2;
         }
      } else {
         this.mData = 1;
      }

      CanbusMsgSender.sendMsg(new byte[]{22, -125, -93, (byte)this.mData});
   }
}
