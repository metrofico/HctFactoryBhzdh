package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated
final class ClassesInfoCache {
   private static final int CALL_TYPE_NO_ARG = 0;
   private static final int CALL_TYPE_PROVIDER = 1;
   private static final int CALL_TYPE_PROVIDER_WITH_EVENT = 2;
   static ClassesInfoCache sInstance = new ClassesInfoCache();
   private final Map mCallbackMap = new HashMap();
   private final Map mHasLifecycleMethods = new HashMap();

   private CallbackInfo createInfo(Class var1, Method[] var2) {
      Class var8 = var1.getSuperclass();
      HashMap var7 = new HashMap();
      if (var8 != null) {
         CallbackInfo var13 = this.getInfo(var8);
         if (var13 != null) {
            var7.putAll(var13.mHandlerToEvent);
         }
      }

      Class[] var10 = var1.getInterfaces();
      int var4 = var10.length;

      for(int var3 = 0; var3 < var4; ++var3) {
         Iterator var9 = this.getInfo(var10[var3]).mHandlerToEvent.entrySet().iterator();

         while(var9.hasNext()) {
            Map.Entry var14 = (Map.Entry)var9.next();
            this.verifyAndPutHandler(var7, (MethodReference)var14.getKey(), (Lifecycle.Event)var14.getValue(), var1);
         }
      }

      if (var2 == null) {
         var2 = this.getDeclaredMethods(var1);
      }

      int var5 = var2.length;
      var4 = 0;

      boolean var6;
      for(var6 = false; var4 < var5; ++var4) {
         Method var16 = var2[var4];
         OnLifecycleEvent var17 = (OnLifecycleEvent)var16.getAnnotation(OnLifecycleEvent.class);
         if (var17 != null) {
            Class[] var15 = var16.getParameterTypes();
            byte var12;
            if (var15.length > 0) {
               if (!var15[0].isAssignableFrom(LifecycleOwner.class)) {
                  throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
               }

               var12 = 1;
            } else {
               var12 = 0;
            }

            Lifecycle.Event var18 = var17.value();
            if (var15.length > 1) {
               if (!var15[1].isAssignableFrom(Lifecycle.Event.class)) {
                  throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
               }

               if (var18 != Lifecycle.Event.ON_ANY) {
                  throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
               }

               var12 = 2;
            }

            if (var15.length > 2) {
               throw new IllegalArgumentException("cannot have more than 2 params");
            }

            this.verifyAndPutHandler(var7, new MethodReference(var12, var16), var18, var1);
            var6 = true;
         }
      }

      CallbackInfo var11 = new CallbackInfo(var7);
      this.mCallbackMap.put(var1, var11);
      this.mHasLifecycleMethods.put(var1, var6);
      return var11;
   }

   private Method[] getDeclaredMethods(Class var1) {
      try {
         Method[] var3 = var1.getDeclaredMethods();
         return var3;
      } catch (NoClassDefFoundError var2) {
         throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", var2);
      }
   }

   private void verifyAndPutHandler(Map var1, MethodReference var2, Lifecycle.Event var3, Class var4) {
      Lifecycle.Event var5 = (Lifecycle.Event)var1.get(var2);
      if (var5 != null && var3 != var5) {
         Method var6 = var2.mMethod;
         throw new IllegalArgumentException("Method " + var6.getName() + " in " + var4.getName() + " already declared with different @OnLifecycleEvent value: previous value " + var5 + ", new value " + var3);
      } else {
         if (var5 == null) {
            var1.put(var2, var3);
         }

      }
   }

   CallbackInfo getInfo(Class var1) {
      CallbackInfo var2 = (CallbackInfo)this.mCallbackMap.get(var1);
      return var2 != null ? var2 : this.createInfo(var1, (Method[])null);
   }

   boolean hasLifecycleMethods(Class var1) {
      Boolean var4 = (Boolean)this.mHasLifecycleMethods.get(var1);
      if (var4 != null) {
         return var4;
      } else {
         Method[] var5 = this.getDeclaredMethods(var1);
         int var3 = var5.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            if ((OnLifecycleEvent)var5[var2].getAnnotation(OnLifecycleEvent.class) != null) {
               this.createInfo(var1, var5);
               return true;
            }
         }

         this.mHasLifecycleMethods.put(var1, false);
         return false;
      }
   }

   @Deprecated
   static class CallbackInfo {
      final Map mEventToHandlers;
      final Map mHandlerToEvent;

      CallbackInfo(Map var1) {
         this.mHandlerToEvent = var1;
         this.mEventToHandlers = new HashMap();

         Map.Entry var3;
         Object var6;
         for(Iterator var4 = var1.entrySet().iterator(); var4.hasNext(); ((List)var6).add((MethodReference)var3.getKey())) {
            var3 = (Map.Entry)var4.next();
            Lifecycle.Event var5 = (Lifecycle.Event)var3.getValue();
            List var2 = (List)this.mEventToHandlers.get(var5);
            var6 = var2;
            if (var2 == null) {
               var6 = new ArrayList();
               this.mEventToHandlers.put(var5, var6);
            }
         }

      }

      private static void invokeMethodsForEvent(List var0, LifecycleOwner var1, Lifecycle.Event var2, Object var3) {
         if (var0 != null) {
            for(int var4 = var0.size() - 1; var4 >= 0; --var4) {
               ((MethodReference)var0.get(var4)).invokeCallback(var1, var2, var3);
            }
         }

      }

      void invokeCallbacks(LifecycleOwner var1, Lifecycle.Event var2, Object var3) {
         invokeMethodsForEvent((List)this.mEventToHandlers.get(var2), var1, var2, var3);
         invokeMethodsForEvent((List)this.mEventToHandlers.get(Lifecycle.Event.ON_ANY), var1, var2, var3);
      }
   }

   @Deprecated
   static final class MethodReference {
      final int mCallType;
      final Method mMethod;

      MethodReference(int var1, Method var2) {
         this.mCallType = var1;
         this.mMethod = var2;
         var2.setAccessible(true);
      }

      public boolean equals(Object var1) {
         boolean var2 = true;
         if (this == var1) {
            return true;
         } else if (!(var1 instanceof MethodReference)) {
            return false;
         } else {
            MethodReference var3 = (MethodReference)var1;
            if (this.mCallType != var3.mCallType || !this.mMethod.getName().equals(var3.mMethod.getName())) {
               var2 = false;
            }

            return var2;
         }
      }

      public int hashCode() {
         return this.mCallType * 31 + this.mMethod.getName().hashCode();
      }

      void invokeCallback(LifecycleOwner var1, Lifecycle.Event var2, Object var3) {
         InvocationTargetException var15;
         label49: {
            IllegalAccessException var10000;
            label48: {
               int var4;
               boolean var10001;
               try {
                  var4 = this.mCallType;
               } catch (InvocationTargetException var11) {
                  var15 = var11;
                  var10001 = false;
                  break label49;
               } catch (IllegalAccessException var12) {
                  var10000 = var12;
                  var10001 = false;
                  break label48;
               }

               if (var4 != 0) {
                  if (var4 != 1) {
                     if (var4 != 2) {
                        return;
                     }

                     try {
                        this.mMethod.invoke(var3, var1, var2);
                        return;
                     } catch (InvocationTargetException var5) {
                        var15 = var5;
                        var10001 = false;
                        break label49;
                     } catch (IllegalAccessException var6) {
                        var10000 = var6;
                        var10001 = false;
                     }
                  } else {
                     try {
                        this.mMethod.invoke(var3, var1);
                        return;
                     } catch (InvocationTargetException var7) {
                        var15 = var7;
                        var10001 = false;
                        break label49;
                     } catch (IllegalAccessException var8) {
                        var10000 = var8;
                        var10001 = false;
                     }
                  }
               } else {
                  try {
                     this.mMethod.invoke(var3);
                     return;
                  } catch (InvocationTargetException var9) {
                     var15 = var9;
                     var10001 = false;
                     break label49;
                  } catch (IllegalAccessException var10) {
                     var10000 = var10;
                     var10001 = false;
                  }
               }
            }

            IllegalAccessException var13 = var10000;
            throw new RuntimeException(var13);
         }

         InvocationTargetException var14 = var15;
         throw new RuntimeException("Failed to call observer method", var14.getCause());
      }
   }
}
