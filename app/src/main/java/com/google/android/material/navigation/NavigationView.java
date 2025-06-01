package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.ThemeEnforcement;

public class NavigationView extends ScrimInsetsFrameLayout {
   private static final int[] CHECKED_STATE_SET = new int[]{16842912};
   private static final int[] DISABLED_STATE_SET = new int[]{-16842910};
   private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
   OnNavigationItemSelectedListener listener;
   private final int maxWidth;
   private final NavigationMenu menu;
   private MenuInflater menuInflater;
   private final NavigationMenuPresenter presenter;

   public NavigationView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public NavigationView(Context var1, AttributeSet var2) {
      this(var1, var2, R.attr.navigationViewStyle);
   }

   public NavigationView(Context var1, AttributeSet var2, int var3) {
      super(var1, var2, var3);
      NavigationMenuPresenter var8 = new NavigationMenuPresenter();
      this.presenter = var8;
      NavigationMenu var9 = new NavigationMenu(var1);
      this.menu = var9;
      TintTypedArray var10 = ThemeEnforcement.obtainTintedStyledAttributes(var1, var2, R.styleable.NavigationView, var3, R.style.Widget_Design_NavigationView);
      ViewCompat.setBackground(this, var10.getDrawable(R.styleable.NavigationView_android_background));
      if (var10.hasValue(R.styleable.NavigationView_elevation)) {
         ViewCompat.setElevation(this, (float)var10.getDimensionPixelSize(R.styleable.NavigationView_elevation, 0));
      }

      ViewCompat.setFitsSystemWindows(this, var10.getBoolean(R.styleable.NavigationView_android_fitsSystemWindows, false));
      this.maxWidth = var10.getDimensionPixelSize(R.styleable.NavigationView_android_maxWidth, 0);
      ColorStateList var6;
      if (var10.hasValue(R.styleable.NavigationView_itemIconTint)) {
         var6 = var10.getColorStateList(R.styleable.NavigationView_itemIconTint);
      } else {
         var6 = this.createDefaultColorStateList(16842808);
      }

      int var4;
      boolean var12;
      if (var10.hasValue(R.styleable.NavigationView_itemTextAppearance)) {
         var4 = var10.getResourceId(R.styleable.NavigationView_itemTextAppearance, 0);
         var12 = true;
      } else {
         var4 = 0;
         var12 = false;
      }

      ColorStateList var11 = null;
      if (var10.hasValue(R.styleable.NavigationView_itemTextColor)) {
         var11 = var10.getColorStateList(R.styleable.NavigationView_itemTextColor);
      }

      ColorStateList var7 = var11;
      if (!var12) {
         var7 = var11;
         if (var11 == null) {
            var7 = this.createDefaultColorStateList(16842806);
         }
      }

      Drawable var13 = var10.getDrawable(R.styleable.NavigationView_itemBackground);
      if (var10.hasValue(R.styleable.NavigationView_itemHorizontalPadding)) {
         var8.setItemHorizontalPadding(var10.getDimensionPixelSize(R.styleable.NavigationView_itemHorizontalPadding, 0));
      }

      int var5 = var10.getDimensionPixelSize(R.styleable.NavigationView_itemIconPadding, 0);
      var9.setCallback(new MenuBuilder.Callback(this) {
         final NavigationView this$0;

         {
            this.this$0 = var1;
         }

         public boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2) {
            boolean var3;
            if (this.this$0.listener != null && this.this$0.listener.onNavigationItemSelected(var2)) {
               var3 = true;
            } else {
               var3 = false;
            }

            return var3;
         }

         public void onMenuModeChange(MenuBuilder var1) {
         }
      });
      var8.setId(1);
      var8.initForMenu(var1, var9);
      var8.setItemIconTintList(var6);
      if (var12) {
         var8.setItemTextAppearance(var4);
      }

      var8.setItemTextColor(var7);
      var8.setItemBackground(var13);
      var8.setItemIconPadding(var5);
      var9.addMenuPresenter(var8);
      this.addView((View)var8.getMenuView(this));
      if (var10.hasValue(R.styleable.NavigationView_menu)) {
         this.inflateMenu(var10.getResourceId(R.styleable.NavigationView_menu, 0));
      }

      if (var10.hasValue(R.styleable.NavigationView_headerLayout)) {
         this.inflateHeaderView(var10.getResourceId(R.styleable.NavigationView_headerLayout, 0));
      }

      var10.recycle();
   }

   private ColorStateList createDefaultColorStateList(int var1) {
      TypedValue var5 = new TypedValue();
      if (!this.getContext().getTheme().resolveAttribute(var1, var5, true)) {
         return null;
      } else {
         ColorStateList var4 = AppCompatResources.getColorStateList(this.getContext(), var5.resourceId);
         if (!this.getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, var5, true)) {
            return null;
         } else {
            int var2 = var5.data;
            var1 = var4.getDefaultColor();
            int[] var8 = DISABLED_STATE_SET;
            int[] var7 = CHECKED_STATE_SET;
            int[] var6 = EMPTY_STATE_SET;
            int var3 = var4.getColorForState(var8, var1);
            return new ColorStateList(new int[][]{var8, var7, var6}, new int[]{var3, var2, var1});
         }
      }
   }

   private MenuInflater getMenuInflater() {
      if (this.menuInflater == null) {
         this.menuInflater = new SupportMenuInflater(this.getContext());
      }

      return this.menuInflater;
   }

   public void addHeaderView(View var1) {
      this.presenter.addHeaderView(var1);
   }

   public MenuItem getCheckedItem() {
      return this.presenter.getCheckedItem();
   }

   public int getHeaderCount() {
      return this.presenter.getHeaderCount();
   }

   public View getHeaderView(int var1) {
      return this.presenter.getHeaderView(var1);
   }

   public Drawable getItemBackground() {
      return this.presenter.getItemBackground();
   }

   public int getItemHorizontalPadding() {
      return this.presenter.getItemHorizontalPadding();
   }

   public int getItemIconPadding() {
      return this.presenter.getItemIconPadding();
   }

   public ColorStateList getItemIconTintList() {
      return this.presenter.getItemTintList();
   }

   public ColorStateList getItemTextColor() {
      return this.presenter.getItemTextColor();
   }

   public Menu getMenu() {
      return this.menu;
   }

   public View inflateHeaderView(int var1) {
      return this.presenter.inflateHeaderView(var1);
   }

   public void inflateMenu(int var1) {
      this.presenter.setUpdateSuspended(true);
      this.getMenuInflater().inflate(var1, this.menu);
      this.presenter.setUpdateSuspended(false);
      this.presenter.updateMenuView(false);
   }

   protected void onInsetsChanged(WindowInsetsCompat var1) {
      this.presenter.dispatchApplyWindowInsets(var1);
   }

   protected void onMeasure(int var1, int var2) {
      int var3 = MeasureSpec.getMode(var1);
      if (var3 != Integer.MIN_VALUE) {
         if (var3 == 0) {
            var1 = MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
         }
      } else {
         var1 = MeasureSpec.makeMeasureSpec(Math.min(MeasureSpec.getSize(var1), this.maxWidth), 1073741824);
      }

      super.onMeasure(var1, var2);
   }

   protected void onRestoreInstanceState(Parcelable var1) {
      if (!(var1 instanceof SavedState)) {
         super.onRestoreInstanceState(var1);
      } else {
         SavedState var2 = (SavedState)var1;
         super.onRestoreInstanceState(var2.getSuperState());
         this.menu.restorePresenterStates(var2.menuState);
      }
   }

   protected Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState(super.onSaveInstanceState());
      var1.menuState = new Bundle();
      this.menu.savePresenterStates(var1.menuState);
      return var1;
   }

   public void removeHeaderView(View var1) {
      this.presenter.removeHeaderView(var1);
   }

   public void setCheckedItem(int var1) {
      MenuItem var2 = this.menu.findItem(var1);
      if (var2 != null) {
         this.presenter.setCheckedItem((MenuItemImpl)var2);
      }

   }

   public void setCheckedItem(MenuItem var1) {
      var1 = this.menu.findItem(var1.getItemId());
      if (var1 != null) {
         this.presenter.setCheckedItem((MenuItemImpl)var1);
      } else {
         throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
      }
   }

   public void setItemBackground(Drawable var1) {
      this.presenter.setItemBackground(var1);
   }

   public void setItemBackgroundResource(int var1) {
      this.setItemBackground(ContextCompat.getDrawable(this.getContext(), var1));
   }

   public void setItemHorizontalPadding(int var1) {
      this.presenter.setItemHorizontalPadding(var1);
   }

   public void setItemHorizontalPaddingResource(int var1) {
      this.presenter.setItemHorizontalPadding(this.getResources().getDimensionPixelSize(var1));
   }

   public void setItemIconPadding(int var1) {
      this.presenter.setItemIconPadding(var1);
   }

   public void setItemIconPaddingResource(int var1) {
      this.presenter.setItemIconPadding(this.getResources().getDimensionPixelSize(var1));
   }

   public void setItemIconTintList(ColorStateList var1) {
      this.presenter.setItemIconTintList(var1);
   }

   public void setItemTextAppearance(int var1) {
      this.presenter.setItemTextAppearance(var1);
   }

   public void setItemTextColor(ColorStateList var1) {
      this.presenter.setItemTextColor(var1);
   }

   public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener var1) {
      this.listener = var1;
   }

   public interface OnNavigationItemSelectedListener {
      boolean onNavigationItemSelected(MenuItem var1);
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
      public Bundle menuState;

      public SavedState(Parcel var1, ClassLoader var2) {
         super(var1, var2);
         this.menuState = var1.readBundle(var2);
      }

      public SavedState(Parcelable var1) {
         super(var1);
      }

      public void writeToParcel(Parcel var1, int var2) {
         super.writeToParcel(var1, var2);
         var1.writeBundle(this.menuState);
      }
   }
}
