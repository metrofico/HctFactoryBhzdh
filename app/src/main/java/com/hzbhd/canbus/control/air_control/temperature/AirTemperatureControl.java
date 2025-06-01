package com.hzbhd.canbus.control.air_control.temperature;

import android.text.TextUtils;
import com.hzbhd.canbus.control.air_control.AbstractAirControl;
import com.hzbhd.canbus.control.air_control.AirControlHelper;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import java.util.TimerTask;

public abstract class AirTemperatureControl extends AbstractAirControl {
   private static final int FAULT_TOLERANT_COUNT = 5;
   private static final long WORK_DELAY = 0L;
   private static final int WORK_PERIOD = 500;
   private int mCount;
   private String mTemperatureNow;

   public boolean isComplete() {
      if (this.mCount < 5) {
         if (TextUtils.equals(this.mTemperatureNow, GeneralAirData.front_left_temperature)) {
            ++this.mCount;
         }

         this.mTemperatureNow = GeneralAirData.front_left_temperature;
         return false;
      } else {
         return true;
      }
   }

   public void most() {
      AirControlHelper.Companion.startTimer(new TimerTask(this) {
         final AirTemperatureControl this$0;

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
      }, 0L, 500);
   }

   public void reset() {
      AirControlHelper.Companion.stopTimer();
      this.mCount = 0;
   }

   public abstract void step();
}
