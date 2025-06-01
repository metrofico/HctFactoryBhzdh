package com.tencent.bugly.proguard;

import android.content.Context;
import android.util.Log;
import com.tencent.bugly.BuglyStrategy;
import java.util.ArrayList;
import java.util.List;

public final class p {
   public static boolean a;
   public static List b = new ArrayList();
   public static boolean c;
   private static w d;
   private static boolean e;

   public static void a(Context var0) {
      synchronized(p.class){}

      try {
         a(var0, (BuglyStrategy)null);
      } finally {
         ;
      }

   }

   public static void a(Context var0, BuglyStrategy var1) {
      synchronized(p.class){}

      label424: {
         Throwable var10000;
         label428: {
            boolean var10001;
            try {
               if (e) {
                  al.d("[init] initial Multi-times, ignore this.");
                  break label424;
               }
            } catch (Throwable var44) {
               var10000 = var44;
               var10001 = false;
               break label428;
            }

            if (var0 == null) {
               label399: {
                  try {
                     Log.w(al.b, "[init] context of init() is null, check it.");
                  } catch (Throwable var40) {
                     var10000 = var40;
                     var10001 = false;
                     break label399;
                  }

                  return;
               }
            } else {
               label429: {
                  aa var3;
                  label421: {
                     try {
                        var3 = aa.a(var0);
                        if (!a(var3)) {
                           break label421;
                        }

                        a = false;
                     } catch (Throwable var45) {
                        var10000 = var45;
                        var10001 = false;
                        break label429;
                     }

                     return;
                  }

                  String var2;
                  try {
                     var2 = var3.e();
                  } catch (Throwable var43) {
                     var10000 = var43;
                     var10001 = false;
                     break label429;
                  }

                  if (var2 == null) {
                     label402: {
                        try {
                           Log.e(al.b, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
                        } catch (Throwable var41) {
                           var10000 = var41;
                           var10001 = false;
                           break label402;
                        }

                        return;
                     }
                  } else {
                     label405: {
                        try {
                           a(var0, var2, var3.D, var1);
                        } catch (Throwable var42) {
                           var10000 = var42;
                           var10001 = false;
                           break label405;
                        }

                        return;
                     }
                  }
               }
            }
         }

         Throwable var46 = var10000;
         throw var46;
      }

   }

   public static void a(Context param0, String param1, boolean param2, BuglyStrategy param3) {
      // $FF: Couldn't be decompiled
   }

   public static void a(o var0) {
      synchronized(p.class){}

      try {
         if (!b.contains(var0)) {
            b.add(var0);
         }
      } finally {
         ;
      }

   }

   private static boolean a(aa var0) {
      List var1 = var0.v;
      var0.getClass();
      return var1 != null && var1.contains("bugly");
   }
}
