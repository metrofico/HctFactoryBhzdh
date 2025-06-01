package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

public final class bm extends m implements Cloneable {
   static ArrayList c;
   public String a = "";
   public ArrayList b = null;

   public final void a(k var1) {
      this.a = var1.b(0, true);
      if (c == null) {
         ArrayList var2 = new ArrayList();
         c = var2;
         var2.add("");
      }

      this.b = (ArrayList)var1.a((Object)c, 1, false);
   }

   public final void a(l var1) {
      var1.a((String)this.a, 0);
      ArrayList var2 = this.b;
      if (var2 != null) {
         var1.a((Collection)var2, 1);
      }

   }

   public final void a(StringBuilder var1, int var2) {
   }
}
