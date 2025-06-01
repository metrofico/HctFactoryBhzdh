package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class BaseMenuPresenter implements MenuPresenter {
   private MenuPresenter.Callback mCallback;
   protected Context mContext;
   private int mId;
   protected LayoutInflater mInflater;
   private int mItemLayoutRes;
   protected MenuBuilder mMenu;
   private int mMenuLayoutRes;
   protected MenuView mMenuView;
   protected Context mSystemContext;
   protected LayoutInflater mSystemInflater;

   public BaseMenuPresenter(Context var1, int var2, int var3) {
      this.mSystemContext = var1;
      this.mSystemInflater = LayoutInflater.from(var1);
      this.mMenuLayoutRes = var2;
      this.mItemLayoutRes = var3;
   }

   protected void addItemView(View var1, int var2) {
      ViewGroup var3 = (ViewGroup)var1.getParent();
      if (var3 != null) {
         var3.removeView(var1);
      }

      ((ViewGroup)this.mMenuView).addView(var1, var2);
   }

   public abstract void bindItemView(MenuItemImpl var1, MenuView.ItemView var2);

   public boolean collapseItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   public MenuView.ItemView createItemView(ViewGroup var1) {
      return (MenuView.ItemView)this.mSystemInflater.inflate(this.mItemLayoutRes, var1, false);
   }

   public boolean expandItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   protected boolean filterLeftoverView(ViewGroup var1, int var2) {
      var1.removeViewAt(var2);
      return true;
   }

   public boolean flagActionItems() {
      return false;
   }

   public MenuPresenter.Callback getCallback() {
      return this.mCallback;
   }

   public int getId() {
      return this.mId;
   }

   public View getItemView(MenuItemImpl var1, View var2, ViewGroup var3) {
      MenuView.ItemView var4;
      if (var2 instanceof MenuView.ItemView) {
         var4 = (MenuView.ItemView)var2;
      } else {
         var4 = this.createItemView(var3);
      }

      this.bindItemView(var1, var4);
      return (View)var4;
   }

   public MenuView getMenuView(ViewGroup var1) {
      if (this.mMenuView == null) {
         MenuView var2 = (MenuView)this.mSystemInflater.inflate(this.mMenuLayoutRes, var1, false);
         this.mMenuView = var2;
         var2.initialize(this.mMenu);
         this.updateMenuView(true);
      }

      return this.mMenuView;
   }

   public void initForMenu(Context var1, MenuBuilder var2) {
      this.mContext = var1;
      this.mInflater = LayoutInflater.from(var1);
      this.mMenu = var2;
   }

   public void onCloseMenu(MenuBuilder var1, boolean var2) {
      MenuPresenter.Callback var3 = this.mCallback;
      if (var3 != null) {
         var3.onCloseMenu(var1, var2);
      }

   }

   public boolean onSubMenuSelected(SubMenuBuilder var1) {
      MenuPresenter.Callback var2 = this.mCallback;
      if (var2 != null) {
         if (var1 == null) {
            var1 = this.mMenu;
         }

         return var2.onOpenSubMenu((MenuBuilder)var1);
      } else {
         return false;
      }
   }

   public void setCallback(MenuPresenter.Callback var1) {
      this.mCallback = var1;
   }

   public void setId(int var1) {
      this.mId = var1;
   }

   public boolean shouldIncludeItem(int var1, MenuItemImpl var2) {
      return true;
   }

   public void updateMenuView(boolean var1) {
      ViewGroup var7 = (ViewGroup)this.mMenuView;
      if (var7 != null) {
         MenuBuilder var6 = this.mMenu;
         int var2 = 0;
         if (var6 != null) {
            var6.flagActionItems();
            ArrayList var10 = this.mMenu.getVisibleItems();
            int var5 = var10.size();
            int var3 = 0;

            int var4;
            for(var2 = 0; var3 < var5; var2 = var4) {
               MenuItemImpl var8 = (MenuItemImpl)var10.get(var3);
               var4 = var2;
               if (this.shouldIncludeItem(var2, var8)) {
                  View var9 = var7.getChildAt(var2);
                  MenuItemImpl var12;
                  if (var9 instanceof MenuView.ItemView) {
                     var12 = ((MenuView.ItemView)var9).getItemData();
                  } else {
                     var12 = null;
                  }

                  View var11 = this.getItemView(var8, var9, var7);
                  if (var8 != var12) {
                     var11.setPressed(false);
                     var11.jumpDrawablesToCurrentState();
                  }

                  if (var11 != var9) {
                     this.addItemView(var11, var2);
                  }

                  var4 = var2 + 1;
               }

               ++var3;
            }
         }

         while(var2 < var7.getChildCount()) {
            if (!this.filterLeftoverView(var7, var2)) {
               ++var2;
            }
         }

      }
   }
}
