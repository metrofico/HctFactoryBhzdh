package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

public final class bp extends m implements Cloneable {
   static ArrayList b;
   public ArrayList a = null;

   public final void a(k var1) {
      if (b == null) {
         b = new ArrayList();
         bo var2 = new bo();
         b.add(var2);
      }

      this.a = (ArrayList)var1.a((Object)b, 0, true);
   }

   public final void a(l var1) {
      var1.a((Collection)this.a, 0);
   }

   public final void a(StringBuilder var1, int var2) {
   }
}
