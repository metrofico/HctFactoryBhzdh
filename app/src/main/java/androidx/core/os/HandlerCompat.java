package androidx.core.os;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

public final class HandlerCompat {
   private static final String TAG = "HandlerCompat";

   private HandlerCompat() {
   }

   public static Handler createAsync(Looper var0) {
      if (VERSION.SDK_INT >= 28) {
         return Handler.createAsync(var0);
      } else {
         if (VERSION.SDK_INT >= 17) {
            Object var1;
            try {
               Handler var7 = (Handler)Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(var0, null, true);
               return var7;
            } catch (IllegalAccessException var2) {
               var1 = var2;
            } catch (InstantiationException var3) {
               var1 = var3;
            } catch (NoSuchMethodException var4) {
               var1 = var4;
            } catch (InvocationTargetException var5) {
               Throwable var6 = var5.getCause();
               if (!(var6 instanceof RuntimeException)) {
                  if (var6 instanceof Error) {
                     throw (Error)var6;
                  }

                  throw new RuntimeException(var6);
               }

               throw (RuntimeException)var6;
            }

            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", (Throwable)var1);
         }

         return new Handler(var0);
      }
   }

   public static Handler createAsync(Looper var0, Handler.Callback var1) {
      if (VERSION.SDK_INT >= 28) {
         return Handler.createAsync(var0, var1);
      } else {
         if (VERSION.SDK_INT >= 17) {
            Object var2;
            try {
               Handler var8 = (Handler)Handler.class.getDeclaredConstructor(Looper.class, Handler.Callback.class, Boolean.TYPE).newInstance(var0, var1, true);
               return var8;
            } catch (IllegalAccessException var3) {
               var2 = var3;
            } catch (InstantiationException var4) {
               var2 = var4;
            } catch (NoSuchMethodException var5) {
               var2 = var5;
            } catch (InvocationTargetException var6) {
               Throwable var7 = var6.getCause();
               if (!(var7 instanceof RuntimeException)) {
                  if (var7 instanceof Error) {
                     throw (Error)var7;
                  }

                  throw new RuntimeException(var7);
               }

               throw (RuntimeException)var7;
            }

            Log.w("HandlerCompat", "Unable to invoke Handler(Looper, Callback, boolean) constructor", (Throwable)var2);
         }

         return new Handler(var0, var1);
      }
   }

   public static boolean hasCallbacks(Handler var0, Runnable var1) {
      if (VERSION.SDK_INT >= 29) {
         return var0.hasCallbacks(var1);
      } else {
         Object var7;
         if (VERSION.SDK_INT >= 16) {
            try {
               boolean var2 = (Boolean)Handler.class.getMethod("hasCallbacks", Runnable.class).invoke(var0, var1);
               return var2;
            } catch (InvocationTargetException var3) {
               Throwable var8 = var3.getCause();
               if (!(var8 instanceof RuntimeException)) {
                  if (var8 instanceof Error) {
                     throw (Error)var8;
                  }

                  throw new RuntimeException(var8);
               }

               throw (RuntimeException)var8;
            } catch (IllegalAccessException var4) {
               var7 = var4;
            } catch (NoSuchMethodException var5) {
               var7 = var5;
            } catch (NullPointerException var6) {
               var7 = var6;
            }
         } else {
            var7 = null;
         }

         throw new UnsupportedOperationException("Failed to call Handler.hasCallbacks(), but there is no safe failure mode for this method. Raising exception.", (Throwable)var7);
      }
   }

   public static boolean postDelayed(Handler var0, Runnable var1, Object var2, long var3) {
      if (VERSION.SDK_INT >= 28) {
         return var0.postDelayed(var1, var2, var3);
      } else {
         Message var5 = Message.obtain(var0, var1);
         var5.obj = var2;
         return var0.sendMessageDelayed(var5, var3);
      }
   }

   private static class Api28Impl {
      public static Handler createAsync(Looper var0) {
         return Handler.createAsync(var0);
      }

      public static Handler createAsync(Looper var0, Handler.Callback var1) {
         return Handler.createAsync(var0, var1);
      }

      public static boolean postDelayed(Handler var0, Runnable var1, Object var2, long var3) {
         return var0.postDelayed(var1, var2, var3);
      }
   }

   private static class Api29Impl {
      public static boolean hasCallbacks(Handler var0, Runnable var1) {
         return var0.hasCallbacks(var1);
      }
   }
}
