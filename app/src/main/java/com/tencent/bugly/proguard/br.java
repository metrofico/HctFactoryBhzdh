package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class br extends m {
   static byte[] i;
   static Map j;
   public byte a = 0;
   public int b = 0;
   public byte[] c = null;
   public String d = "";
   public long e = 0L;
   public String f = "";
   public String g = "";
   public Map h = null;

   static {
      byte[] var0 = (byte[])(new byte[1]);
      i = var0;
      ((byte[])var0)[0] = 0;
      HashMap var1 = new HashMap();
      j = var1;
      var1.put("", "");
   }

   public final void a(k var1) {
      this.a = var1.a((byte)this.a, 0, true);
      this.b = var1.a((int)this.b, 1, true);
      this.c = (byte[])var1.c(2, false);
      this.d = var1.b(3, false);
      this.e = var1.a(this.e, 4, false);
      this.f = var1.b(5, false);
      this.g = var1.b(6, false);
      this.h = (Map)var1.a((Object)j, 7, false);
   }

   public final void a(l var1) {
      var1.a((byte)this.a, 0);
      var1.a((int)this.b, 1);
      byte[] var2 = this.c;
      if (var2 != null) {
         var1.a((byte[])var2, 2);
      }

      String var3 = this.d;
      if (var3 != null) {
         var1.a((String)var3, 3);
      }

      var1.a(this.e, 4);
      var3 = this.f;
      if (var3 != null) {
         var1.a((String)var3, 5);
      }

      var3 = this.g;
      if (var3 != null) {
         var1.a((String)var3, 6);
      }

      Map var4 = this.h;
      if (var4 != null) {
         var1.a((Map)var4, 7);
      }

   }
}
