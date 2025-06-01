package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;
import com.hzbhd.canbus.CanbusMsgService;

public class TestCanBusUtil {
   CanbusMsgService canbusMsgService;
   private int data10 = 0;
   private int data11 = 0;
   private int data12 = 0;
   private int data13 = 0;
   private int data14 = 0;
   private int data15 = 0;
   private int data16 = 0;
   private int data17 = 0;
   private int data18 = 0;
   private int data19 = 0;
   private int data2 = 0;
   private int data3 = 0;
   private int data4 = 0;
   private int data5 = 0;
   private int data6 = 0;
   private int data7 = 0;
   private int data8 = 0;
   private int data9 = 0;
   Context mContext;
   private int pack = 85;
   private int runTag = 0;
   Thread thread;

   private void methodBody(byte[] var1) {
      this.data2 = var1[18];
      this.data3 = var1[17];
      this.data4 = var1[16];
      this.data5 = var1[15];
      this.data6 = var1[14];
      this.data7 = var1[13];
      this.data8 = var1[12];
      this.data9 = var1[11];
      this.data10 = var1[10];
      this.data11 = var1[9];
      this.data12 = var1[8];
      this.data13 = var1[7];
      this.data14 = var1[6];
      this.data15 = var1[5];
      this.data16 = var1[4];
      this.data17 = var1[3];
      this.data18 = var1[2];
      this.data19 = var1[1];
      this.pack = var1[0];

      for(int var2 = 0; var2 <= 256; ++var2) {
         int var3 = this.runTag;
         if (var3 == 1 || var3 == 2) {
            break;
         }

         if (this.data19 == -1) {
            ++this.data18;
            this.data19 = 0;
         }

         if (this.data18 == -1) {
            ++this.data17;
            this.data18 = 0;
         }

         if (this.data17 == -1) {
            ++this.data16;
            this.data17 = 0;
         }

         if (this.data16 == -1) {
            ++this.data15;
            this.data16 = 0;
         }

         if (this.data15 == -1) {
            ++this.data14;
            this.data15 = 0;
         }

         if (this.data14 == -1) {
            ++this.data13;
            this.data14 = 0;
         }

         if (this.data13 == -1) {
            ++this.data12;
            this.data13 = 0;
         }

         if (this.data12 == -1) {
            ++this.data11;
            this.data12 = 0;
         }

         if (this.data11 == -1) {
            ++this.data10;
            this.data11 = 0;
         }

         if (this.data10 == -1) {
            ++this.data9;
            this.data10 = 0;
         }

         if (this.data9 == -1) {
            ++this.data8;
            this.data9 = 0;
         }

         if (this.data8 == -1) {
            ++this.data7;
            this.data8 = 0;
         }

         if (this.data7 == -1) {
            ++this.data6;
            this.data7 = 0;
         }

         if (this.data6 == -1) {
            ++this.data5;
            this.data6 = 0;
         }

         if (this.data5 == 255) {
            ++this.data4;
            this.data5 = 0;
         }

         if (this.data4 == -1) {
            ++this.data3;
            this.data4 = 0;
         }

         if (this.data3 == -1) {
            ++this.data2;
            this.data3 = 0;
         }

         try {
            this.startStandardTest();
         } catch (Exception var5) {
            MyLog.temporaryTracking("遇到了错误");
            this.sendErrorBroadCast("Error：" + var5.toString() + "\n引起错误的数据：{" + Integer.toHexString(this.pack & 255) + "," + Integer.toHexString(this.data19 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data17 & 255) + "," + Integer.toHexString(this.data16 & 255) + "," + Integer.toHexString(this.data15 & 255) + "," + Integer.toHexString(this.data14 & 255) + "," + Integer.toHexString(this.data13 & 255) + "," + Integer.toHexString(this.data12 & 255) + "," + Integer.toHexString(this.data11 & 255) + "," + Integer.toHexString(this.data10 & 255) + "," + Integer.toHexString(this.data9 & 255) + "," + Integer.toHexString(this.data8 & 255) + "," + Integer.toHexString(this.data7 & 255) + "," + Integer.toHexString(this.data6 & 255) + "," + Integer.toHexString(this.data5 & 255) + "," + Integer.toHexString(this.data4 & 255) + "," + Integer.toHexString(this.data3 & 255) + "," + Integer.toHexString(this.data2 & 255) + ",}");
            this.pauseTest();
         }

         int var4 = this.data8;
         if (var4 == -2) {
            this.sendSuccessBroadCast();
            return;
         }

         var3 = this.data19 + 1;
         this.data19 = var3;
         if (var3 == 255) {
            this.methodBody(new byte[]{85, (byte)var3, (byte)this.data18, (byte)this.data17, (byte)this.data16, (byte)this.data15, (byte)this.data14, (byte)this.data13, (byte)this.data12, (byte)this.data11, (byte)this.data10, (byte)this.data9, (byte)var4, (byte)this.data7, (byte)this.data6, (byte)this.data5, (byte)this.data4, (byte)this.data3, (byte)this.data2});
         }
      }

   }

   private void sendErrorBroadCast(String var1) {
      MyLog.temporaryTracking("发送ERROR广播");

      try {
         Intent var2 = new Intent();
         var2.setPackage("com.hzbhd.canbus");
         var2.setAction("com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast");
         var2.putExtra("ERROR_DATA", var1);
         this.mContext.sendBroadcast(var2);
      } catch (Exception var3) {
         MyLog.temporaryTracking("广播错误：" + var3.toString());
      }

   }

   private void sendSuccessBroadCast() {
      MyLog.temporaryTracking("发送SUCCESS广播");

      try {
         Intent var1 = new Intent();
         var1.setPackage("com.hzbhd.canbus");
         var1.setAction("com.hzbhd.canbus.activity.TestCanBusActivity.LocatiopnBroadcast_SUCCESS");
         var1.putExtra("ERROR_DATA", "errorData");
         this.mContext.sendBroadcast(var1);
      } catch (Exception var2) {
         MyLog.temporaryTracking("广播错误：" + var2.toString());
      }

   }

   private void simpleTest() {
      MyLog.temporaryTracking("{" + Integer.toHexString(this.pack & 255) + "," + Integer.toHexString(this.data19 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data18 & 255) + ",}");
      CanbusMsgService var3 = this.canbusMsgService;
      Context var4 = this.mContext;
      byte var1 = (byte)this.data19;
      int var2 = this.data18;
      var3.testCanBusInfo(var4, new byte[]{85, var1, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2, (byte)var2});
   }

   private void startStandardTest() {
      MyLog.temporaryTracking("Analog Data：{" + Integer.toHexString(this.pack & 255) + "," + Integer.toHexString(this.data19 & 255) + "," + Integer.toHexString(this.data18 & 255) + "," + Integer.toHexString(this.data17 & 255) + "," + Integer.toHexString(this.data16 & 255) + "," + Integer.toHexString(this.data15 & 255) + "," + Integer.toHexString(this.data14 & 255) + "," + Integer.toHexString(this.data13 & 255) + "," + Integer.toHexString(this.data12 & 255) + "," + Integer.toHexString(this.data11 & 255) + "," + Integer.toHexString(this.data10 & 255) + "," + Integer.toHexString(this.data9 & 255) + "," + Integer.toHexString(this.data8 & 255) + "," + Integer.toHexString(this.data7 & 255) + "," + Integer.toHexString(this.data6 & 255) + "," + Integer.toHexString(this.data5 & 255) + "," + Integer.toHexString(this.data4 & 255) + "," + Integer.toHexString(this.data3 & 255) + "," + Integer.toHexString(this.data2 & 255) + ",}");
      this.canbusMsgService.testCanBusInfo(this.mContext, new byte[]{85, (byte)this.data19, (byte)this.data18, (byte)this.data17, (byte)this.data16, (byte)this.data15, (byte)this.data14, (byte)this.data13, (byte)this.data12, (byte)this.data11, (byte)this.data10, (byte)this.data9, (byte)this.data8, (byte)this.data7, (byte)this.data6, (byte)this.data5, (byte)this.data4, (byte)this.data3, (byte)this.data2});
   }

   public void continueTest(Context var1) {
      this.mContext = var1;
      this.runTag = 0;
      if (this.canbusMsgService == null) {
         this.canbusMsgService = new CanbusMsgService();
      }

      Thread var2 = new Thread(new Runnable(this) {
         final TestCanBusUtil this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            TestCanBusUtil var1 = this.this$0;
            var1.methodBody(new byte[]{85, (byte)var1.data19, (byte)this.this$0.data18, (byte)this.this$0.data17, (byte)this.this$0.data16, (byte)this.this$0.data15, (byte)this.this$0.data14, (byte)this.this$0.data13, (byte)this.this$0.data12, (byte)this.this$0.data11, (byte)this.this$0.data10, (byte)this.this$0.data9, (byte)this.this$0.data8, (byte)this.this$0.data7, (byte)this.this$0.data6, (byte)this.this$0.data5, (byte)this.this$0.data4, (byte)this.this$0.data3, (byte)this.this$0.data2});
         }
      });
      this.thread = var2;
      var2.start();
   }

   public void openAllTest() {
      for(int var1 = 0; var1 <= 255; ++var1) {
         this.canbusMsgService.testCanBusInfo(this.mContext, new byte[]{85, (byte)var1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1});
      }

   }

   public void pauseTest() {
      this.runTag = 1;
   }

   public void startTest(Context var1) {
      this.mContext = var1;
      this.runTag = 0;
      if (this.canbusMsgService == null) {
         this.canbusMsgService = new CanbusMsgService();
      }

      Thread var2 = new Thread(new Runnable(this) {
         final TestCanBusUtil this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            this.this$0.openAllTest();
            TestCanBusUtil var1 = this.this$0;
            var1.methodBody(new byte[]{(byte)var1.pack, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
         }
      });
      this.thread = var2;
      var2.start();
   }

   public void stopTest() {
      this.runTag = 2;
   }
}
