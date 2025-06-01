package com.hzbhd.canbus.car._225;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirSeatClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnSettingItemSelectListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.SharePreUtil;

public class UiMgr extends AbstractUiMgr {
   private Context mContext;
   OnAirBtnClickListener onAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var2 = 2;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  var2 = 0;
               } else {
                  var2 = 8;
               }
            }
         } else {
            var2 = 1;
         }

         this.this$0.onKeyClick(var2);
      }
   };
   OnAirBtnClickListener onAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var2;
         if (var1 != 0) {
            var2 = 0;
         } else {
            var2 = 35;
         }

         this.this$0.onKeyClick(var2);
      }
   };
   OnAirBtnClickListener onAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var2;
         if (var1 != 0) {
            var2 = 0;
         } else {
            var2 = 5;
         }

         this.this$0.onKeyClick(var2);
      }
   };
   OnAirBtnClickListener onAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         byte var2;
         if (var1 != 0) {
            if (var1 != 1) {
               var2 = 0;
            } else {
               var2 = 3;
            }
         } else {
            var2 = 9;
         }

         this.this$0.onKeyClick(var2);
      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerCenter = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
      }

      public void onClickUp() {
      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.onKeyClick(13);
      }

      public void onClickUp() {
         this.this$0.onKeyClick(12);
      }
   };
   OnAirTemperatureUpDownClickListener onUpDownClickListenerRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.onKeyClick(15);
      }

      public void onClickUp() {
         this.this$0.onKeyClick(14);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var2 = this.getAirUiSet(var1);
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.onAirBtnClickListenerTop, this.onAirBtnClickListenerLeft, this.onAirBtnClickListenerRight, this.onAirBtnClickListenerBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.onUpDownClickListenerLeft, this.onUpDownClickListenerCenter, this.onUpDownClickListenerRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.onKeyClick(7);
         }

         public void onClickRight() {
            this.this$0.onKeyClick(6);
         }
      });
      var2.getFrontArea().setOnAirSeatClickListener(new OnAirSeatClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onLeftSeatClick() {
            this.this$0.onKeyClick(11);
         }

         public void onRightSeatClick() {
            this.this$0.onKeyClick(11);
         }
      });
      this.getSettingUiSet(var1).setOnSettingItemSelectListener(new OnSettingItemSelectListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickItem(int var1, int var2, int var3) {
            if (var1 == 0 && var2 == 0) {
               CanbusMsgSender.sendMsg(new byte[]{22, 114, 4, 2, (byte)(var3 + 1), 1});
               Intent var4 = new Intent(MsgMgr.UPDATE_SETTING_ACTION);
               var4.putExtra(MsgMgr.INTENT_KEY_POSITION, var3);
               this.this$0.mContext.sendBroadcast(var4);
               SharePreUtil.setIntValue(this.this$0.mContext, MsgMgr.SHARE_AIR_SET, var3);
            }

         }
      });
   }

   private void onKeyClick(int var1) {
      (new Thread(this, var1) {
         final UiMgr this$0;
         final int val$value;

         {
            this.this$0 = var1;
            this.val$value = var2;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, 113, (byte)this.val$value, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, 113, (byte)this.val$value, 0});
         }
      }).start();
   }
}
