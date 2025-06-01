package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import java.util.ArrayList;

public class NavigationMenuPresenter implements MenuPresenter {
   private static final String STATE_ADAPTER = "android:menu:adapter";
   private static final String STATE_HEADER = "android:menu:header";
   private static final String STATE_HIERARCHY = "android:menu:list";
   NavigationMenuAdapter adapter;
   private MenuPresenter.Callback callback;
   LinearLayout headerLayout;
   ColorStateList iconTintList;
   private int id;
   Drawable itemBackground;
   int itemHorizontalPadding;
   int itemIconPadding;
   LayoutInflater layoutInflater;
   MenuBuilder menu;
   private NavigationMenuView menuView;
   final View.OnClickListener onClickListener = new View.OnClickListener(this) {
      final NavigationMenuPresenter this$0;

      {
         this.this$0 = var1;
      }

      public void onClick(View var1) {
         NavigationMenuItemView var3 = (NavigationMenuItemView)var1;
         this.this$0.setUpdateSuspended(true);
         MenuItemImpl var4 = var3.getItemData();
         boolean var2 = this.this$0.menu.performItemAction(var4, this.this$0, 0);
         if (var4 != null && var4.isCheckable() && var2) {
            this.this$0.adapter.setCheckedItem(var4);
         }

         this.this$0.setUpdateSuspended(false);
         this.this$0.updateMenuView(false);
      }
   };
   int paddingSeparator;
   private int paddingTopDefault;
   int textAppearance;
   boolean textAppearanceSet;
   ColorStateList textColor;

   public void addHeaderView(View var1) {
      this.headerLayout.addView(var1);
      NavigationMenuView var2 = this.menuView;
      var2.setPadding(0, 0, 0, var2.getPaddingBottom());
   }

   public boolean collapseItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   public void dispatchApplyWindowInsets(WindowInsetsCompat var1) {
      int var2 = var1.getSystemWindowInsetTop();
      if (this.paddingTopDefault != var2) {
         this.paddingTopDefault = var2;
         if (this.headerLayout.getChildCount() == 0) {
            NavigationMenuView var3 = this.menuView;
            var3.setPadding(0, this.paddingTopDefault, 0, var3.getPaddingBottom());
         }
      }

      ViewCompat.dispatchApplyWindowInsets(this.headerLayout, var1);
   }

   public boolean expandItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   public boolean flagActionItems() {
      return false;
   }

   public MenuItemImpl getCheckedItem() {
      return this.adapter.getCheckedItem();
   }

   public int getHeaderCount() {
      return this.headerLayout.getChildCount();
   }

   public View getHeaderView(int var1) {
      return this.headerLayout.getChildAt(var1);
   }

   public int getId() {
      return this.id;
   }

   public Drawable getItemBackground() {
      return this.itemBackground;
   }

   public int getItemHorizontalPadding() {
      return this.itemHorizontalPadding;
   }

   public int getItemIconPadding() {
      return this.itemIconPadding;
   }

   public ColorStateList getItemTextColor() {
      return this.textColor;
   }

   public ColorStateList getItemTintList() {
      return this.iconTintList;
   }

   public MenuView getMenuView(ViewGroup var1) {
      if (this.menuView == null) {
         this.menuView = (NavigationMenuView)this.layoutInflater.inflate(R.layout.design_navigation_menu, var1, false);
         if (this.adapter == null) {
            this.adapter = new NavigationMenuAdapter(this);
         }

         this.headerLayout = (LinearLayout)this.layoutInflater.inflate(R.layout.design_navigation_item_header, this.menuView, false);
         this.menuView.setAdapter(this.adapter);
      }

      return this.menuView;
   }

   public View inflateHeaderView(int var1) {
      View var2 = this.layoutInflater.inflate(var1, this.headerLayout, false);
      this.addHeaderView(var2);
      return var2;
   }

   public void initForMenu(Context var1, MenuBuilder var2) {
      this.layoutInflater = LayoutInflater.from(var1);
      this.menu = var2;
      this.paddingSeparator = var1.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
   }

   public void onCloseMenu(MenuBuilder var1, boolean var2) {
      MenuPresenter.Callback var3 = this.callback;
      if (var3 != null) {
         var3.onCloseMenu(var1, var2);
      }

   }

   public void onRestoreInstanceState(Parcelable var1) {
      if (var1 instanceof Bundle) {
         Bundle var3 = (Bundle)var1;
         SparseArray var2 = var3.getSparseParcelableArray("android:menu:list");
         if (var2 != null) {
            this.menuView.restoreHierarchyState(var2);
         }

         Bundle var5 = var3.getBundle("android:menu:adapter");
         if (var5 != null) {
            this.adapter.restoreInstanceState(var5);
         }

         SparseArray var4 = var3.getSparseParcelableArray("android:menu:header");
         if (var4 != null) {
            this.headerLayout.restoreHierarchyState(var4);
         }
      }

   }

   public Parcelable onSaveInstanceState() {
      Bundle var1 = new Bundle();
      SparseArray var2;
      if (this.menuView != null) {
         var2 = new SparseArray();
         this.menuView.saveHierarchyState(var2);
         var1.putSparseParcelableArray("android:menu:list", var2);
      }

      NavigationMenuAdapter var3 = this.adapter;
      if (var3 != null) {
         var1.putBundle("android:menu:adapter", var3.createInstanceState());
      }

      if (this.headerLayout != null) {
         var2 = new SparseArray();
         this.headerLayout.saveHierarchyState(var2);
         var1.putSparseParcelableArray("android:menu:header", var2);
      }

      return var1;
   }

   public boolean onSubMenuSelected(SubMenuBuilder var1) {
      return false;
   }

   public void removeHeaderView(View var1) {
      this.headerLayout.removeView(var1);
      if (this.headerLayout.getChildCount() == 0) {
         NavigationMenuView var2 = this.menuView;
         var2.setPadding(0, this.paddingTopDefault, 0, var2.getPaddingBottom());
      }

   }

   public void setCallback(MenuPresenter.Callback var1) {
      this.callback = var1;
   }

   public void setCheckedItem(MenuItemImpl var1) {
      this.adapter.setCheckedItem(var1);
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setItemBackground(Drawable var1) {
      this.itemBackground = var1;
      this.updateMenuView(false);
   }

   public void setItemHorizontalPadding(int var1) {
      this.itemHorizontalPadding = var1;
      this.updateMenuView(false);
   }

   public void setItemIconPadding(int var1) {
      this.itemIconPadding = var1;
      this.updateMenuView(false);
   }

   public void setItemIconTintList(ColorStateList var1) {
      this.iconTintList = var1;
      this.updateMenuView(false);
   }

   public void setItemTextAppearance(int var1) {
      this.textAppearance = var1;
      this.textAppearanceSet = true;
      this.updateMenuView(false);
   }

   public void setItemTextColor(ColorStateList var1) {
      this.textColor = var1;
      this.updateMenuView(false);
   }

   public void setUpdateSuspended(boolean var1) {
      NavigationMenuAdapter var2 = this.adapter;
      if (var2 != null) {
         var2.setUpdateSuspended(var1);
      }

   }

   public void updateMenuView(boolean var1) {
      NavigationMenuAdapter var2 = this.adapter;
      if (var2 != null) {
         var2.update();
      }

   }

   private static class HeaderViewHolder extends ViewHolder {
      public HeaderViewHolder(View var1) {
         super(var1);
      }
   }

   private class NavigationMenuAdapter extends RecyclerView.Adapter {
      private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
      private static final String STATE_CHECKED_ITEM = "android:menu:checked";
      private static final int VIEW_TYPE_HEADER = 3;
      private static final int VIEW_TYPE_NORMAL = 0;
      private static final int VIEW_TYPE_SEPARATOR = 2;
      private static final int VIEW_TYPE_SUBHEADER = 1;
      private MenuItemImpl checkedItem;
      private final ArrayList items;
      final NavigationMenuPresenter this$0;
      private boolean updateSuspended;

      NavigationMenuAdapter(NavigationMenuPresenter var1) {
         this.this$0 = var1;
         this.items = new ArrayList();
         this.prepareMenuItems();
      }

      private void appendTransparentIconIfMissing(int var1, int var2) {
         while(var1 < var2) {
            ((NavigationMenuTextItem)this.items.get(var1)).needsEmptyIcon = true;
            ++var1;
         }

      }

      private void prepareMenuItems() {
         if (!this.updateSuspended) {
            this.updateSuspended = true;
            this.items.clear();
            this.items.add(new NavigationMenuHeaderItem());
            int var6 = -1;
            int var9 = this.this$0.menu.getVisibleItems().size();
            int var5 = 0;
            int var4 = 0;

            int var2;
            for(int var3 = var4; var5 < var9; var3 = var2) {
               MenuItemImpl var13 = (MenuItemImpl)this.this$0.menu.getVisibleItems().get(var5);
               if (var13.isChecked()) {
                  this.setCheckedItem(var13);
               }

               if (var13.isCheckable()) {
                  var13.setExclusiveCheckable(false);
               }

               int var7;
               int var8;
               if (!var13.hasSubMenu()) {
                  var8 = var13.getGroupId();
                  byte var14;
                  if (var8 != var6) {
                     var4 = this.items.size();
                     byte var16;
                     if (var13.getIcon() != null) {
                        var16 = 1;
                     } else {
                        var16 = 0;
                     }

                     var14 = var16;
                     var2 = var4;
                     if (var5 != 0) {
                        var2 = var4 + 1;
                        this.items.add(new NavigationMenuSeparatorItem(this.this$0.paddingSeparator, this.this$0.paddingSeparator));
                        var14 = var16;
                     }
                  } else {
                     var14 = (byte)var4;
                     var2 = var3;
                     if (var4 == 0) {
                        var14 = (byte)var4;
                        var2 = var3;
                        if (var13.getIcon() != null) {
                           this.appendTransparentIconIfMissing(var3, this.items.size());
                           var14 = 1;
                           var2 = var3;
                        }
                     }
                  }

                  NavigationMenuTextItem var17 = new NavigationMenuTextItem(var13);
                  var17.needsEmptyIcon = (boolean)var14;
                  this.items.add(var17);
                  var7 = var14;
               } else {
                  SubMenu var12 = var13.getSubMenu();
                  var8 = var6;
                  var7 = var4;
                  var2 = var3;
                  if (var12.hasVisibleItems()) {
                     if (var5 != 0) {
                        this.items.add(new NavigationMenuSeparatorItem(this.this$0.paddingSeparator, 0));
                     }

                     this.items.add(new NavigationMenuTextItem(var13));
                     int var10 = this.items.size();
                     var8 = var12.size();
                     var7 = 0;

                     boolean var1;
                     boolean var15;
                     for(var1 = false; var7 < var8; var1 = var15) {
                        MenuItemImpl var11 = (MenuItemImpl)var12.getItem(var7);
                        var15 = var1;
                        if (var11.isVisible()) {
                           var15 = var1;
                           if (!var1) {
                              var15 = var1;
                              if (var11.getIcon() != null) {
                                 var15 = true;
                              }
                           }

                           if (var11.isCheckable()) {
                              var11.setExclusiveCheckable(false);
                           }

                           if (var13.isChecked()) {
                              this.setCheckedItem(var13);
                           }

                           this.items.add(new NavigationMenuTextItem(var11));
                        }

                        ++var7;
                     }

                     var8 = var6;
                     var7 = var4;
                     var2 = var3;
                     if (var1) {
                        this.appendTransparentIconIfMissing(var10, this.items.size());
                        var8 = var6;
                        var7 = var4;
                        var2 = var3;
                     }
                  }
               }

               ++var5;
               var6 = var8;
               var4 = var7;
            }

            this.updateSuspended = false;
         }
      }

      public Bundle createInstanceState() {
         Bundle var4 = new Bundle();
         MenuItemImpl var3 = this.checkedItem;
         if (var3 != null) {
            var4.putInt("android:menu:checked", var3.getItemId());
         }

         SparseArray var5 = new SparseArray();
         int var1 = 0;

         for(int var2 = this.items.size(); var1 < var2; ++var1) {
            NavigationMenuItem var8 = (NavigationMenuItem)this.items.get(var1);
            if (var8 instanceof NavigationMenuTextItem) {
               MenuItemImpl var6 = ((NavigationMenuTextItem)var8).getMenuItem();
               View var9;
               if (var6 != null) {
                  var9 = var6.getActionView();
               } else {
                  var9 = null;
               }

               if (var9 != null) {
                  ParcelableSparseArray var7 = new ParcelableSparseArray();
                  var9.saveHierarchyState(var7);
                  var5.put(var6.getItemId(), var7);
               }
            }
         }

         var4.putSparseParcelableArray("android:menu:action_views", var5);
         return var4;
      }

      public MenuItemImpl getCheckedItem() {
         return this.checkedItem;
      }

      public int getItemCount() {
         return this.items.size();
      }

      public long getItemId(int var1) {
         return (long)var1;
      }

      public int getItemViewType(int var1) {
         NavigationMenuItem var2 = (NavigationMenuItem)this.items.get(var1);
         if (var2 instanceof NavigationMenuSeparatorItem) {
            return 2;
         } else if (var2 instanceof NavigationMenuHeaderItem) {
            return 3;
         } else if (var2 instanceof NavigationMenuTextItem) {
            return ((NavigationMenuTextItem)var2).getMenuItem().hasSubMenu() ? 1 : 0;
         } else {
            throw new RuntimeException("Unknown item type.");
         }
      }

      public void onBindViewHolder(ViewHolder var1, int var2) {
         int var3 = this.getItemViewType(var2);
         if (var3 != 0) {
            if (var3 != 1) {
               if (var3 == 2) {
                  NavigationMenuSeparatorItem var4 = (NavigationMenuSeparatorItem)this.items.get(var2);
                  var1.itemView.setPadding(0, var4.getPaddingTop(), 0, var4.getPaddingBottom());
               }
            } else {
               ((TextView)var1.itemView).setText(((NavigationMenuTextItem)this.items.get(var2)).getMenuItem().getTitle());
            }
         } else {
            NavigationMenuItemView var7 = (NavigationMenuItemView)var1.itemView;
            var7.setIconTintList(this.this$0.iconTintList);
            if (this.this$0.textAppearanceSet) {
               var7.setTextAppearance(this.this$0.textAppearance);
            }

            if (this.this$0.textColor != null) {
               var7.setTextColor(this.this$0.textColor);
            }

            Drawable var5;
            if (this.this$0.itemBackground != null) {
               var5 = this.this$0.itemBackground.getConstantState().newDrawable();
            } else {
               var5 = null;
            }

            ViewCompat.setBackground(var7, var5);
            NavigationMenuTextItem var6 = (NavigationMenuTextItem)this.items.get(var2);
            var7.setNeedsEmptyIcon(var6.needsEmptyIcon);
            var7.setHorizontalPadding(this.this$0.itemHorizontalPadding);
            var7.setIconPadding(this.this$0.itemIconPadding);
            var7.initialize(var6.getMenuItem(), 0);
         }

      }

      public ViewHolder onCreateViewHolder(ViewGroup var1, int var2) {
         if (var2 != 0) {
            if (var2 != 1) {
               if (var2 != 2) {
                  return var2 != 3 ? null : new HeaderViewHolder(this.this$0.headerLayout);
               } else {
                  return new SeparatorViewHolder(this.this$0.layoutInflater, var1);
               }
            } else {
               return new SubheaderViewHolder(this.this$0.layoutInflater, var1);
            }
         } else {
            return new NormalViewHolder(this.this$0.layoutInflater, var1, this.this$0.onClickListener);
         }
      }

      public void onViewRecycled(ViewHolder var1) {
         if (var1 instanceof NormalViewHolder) {
            ((NavigationMenuItemView)var1.itemView).recycle();
         }

      }

      public void restoreInstanceState(Bundle var1) {
         byte var3 = 0;
         int var4 = var1.getInt("android:menu:checked", 0);
         int var2;
         NavigationMenuItem var6;
         if (var4 != 0) {
            this.updateSuspended = true;
            int var5 = this.items.size();

            for(var2 = 0; var2 < var5; ++var2) {
               var6 = (NavigationMenuItem)this.items.get(var2);
               if (var6 instanceof NavigationMenuTextItem) {
                  MenuItemImpl var9 = ((NavigationMenuTextItem)var6).getMenuItem();
                  if (var9 != null && var9.getItemId() == var4) {
                     this.setCheckedItem(var9);
                     break;
                  }
               }
            }

            this.updateSuspended = false;
            this.prepareMenuItems();
         }

         SparseArray var8 = var1.getSparseParcelableArray("android:menu:action_views");
         if (var8 != null) {
            var4 = this.items.size();

            for(var2 = var3; var2 < var4; ++var2) {
               var6 = (NavigationMenuItem)this.items.get(var2);
               if (var6 instanceof NavigationMenuTextItem) {
                  MenuItemImpl var7 = ((NavigationMenuTextItem)var6).getMenuItem();
                  if (var7 != null) {
                     View var10 = var7.getActionView();
                     if (var10 != null) {
                        ParcelableSparseArray var11 = (ParcelableSparseArray)var8.get(var7.getItemId());
                        if (var11 != null) {
                           var10.restoreHierarchyState(var11);
                        }
                     }
                  }
               }
            }
         }

      }

      public void setCheckedItem(MenuItemImpl var1) {
         if (this.checkedItem != var1 && var1.isCheckable()) {
            MenuItemImpl var2 = this.checkedItem;
            if (var2 != null) {
               var2.setChecked(false);
            }

            this.checkedItem = var1;
            var1.setChecked(true);
         }

      }

      public void setUpdateSuspended(boolean var1) {
         this.updateSuspended = var1;
      }

      public void update() {
         this.prepareMenuItems();
         this.notifyDataSetChanged();
      }
   }

   private static class NavigationMenuHeaderItem implements NavigationMenuItem {
      NavigationMenuHeaderItem() {
      }
   }

   private interface NavigationMenuItem {
   }

   private static class NavigationMenuSeparatorItem implements NavigationMenuItem {
      private final int paddingBottom;
      private final int paddingTop;

      public NavigationMenuSeparatorItem(int var1, int var2) {
         this.paddingTop = var1;
         this.paddingBottom = var2;
      }

      public int getPaddingBottom() {
         return this.paddingBottom;
      }

      public int getPaddingTop() {
         return this.paddingTop;
      }
   }

   private static class NavigationMenuTextItem implements NavigationMenuItem {
      private final MenuItemImpl menuItem;
      boolean needsEmptyIcon;

      NavigationMenuTextItem(MenuItemImpl var1) {
         this.menuItem = var1;
      }

      public MenuItemImpl getMenuItem() {
         return this.menuItem;
      }
   }

   private static class NormalViewHolder extends ViewHolder {
      public NormalViewHolder(LayoutInflater var1, ViewGroup var2, View.OnClickListener var3) {
         super(var1.inflate(R.layout.design_navigation_item, var2, false));
         this.itemView.setOnClickListener(var3);
      }
   }

   private static class SeparatorViewHolder extends ViewHolder {
      public SeparatorViewHolder(LayoutInflater var1, ViewGroup var2) {
         super(var1.inflate(R.layout.design_navigation_item_separator, var2, false));
      }
   }

   private static class SubheaderViewHolder extends ViewHolder {
      public SubheaderViewHolder(LayoutInflater var1, ViewGroup var2) {
         super(var1.inflate(R.layout.design_navigation_item_subheader, var2, false));
      }
   }

   private abstract static class ViewHolder extends RecyclerView.ViewHolder {
      public ViewHolder(View var1) {
         super(var1);
      }
   }
}
