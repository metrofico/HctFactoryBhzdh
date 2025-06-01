package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.HashMap;

public final class av implements Thread.UncaughtExceptionHandler {
   private static String h;
   private static final Object i = new Object();
   protected final Context a;
   protected final as b;
   protected final ac c;
   protected final aa d;
   protected Thread.UncaughtExceptionHandler e;
   protected Thread.UncaughtExceptionHandler f;
   protected boolean g = false;
   private int j;

   public av(Context var1, as var2, ac var3, aa var4) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
   }

   private static String a(Throwable var0) {
      String var1 = var0.getMessage();
      if (var1 == null) {
         return "";
      } else {
         return var1.length() <= 1000 ? var1 : var1.substring(0, 1000) + "\n[Message over limit size:1000, has been cutted!]";
      }
   }

   private static String a(Throwable var0, int var1) {
      if (var0 == null) {
         return null;
      } else {
         StringBuilder var4 = new StringBuilder();

         Throwable var10000;
         label183: {
            int var3;
            StackTraceElement[] var5;
            boolean var10001;
            try {
               if (var0.getStackTrace() == null) {
                  return var4.toString();
               }

               var5 = var0.getStackTrace();
               var3 = var5.length;
            } catch (Throwable var17) {
               var10000 = var17;
               var10001 = false;
               break label183;
            }

            int var2 = 0;

            while(true) {
               if (var2 >= var3) {
                  return var4.toString();
               }

               StackTraceElement var18 = var5[var2];
               if (var1 > 0) {
                  try {
                     if (var4.length() >= var1) {
                        StringBuilder var19 = new StringBuilder("\n[Stack over limit size :");
                        var4.append(var19.append(var1).append(" , has been cutted !]").toString());
                        return var4.toString();
                     }
                  } catch (Throwable var16) {
                     var10000 = var16;
                     var10001 = false;
                     break;
                  }
               }

               try {
                  var4.append(var18.toString()).append("\n");
               } catch (Throwable var15) {
                  var10000 = var15;
                  var10001 = false;
                  break;
               }

               ++var2;
            }
         }

         var0 = var10000;
         al.e("gen stack error %s", var0.toString());
         return var4.toString();
      }
   }

   private static void a(CrashDetailBean var0, Throwable var1, boolean var2) {
      String var9 = var1.getClass().getName();
      String var8 = a(var1);
      int var3 = var1.getStackTrace().length;
      boolean var4;
      if (var1.getCause() != null) {
         var4 = true;
      } else {
         var4 = false;
      }

      al.e("stack frame :%d, has cause %b", var3, var4);
      var3 = var1.getStackTrace().length;
      String var7 = "";
      String var5;
      if (var3 > 0) {
         var5 = var1.getStackTrace()[0].toString();
      } else {
         var5 = "";
      }

      Throwable var6;
      for(var6 = var1; var6 != null && var6.getCause() != null; var6 = var6.getCause()) {
      }

      String var10;
      if (var6 != null && var6 != var1) {
         var0.n = var6.getClass().getName();
         var0.o = a(var6);
         if (var6.getStackTrace().length > 0) {
            var0.p = var6.getStackTrace()[0].toString();
         }

         StringBuilder var12 = new StringBuilder();
         var12.append(var9).append(":").append(var8).append("\n");
         var12.append(var5);
         var12.append("\n......");
         var12.append("\nCaused by:\n");
         var12.append(var0.n).append(":").append(var0.o).append("\n");
         var10 = a(var6, at.h);
         var12.append(var10);
         var0.q = var12.toString();
      } else {
         var0.n = var9;
         String var11 = var7;
         if (at.a().i()) {
            var11 = var7;
            if (var2) {
               al.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!");
               var11 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
            }
         }

         var0.o = var8 + var11;
         var0.p = var5;
         var10 = a(var1, at.h);
         var0.q = var10;
      }

      var0.u = ap.c(var0.q.getBytes());
      var0.z.put(var0.B, var10);
   }

   private static boolean a(Thread.UncaughtExceptionHandler var0) {
      if (var0 == null) {
         return true;
      } else {
         String var3 = var0.getClass().getName();
         StackTraceElement[] var4 = Thread.currentThread().getStackTrace();
         int var2 = var4.length;

         for(int var1 = 0; var1 < var2; ++var1) {
            StackTraceElement var5 = var4[var1];
            String var6 = var5.getClassName();
            String var7 = var5.getMethodName();
            if (var3.equals(var6) && "uncaughtException".equals(var7)) {
               return false;
            }
         }

         return true;
      }
   }

   private static boolean a(Thread var0) {
      Object var1 = i;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label199: {
         label203: {
            try {
               if (h != null && var0.getName().equals(h)) {
                  break label203;
               }
            } catch (Throwable var21) {
               var10000 = var21;
               var10001 = false;
               break label199;
            }

            try {
               h = var0.getName();
               return false;
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label199;
            }
         }

         label187:
         try {
            return true;
         } catch (Throwable var19) {
            var10000 = var19;
            var10001 = false;
            break label187;
         }
      }

      while(true) {
         Throwable var22 = var10000;

         try {
            throw var22;
         } catch (Throwable var18) {
            var10000 = var18;
            var10001 = false;
            continue;
         }
      }
   }

   private CrashDetailBean b(Thread var1, Throwable var2, boolean var3, String var4, byte[] var5, boolean var6) {
      if (var2 == null) {
         al.d("We can do nothing with a null throwable.");
         return null;
      } else {
         CrashDetailBean var9 = new CrashDetailBean();
         var9.r = System.currentTimeMillis();
         var9.C = ab.j();
         var9.D = ab.f();
         var9.E = ab.l();
         var9.F = this.d.k();
         var9.G = this.d.j();
         var9.H = this.d.l();
         if (!var3 && aa.B() >= 31) {
            var9.I = 0L;
         } else {
            var9.I = ab.b(this.a);
         }

         var9.J = ab.g();
         var9.K = ab.h();
         var9.y = ao.a();
         int var7;
         if (var9.y == null) {
            var7 = 0;
         } else {
            var7 = var9.y.length;
         }

         al.a("user log size:%d", var7);
         byte var42;
         if (var3) {
            var42 = 0;
         } else {
            var42 = 2;
         }

         Throwable var10000;
         label496: {
            var9.b = var42;
            var9.e = this.d.g();
            var9.f = this.d.o;
            var9.g = this.d.q();
            var9.m = this.d.f();
            var9.z = ap.a(var6, at.h);
            var9.A = this.d.d;
            var9.B = var1.getName() + "(" + var1.getId() + ")";
            var9.L = this.d.s();
            var9.h = this.d.p();
            var9.i = this.d.A();
            var9.Q = this.d.a;
            var9.R = this.d.a();
            a(var9, var2, var3);
            boolean var10001;
            if (!var3) {
               boolean var43;
               label479: {
                  label478: {
                     if (var4 != null) {
                        try {
                           if (var4.length() > 0) {
                              break label478;
                           }
                        } catch (Throwable var39) {
                           var10000 = var39;
                           var10001 = false;
                           break label496;
                        }
                     }

                     var43 = false;
                     break label479;
                  }

                  var43 = true;
               }

               boolean var8;
               label471: {
                  label470: {
                     if (var5 != null) {
                        try {
                           if (var5.length > 0) {
                              break label470;
                           }
                        } catch (Throwable var38) {
                           var10000 = var38;
                           var10001 = false;
                           break label496;
                        }
                     }

                     var8 = false;
                     break label471;
                  }

                  var8 = true;
               }

               if (var43) {
                  try {
                     HashMap var40 = new HashMap(1);
                     var9.S = var40;
                     var9.S.put("UserData", var4);
                  } catch (Throwable var37) {
                     var10000 = var37;
                     var10001 = false;
                     break label496;
                  }
               }

               if (var8) {
                  try {
                     var9.Y = var5;
                  } catch (Throwable var36) {
                     var10000 = var36;
                     var10001 = false;
                     break label496;
                  }
               }
            }

            label455:
            try {
               var9.U = this.d.z();
               var9.V = this.d.x;
               var9.W = this.d.t();
               var9.X = this.d.y();
               return var9;
            } catch (Throwable var35) {
               var10000 = var35;
               var10001 = false;
               break label455;
            }
         }

         Throwable var41 = var10000;
         al.e("handle crash error %s", var41.toString());
         return var9;
      }
   }

   private static void c() {
      al.e("current process die");
      Process.killProcess(Process.myPid());
      System.exit(1);
   }

   public final void a() {
      synchronized(this){}

      Throwable var10000;
      label427: {
         boolean var10001;
         try {
            if (this.j >= 10) {
               al.a("java crash handler over %d, no need set.", 10);
               return;
            }
         } catch (Throwable var44) {
            var10000 = var44;
            var10001 = false;
            break label427;
         }

         Thread.UncaughtExceptionHandler var2;
         try {
            this.g = true;
            var2 = Thread.getDefaultUncaughtExceptionHandler();
         } catch (Throwable var43) {
            var10000 = var43;
            var10001 = false;
            break label427;
         }

         if (var2 != null) {
            label429: {
               boolean var1;
               try {
                  var1 = this.getClass().getName().equals(var2.getClass().getName());
               } catch (Throwable var41) {
                  var10000 = var41;
                  var10001 = false;
                  break label427;
               }

               if (var1) {
                  return;
               }

               try {
                  if ("com.android.internal.os.RuntimeInit$UncaughtHandler".equals(var2.getClass().getName())) {
                     al.a("backup system java handler: %s", var2.toString());
                     this.f = var2;
                     this.e = var2;
                     break label429;
                  }
               } catch (Throwable var42) {
                  var10000 = var42;
                  var10001 = false;
                  break label427;
               }

               try {
                  al.a("backup java handler: %s", var2.toString());
                  this.e = var2;
               } catch (Throwable var40) {
                  var10000 = var40;
                  var10001 = false;
                  break label427;
               }
            }
         }

         try {
            Thread.setDefaultUncaughtExceptionHandler(this);
            ++this.j;
            al.a("registered java monitor: %s", this.toString());
         } catch (Throwable var39) {
            var10000 = var39;
            var10001 = false;
            break label427;
         }

         return;
      }

      Throwable var45 = var10000;
      throw var45;
   }

   public final void a(StrategyBean var1) {
      synchronized(this){}
      if (var1 != null) {
         try {
            if (var1.f == this.g) {
               return;
            }

            al.a("java changed to %b", var1.f);
            if (!var1.f) {
               this.b();
               return;
            }

            this.a();
         } finally {
            ;
         }

      }
   }

   public final void a(Thread var1, Throwable var2, boolean var3, String var4, byte[] var5, boolean var6) {
      if (var3) {
         al.e("Java Crash Happen cause by %s(%d)", var1.getName(), var1.getId());
         if (a(var1)) {
            al.a("this class has handled this exception");
            if (this.f != null) {
               al.a("call system handler");
               this.f.uncaughtException(var1, var2);
            } else {
               c();
            }
         }
      } else {
         al.e("Java Catch Happen");
      }

      Thread.UncaughtExceptionHandler var120;
      label2239: {
         Throwable var10000;
         label2224: {
            boolean var10001;
            try {
               if (!this.g) {
                  al.c("Java crash handler is disable. Just return.");
                  break label2239;
               }
            } catch (Throwable var119) {
               var10000 = var119;
               var10001 = false;
               break label2224;
            }

            try {
               if (!this.c.b()) {
                  al.d("no remote but still store!");
               }
            } catch (Throwable var118) {
               var10000 = var118;
               var10001 = false;
               break label2224;
            }

            boolean var7;
            try {
               var7 = this.c.c().f;
            } catch (Throwable var117) {
               var10000 = var117;
               var10001 = false;
               break label2224;
            }

            label2225: {
               String var8 = "JAVA_CRASH";
               if (!var7) {
                  try {
                     if (this.c.b()) {
                        al.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!");
                        break label2225;
                     }
                  } catch (Throwable var116) {
                     var10000 = var116;
                     var10001 = false;
                     break label2224;
                  }
               }

               CrashDetailBean var121;
               try {
                  var121 = this.b(var1, var2, var3, var4, var5, var6);
               } catch (Throwable var115) {
                  var10000 = var115;
                  var10001 = false;
                  break label2224;
               }

               if (var121 == null) {
                  try {
                     al.e("pkg crash datas fail!");
                  } catch (Throwable var112) {
                     var10000 = var112;
                     var10001 = false;
                     break label2224;
                  }

                  if (var3) {
                     var120 = this.e;
                     if (var120 != null && a(var120)) {
                        al.e("sys default last handle start!");
                        this.e.uncaughtException(var1, var2);
                        al.e("sys default last handle end!");
                        return;
                     }

                     if (this.f != null) {
                        al.e("system handle start!");
                        this.f.uncaughtException(var1, var2);
                        al.e("system handle end!");
                        return;
                     }

                     al.e("crashreport last handle start!");
                     c();
                     al.e("crashreport last handle end!");
                  }

                  return;
               } else {
                  if (var3) {
                     var4 = var8;
                  } else {
                     var4 = "JAVA_CATCH";
                  }

                  try {
                     as.a(var4, ap.a(), this.d.d, var1.getName(), ap.a(var2), var121);
                     if (!this.b.a(var121, var3)) {
                        this.b.b(var121, var3);
                     }
                  } catch (Throwable var114) {
                     var10000 = var114;
                     var10001 = false;
                     break label2224;
                  }

                  if (var3) {
                     try {
                        this.b.a(var121);
                     } catch (Throwable var113) {
                        var10000 = var113;
                        var10001 = false;
                        break label2224;
                     }
                  }

                  if (var3) {
                     var120 = this.e;
                     if (var120 != null && a(var120)) {
                        al.e("sys default last handle start!");
                        this.e.uncaughtException(var1, var2);
                        al.e("sys default last handle end!");
                        return;
                     }

                     if (this.f != null) {
                        al.e("system handle start!");
                        this.f.uncaughtException(var1, var2);
                        al.e("system handle end!");
                        return;
                     }

                     al.e("crashreport last handle start!");
                     c();
                     al.e("crashreport last handle end!");
                     return;
                  }

                  return;
               }
            }

            if (var3) {
               var4 = "JAVA_CRASH";
            } else {
               var4 = "JAVA_CATCH";
            }

            try {
               as.a(var4, ap.a(), this.d.d, var1.getName(), ap.a(var2), (CrashDetailBean)null);
            } catch (Throwable var111) {
               var10000 = var111;
               var10001 = false;
               break label2224;
            }

            if (var3) {
               var120 = this.e;
               if (var120 != null && a(var120)) {
                  al.e("sys default last handle start!");
                  this.e.uncaughtException(var1, var2);
                  al.e("sys default last handle end!");
                  return;
               }

               if (this.f != null) {
                  al.e("system handle start!");
                  this.f.uncaughtException(var1, var2);
                  al.e("system handle end!");
                  return;
               }

               al.e("crashreport last handle start!");
               c();
               al.e("crashreport last handle end!");
            }

            return;
         }

         Throwable var122 = var10000;
         boolean var19 = false;

         try {
            var19 = true;
            if (!al.a(var122)) {
               var122.printStackTrace();
               var19 = false;
            } else {
               var19 = false;
            }
         } finally {
            if (var19) {
               if (var3) {
                  var120 = this.e;
                  if (var120 != null && a(var120)) {
                     al.e("sys default last handle start!");
                     this.e.uncaughtException(var1, var2);
                     al.e("sys default last handle end!");
                  } else if (this.f != null) {
                     al.e("system handle start!");
                     this.f.uncaughtException(var1, var2);
                     al.e("system handle end!");
                  } else {
                     al.e("crashreport last handle start!");
                     c();
                     al.e("crashreport last handle end!");
                  }
               }

            }
         }

         if (var3) {
            var120 = this.e;
            if (var120 != null && a(var120)) {
               al.e("sys default last handle start!");
               this.e.uncaughtException(var1, var2);
               al.e("sys default last handle end!");
               return;
            }

            if (this.f != null) {
               al.e("system handle start!");
               this.f.uncaughtException(var1, var2);
               al.e("system handle end!");
               return;
            }

            al.e("crashreport last handle start!");
            c();
            al.e("crashreport last handle end!");
         }

         return;
      }

      if (var3) {
         var120 = this.e;
         if (var120 != null && a(var120)) {
            al.e("sys default last handle start!");
            this.e.uncaughtException(var1, var2);
            al.e("sys default last handle end!");
            return;
         }

         if (this.f != null) {
            al.e("system handle start!");
            this.f.uncaughtException(var1, var2);
            al.e("system handle end!");
            return;
         }

         al.e("crashreport last handle start!");
         c();
         al.e("crashreport last handle end!");
      }

   }

   public final void b() {
      synchronized(this){}

      try {
         this.g = false;
         al.a("close java monitor!");
         if ("bugly".equals(Thread.getDefaultUncaughtExceptionHandler().getClass().getName())) {
            al.a("Java monitor to unregister: %s", this.toString());
            Thread.setDefaultUncaughtExceptionHandler(this.e);
            --this.j;
         }
      } finally {
         ;
      }

   }

   public final void uncaughtException(Thread param1, Throwable param2) {
      // $FF: Couldn't be decompiled
   }
}
