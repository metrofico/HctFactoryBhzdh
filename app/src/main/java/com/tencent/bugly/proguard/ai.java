package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public final class ai {
   private static ai b;
   public ah a;
   private final w c;
   private final Context d;
   private Map e = new HashMap();
   private long f;
   private long g;
   private LinkedBlockingQueue h = new LinkedBlockingQueue();
   private LinkedBlockingQueue i = new LinkedBlockingQueue();
   private final Object j = new Object();
   private long k = 0L;
   private int l = 0;

   private ai(Context var1) {
      this.d = var1;
      this.c = w.a();
   }

   public static ai a() {
      synchronized(ai.class){}

      ai var0;
      try {
         var0 = b;
      } finally {
         ;
      }

      return var0;
   }

   public static ai a(Context var0) {
      synchronized(ai.class){}

      ai var4;
      try {
         if (b == null) {
            ai var1 = new ai(var0);
            b = var1;
         }

         var4 = b;
      } finally {
         ;
      }

      return var4;
   }

   // $FF: synthetic method
   static Object a(ai var0) {
      return var0.j;
   }

   private void a(int param1, int param2, byte[] param3, String param4, String param5, ah param6, boolean param7) {
      // $FF: Couldn't be decompiled
   }

   private void a(int param1, LinkedBlockingQueue param2) {
      // $FF: Couldn't be decompiled
   }

   private void a(Runnable var1, long var2) {
      if (var1 == null) {
         al.d("[UploadManager] Upload task should not be null");
      } else {
         al.c("[UploadManager] Execute synchronized upload task (pid=%d | tid=%d)", Process.myPid(), Process.myTid());
         Thread var4 = ap.a(var1, "BUGLY_SYNC_UPLOAD");
         if (var4 == null) {
            al.e("[UploadManager] Failed to start a thread to execute synchronized upload task, add it to queue.");
            this.a(var1, true);
         } else {
            try {
               var4.join(var2);
            } catch (Throwable var6) {
               al.e("[UploadManager] Failed to join upload synchronized task with message: %s. Add it to queue.", var6.getMessage());
               this.a(var1, true);
               this.b();
               return;
            }
         }
      }
   }

   private void a(Runnable var1, boolean var2, boolean var3, long var4) {
      al.c("[UploadManager] Add upload task (pid=%d | tid=%d)", Process.myPid(), Process.myTid());
      if (var3) {
         this.a(var1, var4);
      } else {
         this.a(var1, var2);
         this.b();
      }
   }

   private static void a(LinkedBlockingQueue var0, LinkedBlockingQueue var1, int var2) {
      for(int var3 = 0; var3 < var2; ++var3) {
         Runnable var4 = (Runnable)var0.peek();
         if (var4 == null) {
            break;
         }

         try {
            var1.put(var4);
            var0.poll();
         } catch (Throwable var6) {
            al.e("[UploadManager] Failed to add upload task to temp urgent queue: %s", var6.getMessage());
            continue;
         }
      }

   }

   private boolean a(Runnable param1, boolean param2) {
      // $FF: Couldn't be decompiled
   }

   // $FF: synthetic method
   static int b(ai var0) {
      int var1 = var0.l - 1;
      var0.l = var1;
      return var1;
   }

   private void b() {
      ak var5 = ak.a();
      LinkedBlockingQueue var6 = new LinkedBlockingQueue();
      LinkedBlockingQueue var4 = new LinkedBlockingQueue();
      Object var3 = this.j;
      synchronized(var3){}

      Throwable var10000;
      boolean var10001;
      label355: {
         int var1;
         int var2;
         try {
            al.c("[UploadManager] Try to poll all upload task need and put them into temp queue (pid=%d | tid=%d)", Process.myPid(), Process.myTid());
            var2 = this.h.size();
            var1 = this.i.size();
         } catch (Throwable var36) {
            var10000 = var36;
            var10001 = false;
            break label355;
         }

         if (var2 == 0 && var1 == 0) {
            label334:
            try {
               al.c("[UploadManager] There is no upload task in queue.");
               return;
            } catch (Throwable var33) {
               var10000 = var33;
               var10001 = false;
               break label334;
            }
         } else {
            label356: {
               label343: {
                  if (var5 != null) {
                     try {
                        if (var5.c()) {
                           break label343;
                        }
                     } catch (Throwable var35) {
                        var10000 = var35;
                        var10001 = false;
                        break label356;
                     }
                  }

                  var1 = 0;
               }

               try {
                  a(this.h, var6, var2);
                  a(this.i, var4, var1);
               } catch (Throwable var34) {
                  var10000 = var34;
                  var10001 = false;
                  break label356;
               }

               this.a(var2, var6);
               if (var1 > 0) {
                  al.c("[UploadManager] Execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", var1, Process.myPid(), Process.myTid());
               }

               ak var37 = ak.a();
               if (var37 != null) {
                  var37.a(new Runnable(this, var1, var4) {
                     final int a;
                     final LinkedBlockingQueue b;
                     final ai c;

                     {
                        this.c = var1;
                        this.a = var2;
                        this.b = var3;
                     }

                     public final void run() {
                        for(int var1 = 0; var1 < this.a; ++var1) {
                           Runnable var2 = (Runnable)this.b.poll();
                           if (var2 == null) {
                              break;
                           }

                           var2.run();
                        }

                     }
                  });
               }

               return;
            }
         }
      }

      while(true) {
         Throwable var38 = var10000;

         try {
            throw var38;
         } catch (Throwable var32) {
            var10000 = var32;
            var10001 = false;
            continue;
         }
      }
   }

   public final long a(int var1) {
      synchronized(this){}
      Throwable var10000;
      boolean var10001;
      if (var1 >= 0) {
         label120: {
            Long var4;
            try {
               var4 = (Long)this.e.get(var1);
            } catch (Throwable var15) {
               var10000 = var15;
               var10001 = false;
               break label120;
            }

            if (var4 == null) {
               return 0L;
            }

            long var2;
            try {
               var2 = var4;
            } catch (Throwable var14) {
               var10000 = var14;
               var10001 = false;
               break label120;
            }

            return var2;
         }
      } else {
         label116:
         try {
            al.e("[UploadManager] Unknown upload ID: %d", var1);
            return 0L;
         } catch (Throwable var16) {
            var10000 = var16;
            var10001 = false;
            break label116;
         }
      }

      Throwable var17 = var10000;
      throw var17;
   }

   public final long a(boolean var1) {
      long var9 = ap.b();
      byte var2;
      if (var1) {
         var2 = 5;
      } else {
         var2 = 3;
      }

      List var11 = this.c.a(var2);
      long var3;
      long var5;
      if (var11 != null && var11.size() > 0) {
         long var7 = 0L;
         var5 = var7;

         label522: {
            Throwable var10000;
            label534: {
               y var12;
               boolean var10001;
               try {
                  var12 = (y)var11.get(0);
               } catch (Throwable var54) {
                  var10000 = var54;
                  var10001 = false;
                  break label534;
               }

               var5 = var7;
               var3 = var7;

               try {
                  if (var12.e < var9) {
                     break label522;
                  }
               } catch (Throwable var53) {
                  var10000 = var53;
                  var10001 = false;
                  break label534;
               }

               var5 = var7;

               try {
                  var3 = ap.d(var12.g);
               } catch (Throwable var52) {
                  var10000 = var52;
                  var10001 = false;
                  break label534;
               }

               if (var2 == 3) {
                  var5 = var3;

                  try {
                     this.f = var3;
                  } catch (Throwable var51) {
                     var10000 = var51;
                     var10001 = false;
                     break label534;
                  }
               } else {
                  var5 = var3;

                  try {
                     this.g = var3;
                  } catch (Throwable var50) {
                     var10000 = var50;
                     var10001 = false;
                     break label534;
                  }
               }

               var5 = var3;

               label502:
               try {
                  var11.remove(var12);
                  break label522;
               } catch (Throwable var49) {
                  var10000 = var49;
                  var10001 = false;
                  break label502;
               }
            }

            Throwable var55 = var10000;
            al.a(var55);
            var3 = var5;
         }

         var5 = var3;
         if (var11.size() > 0) {
            this.c.a(var11);
            var5 = var3;
         }
      } else {
         if (var1) {
            var3 = this.g;
         } else {
            var3 = this.f;
         }

         var5 = var3;
      }

      al.c("[UploadManager] Local network consume: %d KB", var5 / 1024L);
      return var5;
   }

   public final void a(int var1, long var2) {
      synchronized(this){}
      Throwable var10000;
      boolean var10001;
      if (var1 >= 0) {
         label55: {
            try {
               this.e.put(var1, var2);
               y var4 = new y();
               var4.b = var1;
               var4.e = var2;
               var4.c = "";
               var4.d = "";
               var4.g = new byte[0];
               this.c.b(var1);
               this.c.a(var4);
               al.c("[UploadManager] Uploading(ID:%d) time: %s", var1, ap.a(var2));
            } catch (Throwable var9) {
               var10000 = var9;
               var10001 = false;
               break label55;
            }

            return;
         }
      } else {
         label58: {
            try {
               al.e("[UploadManager] Unknown uploading ID: %d", var1);
            } catch (Throwable var10) {
               var10000 = var10;
               var10001 = false;
               break label58;
            }

            return;
         }
      }

      Throwable var11 = var10000;
      throw var11;
   }

   public final void a(int param1, bq param2, String param3, String param4, ah param5, long param6, boolean param8) {
      // $FF: Couldn't be decompiled
   }

   public final void a(int var1, bq var2, String var3, String var4, ah var5, boolean var6) {
      this.a(var1, var2.g, ae.a((Object)var2), var3, var4, var5, var6);
   }

   protected final void a(long var1, boolean var3) {
      synchronized(this){}
      byte var4;
      if (var3) {
         var4 = 5;
      } else {
         var4 = 3;
      }

      Throwable var10000;
      label185: {
         boolean var10001;
         try {
            y var5 = new y();
            var5.b = var4;
            var5.e = ap.b();
            var5.c = "";
            var5.d = "";
            var5.g = ap.c(var1);
            this.c.b(var4);
            this.c.a(var5);
         } catch (Throwable var25) {
            var10000 = var25;
            var10001 = false;
            break label185;
         }

         if (var3) {
            try {
               this.g = var1;
            } catch (Throwable var24) {
               var10000 = var24;
               var10001 = false;
               break label185;
            }
         } else {
            try {
               this.f = var1;
            } catch (Throwable var23) {
               var10000 = var23;
               var10001 = false;
               break label185;
            }
         }

         label171:
         try {
            al.c("[UploadManager] Network total consume: %d KB", var1 / 1024L);
            return;
         } catch (Throwable var22) {
            var10000 = var22;
            var10001 = false;
            break label171;
         }
      }

      Throwable var26 = var10000;
      throw var26;
   }

   public final boolean b(int var1) {
      if (p.c) {
         al.c("Uploading frequency will not be checked if SDK is in debug mode.");
         return true;
      } else {
         long var2 = System.currentTimeMillis() - this.a(var1);
         al.c("[UploadManager] Time interval is %d seconds since last uploading(ID: %d).", var2 / 1000L, var1);
         if (var2 < 30000L) {
            al.a("[UploadManager] Data only be uploaded once in %d seconds.", 30L);
            return false;
         } else {
            return true;
         }
      }
   }
}
