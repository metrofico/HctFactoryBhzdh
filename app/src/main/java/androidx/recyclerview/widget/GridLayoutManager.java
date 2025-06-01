package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;

public class GridLayoutManager extends LinearLayoutManager {
   private static final boolean DEBUG = false;
   public static final int DEFAULT_SPAN_COUNT = -1;
   private static final String TAG = "GridLayoutManager";
   int[] mCachedBorders;
   final Rect mDecorInsets = new Rect();
   boolean mPendingSpanCountChange = false;
   final SparseIntArray mPreLayoutSpanIndexCache = new SparseIntArray();
   final SparseIntArray mPreLayoutSpanSizeCache = new SparseIntArray();
   View[] mSet;
   int mSpanCount = -1;
   SpanSizeLookup mSpanSizeLookup = new DefaultSpanSizeLookup();
   private boolean mUsingSpansToEstimateScrollBarDimensions;

   public GridLayoutManager(Context var1, int var2) {
      super(var1);
      this.setSpanCount(var2);
   }

   public GridLayoutManager(Context var1, int var2, int var3, boolean var4) {
      super(var1, var3, var4);
      this.setSpanCount(var2);
   }

   public GridLayoutManager(Context var1, AttributeSet var2, int var3, int var4) {
      super(var1, var2, var3, var4);
      this.setSpanCount(getProperties(var1, var2, var3, var4).spanCount);
   }

   private void assignSpans(RecyclerView.Recycler var1, RecyclerView.State var2, int var3, boolean var4) {
      int var7 = 0;
      int var5 = -1;
      byte var6;
      if (var4) {
         var6 = 1;
         byte var8 = 0;
         var5 = var3;
         var3 = var8;
      } else {
         --var3;
         var6 = -1;
      }

      while(var3 != var5) {
         View var10 = this.mSet[var3];
         LayoutParams var9 = (LayoutParams)var10.getLayoutParams();
         var9.mSpanSize = this.getSpanSize(var1, var2, this.getPosition(var10));
         var9.mSpanIndex = var7;
         var7 += var9.mSpanSize;
         var3 += var6;
      }

   }

   private void cachePreLayoutSpanMapping() {
      int var2 = this.getChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         LayoutParams var4 = (LayoutParams)this.getChildAt(var1).getLayoutParams();
         int var3 = var4.getViewLayoutPosition();
         this.mPreLayoutSpanSizeCache.put(var3, var4.getSpanSize());
         this.mPreLayoutSpanIndexCache.put(var3, var4.getSpanIndex());
      }

   }

   private void calculateItemBorders(int var1) {
      this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, var1);
   }

   static int[] calculateItemBorders(int[] var0, int var1, int var2) {
      int var4;
      int[] var8;
      label29: {
         var4 = 1;
         if (var0 != null && var0.length == var1 + 1) {
            var8 = var0;
            if (var0[var0.length - 1] == var2) {
               break label29;
            }
         }

         var8 = new int[var1 + 1];
      }

      int var5 = 0;
      var8[0] = 0;
      int var6 = var2 / var1;
      int var7 = var2 % var1;
      int var3 = 0;

      for(var2 = var5; var4 <= var1; ++var4) {
         var2 += var7;
         if (var2 > 0 && var1 - var2 < var7) {
            var5 = var6 + 1;
            var2 -= var1;
         } else {
            var5 = var6;
         }

         var3 += var5;
         var8[var4] = var3;
      }

      return var8;
   }

   private void clearPreLayoutSpanMappingCache() {
      this.mPreLayoutSpanSizeCache.clear();
      this.mPreLayoutSpanIndexCache.clear();
   }

   private int computeScrollOffsetWithSpanInfo(RecyclerView.State var1) {
      if (this.getChildCount() != 0 && var1.getItemCount() != 0) {
         this.ensureLayoutState();
         boolean var7 = this.isSmoothScrollbarEnabled();
         View var8 = this.findFirstVisibleChildClosestToStart(var7 ^ true, true);
         View var9 = this.findFirstVisibleChildClosestToEnd(var7 ^ true, true);
         if (var8 != null && var9 != null) {
            int var4 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(var8), this.mSpanCount);
            int var5 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(var9), this.mSpanCount);
            int var3 = Math.min(var4, var5);
            var4 = Math.max(var4, var5);
            var5 = this.mSpanSizeLookup.getCachedSpanGroupIndex(var1.getItemCount() - 1, this.mSpanCount);
            if (this.mShouldReverseLayout) {
               var3 = Math.max(0, var5 + 1 - var4 - 1);
            } else {
               var3 = Math.max(0, var3);
            }

            if (!var7) {
               return var3;
            }

            var4 = Math.abs(this.mOrientationHelper.getDecoratedEnd(var9) - this.mOrientationHelper.getDecoratedStart(var8));
            int var6 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(var8), this.mSpanCount);
            var5 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(var9), this.mSpanCount);
            float var2 = (float)var4 / (float)(var5 - var6 + 1);
            return Math.round((float)var3 * var2 + (float)(this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(var8)));
         }
      }

      return 0;
   }

   private int computeScrollRangeWithSpanInfo(RecyclerView.State var1) {
      if (this.getChildCount() != 0 && var1.getItemCount() != 0) {
         this.ensureLayoutState();
         View var8 = this.findFirstVisibleChildClosestToStart(this.isSmoothScrollbarEnabled() ^ true, true);
         View var7 = this.findFirstVisibleChildClosestToEnd(this.isSmoothScrollbarEnabled() ^ true, true);
         if (var8 != null && var7 != null) {
            if (!this.isSmoothScrollbarEnabled()) {
               return this.mSpanSizeLookup.getCachedSpanGroupIndex(var1.getItemCount() - 1, this.mSpanCount) + 1;
            }

            int var6 = this.mOrientationHelper.getDecoratedEnd(var7);
            int var3 = this.mOrientationHelper.getDecoratedStart(var8);
            int var2 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(var8), this.mSpanCount);
            int var4 = this.mSpanSizeLookup.getCachedSpanGroupIndex(this.getPosition(var7), this.mSpanCount);
            int var5 = this.mSpanSizeLookup.getCachedSpanGroupIndex(var1.getItemCount() - 1, this.mSpanCount);
            return (int)((float)(var6 - var3) / (float)(var4 - var2 + 1) * (float)(var5 + 1));
         }
      }

      return 0;
   }

   private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler var1, RecyclerView.State var2, LinearLayoutManager.AnchorInfo var3, int var4) {
      boolean var9;
      if (var4 == 1) {
         var9 = true;
      } else {
         var9 = false;
      }

      int var5 = this.getSpanIndex(var1, var2, var3.mPosition);
      if (var9) {
         while(var5 > 0 && var3.mPosition > 0) {
            --var3.mPosition;
            var5 = this.getSpanIndex(var1, var2, var3.mPosition);
         }
      } else {
         int var8 = var2.getItemCount();

         int var6;
         for(var4 = var3.mPosition; var4 < var8 - 1; var5 = var6) {
            int var7 = var4 + 1;
            var6 = this.getSpanIndex(var1, var2, var7);
            if (var6 <= var5) {
               break;
            }

            var4 = var7;
         }

         var3.mPosition = var4;
      }

   }

   private void ensureViewSet() {
      View[] var1 = this.mSet;
      if (var1 == null || var1.length != this.mSpanCount) {
         this.mSet = new View[this.mSpanCount];
      }

   }

   private int getSpanGroupIndex(RecyclerView.Recycler var1, RecyclerView.State var2, int var3) {
      if (!var2.isPreLayout()) {
         return this.mSpanSizeLookup.getCachedSpanGroupIndex(var3, this.mSpanCount);
      } else {
         int var4 = var1.convertPreLayoutPositionToPostLayout(var3);
         if (var4 == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + var3);
            return 0;
         } else {
            return this.mSpanSizeLookup.getCachedSpanGroupIndex(var4, this.mSpanCount);
         }
      }
   }

   private int getSpanIndex(RecyclerView.Recycler var1, RecyclerView.State var2, int var3) {
      if (!var2.isPreLayout()) {
         return this.mSpanSizeLookup.getCachedSpanIndex(var3, this.mSpanCount);
      } else {
         int var4 = this.mPreLayoutSpanIndexCache.get(var3, -1);
         if (var4 != -1) {
            return var4;
         } else {
            var4 = var1.convertPreLayoutPositionToPostLayout(var3);
            if (var4 == -1) {
               Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + var3);
               return 0;
            } else {
               return this.mSpanSizeLookup.getCachedSpanIndex(var4, this.mSpanCount);
            }
         }
      }
   }

   private int getSpanSize(RecyclerView.Recycler var1, RecyclerView.State var2, int var3) {
      if (!var2.isPreLayout()) {
         return this.mSpanSizeLookup.getSpanSize(var3);
      } else {
         int var4 = this.mPreLayoutSpanSizeCache.get(var3, -1);
         if (var4 != -1) {
            return var4;
         } else {
            var4 = var1.convertPreLayoutPositionToPostLayout(var3);
            if (var4 == -1) {
               Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + var3);
               return 1;
            } else {
               return this.mSpanSizeLookup.getSpanSize(var4);
            }
         }
      }
   }

   private void guessMeasurement(float var1, int var2) {
      this.calculateItemBorders(Math.max(Math.round(var1 * (float)this.mSpanCount), var2));
   }

   private void measureChild(View var1, int var2, boolean var3) {
      LayoutParams var7 = (LayoutParams)var1.getLayoutParams();
      Rect var8 = var7.mDecorInsets;
      int var5 = var8.top + var8.bottom + var7.topMargin + var7.bottomMargin;
      int var4 = var8.left + var8.right + var7.leftMargin + var7.rightMargin;
      int var6 = this.getSpaceForSpanRange(var7.mSpanIndex, var7.mSpanSize);
      if (this.mOrientation == 1) {
         var4 = getChildMeasureSpec(var6, var2, var4, var7.width, false);
         var2 = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.getHeightMode(), var5, var7.height, true);
      } else {
         var2 = getChildMeasureSpec(var6, var2, var5, var7.height, false);
         var4 = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.getWidthMode(), var4, var7.width, true);
      }

      this.measureChildWithDecorationsAndMargin(var1, var4, var2, var3);
   }

   private void measureChildWithDecorationsAndMargin(View var1, int var2, int var3, boolean var4) {
      RecyclerView.LayoutParams var5 = (RecyclerView.LayoutParams)var1.getLayoutParams();
      if (var4) {
         var4 = this.shouldReMeasureChild(var1, var2, var3, var5);
      } else {
         var4 = this.shouldMeasureChild(var1, var2, var3, var5);
      }

      if (var4) {
         var1.measure(var2, var3);
      }

   }

   private void updateMeasurements() {
      int var1;
      int var2;
      if (this.getOrientation() == 1) {
         var1 = this.getWidth() - this.getPaddingRight();
         var2 = this.getPaddingLeft();
      } else {
         var1 = this.getHeight() - this.getPaddingBottom();
         var2 = this.getPaddingTop();
      }

      this.calculateItemBorders(var1 - var2);
   }

   public boolean checkLayoutParams(RecyclerView.LayoutParams var1) {
      return var1 instanceof LayoutParams;
   }

   void collectPrefetchPositionsForLayoutState(RecyclerView.State var1, LinearLayoutManager.LayoutState var2, RecyclerView.LayoutManager.LayoutPrefetchRegistry var3) {
      int var5 = this.mSpanCount;

      for(int var4 = 0; var4 < this.mSpanCount && var2.hasMore(var1) && var5 > 0; ++var4) {
         int var6 = var2.mCurrentPosition;
         var3.addPosition(var6, Math.max(0, var2.mScrollingOffset));
         var5 -= this.mSpanSizeLookup.getSpanSize(var6);
         var2.mCurrentPosition += var2.mItemDirection;
      }

   }

   public int computeHorizontalScrollOffset(RecyclerView.State var1) {
      return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollOffsetWithSpanInfo(var1) : super.computeHorizontalScrollOffset(var1);
   }

   public int computeHorizontalScrollRange(RecyclerView.State var1) {
      return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollRangeWithSpanInfo(var1) : super.computeHorizontalScrollRange(var1);
   }

   public int computeVerticalScrollOffset(RecyclerView.State var1) {
      return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollOffsetWithSpanInfo(var1) : super.computeVerticalScrollOffset(var1);
   }

   public int computeVerticalScrollRange(RecyclerView.State var1) {
      return this.mUsingSpansToEstimateScrollBarDimensions ? this.computeScrollRangeWithSpanInfo(var1) : super.computeVerticalScrollRange(var1);
   }

   View findReferenceChild(RecyclerView.Recycler var1, RecyclerView.State var2, boolean var3, boolean var4) {
      int var5 = this.getChildCount();
      int var6 = -1;
      byte var7 = 1;
      if (var4) {
         var5 = this.getChildCount() - 1;
         var7 = -1;
      } else {
         var6 = var5;
         var5 = 0;
      }

      int var9 = var2.getItemCount();
      this.ensureLayoutState();
      int var10 = this.mOrientationHelper.getStartAfterPadding();
      int var8 = this.mOrientationHelper.getEndAfterPadding();
      View var13 = null;

      View var12;
      View var14;
      for(var12 = null; var5 != var6; var12 = var14) {
         View var16 = this.getChildAt(var5);
         int var11 = this.getPosition(var16);
         View var15 = var13;
         var14 = var12;
         if (var11 >= 0) {
            var15 = var13;
            var14 = var12;
            if (var11 < var9) {
               if (this.getSpanIndex(var1, var2, var11) != 0) {
                  var15 = var13;
                  var14 = var12;
               } else if (((RecyclerView.LayoutParams)var16.getLayoutParams()).isItemRemoved()) {
                  var15 = var13;
                  var14 = var12;
                  if (var12 == null) {
                     var14 = var16;
                     var15 = var13;
                  }
               } else {
                  if (this.mOrientationHelper.getDecoratedStart(var16) < var8 && this.mOrientationHelper.getDecoratedEnd(var16) >= var10) {
                     return var16;
                  }

                  var15 = var13;
                  var14 = var12;
                  if (var13 == null) {
                     var15 = var16;
                     var14 = var12;
                  }
               }
            }
         }

         var5 += var7;
         var13 = var15;
      }

      if (var13 == null) {
         var13 = var12;
      }

      return var13;
   }

   public RecyclerView.LayoutParams generateDefaultLayoutParams() {
      return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
   }

   public RecyclerView.LayoutParams generateLayoutParams(Context var1, AttributeSet var2) {
      return new LayoutParams(var1, var2);
   }

   public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      return var1 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams)var1) : new LayoutParams(var1);
   }

   public int getColumnCountForAccessibility(RecyclerView.Recycler var1, RecyclerView.State var2) {
      if (this.mOrientation == 1) {
         return this.mSpanCount;
      } else {
         return var2.getItemCount() < 1 ? 0 : this.getSpanGroupIndex(var1, var2, var2.getItemCount() - 1) + 1;
      }
   }

   public int getRowCountForAccessibility(RecyclerView.Recycler var1, RecyclerView.State var2) {
      if (this.mOrientation == 0) {
         return this.mSpanCount;
      } else {
         return var2.getItemCount() < 1 ? 0 : this.getSpanGroupIndex(var1, var2, var2.getItemCount() - 1) + 1;
      }
   }

   int getSpaceForSpanRange(int var1, int var2) {
      int[] var4;
      if (this.mOrientation == 1 && this.isLayoutRTL()) {
         var4 = this.mCachedBorders;
         int var3 = this.mSpanCount;
         return var4[var3 - var1] - var4[var3 - var1 - var2];
      } else {
         var4 = this.mCachedBorders;
         return var4[var2 + var1] - var4[var1];
      }
   }

   public int getSpanCount() {
      return this.mSpanCount;
   }

   public SpanSizeLookup getSpanSizeLookup() {
      return this.mSpanSizeLookup;
   }

   public boolean isUsingSpansToEstimateScrollbarDimensions() {
      return this.mUsingSpansToEstimateScrollBarDimensions;
   }

   void layoutChunk(RecyclerView.Recycler var1, RecyclerView.State var2, LinearLayoutManager.LayoutState var3, LinearLayoutManager.LayoutChunkResult var4) {
      int var15 = this.mOrientationHelper.getModeInOther();
      boolean var10;
      if (var15 != 1073741824) {
         var10 = true;
      } else {
         var10 = false;
      }

      int var11;
      if (this.getChildCount() > 0) {
         var11 = this.mCachedBorders[this.mSpanCount];
      } else {
         var11 = 0;
      }

      if (var10) {
         this.updateMeasurements();
      }

      boolean var16;
      if (var3.mItemDirection == 1) {
         var16 = true;
      } else {
         var16 = false;
      }

      int var8 = this.mSpanCount;
      if (!var16) {
         var8 = this.getSpanIndex(var1, var2, var3.mCurrentPosition) + this.getSpanSize(var1, var2, var3.mCurrentPosition);
      }

      int var9;
      int var12;
      int var13;
      View var17;
      for(var13 = 0; var13 < this.mSpanCount && var3.hasMore(var2) && var8 > 0; ++var13) {
         var12 = var3.mCurrentPosition;
         var9 = this.getSpanSize(var1, var2, var12);
         if (var9 > this.mSpanCount) {
            throw new IllegalArgumentException("Item at position " + var12 + " requires " + var9 + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
         }

         var8 -= var9;
         if (var8 < 0) {
            break;
         }

         var17 = var3.next(var1);
         if (var17 == null) {
            break;
         }

         this.mSet[var13] = var17;
      }

      if (var13 == 0) {
         var4.mFinished = true;
      } else {
         float var6 = 0.0F;
         this.assignSpans(var1, var2, var13, var16);
         var12 = 0;

         float var5;
         int var14;
         View var18;
         LayoutParams var20;
         for(var8 = 0; var12 < var13; var6 = var5) {
            var18 = this.mSet[var12];
            if (var3.mScrapList == null) {
               if (var16) {
                  this.addView(var18);
               } else {
                  this.addView(var18, 0);
               }
            } else if (var16) {
               this.addDisappearingView(var18);
            } else {
               this.addDisappearingView(var18, 0);
            }

            this.calculateItemDecorationsForChild(var18, this.mDecorInsets);
            this.measureChild(var18, var15, false);
            var14 = this.mOrientationHelper.getDecoratedMeasurement(var18);
            var9 = var8;
            if (var14 > var8) {
               var9 = var14;
            }

            var20 = (LayoutParams)var18.getLayoutParams();
            float var7 = (float)this.mOrientationHelper.getDecoratedMeasurementInOther(var18) * 1.0F / (float)var20.mSpanSize;
            var5 = var6;
            if (var7 > var6) {
               var5 = var7;
            }

            ++var12;
            var8 = var9;
         }

         var9 = var8;
         int var21;
         if (var10) {
            this.guessMeasurement(var6, var11);
            var21 = 0;
            var8 = 0;

            while(true) {
               var9 = var8;
               if (var21 >= var13) {
                  break;
               }

               var18 = this.mSet[var21];
               this.measureChild(var18, 1073741824, true);
               var11 = this.mOrientationHelper.getDecoratedMeasurement(var18);
               var9 = var8;
               if (var11 > var8) {
                  var9 = var11;
               }

               ++var21;
               var8 = var9;
            }
         }

         for(var8 = 0; var8 < var13; ++var8) {
            var17 = this.mSet[var8];
            if (this.mOrientationHelper.getDecoratedMeasurement(var17) != var9) {
               var20 = (LayoutParams)var17.getLayoutParams();
               Rect var19 = var20.mDecorInsets;
               var21 = var19.top + var19.bottom + var20.topMargin + var20.bottomMargin;
               var11 = var19.left + var19.right + var20.leftMargin + var20.rightMargin;
               var12 = this.getSpaceForSpanRange(var20.mSpanIndex, var20.mSpanSize);
               if (this.mOrientation == 1) {
                  var11 = getChildMeasureSpec(var12, 1073741824, var11, var20.width, false);
                  var21 = MeasureSpec.makeMeasureSpec(var9 - var21, 1073741824);
               } else {
                  var11 = MeasureSpec.makeMeasureSpec(var9 - var11, 1073741824);
                  var21 = getChildMeasureSpec(var12, 1073741824, var21, var20.height, false);
               }

               this.measureChildWithDecorationsAndMargin(var17, var11, var21, true);
            }
         }

         var14 = 0;
         var4.mConsumed = var9;
         if (this.mOrientation == 1) {
            if (var3.mLayoutDirection == -1) {
               var8 = var3.mOffset;
               var9 = var8 - var9;
            } else {
               var21 = var3.mOffset;
               var8 = var21;
               var21 += var9;
               var9 = var8;
               var8 = var21;
            }

            var21 = 0;
            var11 = 0;
         } else if (var3.mLayoutDirection == -1) {
            var21 = var3.mOffset;
            var11 = var21 - var9;
            var9 = 0;
            var8 = 0;
         } else {
            var11 = var3.mOffset;
            var21 = var9 + var11;
            var8 = 0;
            var9 = 0;
         }

         while(var14 < var13) {
            var18 = this.mSet[var14];
            var20 = (LayoutParams)var18.getLayoutParams();
            if (this.mOrientation == 1) {
               if (this.isLayoutRTL()) {
                  var21 = this.getPaddingLeft() + this.mCachedBorders[this.mSpanCount - var20.mSpanIndex];
                  var12 = this.mOrientationHelper.getDecoratedMeasurementInOther(var18);
                  var11 = var21;
                  var21 -= var12;
               } else {
                  var11 = this.getPaddingLeft() + this.mCachedBorders[var20.mSpanIndex];
                  var12 = this.mOrientationHelper.getDecoratedMeasurementInOther(var18);
                  var21 = var11;
                  var11 += var12;
               }

               var12 = var8;
               var8 = var9;
               var9 = var21;
            } else {
               var12 = this.getPaddingTop() + this.mCachedBorders[var20.mSpanIndex];
               var15 = this.mOrientationHelper.getDecoratedMeasurementInOther(var18);
               var8 = var12;
               var9 = var11;
               var12 += var15;
               var11 = var21;
            }

            this.layoutDecoratedWithMargins(var18, var9, var8, var11, var12);
            if (var20.isItemRemoved() || var20.isItemChanged()) {
               var4.mIgnoreConsumed = true;
            }

            var4.mFocusable |= var18.hasFocusable();
            ++var14;
            var21 = var11;
            var11 = var9;
            var9 = var8;
            var8 = var12;
         }

         Arrays.fill(this.mSet, (Object)null);
      }
   }

   void onAnchorReady(RecyclerView.Recycler var1, RecyclerView.State var2, LinearLayoutManager.AnchorInfo var3, int var4) {
      super.onAnchorReady(var1, var2, var3, var4);
      this.updateMeasurements();
      if (var2.getItemCount() > 0 && !var2.isPreLayout()) {
         this.ensureAnchorIsInCorrectSpan(var1, var2, var3, var4);
      }

      this.ensureViewSet();
   }

   public View onFocusSearchFailed(View var1, int var2, RecyclerView.Recycler var3, RecyclerView.State var4) {
      View var22 = this.findContainingItemView(var1);
      View var21 = null;
      if (var22 == null) {
         return null;
      } else {
         LayoutParams var23 = (LayoutParams)var22.getLayoutParams();
         int var14 = var23.mSpanIndex;
         int var15 = var23.mSpanIndex + var23.mSpanSize;
         if (super.onFocusSearchFailed(var1, var2, var3, var4) == null) {
            return null;
         } else {
            boolean var20;
            if (this.convertFocusDirectionToLayoutDirection(var2) == 1) {
               var20 = true;
            } else {
               var20 = false;
            }

            boolean var25;
            if (var20 != this.mShouldReverseLayout) {
               var25 = true;
            } else {
               var25 = false;
            }

            int var6;
            byte var8;
            if (var25) {
               var2 = this.getChildCount() - 1;
               var6 = -1;
               var8 = -1;
            } else {
               var6 = this.getChildCount();
               var8 = 1;
               var2 = 0;
            }

            boolean var9;
            if (this.mOrientation == 1 && this.isLayoutRTL()) {
               var9 = true;
            } else {
               var9 = false;
            }

            int var16 = this.getSpanGroupIndex(var3, var4, var2);
            int var12 = -1;
            int var10 = -1;
            int var7 = 0;
            int var5 = 0;
            int var11 = var2;
            var1 = null;
            var2 = var10;
            var10 = var6;

            for(var6 = var7; var11 != var10; var11 += var8) {
               var7 = this.getSpanGroupIndex(var3, var4, var11);
               View var27 = this.getChildAt(var11);
               if (var27 == var22) {
                  break;
               }

               if (var27.hasFocusable() && var7 != var16) {
                  if (var21 != null) {
                     break;
                  }
               } else {
                  LayoutParams var24 = (LayoutParams)var27.getLayoutParams();
                  int var18 = var24.mSpanIndex;
                  int var17 = var24.mSpanIndex + var24.mSpanSize;
                  if (var27.hasFocusable() && var18 == var14 && var17 == var15) {
                     return var27;
                  }

                  boolean var26;
                  label142: {
                     if ((!var27.hasFocusable() || var21 != null) && (var27.hasFocusable() || var1 != null)) {
                        label139: {
                           var7 = Math.max(var18, var14);
                           int var19 = Math.min(var17, var15) - var7;
                           if (var27.hasFocusable()) {
                              if (var19 > var6) {
                                 break label139;
                              }

                              if (var19 == var6) {
                                 if (var18 > var12) {
                                    var26 = true;
                                 } else {
                                    var26 = false;
                                 }

                                 if (var9 == var26) {
                                    break label139;
                                 }
                              }
                           } else if (var21 == null) {
                              boolean var13 = true;
                              var26 = true;
                              if (this.isViewPartiallyVisible(var27, false, true)) {
                                 if (var19 > var5) {
                                    var26 = var13;
                                    break label142;
                                 }

                                 if (var19 == var5) {
                                    if (var18 <= var2) {
                                       var26 = false;
                                    }

                                    if (var9 == var26) {
                                       break label139;
                                    }
                                 }
                              }
                           }

                           var26 = false;
                           break label142;
                        }
                     }

                     var26 = true;
                  }

                  if (var26) {
                     if (var27.hasFocusable()) {
                        var12 = var24.mSpanIndex;
                        var6 = Math.min(var17, var15) - Math.max(var18, var14);
                        var21 = var27;
                     } else {
                        var2 = var24.mSpanIndex;
                        var5 = Math.min(var17, var15) - Math.max(var18, var14);
                        var1 = var27;
                     }
                  }
               }
            }

            if (var21 != null) {
               var1 = var21;
            }

            return var1;
         }
      }
   }

   public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler var1, RecyclerView.State var2, View var3, AccessibilityNodeInfoCompat var4) {
      ViewGroup.LayoutParams var6 = var3.getLayoutParams();
      if (!(var6 instanceof LayoutParams)) {
         super.onInitializeAccessibilityNodeInfoForItem(var3, var4);
      } else {
         LayoutParams var7 = (LayoutParams)var6;
         int var5 = this.getSpanGroupIndex(var1, var2, var7.getViewLayoutPosition());
         if (this.mOrientation == 0) {
            var4.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(var7.getSpanIndex(), var7.getSpanSize(), var5, 1, false, false));
         } else {
            var4.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(var5, 1, var7.getSpanIndex(), var7.getSpanSize(), false, false));
         }

      }
   }

   public void onItemsAdded(RecyclerView var1, int var2, int var3) {
      this.mSpanSizeLookup.invalidateSpanIndexCache();
      this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
   }

   public void onItemsChanged(RecyclerView var1) {
      this.mSpanSizeLookup.invalidateSpanIndexCache();
      this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
   }

   public void onItemsMoved(RecyclerView var1, int var2, int var3, int var4) {
      this.mSpanSizeLookup.invalidateSpanIndexCache();
      this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
   }

   public void onItemsRemoved(RecyclerView var1, int var2, int var3) {
      this.mSpanSizeLookup.invalidateSpanIndexCache();
      this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
   }

   public void onItemsUpdated(RecyclerView var1, int var2, int var3, Object var4) {
      this.mSpanSizeLookup.invalidateSpanIndexCache();
      this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
   }

   public void onLayoutChildren(RecyclerView.Recycler var1, RecyclerView.State var2) {
      if (var2.isPreLayout()) {
         this.cachePreLayoutSpanMapping();
      }

      super.onLayoutChildren(var1, var2);
      this.clearPreLayoutSpanMappingCache();
   }

   public void onLayoutCompleted(RecyclerView.State var1) {
      super.onLayoutCompleted(var1);
      this.mPendingSpanCountChange = false;
   }

   public int scrollHorizontallyBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      this.updateMeasurements();
      this.ensureViewSet();
      return super.scrollHorizontallyBy(var1, var2, var3);
   }

   public int scrollVerticallyBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      this.updateMeasurements();
      this.ensureViewSet();
      return super.scrollVerticallyBy(var1, var2, var3);
   }

   public void setMeasuredDimension(Rect var1, int var2, int var3) {
      if (this.mCachedBorders == null) {
         super.setMeasuredDimension(var1, var2, var3);
      }

      int var5 = this.getPaddingLeft() + this.getPaddingRight();
      int var4 = this.getPaddingTop() + this.getPaddingBottom();
      int[] var6;
      if (this.mOrientation == 1) {
         var3 = chooseSize(var3, var1.height() + var4, this.getMinimumHeight());
         var6 = this.mCachedBorders;
         var2 = chooseSize(var2, var6[var6.length - 1] + var5, this.getMinimumWidth());
      } else {
         var2 = chooseSize(var2, var1.width() + var5, this.getMinimumWidth());
         var6 = this.mCachedBorders;
         var3 = chooseSize(var3, var6[var6.length - 1] + var4, this.getMinimumHeight());
      }

      this.setMeasuredDimension(var2, var3);
   }

   public void setSpanCount(int var1) {
      if (var1 != this.mSpanCount) {
         this.mPendingSpanCountChange = true;
         if (var1 >= 1) {
            this.mSpanCount = var1;
            this.mSpanSizeLookup.invalidateSpanIndexCache();
            this.requestLayout();
         } else {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + var1);
         }
      }
   }

   public void setSpanSizeLookup(SpanSizeLookup var1) {
      this.mSpanSizeLookup = var1;
   }

   public void setStackFromEnd(boolean var1) {
      if (!var1) {
         super.setStackFromEnd(false);
      } else {
         throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
      }
   }

   public void setUsingSpansToEstimateScrollbarDimensions(boolean var1) {
      this.mUsingSpansToEstimateScrollBarDimensions = var1;
   }

   public boolean supportsPredictiveItemAnimations() {
      boolean var1;
      if (this.mPendingSavedState == null && !this.mPendingSpanCountChange) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public static final class DefaultSpanSizeLookup extends SpanSizeLookup {
      public int getSpanIndex(int var1, int var2) {
         return var1 % var2;
      }

      public int getSpanSize(int var1) {
         return 1;
      }
   }

   public static class LayoutParams extends RecyclerView.LayoutParams {
      public static final int INVALID_SPAN_ID = -1;
      int mSpanIndex = -1;
      int mSpanSize = 0;

      public LayoutParams(int var1, int var2) {
         super(var1, var2);
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
      }

      public LayoutParams(ViewGroup.LayoutParams var1) {
         super(var1);
      }

      public LayoutParams(ViewGroup.MarginLayoutParams var1) {
         super(var1);
      }

      public LayoutParams(RecyclerView.LayoutParams var1) {
         super(var1);
      }

      public int getSpanIndex() {
         return this.mSpanIndex;
      }

      public int getSpanSize() {
         return this.mSpanSize;
      }
   }

   public abstract static class SpanSizeLookup {
      private boolean mCacheSpanGroupIndices = false;
      private boolean mCacheSpanIndices = false;
      final SparseIntArray mSpanGroupIndexCache = new SparseIntArray();
      final SparseIntArray mSpanIndexCache = new SparseIntArray();

      static int findFirstKeyLessThan(SparseIntArray var0, int var1) {
         int var3 = var0.size() - 1;
         int var2 = 0;

         while(var2 <= var3) {
            int var4 = var2 + var3 >>> 1;
            if (var0.keyAt(var4) < var1) {
               var2 = var4 + 1;
            } else {
               var3 = var4 - 1;
            }
         }

         var1 = var2 - 1;
         if (var1 >= 0 && var1 < var0.size()) {
            return var0.keyAt(var1);
         } else {
            return -1;
         }
      }

      int getCachedSpanGroupIndex(int var1, int var2) {
         if (!this.mCacheSpanGroupIndices) {
            return this.getSpanGroupIndex(var1, var2);
         } else {
            int var3 = this.mSpanGroupIndexCache.get(var1, -1);
            if (var3 != -1) {
               return var3;
            } else {
               var2 = this.getSpanGroupIndex(var1, var2);
               this.mSpanGroupIndexCache.put(var1, var2);
               return var2;
            }
         }
      }

      int getCachedSpanIndex(int var1, int var2) {
         if (!this.mCacheSpanIndices) {
            return this.getSpanIndex(var1, var2);
         } else {
            int var3 = this.mSpanIndexCache.get(var1, -1);
            if (var3 != -1) {
               return var3;
            } else {
               var2 = this.getSpanIndex(var1, var2);
               this.mSpanIndexCache.put(var1, var2);
               return var2;
            }
         }
      }

      public int getSpanGroupIndex(int var1, int var2) {
         int var3;
         int var4;
         int var5;
         int var6;
         int var7;
         int var8;
         label32: {
            if (this.mCacheSpanGroupIndices) {
               var3 = findFirstKeyLessThan(this.mSpanGroupIndexCache, var1);
               if (var3 != -1) {
                  var7 = this.mSpanGroupIndexCache.get(var3);
                  var6 = var3 + 1;
                  var8 = this.getCachedSpanIndex(var3, var2) + this.getSpanSize(var3);
                  var3 = var7;
                  var5 = var6;
                  var4 = var8;
                  if (var8 == var2) {
                     var3 = var7 + 1;
                     var4 = 0;
                     var5 = var6;
                  }
                  break label32;
               }
            }

            var3 = 0;
            var5 = 0;
            var4 = var5;
         }

         int var9 = this.getSpanSize(var1);
         var7 = var4;
         var6 = var5;

         for(var4 = var3; var6 < var1; var7 = var3) {
            var8 = this.getSpanSize(var6);
            var7 += var8;
            if (var7 == var2) {
               var5 = var4 + 1;
               var3 = 0;
            } else {
               var5 = var4;
               var3 = var7;
               if (var7 > var2) {
                  var5 = var4 + 1;
                  var3 = var8;
               }
            }

            ++var6;
            var4 = var5;
         }

         var1 = var4;
         if (var7 + var9 > var2) {
            var1 = var4 + 1;
         }

         return var1;
      }

      public int getSpanIndex(int var1, int var2) {
         int var8 = this.getSpanSize(var1);
         if (var8 == var2) {
            return 0;
         } else {
            int var3;
            int var4;
            int var5;
            label35: {
               if (this.mCacheSpanIndices) {
                  var5 = findFirstKeyLessThan(this.mSpanIndexCache, var1);
                  if (var5 >= 0) {
                     var3 = this.mSpanIndexCache.get(var5) + this.getSpanSize(var5);
                     var4 = var5 + 1;
                     break label35;
                  }
               }

               var4 = 0;
            }

            for(var3 = 0; var4 < var1; var4 = var5 + 1) {
               int var6 = this.getSpanSize(var4);
               int var7 = var3 + var6;
               if (var7 == var2) {
                  var3 = 0;
                  var5 = var4;
               } else {
                  var5 = var4;
                  var3 = var7;
                  if (var7 > var2) {
                     var3 = var6;
                     var5 = var4;
                  }
               }
            }

            if (var8 + var3 <= var2) {
               return var3;
            } else {
               return 0;
            }
         }
      }

      public abstract int getSpanSize(int var1);

      public void invalidateSpanGroupIndexCache() {
         this.mSpanGroupIndexCache.clear();
      }

      public void invalidateSpanIndexCache() {
         this.mSpanIndexCache.clear();
      }

      public boolean isSpanGroupIndexCacheEnabled() {
         return this.mCacheSpanGroupIndices;
      }

      public boolean isSpanIndexCacheEnabled() {
         return this.mCacheSpanIndices;
      }

      public void setSpanGroupIndexCacheEnabled(boolean var1) {
         if (!var1) {
            this.mSpanGroupIndexCache.clear();
         }

         this.mCacheSpanGroupIndices = var1;
      }

      public void setSpanIndexCacheEnabled(boolean var1) {
         if (!var1) {
            this.mSpanGroupIndexCache.clear();
         }

         this.mCacheSpanIndices = var1;
      }
   }
}
