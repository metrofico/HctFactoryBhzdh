package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

abstract class MenuPopup implements ShowableListMenu, MenuPresenter, AdapterView.OnItemClickListener {
   private Rect mEpicenterBounds;

   protected static int measureIndividualMenuWidth(ListAdapter var0, ViewGroup var1, Context var2, int var3) {
      int var5 = 0;
      int var11 = MeasureSpec.makeMeasureSpec(0, 0);
      int var9 = MeasureSpec.makeMeasureSpec(0, 0);
      int var10 = var0.getCount();
      int var6 = 0;
      int var7 = 0;
      Object var13 = null;
      Object var12 = var1;

      for(View var14 = (View)var13; var5 < var10; var12 = var13) {
         int var8 = var0.getItemViewType(var5);
         int var4 = var7;
         if (var8 != var7) {
            var14 = null;
            var4 = var8;
         }

         var13 = var12;
         if (var12 == null) {
            var13 = new FrameLayout(var2);
         }

         var14 = var0.getView(var5, var14, (ViewGroup)var13);
         var14.measure(var11, var9);
         var8 = var14.getMeasuredWidth();
         if (var8 >= var3) {
            return var3;
         }

         var7 = var6;
         if (var8 > var6) {
            var7 = var8;
         }

         ++var5;
         var6 = var7;
         var7 = var4;
      }

      return var6;
   }

   protected static boolean shouldPreserveIconSpacing(MenuBuilder var0) {
      int var2 = var0.size();
      boolean var4 = false;
      int var1 = 0;

      boolean var3;
      while(true) {
         var3 = var4;
         if (var1 >= var2) {
            break;
         }

         MenuItem var5 = var0.getItem(var1);
         if (var5.isVisible() && var5.getIcon() != null) {
            var3 = true;
            break;
         }

         ++var1;
      }

      return var3;
   }

   protected static MenuAdapter toMenuAdapter(ListAdapter var0) {
      return var0 instanceof HeaderViewListAdapter ? (MenuAdapter)((HeaderViewListAdapter)var0).getWrappedAdapter() : (MenuAdapter)var0;
   }

   public abstract void addMenu(MenuBuilder var1);

   protected boolean closeMenuOnSubMenuOpened() {
      return true;
   }

   public boolean collapseItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   public boolean expandItemActionView(MenuBuilder var1, MenuItemImpl var2) {
      return false;
   }

   public Rect getEpicenterBounds() {
      return this.mEpicenterBounds;
   }

   public int getId() {
      return 0;
   }

   public MenuView getMenuView(ViewGroup var1) {
      throw new UnsupportedOperationException("MenuPopups manage their own views");
   }

   public void initForMenu(Context var1, MenuBuilder var2) {
   }

   public void onItemClick(AdapterView var1, View var2, int var3, long var4) {
      ListAdapter var7 = (ListAdapter)var1.getAdapter();
      MenuBuilder var6 = toMenuAdapter(var7).mAdapterMenu;
      MenuItem var8 = (MenuItem)var7.getItem(var3);
      byte var9;
      if (this.closeMenuOnSubMenuOpened()) {
         var9 = 0;
      } else {
         var9 = 4;
      }

      var6.performItemAction(var8, this, var9);
   }

   public abstract void setAnchorView(View var1);

   public void setEpicenterBounds(Rect var1) {
      this.mEpicenterBounds = var1;
   }

   public abstract void setForceShowIcon(boolean var1);

   public abstract void setGravity(int var1);

   public abstract void setHorizontalOffset(int var1);

   public abstract void setOnDismissListener(PopupWindow.OnDismissListener var1);

   public abstract void setShowTitle(boolean var1);

   public abstract void setVerticalOffset(int var1);
}
