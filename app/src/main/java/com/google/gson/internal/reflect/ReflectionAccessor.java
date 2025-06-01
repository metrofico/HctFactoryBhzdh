package com.google.gson.internal.reflect;

import com.google.gson.internal.JavaVersion;
import java.lang.reflect.AccessibleObject;

public abstract class ReflectionAccessor {
   private static final ReflectionAccessor instance;

   static {
      Object var0;
      if (JavaVersion.getMajorJavaVersion() < 9) {
         var0 = new PreJava9ReflectionAccessor();
      } else {
         var0 = new UnsafeReflectionAccessor();
      }

      instance = (ReflectionAccessor)var0;
   }

   public static ReflectionAccessor getInstance() {
      return instance;
   }

   public abstract void makeAccessible(AccessibleObject var1);
}
