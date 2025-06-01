package com.hzbhd.canbus.car_cus._448.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

public class TimerModeCheck {
   private static TimerModeCheck myCountDownTimer;
   public final int ACTION_TAG = 255;
   private Handler mHandler = new Handler(this, Looper.getMainLooper()) {
      final TimerModeCheck this$0;

      {
         this.this$0 = var1;
      }

      public void handleMessage(Message var1) {
         super.handleMessage(var1);
         if (var1.what == 255) {
            this.this$0.thisActionDo.toDo((Object)null);
         }

      }
   };
   private ActionCallback thisActionDo;

   private TimerModeCheck() {
   }

   public static TimerModeCheck getInstance() {
      if (myCountDownTimer == null) {
         myCountDownTimer = new TimerModeCheck();
      }

      return myCountDownTimer;
   }

   public void reset(int var1) {
      this.mHandler.removeMessages(255);
      this.mHandler.obtainMessage().what = 255;
      this.mHandler.sendEmptyMessageDelayed(255, (long)var1);
   }

   public void start(int var1, ActionCallback var2) {
      this.thisActionDo = var2;
      this.reset(var1);
   }
}
