package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class bu extends m {
   static Map i;
   public long a = 0L;
   public byte b = 0;
   public String c = "";
   public String d = "";
   public String e = "";
   public Map f = null;
   public String g = "";
   public boolean h = true;

   static {
      HashMap var0 = new HashMap();
      i = var0;
      var0.put("", "");
   }

   public final void a(k var1) {
      this.a = var1.a(this.a, 0, true);
      this.b = var1.a((byte)this.b, 1, true);
      this.c = var1.b(2, false);
      this.d = var1.b(3, false);
      this.e = var1.b(4, false);
      this.f = (Map)var1.a((Object)i, 5, false);
      this.g = var1.b(6, false);
      this.h = var1.a(7, false);
   }

   public final void a(l var1) {
      var1.a(this.a, 0);
      var1.a((byte)this.b, 1);
      String var2 = this.c;
      if (var2 != null) {
         var1.a((String)var2, 2);
      }

      var2 = this.d;
      if (var2 != null) {
         var1.a((String)var2, 3);
      }

      var2 = this.e;
      if (var2 != null) {
         var1.a((String)var2, 4);
      }

      Map var3 = this.f;
      if (var3 != null) {
         var1.a((Map)var3, 5);
      }

      var2 = this.g;
      if (var2 != null) {
         var1.a((String)var2, 6);
      }

      var1.a(this.h, 7);
   }
}
