package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
   static final boolean DEBUG = false;
   @Deprecated
   public static final int GAP_HANDLING_LAZY = 1;
   public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
   public static final int GAP_HANDLING_NONE = 0;
   public static final int HORIZONTAL = 0;
   static final int INVALID_OFFSET = Integer.MIN_VALUE;
   private static final float MAX_SCROLL_FACTOR = 0.33333334F;
   private static final String TAG = "StaggeredGridLManager";
   public static final int VERTICAL = 1;
   private final AnchorInfo mAnchorInfo = new AnchorInfo(this);
   private final Runnable mCheckForGapsRunnable = new Runnable(this) {
      final StaggeredGridLayoutManager this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.checkForGaps();
      }
   };
   private int mFullSizeSpec;
   private int mGapStrategy = 2;
   private boolean mLaidOutInvalidFullSpan = false;
   private boolean mLastLayoutFromEnd;
   private boolean mLastLayoutRTL;
   private final LayoutState mLayoutState;
   LazySpanLookup mLazySpanLookup = new LazySpanLookup();
   private int mOrientation;
   private SavedState mPendingSavedState;
   int mPendingScrollPosition = -1;
   int mPendingScrollPositionOffset = Integer.MIN_VALUE;
   private int[] mPrefetchDistances;
   OrientationHelper mPrimaryOrientation;
   private BitSet mRemainingSpans;
   boolean mReverseLayout = false;
   OrientationHelper mSecondaryOrientation;
   boolean mShouldReverseLayout = false;
   private int mSizePerSpan;
   private boolean mSmoothScrollbarEnabled = true;
   private int mSpanCount = -1;
   Span[] mSpans;
   private final Rect mTmpRect = new Rect();

   public StaggeredGridLayoutManager(int var1, int var2) {
      this.mOrientation = var2;
      this.setSpanCount(var1);
      this.mLayoutState = new LayoutState();
      this.createOrientationHelpers();
   }

   public StaggeredGridLayoutManager(Context var1, AttributeSet var2, int var3, int var4) {
      RecyclerView.LayoutManager.Properties var5 = getProperties(var1, var2, var3, var4);
      this.setOrientation(var5.orientation);
      this.setSpanCount(var5.spanCount);
      this.setReverseLayout(var5.reverseLayout);
      this.mLayoutState = new LayoutState();
      this.createOrientationHelpers();
   }

   private void appendViewToAllSpans(View var1) {
      for(int var2 = this.mSpanCount - 1; var2 >= 0; --var2) {
         this.mSpans[var2].appendToSpan(var1);
      }

   }

   private void applyPendingSavedState(AnchorInfo var1) {
      if (this.mPendingSavedState.mSpanOffsetsSize > 0) {
         if (this.mPendingSavedState.mSpanOffsetsSize == this.mSpanCount) {
            for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
               this.mSpans[var2].clear();
               int var4 = this.mPendingSavedState.mSpanOffsets[var2];
               int var3 = var4;
               if (var4 != Integer.MIN_VALUE) {
                  if (this.mPendingSavedState.mAnchorLayoutFromEnd) {
                     var3 = this.mPrimaryOrientation.getEndAfterPadding();
                  } else {
                     var3 = this.mPrimaryOrientation.getStartAfterPadding();
                  }

                  var3 += var4;
               }

               this.mSpans[var2].setLine(var3);
            }
         } else {
            this.mPendingSavedState.invalidateSpanInfo();
            SavedState var5 = this.mPendingSavedState;
            var5.mAnchorPosition = var5.mVisibleAnchorPosition;
         }
      }

      this.mLastLayoutRTL = this.mPendingSavedState.mLastLayoutRTL;
      this.setReverseLayout(this.mPendingSavedState.mReverseLayout);
      this.resolveShouldLayoutReverse();
      if (this.mPendingSavedState.mAnchorPosition != -1) {
         this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
         var1.mLayoutFromEnd = this.mPendingSavedState.mAnchorLayoutFromEnd;
      } else {
         var1.mLayoutFromEnd = this.mShouldReverseLayout;
      }

      if (this.mPendingSavedState.mSpanLookupSize > 1) {
         this.mLazySpanLookup.mData = this.mPendingSavedState.mSpanLookup;
         this.mLazySpanLookup.mFullSpanItems = this.mPendingSavedState.mFullSpanItems;
      }

   }

   private void attachViewToSpans(View var1, LayoutParams var2, LayoutState var3) {
      if (var3.mLayoutDirection == 1) {
         if (var2.mFullSpan) {
            this.appendViewToAllSpans(var1);
         } else {
            var2.mSpan.appendToSpan(var1);
         }
      } else if (var2.mFullSpan) {
         this.prependViewToAllSpans(var1);
      } else {
         var2.mSpan.prependToSpan(var1);
      }

   }

   private int calculateScrollDirectionForPosition(int var1) {
      int var3 = this.getChildCount();
      byte var2 = -1;
      if (var3 == 0) {
         if (this.mShouldReverseLayout) {
            var2 = 1;
         }

         return var2;
      } else {
         boolean var4;
         if (var1 < this.getFirstChildPosition()) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (var4 == this.mShouldReverseLayout) {
            var2 = 1;
         }

         return var2;
      }
   }

   private boolean checkSpanForGap(Span var1) {
      if (this.mShouldReverseLayout) {
         if (var1.getEndLine() < this.mPrimaryOrientation.getEndAfterPadding()) {
            return var1.getLayoutParams((View)var1.mViews.get(var1.mViews.size() - 1)).mFullSpan ^ true;
         }
      } else if (var1.getStartLine() > this.mPrimaryOrientation.getStartAfterPadding()) {
         return var1.getLayoutParams((View)var1.mViews.get(0)).mFullSpan ^ true;
      }

      return false;
   }

   private int computeScrollExtent(RecyclerView.State var1) {
      return this.getChildCount() == 0 ? 0 : ScrollbarHelper.computeScrollExtent(var1, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(this.mSmoothScrollbarEnabled ^ true), this.findFirstVisibleItemClosestToEnd(this.mSmoothScrollbarEnabled ^ true), this, this.mSmoothScrollbarEnabled);
   }

   private int computeScrollOffset(RecyclerView.State var1) {
      return this.getChildCount() == 0 ? 0 : ScrollbarHelper.computeScrollOffset(var1, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(this.mSmoothScrollbarEnabled ^ true), this.findFirstVisibleItemClosestToEnd(this.mSmoothScrollbarEnabled ^ true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
   }

   private int computeScrollRange(RecyclerView.State var1) {
      return this.getChildCount() == 0 ? 0 : ScrollbarHelper.computeScrollRange(var1, this.mPrimaryOrientation, this.findFirstVisibleItemClosestToStart(this.mSmoothScrollbarEnabled ^ true), this.findFirstVisibleItemClosestToEnd(this.mSmoothScrollbarEnabled ^ true), this, this.mSmoothScrollbarEnabled);
   }

   private int convertFocusDirectionToLayoutDirection(int var1) {
      int var2 = -1;
      byte var4 = 1;
      byte var3 = 1;
      if (var1 != 1) {
         if (var1 != 2) {
            if (var1 != 17) {
               if (var1 != 33) {
                  if (var1 != 66) {
                     if (var1 != 130) {
                        return Integer.MIN_VALUE;
                     } else {
                        if (this.mOrientation == 1) {
                           var1 = var3;
                        } else {
                           var1 = Integer.MIN_VALUE;
                        }

                        return var1;
                     }
                  } else {
                     if (this.mOrientation == 0) {
                        var1 = var4;
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

   private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int var1) {
      LazySpanLookup.FullSpanItem var3 = new LazySpanLookup.FullSpanItem();
      var3.mGapPerSpan = new int[this.mSpanCount];

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         var3.mGapPerSpan[var2] = var1 - this.mSpans[var2].getEndLine(var1);
      }

      return var3;
   }

   private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int var1) {
      LazySpanLookup.FullSpanItem var3 = new LazySpanLookup.FullSpanItem();
      var3.mGapPerSpan = new int[this.mSpanCount];

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         var3.mGapPerSpan[var2] = this.mSpans[var2].getStartLine(var1) - var1;
      }

      return var3;
   }

   private void createOrientationHelpers() {
      this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
      this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
   }

   private int fill(RecyclerView.Recycler var1, LayoutState var2, RecyclerView.State var3) {
      BitSet var12 = this.mRemainingSpans;
      int var4 = this.mSpanCount;
      var12.set(0, var4, true);
      if (this.mLayoutState.mInfinite) {
         if (var2.mLayoutDirection == 1) {
            var4 = Integer.MAX_VALUE;
         } else {
            var4 = Integer.MIN_VALUE;
         }
      } else if (var2.mLayoutDirection == 1) {
         var4 = var2.mEndLine + var2.mAvailable;
      } else {
         var4 = var2.mStartLine - var2.mAvailable;
      }

      this.updateAllRemainingSpans(var2.mLayoutDirection, var4);
      int var6;
      if (this.mShouldReverseLayout) {
         var6 = this.mPrimaryOrientation.getEndAfterPadding();
      } else {
         var6 = this.mPrimaryOrientation.getStartAfterPadding();
      }

      boolean var5;
      for(var5 = false; var2.hasMore(var3) && (this.mLayoutState.mInfinite || !this.mRemainingSpans.isEmpty()); var5 = true) {
         View var14 = var2.next(var1);
         LayoutParams var13 = (LayoutParams)var14.getLayoutParams();
         int var10 = var13.getViewLayoutPosition();
         int var16 = this.mLazySpanLookup.getSpan(var10);
         boolean var9;
         if (var16 == -1) {
            var9 = true;
         } else {
            var9 = false;
         }

         Span var18;
         if (var9) {
            if (var13.mFullSpan) {
               var18 = this.mSpans[0];
            } else {
               var18 = this.getNextSpan(var2);
            }

            this.mLazySpanLookup.setSpan(var10, var18);
         } else {
            var18 = this.mSpans[var16];
         }

         var13.mSpan = var18;
         if (var2.mLayoutDirection == 1) {
            this.addView(var14);
         } else {
            this.addView(var14, 0);
         }

         this.measureChildWithDecorationsAndMargin(var14, var13, false);
         int var7;
         int var8;
         LazySpanLookup.FullSpanItem var15;
         if (var2.mLayoutDirection == 1) {
            if (var13.mFullSpan) {
               var16 = this.getMaxEnd(var6);
            } else {
               var16 = var18.getEndLine(var6);
            }

            var7 = this.mPrimaryOrientation.getDecoratedMeasurement(var14);
            if (var9 && var13.mFullSpan) {
               var15 = this.createFullSpanItemFromEnd(var16);
               var15.mGapDir = -1;
               var15.mPosition = var10;
               this.mLazySpanLookup.addFullSpanItem(var15);
            }

            var7 += var16;
            var8 = var16;
         } else {
            if (var13.mFullSpan) {
               var16 = this.getMinStart(var6);
            } else {
               var16 = var18.getStartLine(var6);
            }

            var8 = var16 - this.mPrimaryOrientation.getDecoratedMeasurement(var14);
            if (var9 && var13.mFullSpan) {
               var15 = this.createFullSpanItemFromStart(var16);
               var15.mGapDir = 1;
               var15.mPosition = var10;
               this.mLazySpanLookup.addFullSpanItem(var15);
            }

            var7 = var16;
         }

         if (var13.mFullSpan && var2.mItemDirection == -1) {
            if (var9) {
               this.mLaidOutInvalidFullSpan = true;
            } else {
               boolean var11;
               if (var2.mLayoutDirection == 1) {
                  var11 = this.areAllEndsEqual();
               } else {
                  var11 = this.areAllStartsEqual();
               }

               if (var11 ^ true) {
                  var15 = this.mLazySpanLookup.getFullSpanItem(var10);
                  if (var15 != null) {
                     var15.mHasUnwantedGapAfter = true;
                  }

                  this.mLaidOutInvalidFullSpan = true;
               }
            }
         }

         this.attachViewToSpans(var14, var13, var2);
         if (this.isLayoutRTL() && this.mOrientation == 1) {
            if (var13.mFullSpan) {
               var16 = this.mSecondaryOrientation.getEndAfterPadding();
            } else {
               var16 = this.mSecondaryOrientation.getEndAfterPadding() - (this.mSpanCount - 1 - var18.mIndex) * this.mSizePerSpan;
            }

            var10 = this.mSecondaryOrientation.getDecoratedMeasurement(var14);
            var10 = var16 - var10;
            var16 = var16;
         } else {
            if (var13.mFullSpan) {
               var16 = this.mSecondaryOrientation.getStartAfterPadding();
            } else {
               var16 = var18.mIndex * this.mSizePerSpan + this.mSecondaryOrientation.getStartAfterPadding();
            }

            var10 = this.mSecondaryOrientation.getDecoratedMeasurement(var14);
            int var17 = var16;
            var16 += var10;
            var10 = var17;
         }

         if (this.mOrientation == 1) {
            this.layoutDecoratedWithMargins(var14, var10, var8, var16, var7);
         } else {
            this.layoutDecoratedWithMargins(var14, var8, var10, var7, var16);
         }

         if (var13.mFullSpan) {
            this.updateAllRemainingSpans(this.mLayoutState.mLayoutDirection, var4);
         } else {
            this.updateRemainingSpans(var18, this.mLayoutState.mLayoutDirection, var4);
         }

         this.recycle(var1, this.mLayoutState);
         if (this.mLayoutState.mStopInFocusable && var14.hasFocusable()) {
            if (var13.mFullSpan) {
               this.mRemainingSpans.clear();
            } else {
               this.mRemainingSpans.set(var18.mIndex, false);
            }
         }
      }

      if (!var5) {
         this.recycle(var1, this.mLayoutState);
      }

      if (this.mLayoutState.mLayoutDirection == -1) {
         var4 = this.getMinStart(this.mPrimaryOrientation.getStartAfterPadding());
         var4 = this.mPrimaryOrientation.getStartAfterPadding() - var4;
      } else {
         var4 = this.getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
      }

      if (var4 > 0) {
         var4 = Math.min(var2.mAvailable, var4);
      } else {
         var4 = 0;
      }

      return var4;
   }

   private int findFirstReferenceChildPosition(int var1) {
      int var3 = this.getChildCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         int var4 = this.getPosition(this.getChildAt(var2));
         if (var4 >= 0 && var4 < var1) {
            return var4;
         }
      }

      return 0;
   }

   private int findLastReferenceChildPosition(int var1) {
      for(int var2 = this.getChildCount() - 1; var2 >= 0; --var2) {
         int var3 = this.getPosition(this.getChildAt(var2));
         if (var3 >= 0 && var3 < var1) {
            return var3;
         }
      }

      return 0;
   }

   private void fixEndGap(RecyclerView.Recycler var1, RecyclerView.State var2, boolean var3) {
      int var4 = this.getMaxEnd(Integer.MIN_VALUE);
      if (var4 != Integer.MIN_VALUE) {
         var4 = this.mPrimaryOrientation.getEndAfterPadding() - var4;
         if (var4 > 0) {
            var4 -= -this.scrollBy(-var4, var1, var2);
            if (var3 && var4 > 0) {
               this.mPrimaryOrientation.offsetChildren(var4);
            }
         }

      }
   }

   private void fixStartGap(RecyclerView.Recycler var1, RecyclerView.State var2, boolean var3) {
      int var4 = this.getMinStart(Integer.MAX_VALUE);
      if (var4 != Integer.MAX_VALUE) {
         var4 -= this.mPrimaryOrientation.getStartAfterPadding();
         if (var4 > 0) {
            var4 -= this.scrollBy(var4, var1, var2);
            if (var3 && var4 > 0) {
               this.mPrimaryOrientation.offsetChildren(-var4);
            }
         }

      }
   }

   private int getMaxEnd(int var1) {
      int var3 = this.mSpans[0].getEndLine(var1);

      int var4;
      for(int var2 = 1; var2 < this.mSpanCount; var3 = var4) {
         int var5 = this.mSpans[var2].getEndLine(var1);
         var4 = var3;
         if (var5 > var3) {
            var4 = var5;
         }

         ++var2;
      }

      return var3;
   }

   private int getMaxStart(int var1) {
      int var4 = this.mSpans[0].getStartLine(var1);

      int var3;
      for(int var2 = 1; var2 < this.mSpanCount; var4 = var3) {
         int var5 = this.mSpans[var2].getStartLine(var1);
         var3 = var4;
         if (var5 > var4) {
            var3 = var5;
         }

         ++var2;
      }

      return var4;
   }

   private int getMinEnd(int var1) {
      int var3 = this.mSpans[0].getEndLine(var1);

      int var4;
      for(int var2 = 1; var2 < this.mSpanCount; var3 = var4) {
         int var5 = this.mSpans[var2].getEndLine(var1);
         var4 = var3;
         if (var5 < var3) {
            var4 = var5;
         }

         ++var2;
      }

      return var3;
   }

   private int getMinStart(int var1) {
      int var3 = this.mSpans[0].getStartLine(var1);

      int var4;
      for(int var2 = 1; var2 < this.mSpanCount; var3 = var4) {
         int var5 = this.mSpans[var2].getStartLine(var1);
         var4 = var3;
         if (var5 < var3) {
            var4 = var5;
         }

         ++var2;
      }

      return var3;
   }

   private Span getNextSpan(LayoutState var1) {
      boolean var9 = this.preferLastSpan(var1.mLayoutDirection);
      int var4 = -1;
      int var2;
      byte var3;
      if (var9) {
         var2 = this.mSpanCount - 1;
         var3 = -1;
      } else {
         var2 = 0;
         var4 = this.mSpanCount;
         var3 = 1;
      }

      int var5 = var1.mLayoutDirection;
      Span var11 = null;
      Span var10 = null;
      int var6;
      int var7;
      int var8;
      if (var5 == 1) {
         var6 = Integer.MAX_VALUE;
         var8 = this.mPrimaryOrientation.getStartAfterPadding();

         for(var11 = var10; var2 != var4; var6 = var5) {
            var10 = this.mSpans[var2];
            var7 = var10.getEndLine(var8);
            var5 = var6;
            if (var7 < var6) {
               var11 = var10;
               var5 = var7;
            }

            var2 += var3;
         }

         return var11;
      } else {
         var6 = Integer.MIN_VALUE;

         for(var8 = this.mPrimaryOrientation.getEndAfterPadding(); var2 != var4; var6 = var5) {
            var10 = this.mSpans[var2];
            var7 = var10.getStartLine(var8);
            var5 = var6;
            if (var7 > var6) {
               var11 = var10;
               var5 = var7;
            }

            var2 += var3;
         }

         return var11;
      }
   }

   private void handleUpdate(int var1, int var2, int var3) {
      int var5;
      if (this.mShouldReverseLayout) {
         var5 = this.getLastChildPosition();
      } else {
         var5 = this.getFirstChildPosition();
      }

      int var4;
      int var6;
      label43: {
         if (var3 == 8) {
            if (var1 >= var2) {
               var4 = var1 + 1;
               var6 = var2;
               break label43;
            }

            var4 = var2 + 1;
         } else {
            var4 = var1 + var2;
         }

         var6 = var1;
      }

      this.mLazySpanLookup.invalidateAfter(var6);
      if (var3 != 1) {
         if (var3 != 2) {
            if (var3 == 8) {
               this.mLazySpanLookup.offsetForRemoval(var1, 1);
               this.mLazySpanLookup.offsetForAddition(var2, 1);
            }
         } else {
            this.mLazySpanLookup.offsetForRemoval(var1, var2);
         }
      } else {
         this.mLazySpanLookup.offsetForAddition(var1, var2);
      }

      if (var4 > var5) {
         if (this.mShouldReverseLayout) {
            var1 = this.getFirstChildPosition();
         } else {
            var1 = this.getLastChildPosition();
         }

         if (var6 <= var1) {
            this.requestLayout();
         }

      }
   }

   private void measureChildWithDecorationsAndMargin(View var1, int var2, int var3, boolean var4) {
      this.calculateItemDecorationsForChild(var1, this.mTmpRect);
      LayoutParams var5 = (LayoutParams)var1.getLayoutParams();
      var2 = this.updateSpecWithExtra(var2, var5.leftMargin + this.mTmpRect.left, var5.rightMargin + this.mTmpRect.right);
      var3 = this.updateSpecWithExtra(var3, var5.topMargin + this.mTmpRect.top, var5.bottomMargin + this.mTmpRect.bottom);
      if (var4) {
         var4 = this.shouldReMeasureChild(var1, var2, var3, var5);
      } else {
         var4 = this.shouldMeasureChild(var1, var2, var3, var5);
      }

      if (var4) {
         var1.measure(var2, var3);
      }

   }

   private void measureChildWithDecorationsAndMargin(View var1, LayoutParams var2, boolean var3) {
      if (var2.mFullSpan) {
         if (this.mOrientation == 1) {
            this.measureChildWithDecorationsAndMargin(var1, this.mFullSizeSpec, getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom(), var2.height, true), var3);
         } else {
            this.measureChildWithDecorationsAndMargin(var1, getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight(), var2.width, true), this.mFullSizeSpec, var3);
         }
      } else if (this.mOrientation == 1) {
         this.measureChildWithDecorationsAndMargin(var1, getChildMeasureSpec(this.mSizePerSpan, this.getWidthMode(), 0, var2.width, false), getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom(), var2.height, true), var3);
      } else {
         this.measureChildWithDecorationsAndMargin(var1, getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight(), var2.width, true), getChildMeasureSpec(this.mSizePerSpan, this.getHeightMode(), 0, var2.height, false), var3);
      }

   }

   private void onLayoutChildren(RecyclerView.Recycler var1, RecyclerView.State var2, boolean var3) {
      AnchorInfo var7 = this.mAnchorInfo;
      if ((this.mPendingSavedState != null || this.mPendingScrollPosition != -1) && var2.getItemCount() == 0) {
         this.removeAndRecycleAllViews(var1);
         var7.reset();
      } else {
         boolean var6 = var7.mValid;
         boolean var5 = true;
         boolean var4;
         if (var6 && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) {
            var4 = false;
         } else {
            var4 = true;
         }

         if (var4) {
            var7.reset();
            if (this.mPendingSavedState != null) {
               this.applyPendingSavedState(var7);
            } else {
               this.resolveShouldLayoutReverse();
               var7.mLayoutFromEnd = this.mShouldReverseLayout;
            }

            this.updateAnchorInfoForLayout(var2, var7);
            var7.mValid = true;
         }

         if (this.mPendingSavedState == null && this.mPendingScrollPosition == -1 && (var7.mLayoutFromEnd != this.mLastLayoutFromEnd || this.isLayoutRTL() != this.mLastLayoutRTL)) {
            this.mLazySpanLookup.clear();
            var7.mInvalidateOffsets = true;
         }

         if (this.getChildCount() > 0) {
            SavedState var8 = this.mPendingSavedState;
            if (var8 == null || var8.mSpanOffsetsSize < 1) {
               int var9;
               if (var7.mInvalidateOffsets) {
                  for(var9 = 0; var9 < this.mSpanCount; ++var9) {
                     this.mSpans[var9].clear();
                     if (var7.mOffset != Integer.MIN_VALUE) {
                        this.mSpans[var9].setLine(var7.mOffset);
                     }
                  }
               } else if (!var4 && this.mAnchorInfo.mSpanReferenceLines != null) {
                  for(var9 = 0; var9 < this.mSpanCount; ++var9) {
                     Span var10 = this.mSpans[var9];
                     var10.clear();
                     var10.setLine(this.mAnchorInfo.mSpanReferenceLines[var9]);
                  }
               } else {
                  for(var9 = 0; var9 < this.mSpanCount; ++var9) {
                     this.mSpans[var9].cacheReferenceLineAndClear(this.mShouldReverseLayout, var7.mOffset);
                  }

                  this.mAnchorInfo.saveSpanReferenceLines(this.mSpans);
               }
            }
         }

         this.detachAndScrapAttachedViews(var1);
         this.mLayoutState.mRecycle = false;
         this.mLaidOutInvalidFullSpan = false;
         this.updateMeasureSpecs(this.mSecondaryOrientation.getTotalSpace());
         this.updateLayoutState(var7.mPosition, var2);
         if (var7.mLayoutFromEnd) {
            this.setLayoutStateDirection(-1);
            this.fill(var1, this.mLayoutState, var2);
            this.setLayoutStateDirection(1);
            this.mLayoutState.mCurrentPosition = var7.mPosition + this.mLayoutState.mItemDirection;
            this.fill(var1, this.mLayoutState, var2);
         } else {
            this.setLayoutStateDirection(1);
            this.fill(var1, this.mLayoutState, var2);
            this.setLayoutStateDirection(-1);
            this.mLayoutState.mCurrentPosition = var7.mPosition + this.mLayoutState.mItemDirection;
            this.fill(var1, this.mLayoutState, var2);
         }

         this.repositionToWrapContentIfNecessary();
         if (this.getChildCount() > 0) {
            if (this.mShouldReverseLayout) {
               this.fixEndGap(var1, var2, true);
               this.fixStartGap(var1, var2, false);
            } else {
               this.fixStartGap(var1, var2, true);
               this.fixEndGap(var1, var2, false);
            }
         }

         label96: {
            if (var3 && !var2.isPreLayout()) {
               if (this.mGapStrategy == 0 || this.getChildCount() <= 0 || !this.mLaidOutInvalidFullSpan && this.hasGapsToFix() == null) {
                  var4 = false;
               } else {
                  var4 = true;
               }

               if (var4) {
                  this.removeCallbacks(this.mCheckForGapsRunnable);
                  if (this.checkForGaps()) {
                     var4 = var5;
                     break label96;
                  }
               }
            }

            var4 = false;
         }

         if (var2.isPreLayout()) {
            this.mAnchorInfo.reset();
         }

         this.mLastLayoutFromEnd = var7.mLayoutFromEnd;
         this.mLastLayoutRTL = this.isLayoutRTL();
         if (var4) {
            this.mAnchorInfo.reset();
            this.onLayoutChildren(var1, var2, false);
         }

      }
   }

   private boolean preferLastSpan(int var1) {
      int var2 = this.mOrientation;
      boolean var4 = true;
      boolean var5 = true;
      boolean var3;
      if (var2 == 0) {
         if (var1 == -1) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3 != this.mShouldReverseLayout) {
            var3 = var5;
         } else {
            var3 = false;
         }

         return var3;
      } else {
         if (var1 == -1) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3 == this.mShouldReverseLayout) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3 == this.isLayoutRTL()) {
            var3 = var4;
         } else {
            var3 = false;
         }

         return var3;
      }
   }

   private void prependViewToAllSpans(View var1) {
      for(int var2 = this.mSpanCount - 1; var2 >= 0; --var2) {
         this.mSpans[var2].prependToSpan(var1);
      }

   }

   private void recycle(RecyclerView.Recycler var1, LayoutState var2) {
      if (var2.mRecycle && !var2.mInfinite) {
         if (var2.mAvailable == 0) {
            if (var2.mLayoutDirection == -1) {
               this.recycleFromEnd(var1, var2.mEndLine);
            } else {
               this.recycleFromStart(var1, var2.mStartLine);
            }
         } else {
            int var3;
            if (var2.mLayoutDirection == -1) {
               var3 = var2.mStartLine - this.getMaxStart(var2.mStartLine);
               if (var3 < 0) {
                  var3 = var2.mEndLine;
               } else {
                  var3 = var2.mEndLine - Math.min(var3, var2.mAvailable);
               }

               this.recycleFromEnd(var1, var3);
            } else {
               int var4 = this.getMinEnd(var2.mEndLine) - var2.mEndLine;
               if (var4 < 0) {
                  var3 = var2.mStartLine;
               } else {
                  var3 = var2.mStartLine;
                  var3 += Math.min(var4, var2.mAvailable);
               }

               this.recycleFromStart(var1, var3);
            }
         }
      }

   }

   private void recycleFromEnd(RecyclerView.Recycler var1, int var2) {
      for(int var3 = this.getChildCount() - 1; var3 >= 0; --var3) {
         View var8 = this.getChildAt(var3);
         if (this.mPrimaryOrientation.getDecoratedStart(var8) < var2 || this.mPrimaryOrientation.getTransformedStartWithDecoration(var8) < var2) {
            break;
         }

         LayoutParams var7 = (LayoutParams)var8.getLayoutParams();
         if (var7.mFullSpan) {
            byte var6 = 0;
            int var4 = 0;

            while(true) {
               int var5 = var6;
               if (var4 >= this.mSpanCount) {
                  while(var5 < this.mSpanCount) {
                     this.mSpans[var5].popEnd();
                     ++var5;
                  }
                  break;
               }

               if (this.mSpans[var4].mViews.size() == 1) {
                  return;
               }

               ++var4;
            }
         } else {
            if (var7.mSpan.mViews.size() == 1) {
               return;
            }

            var7.mSpan.popEnd();
         }

         this.removeAndRecycleView(var8, var1);
      }

   }

   private void recycleFromStart(RecyclerView.Recycler var1, int var2) {
      while(true) {
         if (this.getChildCount() > 0) {
            byte var5 = 0;
            View var6 = this.getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(var6) <= var2 && this.mPrimaryOrientation.getTransformedEndWithDecoration(var6) <= var2) {
               LayoutParams var7 = (LayoutParams)var6.getLayoutParams();
               if (var7.mFullSpan) {
                  int var3 = 0;

                  while(true) {
                     int var4 = var5;
                     if (var3 >= this.mSpanCount) {
                        while(var4 < this.mSpanCount) {
                           this.mSpans[var4].popStart();
                           ++var4;
                        }
                        break;
                     }

                     if (this.mSpans[var3].mViews.size() == 1) {
                        return;
                     }

                     ++var3;
                  }
               } else {
                  if (var7.mSpan.mViews.size() == 1) {
                     return;
                  }

                  var7.mSpan.popStart();
               }

               this.removeAndRecycleView(var6, var1);
               continue;
            }
         }

         return;
      }
   }

   private void repositionToWrapContentIfNecessary() {
      if (this.mSecondaryOrientation.getMode() != 1073741824) {
         float var1 = 0.0F;
         int var7 = this.getChildCount();
         int var5 = 0;

         int var4;
         View var9;
         for(var4 = 0; var4 < var7; ++var4) {
            var9 = this.getChildAt(var4);
            float var3 = (float)this.mSecondaryOrientation.getDecoratedMeasurement(var9);
            if (!(var3 < var1)) {
               float var2 = var3;
               if (((LayoutParams)var9.getLayoutParams()).isFullSpan()) {
                  var2 = var3 * 1.0F / (float)this.mSpanCount;
               }

               var1 = Math.max(var1, var2);
            }
         }

         int var8 = this.mSizePerSpan;
         int var6 = Math.round(var1 * (float)this.mSpanCount);
         var4 = var6;
         if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
            var4 = Math.min(var6, this.mSecondaryOrientation.getTotalSpace());
         }

         this.updateMeasureSpecs(var4);
         var4 = var5;
         if (this.mSizePerSpan != var8) {
            for(; var4 < var7; ++var4) {
               var9 = this.getChildAt(var4);
               LayoutParams var10 = (LayoutParams)var9.getLayoutParams();
               if (!var10.mFullSpan) {
                  if (this.isLayoutRTL() && this.mOrientation == 1) {
                     var9.offsetLeftAndRight(-(this.mSpanCount - 1 - var10.mSpan.mIndex) * this.mSizePerSpan - -(this.mSpanCount - 1 - var10.mSpan.mIndex) * var8);
                  } else {
                     var5 = var10.mSpan.mIndex * this.mSizePerSpan;
                     var6 = var10.mSpan.mIndex * var8;
                     if (this.mOrientation == 1) {
                        var9.offsetLeftAndRight(var5 - var6);
                     } else {
                        var9.offsetTopAndBottom(var5 - var6);
                     }
                  }
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

   private void setLayoutStateDirection(int var1) {
      this.mLayoutState.mLayoutDirection = var1;
      LayoutState var5 = this.mLayoutState;
      boolean var4 = this.mShouldReverseLayout;
      byte var2 = 1;
      boolean var3;
      if (var1 == -1) {
         var3 = true;
      } else {
         var3 = false;
      }

      byte var6;
      if (var4 == var3) {
         var6 = var2;
      } else {
         var6 = -1;
      }

      var5.mItemDirection = var6;
   }

   private void updateAllRemainingSpans(int var1, int var2) {
      for(int var3 = 0; var3 < this.mSpanCount; ++var3) {
         if (!this.mSpans[var3].mViews.isEmpty()) {
            this.updateRemainingSpans(this.mSpans[var3], var1, var2);
         }
      }

   }

   private boolean updateAnchorFromChildren(RecyclerView.State var1, AnchorInfo var2) {
      int var3;
      if (this.mLastLayoutFromEnd) {
         var3 = this.findLastReferenceChildPosition(var1.getItemCount());
      } else {
         var3 = this.findFirstReferenceChildPosition(var1.getItemCount());
      }

      var2.mPosition = var3;
      var2.mOffset = Integer.MIN_VALUE;
      return true;
   }

   private void updateLayoutState(int var1, RecyclerView.State var2) {
      int var3;
      boolean var4;
      boolean var5;
      label31: {
         LayoutState var7 = this.mLayoutState;
         var5 = false;
         var7.mAvailable = 0;
         this.mLayoutState.mCurrentPosition = var1;
         if (this.isSmoothScrolling()) {
            var3 = var2.getTargetScrollPosition();
            if (var3 != -1) {
               boolean var6 = this.mShouldReverseLayout;
               if (var3 < var1) {
                  var4 = true;
               } else {
                  var4 = false;
               }

               if (var6 == var4) {
                  var1 = this.mPrimaryOrientation.getTotalSpace();
                  var3 = 0;
               } else {
                  var3 = this.mPrimaryOrientation.getTotalSpace();
                  var1 = 0;
               }
               break label31;
            }
         }

         var1 = 0;
         var3 = 0;
      }

      if (this.getClipToPadding()) {
         this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - var3;
         this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + var1;
      } else {
         this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + var1;
         this.mLayoutState.mStartLine = -var3;
      }

      this.mLayoutState.mStopInFocusable = false;
      this.mLayoutState.mRecycle = true;
      LayoutState var8 = this.mLayoutState;
      var4 = var5;
      if (this.mPrimaryOrientation.getMode() == 0) {
         var4 = var5;
         if (this.mPrimaryOrientation.getEnd() == 0) {
            var4 = true;
         }
      }

      var8.mInfinite = var4;
   }

   private void updateRemainingSpans(Span var1, int var2, int var3) {
      int var4 = var1.getDeletedSize();
      if (var2 == -1) {
         if (var1.getStartLine() + var4 <= var3) {
            this.mRemainingSpans.set(var1.mIndex, false);
         }
      } else if (var1.getEndLine() - var4 >= var3) {
         this.mRemainingSpans.set(var1.mIndex, false);
      }

   }

   private int updateSpecWithExtra(int var1, int var2, int var3) {
      if (var2 == 0 && var3 == 0) {
         return var1;
      } else {
         int var4 = MeasureSpec.getMode(var1);
         return var4 != Integer.MIN_VALUE && var4 != 1073741824 ? var1 : MeasureSpec.makeMeasureSpec(Math.max(0, MeasureSpec.getSize(var1) - var2 - var3), var4);
      }
   }

   boolean areAllEndsEqual() {
      int var2 = this.mSpans[0].getEndLine(Integer.MIN_VALUE);

      for(int var1 = 1; var1 < this.mSpanCount; ++var1) {
         if (this.mSpans[var1].getEndLine(Integer.MIN_VALUE) != var2) {
            return false;
         }
      }

      return true;
   }

   boolean areAllStartsEqual() {
      int var2 = this.mSpans[0].getStartLine(Integer.MIN_VALUE);

      for(int var1 = 1; var1 < this.mSpanCount; ++var1) {
         if (this.mSpans[var1].getStartLine(Integer.MIN_VALUE) != var2) {
            return false;
         }
      }

      return true;
   }

   public void assertNotInLayoutOrScroll(String var1) {
      if (this.mPendingSavedState == null) {
         super.assertNotInLayoutOrScroll(var1);
      }

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

   boolean checkForGaps() {
      if (this.getChildCount() != 0 && this.mGapStrategy != 0 && this.isAttachedToWindow()) {
         int var1;
         int var2;
         if (this.mShouldReverseLayout) {
            var1 = this.getLastChildPosition();
            var2 = this.getFirstChildPosition();
         } else {
            var1 = this.getFirstChildPosition();
            var2 = this.getLastChildPosition();
         }

         if (var1 == 0 && this.hasGapsToFix() != null) {
            this.mLazySpanLookup.clear();
            this.requestSimpleAnimationsInNextLayout();
            this.requestLayout();
            return true;
         } else if (!this.mLaidOutInvalidFullSpan) {
            return false;
         } else {
            byte var3;
            if (this.mShouldReverseLayout) {
               var3 = -1;
            } else {
               var3 = 1;
            }

            LazySpanLookup var4 = this.mLazySpanLookup;
            ++var2;
            LazySpanLookup.FullSpanItem var5 = var4.getFirstFullSpanItemInRange(var1, var2, var3, true);
            if (var5 == null) {
               this.mLaidOutInvalidFullSpan = false;
               this.mLazySpanLookup.forceInvalidateAfter(var2);
               return false;
            } else {
               LazySpanLookup.FullSpanItem var6 = this.mLazySpanLookup.getFirstFullSpanItemInRange(var1, var5.mPosition, var3 * -1, true);
               if (var6 == null) {
                  this.mLazySpanLookup.forceInvalidateAfter(var5.mPosition);
               } else {
                  this.mLazySpanLookup.forceInvalidateAfter(var6.mPosition + 1);
               }

               this.requestSimpleAnimationsInNextLayout();
               this.requestLayout();
               return true;
            }
         }
      } else {
         return false;
      }
   }

   public boolean checkLayoutParams(RecyclerView.LayoutParams var1) {
      return var1 instanceof LayoutParams;
   }

   public void collectAdjacentPrefetchPositions(int var1, int var2, RecyclerView.State var3, RecyclerView.LayoutManager.LayoutPrefetchRegistry var4) {
      if (this.mOrientation != 0) {
         var1 = var2;
      }

      if (this.getChildCount() != 0 && var1 != 0) {
         this.prepareLayoutStateForDelta(var1, var3);
         int[] var8 = this.mPrefetchDistances;
         if (var8 == null || var8.length < this.mSpanCount) {
            this.mPrefetchDistances = new int[this.mSpanCount];
         }

         byte var7 = 0;
         var2 = 0;

         int var5;
         for(var1 = 0; var2 < this.mSpanCount; var1 = var5) {
            int var6;
            if (this.mLayoutState.mItemDirection == -1) {
               var6 = this.mLayoutState.mStartLine;
               var5 = this.mSpans[var2].getStartLine(this.mLayoutState.mStartLine);
            } else {
               var6 = this.mSpans[var2].getEndLine(this.mLayoutState.mEndLine);
               var5 = this.mLayoutState.mEndLine;
            }

            var6 -= var5;
            var5 = var1;
            if (var6 >= 0) {
               this.mPrefetchDistances[var1] = var6;
               var5 = var1 + 1;
            }

            ++var2;
         }

         Arrays.sort(this.mPrefetchDistances, 0, var1);

         for(var2 = var7; var2 < var1 && this.mLayoutState.hasMore(var3); ++var2) {
            var4.addPosition(this.mLayoutState.mCurrentPosition, this.mPrefetchDistances[var2]);
            LayoutState var9 = this.mLayoutState;
            var9.mCurrentPosition += this.mLayoutState.mItemDirection;
         }
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
      var1 = this.calculateScrollDirectionForPosition(var1);
      PointF var2 = new PointF();
      if (var1 == 0) {
         return null;
      } else {
         if (this.mOrientation == 0) {
            var2.x = (float)var1;
            var2.y = 0.0F;
         } else {
            var2.x = 0.0F;
            var2.y = (float)var1;
         }

         return var2;
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

   public int[] findFirstCompletelyVisibleItemPositions(int[] var1) {
      if (var1 == null) {
         var1 = new int[this.mSpanCount];
      } else if (var1.length < this.mSpanCount) {
         throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + var1.length);
      }

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         var1[var2] = this.mSpans[var2].findFirstCompletelyVisibleItemPosition();
      }

      return var1;
   }

   View findFirstVisibleItemClosestToEnd(boolean var1) {
      int var4 = this.mPrimaryOrientation.getStartAfterPadding();
      int var3 = this.mPrimaryOrientation.getEndAfterPadding();
      int var2 = this.getChildCount() - 1;

      View var7;
      View var8;
      for(var8 = null; var2 >= 0; var8 = var7) {
         View var9 = this.getChildAt(var2);
         int var6 = this.mPrimaryOrientation.getDecoratedStart(var9);
         int var5 = this.mPrimaryOrientation.getDecoratedEnd(var9);
         var7 = var8;
         if (var5 > var4) {
            if (var6 >= var3) {
               var7 = var8;
            } else {
               if (var5 <= var3 || !var1) {
                  return var9;
               }

               var7 = var8;
               if (var8 == null) {
                  var7 = var9;
               }
            }
         }

         --var2;
      }

      return var8;
   }

   View findFirstVisibleItemClosestToStart(boolean var1) {
      int var3 = this.mPrimaryOrientation.getStartAfterPadding();
      int var4 = this.mPrimaryOrientation.getEndAfterPadding();
      int var5 = this.getChildCount();
      View var8 = null;

      View var7;
      for(int var2 = 0; var2 < var5; var8 = var7) {
         View var9 = this.getChildAt(var2);
         int var6 = this.mPrimaryOrientation.getDecoratedStart(var9);
         var7 = var8;
         if (this.mPrimaryOrientation.getDecoratedEnd(var9) > var3) {
            if (var6 >= var4) {
               var7 = var8;
            } else {
               if (var6 >= var3 || !var1) {
                  return var9;
               }

               var7 = var8;
               if (var8 == null) {
                  var7 = var9;
               }
            }
         }

         ++var2;
      }

      return var8;
   }

   int findFirstVisibleItemPositionInt() {
      View var2;
      if (this.mShouldReverseLayout) {
         var2 = this.findFirstVisibleItemClosestToEnd(true);
      } else {
         var2 = this.findFirstVisibleItemClosestToStart(true);
      }

      int var1;
      if (var2 == null) {
         var1 = -1;
      } else {
         var1 = this.getPosition(var2);
      }

      return var1;
   }

   public int[] findFirstVisibleItemPositions(int[] var1) {
      if (var1 == null) {
         var1 = new int[this.mSpanCount];
      } else if (var1.length < this.mSpanCount) {
         throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + var1.length);
      }

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         var1[var2] = this.mSpans[var2].findFirstVisibleItemPosition();
      }

      return var1;
   }

   public int[] findLastCompletelyVisibleItemPositions(int[] var1) {
      if (var1 == null) {
         var1 = new int[this.mSpanCount];
      } else if (var1.length < this.mSpanCount) {
         throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + var1.length);
      }

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         var1[var2] = this.mSpans[var2].findLastCompletelyVisibleItemPosition();
      }

      return var1;
   }

   public int[] findLastVisibleItemPositions(int[] var1) {
      if (var1 == null) {
         var1 = new int[this.mSpanCount];
      } else if (var1.length < this.mSpanCount) {
         throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + var1.length);
      }

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         var1[var2] = this.mSpans[var2].findLastVisibleItemPosition();
      }

      return var1;
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

   int getFirstChildPosition() {
      int var2 = this.getChildCount();
      int var1 = 0;
      if (var2 != 0) {
         var1 = this.getPosition(this.getChildAt(0));
      }

      return var1;
   }

   public int getGapStrategy() {
      return this.mGapStrategy;
   }

   int getLastChildPosition() {
      int var1 = this.getChildCount();
      if (var1 == 0) {
         var1 = 0;
      } else {
         var1 = this.getPosition(this.getChildAt(var1 - 1));
      }

      return var1;
   }

   public int getOrientation() {
      return this.mOrientation;
   }

   public boolean getReverseLayout() {
      return this.mReverseLayout;
   }

   public int getSpanCount() {
      return this.mSpanCount;
   }

   View hasGapsToFix() {
      int var1 = this.getChildCount() - 1;
      BitSet var7 = new BitSet(this.mSpanCount);
      var7.set(0, this.mSpanCount, true);
      int var2 = this.mOrientation;
      byte var4 = -1;
      byte var11;
      if (var2 == 1 && this.isLayoutRTL()) {
         var11 = 1;
      } else {
         var11 = -1;
      }

      int var3;
      if (this.mShouldReverseLayout) {
         var3 = -1;
      } else {
         var3 = var1 + 1;
         var1 = 0;
      }

      int var5 = var1;
      if (var1 < var3) {
         var4 = 1;
         var5 = var1;
      }

      for(; var5 != var3; var5 += var4) {
         View var8 = this.getChildAt(var5);
         LayoutParams var9 = (LayoutParams)var8.getLayoutParams();
         if (var7.get(var9.mSpan.mIndex)) {
            if (this.checkSpanForGap(var9.mSpan)) {
               return var8;
            }

            var7.clear(var9.mSpan.mIndex);
         }

         if (!var9.mFullSpan) {
            var1 = var5 + var4;
            if (var1 != var3) {
               View var10;
               boolean var12;
               label71: {
                  label70: {
                     var10 = this.getChildAt(var1);
                     int var6;
                     if (this.mShouldReverseLayout) {
                        var6 = this.mPrimaryOrientation.getDecoratedEnd(var8);
                        var1 = this.mPrimaryOrientation.getDecoratedEnd(var10);
                        if (var6 < var1) {
                           return var8;
                        }

                        if (var6 == var1) {
                           break label70;
                        }
                     } else {
                        var6 = this.mPrimaryOrientation.getDecoratedStart(var8);
                        var1 = this.mPrimaryOrientation.getDecoratedStart(var10);
                        if (var6 > var1) {
                           return var8;
                        }

                        if (var6 == var1) {
                           break label70;
                        }
                     }

                     var12 = false;
                     break label71;
                  }

                  var12 = true;
               }

               if (var12) {
                  LayoutParams var14 = (LayoutParams)var10.getLayoutParams();
                  if (var9.mSpan.mIndex - var14.mSpan.mIndex < 0) {
                     var12 = true;
                  } else {
                     var12 = false;
                  }

                  boolean var13;
                  if (var11 < 0) {
                     var13 = true;
                  } else {
                     var13 = false;
                  }

                  if (var12 != var13) {
                     return var8;
                  }
               }
            }
         }
      }

      return null;
   }

   public void invalidateSpanAssignments() {
      this.mLazySpanLookup.clear();
      this.requestLayout();
   }

   public boolean isAutoMeasureEnabled() {
      boolean var1;
      if (this.mGapStrategy != 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   boolean isLayoutRTL() {
      int var1 = this.getLayoutDirection();
      boolean var2 = true;
      if (var1 != 1) {
         var2 = false;
      }

      return var2;
   }

   public void offsetChildrenHorizontal(int var1) {
      super.offsetChildrenHorizontal(var1);

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         this.mSpans[var2].onOffset(var1);
      }

   }

   public void offsetChildrenVertical(int var1) {
      super.offsetChildrenVertical(var1);

      for(int var2 = 0; var2 < this.mSpanCount; ++var2) {
         this.mSpans[var2].onOffset(var1);
      }

   }

   public void onAdapterChanged(RecyclerView.Adapter var1, RecyclerView.Adapter var2) {
      this.mLazySpanLookup.clear();

      for(int var3 = 0; var3 < this.mSpanCount; ++var3) {
         this.mSpans[var3].clear();
      }

   }

   public void onDetachedFromWindow(RecyclerView var1, RecyclerView.Recycler var2) {
      super.onDetachedFromWindow(var1, var2);
      this.removeCallbacks(this.mCheckForGapsRunnable);

      for(int var3 = 0; var3 < this.mSpanCount; ++var3) {
         this.mSpans[var3].clear();
      }

      var1.requestLayout();
   }

   public View onFocusSearchFailed(View var1, int var2, RecyclerView.Recycler var3, RecyclerView.State var4) {
      if (this.getChildCount() == 0) {
         return null;
      } else {
         var1 = this.findContainingItemView(var1);
         if (var1 == null) {
            return null;
         } else {
            this.resolveShouldLayoutReverse();
            int var7 = this.convertFocusDirectionToLayoutDirection(var2);
            if (var7 == Integer.MIN_VALUE) {
               return null;
            } else {
               LayoutParams var10 = (LayoutParams)var1.getLayoutParams();
               boolean var9 = var10.mFullSpan;
               Span var14 = var10.mSpan;
               if (var7 == 1) {
                  var2 = this.getLastChildPosition();
               } else {
                  var2 = this.getFirstChildPosition();
               }

               this.updateLayoutState(var2, var4);
               this.setLayoutStateDirection(var7);
               LayoutState var11 = this.mLayoutState;
               var11.mCurrentPosition = var11.mItemDirection + var2;
               this.mLayoutState.mAvailable = (int)((float)this.mPrimaryOrientation.getTotalSpace() * 0.33333334F);
               this.mLayoutState.mStopInFocusable = true;
               var11 = this.mLayoutState;
               int var6 = 0;
               var11.mRecycle = false;
               this.fill(var3, this.mLayoutState, var4);
               this.mLastLayoutFromEnd = this.mShouldReverseLayout;
               View var12;
               if (!var9) {
                  var12 = var14.getFocusableViewAfter(var2, var7);
                  if (var12 != null && var12 != var1) {
                     return var12;
                  }
               }

               int var5;
               if (this.preferLastSpan(var7)) {
                  for(var5 = this.mSpanCount - 1; var5 >= 0; --var5) {
                     var12 = this.mSpans[var5].getFocusableViewAfter(var2, var7);
                     if (var12 != null && var12 != var1) {
                        return var12;
                     }
                  }
               } else {
                  for(var5 = 0; var5 < this.mSpanCount; ++var5) {
                     var12 = this.mSpans[var5].getFocusableViewAfter(var2, var7);
                     if (var12 != null && var12 != var1) {
                        return var12;
                     }
                  }
               }

               boolean var8 = this.mReverseLayout;
               boolean var13;
               if (var7 == -1) {
                  var13 = true;
               } else {
                  var13 = false;
               }

               if ((var8 ^ true) == var13) {
                  var13 = true;
               } else {
                  var13 = false;
               }

               if (!var9) {
                  if (var13) {
                     var5 = var14.findFirstPartiallyVisibleItemPosition();
                  } else {
                     var5 = var14.findLastPartiallyVisibleItemPosition();
                  }

                  var12 = this.findViewByPosition(var5);
                  if (var12 != null && var12 != var1) {
                     return var12;
                  }
               }

               var5 = var6;
               if (this.preferLastSpan(var7)) {
                  for(var5 = this.mSpanCount - 1; var5 >= 0; --var5) {
                     if (var5 != var14.mIndex) {
                        if (var13) {
                           var6 = this.mSpans[var5].findFirstPartiallyVisibleItemPosition();
                        } else {
                           var6 = this.mSpans[var5].findLastPartiallyVisibleItemPosition();
                        }

                        var12 = this.findViewByPosition(var6);
                        if (var12 != null && var12 != var1) {
                           return var12;
                        }
                     }
                  }
               } else {
                  while(var5 < this.mSpanCount) {
                     if (var13) {
                        var6 = this.mSpans[var5].findFirstPartiallyVisibleItemPosition();
                     } else {
                        var6 = this.mSpans[var5].findLastPartiallyVisibleItemPosition();
                     }

                     var12 = this.findViewByPosition(var6);
                     if (var12 != null && var12 != var1) {
                        return var12;
                     }

                     ++var5;
                  }
               }

               return null;
            }
         }
      }
   }

   public void onInitializeAccessibilityEvent(AccessibilityEvent var1) {
      super.onInitializeAccessibilityEvent(var1);
      if (this.getChildCount() > 0) {
         View var5 = this.findFirstVisibleItemClosestToStart(false);
         View var4 = this.findFirstVisibleItemClosestToEnd(false);
         if (var5 != null && var4 != null) {
            int var2 = this.getPosition(var5);
            int var3 = this.getPosition(var4);
            if (var2 < var3) {
               var1.setFromIndex(var2);
               var1.setToIndex(var3);
            } else {
               var1.setFromIndex(var3);
               var1.setToIndex(var2);
            }
         }
      }

   }

   public void onItemsAdded(RecyclerView var1, int var2, int var3) {
      this.handleUpdate(var2, var3, 1);
   }

   public void onItemsChanged(RecyclerView var1) {
      this.mLazySpanLookup.clear();
      this.requestLayout();
   }

   public void onItemsMoved(RecyclerView var1, int var2, int var3, int var4) {
      this.handleUpdate(var2, var3, 8);
   }

   public void onItemsRemoved(RecyclerView var1, int var2, int var3) {
      this.handleUpdate(var2, var3, 2);
   }

   public void onItemsUpdated(RecyclerView var1, int var2, int var3, Object var4) {
      this.handleUpdate(var2, var3, 4);
   }

   public void onLayoutChildren(RecyclerView.Recycler var1, RecyclerView.State var2) {
      this.onLayoutChildren(var1, var2, true);
   }

   public void onLayoutCompleted(RecyclerView.State var1) {
      super.onLayoutCompleted(var1);
      this.mPendingScrollPosition = -1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.mPendingSavedState = null;
      this.mAnchorInfo.reset();
   }

   public void onRestoreInstanceState(Parcelable var1) {
      if (var1 instanceof SavedState) {
         SavedState var2 = (SavedState)var1;
         this.mPendingSavedState = var2;
         if (this.mPendingScrollPosition != -1) {
            var2.invalidateAnchorPositionInfo();
            this.mPendingSavedState.invalidateSpanInfo();
         }

         this.requestLayout();
      }

   }

   public Parcelable onSaveInstanceState() {
      if (this.mPendingSavedState != null) {
         return new SavedState(this.mPendingSavedState);
      } else {
         SavedState var4 = new SavedState();
         var4.mReverseLayout = this.mReverseLayout;
         var4.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
         var4.mLastLayoutRTL = this.mLastLayoutRTL;
         LazySpanLookup var5 = this.mLazySpanLookup;
         int var2 = 0;
         if (var5 != null && var5.mData != null) {
            var4.mSpanLookup = this.mLazySpanLookup.mData;
            var4.mSpanLookupSize = var4.mSpanLookup.length;
            var4.mFullSpanItems = this.mLazySpanLookup.mFullSpanItems;
         } else {
            var4.mSpanLookupSize = 0;
         }

         if (this.getChildCount() > 0) {
            int var1;
            if (this.mLastLayoutFromEnd) {
               var1 = this.getLastChildPosition();
            } else {
               var1 = this.getFirstChildPosition();
            }

            var4.mAnchorPosition = var1;
            var4.mVisibleAnchorPosition = this.findFirstVisibleItemPositionInt();
            var4.mSpanOffsetsSize = this.mSpanCount;

            for(var4.mSpanOffsets = new int[this.mSpanCount]; var2 < this.mSpanCount; ++var2) {
               label36: {
                  int var3;
                  if (this.mLastLayoutFromEnd) {
                     var3 = this.mSpans[var2].getEndLine(Integer.MIN_VALUE);
                     var1 = var3;
                     if (var3 == Integer.MIN_VALUE) {
                        break label36;
                     }

                     var1 = this.mPrimaryOrientation.getEndAfterPadding();
                  } else {
                     var3 = this.mSpans[var2].getStartLine(Integer.MIN_VALUE);
                     var1 = var3;
                     if (var3 == Integer.MIN_VALUE) {
                        break label36;
                     }

                     var1 = this.mPrimaryOrientation.getStartAfterPadding();
                  }

                  var1 = var3 - var1;
               }

               var4.mSpanOffsets[var2] = var1;
            }
         } else {
            var4.mAnchorPosition = -1;
            var4.mVisibleAnchorPosition = -1;
            var4.mSpanOffsetsSize = 0;
         }

         return var4;
      }
   }

   public void onScrollStateChanged(int var1) {
      if (var1 == 0) {
         this.checkForGaps();
      }

   }

   void prepareLayoutStateForDelta(int var1, RecyclerView.State var2) {
      int var3;
      byte var4;
      if (var1 > 0) {
         var3 = this.getLastChildPosition();
         var4 = 1;
      } else {
         var3 = this.getFirstChildPosition();
         var4 = -1;
      }

      this.mLayoutState.mRecycle = true;
      this.updateLayoutState(var3, var2);
      this.setLayoutStateDirection(var4);
      LayoutState var5 = this.mLayoutState;
      var5.mCurrentPosition = var3 + var5.mItemDirection;
      this.mLayoutState.mAvailable = Math.abs(var1);
   }

   int scrollBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      if (this.getChildCount() != 0 && var1 != 0) {
         this.prepareLayoutStateForDelta(var1, var3);
         int var4 = this.fill(var2, this.mLayoutState, var3);
         if (this.mLayoutState.mAvailable >= var4) {
            if (var1 < 0) {
               var1 = -var4;
            } else {
               var1 = var4;
            }
         }

         this.mPrimaryOrientation.offsetChildren(-var1);
         this.mLastLayoutFromEnd = this.mShouldReverseLayout;
         this.mLayoutState.mAvailable = 0;
         this.recycle(var2, this.mLayoutState);
         return var1;
      } else {
         return 0;
      }
   }

   public int scrollHorizontallyBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      return this.scrollBy(var1, var2, var3);
   }

   public void scrollToPosition(int var1) {
      SavedState var2 = this.mPendingSavedState;
      if (var2 != null && var2.mAnchorPosition != var1) {
         this.mPendingSavedState.invalidateAnchorPositionInfo();
      }

      this.mPendingScrollPosition = var1;
      this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
      this.requestLayout();
   }

   public void scrollToPositionWithOffset(int var1, int var2) {
      SavedState var3 = this.mPendingSavedState;
      if (var3 != null) {
         var3.invalidateAnchorPositionInfo();
      }

      this.mPendingScrollPosition = var1;
      this.mPendingScrollPositionOffset = var2;
      this.requestLayout();
   }

   public int scrollVerticallyBy(int var1, RecyclerView.Recycler var2, RecyclerView.State var3) {
      return this.scrollBy(var1, var2, var3);
   }

   public void setGapStrategy(int var1) {
      this.assertNotInLayoutOrScroll((String)null);
      if (var1 != this.mGapStrategy) {
         if (var1 != 0 && var1 != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
         } else {
            this.mGapStrategy = var1;
            this.requestLayout();
         }
      }
   }

   public void setMeasuredDimension(Rect var1, int var2, int var3) {
      int var5 = this.getPaddingLeft() + this.getPaddingRight();
      int var4 = this.getPaddingTop() + this.getPaddingBottom();
      if (this.mOrientation == 1) {
         var3 = chooseSize(var3, var1.height() + var4, this.getMinimumHeight());
         var4 = chooseSize(var2, this.mSizePerSpan * this.mSpanCount + var5, this.getMinimumWidth());
         var2 = var3;
         var3 = var4;
      } else {
         var2 = chooseSize(var2, var1.width() + var5, this.getMinimumWidth());
         var4 = chooseSize(var3, this.mSizePerSpan * this.mSpanCount + var4, this.getMinimumHeight());
         var3 = var2;
         var2 = var4;
      }

      this.setMeasuredDimension(var3, var2);
   }

   public void setOrientation(int var1) {
      if (var1 != 0 && var1 != 1) {
         throw new IllegalArgumentException("invalid orientation.");
      } else {
         this.assertNotInLayoutOrScroll((String)null);
         if (var1 != this.mOrientation) {
            this.mOrientation = var1;
            OrientationHelper var2 = this.mPrimaryOrientation;
            this.mPrimaryOrientation = this.mSecondaryOrientation;
            this.mSecondaryOrientation = var2;
            this.requestLayout();
         }
      }
   }

   public void setReverseLayout(boolean var1) {
      this.assertNotInLayoutOrScroll((String)null);
      SavedState var2 = this.mPendingSavedState;
      if (var2 != null && var2.mReverseLayout != var1) {
         this.mPendingSavedState.mReverseLayout = var1;
      }

      this.mReverseLayout = var1;
      this.requestLayout();
   }

   public void setSpanCount(int var1) {
      this.assertNotInLayoutOrScroll((String)null);
      if (var1 != this.mSpanCount) {
         this.invalidateSpanAssignments();
         this.mSpanCount = var1;
         this.mRemainingSpans = new BitSet(this.mSpanCount);
         this.mSpans = new Span[this.mSpanCount];

         for(var1 = 0; var1 < this.mSpanCount; ++var1) {
            this.mSpans[var1] = new Span(this, var1);
         }

         this.requestLayout();
      }

   }

   public void smoothScrollToPosition(RecyclerView var1, RecyclerView.State var2, int var3) {
      LinearSmoothScroller var4 = new LinearSmoothScroller(var1.getContext());
      var4.setTargetPosition(var3);
      this.startSmoothScroll(var4);
   }

   public boolean supportsPredictiveItemAnimations() {
      boolean var1;
      if (this.mPendingSavedState == null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   boolean updateAnchorFromPendingData(RecyclerView.State var1, AnchorInfo var2) {
      boolean var5 = var1.isPreLayout();
      boolean var4 = false;
      if (!var5) {
         int var3 = this.mPendingScrollPosition;
         if (var3 != -1) {
            if (var3 >= 0 && var3 < var1.getItemCount()) {
               SavedState var6 = this.mPendingSavedState;
               if (var6 != null && var6.mAnchorPosition != -1 && this.mPendingSavedState.mSpanOffsetsSize >= 1) {
                  var2.mOffset = Integer.MIN_VALUE;
                  var2.mPosition = this.mPendingScrollPosition;
               } else {
                  View var7 = this.findViewByPosition(this.mPendingScrollPosition);
                  if (var7 != null) {
                     if (this.mShouldReverseLayout) {
                        var3 = this.getLastChildPosition();
                     } else {
                        var3 = this.getFirstChildPosition();
                     }

                     var2.mPosition = var3;
                     if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                        if (var2.mLayoutFromEnd) {
                           var2.mOffset = this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedEnd(var7);
                        } else {
                           var2.mOffset = this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset - this.mPrimaryOrientation.getDecoratedStart(var7);
                        }

                        return true;
                     }

                     if (this.mPrimaryOrientation.getDecoratedMeasurement(var7) > this.mPrimaryOrientation.getTotalSpace()) {
                        if (var2.mLayoutFromEnd) {
                           var3 = this.mPrimaryOrientation.getEndAfterPadding();
                        } else {
                           var3 = this.mPrimaryOrientation.getStartAfterPadding();
                        }

                        var2.mOffset = var3;
                        return true;
                     }

                     var3 = this.mPrimaryOrientation.getDecoratedStart(var7) - this.mPrimaryOrientation.getStartAfterPadding();
                     if (var3 < 0) {
                        var2.mOffset = -var3;
                        return true;
                     }

                     var3 = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(var7);
                     if (var3 < 0) {
                        var2.mOffset = var3;
                        return true;
                     }

                     var2.mOffset = Integer.MIN_VALUE;
                  } else {
                     var2.mPosition = this.mPendingScrollPosition;
                     var3 = this.mPendingScrollPositionOffset;
                     if (var3 == Integer.MIN_VALUE) {
                        if (this.calculateScrollDirectionForPosition(var2.mPosition) == 1) {
                           var4 = true;
                        }

                        var2.mLayoutFromEnd = var4;
                        var2.assignCoordinateFromPadding();
                     } else {
                        var2.assignCoordinateFromPadding(var3);
                     }

                     var2.mInvalidateOffsets = true;
                  }
               }

               return true;
            }

            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
         }
      }

      return false;
   }

   void updateAnchorInfoForLayout(RecyclerView.State var1, AnchorInfo var2) {
      if (!this.updateAnchorFromPendingData(var1, var2)) {
         if (!this.updateAnchorFromChildren(var1, var2)) {
            var2.assignCoordinateFromPadding();
            var2.mPosition = 0;
         }
      }
   }

   void updateMeasureSpecs(int var1) {
      this.mSizePerSpan = var1 / this.mSpanCount;
      this.mFullSizeSpec = MeasureSpec.makeMeasureSpec(var1, this.mSecondaryOrientation.getMode());
   }

   class AnchorInfo {
      boolean mInvalidateOffsets;
      boolean mLayoutFromEnd;
      int mOffset;
      int mPosition;
      int[] mSpanReferenceLines;
      boolean mValid;
      final StaggeredGridLayoutManager this$0;

      AnchorInfo(StaggeredGridLayoutManager var1) {
         this.this$0 = var1;
         this.reset();
      }

      void assignCoordinateFromPadding() {
         int var1;
         if (this.mLayoutFromEnd) {
            var1 = this.this$0.mPrimaryOrientation.getEndAfterPadding();
         } else {
            var1 = this.this$0.mPrimaryOrientation.getStartAfterPadding();
         }

         this.mOffset = var1;
      }

      void assignCoordinateFromPadding(int var1) {
         if (this.mLayoutFromEnd) {
            this.mOffset = this.this$0.mPrimaryOrientation.getEndAfterPadding() - var1;
         } else {
            this.mOffset = this.this$0.mPrimaryOrientation.getStartAfterPadding() + var1;
         }

      }

      void reset() {
         this.mPosition = -1;
         this.mOffset = Integer.MIN_VALUE;
         this.mLayoutFromEnd = false;
         this.mInvalidateOffsets = false;
         this.mValid = false;
         int[] var1 = this.mSpanReferenceLines;
         if (var1 != null) {
            Arrays.fill(var1, -1);
         }

      }

      void saveSpanReferenceLines(Span[] var1) {
         int var3 = var1.length;
         int[] var4 = this.mSpanReferenceLines;
         if (var4 == null || var4.length < var3) {
            this.mSpanReferenceLines = new int[this.this$0.mSpans.length];
         }

         for(int var2 = 0; var2 < var3; ++var2) {
            this.mSpanReferenceLines[var2] = var1[var2].getStartLine(Integer.MIN_VALUE);
         }

      }
   }

   public static class LayoutParams extends RecyclerView.LayoutParams {
      public static final int INVALID_SPAN_ID = -1;
      boolean mFullSpan;
      Span mSpan;

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

      public final int getSpanIndex() {
         Span var1 = this.mSpan;
         return var1 == null ? -1 : var1.mIndex;
      }

      public boolean isFullSpan() {
         return this.mFullSpan;
      }

      public void setFullSpan(boolean var1) {
         this.mFullSpan = var1;
      }
   }

   static class LazySpanLookup {
      private static final int MIN_SIZE = 10;
      int[] mData;
      List mFullSpanItems;

      private int invalidateFullSpansAfter(int var1) {
         if (this.mFullSpanItems == null) {
            return -1;
         } else {
            FullSpanItem var4 = this.getFullSpanItem(var1);
            if (var4 != null) {
               this.mFullSpanItems.remove(var4);
            }

            int var3 = this.mFullSpanItems.size();
            int var2 = 0;

            while(true) {
               if (var2 >= var3) {
                  var2 = -1;
                  break;
               }

               if (((FullSpanItem)this.mFullSpanItems.get(var2)).mPosition >= var1) {
                  break;
               }

               ++var2;
            }

            if (var2 != -1) {
               var4 = (FullSpanItem)this.mFullSpanItems.get(var2);
               this.mFullSpanItems.remove(var2);
               return var4.mPosition;
            } else {
               return -1;
            }
         }
      }

      private void offsetFullSpansForAddition(int var1, int var2) {
         List var4 = this.mFullSpanItems;
         if (var4 != null) {
            for(int var3 = var4.size() - 1; var3 >= 0; --var3) {
               FullSpanItem var5 = (FullSpanItem)this.mFullSpanItems.get(var3);
               if (var5.mPosition >= var1) {
                  var5.mPosition += var2;
               }
            }

         }
      }

      private void offsetFullSpansForRemoval(int var1, int var2) {
         List var4 = this.mFullSpanItems;
         if (var4 != null) {
            for(int var3 = var4.size() - 1; var3 >= 0; --var3) {
               FullSpanItem var5 = (FullSpanItem)this.mFullSpanItems.get(var3);
               if (var5.mPosition >= var1) {
                  if (var5.mPosition < var1 + var2) {
                     this.mFullSpanItems.remove(var3);
                  } else {
                     var5.mPosition -= var2;
                  }
               }
            }

         }
      }

      public void addFullSpanItem(FullSpanItem var1) {
         if (this.mFullSpanItems == null) {
            this.mFullSpanItems = new ArrayList();
         }

         int var3 = this.mFullSpanItems.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            FullSpanItem var4 = (FullSpanItem)this.mFullSpanItems.get(var2);
            if (var4.mPosition == var1.mPosition) {
               this.mFullSpanItems.remove(var2);
            }

            if (var4.mPosition >= var1.mPosition) {
               this.mFullSpanItems.add(var2, var1);
               return;
            }
         }

         this.mFullSpanItems.add(var1);
      }

      void clear() {
         int[] var1 = this.mData;
         if (var1 != null) {
            Arrays.fill(var1, -1);
         }

         this.mFullSpanItems = null;
      }

      void ensureSize(int var1) {
         int[] var2 = this.mData;
         if (var2 == null) {
            var2 = new int[Math.max(var1, 10) + 1];
            this.mData = var2;
            Arrays.fill(var2, -1);
         } else if (var1 >= var2.length) {
            int[] var3 = new int[this.sizeForPosition(var1)];
            this.mData = var3;
            System.arraycopy(var2, 0, var3, 0, var2.length);
            var3 = this.mData;
            Arrays.fill(var3, var2.length, var3.length, -1);
         }

      }

      int forceInvalidateAfter(int var1) {
         List var3 = this.mFullSpanItems;
         if (var3 != null) {
            for(int var2 = var3.size() - 1; var2 >= 0; --var2) {
               if (((FullSpanItem)this.mFullSpanItems.get(var2)).mPosition >= var1) {
                  this.mFullSpanItems.remove(var2);
               }
            }
         }

         return this.invalidateAfter(var1);
      }

      public FullSpanItem getFirstFullSpanItemInRange(int var1, int var2, int var3, boolean var4) {
         List var7 = this.mFullSpanItems;
         if (var7 == null) {
            return null;
         } else {
            int var6 = var7.size();

            for(int var5 = 0; var5 < var6; ++var5) {
               FullSpanItem var8 = (FullSpanItem)this.mFullSpanItems.get(var5);
               if (var8.mPosition >= var2) {
                  return null;
               }

               if (var8.mPosition >= var1 && (var3 == 0 || var8.mGapDir == var3 || var4 && var8.mHasUnwantedGapAfter)) {
                  return var8;
               }
            }

            return null;
         }
      }

      public FullSpanItem getFullSpanItem(int var1) {
         List var3 = this.mFullSpanItems;
         if (var3 == null) {
            return null;
         } else {
            for(int var2 = var3.size() - 1; var2 >= 0; --var2) {
               FullSpanItem var4 = (FullSpanItem)this.mFullSpanItems.get(var2);
               if (var4.mPosition == var1) {
                  return var4;
               }
            }

            return null;
         }
      }

      int getSpan(int var1) {
         int[] var2 = this.mData;
         return var2 != null && var1 < var2.length ? var2[var1] : -1;
      }

      int invalidateAfter(int var1) {
         int[] var3 = this.mData;
         if (var3 == null) {
            return -1;
         } else if (var1 >= var3.length) {
            return -1;
         } else {
            int var2 = this.invalidateFullSpansAfter(var1);
            if (var2 == -1) {
               var3 = this.mData;
               Arrays.fill(var3, var1, var3.length, -1);
               return this.mData.length;
            } else {
               var2 = Math.min(var2 + 1, this.mData.length);
               Arrays.fill(this.mData, var1, var2, -1);
               return var2;
            }
         }
      }

      void offsetForAddition(int var1, int var2) {
         int[] var4 = this.mData;
         if (var4 != null && var1 < var4.length) {
            int var3 = var1 + var2;
            this.ensureSize(var3);
            var4 = this.mData;
            System.arraycopy(var4, var1, var4, var3, var4.length - var1 - var2);
            Arrays.fill(this.mData, var1, var3, -1);
            this.offsetFullSpansForAddition(var1, var2);
         }

      }

      void offsetForRemoval(int var1, int var2) {
         int[] var4 = this.mData;
         if (var4 != null && var1 < var4.length) {
            int var3 = var1 + var2;
            this.ensureSize(var3);
            var4 = this.mData;
            System.arraycopy(var4, var3, var4, var1, var4.length - var1 - var2);
            var4 = this.mData;
            Arrays.fill(var4, var4.length - var2, var4.length, -1);
            this.offsetFullSpansForRemoval(var1, var2);
         }

      }

      void setSpan(int var1, Span var2) {
         this.ensureSize(var1);
         this.mData[var1] = var2.mIndex;
      }

      int sizeForPosition(int var1) {
         int var2;
         for(var2 = this.mData.length; var2 <= var1; var2 *= 2) {
         }

         return var2;
      }

      static class FullSpanItem implements Parcelable {
         public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
            public FullSpanItem createFromParcel(Parcel var1) {
               return new FullSpanItem(var1);
            }

            public FullSpanItem[] newArray(int var1) {
               return new FullSpanItem[var1];
            }
         };
         int mGapDir;
         int[] mGapPerSpan;
         boolean mHasUnwantedGapAfter;
         int mPosition;

         FullSpanItem() {
         }

         FullSpanItem(Parcel var1) {
            this.mPosition = var1.readInt();
            this.mGapDir = var1.readInt();
            int var2 = var1.readInt();
            boolean var3 = true;
            if (var2 != 1) {
               var3 = false;
            }

            this.mHasUnwantedGapAfter = var3;
            var2 = var1.readInt();
            if (var2 > 0) {
               int[] var4 = new int[var2];
               this.mGapPerSpan = var4;
               var1.readIntArray(var4);
            }

         }

         public int describeContents() {
            return 0;
         }

         int getGapForSpan(int var1) {
            int[] var2 = this.mGapPerSpan;
            if (var2 == null) {
               var1 = 0;
            } else {
               var1 = var2[var1];
            }

            return var1;
         }

         public String toString() {
            return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
         }

         public void writeToParcel(Parcel var1, int var2) {
            var1.writeInt(this.mPosition);
            var1.writeInt(this.mGapDir);
            var1.writeInt(this.mHasUnwantedGapAfter);
            int[] var3 = this.mGapPerSpan;
            if (var3 != null && var3.length > 0) {
               var1.writeInt(var3.length);
               var1.writeIntArray(this.mGapPerSpan);
            } else {
               var1.writeInt(0);
            }

         }
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
      int mAnchorPosition;
      List mFullSpanItems;
      boolean mLastLayoutRTL;
      boolean mReverseLayout;
      int[] mSpanLookup;
      int mSpanLookupSize;
      int[] mSpanOffsets;
      int mSpanOffsetsSize;
      int mVisibleAnchorPosition;

      public SavedState() {
      }

      SavedState(Parcel var1) {
         this.mAnchorPosition = var1.readInt();
         this.mVisibleAnchorPosition = var1.readInt();
         int var2 = var1.readInt();
         this.mSpanOffsetsSize = var2;
         int[] var5;
         if (var2 > 0) {
            var5 = new int[var2];
            this.mSpanOffsets = var5;
            var1.readIntArray(var5);
         }

         var2 = var1.readInt();
         this.mSpanLookupSize = var2;
         if (var2 > 0) {
            var5 = new int[var2];
            this.mSpanLookup = var5;
            var1.readIntArray(var5);
         }

         var2 = var1.readInt();
         boolean var4 = false;
         boolean var3;
         if (var2 == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.mReverseLayout = var3;
         if (var1.readInt() == 1) {
            var3 = true;
         } else {
            var3 = false;
         }

         this.mAnchorLayoutFromEnd = var3;
         var3 = var4;
         if (var1.readInt() == 1) {
            var3 = true;
         }

         this.mLastLayoutRTL = var3;
         this.mFullSpanItems = var1.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
      }

      public SavedState(SavedState var1) {
         this.mSpanOffsetsSize = var1.mSpanOffsetsSize;
         this.mAnchorPosition = var1.mAnchorPosition;
         this.mVisibleAnchorPosition = var1.mVisibleAnchorPosition;
         this.mSpanOffsets = var1.mSpanOffsets;
         this.mSpanLookupSize = var1.mSpanLookupSize;
         this.mSpanLookup = var1.mSpanLookup;
         this.mReverseLayout = var1.mReverseLayout;
         this.mAnchorLayoutFromEnd = var1.mAnchorLayoutFromEnd;
         this.mLastLayoutRTL = var1.mLastLayoutRTL;
         this.mFullSpanItems = var1.mFullSpanItems;
      }

      public int describeContents() {
         return 0;
      }

      void invalidateAnchorPositionInfo() {
         this.mSpanOffsets = null;
         this.mSpanOffsetsSize = 0;
         this.mAnchorPosition = -1;
         this.mVisibleAnchorPosition = -1;
      }

      void invalidateSpanInfo() {
         this.mSpanOffsets = null;
         this.mSpanOffsetsSize = 0;
         this.mSpanLookupSize = 0;
         this.mSpanLookup = null;
         this.mFullSpanItems = null;
      }

      public void writeToParcel(Parcel var1, int var2) {
         var1.writeInt(this.mAnchorPosition);
         var1.writeInt(this.mVisibleAnchorPosition);
         var1.writeInt(this.mSpanOffsetsSize);
         if (this.mSpanOffsetsSize > 0) {
            var1.writeIntArray(this.mSpanOffsets);
         }

         var1.writeInt(this.mSpanLookupSize);
         if (this.mSpanLookupSize > 0) {
            var1.writeIntArray(this.mSpanLookup);
         }

         var1.writeInt(this.mReverseLayout);
         var1.writeInt(this.mAnchorLayoutFromEnd);
         var1.writeInt(this.mLastLayoutRTL);
         var1.writeList(this.mFullSpanItems);
      }
   }

   class Span {
      static final int INVALID_LINE = Integer.MIN_VALUE;
      int mCachedEnd;
      int mCachedStart;
      int mDeletedSize;
      final int mIndex;
      ArrayList mViews;
      final StaggeredGridLayoutManager this$0;

      Span(StaggeredGridLayoutManager var1, int var2) {
         this.this$0 = var1;
         this.mViews = new ArrayList();
         this.mCachedStart = Integer.MIN_VALUE;
         this.mCachedEnd = Integer.MIN_VALUE;
         this.mDeletedSize = 0;
         this.mIndex = var2;
      }

      void appendToSpan(View var1) {
         LayoutParams var2 = this.getLayoutParams(var1);
         var2.mSpan = this;
         this.mViews.add(var1);
         this.mCachedEnd = Integer.MIN_VALUE;
         if (this.mViews.size() == 1) {
            this.mCachedStart = Integer.MIN_VALUE;
         }

         if (var2.isItemRemoved() || var2.isItemChanged()) {
            this.mDeletedSize += this.this$0.mPrimaryOrientation.getDecoratedMeasurement(var1);
         }

      }

      void cacheReferenceLineAndClear(boolean var1, int var2) {
         int var3;
         if (var1) {
            var3 = this.getEndLine(Integer.MIN_VALUE);
         } else {
            var3 = this.getStartLine(Integer.MIN_VALUE);
         }

         this.clear();
         if (var3 != Integer.MIN_VALUE) {
            if ((!var1 || var3 >= this.this$0.mPrimaryOrientation.getEndAfterPadding()) && (var1 || var3 <= this.this$0.mPrimaryOrientation.getStartAfterPadding())) {
               int var4 = var3;
               if (var2 != Integer.MIN_VALUE) {
                  var4 = var3 + var2;
               }

               this.mCachedEnd = var4;
               this.mCachedStart = var4;
            }
         }
      }

      void calculateCachedEnd() {
         ArrayList var1 = this.mViews;
         View var3 = (View)var1.get(var1.size() - 1);
         LayoutParams var2 = this.getLayoutParams(var3);
         this.mCachedEnd = this.this$0.mPrimaryOrientation.getDecoratedEnd(var3);
         if (var2.mFullSpan) {
            LazySpanLookup.FullSpanItem var4 = this.this$0.mLazySpanLookup.getFullSpanItem(var2.getViewLayoutPosition());
            if (var4 != null && var4.mGapDir == 1) {
               this.mCachedEnd += var4.getGapForSpan(this.mIndex);
            }
         }

      }

      void calculateCachedStart() {
         View var1 = (View)this.mViews.get(0);
         LayoutParams var2 = this.getLayoutParams(var1);
         this.mCachedStart = this.this$0.mPrimaryOrientation.getDecoratedStart(var1);
         if (var2.mFullSpan) {
            LazySpanLookup.FullSpanItem var3 = this.this$0.mLazySpanLookup.getFullSpanItem(var2.getViewLayoutPosition());
            if (var3 != null && var3.mGapDir == -1) {
               this.mCachedStart -= var3.getGapForSpan(this.mIndex);
            }
         }

      }

      void clear() {
         this.mViews.clear();
         this.invalidateCache();
         this.mDeletedSize = 0;
      }

      public int findFirstCompletelyVisibleItemPosition() {
         int var1;
         if (this.this$0.mReverseLayout) {
            var1 = this.findOneVisibleChild(this.mViews.size() - 1, -1, true);
         } else {
            var1 = this.findOneVisibleChild(0, this.mViews.size(), true);
         }

         return var1;
      }

      public int findFirstPartiallyVisibleItemPosition() {
         int var1;
         if (this.this$0.mReverseLayout) {
            var1 = this.findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
         } else {
            var1 = this.findOnePartiallyVisibleChild(0, this.mViews.size(), true);
         }

         return var1;
      }

      public int findFirstVisibleItemPosition() {
         int var1;
         if (this.this$0.mReverseLayout) {
            var1 = this.findOneVisibleChild(this.mViews.size() - 1, -1, false);
         } else {
            var1 = this.findOneVisibleChild(0, this.mViews.size(), false);
         }

         return var1;
      }

      public int findLastCompletelyVisibleItemPosition() {
         int var1;
         if (this.this$0.mReverseLayout) {
            var1 = this.findOneVisibleChild(0, this.mViews.size(), true);
         } else {
            var1 = this.findOneVisibleChild(this.mViews.size() - 1, -1, true);
         }

         return var1;
      }

      public int findLastPartiallyVisibleItemPosition() {
         int var1;
         if (this.this$0.mReverseLayout) {
            var1 = this.findOnePartiallyVisibleChild(0, this.mViews.size(), true);
         } else {
            var1 = this.findOnePartiallyVisibleChild(this.mViews.size() - 1, -1, true);
         }

         return var1;
      }

      public int findLastVisibleItemPosition() {
         int var1;
         if (this.this$0.mReverseLayout) {
            var1 = this.findOneVisibleChild(0, this.mViews.size(), false);
         } else {
            var1 = this.findOneVisibleChild(this.mViews.size() - 1, -1, false);
         }

         return var1;
      }

      int findOnePartiallyOrCompletelyVisibleChild(int var1, int var2, boolean var3, boolean var4, boolean var5) {
         int var9 = this.this$0.mPrimaryOrientation.getStartAfterPadding();
         int var10 = this.this$0.mPrimaryOrientation.getEndAfterPadding();
         byte var6;
         if (var2 > var1) {
            var6 = 1;
         } else {
            var6 = -1;
         }

         for(; var1 != var2; var1 += var6) {
            boolean var7;
            boolean var8;
            int var11;
            int var12;
            View var13;
            label45: {
               label44: {
                  var13 = (View)this.mViews.get(var1);
                  var11 = this.this$0.mPrimaryOrientation.getDecoratedStart(var13);
                  var12 = this.this$0.mPrimaryOrientation.getDecoratedEnd(var13);
                  var8 = false;
                  if (var5) {
                     if (var11 <= var10) {
                        break label44;
                     }
                  } else if (var11 < var10) {
                     break label44;
                  }

                  var7 = false;
                  break label45;
               }

               var7 = true;
            }

            label51: {
               if (var5) {
                  if (var12 < var9) {
                     break label51;
                  }
               } else if (var12 <= var9) {
                  break label51;
               }

               var8 = true;
            }

            if (var7 && var8) {
               if (var3 && var4) {
                  if (var11 >= var9 && var12 <= var10) {
                     return this.this$0.getPosition(var13);
                  }
               } else {
                  if (var4) {
                     return this.this$0.getPosition(var13);
                  }

                  if (var11 < var9 || var12 > var10) {
                     return this.this$0.getPosition(var13);
                  }
               }
            }
         }

         return -1;
      }

      int findOnePartiallyVisibleChild(int var1, int var2, boolean var3) {
         return this.findOnePartiallyOrCompletelyVisibleChild(var1, var2, false, false, var3);
      }

      int findOneVisibleChild(int var1, int var2, boolean var3) {
         return this.findOnePartiallyOrCompletelyVisibleChild(var1, var2, var3, true, false);
      }

      public int getDeletedSize() {
         return this.mDeletedSize;
      }

      int getEndLine() {
         int var1 = this.mCachedEnd;
         if (var1 != Integer.MIN_VALUE) {
            return var1;
         } else {
            this.calculateCachedEnd();
            return this.mCachedEnd;
         }
      }

      int getEndLine(int var1) {
         int var2 = this.mCachedEnd;
         if (var2 != Integer.MIN_VALUE) {
            return var2;
         } else if (this.mViews.size() == 0) {
            return var1;
         } else {
            this.calculateCachedEnd();
            return this.mCachedEnd;
         }
      }

      public View getFocusableViewAfter(int var1, int var2) {
         View var4 = null;
         View var5 = null;
         View var6;
         if (var2 == -1) {
            int var3 = this.mViews.size();
            var2 = 0;
            var4 = var5;

            while(true) {
               var5 = var4;
               if (var2 >= var3) {
                  break;
               }

               var6 = (View)this.mViews.get(var2);
               if (this.this$0.mReverseLayout) {
                  var5 = var4;
                  if (this.this$0.getPosition(var6) <= var1) {
                     break;
                  }
               }

               if (!this.this$0.mReverseLayout && this.this$0.getPosition(var6) >= var1) {
                  var5 = var4;
                  break;
               }

               var5 = var4;
               if (!var6.hasFocusable()) {
                  break;
               }

               ++var2;
               var4 = var6;
            }
         } else {
            var2 = this.mViews.size() - 1;

            while(true) {
               var5 = var4;
               if (var2 < 0) {
                  break;
               }

               var6 = (View)this.mViews.get(var2);
               if (this.this$0.mReverseLayout) {
                  var5 = var4;
                  if (this.this$0.getPosition(var6) >= var1) {
                     break;
                  }
               }

               if (!this.this$0.mReverseLayout && this.this$0.getPosition(var6) <= var1) {
                  var5 = var4;
                  break;
               }

               var5 = var4;
               if (!var6.hasFocusable()) {
                  break;
               }

               --var2;
               var4 = var6;
            }
         }

         return var5;
      }

      LayoutParams getLayoutParams(View var1) {
         return (LayoutParams)var1.getLayoutParams();
      }

      int getStartLine() {
         int var1 = this.mCachedStart;
         if (var1 != Integer.MIN_VALUE) {
            return var1;
         } else {
            this.calculateCachedStart();
            return this.mCachedStart;
         }
      }

      int getStartLine(int var1) {
         int var2 = this.mCachedStart;
         if (var2 != Integer.MIN_VALUE) {
            return var2;
         } else if (this.mViews.size() == 0) {
            return var1;
         } else {
            this.calculateCachedStart();
            return this.mCachedStart;
         }
      }

      void invalidateCache() {
         this.mCachedStart = Integer.MIN_VALUE;
         this.mCachedEnd = Integer.MIN_VALUE;
      }

      void onOffset(int var1) {
         int var2 = this.mCachedStart;
         if (var2 != Integer.MIN_VALUE) {
            this.mCachedStart = var2 + var1;
         }

         var2 = this.mCachedEnd;
         if (var2 != Integer.MIN_VALUE) {
            this.mCachedEnd = var2 + var1;
         }

      }

      void popEnd() {
         int var1 = this.mViews.size();
         View var3 = (View)this.mViews.remove(var1 - 1);
         LayoutParams var2 = this.getLayoutParams(var3);
         var2.mSpan = null;
         if (var2.isItemRemoved() || var2.isItemChanged()) {
            this.mDeletedSize -= this.this$0.mPrimaryOrientation.getDecoratedMeasurement(var3);
         }

         if (var1 == 1) {
            this.mCachedStart = Integer.MIN_VALUE;
         }

         this.mCachedEnd = Integer.MIN_VALUE;
      }

      void popStart() {
         View var1 = (View)this.mViews.remove(0);
         LayoutParams var2 = this.getLayoutParams(var1);
         var2.mSpan = null;
         if (this.mViews.size() == 0) {
            this.mCachedEnd = Integer.MIN_VALUE;
         }

         if (var2.isItemRemoved() || var2.isItemChanged()) {
            this.mDeletedSize -= this.this$0.mPrimaryOrientation.getDecoratedMeasurement(var1);
         }

         this.mCachedStart = Integer.MIN_VALUE;
      }

      void prependToSpan(View var1) {
         LayoutParams var2 = this.getLayoutParams(var1);
         var2.mSpan = this;
         this.mViews.add(0, var1);
         this.mCachedStart = Integer.MIN_VALUE;
         if (this.mViews.size() == 1) {
            this.mCachedEnd = Integer.MIN_VALUE;
         }

         if (var2.isItemRemoved() || var2.isItemChanged()) {
            this.mDeletedSize += this.this$0.mPrimaryOrientation.getDecoratedMeasurement(var1);
         }

      }

      void setLine(int var1) {
         this.mCachedStart = var1;
         this.mCachedEnd = var1;
      }
   }
}
