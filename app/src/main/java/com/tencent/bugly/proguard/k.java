package com.tencent.bugly.proguard;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class k {
   protected String a = "GBK";
   private ByteBuffer b;

   public k() {
   }

   public k(byte[] var1) {
      this.b = ByteBuffer.wrap(var1);
   }

   public k(byte[] var1, byte var2) {
      ByteBuffer var3 = ByteBuffer.wrap(var1);
      this.b = var3;
      var3.position(4);
   }

   private double a(double var1, int var3, boolean var4) {
      if (this.b(var3)) {
         a var5 = new a();
         this.a(var5);
         byte var6 = var5.a;
         if (var6 != 4) {
            if (var6 != 5) {
               if (var6 != 12) {
                  throw new h("type mismatch.");
               }

               var1 = 0.0;
            } else {
               var1 = this.b.getDouble();
            }
         } else {
            var1 = (double)this.b.getFloat();
         }
      } else if (var4) {
         throw new h("require field not exist.");
      }

      return var1;
   }

   private float a(float var1, int var2, boolean var3) {
      if (this.b(var2)) {
         a var4 = new a();
         this.a(var4);
         byte var5 = var4.a;
         if (var5 != 4) {
            if (var5 != 12) {
               throw new h("type mismatch.");
            }

            var1 = 0.0F;
         } else {
            var1 = this.b.getFloat();
         }
      } else if (var3) {
         throw new h("require field not exist.");
      }

      return var1;
   }

   private static int a(a var0, ByteBuffer var1) {
      byte var2 = var1.get();
      var0.a = (byte)(var2 & 15);
      var0.b = (var2 & 240) >> 4;
      if (var0.b == 15) {
         var0.b = var1.get();
         return 2;
      } else {
         return 1;
      }
   }

   private List a(List var1, int var2, boolean var3) {
      if (var1 != null && !var1.isEmpty()) {
         byte var4 = 0;
         Object[] var6 = this.b(var1.get(0), var2, var3);
         if (var6 == null) {
            return null;
         } else {
            ArrayList var5 = new ArrayList();

            for(var2 = var4; var2 < var6.length; ++var2) {
               var5.add(var6[var2]);
            }

            return var5;
         }
      } else {
         return new ArrayList();
      }
   }

   private Map a(Map var1, Map var2, int var3, boolean var4) {
      if (var2 != null && !var2.isEmpty()) {
         Map.Entry var6 = (Map.Entry)var2.entrySet().iterator().next();
         Object var8 = var6.getKey();
         Object var9 = var6.getValue();
         if (this.b(var3)) {
            a var7 = new a();
            this.a(var7);
            if (var7.a != 8) {
               throw new h("type mismatch.");
            }

            int var5 = this.a((int)0, 0, true);
            if (var5 < 0) {
               throw new h("size invalid: ".concat(String.valueOf(var5)));
            }

            for(var3 = 0; var3 < var5; ++var3) {
               var1.put(this.a((Object)var8, 0, true), this.a((Object)var9, 1, true));
            }
         } else if (var4) {
            throw new h("require field not exist.");
         }

         return var1;
      } else {
         return new HashMap();
      }
   }

   private void a() {
      a var1 = new a();

      do {
         this.a(var1);
         this.a(var1.a);
      } while(var1.a != 11);

   }

   private void a(byte var1) {
      int var3 = 0;
      int var2 = 0;
      switch (var1) {
         case 0:
            this.a((int)1);
            return;
         case 1:
            this.a((int)2);
            return;
         case 2:
            this.a((int)4);
            return;
         case 3:
            this.a((int)8);
            return;
         case 4:
            this.a((int)4);
            return;
         case 5:
            this.a((int)8);
            return;
         case 6:
            byte var5 = this.b.get();
            var1 = var5;
            if (var5 < 0) {
               var1 = var5 + 256;
            }

            this.a(var1);
            return;
         case 7:
            this.a(this.b.getInt());
            return;
         case 8:
            var2 = this.a((int)0, 0, true);

            for(var1 = var3; var1 < var2 * 2; ++var1) {
               this.b();
            }

            return;
         case 9:
            var3 = this.a((int)0, 0, true);

            for(var1 = var2; var1 < var3; ++var1) {
               this.b();
            }

            return;
         case 10:
            this.a();
            return;
         case 11:
         case 12:
            return;
         case 13:
            a var4 = new a();
            this.a(var4);
            if (var4.a == 0) {
               this.a(this.a((int)0, 0, true));
               return;
            }

            throw new h("skipField with invalid type, type value: " + var1 + ", " + var4.a);
         default:
            throw new h("invalid type.");
      }
   }

   private void a(int var1) {
      ByteBuffer var2 = this.b;
      var2.position(var2.position() + var1);
   }

   private void a(a var1) {
      a(var1, this.b);
   }

   private Object[] a(Object[] var1, int var2, boolean var3) {
      if (var1 != null && var1.length != 0) {
         return this.b(var1[0], var2, var3);
      } else {
         throw new h("unable to get type of key and value.");
      }
   }

   private void b() {
      a var1 = new a();
      this.a(var1);
      this.a(var1.a);
   }

   private boolean b(int var1) {
      boolean var10001;
      a var3;
      try {
         var3 = new a();
      } catch (BufferUnderflowException | h var6) {
         var10001 = false;
         return false;
      }

      int var2;
      while(true) {
         label39:
         try {
            var2 = a(var3, this.b.duplicate());
            if (var1 > var3.b && var3.a != 11) {
               break label39;
            }
            break;
         } catch (BufferUnderflowException | h var7) {
            var10001 = false;
            return false;
         }

         try {
            this.a(var2);
            this.a(var3.a);
         } catch (BufferUnderflowException | h var5) {
            var10001 = false;
            return false;
         }
      }

      try {
         var2 = var3.b;
      } catch (BufferUnderflowException | h var4) {
         var10001 = false;
         return false;
      }

      if (var1 == var2) {
         return true;
      } else {
         return false;
      }
   }

   private Object[] b(Object var1, int var2, boolean var3) {
      if (!this.b(var2)) {
         if (!var3) {
            return null;
         } else {
            throw new h("require field not exist.");
         }
      } else {
         a var5 = new a();
         this.a(var5);
         if (var5.a != 9) {
            throw new h("type mismatch.");
         } else {
            int var4 = this.a((int)0, 0, true);
            if (var4 < 0) {
               throw new h("size invalid: ".concat(String.valueOf(var4)));
            } else {
               Object[] var6 = (Object[])Array.newInstance(var1.getClass(), var4);

               for(var2 = 0; var2 < var4; ++var2) {
                  var6[var2] = this.a((Object)var1, 0, true);
               }

               return var6;
            }
         }
      }
   }

   private boolean[] d(int var1, boolean var2) {
      boolean[] var6;
      if (this.b(var1)) {
         a var4 = new a();
         this.a(var4);
         if (var4.a != 9) {
            throw new h("type mismatch.");
         }

         int var3 = this.a((int)0, 0, true);
         if (var3 < 0) {
            throw new h("size invalid: ".concat(String.valueOf(var3)));
         }

         boolean[] var5 = new boolean[var3];
         var1 = 0;

         while(true) {
            var6 = var5;
            if (var1 >= var3) {
               break;
            }

            var5[var1] = this.a(0, true);
            ++var1;
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var6 = null;
      }

      return var6;
   }

   private short[] e(int var1, boolean var2) {
      short[] var6;
      if (this.b(var1)) {
         a var4 = new a();
         this.a(var4);
         if (var4.a != 9) {
            throw new h("type mismatch.");
         }

         int var3 = this.a((int)0, 0, true);
         if (var3 < 0) {
            throw new h("size invalid: ".concat(String.valueOf(var3)));
         }

         short[] var5 = new short[var3];
         var1 = 0;

         while(true) {
            var6 = var5;
            if (var1 >= var3) {
               break;
            }

            var5[var1] = this.a((short)var5[0], 0, true);
            ++var1;
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var6 = null;
      }

      return var6;
   }

   private int[] f(int var1, boolean var2) {
      int[] var6;
      if (this.b(var1)) {
         a var4 = new a();
         this.a(var4);
         if (var4.a != 9) {
            throw new h("type mismatch.");
         }

         int var3 = this.a((int)0, 0, true);
         if (var3 < 0) {
            throw new h("size invalid: ".concat(String.valueOf(var3)));
         }

         int[] var5 = new int[var3];
         var1 = 0;

         while(true) {
            var6 = var5;
            if (var1 >= var3) {
               break;
            }

            var5[var1] = this.a((int)var5[0], 0, true);
            ++var1;
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var6 = null;
      }

      return var6;
   }

   private long[] g(int var1, boolean var2) {
      long[] var6;
      if (this.b(var1)) {
         a var4 = new a();
         this.a(var4);
         if (var4.a != 9) {
            throw new h("type mismatch.");
         }

         int var3 = this.a((int)0, 0, true);
         if (var3 < 0) {
            throw new h("size invalid: ".concat(String.valueOf(var3)));
         }

         long[] var5 = new long[var3];
         var1 = 0;

         while(true) {
            var6 = var5;
            if (var1 >= var3) {
               break;
            }

            var5[var1] = this.a(var5[0], 0, true);
            ++var1;
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var6 = null;
      }

      return var6;
   }

   private float[] h(int var1, boolean var2) {
      float[] var6;
      if (this.b(var1)) {
         a var4 = new a();
         this.a(var4);
         if (var4.a != 9) {
            throw new h("type mismatch.");
         }

         int var3 = this.a((int)0, 0, true);
         if (var3 < 0) {
            throw new h("size invalid: ".concat(String.valueOf(var3)));
         }

         float[] var5 = new float[var3];
         var1 = 0;

         while(true) {
            var6 = var5;
            if (var1 >= var3) {
               break;
            }

            var5[var1] = this.a(var5[0], 0, true);
            ++var1;
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var6 = null;
      }

      return var6;
   }

   private double[] i(int var1, boolean var2) {
      double[] var6;
      if (this.b(var1)) {
         a var4 = new a();
         this.a(var4);
         if (var4.a != 9) {
            throw new h("type mismatch.");
         }

         int var3 = this.a((int)0, 0, true);
         if (var3 < 0) {
            throw new h("size invalid: ".concat(String.valueOf(var3)));
         }

         double[] var5 = new double[var3];
         var1 = 0;

         while(true) {
            var6 = var5;
            if (var1 >= var3) {
               break;
            }

            var5[var1] = this.a(var5[0], 0, true);
            ++var1;
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var6 = null;
      }

      return var6;
   }

   public final byte a(byte var1, int var2, boolean var3) {
      if (this.b(var2)) {
         a var4 = new a();
         this.a(var4);
         byte var5 = var4.a;
         if (var5 != 0) {
            if (var5 != 12) {
               throw new h("type mismatch.");
            }

            var1 = 0;
         } else {
            var1 = this.b.get();
         }
      } else if (var3) {
         throw new h("require field not exist.");
      }

      return var1;
   }

   public final int a(int var1, int var2, boolean var3) {
      if (this.b(var2)) {
         a var4 = new a();
         this.a(var4);
         byte var5 = var4.a;
         if (var5 != 0) {
            if (var5 != 1) {
               if (var5 != 2) {
                  if (var5 != 12) {
                     throw new h("type mismatch.");
                  }

                  var1 = 0;
               } else {
                  var1 = this.b.getInt();
               }
            } else {
               var1 = this.b.getShort();
            }
         } else {
            var1 = this.b.get();
         }
      } else if (var3) {
         throw new h("require field not exist.");
      }

      return var1;
   }

   public final int a(String var1) {
      this.a = var1;
      return 0;
   }

   public final long a(long var1, int var3, boolean var4) {
      if (this.b(var3)) {
         a var5 = new a();
         this.a(var5);
         byte var6 = var5.a;
         if (var6 != 0) {
            if (var6 != 1) {
               if (var6 != 2) {
                  if (var6 != 3) {
                     if (var6 != 12) {
                        throw new h("type mismatch.");
                     }

                     var1 = 0L;
                  } else {
                     var1 = this.b.getLong();
                  }

                  return var1;
               }

               var3 = this.b.getInt();
            } else {
               var3 = this.b.getShort();
            }
         } else {
            var3 = this.b.get();
         }

         var1 = (long)var3;
      } else if (var4) {
         throw new h("require field not exist.");
      }

      return var1;
   }

   public final m a(m var1, int var2, boolean var3) {
      if (this.b(var2)) {
         try {
            var1 = (m)var1.getClass().newInstance();
         } catch (Exception var5) {
            throw new h(var5.getMessage());
         }

         a var4 = new a();
         this.a(var4);
         if (var4.a != 10) {
            throw new h("type mismatch.");
         }

         var1.a(this);
         this.a();
      } else {
         if (var3) {
            throw new h("require field not exist.");
         }

         var1 = null;
      }

      return var1;
   }

   public final Object a(Object var1, int var2, boolean var3) {
      if (var1 instanceof Byte) {
         return this.a((byte)0, var2, var3);
      } else if (var1 instanceof Boolean) {
         return this.a(var2, var3);
      } else if (var1 instanceof Short) {
         return this.a((short)0, var2, var3);
      } else if (var1 instanceof Integer) {
         return this.a((int)0, var2, var3);
      } else if (var1 instanceof Long) {
         return this.a(0L, var2, var3);
      } else if (var1 instanceof Float) {
         return this.a(0.0F, var2, var3);
      } else if (var1 instanceof Double) {
         return this.a(0.0, var2, var3);
      } else if (var1 instanceof String) {
         return String.valueOf(this.b(var2, var3));
      } else if (var1 instanceof Map) {
         return this.a((Map)var1, var2, var3);
      } else if (var1 instanceof List) {
         return this.a((List)var1, var2, var3);
      } else if (var1 instanceof m) {
         return this.a((m)var1, var2, var3);
      } else if (var1.getClass().isArray()) {
         if (!(var1 instanceof byte[]) && !(var1 instanceof Byte[])) {
            if (var1 instanceof boolean[]) {
               return this.d(var2, var3);
            } else if (var1 instanceof short[]) {
               return this.e(var2, var3);
            } else if (var1 instanceof int[]) {
               return this.f(var2, var3);
            } else if (var1 instanceof long[]) {
               return this.g(var2, var3);
            } else if (var1 instanceof float[]) {
               return this.h(var2, var3);
            } else {
               return var1 instanceof double[] ? this.i(var2, var3) : this.a((Object[])var1, var2, var3);
            }
         } else {
            return this.c(var2, var3);
         }
      } else {
         throw new h("read object error: unsupport type.");
      }
   }

   public final HashMap a(Map var1, int var2, boolean var3) {
      return (HashMap)this.a(new HashMap(), var1, var2, var3);
   }

   public final short a(short var1, int var2, boolean var3) {
      if (this.b(var2)) {
         a var4 = new a();
         this.a(var4);
         byte var5 = var4.a;
         if (var5 != 0) {
            if (var5 != 1) {
               if (var5 != 12) {
                  throw new h("type mismatch.");
               }

               var1 = 0;
            } else {
               var1 = this.b.getShort();
            }
         } else {
            var1 = (short)this.b.get();
         }
      } else if (var3) {
         throw new h("require field not exist.");
      }

      return var1;
   }

   public final void a(byte[] var1) {
      ByteBuffer var2 = this.b;
      if (var2 != null) {
         var2.clear();
      }

      this.b = ByteBuffer.wrap(var1);
   }

   public final boolean a(int var1, boolean var2) {
      return this.a((byte)0, var1, var2) != 0;
   }

   public final String b(int var1, boolean var2) {
      String var4;
      if (this.b(var1)) {
         a var9 = new a();
         this.a(var9);
         byte var8 = var9.a;
         byte[] var5;
         if (var8 != 6) {
            if (var8 != 7) {
               throw new h("type mismatch.");
            }

            var1 = this.b.getInt();
            if (var1 > 104857600 || var1 < 0) {
               throw new h("String too long: ".concat(String.valueOf(var1)));
            }

            var5 = new byte[var1];
            this.b.get(var5);

            try {
               var4 = new String(var5, this.a);
            } catch (UnsupportedEncodingException var7) {
               var4 = new String(var5);
            }
         } else {
            byte var3 = this.b.get();
            var1 = var3;
            if (var3 < 0) {
               var1 = var3 + 256;
            }

            var5 = new byte[var1];
            this.b.get(var5);

            try {
               var4 = new String(var5, this.a);
            } catch (UnsupportedEncodingException var6) {
               var4 = new String(var5);
            }
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var4 = null;
      }

      return var4;
   }

   public final byte[] c(int var1, boolean var2) {
      byte[] var6;
      if (this.b(var1)) {
         a var4 = new a();
         this.a(var4);
         int var3 = var4.a;
         if (var3 != 9) {
            if (var3 != 13) {
               throw new h("type mismatch.");
            }

            a var5 = new a();
            this.a(var5);
            if (var5.a != 0) {
               throw new h("type mismatch, tag: " + var1 + ", type: " + var4.a + ", " + var5.a);
            }

            var3 = this.a((int)0, 0, true);
            if (var3 < 0) {
               throw new h("invalid size, tag: " + var1 + ", type: " + var4.a + ", " + var5.a + ", size: " + var3);
            }

            var6 = new byte[var3];
            this.b.get(var6);
         } else {
            var3 = this.a((int)0, 0, true);
            if (var3 < 0) {
               throw new h("size invalid: ".concat(String.valueOf(var3)));
            }

            var6 = new byte[var3];

            for(var1 = 0; var1 < var3; ++var1) {
               var6[var1] = this.a((byte)var6[0], 0, true);
            }
         }
      } else {
         if (var2) {
            throw new h("require field not exist.");
         }

         var6 = null;
      }

      return var6;
   }

   public static final class a {
      public byte a;
      public int b;
   }
}
