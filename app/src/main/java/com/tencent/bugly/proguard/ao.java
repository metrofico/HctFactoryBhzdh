package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ao {
   public static boolean a;
   public static boolean b;
   private static SimpleDateFormat c;
   private static int d;
   private static StringBuilder e;
   private static StringBuilder f;
   private static boolean g;
   private static a h;
   private static String i;
   private static String j;
   private static Context k;
   private static String l;
   private static boolean m;
   private static boolean n;
   private static ExecutorService o;
   private static int p;
   private static final Object q = new Object();

   static {
      try {
         SimpleDateFormat var0 = new SimpleDateFormat("MM-dd HH:mm:ss");
         c = var0;
      } catch (Throwable var2) {
         al.b(var2.getCause());
         return;
      }
   }

   private static String a(String var0, String var1, String var2, long var3) {
      e.setLength(0);
      String var5 = var2;
      if (var2.length() > 30720) {
         var5 = var2.substring(var2.length() - 30720, var2.length() - 1);
      }

      Date var7 = new Date();
      SimpleDateFormat var6 = c;
      if (var6 != null) {
         var2 = var6.format(var7);
      } else {
         var2 = var7.toString();
      }

      e.append(var2).append(" ").append(p).append(" ").append(var3).append(" ").append(var0).append(" ").append(var1).append(": ").append(var5).append("\u0001\r\n");
      return e.toString();
   }

   public static void a(int var0) {
      Object var1 = q;
      synchronized(var1){}

      Throwable var10000;
      boolean var10001;
      label253: {
         try {
            d = var0;
         } catch (Throwable var32) {
            var10000 = var32;
            var10001 = false;
            break label253;
         }

         if (var0 < 0) {
            try {
               d = 0;
            } catch (Throwable var31) {
               var10000 = var31;
               var10001 = false;
               break label253;
            }
         } else if (var0 > 30720) {
            try {
               d = 30720;
            } catch (Throwable var30) {
               var10000 = var30;
               var10001 = false;
               break label253;
            }
         }

         label239:
         try {
            return;
         } catch (Throwable var29) {
            var10000 = var29;
            var10001 = false;
            break label239;
         }
      }

      while(true) {
         Throwable var2 = var10000;

         try {
            throw var2;
         } catch (Throwable var28) {
            var10000 = var28;
            var10001 = false;
            continue;
         }
      }
   }

   public static void a(Context var0) {
      synchronized(ao.class){}

      Throwable var10000;
      label192: {
         label191: {
            boolean var10001;
            try {
               if (m) {
                  break label191;
               }
            } catch (Throwable var22) {
               var10000 = var22;
               var10001 = false;
               break label192;
            }

            if (var0 != null) {
               boolean var1;
               try {
                  var1 = b;
               } catch (Throwable var21) {
                  var10000 = var21;
                  var10001 = false;
                  break label192;
               }

               if (var1) {
                  label177:
                  try {
                     o = Executors.newSingleThreadExecutor();
                     StringBuilder var2 = new StringBuilder(0);
                     f = var2;
                     var2 = new StringBuilder(0);
                     e = var2;
                     k = var0;
                     aa var23 = aa.a(var0);
                     i = var23.d;
                     var23.getClass();
                     j = "";
                     StringBuilder var24 = new StringBuilder();
                     l = var24.append(k.getFilesDir().getPath()).append("/buglylog_").append(i).append("_").append(j).append(".txt").toString();
                     p = Process.myPid();
                  } finally {
                     break label177;
                  }

                  try {
                     m = true;
                  } catch (Throwable var20) {
                     var10000 = var20;
                     var10001 = false;
                     break label192;
                  }

                  return;
               }
            }
         }

         return;
      }

      Throwable var25 = var10000;
      throw var25;
   }

   public static void a(String param0, String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   public static void a(String var0, String var1, Throwable var2) {
      if (var2 != null) {
         String var4 = var2.getMessage();
         String var3 = var4;
         if (var4 == null) {
            var3 = "";
         }

         a(var0, var1, var3 + '\n' + ap.b(var2));
      }
   }

   public static byte[] a() {
      if (a) {
         return !b ? null : ap.a(f.toString(), "BuglyLog.txt");
      } else {
         return c();
      }
   }

   private static String b() {
      // $FF: Couldn't be decompiled
   }

   private static byte[] c() {
      if (!b) {
         return null;
      } else {
         if (n) {
            al.a("[LogUtil] Get user log from native.");
            String var0 = b();
            if (var0 != null) {
               al.a("[LogUtil] Got user log from native: %d bytes", var0.length());
               return ap.a(var0, "BuglyNativeLog.txt");
            }
         }

         StringBuilder var1 = new StringBuilder();
         Object var45 = q;
         synchronized(var45){}

         Throwable var10000;
         boolean var10001;
         label476: {
            a var2;
            try {
               var2 = h;
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               break label476;
            }

            if (var2 != null) {
               try {
                  if (var2.a && h.b != null && h.b.length() > 0L) {
                     var1.append(ap.a(h.b, 30720, true));
                  }
               } catch (Throwable var43) {
                  var10000 = var43;
                  var10001 = false;
                  break label476;
               }
            }

            StringBuilder var47;
            try {
               var47 = f;
            } catch (Throwable var42) {
               var10000 = var42;
               var10001 = false;
               break label476;
            }

            if (var47 != null) {
               try {
                  if (var47.length() > 0) {
                     var1.append(f.toString());
                  }
               } catch (Throwable var41) {
                  var10000 = var41;
                  var10001 = false;
                  break label476;
               }
            }

            label453:
            try {
               return ap.a(var1.toString(), "BuglyLog.txt");
            } catch (Throwable var40) {
               var10000 = var40;
               var10001 = false;
               break label453;
            }
         }

         while(true) {
            Throwable var46 = var10000;

            try {
               throw var46;
            } catch (Throwable var39) {
               var10000 = var39;
               var10001 = false;
               continue;
            }
         }
      }
   }

   private static boolean d(String param0, String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   private static void e(String var0, String var1, String var2) {
      synchronized(ao.class){}

      try {
         if (a) {
            f(var0, var1, var2);
            return;
         }

         g(var0, var1, var2);
      } finally {
         ;
      }

   }

   private static void f(String param0, String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   private static void g(String param0, String param1, String param2) {
      // $FF: Couldn't be decompiled
   }

   public static final class a {
      boolean a;
      File b;
      long c = 30720L;
      private String d;
      private long e;

      public a(String var1) {
         if (var1 != null && !var1.equals("")) {
            this.d = var1;
            this.a = this.a();
         }

      }

      final boolean a() {
         try {
            File var1 = new File(this.d);
            this.b = var1;
            if (var1.exists() && !this.b.delete()) {
               this.a = false;
               return false;
            } else if (!this.b.createNewFile()) {
               this.a = false;
               return false;
            } else {
               return true;
            }
         } catch (Throwable var3) {
            al.a(var3);
            this.a = false;
            return false;
         }
      }

      public final boolean a(String var1) {
         if (!this.a) {
            return false;
         } else {
            Object var4 = null;

            FileOutputStream var3;
            try {
               var3 = new FileOutputStream(this.b, true);
            } catch (Throwable var25) {
               Throwable var2 = var25;
               var1 = (String)var4;

               try {
                  al.a(var2);
                  this.a = false;
               } finally {
                  if (var4 != null) {
                     try {
                        var1.close();
                     } catch (IOException var21) {
                     }
                  }

               }

               return false;
            }

            try {
               byte[] var26 = var1.getBytes("UTF-8");
               var3.write(var26);
               var3.flush();
               var3.close();
               this.e += (long)var26.length;
               this.a = true;
            } finally {
               ;
            }

            try {
               var3.close();
            } catch (IOException var22) {
            }

            return true;
         }
      }
   }
}
