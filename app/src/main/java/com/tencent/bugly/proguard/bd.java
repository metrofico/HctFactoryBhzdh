package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler;
import java.util.HashMap;
import java.util.Map;

public final class bd implements NativeExceptionHandler {
   private final Context a;
   private final as b;
   private final aa c;
   private final ac d;

   public bd(Context var1, aa var2, as var3, ac var4) {
      this.a = var1;
      this.b = var3;
      this.c = var2;
      this.d = var4;
   }

   private static Map a(String[] var0) {
      int var1;
      if (var0 == null) {
         var1 = 1;
      } else {
         var1 = var0.length;
      }

      HashMap var3 = new HashMap(var1);
      if (var0 != null) {
         for(var1 = 0; var1 < var0.length; ++var1) {
            String var4 = var0[var1];
            if (var4 != null) {
               al.a("Extra message[%d]: %s", var1, var4);
               String[] var2 = var4.split("=");
               if (var2.length == 2) {
                  var3.put(var2[0], var2[1]);
               } else {
                  al.d("bad extraMsg %s", var4);
               }
            }
         }
      } else {
         al.c("not found extraMsg");
      }

      return var3;
   }

   public final boolean getAndUpdateAnrState() {
      if (ay.a() == null) {
         return false;
      } else {
         ay var3 = ay.a();
         if (var3.a.get()) {
            al.c("anr is processing, return");
            return false;
         } else {
            ActivityManager var2 = var3.b;
            boolean var1;
            if (!z.a(var2) && az.a(var2, 0L) != null) {
               var1 = true;
            } else {
               var1 = false;
            }

            if (!var1) {
               al.c("proc is not in anr, wait next check");
               return false;
            } else {
               return var3.a(System.currentTimeMillis()) ? false : var3.a(true);
            }
         }
      }
   }

   public final void handleNativeException(int var1, int var2, long var3, long var5, String var7, String var8, String var9, String var10, int var11, String var12, int var13, int var14, int var15, String var16, String var17) {
      al.a("Native Crash Happen v1");
      this.handleNativeException2(var1, var2, var3, var5, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17, (String[])null);
   }

   public final void handleNativeException2(int var1, int var2, long var3, long var5, String var7, String var8, String var9, String var10, int var11, String var12, int var13, int var14, int var15, String var16, String var17, String[] var18) {
      Throwable var627;
      label5368: {
         Throwable var10000;
         boolean var10001;
         boolean var19;
         String var22;
         String var23;
         String var24;
         String var634;
         String var635;
         label5364: {
            label5363: {
               label5373: {
                  al.a("Native Crash Happen v2");
                  if (var11 > 0) {
                     try {
                        StringBuilder var633 = new StringBuilder();
                        var16 = var633.append(var7).append("(").append(var12).append(")").toString();
                     } catch (Throwable var624) {
                        var10000 = var624;
                        var10001 = false;
                        break label5373;
                     }
                  } else {
                     var16 = var7;
                  }

                  Map var626;
                  try {
                     var22 = be.a(var9);
                     var626 = a(var18);
                     var9 = (String)var626.get("HasPendingException");
                  } catch (Throwable var623) {
                     var10000 = var623;
                     var10001 = false;
                     break label5373;
                  }

                  label5353: {
                     label5352: {
                        if (var9 != null) {
                           try {
                              if (var9.equals("true")) {
                                 al.a("Native crash happened with a Java pending exception.");
                                 break label5352;
                              }
                           } catch (Throwable var622) {
                              var10000 = var622;
                              var10001 = false;
                              break label5373;
                           }
                        }

                        var19 = false;
                        break label5353;
                     }

                     var19 = true;
                  }

                  aa var628;
                  try {
                     var628 = this.c;
                     var634 = (String)var626.get("ExceptionProcessName");
                  } catch (Throwable var621) {
                     var10000 = var621;
                     var10001 = false;
                     break label5373;
                  }

                  label5376: {
                     if (var634 != null) {
                        label5377: {
                           label5337:
                           try {
                              if (var634.length() != 0) {
                                 break label5337;
                              }
                              break label5377;
                           } catch (Throwable var620) {
                              var10000 = var620;
                              var10001 = false;
                              break label5373;
                           }

                           try {
                              al.c("Name of crash process: %s", var634);
                              break label5376;
                           } catch (Throwable var619) {
                              var10000 = var619;
                              var10001 = false;
                              break label5373;
                           }
                        }
                     }

                     try {
                        var634 = var628.d;
                     } catch (Throwable var618) {
                        var10000 = var618;
                        var10001 = false;
                        break label5373;
                     }
                  }

                  StringBuilder var629;
                  label5324: {
                     try {
                        var9 = (String)var626.get("ExceptionThreadName");
                        al.c("crash thread name:%s tid:%s", var9, var2);
                        if (TextUtils.isEmpty(var9)) {
                           Thread var636 = Thread.currentThread();
                           var629 = new StringBuilder();
                           var635 = var629.append(var636.getName()).append("(").append(var2).append(")").toString();
                           break label5324;
                        }
                     } catch (Throwable var617) {
                        var10000 = var617;
                        var10001 = false;
                        break label5373;
                     }

                     try {
                        StringBuilder var21 = new StringBuilder();
                        var635 = var21.append(var9).append("(").append(var2).append(")").toString();
                     } catch (Throwable var616) {
                        var10000 = var616;
                        var10001 = false;
                        break label5373;
                     }
                  }

                  try {
                     var5 /= 1000L;
                     var23 = (String)var626.get("SysLogPath");
                     var24 = (String)var626.get("JniLogPath");
                     if (!this.d.b()) {
                        al.d("no remote but still store!");
                     }
                  } catch (Throwable var615) {
                     var10000 = var615;
                     var10001 = false;
                     break label5373;
                  }

                  boolean var20;
                  try {
                     var20 = this.d.c().f;
                  } catch (Throwable var614) {
                     var10000 = var614;
                     var10001 = false;
                     break label5373;
                  }

                  if (!var20) {
                     try {
                        if (this.d.b()) {
                           al.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!");
                           var7 = ap.a();
                           var629 = new StringBuilder();
                           as.a("NATIVE_CRASH", var7, var634, var635, var629.append(var16).append("\n").append(var8).append("\n").append(var22).toString(), (CrashDetailBean)null);
                           ap.c(var10);
                           return;
                        }
                     } catch (Throwable var613) {
                        var10000 = var613;
                        var10001 = false;
                        break label5373;
                     }
                  }

                  if (var11 > 0) {
                     var12 = "KERNEL";
                  }

                  var7 = "UNKNOWN";
                  var9 = var7;
                  if (var11 > 0) {
                     break label5363;
                  }

                  if (var13 > 0) {
                     try {
                        var7 = z.a(var13);
                     } catch (Throwable var612) {
                        var10000 = var612;
                        var10001 = false;
                        break label5373;
                     }
                  }

                  var9 = var7;

                  label5299:
                  try {
                     if (!var7.equals(String.valueOf(var13))) {
                        var629 = new StringBuilder();
                        var7 = var629.append(var7).append("(").append(var13).append(")").toString();
                        break label5364;
                     }
                     break label5363;
                  } catch (Throwable var611) {
                     var10000 = var611;
                     var10001 = false;
                     break label5299;
                  }
               }

               var627 = var10000;
               break label5368;
            }

            var7 = var9;
         }

         label5290: {
            CrashDetailBean var632;
            try {
               var632 = this.packageCrashDatas(var634, var635, var5 + var3 * 1000L, var16, var8, var22, var12, var7, var10, var23, var24, var17, (byte[])null, (Map)null, true, var19);
            } catch (Throwable var610) {
               var10000 = var610;
               var10001 = false;
               break label5290;
            }

            if (var632 == null) {
               label5257:
               try {
                  al.e("pkg crash datas fail!");
                  return;
               } catch (Throwable var601) {
                  var10000 = var601;
                  var10001 = false;
                  break label5257;
               }
            } else {
               label5371: {
                  try {
                     var10 = ap.a();
                     StringBuilder var630 = new StringBuilder();
                     as.a("NATIVE_CRASH", var10, var634, var635, var630.append(var16).append("\n").append(var8).append("\n").append(var22).toString(), var632);
                  } catch (Throwable var609) {
                     var10000 = var609;
                     var10001 = false;
                     break label5371;
                  }

                  label5372: {
                     as var631;
                     try {
                        var631 = this.b;
                     } catch (Throwable var607) {
                        var10000 = var607;
                        var10001 = false;
                        break label5372;
                     }

                     if (var631 == null) {
                        label5259:
                        try {
                           al.d("crashHandler is null. Won't upload native crash.");
                           return;
                        } catch (Throwable var602) {
                           var10000 = var602;
                           var10001 = false;
                           break label5259;
                        }
                     } else {
                        label5375: {
                           boolean var625;
                           label5279: {
                              label5278: {
                                 try {
                                    if (!var631.a(var632, true)) {
                                       break label5278;
                                    }
                                 } catch (Throwable var608) {
                                    var10000 = var608;
                                    var10001 = false;
                                    break label5375;
                                 }

                                 var625 = false;
                                 break label5279;
                              }

                              var625 = true;
                           }

                           var7 = null;

                           try {
                              if (NativeCrashHandler.getInstance() != null) {
                                 var7 = NativeCrashHandler.getDumpFilePath();
                              }
                           } catch (Throwable var606) {
                              var10000 = var606;
                              var10001 = false;
                              break label5375;
                           }

                           try {
                              be.a(true, var7);
                           } catch (Throwable var605) {
                              var10000 = var605;
                              var10001 = false;
                              break label5375;
                           }

                           if (var625) {
                              try {
                                 this.b.b(var632, true);
                              } catch (Throwable var604) {
                                 var10000 = var604;
                                 var10001 = false;
                                 break label5375;
                              }
                           }

                           label5261:
                           try {
                              this.b.a(var632);
                              at.a().t.b();
                              return;
                           } catch (Throwable var603) {
                              var10000 = var603;
                              var10001 = false;
                              break label5261;
                           }
                        }
                     }
                  }

                  var627 = var10000;
                  break label5368;
               }
            }
         }

         var627 = var10000;
      }

      if (!al.a(var627)) {
         var627.printStackTrace();
      }

   }

   public final CrashDetailBean packageCrashDatas(String var1, String var2, long var3, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, byte[] var14, Map var15, boolean var16, boolean var17) {
      var17 = at.a().i();
      if (var17) {
         al.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!");
      }

      CrashDetailBean var21 = new CrashDetailBean();
      var21.b = 1;
      var21.e = this.c.g();
      var21.f = this.c.o;
      var21.g = this.c.q();
      var21.m = this.c.f();
      var21.n = var5;
      String var20 = "";
      if (var17) {
         var5 = " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]";
      } else {
         var5 = "";
      }

      var21.o = var5;
      var21.p = var6;
      if (var7 == null) {
         var7 = var20;
      }

      var21.q = var7;
      var21.r = var3;
      var21.u = ap.c(var21.q.getBytes());
      var21.A = var1;
      var21.B = var2;
      var21.L = this.c.s();
      var21.h = this.c.p();
      var21.i = this.c.A();
      var21.v = var10;
      if (NativeCrashHandler.getInstance() != null) {
         var2 = NativeCrashHandler.getDumpFilePath();
      } else {
         var2 = null;
      }

      var5 = be.a(var2, var10);
      if (!ap.b(var5)) {
         var21.Z = var5;
      }

      var21.aa = be.b(var2);
      var21.w = be.a(var11, at.f, at.k, at.p);
      var21.x = be.a(var12, at.f, (String)null, true);
      var21.N = var9;
      var21.O = var8;
      var21.P = var13;
      var21.F = this.c.k();
      var21.G = this.c.j();
      var21.H = this.c.l();
      var21.I = ab.b(this.a);
      var21.J = ab.g();
      var21.K = ab.h();
      if (var16) {
         var21.C = ab.j();
         var21.D = ab.f();
         var21.E = ab.l();
         var21.y = ao.a();
         var21.Q = this.c.a;
         var21.R = this.c.a();
         var21.z = ap.a(this.c.Q, at.h);
         int var18 = var21.q.indexOf("java:\n");
         if (var18 > 0) {
            int var19 = var18 + 6;
            if (var19 < var21.q.length()) {
               var2 = var21.q;
               var2 = var2.substring(var19, var2.length() - 1);
               if (var2.length() > 0 && var21.z.containsKey(var21.B)) {
                  var5 = (String)var21.z.get(var21.B);
                  var18 = var5.indexOf(var2);
                  if (var18 > 0) {
                     var2 = var5.substring(var18);
                     var21.z.put(var21.B, var2);
                     var21.q = var21.q.substring(0, var19);
                     var21.q = var21.q + var2;
                  }
               }
            }
         }

         if (var1 == null) {
            var21.A = this.c.d;
         }

         var21.U = this.c.z();
         var21.V = this.c.x;
         var21.W = this.c.t();
         var21.X = this.c.y();
      } else {
         var21.C = -1L;
         var21.D = -1L;
         var21.E = -1L;
         if (var21.w == null) {
            var21.w = "This crash occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
         }

         var21.Q = -1L;
         var21.U = -1;
         var21.V = -1;
         var21.W = var15;
         var21.X = this.c.y();
         var21.z = null;
         if (var1 == null) {
            var21.A = "unknown(record)";
         }

         if (var14 != null) {
            var21.y = var14;
         }
      }

      return var21;
   }
}
