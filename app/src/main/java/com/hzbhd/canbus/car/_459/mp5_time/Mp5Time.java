package com.hzbhd.canbus.car._459.mp5_time;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.CanbusMsgSender;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Mp5Time {
   private int MP5_TIME_RE_SEAND;
   private Calendar calendar;
   private LocalDateTime dateTime;
   private Handler handler;
   private boolean reDoTag;
   private SimpleDateFormat sdf;

   private Mp5Time() {
      this.MP5_TIME_RE_SEAND = 222;
      this.reDoTag = false;
      this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
      this.calendar = Calendar.getInstance();
      this.handler = new Handler(this, Looper.getMainLooper()) {
         final Mp5Time this$0;

         {
            this.this$0 = var1;
         }

         public void handleMessage(Message var1) {
            super.handleMessage(var1);
            if (this.this$0.reDoTag && var1.what == this.this$0.MP5_TIME_RE_SEAND) {
               Date var8 = new Date(System.currentTimeMillis());
               this.this$0.calendar.setTime(var8);
               int var5 = this.this$0.calendar.get(1);
               int var2 = this.this$0.calendar.get(2);
               int var7 = this.this$0.calendar.get(5);
               int var3 = this.this$0.calendar.get(11);
               int var4 = this.this$0.calendar.get(12);
               int var6 = this.this$0.calendar.get(13);
               this.this$0.calendar.get(14);
               CanbusMsgSender.sendMsg(new byte[]{22, 24, -2, -26, 40, (byte)(var6 * 4), (byte)var4, (byte)var3, (byte)(var2 + 1), (byte)(var7 * 4), (byte)(var5 - 1985), 0, 0, 1});
               this.this$0.reDo();
            }

         }
      };
   }

   // $FF: synthetic method
   Mp5Time(Object var1) {
      this();
   }

   public static Mp5Time getInstance() {
      return Mp5Time.Holder.INSTANCE;
   }

   private void reDo() {
      this.handler.sendEmptyMessageDelayed(this.MP5_TIME_RE_SEAND, 1000L);
   }

   public void start() {
      if (!this.reDoTag) {
         this.reDoTag = true;
         this.reDo();
      }

   }

   public void stop() {
      this.reDoTag = false;
   }

   private static class Holder {
      private static final Mp5Time INSTANCE = new Mp5Time();
   }
}
