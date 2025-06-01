package com.tencent.bugly.crashreport.crash.anr;

import com.tencent.bugly.proguard.al;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class TraceFileHelper {
   private static String a(BufferedReader var0) throws IOException {
      StringBuffer var3 = new StringBuffer();

      for(int var1 = 0; var1 < 3; ++var1) {
         String var2 = var0.readLine();
         if (var2 == null) {
            return null;
         }

         var3.append(var2 + "\n");
      }

      return var3.toString();
   }

   private static Object[] a(BufferedReader var0, Pattern... var1) throws IOException {
      label19:
      while(true) {
         String var4 = var0.readLine();
         if (var4 != null) {
            int var3 = var1.length;
            int var2 = 0;

            while(true) {
               if (var2 >= var3) {
                  continue label19;
               }

               Pattern var5 = var1[var2];
               if (var5.matcher(var4).matches()) {
                  return new Object[]{var5, var4};
               }

               ++var2;
            }
         }

         return null;
      }
   }

   private static String b(BufferedReader var0) throws IOException {
      StringBuffer var1 = new StringBuffer();

      while(true) {
         String var2 = var0.readLine();
         if (var2 == null || var2.trim().length() <= 0) {
            return var1.toString();
         }

         var1.append(var2 + "\n");
      }
   }

   public static a readFirstDumpInfo(String var0, boolean var1) {
      if (var0 == null) {
         al.e("path:%s", var0);
         return null;
      } else {
         a var2 = new a();
         readTraceFile(var0, new b(var2, var1) {
            final a a;
            final boolean b;

            {
               this.a = var1;
               this.b = var2;
            }

            public final boolean a(long var1) {
               al.c("process end %d", var1);
               return false;
            }

            public final boolean a(long var1, long var3, String var5) {
               al.c("new process %s", var5);
               this.a.a = var1;
               this.a.b = var5;
               this.a.c = var3;
               return this.b;
            }

            public final boolean a(String var1, int var2, String var3, String var4) {
               al.c("new thread %s", var1);
               if (this.a.d == null) {
                  this.a.d = new HashMap();
               }

               this.a.d.put(var1, new String[]{var3, var4, String.valueOf(var2)});
               return true;
            }
         });
         if (var2.a > 0L && var2.c > 0L && var2.b != null) {
            return var2;
         } else {
            al.e("first dump error %s", var2.a + " " + var2.c + " " + var2.b);
            return null;
         }
      }
   }

   public static a readTargetDumpInfo(String var0, String var1, boolean var2) {
      if (var0 != null && var1 != null) {
         a var3 = new a();
         readTraceFile(var1, new b(var3, var0, var2) {
            final a a;
            final String b;
            final boolean c;

            {
               this.a = var1;
               this.b = var2;
               this.c = var3;
            }

            public final boolean a(long var1) {
               al.c("process end %d", var1);
               return this.a.a <= 0L || this.a.c <= 0L || this.a.b == null;
            }

            public final boolean a(long var1, long var3, String var5) {
               al.c("new process %s", var5);
               if (!var5.equals(this.b)) {
                  return true;
               } else {
                  this.a.a = var1;
                  this.a.b = var5;
                  this.a.c = var3;
                  return this.c;
               }
            }

            public final boolean a(String var1, int var2, String var3, String var4) {
               al.c("new thread %s", var1);
               if (this.a.a > 0L && this.a.c > 0L && this.a.b != null) {
                  if (this.a.d == null) {
                     this.a.d = new HashMap();
                  }

                  this.a.d.put(var1, new String[]{var3, var4, String.valueOf(var2)});
               }

               return true;
            }
         });
         if (var3.a > 0L && var3.c > 0L && var3.b != null) {
            return var3;
         }
      }

      return null;
   }

   public static void readTraceFile(String param0, b param1) {
      // $FF: Couldn't be decompiled
   }

   public static final class a {
      public long a;
      public String b;
      public long c;
      public Map d;
   }

   public interface b {
      boolean a(long var1);

      boolean a(long var1, long var3, String var5);

      boolean a(String var1, int var2, String var3, String var4);
   }
}
