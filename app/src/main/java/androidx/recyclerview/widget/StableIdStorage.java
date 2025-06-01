package androidx.recyclerview.widget;

import androidx.collection.LongSparseArray;

interface StableIdStorage {
   StableIdLookup createStableIdLookup();

   public static class IsolatedStableIdStorage implements StableIdStorage {
      long mNextStableId = 0L;

      public StableIdLookup createStableIdLookup() {
         return new WrapperStableIdLookup(this);
      }

      long obtainId() {
         long var1 = (long)(this.mNextStableId++);
         return var1;
      }

      class WrapperStableIdLookup implements StableIdLookup {
         private final LongSparseArray mLocalToGlobalLookup;
         final IsolatedStableIdStorage this$0;

         WrapperStableIdLookup(IsolatedStableIdStorage var1) {
            this.this$0 = var1;
            this.mLocalToGlobalLookup = new LongSparseArray();
         }

         public long localToGlobal(long var1) {
            Long var4 = (Long)this.mLocalToGlobalLookup.get(var1);
            Long var3 = var4;
            if (var4 == null) {
               var3 = this.this$0.obtainId();
               this.mLocalToGlobalLookup.put(var1, var3);
            }

            return var3;
         }
      }
   }

   public static class NoStableIdStorage implements StableIdStorage {
      private final StableIdLookup mNoIdLookup = new StableIdLookup(this) {
         final NoStableIdStorage this$0;

         {
            this.this$0 = var1;
         }

         public long localToGlobal(long var1) {
            return -1L;
         }
      };

      public StableIdLookup createStableIdLookup() {
         return this.mNoIdLookup;
      }
   }

   public static class SharedPoolStableIdStorage implements StableIdStorage {
      private final StableIdLookup mSameIdLookup = new StableIdLookup(this) {
         final SharedPoolStableIdStorage this$0;

         {
            this.this$0 = var1;
         }

         public long localToGlobal(long var1) {
            return var1;
         }
      };

      public StableIdLookup createStableIdLookup() {
         return this.mSameIdLookup;
      }
   }

   public interface StableIdLookup {
      long localToGlobal(long var1);
   }
}
