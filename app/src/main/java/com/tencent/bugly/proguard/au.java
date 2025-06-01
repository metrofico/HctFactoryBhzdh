package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.LinkedHashMap;
import java.util.Map;

public final class au {
   private static au a;
   private ac b;
   private aa c;
   private as d;
   private Context e;

   private au(Context var1) {
      at var2 = at.a();
      if (var2 != null) {
         this.b = ac.a();
         this.c = aa.a(var1);
         this.d = var2.s;
         this.e = var1;
         ak.a().a(new Runnable(this) {
            final au a;

            {
               this.a = var1;
            }

            public final void run() {
               au.a(this.a);
            }
         });
      }
   }

   public static au a(Context var0) {
      if (a == null) {
         a = new au(var0);
      }

      return a;
   }

   // $FF: synthetic method
   static void a(au var0) {
      al.c("[ExtraCrashManager] Trying to notify Bugly agents.");

      try {
         Class var1 = Class.forName("com.tencent.bugly.agent.GameAgent");
         var0.c.getClass();
         ap.a(var1, "sdkPackageName", "com.tencent.bugly");
         al.c("[ExtraCrashManager] Bugly game agent has been notified.");
      } finally {
         al.a("[ExtraCrashManager] no game agent");
         return;
      }
   }

   // $FF: synthetic method
   static void a(au var0, Thread var1, int var2, String var3, String var4, String var5, Map var6) {
      Thread var10;
      if (var1 == null) {
         var10 = Thread.currentThread();
      } else {
         var10 = var1;
      }

      byte var7 = 5;
      String var197;
      if (var2 != 4) {
         if (var2 != 5 && var2 != 6) {
            if (var2 != 8) {
               al.d("[ExtraCrashManager] Unknown extra crash type: %d", var2);
               return;
            }

            var197 = "H5";
         } else {
            var197 = "Cocos";
         }
      } else {
         var197 = "Unity";
      }

      al.e("[ExtraCrashManager] %s Crash Happen", var197);

      label2239: {
         label2250: {
            Throwable var10000;
            label2256: {
               boolean var10001;
               try {
                  if (!var0.b.b()) {
                     al.d("[ExtraCrashManager] There is no remote strategy, but still store it.");
                  }
               } catch (Throwable var193) {
                  var10000 = var193;
                  var10001 = false;
                  break label2256;
               }

               boolean var8;
               StrategyBean var9;
               try {
                  var9 = var0.b.c();
                  var8 = var9.f;
               } catch (Throwable var192) {
                  var10000 = var192;
                  var10001 = false;
                  break label2256;
               }

               String var199;
               StringBuilder var201;
               if (!var8) {
                  try {
                     if (var0.b.b()) {
                        al.e("[ExtraCrashManager] Crash report was closed by remote. Will not upload to Bugly , print local for helpful!");
                        var199 = ap.a();
                        String var196 = var0.c.d;
                        String var205 = var10.getName();
                        var201 = new StringBuilder();
                        as.a(var197, var199, var196, var205, var201.append(var3).append("\n").append(var4).append("\n").append(var5).toString(), (CrashDetailBean)null);
                        break label2239;
                     }
                  } catch (Throwable var191) {
                     var10000 = var191;
                     var10001 = false;
                     break label2256;
                  }
               }

               if (var2 != 5 && var2 != 6) {
                  if (var2 == 8) {
                     try {
                        if (!var9.l) {
                           al.e("[ExtraCrashManager] %s report is disabled.", var197);
                           break label2250;
                        }
                     } catch (Throwable var190) {
                        var10000 = var190;
                        var10001 = false;
                        break label2256;
                     }
                  }
               } else {
                  label2255: {
                     try {
                        if (var9.k) {
                           break label2255;
                        }

                        al.e("[ExtraCrashManager] %s report is disabled.", var197);
                     } catch (Throwable var194) {
                        var10000 = var194;
                        var10001 = false;
                        break label2256;
                     }

                     al.e("[ExtraCrashManager] Successfully handled.");
                     return;
                  }
               }

               if (var2 == 8) {
                  var2 = var7;
               }

               CrashDetailBean var12;
               try {
                  var12 = new CrashDetailBean();
                  var12.C = ab.j();
                  var12.D = ab.f();
                  var12.E = ab.l();
                  var12.F = var0.c.k();
                  var12.G = var0.c.j();
                  var12.H = var0.c.l();
                  var12.I = ab.b(var0.e);
                  var12.J = ab.g();
                  var12.K = ab.h();
                  var12.b = var2;
                  var12.e = var0.c.g();
                  var12.f = var0.c.o;
                  var12.g = var0.c.q();
                  var12.m = var0.c.f();
                  var12.n = String.valueOf(var3);
                  var12.o = String.valueOf(var4);
               } catch (Throwable var189) {
                  var10000 = var189;
                  var10001 = false;
                  break label2256;
               }

               String var200 = "";
               String var204;
               if (var5 != null) {
                  String[] var11;
                  try {
                     var11 = var5.split("\n");
                  } catch (Throwable var187) {
                     var10000 = var187;
                     var10001 = false;
                     break label2256;
                  }

                  label2205: {
                     try {
                        if (var11.length <= 0) {
                           break label2205;
                        }
                     } catch (Throwable var188) {
                        var10000 = var188;
                        var10001 = false;
                        break label2256;
                     }

                     var200 = var11[0];
                  }

                  var204 = var5;
               } else {
                  var204 = "";
               }

               try {
                  var12.p = var200;
                  var12.q = var204;
                  var12.r = System.currentTimeMillis();
                  var12.u = ap.c(var12.q.getBytes());
                  var12.z = ap.a(var0.c.Q, at.h);
                  var12.A = var0.c.d;
                  var201 = new StringBuilder();
                  var12.B = var201.append(var10.getName()).append("(").append(var10.getId()).append(")").toString();
                  var12.L = var0.c.s();
                  var12.h = var0.c.p();
                  var12.Q = var0.c.a;
                  var12.R = var0.c.a();
                  var12.U = var0.c.z();
                  var12.V = var0.c.x;
                  var12.W = var0.c.t();
                  var12.X = var0.c.y();
                  var12.y = ao.a();
                  if (var12.S == null) {
                     LinkedHashMap var202 = new LinkedHashMap();
                     var12.S = var202;
                  }
               } catch (Throwable var186) {
                  var10000 = var186;
                  var10001 = false;
                  break label2256;
               }

               if (var6 != null) {
                  try {
                     var12.S.putAll(var6);
                  } catch (Throwable var185) {
                     var10000 = var185;
                     var10001 = false;
                     break label2256;
                  }
               }

               as var198;
               label2191: {
                  label2190: {
                     try {
                        var199 = ap.a();
                        var200 = var0.c.d;
                        var204 = var10.getName();
                        StringBuilder var203 = new StringBuilder();
                        as.a(var197, var199, var200, var204, var203.append(var3).append("\n").append(var4).append("\n").append(var5).toString(), var12);
                        var198 = var0.d;
                        if (!at.a().C) {
                           break label2190;
                        }
                     } catch (Throwable var184) {
                        var10000 = var184;
                        var10001 = false;
                        break label2256;
                     }

                     var8 = false;
                     break label2191;
                  }

                  var8 = true;
               }

               try {
                  if (!var198.a(var12, var8)) {
                     var0.d.b(var12, false);
                  }
               } catch (Throwable var183) {
                  var10000 = var183;
                  var10001 = false;
                  break label2256;
               }

               al.e("[ExtraCrashManager] Successfully handled.");
               return;
            }

            Throwable var195 = var10000;

            try {
               if (!al.a(var195)) {
                  var195.printStackTrace();
               }
            } finally {
               al.e("[ExtraCrashManager] Successfully handled.");
            }

            return;
         }

         al.e("[ExtraCrashManager] Successfully handled.");
         return;
      }

      al.e("[ExtraCrashManager] Successfully handled.");
   }

   public static void a(Thread var0, int var1, String var2, String var3, String var4, Map var5) {
      ak.a().a(new Runnable(var0, var1, var2, var3, var4, var5) {
         final Thread a;
         final int b;
         final String c;
         final String d;
         final String e;
         final Map f;

         {
            this.a = var1;
            this.b = var2;
            this.c = var3;
            this.d = var4;
            this.e = var5;
            this.f = var6;
         }

         public final void run() {
            try {
               if (au.a == null) {
                  al.e("[ExtraCrashManager] Extra crash manager has not been initialized.");
               } else {
                  au.a(au.a, this.a, this.b, this.c, this.d, this.e, this.f);
               }
            } catch (Throwable var3) {
               if (!al.b(var3)) {
                  var3.printStackTrace();
               }

               al.e("[ExtraCrashManager] Crash error %s %s %s", this.c, this.d, this.e);
               return;
            }
         }
      });
   }
}
