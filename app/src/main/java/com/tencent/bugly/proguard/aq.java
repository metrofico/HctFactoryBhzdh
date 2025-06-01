package com.tencent.bugly.proguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public final class aq extends BroadcastReceiver {
   private static aq d;
   private IntentFilter a = new IntentFilter();
   private Context b;
   private String c;
   private boolean e = true;

   // $FF: synthetic method
   static IntentFilter a(aq var0) {
      return var0.a;
   }

   public static aq a() {
      synchronized(aq.class){}

      aq var0;
      try {
         if (d == null) {
            var0 = new aq();
            d = var0;
         }

         var0 = d;
      } finally {
         ;
      }

      return var0;
   }

   private boolean a(Context var1, Intent var2) {
      Throwable var10000;
      label909: {
         synchronized(this){}
         if (var1 != null && var2 != null) {
            boolean var10001;
            try {
               if (!var2.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                  return false;
               }
            } catch (Throwable var97) {
               var10000 = var97;
               var10001 = false;
               break label909;
            }

            label891: {
               try {
                  if (!this.e) {
                     break label891;
                  }

                  this.e = false;
               } catch (Throwable var96) {
                  var10000 = var96;
                  var10001 = false;
                  break label909;
               }

               return true;
            }

            String var101;
            try {
               var101 = ab.c(this.b);
               al.c("is Connect BC ".concat(String.valueOf(var101)));
               StringBuilder var5 = new StringBuilder();
               al.a("network %s changed to %s", var5.append(this.c).toString(), String.valueOf(var101));
            } catch (Throwable var95) {
               var10000 = var95;
               var10001 = false;
               break label909;
            }

            if (var101 == null) {
               try {
                  this.c = null;
               } catch (Throwable var89) {
                  var10000 = var89;
                  var10001 = false;
                  break label909;
               }

               return true;
            } else {
               aa var98;
               long var3;
               String var6;
               ac var102;
               ai var7;
               try {
                  var6 = this.c;
                  this.c = var101;
                  var3 = System.currentTimeMillis();
                  var102 = ac.a();
                  var7 = ai.a();
                  var98 = aa.a(var1);
               } catch (Throwable var94) {
                  var10000 = var94;
                  var10001 = false;
                  break label909;
               }

               if (var102 == null || var7 == null || var98 == null) {
                  try {
                     al.d("not inited BC not work");
                  } catch (Throwable var93) {
                     var10000 = var93;
                     var10001 = false;
                     break label909;
                  }

                  return true;
               }

               at var99;
               try {
                  if (var101.equals(var6) || var3 - var7.a(at.a) <= 30000L) {
                     return true;
                  }

                  al.a("try to upload crash on network changed.");
                  var99 = at.a();
               } catch (Throwable var92) {
                  var10000 = var92;
                  var10001 = false;
                  break label909;
               }

               if (var99 != null) {
                  try {
                     var99.a(0L);
                  } catch (Throwable var91) {
                     var10000 = var91;
                     var10001 = false;
                     break label909;
                  }
               }

               try {
                  al.a("try to upload userinfo on network changed.");
                  s.b.b();
               } catch (Throwable var90) {
                  var10000 = var90;
                  var10001 = false;
                  break label909;
               }

               return true;
            }
         }

         return false;
      }

      Throwable var100 = var10000;
      throw var100;
   }

   // $FF: synthetic method
   static Context b(aq var0) {
      return var0.b;
   }

   // $FF: synthetic method
   static aq b() {
      return d;
   }

   public final void a(Context var1) {
      synchronized(this){}

      try {
         this.b = var1;
         Runnable var4 = new Runnable(this, this) {
            final aq a;
            final aq b;

            {
               this.b = var1;
               this.a = var2;
            }

            public final void run() {
               // $FF: Couldn't be decompiled
            }
         };
         ap.a(var4);
      } finally {
         ;
      }

   }

   public final void a(String var1) {
      synchronized(this){}

      try {
         if (!this.a.hasAction(var1)) {
            this.a.addAction(var1);
         }

         al.c("add action %s", var1);
      } finally {
         ;
      }

   }

   public final void b(Context var1) {
      synchronized(this){}

      try {
         al.a(this.getClass(), "Unregister broadcast receiver of Bugly.");
         var1.unregisterReceiver(this);
         this.b = var1;
      } catch (Throwable var7) {
         Throwable var8 = var7;

         try {
            if (!al.a(var8)) {
               var8.printStackTrace();
            }
         } finally {
            ;
         }

         return;
      }

   }

   public final void onReceive(Context var1, Intent var2) {
      try {
         this.a(var1, var2);
      } catch (Throwable var4) {
         if (!al.a(var4)) {
            var4.printStackTrace();
         }

         return;
      }
   }
}
