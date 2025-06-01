package com.tencent.bugly.proguard;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Build.VERSION;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.util.List;

public class s {
   public static boolean a;
   public static r b;
   private static int c;
   private static long d;
   private static long e;
   private static long f;
   private static int g;
   private static long h;
   private static long i;
   private static long j;
   private static Application.ActivityLifecycleCallbacks k;
   private static Class l;
   private static boolean m;

   // $FF: synthetic method
   static String a(String var0, String var1) {
      StringBuilder var2 = new StringBuilder();
      var2.append(ap.a());
      var2.append("  ");
      var2.append(var0);
      var2.append("  ");
      var2.append(var1);
      var2.append("\n");
      return var2.toString();
   }

   public static void a() {
      r var0 = b;
      if (var0 != null) {
         var0.a(2, false);
      }

   }

   public static void a(long var0) {
      long var2 = var0;
      if (var0 < 0L) {
         var2 = ac.a().c().p;
      }

      f = var2;
   }

   public static void a(Context var0) {
      if (a && var0 != null) {
         Application var1 = null;
         if (VERSION.SDK_INT >= 14) {
            if (var0.getApplicationContext() instanceof Application) {
               var1 = (Application)var0.getApplicationContext();
            }

            if (var1 != null) {
               label48: {
                  Exception var10000;
                  label46: {
                     boolean var10001;
                     Application.ActivityLifecycleCallbacks var4;
                     try {
                        var4 = k;
                     } catch (Exception var3) {
                        var10000 = var3;
                        var10001 = false;
                        break label46;
                     }

                     if (var4 == null) {
                        break label48;
                     }

                     try {
                        var1.unregisterActivityLifecycleCallbacks(var4);
                        break label48;
                     } catch (Exception var2) {
                        var10000 = var2;
                        var10001 = false;
                     }
                  }

                  Exception var5 = var10000;
                  if (!al.a(var5)) {
                     var5.printStackTrace();
                  }
               }
            }
         }

         a = false;
      }

   }

   public static void a(Context var0, BuglyStrategy var1) {
      if (!a) {
         m = aa.a(var0).f;
         b = new r(var0, m);
         a = true;
         long var2;
         if (var1 != null) {
            l = var1.getUserInfoActivity();
            var2 = var1.getAppReportDelay();
         } else {
            var2 = 0L;
         }

         if (var2 <= 0L) {
            c(var0, var1);
         } else {
            ak.a().a(new Runnable(var0, var1) {
               final Context a;
               final BuglyStrategy b;

               {
                  this.a = var1;
                  this.b = var2;
               }

               public final void run() {
                  s.c(this.a, this.b);
               }
            }, var2);
         }
      }
   }

   public static void a(StrategyBean var0, boolean var1) {
      r var2 = b;
      if (var2 != null && !var1) {
         var2.b();
      }

      if (var0 != null) {
         if (var0.p > 0L) {
            e = var0.p;
         }

         if (var0.u > 0) {
            c = var0.u;
         }

         if (var0.v > 0L) {
            d = var0.v;
         }

      }
   }

   private static void c(Context var0, BuglyStrategy var1) {
      boolean var5;
      boolean var6;
      if (var1 != null) {
         var6 = var1.recordUserInfoOnceADay();
         var5 = var1.isEnableUserInfo();
      } else {
         var5 = true;
         var6 = false;
      }

      if (var6) {
         boolean var14;
         label78: {
            aa var8 = aa.a(var0);
            List var11 = r.a(var8.d);
            if (var11 != null) {
               for(int var2 = 0; var2 < var11.size(); ++var2) {
                  UserInfoBean var7 = (UserInfoBean)var11.get(var2);
                  if (var7.n.equals(var8.o) && var7.b == 1) {
                     long var3 = ap.b();
                     if (var3 <= 0L) {
                        break;
                     }

                     if (var7.e >= var3) {
                        if (var7.f <= 0L) {
                           b.b();
                        }

                        var14 = false;
                        break label78;
                     }
                  }
               }
            }

            var14 = true;
         }

         if (!var14) {
            return;
         }

         var5 = false;
      }

      aa var12 = aa.b();
      if (var12 != null && z.a()) {
         var12.a(0, true);
      }

      if (var5) {
         Application var13 = null;
         if (VERSION.SDK_INT >= 14) {
            if (var0.getApplicationContext() instanceof Application) {
               var13 = (Application)var0.getApplicationContext();
            }

            if (var13 != null) {
               try {
                  if (k == null) {
                     a var10 = new a();
                     k = var10;
                  }

                  var13.registerActivityLifecycleCallbacks(k);
               } catch (Exception var9) {
                  if (!al.a(var9)) {
                     var9.printStackTrace();
                  }
               }
            }
         }
      }

      if (m) {
         i = System.currentTimeMillis();
         b.a(1, false);
         al.a("[session] launch app, new start");
         b.a();
         b.a(21600000L);
      }

   }

   // $FF: synthetic method
   static int g() {
      int var0 = g++;
      return var0;
   }

   static final class a implements Application.ActivityLifecycleCallbacks {
      public final void onActivityCreated(Activity var1, Bundle var2) {
         String var4 = var1.getClass().getName();
         if (s.l == null || s.l.getName().equals(var4)) {
            al.c(">>> %s onCreated <<<", var4);
            aa var3 = aa.b();
            if (var3 != null) {
               var3.L.add(s.a(var4, "onCreated"));
            }

         }
      }

      public final void onActivityDestroyed(Activity var1) {
         String var3 = var1.getClass().getName();
         if (s.l == null || s.l.getName().equals(var3)) {
            al.c(">>> %s onDestroyed <<<", var3);
            aa var2 = aa.b();
            if (var2 != null) {
               var2.L.add(s.a(var3, "onDestroyed"));
            }

         }
      }

      public final void onActivityPaused(Activity var1) {
         String var2 = var1.getClass().getName();
         if (s.l == null || s.l.getName().equals(var2)) {
            al.c(">>> %s onPaused <<<", var2);
            aa var3 = aa.b();
            if (var3 != null) {
               var3.L.add(s.a(var2, "onPaused"));
               var3.A = System.currentTimeMillis();
               var3.B = var3.A - var3.z;
               s.h = var3.A;
               if (var3.B < 0L) {
                  var3.B = 0L;
               }

               var3.y = "background";
            }
         }
      }

      public final void onActivityResumed(Activity var1) {
         String var7 = var1.getClass().getName();
         if (s.l == null || s.l.getName().equals(var7)) {
            al.c(">>> %s onResumed <<<", var7);
            aa var6 = aa.b();
            if (var6 != null) {
               var6.L.add(s.a(var7, "onResumed"));
               var6.y = var7;
               var6.z = System.currentTimeMillis();
               var6.C = var6.z - s.i;
               long var4 = var6.z - s.h;
               long var2;
               if (s.f > 0L) {
                  var2 = s.f;
               } else {
                  var2 = s.e;
               }

               if (var4 > var2) {
                  var6.c();
                  s.g();
                  al.a("[session] launch app one times (app in background %d seconds and over %d seconds)", var4 / 1000L, s.e / 1000L);
                  if (s.g % s.c == 0) {
                     s.b.a(4, s.m);
                     return;
                  }

                  s.b.a(4, false);
                  var2 = System.currentTimeMillis();
                  if (var2 - s.j > s.d) {
                     s.j = var2;
                     al.a("add a timer to upload hot start user info");
                     if (s.m) {
                        r var8 = s.b;
                        var2 = s.d;
                        ak.a().a(var8.new a((UserInfoBean)null, true), var2);
                     }
                  }
               }

            }
         }
      }

      public final void onActivitySaveInstanceState(Activity var1, Bundle var2) {
      }

      public final void onActivityStarted(Activity var1) {
         al.c(">>> %s onStart <<<", var1.getClass().getName());
         aa.b().a(var1.hashCode(), true);
      }

      public final void onActivityStopped(Activity var1) {
         al.c(">>> %s onStop <<<", var1.getClass().getName());
         aa.b().a(var1.hashCode(), false);
      }
   }
}
