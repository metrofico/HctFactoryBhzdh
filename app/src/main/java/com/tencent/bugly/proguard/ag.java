package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class ag {
   private final SimpleDateFormat a;
   private final ad b;

   private ag() {
      this.a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.US);
      this.b = new ad();
   }

   // $FF: synthetic method
   ag(byte var1) {
      this();
   }

   private static String a(String var0, Iterable var1) {
      Iterator var3 = var1.iterator();
      if (!var3.hasNext()) {
         return "";
      } else {
         StringBuilder var2 = new StringBuilder();
         var2.append("'").append(((b)var3.next()).a).append("'");

         while(var3.hasNext()) {
            var2.append(var0);
            var2.append("'").append(((b)var3.next()).a).append("'");
         }

         return var2.toString();
      }
   }

   public static List a() {
      Cursor var0 = w.a().a((String)"t_sla", (String[])(new String[]{"_id", "_tm", "_dt"}), (String)null, (String)"_tm", (String)"30");
      if (var0 == null) {
         return null;
      } else if (var0.getCount() <= 0) {
         var0.close();
         return null;
      } else {
         ArrayList var1 = new ArrayList();

         while(true) {
            try {
               if (!var0.moveToNext()) {
                  break;
               }

               b var9 = new b();
               var9.a = var0.getString(var0.getColumnIndex("_id"));
               var9.b = var0.getLong(var0.getColumnIndex("_tm"));
               var9.c = var0.getString(var0.getColumnIndex("_dt"));
               al.c(var9.toString());
               var1.add(var9);
            } catch (Throwable var8) {
               Throwable var2 = var8;

               try {
                  al.b(var2);
                  break;
               } finally {
                  var0.close();
               }
            }
         }

         return var1;
      }
   }

   private b b(c var1) {
      if (var1 != null && !TextUtils.isEmpty(var1.b)) {
         aa var3 = aa.b();
         if (var3 == null) {
            al.d("sla convert failed because ComInfoManager is null");
            return null;
         } else {
            StringBuilder var4 = (new StringBuilder("&app_version=")).append(var3.o).append("&app_name=").append(var3.q).append("&app_bundle_id=").append(var3.c).append("&client_type=android&user_id=").append(var3.f()).append("&sdk_version=").append(var3.h).append("&event_code=").append(var1.b).append("&event_result=").append(var1.d).append("&event_time=").append(this.a.format(new Date(var1.c))).append("&event_cost=").append(var1.e).append("&device_id=").append(var3.g()).append("&debug=").append(var3.D).append("&param_0=").append(var1.f).append("&param_1=").append(var1.a).append("&param_2=");
            String var2;
            if (var3.M) {
               var2 = "rqd";
            } else {
               var2 = "ext";
            }

            String var5 = var4.append(var2).append("&param_4=").append(var3.e()).toString();
            var2 = var5;
            if (!TextUtils.isEmpty(var1.g)) {
               var2 = var5 + "&param_3=" + var1.g;
            }

            al.c("sla convert eventId:%s eventType:%s, eventTime:%s success:%s cost:%s from:%s uploadMsg:", var1.a, var1.b, var1.c, var1.d, var1.e, var1.f, var1.g);
            var5 = var1.a + "-" + var1.b;
            b var6 = new b();
            var6.a = var5;
            var6.b = var1.c;
            var6.c = var2;
            return var6;
         }
      } else {
         al.d("sla convert event is null");
         return null;
      }
   }

   static void c(List var0) {
      if (var0 != null && !var0.isEmpty()) {
         al.c("sla batch report list size:%s", var0.size());
         List var1 = var0;
         if (var0.size() > 30) {
            var1 = var0.subList(0, 29);
         }

         ArrayList var2 = new ArrayList();
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            var2.add(((b)var3.next()).c);
         }

         Pair var4 = ad.a((List)var2);
         al.c("sla batch report result, rspCode:%s rspMsg:%s", var4.first, var4.second);
         if ((Integer)var4.first == 200) {
            d(var1);
         }

      } else {
         al.c("sla batch report data is empty");
      }
   }

   public static void d(List var0) {
      if (var0 != null && !var0.isEmpty()) {
         al.c("sla batch delete list size:%s", var0.size());

         try {
            StringBuilder var1 = new StringBuilder("_id in (");
            String var4 = var1.append(a(",", var0)).append(")").toString();
            al.c("sla batch delete where:%s", var4);
            w.a().a("t_sla", var4);
         } catch (Throwable var3) {
            al.b(var3);
            return;
         }
      } else {
         al.c("sla batch delete list is null");
      }
   }

   private static void e(List var0) {
      Iterator var5 = var0.iterator();

      while(var5.hasNext()) {
         b var2 = (b)var5.next();
         al.c("sla save id:%s time:%s msg:%s", var2.a, var2.b, var2.c);

         try {
            ContentValues var1 = new ContentValues();
            var1.put("_id", var2.a);
            var1.put("_tm", var2.b);
            var1.put("_dt", var2.c);
            w.a().a("t_sla", (ContentValues)var1, (v)null);
         } catch (Throwable var4) {
            al.b(var4);
            continue;
         }
      }

   }

   public final void a(c var1) {
      if (TextUtils.isEmpty(var1.b)) {
         al.d("sla report event is null");
      } else {
         al.c("sla report single event");
         this.a(Collections.singletonList(var1));
      }
   }

   public final void a(List var1) {
      if (var1 != null && !var1.isEmpty()) {
         al.c("sla batch report event size:%s", var1.size());
         ArrayList var2 = new ArrayList();
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            b var3 = this.b((c)var4.next());
            if (var3 != null) {
               var2.add(var3);
            }
         }

         e(var2);
         this.b((List)var2);
      } else {
         al.d("sla batch report event is null");
      }
   }

   public final void b(List var1) {
      if (Looper.myLooper() == Looper.getMainLooper()) {
         ak.a().a(new Runnable(this, var1) {
            final List a;
            final ag b;

            {
               this.b = var1;
               this.a = var2;
            }

            public final void run() {
               ag.c(this.a);
            }
         });
      } else {
         c(var1);
      }
   }

   public static final class a {
      private static final ag a = new ag((byte)0);

      // $FF: synthetic method
      public static ag a() {
         return a;
      }
   }

   public static final class b {
      String a;
      public long b;
      public String c;

      public final String toString() {
         return "SLAData{uuid='" + this.a + '\'' + ", time=" + this.b + ", data='" + this.c + '\'' + '}';
      }
   }

   public static final class c {
      String a;
      String b;
      long c;
      boolean d;
      long e;
      String f;
      String g;

      public c() {
      }

      public c(String var1, String var2, long var3, boolean var5, long var6, String var8, String var9) {
         this.a = var1;
         this.b = var2;
         this.c = var3;
         this.d = var5;
         this.e = var6;
         this.f = var8;
         this.g = var9;
      }
   }
}
