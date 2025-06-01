package com.hzbhd.canbus.car._309;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.adapter.interfaces.OnAirBtnClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirTemperatureUpDownClickListener;
import com.hzbhd.canbus.adapter.interfaces.OnAirWindSpeedUpDownClickListener;
import com.hzbhd.canbus.interfaces.OnAmplifierCreateAndDestroyListener;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   OnAmplifierCreateAndDestroyListener MOnAmplifierCreateAndDestroyListener = new OnAmplifierCreateAndDestroyListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void create() {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 1, 0, 0, 0, 0, 0, 0, 0});
      }

      public void destroy() {
         CanbusMsgSender.sendMsg(new byte[]{22, -54, 0, 0, 0, 0, 0, 0, 0, 0});
      }
   };
   private String[] mAirBtnListFrontBottom;
   private String[] mAirBtnListFrontLeft;
   private String[] mAirBtnListFrontRight;
   private String[] mAirBtnListFrontTop;
   private Context mContext;
   OnAirBtnClickListener mOnAirBtnClickListenerFrontBottom = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontBottom[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontRight = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontRight[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFrontTop = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontTop[var1]);
      }
   };
   OnAirBtnClickListener mOnAirBtnClickListenerFronteft = new OnAirBtnClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickItem(int var1) {
         UiMgr var2 = this.this$0;
         var2.sendAirCommand(var2.mAirBtnListFrontLeft[var1]);
      }
   };
   OnAirWindSpeedUpDownClickListener mOnAirWindSpeedUpDownClickListenerFront = new OnAirWindSpeedUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickLeft() {
         UiMgr.sendAirCmd(7);
      }

      public void onClickRight() {
         UiMgr.sendAirCmd(6);
      }
   };
   OnPanelKeyPositionTouchListener mOnPanelKeyPositionTouchListener = new OnPanelKeyPositionTouchListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      // $FF: synthetic method
      static void lambda$onTouch$0(byte var0) {
         try {
            Thread.sleep(50L);
         } catch (InterruptedException var2) {
            var2.printStackTrace();
         }

         CanbusMsgSender.sendMsg(new byte[]{22, 116, var0, 0});
      }

      public void onTouch(int var1, MotionEvent var2) {
         byte var3 = this.this$0.getByte(var1);
         if (var2.getAction() == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 116, var3, 1});
            this.this$0.timerUtil.startTimer(new TimerTask(this, var3) {
               final <undefinedtype> this$1;
               final byte val$b;

               {
                  this.this$1 = var1;
                  this.val$b = var2;
               }

               public void run() {
                  CanbusMsgSender.sendMsg(new byte[]{22, 116, this.val$b, 2});
               }
            }, 600L, 50L);
         }

         if (var2.getAction() == 1) {
            this.this$0.timerUtil.stopTimer();
            (new Thread(new UiMgr$9$$ExternalSyntheticLambda0(var3))).start();
         }

      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontLeft = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr.sendAirCmd(13);
      }

      public void onClickUp() {
         UiMgr.sendAirCmd(12);
      }
   };
   OnAirTemperatureUpDownClickListener mOnUpDownClickListenerFrontRight = new OnAirTemperatureUpDownClickListener(this) {
      final UiMgr this$0;

      {
         this.this$0 = var1;
      }

      public void onClickDown() {
         UiMgr.sendAirCmd(15);
      }

      public void onClickUp() {
         UiMgr.sendAirCmd(14);
      }
   };
   private TimerUtil timerUtil = new TimerUtil();

   public UiMgr(Context var1) {
      this.mContext = var1;
      this.getPanelKeyPageUiSet(var1).setOnPanelKeyPositionTouchListener(this.mOnPanelKeyPositionTouchListener);
      AirPageUiSet var2 = this.getAirUiSet(var1);
      String[][] var3 = var2.getFrontArea().getLineBtnAction();
      this.mAirBtnListFrontTop = var3[0];
      this.mAirBtnListFrontLeft = var3[1];
      this.mAirBtnListFrontRight = var3[2];
      this.mAirBtnListFrontBottom = var3[3];
      var2.getFrontArea().setOnAirBtnClickListeners(new OnAirBtnClickListener[]{this.mOnAirBtnClickListenerFrontTop, this.mOnAirBtnClickListenerFronteft, this.mOnAirBtnClickListenerFrontRight, this.mOnAirBtnClickListenerFrontBottom});
      var2.getFrontArea().setTempSetViewOnUpDownClickListeners(new OnAirTemperatureUpDownClickListener[]{this.mOnUpDownClickListenerFrontLeft, null, this.mOnUpDownClickListenerFrontRight});
      var2.getFrontArea().setSetWindSpeedViewOnClickListener(this.mOnAirWindSpeedUpDownClickListenerFront);
      this.getAmplifierPageUiSet(var1).setOnAmplifierCreateAndDestroyListener(this.MOnAmplifierCreateAndDestroyListener);
   }

   private byte getByte(int var1) {
      if (var1 >= 1 && var1 <= 9) {
         return (byte)var1;
      } else {
         if (var1 >= 10 && var1 <= 19) {
            var1 += 6;
         } else {
            if (var1 < 20 || var1 > 23) {
               return 0;
            }

            var1 += 12;
         }

         return (byte)var1;
      }
   }

   public static void sendAirCmd(int var0) {
      (new Thread(var0) {
         final int val$data0;

         {
            this.val$data0 = var1;
         }

         public void run() {
            super.run();
            CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)this.val$data0, 1});

            try {
               sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }

            CanbusMsgSender.sendMsg(new byte[]{22, -38, (byte)this.val$data0, 0});
         }
      }).start();
   }

   private void sendAirCommand(String var1) {
      var1.hashCode();
      int var3 = var1.hashCode();
      byte var2 = -1;
      switch (var3) {
         case -1470045433:
            if (var1.equals("front_defog")) {
               var2 = 0;
            }
            break;
         case -631663038:
            if (var1.equals("rear_defog")) {
               var2 = 1;
            }
            break;
         case -597744666:
            if (var1.equals("blow_positive")) {
               var2 = 2;
            }
            break;
         case 3106:
            if (var1.equals("ac")) {
               var2 = 3;
            }
            break;
         case 3005871:
            if (var1.equals("auto")) {
               var2 = 4;
            }
            break;
         case 3094652:
            if (var1.equals("dual")) {
               var2 = 5;
            }
            break;
         case 106858757:
            if (var1.equals("power")) {
               var2 = 6;
            }
            break;
         case 756225563:
            if (var1.equals("in_out_cycle")) {
               var2 = 7;
            }
      }

      switch (var2) {
         case 0:
            sendAirCmd(33);
            break;
         case 1:
            sendAirCmd(5);
            break;
         case 2:
            sendAirCmd(9);
            break;
         case 3:
            sendAirCmd(8);
            break;
         case 4:
            sendAirCmd(1);
            break;
         case 5:
            sendAirCmd(2);
            break;
         case 6:
            sendAirCmd(10);
            break;
         case 7:
            if (GeneralAirData.in_out_cycle) {
               sendAirCmd(38);
            } else {
               sendAirCmd(39);
            }
      }

   }
}
