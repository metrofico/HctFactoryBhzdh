package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;

public class ToolbarWidgetWrapper implements DecorToolbar {
   private static final int AFFECTS_LOGO_MASK = 3;
   private static final long DEFAULT_FADE_DURATION_MS = 200L;
   private static final String TAG = "ToolbarWidgetWrapper";
   private ActionMenuPresenter mActionMenuPresenter;
   private View mCustomView;
   private int mDefaultNavigationContentDescription;
   private Drawable mDefaultNavigationIcon;
   private int mDisplayOpts;
   private CharSequence mHomeDescription;
   private Drawable mIcon;
   private Drawable mLogo;
   boolean mMenuPrepared;
   private Drawable mNavIcon;
   private int mNavigationMode;
   private Spinner mSpinner;
   private CharSequence mSubtitle;
   private View mTabView;
   CharSequence mTitle;
   private boolean mTitleSet;
   Toolbar mToolbar;
   Window.Callback mWindowCallback;

   public ToolbarWidgetWrapper(Toolbar var1, boolean var2) {
      this(var1, var2, R.string.abc_action_bar_up_description, R.drawable.abc_ic_ab_back_material);
   }

   public ToolbarWidgetWrapper(Toolbar var1, boolean var2, int var3, int var4) {
      this.mNavigationMode = 0;
      this.mDefaultNavigationContentDescription = 0;
      this.mToolbar = var1;
      this.mTitle = var1.getTitle();
      this.mSubtitle = var1.getSubtitle();
      boolean var6;
      if (this.mTitle != null) {
         var6 = true;
      } else {
         var6 = false;
      }

      this.mTitleSet = var6;
      this.mNavIcon = var1.getNavigationIcon();
      TintTypedArray var8 = TintTypedArray.obtainStyledAttributes(var1.getContext(), (AttributeSet)null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
      this.mDefaultNavigationIcon = var8.getDrawable(R.styleable.ActionBar_homeAsUpIndicator);
      if (var2) {
         CharSequence var7 = var8.getText(R.styleable.ActionBar_title);
         if (!TextUtils.isEmpty(var7)) {
            this.setTitle(var7);
         }

         var7 = var8.getText(R.styleable.ActionBar_subtitle);
         if (!TextUtils.isEmpty(var7)) {
            this.setSubtitle(var7);
         }

         Drawable var9 = var8.getDrawable(R.styleable.ActionBar_logo);
         if (var9 != null) {
            this.setLogo(var9);
         }

         var9 = var8.getDrawable(R.styleable.ActionBar_icon);
         if (var9 != null) {
            this.setIcon(var9);
         }

         if (this.mNavIcon == null) {
            var9 = this.mDefaultNavigationIcon;
            if (var9 != null) {
               this.setNavigationIcon(var9);
            }
         }

         this.setDisplayOptions(var8.getInt(R.styleable.ActionBar_displayOptions, 0));
         var4 = var8.getResourceId(R.styleable.ActionBar_customNavigationLayout, 0);
         if (var4 != 0) {
            this.setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(var4, this.mToolbar, false));
            this.setDisplayOptions(this.mDisplayOpts | 16);
         }

         var4 = var8.getLayoutDimension(R.styleable.ActionBar_height, 0);
         if (var4 > 0) {
            ViewGroup.LayoutParams var10 = this.mToolbar.getLayoutParams();
            var10.height = var4;
            this.mToolbar.setLayoutParams(var10);
         }

         int var5 = var8.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetStart, -1);
         var4 = var8.getDimensionPixelOffset(R.styleable.ActionBar_contentInsetEnd, -1);
         if (var5 >= 0 || var4 >= 0) {
            this.mToolbar.setContentInsetsRelative(Math.max(var5, 0), Math.max(var4, 0));
         }

         var4 = var8.getResourceId(R.styleable.ActionBar_titleTextStyle, 0);
         Toolbar var11;
         if (var4 != 0) {
            var11 = this.mToolbar;
            var11.setTitleTextAppearance(var11.getContext(), var4);
         }

         var4 = var8.getResourceId(R.styleable.ActionBar_subtitleTextStyle, 0);
         if (var4 != 0) {
            var11 = this.mToolbar;
            var11.setSubtitleTextAppearance(var11.getContext(), var4);
         }

         var4 = var8.getResourceId(R.styleable.ActionBar_popupTheme, 0);
         if (var4 != 0) {
            this.mToolbar.setPopupTheme(var4);
         }
      } else {
         this.mDisplayOpts = this.detectDisplayOptions();
      }

      var8.recycle();
      this.setDefaultNavigationContentDescription(var3);
      this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
      this.mToolbar.setNavigationOnClickListener(new View.OnClickListener(this) {
         final ActionMenuItem mNavItem;
         final ToolbarWidgetWrapper this$0;

         {
            this.this$0 = var1;
            this.mNavItem = new ActionMenuItem(var1.mToolbar.getContext(), 0, 16908332, 0, 0, var1.mTitle);
         }

         public void onClick(View var1) {
            if (this.this$0.mWindowCallback != null && this.this$0.mMenuPrepared) {
               this.this$0.mWindowCallback.onMenuItemSelected(0, this.mNavItem);
            }

         }
      });
   }

   private int detectDisplayOptions() {
      byte var1;
      if (this.mToolbar.getNavigationIcon() != null) {
         var1 = 15;
         this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
      } else {
         var1 = 11;
      }

      return var1;
   }

   private void ensureSpinner() {
      if (this.mSpinner == null) {
         this.mSpinner = new AppCompatSpinner(this.getContext(), (AttributeSet)null, R.attr.actionDropDownStyle);
         Toolbar.LayoutParams var1 = new Toolbar.LayoutParams(-2, -2, 8388627);
         this.mSpinner.setLayoutParams(var1);
      }

   }

   private void setTitleInt(CharSequence var1) {
      this.mTitle = var1;
      if ((this.mDisplayOpts & 8) != 0) {
         this.mToolbar.setTitle(var1);
         if (this.mTitleSet) {
            ViewCompat.setAccessibilityPaneTitle(this.mToolbar.getRootView(), var1);
         }
      }

   }

   private void updateHomeAccessibility() {
      if ((this.mDisplayOpts & 4) != 0) {
         if (TextUtils.isEmpty(this.mHomeDescription)) {
            this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
         } else {
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
         }
      }

   }

   private void updateNavigationIcon() {
      if ((this.mDisplayOpts & 4) != 0) {
         Toolbar var2 = this.mToolbar;
         Drawable var1 = this.mNavIcon;
         if (var1 == null) {
            var1 = this.mDefaultNavigationIcon;
         }

         var2.setNavigationIcon(var1);
      } else {
         this.mToolbar.setNavigationIcon((Drawable)null);
      }

   }

   private void updateToolbarLogo() {
      int var1 = this.mDisplayOpts;
      Drawable var2;
      if ((var1 & 2) != 0) {
         if ((var1 & 1) != 0) {
            var2 = this.mLogo;
            if (var2 == null) {
               var2 = this.mIcon;
            }
         } else {
            var2 = this.mIcon;
         }
      } else {
         var2 = null;
      }

      this.mToolbar.setLogo(var2);
   }

   public void animateToVisibility(int var1) {
      ViewPropertyAnimatorCompat var2 = this.setupAnimatorToVisibility(var1, 200L);
      if (var2 != null) {
         var2.start();
      }

   }

   public boolean canShowOverflowMenu() {
      return this.mToolbar.canShowOverflowMenu();
   }

   public void collapseActionView() {
      this.mToolbar.collapseActionView();
   }

   public void dismissPopupMenus() {
      this.mToolbar.dismissPopupMenus();
   }

   public Context getContext() {
      return this.mToolbar.getContext();
   }

   public View getCustomView() {
      return this.mCustomView;
   }

   public int getDisplayOptions() {
      return this.mDisplayOpts;
   }

   public int getDropdownItemCount() {
      Spinner var2 = this.mSpinner;
      int var1;
      if (var2 != null) {
         var1 = var2.getCount();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int getDropdownSelectedPosition() {
      Spinner var2 = this.mSpinner;
      int var1;
      if (var2 != null) {
         var1 = var2.getSelectedItemPosition();
      } else {
         var1 = 0;
      }

      return var1;
   }

   public int getHeight() {
      return this.mToolbar.getHeight();
   }

   public Menu getMenu() {
      return this.mToolbar.getMenu();
   }

   public int getNavigationMode() {
      return this.mNavigationMode;
   }

   public CharSequence getSubtitle() {
      return this.mToolbar.getSubtitle();
   }

   public CharSequence getTitle() {
      return this.mToolbar.getTitle();
   }

   public ViewGroup getViewGroup() {
      return this.mToolbar;
   }

   public int getVisibility() {
      return this.mToolbar.getVisibility();
   }

   public boolean hasEmbeddedTabs() {
      boolean var1;
      if (this.mTabView != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean hasExpandedActionView() {
      return this.mToolbar.hasExpandedActionView();
   }

   public boolean hasIcon() {
      boolean var1;
      if (this.mIcon != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean hasLogo() {
      boolean var1;
      if (this.mLogo != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean hideOverflowMenu() {
      return this.mToolbar.hideOverflowMenu();
   }

   public void initIndeterminateProgress() {
      Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
   }

   public void initProgress() {
      Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
   }

   public boolean isOverflowMenuShowPending() {
      return this.mToolbar.isOverflowMenuShowPending();
   }

   public boolean isOverflowMenuShowing() {
      return this.mToolbar.isOverflowMenuShowing();
   }

   public boolean isTitleTruncated() {
      return this.mToolbar.isTitleTruncated();
   }

   public void restoreHierarchyState(SparseArray var1) {
      this.mToolbar.restoreHierarchyState(var1);
   }

   public void saveHierarchyState(SparseArray var1) {
      this.mToolbar.saveHierarchyState(var1);
   }

   public void setBackgroundDrawable(Drawable var1) {
      ViewCompat.setBackground(this.mToolbar, var1);
   }

   public void setCollapsible(boolean var1) {
      this.mToolbar.setCollapsible(var1);
   }

   public void setCustomView(View var1) {
      View var2 = this.mCustomView;
      if (var2 != null && (this.mDisplayOpts & 16) != 0) {
         this.mToolbar.removeView(var2);
      }

      this.mCustomView = var1;
      if (var1 != null && (this.mDisplayOpts & 16) != 0) {
         this.mToolbar.addView(var1);
      }

   }

   public void setDefaultNavigationContentDescription(int var1) {
      if (var1 != this.mDefaultNavigationContentDescription) {
         this.mDefaultNavigationContentDescription = var1;
         if (TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
            this.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
         }

      }
   }

   public void setDefaultNavigationIcon(Drawable var1) {
      if (this.mDefaultNavigationIcon != var1) {
         this.mDefaultNavigationIcon = var1;
         this.updateNavigationIcon();
      }

   }

   public void setDisplayOptions(int var1) {
      int var2 = this.mDisplayOpts ^ var1;
      this.mDisplayOpts = var1;
      if (var2 != 0) {
         if ((var2 & 4) != 0) {
            if ((var1 & 4) != 0) {
               this.updateHomeAccessibility();
            }

            this.updateNavigationIcon();
         }

         if ((var2 & 3) != 0) {
            this.updateToolbarLogo();
         }

         if ((var2 & 8) != 0) {
            if ((var1 & 8) != 0) {
               this.mToolbar.setTitle(this.mTitle);
               this.mToolbar.setSubtitle(this.mSubtitle);
            } else {
               this.mToolbar.setTitle((CharSequence)null);
               this.mToolbar.setSubtitle((CharSequence)null);
            }
         }

         if ((var2 & 16) != 0) {
            View var3 = this.mCustomView;
            if (var3 != null) {
               if ((var1 & 16) != 0) {
                  this.mToolbar.addView(var3);
               } else {
                  this.mToolbar.removeView(var3);
               }
            }
         }
      }

   }

   public void setDropdownParams(SpinnerAdapter var1, AdapterView.OnItemSelectedListener var2) {
      this.ensureSpinner();
      this.mSpinner.setAdapter(var1);
      this.mSpinner.setOnItemSelectedListener(var2);
   }

   public void setDropdownSelectedPosition(int var1) {
      Spinner var2 = this.mSpinner;
      if (var2 != null) {
         var2.setSelection(var1);
      } else {
         throw new IllegalStateException("Can't set dropdown selected position without an adapter");
      }
   }

   public void setEmbeddedTabView(ScrollingTabContainerView var1) {
      View var2 = this.mTabView;
      if (var2 != null) {
         ViewParent var4 = var2.getParent();
         Toolbar var3 = this.mToolbar;
         if (var4 == var3) {
            var3.removeView(this.mTabView);
         }
      }

      this.mTabView = var1;
      if (var1 != null && this.mNavigationMode == 2) {
         this.mToolbar.addView(var1, 0);
         Toolbar.LayoutParams var5 = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
         var5.width = -2;
         var5.height = -2;
         var5.gravity = 8388691;
         var1.setAllowCollapse(true);
      }

   }

   public void setHomeButtonEnabled(boolean var1) {
   }

   public void setIcon(int var1) {
      Drawable var2;
      if (var1 != 0) {
         var2 = AppCompatResources.getDrawable(this.getContext(), var1);
      } else {
         var2 = null;
      }

      this.setIcon(var2);
   }

   public void setIcon(Drawable var1) {
      this.mIcon = var1;
      this.updateToolbarLogo();
   }

   public void setLogo(int var1) {
      Drawable var2;
      if (var1 != 0) {
         var2 = AppCompatResources.getDrawable(this.getContext(), var1);
      } else {
         var2 = null;
      }

      this.setLogo(var2);
   }

   public void setLogo(Drawable var1) {
      this.mLogo = var1;
      this.updateToolbarLogo();
   }

   public void setMenu(Menu var1, MenuPresenter.Callback var2) {
      if (this.mActionMenuPresenter == null) {
         ActionMenuPresenter var3 = new ActionMenuPresenter(this.mToolbar.getContext());
         this.mActionMenuPresenter = var3;
         var3.setId(R.id.action_menu_presenter);
      }

      this.mActionMenuPresenter.setCallback(var2);
      this.mToolbar.setMenu((MenuBuilder)var1, this.mActionMenuPresenter);
   }

   public void setMenuCallbacks(MenuPresenter.Callback var1, MenuBuilder.Callback var2) {
      this.mToolbar.setMenuCallbacks(var1, var2);
   }

   public void setMenuPrepared() {
      this.mMenuPrepared = true;
   }

   public void setNavigationContentDescription(int var1) {
      String var2;
      if (var1 == 0) {
         var2 = null;
      } else {
         var2 = this.getContext().getString(var1);
      }

      this.setNavigationContentDescription(var2);
   }

   public void setNavigationContentDescription(CharSequence var1) {
      this.mHomeDescription = var1;
      this.updateHomeAccessibility();
   }

   public void setNavigationIcon(int var1) {
      Drawable var2;
      if (var1 != 0) {
         var2 = AppCompatResources.getDrawable(this.getContext(), var1);
      } else {
         var2 = null;
      }

      this.setNavigationIcon(var2);
   }

   public void setNavigationIcon(Drawable var1) {
      this.mNavIcon = var1;
      this.updateNavigationIcon();
   }

   public void setNavigationMode(int var1) {
      int var2 = this.mNavigationMode;
      if (var1 != var2) {
         View var3;
         if (var2 != 1) {
            if (var2 == 2) {
               var3 = this.mTabView;
               if (var3 != null) {
                  ViewParent var5 = var3.getParent();
                  Toolbar var4 = this.mToolbar;
                  if (var5 == var4) {
                     var4.removeView(this.mTabView);
                  }
               }
            }
         } else {
            Spinner var6 = this.mSpinner;
            if (var6 != null) {
               ViewParent var8 = var6.getParent();
               Toolbar var7 = this.mToolbar;
               if (var8 == var7) {
                  var7.removeView(this.mSpinner);
               }
            }
         }

         this.mNavigationMode = var1;
         if (var1 != 0) {
            if (var1 != 1) {
               if (var1 != 2) {
                  throw new IllegalArgumentException("Invalid navigation mode " + var1);
               }

               var3 = this.mTabView;
               if (var3 != null) {
                  this.mToolbar.addView(var3, 0);
                  Toolbar.LayoutParams var9 = (Toolbar.LayoutParams)this.mTabView.getLayoutParams();
                  var9.width = -2;
                  var9.height = -2;
                  var9.gravity = 8388691;
               }
            } else {
               this.ensureSpinner();
               this.mToolbar.addView(this.mSpinner, 0);
            }
         }
      }

   }

   public void setSubtitle(CharSequence var1) {
      this.mSubtitle = var1;
      if ((this.mDisplayOpts & 8) != 0) {
         this.mToolbar.setSubtitle(var1);
      }

   }

   public void setTitle(CharSequence var1) {
      this.mTitleSet = true;
      this.setTitleInt(var1);
   }

   public void setVisibility(int var1) {
      this.mToolbar.setVisibility(var1);
   }

   public void setWindowCallback(Window.Callback var1) {
      this.mWindowCallback = var1;
   }

   public void setWindowTitle(CharSequence var1) {
      if (!this.mTitleSet) {
         this.setTitleInt(var1);
      }

   }

   public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int var1, long var2) {
      ViewPropertyAnimatorCompat var5 = ViewCompat.animate(this.mToolbar);
      float var4;
      if (var1 == 0) {
         var4 = 1.0F;
      } else {
         var4 = 0.0F;
      }

      return var5.alpha(var4).setDuration(var2).setListener(new ViewPropertyAnimatorListenerAdapter(this, var1) {
         private boolean mCanceled;
         final ToolbarWidgetWrapper this$0;
         final int val$visibility;

         {
            this.this$0 = var1;
            this.val$visibility = var2;
            this.mCanceled = false;
         }

         public void onAnimationCancel(View var1) {
            this.mCanceled = true;
         }

         public void onAnimationEnd(View var1) {
            if (!this.mCanceled) {
               this.this$0.mToolbar.setVisibility(this.val$visibility);
            }

         }

         public void onAnimationStart(View var1) {
            this.this$0.mToolbar.setVisibility(0);
         }
      });
   }

   public boolean showOverflowMenu() {
      return this.mToolbar.showOverflowMenu();
   }
}
