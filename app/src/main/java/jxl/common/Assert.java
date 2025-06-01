package jxl.common;

public final class Assert {
   public static void verify(boolean var0) {
      if (!var0) {
         throw new AssertionFailed();
      }
   }

   public static void verify(boolean var0, String var1) {
      if (!var0) {
         throw new AssertionFailed(var1);
      }
   }
}
