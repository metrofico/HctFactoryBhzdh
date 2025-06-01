package com.tencent.bugly.proguard;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class i {
   private StringBuilder a;
   private int b;

   public i(StringBuilder var1, int var2) {
      this.a = var1;
      this.b = var2;
   }

   private i a(char var1, String var2) {
      this.a(var2);
      this.a.append(var1).append('\n');
      return this;
   }

   private i a(double var1, String var3) {
      this.a(var3);
      this.a.append(var1).append('\n');
      return this;
   }

   private i a(float var1, String var2) {
      this.a(var2);
      this.a.append(var1).append('\n');
      return this;
   }

   private i a(Object var1, String var2) {
      if (var1 == null) {
         this.a.append("null\n");
      } else if (var1 instanceof Byte) {
         this.a((Byte)var1, var2);
      } else if (var1 instanceof Boolean) {
         this.a((Boolean)var1, var2);
      } else if (var1 instanceof Short) {
         this.a((Short)var1, var2);
      } else if (var1 instanceof Integer) {
         this.a((Integer)var1, var2);
      } else if (var1 instanceof Long) {
         this.a((Long)var1, var2);
      } else if (var1 instanceof Float) {
         this.a((Float)var1, var2);
      } else if (var1 instanceof Double) {
         this.a((Double)var1, var2);
      } else if (var1 instanceof String) {
         this.a((String)var1, var2);
      } else if (var1 instanceof Map) {
         this.a((Map)var1, var2);
      } else if (var1 instanceof List) {
         this.a((Collection)((List)var1), var2);
      } else if (var1 instanceof m) {
         this.a((m)var1, var2);
      } else if (var1 instanceof byte[]) {
         this.a((byte[])var1, var2);
      } else if (var1 instanceof boolean[]) {
         this.a((Object)((boolean[])var1), var2);
      } else if (var1 instanceof short[]) {
         this.a((short[])var1, var2);
      } else if (var1 instanceof int[]) {
         this.a((int[])var1, var2);
      } else if (var1 instanceof long[]) {
         this.a((long[])var1, var2);
      } else if (var1 instanceof float[]) {
         this.a((float[])var1, var2);
      } else if (var1 instanceof double[]) {
         this.a((double[])var1, var2);
      } else {
         if (!var1.getClass().isArray()) {
            throw new j("write object error: unsupport type.");
         }

         this.a((Object[])var1, var2);
      }

      return this;
   }

   private i a(Collection var1, String var2) {
      if (var1 == null) {
         this.a(var2);
         this.a.append("null\t");
         return this;
      } else {
         return this.a(var1.toArray(), var2);
      }
   }

   private i a(double[] var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.length == 0) {
         this.a.append(var1.length).append(", []\n");
         return this;
      } else {
         this.a.append(var1.length).append(", [\n");
         i var5 = new i(this.a, this.b + 1);
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var5.a(var1[var3], (String)null);
         }

         this.a((char)']', (String)null);
         return this;
      }
   }

   private i a(float[] var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.length == 0) {
         this.a.append(var1.length).append(", []\n");
         return this;
      } else {
         this.a.append(var1.length).append(", [\n");
         i var5 = new i(this.a, this.b + 1);
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var5.a(var1[var3], (String)null);
         }

         this.a((char)']', (String)null);
         return this;
      }
   }

   private i a(int[] var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.length == 0) {
         this.a.append(var1.length).append(", []\n");
         return this;
      } else {
         this.a.append(var1.length).append(", [\n");
         i var5 = new i(this.a, this.b + 1);
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var5.a((int)var1[var3], (String)null);
         }

         this.a((char)']', (String)null);
         return this;
      }
   }

   private i a(long[] var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.length == 0) {
         this.a.append(var1.length).append(", []\n");
         return this;
      } else {
         this.a.append(var1.length).append(", [\n");
         i var5 = new i(this.a, this.b + 1);
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var5.a(var1[var3], (String)null);
         }

         this.a((char)']', (String)null);
         return this;
      }
   }

   private i a(Object[] var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.length == 0) {
         this.a.append(var1.length).append(", []\n");
         return this;
      } else {
         this.a.append(var1.length).append(", [\n");
         i var5 = new i(this.a, this.b + 1);
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var5.a((Object)var1[var3], (String)null);
         }

         this.a((char)']', (String)null);
         return this;
      }
   }

   private i a(short[] var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.length == 0) {
         this.a.append(var1.length).append(", []\n");
         return this;
      } else {
         this.a.append(var1.length).append(", [\n");
         i var5 = new i(this.a, this.b + 1);
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var5.a((short)var1[var3], (String)null);
         }

         this.a((char)']', (String)null);
         return this;
      }
   }

   private void a(String var1) {
      for(int var2 = 0; var2 < this.b; ++var2) {
         this.a.append('\t');
      }

      if (var1 != null) {
         this.a.append(var1).append(": ");
      }

   }

   public final i a(byte var1, String var2) {
      this.a(var2);
      this.a.append(var1).append('\n');
      return this;
   }

   public final i a(int var1, String var2) {
      this.a(var2);
      this.a.append(var1).append('\n');
      return this;
   }

   public final i a(long var1, String var3) {
      this.a(var3);
      this.a.append(var1).append('\n');
      return this;
   }

   public final i a(m var1, String var2) {
      this.a('{', var2);
      if (var1 == null) {
         this.a.append('\t').append("null");
      } else {
         var1.a(this.a, this.b + 1);
      }

      this.a((char)'}', (String)null);
      return this;
   }

   public final i a(String var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
      } else {
         this.a.append(var1).append('\n');
      }

      return this;
   }

   public final i a(Map var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.isEmpty()) {
         this.a.append(var1.size()).append(", {}\n");
         return this;
      } else {
         this.a.append(var1.size()).append(", {\n");
         i var6 = new i(this.a, this.b + 1);
         i var3 = new i(this.a, this.b + 2);
         Iterator var5 = var1.entrySet().iterator();

         while(var5.hasNext()) {
            Map.Entry var4 = (Map.Entry)var5.next();
            var6.a((char)'(', (String)null);
            var3.a((Object)var4.getKey(), (String)null);
            var3.a((Object)var4.getValue(), (String)null);
            var6.a((char)')', (String)null);
         }

         this.a((char)'}', (String)null);
         return this;
      }
   }

   public final i a(short var1, String var2) {
      this.a(var2);
      this.a.append(var1).append('\n');
      return this;
   }

   public final i a(boolean var1, String var2) {
      this.a(var2);
      StringBuilder var4 = this.a;
      char var3;
      if (var1) {
         var3 = 'T';
      } else {
         var3 = 'F';
      }

      var4.append(var3).append('\n');
      return this;
   }

   public final i a(byte[] var1, String var2) {
      this.a(var2);
      if (var1 == null) {
         this.a.append("null\n");
         return this;
      } else if (var1.length == 0) {
         this.a.append(var1.length).append(", []\n");
         return this;
      } else {
         this.a.append(var1.length).append(", [\n");
         i var5 = new i(this.a, this.b + 1);
         int var4 = var1.length;

         for(int var3 = 0; var3 < var4; ++var3) {
            var5.a((byte)var1[var3], (String)null);
         }

         this.a((char)']', (String)null);
         return this;
      }
   }
}
