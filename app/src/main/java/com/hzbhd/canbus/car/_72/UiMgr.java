package com.hzbhd.canbus.car._72;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.interfaces.OnDriveDataPageStatusListener;
import com.hzbhd.canbus.ui_mgr.AbstractUiMgr;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;

public class UiMgr extends AbstractUiMgr {
   private TimerUtil mTimerUtil;
   private byte[] mVehicleSpeedRequestCommand;
   private TimerTask mVehicleSpeedRequestTask;

   public UiMgr(Context var1) {
      this.getDriverDataPageUiSet(var1).setOnDriveDataPageStatusListener(new OnDriveDataPageStatusListener(this) {
         final UiMgr this$0;

         {
            this.this$0 = var1;
         }

         public void onStatusChange(int var1) {
            if (var1 == -1) {
               this.this$0.getTimerUtil().startTimer(this.this$0.getVehicleSpeedRequestTask(), 0L, 333L);
            } else if (var1 == -2) {
               this.this$0.getTimerUtil().stopTimer();
            }

         }
      });
   }

   private TimerUtil getTimerUtil() {
      if (this.mTimerUtil == null) {
         this.mTimerUtil = new TimerUtil();
      }

      return this.mTimerUtil;
   }

   private byte[] getVehicleSpeedRequestCommand() {
      if (this.mVehicleSpeedRequestCommand == null) {
         this.mVehicleSpeedRequestCommand = new byte[]{22, -112, 22, 0};
      }

      return this.mVehicleSpeedRequestCommand;
   }

   private TimerTask getVehicleSpeedRequestTask() {
      if (this.mVehicleSpeedRequestTask == null) {
         this.mVehicleSpeedRequestTask = new TimerTask(this) {
            final UiMgr this$0;

            {
               this.this$0 = var1;
            }

            public void run() {
               CanbusMsgSender.sendMsg(this.this$0.getVehicleSpeedRequestCommand());
            }
         };
      }

      return this.mVehicleSpeedRequestTask;
   }
}
