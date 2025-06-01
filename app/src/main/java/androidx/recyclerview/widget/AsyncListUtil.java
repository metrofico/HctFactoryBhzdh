package androidx.recyclerview.widget;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public class AsyncListUtil {
   static final boolean DEBUG = false;
   static final String TAG = "AsyncListUtil";
   boolean mAllowScrollHints;
   private final ThreadUtil.BackgroundCallback mBackgroundCallback;
   final ThreadUtil.BackgroundCallback mBackgroundProxy;
   final DataCallback mDataCallback;
   int mDisplayedGeneration = 0;
   int mItemCount = 0;
   private final ThreadUtil.MainThreadCallback mMainThreadCallback;
   final ThreadUtil.MainThreadCallback mMainThreadProxy;
   final SparseIntArray mMissingPositions = new SparseIntArray();
   final int[] mPrevRange = new int[2];
   int mRequestedGeneration = 0;
   private int mScrollHint = 0;
   final Class mTClass;
   final TileList mTileList;
   final int mTileSize;
   final int[] mTmpRange = new int[2];
   final int[] mTmpRangeExtended = new int[2];
   final ViewCallback mViewCallback;

   public AsyncListUtil(Class var1, int var2, DataCallback var3, ViewCallback var4) {
      ThreadUtil.MainThreadCallback var6 = new ThreadUtil.MainThreadCallback(this) {
         final AsyncListUtil this$0;

         {
            this.this$0 = var1;
         }

         private boolean isRequestedGeneration(int var1) {
            boolean var2;
            if (var1 == this.this$0.mRequestedGeneration) {
               var2 = true;
            } else {
               var2 = false;
            }

            return var2;
         }

         private void recycleAllTiles() {
            for(int var1 = 0; var1 < this.this$0.mTileList.size(); ++var1) {
               this.this$0.mBackgroundProxy.recycleTile(this.this$0.mTileList.getAtIndex(var1));
            }

            this.this$0.mTileList.clear();
         }

         public void addTile(int var1, TileList.Tile var2) {
            if (!this.isRequestedGeneration(var1)) {
               this.this$0.mBackgroundProxy.recycleTile(var2);
            } else {
               TileList.Tile var6 = this.this$0.mTileList.addOrReplace(var2);
               if (var6 != null) {
                  Log.e("AsyncListUtil", "duplicate tile @" + var6.mStartPosition);
                  this.this$0.mBackgroundProxy.recycleTile(var6);
               }

               int var3 = var2.mStartPosition;
               int var4 = var2.mItemCount;
               var1 = 0;

               while(true) {
                  while(var1 < this.this$0.mMissingPositions.size()) {
                     int var5 = this.this$0.mMissingPositions.keyAt(var1);
                     if (var2.mStartPosition <= var5 && var5 < var3 + var4) {
                        this.this$0.mMissingPositions.removeAt(var1);
                        this.this$0.mViewCallback.onItemLoaded(var5);
                     } else {
                        ++var1;
                     }
                  }

                  return;
               }
            }
         }

         public void removeTile(int var1, int var2) {
            if (this.isRequestedGeneration(var1)) {
               TileList.Tile var3 = this.this$0.mTileList.removeAtPos(var2);
               if (var3 == null) {
                  Log.e("AsyncListUtil", "tile not found @" + var2);
               } else {
                  this.this$0.mBackgroundProxy.recycleTile(var3);
               }
            }
         }

         public void updateItemCount(int var1, int var2) {
            if (this.isRequestedGeneration(var1)) {
               this.this$0.mItemCount = var2;
               this.this$0.mViewCallback.onDataRefresh();
               AsyncListUtil var3 = this.this$0;
               var3.mDisplayedGeneration = var3.mRequestedGeneration;
               this.recycleAllTiles();
               this.this$0.mAllowScrollHints = false;
               this.this$0.updateRange();
            }
         }
      };
      this.mMainThreadCallback = var6;
      ThreadUtil.BackgroundCallback var5 = new ThreadUtil.BackgroundCallback(this) {
         private int mFirstRequiredTileStart;
         private int mGeneration;
         private int mItemCount;
         private int mLastRequiredTileStart;
         final SparseBooleanArray mLoadedTiles;
         private TileList.Tile mRecycledRoot;
         final AsyncListUtil this$0;

         {
            this.this$0 = var1;
            this.mLoadedTiles = new SparseBooleanArray();
         }

         private TileList.Tile acquireTile() {
            TileList.Tile var1 = this.mRecycledRoot;
            if (var1 != null) {
               this.mRecycledRoot = var1.mNext;
               return var1;
            } else {
               return new TileList.Tile(this.this$0.mTClass, this.this$0.mTileSize);
            }
         }

         private void addTile(TileList.Tile var1) {
            this.mLoadedTiles.put(var1.mStartPosition, true);
            this.this$0.mMainThreadProxy.addTile(this.mGeneration, var1);
         }

         private void flushTileCache(int var1) {
            int var3 = this.this$0.mDataCallback.getMaxCachedTiles();

            while(this.mLoadedTiles.size() >= var3) {
               int var2 = this.mLoadedTiles.keyAt(0);
               SparseBooleanArray var7 = this.mLoadedTiles;
               int var4 = var7.keyAt(var7.size() - 1);
               int var5 = this.mFirstRequiredTileStart - var2;
               int var6 = var4 - this.mLastRequiredTileStart;
               if (var5 > 0 && (var5 >= var6 || var1 == 2)) {
                  this.removeTile(var2);
               } else {
                  if (var6 <= 0 || var5 >= var6 && var1 != 1) {
                     break;
                  }

                  this.removeTile(var4);
               }
            }

         }

         private int getTileStart(int var1) {
            return var1 - var1 % this.this$0.mTileSize;
         }

         private boolean isTileLoaded(int var1) {
            return this.mLoadedTiles.get(var1);
         }

         private void log(String var1, Object... var2) {
            Log.d("AsyncListUtil", "[BKGR] " + String.format(var1, var2));
         }

         private void removeTile(int var1) {
            this.mLoadedTiles.delete(var1);
            this.this$0.mMainThreadProxy.removeTile(this.mGeneration, var1);
         }

         private void requestTiles(int var1, int var2, int var3, boolean var4) {
            for(int var5 = var1; var5 <= var2; var5 += this.this$0.mTileSize) {
               int var6;
               if (var4) {
                  var6 = var2 + var1 - var5;
               } else {
                  var6 = var5;
               }

               this.this$0.mBackgroundProxy.loadTile(var6, var3);
            }

         }

         public void loadTile(int var1, int var2) {
            if (!this.isTileLoaded(var1)) {
               TileList.Tile var3 = this.acquireTile();
               var3.mStartPosition = var1;
               var3.mItemCount = Math.min(this.this$0.mTileSize, this.mItemCount - var3.mStartPosition);
               this.this$0.mDataCallback.fillData(var3.mItems, var3.mStartPosition, var3.mItemCount);
               this.flushTileCache(var2);
               this.addTile(var3);
            }
         }

         public void recycleTile(TileList.Tile var1) {
            this.this$0.mDataCallback.recycleData(var1.mItems, var1.mItemCount);
            var1.mNext = this.mRecycledRoot;
            this.mRecycledRoot = var1;
         }

         public void refresh(int var1) {
            this.mGeneration = var1;
            this.mLoadedTiles.clear();
            this.mItemCount = this.this$0.mDataCallback.refreshData();
            this.this$0.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
         }

         public void updateRange(int var1, int var2, int var3, int var4, int var5) {
            if (var1 <= var2) {
               var1 = this.getTileStart(var1);
               var2 = this.getTileStart(var2);
               this.mFirstRequiredTileStart = this.getTileStart(var3);
               var3 = this.getTileStart(var4);
               this.mLastRequiredTileStart = var3;
               if (var5 == 1) {
                  this.requestTiles(this.mFirstRequiredTileStart, var2, var5, true);
                  this.requestTiles(var2 + this.this$0.mTileSize, this.mLastRequiredTileStart, var5, false);
               } else {
                  this.requestTiles(var1, var3, var5, false);
                  this.requestTiles(this.mFirstRequiredTileStart, var1 - this.this$0.mTileSize, var5, true);
               }

            }
         }
      };
      this.mBackgroundCallback = var5;
      this.mTClass = var1;
      this.mTileSize = var2;
      this.mDataCallback = var3;
      this.mViewCallback = var4;
      this.mTileList = new TileList(var2);
      MessageThreadUtil var7 = new MessageThreadUtil();
      this.mMainThreadProxy = var7.getMainThreadProxy(var6);
      this.mBackgroundProxy = var7.getBackgroundProxy(var5);
      this.refresh();
   }

   private boolean isRefreshPending() {
      boolean var1;
      if (this.mRequestedGeneration != this.mDisplayedGeneration) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public Object getItem(int var1) {
      if (var1 >= 0 && var1 < this.mItemCount) {
         Object var2 = this.mTileList.getItemAt(var1);
         if (var2 == null && !this.isRefreshPending()) {
            this.mMissingPositions.put(var1, 0);
         }

         return var2;
      } else {
         throw new IndexOutOfBoundsException(var1 + " is not within 0 and " + this.mItemCount);
      }
   }

   public int getItemCount() {
      return this.mItemCount;
   }

   void log(String var1, Object... var2) {
      Log.d("AsyncListUtil", "[MAIN] " + String.format(var1, var2));
   }

   public void onRangeChanged() {
      if (!this.isRefreshPending()) {
         this.updateRange();
         this.mAllowScrollHints = true;
      }
   }

   public void refresh() {
      this.mMissingPositions.clear();
      ThreadUtil.BackgroundCallback var2 = this.mBackgroundProxy;
      int var1 = this.mRequestedGeneration + 1;
      this.mRequestedGeneration = var1;
      var2.refresh(var1);
   }

   void updateRange() {
      this.mViewCallback.getItemRangeInto(this.mTmpRange);
      int[] var4 = this.mTmpRange;
      int var3 = var4[0];
      int var2 = var4[1];
      if (var3 <= var2 && var3 >= 0) {
         if (var2 >= this.mItemCount) {
            return;
         }

         int var1;
         int[] var5;
         if (!this.mAllowScrollHints) {
            this.mScrollHint = 0;
         } else {
            label37: {
               var5 = this.mPrevRange;
               if (var3 <= var5[1]) {
                  var1 = var5[0];
                  if (var1 <= var2) {
                     if (var3 < var1) {
                        this.mScrollHint = 1;
                     } else if (var3 > var1) {
                        this.mScrollHint = 2;
                     }
                     break label37;
                  }
               }

               this.mScrollHint = 0;
            }
         }

         var5 = this.mPrevRange;
         var5[0] = var3;
         var5[1] = var2;
         this.mViewCallback.extendRangeInto(var4, this.mTmpRangeExtended, this.mScrollHint);
         var4 = this.mTmpRangeExtended;
         var4[0] = Math.min(this.mTmpRange[0], Math.max(var4[0], 0));
         var4 = this.mTmpRangeExtended;
         var4[1] = Math.max(this.mTmpRange[1], Math.min(var4[1], this.mItemCount - 1));
         ThreadUtil.BackgroundCallback var6 = this.mBackgroundProxy;
         var5 = this.mTmpRange;
         var1 = var5[0];
         var2 = var5[1];
         var5 = this.mTmpRangeExtended;
         var6.updateRange(var1, var2, var5[0], var5[1], this.mScrollHint);
      }

   }

   public abstract static class DataCallback {
      public abstract void fillData(Object[] var1, int var2, int var3);

      public int getMaxCachedTiles() {
         return 10;
      }

      public void recycleData(Object[] var1, int var2) {
      }

      public abstract int refreshData();
   }

   public abstract static class ViewCallback {
      public static final int HINT_SCROLL_ASC = 2;
      public static final int HINT_SCROLL_DESC = 1;
      public static final int HINT_SCROLL_NONE = 0;

      public void extendRangeInto(int[] var1, int[] var2, int var3) {
         int var7 = var1[1];
         int var8 = var1[0];
         int var5 = var7 - var8 + 1;
         int var4 = var5 / 2;
         int var6;
         if (var3 == 1) {
            var6 = var5;
         } else {
            var6 = var4;
         }

         var2[0] = var8 - var6;
         if (var3 == 2) {
            var4 = var5;
         }

         var2[1] = var7 + var4;
      }

      public abstract void getItemRangeInto(int[] var1);

      public abstract void onDataRefresh();

      public abstract void onItemLoaded(int var1);
   }
}
