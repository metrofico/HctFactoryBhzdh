package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class bv extends m implements Cloneable {
   static ArrayList f;
   static Map g;
   public byte a = 0;
   public String b = "";
   public String c = "";
   public ArrayList d = null;
   public Map e = null;

   public final void a(k var1) {
      this.a = var1.a((byte)this.a, 0, true);
      this.b = var1.b(1, false);
      this.c = var1.b(2, false);
      if (f == null) {
         f = new ArrayList();
         bu var2 = new bu();
         f.add(var2);
      }

      this.d = (ArrayList)var1.a((Object)f, 3, false);
      if (g == null) {
         HashMap var3 = new HashMap();
         g = var3;
         var3.put("", "");
      }

      this.e = (Map)var1.a((Object)g, 4, false);
   }

   public final void a(l var1) {
      var1.a((byte)this.a, 0);
      String var2 = this.b;
      if (var2 != null) {
         var1.a((String)var2, 1);
      }

      var2 = this.c;
      if (var2 != null) {
         var1.a((String)var2, 2);
      }

      ArrayList var3 = this.d;
      if (var3 != null) {
         var1.a((Collection)var3, 3);
      }

      Map var4 = this.e;
      if (var4 != null) {
         var1.a((Map)var4, 4);
      }

   }

   public final void a(StringBuilder var1, int var2) {
   }
}
