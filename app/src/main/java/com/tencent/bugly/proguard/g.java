package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class g extends m {
   static byte[] k;
   static Map l;
   static final boolean m = true;
   public short a = 0;
   public byte b = 0;
   public int c = 0;
   public int d = 0;
   public String e = null;
   public String f = null;
   public byte[] g;
   public int h = 0;
   public Map i;
   public Map j;

   public final void a(k var1) {
      Exception var10000;
      label47: {
         boolean var10001;
         try {
            this.a = var1.a((short)this.a, 1, true);
            this.b = var1.a((byte)this.b, 2, true);
            this.c = var1.a((int)this.c, 3, true);
            this.d = var1.a((int)this.d, 4, true);
            this.e = var1.b(5, true);
            this.f = var1.b(6, true);
            if (k == null) {
               k = new byte[]{0};
            }
         } catch (Exception var7) {
            var10000 = var7;
            var10001 = false;
            break label47;
         }

         Map var2;
         try {
            this.g = (byte[])var1.c(7, true);
            this.h = var1.a((int)this.h, 8, true);
            var2 = l;
         } catch (Exception var6) {
            var10000 = var6;
            var10001 = false;
            break label47;
         }

         HashMap var9;
         if (var2 == null) {
            try {
               var9 = new HashMap();
               l = var9;
               var9.put("", "");
            } catch (Exception var5) {
               var10000 = var5;
               var10001 = false;
               break label47;
            }
         }

         try {
            this.i = (Map)var1.a((Object)l, 9, true);
            if (l == null) {
               var9 = new HashMap();
               l = var9;
               var9.put("", "");
            }
         } catch (Exception var4) {
            var10000 = var4;
            var10001 = false;
            break label47;
         }

         try {
            this.j = (Map)var1.a((Object)l, 10, true);
            return;
         } catch (Exception var3) {
            var10000 = var3;
            var10001 = false;
         }
      }

      Exception var8 = var10000;
      var8.printStackTrace();
      System.out.println("RequestPacket decode error " + com.tencent.bugly.proguard.f.a(this.g));
      throw new RuntimeException(var8);
   }

   public final void a(l var1) {
      var1.a((short)this.a, 1);
      var1.a((byte)this.b, 2);
      var1.a((int)this.c, 3);
      var1.a((int)this.d, 4);
      var1.a((String)this.e, 5);
      var1.a((String)this.f, 6);
      var1.a((byte[])this.g, 7);
      var1.a((int)this.h, 8);
      var1.a((Map)this.i, 9);
      var1.a((Map)this.j, 10);
   }

   public final void a(StringBuilder var1, int var2) {
      i var3 = new i(var1, var2);
      var3.a(this.a, "iVersion");
      var3.a(this.b, "cPacketType");
      var3.a(this.c, "iMessageType");
      var3.a(this.d, "iRequestId");
      var3.a(this.e, "sServantName");
      var3.a(this.f, "sFuncName");
      var3.a(this.g, "sBuffer");
      var3.a(this.h, "iTimeout");
      var3.a(this.i, "context");
      var3.a(this.j, "status");
   }

   public final Object clone() {
      Object var1;
      try {
         var1 = super.clone();
      } catch (CloneNotSupportedException var2) {
         if (!m) {
            throw new AssertionError();
         }

         var1 = null;
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      g var4 = (g)var1;
      short var2 = var4.a;
      Integer var3 = 1;
      return n.a(1, var2) && n.a(1, var4.b) && n.a(1, var4.c) && n.a(1, var4.d) && n.a(var3, var4.e) && n.a(var3, var4.f) && n.a(var3, var4.g) && n.a(1, var4.h) && n.a(var3, var4.i) && n.a(var3, var4.j);
   }
}
