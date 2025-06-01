package com.hzbhd.canbus.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.TestCanBusUtil;

public class TestCanBusService extends Service {
   int Tag = 0;
   private MyBinder binder = new MyBinder(this);
   TestCanBusUtil testCanBusUtil;

   public void continueTest() {
      if (this.testCanBusUtil == null) {
         this.testCanBusUtil = new TestCanBusUtil();
      }

      this.testCanBusUtil.continueTest(this);
   }

   public int getTag() {
      return this.Tag;
   }

   public IBinder onBind(Intent var1) {
      MyLog.temporaryTracking("onBind");
      return this.binder;
   }

   public void onCreate() {
      super.onCreate();
      MyLog.temporaryTracking("onCreate");
   }

   public void onDestroy() {
      super.onDestroy();
      if (this.testCanBusUtil == null) {
         this.testCanBusUtil = new TestCanBusUtil();
      }

      this.testCanBusUtil.stopTest();
      MyLog.temporaryTracking("onDestroy");
   }

   public int onStartCommand(Intent var1, int var2, int var3) {
      MyLog.temporaryTracking("onStartCommand");
      if (this.testCanBusUtil == null) {
         this.testCanBusUtil = new TestCanBusUtil();
      }

      this.testCanBusUtil.startTest(this);
      return super.onStartCommand(var1, var2, var3);
   }

   public void pauseTest() {
      if (this.testCanBusUtil == null) {
         this.testCanBusUtil = new TestCanBusUtil();
      }

      this.testCanBusUtil.pauseTest();
   }

   public void setTag(int var1) {
      this.Tag = var1;
   }

   public void stopTest() {
      if (this.testCanBusUtil == null) {
         this.testCanBusUtil = new TestCanBusUtil();
      }

      this.testCanBusUtil.stopTest();
   }

   public class MyBinder extends Binder {
      final TestCanBusService this$0;

      public MyBinder(TestCanBusService var1) {
         this.this$0 = var1;
      }

      public TestCanBusService getService() {
         return this.this$0;
      }
   }
}
