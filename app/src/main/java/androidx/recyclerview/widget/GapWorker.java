package androidx.recyclerview.widget;

import androidx.core.os.TraceCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class GapWorker implements Runnable {
   static final ThreadLocal sGapWorker = new ThreadLocal();
   static Comparator sTaskComparator = new Comparator() {
      public int compare(Task var1, Task var2) {
         RecyclerView var7 = var1.view;
         byte var6 = 1;
         byte var5 = 1;
         boolean var3;
         if (var7 == null) {
            var3 = true;
         } else {
            var3 = false;
         }

         boolean var4;
         if (var2.view == null) {
            var4 = true;
         } else {
            var4 = false;
         }

         byte var9;
         if (var3 != var4) {
            if (var1.view == null) {
               var9 = var5;
            } else {
               var9 = -1;
            }

            return var9;
         } else if (var1.immediate != var2.immediate) {
            var9 = var6;
            if (var1.immediate) {
               var9 = -1;
            }

            return var9;
         } else {
            int var8 = var2.viewVelocity - var1.viewVelocity;
            if (var8 != 0) {
               return var8;
            } else {
               var8 = var1.distanceToItem - var2.distanceToItem;
               return var8 != 0 ? var8 : 0;
            }
         }
      }
   };
   long mFrameIntervalNs;
   long mPostTimeNs;
   ArrayList mRecyclerViews = new ArrayList();
   private ArrayList mTasks = new ArrayList();

   private void buildTaskList() {
      int var5 = this.mRecyclerViews.size();
      int var3 = 0;

      int var1;
      int var2;
      for(var1 = 0; var3 < var5; var1 = var2) {
         RecyclerView var8 = (RecyclerView)this.mRecyclerViews.get(var3);
         var2 = var1;
         if (var8.getWindowVisibility() == 0) {
            var8.mPrefetchRegistry.collectPrefetchPositionsFromView(var8, false);
            var2 = var1 + var8.mPrefetchRegistry.mCount;
         }

         ++var3;
      }

      this.mTasks.ensureCapacity(var1);
      var2 = 0;

      int var4;
      for(var1 = 0; var2 < var5; var1 = var4) {
         RecyclerView var10 = (RecyclerView)this.mRecyclerViews.get(var2);
         if (var10.getWindowVisibility() != 0) {
            var4 = var1;
         } else {
            LayoutPrefetchRegistryImpl var9 = var10.mPrefetchRegistry;
            int var6 = Math.abs(var9.mPrefetchDx) + Math.abs(var9.mPrefetchDy);
            var3 = 0;

            while(true) {
               var4 = var1;
               if (var3 >= var9.mCount * 2) {
                  break;
               }

               Task var11;
               if (var1 >= this.mTasks.size()) {
                  var11 = new Task();
                  this.mTasks.add(var11);
               } else {
                  var11 = (Task)this.mTasks.get(var1);
               }

               var4 = var9.mPrefetchArray[var3 + 1];
               boolean var7;
               if (var4 <= var6) {
                  var7 = true;
               } else {
                  var7 = false;
               }

               var11.immediate = var7;
               var11.viewVelocity = var6;
               var11.distanceToItem = var4;
               var11.view = var10;
               var11.position = var9.mPrefetchArray[var3];
               ++var1;
               var3 += 2;
            }
         }

         ++var2;
      }

      Collections.sort(this.mTasks, sTaskComparator);
   }

   private void flushTaskWithDeadline(Task var1, long var2) {
      long var4;
      if (var1.immediate) {
         var4 = Long.MAX_VALUE;
      } else {
         var4 = var2;
      }

      RecyclerView.ViewHolder var6 = this.prefetchPositionWithDeadline(var1.view, var1.position, var4);
      if (var6 != null && var6.mNestedRecyclerView != null && var6.isBound() && !var6.isInvalid()) {
         this.prefetchInnerRecyclerViewWithDeadline((RecyclerView)var6.mNestedRecyclerView.get(), var2);
      }

   }

   private void flushTasksWithDeadline(long var1) {
      for(int var3 = 0; var3 < this.mTasks.size(); ++var3) {
         Task var4 = (Task)this.mTasks.get(var3);
         if (var4.view == null) {
            break;
         }

         this.flushTaskWithDeadline(var4, var1);
         var4.clear();
      }

   }

   static boolean isPrefetchPositionAttached(RecyclerView var0, int var1) {
      int var3 = var0.mChildHelper.getUnfilteredChildCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         RecyclerView.ViewHolder var4 = RecyclerView.getChildViewHolderInt(var0.mChildHelper.getUnfilteredChildAt(var2));
         if (var4.mPosition == var1 && !var4.isInvalid()) {
            return true;
         }
      }

      return false;
   }

   private void prefetchInnerRecyclerViewWithDeadline(RecyclerView var1, long var2) {
      if (var1 != null) {
         if (var1.mDataSetHasChangedAfterLayout && var1.mChildHelper.getUnfilteredChildCount() != 0) {
            var1.removeAndRecycleViews();
         }

         LayoutPrefetchRegistryImpl var5 = var1.mPrefetchRegistry;
         var5.collectPrefetchPositionsFromView(var1, true);
         if (var5.mCount != 0) {
            label126: {
               Throwable var10000;
               label135: {
                  boolean var10001;
                  try {
                     TraceCompat.beginSection("RV Nested Prefetch");
                     var1.mState.prepareForNestedPrefetch(var1.mAdapter);
                  } catch (Throwable var11) {
                     var10000 = var11;
                     var10001 = false;
                     break label135;
                  }

                  int var4 = 0;

                  while(true) {
                     try {
                        if (var4 >= var5.mCount * 2) {
                           break label126;
                        }

                        this.prefetchPositionWithDeadline(var1, var5.mPrefetchArray[var4], var2);
                     } catch (Throwable var10) {
                        var10000 = var10;
                        var10001 = false;
                        break;
                     }

                     var4 += 2;
                  }
               }

               Throwable var12 = var10000;
               TraceCompat.endSection();
               throw var12;
            }

            TraceCompat.endSection();
         }

      }
   }

   private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView var1, int var2, long var3) {
      if (isPrefetchPositionAttached(var1, var2)) {
         return null;
      } else {
         RecyclerView.Recycler var6 = var1.mRecycler;

         RecyclerView.ViewHolder var5;
         label155: {
            Throwable var10000;
            label161: {
               boolean var10001;
               try {
                  var1.onEnterLayoutOrScroll();
                  var5 = var6.tryGetViewHolderForPositionByDeadline(var2, false, var3);
               } catch (Throwable var18) {
                  var10000 = var18;
                  var10001 = false;
                  break label161;
               }

               if (var5 == null) {
                  break label155;
               }

               try {
                  if (var5.isBound() && !var5.isInvalid()) {
                     var6.recycleView(var5.itemView);
                     break label155;
                  }
               } catch (Throwable var17) {
                  var10000 = var17;
                  var10001 = false;
                  break label161;
               }

               label147:
               try {
                  var6.addViewHolderToRecycledViewPool(var5, false);
                  break label155;
               } catch (Throwable var16) {
                  var10000 = var16;
                  var10001 = false;
                  break label147;
               }
            }

            Throwable var19 = var10000;
            var1.onExitLayoutOrScroll(false);
            throw var19;
         }

         var1.onExitLayoutOrScroll(false);
         return var5;
      }
   }

   public void add(RecyclerView var1) {
      this.mRecyclerViews.add(var1);
   }

   void postFromTraversal(RecyclerView var1, int var2, int var3) {
      if (var1.isAttachedToWindow() && this.mPostTimeNs == 0L) {
         this.mPostTimeNs = var1.getNanoTime();
         var1.post(this);
      }

      var1.mPrefetchRegistry.setPrefetchVector(var2, var3);
   }

   void prefetch(long var1) {
      this.buildTaskList();
      this.flushTasksWithDeadline(var1);
   }

   public void remove(RecyclerView var1) {
      this.mRecyclerViews.remove(var1);
   }

   public void run() {
      label340: {
         Throwable var10000;
         label342: {
            boolean var3;
            boolean var10001;
            try {
               TraceCompat.beginSection("RV Prefetch");
               var3 = this.mRecyclerViews.isEmpty();
            } catch (Throwable var38) {
               var10000 = var38;
               var10001 = false;
               break label342;
            }

            if (var3) {
               break label340;
            }

            int var2;
            try {
               var2 = this.mRecyclerViews.size();
            } catch (Throwable var37) {
               var10000 = var37;
               var10001 = false;
               break label342;
            }

            int var1 = 0;

            long var4;
            long var6;
            for(var6 = 0L; var1 < var2; var6 = var4) {
               RecyclerView var8;
               try {
                  var8 = (RecyclerView)this.mRecyclerViews.get(var1);
               } catch (Throwable var35) {
                  var10000 = var35;
                  var10001 = false;
                  break label342;
               }

               var4 = var6;

               try {
                  if (var8.getWindowVisibility() == 0) {
                     var4 = Math.max(var8.getDrawingTime(), var6);
                  }
               } catch (Throwable var36) {
                  var10000 = var36;
                  var10001 = false;
                  break label342;
               }

               ++var1;
            }

            if (var6 == 0L) {
               break label340;
            }

            try {
               this.prefetch(TimeUnit.MILLISECONDS.toNanos(var6) + this.mFrameIntervalNs);
            } catch (Throwable var34) {
               var10000 = var34;
               var10001 = false;
               break label342;
            }

            this.mPostTimeNs = 0L;
            TraceCompat.endSection();
            return;
         }

         Throwable var39 = var10000;
         this.mPostTimeNs = 0L;
         TraceCompat.endSection();
         throw var39;
      }

      this.mPostTimeNs = 0L;
      TraceCompat.endSection();
   }

   static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {
      int mCount;
      int[] mPrefetchArray;
      int mPrefetchDx;
      int mPrefetchDy;

      public void addPosition(int var1, int var2) {
         if (var1 >= 0) {
            if (var2 >= 0) {
               int var3 = this.mCount * 2;
               int[] var4 = this.mPrefetchArray;
               if (var4 == null) {
                  var4 = new int[4];
                  this.mPrefetchArray = var4;
                  Arrays.fill(var4, -1);
               } else if (var3 >= var4.length) {
                  int[] var5 = new int[var3 * 2];
                  this.mPrefetchArray = var5;
                  System.arraycopy(var4, 0, var5, 0, var4.length);
               }

               var4 = this.mPrefetchArray;
               var4[var3] = var1;
               var4[var3 + 1] = var2;
               ++this.mCount;
            } else {
               throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
         } else {
            throw new IllegalArgumentException("Layout positions must be non-negative");
         }
      }

      void clearPrefetchPositions() {
         int[] var1 = this.mPrefetchArray;
         if (var1 != null) {
            Arrays.fill(var1, -1);
         }

         this.mCount = 0;
      }

      void collectPrefetchPositionsFromView(RecyclerView var1, boolean var2) {
         this.mCount = 0;
         int[] var3 = this.mPrefetchArray;
         if (var3 != null) {
            Arrays.fill(var3, -1);
         }

         RecyclerView.LayoutManager var4 = var1.mLayout;
         if (var1.mAdapter != null && var4 != null && var4.isItemPrefetchEnabled()) {
            if (var2) {
               if (!var1.mAdapterHelper.hasPendingUpdates()) {
                  var4.collectInitialPrefetchPositions(var1.mAdapter.getItemCount(), this);
               }
            } else if (!var1.hasPendingAdapterUpdates()) {
               var4.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, var1.mState, this);
            }

            if (this.mCount > var4.mPrefetchMaxCountObserved) {
               var4.mPrefetchMaxCountObserved = this.mCount;
               var4.mPrefetchMaxObservedInInitialPrefetch = var2;
               var1.mRecycler.updateViewCacheSize();
            }
         }

      }

      boolean lastPrefetchIncludedPosition(int var1) {
         if (this.mPrefetchArray != null) {
            int var3 = this.mCount;

            for(int var2 = 0; var2 < var3 * 2; var2 += 2) {
               if (this.mPrefetchArray[var2] == var1) {
                  return true;
               }
            }
         }

         return false;
      }

      void setPrefetchVector(int var1, int var2) {
         this.mPrefetchDx = var1;
         this.mPrefetchDy = var2;
      }
   }

   static class Task {
      public int distanceToItem;
      public boolean immediate;
      public int position;
      public RecyclerView view;
      public int viewVelocity;

      public void clear() {
         this.immediate = false;
         this.viewVelocity = 0;
         this.distanceToItem = 0;
         this.view = null;
         this.position = 0;
      }
   }
}
