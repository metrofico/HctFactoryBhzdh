package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;

public class Intrinsics {
   private Intrinsics() {
   }

   public static boolean areEqual(double var0, Double var2) {
      boolean var3;
      if (var2 != null && var0 == var2) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static boolean areEqual(float var0, Float var1) {
      boolean var2;
      if (var1 != null && var0 == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static boolean areEqual(Double var0, double var1) {
      boolean var3;
      if (var0 != null && var0 == var1) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public static boolean areEqual(Double var0, Double var1) {
      boolean var2 = true;
      if (var0 == null) {
         if (var1 == null) {
            return var2;
         }
      } else if (var1 != null && var0 == var1) {
         return var2;
      }

      var2 = false;
      return var2;
   }

   public static boolean areEqual(Float var0, float var1) {
      boolean var2;
      if (var0 != null && var0 == var1) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public static boolean areEqual(Float var0, Float var1) {
      boolean var2 = true;
      if (var0 == null) {
         if (var1 == null) {
            return var2;
         }
      } else if (var1 != null && var0 == var1) {
         return var2;
      }

      var2 = false;
      return var2;
   }

   public static boolean areEqual(Object var0, Object var1) {
      boolean var2;
      if (var0 == null) {
         if (var1 == null) {
            var2 = true;
         } else {
            var2 = false;
         }
      } else {
         var2 = var0.equals(var1);
      }

      return var2;
   }

   public static void checkExpressionValueIsNotNull(Object var0, String var1) {
      if (var0 == null) {
         throw (IllegalStateException)sanitizeStackTrace(new IllegalStateException(var1 + " must not be null"));
      }
   }

   public static void checkFieldIsNotNull(Object var0, String var1) {
      if (var0 == null) {
         throw (IllegalStateException)sanitizeStackTrace(new IllegalStateException(var1));
      }
   }

   public static void checkFieldIsNotNull(Object var0, String var1, String var2) {
      if (var0 == null) {
         throw (IllegalStateException)sanitizeStackTrace(new IllegalStateException("Field specified as non-null is null: " + var1 + "." + var2));
      }
   }

   public static void checkHasClass(String var0) throws ClassNotFoundException {
      String var1 = var0.replace('/', '.');

      try {
         Class.forName(var1);
      } catch (ClassNotFoundException var2) {
         throw (ClassNotFoundException)sanitizeStackTrace(new ClassNotFoundException("Class " + var1 + " is not found. Please update the Kotlin runtime to the latest version", var2));
      }
   }

   public static void checkHasClass(String var0, String var1) throws ClassNotFoundException {
      String var2 = var0.replace('/', '.');

      try {
         Class.forName(var2);
      } catch (ClassNotFoundException var3) {
         throw (ClassNotFoundException)sanitizeStackTrace(new ClassNotFoundException("Class " + var2 + " is not found: this code requires the Kotlin runtime of version at least " + var1, var3));
      }
   }

   public static void checkNotNull(Object var0) {
      if (var0 == null) {
         throwJavaNpe();
      }

   }

   public static void checkNotNull(Object var0, String var1) {
      if (var0 == null) {
         throwJavaNpe(var1);
      }

   }

   public static void checkNotNullExpressionValue(Object var0, String var1) {
      if (var0 == null) {
         throw (NullPointerException)sanitizeStackTrace(new NullPointerException(var1 + " must not be null"));
      }
   }

   public static void checkNotNullParameter(Object var0, String var1) {
      if (var0 == null) {
         throwParameterIsNullNPE(var1);
      }

   }

   public static void checkParameterIsNotNull(Object var0, String var1) {
      if (var0 == null) {
         throwParameterIsNullIAE(var1);
      }

   }

   public static void checkReturnedValueIsNotNull(Object var0, String var1) {
      if (var0 == null) {
         throw (IllegalStateException)sanitizeStackTrace(new IllegalStateException(var1));
      }
   }

   public static void checkReturnedValueIsNotNull(Object var0, String var1, String var2) {
      if (var0 == null) {
         throw (IllegalStateException)sanitizeStackTrace(new IllegalStateException("Method specified as non-null returned null: " + var1 + "." + var2));
      }
   }

   public static int compare(int var0, int var1) {
      byte var2;
      if (var0 < var1) {
         var2 = -1;
      } else if (var0 == var1) {
         var2 = 0;
      } else {
         var2 = 1;
      }

      return var2;
   }

   public static int compare(long var0, long var2) {
      long var6;
      int var4 = (var6 = var0 - var2) == 0L ? 0 : (var6 < 0L ? -1 : 1);
      byte var5;
      if (var4 < 0) {
         var5 = -1;
      } else if (var4 == 0) {
         var5 = 0;
      } else {
         var5 = 1;
      }

      return var5;
   }

   private static String createParameterIsNullExceptionMessage(String var0) {
      StackTraceElement var2 = Thread.currentThread().getStackTrace()[4];
      String var1 = var2.getClassName();
      String var3 = var2.getMethodName();
      return "Parameter specified as non-null is null: method " + var1 + "." + var3 + ", parameter " + var0;
   }

   public static void needClassReification() {
      throwUndefinedForReified();
   }

   public static void needClassReification(String var0) {
      throwUndefinedForReified(var0);
   }

   public static void reifiedOperationMarker(int var0, String var1) {
      throwUndefinedForReified();
   }

   public static void reifiedOperationMarker(int var0, String var1, String var2) {
      throwUndefinedForReified(var2);
   }

   private static Throwable sanitizeStackTrace(Throwable var0) {
      return sanitizeStackTrace(var0, Intrinsics.class.getName());
   }

   static Throwable sanitizeStackTrace(Throwable var0, String var1) {
      StackTraceElement[] var5 = var0.getStackTrace();
      int var4 = var5.length;
      int var3 = -1;

      for(int var2 = 0; var2 < var4; ++var2) {
         if (var1.equals(var5[var2].getClassName())) {
            var3 = var2;
         }
      }

      var0.setStackTrace((StackTraceElement[])Arrays.copyOfRange(var5, var3 + 1, var4));
      return var0;
   }

   public static String stringPlus(String var0, Object var1) {
      return var0 + var1;
   }

   public static void throwAssert() {
      throw (AssertionError)sanitizeStackTrace(new AssertionError());
   }

   public static void throwAssert(String var0) {
      throw (AssertionError)sanitizeStackTrace(new AssertionError(var0));
   }

   public static void throwIllegalArgument() {
      throw (IllegalArgumentException)sanitizeStackTrace(new IllegalArgumentException());
   }

   public static void throwIllegalArgument(String var0) {
      throw (IllegalArgumentException)sanitizeStackTrace(new IllegalArgumentException(var0));
   }

   public static void throwIllegalState() {
      throw (IllegalStateException)sanitizeStackTrace(new IllegalStateException());
   }

   public static void throwIllegalState(String var0) {
      throw (IllegalStateException)sanitizeStackTrace(new IllegalStateException(var0));
   }

   public static void throwJavaNpe() {
      throw (NullPointerException)sanitizeStackTrace(new NullPointerException());
   }

   public static void throwJavaNpe(String var0) {
      throw (NullPointerException)sanitizeStackTrace(new NullPointerException(var0));
   }

   public static void throwNpe() {
      throw (KotlinNullPointerException)sanitizeStackTrace(new KotlinNullPointerException());
   }

   public static void throwNpe(String var0) {
      throw (KotlinNullPointerException)sanitizeStackTrace(new KotlinNullPointerException(var0));
   }

   private static void throwParameterIsNullIAE(String var0) {
      throw (IllegalArgumentException)sanitizeStackTrace(new IllegalArgumentException(createParameterIsNullExceptionMessage(var0)));
   }

   private static void throwParameterIsNullNPE(String var0) {
      throw (NullPointerException)sanitizeStackTrace(new NullPointerException(createParameterIsNullExceptionMessage(var0)));
   }

   public static void throwUndefinedForReified() {
      throwUndefinedForReified("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
   }

   public static void throwUndefinedForReified(String var0) {
      throw new UnsupportedOperationException(var0);
   }

   public static void throwUninitializedProperty(String var0) {
      throw (UninitializedPropertyAccessException)sanitizeStackTrace(new UninitializedPropertyAccessException(var0));
   }

   public static void throwUninitializedPropertyAccessException(String var0) {
      throwUninitializedProperty("lateinit property " + var0 + " has not been initialized");
   }

   public static class Kotlin {
      private Kotlin() {
      }
   }
}
