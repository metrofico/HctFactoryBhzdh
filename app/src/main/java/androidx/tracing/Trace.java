package androidx.tracing;

import android.os.Build.VERSION;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Trace {
   static final String TAG = "Trace";
   private static Method sAsyncTraceBeginMethod;
   private static Method sAsyncTraceEndMethod;
   private static Method sIsTagEnabledMethod;
   private static Method sTraceCounterMethod;
   private static long sTraceTagApp;

   private Trace() {
   }

   public static void beginAsyncSection(String var0, int var1) {
      try {
         if (sAsyncTraceBeginMethod == null) {
            TraceApi29Impl.beginAsyncSection(var0, var1);
            return;
         }
      } catch (NoClassDefFoundError | NoSuchMethodError var3) {
      }

      beginAsyncSectionFallback(var0, var1);
   }

   private static void beginAsyncSectionFallback(String var0, int var1) {
      if (VERSION.SDK_INT >= 18) {
         try {
            if (sAsyncTraceBeginMethod == null) {
               sAsyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
            }

            sAsyncTraceBeginMethod.invoke((Object)null, sTraceTagApp, var0, var1);
         } catch (Exception var2) {
            handleException("asyncTraceBegin", var2);
         }
      }

   }

   public static void beginSection(String var0) {
      if (VERSION.SDK_INT >= 18) {
         TraceApi18Impl.beginSection(var0);
      }

   }

   public static void endAsyncSection(String var0, int var1) {
      try {
         if (sAsyncTraceEndMethod == null) {
            TraceApi29Impl.endAsyncSection(var0, var1);
            return;
         }
      } catch (NoClassDefFoundError | NoSuchMethodError var3) {
      }

      endAsyncSectionFallback(var0, var1);
   }

   private static void endAsyncSectionFallback(String var0, int var1) {
      if (VERSION.SDK_INT >= 18) {
         try {
            if (sAsyncTraceEndMethod == null) {
               sAsyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
            }

            sAsyncTraceEndMethod.invoke((Object)null, sTraceTagApp, var0, var1);
         } catch (Exception var2) {
            handleException("asyncTraceEnd", var2);
         }
      }

   }

   public static void endSection() {
      if (VERSION.SDK_INT >= 18) {
         TraceApi18Impl.endSection();
      }

   }

   private static void handleException(String var0, Exception var1) {
      if (var1 instanceof InvocationTargetException) {
         Throwable var2 = var1.getCause();
         if (var2 instanceof RuntimeException) {
            throw (RuntimeException)var2;
         } else {
            throw new RuntimeException(var2);
         }
      } else {
         Log.v("Trace", "Unable to call " + var0 + " via reflection", var1);
      }
   }

   public static boolean isEnabled() {
      try {
         if (sIsTagEnabledMethod == null) {
            boolean var0 = android.os.Trace.isEnabled();
            return var0;
         }
      } catch (NoClassDefFoundError | NoSuchMethodError var2) {
      }

      return isEnabledFallback();
   }

   private static boolean isEnabledFallback() {
      if (VERSION.SDK_INT >= 18) {
         try {
            if (sIsTagEnabledMethod == null) {
               sTraceTagApp = android.os.Trace.class.getField("TRACE_TAG_APP").getLong((Object)null);
               sIsTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }

            boolean var0 = (Boolean)sIsTagEnabledMethod.invoke((Object)null, sTraceTagApp);
            return var0;
         } catch (Exception var2) {
            handleException("isTagEnabled", var2);
         }
      }

      return false;
   }

   public static void setCounter(String var0, int var1) {
      try {
         if (sTraceCounterMethod == null) {
            TraceApi29Impl.setCounter(var0, var1);
            return;
         }
      } catch (NoClassDefFoundError | NoSuchMethodError var3) {
      }

      setCounterFallback(var0, var1);
   }

   private static void setCounterFallback(String var0, int var1) {
      if (VERSION.SDK_INT >= 18) {
         try {
            if (sTraceCounterMethod == null) {
               sTraceCounterMethod = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }

            sTraceCounterMethod.invoke((Object)null, sTraceTagApp, var0, var1);
         } catch (Exception var2) {
            handleException("traceCounter", var2);
         }
      }

   }
}
