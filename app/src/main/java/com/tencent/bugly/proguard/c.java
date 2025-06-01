package com.tencent.bugly.proguard;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class c {
   protected HashMap a = new HashMap();
   protected HashMap b = new HashMap();
   protected String c = "GBK";
   k d = new k();
   private HashMap e = new HashMap();

   private static void a(ArrayList var0, Object var1) {
      while(true) {
         if (var1.getClass().isArray()) {
            if (var1.getClass().getComponentType().toString().equals("byte")) {
               if (Array.getLength(var1) > 0) {
                  var0.add("java.util.List");
                  var1 = Array.get(var1, 0);
                  continue;
               }

               var0.add("Array");
               var0.add("?");
               return;
            }

            throw new IllegalArgumentException("only byte[] is supported");
         } else {
            if (!(var1 instanceof Array)) {
               if (var1 instanceof List) {
                  var0.add("java.util.List");
                  List var4 = (List)var1;
                  if (var4.size() > 0) {
                     var1 = var4.get(0);
                     continue;
                  }

                  var0.add("?");
                  return;
               }

               if (var1 instanceof Map) {
                  var0.add("java.util.Map");
                  Map var3 = (Map)var1;
                  if (var3.size() > 0) {
                     Object var2 = var3.keySet().iterator().next();
                     var1 = var3.get(var2);
                     var0.add(var2.getClass().getName());
                     continue;
                  }

                  var0.add("?");
                  var0.add("?");
                  return;
               }

               var0.add(var1.getClass().getName());
               return;
            }

            throw new IllegalArgumentException("can not support Array, please use List");
         }
      }
   }

   public void a(String var1) {
      this.c = var1;
   }

   public void a(String var1, Object var2) {
      if (var1 != null) {
         if (var2 != null) {
            if (!(var2 instanceof Set)) {
               l var3 = new l();
               var3.a(this.c);
               var3.a((Object)var2, 0);
               byte[] var4 = n.a(var3.a);
               HashMap var5 = new HashMap(1);
               ArrayList var6 = new ArrayList(1);
               a(var6, var2);
               var5.put(com.tencent.bugly.proguard.a.a(var6), var4);
               this.e.remove(var1);
               this.a.put(var1, var5);
            } else {
               throw new IllegalArgumentException("can not support Set");
            }
         } else {
            throw new IllegalArgumentException("put value can not is null");
         }
      } else {
         throw new IllegalArgumentException("put key can not is null");
      }
   }

   public void a(byte[] var1) {
      this.d.a(var1);
      this.d.a(this.c);
      HashMap var3 = new HashMap(1);
      HashMap var2 = new HashMap(1);
      var2.put("", new byte[0]);
      var3.put("", var2);
      this.a = this.d.a((Map)var3, 0, false);
   }

   public byte[] a() {
      l var1 = new l(0);
      var1.a(this.c);
      var1.a((Map)this.a, 0);
      return n.a(var1.a);
   }
}
