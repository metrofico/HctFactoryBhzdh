package com.hzbhd.canbus.car._459.air;

import android.os.CountDownTimer;
import android.util.Log;
import com.hzbhd.proxy.share.ShareDataManager;

public class AirDataBuffer {
   private boolean ac;
   private boolean acMax;
   private boolean auto;
   private boolean bufferTag;
   private boolean cycle;
   private boolean frontDefog;
   private boolean ptc;
   private double temp;
   private int tempOut;
   private CountDownTimer timer;
   private int windLevel;
   private String windMode;

   private AirDataBuffer() {
      this.bufferTag = false;
      this.windLevel = 0;
      this.windMode = "FACE";
      this.temp = 18.0;
      this.cycle = true;
      this.acMax = false;
      this.ptc = false;
      this.ac = false;
      this.frontDefog = false;
      this.auto = false;
      this.tempOut = 30;
   }

   // $FF: synthetic method
   AirDataBuffer(Object var1) {
      this();
   }

   public static AirDataBuffer getInstance() {
      return AirDataBuffer.Holder.INSTANCE;
   }

   public void notifyOtherModule(String var1, Object var2) {
      ShareDataManager.getInstance().reportString("can.bus.all.data.share", "{\"TAG\":\"" + var1 + "\",\"VALUE\":\"" + var2 + "\"}");
   }

   public void resetTimer() {
      this.bufferTag = true;
      CountDownTimer var1 = this.timer;
      if (var1 != null) {
         var1.cancel();
         this.timer = null;
      }

      var1 = new CountDownTimer(this, 3000L, 1000L) {
         final AirDataBuffer this$0;

         {
            this.this$0 = var1;
         }

         public void onFinish() {
            Log.d("AIR_TIMER", "空调数据缓冲倒计结束");
            this.this$0.bufferTag = false;
            AirDataBuffer var1 = this.this$0;
            var1.notifyOtherModule("WIND_LEVEL", var1.windLevel);
            var1 = this.this$0;
            var1.notifyOtherModule("WIND_MODE", var1.windMode);
            var1 = this.this$0;
            var1.notifyOtherModule("TEMP", var1.temp);
            var1 = this.this$0;
            var1.notifyOtherModule("CYCLE", var1.cycle);
            var1 = this.this$0;
            var1.notifyOtherModule("AC_MAX", var1.acMax);
            var1 = this.this$0;
            var1.notifyOtherModule("PTC", var1.ptc);
            var1 = this.this$0;
            var1.notifyOtherModule("AC", var1.ac);
            var1 = this.this$0;
            var1.notifyOtherModule("FRONT_DEFOG", var1.frontDefog);
            var1 = this.this$0;
            var1.notifyOtherModule("AUTO", var1.auto);
            var1 = this.this$0;
            var1.notifyOtherModule("TEMP_OUT", var1.tempOut);
         }

         public void onTick(long var1) {
            Log.d("AIR_TIMER", "空调数据缓冲倒计时：" + var1);
         }
      };
      this.timer = var1;
      var1.start();
   }

   public void setData(int var1, String var2, double var3, boolean var5, boolean var6, boolean var7, boolean var8, boolean var9, boolean var10, int var11) {
      if (!this.bufferTag) {
         if (this.windLevel != var1 && var1 <= 9 && var1 >= 0) {
            this.notifyOtherModule("WIND_LEVEL", var1);
         }

         if (!this.windMode.equals(var2)) {
            this.notifyOtherModule("WIND_MODE", var2);
         }

         if (this.temp != var3) {
            this.notifyOtherModule("TEMP", var3);
         }

         if (this.cycle != var5) {
            this.notifyOtherModule("CYCLE", var5);
         }

         if (this.acMax != var6) {
            this.notifyOtherModule("AC_MAX", var6);
         }

         if (this.ptc != var7) {
            this.notifyOtherModule("PTC", var7);
         }

         if (this.ac != var8) {
            this.notifyOtherModule("AC", var8);
         }

         if (this.frontDefog != var9) {
            this.notifyOtherModule("FRONT_DEFOG", var9);
         }

         if (this.auto != var10) {
            this.notifyOtherModule("AUTO", var10);
         }

         if (this.tempOut != var11) {
            this.notifyOtherModule("TEMP_OUT", var11);
         }
      }

      if (this.windLevel != var1 && var1 <= 9 && var1 >= 0) {
         this.windLevel = var1;
      }

      this.windMode = var2;
      this.temp = var3;
      this.cycle = var5;
      this.acMax = var6;
      this.ptc = var7;
      this.ac = var8;
      this.frontDefog = var9;
      this.auto = var10;
      this.tempOut = var11;
   }

   private static class Holder {
      private static final AirDataBuffer INSTANCE = new AirDataBuffer();
   }
}
