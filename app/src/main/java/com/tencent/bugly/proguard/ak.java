package com.tencent.bugly.proguard;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ak {
   private static final AtomicInteger a = new AtomicInteger(1);
   private static ak b;
   private ScheduledExecutorService c = null;

   protected ak() {
      ScheduledExecutorService var1 = Executors.newScheduledThreadPool(3, new ThreadFactory(this) {
         final ak a;

         {
            this.a = var1;
         }

         public final Thread newThread(Runnable var1) {
            Thread var2 = new Thread(var1);
            var2.setName("BuglyThread-" + ak.a.getAndIncrement());
            return var2;
         }
      });
      this.c = var1;
      if (var1 == null || var1.isShutdown()) {
         al.d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!");
      }

   }

   public static ak a() {
      synchronized(ak.class){}

      ak var0;
      try {
         if (b == null) {
            var0 = new ak();
            b = var0;
         }

         var0 = b;
      } finally {
         ;
      }

      return var0;
   }

   public final boolean a(Runnable var1) {
      synchronized(this){}

      Throwable var10000;
      Throwable var32;
      label318: {
         boolean var10001;
         try {
            if (!this.c()) {
               al.d("[AsyncTaskHandler] Async handler was closed, should not post task.");
               return false;
            }
         } catch (Throwable var31) {
            var10000 = var31;
            var10001 = false;
            break label318;
         }

         if (var1 == null) {
            label304: {
               try {
                  al.d("[AsyncTaskHandler] Task input is null.");
               } catch (Throwable var28) {
                  var10000 = var28;
                  var10001 = false;
                  break label304;
               }

               return false;
            }
         } else {
            label323: {
               try {
                  al.c("[AsyncTaskHandler] Post a normal task: %s", var1.getClass().getName());
               } catch (Throwable var30) {
                  var10000 = var30;
                  var10001 = false;
                  break label323;
               }

               try {
                  this.c.execute(var1);
               } catch (Throwable var29) {
                  var32 = var29;

                  try {
                     if (p.c) {
                        var32.printStackTrace();
                     }
                  } catch (Throwable var27) {
                     var10000 = var27;
                     var10001 = false;
                     break label323;
                  }

                  return false;
               }

               return true;
            }
         }
      }

      var32 = var10000;
      throw var32;
   }

   public final boolean a(Runnable var1, long var2) {
      synchronized(this){}

      Throwable var10000;
      Throwable var24;
      label261: {
         boolean var10001;
         try {
            if (!this.c()) {
               al.d("[AsyncTaskHandler] Async handler was closed, should not post task.");
               return false;
            }
         } catch (Throwable var23) {
            var10000 = var23;
            var10001 = false;
            break label261;
         }

         if (var2 <= 0L) {
            var2 = 0L;
         }

         try {
            al.c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", var2, var1.getClass().getName());
         } catch (Throwable var22) {
            var10000 = var22;
            var10001 = false;
            break label261;
         }

         try {
            this.c.schedule(var1, var2, TimeUnit.MILLISECONDS);
         } catch (Throwable var21) {
            var24 = var21;

            try {
               if (p.c) {
                  var24.printStackTrace();
               }
            } catch (Throwable var20) {
               var10000 = var20;
               var10001 = false;
               break label261;
            }

            return false;
         }

         return true;
      }

      var24 = var10000;
      throw var24;
   }

   public final void b() {
      synchronized(this){}

      Throwable var10000;
      label81: {
         ScheduledExecutorService var1;
         boolean var10001;
         try {
            var1 = this.c;
         } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            break label81;
         }

         if (var1 == null) {
            return;
         }

         label72:
         try {
            if (!var1.isShutdown()) {
               al.c("[AsyncTaskHandler] Close async handler.");
               this.c.shutdownNow();
            }

            return;
         } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            break label72;
         }
      }

      Throwable var8 = var10000;
      throw var8;
   }

   public final boolean c() {
      synchronized(this){}

      boolean var1;
      label91: {
         Throwable var10000;
         label96: {
            boolean var10001;
            ScheduledExecutorService var2;
            try {
               var2 = this.c;
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label96;
            }

            if (var2 == null) {
               break label91;
            }

            try {
               var1 = var2.isShutdown();
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label96;
            }

            if (!var1) {
               var1 = true;
               return var1;
            }
            break label91;
         }

         Throwable var9 = var10000;
         throw var9;
      }

      var1 = false;
      return var1;
   }
}
