package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Build.VERSION;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

final class ActivityRecreator {
   private static final String LOG_TAG = "ActivityRecreator";
   protected static final Class activityThreadClass;
   private static final Handler mainHandler = new Handler(Looper.getMainLooper());
   protected static final Field mainThreadField;
   protected static final Method performStopActivity2ParamsMethod;
   protected static final Method performStopActivity3ParamsMethod;
   protected static final Method requestRelaunchActivityMethod;
   protected static final Field tokenField;

   static {
      Class var0 = getActivityThreadClass();
      activityThreadClass = var0;
      mainThreadField = getMainThreadField();
      tokenField = getTokenField();
      performStopActivity3ParamsMethod = getPerformStopActivity3Params(var0);
      performStopActivity2ParamsMethod = getPerformStopActivity2Params(var0);
      requestRelaunchActivityMethod = getRequestRelaunchActivityMethod(var0);
   }

   private ActivityRecreator() {
   }

   private static Class getActivityThreadClass() {
      try {
         Class var0 = Class.forName("android.app.ActivityThread");
         return var0;
      } finally {
         ;
      }
   }

   private static Field getMainThreadField() {
      try {
         Field var0 = Activity.class.getDeclaredField("mMainThread");
         var0.setAccessible(true);
         return var0;
      } finally {
         ;
      }
   }

   private static Method getPerformStopActivity2Params(Class var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            Method var3 = var0.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            var3.setAccessible(true);
            return var3;
         } finally {
            ;
         }
      }
   }

   private static Method getPerformStopActivity3Params(Class var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            Method var3 = var0.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            var3.setAccessible(true);
            return var3;
         } finally {
            ;
         }
      }
   }

   private static Method getRequestRelaunchActivityMethod(Class var0) {
      if (needsRelaunchCall() && var0 != null) {
         try {
            Method var3 = var0.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, Boolean.TYPE, Configuration.class, Configuration.class, Boolean.TYPE, Boolean.TYPE);
            var3.setAccessible(true);
            return var3;
         } finally {
            return null;
         }
      } else {
         return null;
      }
   }

   private static Field getTokenField() {
      try {
         Field var0 = Activity.class.getDeclaredField("mToken");
         var0.setAccessible(true);
         return var0;
      } finally {
         ;
      }
   }

   private static boolean needsRelaunchCall() {
      boolean var0;
      if (VERSION.SDK_INT != 26 && VERSION.SDK_INT != 27) {
         var0 = false;
      } else {
         var0 = true;
      }

      return var0;
   }

   protected static boolean queueOnStopIfNecessary(Object param0, int param1, Activity param2) {
      // $FF: Couldn't be decompiled
   }

   static boolean recreate(Activity var0) {
      if (VERSION.SDK_INT >= 28) {
         var0.recreate();
         return true;
      } else if (needsRelaunchCall() && requestRelaunchActivityMethod == null) {
         return false;
      } else if (performStopActivity2ParamsMethod == null && performStopActivity3ParamsMethod == null) {
         return false;
      } else {
         Object var6;
         boolean var10001;
         try {
            var6 = tokenField.get(var0);
         } catch (Throwable var62) {
            var10001 = false;
            return false;
         }

         if (var6 == null) {
            return false;
         } else {
            Object var5;
            try {
               var5 = mainThreadField.get(var0);
            } catch (Throwable var61) {
               var10001 = false;
               return false;
            }

            if (var5 == null) {
               return false;
            } else {
               LifecycleCheckCallbacks var1;
               Application var2;
               Handler var3;
               Runnable var4;
               try {
                  var2 = var0.getApplication();
                  var1 = new LifecycleCheckCallbacks(var0);
                  var2.registerActivityLifecycleCallbacks(var1);
                  var3 = mainHandler;
                  var4 = new Runnable(var1, var6) {
                     final LifecycleCheckCallbacks val$callbacks;
                     final Object val$token;

                     {
                        this.val$callbacks = var1;
                        this.val$token = var2;
                     }

                     public void run() {
                        this.val$callbacks.currentlyRecreatingToken = this.val$token;
                     }
                  };
                  var3.post(var4);
               } catch (Throwable var60) {
                  var10001 = false;
                  return false;
               }

               label610: {
                  Throwable var10000;
                  label586: {
                     try {
                        if (needsRelaunchCall()) {
                           requestRelaunchActivityMethod.invoke(var5, var6, null, null, 0, false, null, null, false, false);
                           break label610;
                        }
                     } catch (Throwable var59) {
                        var10000 = var59;
                        var10001 = false;
                        break label586;
                     }

                     label580:
                     try {
                        var0.recreate();
                        break label610;
                     } catch (Throwable var58) {
                        var10000 = var58;
                        var10001 = false;
                        break label580;
                     }
                  }

                  Throwable var65 = var10000;

                  try {
                     Handler var63 = mainHandler;
                     var4 = new Runnable(var2, var1) {
                        final Application val$application;
                        final LifecycleCheckCallbacks val$callbacks;

                        {
                           this.val$application = var1;
                           this.val$callbacks = var2;
                        }

                        public void run() {
                           this.val$application.unregisterActivityLifecycleCallbacks(this.val$callbacks);
                        }
                     };
                     var63.post(var4);
                     throw var65;
                  } catch (Throwable var56) {
                     var10001 = false;
                     return false;
                  }
               }

               try {
                  Runnable var64 = new Runnable(var2, var1) {
                     final Application val$application;
                     final LifecycleCheckCallbacks val$callbacks;

                     {
                        this.val$application = var1;
                        this.val$callbacks = var2;
                     }

                     public void run() {
                        this.val$application.unregisterActivityLifecycleCallbacks(this.val$callbacks);
                     }
                  };
                  var3.post(var64);
                  return true;
               } catch (Throwable var57) {
                  var10001 = false;
                  return false;
               }
            }
         }
      }
   }

   private static final class LifecycleCheckCallbacks implements Application.ActivityLifecycleCallbacks {
      Object currentlyRecreatingToken;
      private Activity mActivity;
      private boolean mDestroyed = false;
      private final int mRecreatingHashCode;
      private boolean mStarted = false;
      private boolean mStopQueued = false;

      LifecycleCheckCallbacks(Activity var1) {
         this.mActivity = var1;
         this.mRecreatingHashCode = var1.hashCode();
      }

      public void onActivityCreated(Activity var1, Bundle var2) {
      }

      public void onActivityDestroyed(Activity var1) {
         if (this.mActivity == var1) {
            this.mActivity = null;
            this.mDestroyed = true;
         }

      }

      public void onActivityPaused(Activity var1) {
         if (this.mDestroyed && !this.mStopQueued && !this.mStarted && ActivityRecreator.queueOnStopIfNecessary(this.currentlyRecreatingToken, this.mRecreatingHashCode, var1)) {
            this.mStopQueued = true;
            this.currentlyRecreatingToken = null;
         }

      }

      public void onActivityResumed(Activity var1) {
      }

      public void onActivitySaveInstanceState(Activity var1, Bundle var2) {
      }

      public void onActivityStarted(Activity var1) {
         if (this.mActivity == var1) {
            this.mStarted = true;
         }

      }

      public void onActivityStopped(Activity var1) {
      }
   }
}
