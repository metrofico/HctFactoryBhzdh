package com.hzbhd.canbus.car._60;

import android.content.Context;
import android.view.MotionEvent;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnPanelKeyPositionTouchListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.ui_set.PanelKeyPageUiSet;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private OnPanelKeyPositionTouchListener mOnPanelKeyPositionTouchListener = new UiMgr$$ExternalSyntheticLambda0(this);
   private final TimerUtil mTimerUtil = new TimerUtil();
   private String[] str = new String[]{"panel_btn_num1", "panel_btn_num2", "panel_btn_num3", "panel_btn_num4", "panel_btn_num5", "panel_btn_num6", "panel_btn_num7", "panel_btn_num8", "panel_btn_num9", "panel_btn_left", "panel_btn_ok", "panel_btn_right", "panel_btn_fmam", "panel_btn_cdmp3", "panel_btn_bc"};

   public UiMgr(Context var1) {
      PanelKeyPageUiSet var2 = this.getPanelKeyPageUiSet(var1);
      var2.setOnPanelKeyPositionTouchListener(this.mOnPanelKeyPositionTouchListener);
      if (this.getCurrentCarId() == 1) {
         var2.setCount(2);
      }

   }

   private void onKeyDown(int var1) {
      CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)var1});
      this.mTimerUtil.startTimer(new TimerTask(this, var1) {
         final UiMgr this$0;
         final int val$diffData;

         {
            this.this$0 = var1;
            this.val$diffData = var2;
         }

         public void run() {
            CanbusMsgSender.sendMsg(new byte[]{22, 6, (byte)this.val$diffData});
         }
      }, 300L, 200L);
   }

   private void onKeyUp() {
      this.mTimerUtil.stopTimer();
      CanbusMsgSender.sendMsg(new byte[]{22, 6, 0});
   }

   // $FF: synthetic method
   void lambda$new$0$com_hzbhd_canbus_car__60_UiMgr(int var1, MotionEvent var2) {
      int var3 = var2.getAction();
      if (var3 != 0) {
         if (var3 == 1) {
            this.onKeyUp();
         }
      } else if (this.getCurrentCarId() == 0) {
         switch (var1) {
            case 0:
               this.onKeyDown(8);
               break;
            case 1:
               this.onKeyDown(9);
               break;
            case 2:
               this.onKeyDown(10);
               break;
            case 3:
               this.onKeyDown(11);
               break;
            case 4:
               this.onKeyDown(12);
               break;
            case 5:
               this.onKeyDown(13);
               break;
            case 6:
               this.onKeyDown(14);
               break;
            case 7:
               this.onKeyDown(15);
               break;
            case 8:
               this.onKeyDown(16);
               break;
            case 9:
               this.onKeyDown(4);
               break;
            case 10:
               this.onKeyDown(1);
               break;
            case 11:
               this.onKeyDown(5);
               break;
            case 12:
               this.onKeyDown(6);
               break;
            case 13:
               this.onKeyDown(7);
               break;
            case 14:
               this.onKeyDown(3);
               break;
            case 15:
               this.onKeyDown(2);
         }
      } else if (var1 != 0) {
         if (var1 == 1) {
            this.onKeyDown(21);
         }
      } else {
         this.onKeyDown(22);
      }

   }

   public void updateUiByDifferentCar(Context var1) {
      super.updateUiByDifferentCar(var1);
      int var2 = this.getCurrentCarId();
      if (var2 != 0) {
         if (var2 == 1) {
            var2 = 0;

            while(true) {
               String[] var3 = this.str;
               if (var2 >= var3.length) {
                  break;
               }

               this.removePanelBtnKeyByName(var1, var3[var2]);
               ++var2;
            }
         }
      } else {
         this.removePanelBtnKeyByName(var1, "panel_btn_clock");
      }

      this.removeMainPrjBtnByName(var1, "drive_data");
   }
}
