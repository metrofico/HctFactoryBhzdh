package com.hzbhd.canbus.util;

import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class TimerUtil {
   private Timer mTimer;
   private TimerTask mTimerTask;

   public void startTimer(TimerTask var1, long var2) {
      Log.i("TimerUtil", "startTimer: " + this);
      if (var1 != null) {
         this.mTimerTask = var1;
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         Timer var4 = this.mTimer;
         if (var4 != null) {
            var4.schedule(this.mTimerTask, var2);
         }

      }
   }

   public void startTimer(TimerTask var1, long var2, long var4) {
      Log.i("TimerUtil", "startTimer: " + this);
      if (var1 != null) {
         this.mTimerTask = var1;
         if (this.mTimer == null) {
            this.mTimer = new Timer();
         }

         Timer var6 = this.mTimer;
         if (var6 != null) {
            var6.schedule(this.mTimerTask, var2, var4);
         }

      }
   }

   public void stopTimer() {
      Log.i("TimerUtil", "stopTimer: " + this);
      TimerTask var1 = this.mTimerTask;
      if (var1 != null) {
         var1.cancel();
         this.mTimerTask = null;
      }

      Timer var2 = this.mTimer;
      if (var2 != null) {
         var2.cancel();
         this.mTimer = null;
      }

   }
}
