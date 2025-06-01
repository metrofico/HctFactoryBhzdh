package com.google.gson.internal;

import java.lang.reflect.Type;

public final class Primitives {
   private Primitives() {
   }

   public static boolean isPrimitive(Type var0) {
      boolean var1;
      if (var0 instanceof Class && ((Class)var0).isPrimitive()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static boolean isWrapperType(Type var0) {
      boolean var1;
      if (var0 != Integer.class && var0 != Float.class && var0 != Byte.class && var0 != Double.class && var0 != Long.class && var0 != Character.class && var0 != Boolean.class && var0 != Short.class && var0 != Void.class) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public static Class unwrap(Class var0) {
      if (var0 == Integer.class) {
         return Integer.TYPE;
      } else if (var0 == Float.class) {
         return Float.TYPE;
      } else if (var0 == Byte.class) {
         return Byte.TYPE;
      } else if (var0 == Double.class) {
         return Double.TYPE;
      } else if (var0 == Long.class) {
         return Long.TYPE;
      } else if (var0 == Character.class) {
         return Character.TYPE;
      } else if (var0 == Boolean.class) {
         return Boolean.TYPE;
      } else if (var0 == Short.class) {
         return Short.TYPE;
      } else {
         Class var1 = var0;
         if (var0 == Void.class) {
            var1 = Void.TYPE;
         }

         return var1;
      }
   }

   public static Class wrap(Class var0) {
      if (var0 == Integer.TYPE) {
         return Integer.class;
      } else if (var0 == Float.TYPE) {
         return Float.class;
      } else if (var0 == Byte.TYPE) {
         return Byte.class;
      } else if (var0 == Double.TYPE) {
         return Double.class;
      } else if (var0 == Long.TYPE) {
         return Long.class;
      } else if (var0 == Character.TYPE) {
         return Character.class;
      } else if (var0 == Boolean.TYPE) {
         return Boolean.class;
      } else if (var0 == Short.TYPE) {
         return Short.class;
      } else {
         Class var1 = var0;
         if (var0 == Void.TYPE) {
            var1 = Void.class;
         }

         return var1;
      }
   }
}
