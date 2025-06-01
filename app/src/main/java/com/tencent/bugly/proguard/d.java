package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class d extends c {
   protected HashMap e = null;
   k f = new k();
   private HashMap g = new HashMap();

   private void c(String var1, Object var2) {
      this.g.put(var1, var2);
   }

   public void a(String var1, Object var2) {
      if (this.e != null) {
         if (var1 != null) {
            if (var2 != null) {
               if (!(var2 instanceof Set)) {
                  l var3 = new l();
                  var3.a(this.c);
                  var3.a((Object)var2, 0);
                  byte[] var4 = n.a(var3.a);
                  this.e.put(var1, var4);
               } else {
                  throw new IllegalArgumentException("can not support Set");
               }
            } else {
               throw new IllegalArgumentException("put value can not is null");
            }
         } else {
            throw new IllegalArgumentException("put key can not is null");
         }
      } else {
         super.a(var1, var2);
      }
   }

   public void a(byte[] var1) {
      try {
         super.a(var1);
      } catch (Exception var3) {
         this.f.a(var1);
         this.f.a(this.c);
         HashMap var4 = new HashMap(1);
         var4.put("", new byte[0]);
         this.e = this.f.a((Map)var4, 0, false);
      }
   }

   public byte[] a() {
      if (this.e != null) {
         l var1 = new l(0);
         var1.a(this.c);
         var1.a((Map)this.e, 0);
         return n.a(var1.a);
      } else {
         return super.a();
      }
   }

   public final Object b(String var1, Object var2) throws b {
      HashMap var3 = this.e;
      byte[] var9;
      if (var3 != null) {
         if (!var3.containsKey(var1)) {
            return null;
         } else if (this.g.containsKey(var1)) {
            return this.g.get(var1);
         } else {
            var9 = (byte[])this.e.get(var1);

            Exception var10000;
            label56: {
               boolean var10001;
               try {
                  this.f.a(var9);
                  this.f.a(this.c);
                  var2 = this.f.a((Object)var2, 0, true);
               } catch (Exception var7) {
                  var10000 = var7;
                  var10001 = false;
                  break label56;
               }

               if (var2 == null) {
                  return var2;
               }

               try {
                  this.c(var1, var2);
                  return var2;
               } catch (Exception var6) {
                  var10000 = var6;
                  var10001 = false;
               }
            }

            Exception var8 = var10000;
            throw new b(var8);
         }
      } else if (!this.a.containsKey(var1)) {
         return null;
      } else if (this.g.containsKey(var1)) {
         return this.g.get(var1);
      } else {
         HashMap var4 = (HashMap)this.a.get(var1);
         var9 = new byte[0];
         Iterator var11 = var4.entrySet().iterator();
         if (var11.hasNext()) {
            Map.Entry var10 = (Map.Entry)var11.next();
            var10.getKey();
            var9 = (byte[])var10.getValue();
         }

         try {
            this.f.a(var9);
            this.f.a(this.c);
            var2 = this.f.a((Object)var2, 0, true);
            this.c(var1, var2);
            return var2;
         } catch (Exception var5) {
            throw new b(var5);
         }
      }
   }

   public void b() {
      this.e = new HashMap();
   }
}
