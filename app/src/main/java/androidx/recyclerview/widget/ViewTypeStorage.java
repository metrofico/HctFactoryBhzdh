package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

interface ViewTypeStorage {
   ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper var1);

   NestedAdapterWrapper getWrapperForGlobalType(int var1);

   public static class IsolatedViewTypeStorage implements ViewTypeStorage {
      SparseArray mGlobalTypeToWrapper = new SparseArray();
      int mNextViewType = 0;

      public ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper var1) {
         return new WrapperViewTypeLookup(this, var1);
      }

      public NestedAdapterWrapper getWrapperForGlobalType(int var1) {
         NestedAdapterWrapper var2 = (NestedAdapterWrapper)this.mGlobalTypeToWrapper.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            throw new IllegalArgumentException("Cannot find the wrapper for global view type " + var1);
         }
      }

      int obtainViewType(NestedAdapterWrapper var1) {
         int var2 = this.mNextViewType++;
         this.mGlobalTypeToWrapper.put(var2, var1);
         return var2;
      }

      void removeWrapper(NestedAdapterWrapper var1) {
         for(int var2 = this.mGlobalTypeToWrapper.size() - 1; var2 >= 0; --var2) {
            if ((NestedAdapterWrapper)this.mGlobalTypeToWrapper.valueAt(var2) == var1) {
               this.mGlobalTypeToWrapper.removeAt(var2);
            }
         }

      }

      class WrapperViewTypeLookup implements ViewTypeLookup {
         private SparseIntArray mGlobalToLocalMapping;
         private SparseIntArray mLocalToGlobalMapping;
         final NestedAdapterWrapper mWrapper;
         final IsolatedViewTypeStorage this$0;

         WrapperViewTypeLookup(IsolatedViewTypeStorage var1, NestedAdapterWrapper var2) {
            this.this$0 = var1;
            this.mLocalToGlobalMapping = new SparseIntArray(1);
            this.mGlobalToLocalMapping = new SparseIntArray(1);
            this.mWrapper = var2;
         }

         public void dispose() {
            this.this$0.removeWrapper(this.mWrapper);
         }

         public int globalToLocal(int var1) {
            int var2 = this.mGlobalToLocalMapping.indexOfKey(var1);
            if (var2 >= 0) {
               return this.mGlobalToLocalMapping.valueAt(var2);
            } else {
               throw new IllegalStateException("requested global type " + var1 + " does not belong to the adapter:" + this.mWrapper.adapter);
            }
         }

         public int localToGlobal(int var1) {
            int var2 = this.mLocalToGlobalMapping.indexOfKey(var1);
            if (var2 > -1) {
               return this.mLocalToGlobalMapping.valueAt(var2);
            } else {
               var2 = this.this$0.obtainViewType(this.mWrapper);
               this.mLocalToGlobalMapping.put(var1, var2);
               this.mGlobalToLocalMapping.put(var2, var1);
               return var2;
            }
         }
      }
   }

   public static class SharedIdRangeViewTypeStorage implements ViewTypeStorage {
      SparseArray mGlobalTypeToWrapper = new SparseArray();

      public ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper var1) {
         return new WrapperViewTypeLookup(this, var1);
      }

      public NestedAdapterWrapper getWrapperForGlobalType(int var1) {
         List var2 = (List)this.mGlobalTypeToWrapper.get(var1);
         if (var2 != null && !var2.isEmpty()) {
            return (NestedAdapterWrapper)var2.get(0);
         } else {
            throw new IllegalArgumentException("Cannot find the wrapper for global view type " + var1);
         }
      }

      void removeWrapper(NestedAdapterWrapper var1) {
         for(int var2 = this.mGlobalTypeToWrapper.size() - 1; var2 >= 0; --var2) {
            List var3 = (List)this.mGlobalTypeToWrapper.valueAt(var2);
            if (var3.remove(var1) && var3.isEmpty()) {
               this.mGlobalTypeToWrapper.removeAt(var2);
            }
         }

      }

      class WrapperViewTypeLookup implements ViewTypeLookup {
         final NestedAdapterWrapper mWrapper;
         final SharedIdRangeViewTypeStorage this$0;

         WrapperViewTypeLookup(SharedIdRangeViewTypeStorage var1, NestedAdapterWrapper var2) {
            this.this$0 = var1;
            this.mWrapper = var2;
         }

         public void dispose() {
            this.this$0.removeWrapper(this.mWrapper);
         }

         public int globalToLocal(int var1) {
            return var1;
         }

         public int localToGlobal(int var1) {
            List var3 = (List)this.this$0.mGlobalTypeToWrapper.get(var1);
            Object var2 = var3;
            if (var3 == null) {
               var2 = new ArrayList();
               this.this$0.mGlobalTypeToWrapper.put(var1, var2);
            }

            if (!((List)var2).contains(this.mWrapper)) {
               ((List)var2).add(this.mWrapper);
            }

            return var1;
         }
      }
   }

   public interface ViewTypeLookup {
      void dispose();

      int globalToLocal(int var1);

      int localToGlobal(int var1);
   }
}
