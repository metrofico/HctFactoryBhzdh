package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class bt extends m implements Cloneable {
   static bs m = new bs();
   static Map n;
   static final boolean o = true;
   public boolean a = true;
   public boolean b = true;
   public boolean c = true;
   public String d = "";
   public String e = "";
   public bs f = null;
   public Map g = null;
   public long h = 0L;
   public String i = "";
   public String j = "";
   public int k = 0;
   public int l = 0;

   static {
      HashMap var0 = new HashMap();
      n = var0;
      var0.put("", "");
   }

   public final void a(k var1) {
      this.a = var1.a(0, true);
      this.b = var1.a(1, true);
      this.c = var1.a(2, true);
      this.d = var1.b(3, false);
      this.e = var1.b(4, false);
      this.f = (bs)var1.a((m)m, 5, false);
      this.g = (Map)var1.a((Object)n, 6, false);
      this.h = var1.a(this.h, 7, false);
      this.i = var1.b(8, false);
      this.j = var1.b(9, false);
      this.k = var1.a((int)this.k, 10, false);
      this.l = var1.a((int)this.l, 11, false);
   }

   public final void a(l var1) {
      var1.a(this.a, 0);
      var1.a(this.b, 1);
      var1.a(this.c, 2);
      String var2 = this.d;
      if (var2 != null) {
         var1.a((String)var2, 3);
      }

      var2 = this.e;
      if (var2 != null) {
         var1.a((String)var2, 4);
      }

      bs var3 = this.f;
      if (var3 != null) {
         var1.a((m)var3, 5);
      }

      Map var4 = this.g;
      if (var4 != null) {
         var1.a((Map)var4, 6);
      }

      var1.a(this.h, 7);
      var2 = this.i;
      if (var2 != null) {
         var1.a((String)var2, 8);
      }

      var2 = this.j;
      if (var2 != null) {
         var1.a((String)var2, 9);
      }

      var1.a((int)this.k, 10);
      var1.a((int)this.l, 11);
   }

   public final void a(StringBuilder var1, int var2) {
      i var3 = new i(var1, var2);
      var3.a(this.a, "enable");
      var3.a(this.b, "enableUserInfo");
      var3.a(this.c, "enableQuery");
      var3.a(this.d, "url");
      var3.a(this.e, "expUrl");
      var3.a((m)this.f, "security");
      var3.a(this.g, "valueMap");
      var3.a(this.h, "strategylastUpdateTime");
      var3.a(this.i, "httpsUrl");
      var3.a(this.j, "httpsExpUrl");
      var3.a(this.k, "eventRecordCount");
      var3.a(this.l, "eventTimeInterval");
   }

   public final Object clone() {
      Object var1;
      try {
         var1 = super.clone();
      } catch (CloneNotSupportedException var2) {
         if (!o) {
            throw new AssertionError();
         }

         var1 = null;
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else {
         bt var2 = (bt)var1;
         return com.tencent.bugly.proguard.n.a(this.a, var2.a) && com.tencent.bugly.proguard.n.a(this.b, var2.b) && com.tencent.bugly.proguard.n.a(this.c, var2.c) && com.tencent.bugly.proguard.n.a(this.d, var2.d) && com.tencent.bugly.proguard.n.a(this.e, var2.e) && com.tencent.bugly.proguard.n.a(this.f, var2.f) && com.tencent.bugly.proguard.n.a(this.g, var2.g) && com.tencent.bugly.proguard.n.a(this.h, var2.h) && com.tencent.bugly.proguard.n.a(this.i, var2.i) && com.tencent.bugly.proguard.n.a(this.j, var2.j) && com.tencent.bugly.proguard.n.a(this.k, var2.k) && com.tencent.bugly.proguard.n.a(this.l, var2.l);
      }
   }

   public final int hashCode() {
      try {
         Exception var1 = new Exception("Need define key first!");
         throw var1;
      } catch (Exception var2) {
         var2.printStackTrace();
         return 0;
      }
   }
}
