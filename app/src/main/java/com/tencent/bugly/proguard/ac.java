package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.Iterator;
import java.util.List;

public final class ac {
   public static int a;
   public static long b;
   private static ac d;
   private static String i;
   public final ak c;
   private final List e;
   private final StrategyBean f;
   private StrategyBean g = null;
   private Context h;

   private ac(Context var1, List var2) {
      this.h = var1;
      if (aa.a(var1) != null) {
         String var3 = aa.a(var1).H;
         if ("oversea".equals(var3)) {
            StrategyBean.a = "https://astat.bugly.qcloud.com/rqd/async";
            StrategyBean.b = "https://astat.bugly.qcloud.com/rqd/async";
         } else if ("na_https".equals(var3)) {
            StrategyBean.a = "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async";
            StrategyBean.b = "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async";
         }
      }

      this.f = new StrategyBean();
      this.e = var2;
      this.c = ak.a();
   }

   // $FF: synthetic method
   static Context a(ac var0) {
      return var0.h;
   }

   // $FF: synthetic method
   static StrategyBean a(ac var0, StrategyBean var1) {
      var0.g = var1;
      return var1;
   }

   public static ac a() {
      synchronized(ac.class){}

      ac var0;
      try {
         var0 = d;
      } finally {
         ;
      }

      return var0;
   }

   public static ac a(Context var0, List var1) {
      synchronized(ac.class){}

      ac var5;
      try {
         if (d == null) {
            ac var2 = new ac(var0, var1);
            d = var2;
         }

         var5 = d;
      } finally {
         ;
      }

      return var5;
   }

   public static void a(String var0) {
      if (!ap.b(var0) && ap.d(var0)) {
         i = var0;
      } else {
         al.d("URL user set is invalid.");
      }
   }

   // $FF: synthetic method
   static StrategyBean b(ac var0) {
      return var0.g;
   }

   public static StrategyBean d() {
      List var0 = w.a().a(2);
      if (var0 != null && var0.size() > 0) {
         y var1 = (y)var0.get(0);
         if (var1.g != null) {
            return (StrategyBean)ap.a(var1.g, StrategyBean.CREATOR);
         }
      }

      return null;
   }

   // $FF: synthetic method
   static String e() {
      return i;
   }

   protected final void a(StrategyBean var1, boolean var2) {
      al.c("[Strategy] Notify %s", s.class.getName());
      s.a(var1, var2);
      Iterator var3 = this.e.iterator();

      while(var3.hasNext()) {
         o var4 = (o)var3.next();

         try {
            al.c("[Strategy] Notify %s", var4.getClass().getName());
            var4.onServerStrategyChanged(var1);
         } catch (Throwable var6) {
            if (!al.a(var6)) {
               var6.printStackTrace();
            }
            continue;
         }
      }

   }

   public final void a(bt var1) {
      if (var1 != null) {
         if (this.g == null || var1.h != this.g.o) {
            StrategyBean var4 = new StrategyBean();
            var4.f = var1.a;
            var4.h = var1.c;
            var4.g = var1.b;
            if (ap.b(i) || !ap.d(i)) {
               if (ap.d(var1.d)) {
                  al.c("[Strategy] Upload url changes to %s", var1.d);
                  var4.q = var1.d;
               }

               if (ap.d(var1.e)) {
                  al.c("[Strategy] Exception upload url changes to %s", var1.e);
                  var4.r = var1.e;
               }
            }

            if (var1.f != null && !ap.b(var1.f.a)) {
               var4.s = var1.f.a;
            }

            if (var1.h != 0L) {
               var4.o = var1.h;
            }

            if (var1 != null && var1.g != null && var1.g.size() > 0) {
               var4.t = var1.g;
               String var5 = (String)var1.g.get("B11");
               boolean var3;
               if (var5 != null && var5.equals("1")) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var4.i = var3;
               var5 = (String)var1.g.get("B3");
               if (var5 != null) {
                  var4.w = Long.parseLong(var5);
               }

               var4.p = (long)var1.l;
               var4.v = (long)var1.l;
               var5 = (String)var1.g.get("B27");
               if (var5 != null && var5.length() > 0) {
                  label114: {
                     Exception var10000;
                     label111: {
                        boolean var10001;
                        int var2;
                        try {
                           var2 = Integer.parseInt(var5);
                        } catch (Exception var7) {
                           var10000 = var7;
                           var10001 = false;
                           break label111;
                        }

                        if (var2 <= 0) {
                           break label114;
                        }

                        try {
                           var4.u = var2;
                           break label114;
                        } catch (Exception var6) {
                           var10000 = var6;
                           var10001 = false;
                        }
                     }

                     Exception var9 = var10000;
                     if (!al.a(var9)) {
                        var9.printStackTrace();
                     }
                  }
               }

               var5 = (String)var1.g.get("B25");
               if (var5 != null && var5.equals("1")) {
                  var3 = true;
               } else {
                  var3 = false;
               }

               var4.k = var3;
            }

            al.a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", var4.f, var4.h, var4.g, var4.i, var4.j, var4.m, var4.n, var4.p, var4.k, var4.o);
            this.g = var4;
            if (!ap.d(var1.d)) {
               al.c("[Strategy] download url is null");
               this.g.q = "";
            }

            if (!ap.d(var1.e)) {
               al.c("[Strategy] download crashurl is null");
               this.g.r = "";
            }

            w.a().b(2);
            y var8 = new y();
            var8.b = 2;
            var8.a = var4.d;
            var8.e = var4.e;
            var8.g = ap.a((Parcelable)var4);
            w.a().a(var8);
            this.a(var4, true);
         }
      }
   }

   public final boolean b() {
      synchronized(this){}
      boolean var4 = false;

      StrategyBean var2;
      try {
         var4 = true;
         var2 = this.g;
         var4 = false;
      } finally {
         if (var4) {
            ;
         }
      }

      boolean var1;
      if (var2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public final StrategyBean c() {
      StrategyBean var1 = this.g;
      if (var1 != null) {
         if (!ap.d(var1.q)) {
            this.g.q = StrategyBean.a;
         }

         if (!ap.d(this.g.r)) {
            this.g.r = StrategyBean.b;
         }

         return this.g;
      } else {
         if (!ap.b(i) && ap.d(i)) {
            this.f.q = i;
            this.f.r = i;
         }

         return this.f;
      }
   }
}
