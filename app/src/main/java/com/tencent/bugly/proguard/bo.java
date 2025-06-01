package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class bo extends m {
   static ArrayList A;
   static Map B;
   static Map C;
   static Map v;
   static bm w;
   static bl x;
   static ArrayList y;
   static ArrayList z;
   public String a = "";
   public long b = 0L;
   public String c = "";
   public String d = "";
   public String e = "";
   public String f = "";
   public String g = "";
   public Map h = null;
   public String i = "";
   public bm j = null;
   public int k = 0;
   public String l = "";
   public String m = "";
   public bl n = null;
   public ArrayList o = null;
   public ArrayList p = null;
   public ArrayList q = null;
   public Map r = null;
   public Map s = null;
   public String t = "";
   public boolean u = true;

   static {
      HashMap var0 = new HashMap();
      v = var0;
      var0.put("", "");
      w = new bm();
      x = new bl();
      y = new ArrayList();
      bl var1 = new bl();
      y.add(var1);
      z = new ArrayList();
      var1 = new bl();
      z.add(var1);
      A = new ArrayList();
      bn var2 = new bn();
      A.add(var2);
      var0 = new HashMap();
      B = var0;
      var0.put("", "");
      var0 = new HashMap();
      C = var0;
      var0.put("", "");
   }

   public final void a(k var1) {
      this.a = var1.b(0, true);
      this.b = var1.a(this.b, 1, true);
      this.c = var1.b(2, true);
      this.d = var1.b(3, false);
      this.e = var1.b(4, false);
      this.f = var1.b(5, false);
      this.g = var1.b(6, false);
      this.h = (Map)var1.a((Object)v, 7, false);
      this.i = var1.b(8, false);
      this.j = (bm)var1.a((m)w, 9, false);
      this.k = var1.a((int)this.k, 10, false);
      this.l = var1.b(11, false);
      this.m = var1.b(12, false);
      this.n = (bl)var1.a((m)x, 13, false);
      this.o = (ArrayList)var1.a((Object)y, 14, false);
      this.p = (ArrayList)var1.a((Object)z, 15, false);
      this.q = (ArrayList)var1.a((Object)A, 16, false);
      this.r = (Map)var1.a((Object)B, 17, false);
      this.s = (Map)var1.a((Object)C, 18, false);
      this.t = var1.b(19, false);
      this.u = var1.a(20, false);
   }

   public final void a(l var1) {
      var1.a((String)this.a, 0);
      var1.a(this.b, 1);
      var1.a((String)this.c, 2);
      String var2 = this.d;
      if (var2 != null) {
         var1.a((String)var2, 3);
      }

      var2 = this.e;
      if (var2 != null) {
         var1.a((String)var2, 4);
      }

      var2 = this.f;
      if (var2 != null) {
         var1.a((String)var2, 5);
      }

      var2 = this.g;
      if (var2 != null) {
         var1.a((String)var2, 6);
      }

      Map var3 = this.h;
      if (var3 != null) {
         var1.a((Map)var3, 7);
      }

      var2 = this.i;
      if (var2 != null) {
         var1.a((String)var2, 8);
      }

      bm var4 = this.j;
      if (var4 != null) {
         var1.a((m)var4, 9);
      }

      var1.a((int)this.k, 10);
      var2 = this.l;
      if (var2 != null) {
         var1.a((String)var2, 11);
      }

      var2 = this.m;
      if (var2 != null) {
         var1.a((String)var2, 12);
      }

      bl var5 = this.n;
      if (var5 != null) {
         var1.a((m)var5, 13);
      }

      ArrayList var6 = this.o;
      if (var6 != null) {
         var1.a((Collection)var6, 14);
      }

      var6 = this.p;
      if (var6 != null) {
         var1.a((Collection)var6, 15);
      }

      var6 = this.q;
      if (var6 != null) {
         var1.a((Collection)var6, 16);
      }

      var3 = this.r;
      if (var3 != null) {
         var1.a((Map)var3, 17);
      }

      var3 = this.s;
      if (var3 != null) {
         var1.a((Map)var3, 18);
      }

      var2 = this.t;
      if (var2 != null) {
         var1.a((String)var2, 19);
      }

      var1.a(this.u, 20);
   }
}
