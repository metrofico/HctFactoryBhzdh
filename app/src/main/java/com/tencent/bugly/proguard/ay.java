package com.tencent.bugly.proguard;

import android.app.ActivityManager;
import android.content.Context;
import android.os.FileObserver;
import android.os.Build.VERSION;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ay {
   public static ay f;
   public final AtomicBoolean a = new AtomicBoolean(false);
   public final ActivityManager b;
   final aa c;
   final ak d;
   String e;
   private final Context g;
   private final ac h;
   private final as i;
   private final Object j = new Object();
   private FileObserver k;
   private boolean l = true;
   private bg m;
   private int n;
   private long o = 0L;

   public ay(Context var1, ac var2, aa var3, ak var4, as var5) {
      Context var6 = ap.a(var1);
      this.g = var6;
      this.b = (ActivityManager)var6.getSystemService("activity");
      if (ap.b(NativeCrashHandler.getDumpFilePath())) {
         this.e = var1.getDir("bugly", 0).getAbsolutePath();
      } else {
         this.e = NativeCrashHandler.getDumpFilePath();
      }

      this.c = var3;
      this.d = var4;
      this.h = var2;
      this.i = var5;
   }

   private CrashDetailBean a(ax var1) {
      CrashDetailBean var4 = new CrashDetailBean();

      Throwable var10000;
      label313: {
         boolean var10001;
         try {
            var4.C = ab.j();
            var4.D = ab.f();
            var4.E = ab.l();
            var4.F = this.c.k();
            var4.G = this.c.j();
            var4.H = this.c.l();
            var4.I = ab.b(this.g);
            var4.J = ab.g();
            var4.K = ab.h();
            var4.b = 3;
            var4.e = this.c.g();
            var4.f = this.c.o;
            var4.g = this.c.q();
            var4.m = this.c.f();
            var4.n = "ANR_EXCEPTION";
            var4.o = var1.f;
            var4.q = var1.g;
            HashMap var3 = new HashMap();
            var4.T = var3;
            var4.T.put("BUGLY_CR_01", var1.e);
         } catch (Throwable var34) {
            var10000 = var34;
            var10001 = false;
            break label313;
         }

         int var2 = -1;

         try {
            if (var4.q != null) {
               var2 = var4.q.indexOf("\n");
            }
         } catch (Throwable var33) {
            var10000 = var33;
            var10001 = false;
            break label313;
         }

         String var36;
         if (var2 > 0) {
            try {
               var36 = var4.q.substring(0, var2);
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label313;
            }
         } else {
            var36 = "GET_FAIL";
         }

         try {
            var4.p = var36;
            var4.r = var1.c;
            if (var4.q != null) {
               var4.u = ap.c(var4.q.getBytes());
            }
         } catch (Throwable var31) {
            var10000 = var31;
            var10001 = false;
            break label313;
         }

         label293:
         try {
            var4.z = var1.b;
            var4.A = var1.a;
            var4.B = "main(1)";
            var4.L = this.c.s();
            var4.h = this.c.p();
            var4.i = this.c.A();
            var4.v = var1.d;
            var4.P = this.c.u;
            var4.Q = this.c.a;
            var4.R = this.c.a();
            var4.U = this.c.z();
            var4.V = this.c.x;
            var4.W = this.c.t();
            var4.X = this.c.y();
            var4.y = ao.a();
            return var4;
         } catch (Throwable var30) {
            var10000 = var30;
            var10001 = false;
            break label293;
         }
      }

      Throwable var35 = var10000;
      if (!al.a(var35)) {
         var35.printStackTrace();
      }

      return var4;
   }

   public static ay a() {
      synchronized(ay.class){}

      ay var0;
      try {
         var0 = f;
      } finally {
         ;
      }

      return var0;
   }

   private static String a(List var0, long var1) {
      if (var0 != null && !var0.isEmpty()) {
         StringBuilder var8 = new StringBuilder(4096);
         var8.append("\n>>>>> 以下为anr过程中主线程堆栈记录，可根据堆栈出现次数推测在该堆栈阻塞的时间，出现次数越多对anr贡献越大，越可能是造成anr的原因 >>>>>\n");
         var8.append("\n>>>>> Thread Stack Traces Records Start >>>>>\n");

         for(int var3 = 0; var3 < var0.size(); ++var3) {
            ba var7 = (ba)var0.get(var3);
            var8.append("Thread name:").append(var7.a).append("\n");
            long var4 = var7.b - var1;
            String var6;
            if (var4 <= 0L) {
               var6 = "before ";
            } else {
               var6 = "after ";
            }

            var8.append("Got ").append(var6);
            var8.append("anr:").append(Math.abs(var4)).append("ms\n");
            var8.append(var7.c).append("\n");
            if (var8.length() * 2 >= 101376) {
               break;
            }
         }

         var8.append("\n<<<<< Thread Stack Traces Records End <<<<<\n");
         return var8.toString();
      } else {
         return "main thread stack not enable";
      }
   }

   // $FF: synthetic method
   static void a(ay var0) {
      long var1 = at.j + System.currentTimeMillis() - ap.b();
      am.a(var0.e, "bugly_trace_", ".txt", var1);
      am.a(var0.e, "manual_bugly_trace_", ".txt", var1);
      am.a(var0.e, "main_stack_record_", ".txt", var1);
      am.a(var0.e, "main_stack_record_", ".txt.merged", var1);
   }

   private static boolean a(String var0, String var1, String var2) {
      TraceFileHelper.a var3 = TraceFileHelper.readTargetDumpInfo(var2, var0, true);
      if (var3 != null && var3.d != null && !var3.d.isEmpty()) {
         StringBuilder var4 = new StringBuilder(1024);
         String[] var5 = (String[])var3.d.get("main");
         if (var5 != null && var5.length >= 3) {
            var4.append("\"main\" tid=").append(var5[2]).append(" :\n").append(var5[0]).append("\n").append(var5[1]).append("\n\n");
         }

         Iterator var7 = var3.d.entrySet().iterator();

         while(var7.hasNext()) {
            Map.Entry var6 = (Map.Entry)var7.next();
            if (!((String)var6.getKey()).equals("main") && var6.getValue() != null && ((String[])var6.getValue()).length >= 3) {
               var4.append("\"").append((String)var6.getKey()).append("\" tid=").append(((String[])var6.getValue())[2]).append(" :\n").append(((String[])var6.getValue())[0]).append("\n").append(((String[])var6.getValue())[1]).append("\n\n");
            }
         }

         return am.a(var1, var4.toString(), var4.length() * 2);
      } else {
         al.e("not found trace dump for %s", var2);
         return false;
      }
   }

   private void c() {
      synchronized(this){}

      Throwable var10000;
      Throwable var23;
      label233: {
         boolean var10001;
         try {
            if (this.e()) {
               al.d("start when started!");
               return;
            }
         } catch (Throwable var22) {
            var10000 = var22;
            var10001 = false;
            break label233;
         }

         FileObserver var1;
         try {
            var1 = new FileObserver(this, "/data/anr/") {
               final ay a;

               {
                  this.a = var1;
               }

               public final void onEvent(int var1, String var2) {
                  if (var2 != null) {
                     var2 = "/data/anr/".concat(String.valueOf(var2));
                     al.d("watching file %s", var2);
                     if (!var2.contains("trace")) {
                        al.d("not anr file %s", var2);
                     } else {
                        this.a.d.a(new Runnable(this, var2) {
                           final String a;
                           final <undefinedtype> b;

                           {
                              this.b = var1;
                              this.a = var2;
                           }

                           public final void run() {
                              ay var6 = this.b.a;
                              String var7 = this.a;
                              if (var6.a(true)) {
                                 Throwable var10000;
                                 label302: {
                                    TraceFileHelper.a var5;
                                    boolean var10001;
                                    try {
                                       al.c("read trace first dump for create time!");
                                       var5 = TraceFileHelper.readFirstDumpInfo(var7, false);
                                    } catch (Throwable var36) {
                                       var10000 = var36;
                                       var10001 = false;
                                       break label302;
                                    }

                                    long var1;
                                    if (var5 != null) {
                                       try {
                                          var1 = var5.c;
                                       } catch (Throwable var35) {
                                          var10000 = var35;
                                          var10001 = false;
                                          break label302;
                                       }
                                    } else {
                                       var1 = -1L;
                                    }

                                    long var3 = var1;
                                    if (var1 == -1L) {
                                       try {
                                          al.d("trace dump fail could not get time!");
                                          var3 = System.currentTimeMillis();
                                       } catch (Throwable var34) {
                                          var10000 = var34;
                                          var10001 = false;
                                          break label302;
                                       }
                                    }

                                    try {
                                       if (var6.a(var3)) {
                                          return;
                                       }
                                    } catch (Throwable var37) {
                                       var10000 = var37;
                                       var10001 = false;
                                       break label302;
                                    }

                                    label279:
                                    try {
                                       var6.a(var3, var7);
                                       return;
                                    } catch (Throwable var33) {
                                       var10000 = var33;
                                       var10001 = false;
                                       break label279;
                                    }
                                 }

                                 Throwable var38 = var10000;
                                 if (!al.a(var38)) {
                                    var38.printStackTrace();
                                 }

                                 al.e("handle anr error %s", var38.getClass().toString());
                              }

                           }
                        });
                     }
                  }
               }
            };
            this.k = var1;
         } catch (Throwable var21) {
            var10000 = var21;
            var10001 = false;
            break label233;
         }

         try {
            var1.startWatching();
            al.a("start anr monitor!");
            ak var24 = this.d;
            Runnable var2 = new Runnable(this) {
               final ay a;

               {
                  this.a = var1;
               }

               public final void run() {
                  ay.a(this.a);
               }
            };
            var24.a(var2);
         } catch (Throwable var20) {
            var23 = var20;

            try {
               this.k = null;
               al.d("start anr monitor failed!");
               if (!al.a(var23)) {
                  var23.printStackTrace();
               }
            } catch (Throwable var19) {
               var10000 = var19;
               var10001 = false;
               break label233;
            }

            return;
         }

         return;
      }

      var23 = var10000;
      throw var23;
   }

   private void c(boolean var1) {
      synchronized(this){}

      Throwable var10000;
      label273: {
         boolean var10001;
         label272: {
            try {
               if (VERSION.SDK_INT <= 19) {
                  break label272;
               }
            } catch (Throwable var32) {
               var10000 = var32;
               var10001 = false;
               break label273;
            }

            if (var1) {
               try {
                  this.g();
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label273;
               }

               return;
            } else {
               try {
                  this.h();
               } catch (Throwable var31) {
                  var10000 = var31;
                  var10001 = false;
                  break label273;
               }

               return;
            }
         }

         if (var1) {
            label256: {
               try {
                  this.c();
               } catch (Throwable var28) {
                  var10000 = var28;
                  var10001 = false;
                  break label256;
               }

               return;
            }
         } else {
            label259: {
               try {
                  this.d();
               } catch (Throwable var29) {
                  var10000 = var29;
                  var10001 = false;
                  break label259;
               }

               return;
            }
         }
      }

      Throwable var2 = var10000;
      throw var2;
   }

   private void d() {
      synchronized(this){}

      Throwable var10000;
      Throwable var1;
      label163: {
         boolean var10001;
         try {
            if (!this.e()) {
               al.d("close when closed!");
               return;
            }
         } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            break label163;
         }

         try {
            this.k.stopWatching();
            this.k = null;
            al.d("close anr monitor!");
         } catch (Throwable var12) {
            var1 = var12;

            try {
               al.d("stop anr monitor failed!");
               if (!al.a(var1)) {
                  var1.printStackTrace();
               }
            } catch (Throwable var11) {
               var10000 = var11;
               var10001 = false;
               break label163;
            }

            return;
         }

         return;
      }

      var1 = var10000;
      throw var1;
   }

   private void d(boolean var1) {
      synchronized(this){}

      try {
         if (this.l != var1) {
            al.a("user change anr %b", var1);
            this.l = var1;
         }
      } finally {
         ;
      }

   }

   private boolean e() {
      synchronized(this){}
      boolean var4 = false;

      FileObserver var2;
      try {
         var4 = true;
         var2 = this.k;
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

   private boolean f() {
      synchronized(this){}

      boolean var1;
      try {
         var1 = this.l;
      } finally {
         ;
      }

      return var1;
   }

   private void g() {
      // $FF: Couldn't be decompiled
   }

   private void h() {
      // $FF: Couldn't be decompiled
   }

   final void a(long param1, String param3) {
      // $FF: Couldn't be decompiled
   }

   public final boolean a(long var1) {
      if (Math.abs(var1 - this.o) < 10000L) {
         al.d("should not process ANR too Fre in %dms", 10000);
         return true;
      } else {
         this.o = var1;
         return false;
      }
   }

   public final boolean a(boolean var1) {
      boolean var2 = this.a.compareAndSet(var1 ^ true, var1);
      al.c("tryChangeAnrState to %s, success:%s", var1, var2);
      return var2;
   }

   public final void b() {
      synchronized(this){}

      try {
         al.d("customer decides whether to open or close.");
      } finally {
         ;
      }

   }

   public final void b(boolean var1) {
      this.d(var1);
      boolean var2 = this.f();
      ac var3 = ac.a();
      var1 = var2;
      if (var3 != null) {
         if (var2 && var3.c().f) {
            var1 = true;
         } else {
            var1 = false;
         }
      }

      if (var1 != this.e()) {
         al.a("anr changed to %b", var1);
         this.c(var1);
      }

   }
}
