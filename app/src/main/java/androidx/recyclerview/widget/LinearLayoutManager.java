package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

public class LinearLayoutManager extends RecyclerView.LayoutManager implements ItemTouchHelper.ViewDropHandler, RecyclerView.SmoothScroller.ScrollVectorProvider {
   static final boolean DEBUG = false;
   public static final int HORIZONTAL = 0;
   public static final int INVALID_OFFSET = Integer.MIN_VALUE;
   private static final float MAX_SCROLL_FACTOR = 0.33333334F;
   private static final String TAG = "LinearLayoutManager";
   public static final int VERTICAL = 1;
   final AnchorInfo mAnchorInfo;
   private int mInitialPrefetchItemCount;
   private boolean mLastStackFromEnd;
   private final LayoutChunkResult mLayoutChunkResult;
   private LayoutState mLayoutState;
   int mOrientation;
   OrientationHelper mOrientationHelper;
   SavedState mPendingSavedState;
   int mPendingScrollPosition;
   int mPendingScrollPositionOffset;
   private boolean mRecycleChildrenOnDetach;
   private int[] mReusableIntPair;
   private boolean mReverseLayout;
   boolean mShouldReverseLayout;
   private boolean mSmoothScrollbarEnabled;
   private boolean mStackFromEnd;

   public LinearLayoutManager(Context var1) {
      this(var1, 1, false);
   }

   public LinearLayoutManager(Context var1, int var2, boolean var3) {
      this.mOrientation = 1;
      this.mReverseLayout = false;
      this.mShouldReverseLayout = false;
      this.mStackFromEnd = false;
      this.mSmoothScrollbarEnabled = true;
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.mPendingSavedState = null;
      this.mAnchorInfo = new AnchorInfo();
      this.mLayoutChunkResult = new LayoutChunkResult();
      this.mInitialPrefetchItemCount = 2;
      this.mReusableIntPair = new int[2];
      this.setOrientation(var2);
      this.setReverseLayout(var3);
   }

   public LinearLayoutManager(Context var1, AttributeSet var2, int var3, int var4) {
      this.mOrientation = 1;
      this.mReverseLayout = false;
      this.mShouldReverseLayout = false;
      this.mStackFromEnd = false;
      this.mSmoothScrollbarEnabled = true;
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.mPendingSavedState = null;
      this.mAnchorInfo = new AnchorInfo();
      this.mLayoutChunkResult = new LayoutChunkResult();
      this.mInitialPrefetchItemCount = 2;
      this.mReusableIntPair = new int[2];
      RecyclerView.LayoutManager.Properties var5 = getProperties(var1, var2, var3, var4);
      this.setOrientation(var5.orientation);
      this.setReverseLayout(var5.reverseLayout);
      this.setStackFromEnd(var5.stackFromEnd);
   }

   private int computeScrollExtent(RecyclerView.State var1) {
      if (this.getChildCount() == 0) {
         return 0;
      } else {
         this.ensureLayoutState();
         return ScrollbarHelper.computeScrollExtent(var1, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(this.mSmoothScrollbarEnabled ^ true, true), this.findFirstVisibleChildClosestToEnd(this.mSmoothScrollbarEnabled ^ true, true), this, this.mSmoothScrollbarEnabled);
      }
   }

   private int computeScrollOffset(RecyclerView.State var1) {
      if (this.getChildCount() == 0) {
         return 0;
      } else {
         this.ensureLayoutState();
         return ScrollbarHelper.computeScrollOffset(var1, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(this.mSmoothScrollbarEnabled ^ true, true), this.findFirstVisibleChildClosestToEnd(this.mSmoothScrollbarEnabled ^ true, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
      }
   }

   private int computeScrollRange(RecyclerView.State var1) {
      if (this.getChildCount() == 0) {
         return 0;
      } else {
         this.ensureLayoutState();
         return ScrollbarHelper.computeScrollRange(var1, this.mOrientationHelper, this.findFirstVisibleChildClosestToStart(this.mSmoothScrollbarEnabled ^ true, true), this.findFirstVisibleChildClosestToEnd(this.mSmoothScrollbarEnabled ^ true, true), this, this.mSmoothScrollbarEnabled);
      }
   }

   private View findFirstPartiallyOrCompletelyInvisibleChild() {
      return this.findOnePartiallyOrCompletelyInvisibleChild(0, this.getChildCount());
   }

   private View findLastPartiallyOrCompletelyInvisibleChild() {
      return this.findOnePartiallyOrCompletelyInvisibleChild(this.getChildCount() - 1, -1);
   }

   private View findPartiallyOrCompletelyInvisibleChildClosestToEnd() {
      View var1;
      if (this.mShouldReverseLayout) {
         var1 = this.findFirstPartiallyOrCompletelyInvisibleChild();
      } else {
         var1 = this.findLastPartiallyOrCompletelyInvisibleChild();
      }

      return var1;
   }

   private View findPartiallyOrCompletelyInvisibleChildClosestToStart() {
      View var1;
      if (this.mShouldReverseLayout) {
         var1 = this.findLastPartiallyOrCompletelyInvisibleChild();
      } else {
         var1 = this.findFirstPartiallyOrCompletelyInvisibleChild();
      }

      return var1;
   }

   private int fixLayoutEndGap(int var1, RecyclerView.Recycler var2, RecyclerView.State var3, boolean var4) {
      int var5 = this.mOrientationHelper.getEndAfterPadding() - var1;
      if (var5 > 0) {
         var5 = -this.scrollBy(-var5, var2, var3);
         if (var4) {
            var1 = this.mOrientationHelper.getEndAfterPadding() - (var1 + var5);
            if (var1 > 0) {
               this.mOrientationHelper.offsetChildren(var1);
               return var1 + var5;
            }
         }

         return var5;
      } else {
         return 0;
      }
   }

   private int fixLayoutStartGap(int var1, RecyclerView.Recycler var2, RecyclerView.State var3, boolean var4) {
      int var5 = var1 - this.mOrientationHelper.getStartAfterPadding();
      if (var5 > 0) {
         int var6 = -this.scrollBy(var5, var2, var3);
         var5 = var6;
         if (var4) {
            var1 = var1 + var6 - this.mOrientationHelper.getStartAfterPadding();
            var5 = var6;
            if (var1 > 0) {
               this.mOrientationHelper.offsetChildren(-var1);
               var5 = var6 - var1;
            }
         }

         return var5;
      } else {
         return 0;
      }
   }

   private View getChildClosestToEnd() {
      int var1;
      if (this.mShouldReverseLayout) {
         var1 = 0;
      } else {
         var1 = this.getChildCount() - 1;
      }

      return this.getChildAt(var1);
   }

   private View getChildClosestToStart() {
      int var1;
      if (this.mShouldReverseLayout) {
         var1 = this.getChildCount() - 1;
      } else {
         var1 = 0;
      }

      return this.getChildAt(var1);
   }

   private void layoutForPredictiveAnimations(RecyclerView.Recycler var1, RecyclerView.State var2, int var3, int var4) {
      if (var2.willRunPredictiveAnimations() && this.getChildCount() != 0 && !var2.isPreLayout() && this.supportsPredictiveItemAnimations()) {
         List var14 = var1.getScrapList();
         int var10 = var14.size();
         int var9 = this.getPosition(this.getChildAt(0));
         int var6 = 0;
         int var7 = 0;

         int var5;
         for(var5 = var7; var6 < var10; ++var6) {
            RecyclerView.ViewHolder var13 = (RecyclerView.ViewHolder)var14.get(var6);
            if (!var13.isRemoved()) {
               int var11 = var13.getLayoutPosition();
               byte var8 = 1;
               boolean var12;
               if (var11 < var9) {
                  var12 = true;
               } else {
                  var12 = false;
               }

               if (var12 != this.mShouldReverseLayout) {
                  var8 = -1;
               }

               if (var8 == -1) {
                  var7 += this.mOrientationHelper.getDecoratedMeasurement(var13.itemView);
               } else {
                  var5 += this.mOrientationHelper.getDecoratedMeasurement(var13.itemView);
               }
            }
         }

         this.mLayoutState.mScrapList = var14;
         if (var7 > 0) {
            this.updateLayoutStateToFillStart(this.getPosition(this.getChildClosestToStart()), var3);
            this.mLayoutState.mExtraFillSpace = var7;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.assignPositionFromScrapList();
            this.fill(var1, this.mLayoutState, var2, false);
         }

         if (var5 > 0) {
            this.updateLayoutStateToFillEnd(this.getPosition(this.getChildClosestToEnd()), var4);
            this.mLayoutState.mExtraFillSpace = var5;
            this.mLayoutState.mAvailable = 0;
            this.mLayoutState.assignPositionFromScrapList();
            this.fill(var1, this.mLayoutState, var2, false);
         }

         this.mLayoutState.mScrapList = null;
      }

   }

   private void logChildren() {
      Log.d("LinearLayoutManager", "internal representation of views on the screen");

      for(int var1 = 0; var1 < this.getChildCount(); ++var1) {
         View var2 = this.getChildAt(var1);
         Log.d("LinearLayoutManager", "item " + this.getPosition(var2) + ", coord:" + this.mOrientationHelper.getDecoratedStart(var2));
      }

      Log.d("LinearLayoutManager", "==============");
   }

   private void recycleByLayoutState(RecyclerView.Recycler var1, LayoutState var2) {
      if (var2.mRecycle && !var2.mInfinite) {
         int var4 = var2.mScrollingOffset;
         int var3 = var2.mNoRecycleSpace;
         if (var2.mLayoutDirection == -1) {
            this.recycleViewsFromEnd(var1, var4, var3);
         } else {
            this.recycleViewsFromStart(var1, var4, var3);
         }
      }

   }

   private void recycleChildren(RecyclerView.Recycler var1, int var2, int var3) {
      if (var2 != var3) {
         int var4 = var2;
         if (var3 > var2) {
            --var3;

            while(var3 >= var2) {
               this.removeAndRecycleViewAt(var3, var1);
               --var3;
            }
         } else {
            while(var4 > var3) {
               this.removeAndRecycleViewAt(var4, var1);
               --var4;
            }
         }

      }
   }

   private void recycleViewsFromEnd(RecyclerView.Recycler var1, int var2, int var3) {
      int var5 = this.getChildCount();
      if (var2 >= 0) {
         int var4 = this.mOrientationHelper.getEnd() - var2 + var3;
         View var6;
         if (!this.mShouldReverseLayout) {
            var3 = var5 - 1;

            for(var2 = var3; var2 >= 0; --var2) {
               var6 = this.getChildAt(var2);
               if (this.mOrientationHelper.getDecoratedStart(var6) < var4 || this.mOrientationHelper.getTransformedStartWithDecoration(var6) < var4) {
                  this.recycleChildren(var1, var3, var2);
                  break;
               }
            }
         } else {
            for(var2 = 0; var2 < var5; ++var2) {
               var6 = this.getChildAt(var2);
               if (this.mOrientationHelper.getDecoratedStart(var6) < var4 || this.mOrientationHelper.getTransformedStartWithDecoration(var6) < var4) {
                  this.recycleChildren(var1, 0, var2);
                  return;
               }
            }
         }

      }
   }

   private void recycleViewsFromStart(RecyclerView.Recycler var1, int var2, int var3) {
      if (var2 >= 0) {
         int var4 = var2 - var3;
         var3 = this.getChildCount();
         View var5;
         if (!this.mShouldReverseLayout) {
            for(var2 = 0; var2 < var3; ++var2) {
               var5 = this.getChildAt(var2);
               if (this.mOrientationHelper.getDecoratedEnd(var5) > var4 || this.mOrientationHelper.getTransformedEndWithDecoration(var5) > var4) {
                  this.recycleChildren(var1, 0, var2);
                  break;
               }
            }
         } else {
            --var3;

            for(var2 = var3; var2 >= 0; --var2) {
               var5 = this.getChildAt(var2);
               if (this.mOrientationHelper.getDecoratedEnd(var5) > var4 || this.mOrientationHelper.getTransformedEndWithDecoration(var5) > var4) {
                  this.recycleChildren(var1, var3, var2);
                  return;
               }
            }
         }

      }
   }

   private void resolveShouldLayoutReverse() {
      if (this.mOrientation != 1 && this.isLayoutRTL()) {
         this.mShouldReverseLayout = this.mReverseLayout ^ true;
      } else {
         this.mShouldReverseLayout = this.mReverseLayout;
      }

   }

   private boolean updateAnchorFromChildren(RecyclerView.Recycler var1, RecyclerView.State var2, AnchorInfo var3) {
      int var4 = this.getChildCount();
      boolean var7 = false;
      if (var4 == 0) {
         return false;
      } else {
         View var11 = this.getFocusedChild();
         if (var11 != null && var3.isViewValidAsAnchor(var11, var2)) {
            var3.assignFromViewAndKeepVisibleRect(var11, this.getPosition(var11));
            return true;
         } else if (this.mLastStackFromEnd != this.mStackFromEnd) {
            return false;
         } else {
            View var12 = this.findReferenceChild(var1, var2, var3.mLayoutFromEnd, this.mStackFromEnd);
            if (var12 == null) {
               return false;
            } else {
               var3.assignFromView(var12, this.getPosition(var12));
               if (!var2.isPreLayout() && this.supportsPredictiveItemAnimations()) {
                  int var9 = this.mOrientationHelper.getDecoratedStart(var12);
                  int var10 = this.mOrientationHelper.getDecoratedEnd(var12);
                  int var8 = this.mOrientationHelper.getStartAfterPadding();
                  int var6 = this.mOrientationHelper.getEndAfterPadding();
                  boolean var13;
                  if (var10 <= var8 && var9 < var8) {
                     var13 = true;
                  } else {
                     var13 = false;
                  }

                  boolean var5 = var7;
                  if (var9 >= var6) {
                     var5 = var7;
                     if (var10 > var6) {
                        var5 = true;
                     }
                  }

                  if (var13 || var5) {
                     var4 = var8;
                     if (var3.mLayoutFromEnd) {
                        var4 = var6;
                     }

                     var3.mCoordinate = var4;
                  }
               }

               return true;
            }
         }
      }
   }

   private boolean updateAnchorFromPendingData(RecyclerView.State var1, AnchorInfo var2) {
      boolean var4 = var1.isPreLayout();
      boolean var5 = false;
      if (!var4) {
         int var3 = this.mPendingScrollPosition;
         if (var3 != -1) {
            if (var3 >= 0 && var3 < var1.getItemCount()) {
               var2.mPosition = this.mPendingScrollPosition;
               SavedState var6 = this.mPendingSavedState;
               if (var6 != null && var6.hasValidAnchor()) {
                  var2.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
                  if (var2.mLayoutFromEnd) {
                     var2.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                  } else {
                     var2.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
                  }

                  return true;
               }

               if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                  View var7 = this.findViewByPosition(this.mPendingScrollPosition);
                  if (var7 != null) {
                     if (this.mOrientationHelper.getDecoratedMeasurement(var7) > this.mOrientationHelper.getTotalSpace()) {
                        var2.assignCoordinateFromPadding();
                        return true;
                     }

                     if (this.mOrientationHelper.getDecoratedStart(var7) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                        var2.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                        var2.mLayoutFromEnd = false;
                        return true;
                     }

                     if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(var7) < 0) {
                        var2.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                        var2.mLayoutFromEnd = true;
                        return true;
                     }

                     if (var2.mLayoutFromEnd) {
                        var3 = this.mOrientationHelper.getDecoratedEnd(var7) + this.mOrientationHelper.getTotalSpaceChange();
                     } else {
                        var3 = this.mOrientationHelper.getDecoratedStart(var7);
                     }

                     var2.mCoordinate = var3;
                  } else {
                     if (this.getChildCount() > 0) {
                        var3 = this.getPosition(this.getChildAt(0));
                        if (this.mPendingScrollPosition < var3) {
                           var4 = true;
                        } else {
                           var4 = false;
                        }

                        if (var4 == this.mShouldReverseLayout) {
                           var5 = true;
                        }

                        var2.mLayoutFromEnd = var5;
                     }

                     var2.assignCoordinateFromPadding();
                  }

                  return true;
               }

               var2.mLayoutFromEnd = this.mShouldReverseLayout;
               if (this.mShouldReverseLayout) {
                  var2.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
               } else {
                  var2.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
               }

               return true;
            }

            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
         }
      }

      return false;
   }

   private void updateAnchorInfoForLayout(RecyclerView.Recycler var1, RecyclerView.State var2, AnchorInfo var3) {
      if (!this.updateAnchorFromPendingData(var2, var3)) {
         if (!this.updateAnchorFromChildren(var1, var2, var3)) {
            var3.assignCoordinateFromPadding();
            int var4;
            if (this.mStackFromEnd) {
               var4 = var2.getItemCount() - 1;
            } else {
               var4 = 0;
            }

            var3.mPosition = var4;
         }
      }
   }

   private void updateLayoutState(int var1, int var2, boolean var3, RecyclerView.State var4) {
      this.mLayoutState.mInfinite = this.resolveIsInfinite();
      this.mLayoutState.mLayoutDirection = var1;
      int[] var10 = this.mReusableIntPair;
      boolean var5 = false;
      var10[0] = 0;
      byte var9 = 1;
      byte var8 = 1;
      var10[1] = 0;
      this.calculateExtraLayoutSpace(var4, var10);
      int var6 = Math.max(0, this.mReusableIntPair[0]);
      int var7 = Math.max(0, this.mReusableIntPair[1]);
      if (var1 == 1) {
         var5 = true;
      }

      LayoutState var12 = this.mLayoutState;
      if (var5) {
         var1 = var7;
      } else {
         var1 = var6;
      }

      var12.mExtraFillSpace = var1;
      var12 = this.mLayoutState;
      if (!var5) {
         var6 = var7;
      }

      var12.mNoRecycleSpace = var6;
      byte var11;
      View var13;
      LayoutState var14;
      if (var5) {
         var12 = this.mLayoutState;
         var12.mExtraFillSpace += this.mOrientationHelper.getEndPadding();
         var13 = this.getChildClosestToEnd();
         var14 = this.mLayoutState;
         var11 = var8;
         if (this.mShouldReverseLayout) {
            var11 = -1;
         }

         var14.mItemDirection = var11;
         this.mLayoutState.mCurrentPosition = this.getPosition(var13) + this.mLayoutState.mItemDirection;
         this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(var13);
         var1 = this.mOrientationHelper.getDecoratedEnd(var13) - this.mOrientationHelper.getEndAfterPadding();
      } else {
         var13 = this.getChildClosestToStart();
         var14 = this.mLayoutState;
         var14.mExtraFillSpace += this.mOrientationHelper.getStartAfterPadding();
         var14 = this.mLayoutState;
         if (this.mShouldReverseLayout) {
            var11 = var9;
         } else {
            var11 = -1;
         }

         var14.mItemDirection = var11;
         this.mLayoutState.mCurrentPosition = this.getPosition(var13) + this.mLayoutState.mItemDirection;
         this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(var13);
         var1 = -this.mOrientationHelper.getDecoratedStart(var13) + this.mOrientationHelper.getStartAfterPadding();
      }

      this.mLayoutState.mAvailable = var2;
      if (var3) {
         var12 = this.mLayoutState;
         var12.mAvailable -= var1;
      }

      this.mLayoutState.mScrollingOffset = var1;
   }

   private void updateLayoutStateToFillEnd(int var1, int var2) {
      this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - var2;
      LayoutState var4 = this.mLayoutState;
      byte var3;
      if (this.mShouldReverseLayout) {
         var3 = -1;
      } else {
         var3 = 1;
      }

      var4.mItemDirection = var3;
      this.mLayoutState.mCurrentPosition = var1;
      this.mLayoutState.mLayoutDirection = 1;
      this.mLayoutState.mOffset = var2;
      this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
   }

   private void updateLayoutStateToFillEnd(AnchorInfo var1) {
      this.updateLayoutStateToFillEnd(var1.mPosition, var1.mCoordinate);
   }

   private void updateLayoutStateToFillStart(int var1, int var2) {
      this.mLayoutState.mAvailable = var2 - this.mOrientationHelper.getStartAfterPadding();
      this.mLayoutState.mCurrentPosition = var1;
      LayoutState var3 = this.mLayoutState;
      byte var4;
      if (this.mShouldReverseLayout) {
         var4 = 1;
      } else {
         var4 = -1;
      }

      var3.mItemDirection = var4;
      this.mLayoutState.mLayoutDirection = -1;
      this.mLayoutState.mOffset = var2;
      this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
   }

   private void updateLayoutStateToFillStart(AnchorInfo var1) {
      this.updateLayoutStateToFillStart(var1.mPosition, var1.mCoordinate);
   }

   public void assertNotInLayoutOrScroll(String var1) {
      if (this.mPendingSavedState == null) {
         super.assertNotInLayoutOrScroll(var1);
      }

   }

   protected void calculateExtraLayoutSpace(RecyclerView.State var1, int[] var2) {
      int var3 = this.getExtraLayoutSpace(var1);
      int var4;
      int var5;
      if (this.mLayoutState.mLayoutDirection == -1) {
         var4 = 0;
         var5 = var3;
      } else {
         var5 = 0;
         var4 = var3;
      }

      var2[0] = var5;
      var2[1] = var4;
   }

   public boolean canScrollHorizontally() {
      boolean var1;
      if (this.mOrientation == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean canScrollVertically() {
      int var1 = this.mOrientation;
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void collectAdjacentPrefetchPositions(int var1, int var2, RecyclerView.State var3, RecyclerView.LayoutManager.LayoutPrefetchRegistry var4) {
      if (this.mOrientation != 0) {
         var1 = var2;
      }

      if (this.getChildCount() != 0 && var1 != 0) {
         this.ensureLayoutState();
         byte var5;
         if (var1 > 0) {
            var5 = 1;
         } else {
            var5 = -1;
         }

         this.updateLayoutState(var5, Math.abs(var1), true, var3);
         this.collectPrefetchPositionsForLayoutState(var3, this.mLayoutState, var4);
      }

   }

   public void collectInitialPrefetchPositions(int var1, RecyclerView.LayoutManager.LayoutPrefetchRegistry var2) {
      SavedState var9 = this.mPendingSavedState;
      byte var4 = -1;
      int var3;
      int var5;
      boolean var7;
      if (var9 != null && var9.hasValidAnchor()) {
         var7 = this.mPendingSavedState.mAnchorLayoutFromEnd;
         var3 = this.mPendingSavedState.mAnchorPosition;
      } else {
         this.resolveShouldLayoutReverse();
         boolean var8 = this.mShouldReverseLayout;
         var5 = this.mPendingScrollPosition;
         var7 = var8;
         var3 = var5;
         if (var5 == -1) {
            if (var8) {
               var3 = var1 - 1;
               var7 = var8;
            } else {
               var3 = 0;
               var7 = var8;
            }
         }
      }

      if (!var7) {
         var4 = 1;
      }

      byte var6 = 0;
      var5 = var3;

      for(var3 = var6; var3 < this.mInitialPrefetchItemCount && var5 >= 0 && var5 < var1; ++var3) {
         var2.addPosition(var5, 0);
         var5 += var4;
      }

   }

   void collectPrefetchPositionsForLayoutState(RecyclerView.State var1, LayoutState var2, RecyclerView.LayoutManager.LayoutPrefetchRegistry var3) {
      int var4 = var2.mCurrentPosition;
      if (var4 >= 0 && var4 < var1.getItemCount()) {
         var3.addPosition(var4, Math.max(0, var2.mScrollingOffset));
      }

   }

   public int computeHorizontalScrollExtent(RecyclerView.State var1) {
      return this.computeScrollExtent(var1);
   }

   public int computeHorizontalScrollOffset(RecyclerView.State var1) {
      return this.computeScrollOffset(var1);
   }

   public int computeHorizontalScrollRange(RecyclerView.State var1) {
      return this.computeScrollRange(var1);
   }

   public PointF computeScrollVectorForPosition(int var1) {
      if (this.getChildCount() == 0) {
         return null;
      } else {
         boolean var4 = false;
         int var3 = this.getPosition(this.getChildAt(0));
         byte var2 = 1;
         if (var1 < var3) {
            var4 = true;
         }

         byte var5 = var2;
         if (var4 != this.mShouldReverseLayout) {
            var5 = -1;
         }

         return this.mOrientation == 0 ? new PointF((float)var5, 0.0F) : new PointF(0.0F, (float)var5);
      }
   }

   public int computeVerticalScrollExtent(RecyclerView.State var1) {
      return this.computeScrollExtent(var1);
   }

   public int computeVerticalScrollOffset(RecyclerView.State var1) {
      return this.computeScrollOffset(var1);
   }

   public int computeVerticalScrollRange(RecyclerView.State var1) {
      return this.computeScrollRange(var1);
   }

   int convertFocusDirectionToLayoutDirection(int var1) {
      int var2 = -1;
      byte var3 = 1;
      byte var4 = 1;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 17) {
               if (var1 != 33) {
                  if (var1 != 66) {
                     if (var1 != 130) {
                        return Integer.MIN_VALUE;
                     } else {
                        if (this.mOrientation == 1) {
                           var1 = var4;
                        } else {
                           var1 = Integer.MIN_VALUE;
                        }

                        return var1;
                     }
                  } else {
                     if (this.mOrientation == 0) {
                        var1 = var3;
                     } else {
                        var1 = Integer.MIN_VALUE;
                     }

                     return var1;
                  }
               } else {
                  if (this.mOrientation != 1) {
                     var2 = Integer.MIN_VALUE;
                  }

                  return var2;
               }
            } else {
               if (this.mOrientation != 0) {
                  var2 = Integer.MIN_VALUE;
               }

               return var2;
            }
         } else if (this.mOrientation == 1) {
            return 1;
         } else {
            return this.isLayoutRTL() ? -1 : 1;
         }
      } else if (this.mOrientation == 1) {
         return -1;
      } else {
         return this.isLayoutRTL() ? 1 : -1;
      }
   }

   LayoutState createLayoutState() {
      return new LayoutState();
   }

   void ensureLayoutState() {
      if (this.mLayoutState == null) {
         this.mLayoutState = this.createLayoutState();
      }

   }

   int fill(RecyclerView.Recycler var1, LayoutState var2, RecyclerView.State var3, boolean var4) {
      int var7 = var2.mAvailable;
      if (var2.mScrollingOffset != Integer.MIN_VALUE) {
         if (var2.mAvailable < 0) {
            var2.mScrollingOffset += var2.mAvailable;
         }

         this.recycleByLayoutState(var1, var2);
      }

      int var6 = var2.mAvailable + var2.mExtraFillSpace;
      LayoutChunkResult var8 = this.mLayoutChunkResult;

      while((var2.mInfinite || var6 > 0) && var2.hasMore(var3)) {
         var8.resetInternal();
         this.layoutChunk(var1, var3, var2, var8);
         if (var8.mFinished) {
            break;
         }

         int var5;
         label45: {
            var2.mOffset += var8.mConsumed * var2.mLayoutDirection;
            if (var8.mIgnoreConsumed && var2.mScrapList == null) {
               var5 = var6;
               if (var3.isPreLayout()) {
                  break label45;
               }
            }

            var2.mAvailable -= var8.mConsumed;
            var5 = var6 - var8.mConsumed;
         }

         if (var2.mScrollingOffset != Integer.MIN_VALUE) {
            var2.mScrollingOffset += var8.mConsumed;
            if (var2.mAvailable < 0) {
               var2.mScrollingOffset += var2.mAvailable;
            }

            this.recycleByLayoutState(var1, var2);
         }

         var6 = var5;
         if (var4) {
            var6 = var5;
            if (var8.mFocusable) {
               break;
            }
         }
      }

      return var7 - var2.mAvailable;
   }

   public int findFirstCompletelyVisibleItemPosition() {
      View var2 = this.findOneVisibleChild(0, this.getChildCount(), true, false);
      int var1;
      if (var2 == null) {
         var1 = -1;
      } else {
         var1 = this.getPosition(var2);
      }

      return var1;
   }

   View findFirstVisibleChildClosestToEnd(boolean var1, boolean var2) {
      return this.mShouldReverseLayout ? this.findOneVisibleChild(0, this.getChildCount(), var1, var2) : this.findOneVisibleChild(this.getChildCount() - 1, -1, var1, var2);
   }

   View findFirstVisibleChildClosestToStart(boolean var1, boolean var2) {
      return this.mShouldReverseLayout ? this.findOneVisibleChild(this.getChildCount() - 1, -1, var1, var2) : this.findOneVisibleChild(0, this.getChildCount(), var1, var2);
   }

   public int findFirstVisibleItemPosition() {
      View var2 = this.findOneVisibleChild(0, this.getChildCount(), false, true);
      int var1;
      if (var2 == null) {
         var1 = -1;
      } else {
         var1 = this.getPosition(var2);
      }

      return var1;
   }

   public int findLastCompletelyVisibleItemPosition() {
      int var2 = this.getChildCount();
      int var1 = -1;
      View var3 = this.findOneVisibleChild(var2 - 1, -1, true, false);
      if (var3 != null) {
         var1 = this.getPosition(var3);
      }

      return var1;
   }

   public int findLastVisibleItemPosition() {
      int var2 = this.getChildCount();
      int var1 = -1;
      View var3 = this.findOneVisibleChild(var2 - 1, -1, false, true);
      if (var3 != null) {
         var1 = this.getPosition(var3);
      }

      return var1;
   }

   View findOnePartiallyOrCompletelyInvisibleChild(int var1, int var2) {
      this.ensureLayoutState();
      short var3;
      if (var2 > var1) {
         var3 = 1;
      } else if (var2 < var1) {
         var3 = -1;
      } else {
         var3 = 0;
      }

      if (var3 == 0) {
         return this.getChildAt(var1);
      } else {
         short var4;
         if (this.mOrientationHelper.getDecoratedStart(this.getChildAt(var1)) < this.mOrientationHelper.getStartAfterPadding()) {
            var4 = 16644;
            var3 = 16388;
         } else {
            var4 = 4161;
            var3 = 4097;
         }

         View var5;
         if (this.mOrientation == 0) {
            var5 = this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(var1, var2, var4, var3);
         } else {
            var5 = this.mVerticalBoundCheck.findOneViewWithinBoundFlags(var1, var2, var4, var3);
         }

         return var5;
      }
   }

   View findOneVisibleChild(int var1, int var2, boolean var3, boolean var4) {
      this.ensureLayoutState();
      short var6 = 320;
      short var5;
      if (var3) {
         var5 = 24579;
      } else {
         var5 = 320;
      }

      if (!var4) {
         var6 = 0;
      }

      View var7;
      if (this.mOrientation == 0) {
         var7 = this.mHorizontalBoundCheck.findOneViewWithinBoundFlags(var1, var2, var5, var6);
      } else {
         var7 = this.mVerticalBoundCheck.findOneViewWithinBoundFlags(var1, var2, var5, var6);
      }

      return var7;
   }

   View findReferenceChild(RecyclerView.Recycler var1, RecyclerView.State var2, boolean var3, boolean var4) {
      this.ensureLayoutState();
      int var5 = this.getChildCount();
      int var6 = -1;
      byte var7;
      if (var4) {
         var5 = this.getChildCount() - 1;
         var7 = -1;
      } else {
         var6 = var5;
         var5 = 0;
         var7 = 1;
      }

      int var11 = var2.getItemCount();
      int var10 = this.mOrientationHelper.getStartAfterPadding();
      int var12 = this.mOrientationHelper.getEndAfterPadding();
      View var14 = null;
      View var20 = null;

      View var18;
      View var19;
      for(var19 = null; var5 != var6; var19 = var18) {
         View var15 = this.getChildAt(var5);
         int var8 = this.getPosition(var15);
         int var9 = this.mOrientationHelper.getDecoratedStart(var15);
         int var13 = this.mOrientationHelper.getDecoratedEnd(var15);
         View var17 = var14;
         View var16 = var20;
         var18 = var19;
         if (var8 >= 0) {
            var17 = var14;
            var16 = var20;
            var18 = var19;
            if (var8 < var11) {
               if (((RecyclerView.LayoutParams)var15.getLayoutParams()).isItemRemoved()) {
                  var17 = var14;
                  var16 = var20;
                  var18 = var19;
                  if (var19 == null) {
                     var17 = var14;
                     var16 = var20;
                     var18 = var15;
                  }
               } else {
                  label90: {
                     boolean var21;
                     if (var13 <= var10 && var9 < var10) {
                        var21 = true;
                     } else {
                        var21 = false;
                     }

                     boolean var22;
                     if (var9 >= var12 && var13 > var12) {
                        var22 = true;
                     } else {
                        var22 = false;
                     }

                     if (!var21 && !var22) {
                        return var15;
                     }

                     label67: {
                        if (var3) {
                           if (var22) {
                              break label67;
                           }

                           var17 = var14;
                           var16 = var20;
                           var18 = var19;
                           if (var14 != null) {
                              break label90;
                           }
                        } else {
                           if (var21) {
                              break label67;
                           }

                           var17 = var14;
                           var16 = var20;
                           var18 = var19;
                           if (var14 != null) {
                              break label90;
                           }
                        }

                        var18 = var19;
                        var16 = var20;
                        var17 = var15;
                        break label90;
                     }

                     var17 = var14;
                     var16 = var15;
                     var18 = var19;
                  }
               }
            }
         }

         var5 += var7;
         var14 = var17;
         var20 = var16;
      }

      if (var14 == null) {
         if (var20 != null) {
            var14 = var20;
         } else {
            var14 = var19;
         }
      }

      return var14;
   }

   public View findViewByPosition(int var1) {
      int var2 = this.getChildCount();
      if (var2 == 0) {
         return null;
      } else {
         int var3 = var1 - this.getPosition(this.getChildAt(0));
         if (var3 >= 0 && var3 < var2) {
            View var4 = this.getChildAt(var3);
            if (this.getPosition(var4) == var1) {
               return var4;
            }
         }

         return super.findViewByPosition(var1);
      }
   }

   public RecyclerView.LayoutParams generateDefaultLayoutParams() {
      return new RecyclerView.LayoutParams(-2, -2);
   }

   @Deprecated
   protected int getExtraLayoutSpace(RecyclerView.State var1) {
      return var1.hasTargetScrollPosition() ? this.mOrientationHelper.getTotalSpace() : 0;
   }

   public int getInitialPrefetchItemCount() {
      return this.mInitialPrefetchItemCount;
   }

   public int getOrientation() {
      return this.mOrientation;
   }

   public boolean getRecycleChildrenOnDetach() {
      return this.mRecycleChildrenOnDetach;
   }

   public boolean getReverseLayout() {
      return this.mReverseLayout;
   }

   public boolean getStackFromEnd() {
      return this.mStackFromEnd;
   }

   public boolean isAutoMeasureEnabled() {
      return true;
   }

   protected boolean isLayoutRTL() {
      int var1 = this.getLayoutDirection();
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public boolean isSmoothScrollbarEnabled() {
      return this.mSmoothScrollbarEnabled;
   }

   void layoutChunk(RecyclerView.Recycler var1, RecyclerView.State var2, LayoutState var3, LayoutChunkResult var4) {
      View var14 = var3.next(var1);
      if (var14 == null) {
         var4.mFinished = true;
      } else {
         RecyclerView.LayoutParams var13 = (RecyclerView.LayoutParams)var14.getLayoutParams();
         boolean var11;
         boolean var12;
         if (var3.mScrapList == null) {
            var12 = this.mShouldReverseLayout;
            if (var3.mLayoutDirection == -1) {
               var11 = true;
            } else {
               var11 = false;
            }

            if (var12 == var11) {
               this.addView(var14);
            } else {
               this.addView(var14, 0);
            }
         } else {
            var12 = this.mShouldReverseLayout;
            if (var3.mLayoutDirection == -1) {
               var11 = true;
            } else {
               var11 = false;
            }

            if (var12 == var11) {
               this.addDisappearingView(var14);
            } else {
               this.addDisappearingView(var14, 0);
            }
         }

         this.measureChildWithMargins(var14, 0, 0);
         var4.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(var14);
         int var5;
         int var6;
         int var7;
         int var8;
         int var9;
         if (this.mOrientation == 1) {
            if (this.isLayoutRTL()) {
               var5 = this.getWidth() - this.getPaddingRight();
               var8 = var5 - this.mOrientationHelper.getDecoratedMeasurementInOther(var14);
            } else {
               var8 = this.getPaddingLeft();
               var5 = this.mOrientationHelper.getDecoratedMeasurementInOther(var14) + var8;
            }

            if (var3.mLayoutDirection == -1) {
               var7 = var3.mOffset;
               var9 = var3.mOffset - var4.mConsumed;
               var6 = var5;
               var5 = var9;
            } else {
               var9 = var3.mOffset;
               var7 = var3.mOffset + var4.mConsumed;
               var6 = var5;
               var5 = var9;
            }
         } else {
            var6 = this.getPaddingTop();
            var5 = this.mOrientationHelper.getDecoratedMeasurementInOther(var14) + var6;
            int var10;
            if (var3.mLayoutDirection == -1) {
               var9 = var3.mOffset;
               var10 = var3.mOffset;
               var8 = var4.mConsumed;
               var7 = var5;
               var8 = var10 - var8;
               var5 = var6;
               var6 = var9;
            } else {
               var9 = var3.mOffset;
               var10 = var3.mOffset + var4.mConsumed;
               var8 = var6;
               var7 = var5;
               var6 = var10;
               var5 = var8;
               var8 = var9;
            }
         }

         this.layoutDecoratedWithMargins(var14, var8, var5, var6, var7);
         if (var13.isItemRemoved() || var13.isItemChanged()) {
            var4.mIgnoreConsumed = true;
         }

         var4.mFocusable = var14.hasFocusable();
      }
   }

   void onAnchorReady(RecyclerView.Recycler var1, RecyclerView.State var2, AnchorInfo var3, int var4) {
   }

   public void onDetachedFromWindow(RecyclerView var1, RecyclerView.Recycler var2) {
      super.onDetachedFromWindow(var1, var2);
      if (this.mRecycleChildrenOnDetach) {
         this.removeAndRecycleAllViews(var2);
         var2.clear();
      }

   }

   public View onFocusSearchFailed(View var1, int var2, RecyclerView.Recycler var3, RecyclerView.State var4) {
      this.resolveShouldLayoutReverse();
      if (this.getChildCount() == 0) {
         return null;
      } else {
         var2 = this.convertFocusDirectionToLayoutDirection(var2);
         if (var2 == Integer.MIN_VALUE) {
            return null;
         } else {
            this.ensureLayoutState();
            this.updateLayoutState(var2, (int)((float)this.mOrientationHelper.getTotalSpace() * 0.33333334F), false, var4);
            this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
            this.mLayoutState.mRecycle = false;
            this.fill(var3, this.mLayoutState, var4, true);
            if (var2 == -1) {
               var1 = this.findPartiallyOrCompletelyInvisibleChildClosestToStart();
            } else {
               var1 = this.findPartiallyOrCompletelyInvisibleChildClosestToEnd();
            }

            View var5;
            if (var2 == -1) {
               var5 = this.getChildClosestToStart();
            } else {
               var5 = this.getChildClosestToEnd();
            }

            if (var5.hasFocusable()) {
               return var1 == null ? null : var5;
            } else {
               return var1;
            }
         }
      }
   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent var1) {
      super.onInitializeAccessibilityEvent(var1);
      if (this.getChildCount() > 0) {
         var1.setFromIndex(this.findFirstVisibleItemPosition());
         var1.setToIndex(this.findLastVisibleItemPosition());
      }

   }

   public void onLayoutChildren(RecyclerView.Recycler var1, RecyclerView.State var2) {
      SavedState var9 = this.mPendingSavedState;
      int var5 = -1;
      if ((var9 != null || this.mPendingScrollPosition != -1) && var2.getItemCount() == 0) {
         this.removeAndRecycleAllViews(var1);
      } else {
         var9 = this.mPendingSavedState;
         if (var9 != null && var9.hasValidAnchor()) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
         }

         this.ensureLayoutState();
         this.mLayoutState.mRecycle = false;
         this.resolveShouldLayoutReverse();
         View var10 = this.getFocusedChild();
         if (this.mAnchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) {
            if (var10 != null && (this.mOrientationHelper.getDecoratedStart(var10) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(var10) <= this.mOrientationHelper.getStartAfterPadding())) {
               this.mAnchorInfo.assignFromViewAndKeepVisibleRect(var10, this.getPosition(var10));
            }
         } else {
            this.mAnchorInfo.reset();
            this.mAnchorInfo.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
            this.updateAnchorInfoForLayout(var1, var2, this.mAnchorInfo);
            this.mAnchorInfo.mValid = true;
         }

         LayoutState var11 = this.mLayoutState;
         int var3;
         if (var11.mLastScrollDelta >= 0) {
            var3 = 1;
         } else {
            var3 = -1;
         }

         var11.mLayoutDirection = var3;
         int[] var12 = this.mReusableIntPair;
         var12[0] = 0;
         var12[1] = 0;
         this.calculateExtraLayoutSpace(var2, var12);
         int var7 = Math.max(0, this.mReusableIntPair[0]) + this.mOrientationHelper.getStartAfterPadding();
         int var6 = Math.max(0, this.mReusableIntPair[1]) + this.mOrientationHelper.getEndPadding();
         int var4 = var7;
         var3 = var6;
         if (var2.isPreLayout()) {
            int var8 = this.mPendingScrollPosition;
            var4 = var7;
            var3 = var6;
            if (var8 != -1) {
               var4 = var7;
               var3 = var6;
               if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                  var10 = this.findViewByPosition(var8);
                  var4 = var7;
                  var3 = var6;
                  if (var10 != null) {
                     if (this.mShouldReverseLayout) {
                        var3 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(var10);
                        var4 = this.mPendingScrollPositionOffset;
                     } else {
                        var4 = this.mOrientationHelper.getDecoratedStart(var10) - this.mOrientationHelper.getStartAfterPadding();
                        var3 = this.mPendingScrollPositionOffset;
                     }

                     var3 -= var4;
                     if (var3 > 0) {
                        var4 = var7 + var3;
                        var3 = var6;
                     } else {
                        var3 = var6 - var3;
                        var4 = var7;
                     }
                  }
               }
            }
         }

         label85: {
            if (this.mAnchorInfo.mLayoutFromEnd) {
               if (!this.mShouldReverseLayout) {
                  break label85;
               }
            } else if (this.mShouldReverseLayout) {
               break label85;
            }

            var5 = 1;
         }

         this.onAnchorReady(var1, var2, this.mAnchorInfo, var5);
         this.detachAndScrapAttachedViews(var1);
         this.mLayoutState.mInfinite = this.resolveIsInfinite();
         this.mLayoutState.mIsPreLayout = var2.isPreLayout();
         this.mLayoutState.mNoRecycleSpace = 0;
         if (this.mAnchorInfo.mLayoutFromEnd) {
            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = var4;
            this.fill(var1, this.mLayoutState, var2, false);
            var5 = this.mLayoutState.mOffset;
            var7 = this.mLayoutState.mCurrentPosition;
            var4 = var3;
            if (this.mLayoutState.mAvailable > 0) {
               var4 = var3 + this.mLayoutState.mAvailable;
            }

            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = var4;
            var11 = this.mLayoutState;
            var11.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(var1, this.mLayoutState, var2, false);
            var6 = this.mLayoutState.mOffset;
            var4 = var5;
            var3 = var6;
            if (this.mLayoutState.mAvailable > 0) {
               var3 = this.mLayoutState.mAvailable;
               this.updateLayoutStateToFillStart(var7, var5);
               this.mLayoutState.mExtraFillSpace = var3;
               this.fill(var1, this.mLayoutState, var2, false);
               var4 = this.mLayoutState.mOffset;
               var3 = var6;
            }
         } else {
            this.updateLayoutStateToFillEnd(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = var3;
            this.fill(var1, this.mLayoutState, var2, false);
            var5 = this.mLayoutState.mOffset;
            var7 = this.mLayoutState.mCurrentPosition;
            var3 = var4;
            if (this.mLayoutState.mAvailable > 0) {
               var3 = var4 + this.mLayoutState.mAvailable;
            }

            this.updateLayoutStateToFillStart(this.mAnchorInfo);
            this.mLayoutState.mExtraFillSpace = var3;
            var11 = this.mLayoutState;
            var11.mCurrentPosition += this.mLayoutState.mItemDirection;
            this.fill(var1, this.mLayoutState, var2, false);
            var6 = this.mLayoutState.mOffset;
            var4 = var6;
            var3 = var5;
            if (this.mLayoutState.mAvailable > 0) {
               var3 = this.mLayoutState.mAvailable;
               this.updateLayoutStateToFillEnd(var7, var5);
               this.mLayoutState.mExtraFillSpace = var3;
               this.fill(var1, this.mLayoutState, var2, false);
               var3 = this.mLayoutState.mOffset;
               var4 = var6;
            }
         }

         var6 = var4;
         var5 = var3;
         if (this.getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
               var6 = this.fixLayoutEndGap(var3, var1, var2, true);
               var5 = var4 + var6;
               var4 = var3 + var6;
               var3 = this.fixLayoutStartGap(var5, var1, var2, false);
            } else {
               var6 = this.fixLayoutStartGap(var4, var1, var2, true);
               var5 = var4 + var6;
               var4 = var3 + var6;
               var3 = this.fixLayoutEndGap(var4, var1, var2, false);
            }

            var6 = var5 + var3;
            var5 = var4 + var3;
         }

         this.layoutForPredictiveAnimations(var1, var2, var6, var5);
         if (!var2.isPreLayout()) {
            this.mOrientationHelper.onLayoutComplete();
         } else {
            this.mAnchorInfo.reset();
         }

         this.mLastStackFromEnd = this.mStackFromEnd;
      }
   }

   public void onLayoutCompleted(RecyclerView.State var1) {
      super.onLayoutCompleted(var1);
      this.mPendingSavedState = null;
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.mAnchorInfo.reset();
   }

   public void onRestoreInstanceState(Parcelable var1) {
      if (var1 instanceof SavedState) {
         SavedState var2 = (SavedState)var1;
         this.mPendingSavedState = var2;
         if (this.mPendingScrollPosition != -1) {
            var2.invalidateAnchor();
         }

         this.requestLayout();
      }

   }

   public Parcelable onSaveInstanceState() {
      if (this.mPendingSavedState != null) {
         return new SavedState(this.mPendingSavedState);
      } else {
         SavedState var2 = new SavedState();
         if (this.getChildCount() > 0) {
            this.ensureLayoutState();
            boolean var1 = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            var2.mAnchorLayoutFromEnd = var1;
            View var3;
            if (var1) {
               var3 = this.getChildClosestToEnd();
               var2.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(var3);
               var2.mAnchorPosition = this.getPosition(var3);
            } else {
               var3 = this.getChildClosestToStart();
               var2.mAnchorPosition = this.getPosition(var3);
               var2.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(var3) - this.mOrientationHelper.getStartAfterPadding();
            }
         } else {
            var2.invalidateAnchor();
         }

         return var2;
      }
   }

   public void prepareForDrop(View var1, View var2, int var3, int var4) {
      this.assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
      this.ensureLayoutState();
      this.resolveShouldLayoutReverse();
      var3 = this.getPosition(var1);
      var4 = this.getPosition(var2);
      byte var5;
      if (var3 < var4) {
         var5 = 1;
      } else {
         var5 = -1;
      }

      if (this.mShouldReverseLayout) {
         if (var5 == 1) {
            this.scrollToPositionWithOffset(var4, this.mOrientationHelper.getEndAfterPadding() - (this.mOrientationHelper.getDecoratedStart(var2) + this.mOrientationHelper.getDecoratedMeasurement(var1)));
         } else {
            this.scrollToPositionWithOffset(var4, this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(var2));
         }
      } else if (var5 == -1) {
         this.scrollToPositionWithOffset(var4, this.mOrientationHelper.getDecoratedStart(var2));
      } else {
         this.scrollToPositionWithOffset(var4, this.mOrientationHelper.getDecoratedEnd(var2) - this.mOrientationHelper.getDecoratedMeasurement(var1));
      }

   }

   boolean resolveIsInfinite() {
      boolean var1;
      if (this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   int scrollBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      if (this.getChildCount() != 0 && var1 != 0) {
         this.ensureLayoutState();
         this.mLayoutState.mRecycle = true;
         byte var4;
         if (var1 > 0) {
            var4 = 1;
         } else {
            var4 = -1;
         }

         int var5 = Math.abs(var1);
         this.updateLayoutState(var4, var5, true, var3);
         int var6 = this.mLayoutState.mScrollingOffset + this.fill(var2, this.mLayoutState, var3, false);
         if (var6 < 0) {
            return 0;
         } else {
            if (var5 > var6) {
               var1 = var4 * var6;
            }

            this.mOrientationHelper.offsetChildren(-var1);
            this.mLayoutState.mLastScrollDelta = var1;
            return var1;
         }
      } else {
         return 0;
      }
   }

   public int scrollHorizontallyBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      return this.mOrientation == 1 ? 0 : this.scrollBy(var1, var2, var3);
   }

   public void scrollToPosition(int var1) {
      this.mPendingScrollPosition = var1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      SavedState var2 = this.mPendingSavedState;
      if (var2 != null) {
         var2.invalidateAnchor();
      }

      this.requestLayout();
   }

   public void scrollToPositionWithOffset(int var1, int var2) {
      this.mPendingScrollPosition = var1;
      this.mPendingScrollPositionOffset = var2;
      SavedState var3 = this.mPendingSavedState;
      if (var3 != null) {
         var3.invalidateAnchor();
      }

      this.requestLayout();
   }

   public int scrollVerticallyBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      return this.mOrientation == 0 ? 0 : this.scrollBy(var1, var2, var3);
   }

   public void setInitialPrefetchItemCount(int var1) {
      this.mInitialPrefetchItemCount = var1;
   }

   public void setOrientation(int var1) {
      if (var1 != 0 && var1 != 1) {
         throw new IllegalArgumentException("invalid orientation:" + var1);
      } else {
         this.assertNotInLayoutOrScroll((String)null);
         if (var1 != this.mOrientation || this.mOrientationHelper == null) {
            OrientationHelper var2 = OrientationHelper.createOrientationHelper(this, var1);
            this.mOrientationHelper = var2;
            this.mAnchorInfo.mOrientationHelper = var2;
            this.mOrientation = var1;
            this.requestLayout();
         }

      }
   }

   public void setRecycleChildrenOnDetach(boolean var1) {
      this.mRecycleChildrenOnDetach = var1;
   }

   public void setReverseLayout(boolean var1) {
      this.assertNotInLayoutOrScroll((String)null);
      if (var1 != this.mReverseLayout) {
         this.mReverseLayout = var1;
         this.requestLayout();
      }
   }

   public void setSmoothScrollbarEnabled(boolean var1) {
      this.mSmoothScrollbarEnabled = var1;
   }

   public void setStackFromEnd(boolean var1) {
      this.assertNotInLayoutOrScroll((String)null);
      if (this.mStackFromEnd != var1) {
         this.mStackFromEnd = var1;
         this.requestLayout();
      }
   }

   boolean shouldMeasureTwice() {
      boolean var1;
      if (this.getHeightMode() != 1073741824 && this.getWidthMode() != 1073741824 && this.hasFlexibleChildInBothOrientations()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public void smoothScrollToPosition(RecyclerView var1, RecyclerView.State var2, int var3) {
      LinearSmoothScroller var4 = new LinearSmoothScroller(var1.getContext());
      var4.setTargetPosition(var3);
      this.startSmoothScroll(var4);
   }

   public boolean supportsPredictiveItemAnimations() {
      boolean var1;
      if (this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   void validateChildOrder() {
      Log.d("LinearLayoutManager", "validating child count " + this.getChildCount());
      int var1 = this.getChildCount();
      boolean var7 = true;
      boolean var6 = true;
      if (var1 >= 1) {
         int var3 = this.getPosition(this.getChildAt(0));
         int var2 = this.mOrientationHelper.getDecoratedStart(this.getChildAt(0));
         int var4;
         int var5;
         View var8;
         StringBuilder var9;
         if (this.mShouldReverseLayout) {
            for(var1 = 1; var1 < this.getChildCount(); ++var1) {
               var8 = this.getChildAt(var1);
               var4 = this.getPosition(var8);
               var5 = this.mOrientationHelper.getDecoratedStart(var8);
               if (var4 < var3) {
                  this.logChildren();
                  var9 = (new StringBuilder()).append("detected invalid position. loc invalid? ");
                  if (var5 >= var2) {
                     var6 = false;
                  }

                  throw new RuntimeException(var9.append(var6).toString());
               }

               if (var5 > var2) {
                  this.logChildren();
                  throw new RuntimeException("detected invalid location");
               }
            }
         } else {
            for(var1 = 1; var1 < this.getChildCount(); ++var1) {
               var8 = this.getChildAt(var1);
               var5 = this.getPosition(var8);
               var4 = this.mOrientationHelper.getDecoratedStart(var8);
               if (var5 < var3) {
                  this.logChildren();
                  var9 = (new StringBuilder()).append("detected invalid position. loc invalid? ");
                  if (var4 < var2) {
                     var6 = var7;
                  } else {
                     var6 = false;
                  }

                  throw new RuntimeException(var9.append(var6).toString());
               }

               if (var4 < var2) {
                  this.logChildren();
                  throw new RuntimeException("detected invalid location");
               }
            }
         }

      }
   }

   static class AnchorInfo {
      int mCoordinate;
      boolean mLayoutFromEnd;
      OrientationHelper mOrientationHelper;
      int mPosition;
      boolean mValid;

      AnchorInfo() {
         this.reset();
      }

      void assignCoordinateFromPadding() {
         int var1;
         if (this.mLayoutFromEnd) {
            var1 = this.mOrientationHelper.getEndAfterPadding();
         } else {
            var1 = this.mOrientationHelper.getStartAfterPadding();
         }

         this.mCoordinate = var1;
      }

      public void assignFromView(View var1, int var2) {
         if (this.mLayoutFromEnd) {
            this.mCoordinate = this.mOrientationHelper.getDecoratedEnd(var1) + this.mOrientationHelper.getTotalSpaceChange();
         } else {
            this.mCoordinate = this.mOrientationHelper.getDecoratedStart(var1);
         }

         this.mPosition = var2;
      }

      public void assignFromViewAndKeepVisibleRect(View var1, int var2) {
         int var3 = this.mOrientationHelper.getTotalSpaceChange();
         if (var3 >= 0) {
            this.assignFromView(var1, var2);
         } else {
            this.mPosition = var2;
            int var4;
            int var5;
            if (this.mLayoutFromEnd) {
               var2 = this.mOrientationHelper.getEndAfterPadding() - var3 - this.mOrientationHelper.getDecoratedEnd(var1);
               this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - var2;
               if (var2 > 0) {
                  var5 = this.mOrientationHelper.getDecoratedMeasurement(var1);
                  var4 = this.mCoordinate;
                  var3 = this.mOrientationHelper.getStartAfterPadding();
                  var3 = var4 - var5 - (var3 + Math.min(this.mOrientationHelper.getDecoratedStart(var1) - var3, 0));
                  if (var3 < 0) {
                     this.mCoordinate += Math.min(var2, -var3);
                  }
               }
            } else {
               var4 = this.mOrientationHelper.getDecoratedStart(var1);
               var2 = var4 - this.mOrientationHelper.getStartAfterPadding();
               this.mCoordinate = var4;
               if (var2 > 0) {
                  var5 = this.mOrientationHelper.getDecoratedMeasurement(var1);
                  int var7 = this.mOrientationHelper.getEndAfterPadding();
                  int var6 = this.mOrientationHelper.getDecoratedEnd(var1);
                  var3 = this.mOrientationHelper.getEndAfterPadding() - Math.min(0, var7 - var3 - var6) - (var4 + var5);
                  if (var3 < 0) {
                     this.mCoordinate -= Math.min(var2, -var3);
                  }
               }
            }

         }
      }

      boolean isViewValidAsAnchor(View var1, RecyclerView.State var2) {
         RecyclerView.LayoutParams var4 = (RecyclerView.LayoutParams)var1.getLayoutParams();
         boolean var3;
         if (!var4.isItemRemoved() && var4.getViewLayoutPosition() >= 0 && var4.getViewLayoutPosition() < var2.getItemCount()) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      void reset() {
         this.mPosition = -1;
         this.mCoordinate = Integer.MIN_VALUE;
         this.mLayoutFromEnd = false;
         this.mValid = false;
      }

      public String toString() {
         return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + '}';
      }
   }

   protected static class LayoutChunkResult {
      public int mConsumed;
      public boolean mFinished;
      public boolean mFocusable;
      public boolean mIgnoreConsumed;

      void resetInternal() {
         this.mConsumed = 0;
         this.mFinished = false;
         this.mIgnoreConsumed = false;
         this.mFocusable = false;
      }
   }

   static class LayoutState {
      static final int INVALID_LAYOUT = Integer.MIN_VALUE;
      static final int ITEM_DIRECTION_HEAD = -1;
      static final int ITEM_DIRECTION_TAIL = 1;
      static final int LAYOUT_END = 1;
      static final int LAYOUT_START = -1;
      static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
      static final String TAG = "LLM#LayoutState";
      int mAvailable;
      int mCurrentPosition;
      int mExtraFillSpace = 0;
      boolean mInfinite;
      boolean mIsPreLayout = false;
      int mItemDirection;
      int mLastScrollDelta;
      int mLayoutDirection;
      int mNoRecycleSpace = 0;
      int mOffset;
      boolean mRecycle = true;
      List mScrapList = null;
      int mScrollingOffset;

      private View nextViewFromScrapList() {
         int var2 = this.mScrapList.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            View var4 = ((RecyclerView.ViewHolder)this.mScrapList.get(var1)).itemView;
            RecyclerView.LayoutParams var3 = (RecyclerView.LayoutParams)var4.getLayoutParams();
            if (!var3.isItemRemoved() && this.mCurrentPosition == var3.getViewLayoutPosition()) {
               this.assignPositionFromScrapList(var4);
               return var4;
            }
         }

         return null;
      }

      public void assignPositionFromScrapList() {
         this.assignPositionFromScrapList((View)null);
      }

      public void assignPositionFromScrapList(View var1) {
         var1 = this.nextViewInLimitedList(var1);
         if (var1 == null) {
            this.mCurrentPosition = -1;
         } else {
            this.mCurrentPosition = ((RecyclerView.LayoutParams)var1.getLayoutParams()).getViewLayoutPosition();
         }

      }

      boolean hasMore(RecyclerView.State var1) {
         int var2 = this.mCurrentPosition;
         boolean var3;
         if (var2 >= 0 && var2 < var1.getItemCount()) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var3;
      }

      void log() {
         Log.d("LLM#LayoutState", "avail:" + this.mAvailable + ", ind:" + this.mCurrentPosition + ", dir:" + this.mItemDirection + ", offset:" + this.mOffset + ", layoutDir:" + this.mLayoutDirection);
      }

      View next(RecyclerView.Recycler var1) {
         if (this.mScrapList != null) {
            return this.nextViewFromScrapList();
         } else {
            View var2 = var1.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return var2;
         }
      }

      public View nextViewInLimitedList(View var1) {
         int var6 = this.mScrapList.size();
         View var7 = null;
         int var4 = Integer.MAX_VALUE;
         int var2 = 0;

         View var8;
         while(true) {
            var8 = var7;
            if (var2 >= var6) {
               break;
            }

            View var9 = ((RecyclerView.ViewHolder)this.mScrapList.get(var2)).itemView;
            RecyclerView.LayoutParams var10 = (RecyclerView.LayoutParams)var9.getLayoutParams();
            var8 = var7;
            int var3 = var4;
            if (var9 != var1) {
               if (var10.isItemRemoved()) {
                  var8 = var7;
                  var3 = var4;
               } else {
                  int var5 = (var10.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
                  if (var5 < 0) {
                     var8 = var7;
                     var3 = var4;
                  } else {
                     var8 = var7;
                     var3 = var4;
                     if (var5 < var4) {
                        if (var5 == 0) {
                           var8 = var9;
                           break;
                        }

                        var3 = var5;
                        var8 = var9;
                     }
                  }
               }
            }

            ++var2;
            var7 = var8;
            var4 = var3;
         }

         return var8;
      }
   }

   public static class SavedState implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      boolean mAnchorLayoutFromEnd;
      int mAnchorOffset;
      int mAnchorPosition;

      public SavedState() {
      }

      SavedState(Parcel var1) {
         this.mAnchorPosition = var1.readInt();
         this.mAnchorOffset = var1.readInt();
         int var2 = var1.readInt();
         boolean var3 = true;
         if (var2 != 1) {
            var3 = false;
         }

         this.mAnchorLayoutFromEnd = var3;
      }

      public SavedState(SavedState var1) {
         this.mAnchorPosition = var1.mAnchorPosition;
         this.mAnchorOffset = var1.mAnchorOffset;
         this.mAnchorLayoutFromEnd = var1.mAnchorLayoutFromEnd;
      }

      public int describeContents() {
         return 0;
      }

      boolean hasValidAnchor() {
         boolean var1;
         if (this.mAnchorPosition >= 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      void invalidateAnchor() {
         this.mAnchorPosition = -1;
      }

      public void writeToParcel(Parcel var1, int var2) {
         var1.writeInt(this.mAnchorPosition);
         var1.writeInt(this.mAnchorOffset);
         var1.writeInt(this.mAnchorLayoutFromEnd);
      }
   }
}
