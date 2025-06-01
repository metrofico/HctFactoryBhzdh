package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;

public class BottomNavigationView extends FrameLayout {
   private static final int MENU_PRESENTER_ID = 1;
   private final MenuBuilder menu;
   private MenuInflater menuInflater;
   private final BottomNavigationMenuView menuView;
   private final BottomNavigationPresenter presenter;
   private OnNavigationItemReselectedListener reselectedListener;
   private OnNavigationItemSelectedListener selectedListener;

   public BottomNavigationView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public BottomNavigationView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.bottomNavigationStyle);
   }

   public BottomNavigationView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      BottomNavigationPresenter var7 = new BottomNavigationPresenter();
      this.presenter = var7;
      BottomNavigationMenu var5 = new BottomNavigationMenu(var1);
      this.menu = var5;
      BottomNavigationMenuView var6 = new BottomNavigationMenuView(var1);
      this.menuView = var6;
      FrameLayout.LayoutParams var4 = new FrameLayout.LayoutParams(-2, -2);
      var4.gravity = 17;
      var6.setLayoutParams(var4);
      var7.setBottomNavigationMenuView(var6);
      var7.setId(1);
      var6.setPresenter(var7);
      var5.addMenuPresenter(var7);
      var7.initForMenu(this.getContext(), var5);
      TintTypedArray var8 = ThemeEnforcement.obtainTintedStyledAttributes(var1, var2, R.styleable.BottomNavigationView, var3, R.style.Widget_Design_BottomNavigationView, R.styleable.BottomNavigationView_itemTextAppearanceInactive, R.styleable.BottomNavigationView_itemTextAppearanceActive);
      if (var8.hasValue(R.styleable.BottomNavigationView_itemIconTint)) {
         var6.setIconTintList(var8.getColorStateList(R.styleable.BottomNavigationView_itemIconTint));
      } else {
         var6.setIconTintList(var6.createDefaultColorStateList(16842808));
      }

      this.setItemIconSize(var8.getDimensionPixelSize(R.styleable.BottomNavigationView_itemIconSize, this.getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_icon_size)));
      if (var8.hasValue(R.styleable.BottomNavigationView_itemTextAppearanceInactive)) {
         this.setItemTextAppearanceInactive(var8.getResourceId(R.styleable.BottomNavigationView_itemTextAppearanceInactive, 0));
      }

      if (var8.hasValue(R.styleable.BottomNavigationView_itemTextAppearanceActive)) {
         this.setItemTextAppearanceActive(var8.getResourceId(R.styleable.BottomNavigationView_itemTextAppearanceActive, 0));
      }

      if (var8.hasValue(R.styleable.BottomNavigationView_itemTextColor)) {
         this.setItemTextColor(var8.getColorStateList(R.styleable.BottomNavigationView_itemTextColor));
      }

      if (var8.hasValue(R.styleable.BottomNavigationView_elevation)) {
         ViewCompat.setElevation(this, (float)var8.getDimensionPixelSize(R.styleable.BottomNavigationView_elevation, 0));
      }

      this.setLabelVisibilityMode(var8.getInteger(R.styleable.BottomNavigationView_labelVisibilityMode, -1));
      this.setItemHorizontalTranslationEnabled(var8.getBoolean(R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
      var6.setItemBackgroundRes(var8.getResourceId(R.styleable.BottomNavigationView_itemBackground, 0));
      if (var8.hasValue(R.styleable.BottomNavigationView_menu)) {
         this.inflateMenu(var8.getResourceId(R.styleable.BottomNavigationView_menu, 0));
      }

      var8.recycle();
      this.addView(var6, var4);
      if (VERSION.SDK_INT < 21) {
         this.addCompatibilityTopDivider(var1);
      }

      var5.setCallback(new MenuBuilder.Callback(this) {
         final BottomNavigationView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2) {
            OnNavigationItemReselectedListener var4 = this.this$0.reselectedListener;
            boolean var3 = true;
            if (var4 != null && var2.getItemId() == this.this$0.getSelectedItemId()) {
               this.this$0.reselectedListener.onNavigationItemReselected(var2);
               return true;
            } else {
               if (this.this$0.selectedListener == null || this.this$0.selectedListener.onNavigationItemSelected(var2)) {
                  var3 = false;
               }

               return var3;
            }
         }

         public void onMenuModeChange(MenuBuilder var1) {
         }
      });
   }

   private void addCompatibilityTopDivider(Context var1) {
      View var2 = new View(var1);
      var2.setBackgroundColor(ContextCompat.getColor(var1, R.color.design_bottom_navigation_shadow_color));
      var2.setLayoutParams(new FrameLayout.LayoutParams(-1, this.getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_shadow_height)));
      this.addView(var2);
   }

   private MenuInflater getMenuInflater() {
      if (this.menuInflater == null) {
         this.menuInflater = new SupportMenuInflater(this.getContext());
      }

      return this.menuInflater;
   }

   public Drawable getItemBackground() {
      return this.menuView.getItemBackground();
   }

   @Deprecated
   public int getItemBackgroundResource() {
      return this.menuView.getItemBackgroundRes();
   }

   public int getItemIconSize() {
      return this.menuView.getItemIconSize();
   }

   public ColorStateList getItemIconTintList() {
      return this.menuView.getIconTintList();
   }

   public int getItemTextAppearanceActive() {
      return this.menuView.getItemTextAppearanceActive();
   }

   public int getItemTextAppearanceInactive() {
      return this.menuView.getItemTextAppearanceInactive();
   }

   public ColorStateList getItemTextColor() {
      return this.menuView.getItemTextColor();
   }

   public int getLabelVisibilityMode() {
      return this.menuView.getLabelVisibilityMode();
   }

   public int getMaxItemCount() {
      return 5;
   }

   public Menu getMenu() {
      return this.menu;
   }

   public int getSelectedItemId() {
      return this.menuView.getSelectedItemId();
   }

   public void inflateMenu(int var1) {
      this.presenter.setUpdateSuspended(true);
      this.getMenuInflater().inflate(var1, this.menu);
      this.presenter.setUpdateSuspended(false);
      this.presenter.updateMenuView(true);
   }

   public boolean isItemHorizontalTranslationEnabled() {
      return this.menuView.isItemHorizontalTranslationEnabled();
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.menu.restorePresenterStates(var2.menuPresenterState);
      }
   }

   protected Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      var1.menuPresenterState = new Bundle();
      this.menu.savePresenterStates(var1.menuPresenterState);
      return var1;
   }

   public void setItemBackground(Drawable var1) {
      this.menuView.setItemBackground(var1);
   }

   public void setItemBackgroundResource(int var1) {
      this.menuView.setItemBackgroundRes(var1);
   }

   public void setItemHorizontalTranslationEnabled(boolean var1) {
      if (this.menuView.isItemHorizontalTranslationEnabled() != var1) {
         this.menuView.setItemHorizontalTranslationEnabled(var1);
         this.presenter.updateMenuView(false);
      }

   }

   public void setItemIconSize(int var1) {
      this.menuView.setItemIconSize(var1);
   }

   public void setItemIconSizeRes(int var1) {
      this.setItemIconSize(this.getResources().getDimensionPixelSize(var1));
   }

   public void setItemIconTintList(ColorStateList var1) {
      this.menuView.setIconTintList(var1);
   }

   public void setItemTextAppearanceActive(int var1) {
      this.menuView.setItemTextAppearanceActive(var1);
   }

   public void setItemTextAppearanceInactive(int var1) {
      this.menuView.setItemTextAppearanceInactive(var1);
   }

   public void setItemTextColor(ColorStateList var1) {
      this.menuView.setItemTextColor(var1);
   }

   public void setLabelVisibilityMode(int var1) {
      if (this.menuView.getLabelVisibilityMode() != var1) {
         this.menuView.setLabelVisibilityMode(var1);
         this.presenter.updateMenuView(false);
      }

   }

   public void setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener var1) {
      this.reselectedListener = var1;
   }

   public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener var1) {
      this.selectedListener = var1;
   }

   public void setSelectedItemId(int var1) {
      MenuItem var2 = this.menu.findItem(var1);
      if (var2 != null && !this.menu.performItemAction(var2, this.presenter, 0)) {
         var2.setChecked(true);
      }

   }

   public interface OnNavigationItemReselectedListener {
      void onNavigationItemReselected(MenuItem var1);
   }

   public interface OnNavigationItemSelectedListener {
      boolean onNavigationItemSelected(MenuItem var1);
   }

   static class SavedState extends AbsSavedState {
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
      Bundle menuPresenterState;

      public SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         this.readFromParcel(var1, var2);
      }

      public SavedState(Parcelable var1) {
         super(var1);
      }

      private void readFromParcel(Parcel var1, ClassLoader var2) {
         this.menuPresenterState = var1.readBundle(var2);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeBundle(this.menuPresenterState);
      }
   }
}
