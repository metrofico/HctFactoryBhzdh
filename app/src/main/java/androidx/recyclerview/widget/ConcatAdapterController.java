package androidx.recyclerview.widget;

import android.util.Log;
import android.view.ViewGroup;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

class ConcatAdapterController implements NestedAdapterWrapper.Callback {
   private List mAttachedRecyclerViews = new ArrayList();
   private final IdentityHashMap mBinderLookup = new IdentityHashMap();
   private final ConcatAdapter mConcatAdapter;
   private WrapperAndLocalPosition mReusableHolder = new WrapperAndLocalPosition();
   private final ConcatAdapter.Config.StableIdMode mStableIdMode;
   private final StableIdStorage mStableIdStorage;
   private final ViewTypeStorage mViewTypeStorage;
   private List mWrappers = new ArrayList();

   ConcatAdapterController(ConcatAdapter var1, ConcatAdapter.Config var2) {
      this.mConcatAdapter = var1;
      if (var2.isolateViewTypes) {
         this.mViewTypeStorage = new ViewTypeStorage.IsolatedViewTypeStorage();
      } else {
         this.mViewTypeStorage = new ViewTypeStorage.SharedIdRangeViewTypeStorage();
      }

      this.mStableIdMode = var2.stableIdMode;
      if (var2.stableIdMode == ConcatAdapter.Config.StableIdMode.NO_STABLE_IDS) {
         this.mStableIdStorage = new StableIdStorage.NoStableIdStorage();
      } else if (var2.stableIdMode == ConcatAdapter.Config.StableIdMode.ISOLATED_STABLE_IDS) {
         this.mStableIdStorage = new StableIdStorage.IsolatedStableIdStorage();
      } else {
         if (var2.stableIdMode != ConcatAdapter.Config.StableIdMode.SHARED_STABLE_IDS) {
            throw new IllegalArgumentException("unknown stable id mode");
         }

         this.mStableIdStorage = new StableIdStorage.SharedPoolStableIdStorage();
      }

   }

   private void calculateAndUpdateStateRestorationPolicy() {
      RecyclerView.Adapter.StateRestorationPolicy var1 = this.computeStateRestorationPolicy();
      if (var1 != this.mConcatAdapter.getStateRestorationPolicy()) {
         this.mConcatAdapter.internalSetStateRestorationPolicy(var1);
      }

   }

   private RecyclerView.Adapter.StateRestorationPolicy computeStateRestorationPolicy() {
      Iterator var2 = this.mWrappers.iterator();

      NestedAdapterWrapper var1;
      RecyclerView.Adapter.StateRestorationPolicy var3;
      do {
         if (!var2.hasNext()) {
            return RecyclerView.Adapter.StateRestorationPolicy.ALLOW;
         }

         var1 = (NestedAdapterWrapper)var2.next();
         var3 = var1.adapter.getStateRestorationPolicy();
         if (var3 == RecyclerView.Adapter.StateRestorationPolicy.PREVENT) {
            return RecyclerView.Adapter.StateRestorationPolicy.PREVENT;
         }
      } while(var3 != RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY || var1.getCachedItemCount() != 0);

      return RecyclerView.Adapter.StateRestorationPolicy.PREVENT;
   }

   private int countItemsBefore(NestedAdapterWrapper var1) {
      Iterator var3 = this.mWrappers.iterator();

      int var2;
      NestedAdapterWrapper var4;
      for(var2 = 0; var3.hasNext(); var2 += var4.getCachedItemCount()) {
         var4 = (NestedAdapterWrapper)var3.next();
         if (var4 == var1) {
            break;
         }
      }

      return var2;
   }

   private WrapperAndLocalPosition findWrapperAndLocalPosition(int var1) {
      WrapperAndLocalPosition var3;
      if (this.mReusableHolder.mInUse) {
         var3 = new WrapperAndLocalPosition();
      } else {
         this.mReusableHolder.mInUse = true;
         var3 = this.mReusableHolder;
      }

      Iterator var4 = this.mWrappers.iterator();

      NestedAdapterWrapper var5;
      for(int var2 = var1; var4.hasNext(); var2 -= var5.getCachedItemCount()) {
         var5 = (NestedAdapterWrapper)var4.next();
         if (var5.getCachedItemCount() > var2) {
            var3.mWrapper = var5;
            var3.mLocalPosition = var2;
            break;
         }
      }

      if (var3.mWrapper != null) {
         return var3;
      } else {
         throw new IllegalArgumentException("Cannot find wrapper for " + var1);
      }
   }

   private NestedAdapterWrapper findWrapperFor(RecyclerView.Adapter var1) {
      int var2 = this.indexOfWrapper(var1);
      return var2 == -1 ? null : (NestedAdapterWrapper)this.mWrappers.get(var2);
   }

   private NestedAdapterWrapper getWrapper(RecyclerView.ViewHolder var1) {
      NestedAdapterWrapper var2 = (NestedAdapterWrapper)this.mBinderLookup.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         throw new IllegalStateException("Cannot find wrapper for " + var1 + ", seems like it is not bound by this adapter: " + this);
      }
   }

   private int indexOfWrapper(RecyclerView.Adapter var1) {
      int var3 = this.mWrappers.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         if (((NestedAdapterWrapper)this.mWrappers.get(var2)).adapter == var1) {
            return var2;
         }
      }

      return -1;
   }

   private boolean isAttachedTo(RecyclerView var1) {
      Iterator var2 = this.mAttachedRecyclerViews.iterator();

      do {
         if (!var2.hasNext()) {
            return false;
         }
      } while(((WeakReference)var2.next()).get() != var1);

      return true;
   }

   private void releaseWrapperAndLocalPosition(WrapperAndLocalPosition var1) {
      var1.mInUse = false;
      var1.mWrapper = null;
      var1.mLocalPosition = -1;
      this.mReusableHolder = var1;
   }

   boolean addAdapter(int var1, RecyclerView.Adapter var2) {
      if (var1 >= 0 && var1 <= this.mWrappers.size()) {
         if (this.hasStableIds()) {
            Preconditions.checkArgument(var2.hasStableIds(), "All sub adapters must have stable ids when stable id mode is ISOLATED_STABLE_IDS or SHARED_STABLE_IDS");
         } else if (var2.hasStableIds()) {
            Log.w("ConcatAdapter", "Stable ids in the adapter will be ignored as the ConcatAdapter is configured not to have stable ids");
         }

         if (this.findWrapperFor(var2) != null) {
            return false;
         } else {
            NestedAdapterWrapper var4 = new NestedAdapterWrapper(var2, this, this.mViewTypeStorage, this.mStableIdStorage.createStableIdLookup());
            this.mWrappers.add(var1, var4);
            Iterator var3 = this.mAttachedRecyclerViews.iterator();

            while(var3.hasNext()) {
               RecyclerView var5 = (RecyclerView)((WeakReference)var3.next()).get();
               if (var5 != null) {
                  var2.onAttachedToRecyclerView(var5);
               }
            }

            if (var4.getCachedItemCount() > 0) {
               this.mConcatAdapter.notifyItemRangeInserted(this.countItemsBefore(var4), var4.getCachedItemCount());
            }

            this.calculateAndUpdateStateRestorationPolicy();
            return true;
         }
      } else {
         throw new IndexOutOfBoundsException("Index must be between 0 and " + this.mWrappers.size() + ". Given:" + var1);
      }
   }

   boolean addAdapter(RecyclerView.Adapter var1) {
      return this.addAdapter(this.mWrappers.size(), var1);
   }

   public boolean canRestoreState() {
      Iterator var1 = this.mWrappers.iterator();

      do {
         if (!var1.hasNext()) {
            return true;
         }
      } while(((NestedAdapterWrapper)var1.next()).adapter.canRestoreState());

      return false;
   }

   public RecyclerView.Adapter getBoundAdapter(RecyclerView.ViewHolder var1) {
      NestedAdapterWrapper var2 = (NestedAdapterWrapper)this.mBinderLookup.get(var1);
      return var2 == null ? null : var2.adapter;
   }

   public List getCopyOfAdapters() {
      if (this.mWrappers.isEmpty()) {
         return Collections.emptyList();
      } else {
         ArrayList var1 = new ArrayList(this.mWrappers.size());
         Iterator var2 = this.mWrappers.iterator();

         while(var2.hasNext()) {
            var1.add(((NestedAdapterWrapper)var2.next()).adapter);
         }

         return var1;
      }
   }

   public long getItemId(int var1) {
      WrapperAndLocalPosition var4 = this.findWrapperAndLocalPosition(var1);
      long var2 = var4.mWrapper.getItemId(var4.mLocalPosition);
      this.releaseWrapperAndLocalPosition(var4);
      return var2;
   }

   public int getItemViewType(int var1) {
      WrapperAndLocalPosition var2 = this.findWrapperAndLocalPosition(var1);
      var1 = var2.mWrapper.getItemViewType(var2.mLocalPosition);
      this.releaseWrapperAndLocalPosition(var2);
      return var1;
   }

   public int getLocalAdapterPosition(RecyclerView.Adapter var1, RecyclerView.ViewHolder var2, int var3) {
      NestedAdapterWrapper var5 = (NestedAdapterWrapper)this.mBinderLookup.get(var2);
      if (var5 == null) {
         return -1;
      } else {
         var3 -= this.countItemsBefore(var5);
         int var4 = var5.adapter.getItemCount();
         if (var3 >= 0 && var3 < var4) {
            return var5.adapter.findRelativeAdapterPositionIn(var1, var2, var3);
         } else {
            throw new IllegalStateException("Detected inconsistent adapter updates. The local position of the view holder maps to " + var3 + " which is out of bounds for the adapter with size " + var4 + ".Make sure to immediately call notify methods in your adapter when you change the backing dataviewHolder:" + var2 + "adapter:" + var1);
         }
      }
   }

   public int getTotalCount() {
      Iterator var2 = this.mWrappers.iterator();

      int var1;
      for(var1 = 0; var2.hasNext(); var1 += ((NestedAdapterWrapper)var2.next()).getCachedItemCount()) {
      }

      return var1;
   }

   public boolean hasStableIds() {
      boolean var1;
      if (this.mStableIdMode != ConcatAdapter.Config.StableIdMode.NO_STABLE_IDS) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void onAttachedToRecyclerView(RecyclerView var1) {
      if (!this.isAttachedTo(var1)) {
         this.mAttachedRecyclerViews.add(new WeakReference(var1));
         Iterator var2 = this.mWrappers.iterator();

         while(var2.hasNext()) {
            ((NestedAdapterWrapper)var2.next()).adapter.onAttachedToRecyclerView(var1);
         }

      }
   }

   public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
      WrapperAndLocalPosition var3 = this.findWrapperAndLocalPosition(var2);
      this.mBinderLookup.put(var1, var3.mWrapper);
      var3.mWrapper.onBindViewHolder(var1, var3.mLocalPosition);
      this.releaseWrapperAndLocalPosition(var3);
   }

   public void onChanged(NestedAdapterWrapper var1) {
      this.mConcatAdapter.notifyDataSetChanged();
      this.calculateAndUpdateStateRestorationPolicy();
   }

   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return this.mViewTypeStorage.getWrapperForGlobalType(var2).onCreateViewHolder(var1, var2);
   }

   public void onDetachedFromRecyclerView(RecyclerView var1) {
      for(int var2 = this.mAttachedRecyclerViews.size() - 1; var2 >= 0; --var2) {
         WeakReference var3 = (WeakReference)this.mAttachedRecyclerViews.get(var2);
         if (var3.get() == null) {
            this.mAttachedRecyclerViews.remove(var2);
         } else if (var3.get() == var1) {
            this.mAttachedRecyclerViews.remove(var2);
            break;
         }
      }

      Iterator var4 = this.mWrappers.iterator();

      while(var4.hasNext()) {
         ((NestedAdapterWrapper)var4.next()).adapter.onDetachedFromRecyclerView(var1);
      }

   }

   public boolean onFailedToRecycleView(RecyclerView.ViewHolder var1) {
      NestedAdapterWrapper var2 = (NestedAdapterWrapper)this.mBinderLookup.remove(var1);
      if (var2 != null) {
         return var2.adapter.onFailedToRecycleView(var1);
      } else {
         throw new IllegalStateException("Cannot find wrapper for " + var1 + ", seems like it is not bound by this adapter: " + this);
      }
   }

   public void onItemRangeChanged(NestedAdapterWrapper var1, int var2, int var3) {
      int var4 = this.countItemsBefore(var1);
      this.mConcatAdapter.notifyItemRangeChanged(var2 + var4, var3);
   }

   public void onItemRangeChanged(NestedAdapterWrapper var1, int var2, int var3, Object var4) {
      int var5 = this.countItemsBefore(var1);
      this.mConcatAdapter.notifyItemRangeChanged(var2 + var5, var3, var4);
   }

   public void onItemRangeInserted(NestedAdapterWrapper var1, int var2, int var3) {
      int var4 = this.countItemsBefore(var1);
      this.mConcatAdapter.notifyItemRangeInserted(var2 + var4, var3);
   }

   public void onItemRangeMoved(NestedAdapterWrapper var1, int var2, int var3) {
      int var4 = this.countItemsBefore(var1);
      this.mConcatAdapter.notifyItemMoved(var2 + var4, var3 + var4);
   }

   public void onItemRangeRemoved(NestedAdapterWrapper var1, int var2, int var3) {
      int var4 = this.countItemsBefore(var1);
      this.mConcatAdapter.notifyItemRangeRemoved(var2 + var4, var3);
   }

   public void onStateRestorationPolicyChanged(NestedAdapterWrapper var1) {
      this.calculateAndUpdateStateRestorationPolicy();
   }

   public void onViewAttachedToWindow(RecyclerView.ViewHolder var1) {
      this.getWrapper(var1).adapter.onViewAttachedToWindow(var1);
   }

   public void onViewDetachedFromWindow(RecyclerView.ViewHolder var1) {
      this.getWrapper(var1).adapter.onViewDetachedFromWindow(var1);
   }

   public void onViewRecycled(RecyclerView.ViewHolder var1) {
      NestedAdapterWrapper var2 = (NestedAdapterWrapper)this.mBinderLookup.remove(var1);
      if (var2 != null) {
         var2.adapter.onViewRecycled(var1);
      } else {
         throw new IllegalStateException("Cannot find wrapper for " + var1 + ", seems like it is not bound by this adapter: " + this);
      }
   }

   boolean removeAdapter(RecyclerView.Adapter var1) {
      int var3 = this.indexOfWrapper(var1);
      if (var3 == -1) {
         return false;
      } else {
         NestedAdapterWrapper var6 = (NestedAdapterWrapper)this.mWrappers.get(var3);
         int var2 = this.countItemsBefore(var6);
         this.mWrappers.remove(var3);
         this.mConcatAdapter.notifyItemRangeRemoved(var2, var6.getCachedItemCount());
         Iterator var5 = this.mAttachedRecyclerViews.iterator();

         while(var5.hasNext()) {
            RecyclerView var4 = (RecyclerView)((WeakReference)var5.next()).get();
            if (var4 != null) {
               var1.onDetachedFromRecyclerView(var4);
            }
         }

         var6.dispose();
         this.calculateAndUpdateStateRestorationPolicy();
         return true;
      }
   }

   static class WrapperAndLocalPosition {
      boolean mInUse;
      int mLocalPosition;
      NestedAdapterWrapper mWrapper;
   }
}
