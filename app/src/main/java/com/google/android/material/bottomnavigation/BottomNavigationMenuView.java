package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.util.Pools;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.R;
import com.google.android.material.internal.TextScale;

public class BottomNavigationMenuView extends ViewGroup implements MenuView {
   private static final long ACTIVE_ANIMATION_DURATION_MS = 115L;
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private static final int[] DISABLED_STATE_SET = new int[]{-16842910};
   private final int activeItemMaxWidth;
   private final int activeItemMinWidth;
   private BottomNavigationItemView[] buttons;
   private final int inactiveItemMaxWidth;
   private final int inactiveItemMinWidth;
   private Drawable itemBackground;
   private int itemBackgroundRes;
   private final int itemHeight;
   private boolean itemHorizontalTranslationEnabled;
   private int itemIconSize;
   private ColorStateList itemIconTint;
   private final Pools.Pool itemPool;
   private int itemTextAppearanceActive;
   private int itemTextAppearanceInactive;
   private final ColorStateList itemTextColorDefault;
   private ColorStateList itemTextColorFromUser;
   private int labelVisibilityMode;
   private MenuBuilder menu;
   private final View.OnClickListener onClickListener;
   private BottomNavigationPresenter presenter;
   private int selectedItemId;
   private int selectedItemPosition;
   private final TransitionSet set;
   private int[] tempChildWidths;

   public BottomNavigationMenuView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public BottomNavigationMenuView(Context var1, AttributeSet var2) {
      super(var1, var2);
      this.itemPool = new Pools.SynchronizedPool(5);
      this.selectedItemId = 0;
      this.selectedItemPosition = 0;
      Resources var3 = this.getResources();
      this.inactiveItemMaxWidth = var3.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width);
      this.inactiveItemMinWidth = var3.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width);
      this.activeItemMaxWidth = var3.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width);
      this.activeItemMinWidth = var3.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width);
      this.itemHeight = var3.getDimensionPixelSize(R.dimen.design_bottom_navigation_height);
      this.itemTextColorDefault = this.createDefaultColorStateList(16842808);
      AutoTransition var4 = new AutoTransition();
      this.set = var4;
      var4.setOrdering(0);
      var4.setDuration(115L);
      var4.setInterpolator(new FastOutSlowInInterpolator());
      var4.addTransition(new TextScale());
      this.onClickListener = new View.OnClickListener(this) {
         final BottomNavigationMenuView this$0;

         {
            this.this$0 = var1;
         }

         public void onClick(View var1) {
            MenuItemImpl var2 = ((BottomNavigationItemView)var1).getItemData();
            if (!this.this$0.menu.performItemAction(var2, this.this$0.presenter, 0)) {
               var2.setChecked(true);
            }

         }
      };
      this.tempChildWidths = new int[5];
   }

   private BottomNavigationItemView getNewItem() {
      BottomNavigationItemView var2 = (BottomNavigationItemView)this.itemPool.acquire();
      BottomNavigationItemView var1 = var2;
      if (var2 == null) {
         var1 = new BottomNavigationItemView(this.getContext());
      }

      return var1;
   }

   private boolean isShifting(int var1, int var2) {
      boolean var3 = true;
      if (var1 == -1) {
         if (var2 > 3) {
            return var3;
         }
      } else if (var1 == 0) {
         return var3;
      }

      var3 = false;
      return var3;
   }

   public void buildMenuView() {
      this.removeAllViews();
      BottomNavigationItemView[] var5 = this.buttons;
      int var1;
      BottomNavigationItemView var4;
      if (var5 != null) {
         int var2 = var5.length;

         for(var1 = 0; var1 < var2; ++var1) {
            var4 = var5[var1];
            if (var4 != null) {
               this.itemPool.release(var4);
            }
         }
      }

      if (this.menu.size() == 0) {
         this.selectedItemId = 0;
         this.selectedItemPosition = 0;
         this.buttons = null;
      } else {
         this.buttons = new BottomNavigationItemView[this.menu.size()];
         boolean var3 = this.isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());

         for(var1 = 0; var1 < this.menu.size(); ++var1) {
            this.presenter.setUpdateSuspended(true);
            this.menu.getItem(var1).setCheckable(true);
            this.presenter.setUpdateSuspended(false);
            var4 = this.getNewItem();
            this.buttons[var1] = var4;
            var4.setIconTintList(this.itemIconTint);
            var4.setIconSize(this.itemIconSize);
            var4.setTextColor(this.itemTextColorDefault);
            var4.setTextAppearanceInactive(this.itemTextAppearanceInactive);
            var4.setTextAppearanceActive(this.itemTextAppearanceActive);
            var4.setTextColor(this.itemTextColorFromUser);
            Drawable var6 = this.itemBackground;
            if (var6 != null) {
               var4.setItemBackground(var6);
            } else {
               var4.setItemBackground(this.itemBackgroundRes);
            }

            var4.setShifting(var3);
            var4.setLabelVisibilityMode(this.labelVisibilityMode);
            var4.initialize((MenuItemImpl)this.menu.getItem(var1), 0);
            var4.setItemPosition(var1);
            var4.setOnClickListener(this.onClickListener);
            this.addView(var4);
         }

         var1 = Math.min(this.menu.size() - 1, this.selectedItemPosition);
         this.selectedItemPosition = var1;
         this.menu.getItem(var1).setChecked(true);
      }
   }

   public ColorStateList createDefaultColorStateList(int var1) {
      TypedValue var5 = new TypedValue();
      if (!this.getContext().getTheme().resolveAttribute(var1, var5, true)) {
         return null;
      } else {
         ColorStateList var4 = AppCompatResources.getColorStateList(this.getContext(), var5.resourceId);
         if (!this.getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, var5, true)) {
            return null;
         } else {
            int var3 = var5.data;
            int var2 = var4.getDefaultColor();
            int[] var7 = DISABLED_STATE_SET;
            int[] var8 = CHECKED_STATE_SET;
            int[] var6 = EMPTY_STATE_SET;
            var1 = var4.getColorForState(var7, var2);
            return new ColorStateList(new int[][]{var7, var8, var6}, new int[]{var1, var3, var2});
         }
      }
   }

   public ColorStateList getIconTintList() {
      return this.itemIconTint;
   }

   public Drawable getItemBackground() {
      BottomNavigationItemView[] var1 = this.buttons;
      return var1 != null && var1.length > 0 ? var1[0].getBackground() : this.itemBackground;
   }

   @Deprecated
   public int getItemBackgroundRes() {
      return this.itemBackgroundRes;
   }

   public int getItemIconSize() {
      return this.itemIconSize;
   }

   public int getItemTextAppearanceActive() {
      return this.itemTextAppearanceActive;
   }

   public int getItemTextAppearanceInactive() {
      return this.itemTextAppearanceInactive;
   }

   public ColorStateList getItemTextColor() {
      return this.itemTextColorFromUser;
   }

   public int getLabelVisibilityMode() {
      return this.labelVisibilityMode;
   }

   public int getSelectedItemId() {
      return this.selectedItemId;
   }

   public int getWindowAnimations() {
      return 0;
   }

   public void initialize(MenuBuilder var1) {
      this.menu = var1;
   }

   public boolean isItemHorizontalTranslationEnabled() {
      return this.itemHorizontalTranslationEnabled;
   }

   protected void onLayout(boolean var1, int var2, int var3, int var4, int var5) {
      int var6 = this.getChildCount();
      int var7 = var5 - var3;
      var3 = 0;

      for(var5 = 0; var3 < var6; ++var3) {
         View var9 = this.getChildAt(var3);
         if (var9.getVisibility() != 8) {
            if (ViewCompat.getLayoutDirection(this) == 1) {
               int var8 = var4 - var2 - var5;
               var9.layout(var8 - var9.getMeasuredWidth(), 0, var8, var7);
            } else {
               var9.layout(var5, 0, var9.getMeasuredWidth() + var5, var7);
            }

            var5 += var9.getMeasuredWidth();
         }
      }

   }

   protected void onMeasure(int var1, int var2) {
      int var6 = MeasureSpec.getSize(var1);
      int var3 = this.menu.getVisibleItems().size();
      int var8 = this.getChildCount();
      int var7 = MeasureSpec.makeMeasureSpec(this.itemHeight, 1073741824);
      boolean var9 = this.isShifting(this.labelVisibilityMode, var3);
      var1 = 1;
      int var4 = 1;
      int[] var10;
      View var12;
      if (var9 && this.itemHorizontalTranslationEnabled) {
         var12 = this.getChildAt(this.selectedItemPosition);
         var2 = this.activeItemMinWidth;
         var1 = var2;
         if (var12.getVisibility() != 8) {
            var12.measure(MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), var7);
            var1 = Math.max(var2, var12.getMeasuredWidth());
         }

         byte var11;
         if (var12.getVisibility() != 8) {
            var11 = 1;
         } else {
            var11 = 0;
         }

         var2 = var3 - var11;
         int var5 = Math.min(var6 - this.inactiveItemMinWidth * var2, Math.min(var1, this.activeItemMaxWidth));
         var3 = var6 - var5;
         if (var2 == 0) {
            var1 = var4;
         } else {
            var1 = var2;
         }

         var6 = Math.min(var3 / var1, this.inactiveItemMaxWidth);
         var2 = var3 - var2 * var6;

         for(var1 = 0; var1 < var8; var2 = var3) {
            if (this.getChildAt(var1).getVisibility() != 8) {
               var10 = this.tempChildWidths;
               if (var1 == this.selectedItemPosition) {
                  var4 = var5;
               } else {
                  var4 = var6;
               }

               var10[var1] = var4;
               var3 = var2;
               if (var2 > 0) {
                  var10[var1] = var4 + 1;
                  var3 = var2 - 1;
               }
            } else {
               this.tempChildWidths[var1] = 0;
               var3 = var2;
            }

            ++var1;
         }
      } else {
         if (var3 != 0) {
            var1 = var3;
         }

         var4 = Math.min(var6 / var1, this.activeItemMaxWidth);
         var2 = var6 - var3 * var4;

         for(var1 = 0; var1 < var8; var2 = var3) {
            if (this.getChildAt(var1).getVisibility() != 8) {
               var10 = this.tempChildWidths;
               var10[var1] = var4;
               var3 = var2;
               if (var2 > 0) {
                  var10[var1] = var4 + 1;
                  var3 = var2 - 1;
               }
            } else {
               this.tempChildWidths[var1] = 0;
               var3 = var2;
            }

            ++var1;
         }
      }

      var1 = 0;

      for(var2 = 0; var1 < var8; ++var1) {
         var12 = this.getChildAt(var1);
         if (var12.getVisibility() != 8) {
            var12.measure(MeasureSpec.makeMeasureSpec(this.tempChildWidths[var1], 1073741824), var7);
            var12.getLayoutParams().width = var12.getMeasuredWidth();
            var2 += var12.getMeasuredWidth();
         }
      }

      this.setMeasuredDimension(View.resolveSizeAndState(var2, MeasureSpec.makeMeasureSpec(var2, 1073741824), 0), View.resolveSizeAndState(this.itemHeight, var7, 0));
   }

   public void setIconTintList(ColorStateList var1) {
      this.itemIconTint = var1;
      BottomNavigationItemView[] var4 = this.buttons;
      if (var4 != null) {
         int var3 = var4.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            var4[var2].setIconTintList(var1);
         }
      }

   }

   public void setItemBackground(Drawable var1) {
      this.itemBackground = var1;
      BottomNavigationItemView[] var4 = this.buttons;
      if (var4 != null) {
         int var3 = var4.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            var4[var2].setItemBackground(var1);
         }
      }

   }

   public void setItemBackgroundRes(int var1) {
      this.itemBackgroundRes = var1;
      BottomNavigationItemView[] var4 = this.buttons;
      if (var4 != null) {
         int var3 = var4.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            var4[var2].setItemBackground(var1);
         }
      }

   }

   public void setItemHorizontalTranslationEnabled(boolean var1) {
      this.itemHorizontalTranslationEnabled = var1;
   }

   public void setItemIconSize(int var1) {
      this.itemIconSize = var1;
      BottomNavigationItemView[] var4 = this.buttons;
      if (var4 != null) {
         int var3 = var4.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            var4[var2].setIconSize(var1);
         }
      }

   }

   public void setItemTextAppearanceActive(int var1) {
      this.itemTextAppearanceActive = var1;
      BottomNavigationItemView[] var4 = this.buttons;
      if (var4 != null) {
         int var3 = var4.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            BottomNavigationItemView var5 = var4[var2];
            var5.setTextAppearanceActive(var1);
            ColorStateList var6 = this.itemTextColorFromUser;
            if (var6 != null) {
               var5.setTextColor(var6);
            }
         }
      }

   }

   public void setItemTextAppearanceInactive(int var1) {
      this.itemTextAppearanceInactive = var1;
      BottomNavigationItemView[] var6 = this.buttons;
      if (var6 != null) {
         int var3 = var6.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            BottomNavigationItemView var4 = var6[var2];
            var4.setTextAppearanceInactive(var1);
            ColorStateList var5 = this.itemTextColorFromUser;
            if (var5 != null) {
               var4.setTextColor(var5);
            }
         }
      }

   }

   public void setItemTextColor(ColorStateList var1) {
      this.itemTextColorFromUser = var1;
      BottomNavigationItemView[] var4 = this.buttons;
      if (var4 != null) {
         int var3 = var4.length;

         for(int var2 = 0; var2 < var3; ++var2) {
            var4[var2].setTextColor(var1);
         }
      }

   }

   public void setLabelVisibilityMode(int var1) {
      this.labelVisibilityMode = var1;
   }

   public void setPresenter(BottomNavigationPresenter var1) {
      this.presenter = var1;
   }

   void tryRestoreSelectedItemId(int var1) {
      int var3 = this.menu.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         MenuItem var4 = this.menu.getItem(var2);
         if (var1 == var4.getItemId()) {
            this.selectedItemId = var1;
            this.selectedItemPosition = var2;
            var4.setChecked(true);
            break;
         }
      }

   }

   public void updateMenuView() {
      MenuBuilder var5 = this.menu;
      if (var5 != null && this.buttons != null) {
         int var2 = var5.size();
         if (var2 != this.buttons.length) {
            this.buildMenuView();
            return;
         }

         int var3 = this.selectedItemId;

         int var1;
         for(var1 = 0; var1 < var2; ++var1) {
            MenuItem var6 = this.menu.getItem(var1);
            if (var6.isChecked()) {
               this.selectedItemId = var6.getItemId();
               this.selectedItemPosition = var1;
            }
         }

         if (var3 != this.selectedItemId) {
            TransitionManager.beginDelayedTransition(this, this.set);
         }

         boolean var4 = this.isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());

         for(var1 = 0; var1 < var2; ++var1) {
            this.presenter.setUpdateSuspended(true);
            this.buttons[var1].setLabelVisibilityMode(this.labelVisibilityMode);
            this.buttons[var1].setShifting(var4);
            this.buttons[var1].initialize((MenuItemImpl)this.menu.getItem(var1), 0);
            this.presenter.setUpdateSuspended(false);
         }
      }

   }
}
