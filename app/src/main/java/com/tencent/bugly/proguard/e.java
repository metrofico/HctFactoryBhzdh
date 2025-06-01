package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public final class e extends d {
   static HashMap h;
   static HashMap i;
   protected g g;
   private int j;

   public e() {
      g var1 = new g();
      this.g = var1;
      this.j = 0;
      var1.a = 2;
   }

   public final void a(String var1, Object var2) {
      if (!var1.startsWith(".")) {
         super.a(var1, var2);
      } else {
         throw new IllegalArgumentException("put name can not startwith . , now is ".concat(String.valueOf(var1)));
      }
   }

   public final void a(byte[] var1) {
      if (var1.length >= 4) {
         Exception var10000;
         label48: {
            short var2;
            boolean var10001;
            try {
               k var3 = new k(var1, (byte)0);
               var3.a(this.c);
               this.g.a(var3);
               var2 = this.g.a;
            } catch (Exception var8) {
               var10000 = var8;
               var10001 = false;
               break label48;
            }

            k var9;
            HashMap var12;
            if (var2 == 3) {
               label39: {
                  try {
                     var9 = new k(this.g.g);
                     var9.a(this.c);
                     if (h == null) {
                        var12 = new HashMap();
                        h = var12;
                        var12.put("", new byte[0]);
                     }
                  } catch (Exception var5) {
                     var10000 = var5;
                     var10001 = false;
                     break label39;
                  }

                  try {
                     this.e = var9.a((Map)h, 0, false);
                     return;
                  } catch (Exception var4) {
                     var10000 = var4;
                     var10001 = false;
                  }
               }
            } else {
               label44: {
                  try {
                     var9 = new k(this.g.g);
                     var9.a(this.c);
                     if (i == null) {
                        var12 = new HashMap();
                        i = var12;
                        var12 = new HashMap();
                        var12.put("", new byte[0]);
                        i.put("", var12);
                     }
                  } catch (Exception var7) {
                     var10000 = var7;
                     var10001 = false;
                     break label44;
                  }

                  try {
                     this.a = var9.a((Map)i, 0, false);
                     HashMap var11 = new HashMap();
                     this.b = var11;
                     return;
                  } catch (Exception var6) {
                     var10000 = var6;
                     var10001 = false;
                  }
               }
            }
         }

         Exception var10 = var10000;
         throw new RuntimeException(var10);
      } else {
         throw new IllegalArgumentException("decode package must include size head");
      }
   }

   public final byte[] a() {
      if (this.g.a == 2) {
         if (this.g.e.equals("")) {
            throw new IllegalArgumentException("servantName can not is null");
         }

         if (this.g.f.equals("")) {
            throw new IllegalArgumentException("funcName can not is null");
         }
      } else {
         if (this.g.e == null) {
            this.g.e = "";
         }

         if (this.g.f == null) {
            this.g.f = "";
         }
      }

      l var2 = new l(0);
      var2.a(this.c);
      if (this.g.a == 2) {
         var2.a((Map)this.a, 0);
      } else {
         var2.a((Map)this.e, 0);
      }

      this.g.g = n.a(var2.a);
      var2 = new l(0);
      var2.a(this.c);
      this.g.a(var2);
      byte[] var3 = n.a(var2.a);
      int var1 = var3.length + 4;
      ByteBuffer var4 = ByteBuffer.allocate(var1);
      var4.putInt(var1).put(var3).flip();
      return var4.array();
   }

   public final void b() {
      super.b();
      this.g.a = 3;
   }

   public final void b(String var1) {
      this.g.e = var1;
   }

   public final void c() {
      this.g.d = 1;
   }

   public final void c(String var1) {
      this.g.f = var1;
   }
}
