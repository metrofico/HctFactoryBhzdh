package com.hzbhd.ui.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import com.hzbhd.config.use.UIDefault;
import com.hzbhd.ui.util.test.TestInterface;
import com.hzbhd.util.ContextUtil;
import com.hzbhd.util.HandlerThreadUtilKt;
import com.hzbhd.util.LogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
   d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.J\u000e\u0010/\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.J\u0006\u00100\u001a\u000201J\b\u00102\u001a\u00020\u0004H\u0002J\u000e\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0018J+\u00105\u001a\u0002H6\"\b\b\u0000\u00106*\u00020\u00012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H6082\u0006\u00109\u001a\u00020\u0004¢\u0006\u0002\u0010:J3\u00105\u001a\u0002H6\"\b\b\u0000\u00106*\u00020\u00012\f\u00107\u001a\b\u0012\u0004\u0012\u0002H6082\u0006\u00109\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u0004¢\u0006\u0002\u0010<J\u000e\u0010=\u001a\u0002012\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010>\u001a\u0002012\u0006\u0010?\u001a\u00020\u0004H\u0007J\u0014\u0010@\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010BJ\u001c\u0010C\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010B2\u0006\u0010\"\u001a\u00020#J\u0014\u0010D\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010BJ\u001c\u0010E\u001a\u0002012\f\u0010A\u001a\b\u0012\u0004\u0012\u0002010B2\u0006\u0010\"\u001a\u00020#J\u000e\u0010F\u001a\u0002012\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010G\u001a\u0002012\u0006\u0010H\u001a\u00020IJ\u000e\u0010G\u001a\u0002012\u0006\u0010A\u001a\u00020\u0004J\u000e\u0010J\u001a\u0002012\u0006\u0010?\u001a\u00020\u0018R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0006j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\r\u001a\u0004\b\u001f\u0010 R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010$\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001b\u0010'\u001a\u00020(8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\r\u001a\u0004\b)\u0010*¨\u0006K"},
   d2 = {"Lcom/hzbhd/ui/util/BaseUtil;", "", "()V", "CONFIG_CLASS_NAME", "", "configMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "handlerThread", "Landroid/os/HandlerThread;", "getHandlerThread", "()Landroid/os/HandlerThread;", "handlerThread$delegate", "matchLayoutParams", "Landroid/view/ViewGroup$LayoutParams;", "getMatchLayoutParams", "()Landroid/view/ViewGroup$LayoutParams;", "screenHeight", "", "getScreenHeight", "()I", "screenWidth", "getScreenWidth", "share", "Landroid/content/SharedPreferences;", "getShare", "()Landroid/content/SharedPreferences;", "share$delegate", "time", "", "topActivityName", "getTopActivityName", "()Ljava/lang/String;", "uiIdObserver", "Lcom/hzbhd/ui/util/UiIdObserver;", "getUiIdObserver", "()Lcom/hzbhd/ui/util/UiIdObserver;", "uiIdObserver$delegate", "changeFloatToHeight", "size", "", "changeFloatToWidth", "clearUIConfig", "", "createTag", "getString", "id", "getUIConfig", "T", "configClass", "Ljava/lang/Class;", "uiId", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "defaultClass", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "init", "logTime", "type", "runBack", "action", "Lkotlin/Function0;", "runBackDelay", "runUi", "runUiDelay", "scaleUI", "startActivity", "componentName", "Landroid/content/ComponentName;", "startTestMode", "java-base_release"},
   k = 1,
   mv = {1, 7, 1},
   xi = 48
)
public final class BaseUtil {
   private static final String CONFIG_CLASS_NAME = "MyConfig";
   public static final BaseUtil INSTANCE = new BaseUtil();
   private static HashMap configMap;
   private static final Lazy context$delegate;
   private static final Lazy handlerThread$delegate;
   private static final Lazy share$delegate;
   private static long time;
   private static final Lazy uiIdObserver$delegate;

   static {
      share$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      context$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      uiIdObserver$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      configMap = new HashMap();
      handlerThread$delegate = LazyKt.lazy((Function0)null.INSTANCE);
   }

   private BaseUtil() {
   }

   private final String createTag() {
      StackTraceElement[] var3 = (new Throwable()).getStackTrace();
      String var2 = var3[2].getFileName();
      int var1 = var3[2].getLineNumber();
      StringBuffer var4 = new StringBuffer();
      var4.append("(").append(var2).append(":").append(var1).append(")");
      var2 = var4.toString();
      Intrinsics.checkNotNullExpressionValue(var2, "buffer.toString()");
      return var2;
   }

   @JvmStatic
   public static final void logTime(String var0) {
      Intrinsics.checkNotNullParameter(var0, "type");
      long var1 = System.currentTimeMillis();
      if (LogUtil.log5()) {
         Log.d(INSTANCE.createTag(), "log time :" + var0 + " = " + (var1 - time));
      }

      time = var1;
   }

   public final int changeFloatToHeight(float var1) {
      return (int)(var1 * (float)this.getScreenHeight());
   }

   public final int changeFloatToWidth(float var1) {
      return (int)(var1 * (float)this.getScreenWidth());
   }

   public final void clearUIConfig() {
      configMap.clear();
   }

   public final Context getContext() {
      return (Context)context$delegate.getValue();
   }

   public final HandlerThread getHandlerThread() {
      return (HandlerThread)handlerThread$delegate.getValue();
   }

   public final ViewGroup.LayoutParams getMatchLayoutParams() {
      return new ViewGroup.LayoutParams(-1, -1);
   }

   public final int getScreenHeight() {
      return ContextUtil.INSTANCE.getScreenSize().y;
   }

   public final int getScreenWidth() {
      return ContextUtil.INSTANCE.getScreenSize().x;
   }

   public final SharedPreferences getShare() {
      return (SharedPreferences)share$delegate.getValue();
   }

   public final String getString(int var1) {
      String var2 = this.getContext().getResources().getString(var1);
      Intrinsics.checkNotNullExpressionValue(var2, "context.resources.getString(id)");
      return var2;
   }

   public final String getTopActivityName() {
      Object var1 = this.getContext().getSystemService("activity");
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type android.app.ActivityManager");
      List var2 = ((ActivityManager)var1).getRunningTasks(1);
      if (var2 != null && var2.size() > 0) {
         ComponentName var3 = ((ActivityManager.RunningTaskInfo)var2.get(0)).topActivity;
         String var4;
         if (var3 != null) {
            var4 = var3.getClassName();
         } else {
            var4 = null;
         }

         return var4;
      } else {
         return "";
      }
   }

   public final Object getUIConfig(Class var1, String var2) {
      Intrinsics.checkNotNullParameter(var1, "configClass");
      Intrinsics.checkNotNullParameter(var2, "uiId");
      if (!configMap.containsKey(var1.getName())) {
         var2 = "ui_" + var2;

         StringBuilder var3;
         try {
            var3 = new StringBuilder();
            StringBuilder var4 = var3.append(var1.getPackage().getName()).append('.');
            String var10 = var2.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(var10, "this as java.lang.String).toLowerCase()");
            String var5 = var4.append(var10).append('.').toString();
            Map var11 = (Map)configMap;
            var10 = var1.getName();
            Intrinsics.checkNotNullExpressionValue(var10, "configClass.name");
            StringBuilder var6 = new StringBuilder();
            Object var14 = Class.forName(var6.append(var5).append("MyConfig").toString()).newInstance();
            Intrinsics.checkNotNullExpressionValue(var14, "forName(configPackage + …CLASS_NAME).newInstance()");
            var11.put(var10, var14);
         } catch (Exception var8) {
            var3 = (new StringBuilder()).append(var1.getPackage().getName()).append('.');
            var2 = var2.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String).toLowerCase()");
            var2 = var3.append(var2).append('.').toString();
            if (LogUtil.log5()) {
               LogUtil.d("getConfig not found: " + var2);
            }
         }

         try {
            if (configMap.get(var1.getName()) == null) {
               Map var12 = (Map)configMap;
               var2 = var1.getName();
               Intrinsics.checkNotNullExpressionValue(var2, "configClass.name");
               Object var13 = var1.newInstance();
               Intrinsics.checkNotNullExpressionValue(var13, "configClass.newInstance()");
               var12.put(var2, var13);
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      Object var9 = configMap.get(var1.getName());
      Intrinsics.checkNotNull(var9, "null cannot be cast to non-null type T of com.hzbhd.ui.util.BaseUtil.getUIConfig");
      return var9;
   }

   public final Object getUIConfig(Class var1, String var2, String var3) {
      Intrinsics.checkNotNullParameter(var1, "configClass");
      Intrinsics.checkNotNullParameter(var2, "uiId");
      Intrinsics.checkNotNullParameter(var3, "defaultClass");
      if (!configMap.containsKey(var1.getName())) {
         var2 = "ui_" + var2;

         StringBuilder var4;
         String var5;
         String var12;
         try {
            var4 = new StringBuilder();
            StringBuilder var14 = var4.append("com.hzbhd.ui.config.");
            var12 = var2.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(var12, "this as java.lang.String).toLowerCase()");
            String var6 = var14.append(var12).append('.').append(var3).toString();
            Map var15 = (Map)configMap;
            var12 = var1.getName();
            Intrinsics.checkNotNullExpressionValue(var12, "configClass.name");
            Object var18 = Class.forName(var6).newInstance();
            Intrinsics.checkNotNullExpressionValue(var18, "forName(configPackage).newInstance()");
            var15.put(var12, var18);
         } catch (Exception var9) {
            var4 = (new StringBuilder()).append("com.hzbhd.ui.config.");
            var5 = var2.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(var5, "this as java.lang.String).toLowerCase()");
            var3 = var4.append(var5).append('.').append(var3).toString();
            if (LogUtil.log5()) {
               LogUtil.e("getConfig not found defaultClass: " + var3);
            }
         }

         if (configMap.get(var1.getName()) == null) {
            StringBuilder var11;
            try {
               var11 = new StringBuilder();
               var11 = var11.append("com.hzbhd.ui.config.");
               var12 = var2.toLowerCase();
               Intrinsics.checkNotNullExpressionValue(var12, "this as java.lang.String).toLowerCase()");
               var5 = var11.append(var12).append(".MyConfig").toString();
               Map var16 = (Map)configMap;
               var3 = var1.getName();
               Intrinsics.checkNotNullExpressionValue(var3, "configClass.name");
               Object var19 = Class.forName(var5).newInstance();
               Intrinsics.checkNotNullExpressionValue(var19, "forName(configPackage).newInstance()");
               var16.put(var3, var19);
            } catch (Exception var8) {
               var11 = (new StringBuilder()).append("com.hzbhd.ui.config.");
               var2 = var2.toLowerCase();
               Intrinsics.checkNotNullExpressionValue(var2, "this as java.lang.String).toLowerCase()");
               var2 = var11.append(var2).append(".MyConfig").toString();
               if (LogUtil.log5()) {
                  LogUtil.e("getConfig not found myconfig: " + var2);
               }
            }
         }

         try {
            if (configMap.get(var1.getName()) == null) {
               Map var13 = (Map)configMap;
               var2 = var1.getName();
               Intrinsics.checkNotNullExpressionValue(var2, "configClass.name");
               Object var17 = var1.newInstance();
               Intrinsics.checkNotNullExpressionValue(var17, "configClass.newInstance()");
               var13.put(var2, var17);
            }
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      Object var10 = configMap.get(var1.getName());
      Intrinsics.checkNotNull(var10, "null cannot be cast to non-null type T of com.hzbhd.ui.util.BaseUtil.getUIConfig");
      return var10;
   }

   public final UiIdObserver getUiIdObserver() {
      return (UiIdObserver)uiIdObserver$delegate.getValue();
   }

   public final void init(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      ContextUtil.INSTANCE.init(var1);
      this.scaleUI(var1);
   }

   public final void runBack(Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "action");
      HandlerThreadUtilKt.runBack(var1);
   }

   public final void runBackDelay(Function0 var1, long var2) {
      Intrinsics.checkNotNullParameter(var1, "action");
      HandlerThreadUtilKt.runBackDelay(var2, var1);
   }

   public final void runUi(Function0 var1) {
      Intrinsics.checkNotNullParameter(var1, "action");
      HandlerThreadUtilKt.runUi(var1);
   }

   public final void runUiDelay(Function0 var1, long var2) {
      Intrinsics.checkNotNullParameter(var1, "action");
      HandlerThreadUtilKt.runUiDelay(var2, var1);
   }

   public final void scaleUI(Context var1) {
      Intrinsics.checkNotNullParameter(var1, "context");
      DisplayMetrics var4 = var1.getResources().getDisplayMetrics();
      int var3;
      if (ContextUtil.INSTANCE.getScreenSize().x > ContextUtil.INSTANCE.getScreenSize().y) {
         var3 = ContextUtil.INSTANCE.getScreenSize().y;
      } else {
         var3 = ContextUtil.INSTANCE.getScreenSize().x;
      }

      if (var3 != 600 && UIDefault.INSTANCE.getUiScale() != 0) {
         if (LogUtil.log5()) {
            LogUtil.d("scaleUI: " + UIDefault.INSTANCE.getUiScale() + " to " + var3);
         }

         float var2 = (float)var3;
         var4.density = var2 / (float)UIDefault.INSTANCE.getUiScale();
         var4.scaledDensity = var2 / (float)UIDefault.INSTANCE.getUiScale();
         var4.densityDpi = 160;
      } else {
         var4.density = 1.0F;
         var4.scaledDensity = 1.0F;
         var4.densityDpi = 160;
      }

   }

   public final void startActivity(ComponentName var1) {
      Intrinsics.checkNotNullParameter(var1, "componentName");
      Intent var2 = new Intent();
      var2.setComponent(var1);
      var2.addFlags(268435456);
      this.getContext().startActivity(var2);
   }

   public final void startActivity(String var1) {
      Intrinsics.checkNotNullParameter(var1, "action");
      Intent var2 = new Intent();
      var2.setAction(var1);
      var2.addFlags(268435456);
      this.getContext().startActivity(var2);
   }

   public final void startTestMode(int var1) {
      try {
         Object var2 = Class.forName("com.hzbhd.ui.test.MyTest").newInstance();
         Intrinsics.checkNotNull(var2, "null cannot be cast to non-null type com.hzbhd.ui.util.test.TestInterface");
         ((TestInterface)var2).startTest(var1);
      } catch (Exception var3) {
      }

   }
}
