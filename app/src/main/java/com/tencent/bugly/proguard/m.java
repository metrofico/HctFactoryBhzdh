package com.tencent.bugly.proguard;

import java.io.Serializable;

public abstract class m implements Serializable {
   public abstract void a(k var1);

   public abstract void a(l var1);

   public abstract void a(StringBuilder var1, int var2);

   public String toString() {
      StringBuilder var1 = new StringBuilder();
      this.a(var1, 0);
      return var1.toString();
   }
}
