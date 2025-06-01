package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class bf implements Runnable {
   final Handler a;
   long b;
   boolean c;
   long d;
   private final String e;
   private final List f = new LinkedList();
   private final long g;

   bf(Handler var1, String var2) {
      this.a = var1;
      this.e = var2;
      this.b = 5000L;
      this.g = 5000L;
      this.c = true;
   }

   private Thread e() {
      return this.a.getLooper().getThread();
   }

   public final boolean a() {
      return !this.c && SystemClock.uptimeMillis() >= this.d + this.b;
   }

   public final long b() {
      return SystemClock.uptimeMillis() - this.d;
   }

   public final List c() {
      long var2 = System.currentTimeMillis();
      List var4 = this.f;
      synchronized(var4){}

      Throwable var10000;
      boolean var10001;
      label245: {
         ArrayList var5;
         try {
            var5 = new ArrayList(this.f.size());
         } catch (Throwable var26) {
            var10000 = var26;
            var10001 = false;
            break label245;
         }

         int var1 = 0;

         while(true) {
            try {
               if (var1 >= this.f.size()) {
                  break;
               }

               ba var6 = (ba)this.f.get(var1);
               if (!var6.e && var2 - var6.b < 200000L) {
                  var5.add(var6);
                  var6.e = true;
               }
            } catch (Throwable var25) {
               var10000 = var25;
               var10001 = false;
               break label245;
            }

            ++var1;
         }

         label226:
         try {
            return var5;
         } catch (Throwable var24) {
            var10000 = var24;
            var10001 = false;
            break label226;
         }
      }

      while(true) {
         Throwable var27 = var10000;

         try {
            throw var27;
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            continue;
         }
      }
   }

   public final void d() {
      StringBuilder var7 = new StringBuilder(1024);
      long var5 = System.nanoTime();

      boolean var10001;
      label296: {
         SecurityException var10000;
         label300: {
            StackTraceElement[] var8;
            try {
               var8 = this.e().getStackTrace();
               if (var8.length == 0) {
                  var7.append("Thread does not have stack trace.\n");
                  break label296;
               }
            } catch (SecurityException var32) {
               var10000 = var32;
               var10001 = false;
               break label300;
            }

            int var2;
            try {
               var2 = var8.length;
            } catch (SecurityException var31) {
               var10000 = var31;
               var10001 = false;
               break label300;
            }

            int var1 = 0;

            while(true) {
               if (var1 >= var2) {
                  break label296;
               }

               try {
                  var7.append(var8[var1]).append("\n");
               } catch (SecurityException var30) {
                  var10000 = var30;
                  var10001 = false;
                  break;
               }

               ++var1;
            }
         }

         SecurityException var34 = var10000;
         var7.append("getStackTrace() encountered:\n").append(var34.getMessage()).append("\n");
         al.a(var34);
      }

      long var3 = System.nanoTime();
      ba var36 = new ba(var7.toString(), System.currentTimeMillis());
      var36.d = var3 - var5;
      String var33 = this.e().getName();
      if (var33 == null) {
         var33 = "";
      }

      var36.a = var33;
      List var35 = this.f;
      synchronized(var35){}

      while(true) {
         Throwable var38;
         label272: {
            try {
               if (this.f.size() >= 32) {
                  this.f.remove(0);
                  continue;
               }
            } catch (Throwable var29) {
               var38 = var29;
               var10001 = false;
               break label272;
            }

            label265:
            try {
               this.f.add(var36);
               return;
            } catch (Throwable var28) {
               var38 = var28;
               var10001 = false;
               break label265;
            }
         }

         while(true) {
            Throwable var37 = var38;

            try {
               throw var37;
            } catch (Throwable var27) {
               var38 = var27;
               var10001 = false;
               continue;
            }
         }
      }
   }

   public final void run() {
      this.c = true;
      this.b = this.g;
   }
}
