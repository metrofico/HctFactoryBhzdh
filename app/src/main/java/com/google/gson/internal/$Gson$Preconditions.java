package com.google.gson.internal;

public final class $Gson$Preconditions {
   private $Gson$Preconditions() {
      throw new UnsupportedOperationException();
   }

   public static void checkArgument(boolean var0) {
      if (!var0) {
         throw new IllegalArgumentException();
      }
   }

   public static Object checkNotNull(Object var0) {
      var0.getClass();
      return var0;
   }
}
