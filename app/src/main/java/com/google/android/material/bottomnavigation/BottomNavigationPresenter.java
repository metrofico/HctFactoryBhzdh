package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;

public class BottomNavigationPresenter implements MenuPresenter {
   private int id;
   private MenuBuilder menu;
   private BottomNavigationMenuView menuView;
   private boolean updateSuspended = false;

   public boolean collapseItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   public boolean expandItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   public boolean flagActionItems() {
      return false;
   }

   public int getId() {
      return this.id;
   }

   public MenuView getMenuView(ViewGroup var1) {
      return this.menuView;
   }

   public void initForMenu(Context var1, MenuBuilder var2) {
      this.menu = var2;
      this.menuView.initialize(var2);
   }

   public void onCloseMenu(MenuBuilder var1, boolean var2) {
   }

   public void onRestoreInstanceState(Parcelable var1) {
      if (var1 instanceof SavedState) {
         this.menuView.tryRestoreSelectedItemId(((SavedState)var1).selectedItemId);
      }

   }

   public Parcelable onSaveInstanceState() {
      SavedState var1 = new SavedState();
      var1.selectedItemId = this.menuView.getSelectedItemId();
      return var1;
   }

   public boolean onSubMenuSelected(SubMenuBuilder var1) {
      return false;
   }

   public void setBottomNavigationMenuView(BottomNavigationMenuView var1) {
      this.menuView = var1;
   }

   public void setCallback(MenuPresenter.Callback var1) {
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setUpdateSuspended(boolean var1) {
      this.updateSuspended = var1;
   }

   public void updateMenuView(boolean var1) {
      if (!this.updateSuspended) {
         if (var1) {
            this.menuView.buildMenuView();
         } else {
            this.menuView.updateMenuView();
         }

      }
   }

   static class SavedState implements Parcelable {
      public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
         public SavedState createFromParcel(Parcel var1) {
            return new SavedState(var1);
         }

         public SavedState[] newArray(int var1) {
            return new SavedState[var1];
         }
      };
      int selectedItemId;

      SavedState() {
      }

      SavedState(Parcel var1) {
         this.selectedItemId = var1.readInt();
      }

      public int describeContents() {
         return 0;
      }

      public void writeToParcel(Parcel var1, int var2) {
         var1.writeInt(this.selectedItemId);
      }
   }
}
