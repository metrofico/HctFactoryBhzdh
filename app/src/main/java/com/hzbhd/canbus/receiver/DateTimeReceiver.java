package com.hzbhd.canbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.util.SystemUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeReceiver extends BroadcastReceiver {
   private final String KEY_AUTO_GPS_TIME = "auto_gps_time";
   private DateFormat MMddyyyy = new SimpleDateFormat("M/d/yyyy");
   private int bDay;
   private int bHours;
   private int bHours24H;
   private int bMins;
   private int bMonth;
   private int bSecond;
   private int bYear2Dig;
   private int bYearTotal;
   private DateFormat ddMMyyyy = new SimpleDateFormat("d/M/yyyy");
   private boolean isFormat24H;
   private boolean isFormatPm;
   private boolean isGpsTime;
   private int mDayOfWeek;
   private DateFormat mFormat;
   private MsgMgrInterface mMsgMgrInterface;
   private int systemDateFormat;
   private DateFormat yyyyMMdd = new SimpleDateFormat("yyyy/M/d");

   private DateFormat getDateFormat() {
      if (this.mFormat == null) {
         this.mFormat = new SimpleDateFormat("yyyy/MM/dd==HHmm");
      }

      return this.mFormat;
   }

   private MsgMgrInterface getMsgMgrInterface(Context var1) {
      if (this.mMsgMgrInterface == null) {
         this.mMsgMgrInterface = MsgMgrFactory.getCanMsgMgrUtil(var1);
      }

      return this.mMsgMgrInterface;
   }

   public byte[] getCurrentDateAndTime(Context var1) {
      Date var6 = new Date();
      Calendar var5 = Calendar.getInstance();
      DateFormat var7 = this.getDateFormat();
      var7.setTimeZone(var5.getTimeZone());
      String var8 = var7.format(var6);
      String[] var12 = var8.split("==");
      boolean var4 = true;
      String var13 = var12[1];
      String var11 = var8.replace("/", "").replace("==", "");
      byte[] var14 = var11.getBytes();
      String var10 = android.text.format.DateFormat.getTimeFormat(var1).format(var5.getTime());
      GregorianCalendar var9 = new GregorianCalendar();
      var5.setTime(var6);
      this.bYearTotal = Integer.valueOf(var11.substring(0, 4));
      this.bYear2Dig = Integer.valueOf(var11.substring(2, 4));
      this.bMonth = Integer.valueOf(var11.substring(4, 6));
      this.bDay = Integer.valueOf(var11.substring(6, 8));
      int var2 = Integer.valueOf(var13) / 100;
      this.bHours = var2;
      this.bHours24H = var2;
      this.bMins = Integer.valueOf(var13) % 100;
      this.bSecond = var5.get(13);
      this.isFormat24H = android.text.format.DateFormat.is24HourFormat(var1);
      boolean var3;
      if (var9.get(9) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isFormatPm = var3;
      if (System.getInt(var1.getContentResolver(), "auto_gps_time", 0) == 1) {
         var3 = true;
      } else {
         var3 = false;
      }

      this.isGpsTime = var3;
      this.systemDateFormat = this.getSystemDateFormat(var1, var6);
      this.mDayOfWeek = var5.get(7);
      if (!android.text.format.DateFormat.is24HourFormat(var1)) {
         var2 = this.bHours;
         if (var2 >= 12) {
            this.bHours = var2 - 12;
         }

         if (var10.contains("PM")) {
            var2 = this.bHours24H;
            if (var2 < 12) {
               this.bHours24H = var2 + 12;
               var14[8] = (this.bHours24H / 10 + "").getBytes()[0];
               var14[9] = (this.bHours24H % 10 + "").getBytes()[0];
            }
         }
      } else {
         if (this.bHours >= 12) {
            var3 = var4;
         } else {
            var3 = false;
         }

         this.isFormatPm = var3;
      }

      return var14;
   }

   public int getSystemDateFormat(Context var1, Date var2) {
      String var3 = android.text.format.DateFormat.getDateFormat(var1).format(var2);
      if (var3.equals(this.ddMMyyyy.format(var2))) {
         return 1;
      } else if (var3.equals(this.yyyyMMdd.format(var2))) {
         return 2;
      } else {
         return var3.equals(this.MMddyyyy.format(var2)) ? 3 : 0;
      }
   }

   public void onReceive(Context var1, Intent var2) {
      String var3 = var2.getAction();
      var2.getExtras();
      if ("android.intent.action.TIME_TICK".equals(var3) || "android.intent.action.TIME_SET".equals(var3)) {
         this.reportDateAndTime(var1);
      }

   }

   public void reportDateAndTime(Context var1) {
      this.getCurrentDateAndTime(var1);
      if (this.getMsgMgrInterface(var1) != null) {
         this.mMsgMgrInterface.dateTimeRepCanbus(this.bYearTotal, this.bYear2Dig, this.bMonth, this.bDay, this.bHours, this.bMins, this.bSecond, this.bHours24H, this.systemDateFormat, this.isFormat24H, this.isFormatPm, this.isGpsTime, this.mDayOfWeek);
      }

      SystemUtil.printCanbusParameter();
   }
}
