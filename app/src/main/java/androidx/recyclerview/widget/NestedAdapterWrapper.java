package androidx.recyclerview.widget;

import android.view.ViewGroup;
import androidx.core.util.Preconditions;

class NestedAdapterWrapper {
   public final RecyclerView.Adapter adapter;
   private RecyclerView.AdapterDataObserver mAdapterObserver = new RecyclerView.AdapterDataObserver(this) {
      final NestedAdapterWrapper this$0;

      {
         this.this$0 = var1;
      }

      public void onChanged() {
         NestedAdapterWrapper var1 = this.this$0;
         var1.mCachedItemCount = var1.adapter.getItemCount();
         this.this$0.mCallback.onChanged(this.this$0);
      }

      public void onItemRangeChanged(int var1, int var2) {
         this.this$0.mCallback.onItemRangeChanged(this.this$0, var1, var2, (Object)null);
      }

      public void onItemRangeChanged(int var1, int var2, Object var3) {
         this.this$0.mCallback.onItemRangeChanged(this.this$0, var1, var2, var3);
      }

      public void onItemRangeInserted(int var1, int var2) {
         NestedAdapterWrapper var3 = this.this$0;
         var3.mCachedItemCount += var2;
         this.this$0.mCallback.onItemRangeInserted(this.this$0, var1, var2);
         if (this.this$0.mCachedItemCount > 0 && this.this$0.adapter.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
            this.this$0.mCallback.onStateRestorationPolicyChanged(this.this$0);
         }

      }

      public void onItemRangeMoved(int var1, int var2, int var3) {
         boolean var4 = true;
         if (var3 != 1) {
            var4 = false;
         }

         Preconditions.checkArgument(var4, "moving more than 1 item is not supported in RecyclerView");
         this.this$0.mCallback.onItemRangeMoved(this.this$0, var1, var2);
      }

      public void onItemRangeRemoved(int var1, int var2) {
         NestedAdapterWrapper var3 = this.this$0;
         var3.mCachedItemCount -= var2;
         this.this$0.mCallback.onItemRangeRemoved(this.this$0, var1, var2);
         if (this.this$0.mCachedItemCount < 1 && this.this$0.adapter.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
            this.this$0.mCallback.onStateRestorationPolicyChanged(this.this$0);
         }

      }

      public void onStateRestorationPolicyChanged() {
         this.this$0.mCallback.onStateRestorationPolicyChanged(this.this$0);
      }
   };
   int mCachedItemCount;
   final Callback mCallback;
   private final StableIdStorage.StableIdLookup mStableIdLookup;
   private final ViewTypeStorage.ViewTypeLookup mViewTypeLookup;

   NestedAdapterWrapper(RecyclerView.Adapter var1, Callback var2, ViewTypeStorage var3, StableIdStorage.StableIdLookup var4) {
      this.adapter = var1;
      this.mCallback = var2;
      this.mViewTypeLookup = var3.createViewTypeWrapper(this);
      this.mStableIdLookup = var4;
      this.mCachedItemCount = var1.getItemCount();
      var1.registerAdapterDataObserver(this.mAdapterObserver);
   }

   void dispose() {
      this.adapter.unregisterAdapterDataObserver(this.mAdapterObserver);
      this.mViewTypeLookup.dispose();
   }

   int getCachedItemCount() {
      return this.mCachedItemCount;
   }

   public long getItemId(int var1) {
      long var2 = this.adapter.getItemId(var1);
      return this.mStableIdLookup.localToGlobal(var2);
   }

   int getItemViewType(int var1) {
      return this.mViewTypeLookup.localToGlobal(this.adapter.getItemViewType(var1));
   }

   void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
      this.adapter.bindViewHolder(var1, var2);
   }

   RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      var2 = this.mViewTypeLookup.globalToLocal(var2);
      return this.adapter.onCreateViewHolder(var1, var2);
   }

   interface Callback {
      void onChanged(NestedAdapterWrapper var1);

      void onItemRangeChanged(NestedAdapterWrapper var1, int var2, int var3);

      void onItemRangeChanged(NestedAdapterWrapper var1, int var2, int var3, Object var4);

      void onItemRangeInserted(NestedAdapterWrapper var1, int var2, int var3);

      void onItemRangeMoved(NestedAdapterWrapper var1, int var2, int var3);

      void onItemRangeRemoved(NestedAdapterWrapper var1, int var2, int var3);

      void onStateRestorationPolicyChanged(NestedAdapterWrapper var1);
   }
}
