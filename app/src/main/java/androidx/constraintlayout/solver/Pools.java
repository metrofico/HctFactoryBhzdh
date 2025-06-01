package androidx.constraintlayout.solver;

final class Pools {
   private static final boolean DEBUG = false;

   private Pools() {
   }

   interface Pool {
      Object acquire();

      boolean release(Object var1);

      void releaseAll(Object[] var1, int var2);
   }

   static class SimplePool implements Pool {
      private final Object[] mPool;
      private int mPoolSize;

      SimplePool(int var1) {
         if (var1 > 0) {
            this.mPool = new Object[var1];
         } else {
            throw new IllegalArgumentException("The max pool size must be > 0");
         }
      }

      private boolean isInPool(Object var1) {
         for(int var2 = 0; var2 < this.mPoolSize; ++var2) {
            if (this.mPool[var2] == var1) {
               return true;
            }
         }

         return false;
      }

      public Object acquire() {
         int var2 = this.mPoolSize;
         if (var2 > 0) {
            int var1 = var2 - 1;
            Object[] var4 = this.mPool;
            Object var3 = var4[var1];
            var4[var1] = null;
            this.mPoolSize = var2 - 1;
            return var3;
         } else {
            return null;
         }
      }

      public boolean release(Object var1) {
         int var2 = this.mPoolSize;
         Object[] var3 = this.mPool;
         if (var2 < var3.length) {
            var3[var2] = var1;
            this.mPoolSize = var2 + 1;
            return true;
         } else {
            return false;
         }
      }

      public void releaseAll(Object[] var1, int var2) {
         int var3 = var2;
         if (var2 > var1.length) {
            var3 = var1.length;
         }

         for(var2 = 0; var2 < var3; ++var2) {
            Object var5 = var1[var2];
            int var4 = this.mPoolSize;
            Object[] var6 = this.mPool;
            if (var4 < var6.length) {
               var6[var4] = var5;
               this.mPoolSize = var4 + 1;
            }
         }

      }
   }
}
