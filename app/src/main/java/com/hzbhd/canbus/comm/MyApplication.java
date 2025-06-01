package com.hzbhd.canbus.comm;

import android.app.Activity;
import android.app.Application;
import com.hzbhd.canbus.adapter.util.CrashReportUtils;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.ui.util.BaseUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class MyApplication extends Application {
   public static boolean IS_SET;
   private List mActivityList;

   public void addActivity(Activity var1) {
      this.mActivityList.add(var1);
   }

   public void onCreate() {
      super.onCreate();
      BaseUtil.INSTANCE.init(this.getApplicationContext());
      BaseUtil.INSTANCE.startTestMode(0);
      this.mActivityList = new ArrayList();
      BaseUtil.INSTANCE.runBack(new Function0(this) {
         final MyApplication this$0;

         {
            this.this$0 = var1;
         }

         public Unit invoke() {
            CrashReportUtils.init(this.this$0.getApplicationContext());
            if (!Dependency.isStart()) {
               (new Dependency(this.this$0.getApplicationContext())).start();
            }

            return null;
         }
      });
      ScreenConfig.initScreenConfig(this);
   }

   public void removeActivity(Activity var1) {
      this.mActivityList.remove(var1);
   }

   public void removeAllActivity() {
      Iterator var2 = this.mActivityList.iterator();

      while(var2.hasNext()) {
         Activity var1 = (Activity)var2.next();
         if (var1 != null) {
            var1.finish();
         }
      }

   }
}
