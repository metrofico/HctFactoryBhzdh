package com.hzbhd.canbus.control.air_control.wind;

import com.hzbhd.canbus.control.air_control.AbstractAirControl;
import com.hzbhd.canbus.control.air_control.AirControlHelper;
import java.util.TimerTask;

public abstract class AirWindControl extends AbstractAirControl {
   private static final long WORK_DELAY = 0L;
   private static final int WORK_PERIOD = 200;

   public abstract boolean isComplete();

   public void most() {
      AirControlHelper.Companion.startTimer(new TimerTask(this) {
         final AirWindControl this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            if (this.this$0.isComplete()) {
               this.this$0.reset();
            } else {
               this.this$0.step();
            }

         }
      }, 0L, 200);
   }

   public void reset() {
      AirControlHelper.Companion.stopTimer();
   }

   public abstract void step();
}
