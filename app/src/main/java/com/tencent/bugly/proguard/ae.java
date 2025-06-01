package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class ae {
   public static bq a(Context param0, int param1, byte[] param2) {
      // $FF: Couldn't be decompiled
   }

   public static br a(byte[] var0) {
      Object var1 = null;
      if (var0 != null) {
         Throwable var10000;
         label86: {
            boolean var10001;
            br var9;
            Object var11;
            try {
               e var2 = new e();
               var2.b();
               var2.a("utf-8");
               var2.a(var0);
               var9 = new br();
               var11 = var2.b("detail", var9);
            } catch (Throwable var8) {
               var10000 = var8;
               var10001 = false;
               break label86;
            }

            var9 = (br)var1;

            label77:
            try {
               if (br.class.isInstance(var11)) {
                  var9 = (br)br.class.cast(var11);
               }

               return var9;
            } catch (Throwable var7) {
               var10000 = var7;
               var10001 = false;
               break label77;
            }
         }

         Throwable var10 = var10000;
         if (!al.b(var10)) {
            var10.printStackTrace();
         }
      }

      return null;
   }

   public static bu a(UserInfoBean var0) {
      if (var0 == null) {
         return null;
      } else {
         bu var3 = new bu();
         var3.a = var0.e;
         var3.e = var0.j;
         var3.d = var0.c;
         var3.c = var0.d;
         boolean var2;
         if (var0.o == 1) {
            var2 = true;
         } else {
            var2 = false;
         }

         var3.h = var2;
         int var1 = var0.b;
         if (var1 != 1) {
            if (var1 != 2) {
               if (var1 != 3) {
                  if (var1 != 4) {
                     if (var1 != 8) {
                        if (var0.b < 10 || var0.b >= 20) {
                           al.e("unknown uinfo type %d ", var0.b);
                           return null;
                        }

                        var3.b = (byte)var0.b;
                     } else {
                        var3.b = 8;
                     }
                  } else {
                     var3.b = 3;
                  }
               } else {
                  var3.b = 2;
               }
            } else {
               var3.b = 4;
            }
         } else {
            var3.b = 1;
         }

         var3.f = new HashMap();
         if (var0.p >= 0) {
            var3.f.put("C01", "" + var0.p);
         }

         if (var0.q >= 0) {
            var3.f.put("C02", "" + var0.q);
         }

         Iterator var4;
         Map.Entry var5;
         if (var0.r != null && var0.r.size() > 0) {
            var4 = var0.r.entrySet().iterator();

            while(var4.hasNext()) {
               var5 = (Map.Entry)var4.next();
               var3.f.put("C03_" + (String)var5.getKey(), var5.getValue());
            }
         }

         if (var0.s != null && var0.s.size() > 0) {
            var4 = var0.s.entrySet().iterator();

            while(var4.hasNext()) {
               var5 = (Map.Entry)var4.next();
               var3.f.put("C04_" + (String)var5.getKey(), var5.getValue());
            }
         }

         var3.f.put("A36", "" + (var0.l ^ true));
         var3.f.put("F02", "" + var0.g);
         var3.f.put("F03", "" + var0.h);
         var3.f.put("F04", var0.j);
         var3.f.put("F05", "" + var0.i);
         var3.f.put("F06", var0.m);
         var3.f.put("F10", "" + var0.k);
         al.c("summary type %d vm:%d", var3.b, var3.f.size());
         return var3;
      }
   }

   public static m a(byte[] var0, Class var1) {
      if (var0 != null && var0.length > 0) {
         label35:
         try {
            m var2 = (m)var1.newInstance();
            k var5 = new k(var0);
            var5.a("utf-8");
            var2.a(var5);
            return var2;
         } catch (Throwable var4) {
            if (!al.b(var4)) {
               var4.printStackTrace();
            }
            break label35;
         }
      }

      return null;
   }

   public static byte[] a(m var0) {
      try {
         l var1 = new l();
         var1.a("utf-8");
         var0.a(var1);
         byte[] var4 = new byte[var1.a.position()];
         System.arraycopy(var1.a.array(), 0, var4, 0, var1.a.position());
         return var4;
      } catch (Throwable var3) {
         if (!al.b(var3)) {
            var3.printStackTrace();
         }

         return null;
      }
   }

   public static byte[] a(Object var0) {
      try {
         e var1 = new e();
         var1.b();
         var1.a("utf-8");
         var1.c();
         var1.b("RqdServer");
         var1.c("sync");
         var1.a("detail", var0);
         byte[] var4 = var1.a();
         return var4;
      } catch (Throwable var3) {
         if (!al.b(var3)) {
            var3.printStackTrace();
         }

         return null;
      }
   }
}
