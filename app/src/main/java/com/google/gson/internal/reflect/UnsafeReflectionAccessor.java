package com.google.gson.internal.reflect;

import com.google.gson.JsonIOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

final class UnsafeReflectionAccessor extends ReflectionAccessor {
   private static Class unsafeClass;
   private final Field overrideField = getOverrideField();
   private final Object theUnsafe = getUnsafeInstance();

   private static Field getOverrideField() {
      try {
         Field var0 = AccessibleObject.class.getDeclaredField("override");
         return var0;
      } catch (NoSuchFieldException var1) {
         return null;
      }
   }

   private static Object getUnsafeInstance() {
      Object var0 = null;

      Object var4;
      try {
         Class var1 = Class.forName("sun.misc.Unsafe");
         unsafeClass = var1;
         Field var3 = var1.getDeclaredField("theUnsafe");
         var3.setAccessible(true);
         var4 = var3.get((Object)null);
      } catch (Exception var2) {
         return var0;
      }

      var0 = var4;
      return var0;
   }

   public void makeAccessible(AccessibleObject var1) {
      if (!this.makeAccessibleWithUnsafe(var1)) {
         try {
            var1.setAccessible(true);
         } catch (SecurityException var3) {
            throw new JsonIOException("Gson couldn't modify fields for " + var1 + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", var3);
         }
      }

   }

   boolean makeAccessibleWithUnsafe(AccessibleObject var1) {
      if (this.theUnsafe != null && this.overrideField != null) {
         try {
            long var2 = (Long)unsafeClass.getMethod("objectFieldOffset", Field.class).invoke(this.theUnsafe, this.overrideField);
            unsafeClass.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.theUnsafe, var1, var2, true);
            return true;
         } catch (Exception var4) {
         }
      }

      return false;
   }
}
