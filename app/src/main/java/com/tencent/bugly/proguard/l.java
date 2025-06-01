package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class l {
   public ByteBuffer a;
   protected String b;

   public l() {
      this(128);
   }

   public l(int var1) {
      this.b = "GBK";
      this.a = ByteBuffer.allocate(var1);
   }

   private void a(double var1, int var3) {
      this.a(10);
      this.b((byte)5, var3);
      this.a.putDouble(var1);
   }

   private void a(float var1, int var2) {
      this.a(6);
      this.b((byte)4, var2);
      this.a.putFloat(var1);
   }

   private void a(int var1) {
      if (this.a.remaining() < var1) {
         ByteBuffer var2 = ByteBuffer.allocate((this.a.capacity() + var1) * 2);
         var2.put(this.a.array(), 0, this.a.position());
         this.a = var2;
      }

   }

   private void a(double[] var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      this.a((int)var1.length, 0);
      int var3 = var1.length;

      for(var2 = 0; var2 < var3; ++var2) {
         this.a(var1[var2], 0);
      }

   }

   private void a(float[] var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      this.a((int)var1.length, 0);
      int var3 = var1.length;

      for(var2 = 0; var2 < var3; ++var2) {
         this.a(var1[var2], 0);
      }

   }

   private void a(int[] var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      this.a((int)var1.length, 0);
      int var3 = var1.length;

      for(var2 = 0; var2 < var3; ++var2) {
         this.a((int)var1[var2], 0);
      }

   }

   private void a(long[] var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      this.a((int)var1.length, 0);
      int var3 = var1.length;

      for(var2 = 0; var2 < var3; ++var2) {
         this.a(var1[var2], 0);
      }

   }

   private void a(Object[] var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      this.a((int)var1.length, 0);
      int var3 = var1.length;

      for(var2 = 0; var2 < var3; ++var2) {
         this.a((Object)var1[var2], 0);
      }

   }

   private void a(short[] var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      this.a((int)var1.length, 0);
      int var3 = var1.length;

      for(var2 = 0; var2 < var3; ++var2) {
         this.a((short)var1[var2], 0);
      }

   }

   private void a(boolean[] var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      this.a((int)var1.length, 0);
      int var3 = var1.length;

      for(var2 = 0; var2 < var3; ++var2) {
         this.a(var1[var2], 0);
      }

   }

   private void b(byte var1, int var2) {
      byte var3;
      if (var2 < 15) {
         var3 = (byte)(var1 | var2 << 4);
         this.a.put(var3);
      } else if (var2 < 256) {
         var3 = (byte)(var1 | 240);
         this.a.put(var3);
         this.a.put((byte)var2);
      } else {
         throw new j("tag is too large: ".concat(String.valueOf(var2)));
      }
   }

   public final int a(String var1) {
      this.b = var1;
      return 0;
   }

   public final void a(byte var1, int var2) {
      this.a(3);
      if (var1 == 0) {
         this.b((byte)12, var2);
      } else {
         this.b((byte)0, var2);
         this.a.put(var1);
      }
   }

   public final void a(int var1, int var2) {
      this.a(6);
      if (var1 >= -32768 && var1 <= 32767) {
         this.a((short)var1, var2);
      } else {
         this.b((byte)2, var2);
         this.a.putInt(var1);
      }
   }

   public final void a(long var1, int var3) {
      this.a(10);
      if (var1 >= -2147483648L && var1 <= 2147483647L) {
         this.a((int)var1, var3);
      } else {
         this.b((byte)3, var3);
         this.a.putLong(var1);
      }
   }

   public final void a(m var1, int var2) {
      this.a(2);
      this.b((byte)10, var2);
      var1.a(this);
      this.a(2);
      this.b((byte)11, 0);
   }

   public final void a(Object var1, int var2) {
      if (var1 instanceof Byte) {
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
         this.a((boolean[])var1, var2);
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
      } else if (var1.getClass().isArray()) {
         this.a((Object[])var1, var2);
      } else if (var1 instanceof Collection) {
         this.a((Collection)var1, var2);
      } else {
         throw new j("write object error: unsupport type. " + var1.getClass());
      }
   }

   public final void a(String var1, int var2) {
      byte[] var5;
      label16: {
         byte[] var3;
         try {
            var3 = var1.getBytes(this.b);
         } catch (UnsupportedEncodingException var4) {
            var5 = var1.getBytes();
            break label16;
         }

         var5 = var3;
      }

      this.a(var5.length + 10);
      if (var5.length > 255) {
         this.b((byte)7, var2);
         this.a.putInt(var5.length);
         this.a.put(var5);
      } else {
         this.b((byte)6, var2);
         this.a.put((byte)var5.length);
         this.a.put(var5);
      }
   }

   public final void a(Collection var1, int var2) {
      this.a(8);
      this.b((byte)9, var2);
      if (var1 == null) {
         var2 = 0;
      } else {
         var2 = var1.size();
      }

      this.a((int)var2, 0);
      if (var1 != null) {
         Iterator var3 = var1.iterator();

         while(var3.hasNext()) {
            this.a((Object)var3.next(), 0);
         }
      }

   }

   public final void a(Map var1, int var2) {
      this.a(8);
      this.b((byte)8, var2);
      if (var1 == null) {
         var2 = 0;
      } else {
         var2 = var1.size();
      }

      this.a((int)var2, 0);
      if (var1 != null) {
         Iterator var3 = var1.entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry var4 = (Map.Entry)var3.next();
            this.a((Object)var4.getKey(), 0);
            this.a((Object)var4.getValue(), 1);
         }
      }

   }

   public final void a(short var1, int var2) {
      this.a(4);
      if (var1 >= -128 && var1 <= 127) {
         this.a((byte)var1, var2);
      } else {
         this.b((byte)1, var2);
         this.a.putShort(var1);
      }
   }

   public final void a(boolean var1, int var2) {
      this.a((byte)var1, var2);
   }

   public final void a(byte[] var1, int var2) {
      this.a(var1.length + 8);
      this.b((byte)13, var2);
      this.b((byte)0, 0);
      this.a((int)var1.length, 0);
      this.a.put(var1);
   }
}
