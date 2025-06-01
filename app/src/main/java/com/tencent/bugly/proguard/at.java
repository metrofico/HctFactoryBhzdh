package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class at {
   private static at D;
   public static int a;
   public static boolean b;
   public static int d;
   public static boolean e;
   public static int f;
   public static int g;
   public static int h;
   public static long i;
   public static long j;
   public static String k;
   public static boolean l;
   public static String m;
   public static int n;
   public static boolean o;
   public static boolean p;
   public static String q;
   public static String r;
   public Boolean A;
   public int B = 31;
   public boolean C = false;
   public final Context c;
   public final as s;
   public final av t;
   public final NativeCrashHandler u;
   public final ac v;
   public final ak w;
   public final ay x;
   public BuglyStrategy.a y;
   public aw z;

   private at(Context var1, ak var2, boolean var3, BuglyStrategy.a var4) {
      a = 1004;
      var1 = ap.a(var1);
      this.c = var1;
      ac var5 = ac.a();
      this.v = var5;
      this.w = var2;
      this.y = var4;
      this.z = null;
      as var8 = new as(var1, ai.a(), com.tencent.bugly.proguard.w.a(), var5, var4);
      this.s = var8;
      aa var6 = aa.a(var1);
      this.t = new av(var1, var8, var5, var6);
      NativeCrashHandler var7 = NativeCrashHandler.getInstance(var1, var6, var8, var5, var2, var3, (String)null);
      this.u = var7;
      var6.N = var7;
      if (ay.f == null) {
         ay.f = new ay(var1, var5, var6, var2, var8);
      }

      this.x = ay.f;
   }

   public static at a() {
      synchronized(at.class){}

      at var0;
      try {
         var0 = D;
      } finally {
         ;
      }

      return var0;
   }

   public static at a(Context var0, boolean var1, BuglyStrategy.a var2) {
      synchronized(at.class){}

      at var6;
      try {
         if (D == null) {
            at var3 = new at(var0, ak.a(), var1, var2);
            D = var3;
         }

         var6 = D;
      } finally {
         ;
      }

      return var6;
   }

   // $FF: synthetic method
   static av a(at var0) {
      return var0.t;
   }

   public final void a(long var1) {
      ak.a().a(new Thread(this) {
         final at a;

         {
            this.a = var1;
         }

         public final void run() {
            boolean var4 = ap.a(this.a.c, "local_crash_lock");
            byte var2 = 0;
            if (!var4) {
               al.c("Failed to lock file for uploading local crash.");
            } else {
               ag var9 = ag.a.a();
               List var5 = ag.a();
               if (var5 != null && !var5.isEmpty()) {
                  al.c("sla load local data list size:%s", var5.size());
                  Iterator var8 = var5.iterator();
                  ArrayList var7 = new ArrayList();

                  while(var8.hasNext()) {
                     ag.b var6 = (ag.b)var8.next();
                     boolean var1;
                     if (var6.b < ap.b() - 604800000L) {
                        var1 = true;
                     } else {
                        var1 = false;
                     }

                     if (var1) {
                        al.c("sla local data is expired:%s", var6.c);
                        var7.add(var6);
                        var8.remove();
                     }
                  }

                  ag.d(var7);
                  var9.b(var5);
               } else {
                  al.c("sla local data is null");
               }

               Object var11 = as.a();
               if (var11 != null && ((List)var11).size() > 0) {
                  al.c("Size of crash list: %s", ((List)var11).size());
                  int var3 = ((List)var11).size();
                  if ((long)var3 > 20L) {
                     ArrayList var12 = new ArrayList();
                     Collections.sort((List)var11);

                     for(int var10 = var2; (long)var10 < 20L; ++var10) {
                        var12.add(((List)var11).get(var3 - 1 - var10));
                     }

                     var11 = var12;
                  }

                  this.a.s.a((List)var11, 0L, false, false, false);
               } else {
                  al.c("no crash need to be uploaded at this start");
               }

               ap.b(this.a.c, "local_crash_lock");
            }
         }
      }, var1);
   }

   public final void a(CrashDetailBean var1) {
      this.s.b(var1);
   }

   public final void a(boolean var1, boolean var2, boolean var3) {
      synchronized(this){}

      try {
         this.u.testNativeCrash(var1, var2, var3);
      } finally {
         ;
      }

   }

   public final void b() {
      synchronized(this){}

      try {
         this.t.a();
         this.e();
         this.f();
      } finally {
         ;
      }

   }

   public final void c() {
      synchronized(this){}

      try {
         this.t.b();
         this.d();
         this.g();
      } finally {
         ;
      }

   }

   public final void d() {
      this.u.setUserOpened(false);
   }

   public final void e() {
      this.u.setUserOpened(true);
   }

   public final void f() {
      (new Handler(Looper.getMainLooper())).post(new Runnable(this) {
         final at a;

         {
            this.a = var1;
         }

         public final void run() {
            NativeCrashHandler.getInstance().unBlockSigquit(true);
         }
      });
      this.x.b(true);
   }

   public final void g() {
      (new Handler(Looper.getMainLooper())).post(new Runnable(this) {
         final at a;

         {
            this.a = var1;
         }

         public final void run() {
            NativeCrashHandler.getInstance().unBlockSigquit(false);
         }
      });
      this.x.b(false);
   }

   public final void h() {
      synchronized(this){}
      int var1 = 0;

      while(true) {
         int var2 = var1 + 1;
         if (var1 >= 30) {
            return;
         }

         try {
            al.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", var2);
            ap.b(5000L);
         } catch (Throwable var9) {
            Throwable var3 = var9;

            try {
               if (!al.a(var3)) {
                  var3.printStackTrace();
               }
            } finally {
               ;
            }

            return;
         }

         var1 = var2;
      }
   }

   public final boolean i() {
      return this.x.a.get();
   }

   public final boolean j() {
      return (this.B & 16) > 0;
   }

   public final boolean k() {
      return (this.B & 8) > 0;
   }
}
