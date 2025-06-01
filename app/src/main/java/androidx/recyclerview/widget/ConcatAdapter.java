package androidx.recyclerview.widget;

import android.view.ViewGroup;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ConcatAdapter extends RecyclerView.Adapter {
   static final String TAG = "ConcatAdapter";
   private final ConcatAdapterController mController;

   public ConcatAdapter(Config var1, List var2) {
      this.mController = new ConcatAdapterController(this, var1);
      Iterator var3 = var2.iterator();

      while(var3.hasNext()) {
         this.addAdapter((RecyclerView.Adapter)var3.next());
      }

      super.setHasStableIds(this.mController.hasStableIds());
   }

   @SafeVarargs
   public ConcatAdapter(Config var1, RecyclerView.Adapter... var2) {
      this(var1, Arrays.asList(var2));
   }

   public ConcatAdapter(List var1) {
      this(ConcatAdapter.Config.DEFAULT, var1);
   }

   @SafeVarargs
   public ConcatAdapter(RecyclerView.Adapter... var1) {
      this(ConcatAdapter.Config.DEFAULT, var1);
   }

   public boolean addAdapter(int var1, RecyclerView.Adapter var2) {
      return this.mController.addAdapter(var1, var2);
   }

   public boolean addAdapter(RecyclerView.Adapter var1) {
      return this.mController.addAdapter(var1);
   }

   public int findRelativeAdapterPositionIn(RecyclerView.Adapter var1, RecyclerView.ViewHolder var2, int var3) {
      return this.mController.getLocalAdapterPosition(var1, var2, var3);
   }

   public List getAdapters() {
      return Collections.unmodifiableList(this.mController.getCopyOfAdapters());
   }

   public int getItemCount() {
      return this.mController.getTotalCount();
   }

   public long getItemId(int var1) {
      return this.mController.getItemId(var1);
   }

   public int getItemViewType(int var1) {
      return this.mController.getItemViewType(var1);
   }

   void internalSetStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy var1) {
      super.setStateRestorationPolicy(var1);
   }

   public void onAttachedToRecyclerView(RecyclerView var1) {
      this.mController.onAttachedToRecyclerView(var1);
   }

   public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
      this.mController.onBindViewHolder(var1, var2);
   }

   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
      return this.mController.onCreateViewHolder(var1, var2);
   }

   public void onDetachedFromRecyclerView(RecyclerView var1) {
      this.mController.onDetachedFromRecyclerView(var1);
   }

   public boolean onFailedToRecycleView(RecyclerView.ViewHolder var1) {
      return this.mController.onFailedToRecycleView(var1);
   }

   public void onViewAttachedToWindow(RecyclerView.ViewHolder var1) {
      this.mController.onViewAttachedToWindow(var1);
   }

   public void onViewDetachedFromWindow(RecyclerView.ViewHolder var1) {
      this.mController.onViewDetachedFromWindow(var1);
   }

   public void onViewRecycled(RecyclerView.ViewHolder var1) {
      this.mController.onViewRecycled(var1);
   }

   public boolean removeAdapter(RecyclerView.Adapter var1) {
      return this.mController.removeAdapter(var1);
   }

   public void setHasStableIds(boolean var1) {
      throw new UnsupportedOperationException("Calling setHasStableIds is not allowed on the ConcatAdapter. Use the Config object passed in the constructor to control this behavior");
   }

   public void setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy var1) {
      throw new UnsupportedOperationException("Calling setStateRestorationPolicy is not allowed on the ConcatAdapter. This value is inferred from added adapters");
   }

   public static final class Config {
      public static final Config DEFAULT;
      public final boolean isolateViewTypes;
      public final StableIdMode stableIdMode;

      static {
         DEFAULT = new Config(true, ConcatAdapter.Config.StableIdMode.NO_STABLE_IDS);
      }

      Config(boolean var1, StableIdMode var2) {
         this.isolateViewTypes = var1;
         this.stableIdMode = var2;
      }

      public static final class Builder {
         private boolean mIsolateViewTypes;
         private StableIdMode mStableIdMode;

         public Builder() {
            this.mIsolateViewTypes = ConcatAdapter.Config.DEFAULT.isolateViewTypes;
            this.mStableIdMode = ConcatAdapter.Config.DEFAULT.stableIdMode;
         }

         public Config build() {
            return new Config(this.mIsolateViewTypes, this.mStableIdMode);
         }

         public Builder setIsolateViewTypes(boolean var1) {
            this.mIsolateViewTypes = var1;
            return this;
         }

         public Builder setStableIdMode(StableIdMode var1) {
            this.mStableIdMode = var1;
            return this;
         }
      }

      public static enum StableIdMode {
         private static final StableIdMode[] $VALUES;
         ISOLATED_STABLE_IDS,
         NO_STABLE_IDS,
         SHARED_STABLE_IDS;

         static {
            StableIdMode var0 = new StableIdMode("NO_STABLE_IDS", 0);
            NO_STABLE_IDS = var0;
            StableIdMode var1 = new StableIdMode("ISOLATED_STABLE_IDS", 1);
            ISOLATED_STABLE_IDS = var1;
            StableIdMode var2 = new StableIdMode("SHARED_STABLE_IDS", 2);
            SHARED_STABLE_IDS = var2;
            $VALUES = new StableIdMode[]{var0, var1, var2};
         }
      }
   }
}
