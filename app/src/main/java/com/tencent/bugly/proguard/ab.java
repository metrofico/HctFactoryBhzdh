package com.tencent.bugly.proguard;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ab {
   private static final ArrayList a = new ArrayList() {
      {
         this.add(new l((byte)0));
         this.add(new f((byte)0));
         this.add(new g((byte)0));
         this.add(new m((byte)0));
         this.add(new h((byte)0));
         this.add(new i((byte)0));
         this.add(new k((byte)0));
         this.add(new e((byte)0));
         this.add(new j((byte)0));
         this.add(new b((byte)0));
         this.add(new d((byte)0));
         this.add(new c((byte)0));
      }
   };
   private static final Map b = new HashMap() {
      {
         this.put(1, "GPRS");
         this.put(2, "EDGE");
         this.put(3, "UMTS");
         this.put(8, "HSDPA");
         this.put(9, "HSUPA");
         this.put(10, "HSPA");
         this.put(4, "CDMA");
         this.put(5, "EVDO_0");
         this.put(6, "EVDO_A");
         this.put(7, "1xRTT");
         this.put(11, "iDen");
         this.put(12, "EVDO_B");
         this.put(13, "LTE");
         this.put(14, "eHRPD");
         this.put(15, "HSPA+");
      }
   };
   private static final String[] c = new String[]{"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};

   public static String a() {
      try {
         String var0 = Build.MODEL;
         return var0;
      } catch (Throwable var2) {
         if (!al.a(var2)) {
            var2.printStackTrace();
         }

         return "fail";
      }
   }

   public static String a(Context var0) {
      if (var0 != null && var0.getApplicationInfo() != null) {
         String var1 = var0.getApplicationInfo().nativeLibraryDir;
         if (TextUtils.isEmpty(var1)) {
            return "fail";
         }

         if (var1.endsWith("arm")) {
            return "armeabi-v7a";
         }

         if (var1.endsWith("arm64")) {
            return "arm64-v8a";
         }

         if (var1.endsWith("x86")) {
            return "x86";
         }

         if (var1.endsWith("x86_64")) {
            return "x86_64";
         }
      }

      return "fail";
   }

   public static long b(Context param0) {
      // $FF: Couldn't be decompiled
   }

   public static String b() {
      try {
         String var0 = VERSION.RELEASE;
         return var0;
      } catch (Throwable var2) {
         if (!al.a(var2)) {
            var2.printStackTrace();
         }

         return "fail";
      }
   }

   public static int c() {
      try {
         int var0 = VERSION.SDK_INT;
         return var0;
      } catch (Throwable var3) {
         if (!al.a(var3)) {
            var3.printStackTrace();
         }

         return -1;
      }
   }

   public static String c(Context var0) {
      String var3 = "unknown";

      String var2;
      String var11;
      Exception var13;
      label85: {
         label69: {
            int var1;
            label62: {
               Exception var10000;
               label70: {
                  NetworkInfo var4;
                  boolean var10001;
                  try {
                     var4 = ((ConnectivityManager)var0.getSystemService("connectivity")).getActiveNetworkInfo();
                  } catch (Exception var9) {
                     var10000 = var9;
                     var10001 = false;
                     break label70;
                  }

                  if (var4 == null) {
                     return null;
                  }

                  try {
                     if (var4.getType() == 1) {
                        break label69;
                     }
                  } catch (Exception var8) {
                     var10000 = var8;
                     var10001 = false;
                     break label70;
                  }

                  var2 = var3;

                  TelephonyManager var10;
                  try {
                     if (var4.getType() != 0) {
                        return var2;
                     }

                     var10 = (TelephonyManager)var0.getSystemService("phone");
                  } catch (Exception var7) {
                     var10000 = var7;
                     var10001 = false;
                     break label70;
                  }

                  var2 = var3;
                  if (var10 == null) {
                     return var2;
                  }

                  try {
                     var1 = var10.getNetworkType();
                     var11 = (String)b.get(var1);
                     break label62;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                  }
               }

               var13 = var10000;
               var11 = var3;
               break label85;
            }

            if (var11 != null) {
               var2 = var11;
               return var2;
            }

            try {
               StringBuilder var12 = new StringBuilder("MOBILE(");
               var2 = var12.append(var1).append(")").toString();
               return var2;
            } catch (Exception var5) {
               var13 = var5;
               break label85;
            }
         }

         var2 = "WIFI";
         return var2;
      }

      var2 = var11;
      if (!al.a(var13)) {
         var13.printStackTrace();
         var2 = var11;
      }

      return var2;
   }

   public static String d() {
      try {
         String var0 = String.valueOf(System.getProperty("os.arch"));
         return var0;
      } catch (Throwable var2) {
         if (!al.a(var2)) {
            var2.printStackTrace();
         }

         return "fail";
      }
   }

   public static long e() {
      int var0;
      long var1;
      try {
         File var3 = Environment.getDataDirectory();
         StatFs var4 = new StatFs(var3.getPath());
         var1 = (long)var4.getBlockSize();
         var0 = var4.getBlockCount();
      } catch (Throwable var6) {
         if (!al.a(var6)) {
            var6.printStackTrace();
         }

         var1 = -1L;
         return var1;
      }

      var1 = (long)var0 * var1;
      return var1;
   }

   public static long f() {
      int var0;
      long var1;
      try {
         File var3 = Environment.getDataDirectory();
         StatFs var4 = new StatFs(var3.getPath());
         var1 = (long)var4.getBlockSize();
         var0 = var4.getAvailableBlocks();
      } catch (Throwable var6) {
         if (!al.a(var6)) {
            var6.printStackTrace();
         }

         var1 = -1L;
         return var1;
      }

      var1 = (long)var0 * var1;
      return var1;
   }

   public static long g() {
      long var2 = 0L;

      BufferedReader var8;
      try {
         FileReader var6 = new FileReader("/proc/self/status");
         var8 = new BufferedReader(var6);
      } finally {
         ;
      }

      long var0;
      long var4;
      Throwable var10000;
      label729: {
         boolean var10001;
         label742: {
            label722: {
               String var82;
               try {
                  var82 = var8.readLine();
               } catch (Throwable var81) {
                  var10000 = var81;
                  var10001 = false;
                  break label722;
               }

               while(true) {
                  var0 = var2;
                  if (var82 == null) {
                     break label742;
                  }

                  try {
                     if (var82.startsWith("VmSize")) {
                        var0 = Long.parseLong(var82.replaceAll("[^\\d]", ""));
                        break label742;
                     }
                  } catch (Throwable var80) {
                     var10000 = var80;
                     var10001 = false;
                     break;
                  }

                  try {
                     var82 = var8.readLine();
                  } catch (Throwable var79) {
                     var10000 = var79;
                     var10001 = false;
                     break;
                  }
               }
            }

            Throwable var7 = var10000;
            BufferedReader var83 = var8;
            boolean var41 = false;

            try {
               var41 = true;
               al.a(var7);
               var41 = false;
            } finally {
               if (var41) {
                  if (var8 != null) {
                     label689:
                     try {
                        var83.close();
                     } catch (Throwable var75) {
                        var75.printStackTrace();
                        break label689;
                     }
                  }

               }
            }

            var0 = var2;
            if (var8 == null) {
               return var0 * 1024L;
            }

            var4 = var2;

            try {
               var83.close();
            } catch (Throwable var77) {
               var10000 = var77;
               var10001 = false;
               break label729;
            }

            var0 = var2;
            return var0 * 1024L;
         }

         var4 = var0;

         label705:
         try {
            var8.close();
            return var0 * 1024L;
         } catch (Throwable var78) {
            var10000 = var78;
            var10001 = false;
            break label705;
         }
      }

      Throwable var84 = var10000;
      var84.printStackTrace();
      var0 = var4;
      return var0 * 1024L;
   }

   public static long h() {
      return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
   }

   public static long i() {
      // $FF: Couldn't be decompiled
   }

   public static long j() {
      // $FF: Couldn't be decompiled
   }

   public static long k() {
      if (!s()) {
         return 0L;
      } else {
         int var0;
         int var1;
         try {
            StatFs var2 = new StatFs(Environment.getExternalStorageDirectory().getPath());
            var0 = var2.getBlockSize();
            var1 = var2.getBlockCount();
         } catch (Throwable var4) {
            if (!al.a(var4)) {
               var4.printStackTrace();
            }

            return -2L;
         }

         return (long)var1 * (long)var0;
      }
   }

   public static long l() {
      if (!s()) {
         return 0L;
      } else {
         int var0;
         int var1;
         try {
            StatFs var2 = new StatFs(Environment.getExternalStorageDirectory().getPath());
            var0 = var2.getBlockSize();
            var1 = var2.getAvailableBlocks();
         } catch (Throwable var4) {
            if (!al.a(var4)) {
               var4.printStackTrace();
            }

            return -2L;
         }

         return (long)var1 * (long)var0;
      }
   }

   public static String m() {
      return "";
   }

   public static String n() {
      Iterator var1 = a.iterator();

      String var0;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         var0 = ((a)var1.next()).a();
      } while(TextUtils.isEmpty(var0));

      return var0;
   }

   public static boolean o() {
      return !TextUtils.isEmpty((new i((byte)0)).a());
   }

   public static boolean p() {
      return !TextUtils.isEmpty((new k((byte)0)).a());
   }

   public static boolean q() {
      String[] var2 = c;
      int var1 = var2.length;
      int var0 = 0;

      boolean var3;
      while(true) {
         if (var0 >= var1) {
            var3 = false;
            break;
         }

         if ((new File(var2[var0])).exists()) {
            var3 = true;
            break;
         }

         ++var0;
      }

      boolean var4;
      if (Build.TAGS != null && Build.TAGS.contains("test-keys")) {
         var4 = true;
      } else {
         var4 = false;
      }

      return var4 || var3;
   }

   public static boolean r() {
      float var0 = (float)((double)Runtime.getRuntime().maxMemory() * 1.0 / 1048576.0);
      float var1 = (float)((double)Runtime.getRuntime().totalMemory() * 1.0 / 1048576.0);
      float var2 = var0 - var1;
      al.c("maxMemory : %f", var0);
      al.c("totalMemory : %f", var1);
      al.c("freeMemory : %f", var2);
      return var2 < 10.0F;
   }

   private static boolean s() {
      boolean var0;
      try {
         var0 = Environment.getExternalStorageState().equals("mounted");
      } catch (Throwable var3) {
         if (!al.a(var3)) {
            var3.printStackTrace();
         }

         return false;
      }

      if (var0) {
         return true;
      } else {
         return false;
      }
   }

   abstract static class a {
      private a() {
      }

      // $FF: synthetic method
      a(byte var1) {
         this();
      }

      public abstract String a();
   }

   static final class b extends a {
      private b() {
         super((byte)0);
      }

      // $FF: synthetic method
      b(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.gn.gnromvernumber");
         return !ap.b(var1) && !var1.equals("fail") ? "amigo/" + var1 + "/" + ap.a("ro.build.display.id") : null;
      }
   }

   static final class c extends a {
      private c() {
         super((byte)0);
      }

      // $FF: synthetic method
      c(byte var1) {
         this();
      }

      public final String a() {
         return ap.a("ro.build.fingerprint") + "/" + ap.a("ro.build.rom.id");
      }
   }

   static final class d extends a {
      private d() {
         super((byte)0);
      }

      // $FF: synthetic method
      d(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.build.tyd.kbstyle_version");
         return !ap.b(var1) && !var1.equals("fail") ? "dido/".concat(String.valueOf(var1)) : null;
      }
   }

   static final class e extends a {
      private e() {
         super((byte)0);
      }

      // $FF: synthetic method
      e(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.aa.romver");
         return !ap.b(var1) && !var1.equals("fail") ? "htc/" + var1 + "/" + ap.a("ro.build.description") : null;
      }
   }

   static final class f extends a {
      private f() {
         super((byte)0);
      }

      // $FF: synthetic method
      f(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.build.version.emui");
         return !ap.b(var1) && !var1.equals("fail") ? "HuaWei/EMOTION/".concat(String.valueOf(var1)) : null;
      }
   }

   static final class g extends a {
      private g() {
         super((byte)0);
      }

      // $FF: synthetic method
      g(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.lenovo.series");
         return !ap.b(var1) && !var1.equals("fail") ? "Lenovo/VIBE/".concat(String.valueOf(ap.a("ro.build.version.incremental"))) : null;
      }
   }

   static final class h extends a {
      private h() {
         super((byte)0);
      }

      // $FF: synthetic method
      h(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.meizu.product.model");
         return !ap.b(var1) && !var1.equals("fail") ? "Meizu/FLYME/" + ap.a("ro.build.display.id") : null;
      }
   }

   static final class i extends a {
      private i() {
         super((byte)0);
      }

      // $FF: synthetic method
      i(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.build.version.opporom");
         return !ap.b(var1) && !var1.equals("fail") ? "Oppo/COLOROS/".concat(String.valueOf(var1)) : null;
      }
   }

   static final class j extends a {
      private j() {
         super((byte)0);
      }

      // $FF: synthetic method
      j(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.lewa.version");
         return !ap.b(var1) && !var1.equals("fail") ? "tcl/" + var1 + "/" + ap.a("ro.build.display.id") : null;
      }
   }

   static final class k extends a {
      private k() {
         super((byte)0);
      }

      // $FF: synthetic method
      k(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.vivo.os.build.display.id");
         return !ap.b(var1) && !var1.equals("fail") ? "vivo/FUNTOUCH/".concat(String.valueOf(var1)) : null;
      }
   }

   static final class l extends a {
      private l() {
         super((byte)0);
      }

      // $FF: synthetic method
      l(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.miui.ui.version.name");
         return !ap.b(var1) && !var1.equals("fail") ? "XiaoMi/MIUI/".concat(String.valueOf(var1)) : null;
      }
   }

   static final class m extends a {
      private m() {
         super((byte)0);
      }

      // $FF: synthetic method
      m(byte var1) {
         this();
      }

      public final String a() {
         String var1 = ap.a("ro.build.nubia.rom.name");
         return !ap.b(var1) && !var1.equals("fail") ? "Zte/NUBIA/" + var1 + "_" + ap.a("ro.build.nubia.rom.code") : null;
      }
   }
}
