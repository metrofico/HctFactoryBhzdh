package com.tencent.bugly.crashreport;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ac;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.tencent.bugly.proguard.aq;
import com.tencent.bugly.proguard.at;
import com.tencent.bugly.proguard.au;
import com.tencent.bugly.proguard.bc;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.s;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CrashReport {
   private static Context a;

   public static void closeBugly() {
      if (!p.a) {
         Log.w(al.b, "Can not close bugly because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.w(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else if (a != null) {
         aq var0 = aq.a();
         if (var0 != null) {
            var0.b(a);
         }

         closeCrashReport();
         s.a(a);
         ak var1 = ak.a();
         if (var1 != null) {
            var1.b();
         }

      }
   }

   public static void closeCrashReport() {
      if (!p.a) {
         Log.w(al.b, "Can not close crash report because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.w(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         at.a().c();
      }
   }

   public static void closeNativeReport() {
      if (!p.a) {
         Log.w(al.b, "Can not close native report because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         at.a().d();
      }
   }

   public static void enableBugly(boolean var0) {
      p.a = var0;
   }

   public static void enableObtainId(Context var0, boolean var1) {
      setCollectPrivacyInfo(var0, var1);
   }

   public static Set getAllUserDataKeys(Context var0) {
      if (!p.a) {
         Log.w(al.b, "Can not get all keys of user data because bugly is disable.");
         return new HashSet();
      } else if (var0 == null) {
         Log.e(al.b, "getAllUserDataKeys args context should not be null");
         return new HashSet();
      } else {
         return aa.a(var0).w();
      }
   }

   public static String getAppChannel() {
      if (!p.a) {
         Log.w(al.b, "Can not get App channel because bugly is disable.");
         return "unknown";
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
         return "unknown";
      } else {
         return aa.a(a).s;
      }
   }

   public static String getAppID() {
      if (!p.a) {
         Log.w(al.b, "Can not get App ID because bugly is disable.");
         return "unknown";
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
         return "unknown";
      } else {
         return aa.a(a).e();
      }
   }

   public static String getAppVer() {
      if (!p.a) {
         Log.w(al.b, "Can not get app version because bugly is disable.");
         return "unknown";
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
         return "unknown";
      } else {
         return aa.a(a).o;
      }
   }

   public static String getBuglyVersion(Context var0) {
      if (var0 == null) {
         al.d("Please call with context.");
         return "unknown";
      } else {
         return aa.a(var0).h;
      }
   }

   public static Context getContext() {
      return a;
   }

   public static String getDeviceID(Context var0) {
      return aa.a(var0).g();
   }

   public static Proxy getHttpProxy() {
      return an.a;
   }

   public static Map getSdkExtraData() {
      if (!p.a) {
         Log.w(al.b, "Can not get SDK extra data because bugly is disable.");
         return new HashMap();
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
         return null;
      } else {
         return aa.a(a).K;
      }
   }

   public static Map getSdkExtraData(Context var0) {
      if (!p.a) {
         Log.w(al.b, "Can not get SDK extra data because bugly is disable.");
         return new HashMap();
      } else if (var0 == null) {
         al.d("Context should not be null.");
         return null;
      } else {
         return aa.a(var0).K;
      }
   }

   public static String getUserData(Context var0, String var1) {
      if (!p.a) {
         Log.w(al.b, "Can not get user data because bugly is disable.");
         return "unknown";
      } else if (var0 == null) {
         Log.e(al.b, "getUserDataValue args context should not be null");
         return "unknown";
      } else {
         return ap.b(var1) ? null : aa.a(var0).g(var1);
      }
   }

   public static int getUserDatasSize(Context var0) {
      if (!p.a) {
         Log.w(al.b, "Can not get size of user data because bugly is disable.");
         return -1;
      } else if (var0 == null) {
         Log.e(al.b, "getUserDatasSize args context should not be null");
         return -1;
      } else {
         return aa.a(var0).v();
      }
   }

   public static String getUserId() {
      if (!p.a) {
         Log.w(al.b, "Can not get user ID because bugly is disable.");
         return "unknown";
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
         return "unknown";
      } else {
         return aa.a(a).f();
      }
   }

   public static int getUserSceneTagId(Context var0) {
      if (!p.a) {
         Log.w(al.b, "Can not get user scene tag because bugly is disable.");
         return -1;
      } else if (var0 == null) {
         Log.e(al.b, "getUserSceneTagId args context should not be null");
         return -1;
      } else {
         return aa.a(var0).z();
      }
   }

   public static void initCrashReport(Context var0) {
      if (var0 != null) {
         a = var0;
         p.a((o)CrashModule.getInstance());
         p.a(var0);
      }
   }

   public static void initCrashReport(Context var0, UserStrategy var1) {
      if (var0 != null) {
         a = var0;
         p.a((o)CrashModule.getInstance());
         p.a(var0, var1);
      }
   }

   public static void initCrashReport(Context var0, String var1, boolean var2) {
      initCrashReport(var0, var1, var2, (UserStrategy)null);
   }

   public static void initCrashReport(Context var0, String var1, boolean var2, UserStrategy var3) {
      if (var0 != null) {
         a = var0;
         p.a((o)CrashModule.getInstance());
         p.a(var0, var1, var2, var3);
      }
   }

   public static boolean isLastSessionCrash() {
      if (!p.a) {
         Log.w(al.b, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
         return false;
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
         return false;
      } else {
         at var0 = at.a();
         Boolean var1 = var0.A;
         if (var1 != null) {
            return var1;
         } else {
            String var5 = aa.b().d;
            List var3 = w.a().a(1);
            ArrayList var2 = new ArrayList();
            if (var3 != null && var3.size() > 0) {
               Iterator var6 = var3.iterator();

               while(var6.hasNext()) {
                  y var4 = (y)var6.next();
                  if (var5.equals(var4.c)) {
                     var0.A = Boolean.TRUE;
                     var2.add(var4);
                  }
               }

               if (var2.size() > 0) {
                  w.a().a((List)var2);
               }

               return true;
            } else {
               var0.A = Boolean.FALSE;
               return false;
            }
         }
      }
   }

   public static void postCatchedException(Throwable var0) {
      postCatchedException(var0, Thread.currentThread());
   }

   public static void postCatchedException(Throwable var0, Thread var1) {
      postCatchedException(var0, var1, false);
   }

   public static void postCatchedException(Throwable var0, Thread var1, boolean var2) {
      // $FF: Couldn't be decompiled
   }

   public static void postException(int var0, String var1, String var2, String var3, Map var4) {
      postException(Thread.currentThread(), var0, var1, var2, var3, var4);
   }

   public static void postException(Thread var0, int var1, String var2, String var3, String var4, Map var5) {
      if (!p.a) {
         Log.w(al.b, "Can not post crash caught because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         au.a(var0, var1, var2, var3, var4, var5);
      }
   }

   private static void putSdkData(Context var0, String var1, String var2) {
      if (var0 != null && !ap.b(var1) && !ap.b(var2)) {
         String var3 = var1.replace("[a-zA-Z[0-9]]+", "");
         var1 = var3;
         if (var3.length() > 100) {
            Log.w(al.b, String.format("putSdkData key length over limit %d, will be cutted.", 50));
            var1 = var3.substring(0, 50);
         }

         var3 = var2;
         if (var2.length() > 500) {
            Log.w(al.b, String.format("putSdkData value length over limit %d, will be cutted!", 200));
            var3 = var2.substring(0, 200);
         }

         aa.a(var0).b(var1, var3);
         al.b(String.format("[param] putSdkData data: %s - %s", var1, var3));
      }

   }

   public static void putUserData(Context var0, String var1, String var2) {
      if (!p.a) {
         Log.w(al.b, "Can not put user data because bugly is disable.");
      } else if (var0 == null) {
         Log.w(al.b, "putUserData args context should not be null");
      } else if (var1 == null) {
         (new StringBuilder()).append(var1);
         al.d("putUserData args key should not be null or empty");
      } else if (var2 == null) {
         (new StringBuilder()).append(var2);
         al.d("putUserData args value should not be null");
      } else {
         String var3 = var2;
         if (var2.length() > 200) {
            al.d("user data value length over limit %d, it will be cutted!", 200);
            var3 = var2.substring(0, 200);
         }

         aa var5 = aa.a(var0);
         if (var5.w().contains(var1)) {
            NativeCrashHandler var6 = NativeCrashHandler.getInstance();
            if (var6 != null) {
               var6.putKeyValueToNative(var1, var3);
            }

            aa.a(var0).a(var1, var3);
            al.c("replace KV %s %s", var1, var3);
         } else if (var5.v() >= 50) {
            al.d("user data size is over limit %d, it will be cutted!", 50);
         } else {
            var2 = var1;
            if (var1.length() > 50) {
               al.d("user data key length over limit %d , will drop this new key %s", 50, var1);
               var2 = var1.substring(0, 50);
            }

            NativeCrashHandler var4 = NativeCrashHandler.getInstance();
            if (var4 != null) {
               var4.putKeyValueToNative(var2, var3);
            }

            aa.a(var0).a(var2, var3);
            al.b("[param] set user data: %s - %s", var2, var3);
         }
      }
   }

   public static String removeUserData(Context var0, String var1) {
      if (!p.a) {
         Log.w(al.b, "Can not remove user data because bugly is disable.");
         return "unknown";
      } else if (var0 == null) {
         Log.e(al.b, "removeUserData args context should not be null");
         return "unknown";
      } else if (ap.b(var1)) {
         return null;
      } else {
         al.b("[param] remove user data: %s", var1);
         return aa.a(var0).f(var1);
      }
   }

   public static void setAllThreadStackEnable(Context var0, boolean var1, boolean var2) {
      aa var3 = aa.a(var0);
      var3.Q = var1;
      var3.R = var2;
   }

   public static void setAppChannel(Context var0, String var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set App channel because Bugly is disable.");
      } else if (var0 == null) {
         Log.w(al.b, "setAppChannel args context should not be null");
      } else if (var1 == null) {
         Log.w(al.b, "App channel is null, will not set");
      } else {
         aa.a(var0).s = var1;
         NativeCrashHandler var2 = NativeCrashHandler.getInstance();
         if (var2 != null) {
            var2.setNativeAppChannel(var1);
         }

      }
   }

   public static void setAppPackage(Context var0, String var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set App package because bugly is disable.");
      } else if (var0 == null) {
         Log.w(al.b, "setAppPackage args context should not be null");
      } else if (var1 == null) {
         Log.w(al.b, "App package is null, will not set");
      } else {
         aa.a(var0).c = var1;
         NativeCrashHandler var2 = NativeCrashHandler.getInstance();
         if (var2 != null) {
            var2.setNativeAppPackage(var1);
         }

      }
   }

   public static void setAppVersion(Context var0, String var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set App version because bugly is disable.");
      } else if (var0 == null) {
         Log.w(al.b, "setAppVersion args context should not be null");
      } else if (var1 == null) {
         Log.w(al.b, "App version is null, will not set");
      } else {
         aa.a(var0).o = var1;
         NativeCrashHandler var2 = NativeCrashHandler.getInstance();
         if (var2 != null) {
            var2.setNativeAppVersion(var1);
         }

      }
   }

   public static void setBuglyDbName(String var0) {
      if (!p.a) {
         Log.w(al.b, "Can not set DB name because bugly is disable.");
      } else {
         Log.i(al.b, "Set Bugly DB name: ".concat(String.valueOf(var0)));
         x.a = var0;
      }
   }

   public static void setCollectPrivacyInfo(Context var0, boolean var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set collect privacy info enable because bugly is disable.");
      } else if (var0 == null) {
         Log.w(al.b, "setCollectPrivacyInfo args context should not be null");
      } else {
         Log.i(al.b, "setCollectPrivacyInfo: ".concat(String.valueOf(var1)));
         aa.a(var0).n = var1;
      }
   }

   public static void setContext(Context var0) {
      a = var0;
   }

   public static void setCrashFilter(String var0) {
      if (!p.a) {
         Log.w(al.b, "Can not set App package because bugly is disable.");
      } else {
         Log.i(al.b, "Set crash stack filter: ".concat(String.valueOf(var0)));
         at.q = var0;
      }
   }

   public static void setCrashRegularFilter(String var0) {
      if (!p.a) {
         Log.w(al.b, "Can not set App package because bugly is disable.");
      } else {
         Log.i(al.b, "Set crash stack filter: ".concat(String.valueOf(var0)));
         at.r = var0;
      }
   }

   public static void setDeviceId(Context var0, String var1) {
      if (var0 != null && !TextUtils.isEmpty(var1)) {
         aa.a(var0).a(var1);
      }

   }

   public static void setDeviceModel(Context var0, String var1) {
      if (var0 != null && !TextUtils.isEmpty(var1)) {
         aa.a(var0).b(var1);
      }

   }

   public static void setDumpFilePath(Context var0, String var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set App version because bugly is disable.");
      } else if (var0 == null) {
         Log.w(al.b, "setTombPath args context should not be null");
      } else if (var1 == null) {
         Log.w(al.b, "tombstone path is null, will not set");
      } else {
         Log.i(al.b, "user set tombstone path: ".concat(String.valueOf(var1)));
         NativeCrashHandler.setDumpFilePath(var1);
      }
   }

   public static void setHandleNativeCrashInJava(boolean var0) {
      if (!p.a) {
         Log.w(al.b, "Can not set App package because bugly is disable.");
      } else {
         Log.i(al.b, "Should handle native crash in Java profile after handled in native profile: ".concat(String.valueOf(var0)));
         NativeCrashHandler.setShouldHandleInJava(var0);
      }
   }

   public static void setHttpProxy(String var0, int var1) {
      if (TextUtils.isEmpty(var0)) {
         an.a = null;
      } else {
         an.a = new Proxy(Type.HTTP, new InetSocketAddress(var0, var1));
      }
   }

   public static void setHttpProxy(InetAddress var0, int var1) {
      if (var0 == null) {
         an.a = null;
      } else {
         an.a = new Proxy(Type.HTTP, new InetSocketAddress(var0, var1));
      }
   }

   @Deprecated
   public static void setIsAppForeground(Context var0, boolean var1) {
      al.a("App fore and back status are no longer supported");
   }

   public static void setIsDevelopmentDevice(Context var0, boolean var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set 'isDevelopmentDevice' because bugly is disable.");
      } else if (var0 == null) {
         al.d("Context should not be null.");
      } else {
         if (var1) {
            al.c("This is a development device.");
         } else {
            al.c("This is not a development device.");
         }

         aa.a(var0).I = var1;
      }
   }

   public static boolean setJavascriptMonitor(WebView var0, boolean var1) {
      return setJavascriptMonitor(var0, var1, false);
   }

   public static boolean setJavascriptMonitor(WebView var0, boolean var1, boolean var2) {
      if (var0 == null) {
         Log.w(al.b, "WebView is null.");
         return false;
      } else {
         var0.getSettings().setSavePassword(false);
         var0.getSettings().setAllowFileAccess(false);
         return setJavascriptMonitor(new a(var0) {
            final WebView a;

            {
               this.a = var1;
            }

            public final String a() {
               return this.a.getUrl();
            }

            public final void a(H5JavaScriptInterface var1, String var2) {
               this.a.addJavascriptInterface(var1, var2);
            }

            public final void a(String var1) {
               this.a.loadUrl(var1);
            }

            public final void b() {
               WebSettings var1 = this.a.getSettings();
               if (!var1.getJavaScriptEnabled()) {
                  var1.setJavaScriptEnabled(true);
               }

            }

            public final CharSequence c() {
               return this.a.getContentDescription();
            }
         }, var1, var2);
      }
   }

   public static boolean setJavascriptMonitor(a var0, boolean var1) {
      return setJavascriptMonitor(var0, var1, false);
   }

   public static boolean setJavascriptMonitor(a var0, boolean var1, boolean var2) {
      if (var0 == null) {
         Log.w(al.b, "WebViewInterface is null.");
         return false;
      } else if (!CrashModule.getInstance().hasInitialized()) {
         al.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!");
         return false;
      } else {
         al.a("Set Javascript exception monitor of webview.");
         if (!p.a) {
            Log.w(al.b, "Can not set JavaScript monitor because bugly is disable.");
            return false;
         } else {
            al.c("URL of webview is %s", var0.a());
            if (!var2 && VERSION.SDK_INT < 19) {
               al.e("This interface is only available for Android 4.4 or later.");
               return false;
            } else {
               al.a("Enable the javascript needed by webview monitor.");
               var0.b();
               H5JavaScriptInterface var3 = H5JavaScriptInterface.getInstance(var0);
               if (var3 != null) {
                  al.a("Add a secure javascript interface to the webview.");
                  var0.a(var3, "exceptionUploader");
               }

               if (var1) {
                  al.a("Inject bugly.js(v%s) to the webview.", bc.b());
                  String var4 = bc.a();
                  if (var4 == null) {
                     al.e("Failed to inject Bugly.js.", bc.b());
                     return false;
                  }

                  var0.a("javascript:".concat(String.valueOf(var4)));
               }

               return true;
            }
         }
      }
   }

   public static void setSdkExtraData(Context param0, String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   public static void setServerUrl(String var0) {
      if (!ap.b(var0) && ap.d(var0)) {
         ac.a(var0);
         StrategyBean.a = var0;
         StrategyBean.b = var0;
      } else {
         Log.i(al.b, "URL is invalid.");
      }
   }

   public static void setSessionIntervalMills(long var0) {
      if (!p.a) {
         Log.w(al.b, "Can not set 'SessionIntervalMills' because bugly is disable.");
      } else {
         s.a(var0);
      }
   }

   public static void setUserId(Context var0, String var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set user ID because bugly is disable.");
      } else if (var0 == null) {
         Log.e(al.b, "Context should not be null when bugly has not been initialed!");
      } else if (TextUtils.isEmpty(var1)) {
         al.d("userId should not be null");
      } else {
         String var2 = var1;
         if (var1.length() > 100) {
            var2 = var1.substring(0, 100);
            al.d("userId %s length is over limit %d substring to %s", var1, 100, var2);
         }

         if (!var2.equals(aa.a(var0).f())) {
            aa var3 = aa.a(var0);
            Object var12 = var3.V;
            synchronized(var12){}
            String var10;
            if (var2 == null) {
               var10 = "10000";
            } else {
               var10 = var2;
            }

            try {
               var3.l = String.valueOf(var10);
            } catch (Throwable var9) {
               Throwable var10000 = var9;
               boolean var10001 = false;

               while(true) {
                  Throwable var11 = var10000;

                  try {
                     throw var11;
                  } catch (Throwable var8) {
                     var10000 = var8;
                     var10001 = false;
                     continue;
                  }
               }
            }

            al.b("[user] set userId : %s", var2);
            NativeCrashHandler var13 = NativeCrashHandler.getInstance();
            if (var13 != null) {
               var13.setNativeUserId(var2);
            }

            if (CrashModule.getInstance().hasInitialized()) {
               s.a();
            }

         }
      }
   }

   public static void setUserId(String var0) {
      if (!p.a) {
         Log.w(al.b, "Can not set user ID because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         setUserId(a, var0);
      }
   }

   public static void setUserSceneTag(Context var0, int var1) {
      if (!p.a) {
         Log.w(al.b, "Can not set tag caught because bugly is disable.");
      } else if (var0 == null) {
         Log.e(al.b, "setTag args context should not be null");
      } else {
         if (var1 <= 0) {
            al.d("setTag args tagId should > 0");
         }

         aa var3 = aa.a(var0);
         Object var24 = var3.U;
         synchronized(var24){}

         label249: {
            Throwable var10000;
            boolean var10001;
            label250: {
               int var2;
               try {
                  var2 = var3.w;
               } catch (Throwable var23) {
                  var10000 = var23;
                  var10001 = false;
                  break label250;
               }

               if (var2 != var1) {
                  try {
                     var3.w = var1;
                     al.a("user scene tag %d changed to tag %d", var2, var3.w);
                  } catch (Throwable var22) {
                     var10000 = var22;
                     var10001 = false;
                     break label250;
                  }
               }

               label232:
               try {
                  break label249;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label232;
               }
            }

            while(true) {
               Throwable var25 = var10000;

               try {
                  throw var25;
               } catch (Throwable var20) {
                  var10000 = var20;
                  var10001 = false;
                  continue;
               }
            }
         }

         al.b("[param] set user scene tag: %d", var1);
      }
   }

   public static void startCrashReport() {
      if (!p.a) {
         Log.w(al.b, "Can not start crash report because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.w(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         at.a().b();
      }
   }

   public static void testANRCrash() {
      if (!p.a) {
         Log.w(al.b, "Can not test ANR crash because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         al.a("start to create a anr crash for test!");
         at.a().h();
      }
   }

   public static void testJavaCrash() {
      if (!p.a) {
         Log.w(al.b, "Can not test Java crash because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         aa var1 = aa.b();
         if (var1 != null) {
            int var0 = var1.x;
            if (var0 != 24096) {
               var1.x = 24096;
               al.a("server scene tag %d changed to tag %d", var0, var1.x);
            }
         }

         throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
      }
   }

   public static void testNativeCrash() {
      testNativeCrash(true, true, false);
   }

   public static void testNativeCrash(boolean var0, boolean var1, boolean var2) {
      if (!p.a) {
         Log.w(al.b, "Can not test native crash because bugly is disable.");
      } else if (!CrashModule.getInstance().hasInitialized()) {
         Log.e(al.b, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
      } else {
         al.a("start to create a native crash for test!");
         at.a().a(var0, var1, var2);
      }
   }

   public static void uploadUserInfo() {
      if (!p.a) {
         Log.w(al.b, "Can not upload user info because bugly is disable.");
      } else if (s.b == null) {
         Log.w(al.b, "Can not upload user info because bugly is not init.");
      } else {
         s.b.b();
      }
   }

   public static class CrashHandleCallback extends BuglyStrategy.a {
   }

   public static class UserStrategy extends BuglyStrategy {
      CrashHandleCallback c;

      public UserStrategy(Context var1) {
      }

      public int getCallBackType() {
         synchronized(this){}

         int var1;
         try {
            var1 = this.a;
         } finally {
            ;
         }

         return var1;
      }

      public boolean getCloseErrorCallback() {
         synchronized(this){}

         boolean var1;
         try {
            var1 = this.b;
         } finally {
            ;
         }

         return var1;
      }

      public CrashHandleCallback getCrashHandleCallback() {
         synchronized(this){}

         CrashHandleCallback var1;
         try {
            var1 = this.c;
         } finally {
            ;
         }

         return var1;
      }

      public void setCallBackType(int var1) {
         synchronized(this){}

         try {
            this.a = var1;
         } finally {
            ;
         }

      }

      public void setCloseErrorCallback(boolean var1) {
         synchronized(this){}

         try {
            this.b = var1;
         } finally {
            ;
         }

      }

      public void setCrashHandleCallback(CrashHandleCallback var1) {
         synchronized(this){}

         try {
            this.c = var1;
         } finally {
            ;
         }

      }
   }

   public interface a {
      String a();

      void a(H5JavaScriptInterface var1, String var2);

      void a(String var1);

      void b();

      CharSequence c();
   }
}
