package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Process;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public final class aa {
   private static final Map W = new HashMap();
   private static aa aq = null;
   public long A = 0L;
   public long B = 0L;
   public long C = 0L;
   public boolean D = false;
   public String E = null;
   public String F = null;
   public String G = null;
   public String H = "";
   public boolean I = false;
   public boolean J = false;
   public HashMap K = new HashMap();
   public List L = new ArrayList();
   public boolean M = false;
   public q N = null;
   public final SharedPreferences O;
   public final SharedPreferences P;
   public boolean Q = true;
   public boolean R = true;
   public boolean S = false;
   public final Object T = new Object();
   public final Object U = new Object();
   public final Object V = new Object();
   private final Context X;
   private String Y;
   private String Z;
   public final long a = System.currentTimeMillis();
   private String aa;
   private String ab = "unknown";
   private String ac = "";
   private String ad = null;
   private long ae = -1L;
   private long af = -1L;
   private long ag = -1L;
   private String ah = null;
   private String ai = null;
   private String aj = null;
   private Map ak = null;
   private String al = null;
   private Boolean am = null;
   private String an = null;
   private Map ao = null;
   private Map ap = null;
   private final Map ar = new HashMap();
   private final Map as = new HashMap();
   private final Map at = new HashMap();
   private final Object au = new Object();
   private final Object av = new Object();
   private final Object aw = new Object();
   private final Object ax = new Object();
   private final List ay = new ArrayList();
   public final byte b;
   public String c;
   public final String d;
   public String e;
   public boolean f = true;
   public final String g = "com.tencent.bugly";
   public String h = "4.1.9.3";
   public final String i = "";
   @Deprecated
   public final String j = "";
   public final String k;
   public String l = "unknown";
   public long m = 0L;
   public boolean n = false;
   public String o = null;
   public int p;
   public String q = null;
   public String r = null;
   public String s = null;
   public String t = null;
   public String u = null;
   public List v = null;
   public int w = -1;
   public int x = -1;
   public String y = "unknown";
   public long z = 0L;

   private aa(Context var1) {
      this.X = com.tencent.bugly.proguard.ap.a(var1);
      this.b = 1;
      PackageInfo var3 = com.tencent.bugly.proguard.z.b(var1);
      String var2;
      if (var3 != null) {
         label1887:
         try {
            var2 = var3.versionName;
            this.o = var2;
            this.E = var2;
            this.F = Integer.toString(var3.versionCode);
         } catch (Throwable var243) {
            if (!com.tencent.bugly.proguard.al.a(var243)) {
               var243.printStackTrace();
            }
            break label1887;
         }
      }

      this.c = com.tencent.bugly.proguard.z.a(var1);
      this.d = com.tencent.bugly.proguard.z.a(Process.myPid());
      this.q = com.tencent.bugly.proguard.z.c(var1);
      this.k = "Android " + com.tencent.bugly.proguard.ab.b() + ",level " + com.tencent.bugly.proguard.ab.c();
      Map var244 = com.tencent.bugly.proguard.z.d(var1);
      if (var244 != null) {
         label1880: {
            Throwable var10000;
            label1891: {
               boolean var10001;
               String var246;
               try {
                  this.v = com.tencent.bugly.proguard.z.a(var244);
                  var246 = (String)var244.get("BUGLY_APPID");
               } catch (Throwable var242) {
                  var10000 = var242;
                  var10001 = false;
                  break label1891;
               }

               if (var246 != null) {
                  try {
                     this.r = var246;
                     this.b("APP_ID", var246);
                  } catch (Throwable var241) {
                     var10000 = var241;
                     var10001 = false;
                     break label1891;
                  }
               }

               try {
                  var246 = (String)var244.get("BUGLY_APP_VERSION");
               } catch (Throwable var240) {
                  var10000 = var240;
                  var10001 = false;
                  break label1891;
               }

               if (var246 != null) {
                  try {
                     this.o = var246;
                  } catch (Throwable var239) {
                     var10000 = var239;
                     var10001 = false;
                     break label1891;
                  }
               }

               try {
                  var246 = (String)var244.get("BUGLY_APP_CHANNEL");
               } catch (Throwable var238) {
                  var10000 = var238;
                  var10001 = false;
                  break label1891;
               }

               if (var246 != null) {
                  try {
                     this.s = var246;
                  } catch (Throwable var237) {
                     var10000 = var237;
                     var10001 = false;
                     break label1891;
                  }
               }

               try {
                  var246 = (String)var244.get("BUGLY_ENABLE_DEBUG");
               } catch (Throwable var236) {
                  var10000 = var236;
                  var10001 = false;
                  break label1891;
               }

               if (var246 != null) {
                  try {
                     this.D = var246.equalsIgnoreCase("true");
                  } catch (Throwable var235) {
                     var10000 = var235;
                     var10001 = false;
                     break label1891;
                  }
               }

               try {
                  var246 = (String)var244.get("com.tencent.rdm.uuid");
               } catch (Throwable var234) {
                  var10000 = var234;
                  var10001 = false;
                  break label1891;
               }

               if (var246 != null) {
                  try {
                     this.G = var246;
                  } catch (Throwable var233) {
                     var10000 = var233;
                     var10001 = false;
                     break label1891;
                  }
               }

               try {
                  var246 = (String)var244.get("BUGLY_APP_BUILD_NO");
                  if (!TextUtils.isEmpty(var246)) {
                     this.p = Integer.parseInt(var246);
                  }
               } catch (Throwable var232) {
                  var10000 = var232;
                  var10001 = false;
                  break label1891;
               }

               try {
                  var2 = (String)var244.get("BUGLY_AREA");
               } catch (Throwable var231) {
                  var10000 = var231;
                  var10001 = false;
                  break label1891;
               }

               if (var2 == null) {
                  break label1880;
               }

               label1837:
               try {
                  this.H = var2;
                  break label1880;
               } catch (Throwable var230) {
                  var10000 = var230;
                  var10001 = false;
                  break label1837;
               }
            }

            Throwable var245 = var10000;
            if (!com.tencent.bugly.proguard.al.a(var245)) {
               var245.printStackTrace();
            }
         }
      }

      label1829:
      try {
         if (!var1.getDatabasePath("bugly_db_").exists()) {
            this.J = true;
            com.tencent.bugly.proguard.al.c("App is first time to be installed on the device.");
         }
      } catch (Throwable var229) {
         if (com.tencent.bugly.proguard.p.c) {
            var229.printStackTrace();
         }
         break label1829;
      }

      this.O = com.tencent.bugly.proguard.ap.a("BUGLY_COMMON_VALUES", var1);
      this.P = com.tencent.bugly.proguard.ap.a("BUGLY_RESERVED_VALUES", var1);
      this.aj = com.tencent.bugly.proguard.ab.a(var1);
      this.E();
      com.tencent.bugly.proguard.al.c("com info create end");
   }

   public static int B() {
      return com.tencent.bugly.proguard.ab.c();
   }

   @Deprecated
   public static boolean C() {
      com.tencent.bugly.proguard.al.a("Detect if the emulator is unavailable");
      return false;
   }

   @Deprecated
   public static boolean D() {
      com.tencent.bugly.proguard.al.a("Detect if the device hook is unavailable");
      return false;
   }

   private void E() {
      Throwable var10000;
      label269: {
         Iterator var1;
         boolean var10001;
         try {
            var1 = this.P.getAll().entrySet().iterator();
         } catch (Throwable var31) {
            var10000 = var31;
            var10001 = false;
            break label269;
         }

         while(true) {
            try {
               if (!var1.hasNext()) {
                  break;
               }

               Map.Entry var2 = (Map.Entry)var1.next();
               com.tencent.bugly.proguard.al.c("put reserved request data from sp, key:%s value:%s", var2.getKey(), var2.getValue());
               this.a((String)var2.getKey(), var2.getValue().toString(), false);
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label269;
            }
         }

         Iterator var35;
         try {
            var35 = W.entrySet().iterator();
         } catch (Throwable var29) {
            var10000 = var29;
            var10001 = false;
            break label269;
         }

         while(true) {
            try {
               if (!var35.hasNext()) {
                  break;
               }

               Map.Entry var33 = (Map.Entry)var35.next();
               com.tencent.bugly.proguard.al.c("put reserved request data from cache, key:%s value:%s", var33.getKey(), var33.getValue());
               this.a((String)var33.getKey(), (String)var33.getValue(), true);
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               break label269;
            }
         }

         label246:
         try {
            W.clear();
            return;
         } catch (Throwable var28) {
            var10000 = var28;
            var10001 = false;
            break label246;
         }
      }

      Throwable var34 = var10000;
      com.tencent.bugly.proguard.al.b(var34);
   }

   private String F() {
      if (TextUtils.isEmpty(this.ad)) {
         this.ad = com.tencent.bugly.proguard.ap.d("androidid", (String)null);
      }

      return this.ad;
   }

   private static String G() {
      String var1 = UUID.randomUUID().toString();
      String var0 = var1;
      if (!com.tencent.bugly.proguard.ap.b(var1)) {
         var0 = var1.replaceAll("-", "");
      }

      return var0;
   }

   public static aa a(Context var0) {
      synchronized(aa.class){}

      aa var4;
      try {
         if (aq == null) {
            aa var1 = new aa(var0);
            aq = var1;
         }

         var4 = aq;
      } finally {
         ;
      }

      return var4;
   }

   private void a(String var1, String var2, boolean var3) {
      if (com.tencent.bugly.proguard.ap.b(var1)) {
         com.tencent.bugly.proguard.al.d("key should not be empty %s", var1);
      } else {
         com.tencent.bugly.proguard.al.c("putExtraRequestData key:%s value:%s save:%s", var1, var2, var3);
         Object var4 = this.ax;
         synchronized(var4){}

         Throwable var10000;
         boolean var10001;
         label302: {
            label301: {
               try {
                  if (TextUtils.isEmpty(var2)) {
                     this.as.remove(var1);
                     this.P.edit().remove(var1).apply();
                     break label301;
                  }
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label302;
               }

               try {
                  this.as.put(var1, var2);
               } catch (Throwable var33) {
                  var10000 = var33;
                  var10001 = false;
                  break label302;
               }

               if (var3) {
                  try {
                     this.P.edit().putString(var1, var2).apply();
                  } catch (Throwable var32) {
                     var10000 = var32;
                     var10001 = false;
                     break label302;
                  }
               }
            }

            label281:
            try {
               return;
            } catch (Throwable var31) {
               var10000 = var31;
               var10001 = false;
               break label281;
            }
         }

         while(true) {
            Throwable var35 = var10000;

            try {
               throw var35;
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               continue;
            }
         }
      }
   }

   public static aa b() {
      synchronized(aa.class){}

      aa var0;
      try {
         var0 = aq;
      } finally {
         ;
      }

      return var0;
   }

   @Deprecated
   public static String n() {
      return "";
   }

   public final Map A() {
      synchronized(this){}

      Throwable var10000;
      label75: {
         Map var1;
         boolean var10001;
         Map var2;
         try {
            var1 = this.ao;
            var2 = this.ap;
         } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            break label75;
         }

         if (var2 == null) {
            return var1;
         }

         label66:
         try {
            var1.putAll(var2);
            return var1;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label66;
         }
      }

      Throwable var9 = var10000;
      throw var9;
   }

   public final void a(int var1, boolean var2) {
      boolean var3 = false;
      com.tencent.bugly.proguard.al.c("setActivityForeState, hash:%s isFore:%s", var1, var2);
      if (var2) {
         this.ay.add(var1);
      } else {
         this.ay.remove(var1);
         this.ay.remove(0);
      }

      q var4 = this.N;
      if (var4 != null) {
         var2 = var3;
         if (this.ay.size() > 0) {
            var2 = true;
         }

         var4.setNativeIsAppForeground(var2);
      }

   }

   public final void a(String param1) {
      // $FF: Couldn't be decompiled
   }

   public final void a(String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   public final boolean a() {
      boolean var1;
      if (this.ay.size() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      com.tencent.bugly.proguard.al.c("isAppForeground:%s", var1);
      return var1;
   }

   public final void b(String var1) {
      com.tencent.bugly.proguard.al.a("change deviceModel，old:%s new:%s", this.Z, var1);
      this.Z = var1;
      if (!TextUtils.isEmpty(var1)) {
         com.tencent.bugly.proguard.ap.c("deviceModel", var1);
      }

   }

   public final void b(String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   public final void c() {
      // $FF: Couldn't be decompiled
   }

   public final void c(String var1) {
      synchronized(this){}

      try {
         this.ab = String.valueOf(var1);
      } finally {
         ;
      }

   }

   public final String d() {
      Object var1 = this.au;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label122: {
         try {
            if (this.Y == null) {
               this.c();
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label122;
         }

         label119:
         try {
            String var15 = this.Y;
            return var15;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label119;
         }
      }

      while(true) {
         Throwable var2 = var10000;

         try {
            throw var2;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public final void d(String var1) {
      synchronized(this){}

      try {
         this.ac = String.valueOf(var1);
      } finally {
         ;
      }

   }

   public final String e() {
      return !com.tencent.bugly.proguard.ap.b(this.e) ? this.e : this.r;
   }

   public final void e(String var1) {
      if (!TextUtils.isEmpty(var1)) {
         this.ai = var1.trim();
      }

   }

   public final String f() {
      // $FF: Couldn't be decompiled
   }

   public final String f(String param1) {
      // $FF: Couldn't be decompiled
   }

   public final String g() {
      String var1 = this.aa;
      if (var1 != null) {
         return var1;
      } else {
         var1 = com.tencent.bugly.proguard.ap.d("deviceId", (String)null);
         this.aa = var1;
         if (var1 != null) {
            return var1;
         } else {
            var1 = this.F();
            this.aa = var1;
            if (TextUtils.isEmpty(var1)) {
               this.aa = G();
            }

            var1 = this.aa;
            if (var1 != null) {
               com.tencent.bugly.proguard.ap.c("deviceId", var1);
               return this.aa;
            } else {
               return "";
            }
         }
      }
   }

   public final String g(String param1) {
      // $FF: Couldn't be decompiled
   }

   public final String h() {
      synchronized(this){}

      Throwable var10000;
      label314: {
         String var1;
         boolean var10001;
         try {
            var1 = this.Z;
         } catch (Throwable var30) {
            var10000 = var30;
            var10001 = false;
            break label314;
         }

         if (var1 != null) {
            return var1;
         }

         try {
            var1 = com.tencent.bugly.proguard.ap.d("deviceModel", (String)null);
            this.Z = var1;
         } catch (Throwable var29) {
            var10000 = var29;
            var10001 = false;
            break label314;
         }

         if (var1 != null) {
            label289: {
               try {
                  com.tencent.bugly.proguard.al.c("collect device model from sp:%s", var1);
                  var1 = this.Z;
               } catch (Throwable var27) {
                  var10000 = var27;
                  var10001 = false;
                  break label289;
               }

               return var1;
            }
         } else {
            label315: {
               label307: {
                  try {
                     if (this.n) {
                        break label307;
                     }

                     com.tencent.bugly.proguard.al.c("not allow collect device model");
                  } catch (Throwable var31) {
                     var10000 = var31;
                     var10001 = false;
                     break label315;
                  }

                  return "fail";
               }

               try {
                  var1 = com.tencent.bugly.proguard.ab.a();
                  this.Z = var1;
                  com.tencent.bugly.proguard.al.c("collect device model:%s", var1);
                  com.tencent.bugly.proguard.ap.c("deviceModel", this.Z);
                  var1 = this.Z;
               } catch (Throwable var28) {
                  var10000 = var28;
                  var10001 = false;
                  break label315;
               }

               return var1;
            }
         }
      }

      Throwable var32 = var10000;
      throw var32;
   }

   public final String i() {
      synchronized(this){}

      String var1;
      try {
         var1 = this.ac;
      } finally {
         ;
      }

      return var1;
   }

   public final long j() {
      if (this.ae <= 0L) {
         this.ae = com.tencent.bugly.proguard.ab.e();
      }

      return this.ae;
   }

   public final long k() {
      if (this.af <= 0L) {
         this.af = com.tencent.bugly.proguard.ab.i();
      }

      return this.af;
   }

   public final long l() {
      if (this.ag <= 0L) {
         this.ag = com.tencent.bugly.proguard.ab.k();
      }

      return this.ag;
   }

   public final String m() {
      if (!TextUtils.isEmpty(this.ai)) {
         com.tencent.bugly.proguard.al.c("get cpu type from so:%s", this.ai);
         return this.ai;
      } else if (!TextUtils.isEmpty(this.aj)) {
         com.tencent.bugly.proguard.al.c("get cpu type from lib dir:%s", this.aj);
         return this.aj;
      } else {
         return "unknown";
      }
   }

   public final String o() {
      // $FF: Couldn't be decompiled
   }

   public final Map p() {
      synchronized(this){}

      Throwable var10000;
      label137: {
         Map var1;
         boolean var10001;
         try {
            var1 = this.ak;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label137;
         }

         if (var1 == null) {
            return null;
         }

         try {
            if (var1.size() <= 0) {
               return null;
            }
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            break label137;
         }

         HashMap var15;
         try {
            var15 = new HashMap(this.ak.size());
            var15.putAll(this.ak);
         } catch (Throwable var11) {
            var10000 = var11;
            var10001 = false;
            break label137;
         }

         return var15;
      }

      Throwable var14 = var10000;
      throw var14;
   }

   public final String q() {
      if (this.al == null) {
         this.al = com.tencent.bugly.proguard.ab.m();
      }

      return this.al;
   }

   public final Boolean r() {
      if (this.am == null) {
         this.am = com.tencent.bugly.proguard.ab.q();
      }

      return this.am;
   }

   public final String s() {
      if (this.an == null) {
         String var1 = com.tencent.bugly.proguard.ab.n();
         this.an = var1;
         com.tencent.bugly.proguard.al.a("ROM ID: %s", var1);
      }

      return this.an;
   }

   public final Map t() {
      Object var1 = this.av;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label123: {
         try {
            if (this.ar.size() <= 0) {
               return null;
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label123;
         }

         label117:
         try {
            HashMap var15 = new HashMap(this.ar);
            return var15;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label117;
         }
      }

      while(true) {
         Throwable var2 = var10000;

         try {
            throw var2;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public final void u() {
      // $FF: Couldn't be decompiled
   }

   public final int v() {
      // $FF: Couldn't be decompiled
   }

   public final Set w() {
      // $FF: Couldn't be decompiled
   }

   public final Map x() {
      Object var1 = this.ax;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label123: {
         try {
            if (this.as.size() <= 0) {
               return null;
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label123;
         }

         label117:
         try {
            HashMap var15 = new HashMap(this.as);
            return var15;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label117;
         }
      }

      while(true) {
         Throwable var2 = var10000;

         try {
            throw var2;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public final Map y() {
      Object var1 = this.aw;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label123: {
         try {
            if (this.at.size() <= 0) {
               return null;
            }
         } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            break label123;
         }

         label117:
         try {
            HashMap var15 = new HashMap(this.at);
            return var15;
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label117;
         }
      }

      while(true) {
         Throwable var2 = var10000;

         try {
            throw var2;
         } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            continue;
         }
      }
   }

   public final int z() {
      // $FF: Couldn't be decompiled
   }
}
