package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class $Gson$Types {
   static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

   private $Gson$Types() {
      throw new UnsupportedOperationException();
   }

   public static GenericArrayType arrayOf(Type var0) {
      return new GenericArrayTypeImpl(var0);
   }

   public static Type canonicalize(Type var0) {
      if (var0 instanceof Class) {
         Class var1 = (Class)var0;
         Object var4 = var1;
         if (var1.isArray()) {
            var4 = new GenericArrayTypeImpl(canonicalize(var1.getComponentType()));
         }

         return (Type)var4;
      } else if (var0 instanceof ParameterizedType) {
         ParameterizedType var3 = (ParameterizedType)var0;
         return new ParameterizedTypeImpl(var3.getOwnerType(), var3.getRawType(), var3.getActualTypeArguments());
      } else if (var0 instanceof GenericArrayType) {
         return new GenericArrayTypeImpl(((GenericArrayType)var0).getGenericComponentType());
      } else if (var0 instanceof WildcardType) {
         WildcardType var2 = (WildcardType)var0;
         return new WildcardTypeImpl(var2.getUpperBounds(), var2.getLowerBounds());
      } else {
         return var0;
      }
   }

   static void checkNotPrimitive(Type var0) {
      boolean var1;
      if (var0 instanceof Class && ((Class)var0).isPrimitive()) {
         var1 = false;
      } else {
         var1 = true;
      }

      $Gson$Preconditions.checkArgument(var1);
   }

   private static Class declaringClassOf(TypeVariable var0) {
      GenericDeclaration var1 = var0.getGenericDeclaration();
      Class var2;
      if (var1 instanceof Class) {
         var2 = (Class)var1;
      } else {
         var2 = null;
      }

      return var2;
   }

   static boolean equal(Object var0, Object var1) {
      boolean var2;
      if (var0 == var1 || var0 != null && var0.equals(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static boolean equals(Type var0, Type var1) {
      boolean var3 = true;
      boolean var4 = true;
      boolean var2 = true;
      if (var0 == var1) {
         return true;
      } else if (var0 instanceof Class) {
         return var0.equals(var1);
      } else if (var0 instanceof ParameterizedType) {
         if (!(var1 instanceof ParameterizedType)) {
            return false;
         } else {
            ParameterizedType var8 = (ParameterizedType)var0;
            ParameterizedType var12 = (ParameterizedType)var1;
            if (!equal(var8.getOwnerType(), var12.getOwnerType()) || !var8.getRawType().equals(var12.getRawType()) || !Arrays.equals(var8.getActualTypeArguments(), var12.getActualTypeArguments())) {
               var2 = false;
            }

            return var2;
         }
      } else if (var0 instanceof GenericArrayType) {
         if (!(var1 instanceof GenericArrayType)) {
            return false;
         } else {
            GenericArrayType var7 = (GenericArrayType)var0;
            GenericArrayType var11 = (GenericArrayType)var1;
            return equals(var7.getGenericComponentType(), var11.getGenericComponentType());
         }
      } else if (var0 instanceof WildcardType) {
         if (!(var1 instanceof WildcardType)) {
            return false;
         } else {
            WildcardType var6 = (WildcardType)var0;
            WildcardType var10 = (WildcardType)var1;
            if (Arrays.equals(var6.getUpperBounds(), var10.getUpperBounds()) && Arrays.equals(var6.getLowerBounds(), var10.getLowerBounds())) {
               var2 = var3;
            } else {
               var2 = false;
            }

            return var2;
         }
      } else if (var0 instanceof TypeVariable) {
         if (!(var1 instanceof TypeVariable)) {
            return false;
         } else {
            TypeVariable var5 = (TypeVariable)var0;
            TypeVariable var9 = (TypeVariable)var1;
            if (var5.getGenericDeclaration() == var9.getGenericDeclaration() && var5.getName().equals(var9.getName())) {
               var2 = var4;
            } else {
               var2 = false;
            }

            return var2;
         }
      } else {
         return false;
      }
   }

   public static Type getArrayComponentType(Type var0) {
      Object var1;
      if (var0 instanceof GenericArrayType) {
         var1 = ((GenericArrayType)var0).getGenericComponentType();
      } else {
         var1 = ((Class)var0).getComponentType();
      }

      return (Type)var1;
   }

   public static Type getCollectionElementType(Type var0, Class var1) {
      Type var2 = getSupertype(var0, var1, Collection.class);
      var0 = var2;
      if (var2 instanceof WildcardType) {
         var0 = ((WildcardType)var2).getUpperBounds()[0];
      }

      return (Type)(var0 instanceof ParameterizedType ? ((ParameterizedType)var0).getActualTypeArguments()[0] : Object.class);
   }

   static Type getGenericSupertype(Type var0, Class var1, Class var2) {
      if (var2 == var1) {
         return var0;
      } else {
         if (var2.isInterface()) {
            Class[] var6 = var1.getInterfaces();
            int var3 = 0;

            for(int var4 = var6.length; var3 < var4; ++var3) {
               Class var5 = var6[var3];
               if (var5 == var2) {
                  return var1.getGenericInterfaces()[var3];
               }

               if (var2.isAssignableFrom(var5)) {
                  return getGenericSupertype(var1.getGenericInterfaces()[var3], var6[var3], var2);
               }
            }
         }

         if (!var1.isInterface()) {
            while(var1 != Object.class) {
               Class var7 = var1.getSuperclass();
               if (var7 == var2) {
                  return var1.getGenericSuperclass();
               }

               if (var2.isAssignableFrom(var7)) {
                  return getGenericSupertype(var1.getGenericSuperclass(), var7, var2);
               }

               var1 = var7;
            }
         }

         return var2;
      }
   }

   public static Type[] getMapKeyAndValueTypes(Type var0, Class var1) {
      if (var0 == Properties.class) {
         return new Type[]{String.class, String.class};
      } else {
         var0 = getSupertype(var0, var1, Map.class);
         return var0 instanceof ParameterizedType ? ((ParameterizedType)var0).getActualTypeArguments() : new Type[]{Object.class, Object.class};
      }
   }

   public static Class getRawType(Type var0) {
      if (var0 instanceof Class) {
         return (Class)var0;
      } else if (var0 instanceof ParameterizedType) {
         var0 = ((ParameterizedType)var0).getRawType();
         $Gson$Preconditions.checkArgument(var0 instanceof Class);
         return (Class)var0;
      } else if (var0 instanceof GenericArrayType) {
         return Array.newInstance(getRawType(((GenericArrayType)var0).getGenericComponentType()), 0).getClass();
      } else if (var0 instanceof TypeVariable) {
         return Object.class;
      } else if (var0 instanceof WildcardType) {
         return getRawType(((WildcardType)var0).getUpperBounds()[0]);
      } else {
         String var1;
         if (var0 == null) {
            var1 = "null";
         } else {
            var1 = var0.getClass().getName();
         }

         throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + var0 + "> is of type " + var1);
      }
   }

   static Type getSupertype(Type var0, Class var1, Class var2) {
      Type var3 = var0;
      if (var0 instanceof WildcardType) {
         var3 = ((WildcardType)var0).getUpperBounds()[0];
      }

      $Gson$Preconditions.checkArgument(var2.isAssignableFrom(var1));
      return resolve(var3, var1, getGenericSupertype(var3, var1, var2));
   }

   static int hashCodeOrZero(Object var0) {
      int var1;
      if (var0 != null) {
         var1 = var0.hashCode();
      } else {
         var1 = 0;
      }

      return var1;
   }

   private static int indexOf(Object[] var0, Object var1) {
      int var3 = var0.length;

      for(int var2 = 0; var2 < var3; ++var2) {
         if (var1.equals(var0[var2])) {
            return var2;
         }
      }

      throw new NoSuchElementException();
   }

   public static ParameterizedType newParameterizedTypeWithOwner(Type var0, Type var1, Type... var2) {
      return new ParameterizedTypeImpl(var0, var1, var2);
   }

   public static Type resolve(Type var0, Class var1, Type var2) {
      return resolve(var0, var1, var2, new HashSet());
   }

   private static Type resolve(Type var0, Class var1, Type var2, Collection var3) {
      while(true) {
         Type var25;
         if (var2 instanceof TypeVariable) {
            TypeVariable var22 = (TypeVariable)var2;
            if (var3.contains(var22)) {
               return var2;
            }

            var3.add(var22);
            var25 = resolveTypeVariable(var0, var1, var22);
            var2 = var25;
            if (var25 != var22) {
               continue;
            }

            return var25;
         }

         if (var2 instanceof Class) {
            Class var9 = (Class)var2;
            if (var9.isArray()) {
               Class var19 = var9.getComponentType();
               var0 = resolve(var0, var1, var19, var3);
               Object var18;
               if (var19 == var0) {
                  var18 = var9;
               } else {
                  var18 = arrayOf(var0);
               }

               return (Type)var18;
            }
         }

         if (var2 instanceof GenericArrayType) {
            GenericArrayType var17 = (GenericArrayType)var2;
            var25 = var17.getGenericComponentType();
            var0 = resolve(var0, var1, var25, var3);
            GenericArrayType var14;
            if (var25 == var0) {
               var14 = var17;
            } else {
               var14 = arrayOf(var0);
            }

            return var14;
         }

         boolean var8 = var2 instanceof ParameterizedType;
         int var6 = 0;
         if (var8) {
            ParameterizedType var21 = (ParameterizedType)var2;
            var2 = var21.getOwnerType();
            Type var24 = resolve(var0, var1, var2, var3);
            boolean var4;
            if (var24 != var2) {
               var4 = true;
            } else {
               var4 = false;
            }

            Type[] var16 = var21.getActualTypeArguments();

            Type[] var23;
            for(int var7 = var16.length; var6 < var7; var16 = var23) {
               Type var12 = resolve(var0, var1, var16[var6], var3);
               boolean var5 = var4;
               var23 = var16;
               if (var12 != var16[var6]) {
                  var5 = var4;
                  var23 = var16;
                  if (!var4) {
                     var23 = (Type[])var16.clone();
                     var5 = true;
                  }

                  var23[var6] = var12;
               }

               ++var6;
               var4 = var5;
            }

            ParameterizedType var13 = var21;
            if (var4) {
               var13 = newParameterizedTypeWithOwner(var24, var21.getRawType(), var16);
            }

            return var13;
         }

         Object var20 = var2;
         if (var2 instanceof WildcardType) {
            WildcardType var15 = (WildcardType)var2;
            Type[] var11 = var15.getLowerBounds();
            Type[] var10 = var15.getUpperBounds();
            if (var11.length == 1) {
               var0 = resolve(var0, var1, var11[0], var3);
               var20 = var15;
               if (var0 != var11[0]) {
                  return supertypeOf(var0);
               }
            } else {
               var20 = var15;
               if (var10.length == 1) {
                  var0 = resolve(var0, var1, var10[0], var3);
                  var20 = var15;
                  if (var0 != var10[0]) {
                     return subtypeOf(var0);
                  }
               }
            }
         }

         return (Type)var20;
      }
   }

   static Type resolveTypeVariable(Type var0, Class var1, TypeVariable var2) {
      Class var4 = declaringClassOf(var2);
      if (var4 == null) {
         return var2;
      } else {
         var0 = getGenericSupertype(var0, var1, var4);
         if (var0 instanceof ParameterizedType) {
            int var3 = indexOf(var4.getTypeParameters(), var2);
            return ((ParameterizedType)var0).getActualTypeArguments()[var3];
         } else {
            return var2;
         }
      }
   }

   public static WildcardType subtypeOf(Type var0) {
      Type[] var1;
      if (var0 instanceof WildcardType) {
         var1 = ((WildcardType)var0).getUpperBounds();
      } else {
         var1 = new Type[]{var0};
      }

      return new WildcardTypeImpl(var1, EMPTY_TYPE_ARRAY);
   }

   public static WildcardType supertypeOf(Type var0) {
      Type[] var1;
      if (var0 instanceof WildcardType) {
         var1 = ((WildcardType)var0).getLowerBounds();
      } else {
         var1 = new Type[]{var0};
      }

      return new WildcardTypeImpl(new Type[]{Object.class}, var1);
   }

   public static String typeToString(Type var0) {
      String var1;
      if (var0 instanceof Class) {
         var1 = ((Class)var0).getName();
      } else {
         var1 = var0.toString();
      }

      return var1;
   }

   private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
      private static final long serialVersionUID = 0L;
      private final Type componentType;

      public GenericArrayTypeImpl(Type var1) {
         this.componentType = $Gson$Types.canonicalize(var1);
      }

      public boolean equals(Object var1) {
         boolean var2;
         if (var1 instanceof GenericArrayType && $Gson$Types.equals(this, (GenericArrayType)var1)) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public Type getGenericComponentType() {
         return this.componentType;
      }

      public int hashCode() {
         return this.componentType.hashCode();
      }

      public String toString() {
         return $Gson$Types.typeToString(this.componentType) + "[]";
      }
   }

   private static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
      private static final long serialVersionUID = 0L;
      private final Type ownerType;
      private final Type rawType;
      private final Type[] typeArguments;

      public ParameterizedTypeImpl(Type var1, Type var2, Type... var3) {
         boolean var7 = var2 instanceof Class;
         byte var5 = 0;
         if (var7) {
            Class var9 = (Class)var2;
            var7 = Modifier.isStatic(var9.getModifiers());
            boolean var8 = true;
            boolean var4;
            if (!var7 && var9.getEnclosingClass() != null) {
               var4 = false;
            } else {
               var4 = true;
            }

            var7 = var8;
            if (var1 == null) {
               if (var4) {
                  var7 = var8;
               } else {
                  var7 = false;
               }
            }

            $Gson$Preconditions.checkArgument(var7);
         }

         if (var1 == null) {
            var1 = null;
         } else {
            var1 = $Gson$Types.canonicalize(var1);
         }

         this.ownerType = var1;
         this.rawType = $Gson$Types.canonicalize(var2);
         Type[] var10 = (Type[])var3.clone();
         this.typeArguments = var10;
         int var6 = var10.length;

         for(int var11 = var5; var11 < var6; ++var11) {
            $Gson$Preconditions.checkNotNull(this.typeArguments[var11]);
            $Gson$Types.checkNotPrimitive(this.typeArguments[var11]);
            var10 = this.typeArguments;
            var10[var11] = $Gson$Types.canonicalize(var10[var11]);
         }

      }

      public boolean equals(Object var1) {
         boolean var2;
         if (var1 instanceof ParameterizedType && $Gson$Types.equals(this, (ParameterizedType)var1)) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public Type[] getActualTypeArguments() {
         return (Type[])this.typeArguments.clone();
      }

      public Type getOwnerType() {
         return this.ownerType;
      }

      public Type getRawType() {
         return this.rawType;
      }

      public int hashCode() {
         return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ $Gson$Types.hashCodeOrZero(this.ownerType);
      }

      public String toString() {
         int var2 = this.typeArguments.length;
         if (var2 == 0) {
            return $Gson$Types.typeToString(this.rawType);
         } else {
            StringBuilder var3 = new StringBuilder((var2 + 1) * 30);
            var3.append($Gson$Types.typeToString(this.rawType)).append("<").append($Gson$Types.typeToString(this.typeArguments[0]));

            for(int var1 = 1; var1 < var2; ++var1) {
               var3.append(", ").append($Gson$Types.typeToString(this.typeArguments[var1]));
            }

            return var3.append(">").toString();
         }
      }
   }

   private static final class WildcardTypeImpl implements WildcardType, Serializable {
      private static final long serialVersionUID = 0L;
      private final Type lowerBound;
      private final Type upperBound;

      public WildcardTypeImpl(Type[] var1, Type[] var2) {
         int var3 = var2.length;
         boolean var5 = true;
         boolean var4;
         if (var3 <= 1) {
            var4 = true;
         } else {
            var4 = false;
         }

         $Gson$Preconditions.checkArgument(var4);
         if (var1.length == 1) {
            var4 = true;
         } else {
            var4 = false;
         }

         $Gson$Preconditions.checkArgument(var4);
         if (var2.length == 1) {
            $Gson$Preconditions.checkNotNull(var2[0]);
            $Gson$Types.checkNotPrimitive(var2[0]);
            if (var1[0] == Object.class) {
               var4 = var5;
            } else {
               var4 = false;
            }

            $Gson$Preconditions.checkArgument(var4);
            this.lowerBound = $Gson$Types.canonicalize(var2[0]);
            this.upperBound = Object.class;
         } else {
            $Gson$Preconditions.checkNotNull(var1[0]);
            $Gson$Types.checkNotPrimitive(var1[0]);
            this.lowerBound = null;
            this.upperBound = $Gson$Types.canonicalize(var1[0]);
         }

      }

      public boolean equals(Object var1) {
         boolean var2;
         if (var1 instanceof WildcardType && $Gson$Types.equals(this, (WildcardType)var1)) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public Type[] getLowerBounds() {
         Type var2 = this.lowerBound;
         Type[] var1;
         if (var2 != null) {
            var1 = new Type[]{var2};
         } else {
            var1 = $Gson$Types.EMPTY_TYPE_ARRAY;
         }

         return var1;
      }

      public Type[] getUpperBounds() {
         return new Type[]{this.upperBound};
      }

      public int hashCode() {
         Type var2 = this.lowerBound;
         int var1;
         if (var2 != null) {
            var1 = var2.hashCode() + 31;
         } else {
            var1 = 1;
         }

         return var1 ^ this.upperBound.hashCode() + 31;
      }

      public String toString() {
         if (this.lowerBound != null) {
            return "? super " + $Gson$Types.typeToString(this.lowerBound);
         } else {
            return this.upperBound == Object.class ? "?" : "? extends " + $Gson$Types.typeToString(this.upperBound);
         }
      }
   }
}
