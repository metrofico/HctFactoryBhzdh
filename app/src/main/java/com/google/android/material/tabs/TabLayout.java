package com.google.android.material.tabs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.View.MeasureSpec;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.Pools;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

@ViewPager.DecorView
public class TabLayout extends HorizontalScrollView {
   private static final int ANIMATION_DURATION = 300;
   static final int DEFAULT_GAP_TEXT_ICON = 8;
   private static final int DEFAULT_HEIGHT = 48;
   private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
   static final int FIXED_WRAP_GUTTER_MIN = 16;
   public static final int GRAVITY_CENTER = 1;
   public static final int GRAVITY_FILL = 0;
   public static final int INDICATOR_GRAVITY_BOTTOM = 0;
   public static final int INDICATOR_GRAVITY_CENTER = 1;
   public static final int INDICATOR_GRAVITY_STRETCH = 3;
   public static final int INDICATOR_GRAVITY_TOP = 2;
   private static final int INVALID_WIDTH = -1;
   private static final int MIN_INDICATOR_WIDTH = 24;
   public static final int MODE_FIXED = 1;
   public static final int MODE_SCROLLABLE = 0;
   private static final int TAB_MIN_WIDTH_MARGIN = 56;
   private static final Pools.Pool tabPool = new Pools.SynchronizedPool(16);
   private AdapterChangeListener adapterChangeListener;
   private int contentInsetStart;
   private BaseOnTabSelectedListener currentVpSelectedListener;
   boolean inlineLabel;
   int mode;
   private TabLayoutOnPageChangeListener pageChangeListener;
   private PagerAdapter pagerAdapter;
   private DataSetObserver pagerAdapterObserver;
   private final int requestedTabMaxWidth;
   private final int requestedTabMinWidth;
   private ValueAnimator scrollAnimator;
   private final int scrollableTabMinWidth;
   private BaseOnTabSelectedListener selectedListener;
   private final ArrayList selectedListeners;
   private Tab selectedTab;
   private boolean setupViewPagerImplicitly;
   private final SlidingTabIndicator slidingTabIndicator;
   final int tabBackgroundResId;
   int tabGravity;
   ColorStateList tabIconTint;
   PorterDuff.Mode tabIconTintMode;
   int tabIndicatorAnimationDuration;
   boolean tabIndicatorFullWidth;
   int tabIndicatorGravity;
   int tabMaxWidth;
   int tabPaddingBottom;
   int tabPaddingEnd;
   int tabPaddingStart;
   int tabPaddingTop;
   ColorStateList tabRippleColorStateList;
   Drawable tabSelectedIndicator;
   int tabTextAppearance;
   ColorStateList tabTextColors;
   float tabTextMultiLineSize;
   float tabTextSize;
   private final RectF tabViewContentBounds;
   private final Pools.Pool tabViewPool;
   private final ArrayList tabs;
   boolean unboundedRipple;
   ViewPager viewPager;

   public TabLayout(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public TabLayout(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.tabStyle);
   }

   public TabLayout(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      this.tabs = new ArrayList();
      this.tabViewContentBounds = new RectF();
      this.tabMaxWidth = Integer.MAX_VALUE;
      this.selectedListeners = new ArrayList();
      this.tabViewPool = new Pools.SimplePool(12);
      this.setHorizontalScrollBarEnabled(false);
      SlidingTabIndicator var4 = new SlidingTabIndicator(this, var1);
      this.slidingTabIndicator = var4;
      super.addView(var4, 0, new FrameLayout.LayoutParams(-2, -1));
      TypedArray var8 = ThemeEnforcement.obtainStyledAttributes(var1, var2, R.styleable.TabLayout, var3, R.style.Widget_Design_TabLayout, R.styleable.TabLayout_tabTextAppearance);
      var4.setSelectedIndicatorHeight(var8.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, -1));
      var4.setSelectedIndicatorColor(var8.getColor(R.styleable.TabLayout_tabIndicatorColor, 0));
      this.setSelectedTabIndicator(MaterialResources.getDrawable(var1, var8, R.styleable.TabLayout_tabIndicator));
      this.setSelectedTabIndicatorGravity(var8.getInt(R.styleable.TabLayout_tabIndicatorGravity, 0));
      this.setTabIndicatorFullWidth(var8.getBoolean(R.styleable.TabLayout_tabIndicatorFullWidth, true));
      var3 = var8.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
      this.tabPaddingBottom = var3;
      this.tabPaddingEnd = var3;
      this.tabPaddingTop = var3;
      this.tabPaddingStart = var3;
      this.tabPaddingStart = var8.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, this.tabPaddingStart);
      this.tabPaddingTop = var8.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, this.tabPaddingTop);
      this.tabPaddingEnd = var8.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
      this.tabPaddingBottom = var8.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
      var3 = var8.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
      this.tabTextAppearance = var3;
      TypedArray var9 = var1.obtainStyledAttributes(var3, androidx.appcompat.R.styleable.TextAppearance);

      try {
         this.tabTextSize = (float)var9.getDimensionPixelSize(androidx.appcompat.R.styleable.TextAppearance_android_textSize, 0);
         this.tabTextColors = MaterialResources.getColorStateList(var1, var9, androidx.appcompat.R.styleable.TextAppearance_android_textColor);
      } finally {
         var9.recycle();
      }

      if (var8.hasValue(R.styleable.TabLayout_tabTextColor)) {
         this.tabTextColors = MaterialResources.getColorStateList(var1, var8, R.styleable.TabLayout_tabTextColor);
      }

      if (var8.hasValue(R.styleable.TabLayout_tabSelectedTextColor)) {
         var3 = var8.getColor(R.styleable.TabLayout_tabSelectedTextColor, 0);
         this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), var3);
      }

      this.tabIconTint = MaterialResources.getColorStateList(var1, var8, R.styleable.TabLayout_tabIconTint);
      this.tabIconTintMode = ViewUtils.parseTintMode(var8.getInt(R.styleable.TabLayout_tabIconTintMode, -1), (PorterDuff.Mode)null);
      this.tabRippleColorStateList = MaterialResources.getColorStateList(var1, var8, R.styleable.TabLayout_tabRippleColor);
      this.tabIndicatorAnimationDuration = var8.getInt(R.styleable.TabLayout_tabIndicatorAnimationDuration, 300);
      this.requestedTabMinWidth = var8.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, -1);
      this.requestedTabMaxWidth = var8.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, -1);
      this.tabBackgroundResId = var8.getResourceId(R.styleable.TabLayout_tabBackground, 0);
      this.contentInsetStart = var8.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
      this.mode = var8.getInt(R.styleable.TabLayout_tabMode, 1);
      this.tabGravity = var8.getInt(R.styleable.TabLayout_tabGravity, 0);
      this.inlineLabel = var8.getBoolean(R.styleable.TabLayout_tabInlineLabel, false);
      this.unboundedRipple = var8.getBoolean(R.styleable.TabLayout_tabUnboundedRipple, false);
      var8.recycle();
      Resources var7 = this.getResources();
      this.tabTextMultiLineSize = (float)var7.getDimensionPixelSize(R.dimen.design_tab_text_size_2line);
      this.scrollableTabMinWidth = var7.getDimensionPixelSize(R.dimen.design_tab_scrollable_min_width);
      this.applyModeAndGravity();
   }

   private void addTabFromItemView(TabItem var1) {
      Tab var2 = this.newTab();
      if (var1.text != null) {
         var2.setText(var1.text);
      }

      if (var1.icon != null) {
         var2.setIcon(var1.icon);
      }

      if (var1.customLayout != 0) {
         var2.setCustomView(var1.customLayout);
      }

      if (!TextUtils.isEmpty(var1.getContentDescription())) {
         var2.setContentDescription(var1.getContentDescription());
      }

      this.addTab(var2);
   }

   private void addTabView(Tab var1) {
      TabView var2 = var1.view;
      this.slidingTabIndicator.addView(var2, var1.getPosition(), this.createLayoutParamsForTabs());
   }

   private void addViewInternal(View var1) {
      if (var1 instanceof TabItem) {
         this.addTabFromItemView((TabItem)var1);
      } else {
         throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
      }
   }

   private void animateToTab(int var1) {
      if (var1 != -1) {
         if (this.getWindowToken() != null && ViewCompat.isLaidOut(this) && !this.slidingTabIndicator.childrenNeedLayout()) {
            int var2 = this.getScrollX();
            int var3 = this.calculateScrollXForTab(var1, 0.0F);
            if (var2 != var3) {
               this.ensureScrollAnimator();
               this.scrollAnimator.setIntValues(new int[]{var2, var3});
               this.scrollAnimator.start();
            }

            this.slidingTabIndicator.animateIndicatorToPosition(var1, this.tabIndicatorAnimationDuration);
         } else {
            this.setScrollPosition(var1, 0.0F, true);
         }
      }
   }

   private void applyModeAndGravity() {
      int var1;
      if (this.mode == 0) {
         var1 = Math.max(0, this.contentInsetStart - this.tabPaddingStart);
      } else {
         var1 = 0;
      }

      ViewCompat.setPaddingRelative(this.slidingTabIndicator, var1, 0, 0, 0);
      var1 = this.mode;
      if (var1 != 0) {
         if (var1 == 1) {
            this.slidingTabIndicator.setGravity(1);
         }
      } else {
         this.slidingTabIndicator.setGravity(8388611);
      }

      this.updateTabViews(true);
   }

   private int calculateScrollXForTab(int var1, float var2) {
      int var4 = this.mode;
      int var3 = 0;
      if (var4 == 0) {
         View var6 = this.slidingTabIndicator.getChildAt(var1);
         ++var1;
         View var5;
         if (var1 < this.slidingTabIndicator.getChildCount()) {
            var5 = this.slidingTabIndicator.getChildAt(var1);
         } else {
            var5 = null;
         }

         if (var6 != null) {
            var1 = var6.getWidth();
         } else {
            var1 = 0;
         }

         if (var5 != null) {
            var3 = var5.getWidth();
         }

         var4 = var6.getLeft() + var1 / 2 - this.getWidth() / 2;
         var1 = (int)((float)(var1 + var3) * 0.5F * var2);
         if (ViewCompat.getLayoutDirection(this) == 0) {
            var1 += var4;
         } else {
            var1 = var4 - var1;
         }

         return var1;
      } else {
         return 0;
      }
   }

   private void configureTab(Tab var1, int var2) {
      var1.setPosition(var2);
      this.tabs.add(var2, var1);
      int var3 = this.tabs.size();

      while(true) {
         ++var2;
         if (var2 >= var3) {
            return;
         }

         ((Tab)this.tabs.get(var2)).setPosition(var2);
      }
   }

   private static ColorStateList createColorStateList(int var0, int var1) {
      return new ColorStateList(new int[][]{SELECTED_STATE_SET, EMPTY_STATE_SET}, new int[]{var1, var0});
   }

   private LinearLayout.LayoutParams createLayoutParamsForTabs() {
      LinearLayout.LayoutParams var1 = new LinearLayout.LayoutParams(-2, -1);
      this.updateTabViewLayoutParams(var1);
      return var1;
   }

   private TabView createTabView(Tab var1) {
      Pools.Pool var2 = this.tabViewPool;
      TabView var4;
      if (var2 != null) {
         var4 = (TabView)var2.acquire();
      } else {
         var4 = null;
      }

      TabView var3 = var4;
      if (var4 == null) {
         var3 = new TabView(this, this.getContext());
      }

      var3.setTab(var1);
      var3.setFocusable(true);
      var3.setMinimumWidth(this.getTabMinWidth());
      if (TextUtils.isEmpty(var1.contentDesc)) {
         var3.setContentDescription(var1.text);
      } else {
         var3.setContentDescription(var1.contentDesc);
      }

      return var3;
   }

   private void dispatchTabReselected(Tab var1) {
      for(int var2 = this.selectedListeners.size() - 1; var2 >= 0; --var2) {
         ((BaseOnTabSelectedListener)this.selectedListeners.get(var2)).onTabReselected(var1);
      }

   }

   private void dispatchTabSelected(Tab var1) {
      for(int var2 = this.selectedListeners.size() - 1; var2 >= 0; --var2) {
         ((BaseOnTabSelectedListener)this.selectedListeners.get(var2)).onTabSelected(var1);
      }

   }

   private void dispatchTabUnselected(Tab var1) {
      for(int var2 = this.selectedListeners.size() - 1; var2 >= 0; --var2) {
         ((BaseOnTabSelectedListener)this.selectedListeners.get(var2)).onTabUnselected(var1);
      }

   }

   private void ensureScrollAnimator() {
      if (this.scrollAnimator == null) {
         ValueAnimator var1 = new ValueAnimator();
         this.scrollAnimator = var1;
         var1.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
         this.scrollAnimator.setDuration((long)this.tabIndicatorAnimationDuration);
         this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            final TabLayout this$0;

            {
               this.this$0 = var1;
            }

            public void onAnimationUpdate(ValueAnimator var1) {
               this.this$0.scrollTo((Integer)var1.getAnimatedValue(), 0);
            }
         });
      }

   }

   private int getDefaultHeight() {
      int var4 = this.tabs.size();
      boolean var3 = false;
      int var1 = 0;

      boolean var2;
      while(true) {
         var2 = var3;
         if (var1 >= var4) {
            break;
         }

         Tab var5 = (Tab)this.tabs.get(var1);
         if (var5 != null && var5.getIcon() != null && !TextUtils.isEmpty(var5.getText())) {
            var2 = true;
            break;
         }

         ++var1;
      }

      byte var6;
      if (var2 && !this.inlineLabel) {
         var6 = 72;
      } else {
         var6 = 48;
      }

      return var6;
   }

   private int getTabMinWidth() {
      int var1 = this.requestedTabMinWidth;
      if (var1 != -1) {
         return var1;
      } else {
         if (this.mode == 0) {
            var1 = this.scrollableTabMinWidth;
         } else {
            var1 = 0;
         }

         return var1;
      }
   }

   private int getTabScrollRange() {
      return Math.max(0, this.slidingTabIndicator.getWidth() - this.getWidth() - this.getPaddingLeft() - this.getPaddingRight());
   }

   private void removeTabViewAt(int var1) {
      TabView var2 = (TabView)this.slidingTabIndicator.getChildAt(var1);
      this.slidingTabIndicator.removeViewAt(var1);
      if (var2 != null) {
         var2.reset();
         this.tabViewPool.release(var2);
      }

      this.requestLayout();
   }

   private void setSelectedTabView(int var1) {
      int var3 = this.slidingTabIndicator.getChildCount();
      if (var1 < var3) {
         for(int var2 = 0; var2 < var3; ++var2) {
            View var6 = this.slidingTabIndicator.getChildAt(var2);
            boolean var5 = true;
            boolean var4;
            if (var2 == var1) {
               var4 = true;
            } else {
               var4 = false;
            }

            var6.setSelected(var4);
            if (var2 == var1) {
               var4 = var5;
            } else {
               var4 = false;
            }

            var6.setActivated(var4);
         }
      }

   }

   private void setupWithViewPager(ViewPager var1, boolean var2, boolean var3) {
      ViewPager var5 = this.viewPager;
      if (var5 != null) {
         TabLayoutOnPageChangeListener var4 = this.pageChangeListener;
         if (var4 != null) {
            var5.removeOnPageChangeListener(var4);
         }

         AdapterChangeListener var8 = this.adapterChangeListener;
         if (var8 != null) {
            this.viewPager.removeOnAdapterChangeListener(var8);
         }
      }

      BaseOnTabSelectedListener var9 = this.currentVpSelectedListener;
      if (var9 != null) {
         this.removeOnTabSelectedListener(var9);
         this.currentVpSelectedListener = null;
      }

      if (var1 != null) {
         this.viewPager = var1;
         if (this.pageChangeListener == null) {
            this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
         }

         this.pageChangeListener.reset();
         var1.addOnPageChangeListener(this.pageChangeListener);
         ViewPagerOnTabSelectedListener var6 = new ViewPagerOnTabSelectedListener(var1);
         this.currentVpSelectedListener = var6;
         this.addOnTabSelectedListener(var6);
         PagerAdapter var7 = var1.getAdapter();
         if (var7 != null) {
            this.setPagerAdapter(var7, var2);
         }

         if (this.adapterChangeListener == null) {
            this.adapterChangeListener = new AdapterChangeListener(this);
         }

         this.adapterChangeListener.setAutoRefresh(var2);
         var1.addOnAdapterChangeListener(this.adapterChangeListener);
         this.setScrollPosition(var1.getCurrentItem(), 0.0F, true);
      } else {
         this.viewPager = null;
         this.setPagerAdapter((PagerAdapter)null, false);
      }

      this.setupViewPagerImplicitly = var3;
   }

   private void updateAllTabs() {
      int var2 = this.tabs.size();

      for(int var1 = 0; var1 < var2; ++var1) {
         ((Tab)this.tabs.get(var1)).updateView();
      }

   }

   private void updateTabViewLayoutParams(LinearLayout.LayoutParams var1) {
      if (this.mode == 1 && this.tabGravity == 0) {
         var1.width = 0;
         var1.weight = 1.0F;
      } else {
         var1.width = -2;
         var1.weight = 0.0F;
      }

   }

   public void addOnTabSelectedListener(BaseOnTabSelectedListener var1) {
      if (!this.selectedListeners.contains(var1)) {
         this.selectedListeners.add(var1);
      }

   }

   public void addTab(Tab var1) {
      this.addTab(var1, this.tabs.isEmpty());
   }

   public void addTab(Tab var1, int var2) {
      this.addTab(var1, var2, this.tabs.isEmpty());
   }

   public void addTab(Tab var1, int var2, boolean var3) {
      if (var1.parent == this) {
         this.configureTab(var1, var2);
         this.addTabView(var1);
         if (var3) {
            var1.select();
         }

      } else {
         throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
      }
   }

   public void addTab(Tab var1, boolean var2) {
      this.addTab(var1, this.tabs.size(), var2);
   }

   public void addView(View var1) {
      this.addViewInternal(var1);
   }

   public void addView(View var1, int var2) {
      this.addViewInternal(var1);
   }

   public void addView(View var1, int var2, ViewGroup.LayoutParams var3) {
      this.addViewInternal(var1);
   }

   public void addView(View var1, ViewGroup.LayoutParams var2) {
      this.addViewInternal(var1);
   }

   public void clearOnTabSelectedListeners() {
      this.selectedListeners.clear();
   }

   protected Tab createTabFromPool() {
      Tab var2 = (Tab)tabPool.acquire();
      Tab var1 = var2;
      if (var2 == null) {
         var1 = new Tab();
      }

      return var1;
   }

   int dpToPx(int var1) {
      return Math.round(this.getResources().getDisplayMetrics().density * (float)var1);
   }

   public FrameLayout.LayoutParams generateLayoutParams(AttributeSet var1) {
      return this.generateDefaultLayoutParams();
   }

   public int getSelectedTabPosition() {
      Tab var2 = this.selectedTab;
      int var1;
      if (var2 != null) {
         var1 = var2.getPosition();
      } else {
         var1 = -1;
      }

      return var1;
   }

   public Tab getTabAt(int var1) {
      Tab var2;
      if (var1 >= 0 && var1 < this.getTabCount()) {
         var2 = (Tab)this.tabs.get(var1);
      } else {
         var2 = null;
      }

      return var2;
   }

   public int getTabCount() {
      return this.tabs.size();
   }

   public int getTabGravity() {
      return this.tabGravity;
   }

   public ColorStateList getTabIconTint() {
      return this.tabIconTint;
   }

   public int getTabIndicatorGravity() {
      return this.tabIndicatorGravity;
   }

   int getTabMaxWidth() {
      return this.tabMaxWidth;
   }

   public int getTabMode() {
      return this.mode;
   }

   public ColorStateList getTabRippleColor() {
      return this.tabRippleColorStateList;
   }

   public Drawable getTabSelectedIndicator() {
      return this.tabSelectedIndicator;
   }

   public ColorStateList getTabTextColors() {
      return this.tabTextColors;
   }

   public boolean hasUnboundedRipple() {
      return this.unboundedRipple;
   }

   public boolean isInlineLabel() {
      return this.inlineLabel;
   }

   public boolean isTabIndicatorFullWidth() {
      return this.tabIndicatorFullWidth;
   }

   public Tab newTab() {
      Tab var1 = this.createTabFromPool();
      var1.parent = this;
      var1.view = this.createTabView(var1);
      return var1;
   }

   protected void onAttachedToWindow() {
      super.onAttachedToWindow();
      if (this.viewPager == null) {
         ViewParent var1 = this.getParent();
         if (var1 instanceof ViewPager) {
            this.setupWithViewPager((ViewPager)var1, true, true);
         }
      }

   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (this.setupViewPagerImplicitly) {
         this.setupWithViewPager((ViewPager)null);
         this.setupViewPagerImplicitly = false;
      }

   }

   protected void onDraw(Canvas var1) {
      for(int var2 = 0; var2 < this.slidingTabIndicator.getChildCount(); ++var2) {
         View var3 = this.slidingTabIndicator.getChildAt(var2);
         if (var3 instanceof TabView) {
            ((TabView)var3).drawBackground(var1);
         }
      }

      super.onDraw(var1);
   }

   protected void onMeasure(int var1, int var2) {
      int var3 = this.dpToPx(this.getDefaultHeight()) + this.getPaddingTop() + this.getPaddingBottom();
      int var4 = MeasureSpec.getMode(var2);
      if (var4 != Integer.MIN_VALUE) {
         if (var4 == 0) {
            var2 = MeasureSpec.makeMeasureSpec(var3, 1073741824);
         }
      } else {
         var2 = MeasureSpec.makeMeasureSpec(Math.min(var3, MeasureSpec.getSize(var2)), 1073741824);
      }

      var4 = MeasureSpec.getSize(var1);
      if (MeasureSpec.getMode(var1) != 0) {
         var3 = this.requestedTabMaxWidth;
         if (var3 <= 0) {
            var3 = var4 - this.dpToPx(56);
         }

         this.tabMaxWidth = var3;
      }

      super.onMeasure(var1, var2);
      var3 = this.getChildCount();
      boolean var6 = true;
      if (var3 == 1) {
         View var5;
         label39: {
            boolean var7 = false;
            var5 = this.getChildAt(0);
            var4 = this.mode;
            if (var4 != 0) {
               if (var4 != 1) {
                  var6 = var7;
                  break label39;
               }

               if (var5.getMeasuredWidth() != this.getMeasuredWidth()) {
                  break label39;
               }
            } else if (var5.getMeasuredWidth() < this.getMeasuredWidth()) {
               break label39;
            }

            var6 = false;
         }

         if (var6) {
            var1 = getChildMeasureSpec(var2, this.getPaddingTop() + this.getPaddingBottom(), var5.getLayoutParams().height);
            var5.measure(MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 1073741824), var1);
         }
      }

   }

   void populateFromPagerAdapter() {
      this.removeAllTabs();
      PagerAdapter var3 = this.pagerAdapter;
      if (var3 != null) {
         int var2 = var3.getCount();

         int var1;
         for(var1 = 0; var1 < var2; ++var1) {
            this.addTab(this.newTab().setText(this.pagerAdapter.getPageTitle(var1)), false);
         }

         ViewPager var4 = this.viewPager;
         if (var4 != null && var2 > 0) {
            var1 = var4.getCurrentItem();
            if (var1 != this.getSelectedTabPosition() && var1 < this.getTabCount()) {
               this.selectTab(this.getTabAt(var1));
            }
         }
      }

   }

   protected boolean releaseFromTabPool(Tab var1) {
      return tabPool.release(var1);
   }

   public void removeAllTabs() {
      for(int var1 = this.slidingTabIndicator.getChildCount() - 1; var1 >= 0; --var1) {
         this.removeTabViewAt(var1);
      }

      Iterator var2 = this.tabs.iterator();

      while(var2.hasNext()) {
         Tab var3 = (Tab)var2.next();
         var2.remove();
         var3.reset();
         this.releaseFromTabPool(var3);
      }

      this.selectedTab = null;
   }

   public void removeOnTabSelectedListener(BaseOnTabSelectedListener var1) {
      this.selectedListeners.remove(var1);
   }

   public void removeTab(Tab var1) {
      if (var1.parent == this) {
         this.removeTabAt(var1.getPosition());
      } else {
         throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
      }
   }

   public void removeTabAt(int var1) {
      Tab var5 = this.selectedTab;
      int var2;
      if (var5 != null) {
         var2 = var5.getPosition();
      } else {
         var2 = 0;
      }

      this.removeTabViewAt(var1);
      var5 = (Tab)this.tabs.remove(var1);
      if (var5 != null) {
         var5.reset();
         this.releaseFromTabPool(var5);
      }

      int var4 = this.tabs.size();

      for(int var3 = var1; var3 < var4; ++var3) {
         ((Tab)this.tabs.get(var3)).setPosition(var3);
      }

      if (var2 == var1) {
         if (this.tabs.isEmpty()) {
            var5 = null;
         } else {
            var5 = (Tab)this.tabs.get(Math.max(0, var1 - 1));
         }

         this.selectTab(var5);
      }

   }

   void selectTab(Tab var1) {
      this.selectTab(var1, true);
   }

   void selectTab(Tab var1, boolean var2) {
      Tab var4 = this.selectedTab;
      if (var4 == var1) {
         if (var4 != null) {
            this.dispatchTabReselected(var1);
            this.animateToTab(var1.getPosition());
         }
      } else {
         int var3;
         if (var1 != null) {
            var3 = var1.getPosition();
         } else {
            var3 = -1;
         }

         if (var2) {
            if ((var4 == null || var4.getPosition() == -1) && var3 != -1) {
               this.setScrollPosition(var3, 0.0F, true);
            } else {
               this.animateToTab(var3);
            }

            if (var3 != -1) {
               this.setSelectedTabView(var3);
            }
         }

         this.selectedTab = var1;
         if (var4 != null) {
            this.dispatchTabUnselected(var4);
         }

         if (var1 != null) {
            this.dispatchTabSelected(var1);
         }
      }

   }

   public void setInlineLabel(boolean var1) {
      if (this.inlineLabel != var1) {
         this.inlineLabel = var1;

         for(int var2 = 0; var2 < this.slidingTabIndicator.getChildCount(); ++var2) {
            View var3 = this.slidingTabIndicator.getChildAt(var2);
            if (var3 instanceof TabView) {
               ((TabView)var3).updateOrientation();
            }
         }

         this.applyModeAndGravity();
      }

   }

   public void setInlineLabelResource(int var1) {
      this.setInlineLabel(this.getResources().getBoolean(var1));
   }

   @Deprecated
   public void setOnTabSelectedListener(BaseOnTabSelectedListener var1) {
      BaseOnTabSelectedListener var2 = this.selectedListener;
      if (var2 != null) {
         this.removeOnTabSelectedListener(var2);
      }

      this.selectedListener = var1;
      if (var1 != null) {
         this.addOnTabSelectedListener(var1);
      }

   }

   void setPagerAdapter(PagerAdapter var1, boolean var2) {
      PagerAdapter var4 = this.pagerAdapter;
      if (var4 != null) {
         DataSetObserver var3 = this.pagerAdapterObserver;
         if (var3 != null) {
            var4.unregisterDataSetObserver(var3);
         }
      }

      this.pagerAdapter = var1;
      if (var2 && var1 != null) {
         if (this.pagerAdapterObserver == null) {
            this.pagerAdapterObserver = new PagerAdapterObserver(this);
         }

         var1.registerDataSetObserver(this.pagerAdapterObserver);
      }

      this.populateFromPagerAdapter();
   }

   void setScrollAnimatorListener(Animator.AnimatorListener var1) {
      this.ensureScrollAnimator();
      this.scrollAnimator.addListener(var1);
   }

   public void setScrollPosition(int var1, float var2, boolean var3) {
      this.setScrollPosition(var1, var2, var3, true);
   }

   void setScrollPosition(int var1, float var2, boolean var3, boolean var4) {
      int var5 = Math.round((float)var1 + var2);
      if (var5 >= 0 && var5 < this.slidingTabIndicator.getChildCount()) {
         if (var4) {
            this.slidingTabIndicator.setIndicatorPositionFromTabPosition(var1, var2);
         }

         ValueAnimator var6 = this.scrollAnimator;
         if (var6 != null && var6.isRunning()) {
            this.scrollAnimator.cancel();
         }

         this.scrollTo(this.calculateScrollXForTab(var1, var2), 0);
         if (var3) {
            this.setSelectedTabView(var5);
         }
      }

   }

   public void setSelectedTabIndicator(int var1) {
      if (var1 != 0) {
         this.setSelectedTabIndicator(AppCompatResources.getDrawable(this.getContext(), var1));
      } else {
         this.setSelectedTabIndicator((Drawable)null);
      }

   }

   public void setSelectedTabIndicator(Drawable var1) {
      if (this.tabSelectedIndicator != var1) {
         this.tabSelectedIndicator = var1;
         ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
      }

   }

   public void setSelectedTabIndicatorColor(int var1) {
      this.slidingTabIndicator.setSelectedIndicatorColor(var1);
   }

   public void setSelectedTabIndicatorGravity(int var1) {
      if (this.tabIndicatorGravity != var1) {
         this.tabIndicatorGravity = var1;
         ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
      }

   }

   @Deprecated
   public void setSelectedTabIndicatorHeight(int var1) {
      this.slidingTabIndicator.setSelectedIndicatorHeight(var1);
   }

   public void setTabGravity(int var1) {
      if (this.tabGravity != var1) {
         this.tabGravity = var1;
         this.applyModeAndGravity();
      }

   }

   public void setTabIconTint(ColorStateList var1) {
      if (this.tabIconTint != var1) {
         this.tabIconTint = var1;
         this.updateAllTabs();
      }

   }

   public void setTabIconTintResource(int var1) {
      this.setTabIconTint(AppCompatResources.getColorStateList(this.getContext(), var1));
   }

   public void setTabIndicatorFullWidth(boolean var1) {
      this.tabIndicatorFullWidth = var1;
      ViewCompat.postInvalidateOnAnimation(this.slidingTabIndicator);
   }

   public void setTabMode(int var1) {
      if (var1 != this.mode) {
         this.mode = var1;
         this.applyModeAndGravity();
      }

   }

   public void setTabRippleColor(ColorStateList var1) {
      if (this.tabRippleColorStateList != var1) {
         this.tabRippleColorStateList = var1;

         for(int var2 = 0; var2 < this.slidingTabIndicator.getChildCount(); ++var2) {
            View var3 = this.slidingTabIndicator.getChildAt(var2);
            if (var3 instanceof TabView) {
               ((TabView)var3).updateBackgroundDrawable(this.getContext());
            }
         }
      }

   }

   public void setTabRippleColorResource(int var1) {
      this.setTabRippleColor(AppCompatResources.getColorStateList(this.getContext(), var1));
   }

   public void setTabTextColors(int var1, int var2) {
      this.setTabTextColors(createColorStateList(var1, var2));
   }

   public void setTabTextColors(ColorStateList var1) {
      if (this.tabTextColors != var1) {
         this.tabTextColors = var1;
         this.updateAllTabs();
      }

   }

   @Deprecated
   public void setTabsFromPagerAdapter(PagerAdapter var1) {
      this.setPagerAdapter(var1, false);
   }

   public void setUnboundedRipple(boolean var1) {
      if (this.unboundedRipple != var1) {
         this.unboundedRipple = var1;

         for(int var2 = 0; var2 < this.slidingTabIndicator.getChildCount(); ++var2) {
            View var3 = this.slidingTabIndicator.getChildAt(var2);
            if (var3 instanceof TabView) {
               ((TabView)var3).updateBackgroundDrawable(this.getContext());
            }
         }
      }

   }

   public void setUnboundedRippleResource(int var1) {
      this.setUnboundedRipple(this.getResources().getBoolean(var1));
   }

   public void setupWithViewPager(ViewPager var1) {
      this.setupWithViewPager(var1, true);
   }

   public void setupWithViewPager(ViewPager var1, boolean var2) {
      this.setupWithViewPager(var1, var2, false);
   }

   public boolean shouldDelayChildPressedState() {
      boolean var1;
      if (this.getTabScrollRange() > 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   void updateTabViews(boolean var1) {
      for(int var2 = 0; var2 < this.slidingTabIndicator.getChildCount(); ++var2) {
         View var3 = this.slidingTabIndicator.getChildAt(var2);
         var3.setMinimumWidth(this.getTabMinWidth());
         this.updateTabViewLayoutParams((LinearLayout.LayoutParams)var3.getLayoutParams());
         if (var1) {
            var3.requestLayout();
         }
      }

   }

   private class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
      private boolean autoRefresh;
      final TabLayout this$0;

      AdapterChangeListener(TabLayout var1) {
         this.this$0 = var1;
      }

      public void onAdapterChanged(ViewPager var1, PagerAdapter var2, PagerAdapter var3) {
         if (this.this$0.viewPager == var1) {
            this.this$0.setPagerAdapter(var3, this.autoRefresh);
         }

      }

      void setAutoRefresh(boolean var1) {
         this.autoRefresh = var1;
      }
   }

   public interface BaseOnTabSelectedListener {
      void onTabReselected(Tab var1);

      void onTabSelected(Tab var1);

      void onTabUnselected(Tab var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface Mode {
   }

   public interface OnTabSelectedListener extends BaseOnTabSelectedListener {
   }

   private class PagerAdapterObserver extends DataSetObserver {
      final TabLayout this$0;

      PagerAdapterObserver(TabLayout var1) {
         this.this$0 = var1;
      }

      public void onChanged() {
         this.this$0.populateFromPagerAdapter();
      }

      public void onInvalidated() {
         this.this$0.populateFromPagerAdapter();
      }
   }

   private class SlidingTabIndicator extends LinearLayout {
      private final GradientDrawable defaultSelectionIndicator;
      private ValueAnimator indicatorAnimator;
      private int indicatorLeft;
      private int indicatorRight;
      private int layoutDirection;
      private int selectedIndicatorHeight;
      private final Paint selectedIndicatorPaint;
      int selectedPosition;
      float selectionOffset;
      final TabLayout this$0;

      SlidingTabIndicator(TabLayout var1, Context var2) {
         super(var2);
         this.this$0 = var1;
         this.selectedPosition = -1;
         this.layoutDirection = -1;
         this.indicatorLeft = -1;
         this.indicatorRight = -1;
         this.setWillNotDraw(false);
         this.selectedIndicatorPaint = new Paint();
         this.defaultSelectionIndicator = new GradientDrawable();
      }

      private void calculateTabViewContentBounds(TabView var1, RectF var2) {
         int var4 = var1.getContentWidth();
         int var3 = var4;
         if (var4 < this.this$0.dpToPx(24)) {
            var3 = this.this$0.dpToPx(24);
         }

         var4 = (var1.getLeft() + var1.getRight()) / 2;
         var3 /= 2;
         var2.set((float)(var4 - var3), 0.0F, (float)(var4 + var3), 0.0F);
      }

      private void updateIndicatorPosition() {
         View var8 = this.getChildAt(this.selectedPosition);
         int var4;
         int var5;
         if (var8 != null && var8.getWidth() > 0) {
            var5 = var8.getLeft();
            var4 = var8.getRight();
            int var3 = var5;
            int var2 = var4;
            if (!this.this$0.tabIndicatorFullWidth) {
               var3 = var5;
               var2 = var4;
               if (var8 instanceof TabView) {
                  this.calculateTabViewContentBounds((TabView)var8, this.this$0.tabViewContentBounds);
                  var3 = (int)this.this$0.tabViewContentBounds.left;
                  var2 = (int)this.this$0.tabViewContentBounds.right;
               }
            }

            var5 = var3;
            var4 = var2;
            if (this.selectionOffset > 0.0F) {
               var5 = var3;
               var4 = var2;
               if (this.selectedPosition < this.getChildCount() - 1) {
                  var8 = this.getChildAt(this.selectedPosition + 1);
                  int var6 = var8.getLeft();
                  int var7 = var8.getRight();
                  var5 = var6;
                  var4 = var7;
                  if (!this.this$0.tabIndicatorFullWidth) {
                     var5 = var6;
                     var4 = var7;
                     if (var8 instanceof TabView) {
                        this.calculateTabViewContentBounds((TabView)var8, this.this$0.tabViewContentBounds);
                        var5 = (int)this.this$0.tabViewContentBounds.left;
                        var4 = (int)this.this$0.tabViewContentBounds.right;
                     }
                  }

                  float var1 = this.selectionOffset;
                  var5 = (int)((float)var5 * var1 + (1.0F - var1) * (float)var3);
                  var4 = (int)((float)var4 * var1 + (1.0F - var1) * (float)var2);
               }
            }
         } else {
            var5 = -1;
            var4 = -1;
         }

         this.setIndicatorPosition(var5, var4);
      }

      void animateIndicatorToPosition(int var1, int var2) {
         ValueAnimator var7 = this.indicatorAnimator;
         if (var7 != null && var7.isRunning()) {
            this.indicatorAnimator.cancel();
         }

         View var8 = this.getChildAt(var1);
         if (var8 == null) {
            this.updateIndicatorPosition();
         } else {
            int var5 = var8.getLeft();
            int var6 = var8.getRight();
            int var4 = var5;
            int var3 = var6;
            if (!this.this$0.tabIndicatorFullWidth) {
               var4 = var5;
               var3 = var6;
               if (var8 instanceof TabView) {
                  this.calculateTabViewContentBounds((TabView)var8, this.this$0.tabViewContentBounds);
                  var4 = (int)this.this$0.tabViewContentBounds.left;
                  var3 = (int)this.this$0.tabViewContentBounds.right;
               }
            }

            var5 = this.indicatorLeft;
            var6 = this.indicatorRight;
            if (var5 != var4 || var6 != var3) {
               var7 = new ValueAnimator();
               this.indicatorAnimator = var7;
               var7.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
               var7.setDuration((long)var2);
               var7.setFloatValues(new float[]{0.0F, 1.0F});
               var7.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this, var5, var4, var6, var3) {
                  final SlidingTabIndicator this$1;
                  final int val$finalTargetLeft;
                  final int val$finalTargetRight;
                  final int val$startLeft;
                  final int val$startRight;

                  {
                     this.this$1 = var1;
                     this.val$startLeft = var2;
                     this.val$finalTargetLeft = var3;
                     this.val$startRight = var4;
                     this.val$finalTargetRight = var5;
                  }

                  public void onAnimationUpdate(ValueAnimator var1) {
                     float var2 = var1.getAnimatedFraction();
                     this.this$1.setIndicatorPosition(AnimationUtils.lerp(this.val$startLeft, this.val$finalTargetLeft, var2), AnimationUtils.lerp(this.val$startRight, this.val$finalTargetRight, var2));
                  }
               });
               var7.addListener(new AnimatorListenerAdapter(this, var1) {
                  final SlidingTabIndicator this$1;
                  final int val$position;

                  {
                     this.this$1 = var1;
                     this.val$position = var2;
                  }

                  public void onAnimationEnd(Animator var1) {
                     this.this$1.selectedPosition = this.val$position;
                     this.this$1.selectionOffset = 0.0F;
                  }
               });
               var7.start();
            }

         }
      }

      boolean childrenNeedLayout() {
         int var2 = this.getChildCount();

         for(int var1 = 0; var1 < var2; ++var1) {
            if (this.getChildAt(var1).getWidth() <= 0) {
               return true;
            }
         }

         return false;
      }

      public void draw(Canvas var1) {
         Drawable var6 = this.this$0.tabSelectedIndicator;
         int var4 = 0;
         int var2;
         if (var6 != null) {
            var2 = this.this$0.tabSelectedIndicator.getIntrinsicHeight();
         } else {
            var2 = 0;
         }

         int var3 = this.selectedIndicatorHeight;
         if (var3 >= 0) {
            var2 = var3;
         }

         int var5 = this.this$0.tabIndicatorGravity;
         if (var5 != 0) {
            if (var5 != 1) {
               var3 = var4;
               if (var5 != 2) {
                  if (var5 != 3) {
                     var2 = 0;
                     var3 = var4;
                  } else {
                     var2 = this.getHeight();
                     var3 = var4;
                  }
               }
            } else {
               var3 = (this.getHeight() - var2) / 2;
               var2 = (this.getHeight() + var2) / 2;
            }
         } else {
            var3 = this.getHeight() - var2;
            var2 = this.getHeight();
         }

         var4 = this.indicatorLeft;
         if (var4 >= 0 && this.indicatorRight > var4) {
            Object var7;
            if (this.this$0.tabSelectedIndicator != null) {
               var7 = this.this$0.tabSelectedIndicator;
            } else {
               var7 = this.defaultSelectionIndicator;
            }

            var6 = DrawableCompat.wrap((Drawable)var7);
            var6.setBounds(this.indicatorLeft, var3, this.indicatorRight, var2);
            if (this.selectedIndicatorPaint != null) {
               if (VERSION.SDK_INT == 21) {
                  var6.setColorFilter(this.selectedIndicatorPaint.getColor(), android.graphics.PorterDuff.Mode.SRC_IN);
               } else {
                  DrawableCompat.setTint(var6, this.selectedIndicatorPaint.getColor());
               }
            }

            var6.draw(var1);
         }

         super.draw(var1);
      }

      float getIndicatorPosition() {
         return (float)this.selectedPosition + this.selectionOffset;
      }

      protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
         super.onLayout(var1, var2, var3, var4, var5);
         ValueAnimator var8 = this.indicatorAnimator;
         if (var8 != null && var8.isRunning()) {
            this.indicatorAnimator.cancel();
            long var6 = this.indicatorAnimator.getDuration();
            this.animateIndicatorToPosition(this.selectedPosition, Math.round((1.0F - this.indicatorAnimator.getAnimatedFraction()) * (float)var6));
         } else {
            this.updateIndicatorPosition();
         }

      }

      protected void onMeasure(int var1, int var2) {
         super.onMeasure(var1, var2);
         if (MeasureSpec.getMode(var1) == 1073741824) {
            int var3 = this.this$0.mode;
            boolean var7 = true;
            if (var3 == 1 && this.this$0.tabGravity == 1) {
               int var8 = this.getChildCount();
               byte var6 = 0;
               var3 = 0;

               int var4;
               int var5;
               for(var4 = 0; var3 < var8; var4 = var5) {
                  View var9 = this.getChildAt(var3);
                  var5 = var4;
                  if (var9.getVisibility() == 0) {
                     var5 = Math.max(var4, var9.getMeasuredWidth());
                  }

                  ++var3;
               }

               if (var4 <= 0) {
                  return;
               }

               var3 = this.this$0.dpToPx(16);
               boolean var10;
               if (var4 * var8 > this.getMeasuredWidth() - var3 * 2) {
                  this.this$0.tabGravity = 0;
                  this.this$0.updateTabViews(false);
                  var10 = var7;
               } else {
                  var10 = false;

                  for(var5 = var6; var5 < var8; ++var5) {
                     LinearLayout.LayoutParams var11 = (LinearLayout.LayoutParams)this.getChildAt(var5).getLayoutParams();
                     if (var11.width != var4 || var11.weight != 0.0F) {
                        var11.width = var4;
                        var11.weight = 0.0F;
                        var10 = true;
                     }
                  }
               }

               if (var10) {
                  super.onMeasure(var1, var2);
               }
            }

         }
      }

      public void onRtlPropertiesChanged(int var1) {
         super.onRtlPropertiesChanged(var1);
         if (VERSION.SDK_INT < 23 && this.layoutDirection != var1) {
            this.requestLayout();
            this.layoutDirection = var1;
         }

      }

      void setIndicatorPosition(int var1, int var2) {
         if (var1 != this.indicatorLeft || var2 != this.indicatorRight) {
            this.indicatorLeft = var1;
            this.indicatorRight = var2;
            ViewCompat.postInvalidateOnAnimation(this);
         }

      }

      void setIndicatorPositionFromTabPosition(int var1, float var2) {
         ValueAnimator var3 = this.indicatorAnimator;
         if (var3 != null && var3.isRunning()) {
            this.indicatorAnimator.cancel();
         }

         this.selectedPosition = var1;
         this.selectionOffset = var2;
         this.updateIndicatorPosition();
      }

      void setSelectedIndicatorColor(int var1) {
         if (this.selectedIndicatorPaint.getColor() != var1) {
            this.selectedIndicatorPaint.setColor(var1);
            ViewCompat.postInvalidateOnAnimation(this);
         }

      }

      void setSelectedIndicatorHeight(int var1) {
         if (this.selectedIndicatorHeight != var1) {
            this.selectedIndicatorHeight = var1;
            ViewCompat.postInvalidateOnAnimation(this);
         }

      }
   }

   public static class Tab {
      public static final int INVALID_POSITION = -1;
      private CharSequence contentDesc;
      private View customView;
      private Drawable icon;
      public TabLayout parent;
      private int position = -1;
      private Object tag;
      private CharSequence text;
      public TabView view;

      public CharSequence getContentDescription() {
         TabView var1 = this.view;
         CharSequence var2;
         if (var1 == null) {
            var2 = null;
         } else {
            var2 = var1.getContentDescription();
         }

         return var2;
      }

      public View getCustomView() {
         return this.customView;
      }

      public Drawable getIcon() {
         return this.icon;
      }

      public int getPosition() {
         return this.position;
      }

      public Object getTag() {
         return this.tag;
      }

      public CharSequence getText() {
         return this.text;
      }

      public boolean isSelected() {
         TabLayout var2 = this.parent;
         if (var2 != null) {
            boolean var1;
            if (var2.getSelectedTabPosition() == this.position) {
               var1 = true;
            } else {
               var1 = false;
            }

            return var1;
         } else {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         }
      }

      void reset() {
         this.parent = null;
         this.view = null;
         this.tag = null;
         this.icon = null;
         this.text = null;
         this.contentDesc = null;
         this.position = -1;
         this.customView = null;
      }

      public void select() {
         TabLayout var1 = this.parent;
         if (var1 != null) {
            var1.selectTab(this);
         } else {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         }
      }

      public Tab setContentDescription(int var1) {
         TabLayout var2 = this.parent;
         if (var2 != null) {
            return this.setContentDescription(var2.getResources().getText(var1));
         } else {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         }
      }

      public Tab setContentDescription(CharSequence var1) {
         this.contentDesc = var1;
         this.updateView();
         return this;
      }

      public Tab setCustomView(int var1) {
         return this.setCustomView(LayoutInflater.from(this.view.getContext()).inflate(var1, this.view, false));
      }

      public Tab setCustomView(View var1) {
         this.customView = var1;
         this.updateView();
         return this;
      }

      public Tab setIcon(int var1) {
         TabLayout var2 = this.parent;
         if (var2 != null) {
            return this.setIcon(AppCompatResources.getDrawable(var2.getContext(), var1));
         } else {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         }
      }

      public Tab setIcon(Drawable var1) {
         this.icon = var1;
         this.updateView();
         return this;
      }

      void setPosition(int var1) {
         this.position = var1;
      }

      public Tab setTag(Object var1) {
         this.tag = var1;
         return this;
      }

      public Tab setText(int var1) {
         TabLayout var2 = this.parent;
         if (var2 != null) {
            return this.setText(var2.getResources().getText(var1));
         } else {
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
         }
      }

      public Tab setText(CharSequence var1) {
         if (TextUtils.isEmpty(this.contentDesc) && !TextUtils.isEmpty(var1)) {
            this.view.setContentDescription(var1);
         }

         this.text = var1;
         this.updateView();
         return this;
      }

      void updateView() {
         TabView var1 = this.view;
         if (var1 != null) {
            var1.update();
         }

      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface TabGravity {
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface TabIndicatorGravity {
   }

   public static class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
      private int previousScrollState;
      private int scrollState;
      private final WeakReference tabLayoutRef;

      public TabLayoutOnPageChangeListener(TabLayout var1) {
         this.tabLayoutRef = new WeakReference(var1);
      }

      public void onPageScrollStateChanged(int var1) {
         this.previousScrollState = this.scrollState;
         this.scrollState = var1;
      }

      public void onPageScrolled(int var1, float var2, int var3) {
         TabLayout var6 = (TabLayout)this.tabLayoutRef.get();
         if (var6 != null) {
            var3 = this.scrollState;
            boolean var5 = false;
            boolean var4;
            if (var3 == 2 && this.previousScrollState != 1) {
               var4 = false;
            } else {
               var4 = true;
            }

            if (var3 != 2 || this.previousScrollState != 0) {
               var5 = true;
            }

            var6.setScrollPosition(var1, var2, var4, var5);
         }

      }

      public void onPageSelected(int var1) {
         TabLayout var4 = (TabLayout)this.tabLayoutRef.get();
         if (var4 != null && var4.getSelectedTabPosition() != var1 && var1 < var4.getTabCount()) {
            int var2 = this.scrollState;
            boolean var3;
            if (var2 == 0 || var2 == 2 && this.previousScrollState == 0) {
               var3 = true;
            } else {
               var3 = false;
            }

            var4.selectTab(var4.getTabAt(var1), var3);
         }

      }

      void reset() {
         this.scrollState = 0;
         this.previousScrollState = 0;
      }
   }

   class TabView extends LinearLayout {
      private Drawable baseBackgroundDrawable;
      private ImageView customIconView;
      private TextView customTextView;
      private View customView;
      private int defaultMaxLines;
      private ImageView iconView;
      private Tab tab;
      private TextView textView;
      final TabLayout this$0;

      public TabView(TabLayout var1, Context var2) {
         super(var2);
         this.this$0 = var1;
         this.defaultMaxLines = 2;
         this.updateBackgroundDrawable(var2);
         ViewCompat.setPaddingRelative(this, var1.tabPaddingStart, var1.tabPaddingTop, var1.tabPaddingEnd, var1.tabPaddingBottom);
         this.setGravity(17);
         this.setOrientation(var1.inlineLabel ^ 1);
         this.setClickable(true);
         ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
      }

      private float approximateLineWidth(Layout var1, int var2, float var3) {
         return var1.getLineWidth(var2) * (var3 / var1.getPaint().getTextSize());
      }

      private void drawBackground(Canvas var1) {
         Drawable var2 = this.baseBackgroundDrawable;
         if (var2 != null) {
            var2.setBounds(this.getLeft(), this.getTop(), this.getRight(), this.getBottom());
            this.baseBackgroundDrawable.draw(var1);
         }

      }

      private int getContentWidth() {
         TextView var10 = this.textView;
         int var3 = 0;
         ImageView var9 = this.iconView;
         View var8 = this.customView;
         int var4 = 0;
         int var1 = 0;

         int var5;
         for(int var2 = var1; var3 < 3; var2 = var5) {
            View var11 = (new View[]{var10, var9, var8})[var3];
            int var7 = var4;
            int var6 = var1;
            var5 = var2;
            if (var11 != null) {
               var7 = var4;
               var6 = var1;
               var5 = var2;
               if (var11.getVisibility() == 0) {
                  if (var2 != 0) {
                     var1 = Math.min(var1, var11.getLeft());
                  } else {
                     var1 = var11.getLeft();
                  }

                  if (var2 != 0) {
                     var2 = Math.max(var4, var11.getRight());
                  } else {
                     var2 = var11.getRight();
                  }

                  var5 = 1;
                  var6 = var1;
                  var7 = var2;
               }
            }

            ++var3;
            var4 = var7;
            var1 = var6;
         }

         return var4 - var1;
      }

      private void updateBackgroundDrawable(Context var1) {
         int var2 = this.this$0.tabBackgroundResId;
         Object var4 = null;
         Drawable var6;
         if (var2 != 0) {
            var6 = AppCompatResources.getDrawable(var1, this.this$0.tabBackgroundResId);
            this.baseBackgroundDrawable = var6;
            if (var6 != null && var6.isStateful()) {
               this.baseBackgroundDrawable.setState(this.getDrawableState());
            }
         } else {
            this.baseBackgroundDrawable = null;
         }

         GradientDrawable var3 = new GradientDrawable();
         GradientDrawable var7 = (GradientDrawable)var3;
         var3.setColor(0);
         Object var8 = var3;
         if (this.this$0.tabRippleColorStateList != null) {
            var7 = new GradientDrawable();
            var7.setCornerRadius(1.0E-5F);
            var7.setColor(-1);
            ColorStateList var5 = RippleUtils.convertToRippleDrawableColor(this.this$0.tabRippleColorStateList);
            if (VERSION.SDK_INT >= 21) {
               if (this.this$0.unboundedRipple) {
                  var3 = null;
               }

               if (this.this$0.unboundedRipple) {
                  var7 = (GradientDrawable)var4;
               }

               var8 = new RippleDrawable(var5, var3, var7);
            } else {
               var6 = DrawableCompat.wrap(var7);
               DrawableCompat.setTintList(var6, var5);
               var8 = new LayerDrawable(new Drawable[]{var3, var6});
            }
         }

         ViewCompat.setBackground(this, (Drawable)var8);
         this.this$0.invalidate();
      }

      private void updateTextAndIcon(TextView var1, ImageView var2) {
         Tab var5 = this.tab;
         Object var7 = null;
         Drawable var11;
         if (var5 != null && var5.getIcon() != null) {
            var11 = DrawableCompat.wrap(this.tab.getIcon()).mutate();
         } else {
            var11 = null;
         }

         Tab var6 = this.tab;
         CharSequence var12;
         if (var6 != null) {
            var12 = var6.getText();
         } else {
            var12 = null;
         }

         if (var2 != null) {
            if (var11 != null) {
               var2.setImageDrawable(var11);
               var2.setVisibility(0);
               this.setVisibility(0);
            } else {
               var2.setVisibility(8);
               var2.setImageDrawable((Drawable)null);
            }
         }

         boolean var4 = TextUtils.isEmpty(var12) ^ true;
         if (var1 != null) {
            if (var4) {
               var1.setText(var12);
               var1.setVisibility(0);
               this.setVisibility(0);
            } else {
               var1.setVisibility(8);
               var1.setText((CharSequence)null);
            }
         }

         if (var2 != null) {
            ViewGroup.MarginLayoutParams var8 = (ViewGroup.MarginLayoutParams)var2.getLayoutParams();
            int var3;
            if (var4 && var2.getVisibility() == 0) {
               var3 = this.this$0.dpToPx(8);
            } else {
               var3 = 0;
            }

            if (this.this$0.inlineLabel) {
               if (var3 != MarginLayoutParamsCompat.getMarginEnd(var8)) {
                  MarginLayoutParamsCompat.setMarginEnd(var8, var3);
                  var8.bottomMargin = 0;
                  var2.setLayoutParams(var8);
                  var2.requestLayout();
               }
            } else if (var3 != var8.bottomMargin) {
               var8.bottomMargin = var3;
               MarginLayoutParamsCompat.setMarginEnd(var8, 0);
               var2.setLayoutParams(var8);
               var2.requestLayout();
            }
         }

         Tab var9 = this.tab;
         CharSequence var10;
         if (var9 != null) {
            var10 = var9.contentDesc;
         } else {
            var10 = null;
         }

         if (var4) {
            var10 = (CharSequence)var7;
         }

         TooltipCompat.setTooltipText(this, var10);
      }

      protected void drawableStateChanged() {
         super.drawableStateChanged();
         int[] var4 = this.getDrawableState();
         Drawable var3 = this.baseBackgroundDrawable;
         boolean var2 = false;
         boolean var1 = var2;
         if (var3 != null) {
            var1 = var2;
            if (var3.isStateful()) {
               var1 = false | this.baseBackgroundDrawable.setState(var4);
            }
         }

         if (var1) {
            this.invalidate();
            this.this$0.invalidate();
         }

      }

      public Tab getTab() {
         return this.tab;
      }

      public void onInitializeAccessibilityEvent(AccessibilityEvent var1) {
         super.onInitializeAccessibilityEvent(var1);
         var1.setClassName(ActionBar.Tab.class.getName());
      }

      public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo var1) {
         super.onInitializeAccessibilityNodeInfo(var1);
         var1.setClassName(ActionBar.Tab.class.getName());
      }

      public void onMeasure(int var1, int var2) {
         int var8 = MeasureSpec.getSize(var1);
         int var7 = MeasureSpec.getMode(var1);
         int var6 = this.this$0.getTabMaxWidth();
         int var5 = var1;
         if (var6 > 0) {
            label57: {
               if (var7 != 0) {
                  var5 = var1;
                  if (var8 <= var6) {
                     break label57;
                  }
               }

               var5 = MeasureSpec.makeMeasureSpec(this.this$0.tabMaxWidth, Integer.MIN_VALUE);
            }
         }

         super.onMeasure(var5, var2);
         if (this.textView != null) {
            float var4 = this.this$0.tabTextSize;
            var6 = this.defaultMaxLines;
            ImageView var10 = this.iconView;
            boolean var12 = true;
            float var3;
            if (var10 != null && var10.getVisibility() == 0) {
               var1 = 1;
               var3 = var4;
            } else {
               TextView var13 = this.textView;
               var3 = var4;
               var1 = var6;
               if (var13 != null) {
                  var3 = var4;
                  var1 = var6;
                  if (var13.getLineCount() > 1) {
                     var3 = this.this$0.tabTextMultiLineSize;
                     var1 = var6;
                  }
               }
            }

            var4 = this.textView.getTextSize();
            int var9 = this.textView.getLineCount();
            var6 = TextViewCompat.getMaxLines(this.textView);
            float var15;
            var8 = (var15 = var3 - var4) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
            if (var8 != 0 || var6 >= 0 && var1 != var6) {
               boolean var11 = var12;
               if (this.this$0.mode == 1) {
                  var11 = var12;
                  if (var8 > 0) {
                     var11 = var12;
                     if (var9 == 1) {
                        label65: {
                           Layout var14 = this.textView.getLayout();
                           if (var14 != null) {
                              var11 = var12;
                              if (!(this.approximateLineWidth(var14, 0, var3) > (float)(this.getMeasuredWidth() - this.getPaddingLeft() - this.getPaddingRight()))) {
                                 break label65;
                              }
                           }

                           var11 = false;
                        }
                     }
                  }
               }

               if (var11) {
                  this.textView.setTextSize(0, var3);
                  this.textView.setMaxLines(var1);
                  super.onMeasure(var5, var2);
               }
            }
         }

      }

      public boolean performClick() {
         boolean var2 = super.performClick();
         boolean var1 = var2;
         if (this.tab != null) {
            if (!var2) {
               this.playSoundEffect(0);
            }

            this.tab.select();
            var1 = true;
         }

         return var1;
      }

      void reset() {
         this.setTab((Tab)null);
         this.setSelected(false);
      }

      public void setSelected(boolean var1) {
         boolean var2;
         if (this.isSelected() != var1) {
            var2 = true;
         } else {
            var2 = false;
         }

         super.setSelected(var1);
         if (var2 && var1 && VERSION.SDK_INT < 16) {
            this.sendAccessibilityEvent(4);
         }

         TextView var3 = this.textView;
         if (var3 != null) {
            var3.setSelected(var1);
         }

         ImageView var4 = this.iconView;
         if (var4 != null) {
            var4.setSelected(var1);
         }

         View var5 = this.customView;
         if (var5 != null) {
            var5.setSelected(var1);
         }

      }

      void setTab(Tab var1) {
         if (var1 != this.tab) {
            this.tab = var1;
            this.update();
         }

      }

      final void update() {
         Tab var5 = this.tab;
         Object var4 = null;
         View var3;
         if (var5 != null) {
            var3 = var5.getCustomView();
         } else {
            var3 = null;
         }

         if (var3 != null) {
            ViewParent var6 = var3.getParent();
            if (var6 != this) {
               if (var6 != null) {
                  ((ViewGroup)var6).removeView(var3);
               }

               this.addView(var3);
            }

            this.customView = var3;
            TextView var9 = this.textView;
            if (var9 != null) {
               var9.setVisibility(8);
            }

            ImageView var11 = this.iconView;
            if (var11 != null) {
               var11.setVisibility(8);
               this.iconView.setImageDrawable((Drawable)null);
            }

            var9 = (TextView)var3.findViewById(16908308);
            this.customTextView = var9;
            if (var9 != null) {
               this.defaultMaxLines = TextViewCompat.getMaxLines(var9);
            }

            this.customIconView = (ImageView)var3.findViewById(16908294);
         } else {
            var3 = this.customView;
            if (var3 != null) {
               this.removeView(var3);
               this.customView = null;
            }

            this.customTextView = null;
            this.customIconView = null;
         }

         var3 = this.customView;
         boolean var2 = false;
         TextView var10;
         if (var3 == null) {
            if (this.iconView == null) {
               ImageView var7 = (ImageView)LayoutInflater.from(this.getContext()).inflate(R.layout.design_layout_tab_icon, this, false);
               this.addView(var7, 0);
               this.iconView = var7;
            }

            Drawable var8 = (Drawable)var4;
            if (var5 != null) {
               var8 = (Drawable)var4;
               if (var5.getIcon() != null) {
                  var8 = DrawableCompat.wrap(var5.getIcon()).mutate();
               }
            }

            if (var8 != null) {
               DrawableCompat.setTintList(var8, this.this$0.tabIconTint);
               if (this.this$0.tabIconTintMode != null) {
                  DrawableCompat.setTintMode(var8, this.this$0.tabIconTintMode);
               }
            }

            if (this.textView == null) {
               var10 = (TextView)LayoutInflater.from(this.getContext()).inflate(R.layout.design_layout_tab_text, this, false);
               this.addView(var10);
               this.textView = var10;
               this.defaultMaxLines = TextViewCompat.getMaxLines(var10);
            }

            TextViewCompat.setTextAppearance(this.textView, this.this$0.tabTextAppearance);
            if (this.this$0.tabTextColors != null) {
               this.textView.setTextColor(this.this$0.tabTextColors);
            }

            this.updateTextAndIcon(this.textView, this.iconView);
         } else {
            var10 = this.customTextView;
            if (var10 != null || this.customIconView != null) {
               this.updateTextAndIcon(var10, this.customIconView);
            }
         }

         if (var5 != null && !TextUtils.isEmpty(var5.contentDesc)) {
            this.setContentDescription(var5.contentDesc);
         }

         boolean var1 = var2;
         if (var5 != null) {
            var1 = var2;
            if (var5.isSelected()) {
               var1 = true;
            }
         }

         this.setSelected(var1);
      }

      final void updateOrientation() {
         this.setOrientation(this.this$0.inlineLabel ^ 1);
         TextView var1 = this.customTextView;
         if (var1 == null && this.customIconView == null) {
            this.updateTextAndIcon(this.textView, this.iconView);
         } else {
            this.updateTextAndIcon(var1, this.customIconView);
         }

      }
   }

   public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
      private final ViewPager viewPager;

      public ViewPagerOnTabSelectedListener(ViewPager var1) {
         this.viewPager = var1;
      }

      public void onTabReselected(Tab var1) {
      }

      public void onTabSelected(Tab var1) {
         this.viewPager.setCurrentItem(var1.getPosition());
      }

      public void onTabUnselected(Tab var1) {
      }
   }
}
