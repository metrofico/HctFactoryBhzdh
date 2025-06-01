package com.google.gson.reflect;

import com.google.gson.internal.$Gson$Preconditions;
import com.google.gson.internal.$Gson$Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken {
   final int hashCode;
   final Class rawType;
   final Type type;

   protected TypeToken() {
      Type var1 = getSuperclassTypeParameter(this.getClass());
      this.type = var1;
      this.rawType = $Gson$Types.getRawType(var1);
      this.hashCode = var1.hashCode();
   }

   TypeToken(Type var1) {
      var1 = $Gson$Types.canonicalize((Type)$Gson$Preconditions.checkNotNull(var1));
      this.type = var1;
      this.rawType = $Gson$Types.getRawType(var1);
      this.hashCode = var1.hashCode();
   }

   private static AssertionError buildUnexpectedTypeError(Type var0, Class... var1) {
      StringBuilder var4 = new StringBuilder("Unexpected type. Expected one of: ");
      int var3 = var1.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         var4.append(var1[var2].getName()).append(", ");
      }

      var4.append("but got: ").append(var0.getClass().getName()).append(", for type token: ").append(var0.toString()).append('.');
      return new AssertionError(var4.toString());
   }

   public static TypeToken get(Class var0) {
      return new TypeToken(var0);
   }

   public static TypeToken get(Type var0) {
      return new TypeToken(var0);
   }

   public static TypeToken getArray(Type var0) {
      return new TypeToken($Gson$Types.arrayOf(var0));
   }

   public static TypeToken getParameterized(Type var0, Type... var1) {
      return new TypeToken($Gson$Types.newParameterizedTypeWithOwner((Type)null, var0, var1));
   }

   static Type getSuperclassTypeParameter(Class var0) {
      Type var1 = var0.getGenericSuperclass();
      if (!(var1 instanceof Class)) {
         return $Gson$Types.canonicalize(((ParameterizedType)var1).getActualTypeArguments()[0]);
      } else {
         throw new RuntimeException("Missing type parameter.");
      }
   }

   private static boolean isAssignableFrom(Type var0, GenericArrayType var1) {
      Type var2 = var1.getGenericComponentType();
      if (!(var2 instanceof ParameterizedType)) {
         return true;
      } else {
         Object var4;
         if (var0 instanceof GenericArrayType) {
            var4 = ((GenericArrayType)var0).getGenericComponentType();
         } else {
            var4 = var0;
            if (var0 instanceof Class) {
               Class var3 = (Class)var0;

               while(true) {
                  var4 = var3;
                  if (!var3.isArray()) {
                     break;
                  }

                  var3 = var3.getComponentType();
               }
            }
         }

         return isAssignableFrom((Type)var4, (ParameterizedType)var2, new HashMap());
      }
   }

   private static boolean isAssignableFrom(Type var0, ParameterizedType var1, Map var2) {
      byte var4 = 0;
      if (var0 == null) {
         return false;
      } else if (var1.equals(var0)) {
         return true;
      } else {
         Class var7 = $Gson$Types.getRawType(var0);
         ParameterizedType var6 = null;
         if (var0 instanceof ParameterizedType) {
            var6 = (ParameterizedType)var0;
         }

         int var3;
         if (var6 != null) {
            Type[] var8 = var6.getActualTypeArguments();
            TypeVariable[] var9 = var7.getTypeParameters();

            for(var3 = 0; var3 < var8.length; ++var3) {
               var0 = var8[var3];

               TypeVariable var10;
               for(var10 = var9[var3]; var0 instanceof TypeVariable; var0 = (Type)var2.get(((TypeVariable)var0).getName())) {
               }

               var2.put(var10.getName(), var0);
            }

            if (typeEquals(var6, var1, var2)) {
               return true;
            }
         }

         Type[] var11 = var7.getGenericInterfaces();
         int var5 = var11.length;

         for(var3 = var4; var3 < var5; ++var3) {
            if (isAssignableFrom(var11[var3], var1, new HashMap(var2))) {
               return true;
            }
         }

         return isAssignableFrom(var7.getGenericSuperclass(), var1, new HashMap(var2));
      }
   }

   private static boolean matches(Type var0, Type var1, Map var2) {
      boolean var3;
      if (var1.equals(var0) || var0 instanceof TypeVariable && var1.equals(var2.get(((TypeVariable)var0).getName()))) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   private static boolean typeEquals(ParameterizedType var0, ParameterizedType var1, Map var2) {
      if (var0.getRawType().equals(var1.getRawType())) {
         Type[] var4 = var0.getActualTypeArguments();
         Type[] var5 = var1.getActualTypeArguments();

         for(int var3 = 0; var3 < var4.length; ++var3) {
            if (!matches(var4[var3], var5[var3], var2)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public final boolean equals(Object var1) {
      boolean var2;
      if (var1 instanceof TypeToken && $Gson$Types.equals(this.type, ((TypeToken)var1).type)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public final Class getRawType() {
      return this.rawType;
   }

   public final Type getType() {
      return this.type;
   }

   public final int hashCode() {
      return this.hashCode;
   }

   @Deprecated
   public boolean isAssignableFrom(TypeToken var1) {
      return this.isAssignableFrom(var1.getType());
   }

   @Deprecated
   public boolean isAssignableFrom(Class var1) {
      return this.isAssignableFrom((Type)var1);
   }

   @Deprecated
   public boolean isAssignableFrom(Type var1) {
      boolean var3 = false;
      if (var1 == null) {
         return false;
      } else if (this.type.equals(var1)) {
         return true;
      } else {
         Type var4 = this.type;
         if (var4 instanceof Class) {
            return this.rawType.isAssignableFrom($Gson$Types.getRawType(var1));
         } else if (var4 instanceof ParameterizedType) {
            return isAssignableFrom(var1, (ParameterizedType)var4, new HashMap());
         } else if (var4 instanceof GenericArrayType) {
            boolean var2 = var3;
            if (this.rawType.isAssignableFrom($Gson$Types.getRawType(var1))) {
               var2 = var3;
               if (isAssignableFrom(var1, (GenericArrayType)this.type)) {
                  var2 = true;
               }
            }

            return var2;
         } else {
            throw buildUnexpectedTypeError(var4, Class.class, ParameterizedType.class, GenericArrayType.class);
         }
      }
   }

   public final String toString() {
      return $Gson$Types.typeToString(this.type);
   }
}
