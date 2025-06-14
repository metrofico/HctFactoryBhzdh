package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPager extends ViewGroup {
   private static final int CLOSE_ENOUGH = 2;
   private static final Comparator COMPARATOR = new Comparator() {
      public int compare(ItemInfo var1, ItemInfo var2) {
         return var1.position - var2.position;
      }
   };
   private static final boolean DEBUG = false;
   private static final int DEFAULT_GUTTER_SIZE = 16;
   private static final int DEFAULT_OFFSCREEN_PAGES = 1;
   private static final int DRAW_ORDER_DEFAULT = 0;
   private static final int DRAW_ORDER_FORWARD = 1;
   private static final int DRAW_ORDER_REVERSE = 2;
   private static final int INVALID_POINTER = -1;
   static final int[] LAYOUT_ATTRS = new int[]{16842931};
   private static final int MAX_SETTLE_DURATION = 600;
   private static final int MIN_DISTANCE_FOR_FLING = 25;
   private static final int MIN_FLING_VELOCITY = 400;
   public static final int SCROLL_STATE_DRAGGING = 1;
   public static final int SCROLL_STATE_IDLE = 0;
   public static final int SCROLL_STATE_SETTLING = 2;
   private static final String TAG = "ViewPager";
   private static final boolean USE_CACHE = false;
   private static final Interpolator sInterpolator = new Interpolator() {
      public float getInterpolation(float var1) {
         --var1;
         return var1 * var1 * var1 * var1 * var1 + 1.0F;
      }
   };
   private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
   private int mActivePointerId = -1;
   PagerAdapter mAdapter;
   private List mAdapterChangeListeners;
   private int mBottomPageBounds;
   private boolean mCalledSuper;
   private int mChildHeightMeasureSpec;
   private int mChildWidthMeasureSpec;
   private int mCloseEnough;
   int mCurItem;
   private int mDecorChildCount;
   private int mDefaultGutterSize;
   private int mDrawingOrder;
   private ArrayList mDrawingOrderedChildren;
   private final Runnable mEndScrollRunnable = new Runnable(this) {
      final ViewPager this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.setScrollState(0);
         this.this$0.populate();
      }
   };
   private int mExpectedAdapterCount;
   private long mFakeDragBeginTime;
   private boolean mFakeDragging;
   private boolean mFirstLayout = true;
   private float mFirstOffset = -3.4028235E38F;
   private int mFlingDistance;
   private int mGutterSize;
   private boolean mInLayout;
   private float mInitialMotionX;
   private float mInitialMotionY;
   private OnPageChangeListener mInternalPageChangeListener;
   private boolean mIsBeingDragged;
   private boolean mIsScrollStarted;
   private boolean mIsUnableToDrag;
   private final ArrayList mItems = new ArrayList();
   private float mLastMotionX;
   private float mLastMotionY;
   private float mLastOffset = Float.MAX_VALUE;
   private EdgeEffect mLeftEdge;
   private Drawable mMarginDrawable;
   private int mMaximumVelocity;
   private int mMinimumVelocity;
   private boolean mNeedCalculatePageOffsets = false;
   private PagerObserver mObserver;
   private int mOffscreenPageLimit = 1;
   private OnPageChangeListener mOnPageChangeListener;
   private List mOnPageChangeListeners;
   private int mPageMargin;
   private PageTransformer mPageTransformer;
   private int mPageTransformerLayerType;
   private boolean mPopulatePending;
   private Parcelable mRestoredAdapterState = null;
   private ClassLoader mRestoredClassLoader = null;
   private int mRestoredCurItem = -1;
   private EdgeEffect mRightEdge;
   private int mScrollState = 0;
   private Scroller mScroller;
   private boolean mScrollingCacheEnabled;
   private final ItemInfo mTempItem = new ItemInfo();
   private final Rect mTempRect = new Rect();
   private int mTopPageBounds;
   private int mTouchSlop;
   private VelocityTracker mVelocityTracker;

   public ViewPager(Context var1) {
      super(var1);
      this.initViewPager();
   }

   public ViewPager(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.initViewPager();
   }

   private void calculatePageOffsets(ItemInfo var1, int var2, ItemInfo var3) {
      int var10 = this.mAdapter.getCount();
      int var7 = this.getClientWidth();
      float var5;
      if (var7 > 0) {
         var5 = (float)this.mPageMargin / (float)var7;
      } else {
         var5 = 0.0F;
      }

      float var4;
      float var6;
      int var8;
      int var9;
      if (var3 != null) {
         var7 = var3.position;
         if (var7 < var1.position) {
            var4 = var3.offset + var3.widthFactor + var5;
            ++var7;

            for(var8 = 0; var7 <= var1.position && var8 < this.mItems.size(); var7 = var9 + 1) {
               var3 = (ItemInfo)this.mItems.get(var8);

               while(true) {
                  var9 = var7;
                  var6 = var4;
                  if (var7 <= var3.position) {
                     break;
                  }

                  var9 = var7;
                  var6 = var4;
                  if (var8 >= this.mItems.size() - 1) {
                     break;
                  }

                  ++var8;
                  var3 = (ItemInfo)this.mItems.get(var8);
               }

               while(var9 < var3.position) {
                  var6 += this.mAdapter.getPageWidth(var9) + var5;
                  ++var9;
               }

               var3.offset = var6;
               var4 = var6 + var3.widthFactor + var5;
            }
         } else if (var7 > var1.position) {
            var8 = this.mItems.size() - 1;
            var4 = var3.offset;
            --var7;

            while(var7 >= var1.position && var8 >= 0) {
               var3 = (ItemInfo)this.mItems.get(var8);

               while(true) {
                  var9 = var7;
                  var6 = var4;
                  if (var7 >= var3.position) {
                     break;
                  }

                  var9 = var7;
                  var6 = var4;
                  if (var8 <= 0) {
                     break;
                  }

                  --var8;
                  var3 = (ItemInfo)this.mItems.get(var8);
               }

               while(var9 > var3.position) {
                  var6 -= this.mAdapter.getPageWidth(var9) + var5;
                  --var9;
               }

               var4 = var6 - (var3.widthFactor + var5);
               var3.offset = var4;
               var7 = var9 - 1;
            }
         }
      }

      var9 = this.mItems.size();
      var6 = var1.offset;
      var7 = var1.position - 1;
      if (var1.position == 0) {
         var4 = var1.offset;
      } else {
         var4 = -3.4028235E38F;
      }

      this.mFirstOffset = var4;
      var8 = var1.position;
      --var10;
      if (var8 == var10) {
         var4 = var1.offset + var1.widthFactor - 1.0F;
      } else {
         var4 = Float.MAX_VALUE;
      }

      this.mLastOffset = var4;
      var8 = var2 - 1;

      for(var4 = var6; var8 >= 0; --var7) {
         for(var3 = (ItemInfo)this.mItems.get(var8); var7 > var3.position; --var7) {
            var4 -= this.mAdapter.getPageWidth(var7) + var5;
         }

         var4 -= var3.widthFactor + var5;
         var3.offset = var4;
         if (var3.position == 0) {
            this.mFirstOffset = var4;
         }

         --var8;
      }

      var4 = var1.offset + var1.widthFactor + var5;
      var8 = var1.position + 1;
      var7 = var2 + 1;

      for(var2 = var8; var7 < var9; ++var2) {
         for(var1 = (ItemInfo)this.mItems.get(var7); var2 < var1.position; ++var2) {
            var4 += this.mAdapter.getPageWidth(var2) + var5;
         }

         if (var1.position == var10) {
            this.mLastOffset = var1.widthFactor + var4 - 1.0F;
         }

         var1.offset = var4;
         var4 += var1.widthFactor + var5;
         ++var7;
      }

      this.mNeedCalculatePageOffsets = false;
   }

   private void completeScroll(boolean var1) {
      boolean var2;
      if (this.mScrollState == 2) {
         var2 = true;
      } else {
         var2 = false;
      }

      int var3;
      if (var2) {
         this.setScrollingCacheEnabled(false);
         if (this.mScroller.isFinished() ^ true) {
            this.mScroller.abortAnimation();
            var3 = this.getScrollX();
            int var6 = this.getScrollY();
            int var5 = this.mScroller.getCurrX();
            int var4 = this.mScroller.getCurrY();
            if (var3 != var5 || var6 != var4) {
               this.scrollTo(var5, var4);
               if (var5 != var3) {
                  this.pageScrolled(var5);
               }
            }
         }
      }

      this.mPopulatePending = false;

      for(var3 = 0; var3 < this.mItems.size(); ++var3) {
         ItemInfo var7 = (ItemInfo)this.mItems.get(var3);
         if (var7.scrolling) {
            var7.scrolling = false;
            var2 = true;
         }
      }

      if (var2) {
         if (var1) {
            ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
         } else {
            this.mEndScrollRunnable.run();
         }
      }

   }

   private int determineTargetPage(int var1, float var2, int var3, int var4) {
      if (Math.abs(var4) > this.mFlingDistance && Math.abs(var3) > this.mMinimumVelocity) {
         if (var3 <= 0) {
            ++var1;
         }
      } else {
         float var5;
         if (var1 >= this.mCurItem) {
            var5 = 0.4F;
         } else {
            var5 = 0.6F;
         }

         var1 += (int)(var2 + var5);
      }

      var3 = var1;
      if (this.mItems.size() > 0) {
         ItemInfo var6 = (ItemInfo)this.mItems.get(0);
         ArrayList var7 = this.mItems;
         ItemInfo var8 = (ItemInfo)var7.get(var7.size() - 1);
         var3 = Math.max(var6.position, Math.min(var1, var8.position));
      }

      return var3;
   }

   private void dispatchOnPageScrolled(int var1, float var2, int var3) {
      OnPageChangeListener var6 = this.mOnPageChangeListener;
      if (var6 != null) {
         var6.onPageScrolled(var1, var2, var3);
      }

      List var7 = this.mOnPageChangeListeners;
      if (var7 != null) {
         int var4 = 0;

         for(int var5 = var7.size(); var4 < var5; ++var4) {
            var6 = (OnPageChangeListener)this.mOnPageChangeListeners.get(var4);
            if (var6 != null) {
               var6.onPageScrolled(var1, var2, var3);
            }
         }
      }

      var6 = this.mInternalPageChangeListener;
      if (var6 != null) {
         var6.onPageScrolled(var1, var2, var3);
      }

   }

   private void dispatchOnPageSelected(int var1) {
      OnPageChangeListener var4 = this.mOnPageChangeListener;
      if (var4 != null) {
         var4.onPageSelected(var1);
      }

      List var5 = this.mOnPageChangeListeners;
      if (var5 != null) {
         int var2 = 0;

         for(int var3 = var5.size(); var2 < var3; ++var2) {
            var4 = (OnPageChangeListener)this.mOnPageChangeListeners.get(var2);
            if (var4 != null) {
               var4.onPageSelected(var1);
            }
         }
      }

      var4 = this.mInternalPageChangeListener;
      if (var4 != null) {
         var4.onPageSelected(var1);
      }

   }

   private void dispatchOnScrollStateChanged(int var1) {
      OnPageChangeListener var4 = this.mOnPageChangeListener;
      if (var4 != null) {
         var4.onPageScrollStateChanged(var1);
      }

      List var5 = this.mOnPageChangeListeners;
      if (var5 != null) {
         int var2 = 0;

         for(int var3 = var5.size(); var2 < var3; ++var2) {
            var4 = (OnPageChangeListener)this.mOnPageChangeListeners.get(var2);
            if (var4 != null) {
               var4.onPageScrollStateChanged(var1);
            }
         }
      }

      var4 = this.mInternalPageChangeListener;
      if (var4 != null) {
         var4.onPageScrollStateChanged(var1);
      }

   }

   private void enableLayers(boolean var1) {
      int var4 = this.getChildCount();

      for(int var2 = 0; var2 < var4; ++var2) {
         int var3;
         if (var1) {
            var3 = this.mPageTransformerLayerType;
         } else {
            var3 = 0;
         }

         this.getChildAt(var2).setLayerType(var3, (Paint)null);
      }

   }

   private void endDrag() {
      this.mIsBeingDragged = false;
      this.mIsUnableToDrag = false;
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 != null) {
         var1.recycle();
         this.mVelocityTracker = null;
      }

   }

   private Rect getChildRectInPagerCoordinates(Rect var1, View var2) {
      Rect var3 = var1;
      if (var1 == null) {
         var3 = new Rect();
      }

      if (var2 == null) {
         var3.set(0, 0, 0, 0);
         return var3;
      } else {
         var3.left = var2.getLeft();
         var3.right = var2.getRight();
         var3.top = var2.getTop();
         var3.bottom = var2.getBottom();

         ViewGroup var5;
         for(ViewParent var4 = var2.getParent(); var4 instanceof ViewGroup && var4 != this; var4 = var5.getParent()) {
            var5 = (ViewGroup)var4;
            var3.left += var5.getLeft();
            var3.right += var5.getRight();
            var3.top += var5.getTop();
            var3.bottom += var5.getBottom();
         }

         return var3;
      }
   }

   private int getClientWidth() {
      return this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight();
   }

   private ItemInfo infoForCurrentScrollPosition() {
      int var5 = this.getClientWidth();
      float var3 = 0.0F;
      float var1;
      if (var5 > 0) {
         var1 = (float)this.getScrollX() / (float)var5;
      } else {
         var1 = 0.0F;
      }

      float var2;
      if (var5 > 0) {
         var2 = (float)this.mPageMargin / (float)var5;
      } else {
         var2 = 0.0F;
      }

      ItemInfo var11 = null;
      var5 = 0;
      int var7 = -1;
      boolean var6 = true;

      ItemInfo var10;
      for(float var4 = 0.0F; var5 < this.mItems.size(); var11 = var10) {
         ItemInfo var12 = (ItemInfo)this.mItems.get(var5);
         int var8 = var5;
         var10 = var12;
         if (!var6) {
            int var9 = var12.position;
            ++var7;
            var8 = var5;
            var10 = var12;
            if (var9 != var7) {
               var10 = this.mTempItem;
               var10.offset = var3 + var4 + var2;
               var10.position = var7;
               var10.widthFactor = this.mAdapter.getPageWidth(var10.position);
               var8 = var5 - 1;
            }
         }

         var3 = var10.offset;
         var4 = var10.widthFactor;
         if (!var6 && !(var1 >= var3)) {
            return var11;
         }

         if (var1 < var4 + var3 + var2 || var8 == this.mItems.size() - 1) {
            return var10;
         }

         var7 = var10.position;
         var4 = var10.widthFactor;
         var5 = var8 + 1;
         var6 = false;
      }

      return var11;
   }

   private static boolean isDecorView(View var0) {
      boolean var1;
      if (var0.getClass().getAnnotation(DecorView.class) != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private boolean isGutterDrag(float var1, float var2) {
      boolean var3;
      if ((!(var1 < (float)this.mGutterSize) || !(var2 > 0.0F)) && (!(var1 > (float)(this.getWidth() - this.mGutterSize)) || !(var2 < 0.0F))) {
         var3 = false;
      } else {
         var3 = true;
      }

      return var3;
   }

   private void onSecondaryPointerUp(MotionEvent var1) {
      int var2 = var1.getActionIndex();
      if (var1.getPointerId(var2) == this.mActivePointerId) {
         byte var4;
         if (var2 == 0) {
            var4 = 1;
         } else {
            var4 = 0;
         }

         this.mLastMotionX = var1.getX(var4);
         this.mActivePointerId = var1.getPointerId(var4);
         VelocityTracker var3 = this.mVelocityTracker;
         if (var3 != null) {
            var3.clear();
         }
      }

   }

   private boolean pageScrolled(int var1) {
      if (this.mItems.size() == 0) {
         if (this.mFirstLayout) {
            return false;
         } else {
            this.mCalledSuper = false;
            this.onPageScrolled(0, 0.0F, 0);
            if (this.mCalledSuper) {
               return false;
            } else {
               throw new IllegalStateException("onPageScrolled did not call superclass implementation");
            }
         }
      } else {
         ItemInfo var7 = this.infoForCurrentScrollPosition();
         int var6 = this.getClientWidth();
         int var5 = this.mPageMargin;
         float var3 = (float)var5;
         float var2 = (float)var6;
         var3 /= var2;
         int var4 = var7.position;
         var2 = ((float)var1 / var2 - var7.offset) / (var7.widthFactor + var3);
         var1 = (int)((float)(var6 + var5) * var2);
         this.mCalledSuper = false;
         this.onPageScrolled(var4, var2, var1);
         if (this.mCalledSuper) {
            return true;
         } else {
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
         }
      }
   }

   private boolean performDrag(float var1) {
      float var2 = this.mLastMotionX;
      this.mLastMotionX = var1;
      float var3 = (float)this.getScrollX() + (var2 - var1);
      float var4 = (float)this.getClientWidth();
      var1 = this.mFirstOffset * var4;
      var2 = this.mLastOffset * var4;
      ArrayList var10 = this.mItems;
      boolean var8 = false;
      boolean var7 = false;
      boolean var9 = false;
      ItemInfo var13 = (ItemInfo)var10.get(0);
      ArrayList var11 = this.mItems;
      ItemInfo var14 = (ItemInfo)var11.get(var11.size() - 1);
      boolean var5;
      if (var13.position != 0) {
         var1 = var13.offset * var4;
         var5 = false;
      } else {
         var5 = true;
      }

      boolean var6;
      if (var14.position != this.mAdapter.getCount() - 1) {
         var2 = var14.offset * var4;
         var6 = false;
      } else {
         var6 = true;
      }

      if (var3 < var1) {
         var7 = var9;
         if (var5) {
            this.mLeftEdge.onPull(Math.abs(var1 - var3) / var4);
            var7 = true;
         }
      } else {
         var1 = var3;
         if (var3 > var2) {
            var7 = var8;
            if (var6) {
               this.mRightEdge.onPull(Math.abs(var3 - var2) / var4);
               var7 = true;
            }

            var1 = var2;
         }
      }

      var2 = this.mLastMotionX;
      int var12 = (int)var1;
      this.mLastMotionX = var2 + (var1 - (float)var12);
      this.scrollTo(var12, this.getScrollY());
      this.pageScrolled(var12);
      return var7;
   }

   private void recomputeScrollPosition(int var1, int var2, int var3, int var4) {
      if (var2 > 0 && !this.mItems.isEmpty()) {
         if (!this.mScroller.isFinished()) {
            this.mScroller.setFinalX(this.getCurrentItem() * this.getClientWidth());
         } else {
            int var8 = this.getPaddingLeft();
            int var9 = this.getPaddingRight();
            int var7 = this.getPaddingLeft();
            int var6 = this.getPaddingRight();
            this.scrollTo((int)((float)this.getScrollX() / (float)(var2 - var7 - var6 + var4) * (float)(var1 - var8 - var9 + var3)), this.getScrollY());
         }
      } else {
         ItemInfo var10 = this.infoForPosition(this.mCurItem);
         float var5;
         if (var10 != null) {
            var5 = Math.min(var10.offset, this.mLastOffset);
         } else {
            var5 = 0.0F;
         }

         var1 = (int)(var5 * (float)(var1 - this.getPaddingLeft() - this.getPaddingRight()));
         if (var1 != this.getScrollX()) {
            this.completeScroll(false);
            this.scrollTo(var1, this.getScrollY());
         }
      }

   }

   private void removeNonDecorViews() {
      int var2;
      for(int var1 = 0; var1 < this.getChildCount(); var1 = var2 + 1) {
         var2 = var1;
         if (!((LayoutParams)this.getChildAt(var1).getLayoutParams()).isDecor) {
            this.removeViewAt(var1);
            var2 = var1 - 1;
         }
      }

   }

   private void requestParentDisallowInterceptTouchEvent(boolean var1) {
      ViewParent var2 = this.getParent();
      if (var2 != null) {
         var2.requestDisallowInterceptTouchEvent(var1);
      }

   }

   private boolean resetTouch() {
      this.mActivePointerId = -1;
      this.endDrag();
      this.mLeftEdge.onRelease();
      this.mRightEdge.onRelease();
      boolean var1;
      if (!this.mLeftEdge.isFinished() && !this.mRightEdge.isFinished()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   private void scrollToItem(int var1, boolean var2, int var3, boolean var4) {
      ItemInfo var6 = this.infoForPosition(var1);
      int var5;
      if (var6 != null) {
         var5 = (int)((float)this.getClientWidth() * Math.max(this.mFirstOffset, Math.min(var6.offset, this.mLastOffset)));
      } else {
         var5 = 0;
      }

      if (var2) {
         this.smoothScrollTo(var5, 0, var3);
         if (var4) {
            this.dispatchOnPageSelected(var1);
         }
      } else {
         if (var4) {
            this.dispatchOnPageSelected(var1);
         }

         this.completeScroll(false);
         this.scrollTo(var5, 0);
         this.pageScrolled(var5);
      }

   }

   private void setScrollingCacheEnabled(boolean var1) {
      if (this.mScrollingCacheEnabled != var1) {
         this.mScrollingCacheEnabled = var1;
      }

   }

   private void sortChildDrawingOrder() {
      if (this.mDrawingOrder != 0) {
         ArrayList var3 = this.mDrawingOrderedChildren;
         if (var3 == null) {
            this.mDrawingOrderedChildren = new ArrayList();
         } else {
            var3.clear();
         }

         int var2 = this.getChildCount();

         for(int var1 = 0; var1 < var2; ++var1) {
            View var4 = this.getChildAt(var1);
            this.mDrawingOrderedChildren.add(var4);
         }

         Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
      }

   }

   public void addFocusables(ArrayList var1, int var2, int var3) {
      int var6 = var1.size();
      int var5 = this.getDescendantFocusability();
      if (var5 != 393216) {
         for(int var4 = 0; var4 < this.getChildCount(); ++var4) {
            View var7 = this.getChildAt(var4);
            if (var7.getVisibility() == 0) {
               ItemInfo var8 = this.infoForChild(var7);
               if (var8 != null && var8.position == this.mCurItem) {
                  var7.addFocusables(var1, var2, var3);
               }
            }
         }
      }

      if (var5 != 262144 || var6 == var1.size()) {
         if (!this.isFocusable()) {
            return;
         }

         if ((var3 & 1) == 1 && this.isInTouchMode() && !this.isFocusableInTouchMode()) {
            return;
         }

         if (var1 != null) {
            var1.add(this);
         }
      }

   }

   ItemInfo addNewItem(int var1, int var2) {
      ItemInfo var3 = new ItemInfo();
      var3.position = var1;
      var3.object = this.mAdapter.instantiateItem((ViewGroup)this, var1);
      var3.widthFactor = this.mAdapter.getPageWidth(var1);
      if (var2 >= 0 && var2 < this.mItems.size()) {
         this.mItems.add(var2, var3);
      } else {
         this.mItems.add(var3);
      }

      return var3;
   }

   public void addOnAdapterChangeListener(OnAdapterChangeListener var1) {
      if (this.mAdapterChangeListeners == null) {
         this.mAdapterChangeListeners = new ArrayList();
      }

      this.mAdapterChangeListeners.add(var1);
   }

   public void addOnPageChangeListener(OnPageChangeListener var1) {
      if (this.mOnPageChangeListeners == null) {
         this.mOnPageChangeListeners = new ArrayList();
      }

      this.mOnPageChangeListeners.add(var1);
   }

   public void addTouchables(ArrayList var1) {
      for(int var2 = 0; var2 < this.getChildCount(); ++var2) {
         View var3 = this.getChildAt(var2);
         if (var3.getVisibility() == 0) {
            ItemInfo var4 = this.infoForChild(var3);
            if (var4 != null && var4.position == this.mCurItem) {
               var3.addTouchables(var1);
            }
         }
      }

   }

   public void addView(View var1, int var2, ViewGroup.LayoutParams var3) {
      ViewGroup.LayoutParams var4 = var3;
      if (!this.checkLayoutParams(var3)) {
         var4 = this.generateLayoutParams(var3);
      }

      LayoutParams var5 = (LayoutParams)var4;
      var5.isDecor |= isDecorView(var1);
      if (this.mInLayout) {
         if (var5 != null && var5.isDecor) {
            throw new IllegalStateException("Cannot add pager decor view during layout");
         }

         var5.needsMeasure = true;
         this.addViewInLayout(var1, var2, var4);
      } else {
         super.addView(var1, var2, var4);
      }

   }

   public boolean arrowScroll(int var1) {
      boolean var4;
      View var5;
      View var6;
      label86: {
         var6 = this.findFocus();
         var4 = false;
         if (var6 != this) {
            var5 = var6;
            if (var6 == null) {
               break label86;
            }

            ViewParent var9 = var6.getParent();

            boolean var2;
            while(true) {
               if (!(var9 instanceof ViewGroup)) {
                  var2 = false;
                  break;
               }

               if (var9 == this) {
                  var2 = true;
                  break;
               }

               var9 = var9.getParent();
            }

            var5 = var6;
            if (var2) {
               break label86;
            }

            StringBuilder var7 = new StringBuilder();
            var7.append(var6.getClass().getSimpleName());

            for(var9 = var6.getParent(); var9 instanceof ViewGroup; var9 = var9.getParent()) {
               var7.append(" => ").append(var9.getClass().getSimpleName());
            }

            Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + var7.toString());
         }

         var5 = null;
      }

      var6 = FocusFinder.getInstance().findNextFocus(this, var5, var1);
      if (var6 != null && var6 != var5) {
         int var3;
         int var8;
         if (var1 == 17) {
            var3 = this.getChildRectInPagerCoordinates(this.mTempRect, var6).left;
            var8 = this.getChildRectInPagerCoordinates(this.mTempRect, var5).left;
            if (var5 != null && var3 >= var8) {
               var4 = this.pageLeft();
            } else {
               var4 = var6.requestFocus();
            }
         } else if (var1 == 66) {
            var8 = this.getChildRectInPagerCoordinates(this.mTempRect, var6).left;
            var3 = this.getChildRectInPagerCoordinates(this.mTempRect, var5).left;
            if (var5 != null && var8 <= var3) {
               var4 = this.pageRight();
            } else {
               var4 = var6.requestFocus();
            }
         }
      } else if (var1 != 17 && var1 != 1) {
         if (var1 == 66 || var1 == 2) {
            var4 = this.pageRight();
         }
      } else {
         var4 = this.pageLeft();
      }

      if (var4) {
         this.playSoundEffect(SoundEffectConstants.getContantForFocusDirection(var1));
      }

      return var4;
   }

   public boolean beginFakeDrag() {
      if (this.mIsBeingDragged) {
         return false;
      } else {
         this.mFakeDragging = true;
         this.setScrollState(1);
         this.mLastMotionX = 0.0F;
         this.mInitialMotionX = 0.0F;
         VelocityTracker var3 = this.mVelocityTracker;
         if (var3 == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
         } else {
            var3.clear();
         }

         long var1 = SystemClock.uptimeMillis();
         MotionEvent var4 = MotionEvent.obtain(var1, var1, 0, 0.0F, 0.0F, 0);
         this.mVelocityTracker.addMovement(var4);
         var4.recycle();
         this.mFakeDragBeginTime = var1;
         return true;
      }
   }

   protected boolean canScroll(View var1, boolean var2, int var3, int var4, int var5) {
      boolean var12 = var1 instanceof ViewGroup;
      boolean var11 = true;
      if (var12) {
         ViewGroup var13 = (ViewGroup)var1;
         int var8 = var1.getScrollX();
         int var7 = var1.getScrollY();

         for(int var6 = var13.getChildCount() - 1; var6 >= 0; --var6) {
            View var14 = var13.getChildAt(var6);
            int var9 = var4 + var8;
            if (var9 >= var14.getLeft() && var9 < var14.getRight()) {
               int var10 = var5 + var7;
               if (var10 >= var14.getTop() && var10 < var14.getBottom() && this.canScroll(var14, true, var3, var9 - var14.getLeft(), var10 - var14.getTop())) {
                  return true;
               }
            }
         }
      }

      if (var2 && var1.canScrollHorizontally(-var3)) {
         var2 = var11;
      } else {
         var2 = false;
      }

      return var2;
   }

   public boolean canScrollHorizontally(int var1) {
      PagerAdapter var6 = this.mAdapter;
      boolean var5 = false;
      boolean var4 = false;
      if (var6 == null) {
         return false;
      } else {
         int var2 = this.getClientWidth();
         int var3 = this.getScrollX();
         if (var1 < 0) {
            if (var3 > (int)((float)var2 * this.mFirstOffset)) {
               var4 = true;
            }

            return var4;
         } else {
            var4 = var5;
            if (var1 > 0) {
               var4 = var5;
               if (var3 < (int)((float)var2 * this.mLastOffset)) {
                  var4 = true;
               }
            }

            return var4;
         }
      }
   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      boolean var2;
      if (var1 instanceof LayoutParams && super.checkLayoutParams(var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   public void clearOnPageChangeListeners() {
      List var1 = this.mOnPageChangeListeners;
      if (var1 != null) {
         var1.clear();
      }

   }

   public void computeScroll() {
      this.mIsScrollStarted = true;
      if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
         int var3 = this.getScrollX();
         int var2 = this.getScrollY();
         int var4 = this.mScroller.getCurrX();
         int var1 = this.mScroller.getCurrY();
         if (var3 != var4 || var2 != var1) {
            this.scrollTo(var4, var1);
            if (!this.pageScrolled(var4)) {
               this.mScroller.abortAnimation();
               this.scrollTo(0, var1);
            }
         }

         ViewCompat.postInvalidateOnAnimation(this);
      } else {
         this.completeScroll(true);
      }
   }

   void dataSetChanged() {
      int var9 = this.mAdapter.getCount();
      this.mExpectedAdapterCount = var9;
      boolean var4;
      if (this.mItems.size() < this.mOffscreenPageLimit * 2 + 1 && this.mItems.size() < var9) {
         var4 = true;
      } else {
         var4 = false;
      }

      int var2 = this.mCurItem;
      int var3 = 0;
      boolean var1 = false;

      boolean var5;
      boolean var6;
      for(var5 = var4; var3 < this.mItems.size(); var1 = var6) {
         ItemInfo var10 = (ItemInfo)this.mItems.get(var3);
         int var8 = this.mAdapter.getItemPosition(var10.object);
         int var7;
         int var13;
         if (var8 == -1) {
            var13 = var2;
            var7 = var3;
            var6 = var1;
         } else {
            label74: {
               if (var8 == -2) {
                  this.mItems.remove(var3);
                  int var12 = var3 - 1;
                  var4 = var1;
                  if (!var1) {
                     this.mAdapter.startUpdate((ViewGroup)this);
                     var4 = true;
                  }

                  this.mAdapter.destroyItem((ViewGroup)this, var10.position, var10.object);
                  var3 = var12;
                  var1 = var4;
                  if (this.mCurItem == var10.position) {
                     var2 = Math.max(0, Math.min(this.mCurItem, var9 - 1));
                     var1 = var4;
                     var3 = var12;
                  }
               } else {
                  var13 = var2;
                  var7 = var3;
                  var6 = var1;
                  if (var10.position == var8) {
                     break label74;
                  }

                  if (var10.position == this.mCurItem) {
                     var2 = var8;
                  }

                  var10.position = var8;
               }

               var5 = true;
               var13 = var2;
               var7 = var3;
               var6 = var1;
            }
         }

         var3 = var7 + 1;
         var2 = var13;
      }

      if (var1) {
         this.mAdapter.finishUpdate((ViewGroup)this);
      }

      Collections.sort(this.mItems, COMPARATOR);
      if (var5) {
         var3 = this.getChildCount();

         for(int var11 = 0; var11 < var3; ++var11) {
            LayoutParams var14 = (LayoutParams)this.getChildAt(var11).getLayoutParams();
            if (!var14.isDecor) {
               var14.widthFactor = 0.0F;
            }
         }

         this.setCurrentItemInternal(var2, false, true);
         this.requestLayout();
      }

   }

   public boolean dispatchKeyEvent(KeyEvent var1) {
      boolean var2;
      if (!super.dispatchKeyEvent(var1) && !this.executeKeyEvent(var1)) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent var1) {
      if (var1.getEventType() == 4096) {
         return super.dispatchPopulateAccessibilityEvent(var1);
      } else {
         int var3 = this.getChildCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            View var4 = this.getChildAt(var2);
            if (var4.getVisibility() == 0) {
               ItemInfo var5 = this.infoForChild(var4);
               if (var5 != null && var5.position == this.mCurItem && var4.dispatchPopulateAccessibilityEvent(var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   float distanceInfluenceForSnapDuration(float var1) {
      return (float)Math.sin((double)((var1 - 0.5F) * 0.47123894F));
   }

   public void draw(Canvas var1) {
      boolean var3;
      label36: {
         super.draw(var1);
         int var4 = this.getOverScrollMode();
         var3 = false;
         boolean var2 = false;
         if (var4 != 0) {
            label35: {
               if (var4 == 1) {
                  PagerAdapter var8 = this.mAdapter;
                  if (var8 != null && var8.getCount() > 1) {
                     break label35;
                  }
               }

               this.mLeftEdge.finish();
               this.mRightEdge.finish();
               break label36;
            }
         }

         int var10;
         if (!this.mLeftEdge.isFinished()) {
            var10 = var1.save();
            int var9 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
            var4 = this.getWidth();
            var1.rotate(270.0F);
            var1.translate((float)(-var9 + this.getPaddingTop()), this.mFirstOffset * (float)var4);
            this.mLeftEdge.setSize(var9, var4);
            var2 = false | this.mLeftEdge.draw(var1);
            var1.restoreToCount(var10);
         }

         var3 = var2;
         if (!this.mRightEdge.isFinished()) {
            var4 = var1.save();
            var10 = this.getWidth();
            int var5 = this.getHeight();
            int var7 = this.getPaddingTop();
            int var6 = this.getPaddingBottom();
            var1.rotate(90.0F);
            var1.translate((float)(-this.getPaddingTop()), -(this.mLastOffset + 1.0F) * (float)var10);
            this.mRightEdge.setSize(var5 - var7 - var6, var10);
            var3 = var2 | this.mRightEdge.draw(var1);
            var1.restoreToCount(var4);
         }
      }

      if (var3) {
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   protected void drawableStateChanged() {
      super.drawableStateChanged();
      Drawable var1 = this.mMarginDrawable;
      if (var1 != null && var1.isStateful()) {
         var1.setState(this.getDrawableState());
      }

   }

   public void endFakeDrag() {
      if (this.mFakeDragging) {
         if (this.mAdapter != null) {
            VelocityTracker var4 = this.mVelocityTracker;
            var4.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
            int var3 = (int)var4.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int var2 = this.getClientWidth();
            int var1 = this.getScrollX();
            ItemInfo var5 = this.infoForCurrentScrollPosition();
            this.setCurrentItemInternal(this.determineTargetPage(var5.position, ((float)var1 / (float)var2 - var5.offset) / var5.widthFactor, var3, (int)(this.mLastMotionX - this.mInitialMotionX)), true, true, var3);
         }

         this.endDrag();
         this.mFakeDragging = false;
      } else {
         throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
      }
   }

   public boolean executeKeyEvent(KeyEvent var1) {
      boolean var3;
      if (var1.getAction() == 0) {
         int var2 = var1.getKeyCode();
         if (var2 == 21) {
            if (var1.hasModifiers(2)) {
               var3 = this.pageLeft();
            } else {
               var3 = this.arrowScroll(17);
            }

            return var3;
         }

         if (var2 == 22) {
            if (var1.hasModifiers(2)) {
               var3 = this.pageRight();
            } else {
               var3 = this.arrowScroll(66);
            }

            return var3;
         }

         if (var2 == 61) {
            if (var1.hasNoModifiers()) {
               var3 = this.arrowScroll(2);
               return var3;
            }

            if (var1.hasModifiers(1)) {
               var3 = this.arrowScroll(1);
               return var3;
            }
         }
      }

      var3 = false;
      return var3;
   }

   public void fakeDragBy(float var1) {
      if (this.mFakeDragging) {
         if (this.mAdapter != null) {
            this.mLastMotionX += var1;
            float var3 = (float)this.getScrollX() - var1;
            float var4 = (float)this.getClientWidth();
            var1 = this.mFirstOffset * var4;
            float var2 = this.mLastOffset * var4;
            ItemInfo var8 = (ItemInfo)this.mItems.get(0);
            ArrayList var9 = this.mItems;
            ItemInfo var11 = (ItemInfo)var9.get(var9.size() - 1);
            if (var8.position != 0) {
               var1 = var8.offset * var4;
            }

            if (var11.position != this.mAdapter.getCount() - 1) {
               var2 = var11.offset * var4;
            }

            if (!(var3 < var1)) {
               var1 = var3;
               if (var3 > var2) {
                  var1 = var2;
               }
            }

            var2 = this.mLastMotionX;
            int var5 = (int)var1;
            this.mLastMotionX = var2 + (var1 - (float)var5);
            this.scrollTo(var5, this.getScrollY());
            this.pageScrolled(var5);
            long var6 = SystemClock.uptimeMillis();
            MotionEvent var10 = MotionEvent.obtain(this.mFakeDragBeginTime, var6, 2, this.mLastMotionX, 0.0F, 0);
            this.mVelocityTracker.addMovement(var10);
            var10.recycle();
         }
      } else {
         throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
      }
   }

   protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
      return new LayoutParams();
   }

   public ViewGroup.LayoutParams generateLayoutParams(AttributeSet var1) {
      return new LayoutParams(this.getContext(), var1);
   }

   protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      return this.generateDefaultLayoutParams();
   }

   public PagerAdapter getAdapter() {
      return this.mAdapter;
   }

   protected int getChildDrawingOrder(int var1, int var2) {
      int var3 = var2;
      if (this.mDrawingOrder == 2) {
         var3 = var1 - 1 - var2;
      }

      return ((LayoutParams)((View)this.mDrawingOrderedChildren.get(var3)).getLayoutParams()).childIndex;
   }

   public int getCurrentItem() {
      return this.mCurItem;
   }

   public int getOffscreenPageLimit() {
      return this.mOffscreenPageLimit;
   }

   public int getPageMargin() {
      return this.mPageMargin;
   }

   ItemInfo infoForAnyChild(View var1) {
      while(true) {
         ViewParent var2 = var1.getParent();
         if (var2 != this) {
            if (var2 != null && var2 instanceof View) {
               var1 = (View)var2;
               continue;
            }

            return null;
         }

         return this.infoForChild(var1);
      }
   }

   ItemInfo infoForChild(View var1) {
      for(int var2 = 0; var2 < this.mItems.size(); ++var2) {
         ItemInfo var3 = (ItemInfo)this.mItems.get(var2);
         if (this.mAdapter.isViewFromObject(var1, var3.object)) {
            return var3;
         }
      }

      return null;
   }

   ItemInfo infoForPosition(int var1) {
      for(int var2 = 0; var2 < this.mItems.size(); ++var2) {
         ItemInfo var3 = (ItemInfo)this.mItems.get(var2);
         if (var3.position == var1) {
            return var3;
         }
      }

      return null;
   }

   void initViewPager() {
      this.setWillNotDraw(false);
      this.setDescendantFocusability(262144);
      this.setFocusable(true);
      Context var2 = this.getContext();
      this.mScroller = new Scroller(var2, sInterpolator);
      ViewConfiguration var3 = ViewConfiguration.get(var2);
      float var1 = var2.getResources().getDisplayMetrics().density;
      this.mTouchSlop = var3.getScaledPagingTouchSlop();
      this.mMinimumVelocity = (int)(400.0F * var1);
      this.mMaximumVelocity = var3.getScaledMaximumFlingVelocity();
      this.mLeftEdge = new EdgeEffect(var2);
      this.mRightEdge = new EdgeEffect(var2);
      this.mFlingDistance = (int)(25.0F * var1);
      this.mCloseEnough = (int)(2.0F * var1);
      this.mDefaultGutterSize = (int)(var1 * 16.0F);
      ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate(this));
      if (ViewCompat.getImportantForAccessibility(this) == 0) {
         ViewCompat.setImportantForAccessibility(this, 1);
      }

      ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener(this) {
         private final Rect mTempRect;
         final ViewPager this$0;

         {
            this.this$0 = var1;
            this.mTempRect = new Rect();
         }

         public WindowInsetsCompat onApplyWindowInsets(View var1, WindowInsetsCompat var2) {
            WindowInsetsCompat var5 = ViewCompat.onApplyWindowInsets(var1, var2);
            if (var5.isConsumed()) {
               return var5;
            } else {
               Rect var7 = this.mTempRect;
               var7.left = var5.getSystemWindowInsetLeft();
               var7.top = var5.getSystemWindowInsetTop();
               var7.right = var5.getSystemWindowInsetRight();
               var7.bottom = var5.getSystemWindowInsetBottom();
               int var3 = 0;

               for(int var4 = this.this$0.getChildCount(); var3 < var4; ++var3) {
                  WindowInsetsCompat var6 = ViewCompat.dispatchApplyWindowInsets(this.this$0.getChildAt(var3), var5);
                  var7.left = Math.min(var6.getSystemWindowInsetLeft(), var7.left);
                  var7.top = Math.min(var6.getSystemWindowInsetTop(), var7.top);
                  var7.right = Math.min(var6.getSystemWindowInsetRight(), var7.right);
                  var7.bottom = Math.min(var6.getSystemWindowInsetBottom(), var7.bottom);
               }

               return var5.replaceSystemWindowInsets(var7.left, var7.top, var7.right, var7.bottom);
            }
         }
      });
   }

   public boolean isFakeDragging() {
      return this.mFakeDragging;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mFirstLayout = true;
   }

   protected void onDetachedFromWindow() {
      this.removeCallbacks(this.mEndScrollRunnable);
      Scroller var1 = this.mScroller;
      if (var1 != null && !var1.isFinished()) {
         this.mScroller.abortAnimation();
      }

      super.onDetachedFromWindow();
   }

   protected void onDraw(Canvas var1) {
      super.onDraw(var1);
      if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
         int var10 = this.getScrollX();
         int var12 = this.getWidth();
         float var2 = (float)this.mPageMargin;
         float var6 = (float)var12;
         float var3 = var2 / var6;
         ArrayList var13 = this.mItems;
         int var8 = 0;
         ItemInfo var14 = (ItemInfo)var13.get(0);
         var2 = var14.offset;
         int var9 = this.mItems.size();
         int var7 = var14.position;

         for(int var11 = ((ItemInfo)this.mItems.get(var9 - 1)).position; var7 < var11; ++var7) {
            while(var7 > var14.position && var8 < var9) {
               var13 = this.mItems;
               ++var8;
               var14 = (ItemInfo)var13.get(var8);
            }

            float var4;
            if (var7 == var14.position) {
               var4 = (var14.offset + var14.widthFactor) * var6;
               var2 = var14.offset + var14.widthFactor + var3;
            } else {
               float var5 = this.mAdapter.getPageWidth(var7);
               var4 = var2 + var5 + var3;
               var5 = (var2 + var5) * var6;
               var2 = var4;
               var4 = var5;
            }

            if ((float)this.mPageMargin + var4 > (float)var10) {
               this.mMarginDrawable.setBounds(Math.round(var4), this.mTopPageBounds, Math.round((float)this.mPageMargin + var4), this.mBottomPageBounds);
               this.mMarginDrawable.draw(var1);
            }

            if (var4 > (float)(var10 + var12)) {
               break;
            }
         }
      }

   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      int var7 = var1.getAction() & 255;
      if (var7 != 3 && var7 != 1) {
         if (var7 != 0) {
            if (this.mIsBeingDragged) {
               return true;
            }

            if (this.mIsUnableToDrag) {
               return false;
            }
         }

         float var2;
         if (var7 != 0) {
            if (var7 != 2) {
               if (var7 == 6) {
                  this.onSecondaryPointerUp(var1);
               }
            } else {
               var7 = this.mActivePointerId;
               if (var7 != -1) {
                  var7 = var1.findPointerIndex(var7);
                  float var3 = var1.getX(var7);
                  float var5 = var3 - this.mLastMotionX;
                  float var6 = Math.abs(var5);
                  float var4 = var1.getY(var7);
                  var2 = Math.abs(var4 - this.mInitialMotionY);
                  float var9;
                  var7 = (var9 = var5 - 0.0F) == 0.0F ? 0 : (var9 < 0.0F ? -1 : 1);
                  if (var7 != 0 && !this.isGutterDrag(this.mLastMotionX, var5) && this.canScroll(this, false, (int)var5, (int)var3, (int)var4)) {
                     this.mLastMotionX = var3;
                     this.mLastMotionY = var4;
                     this.mIsUnableToDrag = true;
                     return false;
                  }

                  int var8 = this.mTouchSlop;
                  if (var6 > (float)var8 && var6 * 0.5F > var2) {
                     this.mIsBeingDragged = true;
                     this.requestParentDisallowInterceptTouchEvent(true);
                     this.setScrollState(1);
                     var2 = this.mInitialMotionX;
                     var5 = (float)this.mTouchSlop;
                     if (var7 > 0) {
                        var2 += var5;
                     } else {
                        var2 -= var5;
                     }

                     this.mLastMotionX = var2;
                     this.mLastMotionY = var4;
                     this.setScrollingCacheEnabled(true);
                  } else if (var2 > (float)var8) {
                     this.mIsUnableToDrag = true;
                  }

                  if (this.mIsBeingDragged && this.performDrag(var3)) {
                     ViewCompat.postInvalidateOnAnimation(this);
                  }
               }
            }
         } else {
            var2 = var1.getX();
            this.mInitialMotionX = var2;
            this.mLastMotionX = var2;
            var2 = var1.getY();
            this.mInitialMotionY = var2;
            this.mLastMotionY = var2;
            this.mActivePointerId = var1.getPointerId(0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            this.mScroller.computeScrollOffset();
            if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
               this.mScroller.abortAnimation();
               this.mPopulatePending = false;
               this.populate();
               this.mIsBeingDragged = true;
               this.requestParentDisallowInterceptTouchEvent(true);
               this.setScrollState(1);
            } else {
               this.completeScroll(false);
               this.mIsBeingDragged = false;
            }
         }

         if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
         }

         this.mVelocityTracker.addMovement(var1);
         return this.mIsBeingDragged;
      } else {
         this.resetTouch();
         return false;
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      int var14 = this.getChildCount();
      int var15 = var4 - var2;
      int var16 = var5 - var3;
      var3 = this.getPaddingLeft();
      var2 = this.getPaddingTop();
      var5 = this.getPaddingRight();
      var4 = this.getPaddingBottom();
      int var17 = this.getScrollX();
      int var9 = 0;

      int var7;
      int var8;
      View var18;
      for(var8 = 0; var9 < var14; var8 = var7) {
         var18 = this.getChildAt(var9);
         int var13 = var3;
         int var12 = var2;
         int var11 = var5;
         int var10 = var4;
         var7 = var8;
         if (var18.getVisibility() != 8) {
            LayoutParams var19 = (LayoutParams)var18.getLayoutParams();
            var13 = var3;
            var12 = var2;
            var11 = var5;
            var10 = var4;
            var7 = var8;
            if (var19.isDecor) {
               label65: {
                  var7 = var19.gravity & 7;
                  var11 = var19.gravity & 112;
                  if (var7 != 1) {
                     if (var7 == 3) {
                        var7 = var18.getMeasuredWidth() + var3;
                        var10 = var3;
                        var3 = var7;
                        break label65;
                     }

                     if (var7 != 5) {
                        var10 = var3;
                        var3 = var3;
                        break label65;
                     }

                     var7 = var15 - var5 - var18.getMeasuredWidth();
                     var5 += var18.getMeasuredWidth();
                  } else {
                     var7 = Math.max((var15 - var18.getMeasuredWidth()) / 2, var3);
                  }

                  var10 = var7;
               }

               if (var11 != 16) {
                  if (var11 != 48) {
                     if (var11 != 80) {
                        var7 = var2;
                        var2 = var2;
                     } else {
                        var7 = var16 - var4 - var18.getMeasuredHeight();
                        var4 += var18.getMeasuredHeight();
                     }
                  } else {
                     var11 = var18.getMeasuredHeight() + var2;
                     var7 = var2;
                     var2 = var11;
                  }
               } else {
                  var7 = Math.max((var16 - var18.getMeasuredHeight()) / 2, var2);
               }

               var10 += var17;
               var18.layout(var10, var7, var18.getMeasuredWidth() + var10, var7 + var18.getMeasuredHeight());
               var7 = var8 + 1;
               var10 = var4;
               var11 = var5;
               var12 = var2;
               var13 = var3;
            }
         }

         ++var9;
         var3 = var13;
         var2 = var12;
         var5 = var11;
         var4 = var10;
      }

      for(var7 = 0; var7 < var14; ++var7) {
         var18 = this.getChildAt(var7);
         if (var18.getVisibility() != 8) {
            LayoutParams var20 = (LayoutParams)var18.getLayoutParams();
            if (!var20.isDecor) {
               ItemInfo var21 = this.infoForChild(var18);
               if (var21 != null) {
                  float var6 = (float)(var15 - var3 - var5);
                  var9 = (int)(var21.offset * var6) + var3;
                  if (var20.needsMeasure) {
                     var20.needsMeasure = false;
                     var18.measure(MeasureSpec.makeMeasureSpec((int)(var6 * var20.widthFactor), 1073741824), MeasureSpec.makeMeasureSpec(var16 - var2 - var4, 1073741824));
                  }

                  var18.layout(var9, var2, var18.getMeasuredWidth() + var9, var18.getMeasuredHeight() + var2);
               }
            }
         }
      }

      this.mTopPageBounds = var2;
      this.mBottomPageBounds = var16 - var4;
      this.mDecorChildCount = var8;
      if (this.mFirstLayout) {
         this.scrollToItem(this.mCurItem, false, 0, false);
      }

      this.mFirstLayout = false;
   }

   protected void onMeasure(int var1, int var2) {
      this.setMeasuredDimension(getDefaultSize(0, var1), getDefaultSize(0, var2));
      var1 = this.getMeasuredWidth();
      this.mGutterSize = Math.min(var1 / 10, this.mDefaultGutterSize);
      var1 = var1 - this.getPaddingLeft() - this.getPaddingRight();
      var2 = this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom();
      int var11 = this.getChildCount();
      int var5 = 0;

      while(true) {
         boolean var8 = true;
         int var10 = 1073741824;
         int var3;
         if (var5 >= var11) {
            this.mChildWidthMeasureSpec = MeasureSpec.makeMeasureSpec(var1, 1073741824);
            this.mChildHeightMeasureSpec = MeasureSpec.makeMeasureSpec(var2, 1073741824);
            this.mInLayout = true;
            this.populate();
            var2 = 0;
            this.mInLayout = false;

            for(var3 = this.getChildCount(); var2 < var3; ++var2) {
               View var15 = this.getChildAt(var2);
               if (var15.getVisibility() != 8) {
                  LayoutParams var16 = (LayoutParams)var15.getLayoutParams();
                  if (var16 == null || !var16.isDecor) {
                     var15.measure(MeasureSpec.makeMeasureSpec((int)((float)var1 * var16.widthFactor), 1073741824), this.mChildHeightMeasureSpec);
                  }
               }
            }

            return;
         }

         View var13 = this.getChildAt(var5);
         var3 = var1;
         int var4 = var2;
         if (var13.getVisibility() != 8) {
            LayoutParams var12 = (LayoutParams)var13.getLayoutParams();
            var3 = var1;
            var4 = var2;
            if (var12 != null) {
               var3 = var1;
               var4 = var2;
               if (var12.isDecor) {
                  var3 = var12.gravity & 7;
                  var4 = var12.gravity & 112;
                  boolean var7;
                  if (var4 != 48 && var4 != 80) {
                     var7 = false;
                  } else {
                     var7 = true;
                  }

                  boolean var6 = var8;
                  if (var3 != 3) {
                     if (var3 == 5) {
                        var6 = var8;
                     } else {
                        var6 = false;
                     }
                  }

                  var4 = Integer.MIN_VALUE;
                  if (var7) {
                     var3 = Integer.MIN_VALUE;
                     var4 = 1073741824;
                  } else if (var6) {
                     var3 = 1073741824;
                  } else {
                     var3 = Integer.MIN_VALUE;
                  }

                  int var14;
                  if (var12.width != -2) {
                     if (var12.width != -1) {
                        var4 = var12.width;
                     } else {
                        var4 = var1;
                     }

                     var14 = 1073741824;
                  } else {
                     var14 = var4;
                     var4 = var1;
                  }

                  if (var12.height != -2) {
                     if (var12.height != -1) {
                        var3 = var12.height;
                     } else {
                        var3 = var2;
                     }
                  } else {
                     var10 = var3;
                     var3 = var2;
                  }

                  var13.measure(MeasureSpec.makeMeasureSpec(var4, var14), MeasureSpec.makeMeasureSpec(var3, var10));
                  if (var7) {
                     var4 = var2 - var13.getMeasuredHeight();
                     var3 = var1;
                  } else {
                     var3 = var1;
                     var4 = var2;
                     if (var6) {
                        var3 = var1 - var13.getMeasuredWidth();
                        var4 = var2;
                     }
                  }
               }
            }
         }

         ++var5;
         var1 = var3;
         var2 = var4;
      }
   }

   protected void onPageScrolled(int var1, float var2, int var3) {
      int var4 = this.mDecorChildCount;
      byte var9 = 0;
      if (var4 > 0) {
         int var11 = this.getScrollX();
         var4 = this.getPaddingLeft();
         int var5 = this.getPaddingRight();
         int var10 = this.getWidth();
         int var12 = this.getChildCount();

         for(int var7 = 0; var7 < var12; ++var7) {
            View var14 = this.getChildAt(var7);
            LayoutParams var13 = (LayoutParams)var14.getLayoutParams();
            if (var13.isDecor) {
               int var6 = var13.gravity & 7;
               if (var6 != 1) {
                  if (var6 != 3) {
                     if (var6 != 5) {
                        var6 = var4;
                        var4 = var4;
                     } else {
                        var6 = var10 - var5 - var14.getMeasuredWidth();
                        var5 += var14.getMeasuredWidth();
                     }
                  } else {
                     int var8 = var14.getWidth() + var4;
                     var6 = var4;
                     var4 = var8;
                  }
               } else {
                  var6 = Math.max((var10 - var14.getMeasuredWidth()) / 2, var4);
               }

               var6 = var6 + var11 - var14.getLeft();
               if (var6 != 0) {
                  var14.offsetLeftAndRight(var6);
               }
            }
         }
      }

      this.dispatchOnPageScrolled(var1, var2, var3);
      if (this.mPageTransformer != null) {
         var4 = this.getScrollX();
         var3 = this.getChildCount();

         for(var1 = var9; var1 < var3; ++var1) {
            View var15 = this.getChildAt(var1);
            if (!((LayoutParams)var15.getLayoutParams()).isDecor) {
               var2 = (float)(var15.getLeft() - var4) / (float)this.getClientWidth();
               this.mPageTransformer.transformPage(var15, var2);
            }
         }
      }

      this.mCalledSuper = true;
   }

   protected boolean onRequestFocusInDescendants(int var1, Rect var2) {
      int var3 = this.getChildCount();
      int var4 = -1;
      byte var5;
      if ((var1 & 2) != 0) {
         var4 = var3;
         var3 = 0;
         var5 = 1;
      } else {
         --var3;
         var5 = -1;
      }

      for(; var3 != var4; var3 += var5) {
         View var6 = this.getChildAt(var3);
         if (var6.getVisibility() == 0) {
            ItemInfo var7 = this.infoForChild(var6);
            if (var7 != null && var7.position == this.mCurItem && var6.requestFocus(var1, var2)) {
               return true;
            }
         }
      }

      return false;
   }

   public void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         PagerAdapter var3 = this.mAdapter;
         if (var3 != null) {
            var3.restoreState(var2.adapterState, var2.loader);
            this.setCurrentItemInternal(var2.position, false, true);
         } else {
            this.mRestoredCurItem = var2.position;
            this.mRestoredAdapterState = var2.adapterState;
            this.mRestoredClassLoader = var2.loader;
         }

      }
   }

   public Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      var1.position = this.mCurItem;
      PagerAdapter var2 = this.mAdapter;
      if (var2 != null) {
         var1.adapterState = var2.saveState();
      }

      return var1;
   }

   protected void onSizeChanged(int var1, int var2, int var3, int var4) {
      super.onSizeChanged(var1, var2, var3, var4);
      if (var1 != var3) {
         var2 = this.mPageMargin;
         this.recomputeScrollPosition(var1, var3, var2, var2);
      }

   }

   public boolean onTouchEvent(MotionEvent var1) {
      if (this.mFakeDragging) {
         return true;
      } else {
         int var6 = var1.getAction();
         boolean var9 = false;
         if (var6 == 0 && var1.getEdgeFlags() != 0) {
            return false;
         } else {
            PagerAdapter var10 = this.mAdapter;
            if (var10 != null && var10.getCount() != 0) {
               if (this.mVelocityTracker == null) {
                  this.mVelocityTracker = VelocityTracker.obtain();
               }

               this.mVelocityTracker.addMovement(var1);
               var6 = var1.getAction() & 255;
               float var2;
               if (var6 != 0) {
                  float var3;
                  if (var6 != 1) {
                     if (var6 != 2) {
                        if (var6 != 3) {
                           if (var6 != 5) {
                              if (var6 == 6) {
                                 this.onSecondaryPointerUp(var1);
                                 this.mLastMotionX = var1.getX(var1.findPointerIndex(this.mActivePointerId));
                              }
                           } else {
                              var6 = var1.getActionIndex();
                              this.mLastMotionX = var1.getX(var6);
                              this.mActivePointerId = var1.getPointerId(var6);
                           }
                        } else if (this.mIsBeingDragged) {
                           this.scrollToItem(this.mCurItem, true, 0, false);
                           var9 = this.resetTouch();
                        }
                     } else {
                        label64: {
                           if (!this.mIsBeingDragged) {
                              var6 = var1.findPointerIndex(this.mActivePointerId);
                              if (var6 == -1) {
                                 var9 = this.resetTouch();
                                 break label64;
                              }

                              var2 = var1.getX(var6);
                              float var5 = Math.abs(var2 - this.mLastMotionX);
                              var3 = var1.getY(var6);
                              float var4 = Math.abs(var3 - this.mLastMotionY);
                              if (var5 > (float)this.mTouchSlop && var5 > var4) {
                                 this.mIsBeingDragged = true;
                                 this.requestParentDisallowInterceptTouchEvent(true);
                                 var4 = this.mInitialMotionX;
                                 if (var2 - var4 > 0.0F) {
                                    var2 = var4 + (float)this.mTouchSlop;
                                 } else {
                                    var2 = var4 - (float)this.mTouchSlop;
                                 }

                                 this.mLastMotionX = var2;
                                 this.mLastMotionY = var3;
                                 this.setScrollState(1);
                                 this.setScrollingCacheEnabled(true);
                                 ViewParent var11 = this.getParent();
                                 if (var11 != null) {
                                    var11.requestDisallowInterceptTouchEvent(true);
                                 }
                              }
                           }

                           if (this.mIsBeingDragged) {
                              var9 = false | this.performDrag(var1.getX(var1.findPointerIndex(this.mActivePointerId)));
                           }
                        }
                     }
                  } else if (this.mIsBeingDragged) {
                     VelocityTracker var12 = this.mVelocityTracker;
                     var12.computeCurrentVelocity(1000, (float)this.mMaximumVelocity);
                     int var7 = (int)var12.getXVelocity(this.mActivePointerId);
                     this.mPopulatePending = true;
                     var6 = this.getClientWidth();
                     int var8 = this.getScrollX();
                     ItemInfo var13 = this.infoForCurrentScrollPosition();
                     var3 = (float)this.mPageMargin;
                     var2 = (float)var6;
                     var3 /= var2;
                     this.setCurrentItemInternal(this.determineTargetPage(var13.position, ((float)var8 / var2 - var13.offset) / (var13.widthFactor + var3), var7, (int)(var1.getX(var1.findPointerIndex(this.mActivePointerId)) - this.mInitialMotionX)), true, true, var7);
                     var9 = this.resetTouch();
                  }
               } else {
                  this.mScroller.abortAnimation();
                  this.mPopulatePending = false;
                  this.populate();
                  var2 = var1.getX();
                  this.mInitialMotionX = var2;
                  this.mLastMotionX = var2;
                  var2 = var1.getY();
                  this.mInitialMotionY = var2;
                  this.mLastMotionY = var2;
                  this.mActivePointerId = var1.getPointerId(0);
               }

               if (var9) {
                  ViewCompat.postInvalidateOnAnimation(this);
               }

               return true;
            } else {
               return false;
            }
         }
      }
   }

   boolean pageLeft() {
      int var1 = this.mCurItem;
      if (var1 > 0) {
         this.setCurrentItem(var1 - 1, true);
         return true;
      } else {
         return false;
      }
   }

   boolean pageRight() {
      PagerAdapter var1 = this.mAdapter;
      if (var1 != null && this.mCurItem < var1.getCount() - 1) {
         this.setCurrentItem(this.mCurItem + 1, true);
         return true;
      } else {
         return false;
      }
   }

   void populate() {
      this.populate(this.mCurItem);
   }

   void populate(int var1) {
      int var5 = this.mCurItem;
      ItemInfo var14;
      if (var5 != var1) {
         var14 = this.infoForPosition(var5);
         this.mCurItem = var1;
      } else {
         var14 = null;
      }

      if (this.mAdapter == null) {
         this.sortChildDrawingOrder();
      } else if (this.mPopulatePending) {
         this.sortChildDrawingOrder();
      } else if (this.getWindowToken() != null) {
         this.mAdapter.startUpdate((ViewGroup)this);
         var1 = this.mOffscreenPageLimit;
         int var11 = Math.max(0, this.mCurItem - var1);
         int var9 = this.mAdapter.getCount();
         int var10 = Math.min(var9 - 1, this.mCurItem + var1);
         if (var9 != this.mExpectedAdapterCount) {
            String var21;
            try {
               var21 = this.getResources().getResourceName(this.getId());
            } catch (Resources.NotFoundException var17) {
               var21 = Integer.toHexString(this.getId());
            }

            throw new IllegalStateException("The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: " + this.mExpectedAdapterCount + ", found: " + var9 + " Pager id: " + var21 + " Pager class: " + this.getClass() + " Problematic adapter: " + this.mAdapter.getClass());
         } else {
            var1 = 0;

            ItemInfo var13;
            while(true) {
               if (var1 < this.mItems.size()) {
                  var13 = (ItemInfo)this.mItems.get(var1);
                  if (var13.position < this.mCurItem) {
                     ++var1;
                     continue;
                  }

                  if (var13.position == this.mCurItem) {
                     break;
                  }
               }

               var13 = null;
               break;
            }

            ItemInfo var15 = var13;
            if (var13 == null) {
               var15 = var13;
               if (var9 > 0) {
                  var15 = this.addNewItem(this.mCurItem, var1);
               }
            }

            if (var15 != null) {
               var5 = var1 - 1;
               if (var5 >= 0) {
                  var13 = (ItemInfo)this.mItems.get(var5);
               } else {
                  var13 = null;
               }

               int var12 = this.getClientWidth();
               float var4;
               if (var12 <= 0) {
                  var4 = 0.0F;
               } else {
                  var4 = 2.0F - var15.widthFactor + (float)this.getPaddingLeft() / (float)var12;
               }

               int var8 = this.mCurItem - 1;
               float var3 = 0.0F;

               float var2;
               int var6;
               int var7;
               ItemInfo var16;
               for(var16 = var13; var8 >= 0; var3 = var2) {
                  label202: {
                     label241: {
                        if (var3 >= var4 && var8 < var11) {
                           if (var16 == null) {
                              break;
                           }

                           var7 = var1;
                           var6 = var5;
                           var13 = var16;
                           var2 = var3;
                           if (var8 != var16.position) {
                              break label202;
                           }

                           var7 = var1;
                           var6 = var5;
                           var13 = var16;
                           var2 = var3;
                           if (var16.scrolling) {
                              break label202;
                           }

                           this.mItems.remove(var5);
                           this.mAdapter.destroyItem((ViewGroup)this, var8, var16.object);
                           --var5;
                           --var1;
                           var6 = var1;
                           var7 = var5;
                           var2 = var3;
                           if (var5 >= 0) {
                              var13 = (ItemInfo)this.mItems.get(var5);
                              var2 = var3;
                              break label241;
                           }
                        } else if (var16 != null && var8 == var16.position) {
                           var3 += var16.widthFactor;
                           --var5;
                           var6 = var1;
                           var7 = var5;
                           var2 = var3;
                           if (var5 >= 0) {
                              var13 = (ItemInfo)this.mItems.get(var5);
                              var2 = var3;
                              break label241;
                           }
                        } else {
                           var3 += this.addNewItem(var8, var5 + 1).widthFactor;
                           ++var1;
                           var6 = var1;
                           var7 = var5;
                           var2 = var3;
                           if (var5 >= 0) {
                              var13 = (ItemInfo)this.mItems.get(var5);
                              var2 = var3;
                              break label241;
                           }
                        }

                        var13 = null;
                        var5 = var7;
                        var1 = var6;
                     }

                     var6 = var5;
                     var7 = var1;
                  }

                  --var8;
                  var1 = var7;
                  var5 = var6;
                  var16 = var13;
               }

               var3 = var15.widthFactor;
               var6 = var1 + 1;
               if (var3 < 2.0F) {
                  if (var6 < this.mItems.size()) {
                     var13 = (ItemInfo)this.mItems.get(var6);
                  } else {
                     var13 = null;
                  }

                  if (var12 <= 0) {
                     var4 = 0.0F;
                  } else {
                     var4 = (float)this.getPaddingRight() / (float)var12 + 2.0F;
                  }

                  var5 = this.mCurItem;
                  var16 = var13;

                  while(true) {
                     var7 = var5 + 1;
                     if (var7 >= var9) {
                        break;
                     }

                     label242: {
                        if (var3 >= var4 && var7 > var10) {
                           if (var16 == null) {
                              break;
                           }

                           var2 = var3;
                           var5 = var6;
                           var13 = var16;
                           if (var7 != var16.position) {
                              break label242;
                           }

                           var2 = var3;
                           var5 = var6;
                           var13 = var16;
                           if (var16.scrolling) {
                              break label242;
                           }

                           this.mItems.remove(var6);
                           this.mAdapter.destroyItem((ViewGroup)this, var7, var16.object);
                           var2 = var3;
                           var5 = var6;
                           if (var6 < this.mItems.size()) {
                              var13 = (ItemInfo)this.mItems.get(var6);
                              var2 = var3;
                              var5 = var6;
                              break label242;
                           }
                        } else if (var16 != null && var7 == var16.position) {
                           var3 += var16.widthFactor;
                           ++var6;
                           var2 = var3;
                           var5 = var6;
                           if (var6 < this.mItems.size()) {
                              var13 = (ItemInfo)this.mItems.get(var6);
                              var2 = var3;
                              var5 = var6;
                              break label242;
                           }
                        } else {
                           var13 = this.addNewItem(var7, var6);
                           ++var6;
                           var3 += var13.widthFactor;
                           var2 = var3;
                           var5 = var6;
                           if (var6 < this.mItems.size()) {
                              var13 = (ItemInfo)this.mItems.get(var6);
                              var5 = var6;
                              var2 = var3;
                              break label242;
                           }
                        }

                        var13 = null;
                     }

                     var3 = var2;
                     var6 = var5;
                     var16 = var13;
                     var5 = var7;
                  }
               }

               this.calculatePageOffsets(var15, var1, var14);
               this.mAdapter.setPrimaryItem((ViewGroup)this, this.mCurItem, var15.object);
            }

            this.mAdapter.finishUpdate((ViewGroup)this);
            var5 = this.getChildCount();

            for(var1 = 0; var1 < var5; ++var1) {
               View var18 = this.getChildAt(var1);
               LayoutParams var19 = (LayoutParams)var18.getLayoutParams();
               var19.childIndex = var1;
               if (!var19.isDecor && var19.widthFactor == 0.0F) {
                  var14 = this.infoForChild(var18);
                  if (var14 != null) {
                     var19.widthFactor = var14.widthFactor;
                     var19.position = var14.position;
                  }
               }
            }

            this.sortChildDrawingOrder();
            if (this.hasFocus()) {
               View var20 = this.findFocus();
               if (var20 != null) {
                  var13 = this.infoForAnyChild(var20);
               } else {
                  var13 = null;
               }

               if (var13 == null || var13.position != this.mCurItem) {
                  for(var1 = 0; var1 < this.getChildCount(); ++var1) {
                     var20 = this.getChildAt(var1);
                     var14 = this.infoForChild(var20);
                     if (var14 != null && var14.position == this.mCurItem && var20.requestFocus(2)) {
                        break;
                     }
                  }
               }
            }

         }
      }
   }

   public void removeOnAdapterChangeListener(OnAdapterChangeListener var1) {
      List var2 = this.mAdapterChangeListeners;
      if (var2 != null) {
         var2.remove(var1);
      }

   }

   public void removeOnPageChangeListener(OnPageChangeListener var1) {
      List var2 = this.mOnPageChangeListeners;
      if (var2 != null) {
         var2.remove(var1);
      }

   }

   public void removeView(View var1) {
      if (this.mInLayout) {
         this.removeViewInLayout(var1);
      } else {
         super.removeView(var1);
      }

   }

   public void setAdapter(PagerAdapter var1) {
      PagerAdapter var6 = this.mAdapter;
      byte var3 = 0;
      int var2;
      if (var6 != null) {
         var6.setViewPagerObserver((DataSetObserver)null);
         this.mAdapter.startUpdate((ViewGroup)this);

         for(var2 = 0; var2 < this.mItems.size(); ++var2) {
            ItemInfo var8 = (ItemInfo)this.mItems.get(var2);
            this.mAdapter.destroyItem((ViewGroup)this, var8.position, var8.object);
         }

         this.mAdapter.finishUpdate((ViewGroup)this);
         this.mItems.clear();
         this.removeNonDecorViews();
         this.mCurItem = 0;
         this.scrollTo(0, 0);
      }

      var6 = this.mAdapter;
      this.mAdapter = var1;
      this.mExpectedAdapterCount = 0;
      if (var1 != null) {
         if (this.mObserver == null) {
            this.mObserver = new PagerObserver(this);
         }

         this.mAdapter.setViewPagerObserver(this.mObserver);
         this.mPopulatePending = false;
         boolean var5 = this.mFirstLayout;
         this.mFirstLayout = true;
         this.mExpectedAdapterCount = this.mAdapter.getCount();
         if (this.mRestoredCurItem >= 0) {
            this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
            this.setCurrentItemInternal(this.mRestoredCurItem, false, true);
            this.mRestoredCurItem = -1;
            this.mRestoredAdapterState = null;
            this.mRestoredClassLoader = null;
         } else if (!var5) {
            this.populate();
         } else {
            this.requestLayout();
         }
      }

      List var7 = this.mAdapterChangeListeners;
      if (var7 != null && !var7.isEmpty()) {
         int var4 = this.mAdapterChangeListeners.size();

         for(var2 = var3; var2 < var4; ++var2) {
            ((OnAdapterChangeListener)this.mAdapterChangeListeners.get(var2)).onAdapterChanged(this, var6, var1);
         }
      }

   }

   public void setCurrentItem(int var1) {
      this.mPopulatePending = false;
      this.setCurrentItemInternal(var1, this.mFirstLayout ^ true, false);
   }

   public void setCurrentItem(int var1, boolean var2) {
      this.mPopulatePending = false;
      this.setCurrentItemInternal(var1, var2, false);
   }

   void setCurrentItemInternal(int var1, boolean var2, boolean var3) {
      this.setCurrentItemInternal(var1, var2, var3, 0);
   }

   void setCurrentItemInternal(int var1, boolean var2, boolean var3, int var4) {
      PagerAdapter var8 = this.mAdapter;
      boolean var7 = false;
      if (var8 != null && var8.getCount() > 0) {
         if (!var3 && this.mCurItem == var1 && this.mItems.size() != 0) {
            this.setScrollingCacheEnabled(false);
         } else {
            int var5;
            if (var1 < 0) {
               var5 = 0;
            } else {
               var5 = var1;
               if (var1 >= this.mAdapter.getCount()) {
                  var5 = this.mAdapter.getCount() - 1;
               }
            }

            int var6 = this.mOffscreenPageLimit;
            var1 = this.mCurItem;
            if (var5 > var1 + var6 || var5 < var1 - var6) {
               for(var1 = 0; var1 < this.mItems.size(); ++var1) {
                  ((ItemInfo)this.mItems.get(var1)).scrolling = true;
               }
            }

            var3 = var7;
            if (this.mCurItem != var5) {
               var3 = true;
            }

            if (this.mFirstLayout) {
               this.mCurItem = var5;
               if (var3) {
                  this.dispatchOnPageSelected(var5);
               }

               this.requestLayout();
            } else {
               this.populate(var5);
               this.scrollToItem(var5, var2, var4, var3);
            }

         }
      } else {
         this.setScrollingCacheEnabled(false);
      }
   }

   OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener var1) {
      OnPageChangeListener var2 = this.mInternalPageChangeListener;
      this.mInternalPageChangeListener = var1;
      return var2;
   }

   public void setOffscreenPageLimit(int var1) {
      int var2 = var1;
      if (var1 < 1) {
         Log.w("ViewPager", "Requested offscreen page limit " + var1 + " too small; defaulting to " + 1);
         var2 = 1;
      }

      if (var2 != this.mOffscreenPageLimit) {
         this.mOffscreenPageLimit = var2;
         this.populate();
      }

   }

   @Deprecated
   public void setOnPageChangeListener(OnPageChangeListener var1) {
      this.mOnPageChangeListener = var1;
   }

   public void setPageMargin(int var1) {
      int var2 = this.mPageMargin;
      this.mPageMargin = var1;
      int var3 = this.getWidth();
      this.recomputeScrollPosition(var3, var3, var1, var2);
      this.requestLayout();
   }

   public void setPageMarginDrawable(int var1) {
      this.setPageMarginDrawable(ContextCompat.getDrawable(this.getContext(), var1));
   }

   public void setPageMarginDrawable(Drawable var1) {
      this.mMarginDrawable = var1;
      if (var1 != null) {
         this.refreshDrawableState();
      }

      boolean var2;
      if (var1 == null) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.setWillNotDraw(var2);
      this.invalidate();
   }

   public void setPageTransformer(boolean var1, PageTransformer var2) {
      this.setPageTransformer(var1, var2, 2);
   }

   public void setPageTransformer(boolean var1, PageTransformer var2, int var3) {
      byte var5 = 1;
      boolean var6;
      if (var2 != null) {
         var6 = true;
      } else {
         var6 = false;
      }

      boolean var7;
      if (this.mPageTransformer != null) {
         var7 = true;
      } else {
         var7 = false;
      }

      boolean var4;
      if (var6 != var7) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.mPageTransformer = var2;
      this.setChildrenDrawingOrderEnabled(var6);
      if (var6) {
         if (var1) {
            var5 = 2;
         }

         this.mDrawingOrder = var5;
         this.mPageTransformerLayerType = var3;
      } else {
         this.mDrawingOrder = 0;
      }

      if (var4) {
         this.populate();
      }

   }

   void setScrollState(int var1) {
      if (this.mScrollState != var1) {
         this.mScrollState = var1;
         if (this.mPageTransformer != null) {
            boolean var2;
            if (var1 != 0) {
               var2 = true;
            } else {
               var2 = false;
            }

            this.enableLayers(var2);
         }

         this.dispatchOnScrollStateChanged(var1);
      }
   }

   void smoothScrollTo(int var1, int var2) {
      this.smoothScrollTo(var1, var2, 0);
   }

   void smoothScrollTo(int var1, int var2, int var3) {
      if (this.getChildCount() == 0) {
         this.setScrollingCacheEnabled(false);
      } else {
         Scroller var11 = this.mScroller;
         boolean var7;
         if (var11 != null && !var11.isFinished()) {
            var7 = true;
         } else {
            var7 = false;
         }

         int var12;
         if (var7) {
            if (this.mIsScrollStarted) {
               var12 = this.mScroller.getCurrX();
            } else {
               var12 = this.mScroller.getStartX();
            }

            this.mScroller.abortAnimation();
            this.setScrollingCacheEnabled(false);
         } else {
            var12 = this.getScrollX();
         }

         int var8 = this.getScrollY();
         int var9 = var1 - var12;
         var2 -= var8;
         if (var9 == 0 && var2 == 0) {
            this.completeScroll(false);
            this.populate();
            this.setScrollState(0);
         } else {
            this.setScrollingCacheEnabled(true);
            this.setScrollState(2);
            var1 = this.getClientWidth();
            int var10 = var1 / 2;
            float var5 = (float)Math.abs(var9);
            float var4 = (float)var1;
            float var6 = Math.min(1.0F, var5 * 1.0F / var4);
            var5 = (float)var10;
            var6 = this.distanceInfluenceForSnapDuration(var6);
            var1 = Math.abs(var3);
            if (var1 > 0) {
               var1 = Math.round(Math.abs((var5 + var6 * var5) / (float)var1) * 1000.0F) * 4;
            } else {
               var5 = this.mAdapter.getPageWidth(this.mCurItem);
               var1 = (int)(((float)Math.abs(var9) / (var4 * var5 + (float)this.mPageMargin) + 1.0F) * 100.0F);
            }

            var1 = Math.min(var1, 600);
            this.mIsScrollStarted = false;
            this.mScroller.startScroll(var12, var8, var9, var2, var1);
            ViewCompat.postInvalidateOnAnimation(this);
         }
      }
   }

   protected boolean verifyDrawable(Drawable var1) {
      boolean var2;
      if (!super.verifyDrawable(var1) && var1 != this.mMarginDrawable) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   @Inherited
   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.TYPE})
   public @interface DecorView {
   }

   static class ItemInfo {
      Object object;
      float offset;
      int position;
      boolean scrolling;
      float widthFactor;
   }

   public static class LayoutParams extends ViewGroup.LayoutParams {
      int childIndex;
      public int gravity;
      public boolean isDecor;
      boolean needsMeasure;
      int position;
      float widthFactor = 0.0F;

      public LayoutParams() {
         super(-1, -1);
      }

      public LayoutParams(Context var1, AttributeSet var2) {
         super(var1, var2);
         TypedArray var3 = var1.obtainStyledAttributes(var2, ViewPager.LAYOUT_ATTRS);
         this.gravity = var3.getInteger(0, 48);
         var3.recycle();
      }
   }

   class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
      final ViewPager this$0;

      MyAccessibilityDelegate(ViewPager var1) {
         this.this$0 = var1;
      }

      private boolean canScroll() {
         PagerAdapter var2 = this.this$0.mAdapter;
         boolean var1 = true;
         if (var2 == null || this.this$0.mAdapter.getCount() <= 1) {
            var1 = false;
         }

         return var1;
      }

      public void onInitializeAccessibilityEvent(View var1, AccessibilityEvent var2) {
         super.onInitializeAccessibilityEvent(var1, var2);
         var2.setClassName(ViewPager.class.getName());
         var2.setScrollable(this.canScroll());
         if (var2.getEventType() == 4096 && this.this$0.mAdapter != null) {
            var2.setItemCount(this.this$0.mAdapter.getCount());
            var2.setFromIndex(this.this$0.mCurItem);
            var2.setToIndex(this.this$0.mCurItem);
         }

      }

      public void onInitializeAccessibilityNodeInfo(View var1, AccessibilityNodeInfoCompat var2) {
         super.onInitializeAccessibilityNodeInfo(var1, var2);
         var2.setClassName(ViewPager.class.getName());
         var2.setScrollable(this.canScroll());
         if (this.this$0.canScrollHorizontally(1)) {
            var2.addAction(4096);
         }

         if (this.this$0.canScrollHorizontally(-1)) {
            var2.addAction(8192);
         }

      }

      public boolean performAccessibilityAction(View var1, int var2, Bundle var3) {
         if (super.performAccessibilityAction(var1, var2, var3)) {
            return true;
         } else {
            ViewPager var4;
            if (var2 != 4096) {
               if (var2 != 8192) {
                  return false;
               } else if (this.this$0.canScrollHorizontally(-1)) {
                  var4 = this.this$0;
                  var4.setCurrentItem(var4.mCurItem - 1);
                  return true;
               } else {
                  return false;
               }
            } else if (this.this$0.canScrollHorizontally(1)) {
               var4 = this.this$0;
               var4.setCurrentItem(var4.mCurItem + 1);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   public interface OnAdapterChangeListener {
      void onAdapterChanged(ViewPager var1, PagerAdapter var2, PagerAdapter var3);
   }

   public interface OnPageChangeListener {
      void onPageScrollStateChanged(int var1);

      void onPageScrolled(int var1, float var2, int var3);

      void onPageSelected(int var1);
   }

   public interface PageTransformer {
      void transformPage(View var1, float var2);
   }

   private class PagerObserver extends DataSetObserver {
      final ViewPager this$0;

      PagerObserver(ViewPager var1) {
         this.this$0 = var1;
      }

      public void onChanged() {
         this.this$0.dataSetChanged();
      }

      public void onInvalidated() {
         this.this$0.dataSetChanged();
      }
   }

   public static class SavedState extends AbsSavedState {
      public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1, (ClassLoader)null);
         }

         public SavedState createFromParcel(Parcel var1, ClassLoader var2) {
            return new SavedState(var1, var2);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      Parcelable adapterState;
      ClassLoader loader;
      int position;

      SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         ClassLoader var3 = var2;
         if (var2 == null) {
            var3 = this.getClass().getClassLoader();
         }

         this.position = var1.readInt();
         this.adapterState = var1.readParcelable(var3);
         this.loader = var3;
      }

      public SavedState(Parcelable var1) {
         super(var1);
      }

      public String toString() {
         return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeInt(this.position);
         var1.writeParcelable(this.adapterState, var2);
      }
   }

   public static class SimpleOnPageChangeListener implements OnPageChangeListener {
      public void onPageScrollStateChanged(int var1) {
      }

      public void onPageScrolled(int var1, float var2, int var3) {
      }

      public void onPageSelected(int var1) {
      }
   }

   static class ViewPositionComparator implements Comparator {
      public int compare(View var1, View var2) {
         LayoutParams var4 = (LayoutParams)var1.getLayoutParams();
         LayoutParams var5 = (LayoutParams)var2.getLayoutParams();
         if (var4.isDecor != var5.isDecor) {
            byte var3;
            if (var4.isDecor) {
               var3 = 1;
            } else {
               var3 = -1;
            }

            return var3;
         } else {
            return var4.position - var5.position;
         }
      }
   }
}
