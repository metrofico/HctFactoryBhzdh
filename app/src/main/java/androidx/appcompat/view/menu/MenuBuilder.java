package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ActionProvider;
import androidx.core.view.ViewConfigurationCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder implements SupportMenu {
   private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
   private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
   private static final String PRESENTER_KEY = "android:menu:presenters";
   private static final String TAG = "MenuBuilder";
   private static final int[] sCategoryToOrder = new int[]{1, 4, 5, 3, 2, 0};
   private ArrayList mActionItems;
   private Callback mCallback;
   private final Context mContext;
   private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
   private int mDefaultShowAsAction = 0;
   private MenuItemImpl mExpandedItem;
   private boolean mGroupDividerEnabled = false;
   Drawable mHeaderIcon;
   CharSequence mHeaderTitle;
   View mHeaderView;
   private boolean mIsActionItemsStale;
   private boolean mIsClosing = false;
   private boolean mIsVisibleItemsStale;
   private ArrayList mItems;
   private boolean mItemsChangedWhileDispatchPrevented = false;
   private ArrayList mNonActionItems;
   private boolean mOptionalIconsVisible = false;
   private boolean mOverrideVisibleItems;
   private CopyOnWriteArrayList mPresenters = new CopyOnWriteArrayList();
   private boolean mPreventDispatchingItemsChanged = false;
   private boolean mQwertyMode;
   private final Resources mResources;
   private boolean mShortcutsVisible;
   private boolean mStructureChangedWhileDispatchPrevented = false;
   private ArrayList mTempShortcutItemList = new ArrayList();
   private ArrayList mVisibleItems;

   public MenuBuilder(Context var1) {
      this.mContext = var1;
      this.mResources = var1.getResources();
      this.mItems = new ArrayList();
      this.mVisibleItems = new ArrayList();
      this.mIsVisibleItemsStale = true;
      this.mActionItems = new ArrayList();
      this.mNonActionItems = new ArrayList();
      this.mIsActionItemsStale = true;
      this.setShortcutsVisibleInner(true);
   }

   private MenuItemImpl createNewMenuItem(int var1, int var2, int var3, int var4, CharSequence var5, int var6) {
      return new MenuItemImpl(this, var1, var2, var3, var4, var5, var6);
   }

   private void dispatchPresenterUpdate(boolean var1) {
      if (!this.mPresenters.isEmpty()) {
         this.stopDispatchingItemsChanged();
         Iterator var4 = this.mPresenters.iterator();

         while(var4.hasNext()) {
            WeakReference var3 = (WeakReference)var4.next();
            MenuPresenter var2 = (MenuPresenter)var3.get();
            if (var2 == null) {
               this.mPresenters.remove(var3);
            } else {
               var2.updateMenuView(var1);
            }
         }

         this.startDispatchingItemsChanged();
      }
   }

   private void dispatchRestoreInstanceState(Bundle var1) {
      SparseArray var3 = var1.getSparseParcelableArray("android:menu:presenters");
      if (var3 != null && !this.mPresenters.isEmpty()) {
         Iterator var4 = this.mPresenters.iterator();

         while(var4.hasNext()) {
            WeakReference var5 = (WeakReference)var4.next();
            MenuPresenter var7 = (MenuPresenter)var5.get();
            if (var7 == null) {
               this.mPresenters.remove(var5);
            } else {
               int var2 = var7.getId();
               if (var2 > 0) {
                  Parcelable var6 = (Parcelable)var3.get(var2);
                  if (var6 != null) {
                     var7.onRestoreInstanceState(var6);
                  }
               }
            }
         }
      }

   }

   private void dispatchSaveInstanceState(Bundle var1) {
      if (!this.mPresenters.isEmpty()) {
         SparseArray var3 = new SparseArray();
         Iterator var4 = this.mPresenters.iterator();

         while(var4.hasNext()) {
            WeakReference var6 = (WeakReference)var4.next();
            MenuPresenter var5 = (MenuPresenter)var6.get();
            if (var5 == null) {
               this.mPresenters.remove(var6);
            } else {
               int var2 = var5.getId();
               if (var2 > 0) {
                  Parcelable var7 = var5.onSaveInstanceState();
                  if (var7 != null) {
                     var3.put(var2, var7);
                  }
               }
            }
         }

         var1.putSparseParcelableArray("android:menu:presenters", var3);
      }
   }

   private boolean dispatchSubMenuSelected(SubMenuBuilder var1, MenuPresenter var2) {
      boolean var4 = this.mPresenters.isEmpty();
      boolean var3 = false;
      if (var4) {
         return false;
      } else {
         if (var2 != null) {
            var3 = var2.onSubMenuSelected(var1);
         }

         Iterator var5 = this.mPresenters.iterator();

         while(var5.hasNext()) {
            WeakReference var6 = (WeakReference)var5.next();
            var2 = (MenuPresenter)var6.get();
            if (var2 == null) {
               this.mPresenters.remove(var6);
            } else if (!var3) {
               var3 = var2.onSubMenuSelected(var1);
            }
         }

         return var3;
      }
   }

   private static int findInsertIndex(ArrayList var0, int var1) {
      for(int var2 = var0.size() - 1; var2 >= 0; --var2) {
         if (((MenuItemImpl)var0.get(var2)).getOrdering() <= var1) {
            return var2 + 1;
         }
      }

      return 0;
   }

   private static int getOrdering(int var0) {
      int var1 = (-65536 & var0) >> 16;
      if (var1 >= 0) {
         int[] var2 = sCategoryToOrder;
         if (var1 < var2.length) {
            return var0 & '\uffff' | var2[var1] << 16;
         }
      }

      throw new IllegalArgumentException("order does not contain a valid category.");
   }

   private void removeItemAtInt(int var1, boolean var2) {
      if (var1 >= 0 && var1 < this.mItems.size()) {
         this.mItems.remove(var1);
         if (var2) {
            this.onItemsChanged(true);
         }
      }

   }

   private void setHeaderInternal(int var1, CharSequence var2, int var3, Drawable var4, View var5) {
      Resources var6 = this.getResources();
      if (var5 != null) {
         this.mHeaderView = var5;
         this.mHeaderTitle = null;
         this.mHeaderIcon = null;
      } else {
         if (var1 > 0) {
            this.mHeaderTitle = var6.getText(var1);
         } else if (var2 != null) {
            this.mHeaderTitle = var2;
         }

         if (var3 > 0) {
            this.mHeaderIcon = ContextCompat.getDrawable(this.getContext(), var3);
         } else if (var4 != null) {
            this.mHeaderIcon = var4;
         }

         this.mHeaderView = null;
      }

      this.onItemsChanged(false);
   }

   private void setShortcutsVisibleInner(boolean var1) {
      boolean var2 = true;
      if (var1 && this.mResources.getConfiguration().keyboard != 1 && ViewConfigurationCompat.shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration.get(this.mContext), this.mContext)) {
         var1 = var2;
      } else {
         var1 = false;
      }

      this.mShortcutsVisible = var1;
   }

   public MenuItem add(int var1) {
      return this.addInternal(0, 0, 0, this.mResources.getString(var1));
   }

   public MenuItem add(int var1, int var2, int var3, int var4) {
      return this.addInternal(var1, var2, var3, this.mResources.getString(var4));
   }

   public MenuItem add(int var1, int var2, int var3, CharSequence var4) {
      return this.addInternal(var1, var2, var3, var4);
   }

   public MenuItem add(CharSequence var1) {
      return this.addInternal(0, 0, 0, var1);
   }

   public int addIntentOptions(int var1, int var2, int var3, ComponentName var4, Intent[] var5, Intent var6, int var7, MenuItem[] var8) {
      PackageManager var12 = this.mContext.getPackageManager();
      byte var11 = 0;
      List var13 = var12.queryIntentActivityOptions(var4, var5, var6, 0);
      int var9;
      if (var13 != null) {
         var9 = var13.size();
      } else {
         var9 = 0;
      }

      int var10 = var11;
      if ((var7 & 1) == 0) {
         this.removeGroup(var1);
         var10 = var11;
      }

      for(; var10 < var9; ++var10) {
         ResolveInfo var14 = (ResolveInfo)var13.get(var10);
         Intent var15;
         if (var14.specificIndex < 0) {
            var15 = var6;
         } else {
            var15 = var5[var14.specificIndex];
         }

         var15 = new Intent(var15);
         var15.setComponent(new ComponentName(var14.activityInfo.applicationInfo.packageName, var14.activityInfo.name));
         MenuItem var16 = this.add(var1, var2, var3, var14.loadLabel(var12)).setIcon(var14.loadIcon(var12)).setIntent(var15);
         if (var8 != null && var14.specificIndex >= 0) {
            var8[var14.specificIndex] = var16;
         }
      }

      return var9;
   }

   protected MenuItem addInternal(int var1, int var2, int var3, CharSequence var4) {
      int var5 = getOrdering(var3);
      MenuItemImpl var8 = this.createNewMenuItem(var1, var2, var3, var5, var4, this.mDefaultShowAsAction);
      ContextMenu.ContextMenuInfo var6 = this.mCurrentMenuInfo;
      if (var6 != null) {
         var8.setMenuInfo(var6);
      }

      ArrayList var7 = this.mItems;
      var7.add(findInsertIndex(var7, var5), var8);
      this.onItemsChanged(true);
      return var8;
   }

   public void addMenuPresenter(MenuPresenter var1) {
      this.addMenuPresenter(var1, this.mContext);
   }

   public void addMenuPresenter(MenuPresenter var1, Context var2) {
      this.mPresenters.add(new WeakReference(var1));
      var1.initForMenu(var2, this);
      this.mIsActionItemsStale = true;
   }

   public SubMenu addSubMenu(int var1) {
      return this.addSubMenu(0, 0, 0, this.mResources.getString(var1));
   }

   public SubMenu addSubMenu(int var1, int var2, int var3, int var4) {
      return this.addSubMenu(var1, var2, var3, this.mResources.getString(var4));
   }

   public SubMenu addSubMenu(int var1, int var2, int var3, CharSequence var4) {
      MenuItemImpl var6 = (MenuItemImpl)this.addInternal(var1, var2, var3, var4);
      SubMenuBuilder var5 = new SubMenuBuilder(this.mContext, this, var6);
      var6.setSubMenu(var5);
      return var5;
   }

   public SubMenu addSubMenu(CharSequence var1) {
      return this.addSubMenu(0, 0, 0, var1);
   }

   public void changeMenuMode() {
      Callback var1 = this.mCallback;
      if (var1 != null) {
         var1.onMenuModeChange(this);
      }

   }

   public void clear() {
      MenuItemImpl var1 = this.mExpandedItem;
      if (var1 != null) {
         this.collapseItemActionView(var1);
      }

      this.mItems.clear();
      this.onItemsChanged(true);
   }

   public void clearAll() {
      this.mPreventDispatchingItemsChanged = true;
      this.clear();
      this.clearHeader();
      this.mPresenters.clear();
      this.mPreventDispatchingItemsChanged = false;
      this.mItemsChangedWhileDispatchPrevented = false;
      this.mStructureChangedWhileDispatchPrevented = false;
      this.onItemsChanged(true);
   }

   public void clearHeader() {
      this.mHeaderIcon = null;
      this.mHeaderTitle = null;
      this.mHeaderView = null;
      this.onItemsChanged(false);
   }

   public void close() {
      this.close(true);
   }

   public final void close(boolean var1) {
      if (!this.mIsClosing) {
         this.mIsClosing = true;
         Iterator var3 = this.mPresenters.iterator();

         while(var3.hasNext()) {
            WeakReference var2 = (WeakReference)var3.next();
            MenuPresenter var4 = (MenuPresenter)var2.get();
            if (var4 == null) {
               this.mPresenters.remove(var2);
            } else {
               var4.onCloseMenu(this, var1);
            }
         }

         this.mIsClosing = false;
      }
   }

   public boolean collapseItemActionView(MenuItemImpl var1) {
      boolean var5 = this.mPresenters.isEmpty();
      boolean var4 = false;
      boolean var3 = false;
      boolean var2 = var4;
      if (!var5) {
         if (this.mExpandedItem != var1) {
            var2 = var4;
         } else {
            this.stopDispatchingItemsChanged();
            Iterator var8 = this.mPresenters.iterator();
            var2 = var3;

            while(true) {
               var3 = var2;
               if (!var8.hasNext()) {
                  break;
               }

               WeakReference var6 = (WeakReference)var8.next();
               MenuPresenter var7 = (MenuPresenter)var6.get();
               if (var7 == null) {
                  this.mPresenters.remove(var6);
               } else {
                  var3 = var7.collapseItemActionView(this, var1);
                  var2 = var3;
                  if (var3) {
                     break;
                  }
               }
            }

            this.startDispatchingItemsChanged();
            var2 = var3;
            if (var3) {
               this.mExpandedItem = null;
               var2 = var3;
            }
         }
      }

      return var2;
   }

   boolean dispatchMenuItemSelected(MenuBuilder var1, MenuItem var2) {
      Callback var4 = this.mCallback;
      boolean var3;
      if (var4 != null && var4.onMenuItemSelected(var1, var2)) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean expandItemActionView(MenuItemImpl var1) {
      boolean var3 = this.mPresenters.isEmpty();
      boolean var2 = false;
      if (var3) {
         return false;
      } else {
         this.stopDispatchingItemsChanged();
         Iterator var4 = this.mPresenters.iterator();

         while(true) {
            var3 = var2;
            if (!var4.hasNext()) {
               break;
            }

            WeakReference var6 = (WeakReference)var4.next();
            MenuPresenter var5 = (MenuPresenter)var6.get();
            if (var5 == null) {
               this.mPresenters.remove(var6);
            } else {
               var3 = var5.expandItemActionView(this, var1);
               var2 = var3;
               if (var3) {
                  break;
               }
            }
         }

         this.startDispatchingItemsChanged();
         if (var3) {
            this.mExpandedItem = var1;
         }

         return var3;
      }
   }

   public int findGroupIndex(int var1) {
      return this.findGroupIndex(var1, 0);
   }

   public int findGroupIndex(int var1, int var2) {
      int var4 = this.size();
      int var3 = var2;
      if (var2 < 0) {
         var3 = 0;
      }

      while(var3 < var4) {
         if (((MenuItemImpl)this.mItems.get(var3)).getGroupId() == var1) {
            return var3;
         }

         ++var3;
      }

      return -1;
   }

   public MenuItem findItem(int var1) {
      int var3 = this.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         MenuItemImpl var4 = (MenuItemImpl)this.mItems.get(var2);
         if (var4.getItemId() == var1) {
            return var4;
         }

         if (var4.hasSubMenu()) {
            MenuItem var5 = var4.getSubMenu().findItem(var1);
            if (var5 != null) {
               return var5;
            }
         }
      }

      return null;
   }

   public int findItemIndex(int var1) {
      int var3 = this.size();

      for(int var2 = 0; var2 < var3; ++var2) {
         if (((MenuItemImpl)this.mItems.get(var2)).getItemId() == var1) {
            return var2;
         }
      }

      return -1;
   }

   MenuItemImpl findItemWithShortcutForKey(int var1, KeyEvent var2) {
      ArrayList var9 = this.mTempShortcutItemList;
      var9.clear();
      this.findItemsWithShortcutForKey(var9, var1, var2);
      if (var9.isEmpty()) {
         return null;
      } else {
         int var6 = var2.getMetaState();
         KeyCharacterMap.KeyData var8 = new KeyCharacterMap.KeyData();
         var2.getKeyData(var8);
         int var5 = var9.size();
         if (var5 == 1) {
            return (MenuItemImpl)var9.get(0);
         } else {
            boolean var7 = this.isQwertyMode();

            for(int var3 = 0; var3 < var5; ++var3) {
               MenuItemImpl var10 = (MenuItemImpl)var9.get(var3);
               char var4;
               if (var7) {
                  var4 = var10.getAlphabeticShortcut();
               } else {
                  var4 = var10.getNumericShortcut();
               }

               if (var4 == var8.meta[0] && (var6 & 2) == 0 || var4 == var8.meta[2] && (var6 & 2) != 0 || var7 && var4 == '\b' && var1 == 67) {
                  return var10;
               }
            }

            return null;
         }
      }
   }

   void findItemsWithShortcutForKey(List var1, int var2, KeyEvent var3) {
      boolean var9 = this.isQwertyMode();
      int var7 = var3.getModifiers();
      KeyCharacterMap.KeyData var10 = new KeyCharacterMap.KeyData();
      if (var3.getKeyData(var10) || var2 == 67) {
         int var8 = this.mItems.size();

         for(int var4 = 0; var4 < var8; ++var4) {
            MenuItemImpl var11 = (MenuItemImpl)this.mItems.get(var4);
            if (var11.hasSubMenu()) {
               ((MenuBuilder)var11.getSubMenu()).findItemsWithShortcutForKey(var1, var2, var3);
            }

            char var5;
            if (var9) {
               var5 = var11.getAlphabeticShortcut();
            } else {
               var5 = var11.getNumericShortcut();
            }

            int var6;
            if (var9) {
               var6 = var11.getAlphabeticModifiers();
            } else {
               var6 = var11.getNumericModifiers();
            }

            boolean var12;
            if ((var7 & 69647) == (var6 & 69647)) {
               var12 = true;
            } else {
               var12 = false;
            }

            if (var12 && var5 != 0 && (var5 == var10.meta[0] || var5 == var10.meta[2] || var9 && var5 == '\b' && var2 == 67) && var11.isEnabled()) {
               var1.add(var11);
            }
         }

      }
   }

   public void flagActionItems() {
      ArrayList var3 = this.getVisibleItems();
      if (this.mIsActionItemsStale) {
         Iterator var4 = this.mPresenters.iterator();
         boolean var1 = false;

         while(var4.hasNext()) {
            WeakReference var5 = (WeakReference)var4.next();
            MenuPresenter var6 = (MenuPresenter)var5.get();
            if (var6 == null) {
               this.mPresenters.remove(var5);
            } else {
               var1 |= var6.flagActionItems();
            }
         }

         if (var1) {
            this.mActionItems.clear();
            this.mNonActionItems.clear();
            int var2 = var3.size();

            for(int var7 = 0; var7 < var2; ++var7) {
               MenuItemImpl var8 = (MenuItemImpl)var3.get(var7);
               if (var8.isActionButton()) {
                  this.mActionItems.add(var8);
               } else {
                  this.mNonActionItems.add(var8);
               }
            }
         } else {
            this.mActionItems.clear();
            this.mNonActionItems.clear();
            this.mNonActionItems.addAll(this.getVisibleItems());
         }

         this.mIsActionItemsStale = false;
      }
   }

   public ArrayList getActionItems() {
      this.flagActionItems();
      return this.mActionItems;
   }

   protected String getActionViewStatesKey() {
      return "android:menu:actionviewstates";
   }

   public Context getContext() {
      return this.mContext;
   }

   public MenuItemImpl getExpandedItem() {
      return this.mExpandedItem;
   }

   public Drawable getHeaderIcon() {
      return this.mHeaderIcon;
   }

   public CharSequence getHeaderTitle() {
      return this.mHeaderTitle;
   }

   public View getHeaderView() {
      return this.mHeaderView;
   }

   public MenuItem getItem(int var1) {
      return (MenuItem)this.mItems.get(var1);
   }

   public ArrayList getNonActionItems() {
      this.flagActionItems();
      return this.mNonActionItems;
   }

   boolean getOptionalIconsVisible() {
      return this.mOptionalIconsVisible;
   }

   Resources getResources() {
      return this.mResources;
   }

   public MenuBuilder getRootMenu() {
      return this;
   }

   public ArrayList getVisibleItems() {
      if (!this.mIsVisibleItemsStale) {
         return this.mVisibleItems;
      } else {
         this.mVisibleItems.clear();
         int var2 = this.mItems.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            MenuItemImpl var3 = (MenuItemImpl)this.mItems.get(var1);
            if (var3.isVisible()) {
               this.mVisibleItems.add(var3);
            }
         }

         this.mIsVisibleItemsStale = false;
         this.mIsActionItemsStale = true;
         return this.mVisibleItems;
      }
   }

   public boolean hasVisibleItems() {
      if (this.mOverrideVisibleItems) {
         return true;
      } else {
         int var2 = this.size();

         for(int var1 = 0; var1 < var2; ++var1) {
            if (((MenuItemImpl)this.mItems.get(var1)).isVisible()) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean isGroupDividerEnabled() {
      return this.mGroupDividerEnabled;
   }

   boolean isQwertyMode() {
      return this.mQwertyMode;
   }

   public boolean isShortcutKey(int var1, KeyEvent var2) {
      boolean var3;
      if (this.findItemWithShortcutForKey(var1, var2) != null) {
         var3 = true;
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean isShortcutsVisible() {
      return this.mShortcutsVisible;
   }

   void onItemActionRequestChanged(MenuItemImpl var1) {
      this.mIsActionItemsStale = true;
      this.onItemsChanged(true);
   }

   void onItemVisibleChanged(MenuItemImpl var1) {
      this.mIsVisibleItemsStale = true;
      this.onItemsChanged(true);
   }

   public void onItemsChanged(boolean var1) {
      if (!this.mPreventDispatchingItemsChanged) {
         if (var1) {
            this.mIsVisibleItemsStale = true;
            this.mIsActionItemsStale = true;
         }

         this.dispatchPresenterUpdate(var1);
      } else {
         this.mItemsChangedWhileDispatchPrevented = true;
         if (var1) {
            this.mStructureChangedWhileDispatchPrevented = true;
         }
      }

   }

   public boolean performIdentifierAction(int var1, int var2) {
      return this.performItemAction(this.findItem(var1), var2);
   }

   public boolean performItemAction(MenuItem var1, int var2) {
      return this.performItemAction(var1, (MenuPresenter)null, var2);
   }

   public boolean performItemAction(MenuItem var1, MenuPresenter var2, int var3) {
      MenuItemImpl var7 = (MenuItemImpl)var1;
      if (var7 != null && var7.isEnabled()) {
         boolean var6 = var7.invoke();
         ActionProvider var8 = var7.getSupportActionProvider();
         boolean var4;
         if (var8 != null && var8.hasSubMenu()) {
            var4 = true;
         } else {
            var4 = false;
         }

         boolean var5;
         if (var7.hasCollapsibleActionView()) {
            var6 |= var7.expandActionView();
            var5 = var6;
            if (var6) {
               this.close(true);
               var5 = var6;
            }
         } else if (!var7.hasSubMenu() && !var4) {
            var5 = var6;
            if ((var3 & 1) == 0) {
               this.close(true);
               var5 = var6;
            }
         } else {
            if ((var3 & 4) == 0) {
               this.close(false);
            }

            if (!var7.hasSubMenu()) {
               var7.setSubMenu(new SubMenuBuilder(this.getContext(), this, var7));
            }

            SubMenuBuilder var9 = (SubMenuBuilder)var7.getSubMenu();
            if (var4) {
               var8.onPrepareSubMenu(var9);
            }

            var6 |= this.dispatchSubMenuSelected(var9, var2);
            var5 = var6;
            if (!var6) {
               this.close(true);
               var5 = var6;
            }
         }

         return var5;
      } else {
         return false;
      }
   }

   public boolean performShortcut(int var1, KeyEvent var2, int var3) {
      MenuItemImpl var5 = this.findItemWithShortcutForKey(var1, var2);
      boolean var4;
      if (var5 != null) {
         var4 = this.performItemAction(var5, var3);
      } else {
         var4 = false;
      }

      if ((var3 & 2) != 0) {
         this.close(true);
      }

      return var4;
   }

   public void removeGroup(int var1) {
      int var3 = this.findGroupIndex(var1);
      if (var3 >= 0) {
         int var4 = this.mItems.size();

         for(int var2 = 0; var2 < var4 - var3 && ((MenuItemImpl)this.mItems.get(var3)).getGroupId() == var1; ++var2) {
            this.removeItemAtInt(var3, false);
         }

         this.onItemsChanged(true);
      }

   }

   public void removeItem(int var1) {
      this.removeItemAtInt(this.findItemIndex(var1), true);
   }

   public void removeItemAt(int var1) {
      this.removeItemAtInt(var1, true);
   }

   public void removeMenuPresenter(MenuPresenter var1) {
      Iterator var2 = this.mPresenters.iterator();

      while(true) {
         WeakReference var3;
         MenuPresenter var4;
         do {
            if (!var2.hasNext()) {
               return;
            }

            var3 = (WeakReference)var2.next();
            var4 = (MenuPresenter)var3.get();
         } while(var4 != null && var4 != var1);

         this.mPresenters.remove(var3);
      }
   }

   public void restoreActionViewStates(Bundle var1) {
      if (var1 != null) {
         SparseArray var4 = var1.getSparseParcelableArray(this.getActionViewStatesKey());
         int var3 = this.size();

         int var2;
         for(var2 = 0; var2 < var3; ++var2) {
            MenuItem var6 = this.getItem(var2);
            View var5 = var6.getActionView();
            if (var5 != null && var5.getId() != -1) {
               var5.restoreHierarchyState(var4);
            }

            if (var6.hasSubMenu()) {
               ((SubMenuBuilder)var6.getSubMenu()).restoreActionViewStates(var1);
            }
         }

         var2 = var1.getInt("android:menu:expandedactionview");
         if (var2 > 0) {
            MenuItem var7 = this.findItem(var2);
            if (var7 != null) {
               var7.expandActionView();
            }
         }

      }
   }

   public void restorePresenterStates(Bundle var1) {
      this.dispatchRestoreInstanceState(var1);
   }

   public void saveActionViewStates(Bundle var1) {
      int var3 = this.size();
      SparseArray var5 = null;

      SparseArray var6;
      for(int var2 = 0; var2 < var3; var5 = var6) {
         MenuItem var7 = this.getItem(var2);
         View var8 = var7.getActionView();
         var6 = var5;
         if (var8 != null) {
            var6 = var5;
            if (var8.getId() != -1) {
               SparseArray var4 = var5;
               if (var5 == null) {
                  var4 = new SparseArray();
               }

               var8.saveHierarchyState(var4);
               var6 = var4;
               if (var7.isActionViewExpanded()) {
                  var1.putInt("android:menu:expandedactionview", var7.getItemId());
                  var6 = var4;
               }
            }
         }

         if (var7.hasSubMenu()) {
            ((SubMenuBuilder)var7.getSubMenu()).saveActionViewStates(var1);
         }

         ++var2;
      }

      if (var5 != null) {
         var1.putSparseParcelableArray(this.getActionViewStatesKey(), var5);
      }

   }

   public void savePresenterStates(Bundle var1) {
      this.dispatchSaveInstanceState(var1);
   }

   public void setCallback(Callback var1) {
      this.mCallback = var1;
   }

   public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo var1) {
      this.mCurrentMenuInfo = var1;
   }

   public MenuBuilder setDefaultShowAsAction(int var1) {
      this.mDefaultShowAsAction = var1;
      return this;
   }

   void setExclusiveItemChecked(MenuItem var1) {
      int var3 = var1.getGroupId();
      int var4 = this.mItems.size();
      this.stopDispatchingItemsChanged();

      for(int var2 = 0; var2 < var4; ++var2) {
         MenuItemImpl var6 = (MenuItemImpl)this.mItems.get(var2);
         if (var6.getGroupId() == var3 && var6.isExclusiveCheckable() && var6.isCheckable()) {
            boolean var5;
            if (var6 == var1) {
               var5 = true;
            } else {
               var5 = false;
            }

            var6.setCheckedInt(var5);
         }
      }

      this.startDispatchingItemsChanged();
   }

   public void setGroupCheckable(int var1, boolean var2, boolean var3) {
      int var5 = this.mItems.size();

      for(int var4 = 0; var4 < var5; ++var4) {
         MenuItemImpl var6 = (MenuItemImpl)this.mItems.get(var4);
         if (var6.getGroupId() == var1) {
            var6.setExclusiveCheckable(var3);
            var6.setCheckable(var2);
         }
      }

   }

   public void setGroupDividerEnabled(boolean var1) {
      this.mGroupDividerEnabled = var1;
   }

   public void setGroupEnabled(int var1, boolean var2) {
      int var4 = this.mItems.size();

      for(int var3 = 0; var3 < var4; ++var3) {
         MenuItemImpl var5 = (MenuItemImpl)this.mItems.get(var3);
         if (var5.getGroupId() == var1) {
            var5.setEnabled(var2);
         }
      }

   }

   public void setGroupVisible(int var1, boolean var2) {
      int var6 = this.mItems.size();
      int var4 = 0;

      boolean var3;
      boolean var5;
      for(var5 = false; var4 < var6; var5 = var3) {
         MenuItemImpl var7 = (MenuItemImpl)this.mItems.get(var4);
         var3 = var5;
         if (var7.getGroupId() == var1) {
            var3 = var5;
            if (var7.setVisibleInt(var2)) {
               var3 = true;
            }
         }

         ++var4;
      }

      if (var5) {
         this.onItemsChanged(true);
      }

   }

   protected MenuBuilder setHeaderIconInt(int var1) {
      this.setHeaderInternal(0, (CharSequence)null, var1, (Drawable)null, (View)null);
      return this;
   }

   protected MenuBuilder setHeaderIconInt(Drawable var1) {
      this.setHeaderInternal(0, (CharSequence)null, 0, var1, (View)null);
      return this;
   }

   protected MenuBuilder setHeaderTitleInt(int var1) {
      this.setHeaderInternal(var1, (CharSequence)null, 0, (Drawable)null, (View)null);
      return this;
   }

   protected MenuBuilder setHeaderTitleInt(CharSequence var1) {
      this.setHeaderInternal(0, var1, 0, (Drawable)null, (View)null);
      return this;
   }

   protected MenuBuilder setHeaderViewInt(View var1) {
      this.setHeaderInternal(0, (CharSequence)null, 0, (Drawable)null, var1);
      return this;
   }

   public void setOptionalIconsVisible(boolean var1) {
      this.mOptionalIconsVisible = var1;
   }

   public void setOverrideVisibleItems(boolean var1) {
      this.mOverrideVisibleItems = var1;
   }

   public void setQwertyMode(boolean var1) {
      this.mQwertyMode = var1;
      this.onItemsChanged(false);
   }

   public void setShortcutsVisible(boolean var1) {
      if (this.mShortcutsVisible != var1) {
         this.setShortcutsVisibleInner(var1);
         this.onItemsChanged(false);
      }
   }

   public int size() {
      return this.mItems.size();
   }

   public void startDispatchingItemsChanged() {
      this.mPreventDispatchingItemsChanged = false;
      if (this.mItemsChangedWhileDispatchPrevented) {
         this.mItemsChangedWhileDispatchPrevented = false;
         this.onItemsChanged(this.mStructureChangedWhileDispatchPrevented);
      }

   }

   public void stopDispatchingItemsChanged() {
      if (!this.mPreventDispatchingItemsChanged) {
         this.mPreventDispatchingItemsChanged = true;
         this.mItemsChangedWhileDispatchPrevented = false;
         this.mStructureChangedWhileDispatchPrevented = false;
      }

   }

   public interface Callback {
      boolean onMenuItemSelected(MenuBuilder var1, MenuItem var2);

      void onMenuModeChange(MenuBuilder var1);
   }

   public interface ItemInvoker {
      boolean invokeItem(MenuItemImpl var1);
   }
}
