package com.hzbhd.canbus.car._320;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.bean.FrontArea;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import java.util.Arrays;

public class UiMgr extends AbstractUiMgr {
   private byte[] m0x8AData = new byte[]{22, -118, 0, 0};
   private Context mContext;
   private Handler mHandler = new Handler(Looper.getMainLooper());
   private OnAirBtnClickListener mOnAirBtnClickListenerBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(3, var1);
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerLeft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(1, var1);
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(2, var1);
      }
   };
   private OnAirBtnClickListener mOnAirBtnClickListenerTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         this.this$0.sendAirCommand(0, var1);
      }
   };
   private OnAirTemperatureUpDownClickListener mOnUpDownClickListener = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         this.this$0.sendAirCommandUpDown(2, 2);
      }

      public void onClickUp() {
         this.this$0.sendAirCommandUpDown(2, 1);
      }
   };

   public UiMgr(Context var1) {
      this.mContext = var1;
      AirPageUiSet var3 = this.getAirUiSet(var1);
      var3.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerTop, this.mOnAirBtnClickListenerLeft, this.mOnAirBtnClickListenerRight, this.mOnAirBtnClickListenerBottom});
      FrontArea var4 = var3.getFrontArea();
      OnAirTemperatureUpDownClickListener var2 = this.mOnUpDownClickListener;
      var4.setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{var2, null, var2});
      var3.getFrontArea().setSetWindSpeedViewOnClickListener(new OnAirWindSpeedUpDownClickListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onClickLeft() {
            this.this$0.sendAirCommandUpDown(1, 2);
         }

         public void onClickRight() {
            this.this$0.sendAirCommandUpDown(1, 1);
         }
      });
   }

   private void sendAirCommand(int var1, int var2) {
      String var5 = this.getAirUiSet(this.mContext).getFrontArea().getLineBtnAction()[var1][var2];
      byte[] var4 = this.m0x8AData;
      Arrays.copyOf(var4, var4.length);
      var5.hashCode();
      int var3 = var5.hashCode();
      byte var7 = 2;
      byte var6 = -1;
      switch (var3) {
         case -1470045433:
            if (var5.equals("front_defog")) {
               var6 = 0;
            }
            break;
         case -631663038:
            if (var5.equals("rear_defog")) {
               var6 = 1;
            }
            break;
         case 3106:
            if (var5.equals("ac")) {
               var6 = 2;
            }
            break;
         case 3005871:
            if (var5.equals("auto")) {
               var6 = 3;
            }
            break;
         case 106858757:
            if (var5.equals("power")) {
               var6 = 4;
            }
            break;
         case 756225563:
            if (var5.equals("in_out_cycle")) {
               var6 = 5;
            }
            break;
         case 1018451744:
            if (var5.equals("blow_head_foot")) {
               var6 = 6;
            }
            break;
         case 1434490075:
            if (var5.equals("blow_foot")) {
               var6 = 7;
            }
            break;
         case 1434539597:
            if (var5.equals("blow_head")) {
               var6 = 8;
            }
            break;
         case 1568764496:
            if (var5.equals("blow_window_foot")) {
               var6 = 9;
            }
      }

      switch (var6) {
         case 0:
            if (!GeneralAirData.front_defog) {
               var7 = 1;
            }

            this.sendAirCommandUpDown(7, var7);
            break;
         case 1:
            if (!GeneralAirData.rear_defog) {
               var7 = 1;
            }

            this.sendAirCommandUpDown(6, var7);
            break;
         case 2:
            if (!GeneralAirData.ac) {
               var7 = 1;
            }

            this.sendAirCommandUpDown(4, var7);
            break;
         case 3:
            if (!GeneralAirData.auto) {
               var7 = 1;
            }

            this.sendAirCommandUpDown(9, var7);
            break;
         case 4:
            this.sendAirCommandUpDown(0);
            break;
         case 5:
            if (!GeneralAirData.in_out_cycle) {
               var7 = 1;
            }

            this.sendAirCommandUpDown(5, var7);
            break;
         case 6:
            this.sendAirCommandUpDown(8, 2);
            break;
         case 7:
            this.sendAirCommandUpDown(8, 3);
            break;
         case 8:
            this.sendAirCommandUpDown(8, 1);
            break;
         case 9:
            this.sendAirCommandUpDown(8, 4);
      }

   }

   private void sendAirCommand(byte[] var1) {
      CanbusMsgSender.sendMsg(var1);
   }

   private void sendAirCommandUpDown(int var1) {
      this.sendAirCommandUpDown(var1, 1);
   }

   private void sendAirCommandUpDown(int var1, int var2) {
      CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)var1, (byte)var2});
      this.mHandler.postDelayed(new Runnable(this, var1) {
         final UiMgr this$0;
         final int val$command;

         {
            this.this$0 = var1;
            this.val$command = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, -118, (byte)this.val$command, 0});
         }
      }, 100L);
   }
}
