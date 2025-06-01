package com.hzbhd.canbus.adapter.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.commontools.utils.ConfigUtil;
import com.hzbhd.util.LogUtil;
import com.tencent.bugly.crashreport.CrashReport;
import java.util.Date;

public class CrashReportUtils {
   public static final String ACTION_CARSHREPORT = ".crashreport";
   public static final String BUGLY_APPID_BLINK = "77f7e10704";
   public static final String BUGLY_APPID_BLUETOOTH = "f36c398ba5";
   public static final String BUGLY_APPID_CANBUS = "10389eeeba";
   public static final String BUGLY_APPID_DSP = "d23c2be472";
   public static final String BUGLY_APPID_EQ = "f4c3dda65a";
   public static final String BUGLY_APPID_LAUNCHER = "bca81f4f68";
   public static final String BUGLY_APPID_MEDIA = "6add284758";
   public static final String BUGLY_APPID_RADIO = "e0380c76e8";
   public static final String BUGLY_APPID_SETTING = "994f39c7c6";
   public static final String BUGLY_APPID_SWC = "503561c667";
   public static final String BUGLY_APPID_SYSTEMUI = "4115c8cdf7";
   public static final String PACKAGENAME_BLINK = "com.hzbhd.blink";
   public static final String PACKAGENAME_BLUETOOTH = "com.hzbhd.bluetooth";
   public static final String PACKAGENAME_CANBUS = "com.hzbhd.canbus";
   public static final String PACKAGENAME_DSP = "com.hzbhd.dsp";
   public static final String PACKAGENAME_EQ = "com.hzbhd.eq";
   public static final String PACKAGENAME_LAUNCHER = "com.android.launcher3";
   public static final String PACKAGENAME_MEDIA = "com.hzbhd.media";
   public static final String PACKAGENAME_RADIO = "com.hzbhd.radio";
   public static final String PACKAGENAME_SETTING = "com.android.settings";
   public static final String PACKAGENAME_SWC = "com.hzbhd.swc";
   public static final String PACKAGENAME_SYSTEMUI = "com.android.systemui";
   private static final String TAG = "com.hzbhd.canbus.adapter.util.CrashReportUtils";

   public static boolean getCrashReportIsOpen(Context var0, String var1) {
      String var2 = System.getString(var0.getContentResolver(), var1 + ".crashreport");
      if (LogUtil.log5()) {
         LogUtil.d(var1 + "<-----get---->" + var2);
      }

      return TextUtils.isEmpty(var2) ? false : var2.equals("true");
   }

   private static String getMCUVersion() {
      return SystemProperties.get("persist.bhd.mcu.version");
   }

   private static String getOSVersion() {
      return ConfigUtil.getDeviceId() + " _ " + (new Date(Build.TIME)).toGMTString();
   }

   public static void init(Context var0) {
      if (LogUtil.log5()) {
         LogUtil.d("init---------------->" + var0.getPackageName());
      }

      String var2 = var0.getPackageName();
      var2.hashCode();
      switch (var2) {
         case "com.hzbhd.dsp":
            initCrashReport(var0, "d23c2be472");
            break;
         case "com.hzbhd.swc":
            initCrashReport(var0, "503561c667");
            break;
         case "com.hzbhd.bluetooth":
            initCrashReport(var0, "f36c398ba5");
            break;
         case "com.hzbhd.canbus":
            initCrashReport(var0, "10389eeeba");
            break;
         case "com.android.launcher3":
            initCrashReport(var0, "bca81f4f68");
            break;
         case "994f39c7c6":
            initCrashReport(var0, "994f39c7c6");
            break;
         case "com.hzbhd.eq":
            initCrashReport(var0, "f4c3dda65a");
            break;
         case "com.android.systemui":
            initCrashReport(var0, "4115c8cdf7");
            break;
         case "com.hzbhd.blink":
            initCrashReport(var0, "77f7e10704");
            break;
         case "com.hzbhd.media":
            initCrashReport(var0, "6add284758");
            break;
         case "com.hzbhd.radio":
            initCrashReport(var0, "e0380c76e8");
      }

   }

   private static void initCrashReport(Context var0, String var1) {
      String var2 = var0.getPackageName();
      if (getCrashReportIsOpen(var0, var2)) {
         if (LogUtil.log5()) {
            LogUtil.d(var2 + "<---------> is open bugly");
         }

         CrashReport.initCrashReport(var0, var1, false);
         CrashReport.putUserData(var0, "OS版本", getOSVersion());
         CrashReport.putUserData(var0, "MCU版本", getMCUVersion());
      }

   }

   public static void openAll(Context var0) {
      if (LogUtil.log5()) {
         LogUtil.d("--------->openAllBugly");
      }

      setCrashReportIsOpen(var0, "com.android.launcher3", true);
      setCrashReportIsOpen(var0, "com.hzbhd.bluetooth", true);
      setCrashReportIsOpen(var0, "com.hzbhd.canbus", true);
      setCrashReportIsOpen(var0, "com.hzbhd.radio", true);
      setCrashReportIsOpen(var0, "com.hzbhd.media", true);
      setCrashReportIsOpen(var0, "com.hzbhd.dsp", true);
      setCrashReportIsOpen(var0, "com.hzbhd.eq", true);
      setCrashReportIsOpen(var0, "com.android.systemui", true);
      setCrashReportIsOpen(var0, "com.android.settings", true);
      setCrashReportIsOpen(var0, "com.hzbhd.swc", true);
      setCrashReportIsOpen(var0, "com.hzbhd.blink", true);
      ActivityManager var1 = (ActivityManager)var0.getSystemService("activity");
      var1.killBackgroundProcesses("com.android.launcher3");
      var1.killBackgroundProcesses("com.hzbhd.bluetooth");
      var1.killBackgroundProcesses("com.hzbhd.canbus");
      var1.killBackgroundProcesses("com.hzbhd.radio");
      var1.killBackgroundProcesses("com.hzbhd.media");
      var1.killBackgroundProcesses("com.hzbhd.dsp");
      var1.killBackgroundProcesses("com.hzbhd.eq");
      var1.killBackgroundProcesses("com.android.systemui");
      var1.killBackgroundProcesses("com.android.settings");
      var1.killBackgroundProcesses("com.hzbhd.swc");
      var1.killBackgroundProcesses("com.hzbhd.blink");
   }

   public static void openCanBusBugly(Context var0, boolean var1) {
      Log.e(TAG, "开启BUGLY" + var1);
      setCrashReportIsOpen(var0, "com.hzbhd.canbus", var1);
      ((ActivityManager)var0.getSystemService("activity")).killBackgroundProcesses("com.hzbhd.canbus");
   }

   public static void setCrashReportIsOpen(Context var0, String var1, boolean var2) {
      if (LogUtil.log5()) {
         LogUtil.d(var1 + "<-----set---->" + var2);
      }

      System.putString(var0.getContentResolver(), var1 + ".crashreport", String.valueOf(var2));
   }

   public static void testJavaCrash() {
      CrashReport.testJavaCrash();
   }
}
