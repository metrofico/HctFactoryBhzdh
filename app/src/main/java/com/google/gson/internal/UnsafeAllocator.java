package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator {
   static void assertInstantiable(Class var0) {
      int var1 = var0.getModifiers();
      if (!Modifier.isInterface(var1)) {
         if (Modifier.isAbstract(var1)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + var0.getName());
         }
      } else {
         throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + var0.getName());
      }
   }

   public static UnsafeAllocator create() {
      UnsafeAllocator var6;
      try {
         Class var8 = Class.forName("sun.misc.Unsafe");
         Field var2 = var8.getDeclaredField("theUnsafe");
         var2.setAccessible(true);
         Object var7 = var2.get((Object)null);
         var6 = new UnsafeAllocator(var8.getMethod("allocateInstance", Class.class), var7) {
            final Method val$allocateInstance;
            final Object val$unsafe;

            {
               this.val$allocateInstance = var1;
               this.val$unsafe = var2;
            }

            public Object newInstance(Class var1) throws Exception {
               assertInstantiable(var1);
               return this.val$allocateInstance.invoke(this.val$unsafe, var1);
            }
         };
         return var6;
      } catch (Exception var5) {
         Method var1;
         try {
            var1 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
            var1.setAccessible(true);
            int var0 = (Integer)var1.invoke((Object)null, Object.class);
            var1 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
            var1.setAccessible(true);
            var6 = new UnsafeAllocator(var1, var0) {
               final int val$constructorId;
               final Method val$newInstance;

               {
                  this.val$newInstance = var1;
                  this.val$constructorId = var2;
               }

               public Object newInstance(Class var1) throws Exception {
                  assertInstantiable(var1);
                  return this.val$newInstance.invoke((Object)null, var1, this.val$constructorId);
               }
            };
            return var6;
         } catch (Exception var4) {
            try {
               var1 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
               var1.setAccessible(true);
               var6 = new UnsafeAllocator(var1) {
                  final Method val$newInstance;

                  {
                     this.val$newInstance = var1;
                  }

                  public Object newInstance(Class var1) throws Exception {
                     assertInstantiable(var1);
                     return this.val$newInstance.invoke((Object)null, var1, Object.class);
                  }
               };
               return var6;
            } catch (Exception var3) {
               return new UnsafeAllocator() {
                  public Object newInstance(Class var1) {
                     throw new UnsupportedOperationException("Cannot allocate " + var1);
                  }
               };
            }
         }
      }
   }

   public abstract Object newInstance(Class var1) throws Exception;
}
