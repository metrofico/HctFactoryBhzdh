package com.tencent.bugly.proguard;

public final class bl extends m implements Cloneable {
   public String a = "";
   public String b = "";
   public String c = "";
   public String d = "";
   public String e = "";

   public final void a(k var1) {
      this.a = var1.b(0, true);
      this.b = var1.b(1, false);
      this.c = var1.b(2, false);
      this.d = var1.b(3, false);
      this.e = var1.b(4, false);
   }

   public final void a(l var1) {
      var1.a((String)this.a, 0);
      String var2 = this.b;
      if (var2 != null) {
         var1.a((String)var2, 1);
      }

      var2 = this.c;
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

   }

   public final void a(StringBuilder var1, int var2) {
   }
}
