package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor {
   private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();
   private final Map instanceCreators;

   public ConstructorConstructor(Map var1) {
      this.instanceCreators = var1;
   }

   private ObjectConstructor newDefaultConstructor(Class var1) {
      try {
         Constructor var3 = var1.getDeclaredConstructor();
         if (!var3.isAccessible()) {
            this.accessor.makeAccessible(var3);
         }

         ObjectConstructor var4 = new ObjectConstructor(this, var3) {
            final ConstructorConstructor this$0;
            final Constructor val$constructor;

            {
               this.this$0 = var1;
               this.val$constructor = var2;
            }

            public Object construct() {
               try {
                  Object var1 = this.val$constructor.newInstance((Object[])null);
                  return var1;
               } catch (InstantiationException var2) {
                  throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", var2);
               } catch (InvocationTargetException var3) {
                  throw new RuntimeException("Failed to invoke " + this.val$constructor + " with no args", var3.getTargetException());
               } catch (IllegalAccessException var4) {
                  throw new AssertionError(var4);
               }
            }
         };
         return var4;
      } catch (NoSuchMethodException var2) {
         return null;
      }
   }

   private ObjectConstructor newDefaultImplementationConstructor(Type var1, Class var2) {
      if (Collection.class.isAssignableFrom(var2)) {
         if (SortedSet.class.isAssignableFrom(var2)) {
            return new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new TreeSet();
               }
            };
         } else if (EnumSet.class.isAssignableFrom(var2)) {
            return new ObjectConstructor(this, var1) {
               final ConstructorConstructor this$0;
               final Type val$type;

               {
                  this.this$0 = var1;
                  this.val$type = var2;
               }

               public Object construct() {
                  Type var1 = this.val$type;
                  if (var1 instanceof ParameterizedType) {
                     var1 = ((ParameterizedType)var1).getActualTypeArguments()[0];
                     if (var1 instanceof Class) {
                        return EnumSet.noneOf((Class)var1);
                     } else {
                        throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
                     }
                  } else {
                     throw new JsonIOException("Invalid EnumSet type: " + this.val$type.toString());
                  }
               }
            };
         } else if (Set.class.isAssignableFrom(var2)) {
            return new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new LinkedHashSet();
               }
            };
         } else {
            return Queue.class.isAssignableFrom(var2) ? new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new ArrayDeque();
               }
            } : new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new ArrayList();
               }
            };
         }
      } else if (Map.class.isAssignableFrom(var2)) {
         if (ConcurrentNavigableMap.class.isAssignableFrom(var2)) {
            return new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new ConcurrentSkipListMap();
               }
            };
         } else if (ConcurrentMap.class.isAssignableFrom(var2)) {
            return new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new ConcurrentHashMap();
               }
            };
         } else if (SortedMap.class.isAssignableFrom(var2)) {
            return new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new TreeMap();
               }
            };
         } else {
            return var1 instanceof ParameterizedType && !String.class.isAssignableFrom(TypeToken.get(((ParameterizedType)var1).getActualTypeArguments()[0]).getRawType()) ? new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new LinkedHashMap();
               }
            } : new ObjectConstructor(this) {
               final ConstructorConstructor this$0;

               {
                  this.this$0 = var1;
               }

               public Object construct() {
                  return new LinkedTreeMap();
               }
            };
         }
      } else {
         return null;
      }
   }

   private ObjectConstructor newUnsafeAllocator(Type var1, Class var2) {
      return new ObjectConstructor(this, var2, var1) {
         final ConstructorConstructor this$0;
         private final UnsafeAllocator unsafeAllocator;
         final Class val$rawType;
         final Type val$type;

         {
            this.this$0 = var1;
            this.val$rawType = var2;
            this.val$type = var3;
            this.unsafeAllocator = UnsafeAllocator.create();
         }

         public Object construct() {
            try {
               Object var1 = this.unsafeAllocator.newInstance(this.val$rawType);
               return var1;
            } catch (Exception var2) {
               throw new RuntimeException("Unable to invoke no-args constructor for " + this.val$type + ". Registering an InstanceCreator with Gson for this type may fix this problem.", var2);
            }
         }
      };
   }

   public ObjectConstructor get(TypeToken var1) {
      Type var2 = var1.getType();
      Class var4 = var1.getRawType();
      InstanceCreator var3 = (InstanceCreator)this.instanceCreators.get(var2);
      if (var3 != null) {
         return new ObjectConstructor(this, var3, var2) {
            final ConstructorConstructor this$0;
            final Type val$type;
            final InstanceCreator val$typeCreator;

            {
               this.this$0 = var1;
               this.val$typeCreator = var2;
               this.val$type = var3;
            }

            public Object construct() {
               return this.val$typeCreator.createInstance(this.val$type);
            }
         };
      } else {
         var3 = (InstanceCreator)this.instanceCreators.get(var4);
         if (var3 != null) {
            return new ObjectConstructor(this, var3, var2) {
               final ConstructorConstructor this$0;
               final InstanceCreator val$rawTypeCreator;
               final Type val$type;

               {
                  this.this$0 = var1;
                  this.val$rawTypeCreator = var2;
                  this.val$type = var3;
               }

               public Object construct() {
                  return this.val$rawTypeCreator.createInstance(this.val$type);
               }
            };
         } else {
            ObjectConstructor var5 = this.newDefaultConstructor(var4);
            if (var5 != null) {
               return var5;
            } else {
               var5 = this.newDefaultImplementationConstructor(var2, var4);
               return var5 != null ? var5 : this.newUnsafeAllocator(var2, var4);
            }
         }
      }
   }

   public String toString() {
      return this.instanceCreators.toString();
   }
}
