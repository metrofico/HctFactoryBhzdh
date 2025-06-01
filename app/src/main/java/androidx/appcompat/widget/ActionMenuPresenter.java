package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopup;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ActionProvider;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
   private static final String TAG = "ActionMenuPresenter";
   private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
   ActionButtonSubmenu mActionButtonPopup;
   private int mActionItemWidthLimit;
   private boolean mExpandedActionViewsExclusive;
   private int mMaxItems;
   private boolean mMaxItemsSet;
   private int mMinCellSize;
   int mOpenSubMenuId;
   OverflowMenuButton mOverflowButton;
   OverflowPopup mOverflowPopup;
   private Drawable mPendingOverflowIcon;
   private boolean mPendingOverflowIconSet;
   private ActionMenuPopupCallback mPopupCallback;
   final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback(this);
   OpenOverflowRunnable mPostedOpenRunnable;
   private boolean mReserveOverflow;
   private boolean mReserveOverflowSet;
   private boolean mStrictWidthLimit;
   private int mWidthLimit;
   private boolean mWidthLimitSet;

   public ActionMenuPresenter(Context var1) {
      super(var1, R.layout.abc_action_menu_layout, R.layout.abc_action_menu_item_layout);
   }

   private View findViewForItem(MenuItem var1) {
      ViewGroup var5 = (ViewGroup)this.mMenuView;
      if (var5 == null) {
         return null;
      } else {
         int var3 = var5.getChildCount();

         for(int var2 = 0; var2 < var3; ++var2) {
            View var4 = var5.getChildAt(var2);
            if (var4 instanceof MenuView.ItemView && ((MenuView.ItemView)var4).getItemData() == var1) {
               return var4;
            }
         }

         return null;
      }
   }

   public void bindItemView(MenuItemImpl var1, MenuView.ItemView var2) {
      var2.initialize(var1, 0);
      ActionMenuView var3 = (ActionMenuView)this.mMenuView;
      ActionMenuItemView var4 = (ActionMenuItemView)var2;
      var4.setItemInvoker(var3);
      if (this.mPopupCallback == null) {
         this.mPopupCallback = new ActionMenuPopupCallback(this);
      }

      var4.setPopupCallback(this.mPopupCallback);
   }

   public boolean dismissPopupMenus() {
      return this.hideOverflowMenu() | this.hideSubMenus();
   }

   public boolean filterLeftoverView(ViewGroup var1, int var2) {
      return var1.getChildAt(var2) == this.mOverflowButton ? false : super.filterLeftoverView(var1, var2);
   }

   public boolean flagActionItems() {
      MenuBuilder var17 = this.mMenu;
      int var11 = 0;
      int var4;
      ArrayList var25;
      if (var17 != null) {
         var25 = this.mMenu.getVisibleItems();
         var4 = var25.size();
      } else {
         var25 = null;
         var4 = 0;
      }

      int var7 = this.mMaxItems;
      int var10 = this.mActionItemWidthLimit;
      int var12 = MeasureSpec.makeMeasureSpec(0, 0);
      ViewGroup var18 = (ViewGroup)this.mMenuView;
      int var5 = 0;
      int var6 = 0;
      int var2 = var6;
      int var3 = var6;

      int var1;
      MenuItemImpl var19;
      for(var1 = var7; var5 < var4; var1 = var7) {
         var19 = (MenuItemImpl)var25.get(var5);
         if (var19.requiresActionButton()) {
            ++var3;
         } else if (var19.requestsActionButton()) {
            ++var2;
         } else {
            var6 = 1;
         }

         var7 = var1;
         if (this.mExpandedActionViewsExclusive) {
            var7 = var1;
            if (var19.isActionViewExpanded()) {
               var7 = 0;
            }
         }

         ++var5;
      }

      var5 = var1;
      if (this.mReserveOverflow) {
         label149: {
            if (var6 == 0) {
               var5 = var1;
               if (var2 + var3 <= var1) {
                  break label149;
               }
            }

            var5 = var1 - 1;
         }
      }

      var3 = var5 - var3;
      SparseBooleanArray var20 = this.mActionButtonGroups;
      var20.clear();
      int var8;
      if (this.mStrictWidthLimit) {
         var1 = this.mMinCellSize;
         var2 = var10 / var1;
         var8 = var1 + var10 % var1 / var2;
      } else {
         var8 = 0;
         var2 = 0;
      }

      int var9 = 0;
      var1 = 0;
      var6 = var10;
      var10 = var4;

      for(byte var23 = (byte)var11; var9 < var10; var1 = var5) {
         var19 = (MenuItemImpl)var25.get(var9);
         View var21;
         byte var24;
         if (var19.requiresActionButton()) {
            var21 = this.getItemView(var19, (View)null, var18);
            if (this.mStrictWidthLimit) {
               var2 -= ActionMenuView.measureChildForCells(var21, var8, var2, var12, var23);
            } else {
               var21.measure(var12, var12);
            }

            var7 = var21.getMeasuredWidth();
            var6 -= var7;
            var5 = var1;
            if (var1 == 0) {
               var5 = var7;
            }

            var1 = var19.getGroupId();
            if (var1 != 0) {
               var20.put(var1, true);
            }

            var19.setIsActionButton(true);
            var24 = var23;
         } else if (!var19.requestsActionButton()) {
            var19.setIsActionButton((boolean)var23);
            var5 = var1;
            var24 = var23;
         } else {
            var11 = var19.getGroupId();
            boolean var16 = var20.get(var11);
            boolean var13;
            if (var3 <= 0 && !var16 || var6 <= 0 || this.mStrictWidthLimit && var2 <= 0) {
               var13 = false;
            } else {
               var13 = true;
            }

            boolean var15 = var13;
            boolean var14 = var13;
            var7 = var6;
            var5 = var2;
            var4 = var1;
            if (var13) {
               var21 = this.getItemView(var19, (View)null, var18);
               if (this.mStrictWidthLimit) {
                  var5 = ActionMenuView.measureChildForCells(var21, var8, var2, var12, 0);
                  var4 = var2 - var5;
                  var2 = var4;
                  if (var5 == 0) {
                     var15 = false;
                     var2 = var4;
                  }
               } else {
                  var21.measure(var12, var12);
               }

               var5 = var21.getMeasuredWidth();
               var7 = var6 - var5;
               var4 = var1;
               if (var1 == 0) {
                  var4 = var5;
               }

               boolean var22;
               label121: {
                  label120: {
                     if (this.mStrictWidthLimit) {
                        if (var7 >= 0) {
                           break label120;
                        }
                     } else if (var7 + var4 > 0) {
                        break label120;
                     }

                     var22 = false;
                     break label121;
                  }

                  var22 = true;
               }

               var14 = var15 & var22;
               var5 = var2;
            }

            if (var14 && var11 != 0) {
               var20.put(var11, true);
               var1 = var3;
            } else {
               var1 = var3;
               if (var16) {
                  var20.put(var11, false);
                  var2 = 0;

                  while(true) {
                     var1 = var3;
                     if (var2 >= var9) {
                        break;
                     }

                     MenuItemImpl var26 = (MenuItemImpl)var25.get(var2);
                     var1 = var3;
                     if (var26.getGroupId() == var11) {
                        var1 = var3;
                        if (var26.isActionButton()) {
                           var1 = var3 + 1;
                        }

                        var26.setIsActionButton(false);
                     }

                     ++var2;
                     var3 = var1;
                  }
               }
            }

            var2 = var1;
            if (var14) {
               var2 = var1 - 1;
            }

            var19.setIsActionButton(var14);
            var24 = 0;
            var3 = var2;
            var6 = var7;
            var2 = var5;
            var5 = var4;
         }

         ++var9;
         var23 = var24;
      }

      return true;
   }

   public View getItemView(MenuItemImpl var1, View var2, ViewGroup var3) {
      View var5 = var1.getActionView();
      if (var5 == null || var1.hasCollapsibleActionView()) {
         var5 = super.getItemView(var1, var2, var3);
      }

      byte var4;
      if (var1.isActionViewExpanded()) {
         var4 = 8;
      } else {
         var4 = 0;
      }

      var5.setVisibility(var4);
      ActionMenuView var7 = (ActionMenuView)var3;
      ViewGroup.LayoutParams var6 = var5.getLayoutParams();
      if (!var7.checkLayoutParams(var6)) {
         var5.setLayoutParams(var7.generateLayoutParams(var6));
      }

      return var5;
   }

   public MenuView getMenuView(ViewGroup var1) {
      MenuView var2 = this.mMenuView;
      MenuView var3 = super.getMenuView(var1);
      if (var2 != var3) {
         ((ActionMenuView)var3).setPresenter(this);
      }

      return var3;
   }

   public Drawable getOverflowIcon() {
      OverflowMenuButton var1 = this.mOverflowButton;
      if (var1 != null) {
         return var1.getDrawable();
      } else {
         return this.mPendingOverflowIconSet ? this.mPendingOverflowIcon : null;
      }
   }

   public boolean hideOverflowMenu() {
      if (this.mPostedOpenRunnable != null && this.mMenuView != null) {
         ((View)this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
         this.mPostedOpenRunnable = null;
         return true;
      } else {
         OverflowPopup var1 = this.mOverflowPopup;
         if (var1 != null) {
            var1.dismiss();
            return true;
         } else {
            return false;
         }
      }
   }

   public boolean hideSubMenus() {
      ActionButtonSubmenu var1 = this.mActionButtonPopup;
      if (var1 != null) {
         var1.dismiss();
         return true;
      } else {
         return false;
      }
   }

   public void initForMenu(Context var1, MenuBuilder var2) {
      super.initForMenu(var1, var2);
      Resources var7 = var1.getResources();
      ActionBarPolicy var5 = ActionBarPolicy.get(var1);
      if (!this.mReserveOverflowSet) {
         this.mReserveOverflow = var5.showsOverflowMenuButton();
      }

      if (!this.mWidthLimitSet) {
         this.mWidthLimit = var5.getEmbeddedMenuWidthLimit();
      }

      if (!this.mMaxItemsSet) {
         this.mMaxItems = var5.getMaxActionButtons();
      }

      int var3 = this.mWidthLimit;
      if (this.mReserveOverflow) {
         if (this.mOverflowButton == null) {
            OverflowMenuButton var6 = new OverflowMenuButton(this, this.mSystemContext);
            this.mOverflowButton = var6;
            if (this.mPendingOverflowIconSet) {
               var6.setImageDrawable(this.mPendingOverflowIcon);
               this.mPendingOverflowIcon = null;
               this.mPendingOverflowIconSet = false;
            }

            int var4 = MeasureSpec.makeMeasureSpec(0, 0);
            this.mOverflowButton.measure(var4, var4);
         }

         var3 -= this.mOverflowButton.getMeasuredWidth();
      } else {
         this.mOverflowButton = null;
      }

      this.mActionItemWidthLimit = var3;
      this.mMinCellSize = (int)(var7.getDisplayMetrics().density * 56.0F);
   }

   public boolean isOverflowMenuShowPending() {
      boolean var1;
      if (this.mPostedOpenRunnable == null && !this.isOverflowMenuShowing()) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public boolean isOverflowMenuShowing() {
      OverflowPopup var2 = this.mOverflowPopup;
      boolean var1;
      if (var2 != null && var2.isShowing()) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   public boolean isOverflowReserved() {
      return this.mReserveOverflow;
   }

   public void onCloseMenu(MenuBuilder var1, boolean var2) {
      this.dismissPopupMenus();
      super.onCloseMenu(var1, var2);
   }

   public void onConfigurationChanged(Configuration var1) {
      if (!this.mMaxItemsSet) {
         this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
      }

      if (this.mMenu != null) {
         this.mMenu.onItemsChanged(true);
      }

   }

   public void onRestoreInstanceState(Parcelable var1) {
      if (var1 instanceof SavedState) {
         SavedState var2 = (SavedState)var1;
         if (var2.openSubMenuId > 0) {
            MenuItem var3 = this.mMenu.findItem(var2.openSubMenuId);
            if (var3 != null) {
               this.onSubMenuSelected((SubMenuBuilder)var3.getSubMenu());
            }
         }

      }
   }

   public Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState();
      var1.openSubMenuId = this.mOpenSubMenuId;
      return var1;
   }

   public boolean onSubMenuSelected(SubMenuBuilder var1) {
      boolean var4 = var1.hasVisibleItems();
      boolean var5 = false;
      if (!var4) {
         return false;
      } else {
         SubMenuBuilder var6;
         for(var6 = var1; var6.getParentMenu() != this.mMenu; var6 = (SubMenuBuilder)var6.getParentMenu()) {
         }

         View var7 = this.findViewForItem(var6.getItem());
         if (var7 == null) {
            return false;
         } else {
            this.mOpenSubMenuId = var1.getItem().getItemId();
            int var3 = var1.size();
            int var2 = 0;

            while(true) {
               var4 = var5;
               if (var2 >= var3) {
                  break;
               }

               MenuItem var8 = var1.getItem(var2);
               if (var8.isVisible() && var8.getIcon() != null) {
                  var4 = true;
                  break;
               }

               ++var2;
            }

            ActionButtonSubmenu var9 = new ActionButtonSubmenu(this, this.mContext, var1, var7);
            this.mActionButtonPopup = var9;
            var9.setForceShowIcon(var4);
            this.mActionButtonPopup.show();
            super.onSubMenuSelected(var1);
            return true;
         }
      }
   }

   public void onSubUiVisibilityChanged(boolean var1) {
      if (var1) {
         super.onSubMenuSelected((SubMenuBuilder)null);
      } else if (this.mMenu != null) {
         this.mMenu.close(false);
      }

   }

   public void setExpandedActionViewsExclusive(boolean var1) {
      this.mExpandedActionViewsExclusive = var1;
   }

   public void setItemLimit(int var1) {
      this.mMaxItems = var1;
      this.mMaxItemsSet = true;
   }

   public void setMenuView(ActionMenuView var1) {
      this.mMenuView = var1;
      var1.initialize(this.mMenu);
   }

   public void setOverflowIcon(Drawable var1) {
      OverflowMenuButton var2 = this.mOverflowButton;
      if (var2 != null) {
         var2.setImageDrawable(var1);
      } else {
         this.mPendingOverflowIconSet = true;
         this.mPendingOverflowIcon = var1;
      }

   }

   public void setReserveOverflow(boolean var1) {
      this.mReserveOverflow = var1;
      this.mReserveOverflowSet = true;
   }

   public void setWidthLimit(int var1, boolean var2) {
      this.mWidthLimit = var1;
      this.mStrictWidthLimit = var2;
      this.mWidthLimitSet = true;
   }

   public boolean shouldIncludeItem(int var1, MenuItemImpl var2) {
      return var2.isActionButton();
   }

   public boolean showOverflowMenu() {
      if (this.mReserveOverflow && !this.isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.getNonActionItems().isEmpty()) {
         this.mPostedOpenRunnable = new OpenOverflowRunnable(this, new OverflowPopup(this, this.mContext, this.mMenu, this.mOverflowButton, true));
         ((View)this.mMenuView).post(this.mPostedOpenRunnable);
         return true;
      } else {
         return false;
      }
   }

   public void updateMenuView(boolean var1) {
      super.updateMenuView(var1);
      ((View)this.mMenuView).requestLayout();
      MenuBuilder var5 = this.mMenu;
      boolean var3 = false;
      int var4;
      if (var5 != null) {
         ArrayList var6 = this.mMenu.getActionItems();
         var4 = var6.size();

         for(int var2 = 0; var2 < var4; ++var2) {
            ActionProvider var8 = ((MenuItemImpl)var6.get(var2)).getSupportActionProvider();
            if (var8 != null) {
               var8.setSubUiVisibilityListener(this);
            }
         }
      }

      ArrayList var9;
      if (this.mMenu != null) {
         var9 = this.mMenu.getNonActionItems();
      } else {
         var9 = null;
      }

      boolean var7 = var3;
      if (this.mReserveOverflow) {
         var7 = var3;
         if (var9 != null) {
            var4 = var9.size();
            if (var4 == 1) {
               var7 = ((MenuItemImpl)var9.get(0)).isActionViewExpanded() ^ true;
            } else {
               var7 = var3;
               if (var4 > 0) {
                  var7 = true;
               }
            }
         }
      }

      if (var7) {
         if (this.mOverflowButton == null) {
            this.mOverflowButton = new OverflowMenuButton(this, this.mSystemContext);
         }

         ViewGroup var10 = (ViewGroup)this.mOverflowButton.getParent();
         if (var10 != this.mMenuView) {
            if (var10 != null) {
               var10.removeView(this.mOverflowButton);
            }

            ActionMenuView var11 = (ActionMenuView)this.mMenuView;
            var11.addView(this.mOverflowButton, var11.generateOverflowButtonLayoutParams());
         }
      } else {
         OverflowMenuButton var12 = this.mOverflowButton;
         if (var12 != null && var12.getParent() == this.mMenuView) {
            ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
         }
      }

      ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
   }

   private class ActionButtonSubmenu extends MenuPopupHelper {
      final ActionMenuPresenter this$0;

      public ActionButtonSubmenu(ActionMenuPresenter var1, Context var2, SubMenuBuilder var3, View var4) {
         super(var2, var3, var4, false, R.attr.actionOverflowMenuStyle);
         this.this$0 = var1;
         if (!((MenuItemImpl)var3.getItem()).isActionButton()) {
            Object var5;
            if (var1.mOverflowButton == null) {
               var5 = (View)var1.mMenuView;
            } else {
               var5 = var1.mOverflowButton;
            }

            this.setAnchorView((View)var5);
         }

         this.setPresenterCallback(var1.mPopupPresenterCallback);
      }

      protected void onDismiss() {
         this.this$0.mActionButtonPopup = null;
         this.this$0.mOpenSubMenuId = 0;
         super.onDismiss();
      }
   }

   private class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
      final ActionMenuPresenter this$0;

      ActionMenuPopupCallback(ActionMenuPresenter var1) {
         this.this$0 = var1;
      }

      public ShowableListMenu getPopup() {
         MenuPopup var1;
         if (this.this$0.mActionButtonPopup != null) {
            var1 = this.this$0.mActionButtonPopup.getPopup();
         } else {
            var1 = null;
         }

         return var1;
      }
   }

   private class OpenOverflowRunnable implements Runnable {
      private OverflowPopup mPopup;
      final ActionMenuPresenter this$0;

      public OpenOverflowRunnable(ActionMenuPresenter var1, OverflowPopup var2) {
         this.this$0 = var1;
         this.mPopup = var2;
      }

      public void run() {
         if (this.this$0.mMenu != null) {
            this.this$0.mMenu.changeMenuMode();
         }

         View var1 = (View)this.this$0.mMenuView;
         if (var1 != null && var1.getWindowToken() != null && this.mPopup.tryShow()) {
            this.this$0.mOverflowPopup = this.mPopup;
         }

         this.this$0.mPostedOpenRunnable = null;
      }
   }

   private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
      final ActionMenuPresenter this$0;

      public OverflowMenuButton(ActionMenuPresenter var1, Context var2) {
         super(var2, (AttributeSet)null, R.attr.actionOverflowButtonStyle);
         this.this$0 = var1;
         this.setClickable(true);
         this.setFocusable(true);
         this.setVisibility(0);
         this.setEnabled(true);
         TooltipCompat.setTooltipText(this, this.getContentDescription());
         this.setOnTouchListener(new ForwardingListener(this, this, var1) {
            final OverflowMenuButton this$1;
            final ActionMenuPresenter val$this$0;

            {
               this.this$1 = var1;
               this.val$this$0 = var3;
            }

            public ShowableListMenu getPopup() {
               return this.this$1.this$0.mOverflowPopup == null ? null : this.this$1.this$0.mOverflowPopup.getPopup();
            }

            public boolean onForwardingStarted() {
               this.this$1.this$0.showOverflowMenu();
               return true;
            }

            public boolean onForwardingStopped() {
               if (this.this$1.this$0.mPostedOpenRunnable != null) {
                  return false;
               } else {
                  this.this$1.this$0.hideOverflowMenu();
                  return true;
               }
            }
         });
      }

      public boolean needsDividerAfter() {
         return false;
      }

      public boolean needsDividerBefore() {
         return false;
      }

      public boolean performClick() {
         if (super.performClick()) {
            return true;
         } else {
            this.playSoundEffect(0);
            this.this$0.showOverflowMenu();
            return true;
         }
      }

      protected boolean setFrame(int var1, int var2, int var3, int var4) {
         boolean var8 = super.setFrame(var1, var2, var3, var4);
         Drawable var9 = this.getDrawable();
         Drawable var10 = this.getBackground();
         if (var9 != null && var10 != null) {
            int var6 = this.getWidth();
            var3 = this.getHeight();
            var1 = Math.max(var6, var3) / 2;
            int var5 = this.getPaddingLeft();
            int var7 = this.getPaddingRight();
            var2 = this.getPaddingTop();
            var4 = this.getPaddingBottom();
            var5 = (var6 + (var5 - var7)) / 2;
            var2 = (var3 + (var2 - var4)) / 2;
            DrawableCompat.setHotspotBounds(var10, var5 - var1, var2 - var1, var5 + var1, var2 + var1);
         }

         return var8;
      }
   }

   private class OverflowPopup extends MenuPopupHelper {
      final ActionMenuPresenter this$0;

      public OverflowPopup(ActionMenuPresenter var1, Context var2, MenuBuilder var3, View var4, boolean var5) {
         super(var2, var3, var4, var5, R.attr.actionOverflowMenuStyle);
         this.this$0 = var1;
         this.setGravity(8388613);
         this.setPresenterCallback(var1.mPopupPresenterCallback);
      }

      protected void onDismiss() {
         if (this.this$0.mMenu != null) {
            this.this$0.mMenu.close();
         }

         this.this$0.mOverflowPopup = null;
         super.onDismiss();
      }
   }

   private class PopupPresenterCallback implements MenuPresenter.Callback {
      final ActionMenuPresenter this$0;

      PopupPresenterCallback(ActionMenuPresenter var1) {
         this.this$0 = var1;
      }

      public void onCloseMenu(MenuBuilder var1, boolean var2) {
         if (var1 instanceof SubMenuBuilder) {
            var1.getRootMenu().close(false);
         }

         MenuPresenter.Callback var3 = this.this$0.getCallback();
         if (var3 != null) {
            var3.onCloseMenu(var1, var2);
         }

      }

      public boolean onOpenSubMenu(MenuBuilder var1) {
         MenuBuilder var3 = this.this$0.mMenu;
         boolean var2 = false;
         if (var1 == var3) {
            return false;
         } else {
            this.this$0.mOpenSubMenuId = ((SubMenuBuilder)var1).getItem().getItemId();
            MenuPresenter.Callback var4 = this.this$0.getCallback();
            if (var4 != null) {
               var2 = var4.onOpenSubMenu(var1);
            }

            return var2;
         }
      }
   }

   private static class SavedState implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      public int openSubMenuId;

      SavedState() {
      }

      SavedState(Parcel var1) {
         this.openSubMenuId = var1.readInt();
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel var1, int var2) {
         var1.writeInt(this.openSubMenuId);
      }
   }
}
