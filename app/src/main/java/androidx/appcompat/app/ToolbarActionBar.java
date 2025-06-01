package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SpinnerAdapter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

class ToolbarActionBar extends ActionBar {
   final DecorToolbar mDecorToolbar;
   private boolean mLastMenuVisibility;
   final AppCompatDelegateImpl.ActionBarMenuCallback mMenuCallback;
   private boolean mMenuCallbackSet;
   private final Toolbar.OnMenuItemClickListener mMenuClicker;
   private final Runnable mMenuInvalidator = new Runnable(this) {
      final ToolbarActionBar this$0;

      {
         this.this$0 = var1;
      }

      public void run() {
         this.this$0.populateOptionsMenu();
      }
   };
   private ArrayList mMenuVisibilityListeners = new ArrayList();
   boolean mToolbarMenuPrepared;
   final Window.Callback mWindowCallback;

   ToolbarActionBar(Toolbar var1, CharSequence var2, Window.Callback var3) {
      Toolbar.OnMenuItemClickListener var4 = new Toolbar.OnMenuItemClickListener(this) {
         final ToolbarActionBar this$0;

         {
            this.this$0 = var1;
         }

         public boolean onMenuItemClick(MenuItem var1) {
            return this.this$0.mWindowCallback.onMenuItemSelected(0, var1);
         }
      };
      this.mMenuClicker = var4;
      Preconditions.checkNotNull(var1);
      ToolbarWidgetWrapper var5 = new ToolbarWidgetWrapper(var1, false);
      this.mDecorToolbar = var5;
      this.mWindowCallback = (Window.Callback)Preconditions.checkNotNull(var3);
      var5.setWindowCallback(var3);
      var1.setOnMenuItemClickListener(var4);
      var5.setWindowTitle(var2);
      this.mMenuCallback = new ToolbarMenuCallback(this);
   }

   private Menu getMenu() {
      if (!this.mMenuCallbackSet) {
         this.mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(this), new MenuBuilderCallback(this));
         this.mMenuCallbackSet = true;
      }

      return this.mDecorToolbar.getMenu();
   }

   public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener var1) {
      this.mMenuVisibilityListeners.add(var1);
   }

   public void addTab(ActionBar.Tab var1) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public void addTab(ActionBar.Tab var1, int var2) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public void addTab(ActionBar.Tab var1, int var2, boolean var3) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public void addTab(ActionBar.Tab var1, boolean var2) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public boolean closeOptionsMenu() {
      return this.mDecorToolbar.hideOverflowMenu();
   }

   public boolean collapseActionView() {
      if (this.mDecorToolbar.hasExpandedActionView()) {
         this.mDecorToolbar.collapseActionView();
         return true;
      } else {
         return false;
      }
   }

   public void dispatchMenuVisibilityChanged(boolean var1) {
      if (var1 != this.mLastMenuVisibility) {
         this.mLastMenuVisibility = var1;
         int var3 = this.mMenuVisibilityListeners.size();

         for(int var2 = 0; var2 < var3; ++var2) {
            ((ActionBar.OnMenuVisibilityListener)this.mMenuVisibilityListeners.get(var2)).onMenuVisibilityChanged(var1);
         }

      }
   }

   public View getCustomView() {
      return this.mDecorToolbar.getCustomView();
   }

   public int getDisplayOptions() {
      return this.mDecorToolbar.getDisplayOptions();
   }

   public float getElevation() {
      return ViewCompat.getElevation(this.mDecorToolbar.getViewGroup());
   }

   public int getHeight() {
      return this.mDecorToolbar.getHeight();
   }

   public int getNavigationItemCount() {
      return 0;
   }

   public int getNavigationMode() {
      return 0;
   }

   public int getSelectedNavigationIndex() {
      return -1;
   }

   public ActionBar.Tab getSelectedTab() {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public CharSequence getSubtitle() {
      return this.mDecorToolbar.getSubtitle();
   }

   public ActionBar.Tab getTabAt(int var1) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public int getTabCount() {
      return 0;
   }

   public Context getThemedContext() {
      return this.mDecorToolbar.getContext();
   }

   public CharSequence getTitle() {
      return this.mDecorToolbar.getTitle();
   }

   public void hide() {
      this.mDecorToolbar.setVisibility(8);
   }

   public boolean invalidateOptionsMenu() {
      this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
      ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
      return true;
   }

   public boolean isShowing() {
      boolean var1;
      if (this.mDecorToolbar.getVisibility() == 0) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isTitleTruncated() {
      return super.isTitleTruncated();
   }

   public ActionBar.Tab newTab() {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public void onConfigurationChanged(Configuration var1) {
      super.onConfigurationChanged(var1);
   }

   void onDestroy() {
      this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
   }

   public boolean onKeyShortcut(int var1, KeyEvent var2) {
      Menu var5 = this.getMenu();
      if (var5 != null) {
         int var3;
         if (var2 != null) {
            var3 = var2.getDeviceId();
         } else {
            var3 = -1;
         }

         var3 = KeyCharacterMap.load(var3).getKeyboardType();
         boolean var4 = true;
         if (var3 == 1) {
            var4 = false;
         }

         var5.setQwertyMode(var4);
         return var5.performShortcut(var1, var2, 0);
      } else {
         return false;
      }
   }

   public boolean onMenuKeyEvent(KeyEvent var1) {
      if (var1.getAction() == 1) {
         this.openOptionsMenu();
      }

      return true;
   }

   public boolean openOptionsMenu() {
      return this.mDecorToolbar.showOverflowMenu();
   }

   void populateOptionsMenu() {
      Menu var2 = this.getMenu();
      MenuBuilder var1;
      if (var2 instanceof MenuBuilder) {
         var1 = (MenuBuilder)var2;
      } else {
         var1 = null;
      }

      if (var1 != null) {
         var1.stopDispatchingItemsChanged();
      }

      try {
         var2.clear();
         if (!this.mWindowCallback.onCreatePanelMenu(0, var2) || !this.mWindowCallback.onPreparePanel(0, (View)null, var2)) {
            var2.clear();
         }
      } finally {
         if (var1 != null) {
            var1.startDispatchingItemsChanged();
         }

      }

   }

   public void removeAllTabs() {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener var1) {
      this.mMenuVisibilityListeners.remove(var1);
   }

   public void removeTab(ActionBar.Tab var1) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public void removeTabAt(int var1) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public boolean requestFocus() {
      ViewGroup var1 = this.mDecorToolbar.getViewGroup();
      if (var1 != null && !var1.hasFocus()) {
         var1.requestFocus();
         return true;
      } else {
         return false;
      }
   }

   public void selectTab(ActionBar.Tab var1) {
      throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
   }

   public void setBackgroundDrawable(Drawable var1) {
      this.mDecorToolbar.setBackgroundDrawable(var1);
   }

   public void setCustomView(int var1) {
      this.setCustomView(LayoutInflater.from(this.mDecorToolbar.getContext()).inflate(var1, this.mDecorToolbar.getViewGroup(), false));
   }

   public void setCustomView(View var1) {
      this.setCustomView(var1, new ActionBar.LayoutParams(-2, -2));
   }

   public void setCustomView(View var1, ActionBar.LayoutParams var2) {
      if (var1 != null) {
         var1.setLayoutParams(var2);
      }

      this.mDecorToolbar.setCustomView(var1);
   }

   public void setDefaultDisplayHomeAsUpEnabled(boolean var1) {
   }

   public void setDisplayHomeAsUpEnabled(boolean var1) {
      byte var2;
      if (var1) {
         var2 = 4;
      } else {
         var2 = 0;
      }

      this.setDisplayOptions(var2, 4);
   }

   public void setDisplayOptions(int var1) {
      this.setDisplayOptions(var1, -1);
   }

   public void setDisplayOptions(int var1, int var2) {
      int var3 = this.mDecorToolbar.getDisplayOptions();
      this.mDecorToolbar.setDisplayOptions(var1 & var2 | ~var2 & var3);
   }

   public void setDisplayShowCustomEnabled(boolean var1) {
      byte var2;
      if (var1) {
         var2 = 16;
      } else {
         var2 = 0;
      }

      this.setDisplayOptions(var2, 16);
   }

   public void setDisplayShowHomeEnabled(boolean var1) {
      byte var2;
      if (var1) {
         var2 = 2;
      } else {
         var2 = 0;
      }

      this.setDisplayOptions(var2, 2);
   }

   public void setDisplayShowTitleEnabled(boolean var1) {
      byte var2;
      if (var1) {
         var2 = 8;
      } else {
         var2 = 0;
      }

      this.setDisplayOptions(var2, 8);
   }

   public void setDisplayUseLogoEnabled(boolean var1) {
      this.setDisplayOptions(var1, 1);
   }

   public void setElevation(float var1) {
      ViewCompat.setElevation(this.mDecorToolbar.getViewGroup(), var1);
   }

   public void setHomeActionContentDescription(int var1) {
      this.mDecorToolbar.setNavigationContentDescription(var1);
   }

   public void setHomeActionContentDescription(CharSequence var1) {
      this.mDecorToolbar.setNavigationContentDescription(var1);
   }

   public void setHomeAsUpIndicator(int var1) {
      this.mDecorToolbar.setNavigationIcon(var1);
   }

   public void setHomeAsUpIndicator(Drawable var1) {
      this.mDecorToolbar.setNavigationIcon(var1);
   }

   public void setHomeButtonEnabled(boolean var1) {
   }

   public void setIcon(int var1) {
      this.mDecorToolbar.setIcon(var1);
   }

   public void setIcon(Drawable var1) {
      this.mDecorToolbar.setIcon(var1);
   }

   public void setListNavigationCallbacks(SpinnerAdapter var1, ActionBar.OnNavigationListener var2) {
      this.mDecorToolbar.setDropdownParams(var1, new NavItemSelectedListener(var2));
   }

   public void setLogo(int var1) {
      this.mDecorToolbar.setLogo(var1);
   }

   public void setLogo(Drawable var1) {
      this.mDecorToolbar.setLogo(var1);
   }

   public void setNavigationMode(int var1) {
      if (var1 != 2) {
         this.mDecorToolbar.setNavigationMode(var1);
      } else {
         throw new IllegalArgumentException("Tabs not supported in this configuration");
      }
   }

   public void setSelectedNavigationItem(int var1) {
      if (this.mDecorToolbar.getNavigationMode() == 1) {
         this.mDecorToolbar.setDropdownSelectedPosition(var1);
      } else {
         throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
      }
   }

   public void setShowHideAnimationEnabled(boolean var1) {
   }

   public void setSplitBackgroundDrawable(Drawable var1) {
   }

   public void setStackedBackgroundDrawable(Drawable var1) {
   }

   public void setSubtitle(int var1) {
      DecorToolbar var3 = this.mDecorToolbar;
      CharSequence var2;
      if (var1 != 0) {
         var2 = var3.getContext().getText(var1);
      } else {
         var2 = null;
      }

      var3.setSubtitle(var2);
   }

   public void setSubtitle(CharSequence var1) {
      this.mDecorToolbar.setSubtitle(var1);
   }

   public void setTitle(int var1) {
      DecorToolbar var3 = this.mDecorToolbar;
      CharSequence var2;
      if (var1 != 0) {
         var2 = var3.getContext().getText(var1);
      } else {
         var2 = null;
      }

      var3.setTitle(var2);
   }

   public void setTitle(CharSequence var1) {
      this.mDecorToolbar.setTitle(var1);
   }

   public void setWindowTitle(CharSequence var1) {
      this.mDecorToolbar.setWindowTitle(var1);
   }

   public void show() {
      this.mDecorToolbar.setVisibility(0);
   }

   private final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
      private boolean mClosingActionMenu;
      final ToolbarActionBar this$0;

      ActionMenuPresenterCallback(ToolbarActionBar var1) {
         this.this$0 = var1;
      }

      public void onCloseMenu(MenuBuilder var1, boolean var2) {
         if (!this.mClosingActionMenu) {
            this.mClosingActionMenu = true;
            this.this$0.mDecorToolbar.dismissPopupMenus();
            this.this$0.mWindowCallback.onPanelClosed(108, var1);
            this.mClosingActionMenu = false;
         }
      }

      public boolean onOpenSubMenu(MenuBuilder var1) {
         this.this$0.mWindowCallback.onMenuOpened(108, var1);
         return true;
      }
   }

   private final class MenuBuilderCallback implements MenuBuilder.Callback {
      final ToolbarActionBar this$0;

      MenuBuilderCallback(ToolbarActionBar var1) {
         this.this$0 = var1;
      }

      public boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2) {
         return false;
      }

      public void onMenuModeChange(MenuBuilder var1) {
         if (this.this$0.mDecorToolbar.isOverflowMenuShowing()) {
            this.this$0.mWindowCallback.onPanelClosed(108, var1);
         } else if (this.this$0.mWindowCallback.onPreparePanel(0, (View)null, var1)) {
            this.this$0.mWindowCallback.onMenuOpened(108, var1);
         }

      }
   }

   private class ToolbarMenuCallback implements AppCompatDelegateImpl.ActionBarMenuCallback {
      final ToolbarActionBar this$0;

      ToolbarMenuCallback(ToolbarActionBar var1) {
         this.this$0 = var1;
      }

      public View onCreatePanelView(int var1) {
         return var1 == 0 ? new View(this.this$0.mDecorToolbar.getContext()) : null;
      }

      public boolean onPreparePanel(int var1) {
         if (var1 == 0 && !this.this$0.mToolbarMenuPrepared) {
            this.this$0.mDecorToolbar.setMenuPrepared();
            this.this$0.mToolbarMenuPrepared = true;
         }

         return false;
      }
   }
}
