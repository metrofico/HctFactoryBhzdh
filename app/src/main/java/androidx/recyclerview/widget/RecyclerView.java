package androidx.recyclerview.widget;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.OverScroller;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild2;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.ScrollingView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import androidx.recyclerview.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerView extends ViewGroup implements ScrollingView, NestedScrollingChild2, NestedScrollingChild3 {
   static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC;
   static final boolean ALLOW_THREAD_GAP_WORK;
   static final boolean DEBUG = false;
   static final int DEFAULT_ORIENTATION = 1;
   static final boolean DISPATCH_TEMP_DETACH = false;
   private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION;
   static final boolean FORCE_INVALIDATE_DISPLAY_LIST;
   static final long FOREVER_NS = Long.MAX_VALUE;
   public static final int HORIZONTAL = 0;
   private static final boolean IGNORE_DETACHED_FOCUSED_CHILD;
   private static final int INVALID_POINTER = -1;
   public static final int INVALID_TYPE = -1;
   private static final Class[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
   static final int MAX_SCROLL_DURATION = 2000;
   private static final int[] NESTED_SCROLLING_ATTRS = new int[]{16843830};
   public static final long NO_ID = -1L;
   public static final int NO_POSITION = -1;
   static final boolean POST_UPDATES_ON_ANIMATION;
   public static final int SCROLL_STATE_DRAGGING = 1;
   public static final int SCROLL_STATE_IDLE = 0;
   public static final int SCROLL_STATE_SETTLING = 2;
   static final String TAG = "RecyclerView";
   public static final int TOUCH_SLOP_DEFAULT = 0;
   public static final int TOUCH_SLOP_PAGING = 1;
   static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
   static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
   private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
   static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
   private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
   private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
   static final String TRACE_PREFETCH_TAG = "RV Prefetch";
   static final String TRACE_SCROLL_TAG = "RV Scroll";
   public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
   static final boolean VERBOSE_TRACING = false;
   public static final int VERTICAL = 1;
   static final Interpolator sQuinticInterpolator;
   RecyclerViewAccessibilityDelegate mAccessibilityDelegate;
   private final AccessibilityManager mAccessibilityManager;
   Adapter mAdapter;
   AdapterHelper mAdapterHelper;
   boolean mAdapterUpdateDuringMeasure;
   private EdgeEffect mBottomGlow;
   private ChildDrawingOrderCallback mChildDrawingOrderCallback;
   ChildHelper mChildHelper;
   boolean mClipToPadding;
   boolean mDataSetHasChangedAfterLayout;
   boolean mDispatchItemsChangedEvent;
   private int mDispatchScrollCounter;
   private int mEatenAccessibilityChangeFlags;
   private EdgeEffectFactory mEdgeEffectFactory;
   boolean mEnableFastScroller;
   boolean mFirstLayoutComplete;
   GapWorker mGapWorker;
   boolean mHasFixedSize;
   private boolean mIgnoreMotionEventTillDown;
   private int mInitialTouchX;
   private int mInitialTouchY;
   private int mInterceptRequestLayoutDepth;
   private OnItemTouchListener mInterceptingOnItemTouchListener;
   boolean mIsAttached;
   ItemAnimator mItemAnimator;
   private ItemAnimator.ItemAnimatorListener mItemAnimatorListener;
   private Runnable mItemAnimatorRunner;
   final ArrayList mItemDecorations;
   boolean mItemsAddedOrRemoved;
   boolean mItemsChanged;
   private int mLastAutoMeasureNonExactMeasuredHeight;
   private int mLastAutoMeasureNonExactMeasuredWidth;
   private boolean mLastAutoMeasureSkippedDueToExact;
   private int mLastTouchX;
   private int mLastTouchY;
   LayoutManager mLayout;
   private int mLayoutOrScrollCounter;
   boolean mLayoutSuppressed;
   boolean mLayoutWasDefered;
   private EdgeEffect mLeftGlow;
   private final int mMaxFlingVelocity;
   private final int mMinFlingVelocity;
   private final int[] mMinMaxLayoutPositions;
   private final int[] mNestedOffsets;
   private final RecyclerViewDataObserver mObserver;
   private List mOnChildAttachStateListeners;
   private OnFlingListener mOnFlingListener;
   private final ArrayList mOnItemTouchListeners;
   final List mPendingAccessibilityImportanceChange;
   SavedState mPendingSavedState;
   boolean mPostedAnimatorRunner;
   GapWorker.LayoutPrefetchRegistryImpl mPrefetchRegistry;
   private boolean mPreserveFocusAfterLayout;
   final Recycler mRecycler;
   RecyclerListener mRecyclerListener;
   final List mRecyclerListeners;
   final int[] mReusableIntPair;
   private EdgeEffect mRightGlow;
   private float mScaledHorizontalScrollFactor;
   private float mScaledVerticalScrollFactor;
   private OnScrollListener mScrollListener;
   private List mScrollListeners;
   private final int[] mScrollOffset;
   private int mScrollPointerId;
   private int mScrollState;
   private NestedScrollingChildHelper mScrollingChildHelper;
   final State mState;
   final Rect mTempRect;
   private final Rect mTempRect2;
   final RectF mTempRectF;
   private EdgeEffect mTopGlow;
   private int mTouchSlop;
   final Runnable mUpdateChildViewsRunnable;
   private VelocityTracker mVelocityTracker;
   final ViewFlinger mViewFlinger;
   private final ViewInfoStore.ProcessCallback mViewInfoProcessCallback;
   final ViewInfoStore mViewInfoStore;

   static {
      boolean var0;
      if (VERSION.SDK_INT != 18 && VERSION.SDK_INT != 19 && VERSION.SDK_INT != 20) {
         var0 = false;
      } else {
         var0 = true;
      }

      FORCE_INVALIDATE_DISPLAY_LIST = var0;
      if (VERSION.SDK_INT >= 23) {
         var0 = true;
      } else {
         var0 = false;
      }

      ALLOW_SIZE_IN_UNSPECIFIED_SPEC = var0;
      if (VERSION.SDK_INT >= 16) {
         var0 = true;
      } else {
         var0 = false;
      }

      POST_UPDATES_ON_ANIMATION = var0;
      if (VERSION.SDK_INT >= 21) {
         var0 = true;
      } else {
         var0 = false;
      }

      ALLOW_THREAD_GAP_WORK = var0;
      if (VERSION.SDK_INT <= 15) {
         var0 = true;
      } else {
         var0 = false;
      }

      FORCE_ABS_FOCUS_SEARCH_DIRECTION = var0;
      if (VERSION.SDK_INT <= 15) {
         var0 = true;
      } else {
         var0 = false;
      }

      IGNORE_DETACHED_FOCUSED_CHILD = var0;
      LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE};
      sQuinticInterpolator = new Interpolator() {
         public float getInterpolation(float var1) {
            --var1;
            return var1 * var1 * var1 * var1 * var1 + 1.0F;
         }
      };
   }

   public RecyclerView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public RecyclerView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.recyclerViewStyle);
   }

   public RecyclerView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.mObserver = new RecyclerViewDataObserver(this);
      this.mRecycler = new Recycler(this);
      this.mViewInfoStore = new ViewInfoStore();
      this.mUpdateChildViewsRunnable = new Runnable(this) {
         final RecyclerView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            if (this.this$0.mFirstLayoutComplete && !this.this$0.isLayoutRequested()) {
               if (!this.this$0.mIsAttached) {
                  this.this$0.requestLayout();
                  return;
               }

               if (this.this$0.mLayoutSuppressed) {
                  this.this$0.mLayoutWasDefered = true;
                  return;
               }

               this.this$0.consumePendingUpdateOperations();
            }

         }
      };
      this.mTempRect = new Rect();
      this.mTempRect2 = new Rect();
      this.mTempRectF = new RectF();
      this.mRecyclerListeners = new ArrayList();
      this.mItemDecorations = new ArrayList();
      this.mOnItemTouchListeners = new ArrayList();
      this.mInterceptRequestLayoutDepth = 0;
      this.mDataSetHasChangedAfterLayout = false;
      this.mDispatchItemsChangedEvent = false;
      this.mLayoutOrScrollCounter = 0;
      this.mDispatchScrollCounter = 0;
      this.mEdgeEffectFactory = new EdgeEffectFactory();
      this.mItemAnimator = new DefaultItemAnimator();
      this.mScrollState = 0;
      this.mScrollPointerId = -1;
      this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
      this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
      boolean var5 = true;
      this.mPreserveFocusAfterLayout = true;
      this.mViewFlinger = new ViewFlinger(this);
      GapWorker.LayoutPrefetchRegistryImpl var6;
      if (ALLOW_THREAD_GAP_WORK) {
         var6 = new GapWorker.LayoutPrefetchRegistryImpl();
      } else {
         var6 = null;
      }

      this.mPrefetchRegistry = var6;
      this.mState = new State();
      this.mItemsAddedOrRemoved = false;
      this.mItemsChanged = false;
      this.mItemAnimatorListener = new ItemAnimatorRestoreListener(this);
      this.mPostedAnimatorRunner = false;
      this.mMinMaxLayoutPositions = new int[2];
      this.mScrollOffset = new int[2];
      this.mNestedOffsets = new int[2];
      this.mReusableIntPair = new int[2];
      this.mPendingAccessibilityImportanceChange = new ArrayList();
      this.mItemAnimatorRunner = new Runnable(this) {
         final RecyclerView this$0;

         {
            this.this$0 = var1;
         }

         public void run() {
            if (this.this$0.mItemAnimator != null) {
               this.this$0.mItemAnimator.runPendingAnimations();
            }

            this.this$0.mPostedAnimatorRunner = false;
         }
      };
      this.mLastAutoMeasureNonExactMeasuredWidth = 0;
      this.mLastAutoMeasureNonExactMeasuredHeight = 0;
      this.mViewInfoProcessCallback = new ViewInfoStore.ProcessCallback(this) {
         final RecyclerView this$0;

         {
            this.this$0 = var1;
         }

         public void processAppeared(ViewHolder var1, ItemAnimator.ItemHolderInfo var2, ItemAnimator.ItemHolderInfo var3) {
            this.this$0.animateAppearance(var1, var2, var3);
         }

         public void processDisappeared(ViewHolder var1, ItemAnimator.ItemHolderInfo var2, ItemAnimator.ItemHolderInfo var3) {
            this.this$0.mRecycler.unscrapView(var1);
            this.this$0.animateDisappearance(var1, var2, var3);
         }

         public void processPersistent(ViewHolder var1, ItemAnimator.ItemHolderInfo var2, ItemAnimator.ItemHolderInfo var3) {
            var1.setIsRecyclable(false);
            if (this.this$0.mDataSetHasChangedAfterLayout) {
               if (this.this$0.mItemAnimator.animateChange(var1, var1, var2, var3)) {
                  this.this$0.postAnimationRunner();
               }
            } else if (this.this$0.mItemAnimator.animatePersistence(var1, var2, var3)) {
               this.this$0.postAnimationRunner();
            }

         }

         public void unused(ViewHolder var1) {
            this.this$0.mLayout.removeAndRecycleView(var1.itemView, this.this$0.mRecycler);
         }
      };
      this.setScrollContainer(true);
      this.setFocusableInTouchMode(true);
      ViewConfiguration var8 = ViewConfiguration.get(var1);
      this.mTouchSlop = var8.getScaledTouchSlop();
      this.mScaledHorizontalScrollFactor = ViewConfigurationCompat.getScaledHorizontalScrollFactor(var8, var1);
      this.mScaledVerticalScrollFactor = ViewConfigurationCompat.getScaledVerticalScrollFactor(var8, var1);
      this.mMinFlingVelocity = var8.getScaledMinimumFlingVelocity();
      this.mMaxFlingVelocity = var8.getScaledMaximumFlingVelocity();
      boolean var4;
      if (this.getOverScrollMode() == 2) {
         var4 = true;
      } else {
         var4 = false;
      }

      this.setWillNotDraw(var4);
      this.mItemAnimator.setListener(this.mItemAnimatorListener);
      this.initAdapterManager();
      this.initChildrenHelper();
      this.initAutofill();
      if (ViewCompat.getImportantForAccessibility(this) == 0) {
         ViewCompat.setImportantForAccessibility(this, 1);
      }

      this.mAccessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
      this.setAccessibilityDelegateCompat(new RecyclerViewAccessibilityDelegate(this));
      TypedArray var9 = var1.obtainStyledAttributes(var2, R.styleable.RecyclerView, var3, 0);
      ViewCompat.saveAttributeDataForStyleable(this, var1, R.styleable.RecyclerView, var2, var9, var3, 0);
      String var7 = var9.getString(R.styleable.RecyclerView_layoutManager);
      if (var9.getInt(R.styleable.RecyclerView_android_descendantFocusability, -1) == -1) {
         this.setDescendantFocusability(262144);
      }

      this.mClipToPadding = var9.getBoolean(R.styleable.RecyclerView_android_clipToPadding, true);
      var4 = var9.getBoolean(R.styleable.RecyclerView_fastScrollEnabled, false);
      this.mEnableFastScroller = var4;
      if (var4) {
         this.initFastScroller((StateListDrawable)var9.getDrawable(R.styleable.RecyclerView_fastScrollVerticalThumbDrawable), var9.getDrawable(R.styleable.RecyclerView_fastScrollVerticalTrackDrawable), (StateListDrawable)var9.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalThumbDrawable), var9.getDrawable(R.styleable.RecyclerView_fastScrollHorizontalTrackDrawable));
      }

      var9.recycle();
      this.createLayoutManager(var1, var7, var2, var3, 0);
      var4 = var5;
      if (VERSION.SDK_INT >= 21) {
         int[] var10 = NESTED_SCROLLING_ATTRS;
         var9 = var1.obtainStyledAttributes(var2, var10, var3, 0);
         ViewCompat.saveAttributeDataForStyleable(this, var1, var10, var2, var9, var3, 0);
         var4 = var9.getBoolean(0, true);
         var9.recycle();
      }

      this.setNestedScrollingEnabled(var4);
   }

   private void addAnimatingView(ViewHolder var1) {
      View var3 = var1.itemView;
      boolean var2;
      if (var3.getParent() == this) {
         var2 = true;
      } else {
         var2 = false;
      }

      this.mRecycler.unscrapView(this.getChildViewHolder(var3));
      if (var1.isTmpDetached()) {
         this.mChildHelper.attachViewToParent(var3, -1, var3.getLayoutParams(), true);
      } else if (!var2) {
         this.mChildHelper.addView(var3, true);
      } else {
         this.mChildHelper.hide(var3);
      }

   }

   private void animateChange(ViewHolder var1, ViewHolder var2, ItemAnimator.ItemHolderInfo var3, ItemAnimator.ItemHolderInfo var4, boolean var5, boolean var6) {
      var1.setIsRecyclable(false);
      if (var5) {
         this.addAnimatingView(var1);
      }

      if (var1 != var2) {
         if (var6) {
            this.addAnimatingView(var2);
         }

         var1.mShadowedHolder = var2;
         this.addAnimatingView(var1);
         this.mRecycler.unscrapView(var1);
         var2.setIsRecyclable(false);
         var2.mShadowingHolder = var1;
      }

      if (this.mItemAnimator.animateChange(var1, var2, var3, var4)) {
         this.postAnimationRunner();
      }

   }

   private void cancelScroll() {
      this.resetScroll();
      this.setScrollState(0);
   }

   static void clearNestedRecyclerViewIfNotNested(ViewHolder var0) {
      if (var0.mNestedRecyclerView != null) {
         View var1 = (View)var0.mNestedRecyclerView.get();

         while(var1 != null) {
            if (var1 == var0.itemView) {
               return;
            }

            ViewParent var2 = var1.getParent();
            if (var2 instanceof View) {
               var1 = (View)var2;
            } else {
               var1 = null;
            }
         }

         var0.mNestedRecyclerView = null;
      }

   }

   private void createLayoutManager(Context param1, String param2, AttributeSet param3, int param4, int param5) {
      // $FF: Couldn't be decompiled
   }

   private boolean didChildRangeChange(int var1, int var2) {
      this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
      int[] var4 = this.mMinMaxLayoutPositions;
      boolean var3 = false;
      if (var4[0] != var1 || var4[1] != var2) {
         var3 = true;
      }

      return var3;
   }

   private void dispatchContentChangedIfNecessary() {
      int var1 = this.mEatenAccessibilityChangeFlags;
      this.mEatenAccessibilityChangeFlags = 0;
      if (var1 != 0 && this.isAccessibilityEnabled()) {
         AccessibilityEvent var2 = AccessibilityEvent.obtain();
         var2.setEventType(2048);
         AccessibilityEventCompat.setContentChangeTypes(var2, var1);
         this.sendAccessibilityEventUnchecked(var2);
      }

   }

   private void dispatchLayoutStep1() {
      State var7 = this.mState;
      boolean var6 = true;
      var7.assertLayoutStep(1);
      this.fillRemainingScrollValues(this.mState);
      this.mState.mIsMeasuring = false;
      this.startInterceptRequestLayout();
      this.mViewInfoStore.clear();
      this.onEnterLayoutOrScroll();
      this.processAdapterUpdatesAndSetAnimationFlags();
      this.saveFocusInfo();
      var7 = this.mState;
      if (!var7.mRunSimpleAnimations || !this.mItemsChanged) {
         var6 = false;
      }

      var7.mTrackOldChangeHolders = var6;
      this.mItemsChanged = false;
      this.mItemsAddedOrRemoved = false;
      var7 = this.mState;
      var7.mInPreLayout = var7.mRunPredictiveAnimations;
      this.mState.mItemCount = this.mAdapter.getItemCount();
      this.findMinMaxChildLayoutPositions(this.mMinMaxLayoutPositions);
      int var1;
      int var2;
      if (this.mState.mRunSimpleAnimations) {
         var2 = this.mChildHelper.getChildCount();

         for(var1 = 0; var1 < var2; ++var1) {
            ViewHolder var9 = getChildViewHolderInt(this.mChildHelper.getChildAt(var1));
            if (!var9.shouldIgnore() && (!var9.isInvalid() || this.mAdapter.hasStableIds())) {
               ItemAnimator.ItemHolderInfo var8 = this.mItemAnimator.recordPreLayoutInformation(this.mState, var9, RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(var9), var9.getUnmodifiedPayloads());
               this.mViewInfoStore.addToPreLayout(var9, var8);
               if (this.mState.mTrackOldChangeHolders && var9.isUpdated() && !var9.isRemoved() && !var9.shouldIgnore() && !var9.isInvalid()) {
                  long var4 = this.getChangedHolderKey(var9);
                  this.mViewInfoStore.addToOldChangeHolders(var4, var9);
               }
            }
         }
      }

      if (this.mState.mRunPredictiveAnimations) {
         this.saveOldPositions();
         var6 = this.mState.mStructureChanged;
         this.mState.mStructureChanged = false;
         this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
         this.mState.mStructureChanged = var6;

         for(var1 = 0; var1 < this.mChildHelper.getChildCount(); ++var1) {
            ViewHolder var10 = getChildViewHolderInt(this.mChildHelper.getChildAt(var1));
            if (!var10.shouldIgnore() && !this.mViewInfoStore.isInPreLayout(var10)) {
               int var3 = RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(var10);
               var6 = var10.hasAnyOfTheFlags(8192);
               var2 = var3;
               if (!var6) {
                  var2 = var3 | 4096;
               }

               ItemAnimator.ItemHolderInfo var11 = this.mItemAnimator.recordPreLayoutInformation(this.mState, var10, var2, var10.getUnmodifiedPayloads());
               if (var6) {
                  this.recordAnimationInfoIfBouncedHiddenView(var10, var11);
               } else {
                  this.mViewInfoStore.addToAppearedInPreLayoutHolders(var10, var11);
               }
            }
         }

         this.clearOldPositions();
      } else {
         this.clearOldPositions();
      }

      this.onExitLayoutOrScroll();
      this.stopInterceptRequestLayout(false);
      this.mState.mLayoutStep = 2;
   }

   private void dispatchLayoutStep2() {
      this.startInterceptRequestLayout();
      this.onEnterLayoutOrScroll();
      this.mState.assertLayoutStep(6);
      this.mAdapterHelper.consumeUpdatesInOnePass();
      this.mState.mItemCount = this.mAdapter.getItemCount();
      this.mState.mDeletedInvisibleItemCountSincePreviousLayout = 0;
      if (this.mPendingSavedState != null && this.mAdapter.canRestoreState()) {
         if (this.mPendingSavedState.mLayoutState != null) {
            this.mLayout.onRestoreInstanceState(this.mPendingSavedState.mLayoutState);
         }

         this.mPendingSavedState = null;
      }

      this.mState.mInPreLayout = false;
      this.mLayout.onLayoutChildren(this.mRecycler, this.mState);
      this.mState.mStructureChanged = false;
      State var2 = this.mState;
      boolean var1;
      if (var2.mRunSimpleAnimations && this.mItemAnimator != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      var2.mRunSimpleAnimations = var1;
      this.mState.mLayoutStep = 4;
      this.onExitLayoutOrScroll();
      this.stopInterceptRequestLayout(false);
   }

   private void dispatchLayoutStep3() {
      this.mState.assertLayoutStep(4);
      this.startInterceptRequestLayout();
      this.onEnterLayoutOrScroll();
      this.mState.mLayoutStep = 1;
      if (this.mState.mRunSimpleAnimations) {
         for(int var1 = this.mChildHelper.getChildCount() - 1; var1 >= 0; --var1) {
            ViewHolder var7 = getChildViewHolderInt(this.mChildHelper.getChildAt(var1));
            if (!var7.shouldIgnore()) {
               long var2 = this.getChangedHolderKey(var7);
               ItemAnimator.ItemHolderInfo var9 = this.mItemAnimator.recordPostLayoutInformation(this.mState, var7);
               ViewHolder var6 = this.mViewInfoStore.getFromOldChangeHolders(var2);
               if (var6 != null && !var6.shouldIgnore()) {
                  boolean var4 = this.mViewInfoStore.isDisappearing(var6);
                  boolean var5 = this.mViewInfoStore.isDisappearing(var7);
                  if (var4 && var6 == var7) {
                     this.mViewInfoStore.addToPostLayout(var7, var9);
                  } else {
                     ItemAnimator.ItemHolderInfo var8 = this.mViewInfoStore.popFromPreLayout(var6);
                     this.mViewInfoStore.addToPostLayout(var7, var9);
                     var9 = this.mViewInfoStore.popFromPostLayout(var7);
                     if (var8 == null) {
                        this.handleMissingPreInfoForChangeError(var2, var7, var6);
                     } else {
                        this.animateChange(var6, var7, var8, var9, var4, var5);
                     }
                  }
               } else {
                  this.mViewInfoStore.addToPostLayout(var7, var9);
               }
            }
         }

         this.mViewInfoStore.process(this.mViewInfoProcessCallback);
      }

      this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
      State var10 = this.mState;
      var10.mPreviousLayoutItemCount = var10.mItemCount;
      this.mDataSetHasChangedAfterLayout = false;
      this.mDispatchItemsChangedEvent = false;
      this.mState.mRunSimpleAnimations = false;
      this.mState.mRunPredictiveAnimations = false;
      this.mLayout.mRequestedSimpleAnimations = false;
      if (this.mRecycler.mChangedScrap != null) {
         this.mRecycler.mChangedScrap.clear();
      }

      if (this.mLayout.mPrefetchMaxObservedInInitialPrefetch) {
         this.mLayout.mPrefetchMaxCountObserved = 0;
         this.mLayout.mPrefetchMaxObservedInInitialPrefetch = false;
         this.mRecycler.updateViewCacheSize();
      }

      this.mLayout.onLayoutCompleted(this.mState);
      this.onExitLayoutOrScroll();
      this.stopInterceptRequestLayout(false);
      this.mViewInfoStore.clear();
      int[] var11 = this.mMinMaxLayoutPositions;
      if (this.didChildRangeChange(var11[0], var11[1])) {
         this.dispatchOnScrolled(0, 0);
      }

      this.recoverFocusFromState();
      this.resetFocusInfo();
   }

   private boolean dispatchToOnItemTouchListeners(MotionEvent var1) {
      OnItemTouchListener var3 = this.mInterceptingOnItemTouchListener;
      if (var3 == null) {
         return var1.getAction() == 0 ? false : this.findInterceptingOnItemTouchListener(var1);
      } else {
         var3.onTouchEvent(this, var1);
         int var2 = var1.getAction();
         if (var2 == 3 || var2 == 1) {
            this.mInterceptingOnItemTouchListener = null;
         }

         return true;
      }
   }

   private boolean findInterceptingOnItemTouchListener(MotionEvent var1) {
      int var4 = var1.getAction();
      int var3 = this.mOnItemTouchListeners.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         OnItemTouchListener var5 = (OnItemTouchListener)this.mOnItemTouchListeners.get(var2);
         if (var5.onInterceptTouchEvent(this, var1) && var4 != 3) {
            this.mInterceptingOnItemTouchListener = var5;
            return true;
         }
      }

      return false;
   }

   private void findMinMaxChildLayoutPositions(int[] var1) {
      int var8 = this.mChildHelper.getChildCount();
      if (var8 == 0) {
         var1[0] = -1;
         var1[1] = -1;
      } else {
         int var3 = Integer.MAX_VALUE;
         int var5 = Integer.MIN_VALUE;

         int var6;
         for(int var4 = 0; var4 < var8; var5 = var6) {
            ViewHolder var9 = getChildViewHolderInt(this.mChildHelper.getChildAt(var4));
            if (var9.shouldIgnore()) {
               var6 = var5;
            } else {
               int var7 = var9.getLayoutPosition();
               int var2 = var3;
               if (var7 < var3) {
                  var2 = var7;
               }

               var3 = var2;
               var6 = var5;
               if (var7 > var5) {
                  var6 = var7;
                  var3 = var2;
               }
            }

            ++var4;
         }

         var1[0] = var3;
         var1[1] = var5;
      }
   }

   static RecyclerView findNestedRecyclerView(View var0) {
      if (!(var0 instanceof ViewGroup)) {
         return null;
      } else if (var0 instanceof RecyclerView) {
         return (RecyclerView)var0;
      } else {
         ViewGroup var3 = (ViewGroup)var0;
         int var2 = var3.getChildCount();

         for(int var1 = 0; var1 < var2; ++var1) {
            RecyclerView var4 = findNestedRecyclerView(var3.getChildAt(var1));
            if (var4 != null) {
               return var4;
            }
         }

         return null;
      }
   }

   private View findNextViewToFocus() {
      int var1;
      if (this.mState.mFocusedItemPosition != -1) {
         var1 = this.mState.mFocusedItemPosition;
      } else {
         var1 = 0;
      }

      int var3 = this.mState.getItemCount();

      ViewHolder var4;
      for(int var2 = var1; var2 < var3; ++var2) {
         var4 = this.findViewHolderForAdapterPosition(var2);
         if (var4 == null) {
            break;
         }

         if (var4.itemView.hasFocusable()) {
            return var4.itemView;
         }
      }

      for(var1 = Math.min(var3, var1) - 1; var1 >= 0; --var1) {
         var4 = this.findViewHolderForAdapterPosition(var1);
         if (var4 == null) {
            return null;
         }

         if (var4.itemView.hasFocusable()) {
            return var4.itemView;
         }
      }

      return null;
   }

   static ViewHolder getChildViewHolderInt(View var0) {
      return var0 == null ? null : ((LayoutParams)var0.getLayoutParams()).mViewHolder;
   }

   static void getDecoratedBoundsWithMarginsInt(View var0, Rect var1) {
      LayoutParams var3 = (LayoutParams)var0.getLayoutParams();
      Rect var2 = var3.mDecorInsets;
      var1.set(var0.getLeft() - var2.left - var3.leftMargin, var0.getTop() - var2.top - var3.topMargin, var0.getRight() + var2.right + var3.rightMargin, var0.getBottom() + var2.bottom + var3.bottomMargin);
   }

   private int getDeepestFocusedViewWithId(View var1) {
      int var2 = var1.getId();

      while(!var1.isFocused() && var1 instanceof ViewGroup && var1.hasFocus()) {
         View var3 = ((ViewGroup)var1).getFocusedChild();
         var1 = var3;
         if (var3.getId() != -1) {
            var2 = var3.getId();
            var1 = var3;
         }
      }

      return var2;
   }

   private String getFullClassName(Context var1, String var2) {
      if (var2.charAt(0) == '.') {
         return var1.getPackageName() + var2;
      } else {
         return var2.contains(".") ? var2 : RecyclerView.class.getPackage().getName() + '.' + var2;
      }
   }

   private NestedScrollingChildHelper getScrollingChildHelper() {
      if (this.mScrollingChildHelper == null) {
         this.mScrollingChildHelper = new NestedScrollingChildHelper(this);
      }

      return this.mScrollingChildHelper;
   }

   private void handleMissingPreInfoForChangeError(long var1, ViewHolder var3, ViewHolder var4) {
      int var6 = this.mChildHelper.getChildCount();

      for(int var5 = 0; var5 < var6; ++var5) {
         ViewHolder var7 = getChildViewHolderInt(this.mChildHelper.getChildAt(var5));
         if (var7 != var3 && this.getChangedHolderKey(var7) == var1) {
            Adapter var8 = this.mAdapter;
            if (var8 != null && var8.hasStableIds()) {
               throw new IllegalStateException("Two different ViewHolders have the same stable ID. Stable IDs in your adapter MUST BE unique and SHOULD NOT change.\n ViewHolder 1:" + var7 + " \n View Holder 2:" + var3 + this.exceptionLabel());
            }

            throw new IllegalStateException("Two different ViewHolders have the same change ID. This might happen due to inconsistent Adapter update events or if the LayoutManager lays out the same View multiple times.\n ViewHolder 1:" + var7 + " \n View Holder 2:" + var3 + this.exceptionLabel());
         }
      }

      Log.e("RecyclerView", "Problem while matching changed view holders with the newones. The pre-layout information for the change holder " + var4 + " cannot be found but it is necessary for " + var3 + this.exceptionLabel());
   }

   private boolean hasUpdatedView() {
      int var2 = this.mChildHelper.getChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         ViewHolder var3 = getChildViewHolderInt(this.mChildHelper.getChildAt(var1));
         if (var3 != null && !var3.shouldIgnore() && var3.isUpdated()) {
            return true;
         }
      }

      return false;
   }

   private void initAutofill() {
      if (ViewCompat.getImportantForAutofill(this) == 0) {
         ViewCompat.setImportantForAutofill(this, 8);
      }

   }

   private void initChildrenHelper() {
      this.mChildHelper = new ChildHelper(new ChildHelper.Callback(this) {
         final RecyclerView this$0;

         {
            this.this$0 = var1;
         }

         public void addView(View var1, int var2) {
            this.this$0.addView(var1, var2);
            this.this$0.dispatchChildAttached(var1);
         }

         public void attachViewToParent(View var1, int var2, ViewGroup.LayoutParams var3) {
            ViewHolder var4 = RecyclerView.getChildViewHolderInt(var1);
            if (var4 != null) {
               if (!var4.isTmpDetached() && !var4.shouldIgnore()) {
                  throw new IllegalArgumentException("Called attach on a child which is not detached: " + var4 + this.this$0.exceptionLabel());
               }

               var4.clearTmpDetachFlag();
            }

            this.this$0.attachViewToParent(var1, var2, var3);
         }

         public void detachViewFromParent(int var1) {
            View var2 = this.getChildAt(var1);
            if (var2 != null) {
               ViewHolder var3 = RecyclerView.getChildViewHolderInt(var2);
               if (var3 != null) {
                  if (var3.isTmpDetached() && !var3.shouldIgnore()) {
                     throw new IllegalArgumentException("called detach on an already detached child " + var3 + this.this$0.exceptionLabel());
                  }

                  var3.addFlags(256);
               }
            }

            this.this$0.detachViewFromParent(var1);
         }

         public View getChildAt(int var1) {
            return this.this$0.getChildAt(var1);
         }

         public int getChildCount() {
            return this.this$0.getChildCount();
         }

         public ViewHolder getChildViewHolder(View var1) {
            return RecyclerView.getChildViewHolderInt(var1);
         }

         public int indexOfChild(View var1) {
            return this.this$0.indexOfChild(var1);
         }

         public void onEnteredHiddenState(View var1) {
            ViewHolder var2 = RecyclerView.getChildViewHolderInt(var1);
            if (var2 != null) {
               var2.onEnteredHiddenState(this.this$0);
            }

         }

         public void onLeftHiddenState(View var1) {
            ViewHolder var2 = RecyclerView.getChildViewHolderInt(var1);
            if (var2 != null) {
               var2.onLeftHiddenState(this.this$0);
            }

         }

         public void removeAllViews() {
            int var2 = this.getChildCount();

            for(int var1 = 0; var1 < var2; ++var1) {
               View var3 = this.getChildAt(var1);
               this.this$0.dispatchChildDetached(var3);
               var3.clearAnimation();
            }

            this.this$0.removeAllViews();
         }

         public void removeViewAt(int var1) {
            View var2 = this.this$0.getChildAt(var1);
            if (var2 != null) {
               this.this$0.dispatchChildDetached(var2);
               var2.clearAnimation();
            }

            this.this$0.removeViewAt(var1);
         }
      });
   }

   private boolean isPreferredNextFocus(View var1, View var2, int var3) {
      boolean var12 = false;
      boolean var11 = false;
      boolean var10 = false;
      boolean var8 = false;
      boolean var13 = false;
      boolean var9 = false;
      boolean var7 = var13;
      if (var2 != null) {
         var7 = var13;
         if (var2 != this) {
            if (var2 == var1) {
               var7 = var13;
            } else {
               if (this.findContainingItemView(var2) == null) {
                  return false;
               }

               if (var1 == null) {
                  return true;
               }

               if (this.findContainingItemView(var1) == null) {
                  return true;
               }

               this.mTempRect.set(0, 0, var1.getWidth(), var1.getHeight());
               this.mTempRect2.set(0, 0, var2.getWidth(), var2.getHeight());
               this.offsetDescendantRectToMyCoords(var1, this.mTempRect);
               this.offsetDescendantRectToMyCoords(var2, this.mTempRect2);
               int var4 = this.mLayout.getLayoutDirection();
               byte var5 = -1;
               byte var6;
               if (var4 == 1) {
                  var6 = -1;
               } else {
                  var6 = 1;
               }

               byte var14;
               if ((this.mTempRect.left < this.mTempRect2.left || this.mTempRect.right <= this.mTempRect2.left) && this.mTempRect.right < this.mTempRect2.right) {
                  var14 = 1;
               } else if ((this.mTempRect.right > this.mTempRect2.right || this.mTempRect.left >= this.mTempRect2.right) && this.mTempRect.left > this.mTempRect2.left) {
                  var14 = -1;
               } else {
                  var14 = 0;
               }

               if ((this.mTempRect.top < this.mTempRect2.top || this.mTempRect.bottom <= this.mTempRect2.top) && this.mTempRect.bottom < this.mTempRect2.bottom) {
                  var5 = 1;
               } else if (this.mTempRect.bottom <= this.mTempRect2.bottom && this.mTempRect.top < this.mTempRect2.bottom || this.mTempRect.top <= this.mTempRect2.top) {
                  var5 = 0;
               }

               if (var3 != 1) {
                  if (var3 != 2) {
                     if (var3 != 17) {
                        if (var3 != 33) {
                           if (var3 != 66) {
                              if (var3 == 130) {
                                 var7 = var9;
                                 if (var5 > 0) {
                                    var7 = true;
                                 }

                                 return var7;
                              }

                              throw new IllegalArgumentException("Invalid direction: " + var3 + this.exceptionLabel());
                           }

                           var7 = var12;
                           if (var14 > 0) {
                              var7 = true;
                           }

                           return var7;
                        }

                        var7 = var11;
                        if (var5 < 0) {
                           var7 = true;
                        }

                        return var7;
                     }

                     var7 = var10;
                     if (var14 < 0) {
                        var7 = true;
                     }

                     return var7;
                  }

                  if (var5 <= 0) {
                     var7 = var8;
                     if (var5 != 0) {
                        return var7;
                     }

                     var7 = var8;
                     if (var14 * var6 <= 0) {
                        return var7;
                     }
                  }

                  var7 = true;
                  return var7;
               }

               if (var5 >= 0) {
                  var7 = var13;
                  if (var5 != 0) {
                     return var7;
                  }

                  var7 = var13;
                  if (var14 * var6 >= 0) {
                     return var7;
                  }
               }

               var7 = true;
            }
         }
      }

      return var7;
   }

   private void nestedScrollByInternal(int var1, int var2, MotionEvent var3, int var4) {
      LayoutManager var12 = this.mLayout;
      if (var12 == null) {
         Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      } else if (!this.mLayoutSuppressed) {
         int[] var13 = this.mReusableIntPair;
         byte var9 = 0;
         var13[0] = 0;
         var13[1] = 0;
         byte var10 = var12.canScrollHorizontally();
         boolean var11 = this.mLayout.canScrollVertically();
         int var5;
         if (var11) {
            var5 = var10 | 2;
         } else {
            var5 = var10;
         }

         this.startNestedScroll(var5, var4);
         int var7;
         if (var10 != 0) {
            var7 = var1;
         } else {
            var7 = 0;
         }

         int var8;
         if (var11) {
            var8 = var2;
         } else {
            var8 = 0;
         }

         int var6 = var1;
         var5 = var2;
         if (this.dispatchNestedPreScroll(var7, var8, this.mReusableIntPair, this.mScrollOffset, var4)) {
            int[] var15 = this.mReusableIntPair;
            var6 = var1 - var15[0];
            var5 = var2 - var15[1];
         }

         if (var10 != 0) {
            var1 = var6;
         } else {
            var1 = 0;
         }

         var2 = var9;
         if (var11) {
            var2 = var5;
         }

         this.scrollByInternal(var1, var2, var3, var4);
         GapWorker var14 = this.mGapWorker;
         if (var14 != null && (var6 != 0 || var5 != 0)) {
            var14.postFromTraversal(this, var6, var5);
         }

         this.stopNestedScroll(var4);
      }
   }

   private void onPointerUp(MotionEvent var1) {
      int var2 = var1.getActionIndex();
      if (var1.getPointerId(var2) == this.mScrollPointerId) {
         byte var4;
         if (var2 == 0) {
            var4 = 1;
         } else {
            var4 = 0;
         }

         this.mScrollPointerId = var1.getPointerId(var4);
         int var3 = (int)(var1.getX(var4) + 0.5F);
         this.mLastTouchX = var3;
         this.mInitialTouchX = var3;
         var2 = (int)(var1.getY(var4) + 0.5F);
         this.mLastTouchY = var2;
         this.mInitialTouchY = var2;
      }

   }

   private boolean predictiveItemAnimationsEnabled() {
      boolean var1;
      if (this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   private void processAdapterUpdatesAndSetAnimationFlags() {
      if (this.mDataSetHasChangedAfterLayout) {
         this.mAdapterHelper.reset();
         if (this.mDispatchItemsChangedEvent) {
            this.mLayout.onItemsChanged(this);
         }
      }

      if (this.predictiveItemAnimationsEnabled()) {
         this.mAdapterHelper.preProcess();
      } else {
         this.mAdapterHelper.consumeUpdatesInOnePass();
      }

      boolean var2 = this.mItemsAddedOrRemoved;
      boolean var3 = false;
      boolean var1;
      if (!var2 && !this.mItemsChanged) {
         var1 = false;
      } else {
         var1 = true;
      }

      State var4 = this.mState;
      if (!this.mFirstLayoutComplete || this.mItemAnimator == null || !this.mDataSetHasChangedAfterLayout && !var1 && !this.mLayout.mRequestedSimpleAnimations || this.mDataSetHasChangedAfterLayout && !this.mAdapter.hasStableIds()) {
         var2 = false;
      } else {
         var2 = true;
      }

      var4.mRunSimpleAnimations = var2;
      var4 = this.mState;
      var2 = var3;
      if (var4.mRunSimpleAnimations) {
         var2 = var3;
         if (var1) {
            var2 = var3;
            if (!this.mDataSetHasChangedAfterLayout) {
               var2 = var3;
               if (this.predictiveItemAnimationsEnabled()) {
                  var2 = true;
               }
            }
         }
      }

      var4.mRunPredictiveAnimations = var2;
   }

   private void pullGlows(float var1, float var2, float var3, float var4) {
      boolean var5;
      boolean var6;
      label32: {
         var6 = true;
         if (var2 < 0.0F) {
            this.ensureLeftGlow();
            EdgeEffectCompat.onPull(this.mLeftGlow, -var2 / (float)this.getWidth(), 1.0F - var3 / (float)this.getHeight());
         } else {
            if (!(var2 > 0.0F)) {
               var5 = false;
               break label32;
            }

            this.ensureRightGlow();
            EdgeEffectCompat.onPull(this.mRightGlow, var2 / (float)this.getWidth(), var3 / (float)this.getHeight());
         }

         var5 = true;
      }

      if (var4 < 0.0F) {
         this.ensureTopGlow();
         EdgeEffectCompat.onPull(this.mTopGlow, -var4 / (float)this.getHeight(), var1 / (float)this.getWidth());
         var5 = var6;
      } else if (var4 > 0.0F) {
         this.ensureBottomGlow();
         EdgeEffectCompat.onPull(this.mBottomGlow, var4 / (float)this.getHeight(), 1.0F - var1 / (float)this.getWidth());
         var5 = var6;
      }

      if (var5 || var2 != 0.0F || var4 != 0.0F) {
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   private void recoverFocusFromState() {
      if (this.mPreserveFocusAfterLayout && this.mAdapter != null && this.hasFocus() && this.getDescendantFocusability() != 393216 && (this.getDescendantFocusability() != 131072 || !this.isFocused())) {
         View var3;
         if (!this.isFocused()) {
            var3 = this.getFocusedChild();
            if (IGNORE_DETACHED_FOCUSED_CHILD && (var3.getParent() == null || !var3.hasFocus())) {
               if (this.mChildHelper.getChildCount() == 0) {
                  this.requestFocus();
                  return;
               }
            } else if (!this.mChildHelper.isHidden(var3)) {
               return;
            }
         }

         long var1 = this.mState.mFocusedItemId;
         View var4 = null;
         ViewHolder var6;
         if (var1 != -1L && this.mAdapter.hasStableIds()) {
            var6 = this.findViewHolderForItemId(this.mState.mFocusedItemId);
         } else {
            var6 = null;
         }

         if (var6 != null && !this.mChildHelper.isHidden(var6.itemView) && var6.itemView.hasFocusable()) {
            var3 = var6.itemView;
         } else {
            var3 = var4;
            if (this.mChildHelper.getChildCount() > 0) {
               var3 = this.findNextViewToFocus();
            }
         }

         if (var3 != null) {
            var4 = var3;
            if ((long)this.mState.mFocusedSubChildId != -1L) {
               View var5 = var3.findViewById(this.mState.mFocusedSubChildId);
               var4 = var3;
               if (var5 != null) {
                  var4 = var3;
                  if (var5.isFocusable()) {
                     var4 = var5;
                  }
               }
            }

            var4.requestFocus();
         }
      }

   }

   private void releaseGlows() {
      EdgeEffect var3 = this.mLeftGlow;
      boolean var2;
      if (var3 != null) {
         var3.onRelease();
         var2 = this.mLeftGlow.isFinished();
      } else {
         var2 = false;
      }

      var3 = this.mTopGlow;
      boolean var1 = var2;
      if (var3 != null) {
         var3.onRelease();
         var1 = var2 | this.mTopGlow.isFinished();
      }

      var3 = this.mRightGlow;
      var2 = var1;
      if (var3 != null) {
         var3.onRelease();
         var2 = var1 | this.mRightGlow.isFinished();
      }

      var3 = this.mBottomGlow;
      var1 = var2;
      if (var3 != null) {
         var3.onRelease();
         var1 = var2 | this.mBottomGlow.isFinished();
      }

      if (var1) {
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   private void requestChildOnScreen(View var1, View var2) {
      View var5;
      if (var2 != null) {
         var5 = var2;
      } else {
         var5 = var1;
      }

      this.mTempRect.set(0, 0, var5.getWidth(), var5.getHeight());
      ViewGroup.LayoutParams var7 = var5.getLayoutParams();
      Rect var9;
      if (var7 instanceof LayoutParams) {
         LayoutParams var8 = (LayoutParams)var7;
         if (!var8.mInsetsDirty) {
            var9 = var8.mDecorInsets;
            Rect var6 = this.mTempRect;
            var6.left -= var9.left;
            var6 = this.mTempRect;
            var6.right += var9.right;
            var6 = this.mTempRect;
            var6.top -= var9.top;
            var6 = this.mTempRect;
            var6.bottom += var9.bottom;
         }
      }

      if (var2 != null) {
         this.offsetDescendantRectToMyCoords(var2, this.mTempRect);
         this.offsetRectIntoDescendantCoords(var1, this.mTempRect);
      }

      LayoutManager var10 = this.mLayout;
      var9 = this.mTempRect;
      boolean var4 = this.mFirstLayoutComplete;
      boolean var3;
      if (var2 == null) {
         var3 = true;
      } else {
         var3 = false;
      }

      var10.requestChildRectangleOnScreen(this, var1, var9, var4 ^ true, var3);
   }

   private void resetFocusInfo() {
      this.mState.mFocusedItemId = -1L;
      this.mState.mFocusedItemPosition = -1;
      this.mState.mFocusedSubChildId = -1;
   }

   private void resetScroll() {
      VelocityTracker var1 = this.mVelocityTracker;
      if (var1 != null) {
         var1.clear();
      }

      this.stopNestedScroll(0);
      this.releaseGlows();
   }

   private void saveFocusInfo() {
      boolean var2 = this.mPreserveFocusAfterLayout;
      State var6 = null;
      View var5;
      if (var2 && this.hasFocus() && this.mAdapter != null) {
         var5 = this.getFocusedChild();
      } else {
         var5 = null;
      }

      ViewHolder var7;
      if (var5 == null) {
         var7 = var6;
      } else {
         var7 = this.findContainingViewHolder(var5);
      }

      if (var7 == null) {
         this.resetFocusInfo();
      } else {
         var6 = this.mState;
         long var3;
         if (this.mAdapter.hasStableIds()) {
            var3 = var7.getItemId();
         } else {
            var3 = -1L;
         }

         var6.mFocusedItemId = var3;
         var6 = this.mState;
         int var1;
         if (this.mDataSetHasChangedAfterLayout) {
            var1 = -1;
         } else if (var7.isRemoved()) {
            var1 = var7.mOldPosition;
         } else {
            var1 = var7.getAbsoluteAdapterPosition();
         }

         var6.mFocusedItemPosition = var1;
         this.mState.mFocusedSubChildId = this.getDeepestFocusedViewWithId(var7.itemView);
      }

   }

   private void setAdapterInternal(Adapter var1, boolean var2, boolean var3) {
      Adapter var4 = this.mAdapter;
      if (var4 != null) {
         var4.unregisterAdapterDataObserver(this.mObserver);
         this.mAdapter.onDetachedFromRecyclerView(this);
      }

      if (!var2 || var3) {
         this.removeAndRecycleViews();
      }

      this.mAdapterHelper.reset();
      var4 = this.mAdapter;
      this.mAdapter = var1;
      if (var1 != null) {
         var1.registerAdapterDataObserver(this.mObserver);
         var1.onAttachedToRecyclerView(this);
      }

      LayoutManager var5 = this.mLayout;
      if (var5 != null) {
         var5.onAdapterChanged(var4, this.mAdapter);
      }

      this.mRecycler.onAdapterChanged(var4, this.mAdapter, var2);
      this.mState.mStructureChanged = true;
   }

   private void stopScrollersInternal() {
      this.mViewFlinger.stop();
      LayoutManager var1 = this.mLayout;
      if (var1 != null) {
         var1.stopSmoothScroller();
      }

   }

   void absorbGlows(int var1, int var2) {
      if (var1 < 0) {
         this.ensureLeftGlow();
         if (this.mLeftGlow.isFinished()) {
            this.mLeftGlow.onAbsorb(-var1);
         }
      } else if (var1 > 0) {
         this.ensureRightGlow();
         if (this.mRightGlow.isFinished()) {
            this.mRightGlow.onAbsorb(var1);
         }
      }

      if (var2 < 0) {
         this.ensureTopGlow();
         if (this.mTopGlow.isFinished()) {
            this.mTopGlow.onAbsorb(-var2);
         }
      } else if (var2 > 0) {
         this.ensureBottomGlow();
         if (this.mBottomGlow.isFinished()) {
            this.mBottomGlow.onAbsorb(var2);
         }
      }

      if (var1 != 0 || var2 != 0) {
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   public void addFocusables(ArrayList var1, int var2, int var3) {
      LayoutManager var4 = this.mLayout;
      if (var4 == null || !var4.onAddFocusables(this, var1, var2, var3)) {
         super.addFocusables(var1, var2, var3);
      }

   }

   public void addItemDecoration(ItemDecoration var1) {
      this.addItemDecoration(var1, -1);
   }

   public void addItemDecoration(ItemDecoration var1, int var2) {
      LayoutManager var3 = this.mLayout;
      if (var3 != null) {
         var3.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
      }

      if (this.mItemDecorations.isEmpty()) {
         this.setWillNotDraw(false);
      }

      if (var2 < 0) {
         this.mItemDecorations.add(var1);
      } else {
         this.mItemDecorations.add(var2, var1);
      }

      this.markItemDecorInsetsDirty();
      this.requestLayout();
   }

   public void addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener var1) {
      if (this.mOnChildAttachStateListeners == null) {
         this.mOnChildAttachStateListeners = new ArrayList();
      }

      this.mOnChildAttachStateListeners.add(var1);
   }

   public void addOnItemTouchListener(OnItemTouchListener var1) {
      this.mOnItemTouchListeners.add(var1);
   }

   public void addOnScrollListener(OnScrollListener var1) {
      if (this.mScrollListeners == null) {
         this.mScrollListeners = new ArrayList();
      }

      this.mScrollListeners.add(var1);
   }

   public void addRecyclerListener(RecyclerListener var1) {
      boolean var2;
      if (var1 != null) {
         var2 = true;
      } else {
         var2 = false;
      }

      Preconditions.checkArgument(var2, "'listener' arg cannot be null.");
      this.mRecyclerListeners.add(var1);
   }

   void animateAppearance(ViewHolder var1, ItemAnimator.ItemHolderInfo var2, ItemAnimator.ItemHolderInfo var3) {
      var1.setIsRecyclable(false);
      if (this.mItemAnimator.animateAppearance(var1, var2, var3)) {
         this.postAnimationRunner();
      }

   }

   void animateDisappearance(ViewHolder var1, ItemAnimator.ItemHolderInfo var2, ItemAnimator.ItemHolderInfo var3) {
      this.addAnimatingView(var1);
      var1.setIsRecyclable(false);
      if (this.mItemAnimator.animateDisappearance(var1, var2, var3)) {
         this.postAnimationRunner();
      }

   }

   void assertInLayoutOrScroll(String var1) {
      if (!this.isComputingLayout()) {
         if (var1 == null) {
            throw new IllegalStateException("Cannot call this method unless RecyclerView is computing a layout or scrolling" + this.exceptionLabel());
         } else {
            throw new IllegalStateException(var1 + this.exceptionLabel());
         }
      }
   }

   void assertNotInLayoutOrScroll(String var1) {
      if (this.isComputingLayout()) {
         if (var1 == null) {
            throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling" + this.exceptionLabel());
         } else {
            throw new IllegalStateException(var1);
         }
      } else {
         if (this.mDispatchScrollCounter > 0) {
            Log.w("RecyclerView", "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException("" + this.exceptionLabel()));
         }

      }
   }

   boolean canReuseUpdatedViewHolder(ViewHolder var1) {
      ItemAnimator var3 = this.mItemAnimator;
      boolean var2;
      if (var3 != null && !var3.canReuseUpdatedViewHolder(var1, var1.getUnmodifiedPayloads())) {
         var2 = false;
      } else {
         var2 = true;
      }

      return var2;
   }

   protected boolean checkLayoutParams(ViewGroup.LayoutParams var1) {
      boolean var2;
      if (var1 instanceof LayoutParams && this.mLayout.checkLayoutParams((LayoutParams)var1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   void clearOldPositions() {
      int var2 = this.mChildHelper.getUnfilteredChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         ViewHolder var3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var1));
         if (!var3.shouldIgnore()) {
            var3.clearOldPosition();
         }
      }

      this.mRecycler.clearOldPositions();
   }

   public void clearOnChildAttachStateChangeListeners() {
      List var1 = this.mOnChildAttachStateListeners;
      if (var1 != null) {
         var1.clear();
      }

   }

   public void clearOnScrollListeners() {
      List var1 = this.mScrollListeners;
      if (var1 != null) {
         var1.clear();
      }

   }

   public int computeHorizontalScrollExtent() {
      LayoutManager var2 = this.mLayout;
      int var1 = 0;
      if (var2 == null) {
         return 0;
      } else {
         if (var2.canScrollHorizontally()) {
            var1 = this.mLayout.computeHorizontalScrollExtent(this.mState);
         }

         return var1;
      }
   }

   public int computeHorizontalScrollOffset() {
      LayoutManager var2 = this.mLayout;
      int var1 = 0;
      if (var2 == null) {
         return 0;
      } else {
         if (var2.canScrollHorizontally()) {
            var1 = this.mLayout.computeHorizontalScrollOffset(this.mState);
         }

         return var1;
      }
   }

   public int computeHorizontalScrollRange() {
      LayoutManager var2 = this.mLayout;
      int var1 = 0;
      if (var2 == null) {
         return 0;
      } else {
         if (var2.canScrollHorizontally()) {
            var1 = this.mLayout.computeHorizontalScrollRange(this.mState);
         }

         return var1;
      }
   }

   public int computeVerticalScrollExtent() {
      LayoutManager var2 = this.mLayout;
      int var1 = 0;
      if (var2 == null) {
         return 0;
      } else {
         if (var2.canScrollVertically()) {
            var1 = this.mLayout.computeVerticalScrollExtent(this.mState);
         }

         return var1;
      }
   }

   public int computeVerticalScrollOffset() {
      LayoutManager var2 = this.mLayout;
      int var1 = 0;
      if (var2 == null) {
         return 0;
      } else {
         if (var2.canScrollVertically()) {
            var1 = this.mLayout.computeVerticalScrollOffset(this.mState);
         }

         return var1;
      }
   }

   public int computeVerticalScrollRange() {
      LayoutManager var2 = this.mLayout;
      int var1 = 0;
      if (var2 == null) {
         return 0;
      } else {
         if (var2.canScrollVertically()) {
            var1 = this.mLayout.computeVerticalScrollRange(this.mState);
         }

         return var1;
      }
   }

   void considerReleasingGlowsOnScroll(int var1, int var2) {
      EdgeEffect var5 = this.mLeftGlow;
      boolean var4;
      if (var5 != null && !var5.isFinished() && var1 > 0) {
         this.mLeftGlow.onRelease();
         var4 = this.mLeftGlow.isFinished();
      } else {
         var4 = false;
      }

      var5 = this.mRightGlow;
      boolean var3 = var4;
      if (var5 != null) {
         var3 = var4;
         if (!var5.isFinished()) {
            var3 = var4;
            if (var1 < 0) {
               this.mRightGlow.onRelease();
               var3 = var4 | this.mRightGlow.isFinished();
            }
         }
      }

      var5 = this.mTopGlow;
      var4 = var3;
      if (var5 != null) {
         var4 = var3;
         if (!var5.isFinished()) {
            var4 = var3;
            if (var2 > 0) {
               this.mTopGlow.onRelease();
               var4 = var3 | this.mTopGlow.isFinished();
            }
         }
      }

      var5 = this.mBottomGlow;
      var3 = var4;
      if (var5 != null) {
         var3 = var4;
         if (!var5.isFinished()) {
            var3 = var4;
            if (var2 < 0) {
               this.mBottomGlow.onRelease();
               var3 = var4 | this.mBottomGlow.isFinished();
            }
         }
      }

      if (var3) {
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   void consumePendingUpdateOperations() {
      if (this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout) {
         if (this.mAdapterHelper.hasPendingUpdates()) {
            if (this.mAdapterHelper.hasAnyUpdateTypes(4) && !this.mAdapterHelper.hasAnyUpdateTypes(11)) {
               TraceCompat.beginSection("RV PartialInvalidate");
               this.startInterceptRequestLayout();
               this.onEnterLayoutOrScroll();
               this.mAdapterHelper.preProcess();
               if (!this.mLayoutWasDefered) {
                  if (this.hasUpdatedView()) {
                     this.dispatchLayout();
                  } else {
                     this.mAdapterHelper.consumePostponedUpdates();
                  }
               }

               this.stopInterceptRequestLayout(true);
               this.onExitLayoutOrScroll();
               TraceCompat.endSection();
            } else if (this.mAdapterHelper.hasPendingUpdates()) {
               TraceCompat.beginSection("RV FullInvalidate");
               this.dispatchLayout();
               TraceCompat.endSection();
            }

         }
      } else {
         TraceCompat.beginSection("RV FullInvalidate");
         this.dispatchLayout();
         TraceCompat.endSection();
      }
   }

   void defaultOnMeasure(int var1, int var2) {
      this.setMeasuredDimension(RecyclerView.LayoutManager.chooseSize(var1, this.getPaddingLeft() + this.getPaddingRight(), ViewCompat.getMinimumWidth(this)), RecyclerView.LayoutManager.chooseSize(var2, this.getPaddingTop() + this.getPaddingBottom(), ViewCompat.getMinimumHeight(this)));
   }

   void dispatchChildAttached(View var1) {
      ViewHolder var4 = getChildViewHolderInt(var1);
      this.onChildAttachedToWindow(var1);
      Adapter var3 = this.mAdapter;
      if (var3 != null && var4 != null) {
         var3.onViewAttachedToWindow(var4);
      }

      List var5 = this.mOnChildAttachStateListeners;
      if (var5 != null) {
         for(int var2 = var5.size() - 1; var2 >= 0; --var2) {
            ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(var2)).onChildViewAttachedToWindow(var1);
         }
      }

   }

   void dispatchChildDetached(View var1) {
      ViewHolder var3 = getChildViewHolderInt(var1);
      this.onChildDetachedFromWindow(var1);
      Adapter var4 = this.mAdapter;
      if (var4 != null && var3 != null) {
         var4.onViewDetachedFromWindow(var3);
      }

      List var5 = this.mOnChildAttachStateListeners;
      if (var5 != null) {
         for(int var2 = var5.size() - 1; var2 >= 0; --var2) {
            ((OnChildAttachStateChangeListener)this.mOnChildAttachStateListeners.get(var2)).onChildViewDetachedFromWindow(var1);
         }
      }

   }

   void dispatchLayout() {
      if (this.mAdapter == null) {
         Log.w("RecyclerView", "No adapter attached; skipping layout");
      } else if (this.mLayout == null) {
         Log.e("RecyclerView", "No layout manager attached; skipping layout");
      } else {
         this.mState.mIsMeasuring = false;
         boolean var1;
         if (!this.mLastAutoMeasureSkippedDueToExact || this.mLastAutoMeasureNonExactMeasuredWidth == this.getWidth() && this.mLastAutoMeasureNonExactMeasuredHeight == this.getHeight()) {
            var1 = false;
         } else {
            var1 = true;
         }

         this.mLastAutoMeasureNonExactMeasuredWidth = 0;
         this.mLastAutoMeasureNonExactMeasuredHeight = 0;
         this.mLastAutoMeasureSkippedDueToExact = false;
         if (this.mState.mLayoutStep == 1) {
            this.dispatchLayoutStep1();
            this.mLayout.setExactMeasureSpecsFrom(this);
            this.dispatchLayoutStep2();
         } else if (!this.mAdapterHelper.hasUpdates() && !var1 && this.mLayout.getWidth() == this.getWidth() && this.mLayout.getHeight() == this.getHeight()) {
            this.mLayout.setExactMeasureSpecsFrom(this);
         } else {
            this.mLayout.setExactMeasureSpecsFrom(this);
            this.dispatchLayoutStep2();
         }

         this.dispatchLayoutStep3();
      }
   }

   public boolean dispatchNestedFling(float var1, float var2, boolean var3) {
      return this.getScrollingChildHelper().dispatchNestedFling(var1, var2, var3);
   }

   public boolean dispatchNestedPreFling(float var1, float var2) {
      return this.getScrollingChildHelper().dispatchNestedPreFling(var1, var2);
   }

   public boolean dispatchNestedPreScroll(int var1, int var2, int[] var3, int[] var4) {
      return this.getScrollingChildHelper().dispatchNestedPreScroll(var1, var2, var3, var4);
   }

   public boolean dispatchNestedPreScroll(int var1, int var2, int[] var3, int[] var4, int var5) {
      return this.getScrollingChildHelper().dispatchNestedPreScroll(var1, var2, var3, var4, var5);
   }

   public final void dispatchNestedScroll(int var1, int var2, int var3, int var4, int[] var5, int var6, int[] var7) {
      this.getScrollingChildHelper().dispatchNestedScroll(var1, var2, var3, var4, var5, var6, var7);
   }

   public boolean dispatchNestedScroll(int var1, int var2, int var3, int var4, int[] var5) {
      return this.getScrollingChildHelper().dispatchNestedScroll(var1, var2, var3, var4, var5);
   }

   public boolean dispatchNestedScroll(int var1, int var2, int var3, int var4, int[] var5, int var6) {
      return this.getScrollingChildHelper().dispatchNestedScroll(var1, var2, var3, var4, var5, var6);
   }

   void dispatchOnScrollStateChanged(int var1) {
      LayoutManager var3 = this.mLayout;
      if (var3 != null) {
         var3.onScrollStateChanged(var1);
      }

      this.onScrollStateChanged(var1);
      OnScrollListener var4 = this.mScrollListener;
      if (var4 != null) {
         var4.onScrollStateChanged(this, var1);
      }

      List var5 = this.mScrollListeners;
      if (var5 != null) {
         for(int var2 = var5.size() - 1; var2 >= 0; --var2) {
            ((OnScrollListener)this.mScrollListeners.get(var2)).onScrollStateChanged(this, var1);
         }
      }

   }

   void dispatchOnScrolled(int var1, int var2) {
      ++this.mDispatchScrollCounter;
      int var3 = this.getScrollX();
      int var4 = this.getScrollY();
      this.onScrollChanged(var3, var4, var3 - var1, var4 - var2);
      this.onScrolled(var1, var2);
      OnScrollListener var5 = this.mScrollListener;
      if (var5 != null) {
         var5.onScrolled(this, var1, var2);
      }

      List var6 = this.mScrollListeners;
      if (var6 != null) {
         for(var3 = var6.size() - 1; var3 >= 0; --var3) {
            ((OnScrollListener)this.mScrollListeners.get(var3)).onScrolled(this, var1, var2);
         }
      }

      --this.mDispatchScrollCounter;
   }

   void dispatchPendingImportantForAccessibilityChanges() {
      for(int var1 = this.mPendingAccessibilityImportanceChange.size() - 1; var1 >= 0; --var1) {
         ViewHolder var3 = (ViewHolder)this.mPendingAccessibilityImportanceChange.get(var1);
         if (var3.itemView.getParent() == this && !var3.shouldIgnore()) {
            int var2 = var3.mPendingAccessibilityState;
            if (var2 != -1) {
               ViewCompat.setImportantForAccessibility(var3.itemView, var2);
               var3.mPendingAccessibilityState = -1;
            }
         }
      }

      this.mPendingAccessibilityImportanceChange.clear();
   }

   public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent var1) {
      this.onPopulateAccessibilityEvent(var1);
      return true;
   }

   protected void dispatchRestoreInstanceState(SparseArray var1) {
      this.dispatchThawSelfOnly(var1);
   }

   protected void dispatchSaveInstanceState(SparseArray var1) {
      this.dispatchFreezeSelfOnly(var1);
   }

   public void draw(Canvas var1) {
      super.draw(var1);
      int var3 = this.mItemDecorations.size();
      boolean var4 = false;

      int var2;
      for(var2 = 0; var2 < var3; ++var2) {
         ((ItemDecoration)this.mItemDecorations.get(var2)).onDrawOver(var1, this, this.mState);
      }

      EdgeEffect var8 = this.mLeftGlow;
      boolean var5 = true;
      int var6;
      boolean var9;
      if (var8 != null && !var8.isFinished()) {
         var6 = var1.save();
         if (this.mClipToPadding) {
            var2 = this.getPaddingBottom();
         } else {
            var2 = 0;
         }

         var1.rotate(270.0F);
         var1.translate((float)(-this.getHeight() + var2), 0.0F);
         var8 = this.mLeftGlow;
         if (var8 != null && var8.draw(var1)) {
            var9 = true;
         } else {
            var9 = false;
         }

         var1.restoreToCount(var6);
      } else {
         var9 = false;
      }

      var8 = this.mTopGlow;
      boolean var10 = var9;
      if (var8 != null) {
         var10 = var9;
         if (!var8.isFinished()) {
            var6 = var1.save();
            if (this.mClipToPadding) {
               var1.translate((float)this.getPaddingLeft(), (float)this.getPaddingTop());
            }

            var8 = this.mTopGlow;
            if (var8 != null && var8.draw(var1)) {
               var10 = true;
            } else {
               var10 = false;
            }

            var10 |= var9;
            var1.restoreToCount(var6);
         }
      }

      var8 = this.mRightGlow;
      var9 = var10;
      if (var8 != null) {
         var9 = var10;
         if (!var8.isFinished()) {
            var6 = var1.save();
            int var7 = this.getWidth();
            if (this.mClipToPadding) {
               var3 = this.getPaddingTop();
            } else {
               var3 = 0;
            }

            var1.rotate(90.0F);
            var1.translate((float)var3, (float)(-var7));
            var8 = this.mRightGlow;
            if (var8 != null && var8.draw(var1)) {
               var9 = true;
            } else {
               var9 = false;
            }

            var9 |= var10;
            var1.restoreToCount(var6);
         }
      }

      var8 = this.mBottomGlow;
      var10 = var9;
      if (var8 != null) {
         var10 = var9;
         if (!var8.isFinished()) {
            var6 = var1.save();
            var1.rotate(180.0F);
            if (this.mClipToPadding) {
               var1.translate((float)(-this.getWidth() + this.getPaddingRight()), (float)(-this.getHeight() + this.getPaddingBottom()));
            } else {
               var1.translate((float)(-this.getWidth()), (float)(-this.getHeight()));
            }

            var8 = this.mBottomGlow;
            var10 = var4;
            if (var8 != null) {
               var10 = var4;
               if (var8.draw(var1)) {
                  var10 = true;
               }
            }

            var10 |= var9;
            var1.restoreToCount(var6);
         }
      }

      if (!var10 && this.mItemAnimator != null && this.mItemDecorations.size() > 0 && this.mItemAnimator.isRunning()) {
         var10 = var5;
      }

      if (var10) {
         ViewCompat.postInvalidateOnAnimation(this);
      }

   }

   public boolean drawChild(Canvas var1, View var2, long var3) {
      return super.drawChild(var1, var2, var3);
   }

   void ensureBottomGlow() {
      if (this.mBottomGlow == null) {
         EdgeEffect var1 = this.mEdgeEffectFactory.createEdgeEffect(this, 3);
         this.mBottomGlow = var1;
         if (this.mClipToPadding) {
            var1.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
         } else {
            var1.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
         }

      }
   }

   void ensureLeftGlow() {
      if (this.mLeftGlow == null) {
         EdgeEffect var1 = this.mEdgeEffectFactory.createEdgeEffect(this, 0);
         this.mLeftGlow = var1;
         if (this.mClipToPadding) {
            var1.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
         } else {
            var1.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
         }

      }
   }

   void ensureRightGlow() {
      if (this.mRightGlow == null) {
         EdgeEffect var1 = this.mEdgeEffectFactory.createEdgeEffect(this, 2);
         this.mRightGlow = var1;
         if (this.mClipToPadding) {
            var1.setSize(this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom(), this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight());
         } else {
            var1.setSize(this.getMeasuredHeight(), this.getMeasuredWidth());
         }

      }
   }

   void ensureTopGlow() {
      if (this.mTopGlow == null) {
         EdgeEffect var1 = this.mEdgeEffectFactory.createEdgeEffect(this, 1);
         this.mTopGlow = var1;
         if (this.mClipToPadding) {
            var1.setSize(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getMeasuredHeight() - this.getPaddingTop() - this.getPaddingBottom());
         } else {
            var1.setSize(this.getMeasuredWidth(), this.getMeasuredHeight());
         }

      }
   }

   String exceptionLabel() {
      return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + this.getContext();
   }

   final void fillRemainingScrollValues(State var1) {
      if (this.getScrollState() == 2) {
         OverScroller var2 = this.mViewFlinger.mOverScroller;
         var1.mRemainingScrollHorizontal = var2.getFinalX() - var2.getCurrX();
         var1.mRemainingScrollVertical = var2.getFinalY() - var2.getCurrY();
      } else {
         var1.mRemainingScrollHorizontal = 0;
         var1.mRemainingScrollVertical = 0;
      }

   }

   public View findChildViewUnder(float var1, float var2) {
      for(int var5 = this.mChildHelper.getChildCount() - 1; var5 >= 0; --var5) {
         View var6 = this.mChildHelper.getChildAt(var5);
         float var4 = var6.getTranslationX();
         float var3 = var6.getTranslationY();
         if (var1 >= (float)var6.getLeft() + var4 && var1 <= (float)var6.getRight() + var4 && var2 >= (float)var6.getTop() + var3 && var2 <= (float)var6.getBottom() + var3) {
            return var6;
         }
      }

      return null;
   }

   public View findContainingItemView(View var1) {
      ViewParent var2;
      for(var2 = var1.getParent(); var2 != null && var2 != this && var2 instanceof View; var2 = var1.getParent()) {
         var1 = (View)var2;
      }

      if (var2 != this) {
         var1 = null;
      }

      return var1;
   }

   public ViewHolder findContainingViewHolder(View var1) {
      var1 = this.findContainingItemView(var1);
      ViewHolder var2;
      if (var1 == null) {
         var2 = null;
      } else {
         var2 = this.getChildViewHolder(var1);
      }

      return var2;
   }

   public ViewHolder findViewHolderForAdapterPosition(int var1) {
      boolean var4 = this.mDataSetHasChangedAfterLayout;
      ViewHolder var6 = null;
      if (var4) {
         return null;
      } else {
         int var3 = this.mChildHelper.getUnfilteredChildCount();

         ViewHolder var5;
         for(int var2 = 0; var2 < var3; var6 = var5) {
            ViewHolder var7 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var2));
            var5 = var6;
            if (var7 != null) {
               var5 = var6;
               if (!var7.isRemoved()) {
                  var5 = var6;
                  if (this.getAdapterPositionInRecyclerView(var7) == var1) {
                     if (!this.mChildHelper.isHidden(var7.itemView)) {
                        return var7;
                     }

                     var5 = var7;
                  }
               }
            }

            ++var2;
         }

         return var6;
      }
   }

   public ViewHolder findViewHolderForItemId(long var1) {
      Adapter var8 = this.mAdapter;
      ViewHolder var5 = null;
      ViewHolder var7 = null;
      ViewHolder var6 = var5;
      if (var8 != null) {
         if (!var8.hasStableIds()) {
            var6 = var5;
         } else {
            int var4 = this.mChildHelper.getUnfilteredChildCount();
            int var3 = 0;
            var5 = var7;

            while(true) {
               var6 = var5;
               if (var3 >= var4) {
                  break;
               }

               var7 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var3));
               var6 = var5;
               if (var7 != null) {
                  var6 = var5;
                  if (!var7.isRemoved()) {
                     var6 = var5;
                     if (var7.getItemId() == var1) {
                        if (!this.mChildHelper.isHidden(var7.itemView)) {
                           return var7;
                        }

                        var6 = var7;
                     }
                  }
               }

               ++var3;
               var5 = var6;
            }
         }
      }

      return var6;
   }

   public ViewHolder findViewHolderForLayoutPosition(int var1) {
      return this.findViewHolderForPosition(var1, false);
   }

   @Deprecated
   public ViewHolder findViewHolderForPosition(int var1) {
      return this.findViewHolderForPosition(var1, false);
   }

   ViewHolder findViewHolderForPosition(int var1, boolean var2) {
      int var4 = this.mChildHelper.getUnfilteredChildCount();
      ViewHolder var5 = null;

      ViewHolder var6;
      for(int var3 = 0; var3 < var4; var5 = var6) {
         ViewHolder var7 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var3));
         var6 = var5;
         if (var7 != null) {
            var6 = var5;
            if (!var7.isRemoved()) {
               label37: {
                  if (var2) {
                     if (var7.mPosition != var1) {
                        var6 = var5;
                        break label37;
                     }
                  } else if (var7.getLayoutPosition() != var1) {
                     var6 = var5;
                     break label37;
                  }

                  if (!this.mChildHelper.isHidden(var7.itemView)) {
                     return var7;
                  }

                  var6 = var7;
               }
            }
         }

         ++var3;
      }

      return var5;
   }

   public boolean fling(int var1, int var2) {
      LayoutManager var9 = this.mLayout;
      if (var9 == null) {
         Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
         return false;
      } else if (this.mLayoutSuppressed) {
         return false;
      } else {
         int var5;
         byte var6;
         boolean var8;
         label56: {
            var6 = var9.canScrollHorizontally();
            var8 = this.mLayout.canScrollVertically();
            if (var6 != 0) {
               var5 = var1;
               if (Math.abs(var1) >= this.mMinFlingVelocity) {
                  break label56;
               }
            }

            var5 = 0;
         }

         label51: {
            if (var8) {
               var1 = var2;
               if (Math.abs(var2) >= this.mMinFlingVelocity) {
                  break label51;
               }
            }

            var1 = 0;
         }

         if (var5 == 0 && var1 == 0) {
            return false;
         } else {
            float var3 = (float)var5;
            float var4 = (float)var1;
            if (!this.dispatchNestedPreFling(var3, var4)) {
               boolean var7;
               if (var6 == 0 && !var8) {
                  var7 = false;
               } else {
                  var7 = true;
               }

               this.dispatchNestedFling(var3, var4, var7);
               OnFlingListener var10 = this.mOnFlingListener;
               if (var10 != null && var10.onFling(var5, var1)) {
                  return true;
               }

               if (var7) {
                  var2 = var6;
                  if (var8) {
                     var2 = var6 | 2;
                  }

                  this.startNestedScroll(var2, 1);
                  var2 = this.mMaxFlingVelocity;
                  var2 = Math.max(-var2, Math.min(var5, var2));
                  var5 = this.mMaxFlingVelocity;
                  var1 = Math.max(-var5, Math.min(var1, var5));
                  this.mViewFlinger.fling(var2, var1);
                  return true;
               }
            }

            return false;
         }
      }
   }

   public View focusSearch(View var1, int var2) {
      View var7 = this.mLayout.onInterceptFocusSearch(var1, var2);
      if (var7 != null) {
         return var7;
      } else {
         Adapter var10 = this.mAdapter;
         boolean var6 = true;
         boolean var3;
         if (var10 != null && this.mLayout != null && !this.isComputingLayout() && !this.mLayoutSuppressed) {
            var3 = true;
         } else {
            var3 = false;
         }

         FocusFinder var11 = FocusFinder.getInstance();
         if (var3 && (var2 == 2 || var2 == 1)) {
            int var4;
            boolean var5;
            if (this.mLayout.canScrollVertically()) {
               if (var2 == 2) {
                  var4 = 130;
               } else {
                  var4 = 33;
               }

               if (var11.findNextFocus(this, var1, var4) == null) {
                  var5 = true;
               } else {
                  var5 = false;
               }

               var3 = var5;
               if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                  var2 = var4;
                  var3 = var5;
               }
            } else {
               var3 = false;
            }

            var5 = var3;
            var4 = var2;
            if (!var3) {
               var5 = var3;
               var4 = var2;
               if (this.mLayout.canScrollHorizontally()) {
                  if (this.mLayout.getLayoutDirection() == 1) {
                     var3 = true;
                  } else {
                     var3 = false;
                  }

                  boolean var8;
                  if (var2 == 2) {
                     var8 = true;
                  } else {
                     var8 = false;
                  }

                  byte var9;
                  if (var3 ^ var8) {
                     var9 = 66;
                  } else {
                     var9 = 17;
                  }

                  if (var11.findNextFocus(this, var1, var9) == null) {
                     var8 = var6;
                  } else {
                     var8 = false;
                  }

                  if (FORCE_ABS_FOCUS_SEARCH_DIRECTION) {
                     var2 = var9;
                  }

                  var5 = var8;
                  var4 = var2;
               }
            }

            if (var5) {
               this.consumePendingUpdateOperations();
               if (this.findContainingItemView(var1) == null) {
                  return null;
               }

               this.startInterceptRequestLayout();
               this.mLayout.onFocusSearchFailed(var1, var4, this.mRecycler, this.mState);
               this.stopInterceptRequestLayout(false);
            }

            var7 = var11.findNextFocus(this, var1, var4);
            var2 = var4;
         } else {
            var7 = var11.findNextFocus(this, var1, var2);
            if (var7 == null && var3) {
               this.consumePendingUpdateOperations();
               if (this.findContainingItemView(var1) == null) {
                  return null;
               }

               this.startInterceptRequestLayout();
               var7 = this.mLayout.onFocusSearchFailed(var1, var2, this.mRecycler, this.mState);
               this.stopInterceptRequestLayout(false);
            }
         }

         if (var7 != null && !var7.hasFocusable()) {
            if (this.getFocusedChild() == null) {
               return super.focusSearch(var1, var2);
            } else {
               this.requestChildOnScreen(var7, (View)null);
               return var1;
            }
         } else {
            if (!this.isPreferredNextFocus(var1, var7, var2)) {
               var7 = super.focusSearch(var1, var2);
            }

            return var7;
         }
      }
   }

   protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
      LayoutManager var1 = this.mLayout;
      if (var1 != null) {
         return var1.generateDefaultLayoutParams();
      } else {
         throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
      }
   }

   public ViewGroup.LayoutParams generateLayoutParams(AttributeSet var1) {
      LayoutManager var2 = this.mLayout;
      if (var2 != null) {
         return var2.generateLayoutParams(this.getContext(), var1);
      } else {
         throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
      }
   }

   protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
      LayoutManager var2 = this.mLayout;
      if (var2 != null) {
         return var2.generateLayoutParams(var1);
      } else {
         throw new IllegalStateException("RecyclerView has no LayoutManager" + this.exceptionLabel());
      }
   }

   public CharSequence getAccessibilityClassName() {
      return "androidx.recyclerview.widget.RecyclerView";
   }

   public Adapter getAdapter() {
      return this.mAdapter;
   }

   int getAdapterPositionInRecyclerView(ViewHolder var1) {
      return !var1.hasAnyOfTheFlags(524) && var1.isBound() ? this.mAdapterHelper.applyPendingUpdatesToPosition(var1.mPosition) : -1;
   }

   public int getBaseline() {
      LayoutManager var1 = this.mLayout;
      return var1 != null ? var1.getBaseline() : super.getBaseline();
   }

   long getChangedHolderKey(ViewHolder var1) {
      long var2;
      if (this.mAdapter.hasStableIds()) {
         var2 = var1.getItemId();
      } else {
         var2 = (long)var1.mPosition;
      }

      return var2;
   }

   public int getChildAdapterPosition(View var1) {
      ViewHolder var3 = getChildViewHolderInt(var1);
      int var2;
      if (var3 != null) {
         var2 = var3.getAbsoluteAdapterPosition();
      } else {
         var2 = -1;
      }

      return var2;
   }

   protected int getChildDrawingOrder(int var1, int var2) {
      ChildDrawingOrderCallback var3 = this.mChildDrawingOrderCallback;
      return var3 == null ? super.getChildDrawingOrder(var1, var2) : var3.onGetChildDrawingOrder(var1, var2);
   }

   public long getChildItemId(View var1) {
      Adapter var6 = this.mAdapter;
      long var4 = -1L;
      long var2 = var4;
      if (var6 != null) {
         if (!var6.hasStableIds()) {
            var2 = var4;
         } else {
            ViewHolder var7 = getChildViewHolderInt(var1);
            var2 = var4;
            if (var7 != null) {
               var2 = var7.getItemId();
            }
         }
      }

      return var2;
   }

   public int getChildLayoutPosition(View var1) {
      ViewHolder var3 = getChildViewHolderInt(var1);
      int var2;
      if (var3 != null) {
         var2 = var3.getLayoutPosition();
      } else {
         var2 = -1;
      }

      return var2;
   }

   @Deprecated
   public int getChildPosition(View var1) {
      return this.getChildAdapterPosition(var1);
   }

   public ViewHolder getChildViewHolder(View var1) {
      ViewParent var2 = var1.getParent();
      if (var2 != null && var2 != this) {
         throw new IllegalArgumentException("View " + var1 + " is not a direct child of " + this);
      } else {
         return getChildViewHolderInt(var1);
      }
   }

   public boolean getClipToPadding() {
      return this.mClipToPadding;
   }

   public RecyclerViewAccessibilityDelegate getCompatAccessibilityDelegate() {
      return this.mAccessibilityDelegate;
   }

   public void getDecoratedBoundsWithMargins(View var1, Rect var2) {
      getDecoratedBoundsWithMarginsInt(var1, var2);
   }

   public EdgeEffectFactory getEdgeEffectFactory() {
      return this.mEdgeEffectFactory;
   }

   public ItemAnimator getItemAnimator() {
      return this.mItemAnimator;
   }

   Rect getItemDecorInsetsForChild(View var1) {
      LayoutParams var5 = (LayoutParams)var1.getLayoutParams();
      if (!var5.mInsetsDirty) {
         return var5.mDecorInsets;
      } else if (this.mState.isPreLayout() && (var5.isItemChanged() || var5.isViewInvalid())) {
         return var5.mDecorInsets;
      } else {
         Rect var4 = var5.mDecorInsets;
         var4.set(0, 0, 0, 0);
         int var3 = this.mItemDecorations.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            this.mTempRect.set(0, 0, 0, 0);
            ((ItemDecoration)this.mItemDecorations.get(var2)).getItemOffsets(this.mTempRect, var1, this, this.mState);
            var4.left += this.mTempRect.left;
            var4.top += this.mTempRect.top;
            var4.right += this.mTempRect.right;
            var4.bottom += this.mTempRect.bottom;
         }

         var5.mInsetsDirty = false;
         return var4;
      }
   }

   public ItemDecoration getItemDecorationAt(int var1) {
      int var2 = this.getItemDecorationCount();
      if (var1 >= 0 && var1 < var2) {
         return (ItemDecoration)this.mItemDecorations.get(var1);
      } else {
         throw new IndexOutOfBoundsException(var1 + " is an invalid index for size " + var2);
      }
   }

   public int getItemDecorationCount() {
      return this.mItemDecorations.size();
   }

   public LayoutManager getLayoutManager() {
      return this.mLayout;
   }

   public int getMaxFlingVelocity() {
      return this.mMaxFlingVelocity;
   }

   public int getMinFlingVelocity() {
      return this.mMinFlingVelocity;
   }

   long getNanoTime() {
      return ALLOW_THREAD_GAP_WORK ? System.nanoTime() : 0L;
   }

   public OnFlingListener getOnFlingListener() {
      return this.mOnFlingListener;
   }

   public boolean getPreserveFocusAfterLayout() {
      return this.mPreserveFocusAfterLayout;
   }

   public RecycledViewPool getRecycledViewPool() {
      return this.mRecycler.getRecycledViewPool();
   }

   public int getScrollState() {
      return this.mScrollState;
   }

   public boolean hasFixedSize() {
      return this.mHasFixedSize;
   }

   public boolean hasNestedScrollingParent() {
      return this.getScrollingChildHelper().hasNestedScrollingParent();
   }

   public boolean hasNestedScrollingParent(int var1) {
      return this.getScrollingChildHelper().hasNestedScrollingParent(var1);
   }

   public boolean hasPendingAdapterUpdates() {
      boolean var1;
      if (this.mFirstLayoutComplete && !this.mDataSetHasChangedAfterLayout && !this.mAdapterHelper.hasPendingUpdates()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   void initAdapterManager() {
      this.mAdapterHelper = new AdapterHelper(new AdapterHelper.Callback(this) {
         final RecyclerView this$0;

         {
            this.this$0 = var1;
         }

         void dispatchUpdate(AdapterHelper.UpdateOp var1) {
            int var2 = var1.cmd;
            if (var2 != 1) {
               if (var2 != 2) {
                  if (var2 != 4) {
                     if (var2 == 8) {
                        this.this$0.mLayout.onItemsMoved(this.this$0, var1.positionStart, var1.itemCount, 1);
                     }
                  } else {
                     this.this$0.mLayout.onItemsUpdated(this.this$0, var1.positionStart, var1.itemCount, var1.payload);
                  }
               } else {
                  this.this$0.mLayout.onItemsRemoved(this.this$0, var1.positionStart, var1.itemCount);
               }
            } else {
               this.this$0.mLayout.onItemsAdded(this.this$0, var1.positionStart, var1.itemCount);
            }

         }

         public ViewHolder findViewHolder(int var1) {
            ViewHolder var2 = this.this$0.findViewHolderForPosition(var1, true);
            if (var2 == null) {
               return null;
            } else {
               return this.this$0.mChildHelper.isHidden(var2.itemView) ? null : var2;
            }
         }

         public void markViewHoldersUpdated(int var1, int var2, Object var3) {
            this.this$0.viewRangeUpdate(var1, var2, var3);
            this.this$0.mItemsChanged = true;
         }

         public void offsetPositionsForAdd(int var1, int var2) {
            this.this$0.offsetPositionRecordsForInsert(var1, var2);
            this.this$0.mItemsAddedOrRemoved = true;
         }

         public void offsetPositionsForMove(int var1, int var2) {
            this.this$0.offsetPositionRecordsForMove(var1, var2);
            this.this$0.mItemsAddedOrRemoved = true;
         }

         public void offsetPositionsForRemovingInvisible(int var1, int var2) {
            this.this$0.offsetPositionRecordsForRemove(var1, var2, true);
            this.this$0.mItemsAddedOrRemoved = true;
            State var3 = this.this$0.mState;
            var3.mDeletedInvisibleItemCountSincePreviousLayout += var2;
         }

         public void offsetPositionsForRemovingLaidOutOrNewView(int var1, int var2) {
            this.this$0.offsetPositionRecordsForRemove(var1, var2, false);
            this.this$0.mItemsAddedOrRemoved = true;
         }

         public void onDispatchFirstPass(AdapterHelper.UpdateOp var1) {
            this.dispatchUpdate(var1);
         }

         public void onDispatchSecondPass(AdapterHelper.UpdateOp var1) {
            this.dispatchUpdate(var1);
         }
      });
   }

   void initFastScroller(StateListDrawable var1, Drawable var2, StateListDrawable var3, Drawable var4) {
      if (var1 != null && var2 != null && var3 != null && var4 != null) {
         Resources var5 = this.getContext().getResources();
         new FastScroller(this, var1, var2, var3, var4, var5.getDimensionPixelSize(R.dimen.fastscroll_default_thickness), var5.getDimensionPixelSize(R.dimen.fastscroll_minimum_range), var5.getDimensionPixelOffset(R.dimen.fastscroll_margin));
      } else {
         throw new IllegalArgumentException("Trying to set fast scroller without both required drawables." + this.exceptionLabel());
      }
   }

   void invalidateGlows() {
      this.mBottomGlow = null;
      this.mTopGlow = null;
      this.mRightGlow = null;
      this.mLeftGlow = null;
   }

   public void invalidateItemDecorations() {
      if (this.mItemDecorations.size() != 0) {
         LayoutManager var1 = this.mLayout;
         if (var1 != null) {
            var1.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
         }

         this.markItemDecorInsetsDirty();
         this.requestLayout();
      }
   }

   boolean isAccessibilityEnabled() {
      AccessibilityManager var2 = this.mAccessibilityManager;
      boolean var1;
      if (var2 != null && var2.isEnabled()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isAnimating() {
      ItemAnimator var2 = this.mItemAnimator;
      boolean var1;
      if (var2 != null && var2.isRunning()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isAttachedToWindow() {
      return this.mIsAttached;
   }

   public boolean isComputingLayout() {
      boolean var1;
      if (this.mLayoutOrScrollCounter > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   @Deprecated
   public boolean isLayoutFrozen() {
      return this.isLayoutSuppressed();
   }

   public final boolean isLayoutSuppressed() {
      return this.mLayoutSuppressed;
   }

   public boolean isNestedScrollingEnabled() {
      return this.getScrollingChildHelper().isNestedScrollingEnabled();
   }

   void jumpToPositionForSmoothScroller(int var1) {
      if (this.mLayout != null) {
         this.setScrollState(2);
         this.mLayout.scrollToPosition(var1);
         this.awakenScrollBars();
      }
   }

   void markItemDecorInsetsDirty() {
      int var2 = this.mChildHelper.getUnfilteredChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         ((LayoutParams)this.mChildHelper.getUnfilteredChildAt(var1).getLayoutParams()).mInsetsDirty = true;
      }

      this.mRecycler.markItemDecorInsetsDirty();
   }

   void markKnownViewsInvalid() {
      int var2 = this.mChildHelper.getUnfilteredChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         ViewHolder var3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var1));
         if (var3 != null && !var3.shouldIgnore()) {
            var3.addFlags(6);
         }
      }

      this.markItemDecorInsetsDirty();
      this.mRecycler.markKnownViewsInvalid();
   }

   public void nestedScrollBy(int var1, int var2) {
      this.nestedScrollByInternal(var1, var2, (MotionEvent)null, 1);
   }

   public void offsetChildrenHorizontal(int var1) {
      int var3 = this.mChildHelper.getChildCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         this.mChildHelper.getChildAt(var2).offsetLeftAndRight(var1);
      }

   }

   public void offsetChildrenVertical(int var1) {
      int var3 = this.mChildHelper.getChildCount();

      for(int var2 = 0; var2 < var3; ++var2) {
         this.mChildHelper.getChildAt(var2).offsetTopAndBottom(var1);
      }

   }

   void offsetPositionRecordsForInsert(int var1, int var2) {
      int var4 = this.mChildHelper.getUnfilteredChildCount();

      for(int var3 = 0; var3 < var4; ++var3) {
         ViewHolder var5 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var3));
         if (var5 != null && !var5.shouldIgnore() && var5.mPosition >= var1) {
            var5.offsetPosition(var2, false);
            this.mState.mStructureChanged = true;
         }
      }

      this.mRecycler.offsetPositionRecordsForInsert(var1, var2);
      this.requestLayout();
   }

   void offsetPositionRecordsForMove(int var1, int var2) {
      int var7 = this.mChildHelper.getUnfilteredChildCount();
      int var3;
      int var4;
      byte var5;
      if (var1 < var2) {
         var5 = -1;
         var3 = var1;
         var4 = var2;
      } else {
         var4 = var1;
         var3 = var2;
         var5 = 1;
      }

      for(int var6 = 0; var6 < var7; ++var6) {
         ViewHolder var8 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var6));
         if (var8 != null && var8.mPosition >= var3 && var8.mPosition <= var4) {
            if (var8.mPosition == var1) {
               var8.offsetPosition(var2 - var1, false);
            } else {
               var8.offsetPosition(var5, false);
            }

            this.mState.mStructureChanged = true;
         }
      }

      this.mRecycler.offsetPositionRecordsForMove(var1, var2);
      this.requestLayout();
   }

   void offsetPositionRecordsForRemove(int var1, int var2, boolean var3) {
      int var5 = this.mChildHelper.getUnfilteredChildCount();

      for(int var4 = 0; var4 < var5; ++var4) {
         ViewHolder var6 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var4));
         if (var6 != null && !var6.shouldIgnore()) {
            if (var6.mPosition >= var1 + var2) {
               var6.offsetPosition(-var2, var3);
               this.mState.mStructureChanged = true;
            } else if (var6.mPosition >= var1) {
               var6.flagRemovedAndOffsetPosition(var1 - 1, -var2, var3);
               this.mState.mStructureChanged = true;
            }
         }
      }

      this.mRecycler.offsetPositionRecordsForRemove(var1, var2, var3);
      this.requestLayout();
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mLayoutOrScrollCounter = 0;
      boolean var4 = true;
      this.mIsAttached = true;
      if (!this.mFirstLayoutComplete || this.isLayoutRequested()) {
         var4 = false;
      }

      this.mFirstLayoutComplete = var4;
      LayoutManager var5 = this.mLayout;
      if (var5 != null) {
         var5.dispatchAttachedToWindow(this);
      }

      this.mPostedAnimatorRunner = false;
      if (ALLOW_THREAD_GAP_WORK) {
         GapWorker var6 = (GapWorker)GapWorker.sGapWorker.get();
         this.mGapWorker = var6;
         if (var6 == null) {
            this.mGapWorker = new GapWorker();
            Display var7 = ViewCompat.getDisplay(this);
            float var2 = 60.0F;
            float var1 = var2;
            if (!this.isInEditMode()) {
               var1 = var2;
               if (var7 != null) {
                  float var3 = var7.getRefreshRate();
                  var1 = var2;
                  if (var3 >= 30.0F) {
                     var1 = var3;
                  }
               }
            }

            this.mGapWorker.mFrameIntervalNs = (long)(1.0E9F / var1);
            GapWorker.sGapWorker.set(this.mGapWorker);
         }

         this.mGapWorker.add(this);
      }

   }

   public void onChildAttachedToWindow(View var1) {
   }

   public void onChildDetachedFromWindow(View var1) {
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      ItemAnimator var1 = this.mItemAnimator;
      if (var1 != null) {
         var1.endAnimations();
      }

      this.stopScroll();
      this.mIsAttached = false;
      LayoutManager var2 = this.mLayout;
      if (var2 != null) {
         var2.dispatchDetachedFromWindow(this, this.mRecycler);
      }

      this.mPendingAccessibilityImportanceChange.clear();
      this.removeCallbacks(this.mItemAnimatorRunner);
      this.mViewInfoStore.onDetach();
      if (ALLOW_THREAD_GAP_WORK) {
         GapWorker var3 = this.mGapWorker;
         if (var3 != null) {
            var3.remove(this);
            this.mGapWorker = null;
         }
      }

   }

   public void onDraw(Canvas var1) {
      super.onDraw(var1);
      int var3 = this.mItemDecorations.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ((ItemDecoration)this.mItemDecorations.get(var2)).onDraw(var1, this, this.mState);
      }

   }

   void onEnterLayoutOrScroll() {
      ++this.mLayoutOrScrollCounter;
   }

   void onExitLayoutOrScroll() {
      this.onExitLayoutOrScroll(true);
   }

   void onExitLayoutOrScroll(boolean var1) {
      int var2 = this.mLayoutOrScrollCounter - 1;
      this.mLayoutOrScrollCounter = var2;
      if (var2 < 1) {
         this.mLayoutOrScrollCounter = 0;
         if (var1) {
            this.dispatchContentChangedIfNecessary();
            this.dispatchPendingImportantForAccessibilityChanges();
         }
      }

   }

   public boolean onGenericMotionEvent(MotionEvent var1) {
      if (this.mLayout == null) {
         return false;
      } else if (this.mLayoutSuppressed) {
         return false;
      } else {
         if (var1.getAction() == 8) {
            float var2;
            float var3;
            label43: {
               label42: {
                  if ((var1.getSource() & 2) != 0) {
                     if (this.mLayout.canScrollVertically()) {
                        var2 = -var1.getAxisValue(9);
                     } else {
                        var2 = 0.0F;
                     }

                     var3 = var2;
                     if (this.mLayout.canScrollHorizontally()) {
                        var3 = var1.getAxisValue(10);
                        break label43;
                     }
                  } else {
                     if ((var1.getSource() & 4194304) == 0) {
                        break label42;
                     }

                     var3 = var1.getAxisValue(26);
                     if (!this.mLayout.canScrollVertically()) {
                        if (this.mLayout.canScrollHorizontally()) {
                           var2 = 0.0F;
                           break label43;
                        }
                        break label42;
                     }

                     var3 = -var3;
                  }

                  float var4 = 0.0F;
                  var2 = var3;
                  var3 = var4;
                  break label43;
               }

               var2 = 0.0F;
               var3 = 0.0F;
            }

            if (var2 != 0.0F || var3 != 0.0F) {
               this.nestedScrollByInternal((int)(var3 * this.mScaledHorizontalScrollFactor), (int)(var2 * this.mScaledVerticalScrollFactor), var1, 1);
            }
         }

         return false;
      }
   }

   public boolean onInterceptTouchEvent(MotionEvent var1) {
      boolean var8 = this.mLayoutSuppressed;
      boolean var7 = false;
      if (var8) {
         return false;
      } else {
         this.mInterceptingOnItemTouchListener = null;
         if (this.findInterceptingOnItemTouchListener(var1)) {
            this.cancelScroll();
            return true;
         } else {
            LayoutManager var9 = this.mLayout;
            if (var9 == null) {
               return false;
            } else {
               int var3 = var9.canScrollHorizontally();
               var8 = this.mLayout.canScrollVertically();
               if (this.mVelocityTracker == null) {
                  this.mVelocityTracker = VelocityTracker.obtain();
               }

               this.mVelocityTracker.addMovement(var1);
               int var4 = var1.getActionMasked();
               int var2 = var1.getActionIndex();
               if (var4 != 0) {
                  if (var4 != 1) {
                     if (var4 != 2) {
                        if (var4 != 3) {
                           if (var4 != 5) {
                              if (var4 == 6) {
                                 this.onPointerUp(var1);
                              }
                           } else {
                              this.mScrollPointerId = var1.getPointerId(var2);
                              var3 = (int)(var1.getX(var2) + 0.5F);
                              this.mLastTouchX = var3;
                              this.mInitialTouchX = var3;
                              var2 = (int)(var1.getY(var2) + 0.5F);
                              this.mLastTouchY = var2;
                              this.mInitialTouchY = var2;
                           }
                        } else {
                           this.cancelScroll();
                        }
                     } else {
                        var4 = var1.findPointerIndex(this.mScrollPointerId);
                        if (var4 < 0) {
                           Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                           return false;
                        }

                        var2 = (int)(var1.getX(var4) + 0.5F);
                        int var5 = (int)(var1.getY(var4) + 0.5F);
                        if (this.mScrollState != 1) {
                           int var6 = this.mInitialTouchX;
                           var4 = this.mInitialTouchY;
                           boolean var11;
                           if (var3 != 0 && Math.abs(var2 - var6) > this.mTouchSlop) {
                              this.mLastTouchX = var2;
                              var11 = true;
                           } else {
                              var11 = false;
                           }

                           boolean var12 = var11;
                           if (var8) {
                              var12 = var11;
                              if (Math.abs(var5 - var4) > this.mTouchSlop) {
                                 this.mLastTouchY = var5;
                                 var12 = true;
                              }
                           }

                           if (var12) {
                              this.setScrollState(1);
                           }
                        }
                     }
                  } else {
                     this.mVelocityTracker.clear();
                     this.stopNestedScroll(0);
                  }
               } else {
                  if (this.mIgnoreMotionEventTillDown) {
                     this.mIgnoreMotionEventTillDown = false;
                  }

                  this.mScrollPointerId = var1.getPointerId(0);
                  var2 = (int)(var1.getX() + 0.5F);
                  this.mLastTouchX = var2;
                  this.mInitialTouchX = var2;
                  var2 = (int)(var1.getY() + 0.5F);
                  this.mLastTouchY = var2;
                  this.mInitialTouchY = var2;
                  if (this.mScrollState == 2) {
                     this.getParent().requestDisallowInterceptTouchEvent(true);
                     this.setScrollState(1);
                     this.stopNestedScroll(1);
                  }

                  int[] var10 = this.mNestedOffsets;
                  var10[1] = 0;
                  var10[0] = 0;
                  var2 = var3;
                  if (var8) {
                     var2 = var3 | 2;
                  }

                  this.startNestedScroll(var2, 0);
               }

               if (this.mScrollState == 1) {
                  var7 = true;
               }

               return var7;
            }
         }
      }
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      TraceCompat.beginSection("RV OnLayout");
      this.dispatchLayout();
      TraceCompat.endSection();
      this.mFirstLayoutComplete = true;
   }

   protected void onMeasure(int var1, int var2) {
      LayoutManager var7 = this.mLayout;
      if (var7 == null) {
         this.defaultOnMeasure(var1, var2);
      } else {
         boolean var5 = var7.isAutoMeasureEnabled();
         boolean var6 = false;
         if (var5) {
            int var4 = MeasureSpec.getMode(var1);
            int var3 = MeasureSpec.getMode(var2);
            this.mLayout.onMeasure(this.mRecycler, this.mState, var1, var2);
            var5 = var6;
            if (var4 == 1073741824) {
               var5 = var6;
               if (var3 == 1073741824) {
                  var5 = true;
               }
            }

            this.mLastAutoMeasureSkippedDueToExact = var5;
            if (var5 || this.mAdapter == null) {
               return;
            }

            if (this.mState.mLayoutStep == 1) {
               this.dispatchLayoutStep1();
            }

            this.mLayout.setMeasureSpecs(var1, var2);
            this.mState.mIsMeasuring = true;
            this.dispatchLayoutStep2();
            this.mLayout.setMeasuredDimensionFromChildren(var1, var2);
            if (this.mLayout.shouldMeasureTwice()) {
               this.mLayout.setMeasureSpecs(MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824), MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 1073741824));
               this.mState.mIsMeasuring = true;
               this.dispatchLayoutStep2();
               this.mLayout.setMeasuredDimensionFromChildren(var1, var2);
            }

            this.mLastAutoMeasureNonExactMeasuredWidth = this.getMeasuredWidth();
            this.mLastAutoMeasureNonExactMeasuredHeight = this.getMeasuredHeight();
         } else {
            if (this.mHasFixedSize) {
               this.mLayout.onMeasure(this.mRecycler, this.mState, var1, var2);
               return;
            }

            if (this.mAdapterUpdateDuringMeasure) {
               this.startInterceptRequestLayout();
               this.onEnterLayoutOrScroll();
               this.processAdapterUpdatesAndSetAnimationFlags();
               this.onExitLayoutOrScroll();
               if (this.mState.mRunPredictiveAnimations) {
                  this.mState.mInPreLayout = true;
               } else {
                  this.mAdapterHelper.consumeUpdatesInOnePass();
                  this.mState.mInPreLayout = false;
               }

               this.mAdapterUpdateDuringMeasure = false;
               this.stopInterceptRequestLayout(false);
            } else if (this.mState.mRunPredictiveAnimations) {
               this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredHeight());
               return;
            }

            Adapter var8 = this.mAdapter;
            if (var8 != null) {
               this.mState.mItemCount = var8.getItemCount();
            } else {
               this.mState.mItemCount = 0;
            }

            this.startInterceptRequestLayout();
            this.mLayout.onMeasure(this.mRecycler, this.mState, var1, var2);
            this.stopInterceptRequestLayout(false);
            this.mState.mInPreLayout = false;
         }

      }
   }

   protected boolean onRequestFocusInDescendants(int var1, Rect var2) {
      return this.isComputingLayout() ? false : super.onRequestFocusInDescendants(var1, var2);
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         this.mPendingSavedState = var2;
         super.onRestoreInstanceState(var2.getSuperState());
         this.requestLayout();
      }
   }

   protected Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      SavedState var2 = this.mPendingSavedState;
      if (var2 != null) {
         var1.copyFrom(var2);
      } else {
         LayoutManager var3 = this.mLayout;
         if (var3 != null) {
            var1.mLayoutState = var3.onSaveInstanceState();
         } else {
            var1.mLayoutState = null;
         }
      }

      return var1;
   }

   public void onScrollStateChanged(int var1) {
   }

   public void onScrolled(int var1, int var2) {
   }

   protected void onSizeChanged(int var1, int var2, int var3, int var4) {
      super.onSizeChanged(var1, var2, var3, var4);
      if (var1 != var3 || var2 != var4) {
         this.invalidateGlows();
      }

   }

   public boolean onTouchEvent(MotionEvent var1) {
      boolean var14 = this.mLayoutSuppressed;
      boolean var10 = false;
      if (!var14 && !this.mIgnoreMotionEventTillDown) {
         if (this.dispatchToOnItemTouchListeners(var1)) {
            this.cancelScroll();
            return true;
         } else {
            LayoutManager var15 = this.mLayout;
            if (var15 == null) {
               return false;
            } else {
               byte var11 = var15.canScrollHorizontally();
               var14 = this.mLayout.canScrollVertically();
               if (this.mVelocityTracker == null) {
                  this.mVelocityTracker = VelocityTracker.obtain();
               }

               int var5 = var1.getActionMasked();
               int var4 = var1.getActionIndex();
               if (var5 == 0) {
                  int[] var22 = this.mNestedOffsets;
                  var22[1] = 0;
                  var22[0] = 0;
               }

               MotionEvent var23 = MotionEvent.obtain(var1);
               int[] var16 = this.mNestedOffsets;
               var23.offsetLocation((float)var16[0], (float)var16[1]);
               boolean var8;
               if (var5 != 0) {
                  if (var5 != 1) {
                     if (var5 != 2) {
                        if (var5 != 3) {
                           if (var5 != 5) {
                              if (var5 != 6) {
                                 var8 = var10;
                              } else {
                                 this.onPointerUp(var1);
                                 var8 = var10;
                              }
                           } else {
                              this.mScrollPointerId = var1.getPointerId(var4);
                              var5 = (int)(var1.getX(var4) + 0.5F);
                              this.mLastTouchX = var5;
                              this.mInitialTouchX = var5;
                              var4 = (int)(var1.getY(var4) + 0.5F);
                              this.mLastTouchY = var4;
                              this.mInitialTouchY = var4;
                              var8 = var10;
                           }
                        } else {
                           this.cancelScroll();
                           var8 = var10;
                        }
                     } else {
                        var4 = var1.findPointerIndex(this.mScrollPointerId);
                        if (var4 < 0) {
                           Log.e("RecyclerView", "Error processing scroll; pointer index for id " + this.mScrollPointerId + " not found. Did any MotionEvents get skipped?");
                           return false;
                        }

                        int var12 = (int)(var1.getX(var4) + 0.5F);
                        int var13 = (int)(var1.getY(var4) + 0.5F);
                        var4 = this.mLastTouchX - var12;
                        int var20 = this.mLastTouchY - var13;
                        var5 = var4;
                        int var7 = var20;
                        int var6;
                        if (this.mScrollState != 1) {
                           boolean var19;
                           label135: {
                              var6 = var4;
                              if (var11 != 0) {
                                 if (var4 > 0) {
                                    var4 = Math.max(0, var4 - this.mTouchSlop);
                                 } else {
                                    var4 = Math.min(0, var4 + this.mTouchSlop);
                                 }

                                 var6 = var4;
                                 if (var4 != 0) {
                                    var19 = true;
                                    break label135;
                                 }
                              }

                              var19 = false;
                              var4 = var6;
                           }

                           var6 = var20;
                           boolean var9 = var19;
                           if (var14) {
                              if (var20 > 0) {
                                 var7 = Math.max(0, var20 - this.mTouchSlop);
                              } else {
                                 var7 = Math.min(0, var20 + this.mTouchSlop);
                              }

                              var6 = var7;
                              var9 = var19;
                              if (var7 != 0) {
                                 var9 = true;
                                 var6 = var7;
                              }
                           }

                           var5 = var4;
                           var7 = var6;
                           if (var9) {
                              this.setScrollState(1);
                              var7 = var6;
                              var5 = var4;
                           }
                        }

                        var6 = var5;
                        var8 = var10;
                        if (this.mScrollState == 1) {
                           var16 = this.mReusableIntPair;
                           var16[0] = 0;
                           var16[1] = 0;
                           if (var11 != 0) {
                              var20 = var5;
                           } else {
                              var20 = 0;
                           }

                           int var21;
                           if (var14) {
                              var21 = var7;
                           } else {
                              var21 = 0;
                           }

                           var5 = var5;
                           var4 = var7;
                           if (this.dispatchNestedPreScroll(var20, var21, var16, this.mScrollOffset, 0)) {
                              var16 = this.mReusableIntPair;
                              var5 = var6 - var16[0];
                              var4 = var7 - var16[1];
                              int[] var17 = this.mNestedOffsets;
                              var6 = var17[0];
                              var16 = this.mScrollOffset;
                              var17[0] = var6 + var16[0];
                              var17[1] += var16[1];
                              this.getParent().requestDisallowInterceptTouchEvent(true);
                           }

                           var16 = this.mScrollOffset;
                           this.mLastTouchX = var12 - var16[0];
                           this.mLastTouchY = var13 - var16[1];
                           if (var11 != 0) {
                              var6 = var5;
                           } else {
                              var6 = 0;
                           }

                           if (var14) {
                              var7 = var4;
                           } else {
                              var7 = 0;
                           }

                           if (this.scrollByInternal(var6, var7, var1, 0)) {
                              this.getParent().requestDisallowInterceptTouchEvent(true);
                           }

                           GapWorker var18 = this.mGapWorker;
                           var8 = var10;
                           if (var18 != null) {
                              label122: {
                                 if (var5 == 0) {
                                    var8 = var10;
                                    if (var4 == 0) {
                                       break label122;
                                    }
                                 }

                                 var18.postFromTraversal(this, var5, var4);
                                 var8 = var10;
                              }
                           }
                        }
                     }
                  } else {
                     this.mVelocityTracker.addMovement(var23);
                     this.mVelocityTracker.computeCurrentVelocity(1000, (float)this.mMaxFlingVelocity);
                     float var2;
                     if (var11 != 0) {
                        var2 = -this.mVelocityTracker.getXVelocity(this.mScrollPointerId);
                     } else {
                        var2 = 0.0F;
                     }

                     float var3;
                     if (var14) {
                        var3 = -this.mVelocityTracker.getYVelocity(this.mScrollPointerId);
                     } else {
                        var3 = 0.0F;
                     }

                     if (var2 == 0.0F && var3 == 0.0F || !this.fling((int)var2, (int)var3)) {
                        this.setScrollState(0);
                     }

                     this.resetScroll();
                     var8 = true;
                  }
               } else {
                  this.mScrollPointerId = var1.getPointerId(0);
                  var4 = (int)(var1.getX() + 0.5F);
                  this.mLastTouchX = var4;
                  this.mInitialTouchX = var4;
                  var4 = (int)(var1.getY() + 0.5F);
                  this.mLastTouchY = var4;
                  this.mInitialTouchY = var4;
                  var4 = var11;
                  if (var14) {
                     var4 = var11 | 2;
                  }

                  this.startNestedScroll(var4, 0);
                  var8 = var10;
               }

               if (!var8) {
                  this.mVelocityTracker.addMovement(var23);
               }

               var23.recycle();
               return true;
            }
         }
      } else {
         return false;
      }
   }

   void postAnimationRunner() {
      if (!this.mPostedAnimatorRunner && this.mIsAttached) {
         ViewCompat.postOnAnimation(this, this.mItemAnimatorRunner);
         this.mPostedAnimatorRunner = true;
      }

   }

   void processDataSetCompletelyChanged(boolean var1) {
      this.mDispatchItemsChangedEvent |= var1;
      this.mDataSetHasChangedAfterLayout = true;
      this.markKnownViewsInvalid();
   }

   void recordAnimationInfoIfBouncedHiddenView(ViewHolder var1, ItemAnimator.ItemHolderInfo var2) {
      var1.setFlags(0, 8192);
      if (this.mState.mTrackOldChangeHolders && var1.isUpdated() && !var1.isRemoved() && !var1.shouldIgnore()) {
         long var3 = this.getChangedHolderKey(var1);
         this.mViewInfoStore.addToOldChangeHolders(var3, var1);
      }

      this.mViewInfoStore.addToPreLayout(var1, var2);
   }

   void removeAndRecycleViews() {
      ItemAnimator var1 = this.mItemAnimator;
      if (var1 != null) {
         var1.endAnimations();
      }

      LayoutManager var2 = this.mLayout;
      if (var2 != null) {
         var2.removeAndRecycleAllViews(this.mRecycler);
         this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
      }

      this.mRecycler.clear();
   }

   boolean removeAnimatingView(View var1) {
      this.startInterceptRequestLayout();
      boolean var2 = this.mChildHelper.removeViewIfHidden(var1);
      if (var2) {
         ViewHolder var3 = getChildViewHolderInt(var1);
         this.mRecycler.unscrapView(var3);
         this.mRecycler.recycleViewHolderInternal(var3);
      }

      this.stopInterceptRequestLayout(var2 ^ true);
      return var2;
   }

   protected void removeDetachedView(View var1, boolean var2) {
      ViewHolder var3 = getChildViewHolderInt(var1);
      if (var3 != null) {
         if (var3.isTmpDetached()) {
            var3.clearTmpDetachFlag();
         } else if (!var3.shouldIgnore()) {
            throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + var3 + this.exceptionLabel());
         }
      }

      var1.clearAnimation();
      this.dispatchChildDetached(var1);
      super.removeDetachedView(var1, var2);
   }

   public void removeItemDecoration(ItemDecoration var1) {
      LayoutManager var3 = this.mLayout;
      if (var3 != null) {
         var3.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
      }

      this.mItemDecorations.remove(var1);
      if (this.mItemDecorations.isEmpty()) {
         boolean var2;
         if (this.getOverScrollMode() == 2) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.setWillNotDraw(var2);
      }

      this.markItemDecorInsetsDirty();
      this.requestLayout();
   }

   public void removeItemDecorationAt(int var1) {
      int var2 = this.getItemDecorationCount();
      if (var1 >= 0 && var1 < var2) {
         this.removeItemDecoration(this.getItemDecorationAt(var1));
      } else {
         throw new IndexOutOfBoundsException(var1 + " is an invalid index for size " + var2);
      }
   }

   public void removeOnChildAttachStateChangeListener(OnChildAttachStateChangeListener var1) {
      List var2 = this.mOnChildAttachStateListeners;
      if (var2 != null) {
         var2.remove(var1);
      }
   }

   public void removeOnItemTouchListener(OnItemTouchListener var1) {
      this.mOnItemTouchListeners.remove(var1);
      if (this.mInterceptingOnItemTouchListener == var1) {
         this.mInterceptingOnItemTouchListener = null;
      }

   }

   public void removeOnScrollListener(OnScrollListener var1) {
      List var2 = this.mScrollListeners;
      if (var2 != null) {
         var2.remove(var1);
      }

   }

   public void removeRecyclerListener(RecyclerListener var1) {
      this.mRecyclerListeners.remove(var1);
   }

   void repositionShadowingViews() {
      int var2 = this.mChildHelper.getChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         View var5 = this.mChildHelper.getChildAt(var1);
         ViewHolder var6 = this.getChildViewHolder(var5);
         if (var6 != null && var6.mShadowingHolder != null) {
            View var7 = var6.mShadowingHolder.itemView;
            int var3 = var5.getLeft();
            int var4 = var5.getTop();
            if (var3 != var7.getLeft() || var4 != var7.getTop()) {
               var7.layout(var3, var4, var7.getWidth() + var3, var7.getHeight() + var4);
            }
         }
      }

   }

   public void requestChildFocus(View var1, View var2) {
      if (!this.mLayout.onRequestChildFocus(this, this.mState, var1, var2) && var2 != null) {
         this.requestChildOnScreen(var1, var2);
      }

      super.requestChildFocus(var1, var2);
   }

   public boolean requestChildRectangleOnScreen(View var1, Rect var2, boolean var3) {
      return this.mLayout.requestChildRectangleOnScreen(this, var1, var2, var3);
   }

   public void requestDisallowInterceptTouchEvent(boolean var1) {
      int var3 = this.mOnItemTouchListeners.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         ((OnItemTouchListener)this.mOnItemTouchListeners.get(var2)).onRequestDisallowInterceptTouchEvent(var1);
      }

      super.requestDisallowInterceptTouchEvent(var1);
   }

   public void requestLayout() {
      if (this.mInterceptRequestLayoutDepth == 0 && !this.mLayoutSuppressed) {
         super.requestLayout();
      } else {
         this.mLayoutWasDefered = true;
      }

   }

   void saveOldPositions() {
      int var2 = this.mChildHelper.getUnfilteredChildCount();

      for(int var1 = 0; var1 < var2; ++var1) {
         ViewHolder var3 = getChildViewHolderInt(this.mChildHelper.getUnfilteredChildAt(var1));
         if (!var3.shouldIgnore()) {
            var3.saveOldPosition();
         }
      }

   }

   public void scrollBy(int var1, int var2) {
      LayoutManager var5 = this.mLayout;
      if (var5 == null) {
         Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      } else if (!this.mLayoutSuppressed) {
         boolean var4 = var5.canScrollHorizontally();
         boolean var3 = this.mLayout.canScrollVertically();
         if (var4 || var3) {
            if (!var4) {
               var1 = 0;
            }

            if (!var3) {
               var2 = 0;
            }

            this.scrollByInternal(var1, var2, (MotionEvent)null, 0);
         }

      }
   }

   boolean scrollByInternal(int var1, int var2, MotionEvent var3, int var4) {
      this.consumePendingUpdateOperations();
      Adapter var16 = this.mAdapter;
      boolean var15 = true;
      int var5;
      int var6;
      int var7;
      int var8;
      int var9;
      int[] var18;
      if (var16 != null) {
         var18 = this.mReusableIntPair;
         var18[0] = 0;
         var18[1] = 0;
         this.scrollStep(var1, var2, var18);
         var18 = this.mReusableIntPair;
         var8 = var18[0];
         var6 = var18[1];
         var5 = var6;
         var7 = var8;
         var8 = var1 - var8;
         var6 = var2 - var6;
      } else {
         var9 = 0;
         var7 = 0;
         var6 = var7;
         var8 = var7;
         var5 = var9;
      }

      if (!this.mItemDecorations.isEmpty()) {
         this.invalidate();
      }

      var18 = this.mReusableIntPair;
      var18[0] = 0;
      var18[1] = 0;
      this.dispatchNestedScroll(var7, var5, var8, var6, this.mScrollOffset, var4, var18);
      var18 = this.mReusableIntPair;
      var9 = var18[0];
      int var10 = var18[1];
      boolean var17;
      if (var9 == 0 && var10 == 0) {
         var17 = false;
      } else {
         var17 = true;
      }

      int var12 = this.mLastTouchX;
      var18 = this.mScrollOffset;
      int var11 = var18[0];
      this.mLastTouchX = var12 - var11;
      var12 = this.mLastTouchY;
      int var13 = var18[1];
      this.mLastTouchY = var12 - var13;
      var18 = this.mNestedOffsets;
      var18[0] += var11;
      var18[1] += var13;
      if (this.getOverScrollMode() != 2) {
         if (var3 != null && !MotionEventCompat.isFromSource(var3, 8194)) {
            this.pullGlows(var3.getX(), (float)(var8 - var9), var3.getY(), (float)(var6 - var10));
         }

         this.considerReleasingGlowsOnScroll(var1, var2);
      }

      if (var7 != 0 || var5 != 0) {
         this.dispatchOnScrolled(var7, var5);
      }

      if (!this.awakenScrollBars()) {
         this.invalidate();
      }

      boolean var14 = var15;
      if (!var17) {
         var14 = var15;
         if (var7 == 0) {
            if (var5 != 0) {
               var14 = var15;
            } else {
               var14 = false;
            }
         }
      }

      return var14;
   }

   void scrollStep(int var1, int var2, int[] var3) {
      this.startInterceptRequestLayout();
      this.onEnterLayoutOrScroll();
      TraceCompat.beginSection("RV Scroll");
      this.fillRemainingScrollValues(this.mState);
      if (var1 != 0) {
         var1 = this.mLayout.scrollHorizontallyBy(var1, this.mRecycler, this.mState);
      } else {
         var1 = 0;
      }

      if (var2 != 0) {
         var2 = this.mLayout.scrollVerticallyBy(var2, this.mRecycler, this.mState);
      } else {
         var2 = 0;
      }

      TraceCompat.endSection();
      this.repositionShadowingViews();
      this.onExitLayoutOrScroll();
      this.stopInterceptRequestLayout(false);
      if (var3 != null) {
         var3[0] = var1;
         var3[1] = var2;
      }

   }

   public void scrollTo(int var1, int var2) {
      Log.w("RecyclerView", "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
   }

   public void scrollToPosition(int var1) {
      if (!this.mLayoutSuppressed) {
         this.stopScroll();
         LayoutManager var2 = this.mLayout;
         if (var2 == null) {
            Log.e("RecyclerView", "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
         } else {
            var2.scrollToPosition(var1);
            this.awakenScrollBars();
         }
      }
   }

   public void sendAccessibilityEventUnchecked(AccessibilityEvent var1) {
      if (!this.shouldDeferAccessibilityEvent(var1)) {
         super.sendAccessibilityEventUnchecked(var1);
      }
   }

   public void setAccessibilityDelegateCompat(RecyclerViewAccessibilityDelegate var1) {
      this.mAccessibilityDelegate = var1;
      ViewCompat.setAccessibilityDelegate(this, var1);
   }

   public void setAdapter(Adapter var1) {
      this.setLayoutFrozen(false);
      this.setAdapterInternal(var1, false, true);
      this.processDataSetCompletelyChanged(false);
      this.requestLayout();
   }

   public void setChildDrawingOrderCallback(ChildDrawingOrderCallback var1) {
      if (var1 != this.mChildDrawingOrderCallback) {
         this.mChildDrawingOrderCallback = var1;
         boolean var2;
         if (var1 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         this.setChildrenDrawingOrderEnabled(var2);
      }
   }

   boolean setChildImportantForAccessibilityInternal(ViewHolder var1, int var2) {
      if (this.isComputingLayout()) {
         var1.mPendingAccessibilityState = var2;
         this.mPendingAccessibilityImportanceChange.add(var1);
         return false;
      } else {
         ViewCompat.setImportantForAccessibility(var1.itemView, var2);
         return true;
      }
   }

   public void setClipToPadding(boolean var1) {
      if (var1 != this.mClipToPadding) {
         this.invalidateGlows();
      }

      this.mClipToPadding = var1;
      super.setClipToPadding(var1);
      if (this.mFirstLayoutComplete) {
         this.requestLayout();
      }

   }

   public void setEdgeEffectFactory(EdgeEffectFactory var1) {
      Preconditions.checkNotNull(var1);
      this.mEdgeEffectFactory = var1;
      this.invalidateGlows();
   }

   public void setHasFixedSize(boolean var1) {
      this.mHasFixedSize = var1;
   }

   public void setItemAnimator(ItemAnimator var1) {
      ItemAnimator var2 = this.mItemAnimator;
      if (var2 != null) {
         var2.endAnimations();
         this.mItemAnimator.setListener((ItemAnimator.ItemAnimatorListener)null);
      }

      this.mItemAnimator = var1;
      if (var1 != null) {
         var1.setListener(this.mItemAnimatorListener);
      }

   }

   public void setItemViewCacheSize(int var1) {
      this.mRecycler.setViewCacheSize(var1);
   }

   @Deprecated
   public void setLayoutFrozen(boolean var1) {
      this.suppressLayout(var1);
   }

   public void setLayoutManager(LayoutManager var1) {
      if (var1 != this.mLayout) {
         this.stopScroll();
         if (this.mLayout != null) {
            ItemAnimator var2 = this.mItemAnimator;
            if (var2 != null) {
               var2.endAnimations();
            }

            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            this.mRecycler.clear();
            if (this.mIsAttached) {
               this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
            }

            this.mLayout.setRecyclerView((RecyclerView)null);
            this.mLayout = null;
         } else {
            this.mRecycler.clear();
         }

         this.mChildHelper.removeAllViewsUnfiltered();
         this.mLayout = var1;
         if (var1 != null) {
            if (var1.mRecyclerView != null) {
               throw new IllegalArgumentException("LayoutManager " + var1 + " is already attached to a RecyclerView:" + var1.mRecyclerView.exceptionLabel());
            }

            this.mLayout.setRecyclerView(this);
            if (this.mIsAttached) {
               this.mLayout.dispatchAttachedToWindow(this);
            }
         }

         this.mRecycler.updateViewCacheSize();
         this.requestLayout();
      }
   }

   @Deprecated
   public void setLayoutTransition(LayoutTransition var1) {
      if (VERSION.SDK_INT < 18) {
         if (var1 == null) {
            this.suppressLayout(false);
            return;
         }

         if (var1.getAnimator(0) == null && var1.getAnimator(1) == null && var1.getAnimator(2) == null && var1.getAnimator(3) == null && var1.getAnimator(4) == null) {
            this.suppressLayout(true);
            return;
         }
      }

      if (var1 == null) {
         super.setLayoutTransition((LayoutTransition)null);
      } else {
         throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
      }
   }

   public void setNestedScrollingEnabled(boolean var1) {
      this.getScrollingChildHelper().setNestedScrollingEnabled(var1);
   }

   public void setOnFlingListener(OnFlingListener var1) {
      this.mOnFlingListener = var1;
   }

   @Deprecated
   public void setOnScrollListener(OnScrollListener var1) {
      this.mScrollListener = var1;
   }

   public void setPreserveFocusAfterLayout(boolean var1) {
      this.mPreserveFocusAfterLayout = var1;
   }

   public void setRecycledViewPool(RecycledViewPool var1) {
      this.mRecycler.setRecycledViewPool(var1);
   }

   @Deprecated
   public void setRecyclerListener(RecyclerListener var1) {
      this.mRecyclerListener = var1;
   }

   void setScrollState(int var1) {
      if (var1 != this.mScrollState) {
         this.mScrollState = var1;
         if (var1 != 2) {
            this.stopScrollersInternal();
         }

         this.dispatchOnScrollStateChanged(var1);
      }
   }

   public void setScrollingTouchSlop(int var1) {
      ViewConfiguration var2 = ViewConfiguration.get(this.getContext());
      if (var1 != 0) {
         if (var1 == 1) {
            this.mTouchSlop = var2.getScaledPagingTouchSlop();
            return;
         }

         Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + var1 + "; using default value");
      }

      this.mTouchSlop = var2.getScaledTouchSlop();
   }

   public void setViewCacheExtension(ViewCacheExtension var1) {
      this.mRecycler.setViewCacheExtension(var1);
   }

   boolean shouldDeferAccessibilityEvent(AccessibilityEvent var1) {
      boolean var4 = this.isComputingLayout();
      byte var3 = 0;
      if (var4) {
         int var2;
         if (var1 != null) {
            var2 = AccessibilityEventCompat.getContentChangeTypes(var1);
         } else {
            var2 = 0;
         }

         if (var2 == 0) {
            var2 = var3;
         }

         this.mEatenAccessibilityChangeFlags |= var2;
         return true;
      } else {
         return false;
      }
   }

   public void smoothScrollBy(int var1, int var2) {
      this.smoothScrollBy(var1, var2, (Interpolator)null);
   }

   public void smoothScrollBy(int var1, int var2, Interpolator var3) {
      this.smoothScrollBy(var1, var2, var3, Integer.MIN_VALUE);
   }

   public void smoothScrollBy(int var1, int var2, Interpolator var3, int var4) {
      this.smoothScrollBy(var1, var2, var3, var4, false);
   }

   void smoothScrollBy(int var1, int var2, Interpolator var3, int var4, boolean var5) {
      LayoutManager var9 = this.mLayout;
      if (var9 == null) {
         Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
      } else if (!this.mLayoutSuppressed) {
         boolean var8 = var9.canScrollHorizontally();
         int var7 = 0;
         int var6 = var1;
         if (!var8) {
            var6 = 0;
         }

         if (!this.mLayout.canScrollVertically()) {
            var2 = 0;
         }

         if (var6 != 0 || var2 != 0) {
            boolean var10;
            if (var4 != Integer.MIN_VALUE && var4 <= 0) {
               var10 = false;
            } else {
               var10 = true;
            }

            if (var10) {
               if (var5) {
                  byte var11 = (byte)var7;
                  if (var6 != 0) {
                     var11 = 1;
                  }

                  var7 = var11;
                  if (var2 != 0) {
                     var7 = var11 | 2;
                  }

                  this.startNestedScroll(var7, 1);
               }

               this.mViewFlinger.smoothScrollBy(var6, var2, var4, var3);
            } else {
               this.scrollBy(var6, var2);
            }
         }

      }
   }

   public void smoothScrollToPosition(int var1) {
      if (!this.mLayoutSuppressed) {
         LayoutManager var2 = this.mLayout;
         if (var2 == null) {
            Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
         } else {
            var2.smoothScrollToPosition(this, this.mState, var1);
         }
      }
   }

   void startInterceptRequestLayout() {
      int var1 = this.mInterceptRequestLayoutDepth + 1;
      this.mInterceptRequestLayoutDepth = var1;
      if (var1 == 1 && !this.mLayoutSuppressed) {
         this.mLayoutWasDefered = false;
      }

   }

   public boolean startNestedScroll(int var1) {
      return this.getScrollingChildHelper().startNestedScroll(var1);
   }

   public boolean startNestedScroll(int var1, int var2) {
      return this.getScrollingChildHelper().startNestedScroll(var1, var2);
   }

   void stopInterceptRequestLayout(boolean var1) {
      if (this.mInterceptRequestLayoutDepth < 1) {
         this.mInterceptRequestLayoutDepth = 1;
      }

      if (!var1 && !this.mLayoutSuppressed) {
         this.mLayoutWasDefered = false;
      }

      if (this.mInterceptRequestLayoutDepth == 1) {
         if (var1 && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
            this.dispatchLayout();
         }

         if (!this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
         }
      }

      --this.mInterceptRequestLayoutDepth;
   }

   public void stopNestedScroll() {
      this.getScrollingChildHelper().stopNestedScroll();
   }

   public void stopNestedScroll(int var1) {
      this.getScrollingChildHelper().stopNestedScroll(var1);
   }

   public void stopScroll() {
      this.setScrollState(0);
      this.stopScrollersInternal();
   }

   public final void suppressLayout(boolean var1) {
      if (var1 != this.mLayoutSuppressed) {
         this.assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
         if (!var1) {
            this.mLayoutSuppressed = false;
            if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
               this.requestLayout();
            }

            this.mLayoutWasDefered = false;
         } else {
            long var2 = SystemClock.uptimeMillis();
            this.onTouchEvent(MotionEvent.obtain(var2, var2, 3, 0.0F, 0.0F, 0));
            this.mLayoutSuppressed = true;
            this.mIgnoreMotionEventTillDown = true;
            this.stopScroll();
         }
      }

   }

   public void swapAdapter(Adapter var1, boolean var2) {
      this.setLayoutFrozen(false);
      this.setAdapterInternal(var1, true, var2);
      this.processDataSetCompletelyChanged(true);
      this.requestLayout();
   }

   void viewRangeUpdate(int var1, int var2, Object var3) {
      int var5 = this.mChildHelper.getUnfilteredChildCount();

      for(int var4 = 0; var4 < var5; ++var4) {
         View var7 = this.mChildHelper.getUnfilteredChildAt(var4);
         ViewHolder var6 = getChildViewHolderInt(var7);
         if (var6 != null && !var6.shouldIgnore() && var6.mPosition >= var1 && var6.mPosition < var1 + var2) {
            var6.addFlags(2);
            var6.addChangePayload(var3);
            ((LayoutParams)var7.getLayoutParams()).mInsetsDirty = true;
         }
      }

      this.mRecycler.viewRangeUpdate(var1, var2);
   }

   public abstract static class Adapter {
      private boolean mHasStableIds = false;
      private final AdapterDataObservable mObservable = new AdapterDataObservable();
      private StateRestorationPolicy mStateRestorationPolicy;

      public Adapter() {
         this.mStateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW;
      }

      public final void bindViewHolder(ViewHolder var1, int var2) {
         boolean var3;
         if (var1.mBindingAdapter == null) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (var3) {
            var1.mPosition = var2;
            if (this.hasStableIds()) {
               var1.mItemId = this.getItemId(var2);
            }

            var1.setFlags(1, 519);
            TraceCompat.beginSection("RV OnBindView");
         }

         var1.mBindingAdapter = this;
         this.onBindViewHolder(var1, var2, var1.getUnmodifiedPayloads());
         if (var3) {
            var1.clearPayload();
            ViewGroup.LayoutParams var4 = var1.itemView.getLayoutParams();
            if (var4 instanceof LayoutParams) {
               ((LayoutParams)var4).mInsetsDirty = true;
            }

            TraceCompat.endSection();
         }

      }

      boolean canRestoreState() {
         int var1 = null.$SwitchMap$androidx$recyclerview$widget$RecyclerView$Adapter$StateRestorationPolicy[this.mStateRestorationPolicy.ordinal()];
         boolean var3 = false;
         boolean var2 = var3;
         if (var1 != 1) {
            if (var1 != 2) {
               return true;
            }

            var2 = var3;
            if (this.getItemCount() > 0) {
               var2 = true;
            }
         }

         return var2;
      }

      public final ViewHolder createViewHolder(ViewGroup var1, int var2) {
         ViewHolder var5;
         try {
            TraceCompat.beginSection("RV CreateView");
            var5 = this.onCreateViewHolder(var1, var2);
            if (var5.itemView.getParent() != null) {
               IllegalStateException var6 = new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
               throw var6;
            }

            var5.mItemViewType = var2;
         } finally {
            TraceCompat.endSection();
         }

         return var5;
      }

      public int findRelativeAdapterPositionIn(Adapter var1, ViewHolder var2, int var3) {
         return var1 == this ? var3 : -1;
      }

      public abstract int getItemCount();

      public long getItemId(int var1) {
         return -1L;
      }

      public int getItemViewType(int var1) {
         return 0;
      }

      public final StateRestorationPolicy getStateRestorationPolicy() {
         return this.mStateRestorationPolicy;
      }

      public final boolean hasObservers() {
         return this.mObservable.hasObservers();
      }

      public final boolean hasStableIds() {
         return this.mHasStableIds;
      }

      public final void notifyDataSetChanged() {
         this.mObservable.notifyChanged();
      }

      public final void notifyItemChanged(int var1) {
         this.mObservable.notifyItemRangeChanged(var1, 1);
      }

      public final void notifyItemChanged(int var1, Object var2) {
         this.mObservable.notifyItemRangeChanged(var1, 1, var2);
      }

      public final void notifyItemInserted(int var1) {
         this.mObservable.notifyItemRangeInserted(var1, 1);
      }

      public final void notifyItemMoved(int var1, int var2) {
         this.mObservable.notifyItemMoved(var1, var2);
      }

      public final void notifyItemRangeChanged(int var1, int var2) {
         this.mObservable.notifyItemRangeChanged(var1, var2);
      }

      public final void notifyItemRangeChanged(int var1, int var2, Object var3) {
         this.mObservable.notifyItemRangeChanged(var1, var2, var3);
      }

      public final void notifyItemRangeInserted(int var1, int var2) {
         this.mObservable.notifyItemRangeInserted(var1, var2);
      }

      public final void notifyItemRangeRemoved(int var1, int var2) {
         this.mObservable.notifyItemRangeRemoved(var1, var2);
      }

      public final void notifyItemRemoved(int var1) {
         this.mObservable.notifyItemRangeRemoved(var1, 1);
      }

      public void onAttachedToRecyclerView(RecyclerView var1) {
      }

      public abstract void onBindViewHolder(ViewHolder var1, int var2);

      public void onBindViewHolder(ViewHolder var1, int var2, List var3) {
         this.onBindViewHolder(var1, var2);
      }

      public abstract ViewHolder onCreateViewHolder(ViewGroup var1, int var2);

      public void onDetachedFromRecyclerView(RecyclerView var1) {
      }

      public boolean onFailedToRecycleView(ViewHolder var1) {
         return false;
      }

      public void onViewAttachedToWindow(ViewHolder var1) {
      }

      public void onViewDetachedFromWindow(ViewHolder var1) {
      }

      public void onViewRecycled(ViewHolder var1) {
      }

      public void registerAdapterDataObserver(AdapterDataObserver var1) {
         this.mObservable.registerObserver(var1);
      }

      public void setHasStableIds(boolean var1) {
         if (!this.hasObservers()) {
            this.mHasStableIds = var1;
         } else {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
         }
      }

      public void setStateRestorationPolicy(StateRestorationPolicy var1) {
         this.mStateRestorationPolicy = var1;
         this.mObservable.notifyStateRestorationPolicyChanged();
      }

      public void unregisterAdapterDataObserver(AdapterDataObserver var1) {
         this.mObservable.unregisterObserver(var1);
      }

      public static enum StateRestorationPolicy {
         private static final StateRestorationPolicy[] $VALUES;
         ALLOW,
         PREVENT,
         PREVENT_WHEN_EMPTY;

         static {
            StateRestorationPolicy var0 = new StateRestorationPolicy("ALLOW", 0);
            ALLOW = var0;
            StateRestorationPolicy var2 = new StateRestorationPolicy("PREVENT_WHEN_EMPTY", 1);
            PREVENT_WHEN_EMPTY = var2;
            StateRestorationPolicy var1 = new StateRestorationPolicy("PREVENT", 2);
            PREVENT = var1;
            $VALUES = new StateRestorationPolicy[]{var0, var2, var1};
         }
      }
   }

   static class AdapterDataObservable extends Observable {
      public boolean hasObservers() {
         return this.mObservers.isEmpty() ^ true;
      }

      public void notifyChanged() {
         for(int var1 = this.mObservers.size() - 1; var1 >= 0; --var1) {
            ((AdapterDataObserver)this.mObservers.get(var1)).onChanged();
         }

      }

      public void notifyItemMoved(int var1, int var2) {
         for(int var3 = this.mObservers.size() - 1; var3 >= 0; --var3) {
            ((AdapterDataObserver)this.mObservers.get(var3)).onItemRangeMoved(var1, var2, 1);
         }

      }

      public void notifyItemRangeChanged(int var1, int var2) {
         this.notifyItemRangeChanged(var1, var2, (Object)null);
      }

      public void notifyItemRangeChanged(int var1, int var2, Object var3) {
         for(int var4 = this.mObservers.size() - 1; var4 >= 0; --var4) {
            ((AdapterDataObserver)this.mObservers.get(var4)).onItemRangeChanged(var1, var2, var3);
         }

      }

      public void notifyItemRangeInserted(int var1, int var2) {
         for(int var3 = this.mObservers.size() - 1; var3 >= 0; --var3) {
            ((AdapterDataObserver)this.mObservers.get(var3)).onItemRangeInserted(var1, var2);
         }

      }

      public void notifyItemRangeRemoved(int var1, int var2) {
         for(int var3 = this.mObservers.size() - 1; var3 >= 0; --var3) {
            ((AdapterDataObserver)this.mObservers.get(var3)).onItemRangeRemoved(var1, var2);
         }

      }

      public void notifyStateRestorationPolicyChanged() {
         for(int var1 = this.mObservers.size() - 1; var1 >= 0; --var1) {
            ((AdapterDataObserver)this.mObservers.get(var1)).onStateRestorationPolicyChanged();
         }

      }
   }

   public abstract static class AdapterDataObserver {
      public void onChanged() {
      }

      public void onItemRangeChanged(int var1, int var2) {
      }

      public void onItemRangeChanged(int var1, int var2, Object var3) {
         this.onItemRangeChanged(var1, var2);
      }

      public void onItemRangeInserted(int var1, int var2) {
      }

      public void onItemRangeMoved(int var1, int var2, int var3) {
      }

      public void onItemRangeRemoved(int var1, int var2) {
      }

      public void onStateRestorationPolicyChanged() {
      }
   }

   public interface ChildDrawingOrderCallback {
      int onGetChildDrawingOrder(int var1, int var2);
   }

   public static class EdgeEffectFactory {
      public static final int DIRECTION_BOTTOM = 3;
      public static final int DIRECTION_LEFT = 0;
      public static final int DIRECTION_RIGHT = 2;
      public static final int DIRECTION_TOP = 1;

      protected EdgeEffect createEdgeEffect(RecyclerView var1, int var2) {
         return new EdgeEffect(var1.getContext());
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface EdgeDirection {
      }
   }

   public abstract static class ItemAnimator {
      public static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
      public static final int FLAG_CHANGED = 2;
      public static final int FLAG_INVALIDATED = 4;
      public static final int FLAG_MOVED = 2048;
      public static final int FLAG_REMOVED = 8;
      private long mAddDuration = 120L;
      private long mChangeDuration = 250L;
      private ArrayList mFinishedListeners = new ArrayList();
      private ItemAnimatorListener mListener = null;
      private long mMoveDuration = 250L;
      private long mRemoveDuration = 120L;

      static int buildAdapterChangeFlagsForAnimations(ViewHolder var0) {
         int var2 = var0.mFlags & 14;
         if (var0.isInvalid()) {
            return 4;
         } else {
            int var1 = var2;
            if ((var2 & 4) == 0) {
               int var3 = var0.getOldPosition();
               int var4 = var0.getAbsoluteAdapterPosition();
               var1 = var2;
               if (var3 != -1) {
                  var1 = var2;
                  if (var4 != -1) {
                     var1 = var2;
                     if (var3 != var4) {
                        var1 = var2 | 2048;
                     }
                  }
               }
            }

            return var1;
         }
      }

      public abstract boolean animateAppearance(ViewHolder var1, ItemHolderInfo var2, ItemHolderInfo var3);

      public abstract boolean animateChange(ViewHolder var1, ViewHolder var2, ItemHolderInfo var3, ItemHolderInfo var4);

      public abstract boolean animateDisappearance(ViewHolder var1, ItemHolderInfo var2, ItemHolderInfo var3);

      public abstract boolean animatePersistence(ViewHolder var1, ItemHolderInfo var2, ItemHolderInfo var3);

      public boolean canReuseUpdatedViewHolder(ViewHolder var1) {
         return true;
      }

      public boolean canReuseUpdatedViewHolder(ViewHolder var1, List var2) {
         return this.canReuseUpdatedViewHolder(var1);
      }

      public final void dispatchAnimationFinished(ViewHolder var1) {
         this.onAnimationFinished(var1);
         ItemAnimatorListener var2 = this.mListener;
         if (var2 != null) {
            var2.onAnimationFinished(var1);
         }

      }

      public final void dispatchAnimationStarted(ViewHolder var1) {
         this.onAnimationStarted(var1);
      }

      public final void dispatchAnimationsFinished() {
         int var2 = this.mFinishedListeners.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            ((ItemAnimatorFinishedListener)this.mFinishedListeners.get(var1)).onAnimationsFinished();
         }

         this.mFinishedListeners.clear();
      }

      public abstract void endAnimation(ViewHolder var1);

      public abstract void endAnimations();

      public long getAddDuration() {
         return this.mAddDuration;
      }

      public long getChangeDuration() {
         return this.mChangeDuration;
      }

      public long getMoveDuration() {
         return this.mMoveDuration;
      }

      public long getRemoveDuration() {
         return this.mRemoveDuration;
      }

      public abstract boolean isRunning();

      public final boolean isRunning(ItemAnimatorFinishedListener var1) {
         boolean var2 = this.isRunning();
         if (var1 != null) {
            if (!var2) {
               var1.onAnimationsFinished();
            } else {
               this.mFinishedListeners.add(var1);
            }
         }

         return var2;
      }

      public ItemHolderInfo obtainHolderInfo() {
         return new ItemHolderInfo();
      }

      public void onAnimationFinished(ViewHolder var1) {
      }

      public void onAnimationStarted(ViewHolder var1) {
      }

      public ItemHolderInfo recordPostLayoutInformation(State var1, ViewHolder var2) {
         return this.obtainHolderInfo().setFrom(var2);
      }

      public ItemHolderInfo recordPreLayoutInformation(State var1, ViewHolder var2, int var3, List var4) {
         return this.obtainHolderInfo().setFrom(var2);
      }

      public abstract void runPendingAnimations();

      public void setAddDuration(long var1) {
         this.mAddDuration = var1;
      }

      public void setChangeDuration(long var1) {
         this.mChangeDuration = var1;
      }

      void setListener(ItemAnimatorListener var1) {
         this.mListener = var1;
      }

      public void setMoveDuration(long var1) {
         this.mMoveDuration = var1;
      }

      public void setRemoveDuration(long var1) {
         this.mRemoveDuration = var1;
      }

      @Retention(RetentionPolicy.SOURCE)
      public @interface AdapterChanges {
      }

      public interface ItemAnimatorFinishedListener {
         void onAnimationsFinished();
      }

      interface ItemAnimatorListener {
         void onAnimationFinished(ViewHolder var1);
      }

      public static class ItemHolderInfo {
         public int bottom;
         public int changeFlags;
         public int left;
         public int right;
         public int top;

         public ItemHolderInfo setFrom(ViewHolder var1) {
            return this.setFrom(var1, 0);
         }

         public ItemHolderInfo setFrom(ViewHolder var1, int var2) {
            View var3 = var1.itemView;
            this.left = var3.getLeft();
            this.top = var3.getTop();
            this.right = var3.getRight();
            this.bottom = var3.getBottom();
            return this;
         }
      }
   }

   private class ItemAnimatorRestoreListener implements ItemAnimator.ItemAnimatorListener {
      final RecyclerView this$0;

      ItemAnimatorRestoreListener(RecyclerView var1) {
         this.this$0 = var1;
      }

      public void onAnimationFinished(ViewHolder var1) {
         var1.setIsRecyclable(true);
         if (var1.mShadowedHolder != null && var1.mShadowingHolder == null) {
            var1.mShadowedHolder = null;
         }

         var1.mShadowingHolder = null;
         if (!var1.shouldBeKeptAsChild() && !this.this$0.removeAnimatingView(var1.itemView) && var1.isTmpDetached()) {
            this.this$0.removeDetachedView(var1.itemView, false);
         }

      }
   }

   public abstract static class ItemDecoration {
      @Deprecated
      public void getItemOffsets(Rect var1, int var2, RecyclerView var3) {
         var1.set(0, 0, 0, 0);
      }

      public void getItemOffsets(Rect var1, View var2, RecyclerView var3, State var4) {
         this.getItemOffsets(var1, ((LayoutParams)var2.getLayoutParams()).getViewLayoutPosition(), var3);
      }

      @Deprecated
      public void onDraw(Canvas var1, RecyclerView var2) {
      }

      public void onDraw(Canvas var1, RecyclerView var2, State var3) {
         this.onDraw(var1, var2);
      }

      @Deprecated
      public void onDrawOver(Canvas var1, RecyclerView var2) {
      }

      public void onDrawOver(Canvas var1, RecyclerView var2, State var3) {
         this.onDrawOver(var1, var2);
      }
   }

   public abstract static class LayoutManager {
      boolean mAutoMeasure;
      ChildHelper mChildHelper;
      private int mHeight;
      private int mHeightMode;
      ViewBoundsCheck mHorizontalBoundCheck;
      private final ViewBoundsCheck.Callback mHorizontalBoundCheckCallback;
      boolean mIsAttachedToWindow;
      private boolean mItemPrefetchEnabled;
      private boolean mMeasurementCacheEnabled;
      int mPrefetchMaxCountObserved;
      boolean mPrefetchMaxObservedInInitialPrefetch;
      RecyclerView mRecyclerView;
      boolean mRequestedSimpleAnimations;
      SmoothScroller mSmoothScroller;
      ViewBoundsCheck mVerticalBoundCheck;
      private final ViewBoundsCheck.Callback mVerticalBoundCheckCallback;
      private int mWidth;
      private int mWidthMode;

      public LayoutManager() {
         ViewBoundsCheck.Callback var2 = new ViewBoundsCheck.Callback(this) {
            final LayoutManager this$0;

            {
               this.this$0 = var1;
            }

            public View getChildAt(int var1) {
               return this.this$0.getChildAt(var1);
            }

            public int getChildEnd(View var1) {
               LayoutParams var2 = (LayoutParams)var1.getLayoutParams();
               return this.this$0.getDecoratedRight(var1) + var2.rightMargin;
            }

            public int getChildStart(View var1) {
               LayoutParams var2 = (LayoutParams)var1.getLayoutParams();
               return this.this$0.getDecoratedLeft(var1) - var2.leftMargin;
            }

            public int getParentEnd() {
               return this.this$0.getWidth() - this.this$0.getPaddingRight();
            }

            public int getParentStart() {
               return this.this$0.getPaddingLeft();
            }
         };
         this.mHorizontalBoundCheckCallback = var2;
         ViewBoundsCheck.Callback var1 = new ViewBoundsCheck.Callback(this) {
            final LayoutManager this$0;

            {
               this.this$0 = var1;
            }

            public View getChildAt(int var1) {
               return this.this$0.getChildAt(var1);
            }

            public int getChildEnd(View var1) {
               LayoutParams var2 = (LayoutParams)var1.getLayoutParams();
               return this.this$0.getDecoratedBottom(var1) + var2.bottomMargin;
            }

            public int getChildStart(View var1) {
               LayoutParams var2 = (LayoutParams)var1.getLayoutParams();
               return this.this$0.getDecoratedTop(var1) - var2.topMargin;
            }

            public int getParentEnd() {
               return this.this$0.getHeight() - this.this$0.getPaddingBottom();
            }

            public int getParentStart() {
               return this.this$0.getPaddingTop();
            }
         };
         this.mVerticalBoundCheckCallback = var1;
         this.mHorizontalBoundCheck = new ViewBoundsCheck(var2);
         this.mVerticalBoundCheck = new ViewBoundsCheck(var1);
         this.mRequestedSimpleAnimations = false;
         this.mIsAttachedToWindow = false;
         this.mAutoMeasure = false;
         this.mMeasurementCacheEnabled = true;
         this.mItemPrefetchEnabled = true;
      }

      private void addViewInt(View var1, int var2, boolean var3) {
         ViewHolder var7 = RecyclerView.getChildViewHolderInt(var1);
         if (!var3 && !var7.isRemoved()) {
            this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(var7);
         } else {
            this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(var7);
         }

         LayoutParams var6 = (LayoutParams)var1.getLayoutParams();
         if (!var7.wasReturnedFromScrap() && !var7.isScrap()) {
            if (var1.getParent() == this.mRecyclerView) {
               int var5 = this.mChildHelper.indexOfChild(var1);
               int var4 = var2;
               if (var2 == -1) {
                  var4 = this.mChildHelper.getChildCount();
               }

               if (var5 == -1) {
                  throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + this.mRecyclerView.indexOfChild(var1) + this.mRecyclerView.exceptionLabel());
               }

               if (var5 != var4) {
                  this.mRecyclerView.mLayout.moveView(var5, var4);
               }
            } else {
               this.mChildHelper.addView(var1, var2, false);
               var6.mInsetsDirty = true;
               SmoothScroller var8 = this.mSmoothScroller;
               if (var8 != null && var8.isRunning()) {
                  this.mSmoothScroller.onChildAttachedToWindow(var1);
               }
            }
         } else {
            if (var7.isScrap()) {
               var7.unScrap();
            } else {
               var7.clearReturnedFromScrapFlag();
            }

            this.mChildHelper.attachViewToParent(var1, var2, var1.getLayoutParams(), false);
         }

         if (var6.mPendingInvalidate) {
            var7.itemView.invalidate();
            var6.mPendingInvalidate = false;
         }

      }

      public static int chooseSize(int var0, int var1, int var2) {
         int var3 = MeasureSpec.getMode(var0);
         var0 = MeasureSpec.getSize(var0);
         if (var3 != Integer.MIN_VALUE) {
            if (var3 != 1073741824) {
               var0 = Math.max(var1, var2);
            }

            return var0;
         } else {
            return Math.min(var0, Math.max(var1, var2));
         }
      }

      private void detachViewInternal(int var1, View var2) {
         this.mChildHelper.detachViewFromParent(var1);
      }

      public static int getChildMeasureSpec(int var0, int var1, int var2, int var3, boolean var4) {
         label59: {
            label60: {
               var0 = Math.max(0, var0 - var2);
               if (var4) {
                  if (var3 >= 0) {
                     break label60;
                  }

                  if (var3 != -1 || var1 != Integer.MIN_VALUE && (var1 == 0 || var1 != 1073741824)) {
                     break label59;
                  }
               } else {
                  if (var3 >= 0) {
                     break label60;
                  }

                  if (var3 != -1) {
                     if (var3 == -2) {
                        if (var1 != Integer.MIN_VALUE && var1 != 1073741824) {
                           var1 = 0;
                           var3 = var0;
                        } else {
                           var1 = Integer.MIN_VALUE;
                           var3 = var0;
                        }

                        return MeasureSpec.makeMeasureSpec(var3, var1);
                     }
                     break label59;
                  }
               }

               var3 = var0;
               return MeasureSpec.makeMeasureSpec(var3, var1);
            }

            var1 = 1073741824;
            return MeasureSpec.makeMeasureSpec(var3, var1);
         }

         var1 = 0;
         var3 = 0;
         return MeasureSpec.makeMeasureSpec(var3, var1);
      }

      @Deprecated
      public static int getChildMeasureSpec(int var0, int var1, int var2, boolean var3) {
         byte var4;
         label33: {
            var4 = 0;
            var0 = Math.max(0, var0 - var1);
            if (var3) {
               if (var2 < 0) {
                  break label33;
               }
            } else if (var2 < 0) {
               if (var2 != -1) {
                  if (var2 == -2) {
                     var1 = Integer.MIN_VALUE;
                     var2 = var0;
                     var0 = var1;
                     return MeasureSpec.makeMeasureSpec(var2, var0);
                  }
                  break label33;
               }

               var2 = var0;
            }

            var0 = 1073741824;
            return MeasureSpec.makeMeasureSpec(var2, var0);
         }

         var2 = 0;
         var0 = var4;
         return MeasureSpec.makeMeasureSpec(var2, var0);
      }

      private int[] getChildRectangleOnScreenScrollAmount(View var1, Rect var2) {
         int var3 = this.getPaddingLeft();
         int var4 = this.getPaddingTop();
         int var14 = this.getWidth();
         int var13 = this.getPaddingRight();
         int var11 = this.getHeight();
         int var7 = this.getPaddingBottom();
         int var12 = var1.getLeft() + var2.left - var1.getScrollX();
         int var9 = var1.getTop() + var2.top - var1.getScrollY();
         int var5 = var2.width();
         int var8 = var2.height();
         int var10 = var12 - var3;
         var3 = Math.min(0, var10);
         int var6 = var9 - var4;
         var4 = Math.min(0, var6);
         var12 = var5 + var12 - (var14 - var13);
         var5 = Math.max(0, var12);
         var7 = Math.max(0, var8 + var9 - (var11 - var7));
         if (this.getLayoutDirection() == 1) {
            if (var5 != 0) {
               var3 = var5;
            } else {
               var3 = Math.max(var3, var12);
            }
         } else if (var3 == 0) {
            var3 = Math.min(var10, var5);
         }

         if (var4 == 0) {
            var4 = Math.min(var6, var7);
         }

         return new int[]{var3, var4};
      }

      public static Properties getProperties(Context var0, AttributeSet var1, int var2, int var3) {
         Properties var4 = new Properties();
         TypedArray var5 = var0.obtainStyledAttributes(var1, R.styleable.RecyclerView, var2, var3);
         var4.orientation = var5.getInt(R.styleable.RecyclerView_android_orientation, 1);
         var4.spanCount = var5.getInt(R.styleable.RecyclerView_spanCount, 1);
         var4.reverseLayout = var5.getBoolean(R.styleable.RecyclerView_reverseLayout, false);
         var4.stackFromEnd = var5.getBoolean(R.styleable.RecyclerView_stackFromEnd, false);
         var5.recycle();
         return var4;
      }

      private boolean isFocusedChildVisibleAfterScrolling(RecyclerView var1, int var2, int var3) {
         View var11 = var1.getFocusedChild();
         if (var11 == null) {
            return false;
         } else {
            int var6 = this.getPaddingLeft();
            int var7 = this.getPaddingTop();
            int var9 = this.getWidth();
            int var5 = this.getPaddingRight();
            int var4 = this.getHeight();
            int var8 = this.getPaddingBottom();
            Rect var10 = this.mRecyclerView.mTempRect;
            this.getDecoratedBoundsWithMargins(var11, var10);
            return var10.left - var2 < var9 - var5 && var10.right - var2 > var6 && var10.top - var3 < var4 - var8 && var10.bottom - var3 > var7;
         }
      }

      private static boolean isMeasurementUpToDate(int var0, int var1, int var2) {
         int var3 = MeasureSpec.getMode(var1);
         var1 = MeasureSpec.getSize(var1);
         boolean var4 = false;
         boolean var5 = false;
         if (var2 > 0 && var0 != var2) {
            return false;
         } else if (var3 != Integer.MIN_VALUE) {
            if (var3 != 0) {
               if (var3 != 1073741824) {
                  return false;
               } else {
                  var4 = var5;
                  if (var1 == var0) {
                     var4 = true;
                  }

                  return var4;
               }
            } else {
               return true;
            }
         } else {
            if (var1 >= var0) {
               var4 = true;
            }

            return var4;
         }
      }

      private void scrapOrRecycleView(Recycler var1, int var2, View var3) {
         ViewHolder var4 = RecyclerView.getChildViewHolderInt(var3);
         if (!var4.shouldIgnore()) {
            if (var4.isInvalid() && !var4.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
               this.removeViewAt(var2);
               var1.recycleViewHolderInternal(var4);
            } else {
               this.detachViewAt(var2);
               var1.scrapView(var3);
               this.mRecyclerView.mViewInfoStore.onViewDetached(var4);
            }

         }
      }

      public void addDisappearingView(View var1) {
         this.addDisappearingView(var1, -1);
      }

      public void addDisappearingView(View var1, int var2) {
         this.addViewInt(var1, var2, true);
      }

      public void addView(View var1) {
         this.addView(var1, -1);
      }

      public void addView(View var1, int var2) {
         this.addViewInt(var1, var2, false);
      }

      public void assertInLayoutOrScroll(String var1) {
         RecyclerView var2 = this.mRecyclerView;
         if (var2 != null) {
            var2.assertInLayoutOrScroll(var1);
         }

      }

      public void assertNotInLayoutOrScroll(String var1) {
         RecyclerView var2 = this.mRecyclerView;
         if (var2 != null) {
            var2.assertNotInLayoutOrScroll(var1);
         }

      }

      public void attachView(View var1) {
         this.attachView(var1, -1);
      }

      public void attachView(View var1, int var2) {
         this.attachView(var1, var2, (LayoutParams)var1.getLayoutParams());
      }

      public void attachView(View var1, int var2, LayoutParams var3) {
         ViewHolder var4 = RecyclerView.getChildViewHolderInt(var1);
         if (var4.isRemoved()) {
            this.mRecyclerView.mViewInfoStore.addToDisappearedInLayout(var4);
         } else {
            this.mRecyclerView.mViewInfoStore.removeFromDisappearedInLayout(var4);
         }

         this.mChildHelper.attachViewToParent(var1, var2, var3, var4.isRemoved());
      }

      public void calculateItemDecorationsForChild(View var1, Rect var2) {
         RecyclerView var3 = this.mRecyclerView;
         if (var3 == null) {
            var2.set(0, 0, 0, 0);
         } else {
            var2.set(var3.getItemDecorInsetsForChild(var1));
         }
      }

      public boolean canScrollHorizontally() {
         return false;
      }

      public boolean canScrollVertically() {
         return false;
      }

      public boolean checkLayoutParams(LayoutParams var1) {
         boolean var2;
         if (var1 != null) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      public void collectAdjacentPrefetchPositions(int var1, int var2, State var3, LayoutPrefetchRegistry var4) {
      }

      public void collectInitialPrefetchPositions(int var1, LayoutPrefetchRegistry var2) {
      }

      public int computeHorizontalScrollExtent(State var1) {
         return 0;
      }

      public int computeHorizontalScrollOffset(State var1) {
         return 0;
      }

      public int computeHorizontalScrollRange(State var1) {
         return 0;
      }

      public int computeVerticalScrollExtent(State var1) {
         return 0;
      }

      public int computeVerticalScrollOffset(State var1) {
         return 0;
      }

      public int computeVerticalScrollRange(State var1) {
         return 0;
      }

      public void detachAndScrapAttachedViews(Recycler var1) {
         for(int var2 = this.getChildCount() - 1; var2 >= 0; --var2) {
            this.scrapOrRecycleView(var1, var2, this.getChildAt(var2));
         }

      }

      public void detachAndScrapView(View var1, Recycler var2) {
         this.scrapOrRecycleView(var2, this.mChildHelper.indexOfChild(var1), var1);
      }

      public void detachAndScrapViewAt(int var1, Recycler var2) {
         this.scrapOrRecycleView(var2, var1, this.getChildAt(var1));
      }

      public void detachView(View var1) {
         int var2 = this.mChildHelper.indexOfChild(var1);
         if (var2 >= 0) {
            this.detachViewInternal(var2, var1);
         }

      }

      public void detachViewAt(int var1) {
         this.detachViewInternal(var1, this.getChildAt(var1));
      }

      void dispatchAttachedToWindow(RecyclerView var1) {
         this.mIsAttachedToWindow = true;
         this.onAttachedToWindow(var1);
      }

      void dispatchDetachedFromWindow(RecyclerView var1, Recycler var2) {
         this.mIsAttachedToWindow = false;
         this.onDetachedFromWindow(var1, var2);
      }

      public void endAnimation(View var1) {
         if (this.mRecyclerView.mItemAnimator != null) {
            this.mRecyclerView.mItemAnimator.endAnimation(RecyclerView.getChildViewHolderInt(var1));
         }

      }

      public View findContainingItemView(View var1) {
         RecyclerView var2 = this.mRecyclerView;
         if (var2 == null) {
            return null;
         } else {
            var1 = var2.findContainingItemView(var1);
            if (var1 == null) {
               return null;
            } else {
               return this.mChildHelper.isHidden(var1) ? null : var1;
            }
         }
      }

      public View findViewByPosition(int var1) {
         int var3 = this.getChildCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            View var4 = this.getChildAt(var2);
            ViewHolder var5 = RecyclerView.getChildViewHolderInt(var4);
            if (var5 != null && var5.getLayoutPosition() == var1 && !var5.shouldIgnore() && (this.mRecyclerView.mState.isPreLayout() || !var5.isRemoved())) {
               return var4;
            }
         }

         return null;
      }

      public abstract LayoutParams generateDefaultLayoutParams();

      public LayoutParams generateLayoutParams(Context var1, AttributeSet var2) {
         return new LayoutParams(var1, var2);
      }

      public LayoutParams generateLayoutParams(ViewGroup.LayoutParams var1) {
         if (var1 instanceof LayoutParams) {
            return new LayoutParams((LayoutParams)var1);
         } else {
            return var1 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams)var1) : new LayoutParams(var1);
         }
      }

      public int getBaseline() {
         return -1;
      }

      public int getBottomDecorationHeight(View var1) {
         return ((LayoutParams)var1.getLayoutParams()).mDecorInsets.bottom;
      }

      public View getChildAt(int var1) {
         ChildHelper var2 = this.mChildHelper;
         View var3;
         if (var2 != null) {
            var3 = var2.getChildAt(var1);
         } else {
            var3 = null;
         }

         return var3;
      }

      public int getChildCount() {
         ChildHelper var2 = this.mChildHelper;
         int var1;
         if (var2 != null) {
            var1 = var2.getChildCount();
         } else {
            var1 = 0;
         }

         return var1;
      }

      public boolean getClipToPadding() {
         RecyclerView var2 = this.mRecyclerView;
         boolean var1;
         if (var2 != null && var2.mClipToPadding) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public int getColumnCountForAccessibility(Recycler var1, State var2) {
         return -1;
      }

      public int getDecoratedBottom(View var1) {
         return var1.getBottom() + this.getBottomDecorationHeight(var1);
      }

      public void getDecoratedBoundsWithMargins(View var1, Rect var2) {
         RecyclerView.getDecoratedBoundsWithMarginsInt(var1, var2);
      }

      public int getDecoratedLeft(View var1) {
         return var1.getLeft() - this.getLeftDecorationWidth(var1);
      }

      public int getDecoratedMeasuredHeight(View var1) {
         Rect var2 = ((LayoutParams)var1.getLayoutParams()).mDecorInsets;
         return var1.getMeasuredHeight() + var2.top + var2.bottom;
      }

      public int getDecoratedMeasuredWidth(View var1) {
         Rect var2 = ((LayoutParams)var1.getLayoutParams()).mDecorInsets;
         return var1.getMeasuredWidth() + var2.left + var2.right;
      }

      public int getDecoratedRight(View var1) {
         return var1.getRight() + this.getRightDecorationWidth(var1);
      }

      public int getDecoratedTop(View var1) {
         return var1.getTop() - this.getTopDecorationHeight(var1);
      }

      public View getFocusedChild() {
         RecyclerView var1 = this.mRecyclerView;
         if (var1 == null) {
            return null;
         } else {
            View var2 = var1.getFocusedChild();
            return var2 != null && !this.mChildHelper.isHidden(var2) ? var2 : null;
         }
      }

      public int getHeight() {
         return this.mHeight;
      }

      public int getHeightMode() {
         return this.mHeightMode;
      }

      public int getItemCount() {
         RecyclerView var2 = this.mRecyclerView;
         Adapter var3;
         if (var2 != null) {
            var3 = var2.getAdapter();
         } else {
            var3 = null;
         }

         int var1;
         if (var3 != null) {
            var1 = var3.getItemCount();
         } else {
            var1 = 0;
         }

         return var1;
      }

      public int getItemViewType(View var1) {
         return RecyclerView.getChildViewHolderInt(var1).getItemViewType();
      }

      public int getLayoutDirection() {
         return ViewCompat.getLayoutDirection(this.mRecyclerView);
      }

      public int getLeftDecorationWidth(View var1) {
         return ((LayoutParams)var1.getLayoutParams()).mDecorInsets.left;
      }

      public int getMinimumHeight() {
         return ViewCompat.getMinimumHeight(this.mRecyclerView);
      }

      public int getMinimumWidth() {
         return ViewCompat.getMinimumWidth(this.mRecyclerView);
      }

      public int getPaddingBottom() {
         RecyclerView var2 = this.mRecyclerView;
         int var1;
         if (var2 != null) {
            var1 = var2.getPaddingBottom();
         } else {
            var1 = 0;
         }

         return var1;
      }

      public int getPaddingEnd() {
         RecyclerView var2 = this.mRecyclerView;
         int var1;
         if (var2 != null) {
            var1 = ViewCompat.getPaddingEnd(var2);
         } else {
            var1 = 0;
         }

         return var1;
      }

      public int getPaddingLeft() {
         RecyclerView var2 = this.mRecyclerView;
         int var1;
         if (var2 != null) {
            var1 = var2.getPaddingLeft();
         } else {
            var1 = 0;
         }

         return var1;
      }

      public int getPaddingRight() {
         RecyclerView var2 = this.mRecyclerView;
         int var1;
         if (var2 != null) {
            var1 = var2.getPaddingRight();
         } else {
            var1 = 0;
         }

         return var1;
      }

      public int getPaddingStart() {
         RecyclerView var2 = this.mRecyclerView;
         int var1;
         if (var2 != null) {
            var1 = ViewCompat.getPaddingStart(var2);
         } else {
            var1 = 0;
         }

         return var1;
      }

      public int getPaddingTop() {
         RecyclerView var2 = this.mRecyclerView;
         int var1;
         if (var2 != null) {
            var1 = var2.getPaddingTop();
         } else {
            var1 = 0;
         }

         return var1;
      }

      public int getPosition(View var1) {
         return ((LayoutParams)var1.getLayoutParams()).getViewLayoutPosition();
      }

      public int getRightDecorationWidth(View var1) {
         return ((LayoutParams)var1.getLayoutParams()).mDecorInsets.right;
      }

      public int getRowCountForAccessibility(Recycler var1, State var2) {
         return -1;
      }

      public int getSelectionModeForAccessibility(Recycler var1, State var2) {
         return 0;
      }

      public int getTopDecorationHeight(View var1) {
         return ((LayoutParams)var1.getLayoutParams()).mDecorInsets.top;
      }

      public void getTransformedBoundingBox(View var1, boolean var2, Rect var3) {
         if (var2) {
            Rect var4 = ((LayoutParams)var1.getLayoutParams()).mDecorInsets;
            var3.set(-var4.left, -var4.top, var1.getWidth() + var4.right, var1.getHeight() + var4.bottom);
         } else {
            var3.set(0, 0, var1.getWidth(), var1.getHeight());
         }

         if (this.mRecyclerView != null) {
            Matrix var5 = var1.getMatrix();
            if (var5 != null && !var5.isIdentity()) {
               RectF var6 = this.mRecyclerView.mTempRectF;
               var6.set(var3);
               var5.mapRect(var6);
               var3.set((int)Math.floor((double)var6.left), (int)Math.floor((double)var6.top), (int)Math.ceil((double)var6.right), (int)Math.ceil((double)var6.bottom));
            }
         }

         var3.offset(var1.getLeft(), var1.getTop());
      }

      public int getWidth() {
         return this.mWidth;
      }

      public int getWidthMode() {
         return this.mWidthMode;
      }

      boolean hasFlexibleChildInBothOrientations() {
         int var2 = this.getChildCount();

         for(int var1 = 0; var1 < var2; ++var1) {
            ViewGroup.LayoutParams var3 = this.getChildAt(var1).getLayoutParams();
            if (var3.width < 0 && var3.height < 0) {
               return true;
            }
         }

         return false;
      }

      public boolean hasFocus() {
         RecyclerView var2 = this.mRecyclerView;
         boolean var1;
         if (var2 != null && var2.hasFocus()) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public void ignoreView(View var1) {
         ViewParent var2 = var1.getParent();
         RecyclerView var3 = this.mRecyclerView;
         if (var2 == var3 && var3.indexOfChild(var1) != -1) {
            ViewHolder var4 = RecyclerView.getChildViewHolderInt(var1);
            var4.addFlags(128);
            this.mRecyclerView.mViewInfoStore.removeViewHolder(var4);
         } else {
            throw new IllegalArgumentException("View should be fully attached to be ignored" + this.mRecyclerView.exceptionLabel());
         }
      }

      public boolean isAttachedToWindow() {
         return this.mIsAttachedToWindow;
      }

      public boolean isAutoMeasureEnabled() {
         return this.mAutoMeasure;
      }

      public boolean isFocused() {
         RecyclerView var2 = this.mRecyclerView;
         boolean var1;
         if (var2 != null && var2.isFocused()) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public final boolean isItemPrefetchEnabled() {
         return this.mItemPrefetchEnabled;
      }

      public boolean isLayoutHierarchical(Recycler var1, State var2) {
         return false;
      }

      public boolean isMeasurementCacheEnabled() {
         return this.mMeasurementCacheEnabled;
      }

      public boolean isSmoothScrolling() {
         SmoothScroller var2 = this.mSmoothScroller;
         boolean var1;
         if (var2 != null && var2.isRunning()) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isViewPartiallyVisible(View var1, boolean var2, boolean var3) {
         if (this.mHorizontalBoundCheck.isViewWithinBoundFlags(var1, 24579) && this.mVerticalBoundCheck.isViewWithinBoundFlags(var1, 24579)) {
            var3 = true;
         } else {
            var3 = false;
         }

         return var2 ? var3 : var3 ^ true;
      }

      public void layoutDecorated(View var1, int var2, int var3, int var4, int var5) {
         Rect var6 = ((LayoutParams)var1.getLayoutParams()).mDecorInsets;
         var1.layout(var2 + var6.left, var3 + var6.top, var4 - var6.right, var5 - var6.bottom);
      }

      public void layoutDecoratedWithMargins(View var1, int var2, int var3, int var4, int var5) {
         LayoutParams var6 = (LayoutParams)var1.getLayoutParams();
         Rect var7 = var6.mDecorInsets;
         var1.layout(var2 + var7.left + var6.leftMargin, var3 + var7.top + var6.topMargin, var4 - var7.right - var6.rightMargin, var5 - var7.bottom - var6.bottomMargin);
      }

      public void measureChild(View var1, int var2, int var3) {
         LayoutParams var9 = (LayoutParams)var1.getLayoutParams();
         Rect var8 = this.mRecyclerView.getItemDecorInsetsForChild(var1);
         int var7 = var8.left;
         int var6 = var8.right;
         int var4 = var8.top;
         int var5 = var8.bottom;
         var2 = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight() + var2 + var7 + var6, var9.width, this.canScrollHorizontally());
         var3 = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom() + var3 + var4 + var5, var9.height, this.canScrollVertically());
         if (this.shouldMeasureChild(var1, var2, var3, var9)) {
            var1.measure(var2, var3);
         }

      }

      public void measureChildWithMargins(View var1, int var2, int var3) {
         LayoutParams var9 = (LayoutParams)var1.getLayoutParams();
         Rect var8 = this.mRecyclerView.getItemDecorInsetsForChild(var1);
         int var6 = var8.left;
         int var7 = var8.right;
         int var4 = var8.top;
         int var5 = var8.bottom;
         var2 = getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight() + var9.leftMargin + var9.rightMargin + var2 + var6 + var7, var9.width, this.canScrollHorizontally());
         var3 = getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom() + var9.topMargin + var9.bottomMargin + var3 + var4 + var5, var9.height, this.canScrollVertically());
         if (this.shouldMeasureChild(var1, var2, var3, var9)) {
            var1.measure(var2, var3);
         }

      }

      public void moveView(int var1, int var2) {
         View var3 = this.getChildAt(var1);
         if (var3 != null) {
            this.detachViewAt(var1);
            this.attachView(var3, var2);
         } else {
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + var1 + this.mRecyclerView.toString());
         }
      }

      public void offsetChildrenHorizontal(int var1) {
         RecyclerView var2 = this.mRecyclerView;
         if (var2 != null) {
            var2.offsetChildrenHorizontal(var1);
         }

      }

      public void offsetChildrenVertical(int var1) {
         RecyclerView var2 = this.mRecyclerView;
         if (var2 != null) {
            var2.offsetChildrenVertical(var1);
         }

      }

      public void onAdapterChanged(Adapter var1, Adapter var2) {
      }

      public boolean onAddFocusables(RecyclerView var1, ArrayList var2, int var3, int var4) {
         return false;
      }

      public void onAttachedToWindow(RecyclerView var1) {
      }

      @Deprecated
      public void onDetachedFromWindow(RecyclerView var1) {
      }

      public void onDetachedFromWindow(RecyclerView var1, Recycler var2) {
         this.onDetachedFromWindow(var1);
      }

      public View onFocusSearchFailed(View var1, int var2, Recycler var3, State var4) {
         return null;
      }

      public void onInitializeAccessibilityEvent(AccessibilityEvent var1) {
         this.onInitializeAccessibilityEvent(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, var1);
      }

      public void onInitializeAccessibilityEvent(Recycler var1, State var2, AccessibilityEvent var3) {
         RecyclerView var6 = this.mRecyclerView;
         if (var6 != null && var3 != null) {
            boolean var5 = true;
            boolean var4 = var5;
            if (!var6.canScrollVertically(1)) {
               var4 = var5;
               if (!this.mRecyclerView.canScrollVertically(-1)) {
                  var4 = var5;
                  if (!this.mRecyclerView.canScrollHorizontally(-1)) {
                     if (this.mRecyclerView.canScrollHorizontally(1)) {
                        var4 = var5;
                     } else {
                        var4 = false;
                     }
                  }
               }
            }

            var3.setScrollable(var4);
            if (this.mRecyclerView.mAdapter != null) {
               var3.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
            }
         }

      }

      void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat var1) {
         this.onInitializeAccessibilityNodeInfo(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, var1);
      }

      public void onInitializeAccessibilityNodeInfo(Recycler var1, State var2, AccessibilityNodeInfoCompat var3) {
         if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
            var3.addAction(8192);
            var3.setScrollable(true);
         }

         if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
            var3.addAction(4096);
            var3.setScrollable(true);
         }

         var3.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(this.getRowCountForAccessibility(var1, var2), this.getColumnCountForAccessibility(var1, var2), this.isLayoutHierarchical(var1, var2), this.getSelectionModeForAccessibility(var1, var2)));
      }

      void onInitializeAccessibilityNodeInfoForItem(View var1, AccessibilityNodeInfoCompat var2) {
         ViewHolder var3 = RecyclerView.getChildViewHolderInt(var1);
         if (var3 != null && !var3.isRemoved() && !this.mChildHelper.isHidden(var3.itemView)) {
            this.onInitializeAccessibilityNodeInfoForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, var1, var2);
         }

      }

      public void onInitializeAccessibilityNodeInfoForItem(Recycler var1, State var2, View var3, AccessibilityNodeInfoCompat var4) {
      }

      public View onInterceptFocusSearch(View var1, int var2) {
         return null;
      }

      public void onItemsAdded(RecyclerView var1, int var2, int var3) {
      }

      public void onItemsChanged(RecyclerView var1) {
      }

      public void onItemsMoved(RecyclerView var1, int var2, int var3, int var4) {
      }

      public void onItemsRemoved(RecyclerView var1, int var2, int var3) {
      }

      public void onItemsUpdated(RecyclerView var1, int var2, int var3) {
      }

      public void onItemsUpdated(RecyclerView var1, int var2, int var3, Object var4) {
         this.onItemsUpdated(var1, var2, var3);
      }

      public void onLayoutChildren(Recycler var1, State var2) {
         Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
      }

      public void onLayoutCompleted(State var1) {
      }

      public void onMeasure(Recycler var1, State var2, int var3, int var4) {
         this.mRecyclerView.defaultOnMeasure(var3, var4);
      }

      @Deprecated
      public boolean onRequestChildFocus(RecyclerView var1, View var2, View var3) {
         boolean var4;
         if (!this.isSmoothScrolling() && !var1.isComputingLayout()) {
            var4 = false;
         } else {
            var4 = true;
         }

         return var4;
      }

      public boolean onRequestChildFocus(RecyclerView var1, State var2, View var3, View var4) {
         return this.onRequestChildFocus(var1, var3, var4);
      }

      public void onRestoreInstanceState(Parcelable var1) {
      }

      public Parcelable onSaveInstanceState() {
         return null;
      }

      public void onScrollStateChanged(int var1) {
      }

      void onSmoothScrollerStopped(SmoothScroller var1) {
         if (this.mSmoothScroller == var1) {
            this.mSmoothScroller = null;
         }

      }

      boolean performAccessibilityAction(int var1, Bundle var2) {
         return this.performAccessibilityAction(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, var1, var2);
      }

      public boolean performAccessibilityAction(Recycler var1, State var2, int var3, Bundle var4) {
         RecyclerView var7 = this.mRecyclerView;
         if (var7 == null) {
            return false;
         } else {
            int var5;
            label41: {
               label40: {
                  if (var3 != 4096) {
                     if (var3 != 8192) {
                        var3 = 0;
                        var5 = 0;
                        break label41;
                     }

                     if (var7.canScrollVertically(-1)) {
                        var3 = -(this.getHeight() - this.getPaddingTop() - this.getPaddingBottom());
                     } else {
                        var3 = 0;
                     }

                     var5 = var3;
                     if (this.mRecyclerView.canScrollHorizontally(-1)) {
                        var5 = -(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
                        break label40;
                     }
                  } else {
                     if (var7.canScrollVertically(1)) {
                        var3 = this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
                     } else {
                        var3 = 0;
                     }

                     var5 = var3;
                     if (this.mRecyclerView.canScrollHorizontally(1)) {
                        var5 = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
                        break label40;
                     }
                  }

                  var3 = 0;
                  break label41;
               }

               int var6 = var3;
               var3 = var5;
               var5 = var6;
            }

            if (var5 == 0 && var3 == 0) {
               return false;
            } else {
               this.mRecyclerView.smoothScrollBy(var3, var5, (Interpolator)null, Integer.MIN_VALUE, true);
               return true;
            }
         }
      }

      boolean performAccessibilityActionForItem(View var1, int var2, Bundle var3) {
         return this.performAccessibilityActionForItem(this.mRecyclerView.mRecycler, this.mRecyclerView.mState, var1, var2, var3);
      }

      public boolean performAccessibilityActionForItem(Recycler var1, State var2, View var3, int var4, Bundle var5) {
         return false;
      }

      public void postOnAnimation(Runnable var1) {
         RecyclerView var2 = this.mRecyclerView;
         if (var2 != null) {
            ViewCompat.postOnAnimation(var2, var1);
         }

      }

      public void removeAllViews() {
         for(int var1 = this.getChildCount() - 1; var1 >= 0; --var1) {
            this.mChildHelper.removeViewAt(var1);
         }

      }

      public void removeAndRecycleAllViews(Recycler var1) {
         for(int var2 = this.getChildCount() - 1; var2 >= 0; --var2) {
            if (!RecyclerView.getChildViewHolderInt(this.getChildAt(var2)).shouldIgnore()) {
               this.removeAndRecycleViewAt(var2, var1);
            }
         }

      }

      void removeAndRecycleScrapInt(Recycler var1) {
         int var3 = var1.getScrapCount();

         for(int var2 = var3 - 1; var2 >= 0; --var2) {
            View var5 = var1.getScrapViewAt(var2);
            ViewHolder var4 = RecyclerView.getChildViewHolderInt(var5);
            if (!var4.shouldIgnore()) {
               var4.setIsRecyclable(false);
               if (var4.isTmpDetached()) {
                  this.mRecyclerView.removeDetachedView(var5, false);
               }

               if (this.mRecyclerView.mItemAnimator != null) {
                  this.mRecyclerView.mItemAnimator.endAnimation(var4);
               }

               var4.setIsRecyclable(true);
               var1.quickRecycleScrapView(var5);
            }
         }

         var1.clearScrap();
         if (var3 > 0) {
            this.mRecyclerView.invalidate();
         }

      }

      public void removeAndRecycleView(View var1, Recycler var2) {
         this.removeView(var1);
         var2.recycleView(var1);
      }

      public void removeAndRecycleViewAt(int var1, Recycler var2) {
         View var3 = this.getChildAt(var1);
         this.removeViewAt(var1);
         var2.recycleView(var3);
      }

      public boolean removeCallbacks(Runnable var1) {
         RecyclerView var2 = this.mRecyclerView;
         return var2 != null ? var2.removeCallbacks(var1) : false;
      }

      public void removeDetachedView(View var1) {
         this.mRecyclerView.removeDetachedView(var1, false);
      }

      public void removeView(View var1) {
         this.mChildHelper.removeView(var1);
      }

      public void removeViewAt(int var1) {
         if (this.getChildAt(var1) != null) {
            this.mChildHelper.removeViewAt(var1);
         }

      }

      public boolean requestChildRectangleOnScreen(RecyclerView var1, View var2, Rect var3, boolean var4) {
         return this.requestChildRectangleOnScreen(var1, var2, var3, var4, false);
      }

      public boolean requestChildRectangleOnScreen(RecyclerView var1, View var2, Rect var3, boolean var4, boolean var5) {
         int[] var8 = this.getChildRectangleOnScreenScrollAmount(var2, var3);
         int var7 = var8[0];
         int var6 = var8[1];
         if ((!var5 || this.isFocusedChildVisibleAfterScrolling(var1, var7, var6)) && (var7 != 0 || var6 != 0)) {
            if (var4) {
               var1.scrollBy(var7, var6);
            } else {
               var1.smoothScrollBy(var7, var6);
            }

            return true;
         } else {
            return false;
         }
      }

      public void requestLayout() {
         RecyclerView var1 = this.mRecyclerView;
         if (var1 != null) {
            var1.requestLayout();
         }

      }

      public void requestSimpleAnimationsInNextLayout() {
         this.mRequestedSimpleAnimations = true;
      }

      public int scrollHorizontallyBy(int var1, Recycler var2, State var3) {
         return 0;
      }

      public void scrollToPosition(int var1) {
      }

      public int scrollVerticallyBy(int var1, Recycler var2, State var3) {
         return 0;
      }

      @Deprecated
      public void setAutoMeasureEnabled(boolean var1) {
         this.mAutoMeasure = var1;
      }

      void setExactMeasureSpecsFrom(RecyclerView var1) {
         this.setMeasureSpecs(MeasureSpec.makeMeasureSpec(var1.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(var1.getHeight(), 1073741824));
      }

      public final void setItemPrefetchEnabled(boolean var1) {
         if (var1 != this.mItemPrefetchEnabled) {
            this.mItemPrefetchEnabled = var1;
            this.mPrefetchMaxCountObserved = 0;
            RecyclerView var2 = this.mRecyclerView;
            if (var2 != null) {
               var2.mRecycler.updateViewCacheSize();
            }
         }

      }

      void setMeasureSpecs(int var1, int var2) {
         this.mWidth = MeasureSpec.getSize(var1);
         var1 = MeasureSpec.getMode(var1);
         this.mWidthMode = var1;
         if (var1 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.mWidth = 0;
         }

         this.mHeight = MeasureSpec.getSize(var2);
         var1 = MeasureSpec.getMode(var2);
         this.mHeightMode = var1;
         if (var1 == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.mHeight = 0;
         }

      }

      public void setMeasuredDimension(int var1, int var2) {
         this.mRecyclerView.setMeasuredDimension(var1, var2);
      }

      public void setMeasuredDimension(Rect var1, int var2, int var3) {
         int var9 = var1.width();
         int var6 = this.getPaddingLeft();
         int var5 = this.getPaddingRight();
         int var4 = var1.height();
         int var7 = this.getPaddingTop();
         int var8 = this.getPaddingBottom();
         this.setMeasuredDimension(chooseSize(var2, var9 + var6 + var5, this.getMinimumWidth()), chooseSize(var3, var4 + var7 + var8, this.getMinimumHeight()));
      }

      void setMeasuredDimensionFromChildren(int var1, int var2) {
         int var10 = this.getChildCount();
         if (var10 == 0) {
            this.mRecyclerView.defaultOnMeasure(var1, var2);
         } else {
            int var6 = 0;
            int var8 = Integer.MIN_VALUE;
            int var7 = Integer.MAX_VALUE;
            int var3 = Integer.MAX_VALUE;

            int var4;
            int var9;
            for(var4 = Integer.MIN_VALUE; var6 < var10; var3 = var9) {
               View var12 = this.getChildAt(var6);
               Rect var11 = this.mRecyclerView.mTempRect;
               this.getDecoratedBoundsWithMargins(var12, var11);
               int var5 = var7;
               if (var11.left < var7) {
                  var5 = var11.left;
               }

               var7 = var8;
               if (var11.right > var8) {
                  var7 = var11.right;
               }

               var9 = var3;
               if (var11.top < var3) {
                  var9 = var11.top;
               }

               var3 = var4;
               if (var11.bottom > var4) {
                  var3 = var11.bottom;
               }

               ++var6;
               var8 = var7;
               var4 = var3;
               var7 = var5;
            }

            this.mRecyclerView.mTempRect.set(var7, var3, var8, var4);
            this.setMeasuredDimension(this.mRecyclerView.mTempRect, var1, var2);
         }
      }

      public void setMeasurementCacheEnabled(boolean var1) {
         this.mMeasurementCacheEnabled = var1;
      }

      void setRecyclerView(RecyclerView var1) {
         if (var1 == null) {
            this.mRecyclerView = null;
            this.mChildHelper = null;
            this.mWidth = 0;
            this.mHeight = 0;
         } else {
            this.mRecyclerView = var1;
            this.mChildHelper = var1.mChildHelper;
            this.mWidth = var1.getWidth();
            this.mHeight = var1.getHeight();
         }

         this.mWidthMode = 1073741824;
         this.mHeightMode = 1073741824;
      }

      boolean shouldMeasureChild(View var1, int var2, int var3, LayoutParams var4) {
         boolean var5;
         if (!var1.isLayoutRequested() && this.mMeasurementCacheEnabled && isMeasurementUpToDate(var1.getWidth(), var2, var4.width) && isMeasurementUpToDate(var1.getHeight(), var3, var4.height)) {
            var5 = false;
         } else {
            var5 = true;
         }

         return var5;
      }

      boolean shouldMeasureTwice() {
         return false;
      }

      boolean shouldReMeasureChild(View var1, int var2, int var3, LayoutParams var4) {
         boolean var5;
         if (this.mMeasurementCacheEnabled && isMeasurementUpToDate(var1.getMeasuredWidth(), var2, var4.width) && isMeasurementUpToDate(var1.getMeasuredHeight(), var3, var4.height)) {
            var5 = false;
         } else {
            var5 = true;
         }

         return var5;
      }

      public void smoothScrollToPosition(RecyclerView var1, State var2, int var3) {
         Log.e("RecyclerView", "You must override smoothScrollToPosition to support smooth scrolling");
      }

      public void startSmoothScroll(SmoothScroller var1) {
         SmoothScroller var2 = this.mSmoothScroller;
         if (var2 != null && var1 != var2 && var2.isRunning()) {
            this.mSmoothScroller.stop();
         }

         this.mSmoothScroller = var1;
         var1.start(this.mRecyclerView, this);
      }

      public void stopIgnoringView(View var1) {
         ViewHolder var2 = RecyclerView.getChildViewHolderInt(var1);
         var2.stopIgnoring();
         var2.resetInternal();
         var2.addFlags(4);
      }

      void stopSmoothScroller() {
         SmoothScroller var1 = this.mSmoothScroller;
         if (var1 != null) {
            var1.stop();
         }

      }

      public boolean supportsPredictiveItemAnimations() {
         return false;
      }

      public interface LayoutPrefetchRegistry {
         void addPosition(int var1, int var2);
      }

      public static class Properties {
         public int orientation;
         public boolean reverseLayout;
         public int spanCount;
         public boolean stackFromEnd;
      }
   }

   public static class LayoutParams extends ViewGroup.MarginLayoutParams {
      final Rect mDecorInsets = new Rect();
      boolean mInsetsDirty = true;
      boolean mPendingInvalidate = false;
      ViewHolder mViewHolder;

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

      public LayoutParams(LayoutParams var1) {
         super(var1);
      }

      public int getAbsoluteAdapterPosition() {
         return this.mViewHolder.getAbsoluteAdapterPosition();
      }

      public int getBindingAdapterPosition() {
         return this.mViewHolder.getBindingAdapterPosition();
      }

      @Deprecated
      public int getViewAdapterPosition() {
         return this.mViewHolder.getBindingAdapterPosition();
      }

      public int getViewLayoutPosition() {
         return this.mViewHolder.getLayoutPosition();
      }

      @Deprecated
      public int getViewPosition() {
         return this.mViewHolder.getPosition();
      }

      public boolean isItemChanged() {
         return this.mViewHolder.isUpdated();
      }

      public boolean isItemRemoved() {
         return this.mViewHolder.isRemoved();
      }

      public boolean isViewInvalid() {
         return this.mViewHolder.isInvalid();
      }

      public boolean viewNeedsUpdate() {
         return this.mViewHolder.needsUpdate();
      }
   }

   public interface OnChildAttachStateChangeListener {
      void onChildViewAttachedToWindow(View var1);

      void onChildViewDetachedFromWindow(View var1);
   }

   public abstract static class OnFlingListener {
      public abstract boolean onFling(int var1, int var2);
   }

   public interface OnItemTouchListener {
      boolean onInterceptTouchEvent(RecyclerView var1, MotionEvent var2);

      void onRequestDisallowInterceptTouchEvent(boolean var1);

      void onTouchEvent(RecyclerView var1, MotionEvent var2);
   }

   public abstract static class OnScrollListener {
      public void onScrollStateChanged(RecyclerView var1, int var2) {
      }

      public void onScrolled(RecyclerView var1, int var2, int var3) {
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Orientation {
   }

   public static class RecycledViewPool {
      private static final int DEFAULT_MAX_SCRAP = 5;
      private int mAttachCount = 0;
      SparseArray mScrap = new SparseArray();

      private ScrapData getScrapDataForType(int var1) {
         ScrapData var3 = (ScrapData)this.mScrap.get(var1);
         ScrapData var2 = var3;
         if (var3 == null) {
            var2 = new ScrapData();
            this.mScrap.put(var1, var2);
         }

         return var2;
      }

      void attach() {
         ++this.mAttachCount;
      }

      public void clear() {
         for(int var1 = 0; var1 < this.mScrap.size(); ++var1) {
            ((ScrapData)this.mScrap.valueAt(var1)).mScrapHeap.clear();
         }

      }

      void detach() {
         --this.mAttachCount;
      }

      void factorInBindTime(int var1, long var2) {
         ScrapData var4 = this.getScrapDataForType(var1);
         var4.mBindRunningAverageNs = this.runningAverage(var4.mBindRunningAverageNs, var2);
      }

      void factorInCreateTime(int var1, long var2) {
         ScrapData var4 = this.getScrapDataForType(var1);
         var4.mCreateRunningAverageNs = this.runningAverage(var4.mCreateRunningAverageNs, var2);
      }

      public ViewHolder getRecycledView(int var1) {
         ScrapData var2 = (ScrapData)this.mScrap.get(var1);
         if (var2 != null && !var2.mScrapHeap.isEmpty()) {
            ArrayList var3 = var2.mScrapHeap;

            for(var1 = var3.size() - 1; var1 >= 0; --var1) {
               if (!((ViewHolder)var3.get(var1)).isAttachedToTransitionOverlay()) {
                  return (ViewHolder)var3.remove(var1);
               }
            }
         }

         return null;
      }

      public int getRecycledViewCount(int var1) {
         return this.getScrapDataForType(var1).mScrapHeap.size();
      }

      void onAdapterChanged(Adapter var1, Adapter var2, boolean var3) {
         if (var1 != null) {
            this.detach();
         }

         if (!var3 && this.mAttachCount == 0) {
            this.clear();
         }

         if (var2 != null) {
            this.attach();
         }

      }

      public void putRecycledView(ViewHolder var1) {
         int var2 = var1.getItemViewType();
         ArrayList var3 = this.getScrapDataForType(var2).mScrapHeap;
         if (((ScrapData)this.mScrap.get(var2)).mMaxScrap > var3.size()) {
            var1.resetInternal();
            var3.add(var1);
         }
      }

      long runningAverage(long var1, long var3) {
         return var1 == 0L ? var3 : var1 / 4L * 3L + var3 / 4L;
      }

      public void setMaxRecycledViews(int var1, int var2) {
         ScrapData var3 = this.getScrapDataForType(var1);
         var3.mMaxScrap = var2;
         ArrayList var4 = var3.mScrapHeap;

         while(var4.size() > var2) {
            var4.remove(var4.size() - 1);
         }

      }

      int size() {
         int var3 = 0;

         int var1;
         int var2;
         for(var1 = 0; var3 < this.mScrap.size(); var1 = var2) {
            ArrayList var4 = ((ScrapData)this.mScrap.valueAt(var3)).mScrapHeap;
            var2 = var1;
            if (var4 != null) {
               var2 = var1 + var4.size();
            }

            ++var3;
         }

         return var1;
      }

      boolean willBindInTime(int var1, long var2, long var4) {
         long var7 = this.getScrapDataForType(var1).mBindRunningAverageNs;
         boolean var6;
         if (var7 != 0L && var2 + var7 >= var4) {
            var6 = false;
         } else {
            var6 = true;
         }

         return var6;
      }

      boolean willCreateInTime(int var1, long var2, long var4) {
         long var7 = this.getScrapDataForType(var1).mCreateRunningAverageNs;
         boolean var6;
         if (var7 != 0L && var2 + var7 >= var4) {
            var6 = false;
         } else {
            var6 = true;
         }

         return var6;
      }

      static class ScrapData {
         long mBindRunningAverageNs = 0L;
         long mCreateRunningAverageNs = 0L;
         int mMaxScrap = 5;
         final ArrayList mScrapHeap = new ArrayList();
      }
   }

   public final class Recycler {
      static final int DEFAULT_CACHE_SIZE = 2;
      final ArrayList mAttachedScrap;
      final ArrayList mCachedViews;
      ArrayList mChangedScrap;
      RecycledViewPool mRecyclerPool;
      private int mRequestedCacheMax;
      private final List mUnmodifiableAttachedScrap;
      private ViewCacheExtension mViewCacheExtension;
      int mViewCacheMax;
      final RecyclerView this$0;

      public Recycler(RecyclerView var1) {
         this.this$0 = var1;
         ArrayList var2 = new ArrayList();
         this.mAttachedScrap = var2;
         this.mChangedScrap = null;
         this.mCachedViews = new ArrayList();
         this.mUnmodifiableAttachedScrap = Collections.unmodifiableList(var2);
         this.mRequestedCacheMax = 2;
         this.mViewCacheMax = 2;
      }

      private void attachAccessibilityDelegateOnBind(ViewHolder var1) {
         if (this.this$0.isAccessibilityEnabled()) {
            View var3 = var1.itemView;
            if (ViewCompat.getImportantForAccessibility(var3) == 0) {
               ViewCompat.setImportantForAccessibility(var3, 1);
            }

            if (this.this$0.mAccessibilityDelegate == null) {
               return;
            }

            AccessibilityDelegateCompat var2 = this.this$0.mAccessibilityDelegate.getItemDelegate();
            if (var2 instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
               ((RecyclerViewAccessibilityDelegate.ItemDelegate)var2).saveOriginalDelegate(var3);
            }

            ViewCompat.setAccessibilityDelegate(var3, var2);
         }

      }

      private void invalidateDisplayListInt(ViewGroup var1, boolean var2) {
         int var3;
         for(var3 = var1.getChildCount() - 1; var3 >= 0; --var3) {
            View var4 = var1.getChildAt(var3);
            if (var4 instanceof ViewGroup) {
               this.invalidateDisplayListInt((ViewGroup)var4, true);
            }
         }

         if (var2) {
            if (var1.getVisibility() == 4) {
               var1.setVisibility(0);
               var1.setVisibility(4);
            } else {
               var3 = var1.getVisibility();
               var1.setVisibility(4);
               var1.setVisibility(var3);
            }

         }
      }

      private void invalidateDisplayListInt(ViewHolder var1) {
         if (var1.itemView instanceof ViewGroup) {
            this.invalidateDisplayListInt((ViewGroup)var1.itemView, false);
         }

      }

      private boolean tryBindViewHolderByDeadline(ViewHolder var1, int var2, int var3, long var4) {
         var1.mBindingAdapter = null;
         var1.mOwnerRecyclerView = this.this$0;
         int var6 = var1.getItemViewType();
         long var7 = this.this$0.getNanoTime();
         if (var4 != Long.MAX_VALUE && !this.mRecyclerPool.willBindInTime(var6, var7, var4)) {
            return false;
         } else {
            this.this$0.mAdapter.bindViewHolder(var1, var2);
            var4 = this.this$0.getNanoTime();
            this.mRecyclerPool.factorInBindTime(var1.getItemViewType(), var4 - var7);
            this.attachAccessibilityDelegateOnBind(var1);
            if (this.this$0.mState.isPreLayout()) {
               var1.mPreLayoutPosition = var3;
            }

            return true;
         }
      }

      void addViewHolderToRecycledViewPool(ViewHolder var1, boolean var2) {
         RecyclerView.clearNestedRecyclerViewIfNotNested(var1);
         View var4 = var1.itemView;
         if (this.this$0.mAccessibilityDelegate != null) {
            AccessibilityDelegateCompat var3 = this.this$0.mAccessibilityDelegate.getItemDelegate();
            if (var3 instanceof RecyclerViewAccessibilityDelegate.ItemDelegate) {
               var3 = ((RecyclerViewAccessibilityDelegate.ItemDelegate)var3).getAndRemoveOriginalDelegateForItem(var4);
            } else {
               var3 = null;
            }

            ViewCompat.setAccessibilityDelegate(var4, var3);
         }

         if (var2) {
            this.dispatchViewRecycled(var1);
         }

         var1.mBindingAdapter = null;
         var1.mOwnerRecyclerView = null;
         this.getRecycledViewPool().putRecycledView(var1);
      }

      public void bindViewToPosition(View var1, int var2) {
         ViewHolder var5 = RecyclerView.getChildViewHolderInt(var1);
         if (var5 != null) {
            int var3 = this.this$0.mAdapterHelper.findPositionOffset(var2);
            if (var3 >= 0 && var3 < this.this$0.mAdapter.getItemCount()) {
               this.tryBindViewHolderByDeadline(var5, var3, var2, Long.MAX_VALUE);
               ViewGroup.LayoutParams var6 = var5.itemView.getLayoutParams();
               LayoutParams var7;
               if (var6 == null) {
                  var7 = (LayoutParams)this.this$0.generateDefaultLayoutParams();
                  var5.itemView.setLayoutParams(var7);
               } else if (!this.this$0.checkLayoutParams(var6)) {
                  var7 = (LayoutParams)this.this$0.generateLayoutParams(var6);
                  var5.itemView.setLayoutParams(var7);
               } else {
                  var7 = (LayoutParams)var6;
               }

               boolean var4 = true;
               var7.mInsetsDirty = true;
               var7.mViewHolder = var5;
               if (var5.itemView.getParent() != null) {
                  var4 = false;
               }

               var7.mPendingInvalidate = var4;
            } else {
               throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + var2 + "(offset:" + var3 + ").state:" + this.this$0.mState.getItemCount() + this.this$0.exceptionLabel());
            }
         } else {
            throw new IllegalArgumentException("The view does not have a ViewHolder. You cannot pass arbitrary views to this method, they should be created by the Adapter" + this.this$0.exceptionLabel());
         }
      }

      public void clear() {
         this.mAttachedScrap.clear();
         this.recycleAndClearCachedViews();
      }

      void clearOldPositions() {
         int var3 = this.mCachedViews.size();
         byte var2 = 0;

         int var1;
         for(var1 = 0; var1 < var3; ++var1) {
            ((ViewHolder)this.mCachedViews.get(var1)).clearOldPosition();
         }

         var3 = this.mAttachedScrap.size();

         for(var1 = 0; var1 < var3; ++var1) {
            ((ViewHolder)this.mAttachedScrap.get(var1)).clearOldPosition();
         }

         ArrayList var4 = this.mChangedScrap;
         if (var4 != null) {
            var3 = var4.size();

            for(var1 = var2; var1 < var3; ++var1) {
               ((ViewHolder)this.mChangedScrap.get(var1)).clearOldPosition();
            }
         }

      }

      void clearScrap() {
         this.mAttachedScrap.clear();
         ArrayList var1 = this.mChangedScrap;
         if (var1 != null) {
            var1.clear();
         }

      }

      public int convertPreLayoutPositionToPostLayout(int var1) {
         if (var1 >= 0 && var1 < this.this$0.mState.getItemCount()) {
            return !this.this$0.mState.isPreLayout() ? var1 : this.this$0.mAdapterHelper.findPositionOffset(var1);
         } else {
            throw new IndexOutOfBoundsException("invalid position " + var1 + ". State item count is " + this.this$0.mState.getItemCount() + this.this$0.exceptionLabel());
         }
      }

      void dispatchViewRecycled(ViewHolder var1) {
         if (this.this$0.mRecyclerListener != null) {
            this.this$0.mRecyclerListener.onViewRecycled(var1);
         }

         int var3 = this.this$0.mRecyclerListeners.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            ((RecyclerListener)this.this$0.mRecyclerListeners.get(var2)).onViewRecycled(var1);
         }

         if (this.this$0.mAdapter != null) {
            this.this$0.mAdapter.onViewRecycled(var1);
         }

         if (this.this$0.mState != null) {
            this.this$0.mViewInfoStore.removeViewHolder(var1);
         }

      }

      ViewHolder getChangedScrapViewForPosition(int var1) {
         ArrayList var7 = this.mChangedScrap;
         if (var7 != null) {
            int var4 = var7.size();
            if (var4 != 0) {
               byte var3 = 0;

               ViewHolder var8;
               for(int var2 = 0; var2 < var4; ++var2) {
                  var8 = (ViewHolder)this.mChangedScrap.get(var2);
                  if (!var8.wasReturnedFromScrap() && var8.getLayoutPosition() == var1) {
                     var8.addFlags(32);
                     return var8;
                  }
               }

               if (this.this$0.mAdapter.hasStableIds()) {
                  var1 = this.this$0.mAdapterHelper.findPositionOffset(var1);
                  if (var1 > 0 && var1 < this.this$0.mAdapter.getItemCount()) {
                     long var5 = this.this$0.mAdapter.getItemId(var1);

                     for(var1 = var3; var1 < var4; ++var1) {
                        var8 = (ViewHolder)this.mChangedScrap.get(var1);
                        if (!var8.wasReturnedFromScrap() && var8.getItemId() == var5) {
                           var8.addFlags(32);
                           return var8;
                        }
                     }
                  }
               }
            }
         }

         return null;
      }

      RecycledViewPool getRecycledViewPool() {
         if (this.mRecyclerPool == null) {
            this.mRecyclerPool = new RecycledViewPool();
         }

         return this.mRecyclerPool;
      }

      int getScrapCount() {
         return this.mAttachedScrap.size();
      }

      public List getScrapList() {
         return this.mUnmodifiableAttachedScrap;
      }

      ViewHolder getScrapOrCachedViewForId(long var1, int var3, boolean var4) {
         int var5;
         ViewHolder var6;
         for(var5 = this.mAttachedScrap.size() - 1; var5 >= 0; --var5) {
            var6 = (ViewHolder)this.mAttachedScrap.get(var5);
            if (var6.getItemId() == var1 && !var6.wasReturnedFromScrap()) {
               if (var3 == var6.getItemViewType()) {
                  var6.addFlags(32);
                  if (var6.isRemoved() && !this.this$0.mState.isPreLayout()) {
                     var6.setFlags(2, 14);
                  }

                  return var6;
               }

               if (!var4) {
                  this.mAttachedScrap.remove(var5);
                  this.this$0.removeDetachedView(var6.itemView, false);
                  this.quickRecycleScrapView(var6.itemView);
               }
            }
         }

         for(var5 = this.mCachedViews.size() - 1; var5 >= 0; --var5) {
            var6 = (ViewHolder)this.mCachedViews.get(var5);
            if (var6.getItemId() == var1 && !var6.isAttachedToTransitionOverlay()) {
               if (var3 == var6.getItemViewType()) {
                  if (!var4) {
                     this.mCachedViews.remove(var5);
                  }

                  return var6;
               }

               if (!var4) {
                  this.recycleCachedViewAt(var5);
                  return null;
               }
            }
         }

         return null;
      }

      ViewHolder getScrapOrHiddenOrCachedHolderForPosition(int var1, boolean var2) {
         int var5 = this.mAttachedScrap.size();
         byte var4 = 0;

         int var3;
         ViewHolder var6;
         for(var3 = 0; var3 < var5; ++var3) {
            var6 = (ViewHolder)this.mAttachedScrap.get(var3);
            if (!var6.wasReturnedFromScrap() && var6.getLayoutPosition() == var1 && !var6.isInvalid() && (this.this$0.mState.mInPreLayout || !var6.isRemoved())) {
               var6.addFlags(32);
               return var6;
            }
         }

         if (!var2) {
            View var8 = this.this$0.mChildHelper.findHiddenNonRemovedView(var1);
            if (var8 != null) {
               ViewHolder var7 = RecyclerView.getChildViewHolderInt(var8);
               this.this$0.mChildHelper.unhide(var8);
               var1 = this.this$0.mChildHelper.indexOfChild(var8);
               if (var1 != -1) {
                  this.this$0.mChildHelper.detachViewFromParent(var1);
                  this.scrapView(var8);
                  var7.addFlags(8224);
                  return var7;
               }

               throw new IllegalStateException("layout index should not be -1 after unhiding a view:" + var7 + this.this$0.exceptionLabel());
            }
         }

         var5 = this.mCachedViews.size();

         for(var3 = var4; var3 < var5; ++var3) {
            var6 = (ViewHolder)this.mCachedViews.get(var3);
            if (!var6.isInvalid() && var6.getLayoutPosition() == var1 && !var6.isAttachedToTransitionOverlay()) {
               if (!var2) {
                  this.mCachedViews.remove(var3);
               }

               return var6;
            }
         }

         return null;
      }

      View getScrapViewAt(int var1) {
         return ((ViewHolder)this.mAttachedScrap.get(var1)).itemView;
      }

      public View getViewForPosition(int var1) {
         return this.getViewForPosition(var1, false);
      }

      View getViewForPosition(int var1, boolean var2) {
         return this.tryGetViewHolderForPositionByDeadline(var1, var2, Long.MAX_VALUE).itemView;
      }

      void markItemDecorInsetsDirty() {
         int var2 = this.mCachedViews.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            LayoutParams var3 = (LayoutParams)((ViewHolder)this.mCachedViews.get(var1)).itemView.getLayoutParams();
            if (var3 != null) {
               var3.mInsetsDirty = true;
            }
         }

      }

      void markKnownViewsInvalid() {
         int var2 = this.mCachedViews.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            ViewHolder var3 = (ViewHolder)this.mCachedViews.get(var1);
            if (var3 != null) {
               var3.addFlags(6);
               var3.addChangePayload((Object)null);
            }
         }

         if (this.this$0.mAdapter == null || !this.this$0.mAdapter.hasStableIds()) {
            this.recycleAndClearCachedViews();
         }

      }

      void offsetPositionRecordsForInsert(int var1, int var2) {
         int var4 = this.mCachedViews.size();

         for(int var3 = 0; var3 < var4; ++var3) {
            ViewHolder var5 = (ViewHolder)this.mCachedViews.get(var3);
            if (var5 != null && var5.mPosition >= var1) {
               var5.offsetPosition(var2, false);
            }
         }

      }

      void offsetPositionRecordsForMove(int var1, int var2) {
         int var3;
         byte var4;
         int var5;
         if (var1 < var2) {
            var4 = -1;
            var3 = var1;
            var5 = var2;
         } else {
            var4 = 1;
            var5 = var1;
            var3 = var2;
         }

         int var7 = this.mCachedViews.size();

         for(int var6 = 0; var6 < var7; ++var6) {
            ViewHolder var8 = (ViewHolder)this.mCachedViews.get(var6);
            if (var8 != null && var8.mPosition >= var3 && var8.mPosition <= var5) {
               if (var8.mPosition == var1) {
                  var8.offsetPosition(var2 - var1, false);
               } else {
                  var8.offsetPosition(var4, false);
               }
            }
         }

      }

      void offsetPositionRecordsForRemove(int var1, int var2, boolean var3) {
         for(int var4 = this.mCachedViews.size() - 1; var4 >= 0; --var4) {
            ViewHolder var5 = (ViewHolder)this.mCachedViews.get(var4);
            if (var5 != null) {
               if (var5.mPosition >= var1 + var2) {
                  var5.offsetPosition(-var2, var3);
               } else if (var5.mPosition >= var1) {
                  var5.addFlags(8);
                  this.recycleCachedViewAt(var4);
               }
            }
         }

      }

      void onAdapterChanged(Adapter var1, Adapter var2, boolean var3) {
         this.clear();
         this.getRecycledViewPool().onAdapterChanged(var1, var2, var3);
      }

      void quickRecycleScrapView(View var1) {
         ViewHolder var2 = RecyclerView.getChildViewHolderInt(var1);
         var2.mScrapContainer = null;
         var2.mInChangeScrap = false;
         var2.clearReturnedFromScrapFlag();
         this.recycleViewHolderInternal(var2);
      }

      void recycleAndClearCachedViews() {
         for(int var1 = this.mCachedViews.size() - 1; var1 >= 0; --var1) {
            this.recycleCachedViewAt(var1);
         }

         this.mCachedViews.clear();
         if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
            this.this$0.mPrefetchRegistry.clearPrefetchPositions();
         }

      }

      void recycleCachedViewAt(int var1) {
         this.addViewHolderToRecycledViewPool((ViewHolder)this.mCachedViews.get(var1), true);
         this.mCachedViews.remove(var1);
      }

      public void recycleView(View var1) {
         ViewHolder var2 = RecyclerView.getChildViewHolderInt(var1);
         if (var2.isTmpDetached()) {
            this.this$0.removeDetachedView(var1, false);
         }

         if (var2.isScrap()) {
            var2.unScrap();
         } else if (var2.wasReturnedFromScrap()) {
            var2.clearReturnedFromScrapFlag();
         }

         this.recycleViewHolderInternal(var2);
         if (this.this$0.mItemAnimator != null && !var2.isRecyclable()) {
            this.this$0.mItemAnimator.endAnimation(var2);
         }

      }

      void recycleViewHolderInternal(ViewHolder var1) {
         boolean var6 = var1.isScrap();
         boolean var5 = false;
         boolean var3 = false;
         boolean var4 = true;
         if (!var6 && var1.itemView.getParent() == null) {
            if (!var1.isTmpDetached()) {
               if (var1.shouldIgnore()) {
                  throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle." + this.this$0.exceptionLabel());
               } else {
                  var5 = var1.doesTransientStatePreventRecycling();
                  boolean var2;
                  if (this.this$0.mAdapter != null && var5 && this.this$0.mAdapter.onFailedToRecycleView(var1)) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (!var2 && !var1.isRecyclable()) {
                     var4 = false;
                     var2 = var3;
                     var3 = var4;
                  } else {
                     if (this.mViewCacheMax > 0 && !var1.hasAnyOfTheFlags(526)) {
                        int var8 = this.mCachedViews.size();
                        int var9 = var8;
                        if (var8 >= this.mViewCacheMax) {
                           var9 = var8;
                           if (var8 > 0) {
                              this.recycleCachedViewAt(0);
                              var9 = var8 - 1;
                           }
                        }

                        var8 = var9;
                        if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                           var8 = var9;
                           if (var9 > 0) {
                              var8 = var9;
                              if (!this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(var1.mPosition)) {
                                 --var9;

                                 while(var9 >= 0) {
                                    var8 = ((ViewHolder)this.mCachedViews.get(var9)).mPosition;
                                    if (!this.this$0.mPrefetchRegistry.lastPrefetchIncludedPosition(var8)) {
                                       break;
                                    }

                                    --var9;
                                 }

                                 var8 = var9 + 1;
                              }
                           }
                        }

                        this.mCachedViews.add(var8, var1);
                        var2 = true;
                     } else {
                        var2 = false;
                     }

                     if (!var2) {
                        this.addViewHolderToRecycledViewPool(var1, true);
                        var3 = var4;
                     } else {
                        var3 = false;
                     }
                  }

                  this.this$0.mViewInfoStore.removeViewHolder(var1);
                  if (!var2 && !var3 && var5) {
                     var1.mBindingAdapter = null;
                     var1.mOwnerRecyclerView = null;
                  }

               }
            } else {
               throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + var1 + this.this$0.exceptionLabel());
            }
         } else {
            StringBuilder var7 = (new StringBuilder()).append("Scrapped or attached views may not be recycled. isScrap:").append(var1.isScrap()).append(" isAttached:");
            if (var1.itemView.getParent() != null) {
               var5 = true;
            }

            throw new IllegalArgumentException(var7.append(var5).append(this.this$0.exceptionLabel()).toString());
         }
      }

      void scrapView(View var1) {
         ViewHolder var2 = RecyclerView.getChildViewHolderInt(var1);
         if (!var2.hasAnyOfTheFlags(12) && var2.isUpdated() && !this.this$0.canReuseUpdatedViewHolder(var2)) {
            if (this.mChangedScrap == null) {
               this.mChangedScrap = new ArrayList();
            }

            var2.setScrapContainer(this, true);
            this.mChangedScrap.add(var2);
         } else {
            if (var2.isInvalid() && !var2.isRemoved() && !this.this$0.mAdapter.hasStableIds()) {
               throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool." + this.this$0.exceptionLabel());
            }

            var2.setScrapContainer(this, false);
            this.mAttachedScrap.add(var2);
         }

      }

      void setRecycledViewPool(RecycledViewPool var1) {
         RecycledViewPool var2 = this.mRecyclerPool;
         if (var2 != null) {
            var2.detach();
         }

         this.mRecyclerPool = var1;
         if (var1 != null && this.this$0.getAdapter() != null) {
            this.mRecyclerPool.attach();
         }

      }

      void setViewCacheExtension(ViewCacheExtension var1) {
         this.mViewCacheExtension = var1;
      }

      public void setViewCacheSize(int var1) {
         this.mRequestedCacheMax = var1;
         this.updateViewCacheSize();
      }

      ViewHolder tryGetViewHolderForPositionByDeadline(int var1, boolean var2, long var3) {
         if (var1 >= 0 && var1 < this.this$0.mState.getItemCount()) {
            boolean var6;
            boolean var13;
            ViewHolder var15;
            ViewHolder var16;
            label143: {
               boolean var14 = this.this$0.mState.isPreLayout();
               var13 = true;
               if (var14) {
                  var16 = this.getChangedScrapViewForPosition(var1);
                  var15 = var16;
                  if (var16 != null) {
                     var6 = true;
                     break label143;
                  }
               } else {
                  var15 = null;
               }

               var6 = false;
               var16 = var15;
            }

            var15 = var16;
            boolean var5 = var6;
            if (var16 == null) {
               var16 = this.getScrapOrHiddenOrCachedHolderForPosition(var1, var2);
               var15 = var16;
               var5 = var6;
               if (var16 != null) {
                  if (!this.validateViewHolderForOffsetPosition(var16)) {
                     if (!var2) {
                        var16.addFlags(4);
                        if (var16.isScrap()) {
                           this.this$0.removeDetachedView(var16.itemView, false);
                           var16.unScrap();
                        } else if (var16.wasReturnedFromScrap()) {
                           var16.clearReturnedFromScrapFlag();
                        }

                        this.recycleViewHolderInternal(var16);
                     }

                     var15 = null;
                     var5 = var6;
                  } else {
                     var5 = true;
                     var15 = var16;
                  }
               }
            }

            label136: {
               var16 = var15;
               boolean var7 = var5;
               if (var15 == null) {
                  int var19 = this.this$0.mAdapterHelper.findPositionOffset(var1);
                  if (var19 < 0 || var19 >= this.this$0.mAdapter.getItemCount()) {
                     throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + var1 + "(offset:" + var19 + ").state:" + this.this$0.mState.getItemCount() + this.this$0.exceptionLabel());
                  }

                  int var8 = this.this$0.mAdapter.getItemViewType(var19);
                  var6 = var5;
                  if (this.this$0.mAdapter.hasStableIds()) {
                     var16 = this.getScrapOrCachedViewForId(this.this$0.mAdapter.getItemId(var19), var8, var2);
                     var15 = var16;
                     var6 = var5;
                     if (var16 != null) {
                        var16.mPosition = var19;
                        var6 = true;
                        var15 = var16;
                     }
                  }

                  var16 = var15;
                  if (var15 == null) {
                     ViewCacheExtension var17 = this.mViewCacheExtension;
                     var16 = var15;
                     if (var17 != null) {
                        View var20 = var17.getViewForPositionAndType(this, var1, var8);
                        var16 = var15;
                        if (var20 != null) {
                           var16 = this.this$0.getChildViewHolder(var20);
                           if (var16 == null) {
                              throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder" + this.this$0.exceptionLabel());
                           }

                           if (var16.shouldIgnore()) {
                              throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view." + this.this$0.exceptionLabel());
                           }
                        }
                     }
                  }

                  var15 = var16;
                  if (var16 == null) {
                     var15 = this.getRecycledViewPool().getRecycledView(var8);
                     if (var15 != null) {
                        var15.resetInternal();
                        if (RecyclerView.FORCE_INVALIDATE_DISPLAY_LIST) {
                           this.invalidateDisplayListInt(var15);
                        }
                     }
                  }

                  var16 = var15;
                  var7 = var6;
                  if (var15 == null) {
                     long var9 = this.this$0.getNanoTime();
                     if (var3 != Long.MAX_VALUE && !this.mRecyclerPool.willCreateInTime(var8, var9, var3)) {
                        return null;
                     }

                     var16 = this.this$0.mAdapter.createViewHolder(this.this$0, var8);
                     if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                        RecyclerView var21 = RecyclerView.findNestedRecyclerView(var16.itemView);
                        if (var21 != null) {
                           var16.mNestedRecyclerView = new WeakReference(var21);
                        }
                     }

                     long var11 = this.this$0.getNanoTime();
                     this.mRecyclerPool.factorInCreateTime(var8, var11 - var9);
                     break label136;
                  }
               }

               var6 = var7;
            }

            if (var6 && !this.this$0.mState.isPreLayout() && var16.hasAnyOfTheFlags(8192)) {
               var16.setFlags(0, 8192);
               if (this.this$0.mState.mRunSimpleAnimations) {
                  int var18 = RecyclerView.ItemAnimator.buildAdapterChangeFlagsForAnimations(var16);
                  ItemAnimator.ItemHolderInfo var22 = this.this$0.mItemAnimator.recordPreLayoutInformation(this.this$0.mState, var16, var18 | 4096, var16.getUnmodifiedPayloads());
                  this.this$0.recordAnimationInfoIfBouncedHiddenView(var16, var22);
               }
            }

            label154: {
               if (this.this$0.mState.isPreLayout() && var16.isBound()) {
                  var16.mPreLayoutPosition = var1;
               } else if (!var16.isBound() || var16.needsUpdate() || var16.isInvalid()) {
                  var2 = this.tryBindViewHolderByDeadline(var16, this.this$0.mAdapterHelper.findPositionOffset(var1), var1, var3);
                  break label154;
               }

               var2 = false;
            }

            ViewGroup.LayoutParams var23 = var16.itemView.getLayoutParams();
            LayoutParams var24;
            if (var23 == null) {
               var24 = (LayoutParams)this.this$0.generateDefaultLayoutParams();
               var16.itemView.setLayoutParams(var24);
            } else if (!this.this$0.checkLayoutParams(var23)) {
               var24 = (LayoutParams)this.this$0.generateLayoutParams(var23);
               var16.itemView.setLayoutParams(var24);
            } else {
               var24 = (LayoutParams)var23;
            }

            var24.mViewHolder = var16;
            if (var6 && var2) {
               var2 = var13;
            } else {
               var2 = false;
            }

            var24.mPendingInvalidate = var2;
            return var16;
         } else {
            throw new IndexOutOfBoundsException("Invalid item position " + var1 + "(" + var1 + "). Item count:" + this.this$0.mState.getItemCount() + this.this$0.exceptionLabel());
         }
      }

      void unscrapView(ViewHolder var1) {
         if (var1.mInChangeScrap) {
            this.mChangedScrap.remove(var1);
         } else {
            this.mAttachedScrap.remove(var1);
         }

         var1.mScrapContainer = null;
         var1.mInChangeScrap = false;
         var1.clearReturnedFromScrapFlag();
      }

      void updateViewCacheSize() {
         int var1;
         if (this.this$0.mLayout != null) {
            var1 = this.this$0.mLayout.mPrefetchMaxCountObserved;
         } else {
            var1 = 0;
         }

         this.mViewCacheMax = this.mRequestedCacheMax + var1;

         for(var1 = this.mCachedViews.size() - 1; var1 >= 0 && this.mCachedViews.size() > this.mViewCacheMax; --var1) {
            this.recycleCachedViewAt(var1);
         }

      }

      boolean validateViewHolderForOffsetPosition(ViewHolder var1) {
         if (var1.isRemoved()) {
            return this.this$0.mState.isPreLayout();
         } else if (var1.mPosition >= 0 && var1.mPosition < this.this$0.mAdapter.getItemCount()) {
            boolean var3 = this.this$0.mState.isPreLayout();
            boolean var2 = false;
            if (!var3 && this.this$0.mAdapter.getItemViewType(var1.mPosition) != var1.getItemViewType()) {
               return false;
            } else if (this.this$0.mAdapter.hasStableIds()) {
               if (var1.getItemId() == this.this$0.mAdapter.getItemId(var1.mPosition)) {
                  var2 = true;
               }

               return var2;
            } else {
               return true;
            }
         } else {
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + var1 + this.this$0.exceptionLabel());
         }
      }

      void viewRangeUpdate(int var1, int var2) {
         for(int var3 = this.mCachedViews.size() - 1; var3 >= 0; --var3) {
            ViewHolder var5 = (ViewHolder)this.mCachedViews.get(var3);
            if (var5 != null) {
               int var4 = var5.mPosition;
               if (var4 >= var1 && var4 < var2 + var1) {
                  var5.addFlags(2);
                  this.recycleCachedViewAt(var3);
               }
            }
         }

      }
   }

   public interface RecyclerListener {
      void onViewRecycled(ViewHolder var1);
   }

   private class RecyclerViewDataObserver extends AdapterDataObserver {
      final RecyclerView this$0;

      RecyclerViewDataObserver(RecyclerView var1) {
         this.this$0 = var1;
      }

      public void onChanged() {
         this.this$0.assertNotInLayoutOrScroll((String)null);
         this.this$0.mState.mStructureChanged = true;
         this.this$0.processDataSetCompletelyChanged(true);
         if (!this.this$0.mAdapterHelper.hasPendingUpdates()) {
            this.this$0.requestLayout();
         }

      }

      public void onItemRangeChanged(int var1, int var2, Object var3) {
         this.this$0.assertNotInLayoutOrScroll((String)null);
         if (this.this$0.mAdapterHelper.onItemRangeChanged(var1, var2, var3)) {
            this.triggerUpdateProcessor();
         }

      }

      public void onItemRangeInserted(int var1, int var2) {
         this.this$0.assertNotInLayoutOrScroll((String)null);
         if (this.this$0.mAdapterHelper.onItemRangeInserted(var1, var2)) {
            this.triggerUpdateProcessor();
         }

      }

      public void onItemRangeMoved(int var1, int var2, int var3) {
         this.this$0.assertNotInLayoutOrScroll((String)null);
         if (this.this$0.mAdapterHelper.onItemRangeMoved(var1, var2, var3)) {
            this.triggerUpdateProcessor();
         }

      }

      public void onItemRangeRemoved(int var1, int var2) {
         this.this$0.assertNotInLayoutOrScroll((String)null);
         if (this.this$0.mAdapterHelper.onItemRangeRemoved(var1, var2)) {
            this.triggerUpdateProcessor();
         }

      }

      public void onStateRestorationPolicyChanged() {
         if (this.this$0.mPendingSavedState != null) {
            Adapter var1 = this.this$0.mAdapter;
            if (var1 != null && var1.canRestoreState()) {
               this.this$0.requestLayout();
            }

         }
      }

      void triggerUpdateProcessor() {
         if (RecyclerView.POST_UPDATES_ON_ANIMATION && this.this$0.mHasFixedSize && this.this$0.mIsAttached) {
            RecyclerView var1 = this.this$0;
            ViewCompat.postOnAnimation(var1, var1.mUpdateChildViewsRunnable);
         } else {
            this.this$0.mAdapterUpdateDuringMeasure = true;
            this.this$0.requestLayout();
         }

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
      Parcelable mLayoutState;

      SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         if (var2 == null) {
            var2 = LayoutManager.class.getClassLoader();
         }

         this.mLayoutState = var1.readParcelable(var2);
      }

      SavedState(Parcelable var1) {
         super(var1);
      }

      void copyFrom(SavedState var1) {
         this.mLayoutState = var1.mLayoutState;
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeParcelable(this.mLayoutState, 0);
      }
   }

   public static class SimpleOnItemTouchListener implements OnItemTouchListener {
      public boolean onInterceptTouchEvent(RecyclerView var1, MotionEvent var2) {
         return false;
      }

      public void onRequestDisallowInterceptTouchEvent(boolean var1) {
      }

      public void onTouchEvent(RecyclerView var1, MotionEvent var2) {
      }
   }

   public abstract static class SmoothScroller {
      private LayoutManager mLayoutManager;
      private boolean mPendingInitialRun;
      private RecyclerView mRecyclerView;
      private final Action mRecyclingAction = new Action(0, 0);
      private boolean mRunning;
      private boolean mStarted;
      private int mTargetPosition = -1;
      private View mTargetView;

      public PointF computeScrollVectorForPosition(int var1) {
         LayoutManager var2 = this.getLayoutManager();
         if (var2 instanceof ScrollVectorProvider) {
            return ((ScrollVectorProvider)var2).computeScrollVectorForPosition(var1);
         } else {
            Log.w("RecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + ScrollVectorProvider.class.getCanonicalName());
            return null;
         }
      }

      public View findViewByPosition(int var1) {
         return this.mRecyclerView.mLayout.findViewByPosition(var1);
      }

      public int getChildCount() {
         return this.mRecyclerView.mLayout.getChildCount();
      }

      public int getChildPosition(View var1) {
         return this.mRecyclerView.getChildLayoutPosition(var1);
      }

      public LayoutManager getLayoutManager() {
         return this.mLayoutManager;
      }

      public int getTargetPosition() {
         return this.mTargetPosition;
      }

      @Deprecated
      public void instantScrollToPosition(int var1) {
         this.mRecyclerView.scrollToPosition(var1);
      }

      public boolean isPendingInitialRun() {
         return this.mPendingInitialRun;
      }

      public boolean isRunning() {
         return this.mRunning;
      }

      protected void normalize(PointF var1) {
         float var2 = (float)Math.sqrt((double)(var1.x * var1.x + var1.y * var1.y));
         var1.x /= var2;
         var1.y /= var2;
      }

      void onAnimation(int var1, int var2) {
         RecyclerView var4 = this.mRecyclerView;
         if (this.mTargetPosition == -1 || var4 == null) {
            this.stop();
         }

         if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null) {
            PointF var5 = this.computeScrollVectorForPosition(this.mTargetPosition);
            if (var5 != null && (var5.x != 0.0F || var5.y != 0.0F)) {
               var4.scrollStep((int)Math.signum(var5.x), (int)Math.signum(var5.y), (int[])null);
            }
         }

         this.mPendingInitialRun = false;
         View var6 = this.mTargetView;
         if (var6 != null) {
            if (this.getChildPosition(var6) == this.mTargetPosition) {
               this.onTargetFound(this.mTargetView, var4.mState, this.mRecyclingAction);
               this.mRecyclingAction.runIfNecessary(var4);
               this.stop();
            } else {
               Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
               this.mTargetView = null;
            }
         }

         if (this.mRunning) {
            this.onSeekTargetStep(var1, var2, var4.mState, this.mRecyclingAction);
            boolean var3 = this.mRecyclingAction.hasJumpTarget();
            this.mRecyclingAction.runIfNecessary(var4);
            if (var3 && this.mRunning) {
               this.mPendingInitialRun = true;
               var4.mViewFlinger.postOnAnimation();
            }
         }

      }

      protected void onChildAttachedToWindow(View var1) {
         if (this.getChildPosition(var1) == this.getTargetPosition()) {
            this.mTargetView = var1;
         }

      }

      protected abstract void onSeekTargetStep(int var1, int var2, State var3, Action var4);

      protected abstract void onStart();

      protected abstract void onStop();

      protected abstract void onTargetFound(View var1, State var2, Action var3);

      public void setTargetPosition(int var1) {
         this.mTargetPosition = var1;
      }

      void start(RecyclerView var1, LayoutManager var2) {
         var1.mViewFlinger.stop();
         if (this.mStarted) {
            Log.w("RecyclerView", "An instance of " + this.getClass().getSimpleName() + " was started more than once. Each instance of" + this.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
         }

         this.mRecyclerView = var1;
         this.mLayoutManager = var2;
         if (this.mTargetPosition != -1) {
            var1.mState.mTargetPosition = this.mTargetPosition;
            this.mRunning = true;
            this.mPendingInitialRun = true;
            this.mTargetView = this.findViewByPosition(this.getTargetPosition());
            this.onStart();
            this.mRecyclerView.mViewFlinger.postOnAnimation();
            this.mStarted = true;
         } else {
            throw new IllegalArgumentException("Invalid target position");
         }
      }

      protected final void stop() {
         if (this.mRunning) {
            this.mRunning = false;
            this.onStop();
            this.mRecyclerView.mState.mTargetPosition = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            this.mLayoutManager.onSmoothScrollerStopped(this);
            this.mLayoutManager = null;
            this.mRecyclerView = null;
         }
      }

      public static class Action {
         public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
         private boolean mChanged;
         private int mConsecutiveUpdates;
         private int mDuration;
         private int mDx;
         private int mDy;
         private Interpolator mInterpolator;
         private int mJumpToPosition;

         public Action(int var1, int var2) {
            this(var1, var2, Integer.MIN_VALUE, (Interpolator)null);
         }

         public Action(int var1, int var2, int var3) {
            this(var1, var2, var3, (Interpolator)null);
         }

         public Action(int var1, int var2, int var3, Interpolator var4) {
            this.mJumpToPosition = -1;
            this.mChanged = false;
            this.mConsecutiveUpdates = 0;
            this.mDx = var1;
            this.mDy = var2;
            this.mDuration = var3;
            this.mInterpolator = var4;
         }

         private void validate() {
            if (this.mInterpolator != null && this.mDuration < 1) {
               throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
            } else if (this.mDuration < 1) {
               throw new IllegalStateException("Scroll duration must be a positive number");
            }
         }

         public int getDuration() {
            return this.mDuration;
         }

         public int getDx() {
            return this.mDx;
         }

         public int getDy() {
            return this.mDy;
         }

         public Interpolator getInterpolator() {
            return this.mInterpolator;
         }

         boolean hasJumpTarget() {
            boolean var1;
            if (this.mJumpToPosition >= 0) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         }

         public void jumpTo(int var1) {
            this.mJumpToPosition = var1;
         }

         void runIfNecessary(RecyclerView var1) {
            int var2 = this.mJumpToPosition;
            if (var2 >= 0) {
               this.mJumpToPosition = -1;
               var1.jumpToPositionForSmoothScroller(var2);
               this.mChanged = false;
            } else {
               if (this.mChanged) {
                  this.validate();
                  var1.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, this.mDuration, this.mInterpolator);
                  var2 = this.mConsecutiveUpdates + 1;
                  this.mConsecutiveUpdates = var2;
                  if (var2 > 10) {
                     Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                  }

                  this.mChanged = false;
               } else {
                  this.mConsecutiveUpdates = 0;
               }

            }
         }

         public void setDuration(int var1) {
            this.mChanged = true;
            this.mDuration = var1;
         }

         public void setDx(int var1) {
            this.mChanged = true;
            this.mDx = var1;
         }

         public void setDy(int var1) {
            this.mChanged = true;
            this.mDy = var1;
         }

         public void setInterpolator(Interpolator var1) {
            this.mChanged = true;
            this.mInterpolator = var1;
         }

         public void update(int var1, int var2, int var3, Interpolator var4) {
            this.mDx = var1;
            this.mDy = var2;
            this.mDuration = var3;
            this.mInterpolator = var4;
            this.mChanged = true;
         }
      }

      public interface ScrollVectorProvider {
         PointF computeScrollVectorForPosition(int var1);
      }
   }

   public static class State {
      static final int STEP_ANIMATIONS = 4;
      static final int STEP_LAYOUT = 2;
      static final int STEP_START = 1;
      private SparseArray mData;
      int mDeletedInvisibleItemCountSincePreviousLayout = 0;
      long mFocusedItemId;
      int mFocusedItemPosition;
      int mFocusedSubChildId;
      boolean mInPreLayout = false;
      boolean mIsMeasuring = false;
      int mItemCount = 0;
      int mLayoutStep = 1;
      int mPreviousLayoutItemCount = 0;
      int mRemainingScrollHorizontal;
      int mRemainingScrollVertical;
      boolean mRunPredictiveAnimations = false;
      boolean mRunSimpleAnimations = false;
      boolean mStructureChanged = false;
      int mTargetPosition = -1;
      boolean mTrackOldChangeHolders = false;

      void assertLayoutStep(int var1) {
         if ((this.mLayoutStep & var1) == 0) {
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(var1) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
         }
      }

      public boolean didStructureChange() {
         return this.mStructureChanged;
      }

      public Object get(int var1) {
         SparseArray var2 = this.mData;
         return var2 == null ? null : var2.get(var1);
      }

      public int getItemCount() {
         int var1;
         if (this.mInPreLayout) {
            var1 = this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
         } else {
            var1 = this.mItemCount;
         }

         return var1;
      }

      public int getRemainingScrollHorizontal() {
         return this.mRemainingScrollHorizontal;
      }

      public int getRemainingScrollVertical() {
         return this.mRemainingScrollVertical;
      }

      public int getTargetScrollPosition() {
         return this.mTargetPosition;
      }

      public boolean hasTargetScrollPosition() {
         boolean var1;
         if (this.mTargetPosition != -1) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public boolean isMeasuring() {
         return this.mIsMeasuring;
      }

      public boolean isPreLayout() {
         return this.mInPreLayout;
      }

      void prepareForNestedPrefetch(Adapter var1) {
         this.mLayoutStep = 1;
         this.mItemCount = var1.getItemCount();
         this.mInPreLayout = false;
         this.mTrackOldChangeHolders = false;
         this.mIsMeasuring = false;
      }

      public void put(int var1, Object var2) {
         if (this.mData == null) {
            this.mData = new SparseArray();
         }

         this.mData.put(var1, var2);
      }

      public void remove(int var1) {
         SparseArray var2 = this.mData;
         if (var2 != null) {
            var2.remove(var1);
         }
      }

      public String toString() {
         return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mIsMeasuring=" + this.mIsMeasuring + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
      }

      public boolean willRunPredictiveAnimations() {
         return this.mRunPredictiveAnimations;
      }

      public boolean willRunSimpleAnimations() {
         return this.mRunSimpleAnimations;
      }
   }

   public abstract static class ViewCacheExtension {
      public abstract View getViewForPositionAndType(Recycler var1, int var2, int var3);
   }

   class ViewFlinger implements Runnable {
      private boolean mEatRunOnAnimationRequest;
      Interpolator mInterpolator;
      private int mLastFlingX;
      private int mLastFlingY;
      OverScroller mOverScroller;
      private boolean mReSchedulePostAnimationCallback;
      final RecyclerView this$0;

      ViewFlinger(RecyclerView var1) {
         this.this$0 = var1;
         this.mInterpolator = RecyclerView.sQuinticInterpolator;
         this.mEatRunOnAnimationRequest = false;
         this.mReSchedulePostAnimationCallback = false;
         this.mOverScroller = new OverScroller(var1.getContext(), RecyclerView.sQuinticInterpolator);
      }

      private int computeScrollDuration(int var1, int var2) {
         int var3 = Math.abs(var1);
         int var4 = Math.abs(var2);
         boolean var6;
         if (var3 > var4) {
            var6 = true;
         } else {
            var6 = false;
         }

         RecyclerView var5 = this.this$0;
         if (var6) {
            var1 = var5.getWidth();
         } else {
            var1 = var5.getHeight();
         }

         if (var6) {
            var2 = var3;
         } else {
            var2 = var4;
         }

         return Math.min((int)(((float)var2 / (float)var1 + 1.0F) * 300.0F), 2000);
      }

      private void internalPostOnAnimation() {
         this.this$0.removeCallbacks(this);
         ViewCompat.postOnAnimation(this.this$0, this);
      }

      public void fling(int var1, int var2) {
         this.this$0.setScrollState(2);
         this.mLastFlingY = 0;
         this.mLastFlingX = 0;
         if (this.mInterpolator != RecyclerView.sQuinticInterpolator) {
            this.mInterpolator = RecyclerView.sQuinticInterpolator;
            this.mOverScroller = new OverScroller(this.this$0.getContext(), RecyclerView.sQuinticInterpolator);
         }

         this.mOverScroller.fling(0, 0, var1, var2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
         this.postOnAnimation();
      }

      void postOnAnimation() {
         if (this.mEatRunOnAnimationRequest) {
            this.mReSchedulePostAnimationCallback = true;
         } else {
            this.internalPostOnAnimation();
         }

      }

      public void run() {
         if (this.this$0.mLayout == null) {
            this.stop();
         } else {
            this.mReSchedulePostAnimationCallback = false;
            this.mEatRunOnAnimationRequest = true;
            this.this$0.consumePendingUpdateOperations();
            OverScroller var9 = this.mOverScroller;
            if (var9.computeScrollOffset()) {
               int var2 = var9.getCurrX();
               int var1 = var9.getCurrY();
               int var4 = var2 - this.mLastFlingX;
               int var3 = var1 - this.mLastFlingY;
               this.mLastFlingX = var2;
               this.mLastFlingY = var1;
               this.this$0.mReusableIntPair[0] = 0;
               this.this$0.mReusableIntPair[1] = 0;
               RecyclerView var10 = this.this$0;
               var2 = var4;
               var1 = var3;
               if (var10.dispatchNestedPreScroll(var4, var3, var10.mReusableIntPair, (int[])null, 1)) {
                  var2 = var4 - this.this$0.mReusableIntPair[0];
                  var1 = var3 - this.this$0.mReusableIntPair[1];
               }

               if (this.this$0.getOverScrollMode() != 2) {
                  this.this$0.considerReleasingGlowsOnScroll(var2, var1);
               }

               int var5;
               int var6;
               SmoothScroller var16;
               if (this.this$0.mAdapter != null) {
                  this.this$0.mReusableIntPair[0] = 0;
                  this.this$0.mReusableIntPair[1] = 0;
                  var10 = this.this$0;
                  var10.scrollStep(var2, var1, var10.mReusableIntPair);
                  var5 = this.this$0.mReusableIntPair[0];
                  var6 = this.this$0.mReusableIntPair[1];
                  int var7 = var2 - var5;
                  int var8 = var1 - var6;
                  var16 = this.this$0.mLayout.mSmoothScroller;
                  var2 = var5;
                  var4 = var7;
                  var1 = var6;
                  var3 = var8;
                  if (var16 != null) {
                     var2 = var5;
                     var4 = var7;
                     var1 = var6;
                     var3 = var8;
                     if (!var16.isPendingInitialRun()) {
                        var2 = var5;
                        var4 = var7;
                        var1 = var6;
                        var3 = var8;
                        if (var16.isRunning()) {
                           var1 = this.this$0.mState.getItemCount();
                           if (var1 == 0) {
                              var16.stop();
                              var2 = var5;
                              var4 = var7;
                              var1 = var6;
                              var3 = var8;
                           } else if (var16.getTargetPosition() >= var1) {
                              var16.setTargetPosition(var1 - 1);
                              var16.onAnimation(var5, var6);
                              var2 = var5;
                              var4 = var7;
                              var1 = var6;
                              var3 = var8;
                           } else {
                              var16.onAnimation(var5, var6);
                              var2 = var5;
                              var4 = var7;
                              var1 = var6;
                              var3 = var8;
                           }
                        }
                     }
                  }
               } else {
                  byte var11 = 0;
                  byte var13 = 0;
                  var3 = var1;
                  var1 = var13;
                  var4 = var2;
                  var2 = var11;
               }

               if (!this.this$0.mItemDecorations.isEmpty()) {
                  this.this$0.invalidate();
               }

               this.this$0.mReusableIntPair[0] = 0;
               this.this$0.mReusableIntPair[1] = 0;
               var10 = this.this$0;
               var10.dispatchNestedScroll(var2, var1, var4, var3, (int[])null, 1, var10.mReusableIntPair);
               var6 = var4 - this.this$0.mReusableIntPair[0];
               var5 = var3 - this.this$0.mReusableIntPair[1];
               if (var2 != 0 || var1 != 0) {
                  this.this$0.dispatchOnScrolled(var2, var1);
               }

               if (!this.this$0.awakenScrollBars()) {
                  this.this$0.invalidate();
               }

               boolean var12;
               if (var9.getCurrX() == var9.getFinalX()) {
                  var12 = true;
               } else {
                  var12 = false;
               }

               boolean var14;
               if (var9.getCurrY() == var9.getFinalY()) {
                  var14 = true;
               } else {
                  var14 = false;
               }

               if (var9.isFinished() || (var12 || var6 != 0) && (var14 || var5 != 0)) {
                  var12 = true;
               } else {
                  var12 = false;
               }

               var16 = this.this$0.mLayout.mSmoothScroller;
               if (var16 != null && var16.isPendingInitialRun()) {
                  var14 = true;
               } else {
                  var14 = false;
               }

               if (!var14 && var12) {
                  if (this.this$0.getOverScrollMode() != 2) {
                     var2 = (int)var9.getCurrVelocity();
                     if (var6 < 0) {
                        var1 = -var2;
                     } else if (var6 > 0) {
                        var1 = var2;
                     } else {
                        var1 = 0;
                     }

                     if (var5 < 0) {
                        var2 = -var2;
                     } else if (var5 <= 0) {
                        var2 = 0;
                     }

                     this.this$0.absorbGlows(var1, var2);
                  }

                  if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                     this.this$0.mPrefetchRegistry.clearPrefetchPositions();
                  }
               } else {
                  this.postOnAnimation();
                  if (this.this$0.mGapWorker != null) {
                     this.this$0.mGapWorker.postFromTraversal(this.this$0, var2, var1);
                  }
               }
            }

            SmoothScroller var15 = this.this$0.mLayout.mSmoothScroller;
            if (var15 != null && var15.isPendingInitialRun()) {
               var15.onAnimation(0, 0);
            }

            this.mEatRunOnAnimationRequest = false;
            if (this.mReSchedulePostAnimationCallback) {
               this.internalPostOnAnimation();
            } else {
               this.this$0.setScrollState(0);
               this.this$0.stopNestedScroll(1);
            }

         }
      }

      public void smoothScrollBy(int var1, int var2, int var3, Interpolator var4) {
         int var5 = var3;
         if (var3 == Integer.MIN_VALUE) {
            var5 = this.computeScrollDuration(var1, var2);
         }

         Interpolator var6 = var4;
         if (var4 == null) {
            var6 = RecyclerView.sQuinticInterpolator;
         }

         if (this.mInterpolator != var6) {
            this.mInterpolator = var6;
            this.mOverScroller = new OverScroller(this.this$0.getContext(), var6);
         }

         this.mLastFlingY = 0;
         this.mLastFlingX = 0;
         this.this$0.setScrollState(2);
         this.mOverScroller.startScroll(0, 0, var1, var2, var5);
         if (VERSION.SDK_INT < 23) {
            this.mOverScroller.computeScrollOffset();
         }

         this.postOnAnimation();
      }

      public void stop() {
         this.this$0.removeCallbacks(this);
         this.mOverScroller.abortAnimation();
      }
   }

   public abstract static class ViewHolder {
      static final int FLAG_ADAPTER_FULLUPDATE = 1024;
      static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
      static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
      static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
      static final int FLAG_BOUND = 1;
      static final int FLAG_IGNORE = 128;
      static final int FLAG_INVALID = 4;
      static final int FLAG_MOVED = 2048;
      static final int FLAG_NOT_RECYCLABLE = 16;
      static final int FLAG_REMOVED = 8;
      static final int FLAG_RETURNED_FROM_SCRAP = 32;
      static final int FLAG_TMP_DETACHED = 256;
      static final int FLAG_UPDATE = 2;
      private static final List FULLUPDATE_PAYLOADS = Collections.emptyList();
      static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
      public final View itemView;
      Adapter mBindingAdapter;
      int mFlags;
      boolean mInChangeScrap = false;
      private int mIsRecyclableCount = 0;
      long mItemId = -1L;
      int mItemViewType = -1;
      WeakReference mNestedRecyclerView;
      int mOldPosition = -1;
      RecyclerView mOwnerRecyclerView;
      List mPayloads = null;
      int mPendingAccessibilityState = -1;
      int mPosition = -1;
      int mPreLayoutPosition = -1;
      Recycler mScrapContainer = null;
      ViewHolder mShadowedHolder = null;
      ViewHolder mShadowingHolder = null;
      List mUnmodifiedPayloads = null;
      private int mWasImportantForAccessibilityBeforeHidden = 0;

      public ViewHolder(View var1) {
         if (var1 != null) {
            this.itemView = var1;
         } else {
            throw new IllegalArgumentException("itemView may not be null");
         }
      }

      private void createPayloadsIfNeeded() {
         if (this.mPayloads == null) {
            ArrayList var1 = new ArrayList();
            this.mPayloads = var1;
            this.mUnmodifiedPayloads = Collections.unmodifiableList(var1);
         }

      }

      void addChangePayload(Object var1) {
         if (var1 == null) {
            this.addFlags(1024);
         } else if ((1024 & this.mFlags) == 0) {
            this.createPayloadsIfNeeded();
            this.mPayloads.add(var1);
         }

      }

      void addFlags(int var1) {
         this.mFlags |= var1;
      }

      void clearOldPosition() {
         this.mOldPosition = -1;
         this.mPreLayoutPosition = -1;
      }

      void clearPayload() {
         List var1 = this.mPayloads;
         if (var1 != null) {
            var1.clear();
         }

         this.mFlags &= -1025;
      }

      void clearReturnedFromScrapFlag() {
         this.mFlags &= -33;
      }

      void clearTmpDetachFlag() {
         this.mFlags &= -257;
      }

      boolean doesTransientStatePreventRecycling() {
         boolean var1;
         if ((this.mFlags & 16) == 0 && ViewCompat.hasTransientState(this.itemView)) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      void flagRemovedAndOffsetPosition(int var1, int var2, boolean var3) {
         this.addFlags(8);
         this.offsetPosition(var2, var3);
         this.mPosition = var1;
      }

      public final int getAbsoluteAdapterPosition() {
         RecyclerView var1 = this.mOwnerRecyclerView;
         return var1 == null ? -1 : var1.getAdapterPositionInRecyclerView(this);
      }

      @Deprecated
      public final int getAdapterPosition() {
         return this.getBindingAdapterPosition();
      }

      public final Adapter getBindingAdapter() {
         return this.mBindingAdapter;
      }

      public final int getBindingAdapterPosition() {
         if (this.mBindingAdapter == null) {
            return -1;
         } else {
            RecyclerView var2 = this.mOwnerRecyclerView;
            if (var2 == null) {
               return -1;
            } else {
               Adapter var3 = var2.getAdapter();
               if (var3 == null) {
                  return -1;
               } else {
                  int var1 = this.mOwnerRecyclerView.getAdapterPositionInRecyclerView(this);
                  return var1 == -1 ? -1 : var3.findRelativeAdapterPositionIn(this.mBindingAdapter, this, var1);
               }
            }
         }
      }

      public final long getItemId() {
         return this.mItemId;
      }

      public final int getItemViewType() {
         return this.mItemViewType;
      }

      public final int getLayoutPosition() {
         int var2 = this.mPreLayoutPosition;
         int var1 = var2;
         if (var2 == -1) {
            var1 = this.mPosition;
         }

         return var1;
      }

      public final int getOldPosition() {
         return this.mOldPosition;
      }

      @Deprecated
      public final int getPosition() {
         int var2 = this.mPreLayoutPosition;
         int var1 = var2;
         if (var2 == -1) {
            var1 = this.mPosition;
         }

         return var1;
      }

      List getUnmodifiedPayloads() {
         if ((this.mFlags & 1024) == 0) {
            List var1 = this.mPayloads;
            return var1 != null && var1.size() != 0 ? this.mUnmodifiedPayloads : FULLUPDATE_PAYLOADS;
         } else {
            return FULLUPDATE_PAYLOADS;
         }
      }

      boolean hasAnyOfTheFlags(int var1) {
         boolean var2;
         if ((var1 & this.mFlags) != 0) {
            var2 = true;
         } else {
            var2 = false;
         }

         return var2;
      }

      boolean isAdapterPositionUnknown() {
         boolean var1;
         if ((this.mFlags & 512) == 0 && !this.isInvalid()) {
            var1 = false;
         } else {
            var1 = true;
         }

         return var1;
      }

      boolean isAttachedToTransitionOverlay() {
         boolean var1;
         if (this.itemView.getParent() != null && this.itemView.getParent() != this.mOwnerRecyclerView) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean isBound() {
         int var1 = this.mFlags;
         boolean var2 = true;
         if ((var1 & 1) == 0) {
            var2 = false;
         }

         return var2;
      }

      boolean isInvalid() {
         boolean var1;
         if ((this.mFlags & 4) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      public final boolean isRecyclable() {
         boolean var1;
         if ((this.mFlags & 16) == 0 && !ViewCompat.hasTransientState(this.itemView)) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean isRemoved() {
         boolean var1;
         if ((this.mFlags & 8) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean isScrap() {
         boolean var1;
         if (this.mScrapContainer != null) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean isTmpDetached() {
         boolean var1;
         if ((this.mFlags & 256) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean isUpdated() {
         boolean var1;
         if ((this.mFlags & 2) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean needsUpdate() {
         boolean var1;
         if ((this.mFlags & 2) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      void offsetPosition(int var1, boolean var2) {
         if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
         }

         if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
         }

         if (var2) {
            this.mPreLayoutPosition += var1;
         }

         this.mPosition += var1;
         if (this.itemView.getLayoutParams() != null) {
            ((LayoutParams)this.itemView.getLayoutParams()).mInsetsDirty = true;
         }

      }

      void onEnteredHiddenState(RecyclerView var1) {
         int var2 = this.mPendingAccessibilityState;
         if (var2 != -1) {
            this.mWasImportantForAccessibilityBeforeHidden = var2;
         } else {
            this.mWasImportantForAccessibilityBeforeHidden = ViewCompat.getImportantForAccessibility(this.itemView);
         }

         var1.setChildImportantForAccessibilityInternal(this, 4);
      }

      void onLeftHiddenState(RecyclerView var1) {
         var1.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
         this.mWasImportantForAccessibilityBeforeHidden = 0;
      }

      void resetInternal() {
         this.mFlags = 0;
         this.mPosition = -1;
         this.mOldPosition = -1;
         this.mItemId = -1L;
         this.mPreLayoutPosition = -1;
         this.mIsRecyclableCount = 0;
         this.mShadowedHolder = null;
         this.mShadowingHolder = null;
         this.clearPayload();
         this.mWasImportantForAccessibilityBeforeHidden = 0;
         this.mPendingAccessibilityState = -1;
         RecyclerView.clearNestedRecyclerViewIfNotNested(this);
      }

      void saveOldPosition() {
         if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
         }

      }

      void setFlags(int var1, int var2) {
         this.mFlags = var1 & var2 | this.mFlags & ~var2;
      }

      public final void setIsRecyclable(boolean var1) {
         int var2 = this.mIsRecyclableCount;
         if (var1) {
            --var2;
         } else {
            ++var2;
         }

         this.mIsRecyclableCount = var2;
         if (var2 < 0) {
            this.mIsRecyclableCount = 0;
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
         } else if (!var1 && var2 == 1) {
            this.mFlags |= 16;
         } else if (var1 && var2 == 0) {
            this.mFlags &= -17;
         }

      }

      void setScrapContainer(Recycler var1, boolean var2) {
         this.mScrapContainer = var1;
         this.mInChangeScrap = var2;
      }

      boolean shouldBeKeptAsChild() {
         boolean var1;
         if ((this.mFlags & 16) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      boolean shouldIgnore() {
         boolean var1;
         if ((this.mFlags & 128) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }

      void stopIgnoring() {
         this.mFlags &= -129;
      }

      public String toString() {
         String var1;
         if (this.getClass().isAnonymousClass()) {
            var1 = "ViewHolder";
         } else {
            var1 = this.getClass().getSimpleName();
         }

         StringBuilder var3 = new StringBuilder(var1 + "{" + Integer.toHexString(this.hashCode()) + " position=" + this.mPosition + " id=" + this.mItemId + ", oldPos=" + this.mOldPosition + ", pLpos:" + this.mPreLayoutPosition);
         if (this.isScrap()) {
            StringBuilder var2 = var3.append(" scrap ");
            if (this.mInChangeScrap) {
               var1 = "[changeScrap]";
            } else {
               var1 = "[attachedScrap]";
            }

            var2.append(var1);
         }

         if (this.isInvalid()) {
            var3.append(" invalid");
         }

         if (!this.isBound()) {
            var3.append(" unbound");
         }

         if (this.needsUpdate()) {
            var3.append(" update");
         }

         if (this.isRemoved()) {
            var3.append(" removed");
         }

         if (this.shouldIgnore()) {
            var3.append(" ignored");
         }

         if (this.isTmpDetached()) {
            var3.append(" tmpDetached");
         }

         if (!this.isRecyclable()) {
            var3.append(" not recyclable(" + this.mIsRecyclableCount + ")");
         }

         if (this.isAdapterPositionUnknown()) {
            var3.append(" undefined adapter position");
         }

         if (this.itemView.getParent() == null) {
            var3.append(" no parent");
         }

         var3.append("}");
         return var3.toString();
      }

      void unScrap() {
         this.mScrapContainer.unscrapView(this);
      }

      boolean wasReturnedFromScrap() {
         boolean var1;
         if ((this.mFlags & 32) != 0) {
            var1 = true;
         } else {
            var1 = false;
         }

         return var1;
      }
   }
}
